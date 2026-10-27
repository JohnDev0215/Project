package com.amazon.weblab.mobile.experimental;

import com.amazon.weblab.mobile.model.TreatmentAssignment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes5.dex */
public class PlatformWeblabsGlobalState {
    private static final ConcurrentHashMap<PlatformWeblabs, TreatmentAssignment> mInternalWeblabs = new ConcurrentHashMap<>();
    static final ConcurrentHashMap<PlatformWeblabs, TreatmentAssignment> mUpdatedOnlyOnStartupWeblabs = new ConcurrentHashMap<>();
    private static long lastTriggeredTime = 0;

    public static void setWeblabTreatment(PlatformWeblabs platformWeblabs, TreatmentAssignment treatmentAssignment) {
        if (platformWeblabs.isUpdateOnlyOnce()) {
            mUpdatedOnlyOnStartupWeblabs.putIfAbsent(platformWeblabs, treatmentAssignment);
        }
        mInternalWeblabs.put(platformWeblabs, treatmentAssignment);
    }

    public static List<TreatmentAssignment> getTreatmentsToTrigger() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (Math.abs(jCurrentTimeMillis - lastTriggeredTime) < 600000) {
            return null;
        }
        lastTriggeredTime = jCurrentTimeMillis;
        Collection<TreatmentAssignment> collectionValues = mInternalWeblabs.values();
        ArrayList arrayList = new ArrayList();
        for (TreatmentAssignment treatmentAssignment : collectionValues) {
            if (canTrigger(treatmentAssignment)) {
                arrayList.add(treatmentAssignment);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    public static void clear() {
        mInternalWeblabs.clear();
    }

    private static boolean canTrigger(TreatmentAssignment treatmentAssignment) {
        return (treatmentAssignment == null || !treatmentAssignment.canTrigger() || treatmentAssignment.isLocked()) ? false : true;
    }
}
