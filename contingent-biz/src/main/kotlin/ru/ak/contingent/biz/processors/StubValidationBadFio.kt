package ru.ak.contingent.biz.processors

import ru.ak.contingent.common.ContContext
import ru.ak.contingent.common.models.ContError
import ru.ak.contingent.common.models.ContState
import ru.ak.contingent.common.stubs.ContStubs
import ru.ak.contingent.cor.ICorChainDsl
import ru.ak.contingent.cor.processor

fun ICorChainDsl<ContContext>.stubValidationBadFio(title: String) = processor {
    this.title = title
    on { stubCase == ContStubs.BAD_FIO && state == ContState.RUNNING }
    handle {
        state = ContState.FAILING
        this.errors.add(
            ContError(
                group = "validation",
                code = "validation-fio",
                field = "fio",
                message = "Wrong fio field"
            )
        )
    }
}
