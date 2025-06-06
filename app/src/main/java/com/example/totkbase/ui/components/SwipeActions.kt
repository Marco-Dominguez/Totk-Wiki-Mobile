package com.example.swipeactions

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

enum class Type{
    Icon,Text
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SwipeActionsRight(modifier: Modifier = Modifier,
                     isExpanded:Boolean, numberOfActions:Int = 2,
                     onChangedCard:(isExpanded:Boolean) -> Unit,
                     type: Type = Type.Icon, iconPadding: Dp = 29.dp,
                     cornerRadius: Dp = 24.dp, itemWidth: Dp = 100.dp,
                     actionOneColor: Color = Color.Red, actionTwoColor: Color = Color.White,
                     actionOneText: String = "Delete", actionTwoText: String = "Mail",
                     actionOneImage: ImageVector = Icons.Rounded.Delete,
                     actionTwoImage: ImageVector = Icons.Default.Email,
                     actionOneBackColor: Color = Color(0x5EA5A3A3),
                     actionTwoBackColor: Color = Color(0x5EA5A3A3),
                     cardBackground: Color = Color(0xFFCACACE),
                     actionOneClicked:() -> Unit = {}, actionTwoClicked:() -> Unit = {},
                     content: @Composable BoxScope.() -> Unit){


    var foregroundMaxWidth by remember {
        mutableFloatStateOf(0f)
    }
    val offsetX = remember {
        androidx.compose.animation.core.Animatable(initialValue = 0f)
    }


    LaunchedEffect(key1 = isExpanded) {

        if (isExpanded){
            coroutineScope  {
                onChangedCard(true)
                offsetX.animateTo(
                    -(foregroundMaxWidth),
                    animationSpec = spring(0.75f, stiffness = Spring.StiffnessLow)
                )

            }
        }else {
            coroutineScope  {
                onChangedCard(false)
                offsetX.animateTo(
                    0f,
                    animationSpec = spring(0.75f, stiffness = Spring.StiffnessLow)
                )

            }
        }
    }


    val size by animateDpAsState(targetValue = if (isExpanded) iconPadding else 50.dp,
        label = "", animationSpec = spring(dampingRatio = 0.68f,
            stiffness = Spring.StiffnessMediumLow)
    )


    val coroutineScope = rememberCoroutineScope()

    var dragAmnt by remember {
        mutableFloatStateOf(0f)
    }
    val density = LocalDensity.current


    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier,
            contentAlignment = Alignment.CenterEnd) {

            //SWIPE ITEMS
            Row(modifier = Modifier
                .fillMaxHeight()
                .background(Color.Transparent)
                .onSizeChanged {
                    foregroundMaxWidth = with(density) { it.width.toDp() }.value

                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End) {



                if (numberOfActions == 2){
                    Surface(modifier = Modifier
                        .fillMaxHeight()
                        .width(itemWidth)
                        .padding(horizontal = 4.dp)
                        .padding(start = 4.dp)
                        .bounceClick {
                            actionTwoClicked()
                            onChangedCard(false)

                        },
                        color = actionTwoBackColor,
                        shape = RoundedCornerShape(cornerRadius)
                    ) {
                        when(type){
                            Type.Icon -> {
                                Icon(imageVector = actionTwoImage, contentDescription = null,
                                    tint = actionTwoColor, modifier = Modifier.padding(size))
                            }
                            Type.Text -> {
                                Column(horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center) {
                                    AnimatedVisibility(visible = isExpanded,
                                        enter = fadeIn() + slideInHorizontally(),
                                        exit = fadeOut() + slideOutHorizontally()
                                    ) {
                                        Text(text = actionTwoText, color = actionTwoColor,
                                            fontWeight = FontWeight.Bold, fontSize = 17.sp)
                                    }
                                }

                            }
                        }

                    }
                }

                //CARD
                Surface(modifier = Modifier
                    .fillMaxHeight()
                    .width(itemWidth)
                    .padding(horizontal = 4.dp)
                    .padding(start = if (numberOfActions == 1) 4.dp else 0.dp)
                    .bounceClick {
                        actionOneClicked()
                        onChangedCard(false)
                    },
                    color = actionOneBackColor,
                    shape = RoundedCornerShape(cornerRadius)
                ) {
                    when(type){
                        Type.Icon -> {
                            Icon(imageVector = actionOneImage, contentDescription = null,
                                tint = actionOneColor, modifier = Modifier.padding(size))
                        }
                        Type.Text -> {
                            Column(horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center) {
                                AnimatedVisibility(visible = isExpanded,
                                    enter = fadeIn() + slideInHorizontally(),
                                    exit = fadeOut() + slideOutHorizontally()
                                ) {
                                    Text(text = actionOneText, color = actionOneColor,
                                        fontWeight = FontWeight.Bold, fontSize = 17.sp)
                                }
                            }

                        }
                    }
                }



            }

            Box (modifier = Modifier
                .offset(x = offsetX.value.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(cornerRadius))
                .background(cardBackground)
                .draggable(orientation = Orientation.Horizontal,
                    state = rememberDraggableState { dragAmount ->

                        dragAmnt = dragAmount

                        if (offsetX.value.absoluteValue <= foregroundMaxWidth && dragAmount < 0) {
                            coroutineScope.launch {
                                offsetX.animateTo(
                                    offsetX.value + dragAmount,
                                    animationSpec = spring(
                                        Spring.DampingRatioMediumBouncy,
                                        stiffness = Spring.StiffnessLow
                                    )
                                )
                            }
                        }

                        if (offsetX.value <= 0 && dragAmount > 0) {
                            coroutineScope.launch {
                                offsetX.animateTo(
                                    offsetX.value + dragAmount,
                                    animationSpec = spring(
                                        Spring.DampingRatioMediumBouncy,
                                        stiffness = Spring.StiffnessLow
                                    )
                                )
                            }
                        }
                        Log.i("sdfsdsdfs", "SwipeActionsTest: ${dragAmount} ")
                        if (dragAmount <= -12) {
                            coroutineScope.launch {
                                onChangedCard(true)
                                offsetX.animateTo(
                                    -foregroundMaxWidth,
                                    animationSpec = spring(0.75f, stiffness = Spring.StiffnessLow)
                                )

                            }
                        }

                        if (dragAmount >= 12) {
                            coroutineScope.launch {
                                onChangedCard(false)
                                offsetX.animateTo(
                                    0f,
                                    animationSpec = spring(0.75f, stiffness = Spring.StiffnessLow)
                                )

                            }
                        }

                    }, onDragStopped = { endedPosition ->

                        if (offsetX.value.absoluteValue >= (foregroundMaxWidth / 2) && dragAmnt.absoluteValue < 12) {
                            coroutineScope.launch {
                                onChangedCard(true)
                                offsetX.animateTo(
                                    -(foregroundMaxWidth),
                                    animationSpec = spring(0.75f, stiffness = Spring.StiffnessLow)
                                )

                            }
                        }

                        if (offsetX.value.absoluteValue < (foregroundMaxWidth / 2) && dragAmnt.absoluteValue < 12) {
                            coroutineScope.launch {
                                onChangedCard(false)
                                offsetX.animateTo(
                                    0f,
                                    animationSpec = spring(0.75f, stiffness = Spring.StiffnessLow)
                                )

                            }
                        }

                    }),
                contentAlignment = Alignment.Center){
                content()

            }
        }
    }


}

