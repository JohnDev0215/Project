package com.spotify.cosmos.util.policy.proto;

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
public final class EpisodePlayedStateDecorationPolicy extends i implements EpisodePlayedStateDecorationPolicyOrBuilder {
    private static final EpisodePlayedStateDecorationPolicy DEFAULT_INSTANCE;
    public static final int IS_PLAYED_FIELD_NUMBER = 2;
    public static final int LAST_PLAYED_AT_FIELD_NUMBER = 5;
    private static volatile ds1 PARSER = null;
    public static final int PLAYABILITY_RESTRICTION_FIELD_NUMBER = 4;
    public static final int PLAYABLE_FIELD_NUMBER = 3;
    public static final int TIME_LEFT_FIELD_NUMBER = 1;
    private boolean isPlayed_;
    private boolean lastPlayedAt_;
    private boolean playabilityRestriction_;
    private boolean playable_;
    private boolean timeLeft_;

    /* JADX INFO: renamed from: com.spotify.cosmos.util.policy.proto.EpisodePlayedStateDecorationPolicy$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements EpisodePlayedStateDecorationPolicyOrBuilder {
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

        public Builder clearIsPlayed() {
            copyOnWrite();
            ((EpisodePlayedStateDecorationPolicy) this.instance).clearIsPlayed();
            return this;
        }

        public Builder clearLastPlayedAt() {
            copyOnWrite();
            ((EpisodePlayedStateDecorationPolicy) this.instance).clearLastPlayedAt();
            return this;
        }

        public Builder clearPlayabilityRestriction() {
            copyOnWrite();
            ((EpisodePlayedStateDecorationPolicy) this.instance).clearPlayabilityRestriction();
            return this;
        }

        public Builder clearPlayable() {
            copyOnWrite();
            ((EpisodePlayedStateDecorationPolicy) this.instance).clearPlayable();
            return this;
        }

        public Builder clearTimeLeft() {
            copyOnWrite();
            ((EpisodePlayedStateDecorationPolicy) this.instance).clearTimeLeft();
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

        @Override // com.spotify.cosmos.util.policy.proto.EpisodePlayedStateDecorationPolicyOrBuilder
        public boolean getIsPlayed() {
            return ((EpisodePlayedStateDecorationPolicy) this.instance).getIsPlayed();
        }

        @Override // com.spotify.cosmos.util.policy.proto.EpisodePlayedStateDecorationPolicyOrBuilder
        public boolean getLastPlayedAt() {
            return ((EpisodePlayedStateDecorationPolicy) this.instance).getLastPlayedAt();
        }

        @Override // com.spotify.cosmos.util.policy.proto.EpisodePlayedStateDecorationPolicyOrBuilder
        public boolean getPlayabilityRestriction() {
            return ((EpisodePlayedStateDecorationPolicy) this.instance).getPlayabilityRestriction();
        }

        @Override // com.spotify.cosmos.util.policy.proto.EpisodePlayedStateDecorationPolicyOrBuilder
        public boolean getPlayable() {
            return ((EpisodePlayedStateDecorationPolicy) this.instance).getPlayable();
        }

        @Override // com.spotify.cosmos.util.policy.proto.EpisodePlayedStateDecorationPolicyOrBuilder
        public boolean getTimeLeft() {
            return ((EpisodePlayedStateDecorationPolicy) this.instance).getTimeLeft();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder setIsPlayed(boolean z) {
            copyOnWrite();
            ((EpisodePlayedStateDecorationPolicy) this.instance).setIsPlayed(z);
            return this;
        }

        public Builder setLastPlayedAt(boolean z) {
            copyOnWrite();
            ((EpisodePlayedStateDecorationPolicy) this.instance).setLastPlayedAt(z);
            return this;
        }

        public Builder setPlayabilityRestriction(boolean z) {
            copyOnWrite();
            ((EpisodePlayedStateDecorationPolicy) this.instance).setPlayabilityRestriction(z);
            return this;
        }

        public Builder setPlayable(boolean z) {
            copyOnWrite();
            ((EpisodePlayedStateDecorationPolicy) this.instance).setPlayable(z);
            return this;
        }

        public Builder setTimeLeft(boolean z) {
            copyOnWrite();
            ((EpisodePlayedStateDecorationPolicy) this.instance).setTimeLeft(z);
            return this;
        }

        private Builder() {
            super(EpisodePlayedStateDecorationPolicy.DEFAULT_INSTANCE);
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
        public /* bridge */ /* synthetic */ vj1 m220mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m221mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m222mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        EpisodePlayedStateDecorationPolicy episodePlayedStateDecorationPolicy = new EpisodePlayedStateDecorationPolicy();
        DEFAULT_INSTANCE = episodePlayedStateDecorationPolicy;
        i.registerDefaultInstance(EpisodePlayedStateDecorationPolicy.class, episodePlayedStateDecorationPolicy);
    }

    private EpisodePlayedStateDecorationPolicy() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsPlayed() {
        this.isPlayed_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLastPlayedAt() {
        this.lastPlayedAt_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPlayabilityRestriction() {
        this.playabilityRestriction_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPlayable() {
        this.playable_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTimeLeft() {
        this.timeLeft_ = false;
    }

    public static EpisodePlayedStateDecorationPolicy getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static EpisodePlayedStateDecorationPolicy parseDelimitedFrom(InputStream inputStream) {
        return (EpisodePlayedStateDecorationPolicy) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EpisodePlayedStateDecorationPolicy parseFrom(ByteBuffer byteBuffer) {
        return (EpisodePlayedStateDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsPlayed(boolean z) {
        this.isPlayed_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLastPlayedAt(boolean z) {
        this.lastPlayedAt_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayabilityRestriction(boolean z) {
        this.playabilityRestriction_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayable(boolean z) {
        this.playable_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTimeLeft(boolean z) {
        this.timeLeft_ = z;
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001\u0007\u0002\u0007\u0003\u0007\u0004\u0007\u0005\u0007", new Object[]{"timeLeft_", "isPlayed_", "playable_", "playabilityRestriction_", "lastPlayedAt_"});
            case 3:
                return new EpisodePlayedStateDecorationPolicy();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EpisodePlayedStateDecorationPolicy.class) {
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

    @Override // com.spotify.cosmos.util.policy.proto.EpisodePlayedStateDecorationPolicyOrBuilder
    public boolean getIsPlayed() {
        return this.isPlayed_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.EpisodePlayedStateDecorationPolicyOrBuilder
    public boolean getLastPlayedAt() {
        return this.lastPlayedAt_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.EpisodePlayedStateDecorationPolicyOrBuilder
    public boolean getPlayabilityRestriction() {
        return this.playabilityRestriction_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.EpisodePlayedStateDecorationPolicyOrBuilder
    public boolean getPlayable() {
        return this.playable_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.EpisodePlayedStateDecorationPolicyOrBuilder
    public boolean getTimeLeft() {
        return this.timeLeft_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(EpisodePlayedStateDecorationPolicy episodePlayedStateDecorationPolicy) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(episodePlayedStateDecorationPolicy);
    }

    public static EpisodePlayedStateDecorationPolicy parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (EpisodePlayedStateDecorationPolicy) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static EpisodePlayedStateDecorationPolicy parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (EpisodePlayedStateDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static EpisodePlayedStateDecorationPolicy parseFrom(ByteString byteString) {
        return (EpisodePlayedStateDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static EpisodePlayedStateDecorationPolicy parseFrom(ByteString byteString, rl0 rl0Var) {
        return (EpisodePlayedStateDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static EpisodePlayedStateDecorationPolicy parseFrom(byte[] bArr) {
        return (EpisodePlayedStateDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static EpisodePlayedStateDecorationPolicy parseFrom(byte[] bArr, rl0 rl0Var) {
        return (EpisodePlayedStateDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static EpisodePlayedStateDecorationPolicy parseFrom(InputStream inputStream) {
        return (EpisodePlayedStateDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EpisodePlayedStateDecorationPolicy parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (EpisodePlayedStateDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static EpisodePlayedStateDecorationPolicy parseFrom(au auVar) {
        return (EpisodePlayedStateDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static EpisodePlayedStateDecorationPolicy parseFrom(au auVar, rl0 rl0Var) {
        return (EpisodePlayedStateDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
