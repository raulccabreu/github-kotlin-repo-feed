package com.example.githubktrepofeed.domain.models

data class Repository(val id: String, val name: String, val stars: Int, val forks: Int,
                      val authorName: String, val authorAvatarUrl: String)