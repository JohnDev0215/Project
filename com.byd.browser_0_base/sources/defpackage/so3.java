package defpackage;

import android.app.Activity;
import android.content.Intent;
import defpackage.so3;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.b;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.omnibox.g;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.permissions.PermissionCallback;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: VoiceRecognitionHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public final class so3 {
    public static final yj1 j = new yj1("ToolbarMicIphAndroid", false);
    public static final yj1 k = new yj1("AssistantIntentExperimentId", false);
    public static final yj1 l = new yj1("AssistantNonPersonalizedVoiceSearch", true);
    public static final yj1 m = new yj1("AssistantIntentTranslateInfo", false);
    public final a a;
    public Long b;
    public rq3 c;
    public final nw2<u8> d;
    public final c e;
    public final org.chromium.base.b<b> f = new org.chromium.base.b<>();
    public final zp1<Profile> g;
    public Boolean h;
    public boolean i;

    /* JADX INFO: compiled from: VoiceRecognitionHandler.java */
    public interface a {
    }

    /* JADX INFO: compiled from: VoiceRecognitionHandler.java */
    public interface b {
        void E();
    }

    /* JADX INFO: compiled from: VoiceRecognitionHandler.java */
    public static class c {
    }

    /* JADX INFO: compiled from: VoiceRecognitionHandler.java */
    public class d implements WindowAndroid.c {
        public final int a;
        public final int b;
        public boolean c;

        public d(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        /* JADX WARN: Removed duplicated region for block: B:60:0x0136  */
        @Override // org.chromium.ui.base.WindowAndroid.c
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void b(android.content.Intent r14, int r15) {
            /*
                Method dump skipped, instruction units count: 613
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: so3.d.b(android.content.Intent, int):void");
        }
    }

    /* JADX INFO: compiled from: VoiceRecognitionHandler.java */
    public static class e {
        public final String a;
        public final float b;
        public final String c;

        public e(String str, float f, String str2) {
            this.a = str;
            this.b = f;
            this.c = str2;
        }
    }

    /* JADX INFO: compiled from: VoiceRecognitionHandler.java */
    public final class f extends rq3 {
        public f(WebContents webContents) {
            super(webContents);
        }

        @Override // defpackage.rq3
        public final void didFinishNavigationInPrimaryMainFrame(NavigationHandle navigationHandle) {
            RenderFrameHost renderFrameHostY;
            if (navigationHandle.g && !navigationHandle.i) {
                GURL gurl = navigationHandle.e;
                WebContents webContents = this.e.get();
                if (webContents != null && (renderFrameHostY = webContents.y()) != null) {
                    so3 so3Var = so3.this;
                    if (so3Var.g.f() && oj2.q(so3Var.g.get()).g(gurl)) {
                        renderFrameHostY.d();
                    }
                }
            }
            destroy();
        }
    }

    public so3(a aVar, rs1 rs1Var, zp1 zp1Var) {
        ll llVar = new ll();
        this.a = aVar;
        this.d = rs1Var;
        this.e = new c();
        this.g = zp1Var;
        zp1Var.p(llVar.b(new lj1(9, this)));
    }

    public static void c(int i, boolean z) {
        oj2.O("VoiceInteraction.VoiceSearchResult", z);
        String str = i != 0 ? i != 1 ? null : "Assistant" : "System";
        if (str != null) {
            oj2.O("VoiceInteraction.VoiceSearchResult.".concat(str), z);
        }
    }

    public final boolean a() {
        WindowAndroid windowAndroid;
        g gVar = (g) this.a;
        org.chromium.chrome.browser.omnibox.f fVar = gVar.h;
        if (fVar == null || fVar.isIncognito() || (windowAndroid = gVar.u) == null || windowAndroid.k().get() == null || !to3.c(false)) {
            return false;
        }
        if (this.h == null) {
            this.h = Boolean.valueOf(to3.b(windowAndroid));
            if (!this.i) {
                ApplicationStatus.e(new ApplicationStatus.d() { // from class: qo3
                    @Override // org.chromium.base.ApplicationStatus.d
                    public final void v(int i) {
                        so3 so3Var = this.e;
                        if (i == 2) {
                            so3Var.h = null;
                        } else {
                            so3Var.getClass();
                        }
                    }
                });
                this.i = true;
            }
        }
        return this.h.booleanValue();
    }

    public final void b() {
        Iterator<b> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((b) aVar.next()).E();
            }
        }
    }

    public final boolean d(WindowAndroid windowAndroid, Intent intent, int i, int i2) {
        int iA;
        oj2.U(i, 5, "VoiceInteraction.StartEventSource");
        oj2.U(i2, 2, "VoiceInteraction.StartEventTarget");
        d dVar = new d(i, i2);
        Integer numValueOf = Integer.valueOf(R$string.voice_search_error);
        org.chromium.ui.base.d dVar2 = windowAndroid.e;
        if (dVar2 == null) {
            Objects.toString(intent);
            iA = -1;
        } else {
            iA = dVar2.a(intent, dVar, numValueOf);
        }
        return iA >= 0;
    }

    public final boolean e(final Activity activity, final WindowAndroid windowAndroid, final int i) {
        boolean z;
        if (windowAndroid.hasPermission("android.permission.RECORD_AUDIO")) {
            oj2.U(0, 3, "VoiceInteraction.AudioPermissionEvent");
            z = true;
        } else {
            if (windowAndroid.canRequestPermission("android.permission.RECORD_AUDIO")) {
                windowAndroid.b(new String[]{"android.permission.RECORD_AUDIO"}, new PermissionCallback() { // from class: ro3
                    @Override // org.chromium.ui.permissions.PermissionCallback
                    public final void s(int[] iArr, String[] strArr) {
                        so3 so3Var = this.e;
                        so3Var.getClass();
                        int length = iArr.length;
                        so3.a aVar = so3Var.a;
                        if (length != 1) {
                            oj2.U(1, 3, "VoiceInteraction.AudioPermissionEvent");
                            ((g) aVar).Y();
                            return;
                        }
                        int i2 = iArr[0];
                        WindowAndroid windowAndroid2 = windowAndroid;
                        if (i2 == 0) {
                            so3Var.e(activity, windowAndroid2, i);
                            return;
                        }
                        if (windowAndroid2.canRequestPermission("android.permission.RECORD_AUDIO")) {
                            oj2.U(1, 3, "VoiceInteraction.AudioPermissionEvent");
                            ((g) aVar).Y();
                        } else {
                            oj2.U(2, 3, "VoiceInteraction.AudioPermissionEvent");
                            so3Var.b();
                            ((g) aVar).Y();
                        }
                    }
                });
            } else {
                oj2.U(2, 3, "VoiceInteraction.AudioPermissionEvent");
                b();
            }
            z = false;
        }
        if (!z) {
            ((g) this.a).Y();
            return false;
        }
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        intent.putExtra("calling_package", activity.getComponentName().flattenToString());
        intent.putExtra("android.speech.extra.WEB_SEARCH_ONLY", true);
        if (d(windowAndroid, intent, i, 0)) {
            return true;
        }
        to3.a(false);
        b();
        oj2.U(i, 5, "VoiceInteraction.FailureEventSource");
        oj2.U(0, 2, "VoiceInteraction.FailureEventTarget");
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x014c A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(int r11) {
        /*
            Method dump skipped, instruction units count: 347
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.so3.f(int):void");
    }
}
