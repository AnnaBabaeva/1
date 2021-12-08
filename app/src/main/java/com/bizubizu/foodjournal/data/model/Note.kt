package com.bizubizu.foodjournal.data.model

import androidx.room.*
import java.net.FileNameMap


@Entity()
//@TypeConverters(NoteTypeConverters::class)
data class Note(

    @PrimaryKey(autoGenerate = true)
    var id: Int?,

    /*val image get() = "IMG_$id.jpg",*/

    var date: String,
    //var date: Date?,
    //var date: Date = Date(),

    //ресайкл вью
    @ColumnInfo(name = "diary_list")
    var diaryList: MutableList<NoteDiary>?,

    @ColumnInfo(name = "cause_food")
    var causeFood: String,

    var hunger: String,

    @ColumnInfo(name = "sensations_before_eating")
    var sensationsBeforeEating: String,

    @ColumnInfo(name = "sensations_feelings_before")
    var sensationsFeelingsBefore: String,

    @ColumnInfo(name = "taste_food")
    var tasteFood: String,

    @ColumnInfo(name = "sensations_after_eating")
    var sensationsAfterEating: String,

    @ColumnInfo(name = "sensations_feelings_after")
    var sensationsFeelingsAfter: String,

    //калории
    @ColumnInfo(name = "nutritional_value_list")
    var nutritionalValueList: MutableList<NutritionalValue>?,

    //???????????????????????????????
    var calories : String,

    var result: String?,

    var color: String?

) {
    constructor() : this(
        null,
        "",
        null,
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        null,
        "",                                       //???????
        null,
        null
    )

    val photoFileName
        get() = "IMG_$id.jpg"

}






/* //Profile

    // @ColumnInfo(name = "profileImage")
var profileImage:String?,

    // @ColumnInfo(name = "profileText")
var profileText:String?,

    // @ColumnInfo(name = "profileParameters")
var profileParameters:String?,

    // @ColumnInfo(name = "profileWeight")
var profileWeight:String?,

    // @ColumnInfo(name = "profileGoalKal")
var profileGoalKal:String?,

    // @ColumnInfo(name = "profileGoalParameters")
var profileGoalParameters:String?,

    // @ColumnInfo(name = "profileGoalWeight")
var profileGoalWeight:String?,*/

/* //Statistic

 // @ColumnInfo(name = "statisticFoodDay")
var statisticFoodDay:String?,

 // @ColumnInfo(name = "statisticKalDay")
var statisticKalDay:String?,

 // @ColumnInfo(name = "statisticMoodDay")
var statisticMoodDay:String?,

 // @ColumnInfo(name = "statisticCauseFoodDay")
var statisticCauseFoodDay:String?,

 // @ColumnInfo(name = "statisticTasteFood")
var statisticTasteFood:String?,

 // @ColumnInfo(name = "statisticAverageCalories")
var statisticAverageCalories:String?,

 // @ColumnInfo(name = "statisticCauseFood")
var statisticCauseFood:String?,

 // @ColumnInfo(name = "statisticHunger")
var statisticHunger:String?,

 // @ColumnInfo(name = "statisticSensationsBeforeEating")
var statisticSensationsBeforeEating:String?,

 // @ColumnInfo(name = "statisticSensationsFeelingsBefore")
var statisticSensationsFeelingsBefore:String?,

 // @ColumnInfo(name = "statisticSensationsAfterEating")
var statisticSensationsAfterEating:String?,

 // @ColumnInfo(name = "statisticSensationsFeelingsAfter")
var statisticSensationsFeelingsAfter:String?*/
