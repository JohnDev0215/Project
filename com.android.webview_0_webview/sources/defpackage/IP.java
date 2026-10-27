package defpackage;

import android.content.Context;
import android.view.ViewStructure;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class IP extends KP {
    public IP(Context context, PN pn) {
        super(context, pn);
    }

    @Override // android.view.View
    public void onProvideVirtualStructure(ViewStructure viewStructure) {
        InterfaceC0817gE interfaceC0817gEM = m();
        if (interfaceC0817gEM != null) {
            ((WebContentsAccessibilityImpl) interfaceC0817gEM).t(viewStructure, false);
        }
    }
}
