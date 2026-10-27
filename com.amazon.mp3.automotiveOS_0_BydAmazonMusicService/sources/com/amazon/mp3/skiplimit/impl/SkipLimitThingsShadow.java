package com.amazon.mp3.skiplimit.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SkipLimitThingsShadow.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/skiplimit/impl/SkipLimitThingsShadow;", "", "state", "Lcom/amazon/mp3/skiplimit/impl/SkipLimitThingsShadow$State;", "(Lcom/amazon/mp3/skiplimit/impl/SkipLimitThingsShadow$State;)V", "getState", "()Lcom/amazon/mp3/skiplimit/impl/SkipLimitThingsShadow$State;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "State", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SkipLimitThingsShadow {
    private final State state;

    public static /* synthetic */ SkipLimitThingsShadow copy$default(SkipLimitThingsShadow skipLimitThingsShadow, State state, int i, Object obj) {
        if ((i & 1) != 0) {
            state = skipLimitThingsShadow.state;
        }
        return skipLimitThingsShadow.copy(state);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final State getState() {
        return this.state;
    }

    public final SkipLimitThingsShadow copy(State state) {
        Intrinsics.checkNotNullParameter(state, "state");
        return new SkipLimitThingsShadow(state);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SkipLimitThingsShadow) && Intrinsics.areEqual(this.state, ((SkipLimitThingsShadow) other).state);
    }

    public int hashCode() {
        return this.state.hashCode();
    }

    public String toString() {
        return "SkipLimitThingsShadow(state=" + this.state + ')';
    }

    public SkipLimitThingsShadow(State state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.state = state;
    }

    public final State getState() {
        return this.state;
    }

    /* JADX INFO: compiled from: SkipLimitThingsShadow.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/skiplimit/impl/SkipLimitThingsShadow$State;", "", "reported", "Lcom/amazon/mp3/skiplimit/impl/SkipLimitThingsShadow$State$ReportedNode;", "(Lcom/amazon/mp3/skiplimit/impl/SkipLimitThingsShadow$State$ReportedNode;)V", "getReported", "()Lcom/amazon/mp3/skiplimit/impl/SkipLimitThingsShadow$State$ReportedNode;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "ReportedNode", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class State {
        private final ReportedNode reported;

        public static /* synthetic */ State copy$default(State state, ReportedNode reportedNode, int i, Object obj) {
            if ((i & 1) != 0) {
                reportedNode = state.reported;
            }
            return state.copy(reportedNode);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ReportedNode getReported() {
            return this.reported;
        }

        public final State copy(ReportedNode reported) {
            Intrinsics.checkNotNullParameter(reported, "reported");
            return new State(reported);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof State) && Intrinsics.areEqual(this.reported, ((State) other).reported);
        }

        public int hashCode() {
            return this.reported.hashCode();
        }

        public String toString() {
            return "State(reported=" + this.reported + ')';
        }

        public State(ReportedNode reported) {
            Intrinsics.checkNotNullParameter(reported, "reported");
            this.reported = reported;
        }

        public final ReportedNode getReported() {
            return this.reported;
        }

        /* JADX INFO: compiled from: SkipLimitThingsShadow.kt */
        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/skiplimit/impl/SkipLimitThingsShadow$State$ReportedNode;", "", "skips", "Lcom/amazon/mp3/skiplimit/impl/SkipLimitThingsShadow$State$ReportedNode$SkipNode;", "(Lcom/amazon/mp3/skiplimit/impl/SkipLimitThingsShadow$State$ReportedNode$SkipNode;)V", "getSkips", "()Lcom/amazon/mp3/skiplimit/impl/SkipLimitThingsShadow$State$ReportedNode$SkipNode;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "SkipNode", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public static final /* data */ class ReportedNode {
            private final SkipNode skips;

            public static /* synthetic */ ReportedNode copy$default(ReportedNode reportedNode, SkipNode skipNode, int i, Object obj) {
                if ((i & 1) != 0) {
                    skipNode = reportedNode.skips;
                }
                return reportedNode.copy(skipNode);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final SkipNode getSkips() {
                return this.skips;
            }

            public final ReportedNode copy(SkipNode skips) {
                Intrinsics.checkNotNullParameter(skips, "skips");
                return new ReportedNode(skips);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof ReportedNode) && Intrinsics.areEqual(this.skips, ((ReportedNode) other).skips);
            }

            public int hashCode() {
                return this.skips.hashCode();
            }

            public String toString() {
                return "ReportedNode(skips=" + this.skips + ')';
            }

            public ReportedNode(SkipNode skips) {
                Intrinsics.checkNotNullParameter(skips, "skips");
                this.skips = skips;
            }

            public final SkipNode getSkips() {
                return this.skips;
            }

            /* JADX INFO: compiled from: SkipLimitThingsShadow.kt */
            @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\u0003HÖ\u0001J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/skiplimit/impl/SkipLimitThingsShadow$State$ReportedNode$SkipNode;", "", "remaining", "", "(I)V", "getRemaining", "()I", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
            public static final /* data */ class SkipNode {
                private final int remaining;

                public static /* synthetic */ SkipNode copy$default(SkipNode skipNode, int i, int i2, Object obj) {
                    if ((i2 & 1) != 0) {
                        i = skipNode.remaining;
                    }
                    return skipNode.copy(i);
                }

                /* JADX INFO: renamed from: component1, reason: from getter */
                public final int getRemaining() {
                    return this.remaining;
                }

                public final SkipNode copy(int remaining) {
                    return new SkipNode(remaining);
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    return (other instanceof SkipNode) && this.remaining == ((SkipNode) other).remaining;
                }

                public int hashCode() {
                    return Integer.hashCode(this.remaining);
                }

                public String toString() {
                    return "SkipNode(remaining=" + this.remaining + ')';
                }

                public SkipNode(int i) {
                    this.remaining = i;
                }

                public final int getRemaining() {
                    return this.remaining;
                }
            }
        }
    }
}
