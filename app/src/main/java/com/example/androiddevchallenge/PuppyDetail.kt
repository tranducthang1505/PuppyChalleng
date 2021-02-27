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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChildCare
import androidx.compose.material.icons.rounded.Height
import androidx.compose.material.icons.rounded.HourglassEmpty
import androidx.compose.material.icons.rounded.TrendingUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.data.Stats
import com.example.androiddevchallenge.data.allPuppies
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun PuppyDetail(puppy: Puppy) {
    Scaffold(
        topBar = {
            TopBarDetail(puppy = puppy)
        },
        content = { innerPadding ->
            ContentDetail(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp),
                puppy = puppy
            )
        }
    )
}

@Composable
private fun ContentDetail(
    modifier: Modifier = Modifier,
    puppy: Puppy
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier.verticalScroll(scrollState)
    ) {
        Row {
            Text(text = puppy.name, style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.weight(1f))
            Price(puppy.pricePerHour)
        }
        Text(modifier = Modifier.padding(top = 16.dp), text = puppy.about)
        PuppyInfo(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            puppy = puppy
        )
        SectionHeader(
            headerText = stringResource(R.string.gallery),
            modifier = Modifier.padding(top = 16.dp)
        )
        LazyRow(modifier = Modifier.padding(top = 16.dp)) {
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

        SectionHeader(
            headerText = puppy.dogInformation.kind + " Breed",
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(modifier = Modifier.padding(top = 16.dp), text = puppy.dogInformation.description)
        StatsLayout(
            modifier = Modifier.padding(vertical = 16.dp), stats = puppy.dogInformation.stats
        )
        Text(modifier = Modifier.padding(top = 8.dp), text = "From Wikipedia")
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

@Composable
private fun TopBarDetail(puppy: Puppy, modifier: Modifier = Modifier) {
    Box(modifier = modifier.height(200.dp)) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = puppy.artwork),
            contentDescription = "Puppy ArtWork",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun StatsLayout(stats: Stats, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            StatsItem(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.stats_height),
                icon = Icons.Rounded.Height,
                info = "${stats.height.first}inch - ${stats.height.second} (Inches)",
            )
            StatsItem(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.stats_weight),
                icon = Icons.Rounded.TrendingUp,
                info = "${stats.weight.first} - ${stats.weight.second} (Kilograms)"
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            StatsItem(
                modifier = Modifier.weight(1f),
                icon = Icons.Rounded.ChildCare,
                title = stringResource(R.string.stats_litter_size),
                info = "${stats.litterSize.first} - ${stats.litterSize.second} (Children)"
            )
            StatsItem(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.stats_life_span),
                icon = Icons.Rounded.HourglassEmpty,
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
            contentScale = ContentScale.FillWidth
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
        PuppyDetail(puppy = allPuppies.random())
    }
}
