package ui.app.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import ui.model.Version
import kotlin.math.round

@Composable
fun VersionTable(
    versions: List<Version>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        item {
            TableHeader(
                modifier = Modifier.fillMaxSize()
            )
        }

        items(versions) { version ->
            TableVersionItem(
                version = version,
                modifier = Modifier.fillMaxSize()
            )
            Divider()
        }
    }
}

@Composable
private fun TableHeader(modifier: Modifier = Modifier) {
    Row(modifier) {
        AutoSizeText(
            text = "ANDROID PLATFORM\nVERSION",
            textAlign = TextAlign.Center,
            maxLines = 2,
            modifier = Modifier.weight(0.3f).align(Alignment.CenterVertically)
        )

        AutoSizeText(
            text = "API LEVEL",
            textAlign = TextAlign.Center,
            maxLines = 2,
            modifier = Modifier.weight(0.3f).align(Alignment.CenterVertically)
        )

        AutoSizeText(
            text = "CUMULATIVE\nDISTRIBUTION",
            textAlign = TextAlign.Center,
            maxLines = 2,
            modifier = Modifier.weight(0.3f).align(Alignment.CenterVertically)
        )
    }
}

@Composable
private fun TableVersionItem(
    version: Version,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        AutoSizeText(
            text = "${version.version} ${version.name}",
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(0.3f).align(Alignment.CenterVertically)
        )

        AutoSizeText(
            text = "${version.apiLevel}",
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(0.3f).align(Alignment.CenterVertically)
        )

        val textCumulativeDistribution = if (version.cumulativeDistribution != null) {
            "${version.cumulativeDistribution?.format(1)}%"
        } else {
            ""
        }
        AutoSizeText(
            text = textCumulativeDistribution,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(0.3f).align(Alignment.CenterVertically)
        )
    }
}

private fun Double.format(decimals: Int): String {
    var multiplier = 1.0f
    repeat(decimals) { multiplier *= 10 }
    return (round(this * multiplier) / multiplier).toString()
}