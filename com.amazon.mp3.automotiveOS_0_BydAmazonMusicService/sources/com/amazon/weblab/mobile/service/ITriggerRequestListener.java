package com.amazon.weblab.mobile.service;

import com.amazon.weblab.mobile.model.CustomerInfo;
import com.amazon.weblab.mobile.model.SessionInfo;
import com.amazon.weblab.mobile.model.TreatmentAssignment;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public interface ITriggerRequestListener {
    void notifyMismatchAllocation(SessionInfo sessionInfo, CustomerInfo customerInfo, Map<String, TreatmentAssignment> map);
}
