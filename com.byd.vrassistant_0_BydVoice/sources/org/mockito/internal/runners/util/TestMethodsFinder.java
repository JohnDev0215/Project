package org.mockito.internal.runners.util;

import java.lang.reflect.Method;
import org.junit.Test;

/* JADX INFO: loaded from: classes4.dex */
public class TestMethodsFinder {
    private TestMethodsFinder() {
    }

    public static boolean hasTestMethods(Class<?> cls) {
        for (Method method : cls.getMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                return true;
            }
        }
        return false;
    }
}
