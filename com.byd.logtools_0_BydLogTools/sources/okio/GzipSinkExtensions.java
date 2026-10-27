package okio;

import kotlin.jvm.internal.i;

/* JADX INFO: renamed from: okio.-GzipSinkExtensions, reason: invalid class name */
/* JADX INFO: compiled from: GzipSink.kt */
/* JADX INFO: loaded from: classes.dex */
public final class GzipSinkExtensions {
    public static final GzipSink gzip(Sink sink) {
        i.c(sink, "$this$gzip");
        return new GzipSink(sink);
    }
}
