package com.example.game.di

import androidx.room.Room
import androidx.room.migration.Migration
import com.example.game.database.DB
import com.example.game.repositore.Repository
import com.example.game.usecase.DrawUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import androidx.sqlite.db.SupportSQLiteDatabase



val roomModule = module {
    single {
        Room.databaseBuilder(androidApplication(), DB::class.java, "my-db").addMigrations(MIGRATION_2_3).build()
    }
    single {
        get<DB>().drawDao()
    }
    factory {
        Repository(get())
    }
    factory {
        DrawUseCase(get())
    }
}



val MIGRATION_2_3 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Draw ADD COLUMN color INTEGER NOT NULL")
    }
}