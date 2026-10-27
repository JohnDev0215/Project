package com.byd.vrassistant.receiver;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import com.byd.vrassistant.utils.RLog;

/* JADX INFO: loaded from: classes2.dex */
public class ScreenObserver8155 extends ContentObserver {
    public static final int NAVIBAR_MODE_MODE_AC = 2;
    public static final int NAVIBAR_MODE_MODE_ALL = 1;
    public static final String NAVIBAR_MODE_ON_SCREENSAVER = "navibar_mode_on_screensaver";
    public static final int NAVIBAR_MODE_POWER_OFF = 0;
    public static String TAG = "NEU_ScreenObserver8155";

    public ScreenObserver8155(Handler handler) {
        super(handler);
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        RLog.d(TAG, "onChange uri: " + lastPathSegment);
        if (lastPathSegment.equals(NAVIBAR_MODE_ON_SCREENSAVER)) {
            int i = Settings.Global.getInt(BydVoiceApplication.getContext().getContentResolver(), NAVIBAR_MODE_ON_SCREENSAVER, 0);
            RLog.d(TAG, "screenOffType: " + i);
            if (i == 0 || i == 2) {
                VoiceAssistantManager.get().sendCancelPTTEvent();
            }
        }
    }
}
