package m6;

import android.util.Log;
import c6.x;
import h5.h0;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import y5.q;
import y5.s;

/* JADX INFO: compiled from: AndroidLog.kt */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Map<String, String> f8411b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c f8412c = new c();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final CopyOnWriteArraySet<Logger> f8410a = new CopyOnWriteArraySet<>();

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Package r12 = x.class.getPackage();
        String name = r12 != null ? r12.getName() : null;
        if (name != null) {
            linkedHashMap.put(name, "OkHttp");
        }
        String name2 = x.class.getName();
        kotlin.jvm.internal.l.d(name2, "OkHttpClient::class.java.name");
        linkedHashMap.put(name2, "okhttp.OkHttpClient");
        String name3 = k6.e.class.getName();
        kotlin.jvm.internal.l.d(name3, "Http2::class.java.name");
        linkedHashMap.put(name3, "okhttp.Http2");
        String name4 = g6.e.class.getName();
        kotlin.jvm.internal.l.d(name4, "TaskRunner::class.java.name");
        linkedHashMap.put(name4, "okhttp.TaskRunner");
        linkedHashMap.put("okhttp3.mockwebserver.MockWebServer", "okhttp.MockWebServer");
        f8411b = h0.k(linkedHashMap);
    }

    private c() {
    }

    private final void c(String str, String str2) {
        Logger logger = Logger.getLogger(str);
        if (f8410a.add(logger)) {
            kotlin.jvm.internal.l.d(logger, "logger");
            logger.setUseParentHandlers(false);
            logger.setLevel(Log.isLoggable(str2, 3) ? Level.FINE : Log.isLoggable(str2, 4) ? Level.INFO : Level.WARNING);
            logger.addHandler(d.f8413a);
        }
    }

    private final String d(String str) {
        String str2 = f8411b.get(str);
        return str2 != null ? str2 : s.B0(str, 23);
    }

    public final void a(String loggerName, int i7, String message, Throwable th) {
        int iMin;
        kotlin.jvm.internal.l.e(loggerName, "loggerName");
        kotlin.jvm.internal.l.e(message, "message");
        String strD = d(loggerName);
        if (Log.isLoggable(strD, i7)) {
            if (th != null) {
                message = message + "\n" + Log.getStackTraceString(th);
            }
            int i8 = 0;
            int length = message.length();
            while (i8 < length) {
                int iP = q.P(message, '\n', i8, false, 4, null);
                if (iP == -1) {
                    iP = length;
                }
                while (true) {
                    iMin = Math.min(iP, i8 + 4000);
                    String strSubstring = message.substring(i8, iMin);
                    kotlin.jvm.internal.l.d(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    Log.println(i7, strD, strSubstring);
                    if (iMin >= iP) {
                        break;
                    } else {
                        i8 = iMin;
                    }
                }
                i8 = iMin + 1;
            }
        }
    }

    public final void b() {
        for (Map.Entry<String, String> entry : f8411b.entrySet()) {
            c(entry.getKey(), entry.getValue());
        }
    }
}
