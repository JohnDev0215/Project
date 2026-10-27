package defpackage;

import defpackage.gl2;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: TwaIntentHandlingStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public final class xg3 implements w30 {
    public final p70 a;
    public final zg3 b;

    public xg3(p70 p70Var, zg3 zg3Var) {
        this.a = p70Var;
        this.b = zg3Var;
    }

    @Override // defpackage.w30
    public final void a(oj ojVar) {
        c(ojVar, true);
    }

    @Override // defpackage.w30
    public final void b(oj ojVar) {
        c(ojVar, false);
    }

    public final void c(final oj ojVar, final boolean z) {
        final up3 up3Var;
        final zg3 zg3Var = this.b;
        zg3Var.getClass();
        final bk2 bk2VarA = ojVar.A();
        cp3 cp3VarL = ojVar.L();
        if (cp3VarL != null) {
            up3Var = cp3VarL.k;
        } else {
            gl2 gl2VarB = ojVar.B();
            if (gl2VarB == null) {
                up3Var = null;
            } else {
                String str = gl2VarB.a;
                gl2.b bVar = gl2VarB.d;
                String str2 = bVar.a;
                String str3 = bVar.b;
                String str4 = gl2VarB.b;
                boolean z2 = str4 != null && "POST".equals(str4.toUpperCase(Locale.ENGLISH));
                String str5 = gl2VarB.c;
                boolean z3 = str5 != null && "multipart/form-data".equals(str5.toLowerCase(Locale.ENGLISH));
                List<gl2.a> list = bVar.c;
                int size = list == null ? 0 : list.size();
                String[] strArr = new String[size];
                String[][] strArr2 = new String[size][];
                for (int i = 0; i < size; i++) {
                    gl2.a aVar = list.get(i);
                    strArr[i] = aVar.a;
                    List<String> list2 = aVar.b;
                    strArr2[i] = (String[]) list2.toArray(new String[list2.size()]);
                }
                up3Var = new up3(str, str2, str3, z2, z3, strArr, strArr2);
            }
        }
        ((up3Var == null || bk2VarA == null) ? s62.c(Boolean.FALSE) : zg3Var.d.d(up3Var.a[0]).f(new Function() { // from class: yg3
            /* JADX WARN: Removed duplicated region for block: B:36:0x00b2  */
            /* JADX WARN: Removed duplicated region for block: B:42:0x00c6  */
            /* JADX WARN: Removed duplicated region for block: B:44:0x00c9  */
            /* JADX WARN: Removed duplicated region for block: B:45:0x00cc  */
            /* JADX WARN: Removed duplicated region for block: B:49:0x00d2  */
            @Override // java.util.function.Function
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object apply(java.lang.Object r22) {
                /*
                    Method dump skipped, instruction units count: 553
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: defpackage.yg3.apply(java.lang.Object):java.lang.Object");
            }
        })).g(new Callback() { // from class: wg3
            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                xg3 xg3Var = this.e;
                xg3Var.getClass();
                if (((Boolean) obj).booleanValue()) {
                    return;
                }
                boolean z4 = z;
                oj ojVar2 = ojVar;
                p70 p70Var = xg3Var.a;
                if (z4) {
                    p70Var.a(ojVar2);
                } else {
                    p70Var.b(ojVar2);
                }
            }
        });
    }
}
