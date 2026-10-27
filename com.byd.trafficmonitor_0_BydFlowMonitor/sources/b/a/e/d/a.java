package b.a.e.d;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: compiled from: ActivityResultContract.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<I, O> {

    /* JADX INFO: renamed from: b.a.e.d.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityResultContract.java */
    public static final class C0013a<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @SuppressLint({"UnknownNullness"})
        public final T f1423a;

        public C0013a(@SuppressLint({"UnknownNullness"}) T t) {
            this.f1423a = t;
        }

        @SuppressLint({"UnknownNullness"})
        public T a() {
            return this.f1423a;
        }
    }

    public abstract Intent a(Context context, @SuppressLint({"UnknownNullness"}) I i2);

    public C0013a<O> b(Context context, @SuppressLint({"UnknownNullness"}) I i2) {
        return null;
    }

    @SuppressLint({"UnknownNullness"})
    public abstract O c(int i2, Intent intent);
}
