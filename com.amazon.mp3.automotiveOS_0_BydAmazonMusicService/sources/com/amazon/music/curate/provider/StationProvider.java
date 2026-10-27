package com.amazon.music.curate.provider;

import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.music.curate.model.Track;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: StationProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/amazon/music/curate/provider/StationProvider;", "", "startStationFromTrackList", "", "tracks", "", "Lcom/amazon/music/curate/model/Track;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface StationProvider {
    void startStationFromTrackList(List<Track> tracks, FragmentActivity activity);
}
