package com.google.protobuf;

import com.google.protobuf.z;
import defpackage.xi0;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: ExtensionRegistryLite.java */
/* JADX INFO: loaded from: classes.dex */
public class s {
    public static volatile s b;
    public static final s c = new s(0);
    public final Map<a, z.e<?, ?>> a;

    /* JADX INFO: compiled from: ExtensionRegistryLite.java */
    public static final class a {
        public final Object a;
        public final int b;

        public a(int i, Object obj) {
            this.a = obj;
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
            return (System.identityHashCode(this.a) * 65535) + this.b;
        }
    }

    public s() {
        this.a = new HashMap();
    }

    public static s a() {
        s sVar = b;
        if (sVar == null) {
            synchronized (s.class) {
                sVar = b;
                if (sVar == null) {
                    Class<?> cls = xi0.a;
                    s sVar2 = null;
                    if (cls != null) {
                        try {
                            sVar2 = (s) cls.getDeclaredMethod("getEmptyRegistry", new Class[0]).invoke(null, new Object[0]);
                        } catch (Exception unused) {
                        }
                    }
                    if (sVar2 == null) {
                        sVar2 = c;
                    }
                    b = sVar2;
                    sVar = sVar2;
                }
            }
        }
        return sVar;
    }

    public s(s sVar) {
        if (sVar == c) {
            this.a = Collections.emptyMap();
        } else {
            this.a = Collections.unmodifiableMap(sVar.a);
        }
    }

    public s(int i) {
        this.a = Collections.emptyMap();
    }
}
