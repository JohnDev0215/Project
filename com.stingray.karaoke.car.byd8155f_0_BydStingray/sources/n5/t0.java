package N5;

import Q5.C0618s1;
import S8.AbstractC0727y;
import com.stingray.companion.CompanionManager;
import com.stingray.companion.CompanionReceiveListener;
import com.stingray.drivemodedetector.DriveMode;
import com.stingray.karaoke.car.utils.ToastType;
import com.stingray.kca.model.KaraokeSongResponseV2;
import com.stingray.shared.logs.segment.ActionOwner;
import java.util.List;
import k6.C1704l;
import n7.AbstractC1996k;
import n7.AbstractC1998m;

/* JADX INFO: loaded from: classes.dex */
public final class t0 implements CompanionReceiveListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q0 f4770a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Z f4771b;
    public final CompanionManager c;
    public final K5.F d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final X8.c f4772e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final V8.N f4773f;

    public t0(q0 q0Var, Z z6, CompanionManager companionManager, K5.F f10, W5.e eVar, C1704l c1704l) {
        A7.m.f(q0Var, "songQueueManager");
        A7.m.f(z6, "playerManager");
        A7.m.f(companionManager, "companionManager");
        A7.m.f(f10, "songRepository");
        A7.m.f(eVar, "appNavigationManager");
        A7.m.f(c1704l, "driveModeManager");
        this.f4770a = q0Var;
        this.f4771b = z6;
        this.c = companionManager;
        this.d = f10;
        Z8.e eVar2 = S8.E.f8084a;
        this.f4772e = AbstractC0727y.a(X8.m.f10573a);
        this.f4773f = c1704l.d;
    }

    public static String a(String str) {
        A7.m.f(str, "<this>");
        int iP0 = Q8.h.P0(str, 6, ":");
        if (iP0 == -1) {
            return str;
        }
        String strSubstring = str.substring(1 + iP0, str.length());
        A7.m.e(strSubstring, "substring(...)");
        return strSubstring;
    }

    @Override // com.stingray.companion.CompanionReceiveListener
    public final void onAddSongs(String[] strArr, String str, String str2, int i7, boolean z6, boolean z10, boolean z11, boolean z12, Integer num) {
        A7.m.f(strArr, "songIds");
        int length = strArr.length;
        X8.c cVar = this.f4772e;
        if (length == 1) {
            AbstractC0727y.w(cVar, null, null, new r0(this, z12, strArr, z6, i7, z10, z11, null), 3);
            return;
        }
        if (z6) {
            AbstractC0727y.w(cVar, null, null, new s0(this, null), 3);
        }
        this.f4770a.b(AbstractC1996k.b0(strArr), str, str2, i7, z6, z10, ActionOwner.COMPANION, num, null);
    }

    @Override // com.stingray.companion.CompanionReceiveListener
    public final void onClearQueue() {
        this.f4770a.f(ActionOwner.COMPANION);
    }

    @Override // com.stingray.companion.CompanionReceiveListener
    public final void onCommandReceived(String str) {
        A7.m.f(str, "info");
    }

    @Override // com.stingray.companion.CompanionReceiveListener
    public final void onConnectionChanged() {
        this.f4770a.k();
    }

    @Override // com.stingray.companion.CompanionReceiveListener
    public final void onMoveSong(String str, int i7, int i10) {
        KaraokeSongResponseV2 karaokeSongResponseV2;
        A7.m.f(str, "songId");
        q0 q0Var = this.f4770a;
        f0 f0Var = (f0) AbstractC1998m.b0(i7, (List) q0Var.f4752v.getValue());
        if (!A7.m.a((f0Var == null || (karaokeSongResponseV2 = f0Var.f4691a) == null) ? null : karaokeSongResponseV2.f13136a, a(str))) {
            q0Var.k();
        } else {
            if (q0Var.h(f0Var.d, i10, ActionOwner.COMPANION)) {
                return;
            }
            q0Var.k();
        }
    }

    @Override // com.stingray.companion.CompanionReceiveListener
    public final void onRemoveSong(String str, int i7) {
        KaraokeSongResponseV2 karaokeSongResponseV2;
        A7.m.f(str, "songId");
        q0 q0Var = this.f4770a;
        f0 f0Var = (f0) AbstractC1998m.b0(i7, (List) q0Var.f4752v.getValue());
        if (!A7.m.a((f0Var == null || (karaokeSongResponseV2 = f0Var.f4691a) == null) ? null : karaokeSongResponseV2.f13136a, a(str))) {
            q0Var.k();
        } else if (q0Var.i(f0Var.d, ActionOwner.COMPANION)) {
            q0Var.k();
        }
    }

    @Override // com.stingray.companion.CompanionReceiveListener
    public final void onSeek(long j3) {
    }

    @Override // com.stingray.companion.CompanionReceiveListener
    public final void onSelectSong(String str, int i7, boolean z6) {
        KaraokeSongResponseV2 karaokeSongResponseV2;
        q0 q0Var = this.f4770a;
        A7.m.f(str, "songId");
        try {
            if (((V8.f0) this.f4773f.l).getValue() == DriveMode.DRIVING && z6) {
                ToastType toastType = ToastType.DRIVING_DISABLED_ERROR;
                A7.m.f(toastType, "toastType");
                h6.e.c(h6.e.d(toastType), new b0.b(-1064300120, new C0618s1(toastType, 2), true));
                return;
            }
            f0 f0Var = (f0) AbstractC1998m.b0(i7, (List) q0Var.f4752v.getValue());
            if (A7.m.a((f0Var == null || (karaokeSongResponseV2 = f0Var.f4691a) == null) ? null : karaokeSongResponseV2.f13136a, a(str))) {
                String str2 = f0Var.d;
                ActionOwner actionOwner = ActionOwner.COMPANION;
                q0Var.l(str2, true, actionOwner);
                Z.y(this.f4771b, z6, true, actionOwner, true, false, 16);
            }
        } catch (Exception unused) {
        }
    }
}
