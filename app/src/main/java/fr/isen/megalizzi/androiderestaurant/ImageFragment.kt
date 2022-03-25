package fr.isen.megalizzi.androiderestaurant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import fr.isen.megalizzi.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.megalizzi.androiderestaurant.databinding.FragmentImageBinding

class ImageFragment(val url: String?) : Fragment() {

    private lateinit var binding: FragmentImageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?  // Inflate the layout for this fragment
    {
        binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageFragment = binding.imageFragment
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.dish_placeholder2)
            .into(imageFragment)
    }
}