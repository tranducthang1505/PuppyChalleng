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
        weight = 34 to 52,
        litterSize = 7 to 16,
        lifeSpan = 10 to 15
    )
)

val puppy1 = Puppy(
    name = "Polo",
    about = "Polo has been in a foster home for nearly 2 yrs now. Her foster Mom tries to capture her in all her funny positions and expressions.",
    dogInformation = americanBulldogInfo,
    artwork = R.drawable.american_bulldog_puppy
)

val allPuppies = List(100) { puppy1 }
