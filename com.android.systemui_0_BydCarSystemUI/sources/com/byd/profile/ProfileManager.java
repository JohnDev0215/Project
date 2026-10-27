package com.byd.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.GestureManager;
import com.byd.profile.activity.RestModeActivity;
import com.byd.profile.activity.WipeActivity;
import com.byd.profile.dialog.ProfileDialog;
import com.byd.profile.mode.Mode;
import com.byd.profile.mode.RestMode;
import com.byd.profile.mode.WipeMode;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ExecutorService;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class ProfileManager implements IVehicleStatusChange {
    private static final long DELAY_RESUME = 8000;
    public static final boolean IS_SCENE_ENABLE = false;
    public static final int MODE_BABY = 1;
    public static final int MODE_BOSS = 8;
    public static final int MODE_BUSINESS = 9;
    public static final int MODE_CAMPING = 7;
    public static final int MODE_CLEAN = 5;
    public static final int MODE_MEDITATION = 6;
    public static final int MODE_NAP = 2;
    public static final int MODE_NONE = 0;
    public static final int MODE_PET = 3;
    public static final int MODE_ROMANTIC = 4;
    private static final int WHAT_MONITOR = 2;
    private static final int WHAT_RESUME = 3;
    private static volatile ProfileManager sInstance;
    private boolean isCleanResume;
    private boolean isCleanScreen;
    private boolean isNapResume;
    private final Context mContext;
    private int mCurrentMode;
    private ProfileDialog mEditDialog;
    private ExecutorService mExecutorService;
    private GestureManager mGestureManager;
    private Handler mHandler;
    private Mode mLastMode;
    private GestureManager.MonitorStateListener mMonitorStateListener = new GestureManager.MonitorStateListener() { // from class: com.byd.profile.ProfileManager.1
        public void onMonitorState(int i) {
            if (ProfileManager.this.mHandler != null) {
                ProfileManager.this.mHandler.sendEmptyMessage(2);
            }
        }
    };
    private List<OnModeChangeListener> mOnModeChangeListeners;
    private Mode mWipeMode;

    public interface OnModeChangeListener {
        void onModeChange(int mode, boolean isChecked);
    }

    public void clear() {
    }

    public void editMode(int mode, Context context) {
    }

    public boolean isAvailable(int modeId) {
        return false;
    }

    public boolean isStarted(int mode) {
        return false;
    }

    @Override // com.byd.profile.IVehicleStatusChange
    public void onBatteryChange() {
    }

    @Override // com.byd.profile.IVehicleStatusChange
    public void onDoorOpened() {
    }

    @Override // com.byd.profile.IVehicleStatusChange
    public void onGearboxChange() {
    }

    @Override // com.byd.profile.IVehicleStatusChange
    public void onRestFinish() {
    }

    public void operateMode(int modeId) {
    }

    public void registerOnModeChangeListener(OnModeChangeListener onModeChangeListener) {
    }

    public void setProfileMode(int mode, boolean isChecked) {
    }

    public boolean stopMode() {
        return false;
    }

    public boolean stopMode(int modeId) {
        return false;
    }

    public void unregisterOnModeChangeListener(OnModeChangeListener onModeChangeListener) {
    }

    private ProfileManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static ProfileManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (ProfileManager.class) {
                if (sInstance == null) {
                    sInstance = new ProfileManager(context);
                }
            }
        }
        return sInstance;
    }

    public void runTask(Runnable runnable) {
        this.mExecutorService.submit(runnable);
    }

    private Mode initMode(int modeId) {
        Mode restMode;
        BydLogHelper.logD("initMode modeId = " + modeId);
        if (modeId == 2) {
            restMode = new RestMode(this.mContext);
        } else {
            restMode = modeId != 5 ? null : new WipeMode(this.mContext);
        }
        BydLogHelper.logD("initMode successful modeId = " + (restMode != null ? restMode.getModeId() : 0));
        return restMode;
    }

    public boolean isCleanScreen() {
        return this.isCleanScreen;
    }

    public int getProfileMode() {
        BydLogHelper.logD("mCurrentMode = " + this.mCurrentMode);
        return this.mCurrentMode;
    }

    public boolean isRestStarted() {
        return isStarted(2);
    }

    public boolean isStarted() {
        Mode mode = this.mLastMode;
        return mode != null && mode.isStarted();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStatusBarChange(StatusBarChangeEvent event) {
        BydLogHelper.logD("onStatusBarChange");
    }

    public void setNapResume(boolean napResume) {
        this.isNapResume = napResume;
    }

    public void setCleanResume(boolean cleanResume) {
        this.isCleanResume = cleanResume;
    }

    private void registerMonitor() {
        GestureManager gestureManager = (GestureManager) this.mContext.getSystemService("gesture");
        this.mGestureManager = gestureManager;
        gestureManager.subscribeMonitorStateListener(this.mMonitorStateListener);
        this.mGestureManager.setMonitorMode(true);
    }

    private static class MonitorHandler extends Handler {
        private WeakReference<ProfileManager> mProfileManager;

        public MonitorHandler(ProfileManager profileManager) {
            super(Looper.getMainLooper());
            this.mProfileManager = new WeakReference<>(profileManager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            WeakReference<ProfileManager> weakReference = this.mProfileManager;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            ProfileManager profileManager = this.mProfileManager.get();
            int profileMode = profileManager.getProfileMode();
            int i = msg.what;
            if (i != 3) {
                if (i == 2) {
                    profileManager.mHandler.removeMessages(3);
                    profileManager.mHandler.sendEmptyMessageDelayed(3, ProfileManager.DELAY_RESUME);
                    return;
                }
                return;
            }
            if (profileMode != 2 || profileManager.isNapResume) {
                if (profileMode != 5 || profileManager.isCleanResume) {
                    return;
                }
                BydLogHelper.logD("clean mode reStart now");
                Intent intent = new Intent(profileManager.mContext, (Class<?>) WipeActivity.class);
                intent.addFlags(268435456);
                profileManager.mContext.startActivity(intent);
                return;
            }
            BydLogHelper.logD("nap mode reStart now");
            Intent intent2 = new Intent(profileManager.mContext, (Class<?>) RestModeActivity.class);
            intent2.addFlags(268435456);
            profileManager.mContext.startActivity(intent2);
        }
    }
}
