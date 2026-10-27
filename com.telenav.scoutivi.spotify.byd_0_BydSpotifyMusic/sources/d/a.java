package d;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: compiled from: ActivityResultContract.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<I, O> {

    /* JADX INFO: renamed from: d.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityResultContract.java */
    public static final class C0061a<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @SuppressLint({"UnknownNullness"})
        private final T f5605a;

        public C0061a(@SuppressLint({"UnknownNullness"}) T t6) {
            this.f5605a = t6;
        }

        @SuppressLint({"UnknownNullness"})
        public T a() {
            return this.f5605a;
        }
    }

    public abstract Intent a(Context context, @SuppressLint({"UnknownNullness"}) I i7);

    public C0061a<O> b(Context context, @SuppressLint({"UnknownNullness"}) I i7) {
        return null;
    }

    @SuppressLint({"UnknownNullness"})
    public abstract O c(int i7, Intent intent);
}
