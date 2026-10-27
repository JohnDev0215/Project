package com.amazon.mp3.find.skyfire;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.catalog.fragment.BrushFragment;
import com.amazon.mp3.find.util.AlexaUtil;
import com.amazon.mp3.find.view.SearchTabFragmentHost;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.util.Log;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.find.skyfire.Contexts;
import com.amazon.music.find.skyfire.Methods;
import com.amazon.music.find.view.FindSearchBarView;
import com.amazon.music.find.viewmodels.SearchExperienceMode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.skyfire.ui.providers.DownloadProvider;
import com.amazon.music.skyfire.ui.providers.LibraryProvider;
import com.amazon.music.skyfire.ui.providers.NavigationProvider;
import com.amazon.music.skyfire.ui.providers.NotificationProvider;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import com.amazon.music.skyfire.ui.providers.SharingProvider;
import com.amazon.music.skyfire.ui.providers.impl.DefaultExternalActionProvider;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: FindExternalActionProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\u0018\u0000 &2\u00020\u0001:\u0001&B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ6\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u001a\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u001b\u001a\u0004\u0018\u00010\u0014H\u0002J\u0018\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u0014H\u0002J\u0010\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u0014H\u0002J\u0018\u0010 \u001a\u00020\u001a2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u0014H\u0002J\u0018\u0010\"\u001a\u00020\u001a2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u0014H\u0002J\u0018\u0010$\u001a\u00020\u001a2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u0014H\u0002¨\u0006'"}, d2 = {"Lcom/amazon/mp3/find/skyfire/FindExternalActionProvider;", "Lcom/amazon/music/skyfire/ui/providers/impl/DefaultExternalActionProvider;", "libraryProvider", "Lcom/amazon/music/skyfire/ui/providers/LibraryProvider;", "navigationProvider", "Lcom/amazon/music/skyfire/ui/providers/NavigationProvider;", "notificationProvider", "Lcom/amazon/music/skyfire/ui/providers/NotificationProvider;", "playbackProvider", "Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;", "sharingProvider", "Lcom/amazon/music/skyfire/ui/providers/SharingProvider;", "downloadProvider", "Lcom/amazon/music/skyfire/ui/providers/DownloadProvider;", "(Lcom/amazon/music/skyfire/ui/providers/LibraryProvider;Lcom/amazon/music/skyfire/ui/providers/NavigationProvider;Lcom/amazon/music/skyfire/ui/providers/NotificationProvider;Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;Lcom/amazon/music/skyfire/ui/providers/SharingProvider;Lcom/amazon/music/skyfire/ui/providers/DownloadProvider;)V", "handleAction", "", "context", "Landroid/content/Context;", "name", "", "args", "", "view", "Landroid/view/View;", "handleDeepLink", "", "link", "handleUri", "uri", "openAlexa", "pageType", "openFindSeeMore", "contentId", "openSearch", "mode", "openTarget", "target", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FindExternalActionProvider extends DefaultExternalActionProvider {
    private static final String TAG = FindExternalActionProvider.class.getSimpleName();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FindExternalActionProvider(LibraryProvider libraryProvider, NavigationProvider navigationProvider, NotificationProvider notificationProvider, PlaybackProvider playbackProvider, SharingProvider sharingProvider, DownloadProvider downloadProvider) {
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
            case -137257358:
                if (name.equals(Methods.openFindSeeMore)) {
                    String str = args.get(Contexts.OpenFindSeeMore.contentId);
                    if (str == null) {
                        throw new IllegalStateException("Missing content id".toString());
                    }
                    openFindSeeMore(context, str);
                    return true;
                }
                break;
            case 428962109:
                if (name.equals(Methods.openSearch)) {
                    String str2 = args.get(Contexts.OpenSearch.mode);
                    if (str2 == null) {
                        throw new IllegalStateException("Missing search mode".toString());
                    }
                    openSearch(context, str2);
                    return true;
                }
                break;
            case 454393126:
                if (name.equals("OPEN_TARGET")) {
                    String str3 = args.get("TARGET");
                    if (str3 == null) {
                        throw new IllegalStateException("Missing target uri".toString());
                    }
                    openTarget(context, str3);
                    return true;
                }
                break;
            case 2075636654:
                if (name.equals(Methods.openAlexa)) {
                    String str4 = args.get(Contexts.OpenAlexa.pageType);
                    if (str4 == null) {
                        throw new IllegalStateException("Missing page type".toString());
                    }
                    openAlexa(str4);
                    return true;
                }
                break;
        }
        return super.handleAction(context, name, args, view);
    }

    private final void openSearch(Context context, String mode) {
        SearchExperienceMode searchExperienceModeValueOf;
        try {
            searchExperienceModeValueOf = SearchExperienceMode.valueOf(mode);
        } catch (Exception unused) {
            searchExperienceModeValueOf = null;
        }
        SearchTabFragmentHost.selectSearchTab(context instanceof MusicHomeActivity ? (MusicHomeActivity) context : null, searchExperienceModeValueOf, null, false, true);
    }

    private final void openAlexa(String pageType) {
        PageType pageTypeValueOf;
        try {
            pageTypeValueOf = PageType.valueOf(pageType);
        } catch (Exception unused) {
            pageTypeValueOf = PageType.BROWSE_FIND;
        }
        AlexaUtil.INSTANCE.triggerAlexa(pageTypeValueOf);
    }

    private final void openTarget(Context context, String target) {
        if (StringsKt__StringsKt.contains$default((CharSequence) target, (CharSequence) FindSearchBarView.URI_PREFIX, false, 2, (Object) null)) {
            Log.debug(TAG, "openTarget using URI handler.");
            handleUri(context, target);
        } else {
            try {
                Log.debug(TAG, "openTarget using DeepLink handler.");
                handleDeepLink(context, target);
            } catch (Exception unused) {
                Log.error(TAG, Intrinsics.stringPlus("openTarget failed to handle deeplink: ", target));
            }
        }
    }

    private final void openFindSeeMore(Context context, String contentId) {
        ((MusicHomeActivity) context).changeScreenFragment(FindSkyfireFactory.INSTANCE.createFindLandingSeeMorePage(StringsKt__StringsJVMKt.isBlank(contentId) ? null : MapsKt__MapsJVMKt.mapOf(TuplesKt.to("contentId", contentId))), true, true, false);
    }

    private final void handleDeepLink(Context context, String link) throws Exception {
        DeeplinksManager.get(context).handle(Uri.parse(link));
    }

    private final void handleUri(Context context, String uri) {
        BrushFragment brushFragmentCreateFragment = new BrushFragmentNavigation.Builder(uri).withToken(null).withBundle(null).createFragment();
        if (context instanceof NowPlayingFragmentActivity) {
            ((NowPlayingFragmentActivity) context).changeScreenFragment(brushFragmentCreateFragment, true, true, true);
        } else if (context instanceof MusicHomeActivity) {
            ((MusicHomeActivity) context).changeScreenFragment(brushFragmentCreateFragment, true, true, true);
        } else {
            Log.error(TAG, "handleUri unknown activity");
        }
    }
}
