package io.objectbox.reactive;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class SubscriptionBuilder<T> {
    private DataSubscriptionList dataSubscriptionList;
    private ErrorObserver errorObserver;
    private DataObserver<T> observer;
    private boolean onlyChanges;
    private final DataPublisher<T> publisher;
    private final Object publisherParam;
    private Scheduler scheduler;
    private boolean single;
    private DataTransformer<T, Object> transformer;
    private boolean weak;

    public SubscriptionBuilder(DataPublisher<T> dataPublisher, @Nullable Object obj) {
        this.publisher = dataPublisher;
        this.publisherParam = obj;
    }

    public SubscriptionBuilder<T> weak() {
        this.weak = true;
        return this;
    }

    public SubscriptionBuilder<T> single() {
        this.single = true;
        return this;
    }

    public SubscriptionBuilder<T> onlyChanges() {
        this.onlyChanges = true;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <TO> SubscriptionBuilder<TO> transform(DataTransformer<T, TO> dataTransformer) {
        if (this.transformer != null) {
            throw new IllegalStateException("Only one transformer allowed");
        }
        this.transformer = dataTransformer;
        return this;
    }

    public SubscriptionBuilder<T> onError(ErrorObserver errorObserver) {
        if (this.errorObserver != null) {
            throw new IllegalStateException("Only one errorObserver allowed");
        }
        this.errorObserver = errorObserver;
        return this;
    }

    public SubscriptionBuilder<T> on(Scheduler scheduler) {
        if (this.scheduler != null) {
            throw new IllegalStateException("Only one scheduler allowed");
        }
        this.scheduler = scheduler;
        return this;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public DataSubscription observer(DataObserver<T> dataObserver) {
        WeakDataObserver weakDataObserver;
        if (this.weak) {
            weakDataObserver = new WeakDataObserver(dataObserver);
            dataObserver = weakDataObserver;
        } else {
            weakDataObserver = null;
        }
        this.observer = dataObserver;
        DataSubscriptionImpl dataSubscriptionImpl = new DataSubscriptionImpl(this.publisher, this.publisherParam, dataObserver);
        if (weakDataObserver != null) {
            weakDataObserver.setSubscription(dataSubscriptionImpl);
        }
        DataSubscriptionList dataSubscriptionList = this.dataSubscriptionList;
        if (dataSubscriptionList != null) {
            dataSubscriptionList.add(dataSubscriptionImpl);
        }
        if (this.transformer != null || this.scheduler != null || this.errorObserver != null) {
            dataObserver = new ActionObserver(dataSubscriptionImpl);
        }
        if (this.single) {
            if (this.onlyChanges) {
                throw new IllegalStateException("Illegal combination of single() and onlyChanges()");
            }
            this.publisher.publishSingle(dataObserver, this.publisherParam);
        } else {
            this.publisher.subscribe(dataObserver, this.publisherParam);
            if (!this.onlyChanges) {
                this.publisher.publishSingle(dataObserver, this.publisherParam);
            }
        }
        return dataSubscriptionImpl;
    }

    public SubscriptionBuilder<T> dataSubscriptionList(DataSubscriptionList dataSubscriptionList) {
        this.dataSubscriptionList = dataSubscriptionList;
        return this;
    }

    class ActionObserver implements DataObserver<T>, DelegatingObserver<T> {
        private SubscriptionBuilder<T>.ActionObserver.SchedulerRunOnChange schedulerRunOnData;
        private SubscriptionBuilder<T>.ActionObserver.SchedulerRunOnError schedulerRunOnError;
        private final DataSubscriptionImpl subscription;

        public ActionObserver(DataSubscriptionImpl dataSubscriptionImpl) {
            this.subscription = dataSubscriptionImpl;
            if (SubscriptionBuilder.this.scheduler != null) {
                this.schedulerRunOnData = new SchedulerRunOnChange();
                if (SubscriptionBuilder.this.errorObserver != null) {
                    this.schedulerRunOnError = new SchedulerRunOnError();
                }
            }
        }

        @Override // io.objectbox.reactive.DataObserver
        public void onData(T t) {
            if (SubscriptionBuilder.this.transformer != null) {
                transformAndContinue(t);
            } else {
                callOnData(t);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void transformAndContinue(T t) {
            if (this.subscription.isCanceled()) {
                return;
            }
            try {
                callOnData(SubscriptionBuilder.this.transformer.transform(t));
            } catch (Throwable th) {
                callOnError(th, "Transformer failed without an ErrorObserver set");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void callOnError(Throwable th, String str) {
            if (SubscriptionBuilder.this.errorObserver != null) {
                if (this.subscription.isCanceled()) {
                    return;
                }
                if (SubscriptionBuilder.this.scheduler != null) {
                    SubscriptionBuilder.this.scheduler.run(this.schedulerRunOnError, th);
                    return;
                } else {
                    SubscriptionBuilder.this.errorObserver.onError(th);
                    return;
                }
            }
            RuntimeException runtimeException = new RuntimeException(str, th);
            runtimeException.printStackTrace();
            throw runtimeException;
        }

        void callOnData(T t) {
            if (this.subscription.isCanceled()) {
                return;
            }
            if (SubscriptionBuilder.this.scheduler != null) {
                SubscriptionBuilder.this.scheduler.run(this.schedulerRunOnData, t);
                return;
            }
            try {
                SubscriptionBuilder.this.observer.onData(t);
            } catch (Error | RuntimeException e) {
                callOnError(e, "Observer failed without an ErrorObserver set");
            }
        }

        @Override // io.objectbox.reactive.DelegatingObserver
        public DataObserver<T> getObserverDelegate() {
            return SubscriptionBuilder.this.observer;
        }

        class SchedulerRunOnChange implements RunWithParam<T> {
            SchedulerRunOnChange() {
            }

            @Override // io.objectbox.reactive.RunWithParam
            public void run(T t) {
                if (ActionObserver.this.subscription.isCanceled()) {
                    return;
                }
                try {
                    SubscriptionBuilder.this.observer.onData(t);
                } catch (Error | RuntimeException e) {
                    ActionObserver.this.callOnError(e, "Observer failed without an ErrorObserver set");
                }
            }
        }

        class SchedulerRunOnError implements RunWithParam<Throwable> {
            SchedulerRunOnError() {
            }

            @Override // io.objectbox.reactive.RunWithParam
            public void run(Throwable th) {
                if (ActionObserver.this.subscription.isCanceled()) {
                    return;
                }
                SubscriptionBuilder.this.errorObserver.onError(th);
            }
        }
    }
}
