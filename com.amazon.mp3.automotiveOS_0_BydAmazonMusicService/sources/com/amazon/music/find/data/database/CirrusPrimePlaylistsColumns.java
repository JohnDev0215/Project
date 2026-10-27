package com.amazon.music.find.data.database;

import com.amazon.music.find.utils.PageUriUtils;
import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;

/* JADX INFO: compiled from: CirrusDatabaseProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001d¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/find/data/database/CirrusPrimePlaylistsColumns;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "TITLE", "LUID", "ASIN", "DOWNLOAD_STATE", "UPDATED_DATE", "IS_FOLLOWING", "DESCRIPTION", "IS_NEW", "TRACK_COUNT", "ART_URL", "THUMBNAIL_ART_URL", "DURATION", "MARKETPLACE_ID", "VERSION", "CURATED_BY", "IS_PRIME", "IS_SONIC_RUSH", "IS_SHARED_USER_PLAYLIST", "IS_NIGHTWING", "IS_NIGHTWING_ON_DEMAND", "PROFILE_ID", "SOURCE", "_ID", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum CirrusPrimePlaylistsColumns {
    TITLE("title"),
    LUID("luid"),
    ASIN("asin"),
    DOWNLOAD_STATE("download_state"),
    UPDATED_DATE("updatedDate"),
    IS_FOLLOWING("is_following"),
    DESCRIPTION(MediaTrack.ROLE_DESCRIPTION),
    IS_NEW("is_new"),
    TRACK_COUNT("track_count"),
    ART_URL("art_url"),
    THUMBNAIL_ART_URL("thumbnail_art_url"),
    DURATION("duration"),
    MARKETPLACE_ID("marketplace_id"),
    VERSION("version"),
    CURATED_BY("curated_by"),
    IS_PRIME("is_prime"),
    IS_SONIC_RUSH("is_sonic_rush"),
    IS_SHARED_USER_PLAYLIST("is_shared_user_playlist"),
    IS_NIGHTWING("is_nightwing"),
    IS_NIGHTWING_ON_DEMAND("is_nightwing_on_demand"),
    PROFILE_ID("profile_id"),
    SOURCE(PageUriUtils.SOURCE_QUERY_PARAM),
    _ID("_id");

    private final String value;

    CirrusPrimePlaylistsColumns(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }
}
