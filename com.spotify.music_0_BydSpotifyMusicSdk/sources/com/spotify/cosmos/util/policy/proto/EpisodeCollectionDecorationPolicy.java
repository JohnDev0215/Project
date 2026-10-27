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
public final class EpisodeCollectionDecorationPolicy extends i implements EpisodeCollectionDecorationPolicyOrBuilder {
    private static final EpisodeCollectionDecorationPolicy DEFAULT_INSTANCE;
    public static final int IS_FOLLOWING_SHOW_FIELD_NUMBER = 1;
    public static final int IS_IN_LISTEN_LATER_FIELD_NUMBER = 2;
    public static final int IS_NEW_FIELD_NUMBER = 3;
    private static volatile ds1 PARSER;
    private boolean isFollowingShow_;
    private boolean isInListenLater_;
    private boolean isNew_;

    /* JADX INFO: renamed from: com.spotify.cosmos.util.policy.proto.EpisodeCollectionDecorationPolicy$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements EpisodeCollectionDecorationPolicyOrBuilder {
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

        public Builder clearIsFollowingShow() {
            copyOnWrite();
            ((EpisodeCollectionDecorationPolicy) this.instance).clearIsFollowingShow();
            return this;
        }

        public Builder clearIsInListenLater() {
            copyOnWrite();
            ((EpisodeCollectionDecorationPolicy) this.instance).clearIsInListenLater();
            return this;
        }

        public Builder clearIsNew() {
            copyOnWrite();
            ((EpisodeCollectionDecorationPolicy) this.instance).clearIsNew();
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

        @Override // com.spotify.cosmos.util.policy.proto.EpisodeCollectionDecorationPolicyOrBuilder
        public boolean getIsFollowingShow() {
            return ((EpisodeCollectionDecorationPolicy) this.instance).getIsFollowingShow();
        }

        @Override // com.spotify.cosmos.util.policy.proto.EpisodeCollectionDecorationPolicyOrBuilder
        public boolean getIsInListenLater() {
            return ((EpisodeCollectionDecorationPolicy) this.instance).getIsInListenLater();
        }

        @Override // com.spotify.cosmos.util.policy.proto.EpisodeCollectionDecorationPolicyOrBuilder
        public boolean getIsNew() {
            return ((EpisodeCollectionDecorationPolicy) this.instance).getIsNew();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder setIsFollowingShow(boolean z) {
            copyOnWrite();
            ((EpisodeCollectionDecorationPolicy) this.instance).setIsFollowingShow(z);
            return this;
        }

        public Builder setIsInListenLater(boolean z) {
            copyOnWrite();
            ((EpisodeCollectionDecorationPolicy) this.instance).setIsInListenLater(z);
            return this;
        }

        public Builder setIsNew(boolean z) {
            copyOnWrite();
            ((EpisodeCollectionDecorationPolicy) this.instance).setIsNew(z);
            return this;
        }

        private Builder() {
            super(EpisodeCollectionDecorationPolicy.DEFAULT_INSTANCE);
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
        public /* bridge */ /* synthetic */ vj1 m214mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m215mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m216mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        EpisodeCollectionDecorationPolicy episodeCollectionDecorationPolicy = new EpisodeCollectionDecorationPolicy();
        DEFAULT_INSTANCE = episodeCollectionDecorationPolicy;
        i.registerDefaultInstance(EpisodeCollectionDecorationPolicy.class, episodeCollectionDecorationPolicy);
    }

    private EpisodeCollectionDecorationPolicy() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsFollowingShow() {
        this.isFollowingShow_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsInListenLater() {
        this.isInListenLater_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsNew() {
        this.isNew_ = false;
    }

    public static EpisodeCollectionDecorationPolicy getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static EpisodeCollectionDecorationPolicy parseDelimitedFrom(InputStream inputStream) {
        return (EpisodeCollectionDecorationPolicy) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EpisodeCollectionDecorationPolicy parseFrom(ByteBuffer byteBuffer) {
        return (EpisodeCollectionDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsFollowingShow(boolean z) {
        this.isFollowingShow_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsInListenLater(boolean z) {
        this.isInListenLater_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsNew(boolean z) {
        this.isNew_ = z;
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0007\u0002\u0007\u0003\u0007", new Object[]{"isFollowingShow_", "isInListenLater_", "isNew_"});
            case 3:
                return new EpisodeCollectionDecorationPolicy();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EpisodeCollectionDecorationPolicy.class) {
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

    @Override // com.spotify.cosmos.util.policy.proto.EpisodeCollectionDecorationPolicyOrBuilder
    public boolean getIsFollowingShow() {
        return this.isFollowingShow_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.EpisodeCollectionDecorationPolicyOrBuilder
    public boolean getIsInListenLater() {
        return this.isInListenLater_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.EpisodeCollectionDecorationPolicyOrBuilder
    public boolean getIsNew() {
        return this.isNew_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(EpisodeCollectionDecorationPolicy episodeCollectionDecorationPolicy) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(episodeCollectionDecorationPolicy);
    }

    public static EpisodeCollectionDecorationPolicy parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (EpisodeCollectionDecorationPolicy) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static EpisodeCollectionDecorationPolicy parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (EpisodeCollectionDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static EpisodeCollectionDecorationPolicy parseFrom(ByteString byteString) {
        return (EpisodeCollectionDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static EpisodeCollectionDecorationPolicy parseFrom(ByteString byteString, rl0 rl0Var) {
        return (EpisodeCollectionDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static EpisodeCollectionDecorationPolicy parseFrom(byte[] bArr) {
        return (EpisodeCollectionDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static EpisodeCollectionDecorationPolicy parseFrom(byte[] bArr, rl0 rl0Var) {
        return (EpisodeCollectionDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static EpisodeCollectionDecorationPolicy parseFrom(InputStream inputStream) {
        return (EpisodeCollectionDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EpisodeCollectionDecorationPolicy parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (EpisodeCollectionDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static EpisodeCollectionDecorationPolicy parseFrom(au auVar) {
        return (EpisodeCollectionDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static EpisodeCollectionDecorationPolicy parseFrom(au auVar, rl0 rl0Var) {
        return (EpisodeCollectionDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
