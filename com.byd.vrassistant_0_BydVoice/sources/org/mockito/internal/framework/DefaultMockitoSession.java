package org.mockito.internal.framework;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoSession;
import org.mockito.exceptions.misusing.RedundantListenerException;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.junit.TestFinishedEvent;
import org.mockito.internal.junit.UniversalTestListener;
import org.mockito.internal.util.MockitoLogger;
import org.mockito.quality.Strictness;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultMockitoSession implements MockitoSession {
    private final UniversalTestListener listener;
    private final Object testClassInstance;

    public DefaultMockitoSession(Object obj, Strictness strictness, MockitoLogger mockitoLogger) {
        this.testClassInstance = obj;
        UniversalTestListener universalTestListener = new UniversalTestListener(strictness, mockitoLogger);
        this.listener = universalTestListener;
        try {
            Mockito.framework().addListener(universalTestListener);
        } catch (RedundantListenerException unused) {
            Reporter.unfinishedMockingSession();
        }
        MockitoAnnotations.initMocks(obj);
    }

    @Override // org.mockito.MockitoSession
    public void finishMocking() {
        Mockito.framework().removeListener(this.listener);
        this.listener.testFinished(new TestFinishedEvent() { // from class: org.mockito.internal.framework.DefaultMockitoSession.1
            @Override // org.mockito.internal.junit.TestFinishedEvent
            public Throwable getFailure() {
                return null;
            }

            @Override // org.mockito.internal.junit.TestFinishedEvent
            public String getTestMethodName() {
                return null;
            }

            @Override // org.mockito.internal.junit.TestFinishedEvent
            public Object getTestClassInstance() {
                return DefaultMockitoSession.this.testClassInstance;
            }
        });
        Mockito.validateMockitoUsage();
    }
}
