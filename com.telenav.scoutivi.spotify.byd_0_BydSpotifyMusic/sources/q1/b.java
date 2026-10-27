package q1;

/* JADX INFO: compiled from: CallbackException.java */
/* JADX INFO: loaded from: classes.dex */
final class b extends RuntimeException {
    private static final long serialVersionUID = -7530898992688511851L;

    b(Throwable th) {
        super("Unexpected exception thrown by non-Glide code", th);
    }
}
