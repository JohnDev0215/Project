package b.b.a.r.i;

import b.b.a.o;
import b.b.a.p;

/* JADX INFO: compiled from: JsonAdapterAnnotationTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d implements p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b.b.a.r.b f443b;

    public d(b.b.a.r.b bVar) {
        this.f443b = bVar;
    }

    @Override // b.b.a.p
    public <T> o<T> a(b.b.a.d dVar, b.b.a.s.a<T> aVar) {
        b.b.a.q.b bVar = (b.b.a.q.b) aVar.d().getAnnotation(b.b.a.q.b.class);
        if (bVar == null) {
            return null;
        }
        return (o<T>) b(this.f443b, dVar, aVar, bVar);
    }

    public o<?> b(b.b.a.r.b bVar, b.b.a.d dVar, b.b.a.s.a<?> aVar, b.b.a.q.b bVar2) {
        o<?> lVar;
        Object objA = bVar.a(b.b.a.s.a.a(bVar2.value())).a();
        if (objA instanceof o) {
            lVar = (o) objA;
        } else if (objA instanceof p) {
            lVar = ((p) objA).a(dVar, aVar);
        } else {
            boolean z = objA instanceof b.b.a.n;
            if (!z && !(objA instanceof b.b.a.h)) {
                throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer reference.");
            }
            lVar = new l<>(z ? (b.b.a.n) objA : null, objA instanceof b.b.a.h ? (b.b.a.h) objA : null, dVar, aVar, null);
        }
        return (lVar == null || !bVar2.nullSafe()) ? lVar : lVar.a();
    }
}
