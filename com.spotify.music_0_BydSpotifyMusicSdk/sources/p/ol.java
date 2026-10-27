package p;

import android.os.Build;
import androidx.work.NetworkType;

/* JADX INFO: loaded from: classes.dex */
public final class ol extends androidx.work.impl.constraints.controllers.a {
    public final /* synthetic */ int b;
    public final int c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ol(pl plVar) {
        super(plVar);
        this.b = 1;
        v03.j(plVar, "tracker");
        this.c = 5;
    }

    @Override // androidx.work.impl.constraints.controllers.a
    public final int a() {
        return this.c;
    }

    @Override // androidx.work.impl.constraints.controllers.a
    public final boolean b(ky2 ky2Var) {
        switch (this.b) {
            case 0:
                return ky2Var.j.b;
            case 1:
                return ky2Var.j.d;
            case 2:
                return ky2Var.j.a == NetworkType.e;
            case 3:
                NetworkType networkType = ky2Var.j.a;
                if (networkType != NetworkType.k) {
                    return Build.VERSION.SDK_INT >= 30 && networkType == NetworkType.n;
                }
                return true;
            default:
                return ky2Var.j.e;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:13:0x0025. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:17:0x0031. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x000b. Please report as an issue. */
    @Override // androidx.work.impl.constraints.controllers.a
    public final boolean c(Object obj) {
        int i = this.b;
        switch (i) {
            case 0:
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                switch (i) {
                }
                return !zBooleanValue;
            case 1:
                boolean zBooleanValue2 = ((Boolean) obj).booleanValue();
                switch (i) {
                }
                return !zBooleanValue2;
            case 2:
                return e((dn1) obj);
            case 3:
                return e((dn1) obj);
            default:
                boolean zBooleanValue3 = ((Boolean) obj).booleanValue();
                switch (i) {
                }
                return !zBooleanValue3;
        }
    }

    public final boolean e(dn1 dn1Var) {
        switch (this.b) {
            case 2:
                v03.j(dn1Var, "value");
                if (dn1Var.a && dn1Var.b) {
                    break;
                }
                break;
            default:
                v03.j(dn1Var, "value");
                if (dn1Var.a && !dn1Var.c) {
                    break;
                }
                break;
        }
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ol(qy qyVar, int i) {
        super(qyVar);
        this.b = i;
        if (i == 2) {
            v03.j(qyVar, "tracker");
            super(qyVar);
            this.c = 7;
        } else if (i == 3) {
            v03.j(qyVar, "tracker");
            super(qyVar);
            this.c = 7;
        } else if (i != 4) {
            v03.j(qyVar, "tracker");
            this.c = 6;
        } else {
            v03.j(qyVar, "tracker");
            super(qyVar);
            this.c = 9;
        }
    }
}
