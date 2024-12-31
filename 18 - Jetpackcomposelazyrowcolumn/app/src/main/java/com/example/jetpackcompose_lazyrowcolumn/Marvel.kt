package com.example.jetpackcompose_lazyrowcolumn


data class Marvel(
    var name :String,
    var charname:String,
    var img:Int
)

fun getallmarvel(): List<Marvel>{
    return listOf<Marvel>(
        Marvel("robert","ironman",R.drawable.ironman),
        Marvel("khabarnai","thor",R.drawable.thor),
        Marvel("peter parket","Spiderman",R.drawable.spider),

        Marvel("robert","ironman",R.drawable.ironman),
        Marvel("khabarnai","thor",R.drawable.thor),
        Marvel("peter parket","Spiderman",R.drawable.spider),

        Marvel("robert","ironman",R.drawable.ironman),
        Marvel("khabarnai","thor",R.drawable.thor),
        Marvel("peter parket","Spiderman",R.drawable.spider),

        Marvel("robert","ironman",R.drawable.ironman),
        Marvel("khabarnai","thor",R.drawable.thor),
        Marvel("peter parket","Spiderman",R.drawable.spider),

        Marvel("robert","ironman",R.drawable.ironman),
        Marvel("khabarnai","thor",R.drawable.thor),
        Marvel("peter parket","Spiderman",R.drawable.spider),
    )
}