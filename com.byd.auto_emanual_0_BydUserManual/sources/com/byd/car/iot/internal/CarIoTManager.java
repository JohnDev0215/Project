package com.byd.car.iot.internal;

import android.content.Context;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.byd.auto_emanual.Config;
import com.byd.autoiot.service.IAutoiotInterface;
import com.byd.car.iot.ICarIotManager;
import com.byd.car.iot.ICarIotManagerCallback;
import com.byd.car.iot.converter.AutoiotConverterManager;
import com.byd.car.iot.converter.AutoiotSetting;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@ServiceImpl(service = ICarIotManager.class, singleton = Config.USE_PRODUCT_URL)
public class CarIoTManager implements ICarIotManager {
    public CarIoTManager(Context context) {
        AutoiotConverterManager.getInstance().init(context);
    }

    @Override // com.byd.car.iot.ICarIotManager
    public boolean controlIotDevice(AutoiotSetting autoiotSetting) {
        Message message = new Message();
        message.what = 0;
        message.obj = autoiotSetting;
        return AutoiotConverterManager.getInstance().getHandler().sendMessage(message);
    }

    @Override // com.byd.car.iot.ICarIotManager
    public boolean getIotDeviceInfo(AutoiotSetting autoiotSetting) {
        Message message = new Message();
        message.what = 1;
        message.obj = autoiotSetting;
        return AutoiotConverterManager.getInstance().getHandler().sendMessage(message);
    }

    @Override // com.byd.car.iot.ICarIotManager
    public void registerListener(ICarIotManagerCallback iCarIotManagerCallback) {
        AutoiotConverterManager.getInstance().registerCallBack(iCarIotManagerCallback);
    }

    @Override // com.byd.car.iot.ICarIotManager
    public void unregisterListener() {
        AutoiotConverterManager.getInstance().unregisterCallBack();
    }

    @Override // com.byd.car.iot.ICarIotManager
    public List<String> getDeviceList() throws RemoteException {
        int i = 0;
        while (true) {
            IAutoiotInterface remoteServer = AutoiotConverterManager.getInstance().getRemoteServer();
            if (remoteServer != null) {
                return remoteServer.getDeviceList();
            }
            int i2 = i + 1;
            if (i > 100) {
                return null;
            }
            try {
                Thread.sleep(1L);
                Log.d("AutoiotConverterManager", "sleep");
            } catch (Exception e) {
                Log.e("AutoiotConverterManager", "error", e);
            }
            i = i2;
        }
    }
}
