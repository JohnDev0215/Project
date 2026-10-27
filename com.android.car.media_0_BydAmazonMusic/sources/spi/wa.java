package spi;

import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: TextOEMV1.java */
/* JADX INFO: loaded from: classes2.dex */
public final class wa {
    public final List<CharSequence> a;

    /* JADX INFO: compiled from: TextOEMV1.java */
    public static final class b {
        public final List<CharSequence> a;

        public b(CharSequence charSequence) {
            this((List<CharSequence>) Collections.singletonList(charSequence));
        }

        public b(List<CharSequence> list) {
            this.a = list;
        }
    }

    private wa(b bVar) {
        this.a = bVar.a;
    }
}
