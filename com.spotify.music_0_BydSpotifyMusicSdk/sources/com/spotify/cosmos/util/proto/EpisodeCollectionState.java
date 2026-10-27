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
public final class EpisodeCollectionState extends i implements EpisodeCollectionStateOrBuilder {
    private static final EpisodeCollectionState DEFAULT_INSTANCE;
    public static final int IS_FOLLOWING_SHOW_FIELD_NUMBER = 1;
    public static final int IS_IN_LISTEN_LATER_FIELD_NUMBER = 3;
    public static final int IS_NEW_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER;
    private int bitField0_;
    private boolean isFollowingShow_;
    private boolean isInListenLater_;
    private boolean isNew_;

    /* JADX INFO: renamed from: com.spotify.cosmos.util.proto.EpisodeCollectionState$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements EpisodeCollectionStateOrBuilder {
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
            ((EpisodeCollectionState) this.instance).clearIsFollowingShow();
            return this;
        }

        public Builder clearIsInListenLater() {
            copyOnWrite();
            ((EpisodeCollectionState) this.instance).clearIsInListenLater();
            return this;
        }

        public Builder clearIsNew() {
            copyOnWrite();
            ((EpisodeCollectionState) this.instance).clearIsNew();
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

        @Override // com.spotify.cosmos.util.proto.EpisodeCollectionStateOrBuilder
        public boolean getIsFollowingShow() {
            return ((EpisodeCollectionState) this.instance).getIsFollowingShow();
        }

        @Override // com.spotify.cosmos.util.proto.EpisodeCollectionStateOrBuilder
        public boolean getIsInListenLater() {
            return ((EpisodeCollectionState) this.instance).getIsInListenLater();
        }

        @Override // com.spotify.cosmos.util.proto.EpisodeCollectionStateOrBuilder
        public boolean getIsNew() {
            return ((EpisodeCollectionState) this.instance).getIsNew();
        }

        @Override // com.spotify.cosmos.util.proto.EpisodeCollectionStateOrBuilder
        public boolean hasIsFollowingShow() {
            return ((EpisodeCollectionState) this.instance).hasIsFollowingShow();
        }

        @Override // com.spotify.cosmos.util.proto.EpisodeCollectionStateOrBuilder
        public boolean hasIsInListenLater() {
            return ((EpisodeCollectionState) this.instance).hasIsInListenLater();
        }

        @Override // com.spotify.cosmos.util.proto.EpisodeCollectionStateOrBuilder
        public boolean hasIsNew() {
            return ((EpisodeCollectionState) this.instance).hasIsNew();
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
            ((EpisodeCollectionState) this.instance).setIsFollowingShow(z);
            return this;
        }

        public Builder setIsInListenLater(boolean z) {
            copyOnWrite();
            ((EpisodeCollectionState) this.instance).setIsInListenLater(z);
            return this;
        }

        public Builder setIsNew(boolean z) {
            copyOnWrite();
            ((EpisodeCollectionState) this.instance).setIsNew(z);
            return this;
        }

        private Builder() {
            super(EpisodeCollectionState.DEFAULT_INSTANCE);
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
        public /* bridge */ /* synthetic */ vj1 m265mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m266mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m267mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        EpisodeCollectionState episodeCollectionState = new EpisodeCollectionState();
        DEFAULT_INSTANCE = episodeCollectionState;
        i.registerDefaultInstance(EpisodeCollectionState.class, episodeCollectionState);
    }

    private EpisodeCollectionState() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsFollowingShow() {
        this.bitField0_ &= -2;
        this.isFollowingShow_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsInListenLater() {
        this.bitField0_ &= -5;
        this.isInListenLater_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsNew() {
        this.bitField0_ &= -3;
        this.isNew_ = false;
    }

    public static EpisodeCollectionState getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static EpisodeCollectionState parseDelimitedFrom(InputStream inputStream) {
        return (EpisodeCollectionState) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EpisodeCollectionState parseFrom(ByteBuffer byteBuffer) {
        return (EpisodeCollectionState) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsFollowingShow(boolean z) {
        this.bitField0_ |= 1;
        this.isFollowingShow_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsInListenLater(boolean z) {
        this.bitField0_ |= 4;
        this.isInListenLater_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsNew(boolean z) {
        this.bitField0_ |= 2;
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003ဇ\u0002", new Object[]{"bitField0_", "isFollowingShow_", "isNew_", "isInListenLater_"});
            case 3:
                return new EpisodeCollectionState();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EpisodeCollectionState.class) {
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

    @Override // com.spotify.cosmos.util.proto.EpisodeCollectionStateOrBuilder
    public boolean getIsFollowingShow() {
        return this.isFollowingShow_;
    }

    @Override // com.spotify.cosmos.util.proto.EpisodeCollectionStateOrBuilder
    public boolean getIsInListenLater() {
        return this.isInListenLater_;
    }

    @Override // com.spotify.cosmos.util.proto.EpisodeCollectionStateOrBuilder
    public boolean getIsNew() {
        return this.isNew_;
    }

    @Override // com.spotify.cosmos.util.proto.EpisodeCollectionStateOrBuilder
    public boolean hasIsFollowingShow() {
        return (this.bitField0_ & 1) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.EpisodeCollectionStateOrBuilder
    public boolean hasIsInListenLater() {
        return (this.bitField0_ & 4) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.EpisodeCollectionStateOrBuilder
    public boolean hasIsNew() {
        return (this.bitField0_ & 2) != 0;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(EpisodeCollectionState episodeCollectionState) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(episodeCollectionState);
    }

    public static EpisodeCollectionState parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (EpisodeCollectionState) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static EpisodeCollectionState parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (EpisodeCollectionState) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static EpisodeCollectionState parseFrom(ByteString byteString) {
        return (EpisodeCollectionState) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static EpisodeCollectionState parseFrom(ByteString byteString, rl0 rl0Var) {
        return (EpisodeCollectionState) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static EpisodeCollectionState parseFrom(byte[] bArr) {
        return (EpisodeCollectionState) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static EpisodeCollectionState parseFrom(byte[] bArr, rl0 rl0Var) {
        return (EpisodeCollectionState) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static EpisodeCollectionState parseFrom(InputStream inputStream) {
        return (EpisodeCollectionState) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EpisodeCollectionState parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (EpisodeCollectionState) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static EpisodeCollectionState parseFrom(au auVar) {
        return (EpisodeCollectionState) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static EpisodeCollectionState parseFrom(au auVar, rl0 rl0Var) {
        return (EpisodeCollectionState) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
