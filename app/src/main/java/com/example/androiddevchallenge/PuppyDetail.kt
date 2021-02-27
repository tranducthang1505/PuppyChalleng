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
package com.example.androiddevchallenge

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.data.Stats
import com.example.androiddevchallenge.data.TakeCareHistory
import com.example.androiddevchallenge.data.allPuppies
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.utils.isScrollingUp

@Composable
fun PuppyDetail(puppy: Puppy, navController: NavHostController) {
    val lazyListState: LazyListState = rememberLazyListState()
    Box {
        Scaffold {
            ContentDetail(
                puppy = puppy,
                lazyListState = lazyListState
            )
        }
        TopBarDetail(
            puppy = puppy,
            navController = navController,
            extended = lazyListState.isScrollingUp()
        )
    }
}

@Composable
private fun ContentDetail(
    modifier: Modifier = Modifier,
    puppy: Puppy,
    lazyListState: LazyListState
) {
    LazyColumn(modifier = modifier, state = lazyListState) {
        item {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = puppy.artwork),
                contentDescription = "Puppy ArtWork",
                contentScale = ContentScale.Crop
            )
        }
        item {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            ) {
                Text(text = puppy.name, style = MaterialTheme.typography.h4)
                Spacer(modifier = Modifier.weight(1f))
                Price(puppy.pricePerHour)
            }
        }
        item {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 16.dp),
                    text = puppy.about
                )
            }
        }
        item {
            PuppyInfo(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp),
                puppy = puppy
            )
        }
        item {
            SectionHeader(
                headerText = stringResource(R.string.take_care_history),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp)
            )
        }

        item {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                for (item in puppy.takeCareHistory) {
                    TakeCareHistoryLayout(
                        item,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }
        item {
            SectionHeader(
                headerText = stringResource(R.string.gallery),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp)
            )
        }

        item {
            LazyRow(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp)
            ) {
                items(puppy.galleryImage) { image ->
                    Image(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .width(100.dp)
                            .height(140.dp),
                        painter = painterResource(id = image),
                        contentDescription = "Gallery Image",
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }

        item {
            SectionHeader(
                headerText = puppy.dogInformation.kind + " Breed",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp)
            )
        }

        item {
            Text(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp),
                text = puppy.dogInformation.description
            )
        }
        item {
            StatsLayout(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .padding(horizontal = 16.dp),
                stats = puppy.dogInformation.stats
            )
        }
        item {
            Text(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 16.dp),
                text = "From Wikipedia"
            )
        }
    }
}

@Composable
fun TakeCareHistoryLayout(item: TakeCareHistory, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(text = item.date)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "${item.formTo.first} → ${item.formTo.second}")
    }
}

@Composable
fun Price(price: Double, modifier: Modifier = Modifier) {
    Column(modifier, horizontalAlignment = Alignment.End) {
        Text(
            text = stringResource(id = R.string.price, String.format("%.1f", price)),
            style = MaterialTheme.typography.h4
        )
        Text(
            text = stringResource(R.string.per_a_hour),
            style = MaterialTheme.typography.caption
        )
    }
}

@Composable
fun PuppyInfo(modifier: Modifier = Modifier, puppy: Puppy) {
    Row(modifier = modifier) {
        PuppyInfoCell(
            modifier = Modifier.weight(1f),
            value = puppy.currentHeight.toString(),
            des = stringResource(R.string.inches)
        )
        Spacer(modifier = Modifier.width(8.dp))
        PuppyInfoCell(
            modifier = Modifier.weight(1f),
            value = puppy.currentWeight.toString(),
            des = stringResource(R.string.kilograms)
        )
    }
}

@Composable
fun PuppyInfoCell(modifier: Modifier = Modifier, value: String, des: String) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = value, style = MaterialTheme.typography.h3)
            Text(text = des, modifier = Modifier.align(Alignment.Bottom))
        }
    }
}

@Composable
fun SectionHeader(headerText: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = headerText, style = MaterialTheme.typography.subtitle1)
        Divider(thickness = 1.dp, modifier = Modifier.padding(top = 8.dp))
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun TopBarDetail(
    puppy: Puppy,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    extended: Boolean
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable { navController.navigateUp() }
            )
        },
        title = {
            AnimatedVisibility(!extended) {
                Text(text = puppy.name)
            }
        },
        backgroundColor = if (extended) Color.Transparent else MaterialTheme.colors.primarySurface,
        elevation = if (extended) 0.dp else 4.dp
    )
}

@Composable
fun StatsLayout(stats: Stats, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            StatsItem(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.stats_height),
                icon = Icons.Filled.Height,
                info = "${stats.height.first} - ${stats.height.second} (Inches)",
            )
            StatsItem(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.stats_weight),
                icon = Icons.Filled.TrendingUp,
                info = "${stats.weight.first} - ${stats.weight.second} (Kg)"
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            StatsItem(
                modifier = Modifier.weight(1f),
                icon = Icons.Filled.ChildCare,
                title = stringResource(R.string.stats_litter_size),
                info = "${stats.litterSize.first} - ${stats.litterSize.second} (Children)"
            )
            StatsItem(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.stats_life_span),
                icon = Icons.Filled.HourglassEmpty,
                info = "${stats.lifeSpan.first} - ${stats.lifeSpan.second} (Years)"
            )
        }
    }
}

@Composable
fun StatsItem(icon: ImageVector, title: String, info: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier
                .size(36.dp),
            contentDescription = info,
            imageVector = icon,
            contentScale = ContentScale.FillWidth,
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
        )
        Text(text = title)
        Text(text = info)
    }
}

@Preview
@Composable
fun SectionHeaderPreview() {
    SectionHeader("More Detail")
}

@Preview
@Composable
fun StatsLayoutPreview() {
    StatsLayout(stats = allPuppies.random().dogInformation.stats)
}

@Preview
@Composable
fun PuppyDetailPreview() {
    MyTheme {
        val navController = rememberNavController()
        PuppyDetail(puppy = allPuppies.random(), navController)
    }
}
