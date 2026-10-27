package android.os;

import dalvik.annotation.optimization.CriticalNative;
import dalvik.annotation.optimization.FastNative;

/* JADX INFO: loaded from: classes2.dex */
public final class Trace {
    private static final int MAX_SECTION_NAME_LEN = 127;
    private static final String TAG = "Trace";
    public static final long TRACE_TAG_ACTIVITY_MANAGER = 64;
    public static final long TRACE_TAG_ADB = 4194304;
    public static final long TRACE_TAG_AIDL = 16777216;
    public static final long TRACE_TAG_ALWAYS = 1;
    public static final long TRACE_TAG_APEX_MANAGER = 262144;
    public static final long TRACE_TAG_APP = 4096;
    public static final long TRACE_TAG_AUDIO = 256;
    public static final long TRACE_TAG_BIONIC = 65536;
    public static final long TRACE_TAG_CAMERA = 1024;
    public static final long TRACE_TAG_DALVIK = 16384;
    public static final long TRACE_TAG_DATABASE = 1048576;
    public static final long TRACE_TAG_GRAPHICS = 2;
    public static final long TRACE_TAG_HAL = 2048;
    public static final long TRACE_TAG_INPUT = 4;
    public static final long TRACE_TAG_NETWORK = 2097152;
    public static final long TRACE_TAG_NEVER = 0;
    public static final long TRACE_TAG_NNAPI = 33554432;
    private static final long TRACE_TAG_NOT_READY = Long.MIN_VALUE;
    public static final long TRACE_TAG_PACKAGE_MANAGER = 262144;
    public static final long TRACE_TAG_POWER = 131072;
    public static final long TRACE_TAG_RESOURCES = 8192;
    public static final long TRACE_TAG_RRO = 67108864;
    public static final long TRACE_TAG_RS = 32768;
    public static final long TRACE_TAG_SYNC_MANAGER = 128;
    public static final long TRACE_TAG_SYSTEM_SERVER = 524288;
    public static final long TRACE_TAG_VIBRATOR = 8388608;
    public static final long TRACE_TAG_VIDEO = 512;
    public static final long TRACE_TAG_VIEW = 8;
    public static final long TRACE_TAG_WEBVIEW = 16;
    public static final long TRACE_TAG_WINDOW_MANAGER = 32;
    private static volatile long sEnabledTags = Long.MIN_VALUE;
    private static int sZygoteDebugFlags;

    @FastNative
    private static native void nativeAsyncTraceBegin(long j, String str, int i);

    @FastNative
    private static native void nativeAsyncTraceEnd(long j, String str, int i);

    @CriticalNative
    private static native long nativeGetEnabledTags();

    private static native void nativeSetAppTracingAllowed(boolean z);

    private static native void nativeSetTracingEnabled(boolean z);

    @FastNative
    private static native void nativeTraceBegin(long j, String str);

    @FastNative
    private static native void nativeTraceCounter(long j, String str, long j2);

    @FastNative
    private static native void nativeTraceEnd(long j);

    private Trace() {
    }

    public static boolean isTagEnabled(long j) {
        return (j & nativeGetEnabledTags()) != 0;
    }

    public static void traceCounter(long j, String str, int i) {
        if (isTagEnabled(j)) {
            nativeTraceCounter(j, str, i);
        }
    }

    public static void setAppTracingAllowed(boolean z) {
        nativeSetAppTracingAllowed(z);
    }

    public static void setTracingEnabled(boolean z, int i) {
        nativeSetTracingEnabled(z);
        sZygoteDebugFlags = i;
    }

    public static void traceBegin(long j, String str) {
        if (isTagEnabled(j)) {
            nativeTraceBegin(j, str);
        }
    }

    public static void traceEnd(long j) {
        if (isTagEnabled(j)) {
            nativeTraceEnd(j);
        }
    }

    public static void asyncTraceBegin(long j, String str, int i) {
        if (isTagEnabled(j)) {
            nativeAsyncTraceBegin(j, str, i);
        }
    }

    public static void asyncTraceEnd(long j, String str, int i) {
        if (isTagEnabled(j)) {
            nativeAsyncTraceEnd(j, str, i);
        }
    }

    public static boolean isEnabled() {
        return isTagEnabled(4096L);
    }

    public static void beginSection(String str) {
        if (isTagEnabled(4096L)) {
            if (str.length() > 127) {
                throw new IllegalArgumentException("sectionName is too long");
            }
            nativeTraceBegin(4096L, str);
        }
    }

    public static void endSection() {
        if (isTagEnabled(4096L)) {
            nativeTraceEnd(4096L);
        }
    }

    public static void beginAsyncSection(String str, int i) {
        asyncTraceBegin(4096L, str, i);
    }

    public static void endAsyncSection(String str, int i) {
        asyncTraceEnd(4096L, str, i);
    }

    public static void setCounter(String str, long j) {
        if (isTagEnabled(4096L)) {
            nativeTraceCounter(4096L, str, j);
        }
    }
}
