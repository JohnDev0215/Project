package com.google.android.projection.protocol;

import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class Radio {
    public static final int AM_HD_RADIO_TYPE = 2;
    public static final int AM_RADIO_TYPE = 0;
    public static final int FM_HD_RADIO_TYPE = 3;
    public static final int FM_RADIO_TYPE = 1;
    public static final int MAX_HD_SUBCHANNEL = 4;
    public static final int MIN_HD_SUBCHANNEL = 1;

    public interface RadioEndpoint {
        int sendActiveRadioResponse(int i, int i2, RadioStationInfo radioStationInfo);

        int sendCancelResponse(int i, int i2);

        int sendChannelSpacingResponse(int i, int i2, int i3);

        int sendMuteResponse(int i, int i2, boolean z);

        int sendProgramListResponse(int i, int i2, boolean z, List<RadioStationInfo> list);

        int sendRadioSourceResponse(int i, boolean z);

        int sendRadioStationInfoNotification(int i, RadioStationInfo radioStationInfo);

        int sendScanResponse(int i, int i2, boolean z);

        int sendSeekResponse(int i, int i2);

        int sendStationPresetsResponse(List<StationPresetList> list);

        int sendStepResponse(int i, int i2);

        int sendTrafficUpdateResponse(int i, int i2, List<TrafficIncident> list);

        int sendTuneResponse(int i, int i2);
    }

    public abstract int cancel(int i);

    public abstract int configureChannelSpacing(int i, int i2);

    public abstract int enableRadioSource();

    public abstract RadioState getCurrentRadioState();

    public abstract int getProgramList(int i);

    public abstract List<RadioProperties> getRadioProperties();

    public abstract int getTrafficUpdate(int i);

    public abstract int mute(int i, boolean z);

    public abstract int scan(int i, boolean z, boolean z2, boolean z3);

    public abstract int seek(int i, boolean z, boolean z2);

    public abstract int selectRadio(int i);

    public abstract void setRadioEndpoint(RadioEndpoint radioEndpoint);

    public abstract int step(int i, boolean z, boolean z2);

    public abstract int tune(int i, int i2, int i3);

    public static class RadioState {
        public int activeRadioId;
        public List<RadioStationInfo> programList;
        public boolean radioMuted;
        public boolean radioSourceEnabled;
        public RadioStationInfo stationInfo;
        public List<StationPresetList> stationPresetLists;

        public RadioState(boolean radioSourceEnabled, boolean radioMuted, int activeRadioId, RadioStationInfo stationInfo, List<RadioStationInfo> programList, List<StationPresetList> stationPresetLists) {
            this.radioSourceEnabled = radioSourceEnabled;
            this.radioMuted = radioMuted;
            this.activeRadioId = activeRadioId;
            this.stationInfo = stationInfo;
            this.programList = programList;
            this.stationPresetLists = stationPresetLists;
        }
    }

    public static class TrafficIncident {
        public int eventCode;
        public int expectedIncidentDuration;
        public double latitude;
        public double longitude;

        public TrafficIncident(int eventCode, int expectedIncidentDuration, double latitude, double longitude) {
            this.eventCode = eventCode;
            this.expectedIncidentDuration = expectedIncidentDuration;
            this.longitude = longitude;
            this.latitude = latitude;
        }
    }

    public static class StationPresetList {
        public String name;
        public List<StationPreset> presets;
        public List<Integer> restrictedStationTypes;

        public StationPresetList(String name, List<Integer> restrictedStationTypes, List<StationPreset> presets) {
            this.name = name;
            this.restrictedStationTypes = restrictedStationTypes;
            this.presets = presets;
        }
    }

    public static class StationPreset {
        public int channel;
        public int subChannel;
        public int type;

        public StationPreset(int type, int channel, int subChannel) {
            this.type = type;
            this.channel = channel;
            this.subChannel = subChannel;
        }
    }

    public static class RadioStationInfo {
        public int channel;
        public MetaData metaData;
        public int radioType;
        public int subChannel;

        public static class HdData {
        }

        public RadioStationInfo(int radioType, int channel, int subChannel) {
            this.radioType = radioType;
            this.channel = channel;
            this.subChannel = subChannel;
        }

        public RadioStationInfo(int radioType, int channel, int subChannel, MetaData metaData) {
            this(radioType, channel, subChannel);
            this.metaData = metaData;
        }

        public static class MetaData {
            public int audioChannels;
            public HdData hdData;
            public RdsData rdsData;
            public int signalQuality;

            private MetaData(int audioChannels, int signalQuality, RdsData rds, HdData hd) {
                this.audioChannels = audioChannels;
                this.signalQuality = signalQuality;
                this.rdsData = rds;
                this.hdData = hd;
            }

            public static class Builder {
                private HdData mHdData;
                private RdsData mRdsData;
                private int mAudioChannels = -1;
                private int mSignalQuality = -1;

                public Builder setAudioChannels(int audioChannels) {
                    this.mAudioChannels = audioChannels;
                    return this;
                }

                public Builder setSignalQuality(int signalQuality) {
                    this.mSignalQuality = signalQuality;
                    return this;
                }

                public Builder setRdsData(RdsData rdsData) {
                    this.mRdsData = rdsData;
                    return this;
                }

                public Builder setHdData(HdData hdData) {
                    this.mHdData = hdData;
                    return this;
                }

                public MetaData build() {
                    return new MetaData(this.mAudioChannels, this.mSignalQuality, this.mRdsData, this.mHdData);
                }
            }
        }

        public static class RdsData {
            public List<Integer> alternativeFrequencies;
            public int musicSpeechSwitch;
            public int programId;
            public String programServiceName;
            public int programType;
            public String programTypeName;
            public String radioText;
            public boolean trafficAnnouncementFlag;
            public boolean trafficProgramFlag;

            private RdsData(List<Integer> af, int pi, int ms, String psn, int pt, String ptn, String rt, boolean tpf, boolean taf) {
                this.alternativeFrequencies = af;
                this.programId = pi;
                this.musicSpeechSwitch = ms;
                this.programServiceName = psn;
                this.programType = pt;
                this.programTypeName = ptn;
                this.radioText = rt;
                this.trafficProgramFlag = tpf;
                this.trafficAnnouncementFlag = taf;
            }

            public static class Builder {
                private boolean mTrafficAnnouncmentFlag;
                private boolean mTrafficProgramFlag;
                private List<Integer> mAlternativeFrequencies = new ArrayList();
                private int mProgramId = -1;
                private int mMusicSpeechSwitch = -1;
                private String mProgramServiceName = AndroidAutoConst.DEFAULT_SCREEN_NAME;
                private int mProgramType = -1;
                private String mProgramTypeName = AndroidAutoConst.DEFAULT_SCREEN_NAME;
                private String mRadioText = AndroidAutoConst.DEFAULT_SCREEN_NAME;

                public Builder setAlternativeFrequencies(List<Integer> af) {
                    this.mAlternativeFrequencies.clear();
                    this.mAlternativeFrequencies.addAll(af);
                    return this;
                }

                public Builder setProgramId(int programId) {
                    this.mProgramId = programId;
                    return this;
                }

                public Builder setMusicSpeechSwitch(int musicSpeechSwitch) {
                    this.mMusicSpeechSwitch = musicSpeechSwitch;
                    return this;
                }

                public Builder setProgramServiceName(String programServiceName) {
                    this.mProgramServiceName = programServiceName;
                    return this;
                }

                public Builder setProgramType(int programType) {
                    this.mProgramType = programType;
                    return this;
                }

                public Builder setProgramTypeName(String programTypeName) {
                    this.mProgramTypeName = programTypeName;
                    return this;
                }

                public Builder setRadioText(String radioText) {
                    this.mRadioText = radioText;
                    return this;
                }

                public Builder setTrafficProgramFlag(boolean trafficProgramFlag) {
                    this.mTrafficProgramFlag = trafficProgramFlag;
                    return this;
                }

                public Builder setTrafficAnnoucementFlag(boolean trafficAnnouncmentFlag) {
                    this.mTrafficAnnouncmentFlag = trafficAnnouncmentFlag;
                    return this;
                }

                public RdsData build() {
                    return new RdsData(this.mAlternativeFrequencies, this.mProgramId, this.mMusicSpeechSwitch, this.mProgramServiceName, this.mProgramType, this.mProgramTypeName, this.mRadioText, this.mTrafficProgramFlag, this.mTrafficAnnouncmentFlag);
                }
            }
        }
    }

    public static class Range {
        public int max;
        public int min;

        public Range(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    public static class RadioProperties {
        public boolean afSwitch;
        public boolean audioLoopback;
        public boolean backgroundTuner;
        public List<Range> channelRange;
        public int channelSpacing;
        public List<Integer> channelSpacings;
        public boolean muteCapability;
        public int radioId;
        public int rds;
        public int region;
        public int stationPresetsAccess;
        public boolean ta;
        public int trafficService;
        public int type;

        public RadioProperties(int id, int type, List<Range> channelRange, List<Integer> channelSpacings, int channelSpacing, boolean backgroundTuner, int region, int rds, boolean afSwitch, boolean ta, int trafficService, boolean audioLoopback, boolean muteCapability, int stationPresetsAccess) {
            this.radioId = id;
            this.type = type;
            this.channelRange = channelRange;
            this.channelSpacings = channelSpacings;
            this.channelSpacing = channelSpacing;
            this.backgroundTuner = backgroundTuner;
            this.region = region;
            this.rds = rds;
            this.afSwitch = afSwitch;
            this.ta = ta;
            this.trafficService = trafficService;
            this.audioLoopback = audioLoopback;
            this.muteCapability = muteCapability;
            this.stationPresetsAccess = stationPresetsAccess;
        }

        public List<Range> getChannelRange() {
            return this.channelRange;
        }
    }
}
