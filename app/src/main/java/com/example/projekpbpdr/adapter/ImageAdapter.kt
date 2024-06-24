import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global.putString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projekpbpdr.Detail
import com.example.projekpbpdr.R
import com.example.projekpbpdr.data.ImageModel

class ImageAdapter(private val imageList: List<ImageModel>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_viewimg, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.clear() // Membersihkan semua ImageView sebelum mengikat gambar baru
        val startIndex = position * 7
        val endIndex = minOf(startIndex + 7, imageList.size)
        val subList = imageList.subList(startIndex, endIndex)
        subList.forEachIndexed { index, imageModel ->
            holder.bind(imageModel, index)
        }
    }

    override fun getItemCount(): Int {
        return (imageList.size + 6) / 7 // Untuk mengembalikan jumlah "halaman" yang diperlukan
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewArray: Array<ImageView> = arrayOf(
            itemView.findViewById(R.id.imageView1),
            itemView.findViewById(R.id.imageView2),
            itemView.findViewById(R.id.imageView3),
            itemView.findViewById(R.id.imageView4),
            itemView.findViewById(R.id.imageView5),
            itemView.findViewById(R.id.imageView6),
            itemView.findViewById(R.id.imageView7)
        )

        fun bind(imageModel: ImageModel, imageViewPosition: Int) {
            val imageView = imageViewArray[imageViewPosition]

            imageView.setOnClickListener {
                it.animate().scaleX(1.50f).scaleY(1.50f).setDuration(70).withEndAction {
                    it.animate().scaleX(1f).scaleY(1f).setDuration(100)
                }
                Toast.makeText(imageView.context, "ImageView clicked!", Toast.LENGTH_SHORT).show()

                // Start DetailFragment
                val imageUrl = imageModel.imageUrl
                val fragment = Detail()
                val bundle = Bundle().apply {
                    putString("IMAGE_URL", imageUrl)
                }
                fragment.arguments = bundle

                // Navigate to DetailFragment
                val activity = imageView.context as FragmentActivity
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .addToBackStack("DETAIL") // Optional: Add to back stack if needed
                    .commit()
            }

            imageView.visibility = View.VISIBLE
            Glide.with(itemView.context)
                .load(imageModel.imageUrl)
                .into(imageView)
        }

        fun clear() {
            for (imageView in imageViewArray) {
                imageView.visibility = View.GONE
                imageView.setImageResource(R.drawable.design5)
            }
        }
    }
}

//class ImageAdapter(private val imageList: List<ImageModel>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
//
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_viewimg, parent, false)
//        return ImageViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
//        holder.clear() // Membersihkan semua ImageView sebelum mengikat gambar baru
//        val startIndex = position * 7
//        val endIndex = minOf(startIndex + 7, imageList.size)
//        val subList = imageList.subList(startIndex, endIndex)
//        subList.forEachIndexed { index, imageModel ->
//            holder.bind(imageModel, index)
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return (imageList.size + 6) / 7 // Untuk mengembalikan jumlah "halaman" yang diperlukan
//    }
//
//    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val imageViewArray: Array<ImageView> = arrayOf(
//            itemView.findViewById(R.id.imageView1),
//            itemView.findViewById(R.id.imageView2),
//            itemView.findViewById(R.id.imageView3),
//            itemView.findViewById(R.id.imageView4),
//            itemView.findViewById(R.id.imageView5),
//            itemView.findViewById(R.id.imageView6),
//            itemView.findViewById(R.id.imageView7)
//        )
//
//        @SuppressLint("SuspiciousIndentation")
//        fun bind(imageModel: ImageModel, imageViewPosition: Int) {
//            val imageView = imageViewArray[imageViewPosition]
//
//                imageView.setOnClickListener {
//                    it.animate().scaleX(1.50f).scaleY(1.50f).setDuration(70).withEndAction {
//                        it.animate().scaleX(1f).scaleY(1f).setDuration(100)
//                    }
//                    Toast.makeText(imageView.context, "ImageView clicked!", Toast.LENGTH_SHORT).show()
//
//                    // Intent to start new activity
//                    val context = imageView.context
//                    val intent = Intent(context, InsideHomeOnImage::class.java).apply {
//                        putExtra("IMAGE_URL", imageModel.imageUrl)
//                    }
//                    context.startActivity(intent)
//                }
//
//            imageView.visibility = View.VISIBLE
//            Glide.with(itemView.context)
//                .load(imageModel.imageUrl)
//                .into(imageView)
//        }
//
//        fun clear() {
//            for (imageView in imageViewArray) {
//                imageView.visibility = View.GONE
//                imageView.setImageResource(R.drawable.design5)
//            }
//        }
//    }
//}








//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.projekpbpdr.R
//import com.example.projekpbpdr.data.ImageModel
//
//class ImageAdapter(private val imageList: List<ImageModel>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_viewimg, parent, false)
//        return ImageViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
//        holder.clear() // Membersihkan semua ImageView sebelum mengikat gambar baru
//        imageList.take(holder.imageViewArray.size).forEachIndexed { index, imageModel ->
//            holder.bind(imageModel, index)
//        }
//    }
//
//    override fun getItemCount(): Int {
//        var i:Int = 0
//        if (imageList.size/7 == 0){
//            i = 2
//        }else {
//
//        }
//
//        return 2
//    }
//
//    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val imageViewArray: Array<ImageView> = arrayOf(
//            itemView.findViewById(R.id.imageView1),
//            itemView.findViewById(R.id.imageView2),
//            itemView.findViewById(R.id.imageView3),
//            itemView.findViewById(R.id.imageView4),
//            itemView.findViewById(R.id.imageView5),
//            itemView.findViewById(R.id.imageView6),
//            itemView.findViewById(R.id.imageView7)
//        )
//
//        fun bind(imageModel: ImageModel, imageViewPosition: Int) {
//            val imageView = imageViewArray[imageViewPosition]
//            imageView.visibility = View.VISIBLE
//            Glide.with(itemView.context)
//                .load(imageModel.imageUrl)
//                .into(imageView)
//        }
//
//        fun clear() {
//            for (imageView in imageViewArray) {
//                imageView.visibility = View.GONE
//                imageView.setImageResource(R.drawable.design3)
//            }
//        }
//    }
//}
