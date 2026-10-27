package defpackage;

import J.N;
import android.webkit.WebViewRenderProcess;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.chromium.android_webview.AwRenderProcess;

/* JADX INFO: renamed from: cc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0611cc extends WebViewRenderProcess {
    public static WeakHashMap b = new WeakHashMap();
    public WeakReference a;

    public C0611cc(AwRenderProcess awRenderProcess) {
        this.a = new WeakReference(awRenderProcess);
    }

    public static C0611cc a(AwRenderProcess awRenderProcess) {
        if (awRenderProcess == null) {
            return null;
        }
        C0611cc c0611cc = (C0611cc) b.get(awRenderProcess);
        if (c0611cc != null) {
            return c0611cc;
        }
        WeakHashMap weakHashMap = b;
        C0611cc c0611cc2 = new C0611cc(awRenderProcess);
        weakHashMap.put(awRenderProcess, c0611cc2);
        return c0611cc2;
    }

    @Override // android.webkit.WebViewRenderProcess
    public boolean terminate() {
        AwRenderProcess awRenderProcess = (AwRenderProcess) this.a.get();
        if (awRenderProcess == null) {
            return false;
        }
        long j = awRenderProcess.b;
        if (j == 0) {
            return false;
        }
        return N.MjS72mtS(j, awRenderProcess);
    }
}
