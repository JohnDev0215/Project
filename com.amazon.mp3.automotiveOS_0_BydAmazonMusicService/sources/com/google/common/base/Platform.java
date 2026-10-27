package com.google.common.base;

import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
final class Platform {
    private static final Logger logger = Logger.getLogger(Platform.class.getName());
    private static final PatternCompiler patternCompiler = loadPatternCompiler();

    static String nullToEmpty(@CheckForNull String str) {
        return str == null ? "" : str;
    }

    private Platform() {
    }

    static long systemNanoTime() {
        return System.nanoTime();
    }

    static <T extends Enum<T>> Optional<T> getEnumIfPresent(Class<T> cls, String str) {
        WeakReference<? extends Enum<?>> weakReference = Enums.getEnumConstants(cls).get(str);
        return weakReference == null ? Optional.absent() : Optional.of(cls.cast(weakReference.get()));
    }

    static String formatCompact4Digits(double d) {
        return String.format(Locale.ROOT, "%.4g", Double.valueOf(d));
    }

    static boolean stringIsNullOrEmpty(@CheckForNull String str) {
        return str == null || str.isEmpty();
    }

    @CheckForNull
    static String emptyToNull(@CheckForNull String str) {
        if (stringIsNullOrEmpty(str)) {
            return null;
        }
        return str;
    }

    private static PatternCompiler loadPatternCompiler() {
        return new JdkPatternCompiler();
    }

    private static final class JdkPatternCompiler implements PatternCompiler {
        private JdkPatternCompiler() {
        }
    }
}
