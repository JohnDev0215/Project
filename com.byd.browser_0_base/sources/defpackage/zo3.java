package defpackage;

import J.N;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import java.util.HashMap;
import java.util.HashSet;
import org.chromium.base.SysUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.crash.ChromePureJavaExceptionReporter;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: WarmupManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class zo3 {

    @SuppressLint({"StaticFieldLeak"})
    public static zo3 g;
    public final HashSet a = new HashSet();
    public final HashMap b = new HashMap();
    public int c;
    public ViewGroup d;
    public WebContents e;
    public a f;

    /* JADX INFO: compiled from: WarmupManager.java */
    public class a extends rq3 {
        public a() {
        }

        @Override // defpackage.rq3
        public final void renderProcessGone() {
            zo3 zo3Var = zo3.this;
            zo3Var.e.z(zo3Var.f);
            zo3Var.e.destroy();
            zo3Var.e = null;
            zo3Var.f = null;
        }
    }

    public static zo3 a() {
        Object obj = ThreadUtils.a;
        if (g == null) {
            g = new zo3();
        }
        return g;
    }

    public final void b(Context context, int i, int i2) {
        TraceEvent traceEventH0;
        Object obj = ThreadUtils.a;
        if (this.d == null || this.c != i) {
            ViewGroup viewGroup = null;
            try {
                traceEventH0 = TraceEvent.H0("WarmupManager.inflateViewHierarchy", null);
            } catch (InflateException e) {
                Log.e("cr_WarmupManager", "Inflation exception.", e);
                ChromePureJavaExceptionReporter.d(new Throwable("This is not a crash. See https://crbug.com/1259276 for details.", e));
            }
            try {
                ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, SysUtils.isLowEndDevice() ? R$style.Theme_Chromium_WithWindowAnimation_LowEnd : R$style.Theme_Chromium_WithWindowAnimation);
                FrameLayout frameLayout = new FrameLayout(contextThemeWrapper);
                LayoutInflater layoutInflaterFrom = LayoutInflater.from(contextThemeWrapper);
                layoutInflaterFrom.setFactory2(new v8());
                int i3 = R$layout.main;
                ku2 ku2VarT = ku2.t();
                try {
                    View viewInflate = layoutInflaterFrom.inflate(i3, (ViewGroup) frameLayout, true);
                    ku2VarT.close();
                    ViewGroup viewGroup2 = (ViewGroup) viewInflate;
                    if (i != -1) {
                        ViewStub viewStub = (ViewStub) viewGroup2.findViewById(R$id.control_container_stub);
                        viewStub.setLayoutResource(i);
                        viewStub.inflate();
                    }
                    e00 e00Var = (e00) viewGroup2.findViewById(R$id.control_container);
                    if (i2 != -1 && e00Var != null) {
                        e00Var.b(i2);
                    }
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                    }
                    viewGroup = viewGroup2;
                    this.d = viewGroup;
                    this.c = i;
                } catch (Throwable th) {
                    try {
                        ku2VarT.close();
                    } catch (Throwable unused) {
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                if (traceEventH0 != null) {
                    try {
                        traceEventH0.close();
                    } catch (Throwable unused2) {
                    }
                }
                throw th2;
            }
        }
    }

    public final void c(String str, Profile profile) {
        TraceEvent traceEventH0 = TraceEvent.H0("WarmupManager.maybePreconnectUrlAndSubResources", null);
        try {
            Object obj = ThreadUtils.a;
            Uri uri = Uri.parse(str);
            if (uri == null) {
                if (traceEventH0 != null) {
                    traceEventH0.close();
                    return;
                }
                return;
            }
            String scheme = uri.normalizeScheme().getScheme();
            if (!"http".equals(scheme) && !"https".equals(scheme)) {
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
            } else {
                if (this.a.contains(str)) {
                    this.b.put(str, profile);
                } else {
                    N.Mw6Ub3GC(profile, str);
                }
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    public final void d(String str) {
        TraceEvent traceEventH0 = TraceEvent.H0("WarmupManager.maybePrefetchDnsForUrlInBackground", null);
        try {
            Object obj = ThreadUtils.a;
            this.a.add(str);
            new yo3(this, str).c(i9.e);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    public final WebContents e(boolean z, boolean z2) {
        TraceEvent traceEventH0 = TraceEvent.H0("WarmupManager.takeSpareWebContents", null);
        try {
            Object obj = ThreadUtils.a;
            if (z) {
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return null;
            }
            WebContents webContents = this.e;
            if (webContents == null) {
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return null;
            }
            this.e = null;
            webContents.z(this.f);
            this.f = null;
            if (!z2) {
                webContents.Y();
            }
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return webContents;
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }
}
