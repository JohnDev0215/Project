package defpackage;

import defpackage.fc;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: OfflineBackgroundTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class eq1 implements Callback<Boolean> {
    public final /* synthetic */ fc.a e;

    public eq1(fc.a aVar) {
        this.e = aVar;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Boolean bool) {
        this.e.b(bool.booleanValue());
    }
}
