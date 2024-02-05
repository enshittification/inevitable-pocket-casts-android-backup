package au.com.shiftyjelly.pocketcasts.settings.whatsnew

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.com.shiftyjelly.pocketcasts.compose.AppThemeWithBackground
import au.com.shiftyjelly.pocketcasts.ui.theme.Theme
import au.com.shiftyjelly.pocketcasts.images.R as IR

@Composable
fun SlumberStudiosHeader(
    modifier: Modifier = Modifier,
) {
    val width = LocalConfiguration.current.screenWidthDp - (48 + 32)
    val size = width / 4
    val infiniteTransition = rememberInfiniteTransition(label = "sway-animation")
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height((size + 60).dp),
    ) {
            Box(
                modifier = Modifier
                    .size(maxWidth),
                contentAlignment = Alignment.Center,
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.wrapContentWidth(),
                ) {
                    slumberStudioItems.forEach { item ->
                        val position by infiniteTransition.animateFloat(
                            initialValue = -20f,
                            targetValue = 20f,
                            animationSpec = infiniteRepeatable(
                                initialStartOffset = StartOffset(item.delay),
                                animation = tween(4000, easing = LinearEasing),
                                repeatMode = RepeatMode.Reverse,
                            ),
                            label = "",
                        )

                        SlumberStudiosImage(
                            imageRes = item.imageRes,
                            position = position,
                            modifier = Modifier
                                .size(size.dp),
                        )
                    }
                }
            }
    }
}

@Composable
private fun SlumberStudiosImage(
    @DrawableRes imageRes: Int,
    position: Float,
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        Box(
            Modifier
                .offset(y = position.dp),
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = "Slumber Studios",
                modifier = modifier.padding(8.dp),
            )
        }
    }
}

private val slumberStudioItems = listOf(
    SlumberStudioItem(
        imageRes = IR.drawable.slumber_studios_send_me_to_sleep,
        delay = 1000,
    ),
    SlumberStudioItem(
        imageRes = IR.drawable.slumber_studios_deep_sleep_sounds,
        delay = 2000,
    ),
    SlumberStudioItem(
        imageRes = IR.drawable.slumber_studios_get_sleepy,
        delay = 3000,
    ),
    SlumberStudioItem(
        imageRes = IR.drawable.slumber_studios_the_sleepy_bookshelf,
        delay = 4000,
    ),
)

data class SlumberStudioItem(
    val imageRes: Int,
    val delay: Int,
)

@Preview
@Composable
fun SlumberStudiosImagePreview() {
    AppThemeWithBackground(Theme.ThemeType.LIGHT) {
        SlumberStudiosHeader(
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}
