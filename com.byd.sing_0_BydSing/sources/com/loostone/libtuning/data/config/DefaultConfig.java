package com.loostone.libtuning.data.config;

import com.loostone.libtuning.data.bean.EQItem;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultConfig {
    public static final OooO00o Companion = new OooO00o(null);
    private static final String TAG = "DefaultConfig";
    private Integer aef1ReverbEnable;
    private Integer aef1ReverbOutGain;
    private Integer aef2CompressorAttack;
    private Integer aef2CompressorEnable;
    private Integer aef2CompressorPregain;
    private Integer aef2CompressorRatio;
    private Integer aef2CompressorRelease;
    private Integer aef2CompressorThreshold;
    private Integer aef2EQEnable;
    private Integer aef2EchoEnable;
    private Integer aef2EchoFeedback;
    private Integer aef2EchoGain;
    private Integer aef2EchoTime;
    private Integer aef2EchoType;
    private Integer aef2ReverbEnable;
    private Integer aef2ReverbTime;
    private Integer aef2ReverbType;
    private Integer aef2ReverbVolume;
    private Integer aef2VoiceChangeEnable;
    private Integer aef2VoiceMode;
    private Integer aef3EchoHicut;
    private Integer aef3EchoLowcut;
    private Integer aef3EchoStwide;
    private Integer aef3ReverbHicut;
    private Integer aef3ReverbHidamp;
    private Integer aef3ReverbLowcut;
    private Integer aef3ReverbStwide;
    private int audioTrack;
    private int balanceVolume;
    private int beta;
    private Integer debugPlaySinEnable;
    private int downloadFirmwareSwitch;
    private HardEffect hardEffect;
    private List<HardEffectDglItem> hardEffectDglList;
    private List<HardEffect> hardEffectMicList;
    private List<HardEffectDglItem> hardEffectTVLiftList;
    private HardEffectVolume hardEffectVolume;
    private int hardwareMicVolume;
    private int listenMicElectricity;
    private int micLevelFollowSysVol;
    private int playbackFormat;
    private Integer sysSupportHard;
    private int sysVolCurveSwitch;
    private int twoMicCompPregainOffset;
    private VocalRemove vocalRemove;
    private long channel = 100001;
    private int configVersion = 1;
    private int workModeInt = 1;
    private String workMode = "01002";
    private String logLevel = "1";
    private int volSft = 128;
    private int volStr = 1;
    private int captureChannel = 2;
    private int captureRate = 48000;
    private int capturePeriodSize = 256;
    private int capturePeriodCount = 4;
    private int captureFormat = 1;
    private int captureStartThreshold = 1;
    private int captureStopThreshold = 1;
    private int captureSilenceThreshold = 1;
    private int captureSilenceSize = 1;
    private int captureAvailMin = 1;
    private int playbackChannel = 2;
    private int playbackRate = 48000;
    private int playbackPeriodSize = 256;
    private int playbackPeriodCount = 4;
    private int playbackStartThreshold = 1;
    private int playbackStopThreshold = 1;
    private int playbackSilenceThreshold = 1;
    private int playbackSilenceSize = 1;
    private int playbackAvailMin = 1;
    private int micVolume = 90;
    private int musicVolume = 1;
    private int isMicEnable = 1;
    private int systemVolume = 1;
    private int aef2MusicEQEnable = 1;
    private List<EQItem> eqList = new ArrayList();
    private int aef2MusicFFTCalculateFrequency = 1;
    private int aef2MusicFFTCount = 16;
    private int aef2MicFFTCalculateFrequency = 1;
    private int aef2MicFFTCount = 16;
    private double[] volumeCurveUI = new double[0];
    private double[] volumeCurveSystem = new double[0];
    private int[] volumeCurveMax = new int[0];
    private int volumeShiftR = -1;
    private int volumeShiftL = 1;
    private double[] sysVolcurveX = new double[0];
    private double[] sysVolcurveY = new double[0];
    private double[] micVolcurveX = new double[0];
    private double[] micVolcurveY = new double[0];
    private double[] BGMsysVolcurveX = new double[0];
    private double[] BGMsysVolcurveY = new double[0];
    private int dmaStopThreshold = 2048;
    private int playbackWaitSleepInterval = 500;
    private int ringStartThreshold = 2048;
    private int dataPrintFrequency = 2000;
    private int delayFluentThresholdPlayback = 4000;
    private int delayFluentThresholdCapture = 500;
    private int checkDelayIntervalPlayback = 20;
    private int checkDelayIntervalCapture = 20;
    private int jumpToMuteThresholdPlayback = 15;
    private int jumpToMuteThresholdCapture = 15;
    private int keepMuteCountPlayback = 25;
    private int keepMuteCountCapture = 25;
    private int noiseSleepIntervalPlayback = 100000;
    private int noiseSleepIntervalCapture = 100000;
    private int ringBufferLength = 16384;
    private int resampleBufferSize = 32768;
    private int delayAddition = 5;
    private int audioRecord = 1;
    private int listenSystemVolume = 1;
    private int dynamicAdjustCompPregain = 1;
    private int micVolumeStep = 5;
    private int reverbVolumeStep = 5;
    private int musicVolumeStep = 5;
    private int keyMode = 1;
    private int effectPeriod = 240;
    private int fsEnable = 1;
    private int aef2NoiseGatePopRemove = 1;
    private int noiseGatePopRemoveThres = 30;
    private int noiseGatePopRemoveRelease = 1000;
    private int noiseGatePopRemovePopWidth = 2;
    private int noiseGatePopRemoveAutoMuteTime = 300;
    private int noiseGatePopRemoveAutoUnMuteTime = 100;
    private int noiseGatePopRemoveAutoMuteThres = 35;

    public static final class OooO00o {
        public OooO00o() {
        }

        public /* synthetic */ OooO00o(f fVar) {
            this();
        }
    }

    public final Integer getAef1ReverbEnable() {
        return this.aef1ReverbEnable;
    }

    public final Integer getAef1ReverbOutGain() {
        return this.aef1ReverbOutGain;
    }

    public final Integer getAef2CompressorAttack() {
        return this.aef2CompressorAttack;
    }

    public final Integer getAef2CompressorEnable() {
        return this.aef2CompressorEnable;
    }

    public final Integer getAef2CompressorPregain() {
        return this.aef2CompressorPregain;
    }

    public final Integer getAef2CompressorRatio() {
        return this.aef2CompressorRatio;
    }

    public final Integer getAef2CompressorRelease() {
        return this.aef2CompressorRelease;
    }

    public final Integer getAef2CompressorThreshold() {
        return this.aef2CompressorThreshold;
    }

    public final Integer getAef2EQEnable() {
        return this.aef2EQEnable;
    }

    public final Integer getAef2EchoEnable() {
        return this.aef2EchoEnable;
    }

    public final Integer getAef2EchoFeedback() {
        return this.aef2EchoFeedback;
    }

    public final Integer getAef2EchoGain() {
        return this.aef2EchoGain;
    }

    public final Integer getAef2EchoTime() {
        return this.aef2EchoTime;
    }

    public final Integer getAef2EchoType() {
        return this.aef2EchoType;
    }

    public final int getAef2MicFFTCalculateFrequency() {
        return this.aef2MicFFTCalculateFrequency;
    }

    public final int getAef2MicFFTCount() {
        return this.aef2MicFFTCount;
    }

    public final int getAef2MusicEQEnable() {
        return this.aef2MusicEQEnable;
    }

    public final int getAef2MusicFFTCalculateFrequency() {
        return this.aef2MusicFFTCalculateFrequency;
    }

    public final int getAef2MusicFFTCount() {
        return this.aef2MusicFFTCount;
    }

    public final int getAef2NoiseGatePopRemove() {
        return this.aef2NoiseGatePopRemove;
    }

    public final Integer getAef2ReverbEnable() {
        return this.aef2ReverbEnable;
    }

    public final Integer getAef2ReverbTime() {
        return this.aef2ReverbTime;
    }

    public final Integer getAef2ReverbType() {
        return this.aef2ReverbType;
    }

    public final Integer getAef2ReverbVolume() {
        return this.aef2ReverbVolume;
    }

    public final Integer getAef2VoiceChangeEnable() {
        return this.aef2VoiceChangeEnable;
    }

    public final Integer getAef2VoiceMode() {
        return this.aef2VoiceMode;
    }

    public final Integer getAef3EchoHicut() {
        return this.aef3EchoHicut;
    }

    public final Integer getAef3EchoLowcut() {
        return this.aef3EchoLowcut;
    }

    public final Integer getAef3EchoStwide() {
        return this.aef3EchoStwide;
    }

    public final Integer getAef3ReverbHicut() {
        return this.aef3ReverbHicut;
    }

    public final Integer getAef3ReverbHidamp() {
        return this.aef3ReverbHidamp;
    }

    public final Integer getAef3ReverbLowcut() {
        return this.aef3ReverbLowcut;
    }

    public final Integer getAef3ReverbStwide() {
        return this.aef3ReverbStwide;
    }

    public final int getAudioRecord() {
        return this.audioRecord;
    }

    public final int getAudioTrack() {
        return this.audioTrack;
    }

    public final double[] getBGMsysVolcurveX() {
        return this.BGMsysVolcurveX;
    }

    public final double[] getBGMsysVolcurveY() {
        return this.BGMsysVolcurveY;
    }

    public final int getBalanceVolume() {
        return this.balanceVolume;
    }

    public final int getBeta() {
        return this.beta;
    }

    public final int getCaptureAvailMin() {
        return this.captureAvailMin;
    }

    public final int getCaptureChannel() {
        return this.captureChannel;
    }

    public final int getCaptureFormat() {
        return this.captureFormat;
    }

    public final int getCapturePeriodCount() {
        return this.capturePeriodCount;
    }

    public final int getCapturePeriodSize() {
        return this.capturePeriodSize;
    }

    public final int getCaptureRate() {
        return this.captureRate;
    }

    public final int getCaptureSilenceSize() {
        return this.captureSilenceSize;
    }

    public final int getCaptureSilenceThreshold() {
        return this.captureSilenceThreshold;
    }

    public final int getCaptureStartThreshold() {
        return this.captureStartThreshold;
    }

    public final int getCaptureStopThreshold() {
        return this.captureStopThreshold;
    }

    public final long getChannel() {
        return this.channel;
    }

    public final int getCheckDelayIntervalCapture() {
        return this.checkDelayIntervalCapture;
    }

    public final int getCheckDelayIntervalPlayback() {
        return this.checkDelayIntervalPlayback;
    }

    public final int getConfigVersion() {
        return this.configVersion;
    }

    public final int getDataPrintFrequency() {
        return this.dataPrintFrequency;
    }

    public final Integer getDebugPlaySinEnable() {
        return this.debugPlaySinEnable;
    }

    public final int getDelayAddition() {
        return this.delayAddition;
    }

    public final int getDelayFluentThresholdCapture() {
        return this.delayFluentThresholdCapture;
    }

    public final int getDelayFluentThresholdPlayback() {
        return this.delayFluentThresholdPlayback;
    }

    public final int getDmaStopThreshold() {
        return this.dmaStopThreshold;
    }

    public final int getDownloadFirmwareSwitch() {
        return this.downloadFirmwareSwitch;
    }

    public final int getDynamicAdjustCompPregain() {
        return this.dynamicAdjustCompPregain;
    }

    public final int getEffectPeriod() {
        return this.effectPeriod;
    }

    public final List<EQItem> getEqList() {
        return this.eqList;
    }

    public final int getFsEnable() {
        return this.fsEnable;
    }

    public final HardEffect getHardEffect() {
        return this.hardEffect;
    }

    public final List<HardEffectDglItem> getHardEffectDglList() {
        return this.hardEffectDglList;
    }

    public final List<HardEffect> getHardEffectMicList() {
        return this.hardEffectMicList;
    }

    public final List<HardEffectDglItem> getHardEffectTVLiftList() {
        return this.hardEffectTVLiftList;
    }

    public final HardEffectVolume getHardEffectVolume() {
        return this.hardEffectVolume;
    }

    public final int getHardwareMicVolume() {
        return this.hardwareMicVolume;
    }

    public final int getJumpToMuteThresholdCapture() {
        return this.jumpToMuteThresholdCapture;
    }

    public final int getJumpToMuteThresholdPlayback() {
        return this.jumpToMuteThresholdPlayback;
    }

    public final int getKeepMuteCountCapture() {
        return this.keepMuteCountCapture;
    }

    public final int getKeepMuteCountPlayback() {
        return this.keepMuteCountPlayback;
    }

    public final int getKeyMode() {
        return this.keyMode;
    }

    public final int getListenMicElectricity() {
        return this.listenMicElectricity;
    }

    public final int getListenSystemVolume() {
        return this.listenSystemVolume;
    }

    public final String getLogLevel() {
        return this.logLevel;
    }

    public final int getMicLevelFollowSysVol() {
        return this.micLevelFollowSysVol;
    }

    public final double[] getMicVolcurveX() {
        return this.micVolcurveX;
    }

    public final double[] getMicVolcurveY() {
        return this.micVolcurveY;
    }

    public final int getMicVolume() {
        return this.micVolume;
    }

    public final int getMicVolumeStep() {
        return this.micVolumeStep;
    }

    public final int getMusicVolume() {
        return this.musicVolume;
    }

    public final int getMusicVolumeStep() {
        return this.musicVolumeStep;
    }

    public final int getNoiseGatePopRemoveAutoMuteThres() {
        return this.noiseGatePopRemoveAutoMuteThres;
    }

    public final int getNoiseGatePopRemoveAutoMuteTime() {
        return this.noiseGatePopRemoveAutoMuteTime;
    }

    public final int getNoiseGatePopRemoveAutoUnMuteTime() {
        return this.noiseGatePopRemoveAutoUnMuteTime;
    }

    public final int getNoiseGatePopRemovePopWidth() {
        return this.noiseGatePopRemovePopWidth;
    }

    public final int getNoiseGatePopRemoveRelease() {
        return this.noiseGatePopRemoveRelease;
    }

    public final int getNoiseGatePopRemoveThres() {
        return this.noiseGatePopRemoveThres;
    }

    public final int getNoiseSleepIntervalCapture() {
        return this.noiseSleepIntervalCapture;
    }

    public final int getNoiseSleepIntervalPlayback() {
        return this.noiseSleepIntervalPlayback;
    }

    public final int getPlaybackAvailMin() {
        return this.playbackAvailMin;
    }

    public final int getPlaybackChannel() {
        return this.playbackChannel;
    }

    public final int getPlaybackFormat() {
        return this.playbackFormat;
    }

    public final int getPlaybackPeriodCount() {
        return this.playbackPeriodCount;
    }

    public final int getPlaybackPeriodSize() {
        return this.playbackPeriodSize;
    }

    public final int getPlaybackRate() {
        return this.playbackRate;
    }

    public final int getPlaybackSilenceSize() {
        return this.playbackSilenceSize;
    }

    public final int getPlaybackSilenceThreshold() {
        return this.playbackSilenceThreshold;
    }

    public final int getPlaybackStartThreshold() {
        return this.playbackStartThreshold;
    }

    public final int getPlaybackStopThreshold() {
        return this.playbackStopThreshold;
    }

    public final int getPlaybackWaitSleepInterval() {
        return this.playbackWaitSleepInterval;
    }

    public final int getResampleBufferSize() {
        return this.resampleBufferSize;
    }

    public final int getReverbVolumeStep() {
        return this.reverbVolumeStep;
    }

    public final int getRingBufferLength() {
        return this.ringBufferLength;
    }

    public final int getRingStartThreshold() {
        return this.ringStartThreshold;
    }

    public final Integer getSysSupportHard() {
        return this.sysSupportHard;
    }

    public final int getSysVolCurveSwitch() {
        return this.sysVolCurveSwitch;
    }

    public final double[] getSysVolcurveX() {
        return this.sysVolcurveX;
    }

    public final double[] getSysVolcurveY() {
        return this.sysVolcurveY;
    }

    public final int getSystemVolume() {
        return this.systemVolume;
    }

    public final int getTwoMicCompPregainOffset() {
        return this.twoMicCompPregainOffset;
    }

    public final VocalRemove getVocalRemove() {
        return this.vocalRemove;
    }

    public final int getVolSft() {
        return this.volSft;
    }

    public final int getVolStr() {
        return this.volStr;
    }

    public final int[] getVolumeCurveMax() {
        return this.volumeCurveMax;
    }

    public final double[] getVolumeCurveSystem() {
        return this.volumeCurveSystem;
    }

    public final double[] getVolumeCurveUI() {
        return this.volumeCurveUI;
    }

    public final int getVolumeShiftL() {
        return this.volumeShiftL;
    }

    public final int getVolumeShiftR() {
        return this.volumeShiftR;
    }

    public final String getWorkMode() {
        return this.workMode;
    }

    public final int getWorkModeInt() {
        return this.workModeInt;
    }

    public final int isMicEnable() {
        return this.isMicEnable;
    }

    public final void setAef1ReverbEnable(Integer num) {
        this.aef1ReverbEnable = num;
    }

    public final void setAef1ReverbOutGain(Integer num) {
        this.aef1ReverbOutGain = num;
    }

    public final void setAef2CompressorAttack(Integer num) {
        this.aef2CompressorAttack = num;
    }

    public final void setAef2CompressorEnable(Integer num) {
        this.aef2CompressorEnable = num;
    }

    public final void setAef2CompressorPregain(Integer num) {
        this.aef2CompressorPregain = num;
    }

    public final void setAef2CompressorRatio(Integer num) {
        this.aef2CompressorRatio = num;
    }

    public final void setAef2CompressorRelease(Integer num) {
        this.aef2CompressorRelease = num;
    }

    public final void setAef2CompressorThreshold(Integer num) {
        this.aef2CompressorThreshold = num;
    }

    public final void setAef2EQEnable(Integer num) {
        this.aef2EQEnable = num;
    }

    public final void setAef2EchoEnable(Integer num) {
        this.aef2EchoEnable = num;
    }

    public final void setAef2EchoFeedback(Integer num) {
        this.aef2EchoFeedback = num;
    }

    public final void setAef2EchoGain(Integer num) {
        this.aef2EchoGain = num;
    }

    public final void setAef2EchoTime(Integer num) {
        this.aef2EchoTime = num;
    }

    public final void setAef2EchoType(Integer num) {
        this.aef2EchoType = num;
    }

    public final void setAef2MicFFTCalculateFrequency(int i) {
        this.aef2MicFFTCalculateFrequency = i;
    }

    public final void setAef2MicFFTCount(int i) {
        this.aef2MicFFTCount = i;
    }

    public final void setAef2MusicEQEnable(int i) {
        this.aef2MusicEQEnable = i;
    }

    public final void setAef2MusicFFTCalculateFrequency(int i) {
        this.aef2MusicFFTCalculateFrequency = i;
    }

    public final void setAef2MusicFFTCount(int i) {
        this.aef2MusicFFTCount = i;
    }

    public final void setAef2NoiseGatePopRemove(int i) {
        this.aef2NoiseGatePopRemove = i;
    }

    public final void setAef2ReverbEnable(Integer num) {
        this.aef2ReverbEnable = num;
    }

    public final void setAef2ReverbTime(Integer num) {
        this.aef2ReverbTime = num;
    }

    public final void setAef2ReverbType(Integer num) {
        this.aef2ReverbType = num;
    }

    public final void setAef2ReverbVolume(Integer num) {
        this.aef2ReverbVolume = num;
    }

    public final void setAef2VoiceChangeEnable(Integer num) {
        this.aef2VoiceChangeEnable = num;
    }

    public final void setAef2VoiceMode(Integer num) {
        this.aef2VoiceMode = num;
    }

    public final void setAef3EchoHicut(Integer num) {
        this.aef3EchoHicut = num;
    }

    public final void setAef3EchoLowcut(Integer num) {
        this.aef3EchoLowcut = num;
    }

    public final void setAef3EchoStwide(Integer num) {
        this.aef3EchoStwide = num;
    }

    public final void setAef3ReverbHicut(Integer num) {
        this.aef3ReverbHicut = num;
    }

    public final void setAef3ReverbHidamp(Integer num) {
        this.aef3ReverbHidamp = num;
    }

    public final void setAef3ReverbLowcut(Integer num) {
        this.aef3ReverbLowcut = num;
    }

    public final void setAef3ReverbStwide(Integer num) {
        this.aef3ReverbStwide = num;
    }

    public final void setAudioRecord(int i) {
        this.audioRecord = i;
    }

    public final void setAudioTrack(int i) {
        this.audioTrack = i;
    }

    public final void setBGMsysVolcurveX(double[] dArr) {
        h.d(dArr, "<set-?>");
        this.BGMsysVolcurveX = dArr;
    }

    public final void setBGMsysVolcurveY(double[] dArr) {
        h.d(dArr, "<set-?>");
        this.BGMsysVolcurveY = dArr;
    }

    public final void setBalanceVolume(int i) {
        this.balanceVolume = i;
    }

    public final void setBeta(int i) {
        this.beta = i;
    }

    public final void setCaptureAvailMin(int i) {
        this.captureAvailMin = i;
    }

    public final void setCaptureChannel(int i) {
        this.captureChannel = i;
    }

    public final void setCaptureFormat(int i) {
        this.captureFormat = i;
    }

    public final void setCapturePeriodCount(int i) {
        this.capturePeriodCount = i;
    }

    public final void setCapturePeriodSize(int i) {
        this.capturePeriodSize = i;
    }

    public final void setCaptureRate(int i) {
        this.captureRate = i;
    }

    public final void setCaptureSilenceSize(int i) {
        this.captureSilenceSize = i;
    }

    public final void setCaptureSilenceThreshold(int i) {
        this.captureSilenceThreshold = i;
    }

    public final void setCaptureStartThreshold(int i) {
        this.captureStartThreshold = i;
    }

    public final void setCaptureStopThreshold(int i) {
        this.captureStopThreshold = i;
    }

    public final void setChannel(long j) {
        this.channel = j;
    }

    public final void setCheckDelayIntervalCapture(int i) {
        this.checkDelayIntervalCapture = i;
    }

    public final void setCheckDelayIntervalPlayback(int i) {
        this.checkDelayIntervalPlayback = i;
    }

    public final void setConfigVersion(int i) {
        this.configVersion = i;
    }

    public final void setDataPrintFrequency(int i) {
        this.dataPrintFrequency = i;
    }

    public final void setDebugPlaySinEnable(Integer num) {
        this.debugPlaySinEnable = num;
    }

    public final void setDelayAddition(int i) {
        this.delayAddition = i;
    }

    public final void setDelayFluentThresholdCapture(int i) {
        this.delayFluentThresholdCapture = i;
    }

    public final void setDelayFluentThresholdPlayback(int i) {
        this.delayFluentThresholdPlayback = i;
    }

    public final void setDmaStopThreshold(int i) {
        this.dmaStopThreshold = i;
    }

    public final void setDownloadFirmwareSwitch(int i) {
        this.downloadFirmwareSwitch = i;
    }

    public final void setDynamicAdjustCompPregain(int i) {
        this.dynamicAdjustCompPregain = i;
    }

    public final void setEffectPeriod(int i) {
        this.effectPeriod = i;
    }

    public final void setEqList(List<EQItem> list) {
        h.d(list, "<set-?>");
        this.eqList = list;
    }

    public final void setFsEnable(int i) {
        this.fsEnable = i;
    }

    public final void setHardEffect(HardEffect hardEffect) {
        this.hardEffect = hardEffect;
    }

    public final void setHardEffectDglList(List<HardEffectDglItem> list) {
        this.hardEffectDglList = list;
    }

    public final void setHardEffectMicList(List<HardEffect> list) {
        this.hardEffectMicList = list;
    }

    public final void setHardEffectTVLiftList(List<HardEffectDglItem> list) {
        this.hardEffectTVLiftList = list;
    }

    public final void setHardEffectVolume(HardEffectVolume hardEffectVolume) {
        this.hardEffectVolume = hardEffectVolume;
    }

    public final void setHardwareMicVolume(int i) {
        this.hardwareMicVolume = i;
    }

    public final void setJumpToMuteThresholdCapture(int i) {
        this.jumpToMuteThresholdCapture = i;
    }

    public final void setJumpToMuteThresholdPlayback(int i) {
        this.jumpToMuteThresholdPlayback = i;
    }

    public final void setKeepMuteCountCapture(int i) {
        this.keepMuteCountCapture = i;
    }

    public final void setKeepMuteCountPlayback(int i) {
        this.keepMuteCountPlayback = i;
    }

    public final void setKeyMode(int i) {
        this.keyMode = i;
    }

    public final void setListenMicElectricity(int i) {
        this.listenMicElectricity = i;
    }

    public final void setListenSystemVolume(int i) {
        this.listenSystemVolume = i;
    }

    public final void setLogLevel(String str) {
        h.d(str, "<set-?>");
        this.logLevel = str;
    }

    public final void setMicEnable(int i) {
        this.isMicEnable = i;
    }

    public final void setMicLevelFollowSysVol(int i) {
        this.micLevelFollowSysVol = i;
    }

    public final void setMicVolcurveX(double[] dArr) {
        h.d(dArr, "<set-?>");
        this.micVolcurveX = dArr;
    }

    public final void setMicVolcurveY(double[] dArr) {
        h.d(dArr, "<set-?>");
        this.micVolcurveY = dArr;
    }

    public final void setMicVolume(int i) {
        this.micVolume = i;
    }

    public final void setMicVolumeStep(int i) {
        this.micVolumeStep = i;
    }

    public final void setMusicVolume(int i) {
        this.musicVolume = i;
    }

    public final void setMusicVolumeStep(int i) {
        this.musicVolumeStep = i;
    }

    public final void setNoiseGatePopRemoveAutoMuteThres(int i) {
        this.noiseGatePopRemoveAutoMuteThres = i;
    }

    public final void setNoiseGatePopRemoveAutoMuteTime(int i) {
        this.noiseGatePopRemoveAutoMuteTime = i;
    }

    public final void setNoiseGatePopRemoveAutoUnMuteTime(int i) {
        this.noiseGatePopRemoveAutoUnMuteTime = i;
    }

    public final void setNoiseGatePopRemovePopWidth(int i) {
        this.noiseGatePopRemovePopWidth = i;
    }

    public final void setNoiseGatePopRemoveRelease(int i) {
        this.noiseGatePopRemoveRelease = i;
    }

    public final void setNoiseGatePopRemoveThres(int i) {
        this.noiseGatePopRemoveThres = i;
    }

    public final void setNoiseSleepIntervalCapture(int i) {
        this.noiseSleepIntervalCapture = i;
    }

    public final void setNoiseSleepIntervalPlayback(int i) {
        this.noiseSleepIntervalPlayback = i;
    }

    public final void setPlaybackAvailMin(int i) {
        this.playbackAvailMin = i;
    }

    public final void setPlaybackChannel(int i) {
        this.playbackChannel = i;
    }

    public final void setPlaybackFormat(int i) {
        this.playbackFormat = i;
    }

    public final void setPlaybackPeriodCount(int i) {
        this.playbackPeriodCount = i;
    }

    public final void setPlaybackPeriodSize(int i) {
        this.playbackPeriodSize = i;
    }

    public final void setPlaybackRate(int i) {
        this.playbackRate = i;
    }

    public final void setPlaybackSilenceSize(int i) {
        this.playbackSilenceSize = i;
    }

    public final void setPlaybackSilenceThreshold(int i) {
        this.playbackSilenceThreshold = i;
    }

    public final void setPlaybackStartThreshold(int i) {
        this.playbackStartThreshold = i;
    }

    public final void setPlaybackStopThreshold(int i) {
        this.playbackStopThreshold = i;
    }

    public final void setPlaybackWaitSleepInterval(int i) {
        this.playbackWaitSleepInterval = i;
    }

    public final void setResampleBufferSize(int i) {
        this.resampleBufferSize = i;
    }

    public final void setReverbVolumeStep(int i) {
        this.reverbVolumeStep = i;
    }

    public final void setRingBufferLength(int i) {
        this.ringBufferLength = i;
    }

    public final void setRingStartThreshold(int i) {
        this.ringStartThreshold = i;
    }

    public final void setSysSupportHard(Integer num) {
        this.sysSupportHard = num;
    }

    public final void setSysVolCurveSwitch(int i) {
        this.sysVolCurveSwitch = i;
    }

    public final void setSysVolcurveX(double[] dArr) {
        h.d(dArr, "<set-?>");
        this.sysVolcurveX = dArr;
    }

    public final void setSysVolcurveY(double[] dArr) {
        h.d(dArr, "<set-?>");
        this.sysVolcurveY = dArr;
    }

    public final void setSystemVolume(int i) {
        this.systemVolume = i;
    }

    public final void setTwoMicCompPregainOffset(int i) {
        this.twoMicCompPregainOffset = i;
    }

    public final void setVocalRemove(VocalRemove vocalRemove) {
        this.vocalRemove = vocalRemove;
    }

    public final void setVolSft(int i) {
        this.volSft = i;
    }

    public final void setVolStr(int i) {
        this.volStr = i;
    }

    public final void setVolumeCurveMax(int[] iArr) {
        h.d(iArr, "<set-?>");
        this.volumeCurveMax = iArr;
    }

    public final void setVolumeCurveSystem(double[] dArr) {
        h.d(dArr, "<set-?>");
        this.volumeCurveSystem = dArr;
    }

    public final void setVolumeCurveUI(double[] dArr) {
        h.d(dArr, "<set-?>");
        this.volumeCurveUI = dArr;
    }

    public final void setVolumeShiftL(int i) {
        this.volumeShiftL = i;
    }

    public final void setVolumeShiftR(int i) {
        this.volumeShiftR = i;
    }

    public final void setWorkMode(String str) {
        h.d(str, "<set-?>");
        this.workMode = str;
    }

    public final void setWorkModeInt(int i) {
        this.workModeInt = i;
    }
}
