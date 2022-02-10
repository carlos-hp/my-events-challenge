package com.cvargas.myevents.ui.view.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cvargas.myevents.R
import com.cvargas.myevents.data.model.Event
import com.cvargas.myevents.databinding.ActivityDetailBinding
import com.cvargas.myevents.ui.viewmodel.DetailViewModel
import com.cvargas.myevents.utils.Status


class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)

        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        binding.viewModel = viewModel
    }

    override fun onStart() {
        super.onStart()
        intent.extras?.getString("eventId")?.let { eventId ->
            viewModel.setEventId(eventId)
            observeGetEvent(eventId)
        }
    }

    private fun observeGetEvent(eventId: String) {
        viewModel.getEvent(eventId).observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { event -> renderEvent(event) }
                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderEvent(event: Event) {
        viewModel.title.set(event.title)
        viewModel.body.set(event.description)
    }
}