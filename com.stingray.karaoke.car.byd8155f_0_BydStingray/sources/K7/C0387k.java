package K7;

import W7.AbstractC0839c;
import java.lang.reflect.Field;

/* JADX INFO: renamed from: K7.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0387k extends v0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Field f3799m;

    public C0387k(Field field) {
        A7.m.f(field, "field");
        this.f3799m = field;
    }

    @Override // K7.v0
    public final String i() {
        StringBuilder sb = new StringBuilder();
        Field field = this.f3799m;
        String name = field.getName();
        A7.m.e(name, "field.name");
        sb.append(Z7.w.a(name));
        sb.append("()");
        Class<?> type = field.getType();
        A7.m.e(type, "field.type");
        sb.append(AbstractC0839c.b(type));
        return sb.toString();
    }
}
