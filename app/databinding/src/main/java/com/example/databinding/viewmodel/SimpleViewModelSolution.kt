package com.example.databinding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class SimpleViewModelSolution : ViewModel() {
    private val _name = MutableLiveData("Ada")
    private val _lastName = MutableLiveData("Lovelace")
    private val _likes = MutableLiveData(0)

    val name: LiveData<String> = _name;
    val lastName: LiveData<String> = _lastName;
    val likes: LiveData<Int> = _likes;

    // Transformations : 데이터가 변하면서 조건에 따라 다른 데이터도 변경 시킬 때 유용하게 사용함
    // => swichMap or map 으로 대체됨

    val popularity: LiveData<Popularity> = Transformations.map(likes){
        if (it < 3) {
            Popularity.NORMAL
        } else if (it in 4..5) {
            Popularity.POPULAR
        } else {
            Popularity.STAR
        }
    }

    //test SwitchMap
    //새로운 liveData 반환 => RoomDatabase와 같이 LiveData를 반환하는 기능에서 사용 됨 (아직 이해가 잘 안감)
    val popularity_: MutableLiveData<Popularity>
        get() = MutableLiveData(Popularity.NORMAL)
    val _popularity_LiveData: LiveData<Popularity> = Transformations.switchMap(likes) {
        if (it < 3) {
            popularity_.value = Popularity.NORMAL
        } else if (it > 3 && it < 6) {
            popularity_.value = Popularity.POPULAR
        } else {
            popularity_.value = Popularity.STAR
        }
        popularity_
    }


    fun onLike() {
        _likes.value = (likes.value ?: 0) + 1
    }
}

enum class Popularity {
    NORMAL,
    POPULAR,
    STAR
}
