package com.loostone.libtuning.channel.skyworth.old.ai;

import OooO00o.OooO00o.OooO0O0.b;
import android.content.Context;
import android.text.TextUtils;
import com.android.volley.BuildConfig;
import com.google.gson.Gson;
import com.loostone.libtuning.PuremicTuning;
import com.loostone.libtuning.channel.BaseChannelMgr;
import com.loostone.libtuning.channel.skyworth.old.ai.config.AiCmd;
import com.loostone.libtuning.channel.skyworth.old.karaoke.IKaraokeControl;
import com.loostone.libtuning.channel.skyworth.old.karaoke.impl.ChangbaKaraokeControl;
import com.loostone.libtuning.channel.skyworth.old.karaoke.impl.NormalKaraokeControl;
import com.loostone.libtuning.inf.extern.IVolumeAdjustment;

/* JADX INFO: loaded from: classes.dex */
public class AiControlMgr {
    private static AiControlMgr mInstance;
    private final BaseChannelMgr fullSceneSpeakMgr;
    private Context mContext;
    private IKaraokeControl mKaraokeControl;
    private final IVolumeAdjustment volumeAdjustment;
    private final String TAG = "AiControlMgr";
    private final String CHANGBA_APP = "com.changba.sd";

    private AiControlMgr() {
        PuremicTuning.Companion companion = PuremicTuning.Companion;
        this.fullSceneSpeakMgr = companion.getInstance().getChannelMgr();
        this.volumeAdjustment = companion.getInstance().getVolumeAdjustment();
    }

    private void adjustVolume(String str, String str2, int i) {
        if (AiCmd.INCREASE.equals(str2)) {
            str2 = "+" + i;
        } else if (AiCmd.REDUCE.equals(str2)) {
            str2 = "-" + i;
        }
        b bVar = b.f41a;
        bVar.b("AiControlMgr", "adjustVolume, cmd: " + str + ", value:" + str2);
        if (AiCmd.ADJUST_MUSIC.equals(str)) {
            if (this.mKaraokeControl == null || !AiControlFactory.getAiControl().isEnterApp()) {
                return;
            }
            this.mKaraokeControl.adjustMusic(str2);
            return;
        }
        if (AiCmd.ADJUST_MIC.equals(str)) {
            if (!AiControlFactory.getAiControl().isEnterPlayer()) {
                bVar.b("AiControlMgr", "self mic");
                this.volumeAdjustment.setMicVolume(parseVolume(str, str2));
                return;
            } else {
                if (this.mKaraokeControl != null) {
                    bVar.b("AiControlMgr", "else mic");
                    this.mKaraokeControl.adjustMic(str2);
                    return;
                }
                return;
            }
        }
        if (!AiCmd.ADJUST_ECHO.equals(str)) {
            if (AiCmd.ADJUST_TONE.equals(str) && this.mKaraokeControl != null && AiControlFactory.getAiControl().isEnterApp()) {
                this.mKaraokeControl.adjustTone(str2);
                return;
            }
            return;
        }
        if (!AiControlFactory.getAiControl().isEnterPlayer()) {
            bVar.b("AiControlMgr", "self echo");
            this.volumeAdjustment.setAef1ReverbOutGain(parseVolume(str, str2));
            return;
        }
        IKaraokeControl iKaraokeControl = this.mKaraokeControl;
        if (iKaraokeControl != null) {
            if (!"com.changba.sd".equals(iKaraokeControl.getPackageName())) {
                bVar.b("AiControlMgr", "else echo");
                this.mKaraokeControl.adjustEcho(str2);
                return;
            }
            bVar.b("AiControlMgr", "changba echo");
            if ("唱将".equals(str2)) {
                this.mKaraokeControl.adjustEcho("+0");
                return;
            }
            if ("魔音".equals(str2)) {
                this.mKaraokeControl.adjustEcho("+1");
                return;
            }
            if ("专业".equals(str2)) {
                this.mKaraokeControl.adjustEcho("+2");
            } else if ("歌神".equals(str2)) {
                this.mKaraokeControl.adjustEcho("+3");
            } else {
                this.mKaraokeControl.adjustEcho(str2);
            }
        }
    }

