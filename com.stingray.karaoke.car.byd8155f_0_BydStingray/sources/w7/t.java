package W7;

import java.lang.reflect.Field;
import java.lang.reflect.Member;

/* JADX INFO: loaded from: classes.dex */
public final class t extends v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Field f9897a;

    public t(Field field) {
        A7.m.f(field, "member");
        this.f9897a = field;
    }

    @Override // W7.v
    public final Member b() {
        return this.f9897a;
    }
}
