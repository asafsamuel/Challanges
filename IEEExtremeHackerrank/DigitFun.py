s = input()

while s != "END":
    size = str(len(s)) 
    index = 1
    while str(size) != s:
        s = size
        size = str(len(s))
        index += 1
        
    print(index)
    s = input()