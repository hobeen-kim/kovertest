package com.hobeen.kovertest.user

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CheckFile1Test {

    /**
     * This class contains unit tests for the `sum` method in the `CheckFile1` class.
     * The `sum` method is supposed to return the sum of two integer inputs.
     */

    @Test
    fun testSumWithPositiveNumbers() {
        // Arrange
        val checkFile1 = CheckFile1()
        val a = 5
        val b = 10

        // Act
        val result = checkFile1.sum(a, b)

        // Assert
        assertEquals(15, result, "Sum of $a and $b should be 15")
    }

    @Test
    fun testSumWithNegativeNumbers() {
        // Arrange
        val checkFile1 = CheckFile1()
        val a = -5
        val b = -10

        // Act
        val result = checkFile1.sum(a, b)

        // Assert
        assertEquals(-15, result, "Sum of $a and $b should be -15")
    }

    @Test
    fun testSumWithPositiveAndNegativeNumber() {
        // Arrange
        val checkFile1 = CheckFile1()
        val a = 5
        val b = -3

        // Act
        val result = checkFile1.sum(a, b)

        // Assert
        assertEquals(2, result, "Sum of $a and $b should be 2")
    }

    @Test
    fun testSumWithZero() {
        // Arrange
        val checkFile1 = CheckFile1()
        val a = 0
        val b = 7

        // Act
        val result = checkFile1.sum(a, b)

        // Assert
        assertEquals(7, result, "Sum of $a and $b should be 7")
    }

    @Test
    fun testSumWithBothZero() {
        // Arrange
        val checkFile1 = CheckFile1()
        val a = 0
        val b = 0

        // Act
        val result = checkFile1.sum(a, b)

        // Assert
        assertEquals(0, result, "Sum of $a and $b should be 0")
    }

    @Test
    fun testSumWithLargeNumbers() {
        // Arrange
        val checkFile1 = CheckFile1()
        val a = Int.MAX_VALUE
        val b = Int.MAX_VALUE

        // Act
        val result = checkFile1.sum(a, b)

        // Assert
        assertEquals(
            Int.MAX_VALUE + Int.MAX_VALUE,
            result,
            "Sum of $a and $b should be ${Int.MAX_VALUE + Int.MAX_VALUE}"
        )
    }

    @Test
    fun testSumWithMinAndMaxInt() {
        // Arrange
        val checkFile1 = CheckFile1()
        val a = Int.MIN_VALUE
        val b = Int.MAX_VALUE

        // Act
        val result = checkFile1.sum(a, b)

        // Assert
        assertEquals(-1, result, "Sum of $a and $b should be -1")
    }
}