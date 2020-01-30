package com.example.androidarchitecturesample.data.model

import com.google.gson.annotations.SerializedName

data class Promoted(
    val id: Int,
    val title: String,
    val description: String,
    val tags: String,
    val url: String,
    @SerializedName("source_url")
    val sourceUrl: String,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("comment_count")
    val commentCount: Int,
    @SerializedName("report_count")
    val reportCount: Int,
    @SerializedName("related_count")
    val relatedCount: Int,
    val date: String,
    val author: String,
    @SerializedName("author_group")
    val authorGroup: Int,
    @SerializedName("author_avatar")
    val authorAvatar: String,
    @SerializedName("author_avatar_big")
    val authorAvatarBig: String,
    @SerializedName("author_avatar_med")
    val authorAvatarMed: String,
    @SerializedName("author_avatar_lo")
    val authorAvatarLo: String,
    @SerializedName("author_sex")
    val authorSex: String,
    val type: String,
    val group: String,
    val preview: String,
    @SerializedName("user_vote")
    val userVote: Boolean,
    @SerializedName("user_favorite")
    val userFavorite: Boolean,
    @SerializedName("user_observe")
    val userObserve: Boolean,
    @SerializedName("user_lists")
    val userLists: List<Any>,
    val plus18: Boolean,
    val status: String,
    @SerializedName("can_vote")
    val canVote: Boolean,
    @SerializedName("has_own_content")
    val hasOwnContent: Boolean,
    @SerializedName("is_hot")
    val isHot: Boolean,
    val category: String,
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("violation_url")
    val violationUrl: Any,
    val info: Any,
    val app: Any
)