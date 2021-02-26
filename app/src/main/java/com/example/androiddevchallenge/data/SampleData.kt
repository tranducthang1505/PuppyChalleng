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

import com.example.androiddevchallenge.R

private val americanBulldogInfo = DogInformation(
    kind = "American Bulldog",
    description = "The American Bulldog is a large breed of utility dog descended from the Old English Bulldog. They are now used on animal farms, dog sports, and for showing. They are part of American culture and history, and may be used as a cultural icon for the United States. They are generally represented as being strong and tough. In November 2019, the American Bulldog was added to the American Kennel Club (AKC) Foundation Stock Service (FSS).",
    stats = Stats(
        group = DogGroup.WORKING,
        height = 20 to 26,
        weight = 34f to 52f,
        litterSize = 7 to 16,
        lifeSpan = 10 to 15
    )
)

private val chihuahuaInfo = DogInformation(
    kind = "Chihuahua",
    description = "The Chihuahua (/tʃɪˈwɑːwə, -wɑː, -ˈwaʊ.ə/ (About this soundlisten); Spanish: chihuahueño) is one of the smallest breeds of dog, and is named after the Mexican state of Chihuahua.",
    stats = Stats(
        group = DogGroup.COMPANION_DOG,
        height = 6 to 10,
        weight = 1.8f to 2.7f,
        litterSize = 2 to 5,
        lifeSpan = 12 to 20
    )
)

private val bichonFriseInfo = DogInformation(
    kind = "Bichon Frise",
    description = "A Bichon Frise (/ˈbiːʃɒn ˈfriːz/ or /ˈbiːʃɒn frɪˈzeɪ/; from French: bichon à poil frisé, French pronunciation: \u200B[biʃɔ̃ fʁize], meaning 'curly haired dog') is a small breed of dog of the bichon type.",
    stats = Stats(
        group = DogGroup.COMPANION_DOG,
        height = 9 to 11,
        weight = 1.8f to 2.7f,
        litterSize = 1 to 6,
        lifeSpan = 12 to 15
    )
)

private val koreanJindoInfo = DogInformation(
    kind = "Korean Jindo",
    description = "The Korean Jindo (진돗개) is a breed of hunting dog that originated on Jindo Island in South Korea. Brought to the United States with South Korean immigrants, it is celebrated in its native land for its strong loyalty and bravery. The Jindo breed became recognized by the United Kennel Club on January 1, 1998 and by the Fédération Cynologique Internationale in 2005.",
    stats = Stats(
        group = DogGroup.SPORTING,
        height = 18 to 22,
        weight = 15f to 23f,
        litterSize = 1 to 4,
        lifeSpan = 15 to 25
    )
)

private val galleryImages
    get() = listOf(
        R.drawable.gallery_1,
        R.drawable.gallery_2,
        R.drawable.gallery_3,
        R.drawable.gallery_4,
        R.drawable.gallery_5
    ).shuffled()

val puppy1 = Puppy(
    name = "Polo",
    about = "Polo has been in a foster home for nearly 2 yrs now. Her foster Mom tries to capture her in all her funny positions and expressions.",
    dogInformation = americanBulldogInfo,
    artwork = R.drawable.american_bulldog_puppy,
    days = 500,
    currentHeight = 14,
    currentWeight = 20f,
    galleryImage = galleryImages
)

val puppy2 = Puppy(
    name = "CoCo",
    about = "Coco is about 2 months old, she is very healthy and cheerful.",
    dogInformation = chihuahuaInfo,
    artwork = R.drawable.chihuhua_puppy,
    days = 65,
    currentHeight = 4,
    currentWeight = 1.8f,
    galleryImage = galleryImages
)

val puppy3 = Puppy(
    name = "Bruno",
    about = "Bruno is Coco's friend, but he's more mischievous, often running around the house. Enjoyed walking. Currently he is 1 years old",
    dogInformation = chihuahuaInfo,
    artwork = R.drawable.bruno_chihuahua,
    days = 360,
    currentHeight = 7,
    currentWeight = 2.0f,
    galleryImage = galleryImages
)

val puppy4 = Puppy(
    name = "Lady",
    about = "Lady is very young, about 8 months old. Her fur is very smooth and soft, and everyone wants to hug her when encountered. But she is very picky eats.",
    dogInformation = bichonFriseInfo,
    artwork = R.drawable.bichon_frise,
    days = 220,
    currentHeight = 6,
    currentWeight = 1.6f,
    galleryImage = galleryImages
)

val puppy5 = Puppy(
    name = "Jonal",
    about = "Jonal is very friendly and does not like noise. He usually sleeps at noon.",
    dogInformation = koreanJindoInfo,
    artwork = R.drawable.korean_jindo,
    days = 100,
    currentHeight = 10,
    currentWeight = 7f,
    galleryImage = galleryImages
)

val allPuppies = listOf(puppy1, puppy2, puppy3, puppy4, puppy5)
