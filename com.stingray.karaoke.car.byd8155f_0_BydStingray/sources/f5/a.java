package F5;

import A9.k;
import E5.o;
import E5.q;
import E5.u;
import E5.x;
import G5.f;
import io.sentry.C1489n1;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class a extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f2419a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String[] f2420b;
    public final Enum[] c;
    public final C1489n1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f2421e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Enum f2422f;

    public a(Class cls, Enum r52, boolean z6) {
        this.f2419a = cls;
        this.f2422f = r52;
        this.f2421e = z6;
        try {
            Enum[] enumArr = (Enum[]) cls.getEnumConstants();
            this.c = enumArr;
            this.f2420b = new String[enumArr.length];
            int i7 = 0;
            while (true) {
                Enum[] enumArr2 = this.c;
                if (i7 >= enumArr2.length) {
                    this.d = C1489n1.z(this.f2420b);
                    return;
                }
                String strName = enumArr2[i7].name();
                String[] strArr = this.f2420b;
                Field field = cls.getField(strName);
                Set set = f.f2685a;
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

    public static a e(Class cls) {
        return new a(cls, null, false);
    }

    @Override // E5.q
    public final Object a(u uVar) {
        int iG = uVar.G(this.d);
        if (iG != -1) {
            return this.c[iG];
        }
        String strK = uVar.k();
        if (this.f2421e) {
            if (uVar.z() == 6) {
                uVar.K();
                return this.f2422f;
            }
            throw new k("Expected a string but was " + A7.k.w(uVar.z()) + " at path " + strK);
        }
        throw new k("Expected one of " + Arrays.asList(this.f2420b) + " but was " + uVar.y() + " at path " + strK);
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        Enum r22 = (Enum) obj;
        if (r22 == null) {
            throw new NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.");
        }
        xVar.v(this.f2420b[r22.ordinal()]);
    }

    public final a f(Enum r32) {
        return new a(this.f2419a, r32, true);
    }

    public final String toString() {
        return "EnumJsonAdapter(" + this.f2419a.getName() + ")";
    }
}
