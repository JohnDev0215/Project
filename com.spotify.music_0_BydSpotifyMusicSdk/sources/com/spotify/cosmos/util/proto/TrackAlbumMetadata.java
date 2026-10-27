package com.spotify.cosmos.util.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.a;
import com.google.protobuf.i;
import com.spotify.cosmos.util.proto.ImageGroup;
import com.spotify.cosmos.util.proto.TrackAlbumArtistMetadata;
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
public final class TrackAlbumMetadata extends i implements TrackAlbumMetadataOrBuilder {
    public static final int ARTIST_FIELD_NUMBER = 1;
    public static final int COVERS_FIELD_NUMBER = 4;
    private static final TrackAlbumMetadata DEFAULT_INSTANCE;
    public static final int LINK_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 3;
    private static volatile ds1 PARSER;
    private TrackAlbumArtistMetadata artist_;
    private int bitField0_;
    private ImageGroup covers_;
    private String link_ = "";
    private String name_ = "";

    /* JADX INFO: renamed from: com.spotify.cosmos.util.proto.TrackAlbumMetadata$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements TrackAlbumMetadataOrBuilder {
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

        public Builder clearArtist() {
            copyOnWrite();
            ((TrackAlbumMetadata) this.instance).clearArtist();
            return this;
        }

        public Builder clearCovers() {
            copyOnWrite();
            ((TrackAlbumMetadata) this.instance).clearCovers();
            return this;
        }

        public Builder clearLink() {
            copyOnWrite();
            ((TrackAlbumMetadata) this.instance).clearLink();
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((TrackAlbumMetadata) this.instance).clearName();
            return this;
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ Object clone() {
            return super.clone();
        }

        @Override // com.spotify.cosmos.util.proto.TrackAlbumMetadataOrBuilder
        public TrackAlbumArtistMetadata getArtist() {
            return ((TrackAlbumMetadata) this.instance).getArtist();
        }

        @Override // com.spotify.cosmos.util.proto.TrackAlbumMetadataOrBuilder
        public ImageGroup getCovers() {
            return ((TrackAlbumMetadata) this.instance).getCovers();
        }

        @Override // p.h01, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.cosmos.util.proto.TrackAlbumMetadataOrBuilder
        public String getLink() {
            return ((TrackAlbumMetadata) this.instance).getLink();
        }

        @Override // com.spotify.cosmos.util.proto.TrackAlbumMetadataOrBuilder
        public ByteString getLinkBytes() {
            return ((TrackAlbumMetadata) this.instance).getLinkBytes();
        }

        @Override // com.spotify.cosmos.util.proto.TrackAlbumMetadataOrBuilder
        public String getName() {
            return ((TrackAlbumMetadata) this.instance).getName();
        }

        @Override // com.spotify.cosmos.util.proto.TrackAlbumMetadataOrBuilder
        public ByteString getNameBytes() {
            return ((TrackAlbumMetadata) this.instance).getNameBytes();
        }

        @Override // com.spotify.cosmos.util.proto.TrackAlbumMetadataOrBuilder
        public boolean hasArtist() {
            return ((TrackAlbumMetadata) this.instance).hasArtist();
        }

        @Override // com.spotify.cosmos.util.proto.TrackAlbumMetadataOrBuilder
        public boolean hasCovers() {
            return ((TrackAlbumMetadata) this.instance).hasCovers();
        }

        @Override // com.spotify.cosmos.util.proto.TrackAlbumMetadataOrBuilder
        public boolean hasLink() {
            return ((TrackAlbumMetadata) this.instance).hasLink();
        }

        @Override // com.spotify.cosmos.util.proto.TrackAlbumMetadataOrBuilder
        public boolean hasName() {
            return ((TrackAlbumMetadata) this.instance).hasName();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        public Builder mergeArtist(TrackAlbumArtistMetadata trackAlbumArtistMetadata) {
            copyOnWrite();
            ((TrackAlbumMetadata) this.instance).mergeArtist(trackAlbumArtistMetadata);
            return this;
        }

        public Builder mergeCovers(ImageGroup imageGroup) {
            copyOnWrite();
            ((TrackAlbumMetadata) this.instance).mergeCovers(imageGroup);
            return this;
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder setArtist(TrackAlbumArtistMetadata trackAlbumArtistMetadata) {
            copyOnWrite();
            ((TrackAlbumMetadata) this.instance).setArtist(trackAlbumArtistMetadata);
            return this;
        }

        public Builder setCovers(ImageGroup imageGroup) {
            copyOnWrite();
            ((TrackAlbumMetadata) this.instance).setCovers(imageGroup);
            return this;
        }

        public Builder setLink(String str) {
            copyOnWrite();
            ((TrackAlbumMetadata) this.instance).setLink(str);
            return this;
        }

        public Builder setLinkBytes(ByteString byteString) {
            copyOnWrite();
            ((TrackAlbumMetadata) this.instance).setLinkBytes(byteString);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((TrackAlbumMetadata) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((TrackAlbumMetadata) this.instance).setNameBytes(byteString);
            return this;
        }

        private Builder() {
            super(TrackAlbumMetadata.DEFAULT_INSTANCE);
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

        public Builder setArtist(TrackAlbumArtistMetadata.Builder builder) {
            copyOnWrite();
            ((TrackAlbumMetadata) this.instance).setArtist((TrackAlbumArtistMetadata) builder.build());
            return this;
        }

        public Builder setCovers(ImageGroup.Builder builder) {
            copyOnWrite();
            ((TrackAlbumMetadata) this.instance).setCovers((ImageGroup) builder.build());
            return this;
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
        public /* bridge */ /* synthetic */ vj1 m299mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m300mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m301mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        TrackAlbumMetadata trackAlbumMetadata = new TrackAlbumMetadata();
        DEFAULT_INSTANCE = trackAlbumMetadata;
        i.registerDefaultInstance(TrackAlbumMetadata.class, trackAlbumMetadata);
    }

    private TrackAlbumMetadata() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearArtist() {
        this.artist_ = null;
        this.bitField0_ &= -2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCovers() {
        this.covers_ = null;
        this.bitField0_ &= -9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLink() {
        this.bitField0_ &= -3;
        this.link_ = getDefaultInstance().getLink();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.bitField0_ &= -5;
        this.name_ = getDefaultInstance().getName();
    }

    public static TrackAlbumMetadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeArtist(TrackAlbumArtistMetadata trackAlbumArtistMetadata) {
        trackAlbumArtistMetadata.getClass();
        TrackAlbumArtistMetadata trackAlbumArtistMetadata2 = this.artist_;
        if (trackAlbumArtistMetadata2 == null || trackAlbumArtistMetadata2 == TrackAlbumArtistMetadata.getDefaultInstance()) {
            this.artist_ = trackAlbumArtistMetadata;
        } else {
            this.artist_ = (TrackAlbumArtistMetadata) ((TrackAlbumArtistMetadata.Builder) TrackAlbumArtistMetadata.newBuilder(this.artist_).mergeFrom((i) trackAlbumArtistMetadata)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeCovers(ImageGroup imageGroup) {
        imageGroup.getClass();
        ImageGroup imageGroup2 = this.covers_;
        if (imageGroup2 == null || imageGroup2 == ImageGroup.getDefaultInstance()) {
            this.covers_ = imageGroup;
        } else {
            this.covers_ = (ImageGroup) ((ImageGroup.Builder) ImageGroup.newBuilder(this.covers_).mergeFrom((i) imageGroup)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static TrackAlbumMetadata parseDelimitedFrom(InputStream inputStream) {
        return (TrackAlbumMetadata) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TrackAlbumMetadata parseFrom(ByteBuffer byteBuffer) {
        return (TrackAlbumMetadata) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setArtist(TrackAlbumArtistMetadata trackAlbumArtistMetadata) {
        trackAlbumArtistMetadata.getClass();
        this.artist_ = trackAlbumArtistMetadata;
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCovers(ImageGroup imageGroup) {
        imageGroup.getClass();
        this.covers_ = imageGroup;
        this.bitField0_ |= 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLink(String str) {
        str.getClass();
        this.bitField0_ |= 2;
        this.link_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLinkBytes(ByteString byteString) {
        this.link_ = byteString.s();
        this.bitField0_ |= 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setName(String str) {
        str.getClass();
        this.bitField0_ |= 4;
        this.name_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNameBytes(ByteString byteString) {
        this.name_ = byteString.s();
        this.bitField0_ |= 4;
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဉ\u0003", new Object[]{"bitField0_", "artist_", "link_", "name_", "covers_"});
            case 3:
                return new TrackAlbumMetadata();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (TrackAlbumMetadata.class) {
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

    @Override // com.spotify.cosmos.util.proto.TrackAlbumMetadataOrBuilder
    public TrackAlbumArtistMetadata getArtist() {
        TrackAlbumArtistMetadata trackAlbumArtistMetadata = this.artist_;
        return trackAlbumArtistMetadata == null ? TrackAlbumArtistMetadata.getDefaultInstance() : trackAlbumArtistMetadata;
    }

    @Override // com.spotify.cosmos.util.proto.TrackAlbumMetadataOrBuilder
    public ImageGroup getCovers() {
        ImageGroup imageGroup = this.covers_;
        return imageGroup == null ? ImageGroup.getDefaultInstance() : imageGroup;
    }

    @Override // com.google.protobuf.i, p.yj1
    public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override // com.spotify.cosmos.util.proto.TrackAlbumMetadataOrBuilder
    public String getLink() {
        return this.link_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackAlbumMetadataOrBuilder
    public ByteString getLinkBytes() {
        return ByteString.d(this.link_);
    }

    @Override // com.spotify.cosmos.util.proto.TrackAlbumMetadataOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackAlbumMetadataOrBuilder
    public ByteString getNameBytes() {
        return ByteString.d(this.name_);
    }

    @Override // com.spotify.cosmos.util.proto.TrackAlbumMetadataOrBuilder
    public boolean hasArtist() {
        return (this.bitField0_ & 1) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackAlbumMetadataOrBuilder
    public boolean hasCovers() {
        return (this.bitField0_ & 8) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackAlbumMetadataOrBuilder
    public boolean hasLink() {
        return (this.bitField0_ & 2) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackAlbumMetadataOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 4) != 0;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(TrackAlbumMetadata trackAlbumMetadata) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(trackAlbumMetadata);
    }

    public static TrackAlbumMetadata parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (TrackAlbumMetadata) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static TrackAlbumMetadata parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (TrackAlbumMetadata) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static TrackAlbumMetadata parseFrom(ByteString byteString) {
        return (TrackAlbumMetadata) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static TrackAlbumMetadata parseFrom(ByteString byteString, rl0 rl0Var) {
        return (TrackAlbumMetadata) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static TrackAlbumMetadata parseFrom(byte[] bArr) {
        return (TrackAlbumMetadata) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static TrackAlbumMetadata parseFrom(byte[] bArr, rl0 rl0Var) {
        return (TrackAlbumMetadata) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static TrackAlbumMetadata parseFrom(InputStream inputStream) {
        return (TrackAlbumMetadata) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TrackAlbumMetadata parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (TrackAlbumMetadata) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static TrackAlbumMetadata parseFrom(au auVar) {
        return (TrackAlbumMetadata) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static TrackAlbumMetadata parseFrom(au auVar, rl0 rl0Var) {
        return (TrackAlbumMetadata) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
