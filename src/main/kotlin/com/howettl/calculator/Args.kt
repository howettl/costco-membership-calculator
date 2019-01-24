package com.howettl.calculator

import com.xenomachina.argparser.ArgParser

class Args(parser: ArgParser) {
    val transactionsFolder by parser.storing(
        "-i", "--input",
        help = "folder containing transaction files in .csv format"
    )
}