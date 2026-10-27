package defpackage;

import android.app.Activity;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.TagLostException;
import android.os.Process;
import android.os.Vibrator;
import android.util.SparseArray;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Objects;
import org.chromium.device.nfc.NfcDelegate;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class HG implements InterfaceC1757xF {
    public FG A;
    public NG B;
    public InterfaceC1812yF C;
    public final SparseArray D = new SparseArray();
    public Vibrator E;
    public final int s;
    public final NfcDelegate t;
    public InterfaceC1212nJ u;
    public final NfcManager v;
    public final NfcAdapter w;
    public Activity x;
    public final boolean y;
    public GG z;

    public HG(int i, NfcDelegate nfcDelegate, C0934iJ c0934iJ) {
        this.s = i;
        this.t = nfcDelegate;
        if (c0934iJ != null) {
            this.u = AbstractC1098lG.a.b(this, c0934iJ);
        }
        this.y = Is.a.checkPermission("android.permission.NFC", Process.myPid(), Process.myUid()) == 0;
        this.t.b(this.s, new EG(this));
        if (this.y) {
            NfcManager nfcManager = (NfcManager) Is.a.getSystemService("nfc");
            this.v = nfcManager;
            if (nfcManager == null) {
                Ts.f("NfcImpl", "NFC is not supported.", new Object[0]);
                this.w = null;
            } else {
                this.w = nfcManager.getDefaultAdapter();
            }
        } else {
            Ts.f("NfcImpl", "NFC operations are not permitted.", new Object[0]);
            this.w = null;
            this.v = null;
        }
        this.E = (Vibrator) Is.a.getSystemService("vibrator");
    }

    public final boolean A(KI ki) {
        C1208nF c1208nFX = x();
        if (c1208nFX == null) {
            return true;
        }
        ki.a(c1208nFX);
        return false;
    }

    @Override // defpackage.InterfaceC1757xF
    public void D(InterfaceC1482sF interfaceC1482sF) {
        if (A(interfaceC1482sF)) {
            if (this.D.size() == 0) {
                ((RF) interfaceC1482sF).a(M(3, "No pending scan operation to cancel."));
            } else {
                this.D.clear();
                ((RF) interfaceC1482sF).a(null);
                Q();
            }
        }
    }

    public final void G(C1208nF c1208nF) {
        FG fg = this.A;
        if (fg == null) {
            return;
        }
        InterfaceC1647vF interfaceC1647vF = fg.c;
        if (interfaceC1647vF != null) {
            ((C0652dG) interfaceC1647vF).a(c1208nF);
        }
        this.A = null;
        Q();
    }

    @Override // defpackage.LI
    public void I(WJ wj) {
    }

    public final C1208nF M(int i, String str) {
        C1208nF c1208nF = new C1208nF();
        c1208nF.b = i;
        c1208nF.c = str;
        return c1208nF;
    }

    public final void P() {
        if (this.z == null) {
            return;
        }
        this.z = null;
        Activity activity = this.x;
        if (activity == null || this.w == null || activity.isDestroyed()) {
            return;
        }
        this.w.disableReaderMode(this.x);
    }

    public final void Q() {
        if (this.A == null && this.D.size() == 0) {
            P();
        }
    }

    public final void R() {
        if (this.z != null || this.x == null || this.w == null) {
            return;
        }
        if (this.A == null && this.D.size() == 0) {
            return;
        }
        GG gg = new GG(this);
        this.z = gg;
        this.w.enableReaderMode(this.x, gg, 271, null);
    }

    public final void S(C1208nF c1208nF) {
        for (int i = 0; i < this.D.size(); i++) {
            ((CF) this.C).x(c1208nF);
        }
    }

    public final void T(C1263oF c1263oF) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.D.size(); i++) {
            C1373qF c1373qF = (C1373qF) this.D.valueAt(i);
            boolean z = true;
            if (c1263oF.b.length != 0) {
                int i2 = 0;
                while (true) {
                    C1318pF[] c1318pFArr = c1263oF.b;
                    if (i2 >= c1318pFArr.length) {
                        z = false;
                        break;
                    }
                    String str = c1373qF.b;
                    if (str == null || str.equals(c1318pFArr[i2].e)) {
                        String str2 = c1373qF.c;
                        if (str2 != null) {
                            C1318pF[] c1318pFArr2 = c1263oF.b;
                            if (c1318pFArr2[i2].b == 1) {
                                if (str2.compareToIgnoreCase(c1318pFArr2[i2].c) != 0) {
                                    continue;
                                }
                            } else if (!str2.equals(c1318pFArr2[i2].c)) {
                                continue;
                            }
                        }
                        String str3 = c1373qF.d;
                        if (str3 == null || str3.equals(c1263oF.b[i2].d)) {
                            break;
                        }
                    }
                    i2++;
                }
            }
            if (z) {
                arrayList.add(Integer.valueOf(this.D.keyAt(i)));
            }
        }
        if (arrayList.size() != 0) {
            int[] iArr = new int[arrayList.size()];
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                iArr[i3] = ((Integer) arrayList.get(i3)).intValue();
            }
            ((CF) this.C).A(iArr, this.B.d, c1263oF);
        }
    }

    public final void U(C1208nF c1208nF) {
        G(null);
    }

    public final void V() {
        boolean z;
        NG ng = this.B;
        if (ng == null || this.A == null) {
            return;
        }
        try {
            ng.a();
            z = false;
        } catch (IOException unused) {
            z = ng.c;
        }
        if (z) {
            this.B = null;
            return;
        }
        try {
            this.B.a();
            if (!this.A.b.c && !this.B.b.b()) {
                Ts.f("NfcImpl", "Cannot overwrite the NFC tag due to existing data on it.", new Object[0]);
                G(M(0, "NDEFWriteOptions#overwrite does not allow overwrite."));
                this.B = null;
            } else {
                this.B.b.a(CG.e(this.A.a));
                U(null);
            }
        } catch (BG unused2) {
            Ts.f("NfcImpl", "Cannot write data to NFC tag. Invalid NdefMessage.", new Object[0]);
            G(M(4, "Cannot push the message because it's invalid."));
            this.B = null;
        } catch (FormatException e) {
            e = e;
            StringBuilder sbF = Z7.f("Cannot write data to NFC tag: ");
            sbF.append(e.getMessage());
            Ts.f("NfcImpl", sbF.toString(), new Object[0]);
            G(M(7, "Failed to write due to an IO error: " + e.getMessage()));
            this.B = null;
        } catch (TagLostException e2) {
            StringBuilder sbF2 = Z7.f("Cannot write data to NFC tag. Tag is lost: ");
            sbF2.append(e2.getMessage());
            Ts.f("NfcImpl", sbF2.toString(), new Object[0]);
            G(M(7, "Failed to write because the tag is lost: " + e2.getMessage()));
            this.B = null;
        } catch (IOException e3) {
            e = e3;
            StringBuilder sbF3 = Z7.f("Cannot write data to NFC tag: ");
            sbF3.append(e.getMessage());
            Ts.f("NfcImpl", sbF3.toString(), new Object[0]);
            G(M(7, "Failed to write due to an IO error: " + e.getMessage()));
            this.B = null;
        } catch (IllegalStateException e4) {
            e = e4;
            StringBuilder sbF32 = Z7.f("Cannot write data to NFC tag: ");
            sbF32.append(e.getMessage());
            Ts.f("NfcImpl", sbF32.toString(), new Object[0]);
            G(M(7, "Failed to write due to an IO error: " + e.getMessage()));
            this.B = null;
        }
    }

    public final void W() {
        boolean z;
        if (this.B == null || this.C == null || this.D.size() == 0) {
            return;
        }
        FG fg = this.A;
        if (fg == null || !fg.b.b) {
            NG ng = this.B;
            Objects.requireNonNull(ng);
            try {
                ng.a();
                z = false;
            } catch (IOException unused) {
                z = ng.c;
            }
            if (z) {
                this.B = null;
                return;
            }
            try {
                this.B.a();
                NdefMessage ndefMessageC = this.B.b.c();
                if (ndefMessageC != null) {
                    T(CG.f(ndefMessageC));
                    return;
                }
                C1263oF c1263oF = new C1263oF();
                c1263oF.b = new C1318pF[0];
                T(c1263oF);
            } catch (FormatException e) {
                e = e;
                StringBuilder sbF = Z7.f("Cannot read data from NFC tag. IO_ERROR: ");
                sbF.append(e.getMessage());
                Ts.f("NfcImpl", sbF.toString(), new Object[0]);
                S(M(7, "Failed to read due to an IO error: " + e.getMessage()));
            } catch (TagLostException e2) {
                StringBuilder sbF2 = Z7.f("Cannot read data from NFC tag. Tag is lost: ");
                sbF2.append(e2.getMessage());
                Ts.f("NfcImpl", sbF2.toString(), new Object[0]);
                S(M(7, "Failed to read because the tag is lost: " + e2.getMessage()));
            } catch (UnsupportedEncodingException e3) {
                StringBuilder sbF3 = Z7.f("Cannot read data from NFC tag. Cannot convert to NdefMessage:");
                sbF3.append(e3.getMessage());
                Ts.f("NfcImpl", sbF3.toString(), new Object[0]);
                S(M(4, "Failed to decode the NdefMessage read from the tag: " + e3.getMessage()));
            } catch (IOException e4) {
                e = e4;
                StringBuilder sbF4 = Z7.f("Cannot read data from NFC tag. IO_ERROR: ");
                sbF4.append(e.getMessage());
                Ts.f("NfcImpl", sbF4.toString(), new Object[0]);
                S(M(7, "Failed to read due to an IO error: " + e.getMessage()));
            } catch (IllegalStateException e5) {
                e = e5;
                StringBuilder sbF42 = Z7.f("Cannot read data from NFC tag. IO_ERROR: ");
                sbF42.append(e.getMessage());
                Ts.f("NfcImpl", sbF42.toString(), new Object[0]);
                S(M(7, "Failed to read due to an IO error: " + e.getMessage()));
            }
        }
    }

    @Override // defpackage.InterfaceC0822gJ, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.t.a(this.s);
        P();
    }

    @Override // defpackage.InterfaceC1757xF
    public void h(C1373qF c1373qF, int i, InterfaceC1702wF interfaceC1702wF) {
        boolean z;
        C1208nF c1208nFX = x();
        if (c1208nFX == null) {
            z = true;
        } else {
            ((C0931iG) interfaceC1702wF).a(c1208nFX);
            z = false;
        }
        if (z) {
            if (this.D.indexOfKey(i) >= 0) {
                ((C0931iG) interfaceC1702wF).a(M(2, "Cannot start because the received scan request is duplicate."));
            } else {
                this.D.put(i, c1373qF);
                ((C0931iG) interfaceC1702wF).a(null);
                R();
                W();
            }
        }
    }

    @Override // defpackage.InterfaceC1757xF
    public void k(InterfaceC1812yF interfaceC1812yF) {
        this.C = interfaceC1812yF;
    }

    @Override // defpackage.InterfaceC1757xF
    public void o(InterfaceC1537tF interfaceC1537tF) {
        if (A(interfaceC1537tF)) {
            if (this.A == null) {
                ((VF) interfaceC1537tF).a(M(6, "No pending push operation to cancel."));
            } else {
                G(M(5, "The push operation is already cancelled."));
                ((VF) interfaceC1537tF).a(null);
            }
        }
    }

    @Override // defpackage.InterfaceC1757xF
    public void r(C1263oF c1263oF, C1427rF c1427rF, InterfaceC1647vF interfaceC1647vF) {
        C1318pF[] c1318pFArr;
        String str;
        if (A(interfaceC1647vF)) {
            boolean z = false;
            if (c1263oF != null && (c1318pFArr = c1263oF.b) != null && c1318pFArr.length != 0) {
                int i = 0;
                while (true) {
                    C1318pF[] c1318pFArr2 = c1263oF.b;
                    if (i >= c1318pFArr2.length) {
                        z = true;
                        break;
                    }
                    C1318pF c1318pF = c1318pFArr2[i];
                    if (!(c1318pF != null && (c1318pF.c.equals("empty") || (c1318pF.h != null && (!c1318pF.c.equals("mime") ? c1318pF.d != null : (str = c1318pF.d) == null || str.isEmpty()))))) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
            if (!z) {
                ((C0652dG) interfaceC1647vF).a(M(4, "Cannot push the message because it's invalid."));
                return;
            }
            FG fg = this.A;
            if (fg != null) {
                C1208nF c1208nFM = M(5, "Push is cancelled due to a new push request.");
                InterfaceC1647vF interfaceC1647vF2 = fg.c;
                if (interfaceC1647vF2 != null) {
                    ((C0652dG) interfaceC1647vF2).a(c1208nFM);
                }
            }
            this.A = new FG(c1263oF, c1427rF, interfaceC1647vF);
            R();
            V();
        }
    }

    @Override // defpackage.InterfaceC1757xF
    public void s(int i, InterfaceC1592uF interfaceC1592uF) {
        if (A(interfaceC1592uF)) {
            if (this.D.indexOfKey(i) < 0) {
                ((ZF) interfaceC1592uF).a(M(3, "No pending scan operation to cancel."));
            } else {
                this.D.remove(i);
                ((ZF) interfaceC1592uF).a(null);
                Q();
            }
        }
    }

    public final C1208nF x() {
        NfcAdapter nfcAdapter;
        if (!this.y || this.x == null) {
            return M(0, "The operation is not allowed.");
        }
        if (this.v == null || (nfcAdapter = this.w) == null) {
            return M(1, "NFC is not supported.");
        }
        if (nfcAdapter.isEnabled()) {
            return null;
        }
        return M(2, "NFC setting is disabled.");
    }
}
