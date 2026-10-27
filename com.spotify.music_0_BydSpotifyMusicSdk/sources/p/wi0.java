package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class wi0 {
    public final vi0 a;

    public wi0(jw0 jw0Var) {
        this.a = jw0Var;
    }

    public final void a(com.google.protobuf.i iVar) {
        v03.j(iVar, "event");
        String simpleName = iVar.getClass().getSimpleName();
        byte[] byteArray = iVar.toByteArray();
        v03.i(byteArray, "event.toByteArray()");
        ((jw0) this.a).b(simpleName, null, byteArray);
    }
}
