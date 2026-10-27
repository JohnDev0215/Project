package com.amazon.bolthttp.internal.command;

import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.bolthttp.BoltException;
import com.amazon.bolthttp.EventListener;
import com.amazon.bolthttp.HttpResponse;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.RequestStrategy;
import com.amazon.bolthttp.internal.Affinity$CPUAffinity;
import com.amazon.bolthttp.internal.CPUSharedState;
import com.amazon.bolthttp.internal.Command;
import com.amazon.bolthttp.internal.Logger;
import com.amazon.bolthttp.internal.util.StrategyHelper;
import com.amazon.bolthttp.internal.util.TimeUtils;
import com.amazon.bolthttp.policy.RequestContext;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
final class ProcessResponse<T> extends Command<Affinity$CPUAffinity, CPUSharedState> {

    @Nonnull
    private final DownloadStatistics mDownloadStatistics;
    private final HttpResponse<T> mHttpResponse;
    private final Request<T> mRequest;
    private final RequestContext mRequestContext;
    private final RequestStrategy mRequestStrategy;
    private final StrategyHelper<T> mStrategyHelper;

    ProcessResponse(@Nonnull Request<T> request, @Nonnull StrategyHelper<T> strategyHelper, @Nonnull HttpResponse<T> httpResponse, @Nonnull DownloadStatistics downloadStatistics) {
        super(Affinity$CPUAffinity.class);
        this.mRequest = (Request) Preconditions.checkNotNull(request, "request");
        StrategyHelper<T> strategyHelper2 = (StrategyHelper) Preconditions.checkNotNull(strategyHelper, "strategyHelper");
        this.mStrategyHelper = strategyHelper2;
        this.mRequestContext = strategyHelper2.getRequestContext();
        this.mRequestStrategy = strategyHelper2.getRequestStrategy();
        this.mHttpResponse = (HttpResponse) Preconditions.checkNotNull(httpResponse, "httpResponse");
        this.mDownloadStatistics = (DownloadStatistics) Preconditions.checkNotNull(downloadStatistics, "downloadStatistics");
    }

    @Override // com.amazon.bolthttp.internal.Command
    protected void execute() {
        HttpResponse.Handler<T> responseHandler = this.mRequest.getResponseHandler();
        long timeMillis = TimeUtils.getTimeMillis();
        try {
            T tProcess = responseHandler.process(this.mHttpResponse);
            reportEvent(EventListener.ResponseHandlerEvent.State.SUCCESS, this.mRequestContext.getAttemptCount(), TimeUtils.getTimeMillis() - timeMillis, null);
            dispatch(InvokeCallback.onSuccess(this.mRequest, tProcess, this.mDownloadStatistics));
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw ((RuntimeException) e);
            }
            this.mRequestContext.onAttemptFailure(e);
            Logger logger = getLogger();
            Logger.Type type = Logger.Type.WARN;
            logger.log(type, "ProcessResponse: (%sms, #%s) Caught exception %s for %s", Long.valueOf(this.mRequestContext.getTotalElapsedTime()), Integer.valueOf(this.mRequestContext.getAttemptCount()), e, this.mRequest.toSimpleString());
            reportEvent(EventListener.ResponseHandlerEvent.State.FAILURE, this.mRequestContext.getAttemptCount(), TimeUtils.getTimeMillis() - timeMillis, e);
            if (this.mRequestStrategy.getRetryPolicy().shouldRetry(this.mRequestContext)) {
                getLogger().log(Logger.Type.INFO, "ProcessResponse: Retrying request %s", this.mRequest.toSimpleString());
                dispatch(new FetchFromServer(this.mRequest, this.mStrategyHelper));
            } else {
                Exception returnedException = this.mRequestStrategy.getRetryPolicy().getReturnedException(this.mRequestContext);
                Preconditions.checkNotNull(returnedException, "RetryPolicy.getReturnedException() == null");
                getLogger().log(type, "ProcessResponse: (%sms, #%s) Aborting request due to %s for %s", Long.valueOf(this.mRequestContext.getTotalElapsedTime()), Integer.valueOf(this.mRequestContext.getAttemptCount()), returnedException, this.mRequest);
                dispatch(InvokeCallback.onFailure(this.mRequest, BoltException.Component.RESPONSE_HANDLER, returnedException, this.mDownloadStatistics));
            }
        }
    }

    private void reportEvent(@Nonnull EventListener.ResponseHandlerEvent.State state, int i, long j, @Nullable Exception exc) {
        if (this.mRequest.getEventListeners().isEmpty()) {
            return;
        }
        EventListener.ResponseHandlerEvent responseHandlerEvent = new EventListener.ResponseHandlerEvent((EventListener.ResponseHandlerEvent.State) Preconditions.checkNotNull(state, "state"), i, j, exc);
        Iterator<EventListener> it = this.mRequest.getEventListeners().iterator();
        while (it.hasNext()) {
            it.next().onResponseHandlerEvent(responseHandlerEvent, this.mRequest);
        }
    }

    @Override // com.amazon.bolthttp.internal.Command
    public String getTraceName() {
        return String.format("%s:%s", ProcessResponse.class.getSimpleName(), this.mRequest.getUrl().getPath());
    }

    public String toString() {
        return String.format("Command[class=%s, %s]", ProcessResponse.class.getSimpleName(), this.mRequest.toSimpleString());
    }
}
