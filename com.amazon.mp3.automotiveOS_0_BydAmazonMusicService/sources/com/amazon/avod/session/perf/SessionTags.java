package com.amazon.avod.session.perf;

import com.amazon.avod.perf.Marker;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SessionTags {
    public static final String TRACE_INITIALIZATION = fullName("Initialization");
    public static final Marker RETRIEVAL_START = new Marker("SESSION_RETRIEVAL_START");
    public static final Marker RETRIEVAL_SUCCESS = new Marker("SESSION_RETRIEVAL_SUCCESS");
    public static final Marker RETRIEVAL_FAILURE = new Marker("SESSION_RETRIEVAL_FAILURE");

    static String fullName(@Nonnull String str) {
        return "SessionMetric-" + str;
    }
}
