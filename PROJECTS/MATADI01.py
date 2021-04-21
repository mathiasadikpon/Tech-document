# MATADI01
# Calculate the total amount of quarterly sale taxes due TAX_DUE

def main():
    answer = input('\nAre you in New York State juridiction ? (Yes or no) ').upper()
    if answer[0] =='Y':
    
        # Constants
        tax_rate = 0.08875
        VEND_CRED = 0.05

        # Data input
        gross_sales_serv = int(input('\nEnter the value of gross sales and services : '))
        non_tax = int(input('\nEnter the value of non-taxable amount : '))
        pen_int = int(input('\nEnter the total of penalties and interest due for late filing : '))
        
        # Calculated fields
        taxable_amount = gross_sales_serv - non_tax
        pre_tax = taxable_amount * tax_rate
        vend_cred = pre_tax * VEND_CRED
        tax_due = pre_tax + pen_int - vend_cred
        
        # Print
        print('\nTaxable amount is', taxable_amount)
        print('\nThe pre-tax is ${0:0.2f}'.format(pre_tax))
        print('\nThe vendor collection credit is ${0:0.2f}'.format(vend_cred))
        print('\nTax_Due is ${0:0.2f}'.format(tax_due))

    else:
        print('\nSorry, This program is only for New York States juridiction')

    print()
    
    
main()
    
