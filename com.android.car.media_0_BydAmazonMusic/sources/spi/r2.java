package spi;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumSet;

/* JADX INFO: compiled from: ConstructorConstructor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class r2 implements b8<Object> {
    public final /* synthetic */ Type a;

    public r2(o2 o2Var, Type type) {
        this.a = type;
    }

    @Override // spi.b8
    public final Object a() {
        Type type = this.a;
        if (!(type instanceof ParameterizedType)) {
            StringBuilder sbK = c.k("Invalid EnumSet type: ");
            sbK.append(this.a.toString());
            throw new j5(sbK.toString());
        }
        Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        if (type2 instanceof Class) {
            return EnumSet.noneOf((Class) type2);
        }
        StringBuilder sbK2 = c.k("Invalid EnumSet type: ");
        sbK2.append(this.a.toString());
        throw new j5(sbK2.toString());
    }
}
