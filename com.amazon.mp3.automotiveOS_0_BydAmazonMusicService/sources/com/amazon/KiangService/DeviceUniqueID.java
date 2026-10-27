package com.amazon.KiangService;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceUniqueID implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.KiangService.DeviceUniqueID");
    private String type;
    private String value;

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.value, this.type);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DeviceUniqueID)) {
            return false;
        }
        DeviceUniqueID deviceUniqueID = (DeviceUniqueID) obj;
        return Helper.equals(this.value, deviceUniqueID.value) && Helper.equals(this.type, deviceUniqueID.type);
    }
}
