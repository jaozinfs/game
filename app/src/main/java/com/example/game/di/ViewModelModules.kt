package com.example.game.di

import com.example.game.view.TrainingViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val viewModuleModules = module {
    viewModel { TrainingViewModel( get() ) }
}