package com.spotify.localfiles.proto;

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
import p.n51;
import p.o51;
import p.p51;
import p.r1;
import p.rl0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class LocalFile extends i implements LocalFileOrBuilder {
    private static final LocalFile DEFAULT_INSTANCE;
    public static final int METADATA_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER = null;
    public static final int PATH_FIELD_NUMBER = 1;
    private Metadata metadata_;
    private String path_ = "";

    /* JADX INFO: renamed from: com.spotify.localfiles.proto.LocalFile$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements LocalFileOrBuilder {
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

        public Builder clearMetadata() {
            copyOnWrite();
            ((LocalFile) this.instance).clearMetadata();
            return this;
        }

        public Builder clearPath() {
            copyOnWrite();
            ((LocalFile) this.instance).clearPath();
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

        @Override // com.spotify.localfiles.proto.LocalFileOrBuilder
        public Metadata getMetadata() {
            return ((LocalFile) this.instance).getMetadata();
        }

        @Override // com.spotify.localfiles.proto.LocalFileOrBuilder
        public String getPath() {
            return ((LocalFile) this.instance).getPath();
        }

        @Override // com.spotify.localfiles.proto.LocalFileOrBuilder
        public ByteString getPathBytes() {
            return ((LocalFile) this.instance).getPathBytes();
        }

        @Override // com.spotify.localfiles.proto.LocalFileOrBuilder
        public boolean hasMetadata() {
            return ((LocalFile) this.instance).hasMetadata();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder mergeMetadata(Metadata metadata) {
            copyOnWrite();
            ((LocalFile) this.instance).mergeMetadata(metadata);
            return this;
        }

        public Builder setMetadata(Metadata metadata) {
            copyOnWrite();
            ((LocalFile) this.instance).setMetadata(metadata);
            return this;
        }

        public Builder setPath(String str) {
            copyOnWrite();
            ((LocalFile) this.instance).setPath(str);
            return this;
        }

        public Builder setPathBytes(ByteString byteString) {
            copyOnWrite();
            ((LocalFile) this.instance).setPathBytes(byteString);
            return this;
        }

        private Builder() {
            super(LocalFile.DEFAULT_INSTANCE);
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

        public Builder setMetadata(Metadata.Builder builder) {
            copyOnWrite();
            ((LocalFile) this.instance).setMetadata((Metadata) builder.build());
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
        public /* bridge */ /* synthetic */ vj1 m317mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m318mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m319mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    public static final class Metadata extends i implements MetadataOrBuilder {
        public static final int ALBUM_FIELD_NUMBER = 2;
        public static final int ARTIST_FIELD_NUMBER = 3;
        private static final Metadata DEFAULT_INSTANCE;
        public static final int DURATION_FIELD_NUMBER = 4;
        public static final int IMAGE_STATE_FIELD_NUMBER = 5;
        private static volatile ds1 PARSER = null;
        public static final int TITLE_FIELD_NUMBER = 1;
        private int duration_;
        private int imageState_;
        private String title_ = "";
        private String album_ = "";
        private String artist_ = "";

        public static final class Builder extends h01 implements MetadataOrBuilder {
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

            public Builder clearAlbum() {
                copyOnWrite();
                ((Metadata) this.instance).clearAlbum();
                return this;
            }

            public Builder clearArtist() {
                copyOnWrite();
                ((Metadata) this.instance).clearArtist();
                return this;
            }

            public Builder clearDuration() {
                copyOnWrite();
                ((Metadata) this.instance).clearDuration();
                return this;
            }

            public Builder clearImageState() {
                copyOnWrite();
                ((Metadata) this.instance).clearImageState();
                return this;
            }

            public Builder clearTitle() {
                copyOnWrite();
                ((Metadata) this.instance).clearTitle();
                return this;
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // com.spotify.localfiles.proto.LocalFile.MetadataOrBuilder
            public String getAlbum() {
                return ((Metadata) this.instance).getAlbum();
            }

            @Override // com.spotify.localfiles.proto.LocalFile.MetadataOrBuilder
            public ByteString getAlbumBytes() {
                return ((Metadata) this.instance).getAlbumBytes();
            }

            @Override // com.spotify.localfiles.proto.LocalFile.MetadataOrBuilder
            public String getArtist() {
                return ((Metadata) this.instance).getArtist();
            }

            @Override // com.spotify.localfiles.proto.LocalFile.MetadataOrBuilder
            public ByteString getArtistBytes() {
                return ((Metadata) this.instance).getArtistBytes();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.localfiles.proto.LocalFile.MetadataOrBuilder
            public int getDuration() {
                return ((Metadata) this.instance).getDuration();
            }

            @Override // com.spotify.localfiles.proto.LocalFile.MetadataOrBuilder
            public ImageState getImageState() {
                return ((Metadata) this.instance).getImageState();
            }

            @Override // com.spotify.localfiles.proto.LocalFile.MetadataOrBuilder
            public int getImageStateValue() {
                return ((Metadata) this.instance).getImageStateValue();
            }

            @Override // com.spotify.localfiles.proto.LocalFile.MetadataOrBuilder
            public String getTitle() {
                return ((Metadata) this.instance).getTitle();
            }

            @Override // com.spotify.localfiles.proto.LocalFile.MetadataOrBuilder
            public ByteString getTitleBytes() {
                return ((Metadata) this.instance).getTitleBytes();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setAlbum(String str) {
                copyOnWrite();
                ((Metadata) this.instance).setAlbum(str);
                return this;
            }

            public Builder setAlbumBytes(ByteString byteString) {
                copyOnWrite();
                ((Metadata) this.instance).setAlbumBytes(byteString);
                return this;
            }

            public Builder setArtist(String str) {
                copyOnWrite();
                ((Metadata) this.instance).setArtist(str);
                return this;
            }

            public Builder setArtistBytes(ByteString byteString) {
                copyOnWrite();
                ((Metadata) this.instance).setArtistBytes(byteString);
                return this;
            }

            public Builder setDuration(int i) {
                copyOnWrite();
                ((Metadata) this.instance).setDuration(i);
                return this;
            }

            public Builder setImageState(ImageState imageState) {
                copyOnWrite();
                ((Metadata) this.instance).setImageState(imageState);
                return this;
            }

            public Builder setImageStateValue(int i) {
                copyOnWrite();
                ((Metadata) this.instance).setImageStateValue(i);
                return this;
            }

            public Builder setTitle(String str) {
                copyOnWrite();
                ((Metadata) this.instance).setTitle(str);
                return this;
            }

            public Builder setTitleBytes(ByteString byteString) {
                copyOnWrite();
                ((Metadata) this.instance).setTitleBytes(byteString);
                return this;
            }

            private Builder() {
                super(Metadata.DEFAULT_INSTANCE);
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
            public /* bridge */ /* synthetic */ vj1 m320mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m321mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m322mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        public enum ImageState implements n51 {
            UNKNOWN(0),
            AVAILABLE(1),
            UNAVAILABLE(2),
            UNRECOGNIZED(-1);

            public static final int AVAILABLE_VALUE = 1;
            public static final int UNAVAILABLE_VALUE = 2;
            public static final int UNKNOWN_VALUE = 0;
            private static final o51 internalValueMap = new o51() { // from class: com.spotify.localfiles.proto.LocalFile.Metadata.ImageState.1
                /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
                public ImageState m323findValueByNumber(int i) {
                    return ImageState.forNumber(i);
                }
            };
            private final int value;

            public static final class ImageStateVerifier implements p51 {
                static final p51 INSTANCE = new ImageStateVerifier();

                private ImageStateVerifier() {
                }

                @Override // p.p51
                public boolean isInRange(int i) {
                    return ImageState.forNumber(i) != null;
                }
            }

            ImageState(int i) {
                this.value = i;
            }

            public static ImageState forNumber(int i) {
                if (i == 0) {
                    return UNKNOWN;
                }
                if (i == 1) {
                    return AVAILABLE;
                }
                if (i != 2) {
                    return null;
                }
                return UNAVAILABLE;
            }

            public static o51 internalGetValueMap() {
                return internalValueMap;
            }

            public static p51 internalGetVerifier() {
                return ImageStateVerifier.INSTANCE;
            }

            @Override // p.n51
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Deprecated
            public static ImageState valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            Metadata metadata = new Metadata();
            DEFAULT_INSTANCE = metadata;
            i.registerDefaultInstance(Metadata.class, metadata);
        }

        private Metadata() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAlbum() {
            this.album_ = getDefaultInstance().getAlbum();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearArtist() {
            this.artist_ = getDefaultInstance().getArtist();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDuration() {
            this.duration_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearImageState() {
            this.imageState_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTitle() {
            this.title_ = getDefaultInstance().getTitle();
        }

        public static Metadata getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Metadata parseDelimitedFrom(InputStream inputStream) {
            return (Metadata) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Metadata parseFrom(ByteBuffer byteBuffer) {
            return (Metadata) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAlbum(String str) {
            str.getClass();
            this.album_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAlbumBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.album_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtist(String str) {
            str.getClass();
            this.artist_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtistBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.artist_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDuration(int i) {
            this.duration_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setImageState(ImageState imageState) {
            this.imageState_ = imageState.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setImageStateValue(int i) {
            this.imageState_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTitle(String str) {
            str.getClass();
            this.title_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTitleBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.title_ = byteString.s();
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004\u0004\u0005\f", new Object[]{"title_", "album_", "artist_", "duration_", "imageState_"});
                case 3:
                    return new Metadata();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (Metadata.class) {
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

        @Override // com.spotify.localfiles.proto.LocalFile.MetadataOrBuilder
        public String getAlbum() {
            return this.album_;
        }

        @Override // com.spotify.localfiles.proto.LocalFile.MetadataOrBuilder
        public ByteString getAlbumBytes() {
            return ByteString.d(this.album_);
        }

        @Override // com.spotify.localfiles.proto.LocalFile.MetadataOrBuilder
        public String getArtist() {
            return this.artist_;
        }

        @Override // com.spotify.localfiles.proto.LocalFile.MetadataOrBuilder
        public ByteString getArtistBytes() {
            return ByteString.d(this.artist_);
        }

        @Override // com.google.protobuf.i, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.localfiles.proto.LocalFile.MetadataOrBuilder
        public int getDuration() {
            return this.duration_;
        }

        @Override // com.spotify.localfiles.proto.LocalFile.MetadataOrBuilder
        public ImageState getImageState() {
            ImageState imageStateForNumber = ImageState.forNumber(this.imageState_);
            return imageStateForNumber == null ? ImageState.UNRECOGNIZED : imageStateForNumber;
        }

        @Override // com.spotify.localfiles.proto.LocalFile.MetadataOrBuilder
        public int getImageStateValue() {
            return this.imageState_;
        }

        @Override // com.spotify.localfiles.proto.LocalFile.MetadataOrBuilder
        public String getTitle() {
            return this.title_;
        }

        @Override // com.spotify.localfiles.proto.LocalFile.MetadataOrBuilder
        public ByteString getTitleBytes() {
            return ByteString.d(this.title_);
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(Metadata metadata) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(metadata);
        }

        public static Metadata parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (Metadata) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static Metadata parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (Metadata) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static Metadata parseFrom(ByteString byteString) {
            return (Metadata) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Metadata parseFrom(ByteString byteString, rl0 rl0Var) {
            return (Metadata) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static Metadata parseFrom(byte[] bArr) {
            return (Metadata) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Metadata parseFrom(byte[] bArr, rl0 rl0Var) {
            return (Metadata) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static Metadata parseFrom(InputStream inputStream) {
            return (Metadata) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Metadata parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (Metadata) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static Metadata parseFrom(au auVar) {
            return (Metadata) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static Metadata parseFrom(au auVar, rl0 rl0Var) {
            return (Metadata) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface MetadataOrBuilder extends yj1 {
        String getAlbum();

        ByteString getAlbumBytes();

        String getArtist();

        ByteString getArtistBytes();

        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        int getDuration();

        Metadata.ImageState getImageState();

        int getImageStateValue();

        String getTitle();

        ByteString getTitleBytes();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    static {
        LocalFile localFile = new LocalFile();
        DEFAULT_INSTANCE = localFile;
        i.registerDefaultInstance(LocalFile.class, localFile);
    }

    private LocalFile() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMetadata() {
        this.metadata_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPath() {
        this.path_ = getDefaultInstance().getPath();
    }

    public static LocalFile getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeMetadata(Metadata metadata) {
        metadata.getClass();
        Metadata metadata2 = this.metadata_;
        if (metadata2 == null || metadata2 == Metadata.getDefaultInstance()) {
            this.metadata_ = metadata;
        } else {
            this.metadata_ = (Metadata) ((Metadata.Builder) Metadata.newBuilder(this.metadata_).mergeFrom((i) metadata)).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static LocalFile parseDelimitedFrom(InputStream inputStream) {
        return (LocalFile) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static LocalFile parseFrom(ByteBuffer byteBuffer) {
        return (LocalFile) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMetadata(Metadata metadata) {
        metadata.getClass();
        this.metadata_ = metadata;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPath(String str) {
        str.getClass();
        this.path_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPathBytes(ByteString byteString) {
        a.checkByteStringIsUtf8(byteString);
        this.path_ = byteString.s();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\t", new Object[]{"path_", "metadata_"});
            case 3:
                return new LocalFile();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (LocalFile.class) {
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

    @Override // com.spotify.localfiles.proto.LocalFileOrBuilder
    public Metadata getMetadata() {
        Metadata metadata = this.metadata_;
        return metadata == null ? Metadata.getDefaultInstance() : metadata;
    }

    @Override // com.spotify.localfiles.proto.LocalFileOrBuilder
    public String getPath() {
        return this.path_;
    }

    @Override // com.spotify.localfiles.proto.LocalFileOrBuilder
    public ByteString getPathBytes() {
        return ByteString.d(this.path_);
    }

    @Override // com.spotify.localfiles.proto.LocalFileOrBuilder
    public boolean hasMetadata() {
        return this.metadata_ != null;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(LocalFile localFile) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(localFile);
    }

    public static LocalFile parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (LocalFile) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static LocalFile parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (LocalFile) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static LocalFile parseFrom(ByteString byteString) {
        return (LocalFile) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static LocalFile parseFrom(ByteString byteString, rl0 rl0Var) {
        return (LocalFile) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static LocalFile parseFrom(byte[] bArr) {
        return (LocalFile) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static LocalFile parseFrom(byte[] bArr, rl0 rl0Var) {
        return (LocalFile) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static LocalFile parseFrom(InputStream inputStream) {
        return (LocalFile) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static LocalFile parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (LocalFile) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static LocalFile parseFrom(au auVar) {
        return (LocalFile) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static LocalFile parseFrom(au auVar, rl0 rl0Var) {
        return (LocalFile) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
