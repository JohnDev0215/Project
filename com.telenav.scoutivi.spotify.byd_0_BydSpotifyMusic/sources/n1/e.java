package n1;

import java.io.IOException;

/* JADX INFO: compiled from: HttpException.java */
/* JADX INFO: loaded from: classes.dex */
public final class e extends IOException {
    private static final long serialVersionUID = 1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f8503e;

    public e(int i7) {
        this("Http request failed with status code: " + i7, i7);
    }

    public e(String str) {
        this(str, -1);
    }

    public e(String str, int i7) {
        this(str, i7, null);
    }

    public e(String str, int i7, Throwable th) {
        super(str, th);
        this.f8503e = i7;
    }
}
