package com.amazon.mp3.curate.provider;

import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.util.StringUtil;
import com.amazon.music.curate.model.Track;
import com.amazon.music.curate.provider.StationProvider;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CurateStationProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/curate/provider/CurateStationProvider;", "Lcom/amazon/music/curate/provider/StationProvider;", "()V", "startStationFromTrackList", "", "tracks", "", "Lcom/amazon/music/curate/model/Track;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CurateStationProvider implements StationProvider {
    @Override // com.amazon.music.curate.provider.StationProvider
    public void startStationFromTrackList(final List<Track> tracks, final FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        Intrinsics.checkNotNullParameter(activity, "activity");
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.curate.provider.CurateStationProvider$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CurateStationProvider.m776startStationFromTrackList$lambda1(tracks, activity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startStationFromTrackList$lambda-1, reason: not valid java name */
    public static final void m776startStationFromTrackList$lambda1(List tracks, FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(tracks, "$tracks");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(tracks, 10));
        Iterator it = tracks.iterator();
        while (it.hasNext()) {
            arrayList.add(((Track) it.next()).getAsin());
        }
        StationUtils.startSFA(activity, StationUtils.convertAsinsToTracks(arrayList), "TRACK_LIST_SEED", PlaybackPageType.PLAYLIST_DETAIL);
        StationUtils.sendStartSFAUiClickMetrics(StringUtil.join(",", arrayList), EntityIdType.SFA_TRACK_LIST_SEED);
    }
}
