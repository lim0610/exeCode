package com.beike.sample.limengsample.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

/**
 * 创建时间: 2018/07/30 12:25 <br>
 * 作者: limeng <br>
 * 描述:
 */
class FeedBean {
  @SerializedName(value = "beike_name", alternate = ["beikeName"]) var beikeName: String? = null
  @SerializedName(value = "beike_id", alternate = ["beikeId"]) var beikeId: String? = null
  @SerializedName(value = "content_id", alternate = ["contentId"]) var contentId: String? = null
  var id: String? = null
  var type: String? = null
  @SerializedName(value = "recom_type", alternate = ["recomType"]) var recomType: String? = null
  @SerializedName(value = "bd_source", alternate = ["bdSource"]) var bdSource: String? = null
  var flog: JsonObject? = null
  var title: String? = null
  var pic: List<String>? = null
  var time: String? = null
  @SerializedName(value = "next_id", alternate = ["nextId"]) var nextId: String? = null
  var url: String? = null

  @SerializedName(value = "read_flag", alternate = ["readFlag"]) var readFlag: Boolean = false

  var exposed: Boolean = false
  var uniqId: String? = null

//  fun getDbId(): Int {
//    return id?.toInt() ?: 0
//  }
}