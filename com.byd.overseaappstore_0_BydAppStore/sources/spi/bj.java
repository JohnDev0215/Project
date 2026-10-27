package spi;

import android.content.Context;
import androidx.annotation.NonNull;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class bj<T> implements qt<T> {
    public final Collection<? extends qt<T>> b;

    public bj(@NonNull Collection<? extends qt<T>> collection) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
        }
        this.b = collection;
    }

    @SafeVarargs
    public bj(@NonNull qt<T>... qtVarArr) {
        if (qtVarArr.length == 0) {
            throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
        }
        this.b = Arrays.asList(qtVarArr);
    }

    @Override // spi.ig
    public final void a(@NonNull MessageDigest messageDigest) {
        Iterator<? extends qt<T>> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().a(messageDigest);
        }
    }

    @Override // spi.qt
    @NonNull
    public final wm<T> b(@NonNull Context context, @NonNull wm<T> wmVar, int i, int i2) {
        Iterator<? extends qt<T>> it = this.b.iterator();
        wm<T> wmVar2 = wmVar;
        while (it.hasNext()) {
            wm<T> wmVarB = it.next().b(context, wmVar2, i, i2);
            if (wmVar2 != null && !wmVar2.equals(wmVar) && !wmVar2.equals(wmVarB)) {
                wmVar2.recycle();
            }
            wmVar2 = wmVarB;
        }
        return wmVar2;
    }

    @Override // spi.ig
    public final boolean equals(Object obj) {
        if (obj instanceof bj) {
            return this.b.equals(((bj) obj).b);
        }
        return false;
    }

    @Override // spi.ig
    public final int hashCode() {
        return this.b.hashCode();
    }
}
