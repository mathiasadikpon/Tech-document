# MATADI02
# Snowman and Chrismas
from graphics import *

def main():
    # window with blue color
    win = GraphWin("Snowman", 350, 350)
    win.setBackground('blue')
        
    # SNOWMAN
    # Big Circle
    cir_3 = Circle(Point(90, 277), 70)
    cir_3.setFill('white')
    cir_3.setOutline('white')
    cir_3.draw(win)
    
    # middle Circle
    cir_2 = Circle(Point(90, 175), 50)
    cir_2.setFill('white')
    cir_2.setOutline('white')
    cir_2.draw(win)

    point_1 = Circle(Point(90,175),5)
    point_1.setFill('black')
    point_1.draw(win)

    point_2 = point_1.clone()
    point_2.move(0,-20)
    point_2.draw(win)

    point_3 = point_1.clone()
    point_3.move(0,20)
    point_3.draw(win)
    
    # Heat
    cir_1 = Circle(Point(90, 100), 35)
    cir_1.setFill('white')
    cir_1.setOutline('white')
    cir_1.draw(win)

    # Hat
    rec_1 = Rectangle(Point(55,65),Point(125,73))
    rec_1.setFill('black')
    rec_1.draw(win)

    rec_2 = Rectangle(Point(70,20),Point(110,73))
    rec_2.setFill('black')
    rec_2.draw(win)

    # Eye
    eye_1 = Circle(Point(75, 90), 4)
    eye_1.setFill('black')
    eye_1.draw(win)

    eye_2 = Circle(Point(105, 90), 4)
    eye_2.setFill('black')
    eye_2.draw(win)

    # Mouth
    mouth = Polygon(Point(90, 100),Point(90, 112),Point(103, 106))
    mouth.setFill('orange')
    mouth.setOutline('orange')
    mouth.draw(win)

    # Heat dots
    dot_1 = Circle(Point(90, 130),2)
    dot_1.setFill('black')
    dot_1.draw(win)

    dot_2 = dot_1.clone()
    dot_2.move(-10,-7)
    dot_2.draw(win)

    dot_3 = dot_2.clone()
    dot_3.move(-10,-7)
    dot_3.draw(win)

    dot_4 = dot_1.clone()
    dot_4.move(10,-7)
    dot_4.draw(win)

    dot_5 = dot_4.clone()
    dot_5.move(10,-7)
    dot_5.draw(win)

    # Hand
    hand1 = Line(Point(22,140), Point(41,175))
    hand1.setFill('white')
    hand1.setOutline('white')
    hand1.setWidth(5)
    hand1.draw(win)
    finger1 = Line(Point(30,140), Point(30,155))
    finger1.setFill('white')
    finger1.setOutline('white')
    finger1.setWidth(4)
    finger1.draw(win)
    
    hand2 = Line(Point(139,175), Point(160,140))
    hand2.setFill('white')
    hand2.setOutline('white')
    hand2.setWidth(5)
    hand2.draw(win)
    finger2 = finger1.clone()
    finger2.move(122,0)
    finger2.draw(win)
    
    # CHRISMAS
    root = Rectangle(Point(245,310), Point(283, 349))
    root.setFill('brown')
    root.setOutline('brown')
    root.draw(win)

    triangle1 = Polygon(Point(264, 220), Point(349,310), Point(179, 310))
    triangle1.setFill('green')
    triangle1.setOutline('green')
    triangle1.draw(win)
    
    
    triangle2 = Polygon(Point(264, 159), Point(339,235), Point(189, 235))
    triangle2.setFill('green')
    triangle2.setOutline('green')
    triangle2.draw(win)

    triangle3 = Polygon(Point(264, 120), Point(324,169), Point(204, 169))
    triangle3.setFill('green')
    triangle3.setOutline('green')
    triangle3.draw(win)

    triangle4 = Polygon(Point(264, 85), Point(305,125), Point(223, 125))
    triangle4.setFill('green')
    triangle4.setOutline('green')
    triangle4.draw(win)

    triangle5 = Polygon(Point(264,55), Point(291,90), Point(237,90))
    triangle5.setFill('green')
    triangle5.setOutline('green')
    triangle5.draw(win)

    #stars
    rect = Rectangle(Point(179,308), Point(185,315))
    rect.setFill('red')
    rect.setOutline('yellow')
    rect.draw(win)
    re1 = rect.clone()
    re1.move(163,0)
    re1.draw(win)
    cir = Circle(Point(196,236),4)
    cir.setFill('yellow')
    cir.setOutline('red')
    cir.draw(win)
    cir1 = cir.clone()
    cir1.move(137,0)
    cir1.draw(win)
    
    # Clone
    for i in range(1,4):
        x1 = 27*i
        y1 = -27*i
        rec = rect.clone()
        rec.move(x1,y1)
        rec.draw(win)
        r = re1.clone()
        r.move(-x1,y1)
        r.draw(win)

    for i in range(1,4):
        x1 = 37*i
        y1 = -37*i
        c = cir.clone()
        c.move(x1,y1)
        c.draw(win)
        c1 = cir1.clone()
        c1.move(-x1,y1)
        c1.draw(win)

    re2 = rect.clone()
    re2.move(28,-143)
    re2.draw(win)
    re3 = re2.clone()
    re3.move(108,0)
    re3.draw(win)
    for i in range(1,4):
        x1 = 27*i
        y1 = -27*i
        r1 = re2.clone()
        r1.move(x1,y1)
        r1.draw(win)
        r2 = re3.clone()
        r2.move(-x1,y1)
        r2.draw(win)

    ci1 = Circle(Point(264,305),4)
    ci1.setFill('yellow')
    ci1.setOutline('red')
    ci1.draw(win)
    for i in range(1,5):
        y1 = -57*i
        c = ci1.clone()
        c.move(0,y1)
        c.draw(win)

    # Star
    s = Polygon(Point(264,60), Point(264,40))
    s.setWidth(7)
    s.setOutline('yellow')
    s.setFill('yellow')
    s.draw(win)
    s1 = Polygon(Point(256,50), Point(272,50))
    s1.setWidth(7)
    s1.setOutline('yellow')
    s1.setFill('yellow')
    s1.draw(win)
main()
