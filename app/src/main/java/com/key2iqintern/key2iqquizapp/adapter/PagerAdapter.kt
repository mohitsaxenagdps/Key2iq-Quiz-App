package com.key2iqintern.key2iqquizapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.key2iqintern.key2iqquizapp.R
import com.key2iqintern.key2iqquizapp.databinding.QuizItemBinding
import com.key2iqintern.key2iqquizapp.fragment.QuizFragment
import com.key2iqintern.key2iqquizapp.model.QuizModel

class PagerAdapter(private val context: Context) : PagerAdapter() {

    private val quizList = ArrayList<QuizModel>()

    override fun getCount() = quizList.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val binding = QuizItemBinding.inflate(LayoutInflater.from(context), container, false)
        val quiz = quizList[position]
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

        container.addView(binding.root)
        return binding.root

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    private fun View.disable() {
        isEnabled = false
        setBackgroundResource(R.drawable.button_selector)
    }

    fun View.enable() {
        isEnabled = true
        setBackgroundResource(R.drawable.button_selector)
    }

    fun submitList(quiz: List<QuizModel>) {
        quizList.clear()
        quizList.addAll(quiz)
        notifyDataSetChanged()
    }


}