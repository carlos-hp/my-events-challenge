package com.cvargas.myevents.ui.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cvargas.myevents.data.api.ApiHelper
import com.cvargas.myevents.data.api.ApiServiceImpl
import com.cvargas.myevents.data.model.Event
import com.cvargas.myevents.data.repository.EventRepository
import com.cvargas.myevents.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel : ViewModel() {
    private val eventRepository: EventRepository = EventRepository(ApiHelper(ApiServiceImpl()))

    private var eventId: String? = null
    private val eventLiveData = MutableLiveData<Resource<Event?>>()
    private val compositeDisposable = CompositeDisposable()

    val title: ObservableField<String> = ObservableField("")
    val body: ObservableField<String> = ObservableField("")

    fun getEvent(eventId: String): MutableLiveData<Resource<Event?>> {
        eventLiveData.postValue(Resource.loading(null))
        compositeDisposable.add(
            eventRepository.getEvent(eventId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ eventList ->
                    eventLiveData.postValue(Resource.success(eventList))
                }, {
                    eventLiveData.postValue(Resource.error("Something Went Wrong", null))
                })
        )
        return eventLiveData;
    }

    fun setEventId(eventId: String?) {
        this.eventId = eventId
    }
}