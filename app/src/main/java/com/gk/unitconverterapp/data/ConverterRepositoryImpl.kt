package com.gk.unitconverterapp.data

import kotlinx.coroutines.flow.Flow

class ConverterRepositoryImpl(
    private val dao: ConverterDAO
):ConverterRepository {

    override suspend fun insertResult(result: ConversionResult) {
        dao.insertResult(result = result)
    }

    override suspend fun deleteResult(result: ConversionResult) {
        dao.deleteResult(result = result)
    }

    override suspend fun deleteAllResults() {
        dao.deleteAll()
    }

    override fun getSavedResults(): Flow<List<ConversionResult>> {
        return dao.getResults()
    }
}