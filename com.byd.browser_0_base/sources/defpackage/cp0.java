package defpackage;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.d;
import androidx.savedstate.a;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class cp0 implements a.b {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ cp0(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // androidx.savedstate.a.b
    public final Bundle a() {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                FragmentActivity fragmentActivity = (FragmentActivity) obj;
                int i2 = FragmentActivity.B;
                while (FragmentActivity.y1(fragmentActivity.x1())) {
                }
                fragmentActivity.x.e(d.a.ON_STOP);
                return new Bundle();
            default:
                return ((FragmentManager) obj).U();
        }
    }
}
