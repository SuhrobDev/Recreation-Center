package dev.soul.recreationcenter.presentation.ui.feature.blogInfo.composable

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import dev.soul.recreationcenter.R
import dev.soul.recreationcenter.presentation.ui.feature.blogInfo.viewmodel.BlogInfoViewModel
import dev.soul.recreationcenter.presentation.ui.theme.CustomThemeManager
import dev.soul.recreationcenter.presentation.ui.theme.blue
import dev.soul.recreationcenter.presentation.utils.dateConvert
import dev.soul.recreationcenter.presentation.utils.roboto400
import dev.soul.recreationcenter.presentation.utils.roboto700

@Composable
fun BlogInfoScreen(
    viewModel: BlogInfoViewModel = hiltViewModel(),
    blogId: Int,
    navController: NavController,
) {
    Log.d("KJDBASDBA", "BlogInfoScreen: $")

    LaunchedEffect(Unit) {
        viewModel.getBlogInfo(blogId)
    }

    val blogListState = viewModel.blogInfoState.collectAsState().value

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
    }

    BackHandler {
        navController.navigateUp()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomThemeManager.colors.screenBackground)
            .verticalScroll(rememberScrollState())
    ) {

        BlogInfoHead(catImage = blogListState.data?.image?.lg ?: "") {
            navController.navigateUp()
        }
        blogListState.data?.let { it1 ->
            BlogTitle(
                date = it1.date, title = it1.title
            )
        }
        blogListState.data?.let { it1 -> BlogDescription(description = it1.content) }

        if (progress) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 72.dp)
            ) {
                CircularProgressIndicator(color = blue, strokeWidth = 2.dp)
            }
        }
    }

}


@Composable
private fun BlogInfoHead(
    catImage: String, onBackButtonClicked: () -> Unit
) {
    Box {
        AsyncImage(
            model = catImage,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(304.dp),
            contentScale = ContentScale.Crop
        )
        TopAppBar(
            backgroundColor = Color.Transparent, elevation = 0.dp
        ) {
            IconButton(
                onClick = onBackButtonClicked,
                modifier = Modifier.padding(start = 6.dp, top = 12.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_round_back_button),
                    contentDescription = null
                )

            }
        }
    }
}


@Composable
fun BlogTitle(date: String, title: String) {
    val padding16 = dimensionResource(id = R.dimen.padding_medium)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = padding16, vertical = padding16)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = date.dateConvert(),
            fontSize = 12.sp,
            fontFamily = roboto400,
            style = TextStyle(lineHeight = 16.sp),
            maxLines = 1,
            textAlign = TextAlign.Start,
            color = CustomThemeManager.colors.textColor
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            fontSize = 24.sp,
            fontFamily = roboto700,
            style = TextStyle(lineHeight = 28.sp),
            maxLines = 3,
            color = CustomThemeManager.colors.textColor
        )
        Spacer(modifier = Modifier.height(padding16))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp), color = CustomThemeManager.colors.dividerColor
        )

    }
}

@Composable
fun BlogDescription(description: String) {
    val padding16 = dimensionResource(id = R.dimen.padding_medium)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = padding16, end = padding16, bottom = padding16)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = description,
            fontFamily = roboto400,
            fontSize = 16.sp,
            style = TextStyle(lineHeight = 24.sp),
            color = CustomThemeManager.colors.textColor
        )
    }
}