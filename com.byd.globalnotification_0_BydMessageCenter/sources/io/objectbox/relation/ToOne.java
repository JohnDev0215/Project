package io.objectbox.relation;

import c.a.a.a;
import java.io.Serializable;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public class ToOne<TARGET> implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public transient Field f2607a;

    public long a() {
        Field field = this.f2607a;
        if (field == null) {
            a aVar = a.f2200b;
            throw null;
        }
        try {
            Long l = (Long) field.get(null);
            if (l != null) {
                return l.longValue();
            }
            return 0L;
        } catch (IllegalAccessException unused) {
            throw new RuntimeException("Could not access field " + field);
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof ToOne) && a() == ((ToOne) obj).a();
    }

    public int hashCode() {
        long jA = a();
        return (int) (jA ^ (jA >>> 32));
    }

    public void setTargetId(long j) {
        try {
            Field field = this.f2607a;
            if (field != null) {
                field.set(null, Long.valueOf(j));
            } else {
                a aVar = a.f2200b;
                throw null;
            }
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Could not update to-one ID in entity", e2);
        }
    }
}
