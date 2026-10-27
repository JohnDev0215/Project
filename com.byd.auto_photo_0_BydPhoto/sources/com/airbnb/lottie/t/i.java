package com.airbnb.lottie.t;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.util.Pair;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class i<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    T f801a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Nullable
    T f802b;

    private static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public void b(T t, T t2) {
        this.f801a = t;
        this.f802b = t2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        return a(pair.first, this.f801a) && a(pair.second, this.f802b);
    }

    public int hashCode() {
        T t = this.f801a;
        int iHashCode = t == null ? 0 : t.hashCode();
        T t2 = this.f802b;
        return iHashCode ^ (t2 != null ? t2.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.f801a) + " " + String.valueOf(this.f802b) + "}";
    }
}
