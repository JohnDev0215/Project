package com.google.protobuf;

import com.google.protobuf.x;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: SmallSortedMap.java */
/* JADX INFO: loaded from: classes.dex */
public final class d1 extends e1<Object, Object> {
    public d1(int i) {
        super(i);
    }

    @Override // com.google.protobuf.e1
    public final void g() {
        if (!this.h) {
            for (int i = 0; i < d(); i++) {
                Map.Entry<Object, Object> entryC = c(i);
                if (((x.b) entryC.getKey()).c()) {
                    entryC.setValue(Collections.unmodifiableList((List) entryC.getValue()));
                }
            }
            for (Map.Entry<Object, Object> entry : e()) {
                if (((x.b) entry.getKey()).c()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.g();
    }

    @Override // com.google.protobuf.e1, java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
        return put((x.b) obj, obj2);
    }
}
