package net.abb.searchartist.screen.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import net.abb.searchartist.R

class MainActivity : AppCompatActivity()  {


    private lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fragment = findOrReplaceFragment()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    fun findOrReplaceFragment(): Fragment {
        var f = supportFragmentManager
            .findFragmentById(R.id.mainFragment)

        if (f == null) {
            f = MainFragment.newInstance()
            supportFragmentManager
                .beginTransaction().replace(R.id.mainFragment, f).commit()
        }
        return f
    }





}
