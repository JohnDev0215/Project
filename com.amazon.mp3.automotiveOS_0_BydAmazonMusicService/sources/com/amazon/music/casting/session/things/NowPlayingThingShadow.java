package com.amazon.music.casting.session.things;

import com.google.gson.annotations.Expose;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class NowPlayingThingShadow {

    @Expose
    private State state;
    private Long timestamp;
    private Long version;

    private NowPlayingThingShadow(State state) {
        this.state = state;
    }

    public State getState() {
        return this.state;
    }

    public Long getVersion() {
        return this.version;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public static class State {

        @Expose
        private DesiredNode desired;

        @Expose
        private ReportedNode reported;

        public enum LoopMode {
            LOOP_OFF,
            LOOP_ALL,
            LOOP_ONE
        }

        public enum PlayState {
            PLAY,
            PAUSE,
            STOP
        }

        public enum Rating {
            THUMBS_UP,
            NEUTRAL,
            THUMBS_DOWN
        }

        public enum Transport {
            PAUSE,
            NEXT,
            PREVIOUS,
            LOOP,
            SHUFFLE,
            RATINGS,
            VOLUME,
            VOLUME_DIRECTIONAL,
            SCRUB
        }

        public enum VolumeDirection {
            UP,
            DOWN
        }

        private State(DesiredNode desiredNode, ReportedNode reportedNode) {
            this.desired = desiredNode;
            this.reported = reportedNode;
        }

        public DesiredNode getDesiredNode() {
            return this.desired;
        }

        public ReportedNode getReportedNode() {
            return this.reported;
        }

        public static class DesiredNode {

            @Expose
            private LoopMode activeLoopMode;

            @Expose
            private Long activeLoopModeTimeStampInMs;

            @Expose
            private Boolean isShuffling;

            @Expose
            private Long isShufflingTimeStampInMs;

            @Expose
            private PlayState playState;

            @Expose
            private Long playStateTimeStampInMs;

            @Expose
            private TrackInfo trackInformation;

            @Expose
            private Transport transport;

            @Expose
            private Long transportTimeStampInMs;

            @Expose
            private Volume volume;

            public static class TrackInfo {

                @Expose
                public Long positionInMs;

                @Expose
                public Long positionInMsTimeStampInMs;

                public String toString() {
                    return "TrackInfo{positionInMs=" + this.positionInMs + "positionInMsTimeStampInMs=" + this.positionInMsTimeStampInMs + '}';
                }
            }

            public static class Volume {

                @Expose
                public VolumeDirection changeDirection;

                @Expose
                public String changeDirectionRequestId;

                @Expose
                public Long changeDirectionTimeStampInMs;

                @Expose
                public Float level;

                public String toString() {
                    return "Volume{level=" + this.level + "changeDirection=" + this.changeDirection + "changeDirectionRequestId=" + this.changeDirectionRequestId + "changeDirectionTimeStampInMs=" + this.changeDirectionTimeStampInMs + '}';
                }
            }

            DesiredNode(Transport transport, Long l, TrackInfo trackInfo, PlayState playState, Long l2, LoopMode loopMode, Long l3, Boolean bool, Long l4, Volume volume) {
                this.transport = transport;
                this.transportTimeStampInMs = l;
                this.trackInformation = trackInfo;
                this.playState = playState;
                this.playStateTimeStampInMs = l2;
                this.activeLoopMode = loopMode;
                this.activeLoopModeTimeStampInMs = l3;
                this.isShuffling = bool;
                this.isShufflingTimeStampInMs = l4;
                this.volume = volume;
            }

            public Transport getTransport() {
                return this.transport;
            }

            public long getTransportTimeStampInMs() {
                return this.transportTimeStampInMs.longValue();
            }

            public TrackInfo withTrackInformation() {
                return this.trackInformation;
            }

            public PlayState getPlayState() {
                return this.playState;
            }

            public Long getPlayStateTimeStampInMs() {
                return this.playStateTimeStampInMs;
            }

            public LoopMode getActiveLoopMode() {
                return this.activeLoopMode;
            }

            public Long getActiveLoopModeTimeStampInMs() {
                return this.activeLoopModeTimeStampInMs;
            }

            public Boolean isShuffling() {
                return this.isShuffling;
            }

            public Long getIsShufflingTimeStampInMs() {
                return this.isShufflingTimeStampInMs;
            }

            public Volume getVolume() {
                return this.volume;
            }

            public static class Builder {

                @Expose
                private LoopMode activeLoopMode;

                @Expose
                private Long activeLoopModeInMs;

                @Expose
                private Boolean isShuffling;

                @Expose
                private Long isShufflingTimeStampInMs;

                @Expose
                private PlayState playState;

                @Expose
                private Long playStateTimeStampInMs;

                @Expose
                private TrackInfo trackInformation;

                @Expose
                private Transport transport;

                @Expose
                private Long transportTimeStampInMs;

                @Expose
                private Volume volume;

                public Builder withTransport(Transport transport, Long l) {
                    this.transport = transport;
                    this.transportTimeStampInMs = l;
                    return this;
                }

                public Builder withTrackInformation(TrackInfo trackInfo, Long l) {
                    this.trackInformation = trackInfo;
                    trackInfo.positionInMsTimeStampInMs = l;
                    return this;
                }

                public Builder withPlayState(PlayState playState, Long l) {
                    this.playState = playState;
                    this.playStateTimeStampInMs = l;
                    return this;
                }

                public Builder withLoopMode(LoopMode loopMode, Long l) {
                    this.activeLoopMode = loopMode;
                    this.activeLoopModeInMs = l;
                    return this;
                }

                public Builder withIsShuffling(Boolean bool, Long l) {
                    this.isShuffling = bool;
                    this.isShufflingTimeStampInMs = l;
                    return this;
                }

                public Builder withVolumeLevel(Volume volume, Long l) {
                    this.volume = volume;
                    volume.changeDirectionTimeStampInMs = l;
                    return this;
                }

                public DesiredNode build() {
                    return new DesiredNode(this.transport, this.transportTimeStampInMs, this.trackInformation, this.playState, this.playStateTimeStampInMs, this.activeLoopMode, this.activeLoopModeInMs, this.isShuffling, this.isShufflingTimeStampInMs, this.volume);
                }
            }

            public String toString() {
                return "Desired{transport=" + this.transport + ", transportTimeStampInMs=" + this.transportTimeStampInMs + ", trackInformation=" + this.trackInformation + ", playState=" + this.playState + ", playStateTimeStampInMs=" + this.playStateTimeStampInMs + ", activeLoopMode=" + this.activeLoopMode + ", activeLoopModeTimeStampInMs=" + this.activeLoopModeTimeStampInMs + ", isShuffling=" + this.isShuffling + ", isShufflingTimeStampInMs=" + this.isShufflingTimeStampInMs + ", volume=" + this.volume + '}';
            }
        }

        public static class ReportedNode {

            @Expose
            private LoopMode activeLoopMode;

            @Expose
            private Transport[] availableFeatures;

            @Expose
            private LoopMode[] availableLoopModes;

            @Expose
            private Boolean isShuffling;

            @Expose
            private Long optimisticUpdateTimeStampInMs;

            @Expose
            private PlayState playState;

            @Expose
            private String queueId;

            @Expose
            private TrackInfo trackInformation;

            @Expose
            private Volume volume;

            public static class TrackInfo {

                @Expose
                public Long maximumPositionInMs;

                @Expose
                public Long positionInMs;

                @Expose
                public Long positionInMsTimeStamp;

                @Expose
                public String trackId;

                @Expose
                public Rating trackRating;

                public String toString() {
                    return "TrackInfo{trackId=" + this.trackId + ", positionInMs=" + this.positionInMs + ", positionInMsTimeStamp=" + this.positionInMsTimeStamp + ", maximumPositionInMs=" + this.maximumPositionInMs + ", trackRating=" + this.trackRating + '}';
                }
            }

            public static class Volume {

                @Expose
                public float level;

                @Expose
                public float max;

                @Expose
                public float min;

                @Expose
                public float stepSize;

                public String toString() {
                    return "Volume{level=" + this.level + ", min=" + this.min + ", max=" + this.max + ", stepSize=" + this.stepSize + '}';
                }
            }

            ReportedNode(TrackInfo trackInfo, PlayState playState, Transport[] transportArr, LoopMode[] loopModeArr, LoopMode loopMode, Boolean bool, Volume volume, String str, Long l) {
                this.trackInformation = trackInfo;
                this.playState = playState;
                this.availableFeatures = transportArr;
                this.availableLoopModes = loopModeArr;
                this.activeLoopMode = loopMode;
                this.isShuffling = bool;
                this.volume = volume;
                this.queueId = str;
                this.optimisticUpdateTimeStampInMs = l;
            }

            public TrackInfo getTrackInformation() {
                return this.trackInformation;
            }

            public PlayState getPlayState() {
                return this.playState;
            }

            public Transport[] getAvailableFeatures() {
                return this.availableFeatures;
            }

            public LoopMode[] getAvailableLoopModes() {
                return this.availableLoopModes;
            }

            public LoopMode getActiveLoopMode() {
                return this.activeLoopMode;
            }

            public Boolean getIsShuffling() {
                return this.isShuffling;
            }

            public Volume getVolume() {
                return this.volume;
            }

            public String getQueueId() {
                return this.queueId;
            }

            public Long getOptimisticUpdateTimeStampInMs() {
                return this.optimisticUpdateTimeStampInMs;
            }

            public static class Builder {

                @Expose
                private LoopMode activeLoopMode;

                @Expose
                private Transport[] availableFeatures;

                @Expose
                private LoopMode[] availableLoopModes;

                @Expose
                private Boolean isShuffling;

                @Expose
                private Long optimisticUpdateTimeStampInMs;

                @Expose
                private PlayState playState;

                @Expose
                private String queueId;

                @Expose
                private TrackInfo trackInformation;

                @Expose
                private Volume volume;

                public Builder withTrackInformation(TrackInfo trackInfo) {
                    this.trackInformation = trackInfo;
                    return this;
                }

                public Builder withPlayState(PlayState playState) {
                    this.playState = playState;
                    return this;
                }

                public Builder withAvailableFeatures(Transport[] transportArr) {
                    this.availableFeatures = transportArr;
                    return this;
                }

                public Builder withAvailableLoopModes(LoopMode[] loopModeArr) {
                    this.availableLoopModes = loopModeArr;
                    return this;
                }

                public Builder withActiveLoopMode(LoopMode loopMode) {
                    this.activeLoopMode = loopMode;
                    return this;
                }

                public Builder withIsShuffling(Boolean bool) {
                    this.isShuffling = bool;
                    return this;
                }

                public Builder withVolume(Volume volume) {
                    this.volume = volume;
                    return this;
                }

                public Builder withQueueId(String str) {
                    this.queueId = str;
                    return this;
                }

                public Builder withOptimisticUpdateTimeStampInMs(long j) {
                    this.optimisticUpdateTimeStampInMs = Long.valueOf(j);
                    return this;
                }

                public ReportedNode build() {
                    return new ReportedNode(this.trackInformation, this.playState, this.availableFeatures, this.availableLoopModes, this.activeLoopMode, this.isShuffling, this.volume, this.queueId, this.optimisticUpdateTimeStampInMs);
                }
            }

            public String toString() {
                return "Reported{trackInformation=" + this.trackInformation + ", playState=" + this.playState + ", availableFeatures=" + Arrays.toString(this.availableFeatures) + ", availableLoopModes=" + Arrays.toString(this.availableLoopModes) + ", activeLoopMode=" + this.activeLoopMode + ", isShuffling=" + this.isShuffling + ", volume=" + this.volume + ", queueId=" + this.queueId + ", optimisticUpdateTimeStampInMs=" + this.optimisticUpdateTimeStampInMs + '}';
            }
        }

        public String toString() {
            return "State{desired=" + this.desired + ", reported=" + this.reported + '}';
        }

        public static class Builder {

            @Expose
            private DesiredNode desired;

            @Expose
            private ReportedNode reported;

            public Builder withDesiredNode(DesiredNode desiredNode) {
                this.desired = desiredNode;
                return this;
            }

            public Builder withReportedNode(ReportedNode reportedNode) {
                this.reported = reportedNode;
                return this;
            }

            public State build() {
                return new State(this.desired, this.reported);
            }
        }
    }

    public static class Builder {

        @Expose
        private State.DesiredNode desired;

        @Expose
        private State.ReportedNode reported;

        public Builder withDesiredNode(State.DesiredNode desiredNode) {
            this.desired = desiredNode;
            return this;
        }

        public Builder withReportedNode(State.ReportedNode reportedNode) {
            this.reported = reportedNode;
            return this;
        }

        public NowPlayingThingShadow build() {
            return new NowPlayingThingShadow(new State(this.desired, this.reported));
        }
    }

    public String toString() {
        return "NowPlayingThingShadow{state=" + this.state + ", version=" + this.version + ", timestamp=" + this.timestamp + '}';
    }
}
