package dev.corruptedark.fetchrewardsexercise.repositories

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import dev.corruptedark.fetchrewardsexercise.data.FetchRewardsItem
import org.json.JSONArray

class JSONDownloadRepository {

    companion object {
        private const val ID_KEY = "id"
        private const val LIST_ID_KEY = "listId"
        private const val NAME_KEY = "name"
    }


    fun startDownload(context: Context, successListener: (List<FetchRewardsItem>) -> Unit) {
        val requestQueue = Volley.newRequestQueue(context)

        val url = "https://fetch-hiring.s3.amazonaws.com/hiring.json"
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                val rewardsItemList = parseJSONArray(response)
                val sortedFilteredList = rewardsItemList
                    .filter { item -> !(item.name.isNullOrEmpty() or item.name.equals("null")) }
                    .sortedWith(compareBy({ it.listId }, { it.name }))

                successListener.invoke(sortedFilteredList)
            },
            { error ->
                error.message?.let { Log.d(JSONDownloadRepository::class.java.name, it) }
            }
        )

        requestQueue.add(jsonArrayRequest)
    }

    private fun parseJSONArray(jsonArray: JSONArray): List<FetchRewardsItem> {
        val rewardItemList = mutableListOf<FetchRewardsItem>()

        for (outerIndex in 0..<jsonArray.length()) {
            val jsonObject = jsonArray.optJSONObject(outerIndex)

            jsonObject?.let { nonNullJsonObject ->
                val id = nonNullJsonObject.getInt(ID_KEY)
                val listId = nonNullJsonObject.getInt(LIST_ID_KEY)
                val name = nonNullJsonObject.getString(NAME_KEY)

                rewardItemList.add(FetchRewardsItem(id, listId, name))
            }
        }

        return rewardItemList
    }
}