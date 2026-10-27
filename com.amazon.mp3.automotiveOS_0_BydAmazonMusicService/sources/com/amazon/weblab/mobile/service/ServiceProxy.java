package com.amazon.weblab.mobile.service;

import android.util.Log;
import com.amazon.weblab.mobile.experimental.PlatformWeblabsGlobalState;
import com.amazon.weblab.mobile.model.CustomerInfo;
import com.amazon.weblab.mobile.model.MobileWeblabException;
import com.amazon.weblab.mobile.model.MobileWeblabTriggerResult;
import com.amazon.weblab.mobile.model.SessionInfo;
import com.amazon.weblab.mobile.model.TreatmentAssignment;
import com.amazon.weblab.mobile.settings.IMobileWeblabClientAttributes;
import com.amazon.weblab.mobile.settings.IMobileWeblabRuntimeConfiguration;
import com.amazon.weblab.mobile.settings.Interval;
import com.amazon.weblab.mobile.settings.MobileWeblabServiceEndpoint;
import com.amazon.weblab.mobile.utils.TestUtils;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
class ServiceProxy implements IServiceProxy {
    private IMobileWeblabClientAttributes mAttributes;
    private BasePathProvider mBasePathProvider;
    private ServiceClient mClient;
    private Interval mDefaultTtl;
    private MobileWeblabServiceEndpoint mEndpointType;
    private ITriggerRequestListener mListener;
    private RequestFilterCache mRequestFilterCache;

    ServiceProxy(IMobileWeblabClientAttributes iMobileWeblabClientAttributes, IMobileWeblabRuntimeConfiguration iMobileWeblabRuntimeConfiguration, ITriggerRequestListener iTriggerRequestListener) {
        if (iTriggerRequestListener == null) {
            throw new IllegalArgumentException("listener can't be null.");
        }
        if (iMobileWeblabClientAttributes == null) {
            throw new IllegalArgumentException("attr can't be null.");
        }
        if (iMobileWeblabClientAttributes.getWeblabs() == null) {
            throw new IllegalArgumentException("weblab map in attr can't be null.");
        }
        if (iMobileWeblabRuntimeConfiguration == null) {
            throw new IllegalArgumentException("runtimeConf can't be null.");
        }
        if (iMobileWeblabRuntimeConfiguration.getTtl() == null) {
            throw new IllegalArgumentException("ttl in runtimeConf can't be null.");
        }
        if (iMobileWeblabRuntimeConfiguration.getEndpoint() == null) {
            throw new IllegalArgumentException("endpoint in runtimeConf can't be null.");
        }
        this.mListener = iTriggerRequestListener;
        this.mAttributes = iMobileWeblabClientAttributes;
        this.mDefaultTtl = iMobileWeblabRuntimeConfiguration.getTtl();
        this.mEndpointType = iMobileWeblabRuntimeConfiguration.getEndpoint();
        this.mRequestFilterCache = new RequestFilterCache(this.mAttributes.getIdentifier());
        this.mBasePathProvider = new BasePathProvider();
        this.mClient = new ServiceClient(iMobileWeblabRuntimeConfiguration.getNetworkRetries(), this.mAttributes.getIdentifier());
    }

    private static TreatmentAssignment createTreatmentAssignment(String str, String str2, String str3, Long l, Long l2, boolean z) {
        return new TreatmentAssignment(str, str2, str3, l, l2, z, 0L);
    }

