package androidx.core.graphics;

import android.graphics.Insets;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    @NonNull
    public static final b e = new b(0, 0, 0, 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f480a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f481b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f482c;
    public final int d;

    private b(int i, int i2, int i3, int i4) {
        this.f480a = i;
        this.f481b = i2;
        this.f482c = i3;
        this.d = i4;
    }

    @NonNull
    public static b a(int i, int i2, int i3, int i4) {
        return (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) ? e : new b(i, i2, i3, i4);
    }

    @NonNull
    @RequiresApi(api = 29)
    public Insets b() {
        return Insets.of(this.f480a, this.f481b, this.f482c, this.d);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.d == bVar.d && this.f480a == bVar.f480a && this.f482c == bVar.f482c && this.f481b == bVar.f481b;
    }

    public int hashCode() {
        return (((((this.f480a * 31) + this.f481b) * 31) + this.f482c) * 31) + this.d;
    }

    public String toString() {
        return "Insets{left=" + this.f480a + ", top=" + this.f481b + ", right=" + this.f482c + ", bottom=" + this.d + '}';
    }
}