    public static synchronized AiControlMgr getInstance() {
        if (mInstance == null) {
            mInstance = new AiControlMgr();
        }
        return mInstance;
    }

    private int getMicAvailable() {
        return this.fullSceneSpeakMgr.getVerifyStatus() == 2 ? 1 : 0;
    }

    private void initKaraokeControl(Cmd cmd) {
        String value;
        if (AiControlFactory.getAiControl().isEnterApp() && !AiCmd.OPEN_APP.equals(cmd.getCmd())) {
            value = AiControlFactory.getAiControl().getPackageName();
            b bVar = b.f41a;
            bVar.b("AiControlMgr", "already enter karaoke app, package:" + value);
            if (TextUtils.isEmpty(value)) {
                value = KaraokeVoiceAppLogic.getInstance().getPackageName();
                bVar.b("AiControlMgr", "package name is empty, use dft," + value);
            }
        } else if (AiCmd.OPEN_APP.equals(cmd.getCmd()) || AiCmd.SEARCH.equals(cmd.getCmd())) {
            b bVar2 = b.f41a;
            bVar2.b("AiControlMgr", "not enter karaoke app");
            if (KaraokeVoiceAppLogic.getInstance().isForceUsePackageName()) {
                value = KaraokeVoiceAppLogic.getInstance().getPackageName();
                bVar2.b("AiControlMgr", "ForceUsePackage, " + value);
            } else {
                value = cmd.getValue();
                bVar2.b("AiControlMgr", "extra," + value);
            }
            if (TextUtils.isEmpty(value)) {
                value = KaraokeVoiceAppLogic.getInstance().getPackageName();
                bVar2.b("AiControlMgr", "package name is empty, use dft," + value);
            }
        } else {
            value = BuildConfig.FLAVOR;
        }
        if (this.mKaraokeControl == null) {
            b.f41a.b("AiControlMgr", "karaokeControl is null, packageName:" + value);
            if (TextUtils.isEmpty(value)) {
                return;
            }
            if ("com.changba.sd".equals(value)) {
                this.mKaraokeControl = new ChangbaKaraokeControl(this.mContext);
            } else {
                this.mKaraokeControl = new NormalKaraokeControl(this.mContext);
            }
            this.mKaraokeControl.setPackageName(value);
            return;
        }
        b bVar3 = b.f41a;
        bVar3.b("AiControlMgr", "karaokeControl not null, packageName:" + value + ", control.packageName:" + this.mKaraokeControl.getPackageName());
        if (TextUtils.isEmpty(value) || value.equals(this.mKaraokeControl.getPackageName())) {
            return;
        }
        bVar3.b("AiControlMgr", "recreate karaokeControl");
        if ("com.changba.sd".equals(value)) {
            this.mKaraokeControl = new ChangbaKaraokeControl(this.mContext);
        } else {
            this.mKaraokeControl = new NormalKaraokeControl(this.mContext);
        }
        this.mKaraokeControl.setPackageName(value);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int parseVolume(java.lang.String r7, java.lang.String r8) {
        /*
            r6 = this;
            com.loostone.libtuning.inf.extern.IVolumeAdjustment r0 = r6.volumeAdjustment
            int r0 = r0.getMicVolume()
            com.loostone.libtuning.inf.extern.IVolumeAdjustment r1 = r6.volumeAdjustment
            int r1 = r1.getAef1ReverbOutGain()
            boolean r2 = android.text.TextUtils.isEmpty(r8)
            java.lang.String r3 = "AdjustEcho"
            java.lang.String r4 = "AdjustMic"
            if (r2 == 0) goto L24
            boolean r2 = r4.equals(r7)
            if (r2 == 0) goto L1d
            return r0
        L1d:
            boolean r2 = r3.equals(r7)
            if (r2 == 0) goto L24
            return r1
        L24:
            java.lang.String r2 = "+"
            boolean r2 = r8.startsWith(r2)
            r5 = 0
            if (r2 == 0) goto L3f
            boolean r8 = r4.equals(r7)
            if (r8 == 0) goto L36
            int r8 = r0 + 10
            goto L6f
        L36:
            boolean r8 = r3.equals(r7)
            if (r8 == 0) goto L6e
            int r8 = r1 + 10
            goto L6f
        L3f:
            java.lang.String r2 = "-"
            boolean r2 = r8.startsWith(r2)
            if (r2 == 0) goto L59
            boolean r8 = r4.equals(r7)
            if (r8 == 0) goto L50
            int r8 = r0 + (-10)
            goto L6f
        L50:
            boolean r8 = r3.equals(r7)
            if (r8 == 0) goto L6e
            int r8 = r1 + (-10)
            goto L6f
        L59:
            int r8 = java.lang.Integer.parseInt(r8)     // Catch: java.lang.Exception -> L5e
            goto L6f
        L5e:
            boolean r8 = r4.equals(r7)
            if (r8 == 0) goto L66
            r8 = r0
            goto L6f
        L66:
            boolean r8 = r3.equals(r7)
            if (r8 == 0) goto L6e
            r8 = r1
            goto L6f
        L6e:
            r8 = r5
        L6f:
            boolean r2 = r4.equals(r7)
            r4 = 100
            if (r2 == 0) goto L7d
            if (r8 <= r4) goto L7a
            goto L7b
        L7a:
            r0 = r8
        L7b:
            r8 = r0
            goto L88
        L7d:
            boolean r7 = r3.equals(r7)
            if (r7 == 0) goto L88
            if (r8 <= r4) goto L86
            goto L87
        L86:
            r1 = r8
        L87:
            r8 = r1
        L88:
            if (r8 >= 0) goto L8b
            goto L8c
        L8b:
            r5 = r8
        L8c:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loostone.libtuning.channel.skyworth.old.ai.AiControlMgr.parseVolume(java.lang.String, java.lang.String):int");
    }

    public void create(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void destroy() {
        this.mContext = null;
        this.mKaraokeControl = null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int sendCmd(String str) {
        Cmd cmd;
        try {
            cmd = (Cmd) new Gson().fromJson(str, Cmd.class);
        } catch (Exception e) {
            e.printStackTrace();
            cmd = null;
        }
        if (cmd == null) {
            b.f41a.b("AiControlMgr", "sendCmd, cmd = null");
            return -1;
        }
        initKaraokeControl(cmd);
        String value = cmd.getValue();
        b bVar = b.f41a;
        bVar.b("AiControlMgr", "sendCmd, cmd:" + cmd.getCmd() + ", value:" + cmd.getValue());
        if (AiCmd.ADJUST_MIC.equals(cmd.getCmd()) || AiCmd.ADJUST_ECHO.equals(cmd.getCmd())) {
            AiCmd.ADJUST_MIC.equals(cmd.getCmd());
            adjustVolume(cmd.getCmd(), value, 10);
            return 0;
        }
        if (this.mKaraokeControl == null) {
            return -1;
        }
        if (!AiControlFactory.getAiControl().isEnterApp() && !AiCmd.OPEN_APP.equals(cmd.getCmd()) && !AiCmd.SEARCH.equals(cmd.getCmd())) {
            bVar.b("AiControlMgr", "send cmd, exit");
            return -1;
        }
        switch (cmd.getCmd()) {
            case "OpenApp":
                bVar.b("AiControlMgr", "sendCmd, open app");
                if (getMicAvailable() == 1) {
                    this.mKaraokeControl.openApp(null, null);
                }
                AiControlFactory.getAiControl().onMicExist(getMicAvailable());
                return 0;
            case "CloseApp":
                bVar.b("AiControlMgr", "sendCmd, close app");
                this.mKaraokeControl.closeApp();
                return 0;
            case "Search":
                bVar.b("AiControlMgr", "sendCmd, search");
                if (getMicAvailable() == 1) {
                    if (AiControlFactory.getAiControl().isEnterApp() && this.mKaraokeControl.getPackageName() != null && this.mKaraokeControl.getPackageName().equals(AiControlFactory.getAiControl().getPackageName())) {
                        this.mKaraokeControl.search(cmd.getSong(), cmd.getSinger());
                    } else {
                        this.mKaraokeControl.openApp(cmd.getSong(), cmd.getSinger());
                    }
                }
                AiControlFactory.getAiControl().onMicExist(getMicAvailable());
                return 0;
            case "Play":
                if (TextUtils.isEmpty(value)) {
                    bVar.b("AiControlMgr", "sendCmd, play");
                    this.mKaraokeControl.play();
                } else {
                    bVar.b("AiControlMgr", "sendCmd, play, index:" + value);
                    try {
                        this.mKaraokeControl.play(Integer.parseInt(value));
                        break;
                    } catch (Exception unused) {
                    }
                }
                return 0;
            case "Pause":
                bVar.b("AiControlMgr", "sendCmd, pause");
                this.mKaraokeControl.pause();
                return 0;
            case "Stop":
                bVar.b("AiControlMgr", "sendCmd, stop");
                this.mKaraokeControl.stop();
                return 0;
            case "PlayNext":
                bVar.b("AiControlMgr", "sendCmd, play next");
                this.mKaraokeControl.playNext();
                return 0;
            case "Replay":
                bVar.b("AiControlMgr", "sendCmd, replay");
                this.mKaraokeControl.replay();
                return 0;
            case "SwitchVocal":
                bVar.b("AiControlMgr", "sendCmd, switch vocal");
                this.mKaraokeControl.switchVocal(value);
                return 0;
            case "SwitchScore":
                bVar.b("AiControlMgr", "sendCmd, switch score");
                this.mKaraokeControl.switchScore(value);
                return 0;
            case "AdjustMusic":
                bVar.b("AiControlMgr", "sendCmd, adjust music");
                adjustVolume(cmd.getCmd(), value, 10);
                return 0;
            case "AdjustMic":
                bVar.b("AiControlMgr", "sendCmd, adjust mic");
                adjustVolume(cmd.getCmd(), value, 10);
                return 0;
            case "AdjustEcho":
                bVar.b("AiControlMgr", "sendCmd, adjust echo");
                adjustVolume(cmd.getCmd(), value, 10);
                return 0;
            case "AdjustTone":
                bVar.b("AiControlMgr", "sendCmd, adjust tone");
                adjustVolume(cmd.getCmd(), value, 1);
                return 0;
            case "OpenPage":
                bVar.b("AiControlMgr", "sendCmd, open page");
                String page = cmd.getPage();
                if (AiCmd.PAGE_TUNING.equals(page)) {
                    this.mKaraokeControl.openTuning();
                } else if (AiCmd.PAGE_SELECT_SONG.equals(page)) {
                    this.mKaraokeControl.openSelectedSong();
                }
                return 0;
            case "Back":
                bVar.b("AiControlMgr", "sendCmd, back");
                this.mKaraokeControl.back();
                return 0;
            case "NextPage":
                bVar.b("AiControlMgr", "sendCmd, next page");
                this.mKaraokeControl.nextPage();
                return 0;
            case "PrePage":
                bVar.b("AiControlMgr", "sendCmd, pre page");
                this.mKaraokeControl.prePage();
                return 0;
            case "DeleteSong":
                bVar.b("AiControlMgr", "sendCmd, delete song");
                this.mKaraokeControl.deleteSong(value);
                return 0;
            case "TopSong":
                bVar.b("AiControlMgr", "sendCmd, top song");
                this.mKaraokeControl.topSong(value);
                return 0;
            default:
                return 0;
        }
    }
}
