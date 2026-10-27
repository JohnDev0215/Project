package com.amazon.mp3.platform.providers;

import android.content.Context;
import com.amazon.music.destination.AlbumDestination;
import com.amazon.music.destination.AllArtistMerchDestination;
import com.amazon.music.destination.ArtistDestination;
import com.amazon.music.destination.LiveStreamsDestination;
import com.amazon.music.destination.MerchDetailDestination;
import com.amazon.music.destination.PlaylistDestination;
import com.amazon.music.destination.PopularDestination;
import com.amazon.music.platform.data.navigation.DestinationHandler;
import com.amazon.music.platform.data.navigation.MusicDestination;
import com.amazon.music.platform.providers.NavigationProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: NavigationProviderImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\f\u0010\rR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\n\u001a\n \t*\u0004\u0018\u00010\b0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/platform/providers/NavigationProviderImpl;", "Lcom/amazon/music/platform/providers/NavigationProvider;", "", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "logger", "Lorg/slf4j/Logger;", "<init>", "(Landroid/content/Context;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class NavigationProviderImpl extends NavigationProvider {
    private final Context context;
    private final Logger logger;

    public NavigationProviderImpl(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.logger = LoggerFactory.getLogger(NavigationProviderImpl.class.getName());
        addRoute(Reflection.getOrCreateKotlinClass(AlbumDestination.class), new DestinationHandler<AlbumDestination>() { // from class: com.amazon.mp3.platform.providers.NavigationProviderImpl.1
        });
        addRoute(Reflection.getOrCreateKotlinClass(ArtistDestination.class), new DestinationHandler<ArtistDestination>() { // from class: com.amazon.mp3.platform.providers.NavigationProviderImpl.2
        });
        addRoute(Reflection.getOrCreateKotlinClass(PlaylistDestination.class), new DestinationHandler<PlaylistDestination>() { // from class: com.amazon.mp3.platform.providers.NavigationProviderImpl.3
        });
        addRoute(Reflection.getOrCreateKotlinClass(PopularDestination.class), new DestinationHandler<PopularDestination>() { // from class: com.amazon.mp3.platform.providers.NavigationProviderImpl.4
        });
        addRoute(Reflection.getOrCreateKotlinClass(AllArtistMerchDestination.class), new DestinationHandler<AllArtistMerchDestination>() { // from class: com.amazon.mp3.platform.providers.NavigationProviderImpl.5
        });
        addRoute(Reflection.getOrCreateKotlinClass(MerchDetailDestination.class), new DestinationHandler<MerchDetailDestination>() { // from class: com.amazon.mp3.platform.providers.NavigationProviderImpl.6
        });
        addRoute(Reflection.getOrCreateKotlinClass(LiveStreamsDestination.class), new DestinationHandler<LiveStreamsDestination>() { // from class: com.amazon.mp3.platform.providers.NavigationProviderImpl.7
        });
        addRoute(Reflection.getOrCreateKotlinClass(MusicDestination.NowPlayingDestination.class), new DestinationHandler<MusicDestination.NowPlayingDestination>() { // from class: com.amazon.mp3.platform.providers.NavigationProviderImpl.8
        });
        addRoute(Reflection.getOrCreateKotlinClass(MusicDestination.LibraryDestination.class), new DestinationHandler<MusicDestination.LibraryDestination>() { // from class: com.amazon.mp3.platform.providers.NavigationProviderImpl.9
        });
        addRoute(Reflection.getOrCreateKotlinClass(MusicDestination.UrlDestination.class), new DestinationHandler<MusicDestination.UrlDestination>() { // from class: com.amazon.mp3.platform.providers.NavigationProviderImpl.10
        });
    }
}
