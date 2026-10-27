package com.loostone.libtuning.channel.skyworth.old.karaoke.impl;

import OooO00o.OooO00o.OooO0O0.b;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import com.loostone.libtuning.channel.skyworth.old.ai.config.AiCmd;
import com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl;
import com.loostone.libtuning.component.provider.TuningProvider;

/* JADX INFO: loaded from: classes.dex */
public class NormalKaraokeControl implements IKaraokeControl {
    private Context mContext;
    private String mPackageName;
    private final String TAG = "NormalKaraokeControl";
    private final String ACTION = "com.loostone.action.voice.control";
    private final String START_APP_ACTION = "com.karaoke.action.voice.control";

    public NormalKaraokeControl(Context context) {
        this.mContext = context.getApplicationContext();
    }

    private Intent getIntent(String str) {
        b.f41a.b("NormalKaraokeControl", "getIntent, cmd:" + str + ", package:" + getPackageName());
        Intent intent = new Intent("com.loostone.action.voice.control");
        if (!TextUtils.isEmpty(getPackageName())) {
            intent.setPackage(getPackageName());
        }
        intent.putExtra("cmd", str);
        return intent;
    }

    private void sendBroadcast(Intent intent) {
        if (Build.VERSION.SDK_INT >= 16) {
            intent.setFlags(268435456);
        }
        this.mContext.sendBroadcast(intent);
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void adjustEcho(String str) {
        Intent intent = getIntent(AiCmd.ADJUST_ECHO);
        intent.putExtra(TuningProvider.VALUE, str);
        sendBroadcast(intent);
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void adjustMic(String str) {
        Intent intent = getIntent(AiCmd.ADJUST_MIC);
        intent.putExtra(TuningProvider.VALUE, str);
        sendBroadcast(intent);
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void adjustMusic(String str) {
        Intent intent = getIntent(AiCmd.ADJUST_MUSIC);
        intent.putExtra(TuningProvider.VALUE, str);
        sendBroadcast(intent);
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void adjustTone(String str) {
        Intent intent = getIntent(AiCmd.ADJUST_TONE);
        intent.putExtra(TuningProvider.VALUE, str);
        sendBroadcast(intent);
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void back() {
        sendBroadcast(getIntent(AiCmd.BACK));
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void closeApp() {
        sendBroadcast(getIntent(AiCmd.CLOSE_APP));
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void deleteSong(String str) {
        Intent intent = getIntent(AiCmd.DELETE_SONG);
        try {
            int i = Integer.parseInt(str);
            if (i > 0) {
                i--;
            }
            intent.putExtra(TuningProvider.VALUE, i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendBroadcast(intent);
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public String getPackageName() {
        return this.mPackageName;
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void nextPage() {
        sendBroadcast(getIntent(AiCmd.NEXT_PAGE));
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void openApp(String str, String str2) {
        Intent intent = new Intent("com.karaoke.action.voice.control");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setPackage(getPackageName());
        intent.setFlags(335544320);
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("singer", str2);
        }
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("song", str);
        }
        this.mContext.startActivity(intent);
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void openSelectedSong() {
        Intent intent = getIntent(AiCmd.OPEN_PAGE);
        intent.putExtra("page", AiCmd.PAGE_SELECT_SONG);
        sendBroadcast(intent);
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void openTuning() {
        Intent intent = getIntent(AiCmd.OPEN_PAGE);
        intent.putExtra("page", AiCmd.PAGE_TUNING);
        sendBroadcast(intent);
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void pause() {
        sendBroadcast(getIntent(AiCmd.PAUSE));
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void play() {
        sendBroadcast(getIntent(AiCmd.PLAY));
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void playNext() {
        sendBroadcast(getIntent(AiCmd.PLAY_NEXT));
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void prePage() {
        sendBroadcast(getIntent(AiCmd.PRE_PAGE));
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void replay() {
        sendBroadcast(getIntent(AiCmd.REPLAY));
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void search(String str, String str2) {
        Intent intent = getIntent(AiCmd.SEARCH);
        intent.putExtra("singer", str2);
        intent.putExtra("song", str);
        intent.addFlags(32);
        sendBroadcast(intent);
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void setPackageName(String str) {
        this.mPackageName = str;
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void stop() {
        sendBroadcast(getIntent(AiCmd.STOP));
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void switchScore(String str) {
        Intent intent = getIntent(AiCmd.SWITCH_SCORE);
        if (!TextUtils.isEmpty(str)) {
            if ("1".equals(str)) {
                intent.putExtra(TuningProvider.VALUE, 1);
            } else {
                intent.putExtra(TuningProvider.VALUE, 0);
            }
        }
        sendBroadcast(intent);
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void switchVocal(String str) {
        Intent intent = getIntent(AiCmd.SWITCH_VOCAL);
        if (!TextUtils.isEmpty(str)) {
            if ("1".equals(str)) {
                intent.putExtra(TuningProvider.VALUE, 1);
            } else {
                intent.putExtra(TuningProvider.VALUE, 0);
            }
        }
        sendBroadcast(intent);
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void topSong(String str) {
        Intent intent = getIntent(AiCmd.TOP_SONG);
        try {
            int i = Integer.parseInt(str);
            if (i > 0) {
                i--;
            }
            intent.putExtra(TuningProvider.VALUE, i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendBroadcast(intent);
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl
    public void play(int i) {
        Intent intent = getIntent(AiCmd.PLAY);
        if (i > 0) {
            i--;
        }
        intent.putExtra(TuningProvider.VALUE, i);
        sendBroadcast(intent);
    }
}
