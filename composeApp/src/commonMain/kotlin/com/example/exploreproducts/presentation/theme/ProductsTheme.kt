package com.example.exploreproducts.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import exploreproducts.composeapp.generated.resources.Res
import exploreproducts.composeapp.generated.resources.plusjakartasans_bold
import exploreproducts.composeapp.generated.resources.plusjakartasans_light
import exploreproducts.composeapp.generated.resources.plusjakartasans_medium
import exploreproducts.composeapp.generated.resources.plusjakartasans_regular
import exploreproducts.composeapp.generated.resources.plusjakartasans_semibold
import org.jetbrains.compose.resources.Font

@Immutable
data class ProductsTypography(
    //Caption Styles
    val captionOne10Regular: TextStyle = TextStyle.Default,
    val captionOne10Medium: TextStyle = TextStyle.Default,
    val captionTwo11Regular: TextStyle = TextStyle.Default,
    val captionTwo11Medium: TextStyle = TextStyle.Default,

    //Body Styles
    val bodyOne12Regular: TextStyle = TextStyle.Default,
    val bodyOne12Medium: TextStyle = TextStyle.Default,
    val bodyTwo14Regular: TextStyle = TextStyle.Default,
    val bodyTwo14Medium: TextStyle = TextStyle.Default,
    val bodyThree16Regular: TextStyle = TextStyle.Default,
    val bodyThree16Medium: TextStyle = TextStyle.Default,

    //Headline Styles
    val headlineOne18Regular: TextStyle = TextStyle.Default,
    val headlineOne18Medium: TextStyle = TextStyle.Default,
    val headlineTwo20Regular: TextStyle = TextStyle.Default,
    val headlineTwo20Medium: TextStyle = TextStyle.Default,
    val headlineThree22Regular: TextStyle = TextStyle.Default,
    val headlineThree22Medium: TextStyle = TextStyle.Default,
    val headlineFour24Regular: TextStyle = TextStyle.Default,
    val headlineFour24Medium: TextStyle = TextStyle.Default,
    val headlineFive26Regular: TextStyle = TextStyle.Default,
    val headlineFive26Medium: TextStyle = TextStyle.Default,
    val headlineSix28Regular: TextStyle = TextStyle.Default,
    val headlineSix28Medium: TextStyle = TextStyle.Default,
    val headlineSeven30Regular: TextStyle = TextStyle.Default,
    val headlineSeven30Medium: TextStyle = TextStyle.Default,
    val headlineEight32Regular: TextStyle = TextStyle.Default,
    val headlineEight32Medium: TextStyle = TextStyle.Default,
    val headlineNine36Regular: TextStyle = TextStyle.Default,
    val headlineNine36Medium: TextStyle = TextStyle.Default,
    val headlineTen40Regular: TextStyle = TextStyle.Default,
    val headlineTen40Medium: TextStyle = TextStyle.Default,
)

val PlusJakartaSans @Composable get() = FontFamily(
    Font(
        resource = Res.font.plusjakartasans_light,
        weight = FontWeight.Light
    ),
    Font(
        resource = Res.font.plusjakartasans_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resource = Res.font.plusjakartasans_medium,
        weight = FontWeight.Medium
    ),
    Font(
        resource = Res.font.plusjakartasans_semibold,
        weight = FontWeight.SemiBold
    ),
    Font(
        resource = Res.font.plusjakartasans_bold,
        weight = FontWeight.Bold
    ),
)

val CustomTypography @Composable get() = ProductsTypography(
    captionOne10Regular = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 16.sp
    ),
    captionOne10Medium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 16.sp
    ),
    captionTwo11Regular = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 16.sp
    ),
    captionTwo11Medium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp
    ),
    bodyOne12Regular = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    bodyOne12Medium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    bodyTwo14Regular = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    bodyTwo14Medium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    bodyThree16Regular = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyThree16Medium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    headlineOne18Regular = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 26.sp
    ),
    headlineOne18Medium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 26.sp
    ),
    headlineTwo20Regular = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 27.sp
    ),
    headlineTwo20Medium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 27.sp
    ),
    headlineThree22Regular = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    headlineThree22Medium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    headlineFour24Regular = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    headlineFour24Medium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    headlineFive26Regular = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp,
        lineHeight = 34.sp
    ),
    headlineFive26Medium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Medium,
        fontSize = 26.sp,
        lineHeight = 34.sp
    ),
    headlineSix28Regular = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),
    headlineSix28Medium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),
    headlineSeven30Regular = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
        lineHeight = 38.sp
    ),
    headlineSeven30Medium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Medium,
        fontSize = 30.sp,
        lineHeight = 38.sp
    ),
    headlineEight32Regular = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),
    headlineEight32Medium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),
    headlineNine36Regular = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp
    ),
    headlineNine36Medium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Medium,
        fontSize = 36.sp,
        lineHeight = 44.sp
    ),
    headlineTen40Regular = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp,
        lineHeight = 48.sp
    ),
    headlineTen40Medium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Medium,
        fontSize = 40.sp,
        lineHeight = 48.sp
    )
)