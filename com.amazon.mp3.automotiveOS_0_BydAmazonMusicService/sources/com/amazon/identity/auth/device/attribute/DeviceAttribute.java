package com.amazon.identity.auth.device.attribute;

import android.content.Context;
import com.amazon.identity.auth.device.t9;
import com.amazon.identity.auth.device.z9;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public enum DeviceAttribute {
    CentralDeviceType { // from class: com.amazon.identity.auth.device.attribute.DeviceAttribute.1
        @Override // com.amazon.identity.auth.device.attribute.DeviceAttribute
        public Object fetchValue(Context context) {
            return z9.b(context);
        }
    },
    CentralAPK { // from class: com.amazon.identity.auth.device.attribute.DeviceAttribute.2
        @Override // com.amazon.identity.auth.device.attribute.DeviceAttribute
        public Object fetchValue(Context context) {
            return Boolean.valueOf(t9.c(context));
        }
    };

    public abstract Object fetchValue(Context context);
}
