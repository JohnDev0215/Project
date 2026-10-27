package defpackage;

import android.telephony.TelephonyManager;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* JADX INFO: renamed from: zK, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1872zK {
    public static volatile C1872zK d;
    public volatile String a;
    public volatile String b;
    public C1817yK c;

    public static C1872zK a() {
        final C1872zK c1872zK = d;
        if (c1872zK == null) {
            synchronized (C1872zK.class) {
                c1872zK = d;
                if (c1872zK == null) {
                    c1872zK = new C1872zK();
                    ThreadUtils.g(new Runnable(c1872zK) { // from class: wK
                        public final C1872zK s;

                        {
                            this.s = c1872zK;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            C1872zK c1872zK2 = this.s;
                            TelephonyManager telephonyManagerB = C1872zK.b();
                            if (telephonyManagerB != null) {
                                Objects.requireNonNull(c1872zK2);
                                ThreadUtils.b();
                                C1817yK c1817yK = new C1817yK(c1872zK2, null);
                                c1872zK2.c = c1817yK;
                                telephonyManagerB.listen(c1817yK, 1);
                            }
                        }
                    });
                    d = c1872zK;
                }
            }
        }
        return c1872zK;
    }

    public static TelephonyManager b() {
        return (TelephonyManager) Is.a.getSystemService("phone");
    }
}
