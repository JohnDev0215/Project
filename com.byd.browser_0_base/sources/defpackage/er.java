package defpackage;

import android.app.Activity;
import android.content.ComponentName;
import defpackage.al2;
import defpackage.ir;
import java.util.Arrays;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.share.a;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: ChromeProvidedSharingOptionsProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class er extends ir {
    public static final ComponentName p = new ComponentName("CHROME", "CHROME_FEATURE");
    public final vk2 l;
    public final long m;
    public final int n;
    public final al2.a o;

    public er(Activity activity, WindowAndroid windowAndroid, nw2 nw2Var, ji jiVar, vk2 vk2Var, a aVar, boolean z, long j, rq rqVar, xe3 xe3Var, String str, int i, al2.a aVar2, Profile profile) {
        super(activity, windowAndroid, nw2Var, jiVar, aVar, z, rqVar, xe3Var, str, profile);
        this.l = vk2Var;
        this.m = j;
        this.n = i;
        this.o = aVar2;
    }

    @Override // defpackage.ir
    public final ir.a b() {
        return new ir.a(R$drawable.long_screenshot, R$string.sharing_long_screenshot, null, "SharingHubAndroid.LongScreenshotSelected", new jb1(1, this), Arrays.asList(0, 2, 3, 5), Arrays.asList(new Integer[0]), Arrays.asList(5), true);
    }

    @Override // defpackage.ir
    public final ir.a c() {
        return new ir.a(R$drawable.screenshot, R$string.sharing_screenshot, null, "SharingHubAndroid.ScreenshotSelected", new dr(0, this), Arrays.asList(0, 2, 3, 5), Arrays.asList(new Integer[0]), Arrays.asList(5), true);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.ArrayList g(java.util.Set r6, int r7, boolean r8) {
        /*
            r5 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.ArrayList r6 = r5.d(r6, r7, r8)
            java.util.Iterator r6 = r6.iterator()
        Ld:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L7b
            java.lang.Object r7 = r6.next()
            ir$a r7 = (ir.a) r7
            xe3 r8 = r5.j
            boolean r1 = r8.isInitialized()
            java.lang.String r2 = "SharingHubAndroid.ScreenshotSelected"
            r3 = 0
            if (r1 != 0) goto L25
            goto L45
        L25:
            java.lang.String r1 = r7.d
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L34
            java.lang.String r1 = "IPH_ShareScreenshot"
            boolean r8 = r8.shouldTriggerHelpUI(r1)
            goto L46
        L34:
            java.lang.String r1 = "SharingHubAndroid.WebnotesStylize"
            java.lang.String r4 = r7.d
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L45
            java.lang.String r1 = "IPH_SharingHubWebnotesStylize"
            boolean r8 = r8.shouldTriggerHelpUI(r1)
            goto L46
        L45:
            r8 = r3
        L46:
            java.lang.String r1 = r7.d
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L5a
            java.lang.String r1 = "SharingHubAndroid.LongScreenshotSelected"
            java.lang.String r2 = r7.d
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L59
            goto L5a
        L59:
            r3 = 1
        L5a:
            int r1 = r7.a
            android.app.Activity r2 = r5.a
            android.graphics.drawable.Drawable r1 = androidx.appcompat.content.res.AppCompatResources.a(r2, r1)
            android.content.res.Resources r2 = r2.getResources()
            int r4 = r7.b
            java.lang.String r2 = r2.getString(r4)
            cr r4 = new cr
            r4.<init>()
            java.lang.String r7 = r7.c
            org.chromium.ui.modelutil.PropertyModel r7 = defpackage.cl2.a(r1, r2, r7, r4, r8)
            r0.add(r7)
            goto Ld
        L7b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.er.g(java.util.Set, int, boolean):java.util.ArrayList");
    }
}
