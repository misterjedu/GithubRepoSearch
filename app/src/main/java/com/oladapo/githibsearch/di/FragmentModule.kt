package com.oladapo.githibsearch.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FragmentModule {
    @Provides
    fun provideGlide(@ApplicationContext context: Context): RequestManager = Glide.with(context)
}
