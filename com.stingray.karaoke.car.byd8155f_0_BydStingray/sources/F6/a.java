package F6;

import A6.d;
import A7.m;
import B5.C0083g;
import B5.z;
import android.content.Context;
import com.stingray.shared.logs.segment.ActionOwner;
import com.stingray.shared.logs.segment.songQueue.SourceType;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import n7.AbstractC1998m;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f2423a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final A6.c f2424b;
    public C0083g c;

    public a(Context context, A6.c cVar) {
        m.f(context, "context");
        this.f2423a = context;
        this.f2424b = cVar;
    }

    public final void a(String str, z zVar, ActionOwner actionOwner) {
        zVar.k(actionOwner.name(), "action_owner");
        LinkedHashMap linkedHashMap = d.f439a;
        com.bumptech.glide.d.p(zVar);
        if (this.c == null) {
            this.c = C0083g.h(this.f2423a);
        }
        C0083g c0083g = this.c;
        m.c(c0083g);
        c0083g.g(str, zVar);
        this.f2424b.a(str, zVar.h());
        Set setEntrySet = zVar.l.entrySet();
        m.e(setEntrySet, "<get-entries>(...)");
        System.out.println((Object) ("========== " + str + " ==========  " + AbstractC1998m.f0(setEntrySet, null, null, null, new A3.c(7), 31) + "\n"));
    }

    public final void b(ActionOwner actionOwner, SourceType sourceType, Integer num, String str, String str2, List list, boolean z6) {
        m.f(list, "songIds");
        m.f(str, "artistId");
        m.f(actionOwner, "actionOwner");
        z zVar = new z();
        zVar.k(list, "asset_ids");
        zVar.k(Boolean.valueOf(z6), "queue_cleared");
        zVar.k(str, "artist_id");
        zVar.k(num, "queue_overflow_removed");
        if (str2 != null) {
            zVar.k(str2, "asset_source");
        }
        if (sourceType != null) {
            zVar.k(sourceType.name(), "asset_source_type");
        }
        a("Karaoke Queue Add Artist", zVar, actionOwner);
    }

    public final void c(ActionOwner actionOwner, SourceType sourceType, Integer num, String str, String str2, List list, boolean z6) {
        m.f(list, "songIds");
        m.f(str, "playlistId");
        m.f(actionOwner, "actionOwner");
        z zVar = new z();
        zVar.k(list, "asset_ids");
        zVar.k(Boolean.valueOf(z6), "queue_cleared");
        zVar.k(str, "playlist_id");
        zVar.k(num, "queue_overflow_removed");
        if (str2 != null) {
            zVar.k(URLDecoder.decode(str2, "utf-8"), "asset_source");
        }
        if (sourceType != null) {
            zVar.k(sourceType.name(), "asset_source_type");
        }
        a("Karaoke Queue Add Playlist", zVar, actionOwner);
    }

    public final void d(String str, boolean z6, ActionOwner actionOwner, Integer num, String str2, SourceType sourceType, String str3) {
        m.f(str, "songId");
        m.f(actionOwner, "actionOwner");
        m.f(str2, "queueActionUsed");
        z zVar = new z();
        zVar.k(str, "asset_id");
        zVar.k(Boolean.valueOf(z6), "queue_cleared");
        zVar.k(num, "queue_overflow_removed");
        zVar.k(str2, "queue_action_used");
        if (str3 != null) {
            zVar.k(str3, "asset_source");
        }
        if (sourceType != null) {
            zVar.k(sourceType.name(), "asset_source_type");
        }
        a("Karaoke Queue Add Song", zVar, actionOwner);
    }

    public final void e(String str, int i7, int i10, ActionOwner actionOwner) {
        m.f(str, "songId");
        m.f(actionOwner, "actionOwner");
        z zVar = new z();
        zVar.k(str, "asset_id");
        zVar.k(Integer.valueOf(i7), "previous_queue_index");
        zVar.k(Integer.valueOf(i10), "queue_index");
        a("Karaoke Queue Reorder", zVar, actionOwner);
    }
}
