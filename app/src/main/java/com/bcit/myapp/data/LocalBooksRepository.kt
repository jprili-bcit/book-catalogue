package com.bcit.myapp.data

class LocalBooksRepository(private val localBookDao: LocalBookDao) {
    fun insertEntity(localBook: LocalBook) {
        localBookDao.add(localBook)
    }

    fun removeEntity(localBook: LocalBook) {
        localBookDao.remove(localBook)
    }

    fun getAll(): List<LocalBook> {
        return localBookDao.getAll()
    }
}