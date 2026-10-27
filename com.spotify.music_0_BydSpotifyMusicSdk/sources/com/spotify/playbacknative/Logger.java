package com.spotify.playbacknative;

import android.util.Log;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Logger {
    public static final Companion Companion = new Companion(null);

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void d(String str, Object... objArr) {
            v03.j(str, "s");
            v03.j(objArr, "args");
            String unused = LoggerKt.TAG;
            Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
            v03.i(String.format(str, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length)), "format(format, *args)");
        }

        public final void e(String str, Object... objArr) {
            v03.j(str, "s");
            v03.j(objArr, "args");
            String str2 = LoggerKt.TAG;
            Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
            String str3 = String.format(str, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length));
            v03.i(str3, "format(format, *args)");
            Log.e(str2, str3);
        }

        public final void w(String str, Object... objArr) {
            v03.j(str, "s");
            v03.j(objArr, "args");
            String unused = LoggerKt.TAG;
            Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
            v03.i(String.format(str, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length)), "format(format, *args)");
        }

        public final void w(Throwable th, String str, Object... objArr) {
            v03.j(th, "t");
            v03.j(str, "s");
            v03.j(objArr, "args");
            String unused = LoggerKt.TAG;
            Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
            v03.i(String.format(str, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length)), "format(format, *args)");
        }
    }

    public static final void d(String str, Object... objArr) {
        Companion.d(str, objArr);
    }

    public static final void e(String str, Object... objArr) {
        Companion.e(str, objArr);
    }

    public static final void w(String str, Object... objArr) {
        Companion.w(str, objArr);
    }

    public static final void w(Throwable th, String str, Object... objArr) {
        Companion.w(th, str, objArr);
    }
}
