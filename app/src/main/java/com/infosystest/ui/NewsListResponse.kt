package com.infosystest.ui

data class NewsListResponse( var title: String? = "",
                        var rows: List<RowsEntity>?) {
    class RowsEntity( var title: String? ="",
                      var description: String? = "",
                      var imageHref: String? = "")
}