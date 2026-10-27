package com.amazon.weblab.mobile.service;

import com.amazon.mp3.skiplimit.impl.HardCodedRefreshStrategy;
import com.amazon.weblab.mobile.model.CustomerInfo;
import com.amazon.weblab.mobile.model.SessionInfo;
import com.amazon.weblab.mobile.model.TreatmentAssignment;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class RequestFilterCache {
    private Map<String, Long> requestsMap;
    private int triggerExpirationDate;
    private String weblabClientIdentifier;

    protected RequestFilterCache(String str, int i) {
        this.requestsMap = null;
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("weblabClientIdentifier can't be null nor empty");
        }
        this.weblabClientIdentifier = str;
        this.triggerExpirationDate = i;
        this.requestsMap = Collections.synchronizedMap(new LRUCache(500));
    }

    public RequestFilterCache(String str) {
        this(str, HardCodedRefreshStrategy.MS_IN_MINUTE);
    }

    private String createKey(SessionInfo sessionInfo, CustomerInfo customerInfo, TreatmentAssignment treatmentAssignment) {
        StringBuilder sb = new StringBuilder();
        String directedId = "";
        sb.append(sessionInfo == null ? "" : sessionInfo.getSessionId());
        if (customerInfo != null && customerInfo.getDirectedId() != null) {
            directedId = customerInfo.getDirectedId();
        }
        sb.append(directedId);
        sb.append(treatmentAssignment.getWeblabName());
        sb.append(treatmentAssignment.getTreatment());
        return sb.toString();
    }

    private boolean hasExpired(Long l) {
        return Math.abs(l.longValue() - Calendar.getInstance().getTimeInMillis()) > ((long) this.triggerExpirationDate);
    }

    public boolean isCacheHit(SessionInfo sessionInfo, CustomerInfo customerInfo, TreatmentAssignment treatmentAssignment) {
        if (this.requestsMap.get(createKey(sessionInfo, customerInfo, treatmentAssignment)) == null) {
            return false;
        }
        return !hasExpired(r1);
    }

    public void addTriggerRequest(SessionInfo sessionInfo, CustomerInfo customerInfo, TreatmentAssignment treatmentAssignment) {
        this.requestsMap.put(createKey(sessionInfo, customerInfo, treatmentAssignment), Long.valueOf(Calendar.getInstance().getTimeInMillis()));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        synchronized (this.requestsMap) {
            Set<String> setKeySet = this.requestsMap.keySet();
            if (setKeySet.isEmpty()) {
                return "Empty map";
            }
            Iterator<String> it = setKeySet.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append(" ");
            }
            return sb.toString();
        }
    }
}
