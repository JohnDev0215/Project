package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum CannedAccessControlList {
    Private("private"),
    PublicRead("public-read"),
    PublicReadWrite("public-read-write"),
    AwsExecRead("aws-exec-read"),
    AuthenticatedRead("authenticated-read"),
    BucketOwnerRead("bucket-owner-read"),
    BucketOwnerFullControl("bucket-owner-full-control"),
    LogDeliveryWrite("log-delivery-write");

    private static final Map<String, CannedAccessControlList> enumMap;
    private String value;

    static {
        CannedAccessControlList cannedAccessControlList = Private;
        CannedAccessControlList cannedAccessControlList2 = PublicRead;
        CannedAccessControlList cannedAccessControlList3 = PublicReadWrite;
        CannedAccessControlList cannedAccessControlList4 = AwsExecRead;
        CannedAccessControlList cannedAccessControlList5 = AuthenticatedRead;
        CannedAccessControlList cannedAccessControlList6 = BucketOwnerRead;
        CannedAccessControlList cannedAccessControlList7 = BucketOwnerFullControl;
        CannedAccessControlList cannedAccessControlList8 = LogDeliveryWrite;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("private", cannedAccessControlList);
        map.put("public-read", cannedAccessControlList2);
        map.put("public-read-write", cannedAccessControlList3);
        map.put("aws-exec-read", cannedAccessControlList4);
        map.put("authenticated-read", cannedAccessControlList5);
        map.put("bucket-owner-read", cannedAccessControlList6);
        map.put("bucket-owner-full-control", cannedAccessControlList7);
        map.put("log-delivery-write", cannedAccessControlList8);
    }

    CannedAccessControlList(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static CannedAccessControlList fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, CannedAccessControlList> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
