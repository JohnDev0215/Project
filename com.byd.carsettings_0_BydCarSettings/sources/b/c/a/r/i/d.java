package b.c.a.r.i;

import b.c.a.o;
import b.c.a.p;

/* JADX INFO: compiled from: JsonAdapterAnnotationTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes3.dex */
public final class d implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.c.a.r.b f2259a;

    public d(b.c.a.r.b bVar) {
        this.f2259a = bVar;
    }

    @Override // b.c.a.p
    public <T> o<T> a(b.c.a.d dVar, b.c.a.s.a<T> aVar) {
        b.c.a.q.b bVar = (b.c.a.q.b) aVar.d().getAnnotation(b.c.a.q.b.class);
        if (bVar == null) {
            return null;
        }
        return (o<T>) b(this.f2259a, dVar, aVar, bVar);
    }

    public o<?> b(b.c.a.r.b bVar, b.c.a.d dVar, b.c.a.s.a<?> aVar, b.c.a.q.b bVar2) {
        o<?> lVar;
        Object objA = bVar.a(b.c.a.s.a.a(bVar2.value())).a();
        if (objA instanceof o) {
            lVar = (o) objA;
        } else if (objA instanceof p) {
            lVar = ((p) objA).a(dVar, aVar);
        } else {
            boolean z = objA instanceof b.c.a.n;
            if (!z && !(objA instanceof b.c.a.h)) {
                throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer reference.");
            }
            lVar = new l<>(z ? (b.c.a.n) objA : null, objA instanceof b.c.a.h ? (b.c.a.h) objA : null, dVar, aVar, null);
        }
        return (lVar == null || !bVar2.nullSafe()) ? lVar : lVar.a();
    }
}
