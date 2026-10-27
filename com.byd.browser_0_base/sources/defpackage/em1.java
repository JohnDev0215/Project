package defpackage;

import android.content.res.Resources;
import android.os.Handler;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ntp.NewTabPageLayout;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class em1 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ NewTabPageLayout f;

    public /* synthetic */ em1(NewTabPageLayout newTabPageLayout, int i) {
        this.e = i;
        this.f = newTabPageLayout;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        NewTabPageLayout newTabPageLayout = this.f;
        switch (i) {
            case 0:
                Resources resources = newTabPageLayout.n.getResources();
                int i2 = R$string.feature_notification_guide_tooltip_message_voice_search;
                new pk3(newTabPageLayout.n, new Handler()).a(NewTabPageLayout.a(resources, i2, i2, newTabPageLayout.i.b.findViewById(R$id.voice_search_button), true).a());
                break;
            default:
                newTabPageLayout.m();
                break;
        }
    }
}
