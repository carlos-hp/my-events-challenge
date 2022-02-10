package com.cvargas.myevents.ui.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cvargas.myevents.R
import com.cvargas.myevents.data.model.Event
import com.cvargas.myevents.databinding.ListItemEventBinding

class EventAdapter(private val events: MutableList<Event>, private val onClickListener: (Event) -> Unit) :
    RecyclerView.Adapter<EventAdapter.EventView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventView =
        EventView(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_event,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: EventView, position: Int) {
        val event = events[position]
        holder.binding.event = event
        holder.binding.root.setOnClickListener {
            onClickListener.invoke(event)
        }
    }

    override fun getItemCount(): Int = events.size

    inner class EventView constructor(val binding: ListItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    fun addData(list: List<Event>) {
        events.addAll(list)
    }
}
