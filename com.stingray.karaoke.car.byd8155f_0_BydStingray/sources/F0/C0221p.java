package F0;

import S8.AbstractC0727y;
import a6.C0928a;
import android.os.Build;
import android.view.View;
import android.view.contentcapture.ContentCaptureSession;
import androidx.compose.ui.platform.AndroidComposeView;
import com.stingray.drivemodedetector.DriveMode;
import com.stingray.karaoke.car.MainActivity;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.karaoke.car.player.SongQueuePosition;
import com.stingray.karaoke.car.ui.navigation.NavigationRoutes;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import com.stingray.kca.model.AssetTypeV2;
import com.stingray.shared.logs.segment.ActionOwner;
import g6.C1306d;
import h6.C1382a;
import i6.C1415d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import k6.C1696d;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: F0.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0221p extends A7.j implements InterfaceC2772a {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ int f2247t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0221p(int i7, Object obj, Class cls, String str, String str2, int i10, int i11) {
        super(i7, i10, cls, obj, str, str2);
        this.f2247t = i11;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f2247t) {
            case 0:
                View view = (View) this.f455m;
                if (Build.VERSION.SDK_INT >= 30) {
                    I0.g.a(view, 1);
                }
                ContentCaptureSession contentCaptureSessionA = I0.f.a(view);
                if (contentCaptureSessionA == null) {
                    return null;
                }
                return new I0.d(contentCaptureSessionA, view);
            case 1:
                AndroidComposeView androidComposeView = (AndroidComposeView) this.f455m;
                Class cls = AndroidComposeView.f11616K0;
                if (androidComposeView.isFocused() || androidComposeView.hasFocus()) {
                    androidComposeView.clearFocus();
                }
                return m7.q.f19267a;
            case 2:
                AndroidComposeView androidComposeView2 = (AndroidComposeView) this.f455m;
                Class cls2 = AndroidComposeView.f11616K0;
                return androidComposeView2.t();
            case 3:
                f6.h hVar = (f6.h) this.f455m;
                hVar.getClass();
                hVar.l.g(NavigationRoutes.DEV_SETTINGS, null, null);
                return m7.q.f19267a;
            case 4:
                f6.h hVar2 = (f6.h) this.f455m;
                hVar2.getClass();
                hVar2.l.g(NavigationRoutes.DEV_SETTINGS, null, null);
                return m7.q.f19267a;
            case 5:
                MainActivity mainActivity = (MainActivity) this.f455m;
                int i7 = MainActivity.f12974j0;
                mainActivity.q().h();
                return m7.q.f19267a;
            case 6:
                ((C1382a) this.f455m).getClass();
                return m7.q.f19267a;
            case 7:
                ((C0928a) this.f455m).s();
                return m7.q.f19267a;
            case 8:
                f6.o oVar = (f6.o) this.f455m;
                oVar.getClass();
                oVar.f14636v.g(NavigationRoutes.SETTINGS, null, null);
                return m7.q.f19267a;
            case 9:
                f6.o oVar2 = (f6.o) this.f455m;
                oVar2.getClass();
                oVar2.f12228t.g(NavigationRoutes.FAQ, null, null);
                return m7.q.f19267a;
            case 10:
                f6.o oVar3 = (f6.o) this.f455m;
                oVar3.getClass();
                oVar3.f12228t.g(NavigationRoutes.CONTACT_US, null, null);
                return m7.q.f19267a;
            case 11:
                f6.o oVar4 = (f6.o) this.f455m;
                oVar4.getClass();
                oVar4.f12228t.g(NavigationRoutes.ABOUT, null, null);
                return m7.q.f19267a;
            case 12:
                C1415d c1415d = (C1415d) this.f455m;
                String str = c1415d.f12250s;
                if (str != null) {
                    int iHashCode = str.hashCode();
                    if (iHashCode != 935580174) {
                        if (iHashCode != 1169542268) {
                            if (iHashCode == 1770444560 && str.equals("{\"asset_group\":\"TopScores\"}")) {
                                j6.n nVar = c1415d.f15761I;
                                synchronized (nVar) {
                                    AbstractC0727y.w(nVar.f17058b, null, null, new j6.h(nVar, null), 3);
                                }
                            }
                        } else if (str.equals("{\"asset_group\":\"FavoriteSongs\"}")) {
                            j6.n nVar2 = c1415d.f15761I;
                            AssetTypeV2 assetTypeV2 = AssetTypeV2.SONG;
                            synchronized (nVar2) {
                                A7.m.f(assetTypeV2, "assetType");
                                AbstractC0727y.w(nVar2.f17058b, null, null, new j6.e(nVar2, assetTypeV2, null), 3);
                            }
                        }
                    } else if (str.equals("{\"asset_group\":\"History\"}")) {
                        j6.n nVar3 = c1415d.f15761I;
                        synchronized (nVar3) {
                            AbstractC0727y.w(nVar3.f17058b, null, null, new j6.f(nVar3, null), 3);
                        }
                    }
                }
                return m7.q.f19267a;
            case 13:
                ((C1415d) this.f455m).h();
                return m7.q.f19267a;
            case 14:
                f6.p pVar = (f6.p) this.f455m;
                pVar.getClass();
                pVar.f12228t.g(NavigationRoutes.FAQ, null, null);
                return m7.q.f19267a;
            case 15:
                f6.p pVar2 = (f6.p) this.f455m;
                pVar2.getClass();
                pVar2.f12228t.g(NavigationRoutes.CONTACT_US, null, null);
                return m7.q.f19267a;
            case 16:
                f6.p pVar3 = (f6.p) this.f455m;
                pVar3.getClass();
                pVar3.f12228t.g(NavigationRoutes.ABOUT, null, null);
                return m7.q.f19267a;
            case 17:
                d6.j jVar = (d6.j) this.f455m;
                String str2 = jVar.f12250s;
                if (str2 != null) {
                    AbstractC0727y.w(jVar.f12236f, null, null, new d6.i(jVar, str2, null), 3);
                } else {
                    jVar.B.setValue(LoadingInterface$LoadingState.Error);
                }
                return m7.q.f19267a;
            case 18:
                d6.j jVar2 = (d6.j) this.f455m;
                jVar2.getClass();
                jVar2.f13481t.i(NavigationRoutes.SONG_DETAILS.getValue());
                return m7.q.f19267a;
            case 19:
                InterfaceC2772a interfaceC2772a = ((d6.j) this.f455m).f12239i;
                if (interfaceC2772a != null) {
                    interfaceC2772a.a();
                }
                return m7.q.f19267a;
            case 20:
                ((d6.j) this.f455m).h();
                return m7.q.f19267a;
            case 21:
                ((X5.H0) this.f455m).f10174m.f(ActionOwner.HOST);
                return m7.q.f19267a;
            case 22:
                ((C1415d) this.f455m).s();
                return m7.q.f19267a;
            case 23:
                h6.d dVar = (h6.d) this.f455m;
                C1696d c1696d = dVar.l;
                V8.f0 f0Var = c1696d.f18254k;
                V8.f0 f0Var2 = dVar.f15569m;
                Boolean boolValueOf = Boolean.valueOf(true ^ ((Boolean) f0Var2.getValue()).booleanValue());
                f0Var.getClass();
                f0Var.m(null, boolValueOf);
                if (!((Boolean) f0Var2.getValue()).booleanValue()) {
                    c1696d.l.l(null);
                }
                return m7.q.f19267a;
            case 24:
                C1306d c1306d = (C1306d) this.f455m;
                List list = (List) ((V8.f0) c1306d.f14987n.l).getValue();
                if (list != null) {
                    ArrayList arrayList = new ArrayList();
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        String str3 = ((N5.f0) it.next()).f4691a.f13136a;
                        if (str3 != null) {
                            arrayList.add(str3);
                        }
                    }
                    c1306d.l.a(arrayList, SongQueuePosition.SING_NOW, null, null, false, null);
                }
                return m7.q.f19267a;
            case 25:
                ((C1306d) this.f455m).getClass();
                return m7.q.f19267a;
            case 26:
                X5.D0 d02 = (X5.D0) this.f455m;
                if (((V8.f0) d02.f10123S.l).getValue() == DriveMode.PARKED) {
                    d02.f10102A0.m(null, Boolean.valueOf(!((Boolean) r8.getValue()).booleanValue()));
                }
                return m7.q.f19267a;
            case 27:
                X5.D0 d03 = (X5.D0) this.f455m;
                V8.f0 f0Var3 = d03.f10113I;
                f0Var3.m(null, Boolean.valueOf(!((Boolean) f0Var3.getValue()).booleanValue()));
                boolean z6 = ((V8.f0) d03.f10158x.f4635N.l).getValue() == PlayerStatus.PLAYING;
                boolean zBooleanValue = ((Boolean) f0Var3.getValue()).booleanValue();
                n6.u uVar = d03.f10101A;
                if (zBooleanValue && z6 && ((Boolean) ((V8.f0) d03.f10149s0.l).getValue()).booleanValue()) {
                    uVar.b();
                } else {
                    uVar.c();
                }
                d03.t();
                return m7.q.f19267a;
            case 28:
                X5.D0 d04 = (X5.D0) this.f455m;
                d04.f10160y.b();
                ActionOwner actionOwner = ActionOwner.HOST;
                N5.Z z10 = d04.f10158x;
                z10.getClass();
                A7.m.f(actionOwner, "actionOwner");
                z10.q();
                z10.E(false, actionOwner);
                return m7.q.f19267a;
            default:
                X5.D0 d05 = (X5.D0) this.f455m;
                d05.f10158x.u(ActionOwner.HOST);
                d05.q();
                d05.B.c.a("Retry Scoring", "RETRY_SCORING");
                return m7.q.f19267a;
        }
    }
}
