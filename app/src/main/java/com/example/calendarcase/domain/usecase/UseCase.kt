package com.example.calendarcase.domain.usecase

import com.example.calendarcase.domain.model.Note
import com.example.calendarcase.util.DataHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class UseCase<Params>{

    abstract suspend fun run(params: Params): DataHolder<List<Note>>

    @DelicateCoroutinesApi
    operator fun invoke(
        params: Params,
        scope: CoroutineScope = GlobalScope,
        onResult: (DataHolder<List<Note>>) -> Unit = {},
    ) {
        scope.launch(Dispatchers.Main) {
            val deferred = async(Dispatchers.IO) {
                run(params)
            }
            val note = deferred.await()
            onResult(note)
        }
    }

}
