package com.byd.data.common.entiry.cloud.emergency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class EmergencyEventChecker {
    private static final String TAG = "EmergencyEventChecker";
    private final boolean bigEndian = false;
    private final List<EmergencyEventData> lsEmergencyEvent = new ArrayList<EmergencyEventData>() { // from class: com.byd.data.common.entiry.cloud.emergency.EmergencyEventChecker.1
        {
            add(new EmergencyEventData((short) 140, (byte) 0, (byte) 1, (byte) 0, (byte) 1, (byte) 7, 1, new ArrayList<Integer>() { // from class: com.byd.data.common.entiry.cloud.emergency.EmergencyEventChecker.1.1
                {
                    add(0);
                    add(85);
                }
            }, false));
            add(new EmergencyEventData((short) 835, (byte) 0, (byte) 4, (byte) 0, (byte) 4, (byte) 1, 1, new ArrayList<Integer>() { // from class: com.byd.data.common.entiry.cloud.emergency.EmergencyEventChecker.1.2
                {
                    add(-1);
                    add(1);
                }
            }, false));
        }
    };
    private final Map<EmergencyEventData, Integer> mapEmergencyEventCache = new HashMap();
}
