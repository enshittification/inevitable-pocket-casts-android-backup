package au.com.shiftyjelly.pocketcasts.core.ui.widget

import android.content.Context
import android.widget.RemoteViews
import androidx.compose.runtime.Composable
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.LocalContext
import androidx.glance.appwidget.AndroidRemoteViews
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxWidth
import au.com.shiftyjelly.pocketcasts.repositories.R

class GlancePodcastWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Content()
        }
    }
}

@Composable
private fun Content() {
    val packageName = LocalContext.current.packageName
    Row(
        modifier = GlanceModifier.fillMaxWidth()
    ) {
        AndroidRemoteViews(RemoteViews(packageName, R.layout.widget))
    }
}