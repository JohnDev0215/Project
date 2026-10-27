package spi;

/* JADX INFO: compiled from: JsonAdapterAnnotationTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f5 implements ob {
    public final o2 a;

    public f5(o2 o2Var) {
        this.a = o2Var;
    }

    @Override // spi.ob
    public final <T> nb<T> a(j4 j4Var, vb<T> vbVar) {
        e5 e5Var = (e5) vbVar.a.getAnnotation(e5.class);
        if (e5Var == null) {
            return null;
        }
        return (nb<T>) b(this.a, j4Var, vbVar, e5Var);
    }

    public final nb<?> b(o2 o2Var, j4 j4Var, vb<?> vbVar, e5 e5Var) {
        nb<?> lbVar;
        Object objA = o2Var.a(new vb(e5Var.value())).a();
        if (objA instanceof nb) {
            lbVar = (nb) objA;
        } else if (objA instanceof ob) {
            lbVar = ((ob) objA).a(j4Var, vbVar);
        } else {
            boolean z = objA instanceof q5;
            if (!z && !(objA instanceof h5)) {
                StringBuilder sbK = c.k("Invalid attempt to bind an instance of ");
                sbK.append(objA.getClass().getName());
                sbK.append(" as a @JsonAdapter for ");
                sbK.append(vbVar.toString());
                sbK.append(". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
                throw new IllegalArgumentException(sbK.toString());
            }
            lbVar = new lb<>(z ? (q5) objA : null, objA instanceof h5 ? (h5) objA : null, j4Var, vbVar, null);
        }
        return (lbVar == null || !e5Var.nullSafe()) ? lbVar : new mb(lbVar);
    }
}
