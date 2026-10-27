package com.amazon.music.platform.data.entity;

import com.amazon.music.platform.data.entity.SingleType;
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
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/platform/data/entity/SingleType;", "Lcom/amazon/music/platform/data/entity/EntityItemType;", "()V", "Ad", "Interlude", "Podcast", "Track", "Lcom/amazon/music/platform/data/entity/SingleType$Ad;", "Lcom/amazon/music/platform/data/entity/SingleType$Interlude;", "Lcom/amazon/music/platform/data/entity/SingleType$Podcast;", "Lcom/amazon/music/platform/data/entity/SingleType$Track;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public abstract class SingleType implements EntityItemType {
    public /* synthetic */ SingleType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private SingleType() {
    }

    /* JADX INFO: compiled from: EntityItemType.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/platform/data/entity/SingleType$Track;", "Lcom/amazon/music/platform/data/entity/SingleType;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    @Serializable
    public static final class Track extends SingleType {
        public static final Track INSTANCE = new Track();
        private static final /* synthetic */ Lazy<KSerializer<Object>> $cachedSerializer$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<KSerializer<Object>>() { // from class: com.amazon.music.platform.data.entity.SingleType$Track$$cachedSerializer$delegate$1
            @Override // kotlin.jvm.functions.Function0
            public final KSerializer<Object> invoke() {
                return new ObjectSerializer("Track", SingleType.Track.INSTANCE, new Annotation[0]);
            }
        });

        private Track() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: EntityItemType.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/platform/data/entity/SingleType$Ad;", "Lcom/amazon/music/platform/data/entity/SingleType;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    @Serializable
    public static final class Ad extends SingleType {
        public static final Ad INSTANCE = new Ad();
        private static final /* synthetic */ Lazy<KSerializer<Object>> $cachedSerializer$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<KSerializer<Object>>() { // from class: com.amazon.music.platform.data.entity.SingleType$Ad$$cachedSerializer$delegate$1
            @Override // kotlin.jvm.functions.Function0
            public final KSerializer<Object> invoke() {
                return new ObjectSerializer("Ad", SingleType.Ad.INSTANCE, new Annotation[0]);
            }
        });

        private Ad() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: EntityItemType.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/platform/data/entity/SingleType$Interlude;", "Lcom/amazon/music/platform/data/entity/SingleType;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    @Serializable
    public static final class Interlude extends SingleType {
        public static final Interlude INSTANCE = new Interlude();
        private static final /* synthetic */ Lazy<KSerializer<Object>> $cachedSerializer$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<KSerializer<Object>>() { // from class: com.amazon.music.platform.data.entity.SingleType$Interlude$$cachedSerializer$delegate$1
            @Override // kotlin.jvm.functions.Function0
            public final KSerializer<Object> invoke() {
                return new ObjectSerializer("Interlude", SingleType.Interlude.INSTANCE, new Annotation[0]);
            }
        });

        private Interlude() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: EntityItemType.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/platform/data/entity/SingleType$Podcast;", "Lcom/amazon/music/platform/data/entity/SingleType;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    @Serializable
    public static final class Podcast extends SingleType {
        public static final Podcast INSTANCE = new Podcast();
        private static final /* synthetic */ Lazy<KSerializer<Object>> $cachedSerializer$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<KSerializer<Object>>() { // from class: com.amazon.music.platform.data.entity.SingleType$Podcast$$cachedSerializer$delegate$1
            @Override // kotlin.jvm.functions.Function0
            public final KSerializer<Object> invoke() {
                return new ObjectSerializer("Podcast", SingleType.Podcast.INSTANCE, new Annotation[0]);
            }
        });

        private Podcast() {
            super(null);
        }
    }
}
