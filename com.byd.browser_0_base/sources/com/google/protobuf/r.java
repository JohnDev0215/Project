package com.google.protobuf;

import com.google.protobuf.n;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: ExtensionRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class r extends s {
    public static final r e = new r(0);
    public final Map<a, b> d;

    /* JADX INFO: compiled from: ExtensionRegistry.java */
    public static final class a {
        public final n.a a;
        public final int b;

        public a(n.a aVar, int i) {
            this.a = aVar;
            this.b = i;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.a == aVar.a && this.b == aVar.b;
        }

        public final int hashCode() {
            return (this.a.hashCode() * 65535) + this.b;
        }
    }

    /* JADX INFO: compiled from: ExtensionRegistry.java */
    public static final class b {
    }

    public r() {
        new HashMap();
        new HashMap();
        this.d = new HashMap();
        new HashMap();
    }

    public r(int i) {
        super(s.c);
        Collections.emptyMap();
        Collections.emptyMap();
        this.d = Collections.emptyMap();
        Collections.emptyMap();
    }
}
