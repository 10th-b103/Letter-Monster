package com.lemon.backend.domain.friend.repository.custom;

import com.lemon.backend.domain.friend.repository.FriendsRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FriendsRepositoryImpl implements FriendsRepositoryCustom {

    private final JPAQueryFactory query;


}
