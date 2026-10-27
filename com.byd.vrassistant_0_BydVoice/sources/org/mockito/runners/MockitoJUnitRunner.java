package org.mockito.runners;

import java.lang.reflect.InvocationTargetException;
import org.junit.runner.Description;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.notification.RunNotifier;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class MockitoJUnitRunner extends org.mockito.junit.MockitoJUnitRunner {

    @Deprecated
    public static class Silent extends MockitoJUnitRunner {
        public Silent(Class<?> cls) throws InvocationTargetException {
            super(cls);
        }
    }

    @Deprecated
    public static class Strict extends MockitoJUnitRunner {
        public Strict(Class<?> cls) throws InvocationTargetException {
            super(cls);
        }
    }

    public MockitoJUnitRunner(Class<?> cls) throws InvocationTargetException {
        super(cls);
    }

    @Override // org.mockito.junit.MockitoJUnitRunner
    @Deprecated
    public void run(RunNotifier runNotifier) {
        super.run(runNotifier);
    }

    @Override // org.mockito.junit.MockitoJUnitRunner
    @Deprecated
    public Description getDescription() {
        return super.getDescription();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.junit.runner.manipulation.NoTestsRemainException */
    @Override // org.mockito.junit.MockitoJUnitRunner
    @Deprecated
    public void filter(Filter filter) throws NoTestsRemainException {
        super.filter(filter);
    }
}
