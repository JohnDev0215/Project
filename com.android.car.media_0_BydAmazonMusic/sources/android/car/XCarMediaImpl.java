package android.car;

import android.app.Application;
import android.car.drivingstate.IXCarUxRestrictionsManager;
import android.car.media.IXCarMediaControl;
import android.car.media.IXCarMediaService;
import android.car.media.IXCarMediaSource;
import android.car.media.IXCarMediaStateListener;

/* JADX INFO: loaded from: classes.dex */
public class XCarMediaImpl {
    private static final String TAG = "XCarMediaImpl";
    private transient IXCarApplicationStub mXCarApplicationStub;
    private transient IXCarMediaControl mXCarMediaControl;
    private transient IXCarMediaService mXCarMediaService;
    private transient IXCarMediaSource mXCarMediaSource;
    private transient IXCarMediaStateListener mXCarMediaStateListener;
    private transient IXCarUxRestrictionsManager mXCarUxRestrictionsManager;

    public static class LazyHolder {
        private static final XCarMediaImpl INSTANCE = new XCarMediaImpl();

        private LazyHolder() {
        }
    }

    public static XCarMediaImpl getInstance() {
        return LazyHolder.INSTANCE;
    }

    public IXCarApplicationStub getXCarApplicationStub() {
        return this.mXCarApplicationStub;
    }

    public IXCarMediaControl getXCarMediaControl() {
        return this.mXCarMediaControl;
    }

    public IXCarMediaService getXCarMediaService() {
        return this.mXCarMediaService;
    }

    public IXCarMediaSource getXCarMediaSource() {
        return this.mXCarMediaSource;
    }

    public IXCarMediaStateListener getXCarMediaStateListener() {
        return this.mXCarMediaStateListener;
    }

    public IXCarUxRestrictionsManager getXCarUxRestrictionsManager() {
        return this.mXCarUxRestrictionsManager;
    }

    public void init(Application application, IXCarMediaControl iXCarMediaControl, IXCarMediaSource iXCarMediaSource, IXCarMediaService iXCarMediaService, IXCarMediaStateListener iXCarMediaStateListener, IXCarApplicationStub iXCarApplicationStub, IXCarUxRestrictionsManager iXCarUxRestrictionsManager) {
        iXCarMediaControl.init(application);
        iXCarMediaSource.init(application);
        iXCarMediaService.init(application);
        iXCarMediaStateListener.init(application);
        iXCarApplicationStub.init(application);
        iXCarUxRestrictionsManager.init(application);
        this.mXCarMediaControl = iXCarMediaControl;
        this.mXCarMediaSource = iXCarMediaSource;
        this.mXCarMediaService = iXCarMediaService;
        this.mXCarMediaStateListener = iXCarMediaStateListener;
        this.mXCarApplicationStub = iXCarApplicationStub;
        this.mXCarUxRestrictionsManager = iXCarUxRestrictionsManager;
    }
}
