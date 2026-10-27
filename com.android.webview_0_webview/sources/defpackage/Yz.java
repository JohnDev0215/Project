package defpackage;

import android.content.res.Configuration;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public interface Yz extends InterfaceC0771fO {
    void b(boolean z, boolean z2);

    void onAttachedToWindow();

    void onConfigurationChanged(Configuration configuration);

    void onDetachedFromWindow();

    void onWindowFocusChanged(boolean z);

    void u(WindowAndroid windowAndroid);
}
