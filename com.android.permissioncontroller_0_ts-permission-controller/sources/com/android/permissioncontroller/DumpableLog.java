package com.android.permissioncontroller;

import android.util.Log;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DumpableLog.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J\"\u0010\u0010\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\"\u0010\u0011\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0013H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J\"\u0010\u0015\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\"\u0010\u0016\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\"\u0010\u0017\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lcom/android/permissioncontroller/DumpableLog;", "", "()V", "MAX_FILE_SIZE", "", "file", "Ljava/io/File;", "lock", "addLogToDump", "", "level", "", "tag", "message", "exception", "", "d", "e", "get", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "i", "v", "w", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class DumpableLog {
    private static final int MAX_FILE_SIZE = 65536;
    private static final File file;
    public static final DumpableLog INSTANCE = new DumpableLog();
    private static final Object lock = new Object();

    static {
        PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
        Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
        File file2 = new File(permissionControllerApplication.getFilesDir(), Constants.LOGS_TO_DUMP_FILE);
        file = file2;
        file2.createNewFile();
    }

    private DumpableLog() {
    }

    public static /* synthetic */ void v$default(DumpableLog dumpableLog, String str, String str2, Throwable th, int i, Object obj) throws Throwable {
        if ((i & 4) != 0) {
            th = (Throwable) null;
        }
        dumpableLog.v(str, str2, th);
    }

    public final void v(String tag, String message, Throwable exception) throws Throwable {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Log.v(tag, message, exception);
        addLogToDump("v", tag, message, exception);
    }

    public static /* synthetic */ void d$default(DumpableLog dumpableLog, String str, String str2, Throwable th, int i, Object obj) throws Throwable {
        if ((i & 4) != 0) {
            th = (Throwable) null;
        }
        dumpableLog.d(str, str2, th);
    }

    public final void d(String tag, String message, Throwable exception) throws Throwable {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Log.d(tag, message, exception);
        addLogToDump("d", tag, message, exception);
    }

    public static /* synthetic */ void i$default(DumpableLog dumpableLog, String str, String str2, Throwable th, int i, Object obj) throws Throwable {
        if ((i & 4) != 0) {
            th = (Throwable) null;
        }
        dumpableLog.i(str, str2, th);
    }

    public final void i(String tag, String message, Throwable exception) throws Throwable {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Log.i(tag, message, exception);
        addLogToDump("i", tag, message, exception);
    }

    public static /* synthetic */ void w$default(DumpableLog dumpableLog, String str, String str2, Throwable th, int i, Object obj) throws Throwable {
        if ((i & 4) != 0) {
            th = (Throwable) null;
        }
        dumpableLog.w(str, str2, th);
    }

    public final void w(String tag, String message, Throwable exception) throws Throwable {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Log.w(tag, message, exception);
        addLogToDump("w", tag, message, exception);
    }

    public static /* synthetic */ void e$default(DumpableLog dumpableLog, String str, String str2, Throwable th, int i, Object obj) throws Throwable {
        if ((i & 4) != 0) {
            th = (Throwable) null;
        }
        dumpableLog.e(str, str2, th);
    }

    public final void e(String tag, String message, Throwable exception) throws Throwable {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Log.e(tag, message, exception);
        addLogToDump("e", tag, message, exception);
    }

    private final void addLogToDump(String level, String tag, String message, Throwable exception) throws Throwable {
        File file2;
        StringBuilder sb;
        String strStringPlus;
        synchronized (lock) {
            try {
                if (file.length() > 65536) {
                    List dump = FilesKt.readLines$default(file, null, 1, null);
                    FilesKt.writeText$default(file, "truncated at " + System.currentTimeMillis() + '\n', null, 2, null);
                    Iterable $this$forEach$iv = dump.subList(dump.size() / 2, dump.size());
                    for (Object element$iv : $this$forEach$iv) {
                        String it = (String) element$iv;
                        FilesKt.appendText$default(file, it + "\n", null, 2, null);
                    }
                }
                file2 = file;
                sb = new StringBuilder();
                sb.append(System.currentTimeMillis());
                sb.append(' ');
                try {
                    sb.append(tag);
                    sb.append(':');
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                sb.append(level);
                sb.append(' ');
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
            try {
                sb.append(message);
                sb.append(' ');
                if (exception == null || (strStringPlus = Intrinsics.stringPlus(exception.getMessage(), Log.getStackTraceString(exception))) == null) {
                    strStringPlus = "";
                }
                sb.append(strStringPlus);
                sb.append('\n');
                FilesKt.appendText$default(file2, sb.toString(), null, 2, null);
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th4) {
                th = th4;
                throw th;
            }
        }
    }

    public final Object get(Continuation<? super List<String>> continuation) {
        List lines$default;
        synchronized (lock) {
            lines$default = FilesKt.readLines$default(file, null, 1, null);
        }
        return lines$default;
    }
}
