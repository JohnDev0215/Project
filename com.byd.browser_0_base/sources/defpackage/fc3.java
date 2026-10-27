package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.toolbar.top.d;
import org.chromium.chrome.browser.toolbar.top.m;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class fc3 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ m f;

    public /* synthetic */ fc3(m mVar, int i) {
        this.e = i;
        this.f = mVar;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        d dVar;
        int i = this.e;
        m mVar = this.f;
        switch (i) {
            case 0:
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                if (mVar.b == null && (dVar = mVar.c) != null) {
                    dVar.a.e.l(cs2.o, zBooleanValue);
                }
                break;
            default:
                mVar.p((z6) obj);
                break;
        }
    }
}
