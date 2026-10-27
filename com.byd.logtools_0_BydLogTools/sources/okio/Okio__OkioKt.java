package okio;

import kotlin.jvm.internal.i;

/* JADX INFO: compiled from: Okio.kt */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class Okio__OkioKt {
    public static final Sink blackhole() {
        return new BlackholeSink();
    }

    public static final BufferedSource buffer(Source source) {
        i.c(source, "$this$buffer");
        return new RealBufferedSource(source);
    }

    public static final BufferedSink buffer(Sink sink) {
        i.c(sink, "$this$buffer");
        return new RealBufferedSink(sink);
    }
}
