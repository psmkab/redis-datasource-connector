package utils.logger

import org.slf4j.LoggerFactory

/**
 * NAME : sungmin park
 * DATE : 2019-09-14
 */

/**
 * common logger
 *
 * @sample com.api.service.RedisOperationService
 */
inline fun <reified T: Any> logger() = LoggerFactory.getLogger(T::class.java)