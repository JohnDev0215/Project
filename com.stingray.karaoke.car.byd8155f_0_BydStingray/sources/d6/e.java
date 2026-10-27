package D6;

import A7.m;
import B5.C0083g;
import B5.z;
import S8.AbstractC0727y;
import S8.E;
import android.content.Context;
import com.stingray.shared.logs.segment.ActionOwner;
import com.stingray.shared.logs.segment.player.CastPlatform;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1210a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final A6.c f1211b;
    public C0083g c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1212e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1213f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1214g;

    public e(Context context, A6.c cVar) {
        m.f(context, "context");
        this.f1210a = context;
        this.f1211b = cVar;
        this.d = "";
    }

    public static z a(b bVar, a aVar, ActionOwner actionOwner) {
        z zVar = new z();
        zVar.k(bVar.f1198a, "video_player");
        zVar.k(bVar.f1199b, "video_player_background ");
        zVar.k(Boolean.valueOf(bVar.c), "full_screen");
        zVar.k(Boolean.valueOf(bVar.d), "lead_vocals");
        zVar.k(bVar.f1201f, "lobby_id");
        zVar.k(Integer.valueOf(bVar.f1202g), "queue_index");
        zVar.k(bVar.f1203h, "queue_repeat");
        zVar.k(Boolean.valueOf(bVar.f1205j), "is_gaming_mode");
        zVar.k(Boolean.valueOf(bVar.f1206k), "is_scorable");
        String str = bVar.f1204i;
        if (str.length() > 0) {
            zVar.k(str, "ar_filter");
        }
        CastPlatform castPlatform = bVar.f1200e;
        if (castPlatform != null) {
            zVar.k(castPlatform, "cast_platform");
        }
        if (aVar != null) {
            zVar.k(aVar.f1194a, "asset_id");
            zVar.k(aVar.f1195b, "asset_type");
            zVar.k(aVar.c, "genres");
            zVar.k(Integer.valueOf(aVar.d), "position");
            zVar.k(Integer.valueOf(aVar.f1196e), "total_length");
            zVar.k(aVar.f1197f, "video_background_url");
        }
        if (actionOwner != null) {
            zVar.k(actionOwner, "action_owner");
        }
        return zVar;
    }

    public static /* synthetic */ z b(e eVar, b bVar, a aVar, ActionOwner actionOwner, int i7) {
        if ((i7 & 2) != 0) {
            aVar = null;
        }
        if ((i7 & 4) != 0) {
            actionOwner = ActionOwner.HOST;
        }
        eVar.getClass();
        return a(bVar, aVar, actionOwner);
    }

    public final boolean c(String str, z zVar, boolean z6) {
        if (!z6) {
            return false;
        }
        Z8.e eVar = E.f8084a;
        AbstractC0727y.w(AbstractC0727y.a(Z8.d.f11010n), null, null, new d(zVar, this, str, null), 3);
        return true;
    }

    public final void d(b bVar, a aVar, ActionOwner actionOwner) {
        m.f(actionOwner, "actionOwner");
        c("Karaoke Playback Resumed", a(bVar, aVar, actionOwner), this.f1212e);
        this.f1213f = false;
    }
}
