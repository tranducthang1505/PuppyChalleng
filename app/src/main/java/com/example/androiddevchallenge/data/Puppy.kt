/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.data

import androidx.annotation.DrawableRes
import java.util.UUID

data class Puppy(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val about: String,
    @DrawableRes val artwork: Int,
    val dogInformation: DogInformation,
    val days: Int,
    val currentHeight: Int,
    val currentWeight: Float,
    val galleryImage: List<Int>,
    val pricePerHour: Double
)

data class DogInformation(
    val kind: String,
    val description: String,
    val stats: Stats
)

data class Stats(
    val group: DogGroup,
    val height: Pair<Int, Int>, // min-max inches
    val weight: Pair<Float, Float>, // min-max kilograms
    val litterSize: Pair<Int, Int>, // min-max puppies
    val lifeSpan: Pair<Int, Int>, // min-max years
)

enum class DogGroup(val group: String) {
    WORKING("Working Dogs"),
    COMPANION_DOG("Companion Dogs"),
    SPORTING("Sporting Dogs")
}
