package android.car.media;

import android.car.XCar;
import android.car.XCarManagerBase;
import android.car.XCarRoute;
import android.car.utils.Logs;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import androidx.media.MediaBrowserServiceCompat;
import com.alibaba.android.arouter.launcher.ARouter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import spi.c;

/* JADX INFO: loaded from: classes.dex */
public final class XCarMediaManager extends XCarManagerBase {
    public static final int MEDIA_SOURCE_MODE_BROWSE = 1;
    public static final int MEDIA_SOURCE_MODE_PLAYBACK = 0;
    public static final String TAG_MEDIA = "XCarMediaManager";
    private Handler handler;
    private IXCarMediaService mediaService;
    private IXCarMediaStateListener mediaStateListener;

    public interface MediaSourceChangedListener {
        void onMediaSourceChanged(ComponentName componentName);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MediaSourceMode {
    }

    public XCarMediaManager(XCar xCar, IBinder iBinder) {
        super(xCar);
        Handler handler = new Handler(Looper.getMainLooper());
        this.handler = handler;
        handler.post(new Runnable() { // from class: android.car.media.XCarMediaManager.1
            @Override // java.lang.Runnable
            public void run() {
                XCarMediaManager.this.newXCarMediaManager();
            }
        });
    }

    private ComponentName getMediaService(ComponentName componentName) {
        String packageName = componentName.getPackageName();
        String className = componentName.getClassName();
        PackageManager packageManager = getContext().getPackageManager();
        Intent intent = new Intent();
        intent.setPackage(packageName);
        intent.setAction(MediaBrowserServiceCompat.SERVICE_INTERFACE);
        Iterator<ResolveInfo> it = packageManager.queryIntentServices(intent, 64).iterator();
        while (it.hasNext()) {
            String str = it.next().serviceInfo.name;
            if (!TextUtils.isEmpty(str) && (TextUtils.isEmpty(className) || str.equals(className))) {
                return new ComponentName(packageName, str);
            }
        }
        StringBuilder sbK = c.k("No MediaBrowseService with ComponentName: ");
        sbK.append(componentName.flattenToString());
        Logs.d("XCarMediaManager", sbK.toString());
        return null;
    }

    public void addMediaSourceListener(MediaSourceChangedListener mediaSourceChangedListener, int i) {
    }

    public ComponentName getMediaSource(int i) {
        ComponentName mediaService = getMediaService(new ComponentName("com.amazon.mp3.automotiveOS", ""));
        return mediaService == null ? new ComponentName("com.amazon.mp3.automotiveOS", "com.amazon.mp3.auto.AutomotiveMusicMediaBrowserService") : mediaService;
    }

    public void newXCarMediaManager() {
        try {
            this.mediaService = (IXCarMediaService) ARouter.getInstance().build(XCarRoute.CAR_MEDIA_SERVICE).navigation();
            IXCarMediaStateListener iXCarMediaStateListener = (IXCarMediaStateListener) ARouter.getInstance().build(XCarRoute.CAR_MEDIA_STATE_LISTENER).navigation();
            this.mediaStateListener = iXCarMediaStateListener;
            if (iXCarMediaStateListener != null) {
                iXCarMediaStateListener.setCarMediaService(this.mediaService);
            }
        } catch (Exception e) {
            Logs.e("XCarMediaManager", e.getMessage());
        }
    }

    @Override // android.car.XCarManagerBase
    public void onCarDisconnected() {
        IXCarMediaStateListener iXCarMediaStateListener = this.mediaStateListener;
        if (iXCarMediaStateListener != null) {
            iXCarMediaStateListener.onCarDisconnected();
        }
    }

    public void setMediaSource(ComponentName componentName, int i) {
    }
}
