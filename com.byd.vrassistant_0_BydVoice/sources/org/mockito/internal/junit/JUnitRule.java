package org.mockito.internal.junit;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.MockitoLogger;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

/* JADX INFO: loaded from: classes4.dex */
public class JUnitRule implements MockitoRule {
    private final UniversalTestListener listener;
    private final MockitoLogger logger;

    public JUnitRule(MockitoLogger mockitoLogger, Strictness strictness) {
        this.logger = mockitoLogger;
        this.listener = new UniversalTestListener(strictness, mockitoLogger);
    }

    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object obj) {
        return new Statement() { // from class: org.mockito.internal.junit.JUnitRule.1
            public void evaluate() throws Throwable {
                Mockito.framework().addListener(JUnitRule.this.listener);
                try {
                    MockitoAnnotations.initMocks(obj);
                    Throwable thEvaluateSafely = evaluateSafely(statement);
                    Mockito.framework().removeListener(JUnitRule.this.listener);
                    JUnitRule.this.listener.testFinished(new DefaultTestFinishedEvent(obj, frameworkMethod.getName(), thEvaluateSafely));
                    if (thEvaluateSafely != null) {
                        throw thEvaluateSafely;
                    }
                    Mockito.validateMockitoUsage();
                } catch (Throwable th) {
                    Mockito.framework().removeListener(JUnitRule.this.listener);
                    throw th;
                }
            }

            private Throwable evaluateSafely(Statement statement2) {
                try {
                    statement2.evaluate();
                    return null;
                } catch (Throwable th) {
                    return th;
                }
            }
        };
    }

    @Override // org.mockito.junit.MockitoRule
    public MockitoRule silent() {
        return new JUnitRule(this.logger, Strictness.LENIENT);
    }

    @Override // org.mockito.junit.MockitoRule
    public MockitoRule strictness(Strictness strictness) {
        this.listener.setStrictness(strictness);
        return this;
    }
}
