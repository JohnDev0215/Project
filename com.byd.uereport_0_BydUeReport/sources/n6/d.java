package n6;

import k6.o;
import k6.p;

/* JADX INFO: compiled from: JsonAdapterAnnotationTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d implements p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final m6.b f6561d;

    public d(m6.b bVar) {
        this.f6561d = bVar;
    }

    @Override // k6.p
    public <T> o<T> a(k6.d dVar, p6.a<T> aVar) {
        l6.b bVar = (l6.b) aVar.d().getAnnotation(l6.b.class);
        if (bVar == null) {
            return null;
        }
        return (o<T>) b(this.f6561d, dVar, aVar, bVar);
    }

    public o<?> b(m6.b bVar, k6.d dVar, p6.a<?> aVar, l6.b bVar2) {
        o<?> lVar;
        Object objA = bVar.a(p6.a.a(bVar2.value())).a();
        if (objA instanceof o) {
            lVar = (o) objA;
        } else if (objA instanceof p) {
            lVar = ((p) objA).a(dVar, aVar);
        } else {
            boolean z7 = objA instanceof k6.n;
            if (!z7 && !(objA instanceof k6.h)) {
                throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer reference.");
            }
            lVar = new l<>(z7 ? (k6.n) objA : null, objA instanceof k6.h ? (k6.h) objA : null, dVar, aVar, null);
        }
        return (lVar == null || !bVar2.nullSafe()) ? lVar : lVar.a();
    }
}
