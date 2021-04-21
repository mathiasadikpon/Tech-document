# Final Project
from tkinter import *

class PegSolitaireGraphical:
    #This is the top-level class that runs the game.    
    def __init__(self, X,Y):
        self.rows = X
        self.cols = Y
        self.empStr = 'X' # string for empty space
        self.pegStr = 'P' # string for space with peg
        self.root = Tk() #Start event Loop
 
        self.frame1 = Frame(self.root)
        self.board = []   # board of pegs or empty, initialized to all empty
        # Fill the board with 'X" 
        for i in range(X):
            self.board.append([])
            for j in range(Y):
                L = Label(self.frame1, text =self.empStr, font = (40))
                L.grid(row = j, column = i)
                self.board[i].append(L)               
                
        # Information about the Board        
        v = 'NB: The origin of the board of Peg Solitaire is the left  bottom.\n'
        Infos = Label(self.root,font = (40),fg = 'red')
        Infos['text'] = v
        Infos.pack()
        self.frame1.pack()
        
        # Finish button
        self.frame2 = Frame(self.root)
        finish= Button(self.frame2, text ='Finish', font = (40), fg = 'red')
        finish.bind('<Button -1>', lambda event: self.root.destroy())
        finish.grid(row = 0, column = 0)

        # Reset buttom
        reset= Button(self.frame2, text ='Reset', font = (40), fg = 'green')
        reset.bind('<Button -1>',lambda event: Reset(self.board))
        reset.grid(row = 0, column = 1)
        self.frame2.pack()
        

        #Place Peg Frame
        self.frame3 = Frame(self.root)
        frame(self,self.frame3,'Place peg')
        self.frame3.pack()

        #Remove Peg Button
        self.frame4 = Frame(self.root)
        frame(self,self.frame4,'Remove Peg')
        self.frame4.pack()

        #  Jump Peg
        self.Jumpframe =Frame(self.root)
        jumpPegframe(self,self.Jumpframe)
        self.Jumpframe.pack()
        

        # Stuck Frame
        self.stuckframe = Frame(self.root)
        stuck_display(self, self.stuckframe,'isStuck' )
        self.stuckframe.pack()

        # Display Frame
        self.displayframe = Frame(self.root)
        stuck_display(self, self.displayframe,'display')
        self.displayframe.pack()
 
        
        
        self.root.mainloop() # End event loop


    def _onBoard(self, pos):
        '''
        Input: A position
        Output: True if the position is on the board, False otherwise
        '''
        if (1 <= pos[0] <= self.rows)  and  (1 <= pos[1] <= self.cols):
            return True
        else:
            return False

    def congurateY(self, y):
        b = self.cols - y +1
        return b
    
    def _checkPiecesOfMove(self, start, middle, end):
        '''
        Input: 3 positions that are on the board
        Output: True if correct pieces in those positions, False otherwise
        '''
        if self.board [start[0]-1]  [start[1]-1]['text'] == self.pegStr and \
           self.board [middle[0]-1] [middle[1]-1]['text'] == self.pegStr and \
           self.board [end[0]-1] [end[1]-1]['text'] == self.empStr:
            return True
        else:
            return False


    def placePeg(self, x, y):
        if self._onBoard( (x, y) ):
            self.board[x-1][y-1]['text'] = self.pegStr

        
    def removePeg(self, x, y):
        if self._onBoard( (x, y) ):
            self.board[x-1][y-1]['text'] = self.empStr        


    # This helps to reduce the code of jump Peg.
    #It doesn't have error checking because jumpPeg function has it.
    def setpoint(self, a,b,x,y): 
        self.grid[a[0]-1][a[1] -1]['text'] = self.empStr
        self.grid[x-1][y -1]['text'] = self.empStr
        self.grid[b[0]-1][b[1]-1]['text'] = self.pegStr
        
    
    def jumpPeg(self, a,b):
        try:                
            x = (a[0] + b[0])//2
            y = (a[1] + b[1])//2 
            if self.board[a[0]-1][a[1] -1]['text'] == 'P' and \
               self.board[b[0]-1][b[1]-1]['text'] == 'X' and \
               self.board[x-1][y -1]['text'] == 'P':
                if((x+1) in [a[0] , b[0]]) and (y in [a[1] , b[1]]):
                    if a[0] == b[0] and (a[1] == b[1] + 2 or b[1] == a[1] + 2):
                        self.setpoint(a,b,x,y)
                        return True

                    elif a[1] == b[1] and (a[0] == b[0] + 2 or b[0] == a[0] + 2):
                        self.setpoint(a,b,x,y)
                        return True

                    else:
                        return False

           
                elif (x in [a[0] , b[0]]) and ((y+1) in [a[1] , b[1]]):
                        
                    if a[0] == b[0] and (a[1] == b[1] + 2 or b[1] == a[1] + 2):
                        self.setpoint(a,b,x,y)
                        return True

                    elif a[1] == b[1] and (a[0] == b[0] + 2 or b[0] == a[0] + 2):
                        self.setpoint(a,b,x,y)
                        return True

                    else:
                        return False

                    
                else:
                   return False
            else:
                return False
        except:
            return False


    def isStuck(self):
        '''
        Returns True if the game is stuck (i.e. no more moves). False otherwise.
        '''
        
        for r in range(1, self.rows+1):
            for left in range(1, self.cols - 1):
                if self._checkPiecesOfMove( (r,left) , (r,left+1), (r,left+2) ) or \
                   self._checkPiecesOfMove( (r,left+2) , (r,left+1), (r,left) ):
                    return False

        for c in range(1, self.cols+1):
            for top in range(1, self.rows - 1):
                if self._checkPiecesOfMove( (top,c) , (top+1,c), (top+2,c) ) or \
                   self._checkPiecesOfMove( (top+2,c) , (top+1,c), (top,c) ):
                    return False

        # Otherwise, found no possible move, so it's stuck
        return True
                                            

    def display(self):
        l = ''
        for c in range(self.cols):
            for r in range(self.rows):            
                 l += self.board[r][c]['text']
            l += '\n'
        return l

