package com.vygovskiy.settings

import org.springframework.stereotype.Service
import kotlin.system.exitProcess

@Service
class FailService {
    init {
        println("Before exit")
        throw RuntimeException("Some error", RuntimeException("Cause 2", RuntimeException("Cause 1")))
//        exitProcess(1)
    }
}