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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Pets
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.data.allPuppies
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun PuppiesOverview(navController: NavHostController) {
    Scaffold(
        topBar = {
            AppBar()
        },
        content = { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                content = {
                    items(allPuppies) { item ->
                        PuppyItem(
                            puppy = item,
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                            onItemClick = {
                                navController.navigate("detail/${it.id}")
                            }
                        )
                    }
                }
            )
        }
    )
}

@Composable
private fun AppBar() {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Rounded.Pets,
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        },
        title = {
            Text(text = stringResource(R.string.app_title))
        },
        backgroundColor = MaterialTheme.colors.primarySurface
    )
}

@Composable
fun PuppyItem(puppy: Puppy, modifier: Modifier = Modifier, onItemClick: (Puppy) -> Unit) {
    Card(modifier = modifier.fillMaxWidth()) {
        Row(Modifier.clickable { onItemClick(puppy) }) {
            Image(
                painter = painterResource(id = puppy.artwork),
                contentDescription = "Puppy Artwork",
                modifier = Modifier
                    .size(140.dp)
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .height(140.dp)
                    .padding(start = 8.dp, top = 8.dp)
            ) {
                Text(text = puppy.name, style = MaterialTheme.typography.h5)
                Text(
                    text = puppy.about, maxLines = 2, overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.weight(1f))
                Row {
                    Text(
                        text = puppy.dogInformation.kind,
                        Modifier.align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.caption
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    val price = String.format("%.1f", puppy.pricePerHour)
                    Surface(
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.clip(RoundedCornerShape(topStart = 12.dp))
                    ) {
                        Text(
                            text = stringResource(id = R.string.price_per_a_hour, price),
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PuppyItemPreview() {
    PuppyItem(puppy = allPuppies.random()) {
    }
}

@Preview(device = Devices.PIXEL_2)
@Composable
fun PuppiesOverviewPreview() {
    MyTheme {
        val navController = rememberNavController()
        PuppiesOverview(navController)
    }
}
