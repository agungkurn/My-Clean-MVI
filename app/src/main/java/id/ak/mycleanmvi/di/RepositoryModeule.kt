package id.ak.mycleanmvi.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.ak.mycleanmvi.data.repository.MovieRepository
import id.ak.mycleanmvi.domain.repository.IMovieRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModeule {
    @Binds
    fun provideRepository(repository: MovieRepository): IMovieRepository
}