package spi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class yi {
    public Class<?> a;
    public Class<?> b;
    public Class<?> c;

    public yi() {
    }

    public yi(@NonNull Class<?> cls, @NonNull Class<?> cls2) {
        a(cls, cls2, null);
    }

    public yi(@NonNull Class<?> cls, @NonNull Class<?> cls2, @Nullable Class<?> cls3) {
        a(cls, cls2, cls3);
    }

    public final void a(@NonNull Class<?> cls, @NonNull Class<?> cls2, @Nullable Class<?> cls3) {
        this.a = cls;
        this.b = cls2;
        this.c = cls3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || yi.class != obj.getClass()) {
            return false;
        }
        yi yiVar = (yi) obj;
        return this.a.equals(yiVar.a) && this.b.equals(yiVar.b) && tu.b(this.c, yiVar.c);
    }

    public final int hashCode() {
        int iHashCode = (this.b.hashCode() + (this.a.hashCode() * 31)) * 31;
        Class<?> cls = this.c;
        return iHashCode + (cls != null ? cls.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sbK = e.k("MultiClassKey{first=");
        sbK.append(this.a);
        sbK.append(", second=");
        sbK.append(this.b);
        sbK.append('}');
        return sbK.toString();
    }
}
