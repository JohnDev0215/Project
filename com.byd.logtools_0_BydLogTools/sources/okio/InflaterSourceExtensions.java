package okio;

import java.util.zip.Inflater;
import kotlin.jvm.internal.i;

/* JADX INFO: renamed from: okio.-InflaterSourceExtensions, reason: invalid class name */
/* JADX INFO: compiled from: InflaterSource.kt */
/* JADX INFO: loaded from: classes.dex */
public final class InflaterSourceExtensions {
    public static final InflaterSource inflate(Source source, Inflater inflater) {
        i.c(source, "$this$inflate");
        i.c(inflater, "inflater");
        return new InflaterSource(source, inflater);
    }

    public static /* synthetic */ InflaterSource inflate$default(Source source, Inflater inflater, int i, Object obj) {
        if ((i & 1) != 0) {
            inflater = new Inflater();
        }
        i.c(source, "$this$inflate");
        i.c(inflater, "inflater");
        return new InflaterSource(source, inflater);
    }
}
