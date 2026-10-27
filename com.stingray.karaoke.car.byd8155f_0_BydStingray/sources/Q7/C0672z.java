package Q7;

import java.util.ArrayList;

/* JADX INFO: renamed from: Q7.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0672z extends S {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f7210a;

    public C0672z(ArrayList arrayList) {
        this.f7210a = arrayList;
        if (n7.z.x(arrayList).size() != arrayList.size()) {
            throw new IllegalArgumentException("Some properties have the same names");
        }
    }

    public final String toString() {
        return "MultiFieldValueClassRepresentation(underlyingPropertyNamesToTypes=" + this.f7210a + ')';
    }
}
