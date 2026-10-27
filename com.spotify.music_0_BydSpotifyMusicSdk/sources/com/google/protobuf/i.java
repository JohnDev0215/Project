package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import p.au;
import p.cv0;
import p.dr2;
import p.ds1;
import p.e51;
import p.h01;
import p.j01;
import p.j13;
import p.k01;
import p.k72;
import p.km;
import p.l51;
import p.m51;
import p.o02;
import p.o51;
import p.p01;
import p.pl0;
import p.q1;
import p.q51;
import p.r51;
import p.rl0;
import p.ry1;
import p.ta0;
import p.ty1;
import p.u4;
import p.u51;
import p.v51;
import p.vd1;
import p.wj1;

/* JADX INFO: loaded from: classes.dex */
public abstract class i extends a {
    private static final int MEMOIZED_SERIALIZED_SIZE_MASK = Integer.MAX_VALUE;
    private static final int MUTABLE_FLAG_MASK = Integer.MIN_VALUE;
    static final int UNINITIALIZED_HASH_CODE = 0;
    static final int UNINITIALIZED_SERIALIZED_SIZE = Integer.MAX_VALUE;
    private static Map<Object, i> defaultInstanceMap = new ConcurrentHashMap();
    private int memoizedSerializedSize;
    protected o unknownFields;

    public i() {
        this.memoizedHashCode = 0;
        this.memoizedSerializedSize = -1;
        this.unknownFields = o.f;
    }

    public static k01 access$000(pl0 pl0Var) {
        pl0Var.getClass();
        return (k01) pl0Var;
    }

    public static void b(i iVar) throws InvalidProtocolBufferException {
        if (iVar == null || iVar.isInitialized()) {
            return;
        }
        UninitializedMessageException uninitializedMessageExceptionNewUninitializedMessageException = iVar.newUninitializedMessageException();
        uninitializedMessageExceptionNewUninitializedMessageException.getClass();
        InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException(uninitializedMessageExceptionNewUninitializedMessageException.getMessage());
        invalidProtocolBufferException.i(iVar);
        throw invalidProtocolBufferException;
    }

    public static i c(i iVar, InputStream inputStream, rl0 rl0Var) throws InvalidProtocolBufferException {
        try {
            int i = inputStream.read();
            if (i == -1) {
                return null;
            }
            au auVarG = au.g(new q1(au.t(i, inputStream), inputStream));
            i partialFrom = parsePartialFrom(iVar, auVarG, rl0Var);
            try {
                auVarG.a(0);
                return partialFrom;
            } catch (InvalidProtocolBufferException e) {
                e.i(partialFrom);
                throw e;
            }
        } catch (InvalidProtocolBufferException e2) {
            if (e2.a()) {
                throw new InvalidProtocolBufferException(e2);
            }
            throw e2;
        } catch (IOException e3) {
            throw new InvalidProtocolBufferException(e3);
        }
    }

    public static i d(i iVar, byte[] bArr, int i, int i2, rl0 rl0Var) throws InvalidProtocolBufferException {
        i iVarNewMutableInstance = iVar.newMutableInstance();
        try {
            k72 k72VarB = ry1.c.b(iVarNewMutableInstance);
            k72VarB.b(iVarNewMutableInstance, bArr, i, i + i2, new j13(rl0Var));
            k72VarB.c(iVarNewMutableInstance);
            return iVarNewMutableInstance;
        } catch (InvalidProtocolBufferException e) {
            e = e;
            if (e.a()) {
                e = new InvalidProtocolBufferException(e);
            }
            e.i(iVarNewMutableInstance);
            throw e;
        } catch (UninitializedMessageException e2) {
            InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException(e2.getMessage());
            invalidProtocolBufferException.i(iVarNewMutableInstance);
            throw invalidProtocolBufferException;
        } catch (IOException e3) {
            if (e3.getCause() instanceof InvalidProtocolBufferException) {
                throw ((InvalidProtocolBufferException) e3.getCause());
            }
            InvalidProtocolBufferException invalidProtocolBufferException2 = new InvalidProtocolBufferException(e3);
            invalidProtocolBufferException2.i(iVarNewMutableInstance);
            throw invalidProtocolBufferException2;
        } catch (IndexOutOfBoundsException unused) {
            InvalidProtocolBufferException invalidProtocolBufferExceptionJ = InvalidProtocolBufferException.j();
            invalidProtocolBufferExceptionJ.i(iVarNewMutableInstance);
            throw invalidProtocolBufferExceptionJ;
        }
    }

