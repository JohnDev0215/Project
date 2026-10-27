package com.amazon.music.platform.data.navigation;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: MusicDestination.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/amazon/music/platform/data/navigation/MusicDestination;", "", "<init>", "()V", "LibraryDestination", "NowPlayingDestination", "UrlDestination", "Lcom/amazon/music/platform/data/navigation/MusicDestination$LibraryDestination;", "Lcom/amazon/music/platform/data/navigation/MusicDestination$NowPlayingDestination;", "Lcom/amazon/music/platform/data/navigation/MusicDestination$UrlDestination;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public abstract class MusicDestination {

    /* JADX INFO: compiled from: MusicDestination.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/amazon/music/platform/data/navigation/MusicDestination$LibraryDestination;", "Lcom/amazon/music/platform/data/navigation/MusicDestination;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final class LibraryDestination extends MusicDestination {
    }

    /* JADX INFO: compiled from: MusicDestination.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/amazon/music/platform/data/navigation/MusicDestination$UrlDestination;", "Lcom/amazon/music/platform/data/navigation/MusicDestination;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final class UrlDestination extends MusicDestination {
    }

    public /* synthetic */ MusicDestination(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private MusicDestination() {
    }

    /* JADX INFO: compiled from: MusicDestination.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/data/navigation/MusicDestination$NowPlayingDestination;", "Lcom/amazon/music/platform/data/navigation/MusicDestination;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class NowPlayingDestination extends MusicDestination {
        public static final NowPlayingDestination INSTANCE = new NowPlayingDestination();

        private NowPlayingDestination() {
            super(null);
        }
    }
}
