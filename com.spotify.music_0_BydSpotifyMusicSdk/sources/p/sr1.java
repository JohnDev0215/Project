package p;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class sr1 extends ux2 {
    public final /* synthetic */ int g;
    public final String h;
    public final h00 i;
    public final boolean j;

    public sr1(String str, boolean z, int i) {
        qo qoVar = qo.c;
        this.g = i;
        if (i != 1) {
            Objects.requireNonNull(str, "name == null");
            this.h = str;
            this.i = qoVar;
            this.j = z;
            return;
        }
        Objects.requireNonNull(str, "name == null");
        this.h = str;
        this.i = qoVar;
        this.j = z;
    }

    @Override // p.ux2
    public final void c(n32 n32Var, Object obj) {
        String str;
        String str2;
        int i = this.g;
        boolean z = this.j;
        String str3 = this.h;
        h00 h00Var = this.i;
        switch (i) {
            case 0:
                if (obj != null && (str = (String) h00Var.convert(obj)) != null) {
                    n32Var.a(str3, str, z);
                }
                break;
            default:
                if (obj != null && (str2 = (String) h00Var.convert(obj)) != null) {
                    n32Var.d(str3, str2, z);
                }
                break;
        }
    }
}