    private MobileWeblabGetTreatmentAssignmentResponse constructTreatmentMap(Collection<String> collection, Map<String, ServiceAssignment> map, Interval interval) throws MobileWeblabException {
        if (interval == null) {
            interval = this.mDefaultTtl;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jConvertTo = interval.convertTo(TimeUnit.MILLISECONDS) + jCurrentTimeMillis;
        HashMap map2 = new HashMap(map.size());
        for (String str : collection) {
            if (map.containsKey(str)) {
                ServiceAssignment serviceAssignment = map.get(str);
                String version = serviceAssignment.getVersion();
                String treatment = serviceAssignment.getTreatment();
                if (treatment == null || version == null) {
                    version = treatment == null ? "com.amazon.weblab.mobile.version.Default" : "com.amazon.weblab.mobile.version.Locked";
                }
                String str2 = version;
                String str3 = treatment == null ? this.mAttributes.getWeblabs().get(str) : treatment;
                if (str3 != null) {
                    map2.put(str, createTreatmentAssignment(str, str3, str2, Long.valueOf(jCurrentTimeMillis), Long.valueOf(jConvertTo), serviceAssignment.getMayTrigger()));
                }
            }
        }
        return new MobileWeblabGetTreatmentAssignmentResponse(map2);
    }

    @Override // com.amazon.weblab.mobile.service.IServiceProxy
    public MobileWeblabGetTreatmentAssignmentResponse getTreatmentAssignments(SessionInfo sessionInfo, CustomerInfo customerInfo, Collection<String> collection) throws MobileWeblabException {
        if (collection.isEmpty()) {
            return new MobileWeblabGetTreatmentAssignmentResponse(Collections.emptyMap());
        }
        if (sessionInfo == null) {
            throw new IllegalArgumentException("sessionInfo can't be null.");
        }
        String basePath = this.mBasePathProvider.getBasePath(sessionInfo.getMarketplaceId(), this.mEndpointType);
        AssignmentsServiceRequest assignmentsServiceRequest = new AssignmentsServiceRequest(this.mAttributes, sessionInfo, customerInfo, collection);
        try {
            System.currentTimeMillis();
            ServiceResponse serviceResponseInvoke = this.mClient.invoke(basePath, assignmentsServiceRequest);
            System.currentTimeMillis();
            Map<String, ServiceAssignment> assignments = serviceResponseInvoke.getAssignments();
            if (assignments == null || assignments.isEmpty()) {
                Log.e("MWAC", "Service doesn't return assignments.");
                throw new MobileWeblabException("Service doesn't return assignments.");
            }
            TestUtils.logDebugForAutomation("Successfully retrieved " + assignments.size() + " assignments from the service.");
            return constructTreatmentMap(collection, assignments, serviceResponseInvoke.getTtl());
        } catch (IOException e) {
            Log.e("MWAC", "Exception retrieving assignments from service.", e);
            throw new MobileWeblabException(e);
        }
    }

    @Override // com.amazon.weblab.mobile.service.IServiceProxy
    public MobileWeblabTriggerResult recordTrigger(TreatmentAssignment treatmentAssignment, SessionInfo sessionInfo, CustomerInfo customerInfo) throws MobileWeblabException {
        if (treatmentAssignment == null) {
            throw new IllegalArgumentException("treatment can't be null.");
        }
        if (sessionInfo == null) {
            throw new IllegalArgumentException("sessionInfo can't be null.");
        }
        if (this.mRequestFilterCache.isCacheHit(sessionInfo, customerInfo, treatmentAssignment)) {
            return MobileWeblabTriggerResult.SUCCESSFUL;
        }
        MobileWeblabTriggerResult mobileWeblabTriggerResultRecordTriggerImpl = recordTriggerImpl(treatmentAssignment, sessionInfo, customerInfo);
        if (mobileWeblabTriggerResultRecordTriggerImpl == MobileWeblabTriggerResult.SUCCESSFUL) {
            this.mRequestFilterCache.addTriggerRequest(sessionInfo, customerInfo, treatmentAssignment);
        }
        List<TreatmentAssignment> treatmentsToTrigger = PlatformWeblabsGlobalState.getTreatmentsToTrigger();
        if (treatmentsToTrigger != null) {
            Iterator<TreatmentAssignment> it = treatmentsToTrigger.iterator();
            while (it.hasNext()) {
                recordTriggerImpl(it.next(), sessionInfo, customerInfo);
            }
        }
        return mobileWeblabTriggerResultRecordTriggerImpl;
    }

    private Map<String, TreatmentAssignment> constructRecordTriggerMap(MobileWeblabGetTreatmentAssignmentResponse mobileWeblabGetTreatmentAssignmentResponse) {
        return mobileWeblabGetTreatmentAssignmentResponse.getTreatmentAssignments();
    }

    private MobileWeblabTriggerResult recordTriggerImpl(TreatmentAssignment treatmentAssignment, SessionInfo sessionInfo, CustomerInfo customerInfo) throws MobileWeblabException {
        String basePath = this.mBasePathProvider.getBasePath(sessionInfo.getMarketplaceId(), this.mEndpointType);
        TriggersServiceRequest triggersServiceRequest = new TriggersServiceRequest(this.mAttributes, sessionInfo, customerInfo, Collections.singleton(treatmentAssignment));
        try {
            System.currentTimeMillis();
            ServiceResponse serviceResponseInvoke = this.mClient.invoke(basePath, triggersServiceRequest);
            System.currentTimeMillis();
            MobileWeblabTriggerResult mobileWeblabTriggerResult = serviceResponseInvoke.getAssignments().containsKey(treatmentAssignment.getWeblabName()) ? MobileWeblabTriggerResult.REJECTED_FOR_ALLOCATION_MISMATCH : MobileWeblabTriggerResult.SUCCESSFUL;
            if (mobileWeblabTriggerResult == MobileWeblabTriggerResult.REJECTED_FOR_ALLOCATION_MISMATCH) {
                if (TestUtils.isUnderTest()) {
                    TestUtils.logDebugForAutomation("Trigger rejected for allocation mismatch for weblab " + treatmentAssignment.getWeblabName() + " and treatment " + treatmentAssignment.getTreatment());
                }
                this.mListener.notifyMismatchAllocation(sessionInfo, customerInfo, constructRecordTriggerMap(constructTreatmentMap(Collections.singleton(treatmentAssignment.getWeblabName()), serviceResponseInvoke.getAssignments(), serviceResponseInvoke.getTtl())));
            } else if (TestUtils.isUnderTest()) {
                TestUtils.logDebugForAutomation("Trigger successfully recorded for weblab " + treatmentAssignment.getWeblabName() + " and treatment " + treatmentAssignment.getTreatment());
            }
            return mobileWeblabTriggerResult;
        } catch (UriTooLongException e) {
            if (TestUtils.isUnderTest()) {
                TestUtils.logDebugForAutomation("Exception while recording trigger for weblab " + treatmentAssignment.getWeblabName() + " and treatment " + treatmentAssignment.getTreatment());
            }
            throw new MobileWeblabException(e);
        } catch (IOException e2) {
            if (TestUtils.isUnderTest()) {
                TestUtils.logDebugForAutomation("Exception while recording trigger for weblab " + treatmentAssignment.getWeblabName() + " and treatment " + treatmentAssignment.getTreatment());
            }
            throw new MobileWeblabException(e2);
        }
    }
}
