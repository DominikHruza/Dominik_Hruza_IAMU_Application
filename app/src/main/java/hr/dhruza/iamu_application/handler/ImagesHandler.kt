package hr.dhruza.iamu_application.handler

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import hr.dhruza.iamu_application.R
import hr.dhruza.iamu_application.factory.createGetHttpUrlConnection
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.io.File
import java.net.HttpURLConnection
import java.nio.file.Files
import java.nio.file.Paths


fun downloadImage(context: Context, url: String?, fileName: String?): String? {

    val ext = url?.substring(url.lastIndexOf("."))
    val file: File = createFile(context, fileName, ext)

    try {
        val con: HttpURLConnection = createGetHttpUrlConnection(url)
        Files.copy(con.inputStream, Paths.get(file.toURI()))
        return file.absolutePath
    } catch (e: Exception){
        Log.e("DOWNLOAD_IMAGE", e.message, e)
    }
    return null
}

fun createFile(context: Context, fileName: String?, ext: String?): File {
    val dir = context.applicationContext.getExternalFilesDir(null)
    val file = File(dir, File.separator + fileName + ext)
    if(file.exists()){
        file.delete()
    }
    return file
}

@BindingAdapter("android:src")
fun loadImage(view: ImageView, imageUrl: String?) {
    if (imageUrl != null){
        Picasso.get()
            .load(File(imageUrl))
            .error(R.drawable.food)
            .transform(RoundedCornersTransformation(50, 5))
            .into(view)
    } else {
        Picasso.get()
            .load(R.drawable.food)
            .transform(RoundedCornersTransformation(50, 5))
            .into(view)
    }
}