package defpackage;

import android.view.ViewStructure;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content_public.browser.AccessibilitySnapshotCallback;

/* JADX INFO: renamed from: kA, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1036kA extends AccessibilitySnapshotCallback {
    public final /* synthetic */ ViewStructure a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ WebContentsAccessibilityImpl c;

    public C1036kA(WebContentsAccessibilityImpl webContentsAccessibilityImpl, ViewStructure viewStructure, boolean z) {
        this.c = webContentsAccessibilityImpl;
        this.a = viewStructure;
        this.b = z;
    }
}
