package com.ts.androidauto.app.proxy.media;

import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import com.ts.androidauto.app.common.IProxy;
import com.ts.androidauto.app.common.LogUtil;
import com.ts.car.core.ServiceProvider;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.androidauto.IAndroidAutoAppManager;
import ts.car.service.androidauto.IMediaDataListener;
import ts.car.service.androidauto.data.MediaMetadata;

/* JADX INFO: loaded from: classes2.dex */
public class MediaProxy extends IProxy {
    private static final String TAG = "AAP" + MediaProxy.class.getSimpleName();
    private IAndroidAutoAppManager mAndroidAutoAppManager;
    private Context mContext;
    private IMediaDataListener mMediaDataListener;
    private final IServiceConnectionListener mServiceConnectionListener;

    public MediaProxy(Looper looper) {
        super(looper);
        this.mAndroidAutoAppManager = null;
        this.mServiceConnectionListener = new IServiceConnectionListener() { // from class: com.ts.androidauto.app.proxy.media.MediaProxy.1
            public void onServiceConnected(IPlatformService service) {
                LogUtil.debug(MediaProxy.TAG, "onServiceConnected.");
                if (MediaProxy.this.mAndroidAutoAppManager != null) {
                    try {
                        MediaProxy.this.mAndroidAutoAppManager.registerMediaDataListener(MediaProxy.this.mMediaDataListener);
                        return;
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                        return;
                    }
                }
                LogUtil.debug(MediaProxy.TAG, "mAndroidAutoAppManager is null.");
            }

            public void onServiceDisconnected(IPlatformService service) {
                LogUtil.debug(MediaProxy.TAG, "onServiceDisconnected.");
            }
        };
        this.mMediaDataListener = new IMediaDataListener() { // from class: com.ts.androidauto.app.proxy.media.MediaProxy.2
            public void onMusicStatusChanged(int status) {
            }

            public void onMediaMetadataChanged(MediaMetadata metadata) {
            }

            public void onMediaProgressChanged(int progress) {
            }
        };
    }

    @Override // com.ts.androidauto.app.common.IProxy
    public void init(Context context) {
        LogUtil.info(TAG, "init");
        this.mContext = context;
        IAndroidAutoAppManager iAndroidAutoAppManager = (IAndroidAutoAppManager) ServiceProvider.get(IAndroidAutoAppManager.class, context);
        this.mAndroidAutoAppManager = iAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null) {
            iAndroidAutoAppManager.registerConnectionListener(this.mServiceConnectionListener);
        }
    }

    @Override // com.ts.androidauto.app.common.IProxy
    public void uninit() {
        LogUtil.info(TAG, "uninit");
        IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null) {
            try {
                iAndroidAutoAppManager.unregisterMediaDataListener(this.mMediaDataListener);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            this.mAndroidAutoAppManager.unregisterConnectionListener(this.mServiceConnectionListener);
            this.mAndroidAutoAppManager = null;
        }
        removeCallbacksAndMessages(null);
    }
}
