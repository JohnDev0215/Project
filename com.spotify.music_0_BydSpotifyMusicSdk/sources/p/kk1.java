package p;

/* JADX INFO: loaded from: classes.dex */
public final class kk1 extends ik1 {
    public static final kk1 c = new kk1(12, 13);

    @Override // p.ik1
    public final void a(androidx.sqlite.db.framework.b bVar) {
        bVar.w("UPDATE workspec SET required_network_type = 0 WHERE required_network_type IS NULL ");
        bVar.w("UPDATE workspec SET content_uri_triggers = x'' WHERE content_uri_triggers is NULL");
    }
}
