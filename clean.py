import csv

input_file = 'input_data.csv'
output_file = 'output_data.csv'

# Specify which columns you want to remove by their indices
columns_to_remove = [0, 2, 3, 4]  # Indices of RIGHT, LEFT, and ON/OFF columns

with open(input_file, 'r') as infile, open(output_file, 'w', newline='') as outfile:
    reader = csv.reader(infile, delimiter=';')
    writer = csv.writer(outfile, delimiter=';')
    
    for row in reader:
        # Use a list comprehension to keep only the desired columns
        filtered_row = [value for index, value in enumerate(row) if index not in columns_to_remove]
        writer.writerow(filtered_row)