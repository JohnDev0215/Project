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
public final class TrackCollectionDecorationPolicy extends i implements TrackCollectionDecorationPolicyOrBuilder {
    public static final int CAN_ADD_TO_COLLECTION_FIELD_NUMBER = 2;
    public static final int CAN_BAN_FIELD_NUMBER = 4;
    private static final TrackCollectionDecorationPolicy DEFAULT_INSTANCE;
    public static final int IS_BANNED_FIELD_NUMBER = 3;
    public static final int IS_IN_COLLECTION_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER;
    private boolean canAddToCollection_;
    private boolean canBan_;
    private boolean isBanned_;
    private boolean isInCollection_;

    /* JADX INFO: renamed from: com.spotify.cosmos.util.policy.proto.TrackCollectionDecorationPolicy$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements TrackCollectionDecorationPolicyOrBuilder {
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

        public Builder clearCanAddToCollection() {
            copyOnWrite();
            ((TrackCollectionDecorationPolicy) this.instance).clearCanAddToCollection();
            return this;
        }

        public Builder clearCanBan() {
            copyOnWrite();
            ((TrackCollectionDecorationPolicy) this.instance).clearCanBan();
            return this;
        }

        public Builder clearIsBanned() {
            copyOnWrite();
            ((TrackCollectionDecorationPolicy) this.instance).clearIsBanned();
            return this;
        }

        public Builder clearIsInCollection() {
            copyOnWrite();
            ((TrackCollectionDecorationPolicy) this.instance).clearIsInCollection();
            return this;
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ Object clone() {
            return super.clone();
        }

        @Override // com.spotify.cosmos.util.policy.proto.TrackCollectionDecorationPolicyOrBuilder
        public boolean getCanAddToCollection() {
            return ((TrackCollectionDecorationPolicy) this.instance).getCanAddToCollection();
        }

        @Override // com.spotify.cosmos.util.policy.proto.TrackCollectionDecorationPolicyOrBuilder
        public boolean getCanBan() {
            return ((TrackCollectionDecorationPolicy) this.instance).getCanBan();
        }

        @Override // p.h01, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.cosmos.util.policy.proto.TrackCollectionDecorationPolicyOrBuilder
        public boolean getIsBanned() {
            return ((TrackCollectionDecorationPolicy) this.instance).getIsBanned();
        }

        @Override // com.spotify.cosmos.util.policy.proto.TrackCollectionDecorationPolicyOrBuilder
        public boolean getIsInCollection() {
            return ((TrackCollectionDecorationPolicy) this.instance).getIsInCollection();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder setCanAddToCollection(boolean z) {
            copyOnWrite();
            ((TrackCollectionDecorationPolicy) this.instance).setCanAddToCollection(z);
            return this;
        }

        public Builder setCanBan(boolean z) {
            copyOnWrite();
            ((TrackCollectionDecorationPolicy) this.instance).setCanBan(z);
            return this;
        }

        public Builder setIsBanned(boolean z) {
            copyOnWrite();
            ((TrackCollectionDecorationPolicy) this.instance).setIsBanned(z);
            return this;
        }

        public Builder setIsInCollection(boolean z) {
            copyOnWrite();
            ((TrackCollectionDecorationPolicy) this.instance).setIsInCollection(z);
            return this;
        }

        private Builder() {
            super(TrackCollectionDecorationPolicy.DEFAULT_INSTANCE);
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
        public /* bridge */ /* synthetic */ vj1 m238mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m239mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m240mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        TrackCollectionDecorationPolicy trackCollectionDecorationPolicy = new TrackCollectionDecorationPolicy();
        DEFAULT_INSTANCE = trackCollectionDecorationPolicy;
        i.registerDefaultInstance(TrackCollectionDecorationPolicy.class, trackCollectionDecorationPolicy);
    }

    private TrackCollectionDecorationPolicy() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCanAddToCollection() {
        this.canAddToCollection_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCanBan() {
        this.canBan_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsBanned() {
        this.isBanned_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsInCollection() {
        this.isInCollection_ = false;
    }

    public static TrackCollectionDecorationPolicy getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static TrackCollectionDecorationPolicy parseDelimitedFrom(InputStream inputStream) {
        return (TrackCollectionDecorationPolicy) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TrackCollectionDecorationPolicy parseFrom(ByteBuffer byteBuffer) {
        return (TrackCollectionDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCanAddToCollection(boolean z) {
        this.canAddToCollection_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCanBan(boolean z) {
        this.canBan_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsBanned(boolean z) {
        this.isBanned_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsInCollection(boolean z) {
        this.isInCollection_ = z;
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0007\u0002\u0007\u0003\u0007\u0004\u0007", new Object[]{"isInCollection_", "canAddToCollection_", "isBanned_", "canBan_"});
            case 3:
                return new TrackCollectionDecorationPolicy();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (TrackCollectionDecorationPolicy.class) {
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

    @Override // com.spotify.cosmos.util.policy.proto.TrackCollectionDecorationPolicyOrBuilder
    public boolean getCanAddToCollection() {
        return this.canAddToCollection_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.TrackCollectionDecorationPolicyOrBuilder
    public boolean getCanBan() {
        return this.canBan_;
    }

    @Override // com.google.protobuf.i, p.yj1
    public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override // com.spotify.cosmos.util.policy.proto.TrackCollectionDecorationPolicyOrBuilder
    public boolean getIsBanned() {
        return this.isBanned_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.TrackCollectionDecorationPolicyOrBuilder
    public boolean getIsInCollection() {
        return this.isInCollection_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(TrackCollectionDecorationPolicy trackCollectionDecorationPolicy) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(trackCollectionDecorationPolicy);
    }

    public static TrackCollectionDecorationPolicy parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (TrackCollectionDecorationPolicy) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static TrackCollectionDecorationPolicy parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (TrackCollectionDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static TrackCollectionDecorationPolicy parseFrom(ByteString byteString) {
        return (TrackCollectionDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static TrackCollectionDecorationPolicy parseFrom(ByteString byteString, rl0 rl0Var) {
        return (TrackCollectionDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static TrackCollectionDecorationPolicy parseFrom(byte[] bArr) {
        return (TrackCollectionDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static TrackCollectionDecorationPolicy parseFrom(byte[] bArr, rl0 rl0Var) {
        return (TrackCollectionDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static TrackCollectionDecorationPolicy parseFrom(InputStream inputStream) {
        return (TrackCollectionDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TrackCollectionDecorationPolicy parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (TrackCollectionDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static TrackCollectionDecorationPolicy parseFrom(au auVar) {
        return (TrackCollectionDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static TrackCollectionDecorationPolicy parseFrom(au auVar, rl0 rl0Var) {
        return (TrackCollectionDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
