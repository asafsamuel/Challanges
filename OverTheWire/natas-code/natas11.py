#!/usr/bin/python3

import base64
import itertools

ENCRYPTED_DATA = 'ClVLIh4ASCsCBE8lAxMacFMZV2hdVVotEhhUJQNVAmhSEV4sFxFeaAw='
DECRYPTED_DATA = '{"showpassword":"no","bgcolor":"#ffffff"}'
DATA = '{"showpassword":"yes","bgcolor":"#ffffff"}'


def get_shortest_repeated_str(word: str) -> str:
    """
    Returns a shortest period of group of letters which would eventually create the given word.
    for example: abkeabkeabke -> abke
    O(n) solution.
    """
    pattern_end = 0
    for j in range(pattern_end + 1, len(word)):
        pattern_dex = j % (pattern_end + 1)
        if word[pattern_dex] != word[j]:
            pattern_end = j
            continue
        if j == len(word) - 1:
            return word[0: pattern_end + 1]
    return word


def find_key(encrypted_data: str, decrypted_data: str) -> str:
    """
    Find the key that was used for encrypting the data.
    """
    # decode with Base64-Decoder
    b64decode_result = base64.b64decode(encrypted_data)

    # XOR result with decrypted_data
    # k ^ d = e ==> k ^ d ^ d = e ^ d ==> k = e ^ d
    circular_key = ''.join(chr(int(a) ^ ord(b))
                           for a, b in zip(b64decode_result, decrypted_data))

    # return shortest letters which can create the word
    key = get_shortest_repeated_str(circular_key)
    return key


def encrypt(data, key):
    """
    Encrypt the data using base64-encode and xor with the key.
    """
    # Xor with key
    xor_result = bytes(itertools.starmap(lambda a, b: ord(
        a) ^ ord(b), zip(data, itertools.cycle(key))))
    # encode with Base64-Encoder
    b64encode_result = base64.b64encode(xor_result)
    return b64encode_result


def main():
    key = find_key(ENCRYPTED_DATA, DECRYPTED_DATA)
    print("- Encrypt data:", ENCRYPTED_DATA)
    print("- Decrypt data:", DECRYPTED_DATA)
    print("* Key used:", key)

    encoded_data = encrypt(DATA, key)
    print("\n**************************************************************************************")
    print("** Cookie's data value:", encoded_data, "**")
    print("**************************************************************************************")


if __name__ == "__main__":
    main()
