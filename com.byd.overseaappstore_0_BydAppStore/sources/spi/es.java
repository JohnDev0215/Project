package spi;

/* JADX INFO: loaded from: classes.dex */
public abstract class es {

    public static class b extends es {
        public volatile boolean a;

        public b() {
            super();
        }

        @Override // spi.es
        public final void a(boolean z) {
            this.a = z;
        }

        public final void b() {
            if (this.a) {
                throw new IllegalStateException("Already released");
            }
        }
    }

    private es() {
    }

    public abstract void a(boolean z);
}
