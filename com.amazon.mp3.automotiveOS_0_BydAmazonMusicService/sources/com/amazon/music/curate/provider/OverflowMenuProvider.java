package com.amazon.music.curate.provider;

import android.view.ContextMenu;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.model.Track;
import com.amazon.music.curate.model.station;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import kotlin.Metadata;

/* JADX INFO: compiled from: OverflowMenuProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J8\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/curate/provider/OverflowMenuProvider;", "", "showOverflowMenu", "", Environment.PLAYLIST_PATH, "Lcom/amazon/music/curate/model/Playlist;", "menu", "Landroid/view/ContextMenu;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "station", "Lcom/amazon/music/curate/model/Station;", "showTrackOverflowMenu", "userPlaylistId", "", "track", "Lcom/amazon/music/curate/model/Track;", "index", "", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface OverflowMenuProvider {
    void showOverflowMenu(Playlist playlist, ContextMenu menu, FragmentActivity activity);

    void showOverflowMenu(station station, ContextMenu menu, FragmentActivity activity);

    void showTrackOverflowMenu(String userPlaylistId, Track track, int index, PageType pageType, ContextMenu menu, FragmentActivity activity);
}
