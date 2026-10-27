package com.android.internal.infra;

import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.IInterface;
import android.util.Slog;
import com.android.internal.infra.AbstractMultiplePendingRequestsRemoteService;
import com.android.internal.infra.AbstractRemoteService;
import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractMultiplePendingRequestsRemoteService<S extends AbstractMultiplePendingRequestsRemoteService<S, I>, I extends IInterface> extends AbstractRemoteService<S, I> {
    private final int mInitialCapacity;
    protected ArrayList<AbstractRemoteService.BasePendingRequest<S, I>> mPendingRequests;

    public AbstractMultiplePendingRequestsRemoteService(Context context, String str, ComponentName componentName, int i, AbstractRemoteService.VultureCallback<S> vultureCallback, Handler handler, int i2, boolean z, int i3) {
        super(context, str, componentName, i, vultureCallback, handler, i2, z);
        this.mInitialCapacity = i3;
    }

    @Override // com.android.internal.infra.AbstractRemoteService
    void handlePendingRequests() {
        ArrayList<AbstractRemoteService.BasePendingRequest<S, I>> arrayList = this.mPendingRequests;
        if (arrayList != null) {
            int size = arrayList.size();
            if (this.mVerbose) {
                Slog.v(this.mTag, "Sending " + size + " pending requests");
            }
            for (int i = 0; i < size; i++) {
                this.mPendingRequests.get(i).run();
            }
            this.mPendingRequests = null;
        }
    }

    @Override // com.android.internal.infra.AbstractRemoteService
    protected void handleOnDestroy() {
        ArrayList<AbstractRemoteService.BasePendingRequest<S, I>> arrayList = this.mPendingRequests;
        if (arrayList != null) {
            int size = arrayList.size();
            if (this.mVerbose) {
                Slog.v(this.mTag, "Canceling " + size + " pending requests");
            }
            for (int i = 0; i < size; i++) {
                this.mPendingRequests.get(i).cancel();
            }
            this.mPendingRequests = null;
        }
    }

    @Override // com.android.internal.infra.AbstractRemoteService
    final void handleBindFailure() {
        ArrayList<AbstractRemoteService.BasePendingRequest<S, I>> arrayList = this.mPendingRequests;
        if (arrayList != null) {
            int size = arrayList.size();
            if (this.mVerbose) {
                Slog.v(this.mTag, "Sending failure to " + size + " pending requests");
            }
            for (int i = 0; i < size; i++) {
                AbstractRemoteService.BasePendingRequest<S, I> basePendingRequest = this.mPendingRequests.get(i);
                basePendingRequest.onFailed();
                basePendingRequest.finish();
            }
            this.mPendingRequests = null;
        }
    }

    @Override // com.android.internal.infra.AbstractRemoteService
    public void dump(String str, PrintWriter printWriter) {
        super.dump(str, printWriter);
        printWriter.append((CharSequence) str).append("initialCapacity=").append((CharSequence) String.valueOf(this.mInitialCapacity)).println();
        ArrayList<AbstractRemoteService.BasePendingRequest<S, I>> arrayList = this.mPendingRequests;
        printWriter.append((CharSequence) str).append("pendingRequests=").append((CharSequence) String.valueOf(arrayList == null ? 0 : arrayList.size())).println();
    }

    @Override // com.android.internal.infra.AbstractRemoteService
    void handlePendingRequestWhileUnBound(AbstractRemoteService.BasePendingRequest<S, I> basePendingRequest) {
        if (this.mPendingRequests == null) {
            this.mPendingRequests = new ArrayList<>(this.mInitialCapacity);
        }
        this.mPendingRequests.add(basePendingRequest);
        if (this.mVerbose) {
            Slog.v(this.mTag, "queued " + this.mPendingRequests.size() + " requests; last=" + basePendingRequest);
        }
    }
}
