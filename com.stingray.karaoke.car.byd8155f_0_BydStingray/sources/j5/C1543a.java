package j5;

import java.lang.reflect.Field;

/* JADX INFO: renamed from: j5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public enum C1543a extends EnumC1550h {
    public C1543a() {
        super("IDENTITY", 0);
    }

    @Override // j5.EnumC1550h
    public final String b(Field field) {
        return field.getName();
    }
}
