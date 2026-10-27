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
public final class AlbumCollectionState extends i implements AlbumCollectionStateOrBuilder {
    public static final int COLLECTION_LINK_FIELD_NUMBER = 1;
    public static final int COMPLETE_FIELD_NUMBER = 3;
    private static final AlbumCollectionState DEFAULT_INSTANCE;
    public static final int NUM_TRACKS_IN_COLLECTION_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER;
    private int bitField0_;
    private String collectionLink_ = "";
    private boolean complete_;
    private int numTracksInCollection_;

    /* JADX INFO: renamed from: com.spotify.cosmos.util.proto.AlbumCollectionState$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements AlbumCollectionStateOrBuilder {
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

        public Builder clearCollectionLink() {
            copyOnWrite();
            ((AlbumCollectionState) this.instance).clearCollectionLink();
            return this;
        }

        public Builder clearComplete() {
            copyOnWrite();
            ((AlbumCollectionState) this.instance).clearComplete();
            return this;
        }

        public Builder clearNumTracksInCollection() {
            copyOnWrite();
            ((AlbumCollectionState) this.instance).clearNumTracksInCollection();
            return this;
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ Object clone() {
            return super.clone();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumCollectionStateOrBuilder
        public String getCollectionLink() {
            return ((AlbumCollectionState) this.instance).getCollectionLink();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumCollectionStateOrBuilder
        public ByteString getCollectionLinkBytes() {
            return ((AlbumCollectionState) this.instance).getCollectionLinkBytes();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumCollectionStateOrBuilder
        public boolean getComplete() {
            return ((AlbumCollectionState) this.instance).getComplete();
        }

        @Override // p.h01, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumCollectionStateOrBuilder
        public int getNumTracksInCollection() {
            return ((AlbumCollectionState) this.instance).getNumTracksInCollection();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumCollectionStateOrBuilder
        public boolean hasCollectionLink() {
            return ((AlbumCollectionState) this.instance).hasCollectionLink();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumCollectionStateOrBuilder
        public boolean hasComplete() {
            return ((AlbumCollectionState) this.instance).hasComplete();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumCollectionStateOrBuilder
        public boolean hasNumTracksInCollection() {
            return ((AlbumCollectionState) this.instance).hasNumTracksInCollection();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder setCollectionLink(String str) {
            copyOnWrite();
            ((AlbumCollectionState) this.instance).setCollectionLink(str);
            return this;
        }

        public Builder setCollectionLinkBytes(ByteString byteString) {
            copyOnWrite();
            ((AlbumCollectionState) this.instance).setCollectionLinkBytes(byteString);
            return this;
        }

        public Builder setComplete(boolean z) {
            copyOnWrite();
            ((AlbumCollectionState) this.instance).setComplete(z);
            return this;
        }

        public Builder setNumTracksInCollection(int i) {
            copyOnWrite();
            ((AlbumCollectionState) this.instance).setNumTracksInCollection(i);
            return this;
        }

        private Builder() {
            super(AlbumCollectionState.DEFAULT_INSTANCE);
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
        public /* bridge */ /* synthetic */ vj1 m253mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m254mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m255mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        AlbumCollectionState albumCollectionState = new AlbumCollectionState();
        DEFAULT_INSTANCE = albumCollectionState;
        i.registerDefaultInstance(AlbumCollectionState.class, albumCollectionState);
    }

    private AlbumCollectionState() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCollectionLink() {
        this.bitField0_ &= -2;
        this.collectionLink_ = getDefaultInstance().getCollectionLink();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearComplete() {
        this.bitField0_ &= -5;
        this.complete_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNumTracksInCollection() {
        this.bitField0_ &= -3;
        this.numTracksInCollection_ = 0;
    }

    public static AlbumCollectionState getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static AlbumCollectionState parseDelimitedFrom(InputStream inputStream) {
        return (AlbumCollectionState) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AlbumCollectionState parseFrom(ByteBuffer byteBuffer) {
        return (AlbumCollectionState) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCollectionLink(String str) {
        str.getClass();
        this.bitField0_ |= 1;
        this.collectionLink_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCollectionLinkBytes(ByteString byteString) {
        this.collectionLink_ = byteString.s();
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setComplete(boolean z) {
        this.bitField0_ |= 4;
        this.complete_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNumTracksInCollection(int i) {
        this.bitField0_ |= 2;
        this.numTracksInCollection_ = i;
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဋ\u0001\u0003ဇ\u0002", new Object[]{"bitField0_", "collectionLink_", "numTracksInCollection_", "complete_"});
            case 3:
                return new AlbumCollectionState();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (AlbumCollectionState.class) {
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

    @Override // com.spotify.cosmos.util.proto.AlbumCollectionStateOrBuilder
    public String getCollectionLink() {
        return this.collectionLink_;
    }

    @Override // com.spotify.cosmos.util.proto.AlbumCollectionStateOrBuilder
    public ByteString getCollectionLinkBytes() {
        return ByteString.d(this.collectionLink_);
    }

    @Override // com.spotify.cosmos.util.proto.AlbumCollectionStateOrBuilder
    public boolean getComplete() {
        return this.complete_;
    }

    @Override // com.google.protobuf.i, p.yj1
    public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override // com.spotify.cosmos.util.proto.AlbumCollectionStateOrBuilder
    public int getNumTracksInCollection() {
        return this.numTracksInCollection_;
    }

    @Override // com.spotify.cosmos.util.proto.AlbumCollectionStateOrBuilder
    public boolean hasCollectionLink() {
        return (this.bitField0_ & 1) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.AlbumCollectionStateOrBuilder
    public boolean hasComplete() {
        return (this.bitField0_ & 4) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.AlbumCollectionStateOrBuilder
    public boolean hasNumTracksInCollection() {
        return (this.bitField0_ & 2) != 0;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(AlbumCollectionState albumCollectionState) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(albumCollectionState);
    }

    public static AlbumCollectionState parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (AlbumCollectionState) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static AlbumCollectionState parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (AlbumCollectionState) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static AlbumCollectionState parseFrom(ByteString byteString) {
        return (AlbumCollectionState) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AlbumCollectionState parseFrom(ByteString byteString, rl0 rl0Var) {
        return (AlbumCollectionState) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static AlbumCollectionState parseFrom(byte[] bArr) {
        return (AlbumCollectionState) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AlbumCollectionState parseFrom(byte[] bArr, rl0 rl0Var) {
        return (AlbumCollectionState) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static AlbumCollectionState parseFrom(InputStream inputStream) {
        return (AlbumCollectionState) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AlbumCollectionState parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (AlbumCollectionState) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static AlbumCollectionState parseFrom(au auVar) {
        return (AlbumCollectionState) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static AlbumCollectionState parseFrom(au auVar, rl0 rl0Var) {
        return (AlbumCollectionState) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
