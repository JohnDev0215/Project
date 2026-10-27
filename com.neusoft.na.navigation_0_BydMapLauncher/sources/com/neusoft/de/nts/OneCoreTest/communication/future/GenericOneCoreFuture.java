package com.neusoft.de.nts.OneCoreTest.communication.future;

import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.FutureUtil;
import com.neusoft.de.nts.OneCoreTest.utils.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java9.util.concurrent.CompletableFuture;
import java9.util.function.Function;
import org.apache.commons.lang3.time.StopWatch;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes2.dex */
public class GenericOneCoreFuture<RQ, RS, S, R> {
    private static final Logger logger = Logger.getLogger((Class<?>) GenericOneCoreFuture.class);
    private static Map<CompletableFuture, StopWatch> startTimeMap = new HashMap();
    private R emptyResponse;
    private Executor executor;
    private RS expectedResponse;
    private Func1<R, Boolean> filterFunction;
    private Subscription futureSubscription;
    private RQ request;
    private ITransport<S, R> transport;
    private Func1<RQ, S> transportRequestAdaptor;
    private Func1<R, RS> transportResponseAdaptor;
    private final CompletableFuture<RS> oneCoreFuture = new CompletableFuture<>();
    private Func1<RS, Boolean> responseFilterFunction = new Func1<RS, Boolean>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.future.GenericOneCoreFuture.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // rx.functions.Func1
        public Boolean call(RS rs) {
            return true;
        }
    };

    public CompletableFuture<RS> create() {
        return createWithExecutor();
    }

    private CompletableFuture<RS> createWithExecutor() {
        if (this.expectedResponse == null) {
            CompletableFuture.runAsync(new Runnable() { // from class: com.neusoft.de.nts.OneCoreTest.communication.future.GenericOneCoreFuture.4
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public void run() {
                    GenericOneCoreFuture.this.transport.send(GenericOneCoreFuture.this.transportRequestAdaptor.call(GenericOneCoreFuture.this.request));
                }
            }, this.executor).thenRun(new Runnable() { // from class: com.neusoft.de.nts.OneCoreTest.communication.future.GenericOneCoreFuture.3
                @Override // java.lang.Runnable
                public void run() {
                    GenericOneCoreFuture.this.oneCoreFuture.complete(null);
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.future.GenericOneCoreFuture.2
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    return (Void) FutureUtil.handleException(th);
                }
            });
        } else {
            this.futureSubscription = this.transport.getObservable().observeOn(Schedulers.from(this.executor)).filter(new Func1<R, Boolean>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.future.GenericOneCoreFuture.6
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // rx.functions.Func1
                public Boolean call(R r) {
                    return GenericOneCoreFuture.this.onFilter(r);
                }
            }).map(this.transportResponseAdaptor).filter(this.responseFilterFunction).take(1).subscribe(new Action1<RS>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.future.GenericOneCoreFuture.5
                @Override // rx.functions.Action1
                public void call(RS rs) {
                    GenericOneCoreFuture.this.onSubscribe(rs);
                }
            });
            CompletableFuture.runAsync(new Runnable() { // from class: com.neusoft.de.nts.OneCoreTest.communication.future.GenericOneCoreFuture.8
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public void run() {
                    GenericOneCoreFuture.startTimeMap.put(GenericOneCoreFuture.this.oneCoreFuture, StopWatch.createStarted());
                    GenericOneCoreFuture.this.transport.send(GenericOneCoreFuture.this.transportRequestAdaptor.call(GenericOneCoreFuture.this.request));
                }
            }, this.executor).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.future.GenericOneCoreFuture.7
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    return (Void) FutureUtil.handleException(th);
                }
            });
        }
        return this.oneCoreFuture;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSubscribe(RS rs) {
        StopWatch stopWatch = startTimeMap.get(this.oneCoreFuture);
        if (stopWatch != null) {
            logger.debug("Future complete: " + rs + (" Duration: " + stopWatch.toSplitString()));
            startTimeMap.remove(this.oneCoreFuture);
            this.oneCoreFuture.complete(rs);
        } else {
            this.oneCoreFuture.completeExceptionally(new IllegalStateException("There is no corresponding Stopwatch to this future."));
        }
        Subscription subscription = this.futureSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
            this.futureSubscription = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Boolean onFilter(R r) {
        StopWatch stopWatch = startTimeMap.get(this.oneCoreFuture);
        if (stopWatch != null) {
            stopWatch.split();
        }
        boolean zBooleanValue = this.filterFunction.call(r).booleanValue();
        if (stopWatch != null && !zBooleanValue) {
            stopWatch.unsplit();
        }
        return Boolean.valueOf(zBooleanValue);
    }

    public Executor getExecutor() {
        return this.executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public RQ getRequest() {
        return this.request;
    }

    public void setRequest(RQ rq) {
        this.request = rq;
    }

    public R getEmptyResponse() {
        return this.emptyResponse;
    }

    public void setEmptyResponse(R r) {
        this.emptyResponse = r;
    }

    public Func1<R, Boolean> getFilterFunction() {
        return this.filterFunction;
    }

    public void setFilterFunction(Func1<R, Boolean> func1) {
        this.filterFunction = func1;
    }

    public Func1<R, RS> getTransportResponseAdaptor() {
        return this.transportResponseAdaptor;
    }

    public void setTransportResponseAdaptor(Func1<R, RS> func1) {
        this.transportResponseAdaptor = func1;
    }

    public Func1<RQ, S> getTransportRequestAdaptor() {
        return this.transportRequestAdaptor;
    }

    public void setTransportRequestAdaptor(Func1<RQ, S> func1) {
        this.transportRequestAdaptor = func1;
    }

    public Func1<RS, Boolean> getResponseFilterFunction() {
        return this.responseFilterFunction;
    }

    public void setResponseFilterFunction(Func1<RS, Boolean> func1) {
        this.responseFilterFunction = func1;
    }

    public ITransport<S, R> getTransport() {
        return this.transport;
    }

    public void setTransport(ITransport<S, R> iTransport) {
        this.transport = iTransport;
    }

    public CompletableFuture<RS> getFuture() {
        return this.oneCoreFuture;
    }

    public RS getExpectedResponse() {
        return this.expectedResponse;
    }

    public void setExpectedResponse(RS rs) {
        this.expectedResponse = rs;
    }
}
