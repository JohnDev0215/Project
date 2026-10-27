package com.amazon.music.curate.data.database;

import androidx.core.app.NotificationCompat;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.curate.data.PlaylistService;
import com.amazon.music.curate.model.Track;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecsCache.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\u0015\u0016B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\u0007J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\bJ\u000e\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u0007J\u001c\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u00072\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0017"}, d2 = {"Lcom/amazon/music/curate/data/database/RecsCache;", "", NotificationCompat.CATEGORY_SERVICE, "Lcom/amazon/music/curate/data/PlaylistService;", "(Lcom/amazon/music/curate/data/PlaylistService;)V", "recsMap", "", "", "Lcom/amazon/music/curate/data/database/RecsCache$Entry;", "getService", "()Lcom/amazon/music/curate/data/PlaylistService;", "getRecs", "userPlaylistId", "putRecs", "", "entry", "removeAllRecs", "removeRecs", "tracksToRemove", "", "Lcom/amazon/music/curate/model/Track;", "Entry", "Type", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RecsCache {
    private Map<String, Entry> recsMap;
    private final PlaylistService service;

    /* JADX INFO: compiled from: RecsCache.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/curate/data/database/RecsCache$Type;", "", "(Ljava/lang/String;I)V", "RECOMMENDATIONS", "RECENTLY_PLAYED", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum Type {
        RECOMMENDATIONS,
        RECENTLY_PLAYED
    }

    public RecsCache(PlaylistService service) {
        Intrinsics.checkNotNullParameter(service, "service");
        this.service = service;
        this.recsMap = new HashMap();
    }

    public final PlaylistService getService() {
        return this.service;
    }

    /* JADX INFO: compiled from: RecsCache.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0006HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/curate/data/database/RecsCache$Entry;", "", "tracks", "", "Lcom/amazon/music/curate/model/Track;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/music/curate/data/database/RecsCache$Type;", "(Ljava/util/List;Lcom/amazon/music/curate/data/database/RecsCache$Type;)V", "getTracks", "()Ljava/util/List;", "getType", "()Lcom/amazon/music/curate/data/database/RecsCache$Type;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class Entry {
        private final List<Track> tracks;
        private final Type type;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Entry copy$default(Entry entry, List list, Type type, int i, Object obj) {
            if ((i & 1) != 0) {
                list = entry.tracks;
            }
            if ((i & 2) != 0) {
                type = entry.type;
            }
            return entry.copy(list, type);
        }

        public final List<Track> component1() {
            return this.tracks;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Type getType() {
            return this.type;
        }

        public final Entry copy(List<Track> tracks, Type type) {
            Intrinsics.checkNotNullParameter(tracks, "tracks");
            Intrinsics.checkNotNullParameter(type, "type");
            return new Entry(tracks, type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) other;
            return Intrinsics.areEqual(this.tracks, entry.tracks) && this.type == entry.type;
        }

        public int hashCode() {
            return (this.tracks.hashCode() * 31) + this.type.hashCode();
        }

        public String toString() {
            return "Entry(tracks=" + this.tracks + ", type=" + this.type + ')';
        }

        public Entry(List<Track> tracks, Type type) {
            Intrinsics.checkNotNullParameter(tracks, "tracks");
            Intrinsics.checkNotNullParameter(type, "type");
            this.tracks = tracks;
            this.type = type;
        }

        public final List<Track> getTracks() {
            return this.tracks;
        }

        public final Type getType() {
            return this.type;
        }
    }

    public final Entry getRecs(String userPlaylistId) {
        Intrinsics.checkNotNullParameter(userPlaylistId, "userPlaylistId");
        return this.recsMap.get(userPlaylistId);
    }

    public final void putRecs(String userPlaylistId, Entry entry) {
        Intrinsics.checkNotNullParameter(userPlaylistId, "userPlaylistId");
        Intrinsics.checkNotNullParameter(entry, "entry");
        this.recsMap.put(userPlaylistId, entry);
    }

    public final void removeAllRecs(String userPlaylistId) {
        Intrinsics.checkNotNullParameter(userPlaylistId, "userPlaylistId");
        this.recsMap.remove(userPlaylistId);
    }

    public final void removeRecs(String userPlaylistId, List<Track> tracksToRemove) {
        Intrinsics.checkNotNullParameter(userPlaylistId, "userPlaylistId");
        Intrinsics.checkNotNullParameter(tracksToRemove, "tracksToRemove");
        Entry entry = this.recsMap.get(userPlaylistId);
        if (entry != null) {
            Iterator<T> it = tracksToRemove.iterator();
            while (it.hasNext()) {
                entry.getTracks().remove((Track) it.next());
            }
        }
    }
}
