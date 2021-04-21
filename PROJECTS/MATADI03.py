#MATADI03
# Python program to calculate commissions payable
# to travel agents based on productivity>
 
def main():
    ticket_price = eval(input('\nEnter price of the ticket:\t'))
    Sale_price = eval(input('\nEnter price of the sale:\t'))
    Num_passenger = int(input('\nHow many passengers?\t'))
    profit_per_perssenger = Sale_price - ticket_price
    ans = input('\nIs it a domestic flight?\t').upper()
    if ans[0] == 'Y':
        agtcom = 0.5 * Num_passenger * profit_per_perssenger 
        agency_profit = 0.5 * Num_passenger * profit_per_perssenger
    else:
        if profit_per_perssenger >= 60:      
            agtcom = 0.5 * Num_passenger * profit_per_perssenger 
            agency_profit = 0.5 * Num_passenger * profit_per_perssenger 
        else:
            agtcom = Num_passenger * (profit_per_perssenger - 30)
            agency_profit = 30 * Num_passenger
    print('\nTICKET PRICE:',ticket_price,'\n SALE PRICE:',Sale_price)
    print('\nNUMBER OF PASSENGERS:',Num_passenger,'\nPROFIT PER PASSENGER:',int(profit_per_perssenger))
    print('\nAGTCOM:',int(agtcom),'\nAGENCY PROFIT:',int(agency_profit))

    print()
main()
