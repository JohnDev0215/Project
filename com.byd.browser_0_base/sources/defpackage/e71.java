package defpackage;

import org.chromium.base.Callback;
import org.chromium.base.LocaleUtils;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.customtabs.CustomTabActivity;
import org.chromium.chrome.browser.firstrun.LightweightFirstRunActivity;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e71 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ LightweightFirstRunActivity f;

    public /* synthetic */ e71(LightweightFirstRunActivity lightweightFirstRunActivity, int i) {
        this.e = i;
        this.f = lightweightFirstRunActivity;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        LightweightFirstRunActivity lightweightFirstRunActivity = this.f;
        switch (i) {
            case 0:
                if (lightweightFirstRunActivity.s0) {
                    lightweightFirstRunActivity.o0.c();
                }
                break;
            default:
                int i2 = LightweightFirstRunActivity.y0;
                CustomTabActivity.i3(lightweightFirstRunActivity, lightweightFirstRunActivity.getString(R$string.google_privacy_policy_url).replace("$LOCALE", LocaleUtils.getDefaultLocaleString().replace('-', '_')));
                break;
        }
    }
}
