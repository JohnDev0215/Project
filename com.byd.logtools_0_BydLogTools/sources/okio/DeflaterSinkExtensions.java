package okio;

import java.util.zip.Deflater;
import kotlin.jvm.internal.i;

/* JADX INFO: renamed from: okio.-DeflaterSinkExtensions, reason: invalid class name */
/* JADX INFO: compiled from: DeflaterSink.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DeflaterSinkExtensions {
    public static final DeflaterSink deflate(Sink sink, Deflater deflater) {
        i.c(sink, "$this$deflate");
        i.c(deflater, "deflater");
        return new DeflaterSink(sink, deflater);
    }

    public static /* synthetic */ DeflaterSink deflate$default(Sink sink, Deflater deflater, int i, Object obj) {
        if ((i & 1) != 0) {
            deflater = new Deflater();
        }
        i.c(sink, "$this$deflate");
        i.c(deflater, "deflater");
        return new DeflaterSink(sink, deflater);
    }
}
