package ru.ak.contingent.api

import ru.ak.contingent.api.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class RequestSerializationTest {
    private val request = StudentCreateRequest(
        requestId = "123",
        debug = StudentDebug(
            mode = StudentRequestDebugMode.STUB,
            stub = StudentRequestDebugStubs.BAD_FIO
        ),
        student = StudentCreateObject(
            fio = "Иванов Иван Иванович",
            sex = Sex.М,
        )
    )

    @Test
    fun serialize() {
        val json = apiMapper.writeValueAsString(request)

        assertContains(json, Regex("\"fio\":\\s*\"Иванов Иван Иванович\""))
        assertContains(json, Regex("\"sex\":\\s*\"М\""))
        assertContains(json, Regex("\"mode\":\\s*\"stub\""))
        assertContains(json, Regex("\"stub\":\\s*\"badFio\""))
        assertContains(json, Regex("\"requestType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiMapper.writeValueAsString(request)
        val obj = apiMapper.readValue(json, IRequest::class.java) as StudentCreateRequest

        assertEquals(request, obj)
    }

    @Test
    fun deserializeNaked() {
        val jsonString = """
            {"requestId": "123"}
        """.trimIndent()
        val obj = apiMapper.readValue(jsonString, StudentCreateRequest::class.java)

        assertEquals("123", obj.requestId)
    }
}
