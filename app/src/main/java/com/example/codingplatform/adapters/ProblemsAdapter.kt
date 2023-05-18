package com.example.codingplatform.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codingplatform.R
import com.example.codingplatform.databinding.ItemViewBinding
import com.example.codingplatform.models.Problem

class ProblemsAdapter(private val problemsList: List<Problem>, private val listener: ProblemItemListener) :
    RecyclerView.Adapter<ProblemsAdapter.ProblemItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemItemViewHolder {
        return ProblemItemViewHolder(
            ItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProblemItemViewHolder, position: Int) {
        holder.bindItem(problemsList[position], listener)
    }

    override fun getItemCount(): Int {
        return problemsList.size
    }

    class ProblemItemViewHolder(private val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(problem: Problem, listener: ProblemItemListener) {
            with(itemView) {
                binding.problemNumber.text = problem.problemNumber.toString()
                binding.problemName.text = problem.problemName
                if (problem.isLocked) {
                    setBackgroundColor(resources.getColor(R.color.yellow))
                    binding.lockedOrNot.setImageResource(R.drawable.ic_baseline_lock_24)
                    binding.problemName.setTextColor(resources.getColor(R.color.red))
                }

                setOnClickListener {
                    listener.onItemClicked(problem)
                }
            }
        }
    }
}

interface ProblemItemListener {
    fun onItemClicked(item: Problem)
}