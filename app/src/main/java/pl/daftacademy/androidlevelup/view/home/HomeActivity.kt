package pl.daftacademy.androidlevelup.view.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_home.*
import pl.daftacademy.androidlevelup.R

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

        if (savedInstanceState == null) showPage(getString(R.string.all_movies), addToBackStack = false)
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
        when (item.itemId) {
            R.id.all_movies -> showPage(getString(R.string.all_movies))
            R.id.action_movies -> showPage(getString(R.string.action))
            R.id.comedy_movies -> showPage(getString(R.string.comedy))
            R.id.crime_movies -> showPage(getString(R.string.crime))
            R.id.horror_movies -> showPage(getString(R.string.horror))
            R.id.romance_movies -> showPage(getString(R.string.romance))
            else -> return false
        }
        nav.menu.children.find { it.isChecked }?.isChecked = false
        item.isChecked = true
        drawer.closeDrawers()
        return true
    }

    private fun showPage(name: String, addToBackStack: Boolean = false) {
        supportActionBar?.apply {
            title = name
        }
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.container, ContentFragment.create(name))
//            .apply { if(addToBackStack) addToBackStack(null) }
//            .commit()
    }

}