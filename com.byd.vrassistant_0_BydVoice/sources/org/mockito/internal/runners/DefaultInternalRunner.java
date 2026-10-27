package org.mockito.internal.runners;

import org.junit.runner.Description;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.junit.MockitoTestListener;
import org.mockito.internal.util.Supplier;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultInternalRunner implements InternalRunner {
    private final BlockJUnit4ClassRunner runner;

    /* JADX INFO: renamed from: org.mockito.internal.runners.DefaultInternalRunner$1, reason: invalid class name */
    class AnonymousClass1 extends BlockJUnit4ClassRunner {
        private MockitoTestListener mockitoTestListener;
        public Object target;
        final /* synthetic */ Supplier val$listenerSupplier;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Class cls, Supplier supplier) {
            super(cls);
            this.val$listenerSupplier = supplier;
        }

        protected Statement withBefores(FrameworkMethod frameworkMethod, Object obj, Statement statement) {
            this.target = obj;
            this.mockitoTestListener = (MockitoTestListener) this.val$listenerSupplier.get();
            Mockito.framework().addListener(this.mockitoTestListener);
            MockitoAnnotations.initMocks(obj);
            return super.withBefores(frameworkMethod, obj, statement);
        }

        public void run(final RunNotifier runNotifier) {
            runNotifier.addListener(new RunListener() { // from class: org.mockito.internal.runners.DefaultInternalRunner.1.1
                Throwable failure;
                private boolean started;

                public void testStarted(Description description) throws Exception {
                    this.started = true;
                }

                public void testFailure(Failure failure) throws Exception {
                    this.failure = failure.getException();
                    if (this.started || AnonymousClass1.this.mockitoTestListener == null) {
                        return;
                    }
                    Mockito.framework().removeListener(AnonymousClass1.this.mockitoTestListener);
                }

                public void testFinished(Description description) throws Exception {
                    try {
                        if (AnonymousClass1.this.mockitoTestListener != null) {
                            Mockito.framework().removeListener(AnonymousClass1.this.mockitoTestListener);
                            AnonymousClass1.this.mockitoTestListener.testFinished(new DefaultTestFinishedEvent(AnonymousClass1.this.target, description.getMethodName(), this.failure));
                        }
                        Mockito.validateMockitoUsage();
                    } catch (Throwable th) {
                        runNotifier.fireTestFailure(new Failure(description, th));
                    }
                }
            });
            super.run(runNotifier);
        }
    }

    public DefaultInternalRunner(Class<?> cls, Supplier<MockitoTestListener> supplier) throws InitializationError {
        this.runner = new AnonymousClass1(cls, supplier);
    }

    @Override // org.mockito.internal.runners.InternalRunner
    public void run(RunNotifier runNotifier) {
        this.runner.run(runNotifier);
    }

    @Override // org.mockito.internal.runners.InternalRunner
    public Description getDescription() {
        return this.runner.getDescription();
    }

    public void filter(Filter filter) throws NoTestsRemainException {
        this.runner.filter(filter);
    }
}
