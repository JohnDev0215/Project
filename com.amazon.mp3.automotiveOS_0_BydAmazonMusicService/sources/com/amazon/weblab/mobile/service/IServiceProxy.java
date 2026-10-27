package com.amazon.weblab.mobile.service;

import com.amazon.weblab.mobile.model.CustomerInfo;
import com.amazon.weblab.mobile.model.MobileWeblabException;
import com.amazon.weblab.mobile.model.MobileWeblabTriggerResult;
import com.amazon.weblab.mobile.model.SessionInfo;
import com.amazon.weblab.mobile.model.TreatmentAssignment;
import java.util.Collection;

/* JADX INFO: loaded from: classes5.dex */
public interface IServiceProxy {
    MobileWeblabGetTreatmentAssignmentResponse getTreatmentAssignments(SessionInfo sessionInfo, CustomerInfo customerInfo, Collection<String> collection) throws MobileWeblabException;

    MobileWeblabTriggerResult recordTrigger(TreatmentAssignment treatmentAssignment, SessionInfo sessionInfo, CustomerInfo customerInfo) throws MobileWeblabException;
}
