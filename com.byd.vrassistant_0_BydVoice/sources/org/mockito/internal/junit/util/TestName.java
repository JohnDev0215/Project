package org.mockito.internal.junit.util;

import org.mockito.internal.junit.TestFinishedEvent;

/* JADX INFO: loaded from: classes4.dex */
public class TestName {
    public static String getTestName(TestFinishedEvent testFinishedEvent) {
        return testFinishedEvent.getTestClassInstance().getClass().getSimpleName() + "." + testFinishedEvent.getTestMethodName();
    }
}
