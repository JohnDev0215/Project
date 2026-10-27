package l5;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.EnumSet;

/* JADX INFO: renamed from: l5.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1777e implements o {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Type f18637m;

    public /* synthetic */ C1777e(Type type, int i7) {
        this.l = i7;
        this.f18637m = type;
    }

    @Override // l5.o
    public final Object H() {
        switch (this.l) {
            case 0:
                Type type = this.f18637m;
                if (!(type instanceof ParameterizedType)) {
                    throw new j5.p("Invalid EnumSet type: " + type.toString());
                }
                Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
                if (type2 instanceof Class) {
                    return EnumSet.noneOf((Class) type2);
                }
                throw new j5.p("Invalid EnumSet type: " + type.toString());
            default:
                Type type3 = this.f18637m;
                if (!(type3 instanceof ParameterizedType)) {
                    throw new j5.p("Invalid EnumMap type: " + type3.toString());
                }
                Type type4 = ((ParameterizedType) type3).getActualTypeArguments()[0];
                if (type4 instanceof Class) {
                    return new EnumMap((Class) type4);
                }
                throw new j5.p("Invalid EnumMap type: " + type3.toString());
        }
    }
}
