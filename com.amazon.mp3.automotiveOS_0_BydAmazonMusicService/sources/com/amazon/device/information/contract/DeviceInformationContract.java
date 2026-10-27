package com.amazon.device.information.contract;

import android.net.Uri;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class DeviceInformationContract {
    public static final Uri AUTHORITY_URI = Uri.parse("content://com.amazon.device.information.provider");

    private DeviceInformationContract() {
    }

    public static final class DeviceInfo {
        public static final List<String> COLUMN_LIST;
        public static final Uri CONTENT_URI = Uri.withAppendedPath(DeviceInformationContract.AUTHORITY_URI, "device_info");

        private DeviceInfo() {
        }

        static {
            ArrayList arrayList = new ArrayList();
            arrayList.add(CentralAccountManagerCommunication.RegisterChildApplication.KEY_DEVICE_TYPE);
            arrayList.add("dsn");
            COLUMN_LIST = Collections.unmodifiableList(arrayList);
        }
    }
}
