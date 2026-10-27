package com.amazon.mp3.curate.dagger;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.mp3.curate.provider.CurateDownloadProvider;
import com.amazon.mp3.curate.provider.CurateEndpointSettingsProvider;
import com.amazon.mp3.curate.provider.CurateFeatureFlagProvider;
import com.amazon.mp3.curate.provider.CurateLibraryNavigationProvider;
import com.amazon.mp3.curate.provider.CurateNavigationProvider;
import com.amazon.mp3.curate.provider.CurateNetworkProvider;
import com.amazon.mp3.curate.provider.CurateOverflowMenuProvider;
import com.amazon.mp3.curate.provider.CuratePlaybackProvider;
import com.amazon.mp3.curate.provider.CuratePlaylistProvider;
import com.amazon.mp3.curate.provider.CurateShareProvider;
import com.amazon.mp3.curate.provider.CurateStationProvider;
import com.amazon.mp3.curate.provider.CurateStyleSheetProvider;
import com.amazon.mp3.curate.provider.CurateSubscriptionProvider;
import com.amazon.mp3.net.RequestInterceptorFactory;
import com.amazon.music.curate.provider.DownloadProvider;
import com.amazon.music.curate.provider.EndpointSettingsProvider;
import com.amazon.music.curate.provider.FeatureFlagProvider;
import com.amazon.music.curate.provider.LibraryNavigationProvider;
import com.amazon.music.curate.provider.NavigationProvider;
import com.amazon.music.curate.provider.NetworkProvider;
import com.amazon.music.curate.provider.OverflowMenuProvider;
import com.amazon.music.curate.provider.PlaybackProvider;
import com.amazon.music.curate.provider.PlaylistProvider;
import com.amazon.music.curate.provider.ShareProvider;
import com.amazon.music.curate.provider.StationProvider;
import com.amazon.music.curate.provider.StyleSheetProvider;
import com.amazon.music.curate.provider.SubscriptionProvider;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CurateModule.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\b\u0010\u000f\u001a\u00020\u0010H\u0007J\b\u0010\u0011\u001a\u00020\u0012H\u0007J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\u0015\u001a\u00020\u0016H\u0007J\b\u0010\u0017\u001a\u00020\u0018H\u0007J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\u001f\u001a\u00020 H\u0007J\b\u0010!\u001a\u00020\"H\u0007¨\u0006#"}, d2 = {"Lcom/amazon/mp3/curate/dagger/CurateModule;", "", "()V", "provideDownloadProvider", "Lcom/amazon/music/curate/provider/DownloadProvider;", "provideEndpointSettingsProvider", "Lcom/amazon/music/curate/provider/EndpointSettingsProvider;", "context", "Landroid/content/Context;", "provideFeatureFlagProvider", "Lcom/amazon/music/curate/provider/FeatureFlagProvider;", "provideLibraryNavigationProvider", "Lcom/amazon/music/curate/provider/LibraryNavigationProvider;", "fragmentActivity", "Landroidx/fragment/app/FragmentActivity;", "provideNavigationProvider", "Lcom/amazon/music/curate/provider/NavigationProvider;", "provideNetworkProvider", "Lcom/amazon/music/curate/provider/NetworkProvider;", "provideOverflowMenuProvider", "Lcom/amazon/music/curate/provider/OverflowMenuProvider;", "providePlaybackProvider", "Lcom/amazon/music/curate/provider/PlaybackProvider;", "providePlaylistProvider", "Lcom/amazon/music/curate/provider/PlaylistProvider;", "provideRequestInterceptor", "Lcom/amazon/hermes/RequestInterceptor;", "provideShareProvider", "Lcom/amazon/music/curate/provider/ShareProvider;", "provideStationProvider", "Lcom/amazon/music/curate/provider/StationProvider;", "provideStyleSheetProvider", "Lcom/amazon/music/curate/provider/StyleSheetProvider;", "provideSubscriptionProvider", "Lcom/amazon/music/curate/provider/SubscriptionProvider;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CurateModule {
    public final DownloadProvider provideDownloadProvider() {
        return new CurateDownloadProvider();
    }

    public final RequestInterceptor provideRequestInterceptor(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        RequestInterceptor requestInterceptorProvideRequestInterceptor = new RequestInterceptorFactory(context).provideRequestInterceptor();
        Intrinsics.checkNotNullExpressionValue(requestInterceptorProvideRequestInterceptor, "RequestInterceptorFactor…ovideRequestInterceptor()");
        return requestInterceptorProvideRequestInterceptor;
    }

    public final PlaybackProvider providePlaybackProvider() {
        return new CuratePlaybackProvider();
    }

    public final OverflowMenuProvider provideOverflowMenuProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new CurateOverflowMenuProvider(context);
    }

    public final FeatureFlagProvider provideFeatureFlagProvider() {
        return new CurateFeatureFlagProvider();
    }

    public final NetworkProvider provideNetworkProvider() {
        return new CurateNetworkProvider();
    }

    public final PlaylistProvider providePlaylistProvider() {
        return new CuratePlaylistProvider();
    }

    public final SubscriptionProvider provideSubscriptionProvider() {
        return new CurateSubscriptionProvider();
    }

    public final ShareProvider provideShareProvider() {
        return new CurateShareProvider();
    }

    public final NavigationProvider provideNavigationProvider() {
        return new CurateNavigationProvider();
    }

    public final StationProvider provideStationProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new CurateStationProvider();
    }

    public final EndpointSettingsProvider provideEndpointSettingsProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new CurateEndpointSettingsProvider();
    }

    public final LibraryNavigationProvider provideLibraryNavigationProvider(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        return new CurateLibraryNavigationProvider(new WeakReference(fragmentActivity));
    }

    public final StyleSheetProvider provideStyleSheetProvider() {
        return new CurateStyleSheetProvider();
    }
}
