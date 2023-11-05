package dev.soul.recreationcenter.data.common

import dev.soul.recreationcenter.domain.Resource
import kotlinx.coroutines.flow.Flow


typealias FLOW_RESOURCE<T> = Flow<Resource<T>>