/*
 * Copyright (c) 2015 Vitali Vasilioglo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vv.testy

import com.android.builder.testing.ConnectedDeviceProvider
import org.gradle.api.Plugin
import org.gradle.api.Project

class TestyPlugin implements Plugin<Project> {
    void apply(Project project) {
        def DEVICES_PROPERTY = "devices"
        ConnectedDeviceProvider.metaClass.getDevices = {
            def devices = localDevices
            if (project.hasProperty(DEVICES_PROPERTY)) {
                def devicesSerials = project.getProperties().get(DEVICES_PROPERTY).split(/,/)
                devices = localDevices.findAll { devicesSerials.contains(it.serialNumber) }
                println "[testy] ${!devices.empty ? "Execute on: ${devices.collect { it.serialNumber }}" : "Devices not found"}"
            }

            devices
        }
    }
}
