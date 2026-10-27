package com.amazon.weblab.mobile.repository;

import com.amazon.weblab.mobile.model.CustomerInfo;
import com.amazon.weblab.mobile.model.MobileWeblabException;
import com.amazon.weblab.mobile.model.SessionInfo;
import com.amazon.weblab.mobile.model.TreatmentAssignment;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public interface IRepository {
    void clear();

    boolean containsValidatedWeblab(String str);

    IRepository getFailsafeRepository();

    SessionInfo getSessionInfo();

    TreatmentAssignment getTreatmentAssignment(String str) throws IllegalStateException;

    boolean hasBackup();

    void pushAll(Map<String, TreatmentAssignment> map, CustomerInfo customerInfo);

    void restore() throws MobileWeblabException;

    void save() throws MobileWeblabException;

    void setApplicationVersion(String str);

    void setSessionInfo(SessionInfo sessionInfo);

    void updateKeepInCacheDateInMillis(String str);
}
