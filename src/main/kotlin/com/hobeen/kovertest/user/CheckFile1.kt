package com.hobeen.kovertest.user

class CheckFile1 {

    /**
     * Returns the sum of two integers.
     *
     * @return the sum of the two arguments
     */
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    /**
     * Returns the result of subtracting `b` from `a`.
     *
     * @param a The minuend.
     * @param b The subtrahend.
     * @return The difference (a - b).
     */
    fun sub(a: Int, b: Int): Int {
        return a - b
    }

    /**
     * Multiplies two integers.
     *
     * Performs integer multiplication of [a] and [b]. The result follows Kotlin Int semantics and may overflow.
     *
     * @param a first multiplicand
     * @param b second multiplicand
     * @return the product of `a` and `b`
     */
    fun mul(a: Int, b: Int): Int {
        return a * b
    }

    /**
     * Returns the integer division of `a` by `b`.
     *
     * If `b` is zero, returns 0 instead of throwing an exception.
     *
     * @param a Dividend.
     * @param b Divisor; when zero the function returns 0.
     * @return The result of `a / b` using integer division, or 0 if `b == 0`.
     */
    fun div(a: Int, b: Int): Int {

        if (b == 0) {
            return 0
        }

        return a / b
    }
}