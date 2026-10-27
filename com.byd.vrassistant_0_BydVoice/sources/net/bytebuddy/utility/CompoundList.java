package net.bytebuddy.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CompoundList {
    private CompoundList() {
        throw new UnsupportedOperationException("Cannot create a compound list");
    }

    public static <S> List<S> of(S s, List<? extends S> list) {
        return of(Collections.singletonList(s), (List) list);
    }

    public static <S> List<S> of(List<? extends S> list, S s) {
        return of((List) list, Collections.singletonList(s));
    }

    public static <S> List<S> of(List<? extends S> list, List<? extends S> list2) {
        ArrayList arrayList = new ArrayList(list.size() + list2.size());
        arrayList.addAll(list);
        arrayList.addAll(list2);
        return arrayList;
    }
}
