package m5;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import l5.AbstractC1776d;
import q5.C2185a;

/* JADX INFO: renamed from: m5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C1824a implements j5.z {
    @Override // j5.z
    public final j5.y a(j5.m mVar, C2185a c2185a) {
        Type type = c2185a.f21254b;
        boolean z6 = type instanceof GenericArrayType;
        if (!z6 && (!(type instanceof Class) || !((Class) type).isArray())) {
            return null;
        }
        Type genericComponentType = z6 ? ((GenericArrayType) type).getGenericComponentType() : ((Class) type).getComponentType();
        return new C1825b(mVar, mVar.c(new C2185a(genericComponentType)), AbstractC1776d.h(genericComponentType));
    }
}
