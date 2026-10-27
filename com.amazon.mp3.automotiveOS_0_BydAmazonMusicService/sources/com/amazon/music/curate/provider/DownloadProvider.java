package com.amazon.music.curate.provider;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.model.Track;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0001\u0014J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H&J\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H&J\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H&¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/curate/provider/DownloadProvider;", "", "download", "", Environment.PLAYLIST_PATH, "Lcom/amazon/music/curate/model/Playlist;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "track", "Lcom/amazon/music/curate/model/Track;", "getCatalogPlaylistDownloadStatus", "Landroidx/lifecycle/LiveData;", "Lcom/amazon/music/curate/provider/DownloadStatus;", "asin", "", "context", "Landroid/content/Context;", "getTrackDownloadStatus", "getUserPlaylistDownloadStatus", "collectionId", "TrackCollection", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface DownloadProvider {
    void download(Playlist playlist, FragmentActivity activity);

    void download(Track track);

    LiveData<DownloadStatus> getCatalogPlaylistDownloadStatus(String asin, Context context);

    LiveData<DownloadStatus> getTrackDownloadStatus(String asin, Context context);

    LiveData<DownloadStatus> getUserPlaylistDownloadStatus(String collectionId, Context context);

    /* JADX INFO: compiled from: DownloadProvider.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/curate/provider/DownloadProvider$TrackCollection;", "", "id", "", "tracks", "", "Lcom/amazon/music/curate/model/Track;", "(Ljava/lang/String;Ljava/util/List;)V", "getId", "()Ljava/lang/String;", "getTracks", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class TrackCollection {
        private final String id;
        private final List<Track> tracks;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ TrackCollection copy$default(TrackCollection trackCollection, String str, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = trackCollection.id;
            }
            if ((i & 2) != 0) {
                list = trackCollection.tracks;
            }
            return trackCollection.copy(str, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        public final List<Track> component2() {
            return this.tracks;
        }

        public final TrackCollection copy(String id, List<Track> tracks) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(tracks, "tracks");
            return new TrackCollection(id, tracks);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TrackCollection)) {
                return false;
            }
            TrackCollection trackCollection = (TrackCollection) other;
            return Intrinsics.areEqual(this.id, trackCollection.id) && Intrinsics.areEqual(this.tracks, trackCollection.tracks);
        }

        public int hashCode() {
            return (this.id.hashCode() * 31) + this.tracks.hashCode();
        }

        public String toString() {
            return "TrackCollection(id=" + this.id + ", tracks=" + this.tracks + ')';
        }

        public TrackCollection(String id, List<Track> tracks) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(tracks, "tracks");
            this.id = id;
            this.tracks = tracks;
        }

        public final String getId() {
            return this.id;
        }

        public final List<Track> getTracks() {
            return this.tracks;
        }
    }
}
