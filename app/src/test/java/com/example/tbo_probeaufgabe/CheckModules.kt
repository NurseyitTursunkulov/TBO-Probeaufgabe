package com.example.tbo_probeaufgabe

import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.verify.verify


/**
 * Created by nurseiit.tursunkulov on 03.01.2024
 * CheckModules
 */
class CheckModulesTest : KoinTest {

    @Test
    fun checkAllModules() {
        appModule.verify()
    }
}