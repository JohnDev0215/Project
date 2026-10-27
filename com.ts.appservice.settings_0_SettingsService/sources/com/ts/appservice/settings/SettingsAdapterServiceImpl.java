package com.ts.appservice.settings;

import android.content.Context;
import android.content.res.Configuration;
import android.os.RemoteException;
import com.ts.appservice.settings.impl.appconfig.IAppConfigServiceBinder;
import com.ts.appservice.settings.impl.boot.IAppBootServiceBinder;
import com.ts.appservice.settings.impl.caradapter.CarAdapterServiceManager;
import com.ts.appservice.settings.impl.cartips.ICartipsSettingServiceBinder;
import com.ts.appservice.settings.impl.cluster.IClusterSettingServiceBinder;
import com.ts.appservice.settings.impl.container.IContainerServiceBinder;
import com.ts.appservice.settings.impl.dms.IDmsSettingServiceBinder;
import com.ts.appservice.settings.impl.key.IKeyServiceBinder;
import com.ts.appservice.settings.impl.language.ILanguageServiceBinder;
import com.ts.appservice.settings.impl.location.ILocationServiceBinder;
import com.ts.appservice.settings.impl.microphone.IMicroPhoneServiceBinder;
import com.ts.appservice.settings.impl.panorama.IPanoramaServiceBinder;
import com.ts.appservice.settings.impl.property.IPropertyServiceBinder;
import com.ts.appservice.settings.impl.theme.IThemeServiceBinder;
import com.ts.appservice.settings.impl.version.IVersionServiceBinder;
import com.ts.lib.settings.ISettingsAdapterService;
import com.ts.lib.settings.common.utilities.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class SettingsAdapterServiceImpl extends ISettingsAdapterService.Stub {
    private static final String TAG = SettingsAdapterServiceImpl.class.getSimpleName();
    private CarAdapterServiceManager mCarAdapterServiceManager;
    private Context mContext;
    private IAppBootServiceBinder mIAppBootServiceBinder;
    private IAppConfigServiceBinder mIAppConfigServiceBinder;
    private ICartipsSettingServiceBinder mICartipsSettingServiceBinder;
    private IClusterSettingServiceBinder mIClusterSettingServiceBinder;
    private IContainerServiceBinder mIContainerServiceBinder;
    private IDmsSettingServiceBinder mIDmsSettingServiceBinder;
    private IKeyServiceBinder mIKeyServiceBinder;
    private ILanguageServiceBinder mILanguageServiceBinder;
    private ILocationServiceBinder mILocationServiceBinder;
    private IMicroPhoneServiceBinder mIMicroPhoneServiceBinder;
    private IPanoramaServiceBinder mIPanoramaServiceBinder;
    private IPropertyServiceBinder mIPropertyServiceBinder;
    private IThemeServiceBinder mIThemeServiceBinder;
    private IVersionServiceBinder mIVersionServiceBinder;
    private boolean mServiceBindState = false;

    public SettingsAdapterServiceImpl(Context context) {
        this.mContext = context;
    }

    public void init() {
        this.mServiceBindState = true;
        LogUtil.debug(TAG, "init: start");
        this.mIAppBootServiceBinder = new IAppBootServiceBinder(this.mContext);
        this.mIAppConfigServiceBinder = new IAppConfigServiceBinder(this.mContext);
        this.mILanguageServiceBinder = new ILanguageServiceBinder(this.mContext);
        this.mIPropertyServiceBinder = new IPropertyServiceBinder(this.mContext);
        this.mIThemeServiceBinder = new IThemeServiceBinder(this.mContext);
        this.mIMicroPhoneServiceBinder = new IMicroPhoneServiceBinder(this.mContext);
        this.mIKeyServiceBinder = new IKeyServiceBinder(this.mContext);
        this.mIClusterSettingServiceBinder = new IClusterSettingServiceBinder(this.mContext);
        this.mIVersionServiceBinder = new IVersionServiceBinder(this.mContext);
        this.mIPanoramaServiceBinder = new IPanoramaServiceBinder(this.mContext);
        this.mIContainerServiceBinder = new IContainerServiceBinder(this.mContext);
        this.mILocationServiceBinder = new ILocationServiceBinder(this.mContext);
        this.mICartipsSettingServiceBinder = new ICartipsSettingServiceBinder(this.mContext);
        this.mIDmsSettingServiceBinder = new IDmsSettingServiceBinder(this.mContext);
        this.mCarAdapterServiceManager = new CarAdapterServiceManager(this.mContext);
    }

    public boolean isServiceBound() throws RemoteException {
        return this.mServiceBindState;
    }

    public void destory() {
        this.mServiceBindState = false;
        IThemeServiceBinder iThemeServiceBinder = this.mIThemeServiceBinder;
        if (iThemeServiceBinder != null) {
            iThemeServiceBinder.destoryThemeBinder();
        }
        IMicroPhoneServiceBinder iMicroPhoneServiceBinder = this.mIMicroPhoneServiceBinder;
        if (iMicroPhoneServiceBinder != null) {
            iMicroPhoneServiceBinder.destoryMicBinder();
        }
        IClusterSettingServiceBinder iClusterSettingServiceBinder = this.mIClusterSettingServiceBinder;
        if (iClusterSettingServiceBinder != null) {
            iClusterSettingServiceBinder.destoryClusterSettingBinder();
        }
        IPanoramaServiceBinder iPanoramaServiceBinder = this.mIPanoramaServiceBinder;
        if (iPanoramaServiceBinder != null) {
            iPanoramaServiceBinder.destoryPanoramaBinder();
        }
        ILocationServiceBinder iLocationServiceBinder = this.mILocationServiceBinder;
        if (iLocationServiceBinder != null) {
            iLocationServiceBinder.destoryLocationBinder();
        }
        IDmsSettingServiceBinder iDmsSettingServiceBinder = this.mIDmsSettingServiceBinder;
        if (iDmsSettingServiceBinder != null) {
            iDmsSettingServiceBinder.destoryDmsBinder();
        }
        ICartipsSettingServiceBinder iCartipsSettingServiceBinder = this.mICartipsSettingServiceBinder;
        if (iCartipsSettingServiceBinder != null) {
            iCartipsSettingServiceBinder.destoryCartipsSettingBinder();
        }
        IAppConfigServiceBinder iAppConfigServiceBinder = this.mIAppConfigServiceBinder;
        if (iAppConfigServiceBinder != null) {
            iAppConfigServiceBinder.destoryAppConfigServiceBinder();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:47:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.os.IBinder getSettingsAdapterService(java.lang.String r4) throws android.os.RemoteException {
        /*
            Method dump skipped, instruction units count: 320
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.appservice.settings.SettingsAdapterServiceImpl.getSettingsAdapterService(java.lang.String):android.os.IBinder");
    }

    public void onConfigurationChanged(Configuration newConfig) {
        LogUtil.debug(TAG, "onConfigurationChanged()");
        this.mILanguageServiceBinder.onConfigurationChanged(newConfig);
        this.mIPanoramaServiceBinder.onConfigurationChanged(newConfig);
        this.mIClusterSettingServiceBinder.onConfigurationChanged(newConfig);
    }
}
