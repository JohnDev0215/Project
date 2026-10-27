package c.b.b.r.i;

import c.b.b.o;
import c.b.b.p;

/* JADX INFO: compiled from: JsonAdapterAnnotationTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class d implements p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c.b.b.r.b f4231b;

    public d(c.b.b.r.b bVar) {
        this.f4231b = bVar;
    }

    @Override // c.b.b.p
    public <T> o<T> a(c.b.b.d dVar, c.b.b.s.a<T> aVar) {
        c.b.b.q.b bVar = (c.b.b.q.b) aVar.d().getAnnotation(c.b.b.q.b.class);
        if (bVar == null) {
            return null;
        }
        return (o<T>) b(this.f4231b, dVar, aVar, bVar);
    }

    public o<?> b(c.b.b.r.b bVar, c.b.b.d dVar, c.b.b.s.a<?> aVar, c.b.b.q.b bVar2) {
        o<?> lVar;
        Object objA = bVar.a(c.b.b.s.a.a(bVar2.value())).a();
        if (objA instanceof o) {
            lVar = (o) objA;
        } else if (objA instanceof p) {
            lVar = ((p) objA).a(dVar, aVar);
        } else {
            boolean z = objA instanceof c.b.b.n;
            if (!z && !(objA instanceof c.b.b.h)) {
                throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer reference.");
            }
            lVar = new l<>(z ? (c.b.b.n) objA : null, objA instanceof c.b.b.h ? (c.b.b.h) objA : null, dVar, aVar, null);
        }
        return (lVar == null || !bVar2.nullSafe()) ? lVar : lVar.a();
    }
}
