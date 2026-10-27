package ts.car.service.wifi;

import ts.car.service.wifi.data.SoftApClientInfo;

/* JADX INFO: loaded from: classes2.dex */
public interface IExtWifiListener {
    void onClientConnectionEvent(int i, SoftApClientInfo softApClientInfo);
}
