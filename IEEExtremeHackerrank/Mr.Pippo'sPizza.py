from __future__ import print_function
from math import factorial

# get first input from the user
ways_of_cutting = int(raw_input())

while ways_of_cutting:
    
    # first 3 common ways_of_cutting and their results
    if ways_of_cutting == 1:
        print(3)
    elif ways_of_cutting == 2:
        print(4)
    elif ways_of_cutting == 5:
        print(5)
    
    # calculate and print N if it is catalan-results equals to input
    else:
        for n in range(5, 1000):
            if factorial(2 * (n - 2)) / (factorial(n + 1 - 2) * factorial(n - 2)) == ways_of_cutting:
                print(n)
                break

    # get next input from the user
    try:
        line = raw_input()
    except:
        break
    ways_of_cutting = int(line)