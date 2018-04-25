def main():
    width = int(input())
    height = int(input())

    alphabet_size = int(input())
    alphabet = {}

    for i in range(alphabet_size):
        ch = input()
        zoomed = []
        
        for h in range(height):
            line = input()
            line = line.ljust(width)
            zoomed.append(line)
            
        alphabet[ch] = zoomed

    num_strings = int(input())
    strings = []
    
    for i in range(num_strings):
        strings.append(input())

    for phrase in strings:
        for h in range(height):
            for ch in phrase:
                print(alphabet[ch][h], end="")
            print()


if __name__ == '__main__':
    main()