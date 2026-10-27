package com.spotify.cosmos.util.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.a;
import com.google.protobuf.i;
import java.io.InputStream;
import java.nio.ByteBuffer;
import p.au;
import p.ds1;
import p.h01;
import p.i01;
import p.n51;
import p.o51;
import p.p51;
import p.r1;
import p.rl0;
import p.vj1;
import p.wj1;

/* JADX INFO: loaded from: classes.dex */
public final class ShowPlayState extends i implements ShowPlayStateOrBuilder {
    private static final ShowPlayState DEFAULT_INSTANCE;
    public static final int IS_PLAYABLE_FIELD_NUMBER = 3;
    public static final int LABEL_FIELD_NUMBER = 5;
    public static final int LATEST_PLAYED_EPISODE_LINK_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER = null;
    public static final int PLAYABILITY_RESTRICTION_FIELD_NUMBER = 4;
    public static final int PLAYED_PERCENTAGE_FIELD_NUMBER = 6;
    public static final int PLAYED_TIME_FIELD_NUMBER = 2;
    public static final int RESUME_EPISODE_LINK_FIELD_NUMBER = 7;
    private int bitField0_;
    private boolean isPlayable_;
    private int label_;
    private int playabilityRestriction_;
    private int playedPercentage_;
    private long playedTime_;
    private String latestPlayedEpisodeLink_ = "";
    private String resumeEpisodeLink_ = "";

    /* JADX INFO: renamed from: com.spotify.cosmos.util.proto.ShowPlayState$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite$MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[3] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[4] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[2] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[5] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[6] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[0] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[1] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static final class Builder extends h01 implements ShowPlayStateOrBuilder {
        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        public /* bridge */ /* synthetic */ wj1 build() {
            return super.build();
        }

        @Override // p.h01, p.vj1
        public /* bridge */ /* synthetic */ wj1 buildPartial() {
            return super.buildPartial();
        }

        public /* bridge */ /* synthetic */ vj1 clear() {
            return super.clear();
        }

        public Builder clearIsPlayable() {
            copyOnWrite();
            ((ShowPlayState) this.instance).clearIsPlayable();
            return this;
        }

        public Builder clearLabel() {
            copyOnWrite();
            ((ShowPlayState) this.instance).clearLabel();
            return this;
        }

        public Builder clearLatestPlayedEpisodeLink() {
            copyOnWrite();
            ((ShowPlayState) this.instance).clearLatestPlayedEpisodeLink();
            return this;
        }

        public Builder clearPlayabilityRestriction() {
            copyOnWrite();
            ((ShowPlayState) this.instance).clearPlayabilityRestriction();
            return this;
        }

        public Builder clearPlayedPercentage() {
            copyOnWrite();
            ((ShowPlayState) this.instance).clearPlayedPercentage();
            return this;
        }

        public Builder clearPlayedTime() {
            copyOnWrite();
            ((ShowPlayState) this.instance).clearPlayedTime();
            return this;
        }

