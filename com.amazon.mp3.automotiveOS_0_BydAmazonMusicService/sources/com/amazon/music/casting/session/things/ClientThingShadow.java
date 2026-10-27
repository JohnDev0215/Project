package com.amazon.music.casting.session.things;

import com.google.gson.annotations.Expose;

/* JADX INFO: loaded from: classes4.dex */
public class ClientThingShadow {

    @Expose
    private Metadata metadata;

    @Expose
    private State state;
    private Long timestamp;
    private Long version;

    private ClientThingShadow(State state) {
        this.state = state;
    }

    public State getState() {
        return this.state;
    }

    public Metadata getMetadata() {
        return this.metadata;
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

            @Expose
            private String connectTo;

            @Expose
            private String connectToName;

            @Expose
            private String disconnectFrom;

            @Expose
            private String disconnectReason;

            DesiredNode(String str, String str2, String str3, String str4) {
                this.disconnectFrom = str;
                this.disconnectReason = str2;
                this.connectTo = str3;
                this.connectToName = str4;
            }

            public String getDisconnectReason() {
                return this.disconnectReason;
            }

            public String getDisconnectFrom() {
                return this.disconnectFrom;
            }

            public String getConnectTo() {
                return this.connectTo;
            }

            public String getConnectToName() {
                return this.connectToName;
            }

            public static class Builder {

                @Expose
                private String connectTo;

                @Expose
                private String connectToName;

                @Expose
                private String disconnectFrom;

                @Expose
                private String disconnectReason;

                public Builder withDisconnectFrom(String str) {
                    this.disconnectFrom = str;
                    return this;
                }

                public Builder withDisconnectReason(String str) {
                    this.disconnectReason = str;
                    return this;
                }

                public Builder withConnectTo(String str) {
                    this.connectTo = str;
                    return this;
                }

                public Builder withConnectToName(String str) {
                    this.connectToName = str;
                    return this;
                }

                public DesiredNode build() {
                    return new DesiredNode(this.disconnectFrom, this.disconnectReason, this.connectTo, this.connectToName);
                }
            }

            public String toString() {
                return "Desired{disconnectFrom=" + this.disconnectFrom + ", disconnectReason=" + this.disconnectReason + ", connectTo=" + this.connectTo + ", connectToName=" + this.connectToName + '}';
            }
        }

        public static class ReportedNode {

            @Expose
            private Boolean abnormalDisconnect;

            @Expose
            private AllowedParentalControls allowedParentalControls;

            @Expose
            private String connectedTo;

            @Expose
            private Boolean isOnline;

            public static class AllowedParentalControls {

                @Expose
                public Boolean hasExplicitLanguage;

                public String toString() {
                    return "AllowedParentalControls{hasExplicitLanguage=" + this.hasExplicitLanguage + '}';
                }
            }

            ReportedNode(String str, Boolean bool, Boolean bool2, AllowedParentalControls allowedParentalControls) {
                this.connectedTo = str;
                this.isOnline = bool;
                this.abnormalDisconnect = bool2;
                this.allowedParentalControls = allowedParentalControls;
            }

            public String getConnectedTo() {
                return this.connectedTo;
            }

            public Boolean getIsOnline() {
                return this.isOnline;
            }

            public Boolean getAbnormalDisconnect() {
                return this.abnormalDisconnect;
            }

            public AllowedParentalControls getAllowedParentalControls() {
                return this.allowedParentalControls;
            }

            public static class Builder {

                @Expose
                private Boolean abnormalDisconnect;

                @Expose
                private AllowedParentalControls allowedParentalControls;

                @Expose
                private String connectedTo;

                @Expose
                private Boolean isOnline;

                public Builder withConnectedTo(String str) {
                    this.connectedTo = str;
                    return this;
                }

                public Builder withIsOnline(Boolean bool) {
                    this.isOnline = bool;
                    return this;
                }

                public Builder withAbnormalDisconnect(Boolean bool) {
                    this.abnormalDisconnect = bool;
                    return this;
                }

