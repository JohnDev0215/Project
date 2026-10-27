package c.b.a.t.i;

import c.b.a.p;
import c.b.a.q;
import c.b.a.r;

/* JADX INFO: compiled from: JsonAdapterAnnotationTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class d implements r {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c.b.a.t.b f2968c;

    public d(c.b.a.t.b bVar) {
        this.f2968c = bVar;
    }

    @Override // c.b.a.r
    public <T> q<T> a(c.b.a.e eVar, c.b.a.u.a<T> aVar) {
        c.b.a.s.b bVar = (c.b.a.s.b) aVar.c().getAnnotation(c.b.a.s.b.class);
        if (bVar == null) {
            return null;
        }
        return (q<T>) b(this.f2968c, eVar, aVar, bVar);
    }

    public q<?> b(c.b.a.t.b bVar, c.b.a.e eVar, c.b.a.u.a<?> aVar, c.b.a.s.b bVar2) {
        q<?> lVar;
        Object objA = bVar.a(c.b.a.u.a.a(bVar2.value())).a();
        if (objA instanceof q) {
            lVar = (q) objA;
        } else if (objA instanceof r) {
            lVar = ((r) objA).a(eVar, aVar);
        } else {
            boolean z = objA instanceof p;
            if (!z && !(objA instanceof c.b.a.j)) {
                throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer reference.");
            }
            lVar = new l<>(z ? (p) objA : null, objA instanceof c.b.a.j ? (c.b.a.j) objA : null, eVar, aVar, null);
        }
        return lVar != null ? lVar.a() : lVar;
    }
}
