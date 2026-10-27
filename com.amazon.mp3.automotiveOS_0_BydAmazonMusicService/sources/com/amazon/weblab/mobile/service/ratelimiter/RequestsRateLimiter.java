package com.amazon.weblab.mobile.service.ratelimiter;

import android.util.Log;
import com.amazon.weblab.mobile.model.CustomerInfo;
import com.amazon.weblab.mobile.model.MobileWeblabException;
import com.amazon.weblab.mobile.model.MobileWeblabTriggerResult;
import com.amazon.weblab.mobile.model.SessionInfo;
import com.amazon.weblab.mobile.model.TreatmentAssignment;
import com.amazon.weblab.mobile.service.IServiceProxy;
import com.amazon.weblab.mobile.service.InternalServerErrorException;
import com.amazon.weblab.mobile.service.MobileWeblabGetTreatmentAssignmentResponse;
import com.amazon.weblab.mobile.service.ServiceCallStatus;
import com.amazon.weblab.mobile.service.ThrottledServiceCallException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class RequestsRateLimiter implements IServiceProxy {
    private final IServiceProxy mServiceProxy;
    private final RecordLimiter<MobileWeblabGetTreatmentAssignmentResponse> recordLimiterGA;
    private final RecordLimiter<MobileWeblabTriggerResult> recordLimiterRT;

    protected interface Operation<T> {
        T enact() throws MobileWeblabException;
    }

    private static List<RequestRatePolicy> createRatePoliciesOrderedByPriority() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(new ExponentialBackoffRequestRatePolicy());
        arrayList.add(new ThrottledRequestRatePolicy());
        return arrayList;
    }

    public RequestsRateLimiter(IServiceProxy iServiceProxy, String str) {
        this(iServiceProxy, createRatePoliciesOrderedByPriority(), str);
    }

    RequestsRateLimiter(IServiceProxy iServiceProxy, List<RequestRatePolicy> list, String str) {
        this.mServiceProxy = iServiceProxy;
        this.recordLimiterGA = new RecordLimiter<>(list, str);
        this.recordLimiterRT = new RecordLimiter<>(list, str);
    }

    @Override // com.amazon.weblab.mobile.service.IServiceProxy
    public MobileWeblabGetTreatmentAssignmentResponse getTreatmentAssignments(final SessionInfo sessionInfo, final CustomerInfo customerInfo, final Collection<String> collection) throws MobileWeblabException {
        validateParams(sessionInfo, customerInfo, collection);
        return this.recordLimiterGA.checkAndSendRequest(new Operation<MobileWeblabGetTreatmentAssignmentResponse>() { // from class: com.amazon.weblab.mobile.service.ratelimiter.RequestsRateLimiter.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.weblab.mobile.service.ratelimiter.RequestsRateLimiter.Operation
            public MobileWeblabGetTreatmentAssignmentResponse enact() throws MobileWeblabException {
                return RequestsRateLimiter.this.mServiceProxy.getTreatmentAssignments(sessionInfo, customerInfo, collection);
            }
        }, sessionInfo, customerInfo, collection);
    }

    @Override // com.amazon.weblab.mobile.service.IServiceProxy
    public MobileWeblabTriggerResult recordTrigger(final TreatmentAssignment treatmentAssignment, final SessionInfo sessionInfo, final CustomerInfo customerInfo) throws MobileWeblabException {
        Set setSingleton = Collections.singleton(treatmentAssignment.getWeblabName());
        validateParams(sessionInfo, customerInfo, Collections.singleton(treatmentAssignment.getWeblabName()));
        return this.recordLimiterRT.checkAndSendRequest(new Operation<MobileWeblabTriggerResult>() { // from class: com.amazon.weblab.mobile.service.ratelimiter.RequestsRateLimiter.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.weblab.mobile.service.ratelimiter.RequestsRateLimiter.Operation
            public MobileWeblabTriggerResult enact() throws MobileWeblabException {
                return RequestsRateLimiter.this.mServiceProxy.recordTrigger(treatmentAssignment, sessionInfo, customerInfo);
            }
        }, sessionInfo, customerInfo, setSingleton);
    }

    private void validateParams(SessionInfo sessionInfo, CustomerInfo customerInfo, Collection<String> collection) {
        if (sessionInfo == null) {
            throw new IllegalArgumentException("Session can't be null");
        }
        if (customerInfo == null) {
            throw new IllegalArgumentException("Session can't be null");
        }
        if (collection == null) {
            throw new IllegalArgumentException("Weblab list can't be null");
        }
    }

    private static class AllowAllRequestRatePolicy implements RequestRatePolicy {
        @Override // com.amazon.weblab.mobile.service.ratelimiter.RequestRatePolicy
        public boolean allowRequest(SessionInfo sessionInfo, CustomerInfo customerInfo, Collection<String> collection, RequestHistory requestHistory) {
            return true;
        }

        @Override // com.amazon.weblab.mobile.service.ratelimiter.RequestRatePolicy
        public int historySizeRequired() {
            return 0;
        }

        @Override // com.amazon.weblab.mobile.service.ratelimiter.RequestRatePolicy
        public boolean isPolicyApplicable(RequestHistory requestHistory) {
            return true;
        }

        private AllowAllRequestRatePolicy() {
        }
    }

    protected static class RecordLimiter<T> {
        private String mClientIdentifier;
        private RequestRatePolicy mCurrentPolicy;
        private RequestHistory mGetTreatmentAssignmentHistoryEntries;
        private final List<RequestRatePolicy> mRequestRatePolicies = new ArrayList();

        RecordLimiter(List<RequestRatePolicy> list, String str) {
            initializePolicies(list);
            initializeHistoryEntries();
            this.mClientIdentifier = str;
        }

        T checkAndSendRequest(Operation<T> operation, SessionInfo sessionInfo, CustomerInfo customerInfo, Collection<String> collection) throws MobileWeblabException {
            evaluateIfRequestIsAllowed(sessionInfo, customerInfo, collection);
            return requestAndUpdatePolicies(operation, sessionInfo, customerInfo, collection);
        }

        private void initializePolicies(List<RequestRatePolicy> list) {
            this.mRequestRatePolicies.addAll(list);
            AllowAllRequestRatePolicy allowAllRequestRatePolicy = new AllowAllRequestRatePolicy();
            this.mRequestRatePolicies.add(allowAllRequestRatePolicy);
            this.mCurrentPolicy = allowAllRequestRatePolicy;
        }

        private void initializeHistoryEntries() {
            Iterator<RequestRatePolicy> it = this.mRequestRatePolicies.iterator();
            int iMax = 0;
            while (it.hasNext()) {
                iMax = Math.max(iMax, it.next().historySizeRequired());
            }
            this.mGetTreatmentAssignmentHistoryEntries = new RequestHistory(iMax);
        }

        private synchronized void evaluateIfRequestIsAllowed(SessionInfo sessionInfo, CustomerInfo customerInfo, Collection<String> collection) throws MobileWeblabException {
            RequestRatePolicy requestRatePolicy = this.mCurrentPolicy;
            if (requestRatePolicy != null && !requestRatePolicy.allowRequest(sessionInfo, customerInfo, collection, this.mGetTreatmentAssignmentHistoryEntries)) {
                Log.d("MWAC", this.mCurrentPolicy.getClass().getSimpleName() + " rejected the request");
                throw new MobileWeblabException(this.mCurrentPolicy.getClass().getSimpleName() + " rejected the request");
            }
            RequestRatePolicy requestRatePolicy2 = this.mCurrentPolicy;
            if (requestRatePolicy2 != null && requestRatePolicy2.getClass() != AllowAllRequestRatePolicy.class) {
                Log.d("MWAC", this.mCurrentPolicy.getClass().getSimpleName() + " allowed the request");
            }
        }

        private T requestAndUpdatePolicies(Operation<T> operation, SessionInfo sessionInfo, CustomerInfo customerInfo, Collection<String> collection) throws MobileWeblabException {
            ServiceCallStatus serviceCallStatus = ServiceCallStatus.ERROR;
            try {
                try {
                    T tEnact = operation.enact();
                    updateRateLimitPolicy(sessionInfo, customerInfo, collection, ServiceCallStatus.SUCCESSFUL);
                    return tEnact;
                } catch (InternalServerErrorException e) {
                    ServiceCallStatus serviceCallStatus2 = ServiceCallStatus.INTERNAL_SERVER_ERROR;
                    throw e;
                } catch (ThrottledServiceCallException e2) {
                    ServiceCallStatus serviceCallStatus3 = ServiceCallStatus.THROTTLED;
                    throw e2;
                }
            } catch (Throwable th) {
                updateRateLimitPolicy(sessionInfo, customerInfo, collection, serviceCallStatus);
                throw th;
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:8:0x002e, code lost:
        
            r9.mCurrentPolicy = r11;
            android.util.Log.d("MWAC", r9.mCurrentPolicy.getClass().getSimpleName() + " policy was applied");
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private synchronized void updateRateLimitPolicy(com.amazon.weblab.mobile.model.SessionInfo r10, com.amazon.weblab.mobile.model.CustomerInfo r11, java.util.Collection<java.lang.String> r12, com.amazon.weblab.mobile.service.ServiceCallStatus r13) {
            /*
                r9 = this;
                monitor-enter(r9)
                com.amazon.weblab.mobile.service.ratelimiter.RequestHistory r0 = r9.mGetTreatmentAssignmentHistoryEntries     // Catch: java.lang.Throwable -> L52
                com.amazon.weblab.mobile.service.ratelimiter.RequestEntry r8 = new com.amazon.weblab.mobile.service.ratelimiter.RequestEntry     // Catch: java.lang.Throwable -> L52
                long r6 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Throwable -> L52
                r1 = r8
                r2 = r10
                r3 = r11
                r4 = r12
                r5 = r13
                r1.<init>(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L52
                r0.addEntry(r8)     // Catch: java.lang.Throwable -> L52
                java.util.List<com.amazon.weblab.mobile.service.ratelimiter.RequestRatePolicy> r10 = r9.mRequestRatePolicies     // Catch: java.lang.Throwable -> L52
                java.util.Iterator r10 = r10.iterator()     // Catch: java.lang.Throwable -> L52
            L1a:
                boolean r11 = r10.hasNext()     // Catch: java.lang.Throwable -> L52
                if (r11 == 0) goto L50
                java.lang.Object r11 = r10.next()     // Catch: java.lang.Throwable -> L52
                com.amazon.weblab.mobile.service.ratelimiter.RequestRatePolicy r11 = (com.amazon.weblab.mobile.service.ratelimiter.RequestRatePolicy) r11     // Catch: java.lang.Throwable -> L52
                com.amazon.weblab.mobile.service.ratelimiter.RequestHistory r12 = r9.mGetTreatmentAssignmentHistoryEntries     // Catch: java.lang.Throwable -> L52
                boolean r12 = r11.isPolicyApplicable(r12)     // Catch: java.lang.Throwable -> L52
                if (r12 == 0) goto L1a
                r9.mCurrentPolicy = r11     // Catch: java.lang.Throwable -> L52
                java.lang.String r10 = "MWAC"
                java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L52
                r11.<init>()     // Catch: java.lang.Throwable -> L52
                com.amazon.weblab.mobile.service.ratelimiter.RequestRatePolicy r12 = r9.mCurrentPolicy     // Catch: java.lang.Throwable -> L52
                java.lang.Class r12 = r12.getClass()     // Catch: java.lang.Throwable -> L52
                java.lang.String r12 = r12.getSimpleName()     // Catch: java.lang.Throwable -> L52
                r11.append(r12)     // Catch: java.lang.Throwable -> L52
                java.lang.String r12 = " policy was applied"
                r11.append(r12)     // Catch: java.lang.Throwable -> L52
                java.lang.String r11 = r11.toString()     // Catch: java.lang.Throwable -> L52
                android.util.Log.d(r10, r11)     // Catch: java.lang.Throwable -> L52
            L50:
                monitor-exit(r9)
                return
            L52:
                r10 = move-exception
                monitor-exit(r9)
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.weblab.mobile.service.ratelimiter.RequestsRateLimiter.RecordLimiter.updateRateLimitPolicy(com.amazon.weblab.mobile.model.SessionInfo, com.amazon.weblab.mobile.model.CustomerInfo, java.util.Collection, com.amazon.weblab.mobile.service.ServiceCallStatus):void");
        }
    }
}
