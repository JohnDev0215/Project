package defpackage;

import android.database.ContentObserver;

/* JADX INFO: renamed from: th, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1557th extends ContentObserver {
    public C1557th() {
        super(null);
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        AbstractC1667vh.i.incrementAndGet();
    }
}
