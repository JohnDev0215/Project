package defpackage;

import android.os.Handler;
import androidx.appcompat.widget.a;

/* JADX INFO: compiled from: StripLayoutHelperManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class cv2 implements e23 {
    public final /* synthetic */ zu2 e;

    public cv2(zu2 zu2Var) {
        this.e = zu2Var;
    }

    @Override // defpackage.e23
    public final void g() {
        this.e.s();
        new Handler().post(new a(21, this));
    }
}
