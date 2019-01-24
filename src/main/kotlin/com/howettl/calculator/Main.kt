package com.howettl.calculator

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.mainBody

fun main(args: Array<String>) = mainBody {
    ArgParser(args).parseInto(::Args).run {
        Calculator(
            transactionsFolder,
            basicMembershipCost.toDouble(),
            executiveMembershipCost.toDouble(),
            taxRate.toDouble(),
            totalSpending.toDouble()
        ).calculate()
    }
}
