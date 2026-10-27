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
public final class TrackCollectionState extends i implements TrackCollectionStateOrBuilder {
    public static final int CAN_ADD_TO_COLLECTION_FIELD_NUMBER = 2;
    public static final int CAN_BAN_FIELD_NUMBER = 4;
    private static final TrackCollectionState DEFAULT_INSTANCE;
    public static final int IS_BANNED_FIELD_NUMBER = 3;
    public static final int IS_IN_COLLECTION_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER;
    private int bitField0_;
    private boolean canAddToCollection_;
    private boolean canBan_;
    private boolean isBanned_;
    private boolean isInCollection_;

    /* JADX INFO: renamed from: com.spotify.cosmos.util.proto.TrackCollectionState$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements TrackCollectionStateOrBuilder {
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
            ((TrackCollectionState) this.instance).clearCanAddToCollection();
            return this;
        }

        public Builder clearCanBan() {
            copyOnWrite();
            ((TrackCollectionState) this.instance).clearCanBan();
            return this;
        }

        public Builder clearIsBanned() {
            copyOnWrite();
            ((TrackCollectionState) this.instance).clearIsBanned();
            return this;
        }

        public Builder clearIsInCollection() {
            copyOnWrite();
            ((TrackCollectionState) this.instance).clearIsInCollection();
            return this;
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ Object clone() {
            return super.clone();
        }

        @Override // com.spotify.cosmos.util.proto.TrackCollectionStateOrBuilder
        public boolean getCanAddToCollection() {
            return ((TrackCollectionState) this.instance).getCanAddToCollection();
        }

        @Override // com.spotify.cosmos.util.proto.TrackCollectionStateOrBuilder
        public boolean getCanBan() {
            return ((TrackCollectionState) this.instance).getCanBan();
        }

        @Override // p.h01, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.cosmos.util.proto.TrackCollectionStateOrBuilder
        public boolean getIsBanned() {
            return ((TrackCollectionState) this.instance).getIsBanned();
        }

        @Override // com.spotify.cosmos.util.proto.TrackCollectionStateOrBuilder
        public boolean getIsInCollection() {
            return ((TrackCollectionState) this.instance).getIsInCollection();
        }

        @Override // com.spotify.cosmos.util.proto.TrackCollectionStateOrBuilder
        public boolean hasCanAddToCollection() {
            return ((TrackCollectionState) this.instance).hasCanAddToCollection();
        }

        @Override // com.spotify.cosmos.util.proto.TrackCollectionStateOrBuilder
        public boolean hasCanBan() {
            return ((TrackCollectionState) this.instance).hasCanBan();
        }

        @Override // com.spotify.cosmos.util.proto.TrackCollectionStateOrBuilder
        public boolean hasIsBanned() {
            return ((TrackCollectionState) this.instance).hasIsBanned();
        }

        @Override // com.spotify.cosmos.util.proto.TrackCollectionStateOrBuilder
        public boolean hasIsInCollection() {
            return ((TrackCollectionState) this.instance).hasIsInCollection();
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
            ((TrackCollectionState) this.instance).setCanAddToCollection(z);
            return this;
        }

        public Builder setCanBan(boolean z) {
            copyOnWrite();
            ((TrackCollectionState) this.instance).setCanBan(z);
            return this;
        }

        public Builder setIsBanned(boolean z) {
            copyOnWrite();
            ((TrackCollectionState) this.instance).setIsBanned(z);
            return this;
        }

        public Builder setIsInCollection(boolean z) {
            copyOnWrite();
            ((TrackCollectionState) this.instance).setIsInCollection(z);
            return this;
        }

        private Builder() {
            super(TrackCollectionState.DEFAULT_INSTANCE);
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
        public /* bridge */ /* synthetic */ vj1 m305mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m306mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m307mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        TrackCollectionState trackCollectionState = new TrackCollectionState();
        DEFAULT_INSTANCE = trackCollectionState;
        i.registerDefaultInstance(TrackCollectionState.class, trackCollectionState);
    }

    private TrackCollectionState() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCanAddToCollection() {
        this.bitField0_ &= -3;
        this.canAddToCollection_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCanBan() {
        this.bitField0_ &= -9;
        this.canBan_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsBanned() {
        this.bitField0_ &= -5;
        this.isBanned_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsInCollection() {
        this.bitField0_ &= -2;
        this.isInCollection_ = false;
    }

    public static TrackCollectionState getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static TrackCollectionState parseDelimitedFrom(InputStream inputStream) {
        return (TrackCollectionState) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TrackCollectionState parseFrom(ByteBuffer byteBuffer) {
        return (TrackCollectionState) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCanAddToCollection(boolean z) {
        this.bitField0_ |= 2;
        this.canAddToCollection_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCanBan(boolean z) {
        this.bitField0_ |= 8;
        this.canBan_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsBanned(boolean z) {
        this.bitField0_ |= 4;
        this.isBanned_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsInCollection(boolean z) {
        this.bitField0_ |= 1;
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004ဇ\u0003", new Object[]{"bitField0_", "isInCollection_", "canAddToCollection_", "isBanned_", "canBan_"});
            case 3:
                return new TrackCollectionState();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (TrackCollectionState.class) {
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

    @Override // com.spotify.cosmos.util.proto.TrackCollectionStateOrBuilder
    public boolean getCanAddToCollection() {
        return this.canAddToCollection_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackCollectionStateOrBuilder
    public boolean getCanBan() {
        return this.canBan_;
    }

    @Override // com.google.protobuf.i, p.yj1
    public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override // com.spotify.cosmos.util.proto.TrackCollectionStateOrBuilder
    public boolean getIsBanned() {
        return this.isBanned_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackCollectionStateOrBuilder
    public boolean getIsInCollection() {
        return this.isInCollection_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackCollectionStateOrBuilder
    public boolean hasCanAddToCollection() {
        return (this.bitField0_ & 2) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackCollectionStateOrBuilder
    public boolean hasCanBan() {
        return (this.bitField0_ & 8) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackCollectionStateOrBuilder
    public boolean hasIsBanned() {
        return (this.bitField0_ & 4) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackCollectionStateOrBuilder
    public boolean hasIsInCollection() {
        return (this.bitField0_ & 1) != 0;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(TrackCollectionState trackCollectionState) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(trackCollectionState);
    }

    public static TrackCollectionState parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (TrackCollectionState) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static TrackCollectionState parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (TrackCollectionState) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static TrackCollectionState parseFrom(ByteString byteString) {
        return (TrackCollectionState) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static TrackCollectionState parseFrom(ByteString byteString, rl0 rl0Var) {
        return (TrackCollectionState) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static TrackCollectionState parseFrom(byte[] bArr) {
        return (TrackCollectionState) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static TrackCollectionState parseFrom(byte[] bArr, rl0 rl0Var) {
        return (TrackCollectionState) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static TrackCollectionState parseFrom(InputStream inputStream) {
        return (TrackCollectionState) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TrackCollectionState parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (TrackCollectionState) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static TrackCollectionState parseFrom(au auVar) {
        return (TrackCollectionState) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static TrackCollectionState parseFrom(au auVar, rl0 rl0Var) {
        return (TrackCollectionState) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
