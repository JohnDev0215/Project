package com.drakeet.multitype;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.bytebuddy.implementation.MethodDelegation;

/* JADX INFO: compiled from: Type.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B5\u0012\u000e\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0004\u0012\u0010\u0010\u0005\u001a\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0002\u0010\tJ\u0011\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0004HÆ\u0003J\u0013\u0010\u0011\u001a\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u0006HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\bHÆ\u0003JE\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00042\u0012\b\u0002\u0010\u0005\u001a\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bHÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0019\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u0005\u001a\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/drakeet/multitype/Type;", "T", "", "clazz", "Ljava/lang/Class;", MethodDelegation.ImplementationDelegate.FIELD_NAME_PREFIX, "Lcom/drakeet/multitype/ItemViewDelegate;", "linker", "Lcom/drakeet/multitype/Linker;", "(Ljava/lang/Class;Lcom/drakeet/multitype/ItemViewDelegate;Lcom/drakeet/multitype/Linker;)V", "getClazz", "()Ljava/lang/Class;", "getDelegate", "()Lcom/drakeet/multitype/ItemViewDelegate;", "getLinker", "()Lcom/drakeet/multitype/Linker;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "multitype"}, k = 1, mv = {1, 4, 2})
public final /* data */ class Type<T> {
    private final Class<? extends T> clazz;
    private final ItemViewDelegate<T, ?> delegate;
    private final Linker<T> linker;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Type copy$default(Type type, Class cls, ItemViewDelegate itemViewDelegate, Linker linker, int i, Object obj) {
        if ((i & 1) != 0) {
            cls = type.clazz;
        }
        if ((i & 2) != 0) {
            itemViewDelegate = type.delegate;
        }
        if ((i & 4) != 0) {
            linker = type.linker;
        }
        return type.copy(cls, itemViewDelegate, linker);
    }

    public final Class<? extends T> component1() {
        return this.clazz;
    }

    public final ItemViewDelegate<T, ?> component2() {
        return this.delegate;
    }

    public final Linker<T> component3() {
        return this.linker;
    }

    public final Type<T> copy(Class<? extends T> clazz, ItemViewDelegate<T, ?> delegate, Linker<T> linker) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(linker, "linker");
        return new Type<>(clazz, delegate, linker);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Type)) {
            return false;
        }
        Type type = (Type) other;
        return Intrinsics.areEqual(this.clazz, type.clazz) && Intrinsics.areEqual(this.delegate, type.delegate) && Intrinsics.areEqual(this.linker, type.linker);
    }

    public int hashCode() {
        Class<? extends T> cls = this.clazz;
        int iHashCode = (cls != null ? cls.hashCode() : 0) * 31;
        ItemViewDelegate<T, ?> itemViewDelegate = this.delegate;
        int iHashCode2 = (iHashCode + (itemViewDelegate != null ? itemViewDelegate.hashCode() : 0)) * 31;
        Linker<T> linker = this.linker;
        return iHashCode2 + (linker != null ? linker.hashCode() : 0);
    }

    public String toString() {
        return "Type(clazz=" + this.clazz + ", delegate=" + this.delegate + ", linker=" + this.linker + ")";
    }

    public Type(Class<? extends T> clazz, ItemViewDelegate<T, ?> delegate, Linker<T> linker) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(linker, "linker");
        this.clazz = clazz;
        this.delegate = delegate;
        this.linker = linker;
    }

    public final Class<? extends T> getClazz() {
        return this.clazz;
    }

    public final ItemViewDelegate<T, ?> getDelegate() {
        return this.delegate;
    }

    public final Linker<T> getLinker() {
        return this.linker;
    }
}
