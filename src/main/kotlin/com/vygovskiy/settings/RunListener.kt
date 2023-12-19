package com.vygovskiy.settings

import org.apache.commons.logging.LogFactory
import org.slf4j.Logger
import org.springframework.boot.ConfigurableBootstrapContext
import org.springframework.boot.SpringApplication
import org.springframework.boot.SpringApplicationRunListener
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.EnumerablePropertySource
import java.time.Duration
import java.util.*

class RunListener() : SpringApplicationRunListener {
    private val logger = LogFactory.getLog(
        SpringApplication::class.java
    )


    override fun starting(bootstrapContext: ConfigurableBootstrapContext?) {
        logger.info(">>>>>>>>>>> environmentStarting <<<<<<<<<<<<<<<<<<<<") // will not output to log
        println(">>>>>>>>>>> environmentStarting <<<<<<<<<<<<<<<<<<<<")

    }

    override fun environmentPrepared(
        bootstrapContext: ConfigurableBootstrapContext?,
        environment: ConfigurableEnvironment?
    ) {
        logger.info(">>>>>>>>>>> environmentPrepared <<<<<<<<<<<<<<<<<<<<")
        environment?.getProperties()?.forEach { (key, value) ->
            logger.info("$key = $value")
        }

    }

    override fun contextPrepared(context: ConfigurableApplicationContext?) {
        logger.info("!!!!!!!!!!!!! contextPrepared !!!!!!!!!!!!!!")
    }

    override fun contextLoaded(context: ConfigurableApplicationContext?) {
        logger.info("!!!!!!!!!!!!! contextLoaded !!!!!!!!!!!!!!")
    }

    override fun started(context: ConfigurableApplicationContext?, timeTaken: Duration?) {
        logger.info("!!!!!!!!!!!!! started !!!!!!!!!!!!!!")
    }

    override fun ready(context: ConfigurableApplicationContext?, timeTaken: Duration?) {
        logger.info("!!!!!!!!!!!!! ready !!!!!!!!!!!!!!")
    }

    override fun failed(context: ConfigurableApplicationContext?, exception: Throwable?) {
        logger.error("!!!!!!!!!!!!! failed !!!!!!!!!!!!!!", exception)
    }
}


fun ConfigurableEnvironment.getProperties(): Map<String, String> {
    // I suppose that exists another, more correct way to solve this task
    val props = TreeMap<String, String>()
    propertySources.filter { ps -> ps is EnumerablePropertySource<*> }
        .map { ps -> (ps as EnumerablePropertySource<*>).propertyNames }.forEach { arrayPropNames ->
            arrayPropNames.forEach { propName ->
                try {
                    props.put(propName, getProperty(propName) ?: "")
                } catch (exc: Throwable) { /* Не логируем, т.к. это перехватчик ошибок интерполяции строк */
                }
            }
        }
    return props
}