package defpackage;

import J.N;
import android.text.TextUtils;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class va2 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ wa2 f;

    public /* synthetic */ va2(wa2 wa2Var, int i) {
        this.e = i;
        this.f = wa2Var;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        int i2 = 1;
        wa2 wa2Var = this.f;
        switch (i) {
            case 0:
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                ta2 ta2Var = wa2Var.a;
                if (!zBooleanValue) {
                    bi1 bi1Var = ta2Var.a;
                    if (bi1Var != null) {
                        bi1Var.b(2, ta2Var.b);
                    }
                } else {
                    String targetName = ta2Var.c.getTargetName();
                    wa2Var.d = targetName;
                    if (TextUtils.equals(targetName, wa2Var.c)) {
                        wa2Var.a(2, 1);
                    } else if (!N.MatdI239(wa2Var.d).equalsIgnoreCase(N.MatdI239(wa2Var.c))) {
                        wa2Var.a(4, 1);
                    } else {
                        wa2Var.f.r(wa2Var.d, new va2(wa2Var, i2));
                    }
                }
                break;
            default:
                Integer num = (Integer) obj;
                wa2Var.getClass();
                wa2Var.e = num.intValue();
                if (num.intValue() != 0) {
                    wa2Var.a(wa2Var.g == 4 ? 6 : 3, 1);
                } else {
                    wa2Var.a(0, 1);
                }
                break;
        }
    }
}
