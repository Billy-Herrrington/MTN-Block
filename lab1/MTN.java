import hashlib
import json
from time import time


class Blockchain(object):
    def __init__(self):
        self.current_transactions = []
        self.chain = []
        self.mtn_new_block(previous_hash='Mai', proof=260202)

    def mtn_new_block(self, proof, previous_hash=None):
        block = {
            'index': len(self.chain) + 1,
            'timestamp': time(),
            'transactions': self.current_transactions,
            'proof': proof,
            'previous_hash': previous_hash or self.mtn_hash(self.chain[-1]),
        }

        self.current_transactions = []
        self.chain.append(block)
        return block

    def mtn_new_transaction(self, sender, recipient, amount):
        self.current_transactions.append({
            'sender': sender,
            'recipient': recipient,
            'amount': amount,
        })

        return self.mtn_last_block['index'] + 1

    @staticmethod
    def mtn_hash(block):
        block_string = json.dumps(block, sort_keys=True).encode()
        return hashlib.sha256(block_string).hexdigest()

    @property
    def mtn_last_block(self):
        return self.chain[-1]

    def mtn_proof_of_work(self, last_proof):
        proof = 0
        while self.mtn_valid_proof(last_proof, proof) is False:
            proof += 1
        return proof

    @staticmethod
    def mtn_valid_proof(last_proof, proof):
        guess = f'{last_proof}{proof}'.encode()
        guess_hash = hashlib.sha256(guess).hexdigest()
        return guess_hash[:2] == "02"


bChain = Blockchain()
print(json.dumps(bChain.mtn_last_block, indent=2))

bChain.mtn_new_transaction("Mai", "Mark", 15)
print(bChain.mtn_proof_of_work(260202))
bChain.mtn_new_block("260202")
print(json.dumps(bChain.mtn_last_block, indent=2))
print(bChain.mtn_proof_of_work(260202))

bChain.mtn_new_transaction("Mai", "Max", 777)
print(bChain.mtn_proof_of_work(260202))
bChain.mtn_new_block("260202")
print(json.dumps(bChain.mtn_last_block, indent=2))
print(bChain.mtn_proof_of_work(260202))
