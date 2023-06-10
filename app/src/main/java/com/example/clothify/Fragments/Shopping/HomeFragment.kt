package com.example.clothify.Fragments.Shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.clothify.Adapters.HomeViewpagerAdapter
import com.example.clothify.Fragments.Categories.CargosFragment
import com.example.clothify.Fragments.Categories.CropTopsFragment
import com.example.clothify.Fragments.Categories.HoodiesFragment
import com.example.clothify.Fragments.Categories.JeansFragment
import com.example.clothify.Fragments.Categories.JoggersFragment
import com.example.clothify.Fragments.Categories.MainCategoryFragment
import com.example.clothify.Fragments.Categories.TrousersFragment
import com.example.clothify.R
import com.example.clothify.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment: Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriesFragments = arrayListOf<Fragment>(
            MainCategoryFragment(),
            CargosFragment(),
            CropTopsFragment(),
            HoodiesFragment(),
            JeansFragment(),
            JoggersFragment(),
            TrousersFragment()
        )
        val  viewPager22Adapter =
            HomeViewpagerAdapter(categoriesFragments, childFragmentManager, lifecycle)
        binding.viewPagerHome.adapter = viewPager22Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPagerHome) {tab, position ->
            when (position) {
                0 -> tab.text = "Main"
                1 -> tab.text = "Cargos"
                2 -> tab.text = "CropTops"
                3 -> tab.text = "Hoodies"
                4 -> tab.text = "Jeans"
            }
        }.attach()
    }
}