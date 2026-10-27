package p;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class ur1 extends ux2 {
    public final String g;
    public final h00 h;

    public ur1(String str) {
        qo qoVar = qo.c;
        Objects.requireNonNull(str, "name == null");
        this.g = str;
        this.h = qoVar;
    }

    @Override // p.ux2
    public final void c(n32 n32Var, Object obj) {
        String str;
        if (obj == null || (str = (String) this.h.convert(obj)) == null) {
            return;
        }
        n32Var.b(this.g, str);
    }
}
