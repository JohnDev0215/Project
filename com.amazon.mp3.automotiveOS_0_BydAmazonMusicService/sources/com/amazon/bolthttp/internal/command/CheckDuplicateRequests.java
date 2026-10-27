package com.amazon.bolthttp.internal.command;

import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.internal.Affinity$OverlordAffinity;
import com.amazon.bolthttp.internal.Command;
import com.amazon.bolthttp.internal.Logger;
import com.amazon.bolthttp.internal.OverlordSharedState;
import com.amazon.bolthttp.internal.ResponseHandler;
import com.amazon.bolthttp.internal.ResponseHandlerMultimap;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class CheckDuplicateRequests<T> extends Command<Affinity$OverlordAffinity, OverlordSharedState> {
    private final Request<T> mRequest;
    private final ResponseHandler<T> mResponseHandler;

    public CheckDuplicateRequests(@Nonnull Request<T> request, @Nonnull ResponseHandler<T> responseHandler) {
        super(Affinity$OverlordAffinity.class);
        this.mRequest = (Request) Preconditions.checkNotNull(request, "request");
        this.mResponseHandler = (ResponseHandler) Preconditions.checkNotNull(responseHandler, "responseHandler");
    }

    @Override // com.amazon.bolthttp.internal.Command
    protected void execute() {
        ResponseHandlerMultimap responseHandlerMultimap = getSharedState().getResponseHandlerMultimap();
        boolean zContainsKey = responseHandlerMultimap.containsKey(this.mRequest);
        responseHandlerMultimap.put(this.mRequest, this.mResponseHandler);
        if (!zContainsKey) {
            handleRequest();
        } else {
            getLogger().log(Logger.Type.DEBUG, "CheckDuplicateRequests: Found duplicate %s", this.mRequest.toSimpleString());
        }
    }

    private void handleRequest() {
        dispatch(new FetchFromServer(this.mRequest, null));
    }

    @Override // com.amazon.bolthttp.internal.Command
    public String getTraceName() {
        return String.format("%s:%s", CheckDuplicateRequests.class.getSimpleName(), this.mRequest.getUrl().getPath());
    }

    public String toString() {
        return String.format("Command[class=%s, %s]", CheckDuplicateRequests.class.getSimpleName(), this.mRequest.toSimpleString());
    }
}
