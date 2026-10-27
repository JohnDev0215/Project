package h6;

import java.io.IOException;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: RouteException.kt */
/* JADX INFO: loaded from: classes.dex */
public final class j extends RuntimeException {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private IOException f6744e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final IOException f6745g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(IOException firstConnectException) {
        super(firstConnectException);
        l.e(firstConnectException, "firstConnectException");
        this.f6745g = firstConnectException;
        this.f6744e = firstConnectException;
    }

    public final void a(IOException e7) {
        l.e(e7, "e");
        g5.b.a(this.f6745g, e7);
        this.f6744e = e7;
    }

    public final IOException b() {
        return this.f6745g;
    }

    public final IOException c() {
        return this.f6744e;
    }
}
