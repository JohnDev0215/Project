package okio;

import com.amazon.music.find.utils.PageUriUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: JvmOkio.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\n\u0010\u0002\u001a\u00020\u0001*\u00020\u0000\u001a\n\u0010\u0005\u001a\u00020\u0004*\u00020\u0003\u001a\n\u0010\u0002\u001a\u00020\u0001*\u00020\u0006\u001a\n\u0010\u0005\u001a\u00020\u0004*\u00020\u0006\u001a\u0016\u0010\u0002\u001a\u00020\u0001*\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\bH\u0007\u001a\n\u0010\n\u001a\u00020\u0001*\u00020\u0007\u001a\n\u0010\u0005\u001a\u00020\u0004*\u00020\u0007\"\u001c\u0010\r\u001a\n \f*\u0004\u0018\u00010\u000b0\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000e\"\u001c\u0010\u0011\u001a\u00020\b*\u00060\u000fj\u0002`\u00108@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Ljava/io/OutputStream;", "Lokio/Sink;", "sink", "Ljava/io/InputStream;", "Lokio/Source;", PageUriUtils.SOURCE_QUERY_PARAM, "Ljava/net/Socket;", "Ljava/io/File;", "", "append", "appendingSink", "Ljava/util/logging/Logger;", "kotlin.jvm.PlatformType", "logger", "Ljava/util/logging/Logger;", "Ljava/lang/AssertionError;", "Lkotlin/AssertionError;", "isAndroidGetsocknameError", "(Ljava/lang/AssertionError;)Z", "okio"}, k = 5, mv = {1, 5, 1}, xs = "okio/Okio")
public final /* synthetic */ class Okio__JvmOkioKt {
    private static final Logger logger = Logger.getLogger("okio.Okio");

    public static final Sink sink(OutputStream outputStream) {
        Intrinsics.checkNotNullParameter(outputStream, "<this>");
        return new sink(outputStream, new Timeout());
    }

    public static final Source source(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "<this>");
        return new source(inputStream, new Timeout());
    }

    public static final Sink sink(Socket socket) throws IOException {
        Intrinsics.checkNotNullParameter(socket, "<this>");
        SocketAsyncTimeout socketAsyncTimeout = new SocketAsyncTimeout(socket);
        OutputStream outputStream = socket.getOutputStream();
        Intrinsics.checkNotNullExpressionValue(outputStream, "getOutputStream()");
        return socketAsyncTimeout.sink(new sink(outputStream, socketAsyncTimeout));
    }

    public static final Source source(Socket socket) throws IOException {
        Intrinsics.checkNotNullParameter(socket, "<this>");
        SocketAsyncTimeout socketAsyncTimeout = new SocketAsyncTimeout(socket);
        InputStream inputStream = socket.getInputStream();
        Intrinsics.checkNotNullExpressionValue(inputStream, "getInputStream()");
        return socketAsyncTimeout.source(new source(inputStream, socketAsyncTimeout));
    }

    @JvmOverloads
    public static final Sink sink(File file, boolean z) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return Okio.sink(new FileOutputStream(file, z));
    }

    public static /* synthetic */ Sink sink$default(File file, boolean z, int i, Object obj) throws FileNotFoundException {
        if ((i & 1) != 0) {
            z = false;
        }
        return Okio.sink(file, z);
    }

    public static final Sink appendingSink(File file) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return Okio.sink(new FileOutputStream(file, true));
    }

    public static final Source source(File file) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return new source(new FileInputStream(file), Timeout.NONE);
    }

    public static final boolean isAndroidGetsocknameError(AssertionError assertionError) {
        Intrinsics.checkNotNullParameter(assertionError, "<this>");
        if (assertionError.getCause() == null) {
            return false;
        }
        String message = assertionError.getMessage();
        return message == null ? false : StringsKt__StringsKt.contains$default((CharSequence) message, (CharSequence) "getsockname failed", false, 2, (Object) null);
    }
}
