package com.oladapo.githibsearch.di

import com.oladapo.githibsearch.domain.repository.GithubRepository
import com.oladapo.githibsearch.domain.repository.GithubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSessionRepository(
        repositoryImpl: GithubRepositoryImpl
    ): GithubRepository
}
