package com.moovers.monitor

import org.junit.Assert.*
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class ExceptionParserTest {



    @Test
    fun testGeneralError() {
        val exception = Exception("Some error message")

        val messageResource = ExceptionParser.getMessage(exception)

        assertEquals(R.string.error_something_went_wrong, messageResource)
    }
}