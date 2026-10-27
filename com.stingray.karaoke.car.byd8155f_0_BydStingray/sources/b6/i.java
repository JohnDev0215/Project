package b6;

import I5.y;
import K5.u;
import S8.AbstractC0727y;
import androidx.lifecycle.a0;
import com.stingray.karaoke.car.ui.navigation.NavigationRoutes;
import com.stingray.karaoke.car.ui.navigation.NavigationType;
import com.stingray.karaoke.car.ui.player.PlayerSideMenuRoute;
import com.stingray.kca.model.KaraokeItem;
import com.stingray.kca.model.KaraokeItemChildType;
import com.stingray.kca.model.KaraokeItemType;
import com.stingray.shared.logs.segment.songQueue.SourceType;
import java.net.URLEncoder;
import k6.C1705m;
import n3.C1930E;
import n7.z;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class i extends f {
    public final j6.n l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public InterfaceC2782k f12245m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public InterfaceC2782k f12246n;
    public InterfaceC2782k o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final W5.e f12247p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final u f12248q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final L5.i f12249r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public String f12250s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(C1705m c1705m, W5.e eVar, u uVar, L5.i iVar, j6.n nVar) {
        super(c1705m, eVar);
        A7.m.f(c1705m, "languageManager");
        A7.m.f(eVar, "appNavigationManager");
        A7.m.f(uVar, "karaokeItemRepository");
        A7.m.f(iVar, "deepLinkManager");
        A7.m.f(nVar, "profileManager");
        A7.m.f(c1705m, "languageManager");
        A7.m.f(eVar, "appNavigationManager");
        A7.m.f(nVar, "profileManager");
        this.l = nVar;
        this.f12245m = new U5.i(6);
        this.f12246n = new U5.i(7);
        this.o = new U5.i(8);
        AbstractC0727y.w(a0.i(this), null, null, new C0998a(this, null), 3);
        this.f12247p = eVar;
        this.f12248q = uVar;
        this.f12249r = iVar;
    }

    public final void n(KaraokeItem karaokeItem, F6.b bVar) {
        A7.m.f(karaokeItem, "item");
        String str = karaokeItem.f13107a;
        u uVar = this.f12248q;
        if (uVar.d(str) == null) {
            uVar.a(karaokeItem);
        }
        KaraokeItemType karaokeItemType = karaokeItem.f13109e;
        int i7 = karaokeItemType == null ? -1 : h.f12244a[karaokeItemType.ordinal()];
        if (i7 == 1 || i7 == 2) {
            if (str != null) {
                this.f12249r.f(str, bVar);
                return;
            }
            return;
        }
        NavigationType navigationType = this.d;
        NavigationType navigationType2 = NavigationType.PLAYER;
        boolean z6 = navigationType == navigationType2;
        W5.e eVar = this.f12247p;
        eVar.getClass();
        if (A7.m.a(karaokeItem.f13117n, Boolean.FALSE)) {
            if (A7.m.a(karaokeItem.f13116m, Boolean.TRUE)) {
                z.b(null, z6, new b0.b(1919265680, new y(eVar, 17), true), 7);
                return;
            } else {
                z.b(null, z6, W5.g.f9821a, 7);
                return;
            }
        }
        KaraokeItemChildType karaokeItemChildType = karaokeItem.f13110f;
        if (!z6) {
            if (karaokeItemType == KaraokeItemType.PLAY) {
                eVar.g(NavigationRoutes.SONG_DETAILS, str, bVar);
                return;
            }
            int i10 = karaokeItemChildType != null ? W5.a.f9811a[karaokeItemChildType.ordinal()] : -1;
            if (i10 == 1 || i10 == 2) {
                eVar.g(NavigationRoutes.SONGS, str, bVar);
                return;
            } else {
                if (i10 != 3) {
                    return;
                }
                eVar.g(NavigationRoutes.ARTISTS, str, bVar);
                return;
            }
        }
        C1930E c1930e = (C1930E) eVar.d.get(navigationType2);
        KaraokeItemType karaokeItemType2 = KaraokeItemType.PLAY;
        String str2 = karaokeItem.f13108b;
        if (karaokeItemType == karaokeItemType2) {
            String strC = W5.e.c(PlayerSideMenuRoute.SongDetails.getValue() + "/" + URLEncoder.encode(str2, "utf-8") + "/" + URLEncoder.encode(str, "utf-8"), bVar != null ? bVar.f2426b : null, SourceType.PLAYER);
            if (c1930e != null) {
                c1930e.n(strC, new U5.i(1));
                return;
            }
            return;
        }
        if (karaokeItemChildType == KaraokeItemChildType.ARTIST) {
            String strC2 = W5.e.c(PlayerSideMenuRoute.Artists.getValue() + "/" + URLEncoder.encode(str2, "utf-8") + "/" + URLEncoder.encode(str, "utf-8"), bVar != null ? bVar.f2426b : null, SourceType.PLAYER);
            if (c1930e != null) {
                c1930e.n(strC2, new U5.i(2));
                return;
            }
            return;
        }
        String strC3 = W5.e.c(PlayerSideMenuRoute.Songs.getValue() + "/" + URLEncoder.encode(str2, "utf-8") + "/" + URLEncoder.encode(str, "utf-8"), bVar != null ? bVar.f2426b : null, SourceType.PLAYER);
        if (c1930e != null) {
            c1930e.n(strC3, new U5.i(3));
        }
    }
}
