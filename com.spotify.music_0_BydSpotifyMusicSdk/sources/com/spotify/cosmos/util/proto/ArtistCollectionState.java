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
public final class ArtistCollectionState extends i implements ArtistCollectionStateOrBuilder {
    public static final int CAN_BAN_FIELD_NUMBER = 6;
    public static final int COLLECTION_LINK_FIELD_NUMBER = 1;
    private static final ArtistCollectionState DEFAULT_INSTANCE;
    public static final int FOLLOWED_FIELD_NUMBER = 2;
    public static final int IS_BANNED_FIELD_NUMBER = 5;
    public static final int NUM_ALBUMS_IN_COLLECTION_FIELD_NUMBER = 4;
    public static final int NUM_EXPLICITLY_LIKED_TRACKS_FIELD_NUMBER = 7;
    public static final int NUM_TRACKS_IN_COLLECTION_FIELD_NUMBER = 3;
    private static volatile ds1 PARSER;
    private int bitField0_;
    private boolean canBan_;
    private String collectionLink_ = "";
    private boolean followed_;
    private boolean isBanned_;
    private int numAlbumsInCollection_;
    private int numExplicitlyLikedTracks_;
    private int numTracksInCollection_;

    /* JADX INFO: renamed from: com.spotify.cosmos.util.proto.ArtistCollectionState$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements ArtistCollectionStateOrBuilder {
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

        public Builder clearCanBan() {
            copyOnWrite();
            ((ArtistCollectionState) this.instance).clearCanBan();
            return this;
        }

        public Builder clearCollectionLink() {
            copyOnWrite();
            ((ArtistCollectionState) this.instance).clearCollectionLink();
            return this;
        }

        public Builder clearFollowed() {
            copyOnWrite();
            ((ArtistCollectionState) this.instance).clearFollowed();
            return this;
        }

        public Builder clearIsBanned() {
            copyOnWrite();
            ((ArtistCollectionState) this.instance).clearIsBanned();
            return this;
        }

        public Builder clearNumAlbumsInCollection() {
            copyOnWrite();
            ((ArtistCollectionState) this.instance).clearNumAlbumsInCollection();
            return this;
        }

        public Builder clearNumExplicitlyLikedTracks() {
            copyOnWrite();
            ((ArtistCollectionState) this.instance).clearNumExplicitlyLikedTracks();
            return this;
        }

        public Builder clearNumTracksInCollection() {
            copyOnWrite();
            ((ArtistCollectionState) this.instance).clearNumTracksInCollection();
            return this;
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ Object clone() {
            return super.clone();
        }

        @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
        public boolean getCanBan() {
            return ((ArtistCollectionState) this.instance).getCanBan();
        }

        @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
        public String getCollectionLink() {
            return ((ArtistCollectionState) this.instance).getCollectionLink();
        }

        @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
        public ByteString getCollectionLinkBytes() {
            return ((ArtistCollectionState) this.instance).getCollectionLinkBytes();
        }

        @Override // p.h01, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
        public boolean getFollowed() {
            return ((ArtistCollectionState) this.instance).getFollowed();
        }

        @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
        public boolean getIsBanned() {
            return ((ArtistCollectionState) this.instance).getIsBanned();
        }

        @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
        public int getNumAlbumsInCollection() {
            return ((ArtistCollectionState) this.instance).getNumAlbumsInCollection();
        }

        @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
        public int getNumExplicitlyLikedTracks() {
            return ((ArtistCollectionState) this.instance).getNumExplicitlyLikedTracks();
        }

        @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
        public int getNumTracksInCollection() {
            return ((ArtistCollectionState) this.instance).getNumTracksInCollection();
        }

        @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
        public boolean hasCanBan() {
            return ((ArtistCollectionState) this.instance).hasCanBan();
        }

        @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
        public boolean hasCollectionLink() {
            return ((ArtistCollectionState) this.instance).hasCollectionLink();
        }

        @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
        public boolean hasFollowed() {
            return ((ArtistCollectionState) this.instance).hasFollowed();
        }

        @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
        public boolean hasIsBanned() {
            return ((ArtistCollectionState) this.instance).hasIsBanned();
        }

        @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
        public boolean hasNumAlbumsInCollection() {
            return ((ArtistCollectionState) this.instance).hasNumAlbumsInCollection();
        }

        @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
        public boolean hasNumExplicitlyLikedTracks() {
            return ((ArtistCollectionState) this.instance).hasNumExplicitlyLikedTracks();
        }

        @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
        public boolean hasNumTracksInCollection() {
            return ((ArtistCollectionState) this.instance).hasNumTracksInCollection();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder setCanBan(boolean z) {
            copyOnWrite();
            ((ArtistCollectionState) this.instance).setCanBan(z);
            return this;
        }

        public Builder setCollectionLink(String str) {
            copyOnWrite();
            ((ArtistCollectionState) this.instance).setCollectionLink(str);
            return this;
        }

        public Builder setCollectionLinkBytes(ByteString byteString) {
            copyOnWrite();
            ((ArtistCollectionState) this.instance).setCollectionLinkBytes(byteString);
            return this;
        }

        public Builder setFollowed(boolean z) {
            copyOnWrite();
            ((ArtistCollectionState) this.instance).setFollowed(z);
            return this;
        }

        public Builder setIsBanned(boolean z) {
            copyOnWrite();
            ((ArtistCollectionState) this.instance).setIsBanned(z);
            return this;
        }

        public Builder setNumAlbumsInCollection(int i) {
            copyOnWrite();
            ((ArtistCollectionState) this.instance).setNumAlbumsInCollection(i);
            return this;
        }

        public Builder setNumExplicitlyLikedTracks(int i) {
            copyOnWrite();
            ((ArtistCollectionState) this.instance).setNumExplicitlyLikedTracks(i);
            return this;
        }

        public Builder setNumTracksInCollection(int i) {
            copyOnWrite();
            ((ArtistCollectionState) this.instance).setNumTracksInCollection(i);
            return this;
        }

        private Builder() {
            super(ArtistCollectionState.DEFAULT_INSTANCE);
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
        public /* bridge */ /* synthetic */ vj1 m259mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m260mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m261mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        ArtistCollectionState artistCollectionState = new ArtistCollectionState();
        DEFAULT_INSTANCE = artistCollectionState;
        i.registerDefaultInstance(ArtistCollectionState.class, artistCollectionState);
    }

    private ArtistCollectionState() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCanBan() {
        this.bitField0_ &= -33;
        this.canBan_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCollectionLink() {
        this.bitField0_ &= -2;
        this.collectionLink_ = getDefaultInstance().getCollectionLink();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFollowed() {
        this.bitField0_ &= -3;
        this.followed_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsBanned() {
        this.bitField0_ &= -17;
        this.isBanned_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNumAlbumsInCollection() {
        this.bitField0_ &= -9;
        this.numAlbumsInCollection_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNumExplicitlyLikedTracks() {
        this.bitField0_ &= -65;
        this.numExplicitlyLikedTracks_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNumTracksInCollection() {
        this.bitField0_ &= -5;
        this.numTracksInCollection_ = 0;
    }

    public static ArtistCollectionState getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static ArtistCollectionState parseDelimitedFrom(InputStream inputStream) {
        return (ArtistCollectionState) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ArtistCollectionState parseFrom(ByteBuffer byteBuffer) {
        return (ArtistCollectionState) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCanBan(boolean z) {
        this.bitField0_ |= 32;
        this.canBan_ = z;
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
    public void setFollowed(boolean z) {
        this.bitField0_ |= 2;
        this.followed_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsBanned(boolean z) {
        this.bitField0_ |= 16;
        this.isBanned_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNumAlbumsInCollection(int i) {
        this.bitField0_ |= 8;
        this.numAlbumsInCollection_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNumExplicitlyLikedTracks(int i) {
        this.bitField0_ |= 64;
        this.numExplicitlyLikedTracks_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNumTracksInCollection(int i) {
        this.bitField0_ |= 4;
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဋ\u0002\u0004ဋ\u0003\u0005ဇ\u0004\u0006ဇ\u0005\u0007ဋ\u0006", new Object[]{"bitField0_", "collectionLink_", "followed_", "numTracksInCollection_", "numAlbumsInCollection_", "isBanned_", "canBan_", "numExplicitlyLikedTracks_"});
            case 3:
                return new ArtistCollectionState();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (ArtistCollectionState.class) {
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

    @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
    public boolean getCanBan() {
        return this.canBan_;
    }

    @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
    public String getCollectionLink() {
        return this.collectionLink_;
    }

    @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
    public ByteString getCollectionLinkBytes() {
        return ByteString.d(this.collectionLink_);
    }

    @Override // com.google.protobuf.i, p.yj1
    public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
    public boolean getFollowed() {
        return this.followed_;
    }

    @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
    public boolean getIsBanned() {
        return this.isBanned_;
    }

    @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
    public int getNumAlbumsInCollection() {
        return this.numAlbumsInCollection_;
    }

    @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
    public int getNumExplicitlyLikedTracks() {
        return this.numExplicitlyLikedTracks_;
    }

    @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
    public int getNumTracksInCollection() {
        return this.numTracksInCollection_;
    }

    @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
    public boolean hasCanBan() {
        return (this.bitField0_ & 32) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
    public boolean hasCollectionLink() {
        return (this.bitField0_ & 1) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
    public boolean hasFollowed() {
        return (this.bitField0_ & 2) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
    public boolean hasIsBanned() {
        return (this.bitField0_ & 16) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
    public boolean hasNumAlbumsInCollection() {
        return (this.bitField0_ & 8) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
    public boolean hasNumExplicitlyLikedTracks() {
        return (this.bitField0_ & 64) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ArtistCollectionStateOrBuilder
    public boolean hasNumTracksInCollection() {
        return (this.bitField0_ & 4) != 0;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(ArtistCollectionState artistCollectionState) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(artistCollectionState);
    }

    public static ArtistCollectionState parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (ArtistCollectionState) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static ArtistCollectionState parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (ArtistCollectionState) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static ArtistCollectionState parseFrom(ByteString byteString) {
        return (ArtistCollectionState) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ArtistCollectionState parseFrom(ByteString byteString, rl0 rl0Var) {
        return (ArtistCollectionState) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static ArtistCollectionState parseFrom(byte[] bArr) {
        return (ArtistCollectionState) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ArtistCollectionState parseFrom(byte[] bArr, rl0 rl0Var) {
        return (ArtistCollectionState) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static ArtistCollectionState parseFrom(InputStream inputStream) {
        return (ArtistCollectionState) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ArtistCollectionState parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (ArtistCollectionState) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static ArtistCollectionState parseFrom(au auVar) {
        return (ArtistCollectionState) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static ArtistCollectionState parseFrom(au auVar, rl0 rl0Var) {
        return (ArtistCollectionState) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
