package com.amazon.bolthttp.internal.command;

import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.bolthttp.BoltException;
import com.amazon.bolthttp.CancelledException;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.Response;
import com.amazon.bolthttp.internal.Affinity$OverlordAffinity;
import com.amazon.bolthttp.internal.Command;
import com.amazon.bolthttp.internal.Logger;
import com.amazon.bolthttp.internal.OverlordSharedState;
import com.amazon.bolthttp.internal.ResponseHandler;
import com.amazon.bolthttp.internal.ResponseHandlerMultimap;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
final class InvokeCallback<T> extends Command<Affinity$OverlordAffinity, OverlordSharedState> {

    @Nonnull
    private final Response<T> mResponse;

    static <T> InvokeCallback<T> onSuccess(@Nonnull Request<T> request, @Nullable T t, @Nonnull DownloadStatistics downloadStatistics) {
        return new InvokeCallback<>(Response.create(request, t, downloadStatistics));
    }

    static <T> InvokeCallback<T> onFailure(@Nonnull Request<T> request, @Nonnull BoltException.Component component, @Nonnull Exception exc, @Nonnull DownloadStatistics downloadStatistics) {
        Preconditions.checkNotNull(request, "request");
        Preconditions.checkNotNull(component, "component");
        Preconditions.checkNotNull(exc, "exception");
        Preconditions.checkNotNull(downloadStatistics, "statistics");
        return new InvokeCallback<>(Response.create((Request) request, new BoltException(component, request.getUrl(), exc), downloadStatistics));
    }

    InvokeCallback(@Nonnull Response<T> response) {
        super(Affinity$OverlordAffinity.class);
        this.mResponse = (Response) Preconditions.checkNotNull(response, "response");
    }

    @Override // com.amazon.bolthttp.internal.Command
    protected void execute() {
        Request<T> request = this.mResponse.getRequest();
        ResponseHandlerMultimap responseHandlerMultimap = getSharedState().getResponseHandlerMultimap();
        if (!responseHandlerMultimap.containsKey(request)) {
            throw new IllegalStateException("callback not found for request: " + request);
        }
        Collection<ResponseHandler<T>> collectionRemoveAll = responseHandlerMultimap.removeAll(request);
        getLogger().log(Logger.Type.VERBOSE, "InvokeCallback: Invoking %s callback(s) for Request@%s", Integer.valueOf(collectionRemoveAll.size()), request.toSimpleString());
        Response<T> responseCreate = (!request.isCancelled() || (this.mResponse.getException() != null && (this.mResponse.getException().getCause() instanceof CancelledException))) ? this.mResponse : Response.create((Request) request, new BoltException(BoltException.Component.RESPONSE_HANDLER, request.getUrl(), new CancelledException("Completed request cancelled")), this.mResponse.getDownloadStatistics());
        Iterator<ResponseHandler<T>> it = collectionRemoveAll.iterator();
        while (it.hasNext()) {
            it.next().handleResponse(responseCreate);
        }
    }

    @Override // com.amazon.bolthttp.internal.Command
    public String getTraceName() {
        return String.format("%s:%s", InvokeCallback.class.getSimpleName(), this.mResponse.getRequest().getUrl().getPath());
    }

    public String toString() {
        return String.format("Command[class=%s, %s, %s]", InvokeCallback.class.getSimpleName(), this.mResponse.getRequest().toSimpleString(), this.mResponse);
    }
}
