package E5;

import io.sentry.C1489n1;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class K extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f1776a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String[] f1777b;
    public final Enum[] c;
    public final C1489n1 d;

    public K(Class cls) {
        this.f1776a = cls;
        try {
            Enum[] enumArr = (Enum[]) cls.getEnumConstants();
            this.c = enumArr;
            this.f1777b = new String[enumArr.length];
            int i7 = 0;
            while (true) {
                Enum[] enumArr2 = this.c;
                if (i7 >= enumArr2.length) {
                    this.d = C1489n1.z(this.f1777b);
                    return;
                }
                String strName = enumArr2[i7].name();
                String[] strArr = this.f1777b;
                Field field = cls.getField(strName);
                Set set = G5.f.f2685a;
                o oVar = (o) field.getAnnotation(o.class);
                if (oVar != null) {
                    String strName2 = oVar.name();
                    if (!"\u0000".equals(strName2)) {
                        strName = strName2;
                    }
                }
                strArr[i7] = strName;
                i7++;
            }
        } catch (NoSuchFieldException e10) {
            throw new AssertionError("Missing field in ".concat(cls.getName()), e10);
        }
    }

    @Override // E5.q
    public final Object a(u uVar) {
        int iG = uVar.G(this.d);
        if (iG != -1) {
            return this.c[iG];
        }
        String strK = uVar.k();
        throw new A9.k("Expected one of " + Arrays.asList(this.f1777b) + " but was " + uVar.y() + " at path " + strK);
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        xVar.v(this.f1777b[((Enum) obj).ordinal()]);
    }

    public final String toString() {
        return "JsonAdapter(" + this.f1776a.getName() + ")";
    }
}
