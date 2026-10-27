package com.amazon.music.platform.events;

import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.platform.data.entity.CollectionType;
import com.amazon.music.platform.data.entity.EntityId;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.entity.EntityItemType;
import com.amazon.music.platform.data.entity.SingleType;
import com.amazon.music.platform.data.identifiers.Asin;
import com.amazon.music.platform.data.identifiers.LocalId;
import com.amazon.music.platform.data.identifiers.ObjectId;
import com.amazon.music.platform.data.identifiers.PlaylistId;
import com.amazon.music.platform.data.identifiers.QueueId;
import com.amazon.music.platform.data.identifiers.StationId;
import com.amazon.music.platform.data.identifiers.StationKey;
import com.amazon.music.platform.data.identifiers.StationSeedId;
import com.amazon.music.platform.data.identifiers.Url;
import com.amazon.music.platform.data.identifiers.UserPlaylistId;
import com.amazon.music.platform.playback.state.PlaybackState;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import kotlinx.serialization.modules.PolymorphicModuleBuilder;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleBuilder;

/* JADX INFO: compiled from: PlaybackStateEvent.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\b\u0087\b\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB!\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0019\u0010\u001aJ\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R\u0014\u0010\u0018\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001c"}, d2 = {"Lcom/amazon/music/platform/events/PlaybackStateEvent;", "", "", "toString", "getJson", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/playback/state/PlaybackState;", TetheredMessageKey.playbackState, "Lcom/amazon/music/platform/playback/state/PlaybackState;", "getPlaybackState", "()Lcom/amazon/music/platform/playback/state/PlaybackState;", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "entityItemIdentifier", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "getEntityItemIdentifier", "()Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "parentEntityItemId", "getParentEntityItemId", "getName", "()Ljava/lang/String;", "name", "<init>", "(Lcom/amazon/music/platform/playback/state/PlaybackState;Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;)V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
@Serializable
public final /* data */ class PlaybackStateEvent {
    private static final String eventName;
    private static final Json format;
    private static final SerializersModule module;
    private final EntityItemIdentifier entityItemIdentifier;

    /* JADX INFO: renamed from: parentEntityItemId, reason: from kotlin metadata and from toString */
    private final EntityItemIdentifier parentCollectionId;
    private final PlaybackState playbackState;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlaybackStateEvent)) {
            return false;
        }
        PlaybackStateEvent playbackStateEvent = (PlaybackStateEvent) other;
        return this.playbackState == playbackStateEvent.playbackState && Intrinsics.areEqual(this.entityItemIdentifier, playbackStateEvent.entityItemIdentifier) && Intrinsics.areEqual(this.parentCollectionId, playbackStateEvent.parentCollectionId);
    }

    public String getName() {
        return "PlaybackStateEvent";
    }

    public int hashCode() {
        int iHashCode = ((this.playbackState.hashCode() * 31) + this.entityItemIdentifier.hashCode()) * 31;
        EntityItemIdentifier entityItemIdentifier = this.parentCollectionId;
        return iHashCode + (entityItemIdentifier == null ? 0 : entityItemIdentifier.hashCode());
    }

    static {
        SerializersModuleBuilder serializersModuleBuilder = new SerializersModuleBuilder();
        PolymorphicModuleBuilder polymorphicModuleBuilder = new PolymorphicModuleBuilder(Reflection.getOrCreateKotlinClass(EntityId.class), null);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Asin.class);
        KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(Reflection.typeOf(Asin.class));
        Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        polymorphicModuleBuilder.subclass(orCreateKotlinClass, kSerializerSerializer);
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Url.class);
        KSerializer<Object> kSerializerSerializer2 = SerializersKt.serializer(Reflection.typeOf(Url.class));
        Intrinsics.checkNotNull(kSerializerSerializer2, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        polymorphicModuleBuilder.subclass(orCreateKotlinClass2, kSerializerSerializer2);
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(LocalId.class);
        KSerializer<Object> kSerializerSerializer3 = SerializersKt.serializer(Reflection.typeOf(LocalId.class));
        Intrinsics.checkNotNull(kSerializerSerializer3, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        polymorphicModuleBuilder.subclass(orCreateKotlinClass3, kSerializerSerializer3);
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(PlaylistId.class);
        KSerializer<Object> kSerializerSerializer4 = SerializersKt.serializer(Reflection.typeOf(PlaylistId.class));
        Intrinsics.checkNotNull(kSerializerSerializer4, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        polymorphicModuleBuilder.subclass(orCreateKotlinClass4, kSerializerSerializer4);
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(ObjectId.class);
        KSerializer<Object> kSerializerSerializer5 = SerializersKt.serializer(Reflection.typeOf(ObjectId.class));
        Intrinsics.checkNotNull(kSerializerSerializer5, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        polymorphicModuleBuilder.subclass(orCreateKotlinClass5, kSerializerSerializer5);
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(QueueId.class);
        KSerializer<Object> kSerializerSerializer6 = SerializersKt.serializer(Reflection.typeOf(QueueId.class));
        Intrinsics.checkNotNull(kSerializerSerializer6, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        polymorphicModuleBuilder.subclass(orCreateKotlinClass6, kSerializerSerializer6);
        KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(UserPlaylistId.class);
        KSerializer<Object> kSerializerSerializer7 = SerializersKt.serializer(Reflection.typeOf(UserPlaylistId.class));
        Intrinsics.checkNotNull(kSerializerSerializer7, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        polymorphicModuleBuilder.subclass(orCreateKotlinClass7, kSerializerSerializer7);
        polymorphicModuleBuilder.buildTo(serializersModuleBuilder);
        PolymorphicModuleBuilder polymorphicModuleBuilder2 = new PolymorphicModuleBuilder(Reflection.getOrCreateKotlinClass(StationId.class), null);
        KClass orCreateKotlinClass8 = Reflection.getOrCreateKotlinClass(StationKey.class);
        KSerializer<Object> kSerializerSerializer8 = SerializersKt.serializer(Reflection.typeOf(StationKey.class));
        Intrinsics.checkNotNull(kSerializerSerializer8, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        polymorphicModuleBuilder2.subclass(orCreateKotlinClass8, kSerializerSerializer8);
        KClass orCreateKotlinClass9 = Reflection.getOrCreateKotlinClass(StationSeedId.class);
        KSerializer<Object> kSerializerSerializer9 = SerializersKt.serializer(Reflection.typeOf(StationSeedId.class));
        Intrinsics.checkNotNull(kSerializerSerializer9, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        polymorphicModuleBuilder2.subclass(orCreateKotlinClass9, kSerializerSerializer9);
        polymorphicModuleBuilder2.buildTo(serializersModuleBuilder);
        PolymorphicModuleBuilder polymorphicModuleBuilder3 = new PolymorphicModuleBuilder(Reflection.getOrCreateKotlinClass(EntityItemType.class), null);
        KClass orCreateKotlinClass10 = Reflection.getOrCreateKotlinClass(SingleType.Track.class);
        KSerializer<Object> kSerializerSerializer10 = SerializersKt.serializer(Reflection.typeOf(SingleType.Track.class));
        Intrinsics.checkNotNull(kSerializerSerializer10, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        polymorphicModuleBuilder3.subclass(orCreateKotlinClass10, kSerializerSerializer10);
        KClass orCreateKotlinClass11 = Reflection.getOrCreateKotlinClass(SingleType.Podcast.class);
        KSerializer<Object> kSerializerSerializer11 = SerializersKt.serializer(Reflection.typeOf(SingleType.Podcast.class));
        Intrinsics.checkNotNull(kSerializerSerializer11, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        polymorphicModuleBuilder3.subclass(orCreateKotlinClass11, kSerializerSerializer11);
        KClass orCreateKotlinClass12 = Reflection.getOrCreateKotlinClass(SingleType.Ad.class);
        KSerializer<Object> kSerializerSerializer12 = SerializersKt.serializer(Reflection.typeOf(SingleType.Ad.class));
        Intrinsics.checkNotNull(kSerializerSerializer12, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        polymorphicModuleBuilder3.subclass(orCreateKotlinClass12, kSerializerSerializer12);
        KClass orCreateKotlinClass13 = Reflection.getOrCreateKotlinClass(SingleType.Interlude.class);
        KSerializer<Object> kSerializerSerializer13 = SerializersKt.serializer(Reflection.typeOf(SingleType.Interlude.class));
        Intrinsics.checkNotNull(kSerializerSerializer13, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        polymorphicModuleBuilder3.subclass(orCreateKotlinClass13, kSerializerSerializer13);
        KClass orCreateKotlinClass14 = Reflection.getOrCreateKotlinClass(CollectionType.Station.class);
        KSerializer<Object> kSerializerSerializer14 = SerializersKt.serializer(Reflection.typeOf(CollectionType.Station.class));
        Intrinsics.checkNotNull(kSerializerSerializer14, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        polymorphicModuleBuilder3.subclass(orCreateKotlinClass14, kSerializerSerializer14);
        KClass orCreateKotlinClass15 = Reflection.getOrCreateKotlinClass(CollectionType.Album.class);
        KSerializer<Object> kSerializerSerializer15 = SerializersKt.serializer(Reflection.typeOf(CollectionType.Album.class));
        Intrinsics.checkNotNull(kSerializerSerializer15, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        polymorphicModuleBuilder3.subclass(orCreateKotlinClass15, kSerializerSerializer15);
        KClass orCreateKotlinClass16 = Reflection.getOrCreateKotlinClass(CollectionType.Artist.class);
        KSerializer<Object> kSerializerSerializer16 = SerializersKt.serializer(Reflection.typeOf(CollectionType.Artist.class));
        Intrinsics.checkNotNull(kSerializerSerializer16, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        polymorphicModuleBuilder3.subclass(orCreateKotlinClass16, kSerializerSerializer16);
        KClass orCreateKotlinClass17 = Reflection.getOrCreateKotlinClass(CollectionType.Playlist.class);
        KSerializer<Object> kSerializerSerializer17 = SerializersKt.serializer(Reflection.typeOf(CollectionType.Playlist.class));
        Intrinsics.checkNotNull(kSerializerSerializer17, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        polymorphicModuleBuilder3.subclass(orCreateKotlinClass17, kSerializerSerializer17);
        KClass orCreateKotlinClass18 = Reflection.getOrCreateKotlinClass(CollectionType.Queue.class);
        KSerializer<Object> kSerializerSerializer18 = SerializersKt.serializer(Reflection.typeOf(CollectionType.Queue.class));
        Intrinsics.checkNotNull(kSerializerSerializer18, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        polymorphicModuleBuilder3.subclass(orCreateKotlinClass18, kSerializerSerializer18);
        polymorphicModuleBuilder3.buildTo(serializersModuleBuilder);
        module = serializersModuleBuilder.build();
        format = JsonKt.Json$default(null, new Function1<JsonBuilder, Unit>() { // from class: com.amazon.music.platform.events.PlaybackStateEvent$Companion$format$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(JsonBuilder jsonBuilder) {
                invoke2(jsonBuilder);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(JsonBuilder Json) {
                Intrinsics.checkNotNullParameter(Json, "$this$Json");
                Json.setSerializersModule(PlaybackStateEvent.module);
            }
        }, 1, null);
        eventName = "PlaybackStateEvent";
    }

    public PlaybackStateEvent(PlaybackState playbackState, EntityItemIdentifier entityItemIdentifier, EntityItemIdentifier entityItemIdentifier2) {
        Intrinsics.checkNotNullParameter(playbackState, "playbackState");
        Intrinsics.checkNotNullParameter(entityItemIdentifier, "entityItemIdentifier");
        this.playbackState = playbackState;
        this.entityItemIdentifier = entityItemIdentifier;
        this.parentCollectionId = entityItemIdentifier2;
        PlatformProviderUtilKt.freeze(this);
    }

    public String toString() {
        return "PlaybackStateEvent(playbackState='" + this.playbackState + "', entityItemIdentifier='" + this.entityItemIdentifier + "', parentCollectionId='" + this.parentCollectionId + "')";
    }

    public String getJson() {
        Json json = format;
        KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(PlaybackStateEvent.class));
        Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        return json.encodeToString(kSerializerSerializer, this);
    }
}
