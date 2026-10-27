package com.amazon.music.casting.session.things;

import com.google.gson.annotations.Expose;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public class MetadataThingShadow {

    @Expose
    private final State state;
    private Long timestamp;
    private Long version;

    private MetadataThingShadow(State state) {
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

        State(DesiredNode desiredNode, ReportedNode reportedNode) {
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
            public String toString() {
                return "Desired{}";
            }

            DesiredNode() {
            }

            public static class Builder {
                public DesiredNode build() {
                    return new DesiredNode();
                }
            }
        }

        public static class ReportedNode {

            @Expose
            private final Track currentTrack;

            @Expose
            private final Track nextTrack;

            ReportedNode(Track track, Track track2) {
                this.currentTrack = track;
                this.nextTrack = track2;
            }

            public Track getCurrentTrack() {
                return this.currentTrack;
            }

            public Track getNextTrack() {
                return this.nextTrack;
            }

            public static class Builder {

                @Expose
                private Track currentTrack;

                @Expose
                private Track nextTrack;

                public Builder withCurrentTrack(Track track) {
                    this.currentTrack = track;
                    return this;
                }

                public Builder withNextTrack(Track track) {
                    this.nextTrack = track;
                    return this;
                }

                public ReportedNode build() {
                    return new ReportedNode(this.currentTrack, this.nextTrack);
                }
            }

            public String toString() {
                return "Reported{currentTrack=" + this.currentTrack + ", nextTrack=" + this.nextTrack + '}';
            }
        }

        public String toString() {
            return "State{desired=" + this.desired + ", reported=" + this.reported + '}';
        }
    }

    public String toString() {
        return "MetadataThingShadow{state=" + this.state + ", version=" + this.version + ", timestamp=" + this.timestamp + '}';
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

        public MetadataThingShadow build() {
            return new MetadataThingShadow(new State(this.desired, this.reported));
        }
    }

    public static class Track {

        @Expose
        public String albumArtistName;

        @Expose
        public Artist[] albumArtists;

        @Expose
        public String albumAsin;

        @Expose
        public String albumName;

        @Expose
        public HashMap<String, String> artUrlMap;

        @Expose
        public String artistName;

        @Expose
        public Artist[] artists;

        @Expose
        public String asin;

        @Expose
        public String bestAvailableQuality;

        @Expose
        public Boolean canRateTrack;

        @Expose
        public Boolean canScrubBackward;

        @Expose
        public Boolean canScrubForward;

        @Expose
        public Boolean canSkipBackward;

        @Expose
        public Boolean canSkipForward;

        @Expose
        public String cleanExplicit;

        @Expose
        public String customerId;

        @Expose
        public Long durationInMs;

        @Expose
        public Boolean hasLyrics;

        @Expose
        public String identifier;

        @Expose
        public String identifierType;

        @Expose
        public Boolean isSample;

        @Expose
        public String musicTerritoryOfResidence;

        @Expose
        public String name;

        @Expose
        public String playbackFormat;

        @Expose
        public String price;

        @Expose
        public QueueMetadata queueMetadata;

        @Expose
        public String rating;

        @Expose
        public String serviceTier;

        @Expose
        public String[] serviceTiers;

        @Expose
        public String trackId;

        public static class QueueMetadata {

            @Expose
            public String queueId;

            public String toString() {
                return "QueueMetadata{queueId=" + this.queueId + '}';
            }
        }

        public static class Artist {

            @Expose
            public String asin;

            @Expose
            public String contributorAsin;

            @Expose
            public String dmid;

            @Expose
            public String musicTerritoryOfResidence;

            @Expose
            public String name;

            @Expose
            public Role[] roles;

            public static class Role {

                @Expose
                public String sequenceNumber;

                @Expose
                public String type;

                public String toString() {
                    return "Role{sequenceNumber=" + this.sequenceNumber + ", type=" + this.type + "}";
                }
            }

            public String toString() {
                return "AlbumArtists{asin=" + this.asin + ", contributorAsin=" + this.contributorAsin + ", dmid=" + this.dmid + ", musicTerritoryOfResidence=" + this.musicTerritoryOfResidence + ", name=" + this.name + ", roles=" + Arrays.toString(this.roles) + '}';
            }
        }

        public String toString() {
            return "TrackMetadata{queueMetadata=" + this.queueMetadata + ", albumArtistName=" + this.albumArtistName + ", albumArtists=" + Arrays.toString(this.albumArtists) + ", albumAsin=" + this.albumAsin + ", albumName=" + this.albumName + ", artUrlMap=" + this.artUrlMap + ", artistName=" + this.artistName + ", artists=" + Arrays.toString(this.artists) + ", asin=" + this.asin + ", canRateTrack=" + this.canRateTrack + ", canScrubBackward=" + this.canScrubBackward + ", canScrubForward=" + this.canScrubForward + ", canSkipBackward=" + this.canSkipBackward + ", canSkipForward=" + this.canSkipForward + ", customerId=" + this.customerId + ", cleanExplicit=" + this.cleanExplicit + ", durationInMs=" + this.durationInMs + ", hasLyrics=" + this.hasLyrics + ", bestAvailableQuality = " + this.bestAvailableQuality + ", identifier=" + this.identifier + ", identifierType=" + this.identifierType + ", isSample=" + this.isSample + ", musicTerritoryOfResidence=" + this.musicTerritoryOfResidence + ", name=" + this.name + ", price=" + this.price + ", rating=" + this.rating + ", serviceTier=" + this.serviceTier + ", serviceTiers=" + Arrays.toString(this.serviceTiers) + ", trackId=" + this.trackId + ", playbackFormat=" + this.playbackFormat + '}';
        }
    }
}