def Reset(board):
    for r in range(len(board)):
        for col in range(len(board[0])):
            board[r][col]['text'] = 'X'
                
def frame(P,frame, name):
        B = Button(frame, text =name,font = (40), fg = 'green')
        B.grid(row = 0, column = 0)
        
        x = Label(frame, text ='X',font = (40))# X component
        x.grid(row = 0, column = 1)
        xEntry = Entry(frame)
        xEntry.grid(row = 0, column = 2)

        y = Label(frame, text ='Y',font = (40))# Y component
        y.grid(row = 1, column = 1)
        yEntry = Entry(frame)
        yEntry.grid(row = 1, column = 2)
        if name == 'Place peg':
            B.bind('<Button-1>', lambda event: P.placePeg(int(xEntry.get()), P.congurateY(int(yEntry.get()))))
        if name == 'Remove Peg':
            B.bind('<Button-1>', lambda event: P.removePeg(int(xEntry.get()), P.congurateY(int(yEntry.get()))))
        
        return frame
    
def stuck_display(P, frame,name):
        B = Button(frame, text = name,font = (40), fg = 'green')
        B.grid(row = 0, column = 0)       
        L = Label(frame, font = (40), fg = 'green') 
        L.grid(row = 0, column = 1)
        
        if name == 'isStuck':
            L = Label(frame, font = (40), fg = 'green') 
            L.grid(row = 0, column = 1) 
            B.bind('<Button -1>',lambda event : printstuck(P,L))
        if name == 'display':
            L = Label(frame, font = (40)) 
            L.grid(row = 0, column = 1) 
            B.bind('<Button -1>',lambda event : printValue(P, L))
   
def printValue( P,L):
    L['text'] = str(P.display())

def printstuck( P,L):
    L['text'] = str(P.isStuck())

def printjump(P,Isjump,a,b):
    Isjump['text'] = str(P.jumpPeg(a,b))

def jumpPegframe(P,frame):
        B = Button(frame, text ='Jump Peg',font = (40), fg = 'green' )
        B.grid(row = 0, column = 4)
        Isjump = Label(frame, font = (40), fg = 'green' )
        Isjump.grid(row = 0, column = 5)
        L1 = Label(frame, text ='First Peg',font = (40))
        L1.grid(row = 1, column = 0)
        
        x1 = Label(frame, text ='X',font = (40))# X component
        x1.grid(row = 1, column = 1)
        x1Entry = Entry(frame)
        x1Entry.grid(row = 1, column = 2)
        
        y1 = Label(frame, text ='Y',font = (40))# Y component
        y1.grid(row = 2, column = 1)
        y1Entry = Entry(frame)
        y1Entry.grid(row = 2, column = 2)

        L2 = Label(frame, text ='Second Peg',font = (40))
        L2.grid(row = 1, column = 4)
        
        x2 = Label(frame, text ='X',font = (40))# X component
        x2.grid(row = 1, column = 5)
        x2Entry = Entry(frame)
        x2Entry.grid(row = 1, column = 6)
        
        y2 = Label(frame, text ='Y',font = (40))# Y component
        y2.grid(row = 2, column = 5)
        y2Entry = Entry(frame)
        y2Entry.grid(row = 2, column = 6)

        B.bind('<Button-1>',lambda event: printjump(P,Isjump,(int(x1Entry.get()),P.congurateY(int(y1Entry.get()))),(int(x2Entry.get()),P.congurateY(int(y2Entry.get())))))

def main():
    P = PegSolitaireGraphical(3,4)
