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
import p.r1;
import p.rl0;
import p.vj1;
import p.wj1;

/* JADX INFO: loaded from: classes.dex */
public final class EpisodePlayState extends i implements EpisodePlayStateOrBuilder {
    private static final EpisodePlayState DEFAULT_INSTANCE;
    public static final int IS_PLAYABLE_FIELD_NUMBER = 2;
    public static final int IS_PLAYED_FIELD_NUMBER = 3;
    public static final int LAST_PLAYED_AT_FIELD_NUMBER = 4;
    private static volatile ds1 PARSER = null;
    public static final int PLAYABILITY_RESTRICTION_FIELD_NUMBER = 5;
    public static final int TIME_LEFT_FIELD_NUMBER = 1;
    private int bitField0_;
    private boolean isPlayable_;
    private boolean isPlayed_;
    private int lastPlayedAt_;
    private int playabilityRestriction_;
    private int timeLeft_;

    /* JADX INFO: renamed from: com.spotify.cosmos.util.proto.EpisodePlayState$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements EpisodePlayStateOrBuilder {
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
            ((EpisodePlayState) this.instance).clearIsPlayable();
            return this;
        }

        public Builder clearIsPlayed() {
            copyOnWrite();
            ((EpisodePlayState) this.instance).clearIsPlayed();
            return this;
        }

        public Builder clearLastPlayedAt() {
            copyOnWrite();
            ((EpisodePlayState) this.instance).clearLastPlayedAt();
            return this;
        }

        public Builder clearPlayabilityRestriction() {
            copyOnWrite();
            ((EpisodePlayState) this.instance).clearPlayabilityRestriction();
            return this;
        }

        public Builder clearTimeLeft() {
            copyOnWrite();
            ((EpisodePlayState) this.instance).clearTimeLeft();
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

        @Override // com.spotify.cosmos.util.proto.EpisodePlayStateOrBuilder
        public boolean getIsPlayable() {
            return ((EpisodePlayState) this.instance).getIsPlayable();
        }

        @Override // com.spotify.cosmos.util.proto.EpisodePlayStateOrBuilder
        public boolean getIsPlayed() {
            return ((EpisodePlayState) this.instance).getIsPlayed();
        }

        @Override // com.spotify.cosmos.util.proto.EpisodePlayStateOrBuilder
        public int getLastPlayedAt() {
            return ((EpisodePlayState) this.instance).getLastPlayedAt();
        }

        @Override // com.spotify.cosmos.util.proto.EpisodePlayStateOrBuilder
        public PlayabilityRestriction getPlayabilityRestriction() {
            return ((EpisodePlayState) this.instance).getPlayabilityRestriction();
        }

        @Override // com.spotify.cosmos.util.proto.EpisodePlayStateOrBuilder
        public int getTimeLeft() {
            return ((EpisodePlayState) this.instance).getTimeLeft();
        }

        @Override // com.spotify.cosmos.util.proto.EpisodePlayStateOrBuilder
        public boolean hasIsPlayable() {
            return ((EpisodePlayState) this.instance).hasIsPlayable();
        }

        @Override // com.spotify.cosmos.util.proto.EpisodePlayStateOrBuilder
        public boolean hasIsPlayed() {
            return ((EpisodePlayState) this.instance).hasIsPlayed();
        }

        @Override // com.spotify.cosmos.util.proto.EpisodePlayStateOrBuilder
        public boolean hasLastPlayedAt() {
            return ((EpisodePlayState) this.instance).hasLastPlayedAt();
        }

        @Override // com.spotify.cosmos.util.proto.EpisodePlayStateOrBuilder
        public boolean hasPlayabilityRestriction() {
            return ((EpisodePlayState) this.instance).hasPlayabilityRestriction();
        }

        @Override // com.spotify.cosmos.util.proto.EpisodePlayStateOrBuilder
        public boolean hasTimeLeft() {
            return ((EpisodePlayState) this.instance).hasTimeLeft();
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
            ((EpisodePlayState) this.instance).setIsPlayable(z);
            return this;
        }

        public Builder setIsPlayed(boolean z) {
            copyOnWrite();
            ((EpisodePlayState) this.instance).setIsPlayed(z);
            return this;
        }

        public Builder setLastPlayedAt(int i) {
            copyOnWrite();
            ((EpisodePlayState) this.instance).setLastPlayedAt(i);
            return this;
        }

        public Builder setPlayabilityRestriction(PlayabilityRestriction playabilityRestriction) {
            copyOnWrite();
            ((EpisodePlayState) this.instance).setPlayabilityRestriction(playabilityRestriction);
            return this;
        }

        public Builder setTimeLeft(int i) {
            copyOnWrite();
            ((EpisodePlayState) this.instance).setTimeLeft(i);
            return this;
        }

        private Builder() {
            super(EpisodePlayState.DEFAULT_INSTANCE);
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
        public /* bridge */ /* synthetic */ vj1 m273mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m274mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m275mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        EpisodePlayState episodePlayState = new EpisodePlayState();
        DEFAULT_INSTANCE = episodePlayState;
        i.registerDefaultInstance(EpisodePlayState.class, episodePlayState);
    }

    private EpisodePlayState() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsPlayable() {
        this.bitField0_ &= -3;
        this.isPlayable_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsPlayed() {
        this.bitField0_ &= -5;
        this.isPlayed_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLastPlayedAt() {
        this.bitField0_ &= -9;
        this.lastPlayedAt_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPlayabilityRestriction() {
        this.bitField0_ &= -17;
        this.playabilityRestriction_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTimeLeft() {
        this.bitField0_ &= -2;
        this.timeLeft_ = 0;
    }

    public static EpisodePlayState getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static EpisodePlayState parseDelimitedFrom(InputStream inputStream) {
        return (EpisodePlayState) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EpisodePlayState parseFrom(ByteBuffer byteBuffer) {
        return (EpisodePlayState) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsPlayable(boolean z) {
        this.bitField0_ |= 2;
        this.isPlayable_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsPlayed(boolean z) {
        this.bitField0_ |= 4;
        this.isPlayed_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLastPlayedAt(int i) {
        this.bitField0_ |= 8;
        this.lastPlayedAt_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayabilityRestriction(PlayabilityRestriction playabilityRestriction) {
        this.playabilityRestriction_ = playabilityRestriction.getNumber();
        this.bitField0_ |= 16;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTimeLeft(int i) {
        this.bitField0_ |= 1;
        this.timeLeft_ = i;
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဋ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004ဋ\u0003\u0005ဌ\u0004", new Object[]{"bitField0_", "timeLeft_", "isPlayable_", "isPlayed_", "lastPlayedAt_", "playabilityRestriction_", PlayabilityRestriction.internalGetVerifier()});
            case 3:
                return new EpisodePlayState();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EpisodePlayState.class) {
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

    @Override // com.spotify.cosmos.util.proto.EpisodePlayStateOrBuilder
    public boolean getIsPlayable() {
        return this.isPlayable_;
    }

    @Override // com.spotify.cosmos.util.proto.EpisodePlayStateOrBuilder
    public boolean getIsPlayed() {
        return this.isPlayed_;
    }

    @Override // com.spotify.cosmos.util.proto.EpisodePlayStateOrBuilder
    public int getLastPlayedAt() {
        return this.lastPlayedAt_;
    }

    @Override // com.spotify.cosmos.util.proto.EpisodePlayStateOrBuilder
    public PlayabilityRestriction getPlayabilityRestriction() {
        PlayabilityRestriction playabilityRestrictionForNumber = PlayabilityRestriction.forNumber(this.playabilityRestriction_);
        return playabilityRestrictionForNumber == null ? PlayabilityRestriction.UNKNOWN : playabilityRestrictionForNumber;
    }

    @Override // com.spotify.cosmos.util.proto.EpisodePlayStateOrBuilder
    public int getTimeLeft() {
        return this.timeLeft_;
    }

    @Override // com.spotify.cosmos.util.proto.EpisodePlayStateOrBuilder
    public boolean hasIsPlayable() {
        return (this.bitField0_ & 2) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.EpisodePlayStateOrBuilder
    public boolean hasIsPlayed() {
        return (this.bitField0_ & 4) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.EpisodePlayStateOrBuilder
    public boolean hasLastPlayedAt() {
        return (this.bitField0_ & 8) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.EpisodePlayStateOrBuilder
    public boolean hasPlayabilityRestriction() {
        return (this.bitField0_ & 16) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.EpisodePlayStateOrBuilder
    public boolean hasTimeLeft() {
        return (this.bitField0_ & 1) != 0;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(EpisodePlayState episodePlayState) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(episodePlayState);
    }

    public static EpisodePlayState parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (EpisodePlayState) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static EpisodePlayState parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (EpisodePlayState) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static EpisodePlayState parseFrom(ByteString byteString) {
        return (EpisodePlayState) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static EpisodePlayState parseFrom(ByteString byteString, rl0 rl0Var) {
        return (EpisodePlayState) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static EpisodePlayState parseFrom(byte[] bArr) {
        return (EpisodePlayState) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static EpisodePlayState parseFrom(byte[] bArr, rl0 rl0Var) {
        return (EpisodePlayState) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static EpisodePlayState parseFrom(InputStream inputStream) {
        return (EpisodePlayState) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EpisodePlayState parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (EpisodePlayState) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static EpisodePlayState parseFrom(au auVar) {
        return (EpisodePlayState) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static EpisodePlayState parseFrom(au auVar, rl0 rl0Var) {
        return (EpisodePlayState) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
