package com.vv.gradle

import org.gradle.api.Project
import org.gradle.api.Plugin
import groovy.lang.MissingPropertyException
import com.android.builder.testing.ConnectedDeviceProvider


class TestEnhancePlugin implements Plugin<Project> {
    void apply(Project project) {
		def DEVICES_PROPERTY = "devices"
		ConnectedDeviceProvider.metaClass.getProperty = { String name ->
		    def metaProperty = delegate.metaClass.getMetaProperty(name)
            if (metaProperty) {
                def property = metaProperty.getProperty(delegate)
                if (name.equals(DEVICES_PROPERTY) && project.hasProperty(DEVICES_PROPERTY)) {
                    def devicesSerials = project.getProperties().get(DEVICES_PROPERTY).split(/,/)
                    println("running tests on: ${devicesSerials}")
                    def devices = property.findAll { devicesSerials.contains(it.serialNumber) }
                    property = devices
                }
                property
            } else{
                throw new MissingPropertyException(name, getClass())
            }
		}
    }
}
