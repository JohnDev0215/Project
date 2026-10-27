package org.mockito.internal.matchers.text;

import java.lang.reflect.Method;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.util.ObjectMethodsGuru;
import org.mockito.internal.util.StringUtil;

/* JADX INFO: loaded from: classes4.dex */
class MatcherToString {
    MatcherToString() {
    }

    static String toString(ArgumentMatcher<?> argumentMatcher) {
        for (Class<?> superclass = argumentMatcher.getClass(); superclass != Object.class; superclass = superclass.getSuperclass()) {
            for (Method method : superclass.getDeclaredMethods()) {
                if (ObjectMethodsGuru.isToStringMethod(method)) {
                    return argumentMatcher.toString();
                }
            }
        }
        return StringUtil.decamelizeMatcher(argumentMatcher.getClass().getSimpleName());
    }
}
