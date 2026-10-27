package com.android.server.wm;

import android.app.ActivityTaskManager;
import android.content.ComponentName;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Slog;
import android.util.SparseIntArray;
import com.android.server.wm.LaunchParamsController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class CarLaunchParamsModifier implements LaunchParamsController.LaunchParamsModifier {
    private static final boolean DBG = false;
    private static final String TAG = "CAR.LAUNCH";
    private ActivityTaskManagerService mAtm;
    private final Context mContext;
    private DisplayManager mDisplayManager;
    private boolean mIsSourcePreferred;
    private List<ComponentName> mSourcePreferredComponents;
    private final Object mLock = new Object();
    private int mCurrentDriverUser = 0;
    private final ArrayList<Integer> mPassengerDisplays = new ArrayList<>();
    private final SparseIntArray mDisplayToProfileUserMapping = new SparseIntArray();
    private final SparseIntArray mDefaultDisplayForProfileUser = new SparseIntArray();
    final DisplayManager.DisplayListener mDisplayListener = new DisplayManager.DisplayListener() { // from class: com.android.server.wm.CarLaunchParamsModifier.1
        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int displayId) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int displayId) {
            synchronized (CarLaunchParamsModifier.this.mLock) {
                CarLaunchParamsModifier.this.mPassengerDisplays.remove(Integer.valueOf(displayId));
                CarLaunchParamsModifier.this.updateProfileUserConfigForDisplayRemovalLocked(displayId);
            }
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int displayId) {
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void updateProfileUserConfigForDisplayRemovalLocked(int displayId) {
        this.mDisplayToProfileUserMapping.delete(displayId);
        int i = this.mDefaultDisplayForProfileUser.indexOfValue(displayId);
        if (i >= 0) {
            this.mDefaultDisplayForProfileUser.removeAt(i);
        }
    }

    public CarLaunchParamsModifier(Context context) {
        this.mContext = context;
    }

    public void init() {
        ActivityTaskManagerService service = ActivityTaskManager.getService();
        this.mAtm = service;
        LaunchParamsController controller = service.mStackSupervisor.getLaunchParamsController();
        controller.registerModifier(this);
        DisplayManager displayManager = (DisplayManager) this.mContext.getSystemService(DisplayManager.class);
        this.mDisplayManager = displayManager;
        displayManager.registerDisplayListener(this.mDisplayListener, new Handler(Looper.getMainLooper()));
    }

    public void setSourcePreferredComponents(boolean enableSourcePreferred, List<ComponentName> sourcePreferredComponents) {
        synchronized (this.mLock) {
            this.mIsSourcePreferred = enableSourcePreferred;
            this.mSourcePreferredComponents = sourcePreferredComponents;
            if (sourcePreferredComponents != null) {
                Collections.sort(sourcePreferredComponents);
            }
        }
    }

    public void handleCurrentUserSwitching(int newUserId) {
        synchronized (this.mLock) {
            this.mCurrentDriverUser = newUserId;
            this.mDefaultDisplayForProfileUser.clear();
            this.mDisplayToProfileUserMapping.clear();
        }
    }

    private void removeUserFromWhitelistsLocked(int userId) {
        for (int i = this.mDisplayToProfileUserMapping.size() - 1; i >= 0; i--) {
            if (this.mDisplayToProfileUserMapping.valueAt(i) == userId) {
                this.mDisplayToProfileUserMapping.removeAt(i);
            }
        }
        this.mDefaultDisplayForProfileUser.delete(userId);
    }

    public void handleUserStopped(int stoppedUser) {
        synchronized (this.mLock) {
            removeUserFromWhitelistsLocked(stoppedUser);
        }
    }

    public void setDisplayWhitelistForUser(int userId, int[] displayIds) {
        synchronized (this.mLock) {
            for (int displayId : displayIds) {
                if (!this.mPassengerDisplays.contains(Integer.valueOf(displayId))) {
                    Slog.w(TAG, "setDisplayWhitelistForUser called with display:" + displayId + " not in passenger display list:" + this.mPassengerDisplays);
                } else {
                    if (userId == this.mCurrentDriverUser) {
                        this.mDisplayToProfileUserMapping.delete(displayId);
                    } else {
                        this.mDisplayToProfileUserMapping.put(displayId, userId);
                    }
                    int i = this.mDefaultDisplayForProfileUser.indexOfValue(displayId);
                    if (i >= 0) {
                        this.mDefaultDisplayForProfileUser.removeAt(i);
                    }
                }
            }
            if (displayIds.length > 0) {
                this.mDefaultDisplayForProfileUser.put(userId, displayIds[0]);
            } else {
                removeUserFromWhitelistsLocked(userId);
            }
        }
    }

    public void setPassengerDisplays(int[] displayIdsForPassenger) {
        synchronized (this.mLock) {
            for (int id : displayIdsForPassenger) {
                this.mPassengerDisplays.remove(Integer.valueOf(id));
            }
            for (int i = 0; i < this.mPassengerDisplays.size(); i++) {
                int displayId = this.mPassengerDisplays.get(i).intValue();
                updateProfileUserConfigForDisplayRemovalLocked(displayId);
            }
            this.mPassengerDisplays.clear();
            this.mPassengerDisplays.ensureCapacity(displayIdsForPassenger.length);
            for (int id2 : displayIdsForPassenger) {
                this.mPassengerDisplays.add(Integer.valueOf(id2));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0066  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int onCalculate(com.android.server.wm.Task r15, android.content.pm.ActivityInfo.WindowLayout r16, com.android.server.wm.ActivityRecord r17, com.android.server.wm.ActivityRecord r18, android.app.ActivityOptions r19, int r20, com.android.server.wm.LaunchParamsController.LaunchParams r21, com.android.server.wm.LaunchParamsController.LaunchParams r22) {
        /*
            Method dump skipped, instruction units count: 277
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.server.wm.CarLaunchParamsModifier.onCalculate(com.android.server.wm.Task, android.content.pm.ActivityInfo$WindowLayout, com.android.server.wm.ActivityRecord, com.android.server.wm.ActivityRecord, android.app.ActivityOptions, int, com.android.server.wm.LaunchParamsController$LaunchParams, com.android.server.wm.LaunchParamsController$LaunchParams):int");
    }

    private TaskDisplayArea getAlternativeDisplayAreaForPassengerLocked(int userId, TaskDisplayArea originalDisplayArea) {
        int displayId = this.mDefaultDisplayForProfileUser.get(userId, -1);
        if (displayId != -1) {
            return getDefaultTaskDisplayAreaOnDisplay(displayId);
        }
        if (!this.mPassengerDisplays.isEmpty()) {
            Slog.w(TAG, "No default display area for user:" + userId + " reassign to 1st passenger display area");
            return getDefaultTaskDisplayAreaOnDisplay(this.mPassengerDisplays.get(0).intValue());
        }
        Slog.w(TAG, "No default display for user:" + userId + " and no passenger display, keep the requested display area:" + originalDisplayArea);
        return originalDisplayArea;
    }

    TaskDisplayArea getDefaultTaskDisplayAreaOnDisplay(int displayId) {
        DisplayContent dc = this.mAtm.mRootWindowContainer.getDisplayContentOrCreate(displayId);
        if (dc == null) {
            return null;
        }
        return dc.getDefaultTaskDisplayArea();
    }
}
