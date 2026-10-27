package com.loostone.libtuning.channel.skyworth.old.ai.impl;

import OooO00o.OooO00o.OooO0O0.b;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.android.volley.BuildConfig;
import com.loostone.libtuning.channel.skyworth.old.ai.IAiControl;
import com.loostone.libtuning.component.provider.TuningProvider;

/* JADX INFO: loaded from: classes.dex */
public class CommonAiControl implements IAiControl {
    private Context mContext;
    private String mPackageName;
    private final String TAG = "CommonAiControl";
    private int mAppStatus = 0;
    private int mPlayerStatus = 0;
    private int mVoiceKeyStatus = 0;

    public CommonAiControl(Context context) {
        this.mContext = context;
    }

    private void sendBroadcast(String str) {
        b.f41a.b("CommonAiControl", "send cmd:" + str);
        Intent intent = new Intent("com.loostone.karaoke.voice");
        intent.putExtra("cmd", str);
        if (Build.VERSION.SDK_INT >= 16) {
            intent.setFlags(268435456);
        }
        this.mContext.sendBroadcast(intent);
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.ai.IAiControl
    public void enterKaraokeApp(String str) {
        if (this.mAppStatus == 1) {
            return;
        }
        this.mAppStatus = 1;
        this.mPackageName = str;
        sendBroadcast("EnterKaraokeApp");
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.ai.IAiControl
    public void enterKaraokePlayer() {
        if (this.mPlayerStatus == 1) {
            return;
        }
        this.mPlayerStatus = 1;
        sendBroadcast("EnterKaraokePlayer");
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.ai.IAiControl
    public void exitKaraokeApp() {
        if (this.mAppStatus == 0) {
            return;
        }
        this.mAppStatus = 0;
        this.mPackageName = BuildConfig.FLAVOR;
        sendBroadcast("ExitKaraokeApp");
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.ai.IAiControl
    public void exitKaraokePlayer() {
        if (this.mPlayerStatus == 0) {
            return;
        }
        this.mPlayerStatus = 0;
        sendBroadcast("ExitKaraokePlayer");
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.ai.IAiControl
    public String getPackageName() {
        return this.mPackageName;
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.ai.IAiControl
    public boolean isEnterApp() {
        return this.mAppStatus == 1;
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.ai.IAiControl
    public boolean isEnterPlayer() {
        return this.mPlayerStatus == 1;
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.ai.IAiControl
    public void micChanged(int i) {
        b.f41a.b("CommonAiControl", "micChanged:" + i);
        sendBroadcast("MicChanged", String.valueOf(i));
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.ai.IAiControl
    public void onMicExist(int i) {
        b.f41a.b("CommonAiControl", "micExist:" + i);
        if (i == 1) {
            sendBroadcast("MicExist");
        } else {
            sendBroadcast("MicNotExist");
        }
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.ai.IAiControl
    public void onVoiceKey(int i) {
        if (i == 1) {
            if (this.mVoiceKeyStatus == 1) {
                return;
            }
            this.mVoiceKeyStatus = 1;
            sendBroadcast("VoiceKeyDown");
            return;
        }
        if (this.mVoiceKeyStatus == 0) {
            return;
        }
        this.mVoiceKeyStatus = 0;
        sendBroadcast("VoiceKeyUp");
    }

    private void sendBroadcast(String str, String str2) {
        b.f41a.b("CommonAiControl", "send cmd:" + str + ", value:" + str2);
        Intent intent = new Intent("com.loostone.karaoke.voice");
        intent.putExtra("cmd", str);
        intent.putExtra(TuningProvider.VALUE, str2);
        if (Build.VERSION.SDK_INT >= 16) {
            intent.setFlags(268435456);
        }
        this.mContext.sendBroadcast(intent);
    }
}
