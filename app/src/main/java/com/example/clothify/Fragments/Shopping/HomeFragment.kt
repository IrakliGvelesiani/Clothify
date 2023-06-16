package com.example.clothify.Fragments.Shopping

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.clothify.R
import com.example.clothify.Adapters.HomeViewpagerAdapter
import com.example.clothify.databinding.FragmentHomeBinding
import com.example.clothify.Fragments.Categories.*
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriesFragments = arrayListOf(
            MainCategoryFragment(),
            CargosFragment(),
            CropTopsFragment(),
            HoodiesFragment(),
            JeansFragment(),
            JoggersFragment(),
            TrousersFragment()
        )

        binding.viewPagerHome.isUserInputEnabled = false

        val viewPager2Adapter =
            HomeViewpagerAdapter(categoriesFragments, childFragmentManager, lifecycle)
        binding.viewPagerHome.adapter = viewPager2Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPagerHome) { tab, position ->
            when (position) {
                0 -> tab.text = "Main"
                1 -> tab.text = "Cargos"
                2 -> tab.text = "Crop Tops"
                3 -> tab.text = "Hoodies"
                4 -> tab.text = "Jeans"
                5 -> tab.text = "Joggers"
                6 -> tab.text = "Trousers"
            }
        }.attach()
    }
}