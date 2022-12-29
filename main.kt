package cinema

var purchasedTickets = 0
var rows = 0
var seats = 0
var nRow = 0
var nSeat = 0
var currentIncome = 0
const val FIRST_HALF_PRICE = 10
const val SECOND_HALF_PRICE = 8

fun main() {
    // write your code here
    println("Enter the number of rows:")
    rows = readln().trim().toInt()

    println("Enter the number of seats in each row:")
    seats = readln().trim().toInt()

    val theater = Array(rows + 1) { arrayOfNulls<String>(seats + 1) }

    for (i in theater.indices) {
        for (j in 0 until theater[0].size) {
            when {
                i == 0 && j == 0 -> {
                    theater[i][j] = "  "
                    continue
                }

                i == 0 -> {
                    theater[i][j] = "$j "
                    continue
                }

                j == 0 -> {
                    theater[i][j] = "$i "
                    continue
                }

                else -> {
                    theater[i][j] = "S "
                }
            }
        }
    }

    println("1. Show the seats")
    println("2. Buy a ticket")
    println("3. Statistics")
    println("0. Exit")

    do {
        val entry = readln().trim().toInt()

        when (entry) {
            1 -> {
                println("Cinema:")
                printSeats(theater)

                println("1. Show the seats")
                println("2. Buy a ticket")
                println("3. Statistics")
                println("0. Exit")
            }

            2 -> {
                do {
                    do {
                        println("Enter a row number:")
                        nRow = readln().trim().toInt()

                        println("Enter a seat number in that row:")
                        nSeat = readln().trim().toInt()

                        if (nRow > rows || nSeat > seats) {
                            println("Wrong Input!")
                        }
                    } while (nRow > rows || nSeat > seats)
                    if (theater[nRow][nSeat] == "B ") {
                        println("That ticket has already been purchased!")
                    } else {
                        theater[nRow][nSeat] = "B"
                        purchasedTickets++
                        if (rows * seats <= 60 || nRow <= rows / 2) {
                            currentIncome += FIRST_HALF_PRICE
                            println("Ticket price: $${FIRST_HALF_PRICE}")
                        } else {
                            currentIncome += SECOND_HALF_PRICE
                            println("Ticket price: $${SECOND_HALF_PRICE}")
                        }
                    }
                } while (theater[nRow][nSeat] == "B ")

                println("Cinema:")
                theater[nRow][nSeat] = "B "
                printSeats(theater)

                println("1. Show the seats")
                println("2. Buy a ticket")
                println("3. Statistics")
                println("0. Exit")
            }

            3 -> {
                println("Number of purchased tickets: $purchasedTickets")

                val ticketsPercentage = purchasedTickets * 100 / (rows * seats).toDouble()
                val formatPercentage = "%.2f".format(ticketsPercentage)
                println("Percentage: ${formatPercentage}%")

                println("Current income: $${currentIncome}")

                val totalSeats = rows * seats
                val totalIncome = if (totalSeats <= 60) {
                    totalSeats * FIRST_HALF_PRICE
                } else {
                    val firstHalf = rows / 2 * seats
                    val secondHalf = totalSeats - firstHalf
                    firstHalf * FIRST_HALF_PRICE + secondHalf * SECOND_HALF_PRICE
                }
                println("Total income: $${totalIncome}")

                println("1. Show the seats")
                println("2. Buy a ticket")
                println("3. Statistics")
                println("0. Exit")
            }
            0 -> return
        }
    } while (entry > 0)
}

fun printSeats(arrayToPrint: Array<Array<String?>>) {
    for (i in arrayToPrint.indices) {
        for (j in 0 until arrayToPrint[0].size) {
            print(arrayToPrint[i][j])
        }
        println()
    }
}
