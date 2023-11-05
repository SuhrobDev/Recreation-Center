package dev.soul.recreationcenter.presentation.ui.feature.home.composable

import android.util.Log
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.soul.recreationcenter.R
import dev.soul.recreationcenter.domain.model.blog.BlogListModel
import dev.soul.recreationcenter.domain.model.main.ButtonModel
import dev.soul.recreationcenter.presentation.ui.base.SharedViewModel
import dev.soul.recreationcenter.presentation.ui.feature.home.viewmodel.HomeScreenViewModel
import dev.soul.recreationcenter.presentation.ui.navigation.Screens
import dev.soul.recreationcenter.presentation.ui.theme.CustomThemeManager
import dev.soul.recreationcenter.presentation.ui.theme.blue
import dev.soul.recreationcenter.presentation.ui.theme.toolBarBackgroundColor
import dev.soul.recreationcenter.presentation.utils.aboutGradient
import dev.soul.recreationcenter.presentation.utils.aboutIcon
import dev.soul.recreationcenter.presentation.utils.aboutText
import dev.soul.recreationcenter.presentation.utils.roboto400
import dev.soul.recreationcenter.presentation.utils.roboto500
import dev.soul.recreationcenter.presentation.utils.routeGradient
import dev.soul.recreationcenter.presentation.utils.routeIcon
import dev.soul.recreationcenter.presentation.utils.routeText
import dev.soul.recreationcenter.presentation.utils.weatherGradient
import dev.soul.recreationcenter.presentation.utils.weatherIcon
import dev.soul.recreationcenter.presentation.utils.weatherText

@Composable
fun HomeScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {

    Log.d("KJDBASDBA", "HomeScreen: ")

    val padding24 = dimensionResource(id = R.dimen.padding24)
    val padding16 = dimensionResource(id = R.dimen.padding_medium)
    val padding8 = dimensionResource(id = R.dimen.padding_small)

    val scrollState = rememberScrollState()

    val mainListState = viewModel.mainState.collectAsState().value
    val blogListState = viewModel.blogListState.collectAsState().value

    val blogList = remember {
        mutableStateListOf<BlogListModel>()
    }

    val topItemList = remember {
        mutableStateListOf<ButtonModel>()
    }
    var progress by remember {
        mutableStateOf(true)
    }

    if (blogListState.isLoading) {
        progress = true
    }
    if (blogListState.error.isNotEmpty()) {
        progress = false
        Toast.makeText(LocalContext.current, blogListState.error, Toast.LENGTH_SHORT).show()
    }

    if (blogListState.data != null) {
        progress = false
        blogList.clear()
        blogList.addAll(blogListState.data)
    }
    if (mainListState.data != null) {
        topItemList.clear()
        topItemList.addAll(mainListState.data.buttons)
    }

    val systemUiController = rememberSystemUiController()

    val c = CustomThemeManager.colors.screenBackground
    LaunchedEffect(Unit) {
        sharedViewModel.changeScreenTitle(Screens.Home.title)
        systemUiController.setSystemBarsColor(c)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomThemeManager.colors.screenBackground)
    ) {
        LazyColumn {

            item {
                Row(
                    modifier = Modifier
                        .horizontalScroll(scrollState, enabled = true)
                ) {
                    Spacer(modifier = Modifier.width(padding16))
                    ItemSmallTop(
                        cardBackground = weatherGradient,
                        image = weatherIcon,
                        contentText = weatherText
                    )
                    Spacer(modifier = Modifier.width(padding8))

                    ItemSmallTop(
                        cardBackground = aboutGradient, image = aboutIcon, contentText = aboutText
                    )
                    Spacer(modifier = Modifier.width(padding8))

                    ItemSmallTop(
                        cardBackground = routeGradient, image = routeIcon, contentText = routeText
                    )
                    Spacer(modifier = Modifier.width(padding16))

                }

                Spacer(modifier = Modifier.height(padding24))

                Row(
                    modifier = Modifier.background(CustomThemeManager.colors.screenBackground)
                ) {
                    Spacer(modifier = Modifier.width(padding16))
                    Text(
                        modifier = Modifier.wrapContentWidth(),
                        textAlign = TextAlign.Start,
                        text = "Домики и номера",
                        fontFamily = roboto400,
                        fontSize = 24.sp,
                        maxLines = 1,
                        color = CustomThemeManager.colors.textColor
                    )
                }
            }

            items(blogList.size, key = { id ->
                blogList[id].id
            }, itemContent = { index ->
                val blogData = blogList[index]
                ItemHouseAndHostel(
                    blogData = blogData
                ) { id ->
                    navController.navigate(Screens.Blog.passId(id))
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 112.dp)
                        .height(1.dp), color = CustomThemeManager.colors.dividerColor
                )
            })

        }
        if (progress) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 32.dp)
            ) {
                CircularProgressIndicator(color = blue, strokeWidth = 2.dp)
            }
        }

    }

}

/**ITEMS ON TOP OF SCREEN PLACED HORIZONTALLY*/
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemSmallTop(
    cardBackground: Brush, @DrawableRes image: Int, contentText: String
) {
    val padding8 = dimensionResource(id = R.dimen.padding_small)
    val padding12 = dimensionResource(id = R.dimen.padding12)

    Card(
        onClick = {},
        shape = RoundedCornerShape(padding8),

        ) {
        Row(
            modifier = Modifier
                .background(
                    brush = cardBackground
                )
                .padding(horizontal = padding12, vertical = padding12),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.size(17.dp, 20.dp)
            )

            Spacer(modifier = Modifier.width(padding8))

            Text(
                modifier = Modifier.wrapContentWidth(),
                textAlign = TextAlign.Start,
                text = contentText,
                fontFamily = roboto400,
                color = Color(toolBarBackgroundColor.toArgb()),
                fontSize = 14.sp,
                maxLines = 1
            )
        }
    }

}


/***ITEM HOUSE AND HOSTEL*/
@Composable
fun ItemHouseAndHostel(
    blogData: BlogListModel,
    onBlogClick: (blogId: Int) -> Unit
) {
    val padding16 = dimensionResource(id = R.dimen.padding_medium)

    Column(modifier = Modifier
        .clickable { onBlogClick.invoke(blogData.id) }
        .fillMaxWidth()
        .background(CustomThemeManager.colors.screenBackground)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding16)
        ) {

            AsyncImage(
                model = blogData.image.lg,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = blogData.title,
                    fontFamily = roboto500,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(lineHeight = 24.sp),
                    color = CustomThemeManager.colors.textColor
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = blogData.subtitle,
                    fontFamily = roboto500,
                    fontSize = 12.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(lineHeight = 16.sp),
                    color = CustomThemeManager.colors.textColor
                )

            }

        }
    }

}