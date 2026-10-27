package com.google.common.base;

import java.util.logging.Logger;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
final class Platform {
    private static final PatternCompiler patternCompiler;

    static String nullToEmpty(String str) {
        return str == null ? "" : str;
    }

    static {
        Logger.getLogger(Platform.class.getName());
        patternCompiler = loadPatternCompiler();
    }

    private Platform() {
    }

    static long systemNanoTime() {
        return System.nanoTime();
    }

    static CommonPattern compilePattern(String str) {
        Preconditions.checkNotNull(str);
        return patternCompiler.compile(str);
    }

    private static PatternCompiler loadPatternCompiler() {
        return new JdkPatternCompiler();
    }

    private static final class JdkPatternCompiler implements PatternCompiler {
        private JdkPatternCompiler() {
        }

        @Override // com.google.common.base.PatternCompiler
        public CommonPattern compile(String str) {
            return new JdkPattern(Pattern.compile(str));
        }
    }
}