@Composable
fun SwipeActionsLeft(modifier: Modifier = Modifier,
                       isExpanded:Boolean, onChangedCard:(isExpanded:Boolean) -> Unit,
                       numberOfActions:Int = 2,
                       type: Type = Type.Icon, iconPadding: Dp = 29.dp,
                       cornerRadius: Dp = 24.dp, itemWidth: Dp = 100.dp,
                       actionOneColor: Color = Color.Red, actionTwoColor: Color = Color.White,
                       actionOneText: String = "Delete", actionTwoText: String = "Mail",
                       actionOneImage: ImageVector = Icons.Rounded.Delete,
                       actionTwoImage: ImageVector = Icons.Default.Email,
                       actionOneBackColor: Color = Color(0x5EA5A3A3),
                       actionTwoBackColor: Color = Color(0x5EA5A3A3),
                       cardBackground:Color = Color(0xFFCACACE),
                       actionOneClicked:() -> Unit = {}, actionTwoClicked:() -> Unit = {},
                       content: @Composable BoxScope.() -> Unit,
){

    var foregroundMaxWidth by remember {
        mutableFloatStateOf(0f)
    }
    val offsetX = remember {
        androidx.compose.animation.core.Animatable(initialValue = 0f)
    }


    LaunchedEffect(key1 = isExpanded) {

        if (isExpanded){
            coroutineScope  {
                onChangedCard(true)
                offsetX.animateTo(
                    foregroundMaxWidth,
                    animationSpec = spring(0.75f, stiffness = Spring.StiffnessLow)
                )

            }
        }else {
            coroutineScope  {
                onChangedCard(false)
                offsetX.animateTo(
                    0f,
                    animationSpec = spring(0.75f, stiffness = Spring.StiffnessLow)
                )

            }
        }
    }


    val size by animateDpAsState(targetValue = if (isExpanded) iconPadding else 50.dp,
        label = "", animationSpec = spring(dampingRatio = 0.68f,
            stiffness = Spring.StiffnessMediumLow)
    )


    val coroutineScope = rememberCoroutineScope()

    var dragAmnt by remember {
        mutableFloatStateOf(0f)
    }
    val density = LocalDensity.current





    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier,
            contentAlignment = Alignment.CenterStart) {

            //SWIPE ITEMS
            Row(modifier = Modifier
                .fillMaxHeight()
                .background(Color.Transparent)
                .onSizeChanged {
                    foregroundMaxWidth = with(density) { it.width.toDp() }.value
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start) {



                if (numberOfActions == 2){
                    //CARD
                    Surface(modifier = Modifier
                        .fillMaxHeight()
                        .width(itemWidth)
                        .padding(horizontal = 4.dp)
                        .padding(start = if (numberOfActions == 1) 4.dp else 0.dp)
                        .bounceClick {
                            actionTwoClicked()
                            onChangedCard(false)
                        },
                        color = actionTwoBackColor,
                        shape = RoundedCornerShape(cornerRadius)
                    ) {
                        when(type){
                            Type.Icon -> {
                                Icon(imageVector = actionTwoImage, contentDescription = null,
                                    tint = actionTwoColor, modifier = Modifier.padding(size))
                            }
                            Type.Text -> {
                                Column(horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center) {
                                    AnimatedVisibility(visible = isExpanded,
                                        enter = fadeIn() + slideInHorizontally(),
                                        exit = fadeOut() + slideOutHorizontally()
                                    ) {
                                        Text(text = actionTwoText, color = actionTwoColor,
                                            fontWeight = FontWeight.Bold, fontSize = 17.sp)
                                    }
                                }

                            }
                        }
                    }

                }
                Surface(modifier = Modifier
                    .fillMaxHeight()
                    .width(itemWidth)
                    .padding(horizontal = 4.dp)
                    .padding(end = 4.dp)
                    .bounceClick {
                        actionOneClicked()
                        onChangedCard(false)
                    },
                    color = actionOneBackColor,
                    shape = RoundedCornerShape(cornerRadius)
                ) {
                    when(type){
                        Type.Icon -> {
                            Icon(imageVector = actionOneImage, contentDescription = null,
                                tint = actionOneColor, modifier = Modifier.padding(size))
                        }
                        Type.Text -> {
                            Column(horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center) {
                                AnimatedVisibility(visible = isExpanded,
                                    enter = fadeIn() + slideInHorizontally(),
                                    exit = fadeOut() + slideOutHorizontally()
                                ) {
                                    Text(text = actionOneText, color = actionOneColor,
                                        fontWeight = FontWeight.Bold, fontSize = 17.sp)
                                }
                            }

                        }
                    }

                }




            }

            Box (modifier = Modifier
                .offset(x = offsetX.value.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(cornerRadius))
                .background(cardBackground)
                .draggable(orientation = Orientation.Horizontal,
                    state = rememberDraggableState { dragAmount ->

                        dragAmnt = dragAmount

                        //Open Left
                        if (offsetX.value.absoluteValue >= 0 && dragAmount > 0) {
                            coroutineScope.launch {
                                offsetX.animateTo(
                                    offsetX.value + dragAmount,
                                    animationSpec = spring(
                                        Spring.DampingRatioMediumBouncy,
                                        stiffness = Spring.StiffnessLow
                                    )
                                )
                            }
                        }

                        //Close
                        if (offsetX.value <= foregroundMaxWidth && dragAmount < 0) {
                            coroutineScope.launch {
                                offsetX.animateTo(
                                    offsetX.value + dragAmount,
                                    animationSpec = spring(
                                        Spring.DampingRatioMediumBouncy,
                                        stiffness = Spring.StiffnessLow
                                    )
                                )
                            }
                        }
                        Log.i("sdfsdsdfs", "SwipeActionsTest: ${dragAmount} ")
                        //Open
                        if (dragAmount >= 12) {
                            coroutineScope.launch {
                                onChangedCard(true)
                                offsetX.animateTo(
                                    foregroundMaxWidth,
                                    animationSpec = spring(0.75f, stiffness = Spring.StiffnessLow)
                                )

                            }
                        }

                        //Close
                        if (dragAmount <= -12) {
                            coroutineScope.launch {
                                onChangedCard(false)
                                offsetX.animateTo(
                                    0f,
                                    animationSpec = spring(0.75f, stiffness = Spring.StiffnessLow)
                                )

                            }
                        }

                    }, onDragStopped = { endedPosition ->

                        //Open
                        if (offsetX.value.absoluteValue >= (foregroundMaxWidth / 2) && dragAmnt.absoluteValue < 12) {
                            coroutineScope.launch {
                                onChangedCard(true)
                                offsetX.animateTo(
                                    (foregroundMaxWidth),
                                    animationSpec = spring(0.75f, stiffness = Spring.StiffnessLow)
                                )

                            }
                        }

                        //Close
                        if (offsetX.value.absoluteValue < (foregroundMaxWidth / 2) && dragAmnt.absoluteValue < 12) {
                            coroutineScope.launch {
                                onChangedCard(false)
                                offsetX.animateTo(
                                    0f,
                                    animationSpec = spring(0.75f, stiffness = Spring.StiffnessLow)
                                )

                            }
                        }

                    }),
                contentAlignment = Alignment.Center){
                content()

            }
        }
    }


}

fun Modifier.bounceClick(
    scaleDown: Float = 0.92f,
    onClick: () -> Unit
) = composed {

    val interactionSource = remember { MutableInteractionSource() }

    val animatable = remember {
        androidx.compose.animation.core.Animatable(1f)
    }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> animatable.animateTo(scaleDown)
                is PressInteraction.Release -> animatable.animateTo(1f)
                is PressInteraction.Cancel -> animatable.animateTo(1f)
            }
        }
    }

    Modifier
        .graphicsLayer {
            val scale = animatable.value
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            onClick()
        }
}