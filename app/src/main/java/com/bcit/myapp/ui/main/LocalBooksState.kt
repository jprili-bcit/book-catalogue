package com.bcit.myapp.ui.main

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.bcit.myapp.data.LocalBook
import com.bcit.myapp.data.LocalBooksRepository

class LocalBooksState(private val repository: LocalBooksRepository) {
    val localBooks = repository.getAll().toMutableStateList()

    fun add(localBook: LocalBook) {
        repository.insertEntity((localBook))
    }

    fun remove(localBook: LocalBook) {
        repository.removeEntity(localBook)
    }

    fun contains(id: Int): Boolean {
        val localBookIDs = localBooks.map {
            it.id
        }
        return localBookIDs.contains(id)
    }

    fun refresh() {
        localBooks.apply {
            clear()
            addAll(repository.getAll())
        }
    }

    val onClick: (LocalBook) -> Unit = {
        if (contains(it.id)) {
            remove(it)
        } else {
            add(it)
        }
        refresh()
    }
}