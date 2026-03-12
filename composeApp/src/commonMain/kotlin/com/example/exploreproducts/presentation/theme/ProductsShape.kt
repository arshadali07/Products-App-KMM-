package com.example.exploreproducts.presentation.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp

@Immutable
class ProductsShape(
    val fourPixelRounded: CornerBasedShape = ProductsShapeDefaults.FourPixelRounded,
    val sixPixelRounded: CornerBasedShape = ProductsShapeDefaults.SixPixelRounded,
    val eightPixelRounded: CornerBasedShape = ProductsShapeDefaults.EightPixelRounded,
    val tenPixelRounded: CornerBasedShape = ProductsShapeDefaults.TenPixelRounded,
    val twelvePixelRounded: CornerBasedShape = ProductsShapeDefaults.TwelvePixelRounded,
    val fourteenPixelRounded: CornerBasedShape = ProductsShapeDefaults.FourteenPixelRounded,
    val sixteenPixelRounded: CornerBasedShape = ProductsShapeDefaults.SixteenPixelRounded,
    val sixteenPixelBottomRounded: CornerBasedShape = ProductsShapeDefaults.SixteenPixelBottomRounded,
    val eighteenPixelRounded: CornerBasedShape = ProductsShapeDefaults.EighteenPixelRounded,
    val twentyPixelRounded: CornerBasedShape = ProductsShapeDefaults.TwentyPixelRounded,
    val twentyTwoPixelRounded: CornerBasedShape = ProductsShapeDefaults.TwentyTwoPixelRounded,
    val twentyFourPixelRounded: CornerBasedShape = ProductsShapeDefaults.TwentyFourPixelRounded,
    val twentySixPixelRounded: CornerBasedShape = ProductsShapeDefaults.TwentySixPixelRounded,
    val twentyEightPixelRounded: CornerBasedShape = ProductsShapeDefaults.TwentyEightPixelRounded,
    val thirtyPixelRounded: CornerBasedShape = ProductsShapeDefaults.ThirtyPixelRounded,
    val thirtyTwoPixelRounded: CornerBasedShape = ProductsShapeDefaults.ThirtyTwoPixelRounded,
    val fortyPixelRounded: CornerBasedShape = ProductsShapeDefaults.FortyPixelRounded,
    val thirtyTwoPixelBottomRounded: CornerBasedShape = ProductsShapeDefaults.ThirtyTwoPixelBottomRounded,
    val twentyFourPixelBottomRounded: CornerBasedShape = ProductsShapeDefaults.TwentyFourPixelBottomRounded,
    val twentyFourPixelTopRounded: CornerBasedShape = ProductsShapeDefaults.TwentyFourPixelTopRounded,
    val sixteenPixelTopRounded: CornerBasedShape = ProductsShapeDefaults.SixteenPixelTopRounded,
    val bottomSheetRounded: CornerBasedShape = ProductsShapeDefaults.TwentyFourPixelTopRounded,
)

private object ProductsShapeDefaults {
    val FourPixelRounded: CornerBasedShape = RoundedCornerShape(size = 4.dp)
    val SixPixelRounded: CornerBasedShape = RoundedCornerShape(size = 6.dp)
    val EightPixelRounded: CornerBasedShape = RoundedCornerShape(size = 8.dp)
    val TenPixelRounded: CornerBasedShape = RoundedCornerShape(size = 10.dp)
    val TwelvePixelRounded: CornerBasedShape = RoundedCornerShape(size = 12.dp)
    val FourteenPixelRounded: CornerBasedShape = RoundedCornerShape(size = 14.dp)
    val SixteenPixelRounded: CornerBasedShape = RoundedCornerShape(size = 16.dp)
    val SixteenPixelBottomRounded: CornerBasedShape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
    val EighteenPixelRounded: CornerBasedShape = RoundedCornerShape(size = 18.dp)
    val TwentyPixelRounded: CornerBasedShape = RoundedCornerShape(size = 20.dp)
    val TwentyTwoPixelRounded: CornerBasedShape = RoundedCornerShape(size = 22.dp)
    val TwentyFourPixelRounded: CornerBasedShape = RoundedCornerShape(size = 24.dp)
    val TwentySixPixelRounded: CornerBasedShape = RoundedCornerShape(size = 26.dp)
    val TwentyEightPixelRounded: CornerBasedShape = RoundedCornerShape(size = 28.dp)
    val ThirtyPixelRounded: CornerBasedShape = RoundedCornerShape(size = 30.dp)
    val ThirtyTwoPixelRounded: CornerBasedShape = RoundedCornerShape(size = 32.dp)
    val FortyPixelRounded: CornerBasedShape = RoundedCornerShape(size = 40.dp)
    val ThirtyTwoPixelBottomRounded: CornerBasedShape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
    val TwentyFourPixelBottomRounded: CornerBasedShape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
    val TwentyFourPixelTopRounded: CornerBasedShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    val SixteenPixelTopRounded: CornerBasedShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
}