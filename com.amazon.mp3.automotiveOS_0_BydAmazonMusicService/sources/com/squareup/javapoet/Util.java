package com.squareup.javapoet;

import com.amplifyframework.core.model.ModelIdentifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes7.dex */
final class Util {
    private Util() {
    }

    static void checkArgument(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    static <T> T checkNotNull(T t, String str, Object... objArr) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.format(str, objArr));
    }

    static void checkState(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalStateException(String.format(str, objArr));
        }
    }

    static <T> List<T> immutableList(Collection<T> collection) {
        return Collections.unmodifiableList(new ArrayList(collection));
    }

    static <T> Set<T> immutableSet(Collection<T> collection) {
        return Collections.unmodifiableSet(new LinkedHashSet(collection));
    }

    static <T> Set<T> union(Set<T> set, Set<T> set2) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(set);
        linkedHashSet.addAll(set2);
        return linkedHashSet;
    }

    static String characterLiteralWithoutSingleQuotes(char c) {
        if (c == '\f') {
            return "\\f";
        }
        if (c == '\r') {
            return "\\r";
        }
        if (c == '\"') {
            return ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR;
        }
        if (c == '\'') {
            return "\\'";
        }
        if (c == '\\') {
            return "\\\\";
        }
        switch (c) {
            case '\b':
                return "\\b";
            case '\t':
                return "\\t";
            case '\n':
                return "\\n";
            default:
                return Character.isISOControl(c) ? String.format("\\u%04x", Integer.valueOf(c)) : Character.toString(c);
        }
    }

    static String stringLiteralWithDoubleQuotes(String str, String str2) {
        StringBuilder sb = new StringBuilder(str.length() + 2);
        sb.append('\"');
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\'') {
                sb.append("'");
            } else if (cCharAt == '\"') {
                sb.append("\\\"");
            } else {
                sb.append(characterLiteralWithoutSingleQuotes(cCharAt));
                if (cCharAt == '\n' && i + 1 < str.length()) {
                    sb.append("\"\n");
                    sb.append(str2);
                    sb.append(str2);
                    sb.append("+ \"");
                }
            }
        }
        sb.append('\"');
        return sb.toString();
    }
}
