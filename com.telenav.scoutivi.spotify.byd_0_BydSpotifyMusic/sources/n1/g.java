package n1;

import android.content.Context;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import q1.v;

/* JADX INFO: compiled from: MultiTransformation.java */
/* JADX INFO: loaded from: classes.dex */
public class g<T> implements m<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Collection<? extends m<T>> f8505b;

    @SafeVarargs
    public g(m<T>... mVarArr) {
        if (mVarArr.length == 0) {
            throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
        }
        this.f8505b = Arrays.asList(mVarArr);
    }

    @Override // n1.f
    public void a(MessageDigest messageDigest) {
        Iterator<? extends m<T>> it = this.f8505b.iterator();
        while (it.hasNext()) {
            it.next().a(messageDigest);
        }
    }

    @Override // n1.m
    public v<T> b(Context context, v<T> vVar, int i7, int i8) {
        Iterator<? extends m<T>> it = this.f8505b.iterator();
        v<T> vVar2 = vVar;
        while (it.hasNext()) {
            v<T> vVarB = it.next().b(context, vVar2, i7, i8);
            if (vVar2 != null && !vVar2.equals(vVar) && !vVar2.equals(vVarB)) {
                vVar2.a();
            }
            vVar2 = vVarB;
        }
        return vVar2;
    }

    @Override // n1.f
    public boolean equals(Object obj) {
        if (obj instanceof g) {
            return this.f8505b.equals(((g) obj).f8505b);
        }
        return false;
    }

    @Override // n1.f
    public int hashCode() {
        return this.f8505b.hashCode();
    }
}
