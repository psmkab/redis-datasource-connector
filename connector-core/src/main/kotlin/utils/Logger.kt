package utils

import org.slf4j.LoggerFactory

/**
 * NAME : sungmin park
 * DATE : 2019-09-14
 */

/**
 * todo ("add sample")
 * @sample
 */
inline fun <reified T: Any> logger() = LoggerFactory.getLogger(T::class.java)