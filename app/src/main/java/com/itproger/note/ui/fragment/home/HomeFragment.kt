package com.itproger.note.ui.fragment.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.itproger.note.App
import com.itproger.note.R
import com.itproger.note.databinding.FragmentHomeBinding
import com.itproger.note.model.Task
import com.itproger.note.ui.fragment.home.adapter.TaskAdapter

class HomeFragment : Fragment(), TaskAdapter.Result, TaskAdapter.Update {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapter = TaskAdapter(this, this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = App.db.taskDao().getAll()
        adapter.setTasks(list)
                binding.btnAdd.setOnClickListener {
                    findNavController().navigate(R.id.taskFragment)
                }
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(position: Task) {
        val builder = AlertDialog.Builder(binding.root.context).setTitle("Удаление")
            .setMessage("Вы уверены, что хотите удалить это?").setPositiveButton("Да") { _, _ ->
                App.db.taskDao().delete(position = position)
                val list = App.db.taskDao().getAll()
                adapter.setTasks(list)
            }.setNegativeButton("Отмена") { _, _ ->
            }
        builder.show()
    }

    override fun onUpdate(position: Task) {
        val bundle = Bundle()
        bundle.putSerializable("task", position)
        findNavController().navigate(R.id.taskFragment, bundle)
    }
}
