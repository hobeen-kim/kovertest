package com.hobeen.kovertest.user

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CheckFile2Test {

    /**
     * Test class for CheckFile2
     * This class contains unit tests for the `saySomething` method in the `CheckFile2` class.
     * The `saySomething` method takes a string input and returns a personalized greeting message.
     */

    @Test
    fun `saySomething should return correct greeting for input John`() {
        // Arrange
        val checkFile2 = CheckFile2()
        val input = "John"

        // Act
        val result = checkFile2.saySomething(input)

        // Assert
        assertEquals("Hello John", result)
    }

    @Test
    fun `sayAnything should return correct greeting with exclamation for input John`() {
        // Arrange
        val checkFile2 = CheckFile2()
        val input = "John"

        // Act
        val result = checkFile2.sayAnything(input)

        // Assert
        assertEquals("Hello John!!", result)
    }

    @Test
    fun `sayAnything should return correct greeting with exclamation for empty input`() {
        // Arrange
        val checkFile2 = CheckFile2()
        val input = ""

        // Act
        val result = checkFile2.sayAnything(input)

        // Assert
        assertEquals("Hello !!", result)
    }

    @Test
    fun `sayAnything should return correct greeting with exclamation for special characters`() {
        // Arrange
        val checkFile2 = CheckFile2()
        val input = "#$%"

        // Act
        val result = checkFile2.sayAnything(input)

        // Assert
        assertEquals("Hello #$%!!", result)
    }

    @Test
    fun `sayAnything should return correct greeting with exclamation for numerical input`() {
        // Arrange
        val checkFile2 = CheckFile2()
        val input = "12345"

        // Act
        val result = checkFile2.sayAnything(input)

        // Assert
        assertEquals("Hello 12345!!", result)
    }

    @Test
    fun `sayAnything should return correct greeting with exclamation for mixed input`() {
        // Arrange
        val checkFile2 = CheckFile2()
        val input = "John123!"

        // Act
        val result = checkFile2.sayAnything(input)

        // Assert
        assertEquals("Hello John123!!!", result)
    }

    @Test
    fun `saySomething should return correct greeting for input Jane`() {
        // Arrange
        val checkFile2 = CheckFile2()
        val input = "Jane"

        // Act
        val result = checkFile2.saySomething(input)

        // Assert
        assertEquals("Hello Jane", result)
    }

    @Test
    fun `saySomething should return correct greeting for empty input`() {
        // Arrange
        val checkFile2 = CheckFile2()
        val input = ""

        // Act
        val result = checkFile2.saySomething(input)

        // Assert
        assertEquals("Hello ", result)
    }

    @Test
    fun `saySomething should return correct greeting for special characters`() {
        // Arrange
        val checkFile2 = CheckFile2()
        val input = "!@#"

        // Act
        val result = checkFile2.saySomething(input)

        // Assert
        assertEquals("Hello !@#", result)
    }

    @Test
    fun `saySomething should return correct greeting for numerical input`() {
        // Arrange
        val checkFile2 = CheckFile2()
        val input = "12345"

        // Act
        val result = checkFile2.saySomething(input)

        // Assert
        assertEquals("Hello 12345", result)
    }
}