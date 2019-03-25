package pl.daftacademy.androidlevelup.view.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_home.*
import pl.daftacademy.androidlevelup.R
import pl.daftacademy.androidlevelup.entity.Page
import pl.daftacademy.androidlevelup.view.movies.MoviesFragment

val HOMEPAGE = Page.ALL_MOVIES

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        nav.setNavigationItemSelectedListener { changePage(item = it) }
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        if (savedInstanceState == null) showPage(HOMEPAGE, addToBackStack = false)


    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun changePage(item: MenuItem): Boolean {
        val page = Page.fromId(item.itemId) ?: return false

        showPage(page)
        nav.menu.children.find { it.isChecked }?.isChecked = false
        item.isChecked = true
        drawer.closeDrawers()
        return true
    }

    private fun showPage(page: Page, addToBackStack: Boolean = false) {
        supportActionBar?.apply {
            title = getString(page.showText)
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, MoviesFragment.create(page))
            .apply { if(addToBackStack) addToBackStack(null) }
            .commit()
    }
}