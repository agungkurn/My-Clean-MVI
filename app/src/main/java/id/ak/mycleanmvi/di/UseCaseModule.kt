package id.ak.mycleanmvi.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import id.ak.mycleanmvi.domain.usecase.MovieDetailsInteractor
import id.ak.mycleanmvi.domain.usecase.MovieDetailsUseCase
import id.ak.mycleanmvi.domain.usecase.MovieListInteractor
import id.ak.mycleanmvi.domain.usecase.MovieListUseCase

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun provideListUseCase(interactor: MovieListInteractor): MovieListUseCase

    @Binds
    fun provideDetailsUseCase(interactor: MovieDetailsInteractor): MovieDetailsUseCase
}