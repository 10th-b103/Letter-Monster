package com.lemon.backend.domain.users.user.entity;

import com.lemon.backend.domain.FavoriteSketchbook.entity.FavoriteSketchbook;
import com.lemon.backend.domain.base.BaseEntity;
import com.lemon.backend.domain.characters.entity.Characters;
import com.lemon.backend.domain.friend.entity.Friends;
import com.lemon.backend.domain.notification.entity.Notification;
import com.lemon.backend.domain.sketchbook.entity.Sketchbook;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users", indexes = {
        @Index(name = "idx_nickname_nicknameTag", columnList = "nickname, nicknameTag")
})
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE users SET is_deleted = TRUE WHERE users_id = ?")
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Integer id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "nickname_tag")
    private String nicknameTag;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider")
    private Social provider;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "is_language")
    @Builder.Default
    private Boolean isLanguage = false;

    @Builder.Default
    @Column(name = "notification_token")
    private String notificationToken = null;

    @Builder.Default
    @OneToMany(mappedBy = "users", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Characters> characterList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "users", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Sketchbook> sketchbookList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "users", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Friends> friendList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "receiver", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Notification> notificationList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FavoriteSketchbook> favoriteSketchbooks = new ArrayList<>();
}
