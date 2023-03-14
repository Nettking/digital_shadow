import pandas as pd

# read CSV file
data = pd.read_csv('data.csv')

# drop unnecessary columns
data = data.drop(data.columns[0], axis=1)
data = data.drop(data.columns[1], axis=1)
data = data.drop(data.columns[1], axis=1)
data = data.drop(data.columns[2], axis=1)

# save modified data to new CSV file
data.to_csv('cleaned_data.csv', index=False)
