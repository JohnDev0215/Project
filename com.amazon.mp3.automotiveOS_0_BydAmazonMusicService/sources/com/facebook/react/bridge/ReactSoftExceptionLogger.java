package com.facebook.react.bridge;

import com.facebook.common.logging.FLog;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
public class ReactSoftExceptionLogger {
    private static final List<ReactSoftExceptionListener> sListeners = new CopyOnWriteArrayList();

    public interface ReactSoftExceptionListener {
        void logSoftException(String str, Throwable th);
    }

    @DoNotStrip
    public static void addListener(ReactSoftExceptionListener reactSoftExceptionListener) {
        List<ReactSoftExceptionListener> list = sListeners;
        if (list.contains(reactSoftExceptionListener)) {
            return;
        }
        list.add(reactSoftExceptionListener);
    }

    @DoNotStrip
    public static void removeListener(ReactSoftExceptionListener reactSoftExceptionListener) {
        sListeners.remove(reactSoftExceptionListener);
    }

    @DoNotStrip
    public static void clearListeners() {
        sListeners.clear();
    }

    @DoNotStrip
    public static void logSoftExceptionVerbose(String str, Throwable th) {
        logSoftException(str + "|" + th.getClass().getSimpleName() + ":" + th.getMessage(), th);
    }

    @DoNotStrip
    public static void logSoftException(String str, Throwable th) {
        List<ReactSoftExceptionListener> list = sListeners;
        if (list.size() > 0) {
            Iterator<ReactSoftExceptionListener> it = list.iterator();
            while (it.hasNext()) {
                it.next().logSoftException(str, th);
            }
            return;
        }
        FLog.e(str, "Unhandled SoftException", th);
    }

    @DoNotStrip
    private static void logNoThrowSoftExceptionWithMessage(String str, String str2) {
        logSoftException(str, new ReactNoCrashSoftException(str2));
    }
}
