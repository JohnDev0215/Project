package defpackage;

/* JADX INFO: compiled from: Core.java */
/* JADX INFO: loaded from: classes3.dex */
public interface k00 {

    /* JADX INFO: compiled from: Core.java */
    public static class a extends mn0<a> {
        public static final a c;

        static {
            new a().b = true;
            a aVar = new a();
            if (aVar.b) {
                throw new UnsupportedOperationException("Flags is immutable.");
            }
            aVar.a |= 1;
            aVar.b = true;
            c = aVar;
            a aVar2 = new a();
            if (aVar2.b) {
                throw new UnsupportedOperationException("Flags is immutable.");
            }
            aVar2.a |= 2;
            aVar2.b = true;
        }
    }
}
