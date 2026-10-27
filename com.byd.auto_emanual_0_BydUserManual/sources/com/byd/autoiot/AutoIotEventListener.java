package com.byd.autoiot;

import com.byd.autoiot.service.AutoiotProfile;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface AutoIotEventListener {
    void onDeviceChanged(String str, String str2, String str3);

    void onDeviceChangedWithData(String str, String str2, String str3, AutoiotProfile autoiotProfile);

    void onDeviceConnected(String str);

    void onDeviceDeleted(String str);

    void onDeviceDisconnected(String str);

    void onDeviceLeave(String str);

    void onNewDeviceDiscovered(List<String> list);

    void onReadResult(String str, String str2, String str3, AutoiotProfile autoiotProfile);

    void onWriteResult(String str, String str2, String str3, boolean z);
}
