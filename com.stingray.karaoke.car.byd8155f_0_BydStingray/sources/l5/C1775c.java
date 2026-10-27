package l5;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Objects;

/* JADX INFO: renamed from: l5.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1775c implements WildcardType, Serializable {
    public final Type l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Type f18635m;

    public C1775c(Type[] typeArr, Type[] typeArr2) {
        AbstractC1776d.b(typeArr2.length <= 1);
        AbstractC1776d.b(typeArr.length == 1);
        if (typeArr2.length != 1) {
            Objects.requireNonNull(typeArr[0]);
            AbstractC1776d.c(typeArr[0]);
            this.f18635m = null;
            this.l = AbstractC1776d.a(typeArr[0]);
            return;
        }
        Objects.requireNonNull(typeArr2[0]);
        AbstractC1776d.c(typeArr2[0]);
        AbstractC1776d.b(typeArr[0] == Object.class);
        this.f18635m = AbstractC1776d.a(typeArr2[0]);
        this.l = Object.class;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof WildcardType) && AbstractC1776d.e(this, (WildcardType) obj);
    }

    @Override // java.lang.reflect.WildcardType
    public final Type[] getLowerBounds() {
        Type type = this.f18635m;
        return type != null ? new Type[]{type} : AbstractC1776d.f18636a;
    }

    @Override // java.lang.reflect.WildcardType
    public final Type[] getUpperBounds() {
        return new Type[]{this.l};
    }

    public final int hashCode() {
        Type type = this.f18635m;
        return (this.l.hashCode() + 31) ^ (type != null ? type.hashCode() + 31 : 1);
    }

    public final String toString() {
        Type type = this.f18635m;
        if (type != null) {
            return "? super " + AbstractC1776d.k(type);
        }
        Type type2 = this.l;
        if (type2 == Object.class) {
            return "?";
        }
        return "? extends " + AbstractC1776d.k(type2);
    }
}
