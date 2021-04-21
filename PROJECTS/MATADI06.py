#MATADI06

def main():
    
    st = input('\nEnter a number or a letter: ')


    cap_list = []
    for i in st:
        x = i.upper()
        cap_list.append(x)

    str = ''    
    for i in cap_list:
        try :
            if type(int(i)) == type(1):
                str = str + i

        except ValueError:
            
            if ((ord(i)-65) // 3) >= 8:            
                str = str + '9'

            
            elif ((ord(i)-65)// 3 <= 5) or(84 <= ord(i) <= 85) or (87 <= ord(i) <= 88):
                num = '{}'. format(((ord(i)-65)//3) + 2)
                str =  str + num

            elif (((ord(i)-65) / 3) == 6.0) or (((ord(i)-65) / 3) == 7.0):
                num = '{}'.format(((ord(i)-65) // 3) + 1)            
                str = str + num            

            

        except:
            print('Something is wrong. Please, try again.')
            

    
    number = str[0]+'-'+str[1:4]+'-'+str[4:7]+'-'+str[7:len(str)]
    print('\n',number)
    
    print()
main()
    
