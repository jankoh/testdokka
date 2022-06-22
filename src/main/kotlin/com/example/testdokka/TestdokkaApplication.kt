package com.example.testdokka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TestdokkaApplication

/**
 * main entry
 *
 * @param args an [Array] of [String] containing the args
 */
fun main(args: Array<String>) {
	runApplication<TestdokkaApplication>(*args)
}
