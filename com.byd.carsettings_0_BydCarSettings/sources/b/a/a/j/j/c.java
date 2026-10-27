package b.a.a.j.j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: compiled from: ArrayListTypeFieldDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Type f270c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f271d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public t f272e;

    public c(b.a.a.j.h hVar, Class<?> cls, b.a.a.n.c cVar) {
        super(cls, cVar);
        Type type = cVar.f434f;
        if (!(type instanceof ParameterizedType)) {
            this.f270c = Object.class;
            return;
        }
        Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        if (type2 instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) type2).getUpperBounds();
            if (upperBounds.length == 1) {
                type2 = upperBounds[0];
            }
        }
        this.f270c = type2;
    }

    @Override // b.a.a.j.j.l
    public int b() {
        return 14;
    }

    @Override // b.a.a.j.j.l
    public void c(b.a.a.j.a aVar, Object obj, Type type, Map<String, Object> map) {
        b.a.a.j.b bVar = aVar.g;
        int iJ = bVar.j();
        if (iJ == 8 || (iJ == 4 && bVar.V().length() == 0)) {
            if (obj == null) {
                map.put(this.f282a.f429a, null);
                return;
            } else {
                h(obj, null);
                return;
            }
        }
        ArrayList arrayList = new ArrayList();
        b.a.a.j.g gVarJ = aVar.j();
        aVar.W(gVarJ, obj, this.f282a.f429a);
        j(aVar, type, arrayList);
        aVar.Y(gVarJ);
        if (obj == null) {
            map.put(this.f282a.f429a, arrayList);
        } else {
            g(obj, arrayList);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void j(b.a.a.j.a r13, java.lang.reflect.Type r14, java.util.Collection r15) {
        /*
            Method dump skipped, instruction units count: 376
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.j.c.j(b.a.a.j.a, java.lang.reflect.Type, java.util.Collection):void");
    }
}
