package spi;

/* JADX INFO: loaded from: classes.dex */
public final class f3 extends RuntimeException {
    private static final long serialVersionUID = -7530898992688511851L;

    public f3(Throwable th) {
        super("Unexpected exception thrown by non-Glide code", th);
    }
}
