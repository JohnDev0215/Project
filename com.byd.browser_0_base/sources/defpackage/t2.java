package defpackage;

import android.content.Intent;
import androidx.activity.ComponentActivity;

/* JADX INFO: compiled from: ActivityResultContract.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class t2<I, O> {

    /* JADX INFO: compiled from: ActivityResultContract.kt */
    public static final class a<T> {
        public final T a;

        public a(T t) {
            this.a = t;
        }
    }

    public abstract Intent a(ComponentActivity componentActivity, Object obj);

    public a b(ComponentActivity componentActivity, Object obj) {
        i21.e(componentActivity, "context");
        return null;
    }

    public abstract Object c(Intent intent, int i);
}
