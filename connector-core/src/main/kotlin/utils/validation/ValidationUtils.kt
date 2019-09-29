package utils.validation

/**
 * NAME : sungmin park
 * DATE : 2019-09-29
 */

/**
 * if object is not null, run operation.
 *
 * @throws when receiver is null, do not run operation and throw [IllegalArgumentException]
 */
inline fun <T, reified R> T.ifNotNull(operation: () -> R): R =
    if(this != null) operation()
    else throw IllegalArgumentException("Cannot run operation to null value..")

