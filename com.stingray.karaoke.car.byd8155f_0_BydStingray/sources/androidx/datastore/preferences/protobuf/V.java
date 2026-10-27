package androidx.datastore.preferences.protobuf;

import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class V {
    public static final V c = new V();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConcurrentHashMap f11818b = new ConcurrentHashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final G f11817a = new G();

    public final Y a(Class cls) {
        O oZ;
        Y y10;
        P p10;
        Class cls2;
        AbstractC0959w.a(cls, "messageType");
        ConcurrentHashMap concurrentHashMap = this.f11818b;
        Y y11 = (Y) concurrentHashMap.get(cls);
        if (y11 != null) {
            return y11;
        }
        G g7 = this.f11817a;
        g7.getClass();
        Class cls3 = Z.f11823a;
        if (!AbstractC0957u.class.isAssignableFrom(cls) && (cls2 = Z.f11823a) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessageV3 or GeneratedMessageLite");
        }
        X xA = ((F) g7.f11793a).a(cls);
        if ((xA.d & 2) == 2) {
            boolean zIsAssignableFrom = AbstractC0957u.class.isAssignableFrom(cls);
            AbstractC0957u abstractC0957u = xA.f11821a;
            if (zIsAssignableFrom) {
                p10 = new P(Z.d, AbstractC0952o.f11872a, abstractC0957u);
            } else {
                j0 j0Var = Z.f11824b;
                C0951n c0951n = AbstractC0952o.f11873b;
                if (c0951n == null) {
                    throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
                }
                p10 = new P(j0Var, c0951n, abstractC0957u);
            }
            y10 = p10;
        } else {
            if (AbstractC0957u.class.isAssignableFrom(cls)) {
                oZ = xA.d() == 1 ? O.z(xA, S.f11816b, E.f11790b, Z.d, AbstractC0952o.f11872a, L.f11799b) : O.z(xA, S.f11816b, E.f11790b, Z.d, null, L.f11799b);
            } else if (xA.d() == 1) {
                Q q4 = S.f11815a;
                C c10 = E.f11789a;
                j0 j0Var2 = Z.f11824b;
                C0951n c0951n2 = AbstractC0952o.f11873b;
                if (c0951n2 == null) {
                    throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
                }
                oZ = O.z(xA, q4, c10, j0Var2, c0951n2, L.f11798a);
            } else {
                oZ = O.z(xA, S.f11815a, E.f11789a, Z.c, null, L.f11798a);
            }
            y10 = oZ;
        }
        Y y12 = (Y) concurrentHashMap.putIfAbsent(cls, y10);
        return y12 != null ? y12 : y10;
    }
}
