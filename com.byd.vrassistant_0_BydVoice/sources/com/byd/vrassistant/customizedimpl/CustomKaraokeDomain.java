package com.byd.vrassistant.customizedimpl;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.Domains;
import cerence.ark.assistant.controller.domain.karaoke.AbsKaraokeDomain;
import cerence.ark.assistant.controller.domain.karaoke.KaraokeRequestsEnum;
import cerence.ark.assistant.controller.domain.karaoke.param.ShowKaraokeHistoryParam;
import cerence.ark.assistant.controller.domain.karaoke.param.SingSongParam;
import cerence.ark.assistant.controller.domain.karaoke.param.SwitchKaraokeModeParam;
import cerence.ark.assistant.controller.domain.karaoke.result.IsKaraokeRunningResult;
import cerence.ark.assistant.controller.enumeration.KaraokeModeEnum;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.customizedimpl.CustomSystemDomain;
import com.byd.vrassistant.manager.KaraokeManager;
import com.byd.vrassistant.manager.LocalMediaManager;
import com.byd.vrassistant.utils.ForegroundAppUtil;
import com.byd.vrassistant.utils.RLog;

/* JADX INFO: loaded from: classes.dex */
public class CustomKaraokeDomain extends AbsKaraokeDomain {
    public static String TAG = "NEU_CustomKaraokeDomain";
    CustomSystemDomain.TestLogCat testLogCat;

    protected CustomKaraokeDomain(Dhi dhi) {
        super(dhi);
        this.testLogCat = new CustomSystemDomain.TestLogCat(Domains.KARAOKE);
    }

    @Override // cerence.ark.assistant.controller.domain.karaoke.AbsKaraokeDomain
    protected void singSong(SingSongParam singSongParam, IResponseSender<BaseResult> iResponseSender) {
        KaraokeManager.get().searchMusicInfo(singSongParam.getSongInfo(), iResponseSender, this.testLogCat);
    }

    @Override // cerence.ark.assistant.controller.domain.karaoke.AbsKaraokeDomain
    protected void isKaraokeRunning(IResponseSender<IsKaraokeRunningResult> iResponseSender) {
        RLog.i(TAG, KaraokeRequestsEnum.IS_KARAOKE_RUNNING);
        IsKaraokeRunningResult isKaraokeRunningResult = new IsKaraokeRunningResult();
        boolean zIsAppInstalled = ForegroundAppUtil.isAppInstalled(BydVoiceApplication.getContext(), LocalMediaManager.KARAOKE_PACKAGE_NAME);
        boolean zIsStartedApp = ForegroundAppUtil.isStartedApp(LocalMediaManager.KARAOKE_PACKAGE_NAME);
        if (zIsAppInstalled && zIsStartedApp) {
            isKaraokeRunningResult.setIsRunning(true);
        } else {
            isKaraokeRunningResult.setIsRunning(false);
        }
        this.testLogCat.setResponse("OK");
        iResponseSender.send(new Response("OK", isKaraokeRunningResult));
    }

    @Override // cerence.ark.assistant.controller.domain.karaoke.AbsKaraokeDomain
    protected void switchKaraokeMode(SwitchKaraokeModeParam switchKaraokeModeParam, IResponseSender<BaseResult> iResponseSender) {
        RLog.i(TAG, KaraokeRequestsEnum.SWITCH_KARAOKE_MODE);
        if (KaraokeModeEnum.ORIGINAL_TRACK.equals(switchKaraokeModeParam.getMode())) {
            KaraokeManager.get().vocalOn();
        } else {
            KaraokeManager.get().vocalOff();
        }
        this.testLogCat.setResponse("OK");
        iResponseSender.send(new Response("OK"));
    }

    @Override // cerence.ark.assistant.controller.domain.karaoke.AbsKaraokeDomain
    protected void showKaraokeHistory(ShowKaraokeHistoryParam showKaraokeHistoryParam, IResponseSender<BaseResult> iResponseSender) {
        if ("ON".equals(showKaraokeHistoryParam.getAction())) {
            KaraokeManager.get().showKaraokeHistory();
            this.testLogCat.setResponse("OK");
            iResponseSender.send(new Response("OK"));
        }
    }

    @Override // cerence.ark.assistant.api.Activated
    public void activate() {
        RLog.i(TAG, "activate CustomKaraokeDomain");
    }

    @Override // cerence.ark.assistant.api.Activated
    public void deactivate() {
        RLog.i(TAG, "deactivate CustomKaraokeDomain");
    }
}
