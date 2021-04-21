# MATADI05
# Painting houses & building

def main():
    c = 'MATHIAS ADIKPON'
    a = '239 E KINGSBRIDGE ROAD, BRONX NEW YORK 10458'
    e = 'MATHIASADIKPON@GMAIL.COM'
    p = '(646)982-4102'
        
    print(c.center(75))
    print()
    print(a.center(75))
    print()
    print(e.center(75))
    print()
    print(p.center(75))
    
    num_flow = int(input('\nHow many rooms would you like to paint?\t'))

    paint_area = 0
    for i in range(1, num_flow + 1):
        w, l, h = eval(input('\nEnter width, length, heigh in feet of the room{0} separated by comma : '.format(i)))
        num, ww, hw = eval(input('\nEnter number, width, heigh in feet of the windows in room{0} separated by comma : '.format(i)))
        ans = (input('\nDo you want to paint ceiling of the room{0}?'.format(i))).upper()

        if ans[0] == 'Y':
            area = 2*( l*h + w*h) - num * ww * hw + w*l
            paint_area = paint_area + area
        else:
            area = 2*(l*h + w*h) - num * ww * hw 
            paint_area = paint_area + area

        print('\nThe area paintable of the room{0} is {1} {2}'.format(i, area,'square feet'))

    quality = int(input('\nQuality in SQ footage: press 1 for FLAT(50), 2 for semigloss(40), 3 for highgloss(32)'))
    if quality == 1:
        t1 = 50
    elif quality == 2:
        t1 = 40
    elif quality == 3:
        t1 = 32
    else:
        print('\nPress 1 or 2 or 3')

    num_of_gallons = round(paint_area / t1)

    # Paint spplies
    c = int(input('\nCost per gallon: press 1 for comp1($28.00), 2 for comp2($19.25) and 3 for comp3($28.9)'))
    if c == 1:
        t2 = 28
    elif c == 2:
        t2 = 19.25
    elif c == 3:
        t2 = 28.9
    else:
        print('\nPress 1 or 2 or 3')

    paint_supply = t2 * num_of_gallons
        

    # Cost of paint
    coats = int(input('\nWhat is the number of coats? '))
    cost_of_paint = coats * paint_supply

    #Labor
    t = int(input('\nHow long do you want the work to be done? Press 1 for 7 days($10000), 2 for 3 days($12000) and 3 for 30 days($6000)'))
    if t == 1:
        t3 = 10000
    elif t == 2:
        t3 = 12000
    elif t == 3:
        t3 = 6000
    else:
        print('\nPress 1 or 2 or 3')

        
    # Total due
    total_due = t3 + cost_of_paint

        
    print('\nTOTAL AREA : ', paint_area, "square feet")
    print('\nNUMBER OF GALLONS NEEDD :' ,num_of_gallons)
    print('\COST OF PAINT : ', cost_of_paint)
    print('LABOR : ${0}'.format(t3))
    print('\nTOTAL DUE :  ${0}'.format(total_due) )
    print()
              
main()

                         



