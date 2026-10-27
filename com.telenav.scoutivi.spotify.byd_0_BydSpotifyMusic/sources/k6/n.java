package k6;

import java.io.IOException;

/* JADX INFO: compiled from: StreamResetException.kt */
/* JADX INFO: loaded from: classes.dex */
public final class n extends IOException {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b f7483e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(b errorCode) {
        super("stream was reset: " + errorCode);
        kotlin.jvm.internal.l.e(errorCode, "errorCode");
        this.f7483e = errorCode;
    }
}
