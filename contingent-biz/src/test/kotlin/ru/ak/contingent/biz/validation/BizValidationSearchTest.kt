package ru.ak.contingent.biz.validation

import kotlinx.coroutines.test.runTest
import ru.ak.contingent.biz.ContStudentProcessor
import ru.ak.contingent.common.ContContext
import ru.ak.contingent.common.models.ContCommand
import ru.ak.contingent.common.models.ContState
import ru.ak.contingent.common.models.ContStudentFilter
import ru.ak.contingent.common.models.ContWorkMode
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class BizValidationSearchTest {

    private val command = ContCommand.SEARCH
    private val processor by lazy { ContStudentProcessor() }

    @Test
    fun correctEmpty() = runTest {
        val ctx = ContContext(
            command = command,
            state = ContState.NONE,
            workMode = ContWorkMode.TEST,
            studFilterRequest = ContStudentFilter()
        )
        processor.exec(ctx)
        assertEquals(0, ctx.errors.size)
        assertNotEquals(ContState.FAILING, ctx.state)
    }
}