        public Builder clearResumeEpisodeLink() {
            copyOnWrite();
            ((ShowPlayState) this.instance).clearResumeEpisodeLink();
            return this;
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ Object clone() {
            return super.clone();
        }

        @Override // p.h01, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
        public boolean getIsPlayable() {
            return ((ShowPlayState) this.instance).getIsPlayable();
        }

        @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
        public Label getLabel() {
            return ((ShowPlayState) this.instance).getLabel();
        }

        @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
        public String getLatestPlayedEpisodeLink() {
            return ((ShowPlayState) this.instance).getLatestPlayedEpisodeLink();
        }

        @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
        public ByteString getLatestPlayedEpisodeLinkBytes() {
            return ((ShowPlayState) this.instance).getLatestPlayedEpisodeLinkBytes();
        }

        @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
        public PlayabilityRestriction getPlayabilityRestriction() {
            return ((ShowPlayState) this.instance).getPlayabilityRestriction();
        }

        @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
        public int getPlayedPercentage() {
            return ((ShowPlayState) this.instance).getPlayedPercentage();
        }

        @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
        public long getPlayedTime() {
            return ((ShowPlayState) this.instance).getPlayedTime();
        }

        @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
        public String getResumeEpisodeLink() {
            return ((ShowPlayState) this.instance).getResumeEpisodeLink();
        }

        @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
        public ByteString getResumeEpisodeLinkBytes() {
            return ((ShowPlayState) this.instance).getResumeEpisodeLinkBytes();
        }

        @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
        public boolean hasIsPlayable() {
            return ((ShowPlayState) this.instance).hasIsPlayable();
        }

        @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
        public boolean hasLabel() {
            return ((ShowPlayState) this.instance).hasLabel();
        }

        @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
        public boolean hasLatestPlayedEpisodeLink() {
            return ((ShowPlayState) this.instance).hasLatestPlayedEpisodeLink();
        }

        @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
        public boolean hasPlayabilityRestriction() {
            return ((ShowPlayState) this.instance).hasPlayabilityRestriction();
        }

        @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
        public boolean hasPlayedPercentage() {
            return ((ShowPlayState) this.instance).hasPlayedPercentage();
        }

        @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
        public boolean hasPlayedTime() {
            return ((ShowPlayState) this.instance).hasPlayedTime();
        }

        @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
        public boolean hasResumeEpisodeLink() {
            return ((ShowPlayState) this.instance).hasResumeEpisodeLink();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder setIsPlayable(boolean z) {
            copyOnWrite();
            ((ShowPlayState) this.instance).setIsPlayable(z);
            return this;
        }

        public Builder setLabel(Label label) {
            copyOnWrite();
            ((ShowPlayState) this.instance).setLabel(label);
            return this;
        }

        public Builder setLatestPlayedEpisodeLink(String str) {
            copyOnWrite();
            ((ShowPlayState) this.instance).setLatestPlayedEpisodeLink(str);
            return this;
        }

        public Builder setLatestPlayedEpisodeLinkBytes(ByteString byteString) {
            copyOnWrite();
            ((ShowPlayState) this.instance).setLatestPlayedEpisodeLinkBytes(byteString);
            return this;
        }

        public Builder setPlayabilityRestriction(PlayabilityRestriction playabilityRestriction) {
            copyOnWrite();
            ((ShowPlayState) this.instance).setPlayabilityRestriction(playabilityRestriction);
            return this;
        }

        public Builder setPlayedPercentage(int i) {
            copyOnWrite();
            ((ShowPlayState) this.instance).setPlayedPercentage(i);
            return this;
        }

        public Builder setPlayedTime(long j) {
            copyOnWrite();
            ((ShowPlayState) this.instance).setPlayedTime(j);
            return this;
        }

        public Builder setResumeEpisodeLink(String str) {
            copyOnWrite();
            ((ShowPlayState) this.instance).setResumeEpisodeLink(str);
            return this;
        }

        public Builder setResumeEpisodeLinkBytes(ByteString byteString) {
            copyOnWrite();
            ((ShowPlayState) this.instance).setResumeEpisodeLinkBytes(byteString);
            return this;
        }

        private Builder() {
            super(ShowPlayState.DEFAULT_INSTANCE);
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ r1 clone() {
            return super.clone();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ vj1 clone() {
            return super.clone();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString) {
            return super.mergeFrom(byteString);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString, rl0 rl0Var) {
            return super.mergeFrom(byteString, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream) {
            return super.mergeFrom(inputStream);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream, rl0 rl0Var) {
            return super.mergeFrom(inputStream, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(au auVar) {
            return super.mergeFrom(auVar);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m292mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m293mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m294mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    public enum Label implements n51 {
        UNKNOWN_LABEL(0),
        NOT_STARTED(1),
        IN_PROGRESS(2),
        COMPLETED(3);

        public static final int COMPLETED_VALUE = 3;
        public static final int IN_PROGRESS_VALUE = 2;
        public static final int NOT_STARTED_VALUE = 1;
        public static final int UNKNOWN_LABEL_VALUE = 0;
        private static final o51 internalValueMap = new o51() { // from class: com.spotify.cosmos.util.proto.ShowPlayState.Label.1
            /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
            public Label m295findValueByNumber(int i) {
                return Label.forNumber(i);
            }
        };
        private final int value;

        public static final class LabelVerifier implements p51 {
            static final p51 INSTANCE = new LabelVerifier();

            private LabelVerifier() {
            }

            @Override // p.p51
            public boolean isInRange(int i) {
                return Label.forNumber(i) != null;
            }
        }

        Label(int i) {
            this.value = i;
        }

        public static Label forNumber(int i) {
            if (i == 0) {
                return UNKNOWN_LABEL;
            }
            if (i == 1) {
                return NOT_STARTED;
            }
            if (i == 2) {
                return IN_PROGRESS;
            }
            if (i != 3) {
                return null;
            }
            return COMPLETED;
        }

        public static o51 internalGetValueMap() {
            return internalValueMap;
        }

        public static p51 internalGetVerifier() {
            return LabelVerifier.INSTANCE;
        }

        @Override // p.n51
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Label valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        ShowPlayState showPlayState = new ShowPlayState();
        DEFAULT_INSTANCE = showPlayState;
        i.registerDefaultInstance(ShowPlayState.class, showPlayState);
    }

    private ShowPlayState() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsPlayable() {
        this.bitField0_ &= -5;
        this.isPlayable_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLabel() {
        this.bitField0_ &= -17;
        this.label_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLatestPlayedEpisodeLink() {
        this.bitField0_ &= -2;
        this.latestPlayedEpisodeLink_ = getDefaultInstance().getLatestPlayedEpisodeLink();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPlayabilityRestriction() {
        this.bitField0_ &= -9;
        this.playabilityRestriction_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPlayedPercentage() {
        this.bitField0_ &= -33;
        this.playedPercentage_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPlayedTime() {
        this.bitField0_ &= -3;
        this.playedTime_ = 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResumeEpisodeLink() {
        this.bitField0_ &= -65;
        this.resumeEpisodeLink_ = getDefaultInstance().getResumeEpisodeLink();
    }

    public static ShowPlayState getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static ShowPlayState parseDelimitedFrom(InputStream inputStream) {
        return (ShowPlayState) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ShowPlayState parseFrom(ByteBuffer byteBuffer) {
        return (ShowPlayState) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsPlayable(boolean z) {
        this.bitField0_ |= 4;
        this.isPlayable_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLabel(Label label) {
        this.label_ = label.getNumber();
        this.bitField0_ |= 16;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLatestPlayedEpisodeLink(String str) {
        str.getClass();
        this.bitField0_ |= 1;
        this.latestPlayedEpisodeLink_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLatestPlayedEpisodeLinkBytes(ByteString byteString) {
        this.latestPlayedEpisodeLink_ = byteString.s();
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayabilityRestriction(PlayabilityRestriction playabilityRestriction) {
        this.playabilityRestriction_ = playabilityRestriction.getNumber();
        this.bitField0_ |= 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayedPercentage(int i) {
        this.bitField0_ |= 32;
        this.playedPercentage_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayedTime(long j) {
        this.bitField0_ |= 2;
        this.playedTime_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResumeEpisodeLink(String str) {
        str.getClass();
        this.bitField0_ |= 64;
        this.resumeEpisodeLink_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResumeEpisodeLinkBytes(ByteString byteString) {
        this.resumeEpisodeLink_ = byteString.s();
        this.bitField0_ |= 64;
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        AnonymousClass1 anonymousClass1 = null;
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဃ\u0001\u0003ဇ\u0002\u0004ဌ\u0003\u0005ဌ\u0004\u0006ဋ\u0005\u0007ဈ\u0006", new Object[]{"bitField0_", "latestPlayedEpisodeLink_", "playedTime_", "isPlayable_", "playabilityRestriction_", PlayabilityRestriction.internalGetVerifier(), "label_", Label.internalGetVerifier(), "playedPercentage_", "resumeEpisodeLink_"});
            case 3:
                return new ShowPlayState();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (ShowPlayState.class) {
                        try {
                            i01Var = PARSER;
                            if (i01Var == null) {
                                i01Var = new i01(DEFAULT_INSTANCE);
                                PARSER = i01Var;
                            }
                        } finally {
                        }
                        break;
                    }
                }
                return i01Var;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // com.google.protobuf.i, p.yj1
    public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
    public boolean getIsPlayable() {
        return this.isPlayable_;
    }

    @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
    public Label getLabel() {
        Label labelForNumber = Label.forNumber(this.label_);
        return labelForNumber == null ? Label.UNKNOWN_LABEL : labelForNumber;
    }

    @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
    public String getLatestPlayedEpisodeLink() {
        return this.latestPlayedEpisodeLink_;
    }

    @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
    public ByteString getLatestPlayedEpisodeLinkBytes() {
        return ByteString.d(this.latestPlayedEpisodeLink_);
    }

    @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
    public PlayabilityRestriction getPlayabilityRestriction() {
        PlayabilityRestriction playabilityRestrictionForNumber = PlayabilityRestriction.forNumber(this.playabilityRestriction_);
        return playabilityRestrictionForNumber == null ? PlayabilityRestriction.UNKNOWN : playabilityRestrictionForNumber;
    }

    @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
    public int getPlayedPercentage() {
        return this.playedPercentage_;
    }

    @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
    public long getPlayedTime() {
        return this.playedTime_;
    }

    @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
    public String getResumeEpisodeLink() {
        return this.resumeEpisodeLink_;
    }

    @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
    public ByteString getResumeEpisodeLinkBytes() {
        return ByteString.d(this.resumeEpisodeLink_);
    }

    @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
    public boolean hasIsPlayable() {
        return (this.bitField0_ & 4) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
    public boolean hasLabel() {
        return (this.bitField0_ & 16) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
    public boolean hasLatestPlayedEpisodeLink() {
        return (this.bitField0_ & 1) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
    public boolean hasPlayabilityRestriction() {
        return (this.bitField0_ & 8) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
    public boolean hasPlayedPercentage() {
        return (this.bitField0_ & 32) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
    public boolean hasPlayedTime() {
        return (this.bitField0_ & 2) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ShowPlayStateOrBuilder
    public boolean hasResumeEpisodeLink() {
        return (this.bitField0_ & 64) != 0;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(ShowPlayState showPlayState) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(showPlayState);
    }

    public static ShowPlayState parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (ShowPlayState) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static ShowPlayState parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (ShowPlayState) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static ShowPlayState parseFrom(ByteString byteString) {
        return (ShowPlayState) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ShowPlayState parseFrom(ByteString byteString, rl0 rl0Var) {
        return (ShowPlayState) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static ShowPlayState parseFrom(byte[] bArr) {
        return (ShowPlayState) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ShowPlayState parseFrom(byte[] bArr, rl0 rl0Var) {
        return (ShowPlayState) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static ShowPlayState parseFrom(InputStream inputStream) {
        return (ShowPlayState) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ShowPlayState parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (ShowPlayState) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static ShowPlayState parseFrom(au auVar) {
        return (ShowPlayState) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static ShowPlayState parseFrom(au auVar, rl0 rl0Var) {
        return (ShowPlayState) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
