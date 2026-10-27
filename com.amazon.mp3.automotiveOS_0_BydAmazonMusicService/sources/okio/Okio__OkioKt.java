package okio;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Okio.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\n\u0010\u0002\u001a\u00020\u0001*\u00020\u0000\u001a\n\u0010\u0002\u001a\u00020\u0004*\u00020\u0003\u001a\u000f\u0010\u0007\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lokio/Source;", "Lokio/BufferedSource;", "buffer", "Lokio/Sink;", "Lokio/BufferedSink;", "blackhole", "()Lokio/Sink;", "blackholeSink", "okio"}, k = 5, mv = {1, 5, 1}, xs = "okio/Okio")
final /* synthetic */ class Okio__OkioKt {
    public static final BufferedSource buffer(Source source) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        return new buffer(source);
    }

    public static final BufferedSink buffer(Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        return new buffer(sink);
    }

    @JvmName(name = "blackhole")
    public static final Sink blackhole() {
        return new BlackholeSink();
    }
}
