package com.vpavlov.mouse_robot.utils

import java.util.*

class CustomProperties : Properties() {

    abstract class PropertyKey<T>(
        val key: String, val defaultValue: T? = null
    ){
        abstract fun get(properties: CustomProperties): T?
    }

    class PropertyStringKey(key: String, defaultValue: String? = null): PropertyKey<String?>(
        key = key,
        defaultValue = defaultValue
    ) {
        override fun get(properties: CustomProperties): String? = properties.getString(key = key, default = defaultValue)

    }

    class PropertyIntKey(key: String, defaultValue: Int? = null): PropertyKey<Int?>(
        key = key,
        defaultValue = defaultValue
    ) {
        override fun get(properties: CustomProperties): Int?  = properties.getInt(key = key, default = defaultValue)
    }

    class PropertyDoubleKey(key: String, defaultValue: Double? = null): PropertyKey<Double>(
        key = key,
        defaultValue = defaultValue
    ) {
        override fun get(properties: CustomProperties): Double? = properties.getDouble(key = key, default = defaultValue)
    }

    class PropertyBooleanKey(key: String, defaultValue: Boolean? = null): PropertyKey<Boolean>(
        key = key,
        defaultValue = defaultValue
    ) {
        override fun get(properties: CustomProperties): Boolean? = properties.getBoolean(key = key, default = defaultValue)
    }

    class PropertyLongKey(key: String, defaultValue: Long? = null): PropertyKey<Long>(
        key = key,
        defaultValue = defaultValue
    ) {
        override fun get(properties: CustomProperties): Long? = properties.getLong(key = key, default = defaultValue)
    }

    fun getString(key: String, default: String? = null) = this.getProperty(key, default)

    fun getInt(key: String, default: Int? = null) = this.getProperty(key)?.toIntOrNull() ?: default

    fun getBoolean(key: String, default: Boolean? = null) = this.getProperty(key)?.toBoolean() ?: default

    fun getDouble(key: String, default: Double? = null) = this.getProperty(key)?.toDoubleOrNull() ?: default

    fun getLong(key: String, default: Long? = null) = this.getProperty(key)?.toLongOrNull() ?: default

}