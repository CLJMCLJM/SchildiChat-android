/*
 * Copyright (c) 2021 The Matrix.org Foundation C.I.C.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.matrix.android.sdk.internal.federation

import org.matrix.android.sdk.api.federation.FederationVersion
import org.matrix.android.sdk.internal.network.executeRequest
import org.matrix.android.sdk.internal.task.Task
import javax.inject.Inject

internal interface GetFederationVersionTask : Task<Unit, FederationVersion>

internal class DefaultGetFederationVersionTask @Inject constructor(
        private val federationAPI: FederationAPI
) : GetFederationVersionTask {

    override suspend fun execute(params: Unit): FederationVersion {
        val result = executeRequest<FederationGetVersionResult>(null) {
            apiCall = federationAPI.getVersion()
        }

        return FederationVersion(
                name = result.server?.name,
                version = result.server?.version
        )
    }
}
