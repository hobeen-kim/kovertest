package com.hobeen.kovertest.user

class CheckFile2 {

    /**
     * Returns a greeting for the provided name.
     *
     * @param a The name to include in the greeting.
     * @return A string in the form "Hello {a}".
     */
    fun saySomething(a: String): String {
        return "Hello $a"
    }

    /**
     * Returns a greeting that includes the provided text followed by two exclamation marks.
     *
     * @param a The name or text to include in the greeting.
     * @return A string formatted as "Hello <a>!!".
     */
    fun sayAnything(a: String): String {
        return "Hello $a!!"
    }
}