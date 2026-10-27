package com.amazon.mp3.voice.wakeword;

import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.auto.carmode.fragment.CarModeBrushFragment;
import com.amazon.mp3.auto.carmode.fragment.CarModeDetailFragment;
import com.amazon.mp3.auto.carmode.fragment.CarModeNowPlayingFragment;
import com.amazon.mp3.auto.carmode.fragment.CarModePlayQueueFragment;
import com.amazon.mp3.auto.carmode.fragment.CarModeV2BrushFragment;
import com.amazon.mp3.catalog.fragment.ArtistDetailBrushFragment;
import com.amazon.mp3.catalog.fragment.ChartsDetailBrushFragment;
import com.amazon.mp3.catalog.fragment.GenreDetailBrushFragment;
import com.amazon.mp3.catalog.fragment.NewReleasesDetailBrushFragment;
import com.amazon.mp3.detailpages.album.AlbumDetailFragment;
import com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment;
import com.amazon.mp3.find.view.FindLandingFragment;
import com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment;
import com.amazon.mp3.library.mylibrary.LibraryFragment;
import com.amazon.mp3.library.mylibrary.MyLibraryHomeFragment;
import com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment;
import com.amazon.mp3.playback.fragment.NowPlayingFragment;
import com.amazon.mp3.prime.browse.PrimeTabFragmentHost;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.skyfire.ui.fragment.GalleryFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WakeWordMetricsHelper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0007J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\b¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/voice/wakeword/WakeWordMetricsHelper;", "", "()V", "getCurrentFragment", "Landroidx/fragment/app/Fragment;", "getExperienceMode", "Lcom/amazon/music/metrics/mts/event/types/ExperienceMode;", "getPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WakeWordMetricsHelper {
    private static final String TAG;

    static {
        String simpleName = WakeWordMetricsHelper.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "WakeWordMetricsHelper::class.java.simpleName");
        TAG = simpleName;
    }

    public final PageType getPageType() {
        Fragment currentFragment = getCurrentFragment();
        if (currentFragment instanceof NewReleasesDetailBrushFragment) {
            return PageType.BROWSE_NEW_TO_PRIME;
        }
        if (currentFragment instanceof ChartsDetailBrushFragment) {
            return PageType.BROWSE_POPULAR;
        }
        if (currentFragment instanceof LibraryFragment ? true : currentFragment instanceof MyLibraryHomeFragment ? true : currentFragment instanceof MyLibraryV2HomeFragment) {
            return PageType.LIBRARY_LANDING_PAGE;
        }
        if (currentFragment instanceof NowPlayingFragment) {
            return PageType.NOW_PLAYING;
        }
        if (currentFragment instanceof PrimeTabFragmentHost) {
            return PageType.BROWSE_HOME;
        }
        if (currentFragment instanceof FindLandingFragment) {
            return PageType.BROWSE_FIND;
        }
        if (currentFragment instanceof GalleryFragment ? true : currentFragment instanceof ArtistDetailBrushFragment) {
            return PageType.BRUSH_DETAIL_ARTIST;
        }
        if (currentFragment instanceof GenreDetailBrushFragment) {
            return PageType.BROWSE_GEMA;
        }
        if (currentFragment instanceof PlaylistDetailFragment ? true : currentFragment instanceof PrimePlaylistDetailFragment) {
            return PageType.DETAIL_PLAYLIST;
        }
        if (currentFragment instanceof AlbumDetailFragment) {
            return PageType.DETAIL_ALBUM;
        }
        if (currentFragment instanceof CarModeBrushFragment) {
            return PageType.CAR_MODE_PRESET_LIST;
        }
        if (currentFragment instanceof CarModeV2BrushFragment) {
            return PageType.CAR_MODE_HOME;
        }
        if (currentFragment instanceof CarModeNowPlayingFragment) {
            return PageType.CAR_MODE_NOW_PLAYING;
        }
        if (currentFragment instanceof CarModePlayQueueFragment) {
            return PageType.CAR_MODE_PLAY_QUEUE;
        }
        if (currentFragment instanceof CarModeDetailFragment) {
            return PageType.CAR_MODE_DETAIL;
        }
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("WakeWordDetected in screen ");
        sb.append((Object) (currentFragment == null ? null : currentFragment.getClass().getSimpleName()));
        sb.append(" but pageType value not captured");
        Log.debug(str, sb.toString());
        return PageType.UNKNOWN;
    }

    public final ExperienceMode getExperienceMode() {
        return CarModeUtility.isInCarMode() ? ExperienceMode.CAR_MODE : ExperienceMode.MOBILE;
    }

    @VisibleForTesting
    public final Fragment getCurrentFragment() {
        return AndroidUtils.getVisibleFragment(AndroidUtils.getCurrentActivity());
    }
}
