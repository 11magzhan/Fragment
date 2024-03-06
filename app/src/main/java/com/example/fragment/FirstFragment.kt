package com.example.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragment.databinding.FragmentFirstBinding
import kotlin.random.Random

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var secondFragment: SecondFragment
    private lateinit var thirdFragment: ThirdFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        secondFragment = SecondFragment()
        thirdFragment = ThirdFragment()
        binding.btnChange.setOnClickListener { changeFragmentColors() }
        binding.btnSwap.setOnClickListener { swapFragments() }
    }
    fun changeFragmentColors() {
        secondFragment.view?.setBackgroundColor(generateRandomColor())
        thirdFragment.view?.setBackgroundColor(generateRandomColor())
    }

    fun swapFragments() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.thirdFragment, secondFragment)
            .replace(R.id.secondFragment, thirdFragment)
            .commit()
    }

    private fun generateRandomColor(): Int {
        return Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}