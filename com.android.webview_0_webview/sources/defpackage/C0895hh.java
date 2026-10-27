package defpackage;

import android.database.ContentObserver;

/* JADX INFO: renamed from: hh, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0895hh extends ContentObserver {
    public C0895hh() {
        super(null);
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        AbstractC0839gh.e.set(true);
    }
}
