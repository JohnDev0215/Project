package f.k0.k;

import java.io.IOException;

/* JADX INFO: compiled from: StreamResetException.java */
/* JADX INFO: loaded from: classes.dex */
public final class n extends IOException {
    public final b errorCode;

    public n(b bVar) {
        super("stream was reset: " + bVar);
        this.errorCode = bVar;
    }
}
