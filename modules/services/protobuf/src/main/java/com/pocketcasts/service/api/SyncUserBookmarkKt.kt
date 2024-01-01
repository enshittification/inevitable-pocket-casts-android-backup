// Generated by the protocol buffer compiler. DO NOT EDIT!
// source: api.proto

// Generated files should ignore deprecation warnings
@file:Suppress("DEPRECATION")
package com.pocketcasts.service.api

@kotlin.jvm.JvmName("-initializesyncUserBookmark")
public inline fun syncUserBookmark(block: com.pocketcasts.service.api.SyncUserBookmarkKt.Dsl.() -> kotlin.Unit): com.pocketcasts.service.api.SyncUserBookmark =
    com.pocketcasts.service.api.SyncUserBookmarkKt.Dsl._create(com.pocketcasts.service.api.SyncUserBookmark.newBuilder()).apply { block() }._build()
/**
 * Protobuf type `api.SyncUserBookmark`
 */
public object SyncUserBookmarkKt {
    @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
    @com.google.protobuf.kotlin.ProtoDslMarker
    public class Dsl private constructor(
        private val _builder: com.pocketcasts.service.api.SyncUserBookmark.Builder
    ) {
        public companion object {
            @kotlin.jvm.JvmSynthetic
            @kotlin.PublishedApi
            internal fun _create(builder: com.pocketcasts.service.api.SyncUserBookmark.Builder): Dsl = Dsl(builder)
        }

        @kotlin.jvm.JvmSynthetic
        @kotlin.PublishedApi
        internal fun _build(): com.pocketcasts.service.api.SyncUserBookmark = _builder.build()

        /**
         * `string bookmark_uuid = 1;`
         */
        public var bookmarkUuid: kotlin.String
            @JvmName("getBookmarkUuid")
            get() = _builder.getBookmarkUuid()
            @JvmName("setBookmarkUuid")
            set(value) {
                _builder.setBookmarkUuid(value)
            }
        /**
         * `string bookmark_uuid = 1;`
         */
        public fun clearBookmarkUuid() {
            _builder.clearBookmarkUuid()
        }

        /**
         * `string podcast_uuid = 2;`
         */
        public var podcastUuid: kotlin.String
            @JvmName("getPodcastUuid")
            get() = _builder.getPodcastUuid()
            @JvmName("setPodcastUuid")
            set(value) {
                _builder.setPodcastUuid(value)
            }
        /**
         * `string podcast_uuid = 2;`
         */
        public fun clearPodcastUuid() {
            _builder.clearPodcastUuid()
        }

        /**
         * `string episode_uuid = 3;`
         */
        public var episodeUuid: kotlin.String
            @JvmName("getEpisodeUuid")
            get() = _builder.getEpisodeUuid()
            @JvmName("setEpisodeUuid")
            set(value) {
                _builder.setEpisodeUuid(value)
            }
        /**
         * `string episode_uuid = 3;`
         */
        public fun clearEpisodeUuid() {
            _builder.clearEpisodeUuid()
        }

        /**
         * `.google.protobuf.Timestamp created_at = 4;`
         */
        public var createdAt: com.google.protobuf.Timestamp
            @JvmName("getCreatedAt")
            get() = _builder.getCreatedAt()
            @JvmName("setCreatedAt")
            set(value) {
                _builder.setCreatedAt(value)
            }
        /**
         * `.google.protobuf.Timestamp created_at = 4;`
         */
        public fun clearCreatedAt() {
            _builder.clearCreatedAt()
        }
        /**
         * `.google.protobuf.Timestamp created_at = 4;`
         * @return Whether the createdAt field is set.
         */
        public fun hasCreatedAt(): kotlin.Boolean {
            return _builder.hasCreatedAt()
        }

        /**
         * `.google.protobuf.Int32Value time = 5;`
         */
        public var time: com.google.protobuf.Int32Value
            @JvmName("getTime")
            get() = _builder.getTime()
            @JvmName("setTime")
            set(value) {
                _builder.setTime(value)
            }
        /**
         * `.google.protobuf.Int32Value time = 5;`
         */
        public fun clearTime() {
            _builder.clearTime()
        }
        /**
         * `.google.protobuf.Int32Value time = 5;`
         * @return Whether the time field is set.
         */
        public fun hasTime(): kotlin.Boolean {
            return _builder.hasTime()
        }

        /**
         * `.google.protobuf.StringValue title = 6;`
         */
        public var title: com.google.protobuf.StringValue
            @JvmName("getTitle")
            get() = _builder.getTitle()
            @JvmName("setTitle")
            set(value) {
                _builder.setTitle(value)
            }
        /**
         * `.google.protobuf.StringValue title = 6;`
         */
        public fun clearTitle() {
            _builder.clearTitle()
        }
        /**
         * `.google.protobuf.StringValue title = 6;`
         * @return Whether the title field is set.
         */
        public fun hasTitle(): kotlin.Boolean {
            return _builder.hasTitle()
        }

        /**
         * `.google.protobuf.Int64Value title_modified = 7;`
         */
        public var titleModified: com.google.protobuf.Int64Value
            @JvmName("getTitleModified")
            get() = _builder.getTitleModified()
            @JvmName("setTitleModified")
            set(value) {
                _builder.setTitleModified(value)
            }
        /**
         * `.google.protobuf.Int64Value title_modified = 7;`
         */
        public fun clearTitleModified() {
            _builder.clearTitleModified()
        }
        /**
         * `.google.protobuf.Int64Value title_modified = 7;`
         * @return Whether the titleModified field is set.
         */
        public fun hasTitleModified(): kotlin.Boolean {
            return _builder.hasTitleModified()
        }

        /**
         * `.google.protobuf.BoolValue is_deleted = 8;`
         */
        public var isDeleted: com.google.protobuf.BoolValue
            @JvmName("getIsDeleted")
            get() = _builder.getIsDeleted()
            @JvmName("setIsDeleted")
            set(value) {
                _builder.setIsDeleted(value)
            }
        /**
         * `.google.protobuf.BoolValue is_deleted = 8;`
         */
        public fun clearIsDeleted() {
            _builder.clearIsDeleted()
        }
        /**
         * `.google.protobuf.BoolValue is_deleted = 8;`
         * @return Whether the isDeleted field is set.
         */
        public fun hasIsDeleted(): kotlin.Boolean {
            return _builder.hasIsDeleted()
        }

        /**
         * `.google.protobuf.Int64Value is_deleted_modified = 9;`
         */
        public var isDeletedModified: com.google.protobuf.Int64Value
            @JvmName("getIsDeletedModified")
            get() = _builder.getIsDeletedModified()
            @JvmName("setIsDeletedModified")
            set(value) {
                _builder.setIsDeletedModified(value)
            }
        /**
         * `.google.protobuf.Int64Value is_deleted_modified = 9;`
         */
        public fun clearIsDeletedModified() {
            _builder.clearIsDeletedModified()
        }
        /**
         * `.google.protobuf.Int64Value is_deleted_modified = 9;`
         * @return Whether the isDeletedModified field is set.
         */
        public fun hasIsDeletedModified(): kotlin.Boolean {
            return _builder.hasIsDeletedModified()
        }
    }
}
public inline fun com.pocketcasts.service.api.SyncUserBookmark.copy(block: `com.pocketcasts.service.api`.SyncUserBookmarkKt.Dsl.() -> kotlin.Unit): com.pocketcasts.service.api.SyncUserBookmark =
    `com.pocketcasts.service.api`.SyncUserBookmarkKt.Dsl._create(this.toBuilder()).apply { block() }._build()

public val com.pocketcasts.service.api.SyncUserBookmarkOrBuilder.createdAtOrNull: com.google.protobuf.Timestamp?
    get() = if (hasCreatedAt()) getCreatedAt() else null

public val com.pocketcasts.service.api.SyncUserBookmarkOrBuilder.timeOrNull: com.google.protobuf.Int32Value?
    get() = if (hasTime()) getTime() else null

public val com.pocketcasts.service.api.SyncUserBookmarkOrBuilder.titleOrNull: com.google.protobuf.StringValue?
    get() = if (hasTitle()) getTitle() else null

public val com.pocketcasts.service.api.SyncUserBookmarkOrBuilder.titleModifiedOrNull: com.google.protobuf.Int64Value?
    get() = if (hasTitleModified()) getTitleModified() else null

public val com.pocketcasts.service.api.SyncUserBookmarkOrBuilder.isDeletedOrNull: com.google.protobuf.BoolValue?
    get() = if (hasIsDeleted()) getIsDeleted() else null

public val com.pocketcasts.service.api.SyncUserBookmarkOrBuilder.isDeletedModifiedOrNull: com.google.protobuf.Int64Value?
    get() = if (hasIsDeletedModified()) getIsDeletedModified() else null
