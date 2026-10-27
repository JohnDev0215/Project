package spi;

/* JADX INFO: loaded from: classes2.dex */
public final class v8 {
    public static final a a = new a();

    public static final class a extends Throwable {
        private static final long serialVersionUID = -4649703670690200604L;

        public a() {
            super("No further exceptions");
        }

        @Override // java.lang.Throwable
        public final Throwable fillInStackTrace() {
            return this;
        }
    }

    private v8() {
        throw new IllegalStateException("No instances!");
    }

    public static NullPointerException a(String str) {
        return new NullPointerException(d.f(str, " Null values are generally not allowed in 3.x operators and sources."));
    }
}
