package com.example.androidarchitecturesample.data.api

import com.google.gson.annotations.SerializedName

data class Entry(
    val id: Int,
    val author: String,
    @SerializedName("author_avatar")
    val authorAvatar: String,
    @SerializedName("author_avatar_big")
    val authorAvatarBig: String,
    @SerializedName("author_avatar_med")
    val authorAvatarMed: String,
    @SerializedName("author_avatar_lo")
    val authorAvatarLo: String,
    @SerializedName("author_group")
    val authorGroup: Int,
    @SerializedName("author_sex")
    val authorSex: String,
    val date: String,
    val body: String,
    val source: Any,
    val url: String,
    val `receiver`: Any,
    @SerializedName("receiver_avatar")
    val receiverAvatar: Any,
    @SerializedName("receiver_avatar_big")
    val receiverAvatarBig: Any,
    @SerializedName("receiver_avatar_med")
    val receiverAvatarMed: Any,
    @SerializedName("receiver_avatar_lo")
    val receiverAvatarLo: Any,
    @SerializedName("receiver_group")
    val receiverGroup: Any,
    @SerializedName("receiver_sex")
    val receiverSex: Any,
    val comments: List<Comment>,
    val blocked: Boolean,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("user_vote")
    val userVote: Int,
    @SerializedName("user_favorite")
    val userFavorite: Boolean,
    val voters: List<Any>,
    val type: String,
    val embed: Embed,
    val deleted: Boolean,
    @SerializedName("violation_url")
    val violationUrl: Any,
    @SerializedName("can_comment")
    val canComment: Any,
    val app: Any,
    @SerializedName("comment_count")
    val commentCount: Int
) {
    data class Comment(
        val id: Int,
        val author: String,
        @SerializedName("author_avatar")
        val authorAvatar: String,
        @SerializedName("author_avatar_big")
        val authorAvatarBig: String,
        @SerializedName("author_avatar_med")
        val authorAvatarMed: String,
        @SerializedName("author_avatar_lo")
        val authorAvatarLo: String,
        @SerializedName("author_group")
        val authorGroup: Int,
        @SerializedName("author_sex")
        val authorSex: String,
        val date: String,
        val body: String,
        @SerializedName("entry_id")
        val entryId: Int,
        val blocked: Boolean,
        val deleted: Boolean,
        @SerializedName("vote_count")
        val voteCount: Int,
        @SerializedName("user_vote")
        val userVote: Int,
        val voters: List<Any>,
        val type: String,
        val source: Any,
        val embed: Any,
        val app: Any,
        @SerializedName("violation_url")
        val violationUrl: Any
    )

    data class Embed(
        val type: String,
        val preview: String,
        val url: String,
        val plus18: Boolean,
        val source: String
    )
}