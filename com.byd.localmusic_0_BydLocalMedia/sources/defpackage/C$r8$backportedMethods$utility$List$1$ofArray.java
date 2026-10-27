package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: renamed from: $r8$backportedMethods$utility$List$1$ofArray, reason: invalid class name */
/* JADX INFO: loaded from: classes.dex */
public /* synthetic */ class C$r8$backportedMethods$utility$List$1$ofArray {
    public static /* synthetic */ List of(Object[] objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            arrayList.add(Objects.requireNonNull(obj));
        }
        return Collections.unmodifiableList(arrayList);
    }
}
