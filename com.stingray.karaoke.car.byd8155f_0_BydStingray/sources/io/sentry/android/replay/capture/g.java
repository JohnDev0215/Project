package io.sentry.android.replay.capture;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public final class g implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return h6.f.b(Long.valueOf(((io.sentry.rrweb.b) obj).f16836m), Long.valueOf(((io.sentry.rrweb.b) obj2).f16836m));
    }
}
