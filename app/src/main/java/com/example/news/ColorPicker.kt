package com.example.news

object ColorPicker {
    val colors = arrayOf("#FF007F","#FF0000","#FF7F00","#FFFF00","#7FFF00","#00FF00","#00FF7F","#00FFFF","#007FFF","#0000FF")
    var colorIndex = 0

    fun getColor():String{
        return colors[colorIndex++ % colors.size]
    }
}