package com.cvargas.myevents.ui.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cvargas.myevents.data.model.Event
import com.cvargas.myevents.databinding.ActivityHomeBinding
import com.cvargas.myevents.ui.view.adapters.EventAdapter
import com.cvargas.myevents.ui.viewmodel.HomeViewModel
import com.cvargas.myevents.utils.Status

class HomeActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        setupViews()
    }

    private fun setupViews() {
        setRecycleViewLayoutManager()
    }

    private fun setRecycleViewLayoutManager() {
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        adapter = EventAdapter(mutableListOf()) { event ->
            val intent = Intent(baseContext, DetailActivity::class.java)
            intent.putExtra("eventId", event.id)
            startActivity(intent)
        }
        viewBinding.subContent.recyclerView.addItemDecoration(divider)
        viewBinding.subContent.recyclerView.layoutManager = LinearLayoutManager(this)
        viewBinding.subContent.recyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        observeAllEvents()
    }

    private fun observeAllEvents() {
        viewModel.getEvents().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    viewBinding.subContent.progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    viewBinding.subContent.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    viewBinding.subContent.progressBar.visibility = View.VISIBLE
                    viewBinding.subContent.recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    viewBinding.subContent.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(users: List<Event>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

}