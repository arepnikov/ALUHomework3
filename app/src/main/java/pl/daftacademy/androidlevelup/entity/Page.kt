package pl.daftacademy.androidlevelup.entity

import pl.daftacademy.androidlevelup.R

enum class Page(val menuBtnId: Int, val showText: Int, val filterBy: String?) {
    ALL_MOVIES(R.id.all_movies, R.string.all_movies, null),
    ACTION(R.id.action_movies, R.string.action, "Action"),
    COMEDY(R.id.comedy_movies, R.string.comedy, "Comedy"),
    CRIME(R.id.crime_movies, R.string.crime, "Crime"),
    HORROR(R.id.horror_movies, R.string.horror, "Horror"),
    ROMANCE(R.id.romance_movies, R.string.romance, "Romance");

    companion object {
        private val map = Page.values().associateBy(Page::menuBtnId)
        fun fromId(id: Int) = map[id]
    }
}