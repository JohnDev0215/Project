package L5;

import A7.m;
import K5.u;
import N5.C0451g;
import N5.C0452h;
import N5.C0463t;
import N5.C0467x;
import N5.Z;
import N5.q0;
import Q8.o;
import S8.AbstractC0727y;
import S8.E;
import V8.f0;
import X5.C0886u;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.stingray.karaoke.car.deeplink.DeepLinkManager$DeepLinkType;
import com.stingray.karaoke.car.player.PlayerActionType;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.karaoke.car.ui.navigation.NavigationRoutes;
import com.stingray.karaoke.car.ui.navigation.NavigationType;
import com.stingray.karaoke.car.ui.navigation.StingrayKaraokeTabScreen;
import com.stingray.kca.model.KaraokeItem;
import com.stingray.shared.logs.segment.ActionOwner;
import com.stingray.shared.logs.segment.songQueue.SourceType;
import e6.k;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import n3.C1930E;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import n7.v;
import n7.z;
import timber.log.Timber;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final List f4030n = AbstractC1999n.D("internal://app/", "singingmachine://app/", "stingraykaraoke://app/", "https://karaoke.stingray.com/singingmachine/app/", "https://karaoke.stingray.com/app/", "stingraykaraoke://command/");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f4031a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final J5.c f4032b;
    public final u c;
    public final q0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0886u f4033e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final k f4034f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f4035g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final X8.c f4036h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final X8.c f4037i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public W5.e f4038j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Z f4039k;
    public InterfaceC2772a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public InterfaceC2772a f4040m;

    public i(Context context, J5.c cVar, u uVar, q0 q0Var, C0886u c0886u, k kVar) {
        m.f(context, "appContext");
        m.f(cVar, "kcaService");
        m.f(uVar, "karaokeItemRepository");
        m.f(q0Var, "songQueue");
        m.f(c0886u, "playerProgressManager");
        m.f(kVar, "searchManager");
        this.f4031a = context;
        this.f4032b = cVar;
        this.c = uVar;
        this.d = q0Var;
        this.f4033e = c0886u;
        this.f4034f = kVar;
        Z8.e eVar = E.f8084a;
        this.f4036h = AbstractC0727y.a(Z8.d.f11010n);
        this.f4037i = AbstractC0727y.a(X8.m.f10573a);
    }

    public static final void a(i iVar, KaraokeItem karaokeItem, F6.b bVar) {
        iVar.getClass();
        String str = karaokeItem.f13107a;
        u uVar = iVar.c;
        if (uVar.d(str) == null) {
            uVar.a(karaokeItem);
        }
        AbstractC0727y.w(iVar.f4037i, null, null, new h(iVar, karaokeItem, bVar, null), 3);
    }

    public final W5.e b() {
        W5.e eVar = this.f4038j;
        if (eVar != null) {
            return eVar;
        }
        m.j("appNavigationManager");
        throw null;
    }

    public final InterfaceC2772a c() {
        InterfaceC2772a interfaceC2772a = this.f4040m;
        if (interfaceC2772a != null) {
            return interfaceC2772a;
        }
        m.j("dismissPlayer");
        throw null;
    }

    public final Z d() {
        Z z6 = this.f4039k;
        if (z6 != null) {
            return z6;
        }
        m.j("playerManager");
        throw null;
    }

    public final void e(Intent intent) {
        m.f(intent, "intent");
        if (intent.getData() == null || intent.getDataString() == null) {
            return;
        }
        String dataString = intent.getDataString();
        m.c(dataString);
        SourceType sourceType = SourceType.DEEP_LINK;
        String dataString2 = intent.getDataString();
        m.c(dataString2);
        f(dataString, new F6.b(sourceType, dataString2));
    }

    public final void f(String str, F6.b bVar) {
        Map linkedHashMap;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        m.f(str, "link");
        Log.d("DeepLinkManager", "=== Deeplink received: ".concat(str));
        List list = f4030n;
        if (list == null || !list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (o.z0(str, (String) it.next(), false)) {
                    URI uriCreate = URI.create(str);
                    m.e(uriCreate, "create(...)");
                    String query = uriCreate.getQuery();
                    if (query != null) {
                        List listV0 = Q8.h.V0(query, new String[]{"&"}, 0, 6);
                        int iQ = z.q(AbstractC2000o.J(listV0, 10));
                        if (iQ < 16) {
                            iQ = 16;
                        }
                        linkedHashMap = new LinkedHashMap(iQ);
                        Iterator it2 = listV0.iterator();
                        while (it2.hasNext()) {
                            List listV02 = Q8.h.V0((String) it2.next(), new String[]{"="}, 0, 6);
                            linkedHashMap.put((String) listV02.get(0), (String) listV02.get(1));
                        }
                    } else {
                        linkedHashMap = v.l;
                    }
                    String path = uriCreate.getPath();
                    m.e(path, "getPath(...)");
                    List listV03 = Q8.h.V0(path, new String[]{"/"}, 0, 6);
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : listV03) {
                        if (((String) obj).length() > 0) {
                            arrayList.add(obj);
                        }
                    }
                    String str9 = (String) AbstractC1998m.a0(arrayList);
                    if (str9 != null) {
                        try {
                            if (str9.equals(DeepLinkManager$DeepLinkType.CommandBack.getLinkName())) {
                                if (((Boolean) c().a()).booleanValue()) {
                                    return;
                                }
                                b().f();
                                return;
                            }
                            if (str9.equals(DeepLinkManager$DeepLinkType.CommandPlay.getLinkName())) {
                                g();
                                return;
                            }
                            if (str9.equals(DeepLinkManager$DeepLinkType.CommandPause.getLinkName())) {
                                d().s(ActionOwner.DEEPLINK);
                                return;
                            }
                            if (str9.equals(DeepLinkManager$DeepLinkType.CommandSkip.getLinkName())) {
                                d().E(false, ActionOwner.DEEPLINK);
                                return;
                            }
                            if (str9.equals(DeepLinkManager$DeepLinkType.CommandPrevious.getLinkName())) {
                                Z zD = d();
                                ActionOwner actionOwner = ActionOwner.DEEPLINK;
                                m.f(actionOwner, "actionOwner");
                                zD.d(new C0451g(PlayerActionType.SkipPrevious, new C0463t(zD, actionOwner, null, true)));
                                return;
                            }
                            if (str9.equals(DeepLinkManager$DeepLinkType.CommandReplay.getLinkName())) {
                                d().u(ActionOwner.DEEPLINK);
                                return;
                            }
                            if (str9.equals(DeepLinkManager$DeepLinkType.CommandLoopOne.getLinkName())) {
                                d().D(1, ActionOwner.DEEPLINK);
                                return;
                            }
                            if (str9.equals(DeepLinkManager$DeepLinkType.CommandLoopAll.getLinkName())) {
                                d().D(2, ActionOwner.DEEPLINK);
                                return;
                            }
                            if (str9.equals(DeepLinkManager$DeepLinkType.CommandLoopOff.getLinkName())) {
                                d().D(0, ActionOwner.DEEPLINK);
                                return;
                            }
                            if (str9.equals(DeepLinkManager$DeepLinkType.CommandLeadVocalOn.getLinkName())) {
                                Z zD2 = d();
                                ActionOwner actionOwner2 = ActionOwner.DEEPLINK;
                                m.f(actionOwner2, "actionOwner");
                                if (((Boolean) zD2.f4657f0.getValue()).booleanValue()) {
                                    return;
                                }
                                zD2.d(new C0451g(PlayerActionType.ToggleLeadVocal, new C0467x(zD2, actionOwner2, null), new C0452h(zD2, 3)));
                                return;
                            }
                            if (str9.equals(DeepLinkManager$DeepLinkType.CommandLeadVocalOff.getLinkName())) {
                                Z zD3 = d();
                                ActionOwner actionOwner3 = ActionOwner.DEEPLINK;
                                m.f(actionOwner3, "actionOwner");
                                if (((Boolean) zD3.f4657f0.getValue()).booleanValue()) {
                                    zD3.d(new C0451g(PlayerActionType.ToggleLeadVocal, new C0467x(zD3, actionOwner3, null), new C0452h(zD3, 3)));
                                    return;
                                }
                                return;
                            }
                            if (str9.equals(DeepLinkManager$DeepLinkType.CommandQuit.getLinkName())) {
                                InterfaceC2772a interfaceC2772a = this.l;
                                if (interfaceC2772a != null) {
                                    interfaceC2772a.a();
                                    return;
                                } else {
                                    m.j("closeActivity");
                                    throw null;
                                }
                            }
                            boolean zEquals = str9.equals(DeepLinkManager$DeepLinkType.CommandSearch.getLinkName());
                            k kVar = this.f4034f;
                            if (zEquals) {
                                String str10 = (String) linkedHashMap.get("query");
                                if (str10 != null) {
                                    kVar.b(0L, str10, true);
                                }
                                W5.e eVarB = b();
                                StingrayKaraokeTabScreen stingrayKaraokeTabScreen = StingrayKaraokeTabScreen.ExplorePage;
                                m.f(stingrayKaraokeTabScreen, "tab");
                                C1930E c1930e = (C1930E) eVarB.d.get(NavigationType.MAIN);
                                if (c1930e != null) {
                                    c1930e.b(new W5.d(eVarB, stingrayKaraokeTabScreen, false));
                                    return;
                                }
                                return;
                            }
                            if (str9.equals(DeepLinkManager$DeepLinkType.CommandSearchAndPlay.getLinkName())) {
                                String str11 = (String) linkedHashMap.get("query");
                                if (str11 != null) {
                                    kVar.b(0L, str11, true);
                                }
                                W5.e eVarB2 = b();
                                StingrayKaraokeTabScreen stingrayKaraokeTabScreen2 = StingrayKaraokeTabScreen.ExplorePage;
                                m.f(stingrayKaraokeTabScreen2, "tab");
                                C1930E c1930e2 = (C1930E) eVarB2.d.get(NavigationType.MAIN);
                                if (c1930e2 != null) {
                                    c1930e2.b(new W5.d(eVarB2, stingrayKaraokeTabScreen2, false));
                                    return;
                                }
                                return;
                            }
                            if (str9.equals(DeepLinkManager$DeepLinkType.CommandHistory.getLinkName())) {
                                c().a();
                                W5.e eVarB3 = b();
                                StingrayKaraokeTabScreen stingrayKaraokeTabScreen3 = StingrayKaraokeTabScreen.MyKaraokePage;
                                m.f(stingrayKaraokeTabScreen3, "tab");
                                C1930E c1930e3 = (C1930E) eVarB3.d.get(NavigationType.MAIN);
                                if (c1930e3 != null) {
                                    c1930e3.b(new W5.d(eVarB3, stingrayKaraokeTabScreen3, false));
                                    return;
                                }
                                return;
                            }
                            if (str9.equals(DeepLinkManager$DeepLinkType.SongDetails.getLinkName())) {
                                c().a();
                                String str12 = (String) AbstractC1998m.b0(1, arrayList);
                                if (str12 == null || (str8 = (String) AbstractC1998m.h0(Q8.h.V0(str12, new String[]{":"}, 0, 6))) == null) {
                                    return;
                                }
                                b().g(NavigationRoutes.SONG_DETAILS, str8, bVar);
                                return;
                            }
                            boolean zEquals2 = str9.equals(DeepLinkManager$DeepLinkType.ArtistSongs.getLinkName());
                            X8.c cVar = this.f4036h;
                            if (zEquals2) {
                                c().a();
                                String str13 = (String) AbstractC1998m.b0(1, arrayList);
                                if (str13 == null || (str7 = (String) AbstractC1998m.h0(Q8.h.V0(str13, new String[]{":"}, 0, 6))) == null) {
                                    return;
                                }
                                AbstractC0727y.w(cVar, null, null, new a(this, str7, bVar, null), 3);
                                return;
                            }
                            if (str9.equals(DeepLinkManager$DeepLinkType.PartyMix.getLinkName())) {
                                c().a();
                                String str14 = (String) AbstractC1998m.b0(1, arrayList);
                                if (str14 == null || (str6 = (String) AbstractC1998m.h0(Q8.h.V0(str14, new String[]{":"}, 0, 6))) == null) {
                                    return;
                                }
                                AbstractC0727y.w(cVar, null, null, new b(this, str6, bVar, null), 3);
                                return;
                            }
                            if (str9.equals(DeepLinkManager$DeepLinkType.SongsGenre.getLinkName())) {
                                c().a();
                                String str15 = (String) AbstractC1998m.b0(1, arrayList);
                                if (str15 == null || (str5 = (String) AbstractC1998m.h0(Q8.h.V0(str15, new String[]{":"}, 0, 6))) == null) {
                                    return;
                                }
                                AbstractC0727y.w(cVar, null, null, new c(this, str5, bVar, null), 3);
                                return;
                            }
                            if (str9.equals(DeepLinkManager$DeepLinkType.ArtistsGenre.getLinkName())) {
                                c().a();
                                String str16 = (String) AbstractC1998m.b0(1, arrayList);
                                if (str16 == null || (str4 = (String) AbstractC1998m.h0(Q8.h.V0(str16, new String[]{":"}, 0, 6))) == null) {
                                    return;
                                }
                                AbstractC0727y.w(cVar, null, null, new d(this, str4, bVar, null), 3);
                                return;
                            }
                            if (str9.equals(DeepLinkManager$DeepLinkType.Era.getLinkName())) {
                                c().a();
                                String str17 = (String) AbstractC1998m.b0(1, arrayList);
                                if (str17 == null || (str3 = (String) AbstractC1998m.h0(Q8.h.V0(str17, new String[]{":"}, 0, 6))) == null) {
                                    return;
                                }
                                AbstractC0727y.w(cVar, null, null, new e(this, str3, bVar, null), 3);
                                return;
                            }
                            if (str9.equals(DeepLinkManager$DeepLinkType.Language.getLinkName())) {
                                c().a();
                                String str18 = (String) AbstractC1998m.b0(1, arrayList);
                                if (str18 == null || (str2 = (String) AbstractC1998m.h0(Q8.h.V0(str18, new String[]{":"}, 0, 6))) == null) {
                                    return;
                                }
                                AbstractC0727y.w(cVar, null, null, new f(this, str2, bVar, null), 3);
                                return;
                            }
                            return;
                        } catch (Exception e10) {
                            B9.a aVar = Timber.f22577a;
                            aVar.e("");
                            aVar.a(e10);
                            return;
                        }
                    }
                    return;
                }
            }
        }
        if (o.z0(str, "http", false)) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                intent.setFlags(268435456);
                this.f4031a.startActivity(intent);
            } catch (Exception e11) {
                B9.a aVar2 = Timber.f22577a;
                aVar2.e("");
                aVar2.a(e11);
            }
        }
    }

    public final void g() {
        if (!((Boolean) ((f0) d().f4639R.l).getValue()).booleanValue()) {
            d().t(ActionOwner.DEEPLINK);
            return;
        }
        if (((f0) d().f4635N.l).getValue() != PlayerStatus.PLAYING && ((Boolean) ((f0) d().f4647Z.l).getValue()).booleanValue()) {
            if (!((Boolean) ((f0) this.d.f4755y.l).getValue()).booleanValue()) {
                this.f4033e.b();
                Z zD = d();
                ActionOwner actionOwner = ActionOwner.DEEPLINK;
                m.f(actionOwner, "actionOwner");
                zD.q();
                zD.E(false, actionOwner);
                return;
            }
            Z zD2 = d();
            ActionOwner actionOwner2 = ActionOwner.DEEPLINK;
            m.f(actionOwner2, "actionOwner");
            q0 q0Var = zD2.c;
            if (((List) ((f0) q0Var.f4752v.l).getValue()).size() == 1) {
                zD2.u(actionOwner2);
            } else {
                zD2.q();
                q0Var.e(0);
            }
        }
    }
}
