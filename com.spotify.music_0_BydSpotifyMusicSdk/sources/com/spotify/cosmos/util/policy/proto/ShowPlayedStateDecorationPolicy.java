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
public final class ShowPlayedStateDecorationPolicy extends i implements ShowPlayedStateDecorationPolicyOrBuilder {
    private static final ShowPlayedStateDecorationPolicy DEFAULT_INSTANCE;
    public static final int IS_PLAYABLE_FIELD_NUMBER = 3;
    public static final int LABEL_FIELD_NUMBER = 5;
    public static final int LATEST_PLAYED_EPISODE_LINK_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER = null;
    public static final int PLAYABILITY_RESTRICTION_FIELD_NUMBER = 4;
    public static final int PLAYED_TIME_FIELD_NUMBER = 2;
    public static final int RESUME_EPISODE_LINK_FIELD_NUMBER = 7;
    private boolean isPlayable_;
    private boolean label_;
    private boolean latestPlayedEpisodeLink_;
    private boolean playabilityRestriction_;
    private boolean playedTime_;
    private boolean resumeEpisodeLink_;

    /* JADX INFO: renamed from: com.spotify.cosmos.util.policy.proto.ShowPlayedStateDecorationPolicy$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements ShowPlayedStateDecorationPolicyOrBuilder {
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
            ((ShowPlayedStateDecorationPolicy) this.instance).clearIsPlayable();
            return this;
        }

        public Builder clearLabel() {
            copyOnWrite();
            ((ShowPlayedStateDecorationPolicy) this.instance).clearLabel();
            return this;
        }

        public Builder clearLatestPlayedEpisodeLink() {
            copyOnWrite();
            ((ShowPlayedStateDecorationPolicy) this.instance).clearLatestPlayedEpisodeLink();
            return this;
        }

        public Builder clearPlayabilityRestriction() {
            copyOnWrite();
            ((ShowPlayedStateDecorationPolicy) this.instance).clearPlayabilityRestriction();
            return this;
        }

        public Builder clearPlayedTime() {
            copyOnWrite();
            ((ShowPlayedStateDecorationPolicy) this.instance).clearPlayedTime();
            return this;
        }

        public Builder clearResumeEpisodeLink() {
            copyOnWrite();
            ((ShowPlayedStateDecorationPolicy) this.instance).clearResumeEpisodeLink();
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

        @Override // com.spotify.cosmos.util.policy.proto.ShowPlayedStateDecorationPolicyOrBuilder
        public boolean getIsPlayable() {
            return ((ShowPlayedStateDecorationPolicy) this.instance).getIsPlayable();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowPlayedStateDecorationPolicyOrBuilder
        public boolean getLabel() {
            return ((ShowPlayedStateDecorationPolicy) this.instance).getLabel();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowPlayedStateDecorationPolicyOrBuilder
        public boolean getLatestPlayedEpisodeLink() {
            return ((ShowPlayedStateDecorationPolicy) this.instance).getLatestPlayedEpisodeLink();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowPlayedStateDecorationPolicyOrBuilder
        public boolean getPlayabilityRestriction() {
            return ((ShowPlayedStateDecorationPolicy) this.instance).getPlayabilityRestriction();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowPlayedStateDecorationPolicyOrBuilder
        public boolean getPlayedTime() {
            return ((ShowPlayedStateDecorationPolicy) this.instance).getPlayedTime();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowPlayedStateDecorationPolicyOrBuilder
        public boolean getResumeEpisodeLink() {
            return ((ShowPlayedStateDecorationPolicy) this.instance).getResumeEpisodeLink();
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
            ((ShowPlayedStateDecorationPolicy) this.instance).setIsPlayable(z);
            return this;
        }

        public Builder setLabel(boolean z) {
            copyOnWrite();
            ((ShowPlayedStateDecorationPolicy) this.instance).setLabel(z);
            return this;
        }

        public Builder setLatestPlayedEpisodeLink(boolean z) {
            copyOnWrite();
            ((ShowPlayedStateDecorationPolicy) this.instance).setLatestPlayedEpisodeLink(z);
            return this;
        }

        public Builder setPlayabilityRestriction(boolean z) {
            copyOnWrite();
            ((ShowPlayedStateDecorationPolicy) this.instance).setPlayabilityRestriction(z);
            return this;
        }

        public Builder setPlayedTime(boolean z) {
            copyOnWrite();
            ((ShowPlayedStateDecorationPolicy) this.instance).setPlayedTime(z);
            return this;
        }

        public Builder setResumeEpisodeLink(boolean z) {
            copyOnWrite();
            ((ShowPlayedStateDecorationPolicy) this.instance).setResumeEpisodeLink(z);
            return this;
        }

        private Builder() {
            super(ShowPlayedStateDecorationPolicy.DEFAULT_INSTANCE);
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
        public /* bridge */ /* synthetic */ vj1 m235mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m236mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m237mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        ShowPlayedStateDecorationPolicy showPlayedStateDecorationPolicy = new ShowPlayedStateDecorationPolicy();
        DEFAULT_INSTANCE = showPlayedStateDecorationPolicy;
        i.registerDefaultInstance(ShowPlayedStateDecorationPolicy.class, showPlayedStateDecorationPolicy);
    }

    private ShowPlayedStateDecorationPolicy() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsPlayable() {
        this.isPlayable_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLabel() {
        this.label_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLatestPlayedEpisodeLink() {
        this.latestPlayedEpisodeLink_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPlayabilityRestriction() {
        this.playabilityRestriction_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPlayedTime() {
        this.playedTime_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResumeEpisodeLink() {
        this.resumeEpisodeLink_ = false;
    }

    public static ShowPlayedStateDecorationPolicy getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static ShowPlayedStateDecorationPolicy parseDelimitedFrom(InputStream inputStream) {
        return (ShowPlayedStateDecorationPolicy) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ShowPlayedStateDecorationPolicy parseFrom(ByteBuffer byteBuffer) {
        return (ShowPlayedStateDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsPlayable(boolean z) {
        this.isPlayable_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLabel(boolean z) {
        this.label_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLatestPlayedEpisodeLink(boolean z) {
        this.latestPlayedEpisodeLink_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayabilityRestriction(boolean z) {
        this.playabilityRestriction_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayedTime(boolean z) {
        this.playedTime_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResumeEpisodeLink(boolean z) {
        this.resumeEpisodeLink_ = z;
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0006\u0000\u0000\u0001\u0007\u0006\u0000\u0000\u0000\u0001\u0007\u0002\u0007\u0003\u0007\u0004\u0007\u0005\u0007\u0007\u0007", new Object[]{"latestPlayedEpisodeLink_", "playedTime_", "isPlayable_", "playabilityRestriction_", "label_", "resumeEpisodeLink_"});
            case 3:
                return new ShowPlayedStateDecorationPolicy();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (ShowPlayedStateDecorationPolicy.class) {
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

    @Override // com.spotify.cosmos.util.policy.proto.ShowPlayedStateDecorationPolicyOrBuilder
    public boolean getIsPlayable() {
        return this.isPlayable_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowPlayedStateDecorationPolicyOrBuilder
    public boolean getLabel() {
        return this.label_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowPlayedStateDecorationPolicyOrBuilder
    public boolean getLatestPlayedEpisodeLink() {
        return this.latestPlayedEpisodeLink_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowPlayedStateDecorationPolicyOrBuilder
    public boolean getPlayabilityRestriction() {
        return this.playabilityRestriction_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowPlayedStateDecorationPolicyOrBuilder
    public boolean getPlayedTime() {
        return this.playedTime_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowPlayedStateDecorationPolicyOrBuilder
    public boolean getResumeEpisodeLink() {
        return this.resumeEpisodeLink_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(ShowPlayedStateDecorationPolicy showPlayedStateDecorationPolicy) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(showPlayedStateDecorationPolicy);
    }

    public static ShowPlayedStateDecorationPolicy parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (ShowPlayedStateDecorationPolicy) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static ShowPlayedStateDecorationPolicy parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (ShowPlayedStateDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static ShowPlayedStateDecorationPolicy parseFrom(ByteString byteString) {
        return (ShowPlayedStateDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ShowPlayedStateDecorationPolicy parseFrom(ByteString byteString, rl0 rl0Var) {
        return (ShowPlayedStateDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static ShowPlayedStateDecorationPolicy parseFrom(byte[] bArr) {
        return (ShowPlayedStateDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ShowPlayedStateDecorationPolicy parseFrom(byte[] bArr, rl0 rl0Var) {
        return (ShowPlayedStateDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static ShowPlayedStateDecorationPolicy parseFrom(InputStream inputStream) {
        return (ShowPlayedStateDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ShowPlayedStateDecorationPolicy parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (ShowPlayedStateDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static ShowPlayedStateDecorationPolicy parseFrom(au auVar) {
        return (ShowPlayedStateDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static ShowPlayedStateDecorationPolicy parseFrom(au auVar, rl0 rl0Var) {
        return (ShowPlayedStateDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
