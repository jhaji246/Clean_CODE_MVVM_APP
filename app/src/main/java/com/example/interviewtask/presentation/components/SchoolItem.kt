package com.example.interviewtask.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.interviewtask.domain.models.School

@Composable
fun SchoolItem(modifier: Modifier, sample: School) {
    Column(
        modifier = modifier.fillMaxWidth()
            .padding(20.dp)
    ) {
        Card(elevation = CardDefaults.cardElevation(8.dp), shape = RoundedCornerShape(8.dp)) {
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                sample.dbn?.let { Text(text = it, fontWeight = FontWeight.Bold, color = Color.Blue) }
                sample.city?.let { Text(text = it, fontWeight = FontWeight.Bold, color = Color.Blue) }
                sample.location?.let { Text(text = it, fontWeight = FontWeight.Bold, color = Color.Blue) }
                sample.neighborhood?.let { Text(text = it, fontWeight = FontWeight.Bold, color = Color.Blue) }
            }
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}