package com.howettl.calculator

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class Args(parser: ArgParser) {
    val transactionsFolder by parser.storing(
        "-i", "--input",
        help = "folder containing transaction files in .csv format"
    )

    val basicMembershipCost by parser.storing(
        "-b", "--basic",
        help = "The annual cost of a basic membership in dollars"
    ).default("60")

    val executiveMembershipCost by parser.storing(
        "-e", "--executive",
        help = "The annual cost of an executive membership in dollars"
    ).default("120")

    val taxRate by parser.storing(
        "-t", "--tax",
        help = "The tax rate as a percentage"
    ).default("12")

    val totalSpending by parser.storing(
        "-s", "--spending",
        help = "Optionally enter your annual after-tax spending. Does not require transactions."
    ).default("-1")
}