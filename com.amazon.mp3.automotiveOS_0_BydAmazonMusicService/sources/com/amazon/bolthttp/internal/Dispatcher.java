package com.amazon.bolthttp.internal;

import com.amazon.bolthttp.BoltConfig;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.ThreadingModel;
import com.amazon.bolthttp.internal.Commander;
import com.amazon.bolthttp.internal.Logger;
import com.amazon.bolthttp.internal.command.CheckDuplicateRequests;
import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class Dispatcher {
    private final Map<Class<Object>, Commander<?, ?>> mCommanders;
    private final Logger mLogger;

    public Dispatcher(@Nonnull BoltConfig boltConfig, @Nonnull Logger logger) {
        Preconditions.checkNotNull(boltConfig, "config");
        this.mLogger = (Logger) Preconditions.checkNotNull(logger, "logger");
        HashMap map = new HashMap();
        this.mCommanders = map;
        map.put(Affinity$OverlordAffinity.class, new Overlord(new OverlordSharedState(this, boltConfig)));
        map.put(Affinity$NetworkAffinity.class, new ExecutorCommander(new NetworkSharedState(this, boltConfig), ThreadingModel.ExecutorType.NETWORK));
        map.put(Affinity$CPUAffinity.class, new ExecutorCommander(new CPUSharedState(this, boltConfig), ThreadingModel.ExecutorType.CPU));
        map.put(Affinity$DiskAffinity.class, new ExecutorCommander(new DiskSharedState(this, boltConfig), ThreadingModel.ExecutorType.DISK));
    }

    public <T> void initiateRequest(@Nonnull Request<T> request, @Nonnull ResponseHandler<T> responseHandler) {
        Preconditions.checkNotNull(request, "request");
        Preconditions.checkNotNull(responseHandler, "responseHandler");
        this.mLogger.log(Logger.Type.DEBUG, "Received %s from thread %s", request, Thread.currentThread().getName());
        dispatch(new CheckDuplicateRequests(request, responseHandler));
    }

    <T, S extends Commander.SharedState<T>> void dispatch(@Nonnull Command<T, S> command) {
        Preconditions.checkNotNull(command, "command");
        Class<T> affinityClass = command.getAffinityClass();
        if (!this.mCommanders.containsKey(affinityClass)) {
            throw new IllegalStateException("unknown affinity class: " + affinityClass.getName());
        }
        Commander<?, ?> commander = this.mCommanders.get(affinityClass);
        this.mLogger.log(Logger.Type.VERBOSE, "Dispatching %s from thread %s", command, Thread.currentThread().getName());
        commander.handleCommand(command);
    }
}
