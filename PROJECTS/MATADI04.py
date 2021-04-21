#MATADI04
from graphics import *
def main():
    
    r = ''
    for i in range(1,4):
        temp, sets = eval(input('\nEnter the value of current(temp{0})and desired(set{0}) temperatures in the room{0} separated by comma : '.format(i,i,i)))
        if sets <= temp :
            r= r + '0'  

        else:
            r= r + '1'
    win = GraphWin('Tempature', 600, 300)
    win.setBackground('yellow')
    win.setCoords(0.0, 0.0, 3.0, 4.0)
    t = Text(Point(1.25,3), 'The result of tempature from the first room to the third room is')
    t.setTextColor('black')
    t.setStyle('bold')    
    t.draw(win)
    outputText = Entry(Point(2.78,3), 5)    
    outputText.setSize(15)
    outputText.setTextColor('cyan')            
    outputText.setText(r)
    outputText.draw(win)
    win.getMouse()
    win.close()
    
    print('\nThe result of tempature from the first room to the third room is', r)
    print()
main()
