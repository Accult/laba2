package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentFirstBinding

class FirstFragment : Fragment(), AnimalAdapter.Listener {

    private var _binding : FragmentFirstBinding? = null
    private val binding get() = _binding!!

    var animals = ArrayList<Animal>()
    lateinit var adapter: AnimalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var skipper = Animal("Skipper", getString(R.string.fullDescSkipper) , getString(R.string.urlSkipper))
        var kowalski = Animal("Kowalski", getString(R.string.fullDescKowalski), getString(R.string.urlKowalski))
        var private = Animal ("Private", getString(R.string.fullDescPrivate), getString(R.string.urlPrivate))
        var rico = Animal ("Rico",  getString(R.string.fullDescRico), getString(R.string.urlRico))

        animals.add(skipper)
        animals.add(kowalski)
        animals.add(private)
        animals.add(rico)
        adapter = AnimalAdapter(this ,animals, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        binding.rV.layoutManager = layoutManager
        binding.rV.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(itemView: Int) {
        val bundle = bundleOf(SecondFragment.numberInArray to itemView,
            SecondFragment.name to animals.get(itemView).name,
            SecondFragment.fullDesc to animals.get(itemView).fullDesc,
            SecondFragment.url to animals.get(itemView).urlPhoto)
        findNavController().navigate(R.id.action_firstFragment_to_secondFragment, bundle)
    }


}
