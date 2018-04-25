line1 = input()
a1 = input()
a2 = input()
line4 = input()

base, symbols = line1.split(' ')
m = {}
m[' '] = 0 # space = 0

base = int(base)

# from char to value
for i, c in enumerate(symbols):
    m[c] = i
    
total = 0
addin = 0
result = ''

# add calculation
for i in range(len(a1)-1, 0, -1):
    total = m[a1[i]] + m[a2[i]] + addin
    addin = total // base
    result += symbols[total % base]
    
print(line1)
print(a1)
print(a2)
print(line4)
print(' '*(len(a1)-len(result)) + result[::-1])