    public static l51 emptyBooleanList() {
        return km.l;
    }

    public static m51 emptyDoubleList() {
        return ta0.l;
    }

    public static q51 emptyFloatList() {
        return cv0.l;
    }

    public static r51 emptyIntList() {
        return e51.l;
    }

    public static u51 emptyLongList() {
        return vd1.l;
    }

    public static <E> v51 emptyProtobufList() {
        return ty1.l;
    }

    public static <T extends i> T getDefaultInstance(Class<T> cls) {
        i iVar = defaultInstanceMap.get(cls);
        if (iVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                iVar = defaultInstanceMap.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (iVar == null) {
            iVar = (T) ((i) dr2.b(cls)).getDefaultInstanceForType();
            if (iVar == null) {
                throw new IllegalStateException();
            }
            defaultInstanceMap.put(cls, iVar);
        }
        return (T) iVar;
    }

    public static Method getMethodOrDie(Class cls, String str, Class... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Generated message class \"" + cls.getName() + "\" missing method \"" + str + "\".", e);
        }
    }

    public static Object invokeOrDie(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    public static q51 mutableCopy(q51 q51Var) {
        cv0 cv0Var = (cv0) q51Var;
        int i = cv0Var.k;
        int i2 = i == 0 ? 10 : i * 2;
        if (i2 >= i) {
            return new cv0(cv0Var.k, Arrays.copyOf(cv0Var.e, i2));
        }
        throw new IllegalArgumentException();
    }

    public static Object newMessageInfo(wj1 wj1Var, String str, Object[] objArr) {
        return new o02(wj1Var, str, objArr);
    }

    public static <ContainingType extends wj1, Type> k01 newRepeatedGeneratedExtension(ContainingType containingtype, wj1 wj1Var, o51 o51Var, int i, WireFormat$FieldType wireFormat$FieldType, boolean z, Class cls) {
        return new k01(containingtype, Collections.emptyList(), wj1Var, new j01(o51Var, i, wireFormat$FieldType, true, z));
    }

    public static <ContainingType extends wj1, Type> k01 newSingularGeneratedExtension(ContainingType containingtype, Type type, wj1 wj1Var, o51 o51Var, int i, WireFormat$FieldType wireFormat$FieldType, Class cls) {
        return new k01(containingtype, type, wj1Var, new j01(o51Var, i, wireFormat$FieldType, false, false));
    }

    public static <T extends i> T parseDelimitedFrom(T t, InputStream inputStream) throws InvalidProtocolBufferException {
        T t2 = (T) c(t, inputStream, rl0.a());
        b(t2);
        return t2;
    }

    public static <T extends i> T parseFrom(T t, ByteBuffer byteBuffer, rl0 rl0Var) throws InvalidProtocolBufferException {
        T t2 = (T) parseFrom(t, au.h(byteBuffer, false), rl0Var);
        b(t2);
        return t2;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static <T extends i> T parsePartialFrom(T t, au auVar, rl0 rl0Var) throws InvalidProtocolBufferException {
        T t2 = (T) t.newMutableInstance();
        try {
            k72 k72VarB = ry1.c.b(t2);
            e eVar = auVar.d;
            if (eVar == null) {
                eVar = new e(auVar);
            }
            k72VarB.g(t2, eVar, rl0Var);
            k72VarB.c(t2);
            return t2;
        } catch (InvalidProtocolBufferException e) {
            e = e;
            if (e.a()) {
                e = new InvalidProtocolBufferException(e);
            }
            e.i(t2);
            throw e;
        } catch (UninitializedMessageException e2) {
            InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException(e2.getMessage());
            invalidProtocolBufferException.i(t2);
            throw invalidProtocolBufferException;
        } catch (IOException e3) {
            if (e3.getCause() instanceof InvalidProtocolBufferException) {
                throw ((InvalidProtocolBufferException) e3.getCause());
            }
            InvalidProtocolBufferException invalidProtocolBufferException2 = new InvalidProtocolBufferException(e3);
            invalidProtocolBufferException2.i(t2);
            throw invalidProtocolBufferException2;
        } catch (RuntimeException e4) {
            if (e4.getCause() instanceof InvalidProtocolBufferException) {
                throw ((InvalidProtocolBufferException) e4.getCause());
            }
            throw e4;
        }
    }

    public static <T extends i> void registerDefaultInstance(Class<T> cls, T t) {
        t.markImmutable();
        defaultInstanceMap.put(cls, t);
    }

    public Object buildMessageInfo() {
        return dynamicMethod(GeneratedMessageLite$MethodToInvoke.k);
    }

    public void clearMemoizedHashCode() {
        this.memoizedHashCode = 0;
    }

    public void clearMemoizedSerializedSize() {
        setMemoizedSerializedSize(Integer.MAX_VALUE);
    }

    public int computeHashCode() {
        ry1 ry1Var = ry1.c;
        ry1Var.getClass();
        return ry1Var.a(getClass()).j(this);
    }

    public final <MessageType extends i, BuilderType extends h01> BuilderType createBuilder() {
        return (BuilderType) dynamicMethod(GeneratedMessageLite$MethodToInvoke.m);
    }

    public Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj) {
        return dynamicMethod(generatedMessageLite$MethodToInvoke, obj, null);
    }

    public abstract Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2);

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ry1 ry1Var = ry1.c;
        ry1Var.getClass();
        return ry1Var.a(getClass()).f(this, (i) obj);
    }

    public int getMemoizedHashCode() {
        return this.memoizedHashCode;
    }

    public int getMemoizedSerializedSize() {
        return this.memoizedSerializedSize & Integer.MAX_VALUE;
    }

    public final ds1 getParserForType() {
        return (ds1) dynamicMethod(GeneratedMessageLite$MethodToInvoke.o);
    }

    @Override // com.google.protobuf.a
    public int getSerializedSize(k72 k72Var) {
        int iH;
        int iH2;
        if (isMutable()) {
            if (k72Var == null) {
                ry1 ry1Var = ry1.c;
                ry1Var.getClass();
                iH2 = ry1Var.a(getClass()).h(this);
            } else {
                iH2 = k72Var.h(this);
            }
            if (iH2 >= 0) {
                return iH2;
            }
            throw new IllegalStateException(u4.e("serialized size must be non-negative, was ", iH2));
        }
        if (getMemoizedSerializedSize() != Integer.MAX_VALUE) {
            return getMemoizedSerializedSize();
        }
        if (k72Var == null) {
            ry1 ry1Var2 = ry1.c;
            ry1Var2.getClass();
            iH = ry1Var2.a(getClass()).h(this);
        } else {
            iH = k72Var.h(this);
        }
        setMemoizedSerializedSize(iH);
        return iH;
    }

    public int hashCode() {
        if (isMutable()) {
            return computeHashCode();
        }
        if (hashCodeIsNotMemoized()) {
            setMemoizedHashCode(computeHashCode());
        }
        return getMemoizedHashCode();
    }

    public boolean hashCodeIsNotMemoized() {
        return getMemoizedHashCode() == 0;
    }

    @Override // p.yj1
    public final boolean isInitialized() {
        return isInitialized(this, true);
    }

    public boolean isMutable() {
        return (this.memoizedSerializedSize & MUTABLE_FLAG_MASK) != 0;
    }

    public void makeImmutable() {
        ry1 ry1Var = ry1.c;
        ry1Var.getClass();
        ry1Var.a(getClass()).c(this);
        markImmutable();
    }

    public void markImmutable() {
        this.memoizedSerializedSize &= Integer.MAX_VALUE;
    }

    public void mergeLengthDelimitedField(int i, ByteString byteString) {
        if (this.unknownFields == o.f) {
            this.unknownFields = new o();
        }
        o oVar = this.unknownFields;
        oVar.a();
        if (i == 0) {
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }
        oVar.f((i << 3) | 2, byteString);
    }

    public final void mergeUnknownFields(o oVar) {
        this.unknownFields = o.e(this.unknownFields, oVar);
    }

    public void mergeVarintField(int i, int i2) {
        if (this.unknownFields == o.f) {
            this.unknownFields = new o();
        }
        o oVar = this.unknownFields;
        oVar.a();
        if (i == 0) {
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }
        oVar.f(i << 3, Long.valueOf(i2));
    }

    public i newMutableInstance() {
        return (i) dynamicMethod(GeneratedMessageLite$MethodToInvoke.l);
    }

    public boolean parseUnknownField(int i, au auVar) {
        if ((i & 7) == 4) {
            return false;
        }
        if (this.unknownFields == o.f) {
            this.unknownFields = new o();
        }
        return this.unknownFields.d(i, auVar);
    }

    public void setMemoizedHashCode(int i) {
        this.memoizedHashCode = i;
    }

    public void setMemoizedSerializedSize(int i) {
        if (i < 0) {
            throw new IllegalStateException(u4.e("serialized size must be non-negative, was ", i));
        }
        this.memoizedSerializedSize = (i & Integer.MAX_VALUE) | (this.memoizedSerializedSize & MUTABLE_FLAG_MASK);
    }

    public final h01 toBuilder() {
        return ((h01) dynamicMethod(GeneratedMessageLite$MethodToInvoke.m)).mergeFrom(this);
    }

    public String toString() {
        String string = super.toString();
        char[] cArr = j.a;
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(string);
        j.c(this, sb, 0);
        return sb.toString();
    }

    @Override // p.wj1
    public void writeTo(h hVar) {
        ry1 ry1Var = ry1.c;
        ry1Var.getClass();
        k72 k72VarA = ry1Var.a(getClass());
        p01 p01Var = hVar.g;
        if (p01Var == null) {
            p01Var = new p01(hVar);
        }
        k72VarA.e(this, p01Var);
    }

    public static final <T extends i> boolean isInitialized(T t, boolean z) {
        byte bByteValue = ((Byte) t.dynamicMethod(GeneratedMessageLite$MethodToInvoke.c)).byteValue();
        if (bByteValue == 1) {
            return true;
        }
        if (bByteValue == 0) {
            return false;
        }
        ry1 ry1Var = ry1.c;
        ry1Var.getClass();
        boolean zD = ry1Var.a(t.getClass()).d(t);
        if (z) {
            t.dynamicMethod(GeneratedMessageLite$MethodToInvoke.e, zD ? t : null);
        }
        return zD;
    }

    public final <MessageType extends i, BuilderType extends h01> BuilderType createBuilder(MessageType messagetype) {
        return (BuilderType) createBuilder().mergeFrom((i) messagetype);
    }

    public Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke) {
        return dynamicMethod(generatedMessageLite$MethodToInvoke, null, null);
    }

    @Override // p.yj1
    public final i getDefaultInstanceForType() {
        return (i) dynamicMethod(GeneratedMessageLite$MethodToInvoke.n);
    }

    @Override // p.wj1
    public final h01 newBuilderForType() {
        return (h01) dynamicMethod(GeneratedMessageLite$MethodToInvoke.m);
    }

    public static r51 mutableCopy(r51 r51Var) {
        e51 e51Var = (e51) r51Var;
        int i = e51Var.k;
        int i2 = i == 0 ? 10 : i * 2;
        if (i2 >= i) {
            return new e51(Arrays.copyOf(e51Var.e, i2), e51Var.k);
        }
        throw new IllegalArgumentException();
    }

    public static <T extends i> T parseDelimitedFrom(T t, InputStream inputStream, rl0 rl0Var) throws InvalidProtocolBufferException {
        T t2 = (T) c(t, inputStream, rl0Var);
        b(t2);
        return t2;
    }

    public static <T extends i> T parseFrom(T t, ByteBuffer byteBuffer) {
        return (T) parseFrom(t, byteBuffer, rl0.a());
    }

    public static <T extends i> T parseFrom(T t, ByteString byteString) throws InvalidProtocolBufferException {
        T t2 = (T) parseFrom(t, byteString, rl0.a());
        b(t2);
        return t2;
    }

    public static u51 mutableCopy(u51 u51Var) {
        vd1 vd1Var = (vd1) u51Var;
        int i = vd1Var.k;
        int i2 = i == 0 ? 10 : i * 2;
        if (i2 >= i) {
            return new vd1(Arrays.copyOf(vd1Var.e, i2), vd1Var.k);
        }
        throw new IllegalArgumentException();
    }

    public static <T extends i> T parseFrom(T t, ByteString byteString, rl0 rl0Var) throws InvalidProtocolBufferException {
        au auVarM = byteString.m();
        T t2 = (T) parsePartialFrom(t, auVarM, rl0Var);
        try {
            auVarM.a(0);
            b(t2);
            return t2;
        } catch (InvalidProtocolBufferException e) {
            e.i(t2);
            throw e;
        }
    }

    public static l51 mutableCopy(l51 l51Var) {
        km kmVar = (km) l51Var;
        int i = kmVar.k;
        int i2 = i == 0 ? 10 : i * 2;
        if (i2 >= i) {
            return new km(Arrays.copyOf(kmVar.e, i2), kmVar.k);
        }
        throw new IllegalArgumentException();
    }

    public static <T extends i> T parseFrom(T t, byte[] bArr) throws InvalidProtocolBufferException {
        T t2 = (T) d(t, bArr, 0, bArr.length, rl0.a());
        b(t2);
        return t2;
    }

    public static m51 mutableCopy(m51 m51Var) {
        ta0 ta0Var = (ta0) m51Var;
        int i = ta0Var.k;
        int i2 = i == 0 ? 10 : i * 2;
        if (i2 >= i) {
            return new ta0(Arrays.copyOf(ta0Var.e, i2), ta0Var.k);
        }
        throw new IllegalArgumentException();
    }

    public static <T extends i> T parseFrom(T t, byte[] bArr, rl0 rl0Var) throws InvalidProtocolBufferException {
        T t2 = (T) d(t, bArr, 0, bArr.length, rl0Var);
        b(t2);
        return t2;
    }

    public static <E> v51 mutableCopy(v51 v51Var) {
        int size = v51Var.size();
        return v51Var.f(size == 0 ? 10 : size * 2);
    }

    public static <T extends i> T parseFrom(T t, InputStream inputStream) throws InvalidProtocolBufferException {
        T t2 = (T) parsePartialFrom(t, au.g(inputStream), rl0.a());
        b(t2);
        return t2;
    }

    public static <T extends i> T parsePartialFrom(T t, au auVar) {
        return (T) parsePartialFrom(t, auVar, rl0.a());
    }

    public static <T extends i> T parseFrom(T t, InputStream inputStream, rl0 rl0Var) throws InvalidProtocolBufferException {
        T t2 = (T) parsePartialFrom(t, au.g(inputStream), rl0Var);
        b(t2);
        return t2;
    }

    @Override // p.wj1
    public int getSerializedSize() {
        return getSerializedSize(null);
    }

    public static <T extends i> T parseFrom(T t, au auVar) {
        return (T) parseFrom(t, auVar, rl0.a());
    }

    public static <T extends i> T parseFrom(T t, au auVar, rl0 rl0Var) throws InvalidProtocolBufferException {
        T t2 = (T) parsePartialFrom(t, auVar, rl0Var);
        b(t2);
        return t2;
    }
}
