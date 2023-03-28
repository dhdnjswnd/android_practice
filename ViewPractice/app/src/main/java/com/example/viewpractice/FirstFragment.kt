package com.example.viewpractice

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.viewpractice.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private var timeCountThread: TimeCountThread? = null

    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            // 메시지 처리 작업 수행
            println("메세지 처리 작업 수행")
            println(msg.data.getInt("key"))
        }
    }
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonFirst.setOnClickListener {
            // 스레드 실행
            if(timeCountThread==null) {
                timeCountThread = context?.let { it1 ->
                    TimeCountThread(
                        handler,
                        it1, binding.textviewFirst
                    )
                }
                timeCountThread?.start()
            }
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        // 스레드 중지
        binding.buttonSecond.setOnClickListener {
            timeCountThread?.stopThread()
            timeCountThread=null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}