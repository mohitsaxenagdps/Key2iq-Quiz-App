package com.key2iqintern.key2iqquizapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.key2iqintern.key2iqquizapp.R
import com.key2iqintern.key2iqquizapp.databinding.QuizItemBinding
import com.key2iqintern.key2iqquizapp.fragment.QuizFragment
import com.key2iqintern.key2iqquizapp.model.QuizModel


class QuizAdapter : RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    private val quizList = ArrayList<QuizModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val binding = QuizItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuizViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.bind(quizList[position])
    }

    override fun getItemCount() = quizList.size

    fun submitList(quiz: List<QuizModel>) {
        quizList.clear()
        quizList.addAll(quiz)
        notifyDataSetChanged()
    }

    inner class QuizViewHolder(private val binding: QuizItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(quiz: QuizModel) {
            binding.apply {
                if (quiz.options.size == 2) {
                    val opt = quiz.options.keys.toList()
                    option1.text = opt[0]
                    option2.text = opt[1]
                    option3.visibility = View.GONE
                    option4.visibility = View.GONE
                } else {
                    val opt = quiz.options.keys.toList()
                    option1.text = opt[0]
                    option2.text = opt[1]
                    option3.text = opt[2]
                    option4.text = opt[3]
                }
                tvQues.text = quiz.quesText
                btSubmit.disable()
                option1.setOnCheckedChangeListener { _, _ ->
                    btSubmit.enable()
                }
                option2.setOnCheckedChangeListener { _, _ ->
                    btSubmit.enable()
                }
                option3.setOnCheckedChangeListener { _, _ ->
                    btSubmit.enable()
                }
                option4.setOnCheckedChangeListener { _, _ ->
                    btSubmit.enable()
                }
                btSubmit.setOnClickListener {
                    var msg = ""
                    when (radioGroup.checkedRadioButtonId) {
                        R.id.option1 -> {
                            msg = if (quiz.options[option1.text.toString()]!!) {
                                "Correct"
                            } else {
                                "Wrong"
                            }
                        }
                        R.id.option2 -> {
                            msg = if (quiz.options[option2.text.toString()]!!) {
                                "Correct"
                            } else {
                                "Wrong"
                            }
                        }
                        R.id.option3 -> {
                            msg = if (quiz.options[option3.text.toString()]!!) {
                                "Correct"
                            } else {
                                "Wrong"
                            }
                        }
                        R.id.option4 -> {
                            msg = if (quiz.options[option4.text.toString()]!!) {
                                "Correct"
                            } else {
                                "Wrong"
                            }
                        }
                    }

                    try {
                        if (!quiz.options[option1.text.toString()]!!) {
                            option1.isEnabled = false
                        } else {
                            option1.isChecked = true
                        }
                        if (!quiz.options[option2.text.toString()]!!) {
                            option2.isEnabled = false
                        } else {
                            option2.isChecked = true
                        }
                        if (!quiz.options[option3.text.toString()]!!) {
                            option3.isEnabled = false
                        } else {
                            option3.isChecked = true
                        }
                        if (!quiz.options[option4.text.toString()]!!) {
                            option4.isEnabled = false
                        } else {
                            option4.isChecked = true
                        }
                    } catch (e: Exception) {
                    }


                    msgLayout.visibility = View.VISIBLE
                    if (msg == "Wrong") {
                        ivCelebrate.visibility = View.GONE
                        showMsg.text = "Wrong"
                    } else {
                        ivCelebrate.visibility = View.VISIBLE
                        showMsg.text = "Correct"
                    }

                    btSubmit.visibility = View.GONE
                    btContinue.visibility = View.VISIBLE

//                    val snackBar = Snackbar.make(it, msg, Snackbar.LENGTH_LONG)
//                    val view: View = snackBar.view
//                    val params = view.layoutParams as FrameLayout.LayoutParams
//                    params.gravity = Gravity.TOP
//                    view.layoutParams = params
//                    val tv =
//                        view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
//                    tv.gravity = Gravity.CENTER_HORIZONTAL;
//                    tv.textAlignment = View.TEXT_ALIGNMENT_CENTER;
//
//                    snackBar.show()
                }
                btContinue.setOnClickListener {
                    var pos = QuizFragment.binding.viewPager.currentItem
                    QuizFragment.binding.viewPager.setCurrentItem(++pos, false)
                }
            }
        }
    }

    fun View.disable() {
        isEnabled = false
        setBackgroundResource(R.drawable.button_selector)
    }

    fun View.enable() {
        isEnabled = true
        setBackgroundResource(R.drawable.button_selector)
    }

}