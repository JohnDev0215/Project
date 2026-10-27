package defpackage;

import J.N;
import android.content.Intent;
import android.graphics.Rect;
import java.nio.ByteBuffer;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.chrome.browser.tasks.tab_management.q1;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ij2 implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ ij2(Object obj, int i, Object obj2) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i = this.e;
        Object obj = this.g;
        Object obj2 = this.f;
        switch (i) {
            case 1:
                ui2 ui2Var = (ui2) obj;
                on2 on2Var = ui2Var.d;
                String strMNZ4eg9q = N.MNZ4eg9q((ByteBuffer) on2Var.b, on2Var.a);
                on2 on2Var2 = ui2Var.d;
                return new CriticalPersistedTabData((Tab) obj2, strMNZ4eg9q, N.MZZlQD12((ByteBuffer) on2Var2.b, on2Var2.a), ui2Var.a, ui2Var.b, ui2Var.c, ui2Var.d, ui2Var.f, ui2Var.e, ui2Var.g, ui2Var.h, ui2Var.i);
            case 2:
                q1 q1Var = (q1) obj2;
                q1Var.getClass();
                return Float.valueOf(((Rect) ((nw2) obj).get()).left / q1Var.r);
            default:
                ((hj0) obj).getClass();
                return hj0.n((Intent) obj2);
        }
    }
}
