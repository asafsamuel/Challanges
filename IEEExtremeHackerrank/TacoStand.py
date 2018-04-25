days = int(raw_input())

while(days > 0):
    shells, meat, rice, beans = raw_input().split()

    shells = int(shells)
    meat = int(meat)
    rice = int(rice)
    beans = int(beans)

    ingredients = [meat, rice, beans]
    tacos = 0
    
    ingredients.sort()
    if(ingredients[0]+ingredients[1]>ingredients[2]):
        tacos = (ingredients[0]+ingredients[1]+ingredients[2])/2
        
    else:
        tacos=ingredients[0]+ingredients[1];
       
        
    print(min(tacos,shells))
    days = days-1