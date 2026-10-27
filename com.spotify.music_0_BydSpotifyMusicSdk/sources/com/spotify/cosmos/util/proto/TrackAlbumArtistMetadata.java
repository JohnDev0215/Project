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
public final class TrackAlbumArtistMetadata extends i implements TrackAlbumArtistMetadataOrBuilder {
    private static final TrackAlbumArtistMetadata DEFAULT_INSTANCE;
    public static final int LINK_FIELD_NUMBER = 1;
    public static final int NAME_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER;
    private int bitField0_;
    private String link_ = "";
    private String name_ = "";

    /* JADX INFO: renamed from: com.spotify.cosmos.util.proto.TrackAlbumArtistMetadata$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements TrackAlbumArtistMetadataOrBuilder {
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

        public Builder clearLink() {
            copyOnWrite();
            ((TrackAlbumArtistMetadata) this.instance).clearLink();
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((TrackAlbumArtistMetadata) this.instance).clearName();
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

        @Override // com.spotify.cosmos.util.proto.TrackAlbumArtistMetadataOrBuilder
        public String getLink() {
            return ((TrackAlbumArtistMetadata) this.instance).getLink();
        }

        @Override // com.spotify.cosmos.util.proto.TrackAlbumArtistMetadataOrBuilder
        public ByteString getLinkBytes() {
            return ((TrackAlbumArtistMetadata) this.instance).getLinkBytes();
        }

        @Override // com.spotify.cosmos.util.proto.TrackAlbumArtistMetadataOrBuilder
        public String getName() {
            return ((TrackAlbumArtistMetadata) this.instance).getName();
        }

        @Override // com.spotify.cosmos.util.proto.TrackAlbumArtistMetadataOrBuilder
        public ByteString getNameBytes() {
            return ((TrackAlbumArtistMetadata) this.instance).getNameBytes();
        }

        @Override // com.spotify.cosmos.util.proto.TrackAlbumArtistMetadataOrBuilder
        public boolean hasLink() {
            return ((TrackAlbumArtistMetadata) this.instance).hasLink();
        }

        @Override // com.spotify.cosmos.util.proto.TrackAlbumArtistMetadataOrBuilder
        public boolean hasName() {
            return ((TrackAlbumArtistMetadata) this.instance).hasName();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder setLink(String str) {
            copyOnWrite();
            ((TrackAlbumArtistMetadata) this.instance).setLink(str);
            return this;
        }

        public Builder setLinkBytes(ByteString byteString) {
            copyOnWrite();
            ((TrackAlbumArtistMetadata) this.instance).setLinkBytes(byteString);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((TrackAlbumArtistMetadata) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((TrackAlbumArtistMetadata) this.instance).setNameBytes(byteString);
            return this;
        }

        private Builder() {
            super(TrackAlbumArtistMetadata.DEFAULT_INSTANCE);
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
        public /* bridge */ /* synthetic */ vj1 m296mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m297mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m298mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        TrackAlbumArtistMetadata trackAlbumArtistMetadata = new TrackAlbumArtistMetadata();
        DEFAULT_INSTANCE = trackAlbumArtistMetadata;
        i.registerDefaultInstance(TrackAlbumArtistMetadata.class, trackAlbumArtistMetadata);
    }

    private TrackAlbumArtistMetadata() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLink() {
        this.bitField0_ &= -2;
        this.link_ = getDefaultInstance().getLink();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.bitField0_ &= -3;
        this.name_ = getDefaultInstance().getName();
    }

    public static TrackAlbumArtistMetadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static TrackAlbumArtistMetadata parseDelimitedFrom(InputStream inputStream) {
        return (TrackAlbumArtistMetadata) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TrackAlbumArtistMetadata parseFrom(ByteBuffer byteBuffer) {
        return (TrackAlbumArtistMetadata) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLink(String str) {
        str.getClass();
        this.bitField0_ |= 1;
        this.link_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLinkBytes(ByteString byteString) {
        this.link_ = byteString.s();
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setName(String str) {
        str.getClass();
        this.bitField0_ |= 2;
        this.name_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNameBytes(ByteString byteString) {
        this.name_ = byteString.s();
        this.bitField0_ |= 2;
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"bitField0_", "link_", "name_"});
            case 3:
                return new TrackAlbumArtistMetadata();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (TrackAlbumArtistMetadata.class) {
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

    @Override // com.spotify.cosmos.util.proto.TrackAlbumArtistMetadataOrBuilder
    public String getLink() {
        return this.link_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackAlbumArtistMetadataOrBuilder
    public ByteString getLinkBytes() {
        return ByteString.d(this.link_);
    }

    @Override // com.spotify.cosmos.util.proto.TrackAlbumArtistMetadataOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackAlbumArtistMetadataOrBuilder
    public ByteString getNameBytes() {
        return ByteString.d(this.name_);
    }

    @Override // com.spotify.cosmos.util.proto.TrackAlbumArtistMetadataOrBuilder
    public boolean hasLink() {
        return (this.bitField0_ & 1) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackAlbumArtistMetadataOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 2) != 0;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(TrackAlbumArtistMetadata trackAlbumArtistMetadata) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(trackAlbumArtistMetadata);
    }

    public static TrackAlbumArtistMetadata parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (TrackAlbumArtistMetadata) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static TrackAlbumArtistMetadata parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (TrackAlbumArtistMetadata) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static TrackAlbumArtistMetadata parseFrom(ByteString byteString) {
        return (TrackAlbumArtistMetadata) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static TrackAlbumArtistMetadata parseFrom(ByteString byteString, rl0 rl0Var) {
        return (TrackAlbumArtistMetadata) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static TrackAlbumArtistMetadata parseFrom(byte[] bArr) {
        return (TrackAlbumArtistMetadata) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static TrackAlbumArtistMetadata parseFrom(byte[] bArr, rl0 rl0Var) {
        return (TrackAlbumArtistMetadata) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static TrackAlbumArtistMetadata parseFrom(InputStream inputStream) {
        return (TrackAlbumArtistMetadata) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TrackAlbumArtistMetadata parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (TrackAlbumArtistMetadata) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static TrackAlbumArtistMetadata parseFrom(au auVar) {
        return (TrackAlbumArtistMetadata) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static TrackAlbumArtistMetadata parseFrom(au auVar, rl0 rl0Var) {
        return (TrackAlbumArtistMetadata) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
