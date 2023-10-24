package br.edu.ifsp.scl.fastcalculation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifsp.scl.fastcalculation.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var fragmentResultBinding: FragmentResultBinding
    private var result: Float = 0.0f
    private lateinit var settings: Settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            settings = it.getParcelable(Extras.EXTRA_SETTINGS) ?: Settings()
            result = it.getFloat(Extras.EXTRA_RESULT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentResultBinding = FragmentResultBinding.inflate(inflater, container, false)
        with(fragmentResultBinding) {
            "%.1f".format(result).also {
                resultTv.text = it
            }
        }
        fragmentResultBinding.restartBt.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.gameFl, GameFragment.newInstance(settings))
                ?.commit()
        }
        return fragmentResultBinding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(result: Float, settings: Settings) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putFloat(Extras.EXTRA_RESULT, result)
                    putParcelable(Extras.EXTRA_SETTINGS, settings)
                }
            }
    }
}