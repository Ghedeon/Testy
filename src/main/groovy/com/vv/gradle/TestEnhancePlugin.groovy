package com.vv.gradle

import org.gradle.api.Project
import org.gradle.api.Plugin
import com.android.builder.testing.ConnectedDeviceProvider

class TestEnhancePlugin implements Plugin<Project> {
    void apply(Project project) {
		def DEVICES_PROPERTY = "devices"
        ConnectedDeviceProvider.metaClass.getDevices = {
            def devices = localDevices
            if (project.hasProperty(DEVICES_PROPERTY)){
                def devicesSerials = project.getProperties().get(DEVICES_PROPERTY).split(/,/)
                println("running tests on: ${devicesSerials}")
                devices = localDevices.findAll { devicesSerials.contains(it.serialNumber) }
            }

            devices
        }
    }
}
