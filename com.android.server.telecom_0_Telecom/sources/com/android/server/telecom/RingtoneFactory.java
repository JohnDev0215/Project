package com.android.server.telecom;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.VolumeShaper;
import android.net.Uri;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.telecom.CallerInfo;
import android.telecom.Log;
import android.text.TextUtils;
import com.android.internal.annotations.VisibleForTesting;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public class RingtoneFactory {
    private final CallsManager mCallsManager;
    private final Context mContext;

    public RingtoneFactory(CallsManager callsManager, Context context) {
        this.mContext = context;
        this.mCallsManager = callsManager;
    }

    public boolean hasHapticChannels(Ringtone ringtone) {
        boolean zHasHapticChannels = RingtoneManager.hasHapticChannels(ringtone.getUri());
        Log.i(this, "hasHapticChannels %s -> %b", new Object[]{ringtone.getUri(), Boolean.valueOf(zHasHapticChannels)});
        return zHasHapticChannels;
    }

    public Ringtone getRingtone(Call call, VolumeShaper.Configuration configuration) {
        Context contextForUserHandle;
        Uri actualDefaultRingtoneUri;
        if (isWorkContact(call)) {
            contextForUserHandle = getWorkProfileContextForUser(this.mCallsManager.getCurrentUserHandle());
        } else {
            contextForUserHandle = getContextForUserHandle(this.mCallsManager.getCurrentUserHandle());
        }
        Uri ringtone = call.getRingtone();
        Ringtone ringtone2 = (ringtone == null || contextForUserHandle == null) ? null : RingtoneManager.getRingtone(contextForUserHandle, ringtone, configuration);
        if (ringtone2 == null) {
            if (!hasDefaultRingtoneForUser(contextForUserHandle)) {
                contextForUserHandle = this.mContext;
            }
            if (UserManager.get(contextForUserHandle).isUserUnlocked(contextForUserHandle.getUserId())) {
                actualDefaultRingtoneUri = RingtoneManager.getActualDefaultRingtoneUri(contextForUserHandle, 1);
            } else {
                actualDefaultRingtoneUri = Settings.System.DEFAULT_RINGTONE_URI;
            }
            if (actualDefaultRingtoneUri == null) {
                return null;
            }
            ringtone2 = RingtoneManager.getRingtone(contextForUserHandle, actualDefaultRingtoneUri, configuration);
        }
        if (ringtone2 != null) {
            ringtone2.setAudioAttributes(new AudioAttributes.Builder().setUsage(6).setContentType(4).build());
        }
        return ringtone2;
    }

    public Ringtone getRingtone(Call call) {
        return getRingtone(call, null);
    }

    private Context getWorkProfileContextForUser(UserHandle userHandle) {
        int i = 0;
        UserInfo userInfo = null;
        for (UserInfo userInfo2 : UserManager.get(this.mContext).getEnabledProfiles(userHandle.getIdentifier())) {
            if (userInfo2.getUserHandle() != userHandle && userInfo2.isManagedProfile()) {
                i++;
                userInfo = userInfo2;
            }
        }
        if (i == 1) {
            return getContextForUserHandle(userInfo.getUserHandle());
        }
        return null;
    }

    private Context getContextForUserHandle(UserHandle userHandle) {
        if (userHandle == null) {
            return null;
        }
        try {
            return this.mContext.createPackageContextAsUser(this.mContext.getPackageName(), 0, userHandle);
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("RingtoneFactory", "Package name not found: " + e.getMessage(), new Object[0]);
            return null;
        }
    }

    private boolean hasDefaultRingtoneForUser(Context context) {
        if (context == null) {
            return false;
        }
        return !TextUtils.isEmpty(Settings.System.getStringForUser(context.getContentResolver(), "ringtone", context.getUserId()));
    }

    private boolean isWorkContact(Call call) {
        CallerInfo callerInfo = call.getCallerInfo();
        return callerInfo != null && callerInfo.userType == 1;
    }
}
