package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum VerificationState {
    FALSE_POSITIVE("FALSE_POSITIVE"),
    BENIGN_POSITIVE("BENIGN_POSITIVE"),
    TRUE_POSITIVE("TRUE_POSITIVE");

    private static final Map<String, VerificationState> enumMap;
    private String value;

    static {
        VerificationState verificationState = FALSE_POSITIVE;
        VerificationState verificationState2 = BENIGN_POSITIVE;
        VerificationState verificationState3 = TRUE_POSITIVE;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("FALSE_POSITIVE", verificationState);
        map.put("BENIGN_POSITIVE", verificationState2);
        map.put("TRUE_POSITIVE", verificationState3);
    }

    VerificationState(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static VerificationState fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, VerificationState> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
