package com.facebook.systrace;

import androidx.tracing.Trace;

/* JADX INFO: loaded from: classes6.dex */
public class Systrace {
    public static boolean isTracing(long j) {
        return false;
    }

    public static void registerListener(TraceListener traceListener) {
    }

    public static void traceInstant(long j, String str, EventScope eventScope) {
    }

    public static void unregisterListener(TraceListener traceListener) {
    }

    public enum EventScope {
        THREAD('t'),
        PROCESS('p'),
        GLOBAL('g');

        private final char mCode;

        EventScope(char c) {
            this.mCode = c;
        }

        public char getCode() {
            return this.mCode;
        }
    }

    public static void beginSection(long j, String str) {
        Trace.beginSection(str);
    }

    public static void endSection(long j) {
        Trace.endSection();
    }

    public static void beginAsyncSection(long j, String str, int i) {
        Trace.beginAsyncSection(str, i);
    }

    public static void beginAsyncSection(long j, String str, int i, long j2) {
        beginAsyncSection(j, str, i);
    }

    public static void endAsyncSection(long j, String str, int i) {
        Trace.endAsyncSection(str, i);
    }

    public static void endAsyncSection(long j, String str, int i, long j2) {
        endAsyncSection(j, str, i);
    }

    public static void traceCounter(long j, String str, int i) {
        Trace.setCounter(str, i);
    }

    public static void startAsyncFlow(long j, String str, int i) {
        beginAsyncSection(j, str, i);
    }

    public static void endAsyncFlow(long j, String str, int i) {
        endAsyncSection(j, str, i);
    }
}
