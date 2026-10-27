package L7;

import java.util.Arrays;
import java.util.Map;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class c extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final c f4049m = new c(1);

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        String string;
        Map.Entry entry = (Map.Entry) obj;
        A7.m.f(entry, "entry");
        String str = (String) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof boolean[]) {
            string = Arrays.toString((boolean[]) value);
            A7.m.e(string, "toString(this)");
        } else if (value instanceof char[]) {
            string = Arrays.toString((char[]) value);
            A7.m.e(string, "toString(this)");
        } else if (value instanceof byte[]) {
            string = Arrays.toString((byte[]) value);
            A7.m.e(string, "toString(this)");
        } else if (value instanceof short[]) {
            string = Arrays.toString((short[]) value);
            A7.m.e(string, "toString(this)");
        } else if (value instanceof int[]) {
            string = Arrays.toString((int[]) value);
            A7.m.e(string, "toString(this)");
        } else if (value instanceof float[]) {
            string = Arrays.toString((float[]) value);
            A7.m.e(string, "toString(this)");
        } else if (value instanceof long[]) {
            string = Arrays.toString((long[]) value);
            A7.m.e(string, "toString(this)");
        } else if (value instanceof double[]) {
            string = Arrays.toString((double[]) value);
            A7.m.e(string, "toString(this)");
        } else if (value instanceof Object[]) {
            string = Arrays.toString((Object[]) value);
            A7.m.e(string, "toString(this)");
        } else {
            string = value.toString();
        }
        return str + '=' + string;
    }
}
