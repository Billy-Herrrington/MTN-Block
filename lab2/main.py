from uuid import uuid4
from flask import Flask, jsonify, request, render_template
from MTN import Blockchain


app = Flask(__name__)
node_identifier = str(uuid4()).replace('-', '')
blockchain = Blockchain()

@app.route('/')
@app.route('/home')
def home():
    return render_template("home.html")

@app.route('/mine', methods=['GET'])
def mine():
    last_block = blockchain.mtn_last_block
    last_proof = last_block['proof']
    proof = blockchain.mtn_proof_of_work(last_proof)
    blockchain.mtn_new_transaction(
        sender = "0",
        recipient = node_identifier,
        amount = 1,
    )
    previous_hash = blockchain.mtn_hash(last_block)
    block = blockchain.mtn_new_block(proof, previous_hash)
    response = {
        'message': "New Block Forged",
        'index': block['index'],
        'transactions': block['transactions'],
        'proof': block['proof'],
        'previous_hash': block['previous_hash'],
    }
    return jsonify(response), 200

@app.route('/chain', methods=['GET'])
def full_chain():

    response = {
        'chain': (blockchain.chain,),
        'length': len(blockchain.chain),
    }
    return jsonify(response), 200

@app.route('/transactions/new', methods=['POST'])
def new_transaction():
    values = request.get_json()
    required = ['sender', 'recipient', 'amount']
    if not all(k in values for k in required):
        return 'Missing values', 400
    index = blockchain.mtn_new_transaction(values['sender'], values['recipient'], values['amount'])
    response = {'message': f'Transaction will be added to Block {index}'}
    return jsonify(response), 201

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)






