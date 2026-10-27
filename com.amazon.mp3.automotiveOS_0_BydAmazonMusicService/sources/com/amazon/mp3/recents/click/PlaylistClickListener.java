package com.amazon.mp3.recents.click;

import android.content.Context;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.curate.provider.CurateLibraryNavigationProvider;
import com.amazon.mp3.fragment.navigation.NavigationProvider;
import com.amazon.mp3.library.adapter.LibraryPagerAdapter;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.recents.metrics.RecentsPlaylistsMetricsLogger;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistClickListener.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/recents/click/PlaylistClickListener;", "Landroid/view/View$OnClickListener;", "context", "Landroid/content/Context;", "navigationProvider", "Lcom/amazon/mp3/fragment/navigation/NavigationProvider;", "contentMetadata", "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "metricsLogger", "Lcom/amazon/mp3/recents/metrics/RecentsPlaylistsMetricsLogger;", "fragment", "Landroidx/fragment/app/Fragment;", "(Landroid/content/Context;Lcom/amazon/mp3/fragment/navigation/NavigationProvider;Lcom/amazon/music/views/library/metadata/PlaylistMetadata;Lcom/amazon/mp3/recents/metrics/RecentsPlaylistsMetricsLogger;Landroidx/fragment/app/Fragment;)V", "getPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "onClick", "", "view", "Landroid/view/View;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistClickListener implements View.OnClickListener {
    private final PlaylistMetadata contentMetadata;
    private final Context context;
    private final Fragment fragment;
    private final RecentsPlaylistsMetricsLogger metricsLogger;
    private final NavigationProvider navigationProvider;

    public PlaylistClickListener(Context context, NavigationProvider navigationProvider, PlaylistMetadata contentMetadata, RecentsPlaylistsMetricsLogger recentsPlaylistsMetricsLogger, Fragment fragment) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(navigationProvider, "navigationProvider");
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        this.context = context;
        this.navigationProvider = navigationProvider;
        this.contentMetadata = contentMetadata;
        this.metricsLogger = recentsPlaylistsMetricsLogger;
        this.fragment = fragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        CurateLibraryNavigationProvider curateLibraryNavigationProvider = new CurateLibraryNavigationProvider(new WeakReference(this.fragment.getActivity()));
        FragmentActivity activity = this.fragment.getActivity();
        long playlistId = MediaProvider.SmartPlaylists.getPlaylistId(this.contentMetadata.getUri());
        if (playlistId == 1) {
            Intrinsics.checkNotNull(activity);
            String string = activity.getString(R.string.dmusic_recently_added_my_music);
            Intrinsics.checkNotNullExpressionValue(string, "activity!!.getString(R.s…_recently_added_my_music)");
            curateLibraryNavigationProvider.navigateToRecents(1L, "cirrus", string);
        } else if (playlistId == 2) {
            Intrinsics.checkNotNull(activity);
            String string2 = activity.getString(R.string.dmusic_recently_downloaded_songs);
            Intrinsics.checkNotNullExpressionValue(string2, "activity!!.getString(R.s…ecently_downloaded_songs)");
            curateLibraryNavigationProvider.navigateToRecents(2L, "cirrus-local", string2);
        } else if (playlistId == 3) {
            Intrinsics.checkNotNull(activity);
            String string3 = activity.getString(R.string.dmusic_listening_history);
            Intrinsics.checkNotNullExpressionValue(string3, "activity!!.getString(R.s…dmusic_listening_history)");
            curateLibraryNavigationProvider.navigateToRecents(3L, "cirrus", string3);
        }
        RecentsPlaylistsMetricsLogger recentsPlaylistsMetricsLogger = this.metricsLogger;
        if (recentsPlaylistsMetricsLogger == null) {
            return;
        }
        long playlistId2 = PlaylistUtil.getPlaylistId(this.contentMetadata.getUri());
        recentsPlaylistsMetricsLogger.sendUIClickMetrics(playlistId2, getPageType());
        recentsPlaylistsMetricsLogger.sendUIPageViewMetrics(playlistId2);
    }

    private final PageType getPageType() {
        if (Intrinsics.areEqual("cirrus", LastViewedScreenUtil.getLastViewedSource(this.context, LibraryPagerAdapter.DEFAULT_LIBRARY_SOURCE).toCirrusMediaSource())) {
            return PageType.MY_MUSIC_CLOUD_FRONT_PAGE;
        }
        return PageType.MY_MUSIC_OFFLINE_FRONT_PAGE;
    }
}
