package id.ak.mycleanmvi.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.ak.mycleanmvi.BuildConfig
import id.ak.mycleanmvi.data.remote.MovieApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    fun provideGson(): GsonConverterFactory = GsonConverterFactory.create(
        GsonBuilder().serializeNulls().create()
    )

    @Provides
    fun provideMockClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory, client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .client(client)
        .build()

    @Provides
    fun provideMovieListApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)
}