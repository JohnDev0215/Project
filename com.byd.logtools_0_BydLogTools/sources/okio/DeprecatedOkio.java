package okio;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Arrays;
import kotlin.jvm.internal.i;

/* JADX INFO: renamed from: okio.-DeprecatedOkio, reason: invalid class name */
/* JADX INFO: compiled from: -DeprecatedOkio.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DeprecatedOkio {
    public static final DeprecatedOkio INSTANCE = new DeprecatedOkio();

    private DeprecatedOkio() {
    }

    public final Sink appendingSink(File file) {
        i.c(file, "file");
        return Okio.appendingSink(file);
    }

    public final Sink blackhole() {
        return Okio.blackhole();
    }

    public final BufferedSink buffer(Sink sink) {
        i.c(sink, "sink");
        return Okio.buffer(sink);
    }

    public final Sink sink(File file) {
        i.c(file, "file");
        return Okio__JvmOkioKt.sink$default(file, false, 1, null);
    }

    public final Source source(File file) {
        i.c(file, "file");
        return Okio.source(file);
    }

    public final BufferedSource buffer(Source source) {
        i.c(source, BYDAutoMultimediaDevice.PARAM_SOURCE);
        return Okio.buffer(source);
    }

    public final Sink sink(OutputStream outputStream) {
        i.c(outputStream, "outputStream");
        return Okio.sink(outputStream);
    }

    public final Source source(InputStream inputStream) {
        i.c(inputStream, "inputStream");
        return Okio.source(inputStream);
    }

    public final Sink sink(Path path, OpenOption... openOptionArr) {
        i.c(path, "path");
        i.c(openOptionArr, "options");
        return Okio.sink(path, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
    }

    public final Source source(Path path, OpenOption... openOptionArr) {
        i.c(path, "path");
        i.c(openOptionArr, "options");
        return Okio.source(path, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
    }

    public final Sink sink(Socket socket) {
        i.c(socket, "socket");
        return Okio.sink(socket);
    }

    public final Source source(Socket socket) {
        i.c(socket, "socket");
        return Okio.source(socket);
    }
}
