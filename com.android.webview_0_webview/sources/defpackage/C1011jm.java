package defpackage;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: renamed from: jm, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1011jm {
    public static final C1011jm c = new C1011jm();
    public final ConcurrentMap b = new ConcurrentHashMap();
    public final InterfaceC1233nm a = new C0334Sl();

    public InterfaceC1178mm a(Class cls) {
        InterfaceC1178mm interfaceC1178mmX;
        C0677dm c0677dm;
        AbstractC0119Gl.b(cls, "messageType");
        InterfaceC1178mm interfaceC1178mm = (InterfaceC1178mm) this.b.get(cls);
        if (interfaceC1178mm != null) {
            return interfaceC1178mm;
        }
        C0334Sl c0334Sl = (C0334Sl) this.a;
        Objects.requireNonNull(c0334Sl);
        AbstractC1288om.D(cls);
        InterfaceC0442Yl interfaceC0442YlB = c0334Sl.a.b(cls);
        C1067km c1067km = (C1067km) interfaceC0442YlB;
        if ((c1067km.d & 2) == 2) {
            if (AbstractC0065Dl.class.isAssignableFrom(cls)) {
                c0677dm = new C0677dm(AbstractC1288om.d, AbstractC1671vl.a, c1067km.a);
            } else {
                AbstractC1507sm abstractC1507sm = AbstractC1288om.b;
                AbstractC1561tl abstractC1561tl = AbstractC1671vl.b;
                if (abstractC1561tl == null) {
                    throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
                }
                c0677dm = new C0677dm(abstractC1507sm, abstractC1561tl, c1067km.a);
            }
            interfaceC1178mmX = c0677dm;
        } else if (AbstractC0065Dl.class.isAssignableFrom(cls)) {
            interfaceC1178mmX = C0334Sl.a(interfaceC0442YlB) ? C0621cm.x(interfaceC0442YlB, AbstractC0844gm.b, AbstractC0280Pl.b, AbstractC1288om.d, AbstractC1671vl.a, AbstractC0424Xl.b) : C0621cm.x(interfaceC0442YlB, AbstractC0844gm.b, AbstractC0280Pl.b, AbstractC1288om.d, null, AbstractC0424Xl.b);
        } else if (C0334Sl.a(interfaceC0442YlB)) {
            InterfaceC0732em interfaceC0732em = AbstractC0844gm.a;
            AbstractC0280Pl abstractC0280Pl = AbstractC0280Pl.a;
            AbstractC1507sm abstractC1507sm2 = AbstractC1288om.b;
            AbstractC1561tl abstractC1561tl2 = AbstractC1671vl.b;
            if (abstractC1561tl2 == null) {
                throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
            }
            interfaceC1178mmX = C0621cm.x(interfaceC0442YlB, interfaceC0732em, abstractC0280Pl, abstractC1507sm2, abstractC1561tl2, AbstractC0424Xl.a);
        } else {
            interfaceC1178mmX = C0621cm.x(interfaceC0442YlB, AbstractC0844gm.a, AbstractC0280Pl.a, AbstractC1288om.c, null, AbstractC0424Xl.a);
        }
        AbstractC0119Gl.b(cls, "messageType");
        AbstractC0119Gl.b(interfaceC1178mmX, "schema");
        InterfaceC1178mm interfaceC1178mm2 = (InterfaceC1178mm) this.b.putIfAbsent(cls, interfaceC1178mmX);
        return interfaceC1178mm2 != null ? interfaceC1178mm2 : interfaceC1178mmX;
    }

    public InterfaceC1178mm b(Object obj) {
        return a(obj.getClass());
    }
}
