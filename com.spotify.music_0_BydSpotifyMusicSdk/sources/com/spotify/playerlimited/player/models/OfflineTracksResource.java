package com.spotify.playerlimited.player.models;

import com.spotify.playerlimited.player.moshi.MoshiRootName;
import java.util.List;
import p.c71;
import p.hf2;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@MoshiRootName("resources")
@c71(generateAdapter = true)
public final class OfflineTracksResource extends OfflineResource {
    public List c;

    @z61(name = "tracks")
    public static /* synthetic */ void getTracks$annotations() {
    }

    public final String toString() {
        List<OfflineResource> list = this.c;
        if (list == null) {
            return "";
        }
        String strD = "";
        for (OfflineResource offlineResource : list) {
            StringBuilder sb = new StringBuilder();
            sb.append(strD);
            sb.append('[');
            sb.append(offlineResource.a);
            sb.append(" : ");
            strD = hf2.d(sb, offlineResource.b, ']');
        }
        return strD == null ? "" : strD;
    }
}
