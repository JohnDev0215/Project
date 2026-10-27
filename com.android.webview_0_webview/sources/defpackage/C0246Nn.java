package defpackage;

import org.chromium.android_webview.AwContents;
import org.chromium.components.navigation_interception.InterceptNavigationDelegate;
import org.chromium.components.navigation_interception.NavigationParams;

/* JADX INFO: renamed from: Nn, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0246Nn implements InterceptNavigationDelegate {
    public final /* synthetic */ AwContents a;

    public C0246Nn(AwContents awContents, C1838yn c1838yn) {
        this.a = awContents;
    }

    @Override // org.chromium.components.navigation_interception.InterceptNavigationDelegate
    public boolean shouldIgnoreNavigation(NavigationParams navigationParams) {
        if (AbstractC0140Ho.a(navigationParams.g)) {
            return false;
        }
        this.a.E.a.b(navigationParams.a);
        return false;
    }
}
