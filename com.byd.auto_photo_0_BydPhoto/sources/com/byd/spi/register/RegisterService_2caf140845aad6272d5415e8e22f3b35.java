package com.byd.spi.register;

import com.byd.dilink.acquisitionservice.IDiLinkAcquisitionClientManager;
import com.byd.dilink.app.IDiLinkBydIconUtil;
import com.byd.dilink.app.IDiLinkCarModeSkinManager;
import com.byd.dilink.app.IDiLinkCustomVehicleConfig;
import com.byd.dilink.app.IDiLinkUiStyleConfig;
import com.byd.dilink.media.IDiLinkAudioManager;
import com.byd.dilink.media.IDiLinkMediaControl;
import com.byd.dilink.os.IDiLinkCloudMqttServer;
import com.byd.dilink.os.IDiLinkPrefManager;
import com.byd.dilink.provider.IDiLinkDiCareRecordOfCarStatusSetting;
import com.byd.dilink.statusbar.IDiLinkStatusBarManager;
import com.byd.dilink30_30.media.DiLinkAudioManager;
import com.byd.spi.Spi;
import xxWX.xxWX.xxWX.WOWwWxWOO.WOWwWxWOO;
import xxWX.xxWX.xxWX.WOWwWxWOO.xWOwOW;
import xxWX.xxWX.xxWX.WOWwWxWOO.xxWX;
import xxWX.xxWX.xxWX.WW3x3x.WX3xWXwxW;

/* JADX INFO: loaded from: classes2.dex */
public class RegisterService_2caf140845aad6272d5415e8e22f3b35 {
    public static void register() {
        Spi.addService(IDiLinkBydIconUtil.class, xxWX.class);
        Spi.addService(IDiLinkMediaControl.class, xxWX.xxWX.xxWX.xWOwOW.xxWX.class);
        Spi.addService(IDiLinkCustomVehicleConfig.class, WOWwWxWOO.class);
        Spi.addService(IDiLinkCloudMqttServer.class, xxWX.xxWX.xxWX.WW3x3x.xxWX.class);
        Spi.addService(IDiLinkAudioManager.class, DiLinkAudioManager.class);
        Spi.addService(IDiLinkAcquisitionClientManager.class, xxWX.xxWX.xxWX.WX3xWXwxW.xxWX.class);
        Spi.addService(IDiLinkStatusBarManager.class, xxWX.xxWX.xxWX.WWX.xxWX.class);
        Spi.addService(IDiLinkUiStyleConfig.class, xWOwOW.class);
        Spi.addService(IDiLinkPrefManager.class, WX3xWXwxW.class);
        Spi.addService(IDiLinkCarModeSkinManager.class, xxWX.xxWX.xxWX.WOWwWxWOO.WX3xWXwxW.class);
        Spi.addService(IDiLinkDiCareRecordOfCarStatusSetting.class, xxWX.xxWX.xxWX.wXWw.xxWX.class);
    }
}
