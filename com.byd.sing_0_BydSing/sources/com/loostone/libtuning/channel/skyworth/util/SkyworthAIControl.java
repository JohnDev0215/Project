package com.loostone.libtuning.channel.skyworth.util;

import OooO00o.OooO00o.OooO0O0.a;
import OooO00o.OooO00o.OooO0O0.b;
import android.content.Context;
import android.content.Intent;
import com.android.volley.BuildConfig;
import com.loostone.libtuning.component.provider.TuningProvider;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class SkyworthAIControl {
    public static final String ACTION = "com.loostone.karaoke.voice";
    public static final String CMD_ENTER_KARAOKE_APP = "EnterKaraokeApp";
    public static final String CMD_ENTER_KARAOKE_PLAYER = "EnterKaraokePlayer";
    public static final String CMD_EXIT_KARAOKE_APP = "ExitKaraokeApp";
    public static final String CMD_EXIT_KARAOKE_PLAYER = "ExitKaraokePlayer";
    public static final String CMD_MIC_CHANGED = "MicChanged";
    public static final String CMD_MIC_EXIST = "MicExist";
    public static final String CMD_MIC_NOT_EXIST = "MicNotExist";
    public static final String CMD_VOICE_KEY_DOWN = "VoiceKeyDown";
    public static final String CMD_VOICE_KEY_UP = "VoiceKeyUp";
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "SkyworthAIControl";
    private int appStatus;
    private final Context context;
    private String packageName;
    private int playerStatus;
    private int voiceKeyStatus;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }
    }

    public SkyworthAIControl() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public SkyworthAIControl(Context context) {
        h.d(context, "context");
        this.context = context;
        this.packageName = BuildConfig.FLAVOR;
    }

    private final void sendBroadcast(String str, String str2) {
        b.f41a.e(TAG, "send cmd: " + str + ", value: " + ((Object) str2));
        Intent intent = new Intent();
        intent.setAction("com.loostone.karaoke.voice");
        intent.putExtra("cmd", str);
        if (str2 != null) {
            intent.putExtra(TuningProvider.VALUE, str2);
        }
        intent.setFlags(268435456);
        this.context.sendBroadcast(intent);
    }

    public static /* synthetic */ void sendBroadcast$default(SkyworthAIControl skyworthAIControl, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        skyworthAIControl.sendBroadcast(str, str2);
    }

    public final void enterKaraokeApp(String str) {
        h.d(str, "packageName");
        if (this.appStatus == 1) {
            return;
        }
        this.appStatus = 1;
        this.packageName = str;
        sendBroadcast$default(this, "EnterKaraokeApp", null, 2, null);
    }

    public final void enterKaraokePlayer() {
        if (this.playerStatus == 1) {
            return;
        }
        this.playerStatus = 1;
        sendBroadcast$default(this, "EnterKaraokePlayer", null, 2, null);
    }

    public final void exitKaraokeApp() {
        if (this.appStatus == 0) {
            return;
        }
        this.appStatus = 0;
        this.packageName = BuildConfig.FLAVOR;
        sendBroadcast$default(this, "ExitKaraokeApp", null, 2, null);
    }

    public final void exitKaraokePlayer() {
        if (this.playerStatus == 0) {
            return;
        }
        this.playerStatus = 0;
        sendBroadcast$default(this, "ExitKaraokePlayer", null, 2, null);
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final boolean isEnterApp() {
        return this.appStatus == 1;
    }

    public final boolean isEnterPlayer() {
        return this.playerStatus == 1;
    }

    public final void micChanged(int i) {
        sendBroadcast("MicChanged", String.valueOf(i));
    }

    public final void onMicExist(boolean z) {
        if (z) {
            sendBroadcast$default(this, "MicExist", null, 2, null);
        } else {
            sendBroadcast$default(this, "MicNotExist", null, 2, null);
        }
    }

    public final void onVoiceKey(boolean z) {
        if (z) {
            if (this.voiceKeyStatus == 1) {
                return;
            }
            this.voiceKeyStatus = 1;
            sendBroadcast$default(this, "VoiceKeyDown", null, 2, null);
            return;
        }
        if (this.voiceKeyStatus == 0) {
            return;
        }
        this.voiceKeyStatus = 0;
        sendBroadcast$default(this, "VoiceKeyUp", null, 2, null);
    }

    public /* synthetic */ SkyworthAIControl(Context context, int i, f fVar) {
        this((i & 1) != 0 ? a.f39a.a() : context);
    }
}
