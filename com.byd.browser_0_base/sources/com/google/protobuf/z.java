package com.google.protobuf;

import com.google.protobuf.b;
import com.google.protobuf.e;
import com.google.protobuf.n1;
import com.google.protobuf.r0;
import com.google.protobuf.x;
import com.google.protobuf.z;
import com.google.protobuf.z.a;
import defpackage.C0175b;
import defpackage.a72;
import defpackage.bh1;
import defpackage.dj3;
import defpackage.lk;
import defpackage.nu;
import defpackage.si3;
import defpackage.wv1;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: GeneratedMessageLite.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class z<MessageType extends z<MessageType, BuilderType>, BuilderType extends a<MessageType, BuilderType>> extends com.google.protobuf.b<MessageType, BuilderType> {
    private static Map<Object, z<?, ?>> defaultInstanceMap = new ConcurrentHashMap();
    protected k1 unknownFields = k1.f;
    protected int memoizedSerializedSize = -1;

    /* JADX INFO: compiled from: GeneratedMessageLite.java */
    public static abstract class a<MessageType extends z<MessageType, BuilderType>, BuilderType extends a<MessageType, BuilderType>> extends b.a<MessageType, BuilderType> {
        public final MessageType e;
        public MessageType f;
        public boolean g = false;

        public a(MessageType messagetype) {
            this.e = messagetype;
            this.f = (MessageType) messagetype.D(f.NEW_MUTABLE_INSTANCE, null, null);
        }

        public final void A(com.google.protobuf.g gVar, s sVar) throws IOException {
            y();
            try {
                a72 a72Var = a72.c;
                MessageType messagetype = this.f;
                a72Var.getClass();
                b1 b1VarA = a72Var.a(messagetype.getClass());
                MessageType messagetype2 = this.f;
                h hVar = gVar.c;
                if (hVar == null) {
                    hVar = new h(gVar);
                }
                b1VarA.e(messagetype2, hVar, sVar);
            } catch (RuntimeException e) {
                if (!(e.getCause() instanceof IOException)) {
                    throw e;
                }
                throw ((IOException) e.getCause());
            }
        }

        @Override // com.google.protobuf.r0.a
        public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, s sVar) throws IOException {
            A(gVar, sVar);
            return this;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            return this.e;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final Object clone() {
            a aVar = (a) this.e.D(f.NEW_BUILDER, null, null);
            aVar.z(d());
            return aVar;
        }

        @Override // com.google.protobuf.b.a
        public final b.a i(com.google.protobuf.b bVar) {
            return z((z) bVar);
        }

        @Override // com.google.protobuf.b.a
        public final b.a r(byte[] bArr, int i) throws d0 {
            s sVarA = s.a();
            y();
            try {
                a72 a72Var = a72.c;
                MessageType messagetype = this.f;
                a72Var.getClass();
                a72Var.a(messagetype.getClass()).f(this.f, bArr, 0, 0 + i, new e.a(sVarA));
                return this;
            } catch (d0 e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
            } catch (IndexOutOfBoundsException unused) {
                throw d0.k();
            }
        }

        @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
        /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
        public final MessageType build() {
            MessageType messagetype = (MessageType) d();
            if (messagetype.isInitialized()) {
                return messagetype;
            }
            throw new si3();
        }

        @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public final MessageType d() {
            if (this.g) {
                return this.f;
            }
            MessageType messagetype = this.f;
            messagetype.getClass();
            a72 a72Var = a72.c;
            a72Var.getClass();
            a72Var.a(messagetype.getClass()).c(messagetype);
            this.g = true;
            return this.f;
        }

        public final void y() {
            if (this.g) {
                MessageType messagetype = (MessageType) this.f.D(f.NEW_MUTABLE_INSTANCE, null, null);
                MessageType messagetype2 = this.f;
                a72 a72Var = a72.c;
                a72Var.getClass();
                a72Var.a(messagetype.getClass()).a(messagetype, messagetype2);
                this.f = messagetype;
                this.g = false;
            }
        }

        public final BuilderType z(MessageType messagetype) {
            y();
            MessageType messagetype2 = this.f;
            a72 a72Var = a72.c;
            a72Var.getClass();
            a72Var.a(messagetype2.getClass()).a(messagetype2, messagetype);
            return this;
        }
    }

    /* JADX INFO: compiled from: GeneratedMessageLite.java */
    public static class b<T extends z<T, ?>> extends com.google.protobuf.c<T> {
        public final T b;

        public b(T t) {
            this.b = t;
        }

        @Override // defpackage.wv1
        public final Object b(com.google.protobuf.g gVar, s sVar) {
            return z.J(this.b, gVar, sVar);
        }

        @Override // com.google.protobuf.c
        public final r0 d(byte[] bArr, int i, s sVar) {
            return z.K(this.b, bArr, i, sVar);
        }
    }

    /* JADX INFO: compiled from: GeneratedMessageLite.java */
    public static abstract class c<MessageType extends c<MessageType, BuilderType>, BuilderType> extends z<MessageType, BuilderType> implements bh1 {
        protected x<d> extensions = x.d;

        @Override // com.google.protobuf.z, defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            return (z) D(f.GET_DEFAULT_INSTANCE, null, null);
        }

        @Override // com.google.protobuf.z, com.google.protobuf.r0, com.google.protobuf.q0
        public final r0.a e() {
            return (a) D(f.NEW_BUILDER, null, null);
        }

        @Override // com.google.protobuf.z, com.google.protobuf.r0
        public final /* bridge */ /* synthetic */ r0.a h() {
            return h();
        }
    }

    /* JADX INFO: compiled from: GeneratedMessageLite.java */
    public static final class d implements x.b<d> {
        @Override // com.google.protobuf.x.b
        public final boolean G() {
            return false;
        }

        @Override // com.google.protobuf.x.b
        public final int b() {
            return 0;
        }

        @Override // com.google.protobuf.x.b
        public final boolean c() {
            return false;
        }

        @Override // java.lang.Comparable
        public final int compareTo(Object obj) {
            ((d) obj).getClass();
            return 0;
        }

        @Override // com.google.protobuf.x.b
        public final n1.a d() {
            return null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.protobuf.x.b
        public final r0.a t(r0.a aVar, r0 r0Var) {
            a aVar2 = (a) aVar;
            aVar2.z((z) r0Var);
            return aVar2;
        }

        @Override // com.google.protobuf.x.b
        public final n1.b w() {
            throw null;
        }
    }

    /* JADX INFO: compiled from: GeneratedMessageLite.java */
    public static class e<ContainingType extends r0, Type> extends q<ContainingType, Type> {
    }

    /* JADX INFO: compiled from: GeneratedMessageLite.java */
    public enum f {
        GET_MEMOIZED_IS_INITIALIZED,
        SET_MEMOIZED_IS_INITIALIZED,
        BUILD_MESSAGE_INFO,
        NEW_MUTABLE_INSTANCE,
        NEW_BUILDER,
        GET_DEFAULT_INSTANCE,
        GET_PARSER
    }

    /* JADX INFO: compiled from: GeneratedMessageLite.java */
    public static final class g implements Serializable {
        private static final long serialVersionUID = 0;
        public final Class<?> e;
        public final String f;
        public final byte[] g;

        public g(r0 r0Var) {
            Class<?> cls = r0Var.getClass();
            this.e = cls;
            this.f = cls.getName();
            this.g = r0Var.q();
        }

        public Object readResolve() {
            Class<?> cls;
            Class<?> cls2 = this.e;
            byte[] bArr = this.g;
            String str = this.f;
            if (cls2 != null) {
                cls = cls2;
            } else {
                try {
                    cls = Class.forName(str);
                } catch (d0 e) {
                    throw new RuntimeException("Unable to understand proto buffer", e);
                } catch (ClassNotFoundException e2) {
                    throw new RuntimeException(C0175b.j("Unable to find proto buffer class: ", str), e2);
                } catch (IllegalAccessException e3) {
                    throw new RuntimeException("Unable to call parsePartialFrom", e3);
                } catch (NoSuchFieldException unused) {
                    if (cls2 == null) {
                        try {
                            cls2 = Class.forName(str);
                        } catch (d0 e4) {
                            throw new RuntimeException("Unable to understand proto buffer", e4);
                        } catch (ClassNotFoundException e5) {
                            throw new RuntimeException(C0175b.j("Unable to find proto buffer class: ", str), e5);
                        } catch (IllegalAccessException e6) {
                            throw new RuntimeException("Unable to call parsePartialFrom", e6);
                        } catch (NoSuchFieldException e7) {
                            throw new RuntimeException(C0175b.j("Unable to find defaultInstance in ", str), e7);
                        } catch (SecurityException e8) {
                            throw new RuntimeException(C0175b.j("Unable to call defaultInstance in ", str), e8);
                        }
                    }
                    Field declaredField = cls2.getDeclaredField("defaultInstance");
                    declaredField.setAccessible(true);
                    return ((r0) declaredField.get(null)).e().t(bArr).d();
                } catch (SecurityException e9) {
                    throw new RuntimeException(C0175b.j("Unable to call DEFAULT_INSTANCE in ", str), e9);
                }
            }
            Field declaredField2 = cls.getDeclaredField("DEFAULT_INSTANCE");
            declaredField2.setAccessible(true);
            return ((r0) declaredField2.get(null)).e().t(bArr).d();
        }
    }

    public static void B(z zVar) throws d0 {
        if (zVar.isInitialized()) {
            return;
        }
        d0 d0Var = new d0(new si3().getMessage());
        d0Var.e = zVar;
        throw d0Var;
    }

    public static <T extends z<?, ?>> T E(Class<T> cls) {
        z<?, ?> zVar = defaultInstanceMap.get(cls);
        if (zVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zVar = defaultInstanceMap.get(cls);
            } catch (ClassNotFoundException e2) {
                throw new IllegalStateException("Class initialization cannot fail.", e2);
            }
        }
        if (zVar == null) {
            zVar = (T) ((z) dj3.a(cls)).D(f.GET_DEFAULT_INSTANCE, null, null);
            if (zVar == null) {
                throw new IllegalStateException();
            }
            defaultInstanceMap.put(cls, zVar);
        }
        return (T) zVar;
    }

    public static Object F(Object obj, Method method, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e2);
        } catch (InvocationTargetException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    public static <T extends z<T, ?>> T H(T t, lk lkVar) throws d0 {
        s sVarA = s.a();
        try {
            com.google.protobuf.g gVarT = lkVar.t();
            T t2 = (T) J(t, gVarT, sVarA);
            try {
                gVarT.a(0);
                B(t2);
                B(t2);
                return t2;
            } catch (d0 e2) {
                e2.e = t2;
                throw e2;
            }
        } catch (d0 e3) {
            throw e3;
        }
    }

    public static <T extends z<T, ?>> T I(T t, byte[] bArr) throws d0 {
        T t2 = (T) K(t, bArr, bArr.length, s.a());
        B(t2);
        return t2;
    }

    public static <T extends z<T, ?>> T J(T t, com.google.protobuf.g gVar, s sVar) throws d0 {
        T t2 = (T) t.D(f.NEW_MUTABLE_INSTANCE, null, null);
        try {
            a72 a72Var = a72.c;
            a72Var.getClass();
            b1 b1VarA = a72Var.a(t2.getClass());
            h hVar = gVar.c;
            if (hVar == null) {
                hVar = new h(gVar);
            }
            b1VarA.e(t2, hVar, sVar);
            b1VarA.c(t2);
            return t2;
        } catch (d0 e2) {
            e2.e = t2;
            throw e2;
        } catch (IOException e3) {
            if (e3.getCause() instanceof d0) {
                throw ((d0) e3.getCause());
            }
            d0 d0Var = new d0(e3);
            d0Var.e = t2;
            throw d0Var;
        } catch (RuntimeException e4) {
            if (e4.getCause() instanceof d0) {
                throw ((d0) e4.getCause());
            }
            throw e4;
        }
    }

    public static z K(z zVar, byte[] bArr, int i, s sVar) throws d0 {
        z zVar2 = (z) zVar.D(f.NEW_MUTABLE_INSTANCE, null, null);
        try {
            a72 a72Var = a72.c;
            a72Var.getClass();
            b1 b1VarA = a72Var.a(zVar2.getClass());
            b1VarA.f(zVar2, bArr, 0, i + 0, new e.a(sVar));
            b1VarA.c(zVar2);
            if (zVar2.memoizedHashCode == 0) {
                return zVar2;
            }
            throw new RuntimeException();
        } catch (d0 e2) {
            e2.e = zVar2;
            throw e2;
        } catch (IOException e3) {
            if (e3.getCause() instanceof d0) {
                throw ((d0) e3.getCause());
            }
            d0 d0Var = new d0(e3);
            d0Var.e = zVar2;
            throw d0Var;
        } catch (IndexOutOfBoundsException unused) {
            d0 d0VarK = d0.k();
            d0VarK.e = zVar2;
            throw d0VarK;
        }
    }

    public static <T extends z<?, ?>> void M(Class<T> cls, T t) {
        defaultInstanceMap.put(cls, t);
    }

    @Override // com.google.protobuf.b
    public final void A(int i) {
        this.memoizedSerializedSize = i;
    }

    public final Object C(f fVar) {
        return D(fVar, null, null);
    }

    public abstract Object D(f fVar, Object obj, Object obj2);

    @Override // com.google.protobuf.r0
    /* JADX INFO: renamed from: N, reason: merged with bridge method [inline-methods] */
    public final BuilderType h() {
        BuilderType buildertype = (BuilderType) D(f.NEW_BUILDER, null, null);
        buildertype.z(this);
        return buildertype;
    }

    @Override // defpackage.bh1, com.google.protobuf.t0
    public r0 b() {
        return (z) D(f.GET_DEFAULT_INSTANCE, null, null);
    }

    @Override // com.google.protobuf.r0, com.google.protobuf.q0
    public r0.a e() {
        return (a) D(f.NEW_BUILDER, null, null);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        a72 a72Var = a72.c;
        a72Var.getClass();
        return a72Var.a(getClass()).g(this, (z) obj);
    }

    public final int hashCode() {
        int i = this.memoizedHashCode;
        if (i != 0) {
            return i;
        }
        a72 a72Var = a72.c;
        a72Var.getClass();
        int iJ = a72Var.a(getClass()).j(this);
        this.memoizedHashCode = iJ;
        return iJ;
    }

    @Override // defpackage.bh1
    public final boolean isInitialized() {
        byte bByteValue = ((Byte) D(f.GET_MEMOIZED_IS_INITIALIZED, null, null)).byteValue();
        if (bByteValue == 1) {
            return true;
        }
        if (bByteValue == 0) {
            return false;
        }
        a72 a72Var = a72.c;
        a72Var.getClass();
        boolean zD = a72Var.a(getClass()).d(this);
        D(f.SET_MEMOIZED_IS_INITIALIZED, zD ? this : null, null);
        return zD;
    }

    @Override // com.google.protobuf.r0
    public final int m() {
        if (this.memoizedSerializedSize == -1) {
            a72 a72Var = a72.c;
            a72Var.getClass();
            this.memoizedSerializedSize = a72Var.a(getClass()).h(this);
        }
        return this.memoizedSerializedSize;
    }

    @Override // com.google.protobuf.r0
    public final void p(nu nuVar) {
        a72 a72Var = a72.c;
        a72Var.getClass();
        b1 b1VarA = a72Var.a(getClass());
        i iVar = nuVar.k;
        if (iVar == null) {
            iVar = new i(nuVar);
        }
        b1VarA.b(this, iVar);
    }

    public final String toString() {
        String string = super.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(string);
        s0.c(this, sb, 0);
        return sb.toString();
    }

    @Override // com.google.protobuf.r0
    public final wv1<MessageType> u() {
        return (wv1) C(f.GET_PARSER);
    }

    @Override // com.google.protobuf.b
    public final int x() {
        return this.memoizedSerializedSize;
    }
}
