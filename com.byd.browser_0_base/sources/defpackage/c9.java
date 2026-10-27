package defpackage;

import android.content.ComponentName;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: AsyncTabCreationParams.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c9 implements e9 {
    public final LoadUrlParams a;
    public final WebContents b;
    public final Integer c;
    public final ComponentName d;

    public c9(LoadUrlParams loadUrlParams, WebContents webContents, Integer num, ComponentName componentName) {
        this.a = loadUrlParams;
        this.c = num;
        this.b = webContents;
        this.d = componentName;
    }

    @Override // defpackage.e9
    public final WebContents c() {
        return this.b;
    }

    @Override // defpackage.e9
    public final Tab d() {
        return null;
    }
}
