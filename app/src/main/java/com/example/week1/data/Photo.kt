package com.example.week1.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.week1.R

data class Photo(
    @DrawableRes val imageResourceId: Int,
    @StringRes val nameId: Int,
    @StringRes val addressId: Int,
)


private val photos0 = listOf(
    Photo(R.drawable.bye, R.string.photo_name_0, R.string.address_0),
    Photo(R.drawable.reborn, R.string.photo_name_1, R.string.address_1),
    Photo(R.drawable.sejong, R.string.photo_name_2, R.string.address_2),
    Photo(R.drawable.areum, R.string.photo_name_3, R.string.address_3),
    Photo(R.drawable.plana, R.string.photo_name_4, R.string.address_4),
    Photo(R.drawable.thebody, R.string.photo_name_5, R.string.address_5),
    Photo(R.drawable.auditorium, R.string.photo_name_6, R.string.address_6),
    Photo(R.drawable.sportscomplex, R.string.photo_name_7, R.string.address_7),
    Photo(R.drawable.climbing, R.string.photo_name_8, R.string.address_8),
    Photo(R.drawable.basecampd, R.string.photo_name_9, R.string.address_9),
    Photo(R.drawable.basecampy, R.string.photo_name_10, R.string.address_10),
    Photo(R.drawable.awake, R.string.photo_name_11, R.string.address_11),
    Photo(R.drawable.gap, R.string.photo_name_12, R.string.address_12),
    Photo(R.drawable.mje, R.string.photo_name_13, R.string.address_13),
    Photo(R.drawable.sse, R.string.photo_name_14, R.string.address_14),
    Photo(R.drawable.newgreen, R.string.photo_name_15, R.string.address_15),
    Photo(R.drawable.science, R.string.photo_name_16, R.string.address_16),
    Photo(R.drawable.sung, R.string.photo_name_17, R.string.address_17),
    Photo(R.drawable.dplus, R.string.photo_name_18, R.string.address_18),
    Photo(R.drawable.kaist, R.string.photo_name_19, R.string.address_19)
)

//헬스
private val photos1 = listOf(
    photos0[1],
    photos0[2],
    photos0[3],
    photos0[4],
    photos0[5],
    photos0[6],
    photos0[7],
    photos0[13],
    photos0[14]
)

//클라이밍
private val photos2 = listOf(
    photos0[8],
    photos0[9],
    photos0[10],
    photos0[11]
)

//러닝
private val photos3 = listOf(
    photos0[7],
    photos0[12],
    photos0[19]
)

//수영
private val photos4 = listOf(
    photos0[6],
    photos0[15],
    photos0[16]
)

//복싱
private val photos5 = listOf(
    photos0[17],
    photos0[18]
)

//필라테스
private val photos6 = listOf(
    photos0[0]
)

val photos = listOf(
    photos0.shuffled(),
    photos1.shuffled(),
    photos2.shuffled(),
    photos3.shuffled(),
    photos4.shuffled(),
    photos5.shuffled(),
    photos6.shuffled()
)

