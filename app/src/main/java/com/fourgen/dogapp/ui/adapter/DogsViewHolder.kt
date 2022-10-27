package com.dreammkr.pokedex.ui.adapter

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.fourgen.dogapp.R
import com.fourgen.dogapp.databinding.ItemDogBinding
import com.fourgen.dogapp.ui.view.DogDetailActivity
import java.io.File
import java.io.FileOutputStream


class DogsViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {

    private val binding = ItemDogBinding.bind(view)

    fun render(dog_url: String, bread_name: String) {

        Glide.with(itemView.context)
            .load(dog_url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean = false

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    Palette.from(resource!!.toBitmap()).generate { palette ->
                        palette?.let {
                            val rgbColor = it.mutedSwatch?.rgb ?: it.vibrantSwatch?.rgb
                            binding.cvDog.setCardBackgroundColor(rgbColor!!)
                        }
                    }
                    return false
                }

            }).into(binding.ivDog)

        itemView.setOnLongClickListener {
            val intent = Intent(itemView.context, DogDetailActivity::class.java)
            intent.putExtra(itemView.context.getString(R.string.breed_name), bread_name)
            intent.putExtra(itemView.context.getString(R.string.breed_url), dog_url)
            itemView.context.startActivity(intent)
            false
        }

        binding.btnShare.setOnClickListener {
            val bitmap = (binding.ivDog.drawable as BitmapDrawable).bitmap
            shareImage(bitmap, bread_name)
        }

    }

    private fun shareImage(bitmap: Bitmap, name: String) {
        val uri: Uri = getImageToShare(bitmap)
        val intent = Intent(Intent.ACTION_SEND)

        intent.putExtra(Intent.EXTRA_STREAM, uri)
        intent.putExtra(Intent.EXTRA_TEXT, itemView.context.getString(R.string.share_image))
        intent.putExtra(Intent.EXTRA_SUBJECT, name)
        intent.type = itemView.context.getString(R.string.image_type)

        itemView.context.startActivity(
            Intent.createChooser(
                intent,
                itemView.context.getString(R.string.share_image)
            )
        )
    }

    // Retrieving the url to share
    private fun getImageToShare(bitmap: Bitmap): Uri {
        val imageFolder = File(itemView.context.cacheDir, "images")
        var uri: Uri? = null
        try {
            imageFolder.mkdirs()
            val file = File(imageFolder, "dog.png")
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream)
            outputStream.flush()
            outputStream.close()
            uri = FileProvider.getUriForFile(
                itemView.context,
                "com.fourgen.dogapp.fileprovider", file
            )
        } catch (e: Exception) {
            Toast.makeText(itemView.context, "" + e.message, Toast.LENGTH_LONG).show()
        }
        return uri!!
    }

}