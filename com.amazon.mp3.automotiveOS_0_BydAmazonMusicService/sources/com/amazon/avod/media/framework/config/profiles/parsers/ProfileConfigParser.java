package com.amazon.avod.media.framework.config.profiles.parsers;

import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ProfileConfigParser {
    private static Object BOOLEAN_RESULT_PARSER_INSTANCE;
    private static Object INT_RESULT_PARSER_INSTANCE;
    private static Object STRING_RESULT_PARSER_INSTANCE;

    public static class IntProfileResultParser<T extends Integer> implements ProfileResultParser<T> {
        private IntProfileResultParser() {
        }

        public static <T extends Integer> IntProfileResultParser<T> getResultParser() {
            if (ProfileConfigParser.INT_RESULT_PARSER_INSTANCE == null) {
                Object unused = ProfileConfigParser.INT_RESULT_PARSER_INSTANCE = new IntProfileResultParser();
            }
            return (IntProfileResultParser) ProfileConfigParser.INT_RESULT_PARSER_INSTANCE;
        }

        @Override // com.amazon.avod.media.framework.config.profiles.parsers.ProfileResultParser
        /* JADX INFO: renamed from: parse */
        public T parse2(@Nonnull String str) {
            Preconditions.checkNotNull(str, "input");
            try {
                return (T) Integer.valueOf(str);
            } catch (NumberFormatException unused) {
                DLog.logf("Error in parsing value: %s", str);
                return null;
            }
        }
    }

    public static class StringProfileResultParser<T extends String> implements ProfileResultParser<T> {
        private StringProfileResultParser() {
        }

        public static <T extends String> StringProfileResultParser<T> getResultParser() {
            if (ProfileConfigParser.STRING_RESULT_PARSER_INSTANCE == null) {
                Object unused = ProfileConfigParser.STRING_RESULT_PARSER_INSTANCE = new StringProfileResultParser();
            }
            return (StringProfileResultParser) ProfileConfigParser.STRING_RESULT_PARSER_INSTANCE;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.amazon.avod.media.framework.config.profiles.parsers.ProfileResultParser
        /* JADX INFO: renamed from: parse */
        public T parse2(@Nonnull String str) {
            Preconditions.checkNotNull(str, "input");
            return str;
        }
    }

    public static class BooleanProfileResultParser<T extends Boolean> implements ProfileResultParser<Boolean> {
        private BooleanProfileResultParser() {
        }

        public static <T extends Boolean> BooleanProfileResultParser<T> getResultParser() {
            if (ProfileConfigParser.BOOLEAN_RESULT_PARSER_INSTANCE == null) {
                Object unused = ProfileConfigParser.BOOLEAN_RESULT_PARSER_INSTANCE = new BooleanProfileResultParser();
            }
            return (BooleanProfileResultParser) ProfileConfigParser.BOOLEAN_RESULT_PARSER_INSTANCE;
        }

        @Override // com.amazon.avod.media.framework.config.profiles.parsers.ProfileResultParser
        /* JADX INFO: renamed from: parse, reason: merged with bridge method [inline-methods] */
        public Boolean parse2(@Nonnull String str) {
            Preconditions.checkNotNull(str, "input");
            return Boolean.valueOf(str);
        }
    }
}
