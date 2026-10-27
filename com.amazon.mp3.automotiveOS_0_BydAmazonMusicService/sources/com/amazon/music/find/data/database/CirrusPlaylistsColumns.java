package com.amazon.music.find.data.database;

import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;

/* JADX INFO: compiled from: CirrusDatabaseProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/find/data/database/CirrusPlaylistsColumns;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", Contexts.LibraryArtistDetail.name, "DESCRIPTION", "IS_NIGHTWING", "IS_NIGHTWING_ON_DEMAND", "IS_PRIME", "IS_SONIC_RUSH", "SOURCE", "THUMBNAIL_ART_URL", "LUID", "_ID", "DOWNLOAD_STATE", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum CirrusPlaylistsColumns {
    NAME("name"),
    DESCRIPTION(MediaTrack.ROLE_DESCRIPTION),
    IS_NIGHTWING("is_nightwing"),
    IS_NIGHTWING_ON_DEMAND("is_nightwing_on_demand"),
    IS_PRIME("is_prime"),
    IS_SONIC_RUSH("is_sonic_rush"),
    SOURCE(PageUriUtils.SOURCE_QUERY_PARAM),
    THUMBNAIL_ART_URL("thumbnailArtUrl"),
    LUID("luid"),
    _ID("_id"),
    DOWNLOAD_STATE("download_state");

    private final String value;

    CirrusPlaylistsColumns(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }
}
