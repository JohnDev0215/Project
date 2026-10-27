package defpackage;

import J.N;
import android.util.Printer;
import org.chromium.base.EarlyTraceEvent;
import org.chromium.base.TraceEvent;

/* JADX INFO: renamed from: ft, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0795ft implements Printer {
    public static final int b = 18;
    public String a;

    public C0795ft(AbstractC0739et abstractC0739et) {
    }

    public void a(String str) {
        boolean zD = EarlyTraceEvent.d();
        if (TraceEvent.t || zD) {
            StringBuilder sbF = Z7.f("Looper.dispatch: ");
            int iIndexOf = str.indexOf(40, 18);
            int iIndexOf2 = iIndexOf == -1 ? -1 : str.indexOf(41, iIndexOf);
            sbF.append(iIndexOf2 != -1 ? str.substring(iIndexOf + 1, iIndexOf2) : "");
            sbF.append("(");
            int iIndexOf3 = str.indexOf(125, 18);
            int iIndexOf4 = iIndexOf3 == -1 ? -1 : str.indexOf(58, iIndexOf3);
            if (iIndexOf4 == -1) {
                iIndexOf4 = str.length();
            }
            sbF.append(iIndexOf3 != -1 ? str.substring(iIndexOf3 + 2, iIndexOf4) : "");
            sbF.append(")");
            this.a = sbF.toString();
            if (TraceEvent.t) {
                N.M_y76mct(this.a);
            } else {
                EarlyTraceEvent.a(this.a);
            }
        }
    }

    public void b(String str) {
        boolean zD = EarlyTraceEvent.d();
        if ((TraceEvent.t || zD) && this.a != null) {
            if (TraceEvent.t) {
                N.MLJecZJ9(this.a);
            } else {
                EarlyTraceEvent.c(this.a);
            }
        }
        this.a = null;
    }

    @Override // android.util.Printer
    public void println(String str) {
        if (str.startsWith(">")) {
            a(str);
        } else {
            b(str);
        }
    }
}
