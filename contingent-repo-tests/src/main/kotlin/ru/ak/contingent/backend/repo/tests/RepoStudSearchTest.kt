package ru.ak.contingent.backend.repo.tests

import ru.ak.contingent.common.models.ContStudent
import ru.ak.contingent.common.repo.DbStudFilterRequest
import ru.ak.contingent.common.repo.IStudRepository
import kotlin.test.Test
import kotlin.test.assertEquals


abstract class RepoStudSearchTest {
    abstract val repo: IStudRepository

    protected open val initializedObjects: List<ContStudent> = initObjects

    @Test
    fun searchByFio() = runRepoTest {
        val result = repo.searchStud(DbStudFilterRequest(fioFilter = "Иванов"))
        assertEquals(true, result.isSuccess)
        val expected = listOf(initializedObjects[1], initializedObjects[3]).sortedBy { it.id.asInt() }
        assertEquals(expected, result.data?.sortedBy { it.id.asInt() })
        assertEquals(emptyList(), result.errors)
    }

//    @Test
//    fun searchDealSide() = runRepoTest {
//        val result = repo.searchStud(DbStudFilterRequest(dealSide = MkplDealSide.SUPPLY))
//        assertEquals(true, result.isSuccess)
//        val expected = listOf(initializedObjects[2], initializedObjects[4]).sortedBy { it.id.asInt() }
//        assertEquals(expected, result.data?.sortedBy { it.id.asInt() })
//        assertEquals(emptyList(), result.errors)
//    }

    companion object: BaseInitStuds("search") {

        override val initObjects: List<ContStudent> = listOf(
            createInitTestModel(111, "Пупкин Сидор"),
            createInitTestModel(222, "Иванов Иван"),
            createInitTestModel(333, "Петров Роман"),
            createInitTestModel(444, "Иванов Василий"),
        )
    }
}
