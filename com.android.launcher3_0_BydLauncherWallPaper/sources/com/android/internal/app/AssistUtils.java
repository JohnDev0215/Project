package com.android.internal.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.util.Log;
import com.android.internal.R;
import com.android.internal.app.IVoiceInteractionManagerService;
import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class AssistUtils {
    private static final String TAG = "AssistUtils";
    private final Context mContext;
    private final IVoiceInteractionManagerService mVoiceInteractionManagerService = IVoiceInteractionManagerService.Stub.asInterface(ServiceManager.getService(Context.VOICE_INTERACTION_MANAGER_SERVICE));

    public AssistUtils(Context context) {
        this.mContext = context;
    }

    public boolean showSessionForActiveService(Bundle bundle, int i, IVoiceInteractionSessionShowCallback iVoiceInteractionSessionShowCallback, IBinder iBinder) {
        try {
            IVoiceInteractionManagerService iVoiceInteractionManagerService = this.mVoiceInteractionManagerService;
            if (iVoiceInteractionManagerService != null) {
                return iVoiceInteractionManagerService.showSessionForActiveService(bundle, i, iVoiceInteractionSessionShowCallback, iBinder);
            }
            return false;
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to call showSessionForActiveService", e);
            return false;
        }
    }

    public void getActiveServiceSupportedActions(Set<String> set, IVoiceActionCheckCallback iVoiceActionCheckCallback) {
        try {
            IVoiceInteractionManagerService iVoiceInteractionManagerService = this.mVoiceInteractionManagerService;
            if (iVoiceInteractionManagerService != null) {
                iVoiceInteractionManagerService.getActiveServiceSupportedActions(new ArrayList(set), iVoiceActionCheckCallback);
            }
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to call activeServiceSupportedActions", e);
            try {
                iVoiceActionCheckCallback.onComplete(null);
            } catch (RemoteException unused) {
            }
        }
    }

    public void launchVoiceAssistFromKeyguard() {
        try {
            IVoiceInteractionManagerService iVoiceInteractionManagerService = this.mVoiceInteractionManagerService;
            if (iVoiceInteractionManagerService != null) {
                iVoiceInteractionManagerService.launchVoiceAssistFromKeyguard();
            }
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to call launchVoiceAssistFromKeyguard", e);
        }
    }

    public boolean activeServiceSupportsAssistGesture() {
        try {
            IVoiceInteractionManagerService iVoiceInteractionManagerService = this.mVoiceInteractionManagerService;
            if (iVoiceInteractionManagerService != null) {
                return iVoiceInteractionManagerService.activeServiceSupportsAssist();
            }
            return false;
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to call activeServiceSupportsAssistGesture", e);
            return false;
        }
    }

    public boolean activeServiceSupportsLaunchFromKeyguard() {
        try {
            IVoiceInteractionManagerService iVoiceInteractionManagerService = this.mVoiceInteractionManagerService;
            if (iVoiceInteractionManagerService != null) {
                return iVoiceInteractionManagerService.activeServiceSupportsLaunchFromKeyguard();
            }
            return false;
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to call activeServiceSupportsLaunchFromKeyguard", e);
            return false;
        }
    }

    public ComponentName getActiveServiceComponentName() {
        try {
            IVoiceInteractionManagerService iVoiceInteractionManagerService = this.mVoiceInteractionManagerService;
            if (iVoiceInteractionManagerService != null) {
                return iVoiceInteractionManagerService.getActiveServiceComponentName();
            }
            return null;
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to call getActiveServiceComponentName", e);
            return null;
        }
    }

    public boolean isSessionRunning() {
        try {
            IVoiceInteractionManagerService iVoiceInteractionManagerService = this.mVoiceInteractionManagerService;
            if (iVoiceInteractionManagerService != null) {
                return iVoiceInteractionManagerService.isSessionRunning();
            }
            return false;
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to call isSessionRunning", e);
            return false;
        }
    }

    public void hideCurrentSession() {
        try {
            IVoiceInteractionManagerService iVoiceInteractionManagerService = this.mVoiceInteractionManagerService;
            if (iVoiceInteractionManagerService != null) {
                iVoiceInteractionManagerService.hideCurrentSession();
            }
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to call hideCurrentSession", e);
        }
    }

    public void onLockscreenShown() {
        try {
            IVoiceInteractionManagerService iVoiceInteractionManagerService = this.mVoiceInteractionManagerService;
            if (iVoiceInteractionManagerService != null) {
                iVoiceInteractionManagerService.onLockscreenShown();
            }
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to call onLockscreenShown", e);
        }
    }

    public void registerVoiceInteractionSessionListener(IVoiceInteractionSessionListener iVoiceInteractionSessionListener) {
        try {
            IVoiceInteractionManagerService iVoiceInteractionManagerService = this.mVoiceInteractionManagerService;
            if (iVoiceInteractionManagerService != null) {
                iVoiceInteractionManagerService.registerVoiceInteractionSessionListener(iVoiceInteractionSessionListener);
            }
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to register voice interaction listener", e);
        }
    }

    public ComponentName getAssistComponentForUser(int i) {
        String stringForUser = Settings.Secure.getStringForUser(this.mContext.getContentResolver(), Settings.Secure.ASSISTANT, i);
        if (stringForUser != null) {
            return ComponentName.unflattenFromString(stringForUser);
        }
        return null;
    }

    public static boolean isPreinstalledAssistant(Context context, ComponentName componentName) {
        if (componentName == null) {
            return false;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(componentName.getPackageName(), 0);
            return applicationInfo.isSystemApp() || applicationInfo.isUpdatedSystemApp();
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean isDisclosureEnabled(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.ASSIST_DISCLOSURE_ENABLED, 0) != 0;
    }

    public static boolean shouldDisclose(Context context, ComponentName componentName) {
        return (allowDisablingAssistDisclosure(context) && !isDisclosureEnabled(context) && isPreinstalledAssistant(context, componentName)) ? false : true;
    }

    public static boolean allowDisablingAssistDisclosure(Context context) {
        return context.getResources().getBoolean(R.bool.config_allowDisablingAssistDisclosure);
    }
}
