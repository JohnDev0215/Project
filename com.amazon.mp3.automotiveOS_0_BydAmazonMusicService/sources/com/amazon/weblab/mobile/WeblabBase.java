package com.amazon.weblab.mobile;

import com.amazon.weblab.mobile.model.CustomerInfo;
import com.amazon.weblab.mobile.model.MobileWeblabException;
import com.amazon.weblab.mobile.model.MobileWeblabTriggerResult;
import com.amazon.weblab.mobile.model.SessionInfo;
import com.amazon.weblab.mobile.model.TreatmentAssignment;
import com.amazon.weblab.mobile.service.IServiceProxy;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes5.dex */
class WeblabBase implements IMobileWeblab {
    private String mClientIdentifier;
    private CustomerInfo mCustomerInfo;
    private ExecutorService mExecutor;
    private IServiceProxy mProxy;
    private SessionInfo mSessionInfo;
    private TreatmentAssignment mTreatment;

    WeblabBase(TreatmentAssignment treatmentAssignment, SessionInfo sessionInfo, CustomerInfo customerInfo, ExecutorService executorService, IServiceProxy iServiceProxy, String str) {
        if (treatmentAssignment == null) {
            throw new IllegalArgumentException("treatment can't be null");
        }
        if (sessionInfo == null) {
            throw new IllegalArgumentException("session can't be null");
        }
        if (customerInfo == null) {
            throw new IllegalArgumentException("customer can't be null");
        }
        if (executorService == null) {
            throw new IllegalArgumentException("executor can't be null");
        }
        if (executorService.isShutdown()) {
            throw new IllegalArgumentException("executor can't be shut down");
        }
        if (iServiceProxy == null) {
            throw new IllegalArgumentException("proxy can't be null");
        }
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("clientIdentifier can't be null nor empty");
        }
        this.mTreatment = treatmentAssignment;
        this.mSessionInfo = sessionInfo;
        this.mCustomerInfo = customerInfo;
        this.mExecutor = executorService;
        this.mProxy = iServiceProxy;
        this.mClientIdentifier = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MobileWeblabTriggerResult recordTriggerImpl() throws MobileWeblabException {
        if (this.mTreatment.isLocked()) {
            return MobileWeblabTriggerResult.OVERRIDDEN_LOCALLY;
        }
        if (!this.mTreatment.canTrigger()) {
            return MobileWeblabTriggerResult.OVERRIDDEN;
        }
        return this.mProxy.recordTrigger(this.mTreatment, this.mSessionInfo, new CustomerInfo(this.mCustomerInfo.getDirectedId()));
    }

    public Future<MobileWeblabTriggerResult> recordTrigger() throws RejectedExecutionException {
        return this.mExecutor.submit(new Callable<MobileWeblabTriggerResult>() { // from class: com.amazon.weblab.mobile.WeblabBase.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public MobileWeblabTriggerResult call() throws MobileWeblabException {
                return WeblabBase.this.recordTriggerImpl();
            }
        });
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblab
    public String getTreatmentAssignment() {
        return this.mTreatment.getTreatment();
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblab
    public MobileWeblabTreatmentAndTriggerResult getTreatmentAndRecordTrigger() throws RejectedExecutionException {
        return new MobileWeblabTreatmentAndTriggerResult(this.mTreatment.getTreatment(), recordTrigger());
    }
}
