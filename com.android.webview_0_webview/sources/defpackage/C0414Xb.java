package defpackage;

import J.N;
import android.webkit.WebViewDelegate;
import java.util.Objects;
import org.chromium.base.TraceEvent;

/* JADX INFO: renamed from: Xb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0414Xb implements WebViewDelegate.OnTraceEnabledChangeListener {
    public final /* synthetic */ InterfaceC0499ac a;

    public C0414Xb(C0450Zb c0450Zb, InterfaceC0499ac interfaceC0499ac) {
        this.a = interfaceC0499ac;
    }

    public void onTraceEnabledChange(boolean z) {
        Objects.requireNonNull((C1387qb) this.a);
        if (TraceEvent.u == z) {
            return;
        }
        TraceEvent.u = z;
        if (z) {
            N.MRN$Vid3();
        } else {
            N.MOgCa3d$();
        }
    }
}