                public Builder withAllowedParentalControls(AllowedParentalControls allowedParentalControls) {
                    this.allowedParentalControls = allowedParentalControls;
                    return this;
                }

                public ReportedNode build() {
                    return new ReportedNode(this.connectedTo, this.isOnline, this.abnormalDisconnect, this.allowedParentalControls);
                }
            }

            public String toString() {
                return "Reported{connectedTo=" + this.connectedTo + ", isOnline=" + this.isOnline + ", abnormalDisconnect=" + this.abnormalDisconnect + ", allowedParentalControls=" + this.allowedParentalControls + '}';
            }
        }

        public String toString() {
            return "State{desired=" + this.desired + ", reported=" + this.reported + '}';
        }
    }

    public static class Metadata {

        @Expose
        public DesiredNode desired;

        @Expose
        public ReportedNode reported;

        Metadata(DesiredNode desiredNode, ReportedNode reportedNode) {
            this.desired = desiredNode;
            this.reported = reportedNode;
        }

        public static class Timestamp {

            @Expose
            public Long timestamp;

            public Long getTimestamp() {
                return this.timestamp;
            }

            public String toString() {
                return "{timestamp=" + this.timestamp + "}";
            }
        }

        public DesiredNode getDesiredNode() {
            return this.desired;
        }

        public ReportedNode getReportedNode() {
            return this.reported;
        }

        public static class DesiredNode {

            @Expose
            public Timestamp connectTo;

            @Expose
            public Timestamp connectToName;

            @Expose
            public Timestamp disconnectFrom;

            @Expose
            public Timestamp disconnectReason;

            DesiredNode(Timestamp timestamp, Timestamp timestamp2, Timestamp timestamp3, Timestamp timestamp4) {
                this.disconnectFrom = timestamp;
                this.disconnectReason = timestamp2;
                this.connectTo = timestamp3;
                this.connectToName = timestamp4;
            }

            public Timestamp getDisconnectReason() {
                return this.disconnectReason;
            }

            public Timestamp getDisconnectFrom() {
                return this.disconnectFrom;
            }

            public Timestamp getConnectTo() {
                return this.connectTo;
            }

            public Timestamp getConnectToName() {
                return this.connectToName;
            }

            public String toString() {
                return "Desired{disconnectFrom=" + this.disconnectFrom + ", disconnectReason=" + this.disconnectReason + ", connectTo=" + this.connectTo + ", connectToName=" + this.connectToName + '}';
            }
        }

        public static class ReportedNode {

            @Expose
            public Timestamp abnormalDisconnect;

            @Expose
            public Timestamp allowedParentalControls;

            @Expose
            public Timestamp connectedTo;

            @Expose
            public Timestamp isOnline;

            ReportedNode(Timestamp timestamp, Timestamp timestamp2, Timestamp timestamp3, Timestamp timestamp4) {
                this.connectedTo = timestamp;
                this.isOnline = timestamp2;
                this.allowedParentalControls = timestamp3;
                this.abnormalDisconnect = timestamp4;
            }

            public Timestamp getConnectedTo() {
                return this.connectedTo;
            }

            public Timestamp getIsOnline() {
                return this.isOnline;
            }

            public Timestamp getAllowedParentalControls() {
                return this.allowedParentalControls;
            }

            public Timestamp getAbnormalDisconnect() {
                return this.abnormalDisconnect;
            }

            public String toString() {
                return "Reported{connectedTo=" + this.connectedTo + ", isOnline=" + this.isOnline + ", allowedParentalControls=" + this.allowedParentalControls + ", abnormalDisconnect=" + this.abnormalDisconnect + '}';
            }
        }

        public String toString() {
            return "Metadata{desired=" + this.desired + ", reported=" + this.reported + '}';
        }
    }

    public String toString() {
        return "ClientThingShadow{state=" + this.state + ", metadata=" + this.metadata + ", version=" + this.version + ", timestamp=" + this.timestamp + '}';
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

        public ClientThingShadow build() {
            return new ClientThingShadow(new State(this.desired, this.reported));
        }
    }
}
