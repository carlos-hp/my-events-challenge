package com.cvargas.myevents.data.repository

import com.google.gson.JsonDeserializer
import java.util.*

object GsonSerializer {
    val dateSerializer by lazy {
        JsonDeserializer { json, _, _ ->
            Date(json.asJsonPrimitive.asLong)
        }
    }
}