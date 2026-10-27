package m5;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import l5.AbstractC1776d;
import l5.C1778f;
import q5.C2185a;

/* JADX INFO: renamed from: m5.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1827d implements j5.z {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C1778f f19220m;

    public /* synthetic */ C1827d(C1778f c1778f, int i7) {
        this.l = i7;
        this.f19220m = c1778f;
    }

    @Override // j5.z
    public final j5.y a(j5.m mVar, C2185a c2185a) {
        Class cls;
        Type[] actualTypeArguments;
        C1778f c1778f = this.f19220m;
        cls = Object.class;
        switch (this.l) {
            case 0:
                Class cls2 = c2185a.f21253a;
                if (!Collection.class.isAssignableFrom(cls2)) {
                    return null;
                }
                Type type = c2185a.f21254b;
                if (type instanceof WildcardType) {
                    type = ((WildcardType) type).getUpperBounds()[0];
                }
                AbstractC1776d.b(Collection.class.isAssignableFrom(cls2));
                Type typeJ = AbstractC1776d.j(type, cls2, AbstractC1776d.g(type, cls2, Collection.class), new HashMap());
                cls = typeJ instanceof ParameterizedType ? ((ParameterizedType) typeJ).getActualTypeArguments()[0] : Object.class;
                return new C1826c(mVar, cls, mVar.c(new C2185a(cls)), c1778f.h(c2185a));
            default:
                Class cls3 = c2185a.f21253a;
                if (!Map.class.isAssignableFrom(cls3)) {
                    return null;
                }
                Type type2 = c2185a.f21254b;
                if (type2 == Properties.class) {
                    actualTypeArguments = new Type[]{String.class, String.class};
                } else {
                    if (type2 instanceof WildcardType) {
                        type2 = ((WildcardType) type2).getUpperBounds()[0];
                    }
                    AbstractC1776d.b(Map.class.isAssignableFrom(cls3));
                    Type typeJ2 = AbstractC1776d.j(type2, cls3, AbstractC1776d.g(type2, cls3, Map.class), new HashMap());
                    actualTypeArguments = typeJ2 instanceof ParameterizedType ? ((ParameterizedType) typeJ2).getActualTypeArguments() : new Type[]{cls, cls};
                }
                Type type3 = actualTypeArguments[0];
                return new C1833j(this, mVar, actualTypeArguments[0], (type3 == Boolean.TYPE || type3 == Boolean.class) ? c0.c : mVar.c(new C2185a(type3)), actualTypeArguments[1], mVar.c(new C2185a(actualTypeArguments[1])), c1778f.h(c2185a));
        }
    }
}
