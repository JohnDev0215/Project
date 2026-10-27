package g2;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: compiled from: ArrayListTypeFieldDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Type f5024c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5025d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public t f5026e;

    public c(f2.h hVar, Class<?> cls, l2.c cVar) {
        super(cls, cVar);
        Type type = cVar.f6174i;
        if (!(type instanceof ParameterizedType)) {
            this.f5024c = Object.class;
            return;
        }
        Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        if (type2 instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) type2).getUpperBounds();
            if (upperBounds.length == 1) {
                type2 = upperBounds[0];
            }
        }
        this.f5024c = type2;
    }

    @Override // g2.l
    public int b() {
        return 14;
    }

    @Override // g2.l
    public void c(f2.a aVar, Object obj, Type type, Map<String, Object> map) {
        f2.b bVar = aVar.f4861j;
        int iQ = bVar.q();
        if (iQ == 8 || (iQ == 4 && bVar.n0().length() == 0)) {
            if (obj == null) {
                map.put(this.f5036a.f6169d, null);
                return;
            } else {
                h(obj, null);
                return;
            }
        }
        ArrayList arrayList = new ArrayList();
        f2.g gVarQ = aVar.q();
        aVar.o0(gVarQ, obj, this.f5036a.f6169d);
        j(aVar, type, arrayList);
        aVar.r0(gVarQ);
        if (obj == null) {
            map.put(this.f5036a.f6169d, arrayList);
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
    public final void j(f2.a r13, java.lang.reflect.Type r14, java.util.Collection r15) {
        /*
            Method dump skipped, instruction units count: 376
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g2.c.j(f2.a, java.lang.reflect.Type, java.util.Collection):void");
    }
}
