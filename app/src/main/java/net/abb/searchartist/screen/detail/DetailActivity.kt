package net.abb.searchartist.screen.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_detail.*
import net.abb.searchartist.R
import net.abb.searchartist.data.model.Album

class DetailActivity : AppCompatActivity() {

    private var album : Album? = null
    companion object{
        const val EXTRA_ALBUM = "EXTRA_ALBUM"
        @JvmStatic
        fun startActivity(context : Context , album : Album){
            val intet = Intent(context , DetailActivity::class.java)
            intet.putExtra(EXTRA_ALBUM,album )
            context.startActivity(intet)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent?.let {
            album =  it.getParcelableExtra<Album>(EXTRA_ALBUM)
        }


        album?.let {album ->
            tvDetailTitle.text = album?.name
            tvDetailSub.text = album?.artist
            album.url?.let {
                detailUrl.visibility = View.VISIBLE
                detailUrl.text =  applicationContext.getString(R.string.view_on_web)
                detailUrl.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setData(Uri.parse(album?.url))
                    startActivity(intent)
                }
            }

        }
    }
}
