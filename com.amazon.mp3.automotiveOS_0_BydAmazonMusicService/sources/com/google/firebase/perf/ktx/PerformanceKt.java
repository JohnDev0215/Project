package com.google.firebase.perf.ktx;

import com.amazon.alexa.comms.mediaInsights.common.TracePublisherServiceMessageType;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.metrics.HttpMetric;
import com.google.firebase.perf.metrics.Trace;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Performance.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a8\u0010\u0007\u001a\u0002H\b\"\u0004\b\u0000\u0010\b2\u0006\u0010\t\u001a\u00020\u00012\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u0002H\b0\u000b¢\u0006\u0002\b\rH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a)\u0010\u0007\u001a\u00020\u000f*\u00020\u00102\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u000f0\u000b¢\u0006\u0002\b\rH\u0086\bø\u0001\u0000\u001a4\u0010\u0007\u001a\u0002H\b\"\u0004\b\u0000\u0010\b*\u00020\f2\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u0002H\b0\u000b¢\u0006\u0002\b\rH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0011\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0003*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0012"}, d2 = {"LIBRARY_NAME", "", "performance", "Lcom/google/firebase/perf/FirebasePerformance;", "Lcom/google/firebase/ktx/Firebase;", "getPerformance", "(Lcom/google/firebase/ktx/Firebase;)Lcom/google/firebase/perf/FirebasePerformance;", TracePublisherServiceMessageType.TRACE_MESSAGE_KEY, "T", "name", "block", "Lkotlin/Function1;", "Lcom/google/firebase/perf/metrics/Trace;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "", "Lcom/google/firebase/perf/metrics/HttpMetric;", "(Lcom/google/firebase/perf/metrics/Trace;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "com.google.firebase-firebase-perf-ktx"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class PerformanceKt {
    public static final String LIBRARY_NAME = "fire-perf-ktx";

    public static final FirebasePerformance getPerformance(Firebase firebase) {
        Intrinsics.checkNotNullParameter(firebase, "<this>");
        FirebasePerformance firebasePerformance = FirebasePerformance.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebasePerformance, "getInstance()");
        return firebasePerformance;
    }

    public static final void trace(HttpMetric httpMetric, Function1<? super HttpMetric, Unit> block) {
        Intrinsics.checkNotNullParameter(httpMetric, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        httpMetric.start();
        try {
            block.invoke(httpMetric);
        } finally {
            InlineMarker.finallyStart(1);
            httpMetric.stop();
            InlineMarker.finallyEnd(1);
        }
    }

    public static final <T> T trace(Trace trace, Function1<? super Trace, ? extends T> block) {
        Intrinsics.checkNotNullParameter(trace, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        trace.start();
        try {
            return block.invoke(trace);
        } finally {
            InlineMarker.finallyStart(1);
            trace.stop();
            InlineMarker.finallyEnd(1);
        }
    }

    public static final <T> T trace(String name, Function1<? super Trace, ? extends T> block) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(block, "block");
        Trace traceCreate = Trace.create(name);
        Intrinsics.checkNotNullExpressionValue(traceCreate, "create(name)");
        traceCreate.start();
        try {
            return block.invoke(traceCreate);
        } finally {
            InlineMarker.finallyStart(1);
            traceCreate.stop();
            InlineMarker.finallyEnd(1);
        }
    }
}
