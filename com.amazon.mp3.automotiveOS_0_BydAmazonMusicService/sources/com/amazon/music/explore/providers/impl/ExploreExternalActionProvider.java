package com.amazon.music.explore.providers.impl;

import android.content.Context;
import android.view.View;
import com.amazon.music.explore.fragment.PlaybackFragment;
import com.amazon.music.explore.picasso.BlurTransformation;
import com.amazon.music.skyfire.ui.providers.DownloadProvider;
import com.amazon.music.skyfire.ui.providers.LibraryProvider;
import com.amazon.music.skyfire.ui.providers.NavigationProvider;
import com.amazon.music.skyfire.ui.providers.NotificationProvider;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import com.amazon.music.skyfire.ui.providers.SharingProvider;
import com.amazon.music.skyfire.ui.providers.impl.DefaultExternalActionProvider;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.amazon.music.skyfire.ui.skyfire.Methods;
import com.squareup.picasso.Picasso;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExploreExternalActionProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ6\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/explore/providers/impl/ExploreExternalActionProvider;", "Lcom/amazon/music/skyfire/ui/providers/impl/DefaultExternalActionProvider;", "libraryProvider", "Lcom/amazon/music/skyfire/ui/providers/LibraryProvider;", "navigationProvider", "Lcom/amazon/music/skyfire/ui/providers/NavigationProvider;", "notificationProvider", "Lcom/amazon/music/skyfire/ui/providers/NotificationProvider;", "playbackProvider", "Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;", "sharingProvider", "Lcom/amazon/music/skyfire/ui/providers/SharingProvider;", "downloadProvider", "Lcom/amazon/music/skyfire/ui/providers/DownloadProvider;", "(Lcom/amazon/music/skyfire/ui/providers/LibraryProvider;Lcom/amazon/music/skyfire/ui/providers/NavigationProvider;Lcom/amazon/music/skyfire/ui/providers/NotificationProvider;Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;Lcom/amazon/music/skyfire/ui/providers/SharingProvider;Lcom/amazon/music/skyfire/ui/providers/DownloadProvider;)V", "handleAction", "", "context", "Landroid/content/Context;", "name", "", "args", "", "view", "Landroid/view/View;", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExploreExternalActionProvider extends DefaultExternalActionProvider {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExploreExternalActionProvider(LibraryProvider libraryProvider, NavigationProvider navigationProvider, NotificationProvider notificationProvider, PlaybackProvider playbackProvider, SharingProvider sharingProvider, DownloadProvider downloadProvider) {
        super(libraryProvider, navigationProvider, notificationProvider, playbackProvider, sharingProvider, downloadProvider);
        Intrinsics.checkNotNullParameter(libraryProvider, "libraryProvider");
        Intrinsics.checkNotNullParameter(navigationProvider, "navigationProvider");
        Intrinsics.checkNotNullParameter(notificationProvider, "notificationProvider");
        Intrinsics.checkNotNullParameter(playbackProvider, "playbackProvider");
        Intrinsics.checkNotNullParameter(sharingProvider, "sharingProvider");
        Intrinsics.checkNotNullParameter(downloadProvider, "downloadProvider");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.amazon.music.skyfire.ui.providers.impl.DefaultExternalActionProvider, com.amazon.music.skyfire.ui.providers.ExternalActionProvider
    public boolean handleAction(Context context, String name, Map<String, String> args, View view) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(args, "args");
        switch (name.hashCode()) {
            case -1801872935:
                if (name.equals(Methods.setNowPlayingIntroMetadata)) {
                    getPlaybackProvider().setNowPlayingScreenMetadata(args.get("TITLE"), args.get(Contexts.SetNowPlayingIntroMetadata.primarySubtitle), args.get(Contexts.SetNowPlayingIntroMetadata.secondarySubtitle), args.get(Contexts.SetNowPlayingIntroMetadata.artworkUrl));
                    return true;
                }
                break;
            case -1727365531:
                if (name.equals(Methods.setPlaybackViewLoadingImage)) {
                    String str = args.get(Contexts.SetPlaybackViewLoadingImage.imageUrl);
                    Picasso.get().load(str).transform(new BlurTransformation(context, 25.0f)).fetch();
                    PlaybackFragment.setLoadingImage(str);
                    return true;
                }
                break;
            case -1584784524:
                if (name.equals(Methods.openLiveEventDetail)) {
                    NavigationProvider navigationProvider = getNavigationProvider();
                    String str2 = args.get(Contexts.LiveEventDetail.eventId);
                    if (str2 == null) {
                        throw new IllegalStateException("Missing event ID".toString());
                    }
                    navigationProvider.openLiveEventDetail(str2);
                    return true;
                }
                break;
            case -1097125690:
                if (name.equals(Methods.openMerchDetail)) {
                    NavigationProvider navigationProvider2 = getNavigationProvider();
                    String str3 = args.get("ARTIST_ASIN");
                    if (str3 == null) {
                        throw new IllegalStateException("Missing Artist ASIN".toString());
                    }
                    String str4 = args.get("ASIN");
                    if (str4 == null) {
                        throw new IllegalStateException("Missing Merch ASIN".toString());
                    }
                    navigationProvider2.openMerchDetail(str3, str4);
                    return true;
                }
                break;
        }
        return super.handleAction(context, name, args, view);
    }
}
