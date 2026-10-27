package p6;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

/* JADX INFO: compiled from: JvmOkio.kt */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Logger f8888a = Logger.getLogger("okio.Okio");

    public static final boolean b(AssertionError isAndroidGetsocknameError) {
        kotlin.jvm.internal.l.e(isAndroidGetsocknameError, "$this$isAndroidGetsocknameError");
        if (isAndroidGetsocknameError.getCause() == null) {
            return false;
        }
        String message = isAndroidGetsocknameError.getMessage();
        return message != null ? y5.q.F(message, "getsockname failed", false, 2, null) : false;
    }

    public static final y c(Socket sink) throws IOException {
        kotlin.jvm.internal.l.e(sink, "$this$sink");
        z zVar = new z(sink);
        OutputStream outputStream = sink.getOutputStream();
        kotlin.jvm.internal.l.d(outputStream, "getOutputStream()");
        return zVar.v(new s(outputStream, zVar));
    }

    public static final a0 d(InputStream source) {
        kotlin.jvm.internal.l.e(source, "$this$source");
        return new n(source, new b0());
    }

    public static final a0 e(Socket source) throws IOException {
        kotlin.jvm.internal.l.e(source, "$this$source");
        z zVar = new z(source);
        InputStream inputStream = source.getInputStream();
        kotlin.jvm.internal.l.d(inputStream, "getInputStream()");
        return zVar.w(new n(inputStream, zVar));
    }
}
