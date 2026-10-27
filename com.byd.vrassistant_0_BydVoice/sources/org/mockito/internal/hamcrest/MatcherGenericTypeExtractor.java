package org.mockito.internal.hamcrest;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Matcher;
import org.mockito.internal.util.reflection.GenericTypeExtractor;

/* JADX INFO: loaded from: classes4.dex */
public class MatcherGenericTypeExtractor {
    public static Class<?> genericTypeOfMatcher(Class<?> cls) {
        return GenericTypeExtractor.genericTypeOf(cls, BaseMatcher.class, Matcher.class);
    }
}
