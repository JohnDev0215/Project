package com.amazonaws.services.cognitoidentityprovider.model;

import com.google.android.gms.cast.MediaError;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum VerifySoftwareTokenResponseType {
    SUCCESS("SUCCESS"),
    ERROR(MediaError.ERROR_TYPE_ERROR);

    private static final Map<String, VerifySoftwareTokenResponseType> enumMap;
    private String value;

    static {
        VerifySoftwareTokenResponseType verifySoftwareTokenResponseType = SUCCESS;
        VerifySoftwareTokenResponseType verifySoftwareTokenResponseType2 = ERROR;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("SUCCESS", verifySoftwareTokenResponseType);
        map.put(MediaError.ERROR_TYPE_ERROR, verifySoftwareTokenResponseType2);
    }

    VerifySoftwareTokenResponseType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static VerifySoftwareTokenResponseType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, VerifySoftwareTokenResponseType> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
