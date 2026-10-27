package s3;

import android.util.SparseArray;
import java.util.Set;

/* JADX INFO: renamed from: s3.K, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2372K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SparseArray f22194a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f22195b;
    public Set c;

    public final C2371J a(int i7) {
        SparseArray sparseArray = this.f22194a;
        C2371J c2371j = (C2371J) sparseArray.get(i7);
        if (c2371j != null) {
            return c2371j;
        }
        C2371J c2371j2 = new C2371J();
        sparseArray.put(i7, c2371j2);
        return c2371j2;
    }
}
