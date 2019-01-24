# costco-membership-calculator
Calculator to determine the viability of a Costco Executive membership

## Usage

`java -jar calculator.jar -h` - Prints out all command-line arguments

Place one years worth of transaction data (in CSV format) into a directory (ie. `transactions`), then execute the script:

`java -jar calculator.jar -i .\transactions`

Alternatively, you can bypass the transaction calculations by providing your own after-tax annual spending:

`java -jar calculator.jar -i "" -b 7000`