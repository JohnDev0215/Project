package defpackage;

import android.os.Bundle;
import org.chromium.content.browser.TtsPlatformImpl;

/* JADX INFO: renamed from: uz, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1630uz extends TtsPlatformImpl {
    public C1630uz(long j) {
        super(j);
    }

    @Override // org.chromium.content.browser.TtsPlatformImpl
    public void b() {
        this.b.setOnUtteranceProgressListener(new C1575tz(this));
    }

    @Override // org.chromium.content.browser.TtsPlatformImpl
    public int c(String str, float f, int i) {
        Bundle bundle = new Bundle();
        if (f != 1.0d) {
            bundle.putFloat("volume", f);
        }
        return this.b.speak(str, 0, bundle, Integer.toString(i));
    }
}
