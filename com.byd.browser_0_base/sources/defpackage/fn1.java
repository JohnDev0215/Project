package defpackage;

import android.app.Activity;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.TagLostException;
import android.os.Process;
import android.os.Vibrator;
import android.util.Log;
import defpackage.an1;
import defpackage.nn1;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.chromium.base.Callback;
import org.chromium.device.nfc.NfcDelegate;

/* JADX INFO: compiled from: NfcImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class fn1 implements an1 {
    public final int e;
    public final NfcDelegate f;
    public xc2 g;
    public final NfcManager h;
    public final NfcAdapter i;
    public Activity j;
    public final boolean k;
    public d m;
    public c n;
    public b o;
    public mn1 p;
    public dn1 q;
    public final Vibrator s;
    public final ArrayList r = new ArrayList();
    public boolean l = false;

    /* JADX INFO: compiled from: NfcImpl.java */
    public class a implements Callback<Activity> {
        public a() {
        }

        @Override // org.chromium.base.Callback
        public final void onResult(Activity activity) {
            fn1 fn1Var = fn1.this;
            fn1Var.F();
            fn1Var.j = activity;
            fn1Var.v0();
        }
    }

    /* JADX INFO: compiled from: NfcImpl.java */
    public static class b {
        public final an1.a a;

        public b(nn1.h hVar) {
            this.a = hVar;
        }
    }

    /* JADX INFO: compiled from: NfcImpl.java */
    public static class c {
        public final ml1 a;
        public final pl1 b;
        public final an1.b c;

        public c(ml1 ml1Var, pl1 pl1Var, nn1.l lVar) {
            this.a = ml1Var;
            this.b = pl1Var;
            this.c = lVar;
        }
    }

    /* JADX INFO: compiled from: NfcImpl.java */
    public static class d implements NfcAdapter.ReaderCallback {
        public final fn1 a;

        public d(fn1 fn1Var) {
            this.a = fn1Var;
        }

        /* JADX WARN: Removed duplicated region for block: B:35:0x0091  */
        @Override // android.nfc.NfcAdapter.ReaderCallback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void onTagDiscovered(android.nfc.Tag r9) {
            /*
                Method dump skipped, instruction units count: 232
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: fn1.d.onTagDiscovered(android.nfc.Tag):void");
        }
    }

    public fn1(int i, NfcDelegate nfcDelegate, f21<an1> f21Var) {
        this.e = i;
        this.f = nfcDelegate;
        if (f21Var != null) {
            ch1 ch1VarF0 = f21Var.f0();
            xc2 xc2Var = new xc2(ch1VarF0);
            k00 k00VarW0 = ch1VarF0.w0();
            xc2Var.e.i = this;
            xc2Var.f = new nn1.s(k00VarW0, this);
            xc2Var.q();
            this.g = xc2Var;
        }
        boolean z = xz.a.checkPermission("android.permission.NFC", Process.myPid(), Process.myUid()) == 0;
        this.k = z;
        nfcDelegate.b(new a(), i);
        if (z) {
            NfcManager nfcManager = (NfcManager) xz.a.getSystemService("nfc");
            this.h = nfcManager;
            if (nfcManager == null) {
                Log.w("cr_NfcImpl", "NFC is not supported.");
                this.i = null;
            } else {
                this.i = nfcManager.getDefaultAdapter();
            }
        } else {
            Log.w("cr_NfcImpl", "NFC operations are not permitted.");
            this.i = null;
            this.h = null;
        }
        this.s = (Vibrator) xz.a.getSystemService("vibrator");
    }

    public static ll1 C(int i, String str) {
        ll1 ll1Var = new ll1();
        ll1Var.b = i;
        ll1Var.c = str;
        return ll1Var;
    }

    public final void F() {
        NfcAdapter nfcAdapter;
        if (this.m == null) {
            return;
        }
        this.m = null;
        Activity activity = this.j;
        if (activity == null || (nfcAdapter = this.i) == null || activity.isDestroyed()) {
            return;
        }
        nfcAdapter.disableReaderMode(this.j);
    }

    @Override // defpackage.an1
    public final void H() {
        ll1 ll1VarC = C(4, "The make read-only operation is cancelled.");
        b bVar = this.o;
        if (bVar == null) {
            return;
        }
        an1.a aVar = bVar.a;
        if (aVar != null) {
            ((nn1.h) aVar).c(ll1VarC);
        }
        this.o = null;
        R();
    }

    public final void H0(ll1 ll1Var) {
        if (this.r.size() != 0) {
            this.q.q0(ll1Var);
        }
    }

    public final void I0(ml1 ml1Var) {
        ArrayList arrayList = this.r;
        if (arrayList.size() != 0) {
            int[] iArr = new int[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++) {
                iArr[i] = ((Integer) arrayList.get(i)).intValue();
            }
            this.q.e0(iArr, this.p.d, ml1Var);
        }
    }

    @Override // defpackage.an1
    public final void N0(int i, nn1.q qVar) {
        if (t(qVar)) {
            ArrayList arrayList = this.r;
            if (arrayList.contains(Integer.valueOf(i))) {
                qVar.c(C(2, "Cannot start because the received scan request is duplicate."));
                return;
            }
            arrayList.add(Integer.valueOf(i));
            qVar.c(null);
            v0();
            b1();
        }
    }

    @Override // defpackage.an1
    public final void Q(dn1 dn1Var) {
        this.q = dn1Var;
    }

    public final void Q0(ll1 ll1Var) {
        c cVar = this.n;
        if (cVar != null) {
            an1.b bVar = cVar.c;
            if (bVar != null) {
                ((nn1.l) bVar).c(ll1Var);
            }
            this.n = null;
            R();
        }
        if (ll1Var != null) {
            this.p = null;
        }
    }

    public final void R() {
        if (this.n == null && this.o == null && this.r.size() == 0) {
            F();
        }
    }

    public final void T0() {
        boolean z;
        mn1 mn1Var = this.p;
        if (mn1Var == null || this.o == null) {
            return;
        }
        try {
            mn1Var.a();
            z = false;
        } catch (IOException unused) {
            z = mn1Var.c;
        }
        if (z) {
            this.p = null;
            return;
        }
        try {
            this.p.a();
            if (this.p.b.b()) {
                b bVar = this.o;
                if (bVar == null) {
                    return;
                }
                an1.a aVar = bVar.a;
                if (aVar != null) {
                    ((nn1.h) aVar).c(null);
                }
                this.o = null;
                R();
                return;
            }
            Log.w("cr_NfcImpl", "Cannot make NFC tag read-only. The tag cannot be made read-only");
            ll1 ll1VarC = C(1, "Failed to make read-only because the tag cannot be made read-only");
            b bVar2 = this.o;
            if (bVar2 != null) {
                an1.a aVar2 = bVar2.a;
                if (aVar2 != null) {
                    ((nn1.h) aVar2).c(ll1VarC);
                }
                this.o = null;
                R();
            }
            this.p = null;
        } catch (TagLostException e) {
            Log.w("cr_NfcImpl", "Cannot make NFC tag read-only. Tag is lost: " + e.getMessage());
            ll1 ll1VarC2 = C(5, "Failed to make read-only because the tag is lost: " + e.getMessage());
            b bVar3 = this.o;
            if (bVar3 != null) {
                an1.a aVar3 = bVar3.a;
                if (aVar3 != null) {
                    ((nn1.h) aVar3).c(ll1VarC2);
                }
                this.o = null;
                R();
            }
            this.p = null;
        } catch (IOException e2) {
            Log.w("cr_NfcImpl", "Cannot make NFC tag read-only: " + e2.getMessage());
            ll1 ll1VarC3 = C(5, "Failed to make read-only due to an IO error: " + e2.getMessage());
            b bVar4 = this.o;
            if (bVar4 != null) {
                an1.a aVar4 = bVar4.a;
                if (aVar4 != null) {
                    ((nn1.h) aVar4).c(ll1VarC3);
                }
                this.o = null;
                R();
            }
            this.p = null;
        }
    }

    @Override // defpackage.an1
    public final void U(int i) {
        ArrayList arrayList = this.r;
        if (arrayList.contains(Integer.valueOf(i))) {
            arrayList.remove(arrayList.indexOf(Integer.valueOf(i)));
            R();
        }
    }

    public final void U0() {
        boolean z;
        mn1 mn1Var = this.p;
        if (mn1Var == null || this.n == null) {
            return;
        }
        try {
            mn1Var.a();
            z = false;
        } catch (IOException unused) {
            z = mn1Var.c;
        }
        if (z) {
            this.p = null;
            return;
        }
        try {
            this.p.a();
            if (!this.n.b.b && !this.p.b.c()) {
                Log.w("cr_NfcImpl", "Cannot overwrite the NFC tag due to existing data on it.");
                Q0(C(0, "NDEFWriteOptions#overwrite does not allow overwrite."));
            } else {
                this.p.b.a(nl1.e(this.n.a));
                Q0(null);
            }
        } catch (FormatException | IOException | IllegalStateException e) {
            Log.w("cr_NfcImpl", "Cannot write data to NFC tag: " + e.getMessage());
            Q0(C(5, "Failed to write due to an IO error: " + e.getMessage()));
        } catch (TagLostException e2) {
            Log.w("cr_NfcImpl", "Cannot write data to NFC tag. Tag is lost: " + e2.getMessage());
            Q0(C(5, "Failed to write because the tag is lost: " + e2.getMessage()));
        } catch (m21 unused2) {
            Log.w("cr_NfcImpl", "Cannot write data to NFC tag. Invalid NdefMessage.");
            Q0(C(3, "Cannot push the message because it's invalid."));
        }
    }

    @Override // defpackage.an1
    public final void Y() {
        ll1 ll1VarC = C(4, "The push operation is cancelled.");
        c cVar = this.n;
        if (cVar == null) {
            return;
        }
        an1.b bVar = cVar.c;
        if (bVar != null) {
            ((nn1.l) bVar).c(ll1VarC);
        }
        this.n = null;
        R();
    }

    @Override // defpackage.an1
    public final void Y0(ml1 ml1Var, pl1 pl1Var, nn1.l lVar) {
        ol1[] ol1VarArr;
        String str;
        if (t(lVar)) {
            if (this.l) {
                lVar.c(C(4, "Cannot push the message because NFC operations are suspended."));
            }
            boolean z = false;
            if (ml1Var != null && (ol1VarArr = ml1Var.b) != null && ol1VarArr.length != 0) {
                int i = 0;
                while (true) {
                    ol1[] ol1VarArr2 = ml1Var.b;
                    boolean z2 = true;
                    if (i >= ol1VarArr2.length) {
                        z = true;
                        break;
                    }
                    ol1 ol1Var = ol1VarArr2[i];
                    if (ol1Var == null || (!ol1Var.c.equals("empty") && (ol1Var.h == null || (!ol1Var.c.equals("mime") ? ol1Var.d != null : !((str = ol1Var.d) != null && !str.isEmpty()))))) {
                        z2 = false;
                    }
                    if (!z2) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
            if (!z) {
                lVar.c(C(3, "Cannot push the message because it's invalid."));
                return;
            }
            c cVar = this.n;
            if (cVar != null) {
                ll1 ll1VarC = C(4, "Push is cancelled due to a new push request.");
                an1.b bVar = cVar.c;
                if (bVar != null) {
                    ((nn1.l) bVar).c(ll1VarC);
                }
            }
            this.n = new c(ml1Var, pl1Var, lVar);
            v0();
            U0();
        }
    }

    public final void b1() {
        boolean z;
        if (this.p == null || this.q == null || this.r.size() == 0 || this.l) {
            return;
        }
        mn1 mn1Var = this.p;
        mn1Var.getClass();
        try {
            mn1Var.a();
            z = false;
        } catch (IOException unused) {
            z = mn1Var.c;
        }
        if (z) {
            this.p = null;
            return;
        }
        try {
            this.p.a();
            NdefMessage ndefMessage = this.p.b.read();
            if (ndefMessage != null) {
                I0(nl1.d(ndefMessage));
                return;
            }
            ml1 ml1Var = new ml1(0);
            ml1Var.b = new ol1[0];
            I0(ml1Var);
        } catch (FormatException | IOException | IllegalStateException e) {
            Log.w("cr_NfcImpl", "Cannot read data from NFC tag. IO_ERROR: " + e.getMessage());
            H0(C(5, "Failed to read due to an IO error: " + e.getMessage()));
        } catch (TagLostException e2) {
            Log.w("cr_NfcImpl", "Cannot read data from NFC tag. Tag is lost: " + e2.getMessage());
            H0(C(5, "Failed to read because the tag is lost: " + e2.getMessage()));
        } catch (UnsupportedEncodingException e3) {
            Log.w("cr_NfcImpl", "Cannot read data from NFC tag. Cannot convert to NdefMessage:" + e3.getMessage());
            H0(C(3, "Failed to decode the NdefMessage read from the tag: " + e3.getMessage()));
        }
    }

    @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f.a(this.e);
        F();
    }

    @Override // defpackage.an1
    public final void j(nn1.h hVar) {
        if (t(hVar)) {
            if (this.l) {
                hVar.c(C(4, "Cannot make read-only because NFC operations are suspended."));
            }
            b bVar = this.o;
            if (bVar != null) {
                ll1 ll1VarC = C(4, "Make read-only is cancelled due to a new make read-only request.");
                an1.a aVar = bVar.a;
                if (aVar != null) {
                    ((nn1.h) aVar).c(ll1VarC);
                }
            }
            this.o = new b(hVar);
            v0();
            T0();
        }
    }

    public final boolean t(sl<ll1> slVar) {
        NfcAdapter nfcAdapter;
        ll1 ll1VarC = (!this.k || this.j == null) ? C(0, "The operation is not allowed.") : (this.h == null || (nfcAdapter = this.i) == null) ? C(1, "NFC is not supported.") : !nfcAdapter.isEnabled() ? C(2, "NFC setting is disabled.") : null;
        if (ll1VarC == null) {
            return true;
        }
        slVar.c(ll1VarC);
        return false;
    }

    public final void v0() {
        NfcAdapter nfcAdapter;
        if (this.m != null || this.j == null || (nfcAdapter = this.i) == null) {
            return;
        }
        if (this.n == null && this.o == null && this.r.size() == 0) {
            return;
        }
        d dVar = new d(this);
        this.m = dVar;
        nfcAdapter.enableReaderMode(this.j, dVar, 271, null);
    }

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
    }
}
