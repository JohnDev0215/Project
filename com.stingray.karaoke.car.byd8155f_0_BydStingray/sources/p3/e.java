package p3;

import android.util.SparseBooleanArray;
import java.util.ArrayList;
import u.C2465e;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c f20885e = new c();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f20886a;
    public final d d;
    public final SparseBooleanArray c = new SparseBooleanArray();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2465e f20887b = new C2465e(0);

    public e(ArrayList arrayList, ArrayList arrayList2) {
        this.f20886a = arrayList;
        int size = arrayList.size();
        int i7 = Integer.MIN_VALUE;
        d dVar = null;
        for (int i10 = 0; i10 < size; i10++) {
            d dVar2 = (d) arrayList.get(i10);
            int i11 = dVar2.f20880e;
            if (i11 > i7) {
                dVar = dVar2;
                i7 = i11;
            }
        }
        this.d = dVar;
    }

    public final d a(f fVar) {
        return (d) this.f20887b.get(fVar);
    }
}
