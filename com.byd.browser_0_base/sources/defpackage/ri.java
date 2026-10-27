package defpackage;

import org.chromium.base.Callback;
import org.chromium.components.browser_ui.bottomsheet.f;
import org.chromium.components.browser_ui.bottomsheet.j;

/* JADX INFO: compiled from: BottomSheetManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ri implements Callback<Boolean> {
    public int e;
    public final /* synthetic */ j f;

    public ri(j jVar) {
        this.f = jVar;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Boolean bool) {
        boolean zBooleanValue = bool.booleanValue();
        j jVar = this.f;
        if (zBooleanValue) {
            this.e = ((f) jVar).v(0);
        } else {
            ((f) jVar).o.c(this.e);
        }
    }
}
