package com.howettl.calculator

import java.io.File

class Calculator(private val folderPath: String) {

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

    fun calculate() {
        println(transactions.joinToString("\n"))
    }

}