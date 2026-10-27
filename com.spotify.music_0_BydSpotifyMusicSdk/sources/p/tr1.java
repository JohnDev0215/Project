package p;

import java.lang.reflect.Method;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class tr1 extends ux2 {
    public final /* synthetic */ int g;
    public final Method h;
    public final int i;
    public final boolean j;

    public /* synthetic */ tr1(Method method, int i, boolean z, int i2) {
        this.g = i2;
        this.h = method;
        this.i = i;
        this.j = z;
    }

    public final void E(n32 n32Var, Map map) {
        int i = this.g;
        boolean z = this.j;
        Method method = this.h;
        int i2 = this.i;
        switch (i) {
            case 0:
                if (map == null) {
                    throw bk0.Q(method, i2, "Field map was null.", new Object[0]);
                }
                for (Map.Entry entry : map.entrySet()) {
                    String str = (String) entry.getKey();
                    if (str == null) {
                        throw bk0.Q(method, i2, "Field map contained null key.", new Object[0]);
                    }
                    Object value = entry.getValue();
                    if (value == null) {
                        throw bk0.Q(method, i2, u4.j("Field map contained null value for key '", str, "'."), new Object[0]);
                    }
                    String string = value.toString();
                    if (string == null) {
                        throw bk0.Q(method, i2, "Field map value '" + value + "' converted to null by " + qo.class.getName() + " for key '" + str + "'.", new Object[0]);
                    }
                    n32Var.a(str, string, z);
                }
                return;
            default:
                if (map == null) {
                    throw bk0.Q(method, i2, "Query map was null", new Object[0]);
                }
                for (Map.Entry entry2 : map.entrySet()) {
                    String str2 = (String) entry2.getKey();
                    if (str2 == null) {
                        throw bk0.Q(method, i2, "Query map contained null key.", new Object[0]);
                    }
                    Object value2 = entry2.getValue();
                    if (value2 == null) {
                        throw bk0.Q(method, i2, u4.j("Query map contained null value for key '", str2, "'."), new Object[0]);
                    }
                    String string2 = value2.toString();
                    if (string2 == null) {
                        throw bk0.Q(method, i2, "Query map value '" + value2 + "' converted to null by " + qo.class.getName() + " for key '" + str2 + "'.", new Object[0]);
                    }
                    n32Var.d(str2, string2, z);
                }
                return;
        }
    }

    @Override // p.ux2
    public final /* bridge */ /* synthetic */ void c(n32 n32Var, Object obj) {
        switch (this.g) {
            case 0:
                E(n32Var, (Map) obj);
                break;
            default:
                E(n32Var, (Map) obj);
                break;
        }
    }
}
