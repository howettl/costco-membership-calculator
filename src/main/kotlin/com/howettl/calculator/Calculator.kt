package com.howettl.calculator

import java.io.File

class Calculator(
    private val folderPath: String,
    private val basicCost: Double,
    private val executiveCost: Double,
    private val taxRate: Double,
    private val bypassSpending: Double
) {

    private data class Transaction(val date: String, val amount: Double, val vendor: String) {
        constructor(data: String): this(
            data.split(",")[0],
            data.split(",")[4].toDouble(),
            data.split(",")[2]
        )

        override fun toString(): String {
            return "$amount at $vendor on $date"
        }
    }

    private val transactions: List<Transaction> by lazy {
        File(folderPath).walk()
            .asSequence()
            .filter { it.isFile }
            .filter { it.path.endsWith(".csv") || it.path.endsWith(".CSV") }
            .map { file ->
                file.readLines()
                    .filter { it.contains("costco", ignoreCase = true) }
                    .filter { !it.contains("gas", ignoreCase = true) }
            }
            .flatten()
            .map { Transaction(it) }
            .toList()
    }

    private val totalSpending: Double by lazy {
        if (bypassSpending > 0) {
            bypassSpending
        } else {
            transactions.sumByDouble { it.amount * -1 }
        }
    }

    private val requiredSpending: Double by lazy {
        ((executiveCost - basicCost) / 0.02) * (1 + (taxRate / 100))
    }

    private val executiveCoverageSpending: Double by lazy {
        (executiveCost / 0.02) * (1 + (taxRate / 100))
    }

    fun calculate() {
        if (totalSpending >= requiredSpending) {
            println("It would be worth it for you to get an executive membership!")

            if (totalSpending > executiveCoverageSpending) {
                println("Your annual spending would result in a cash back amount that fully covers the cost of your membership.")
            } else {
                println("In order to fully cover the cost of your membership, you would need to spend $${"%.2f".format(executiveCoverageSpending - totalSpending)} more per year.")
            }
        } else {
            println("It would not be worth it for you to get an executive membership.")
            println("You need to spend $${"%.2f".format(requiredSpending - totalSpending)} more per year to justify the executive membership.")
        }

        println("Total after tax spending: $${"%.2f".format(totalSpending)}")
        println("Required after-tax spending to cover basic membership: $${"%.2f".format(requiredSpending)}")
        println("Required after-tax spending to cover executive membership: $${"%.2f".format(executiveCoverageSpending)}")

    }

}