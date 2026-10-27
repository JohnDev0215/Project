package com.amazon.music.platform.data.entity;

import com.amazon.music.platform.data.entity.CollectionType;
import java.lang.annotation.Annotation;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.internal.ObjectSerializer;

/* JADX INFO: compiled from: EntityItemType.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0005\b\t\n\u000b\f¨\u0006\r"}, d2 = {"Lcom/amazon/music/platform/data/entity/CollectionType;", "Lcom/amazon/music/platform/data/entity/EntityItemType;", "()V", "Album", "Artist", "Playlist", "Queue", "Station", "Lcom/amazon/music/platform/data/entity/CollectionType$Album;", "Lcom/amazon/music/platform/data/entity/CollectionType$Artist;", "Lcom/amazon/music/platform/data/entity/CollectionType$Playlist;", "Lcom/amazon/music/platform/data/entity/CollectionType$Queue;", "Lcom/amazon/music/platform/data/entity/CollectionType$Station;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public abstract class CollectionType implements EntityItemType {
    public /* synthetic */ CollectionType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private CollectionType() {
    }

    /* JADX INFO: compiled from: EntityItemType.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/platform/data/entity/CollectionType$Album;", "Lcom/amazon/music/platform/data/entity/CollectionType;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    @Serializable
    public static final class Album extends CollectionType {
        public static final Album INSTANCE = new Album();
        private static final /* synthetic */ Lazy<KSerializer<Object>> $cachedSerializer$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<KSerializer<Object>>() { // from class: com.amazon.music.platform.data.entity.CollectionType$Album$$cachedSerializer$delegate$1
            @Override // kotlin.jvm.functions.Function0
            public final KSerializer<Object> invoke() {
                return new ObjectSerializer("Album", CollectionType.Album.INSTANCE, new Annotation[0]);
            }
        });

        private Album() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: EntityItemType.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/platform/data/entity/CollectionType$Playlist;", "Lcom/amazon/music/platform/data/entity/CollectionType;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    @Serializable
    public static final class Playlist extends CollectionType {
        public static final Playlist INSTANCE = new Playlist();
        private static final /* synthetic */ Lazy<KSerializer<Object>> $cachedSerializer$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<KSerializer<Object>>() { // from class: com.amazon.music.platform.data.entity.CollectionType$Playlist$$cachedSerializer$delegate$1
            @Override // kotlin.jvm.functions.Function0
            public final KSerializer<Object> invoke() {
                return new ObjectSerializer("Playlist", CollectionType.Playlist.INSTANCE, new Annotation[0]);
            }
        });

        private Playlist() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: EntityItemType.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/platform/data/entity/CollectionType$Station;", "Lcom/amazon/music/platform/data/entity/CollectionType;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    @Serializable
    public static final class Station extends CollectionType {
        public static final Station INSTANCE = new Station();
        private static final /* synthetic */ Lazy<KSerializer<Object>> $cachedSerializer$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<KSerializer<Object>>() { // from class: com.amazon.music.platform.data.entity.CollectionType$Station$$cachedSerializer$delegate$1
            @Override // kotlin.jvm.functions.Function0
            public final KSerializer<Object> invoke() {
                return new ObjectSerializer("Station", CollectionType.Station.INSTANCE, new Annotation[0]);
            }
        });

        private Station() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: EntityItemType.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/platform/data/entity/CollectionType$Artist;", "Lcom/amazon/music/platform/data/entity/CollectionType;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    @Serializable
    public static final class Artist extends CollectionType {
        public static final Artist INSTANCE = new Artist();
        private static final /* synthetic */ Lazy<KSerializer<Object>> $cachedSerializer$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<KSerializer<Object>>() { // from class: com.amazon.music.platform.data.entity.CollectionType$Artist$$cachedSerializer$delegate$1
            @Override // kotlin.jvm.functions.Function0
            public final KSerializer<Object> invoke() {
                return new ObjectSerializer("Artist", CollectionType.Artist.INSTANCE, new Annotation[0]);
            }
        });

        private Artist() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: EntityItemType.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/platform/data/entity/CollectionType$Queue;", "Lcom/amazon/music/platform/data/entity/CollectionType;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    @Serializable
    public static final class Queue extends CollectionType {
        public static final Queue INSTANCE = new Queue();
        private static final /* synthetic */ Lazy<KSerializer<Object>> $cachedSerializer$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<KSerializer<Object>>() { // from class: com.amazon.music.platform.data.entity.CollectionType$Queue$$cachedSerializer$delegate$1
            @Override // kotlin.jvm.functions.Function0
            public final KSerializer<Object> invoke() {
                return new ObjectSerializer("Queue", CollectionType.Queue.INSTANCE, new Annotation[0]);
            }
        });

        private Queue() {
            super(null);
        }
    }
}
