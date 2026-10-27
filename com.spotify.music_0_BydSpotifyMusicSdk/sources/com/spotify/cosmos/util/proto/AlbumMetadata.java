package com.spotify.cosmos.util.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.a;
import com.google.protobuf.i;
import com.spotify.cosmos.util.proto.AlbumArtistMetadata;
import com.spotify.cosmos.util.proto.ImageGroup;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import p.a2;
import p.au;
import p.ds1;
import p.h01;
import p.i01;
import p.r1;
import p.rl0;
import p.v51;
import p.vj1;
import p.wj1;

/* JADX INFO: loaded from: classes.dex */
public final class AlbumMetadata extends i implements AlbumMetadataOrBuilder {
    public static final int ARTISTS_FIELD_NUMBER = 1;
    public static final int COPYRIGHT_FIELD_NUMBER = 4;
    public static final int COVERS_FIELD_NUMBER = 5;
    private static final AlbumMetadata DEFAULT_INSTANCE;
    public static final int IS_PREMIUM_ONLY_FIELD_NUMBER = 10;
    public static final int LINK_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 3;
    public static final int NUM_DISCS_FIELD_NUMBER = 7;
    public static final int NUM_TRACKS_FIELD_NUMBER = 8;
    private static volatile ds1 PARSER = null;
    public static final int PLAYABILITY_FIELD_NUMBER = 9;
    public static final int YEAR_FIELD_NUMBER = 6;
    private int bitField0_;
    private ImageGroup covers_;
    private boolean isPremiumOnly_;
    private int numDiscs_;
    private int numTracks_;
    private boolean playability_;
    private int year_;
    private v51 artists_ = i.emptyProtobufList();
    private String link_ = "";
    private String name_ = "";
    private v51 copyright_ = i.emptyProtobufList();

    /* JADX INFO: renamed from: com.spotify.cosmos.util.proto.AlbumMetadata$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements AlbumMetadataOrBuilder {
        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        public Builder addAllArtists(Iterable<? extends AlbumArtistMetadata> iterable) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).addAllArtists(iterable);
            return this;
        }

        public Builder addAllCopyright(Iterable<String> iterable) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).addAllCopyright(iterable);
            return this;
        }

        public Builder addArtists(AlbumArtistMetadata albumArtistMetadata) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).addArtists(albumArtistMetadata);
            return this;
        }

        public Builder addCopyright(String str) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).addCopyright(str);
            return this;
        }

        public Builder addCopyrightBytes(ByteString byteString) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).addCopyrightBytes(byteString);
            return this;
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

        public Builder clearArtists() {
            copyOnWrite();
            ((AlbumMetadata) this.instance).clearArtists();
            return this;
        }

        public Builder clearCopyright() {
            copyOnWrite();
            ((AlbumMetadata) this.instance).clearCopyright();
            return this;
        }

        public Builder clearCovers() {
            copyOnWrite();
            ((AlbumMetadata) this.instance).clearCovers();
            return this;
        }

        public Builder clearIsPremiumOnly() {
            copyOnWrite();
            ((AlbumMetadata) this.instance).clearIsPremiumOnly();
            return this;
        }

        public Builder clearLink() {
            copyOnWrite();
            ((AlbumMetadata) this.instance).clearLink();
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((AlbumMetadata) this.instance).clearName();
            return this;
        }

        public Builder clearNumDiscs() {
            copyOnWrite();
            ((AlbumMetadata) this.instance).clearNumDiscs();
            return this;
        }

        public Builder clearNumTracks() {
            copyOnWrite();
            ((AlbumMetadata) this.instance).clearNumTracks();
            return this;
        }

        public Builder clearPlayability() {
            copyOnWrite();
            ((AlbumMetadata) this.instance).clearPlayability();
            return this;
        }

        public Builder clearYear() {
            copyOnWrite();
            ((AlbumMetadata) this.instance).clearYear();
            return this;
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ Object clone() {
            return super.clone();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public AlbumArtistMetadata getArtists(int i) {
            return ((AlbumMetadata) this.instance).getArtists(i);
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public int getArtistsCount() {
            return ((AlbumMetadata) this.instance).getArtistsCount();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public List<AlbumArtistMetadata> getArtistsList() {
            return Collections.unmodifiableList(((AlbumMetadata) this.instance).getArtistsList());
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public String getCopyright(int i) {
            return ((AlbumMetadata) this.instance).getCopyright(i);
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public ByteString getCopyrightBytes(int i) {
            return ((AlbumMetadata) this.instance).getCopyrightBytes(i);
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public int getCopyrightCount() {
            return ((AlbumMetadata) this.instance).getCopyrightCount();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public List<String> getCopyrightList() {
            return Collections.unmodifiableList(((AlbumMetadata) this.instance).getCopyrightList());
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public ImageGroup getCovers() {
            return ((AlbumMetadata) this.instance).getCovers();
        }

        @Override // p.h01, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public boolean getIsPremiumOnly() {
            return ((AlbumMetadata) this.instance).getIsPremiumOnly();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public String getLink() {
            return ((AlbumMetadata) this.instance).getLink();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public ByteString getLinkBytes() {
            return ((AlbumMetadata) this.instance).getLinkBytes();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public String getName() {
            return ((AlbumMetadata) this.instance).getName();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public ByteString getNameBytes() {
            return ((AlbumMetadata) this.instance).getNameBytes();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public int getNumDiscs() {
            return ((AlbumMetadata) this.instance).getNumDiscs();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public int getNumTracks() {
            return ((AlbumMetadata) this.instance).getNumTracks();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public boolean getPlayability() {
            return ((AlbumMetadata) this.instance).getPlayability();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public int getYear() {
            return ((AlbumMetadata) this.instance).getYear();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public boolean hasCovers() {
            return ((AlbumMetadata) this.instance).hasCovers();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public boolean hasIsPremiumOnly() {
            return ((AlbumMetadata) this.instance).hasIsPremiumOnly();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public boolean hasLink() {
            return ((AlbumMetadata) this.instance).hasLink();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public boolean hasName() {
            return ((AlbumMetadata) this.instance).hasName();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public boolean hasNumDiscs() {
            return ((AlbumMetadata) this.instance).hasNumDiscs();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public boolean hasNumTracks() {
            return ((AlbumMetadata) this.instance).hasNumTracks();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public boolean hasPlayability() {
            return ((AlbumMetadata) this.instance).hasPlayability();
        }

        @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
        public boolean hasYear() {
            return ((AlbumMetadata) this.instance).hasYear();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        public Builder mergeCovers(ImageGroup imageGroup) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).mergeCovers(imageGroup);
            return this;
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder removeArtists(int i) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).removeArtists(i);
            return this;
        }

        public Builder setArtists(int i, AlbumArtistMetadata albumArtistMetadata) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).setArtists(i, albumArtistMetadata);
            return this;
        }

        public Builder setCopyright(int i, String str) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).setCopyright(i, str);
            return this;
        }

        public Builder setCovers(ImageGroup imageGroup) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).setCovers(imageGroup);
            return this;
        }

        public Builder setIsPremiumOnly(boolean z) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).setIsPremiumOnly(z);
            return this;
        }

        public Builder setLink(String str) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).setLink(str);
            return this;
        }

        public Builder setLinkBytes(ByteString byteString) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).setLinkBytes(byteString);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).setNameBytes(byteString);
            return this;
        }

        public Builder setNumDiscs(int i) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).setNumDiscs(i);
            return this;
        }

        public Builder setNumTracks(int i) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).setNumTracks(i);
            return this;
        }

        public Builder setPlayability(boolean z) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).setPlayability(z);
            return this;
        }

        public Builder setYear(int i) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).setYear(i);
            return this;
        }

        private Builder() {
            super(AlbumMetadata.DEFAULT_INSTANCE);
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ r1 clone() {
            return super.clone();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        public Builder addArtists(int i, AlbumArtistMetadata albumArtistMetadata) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).addArtists(i, albumArtistMetadata);
            return this;
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ vj1 clone() {
            return super.clone();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public Builder setArtists(int i, AlbumArtistMetadata.Builder builder) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).setArtists(i, (AlbumArtistMetadata) builder.build());
            return this;
        }

        public Builder setCovers(ImageGroup.Builder builder) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).setCovers((ImageGroup) builder.build());
            return this;
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString) {
            return super.mergeFrom(byteString);
        }

        public Builder addArtists(AlbumArtistMetadata.Builder builder) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).addArtists((AlbumArtistMetadata) builder.build());
            return this;
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString, rl0 rl0Var) {
            return super.mergeFrom(byteString, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream) {
            return super.mergeFrom(inputStream);
        }

        public Builder addArtists(int i, AlbumArtistMetadata.Builder builder) {
            copyOnWrite();
            ((AlbumMetadata) this.instance).addArtists(i, (AlbumArtistMetadata) builder.build());
            return this;
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream, rl0 rl0Var) {
            return super.mergeFrom(inputStream, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(au auVar) {
            return super.mergeFrom(auVar);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m256mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m257mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m258mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        AlbumMetadata albumMetadata = new AlbumMetadata();
        DEFAULT_INSTANCE = albumMetadata;
        i.registerDefaultInstance(AlbumMetadata.class, albumMetadata);
    }

    private AlbumMetadata() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllArtists(Iterable<? extends AlbumArtistMetadata> iterable) {
        ensureArtistsIsMutable();
        a.addAll((Iterable) iterable, (List) this.artists_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllCopyright(Iterable<String> iterable) {
        ensureCopyrightIsMutable();
        a.addAll((Iterable) iterable, (List) this.copyright_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addArtists(AlbumArtistMetadata albumArtistMetadata) {
        albumArtistMetadata.getClass();
        ensureArtistsIsMutable();
        this.artists_.add(albumArtistMetadata);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addCopyright(String str) {
        str.getClass();
        ensureCopyrightIsMutable();
        this.copyright_.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addCopyrightBytes(ByteString byteString) {
        ensureCopyrightIsMutable();
        this.copyright_.add(byteString.s());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearArtists() {
        this.artists_ = i.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCopyright() {
        this.copyright_ = i.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCovers() {
        this.covers_ = null;
        this.bitField0_ &= -5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsPremiumOnly() {
        this.bitField0_ &= -129;
        this.isPremiumOnly_ = false;
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

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNumDiscs() {
        this.bitField0_ &= -17;
        this.numDiscs_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNumTracks() {
        this.bitField0_ &= -33;
        this.numTracks_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPlayability() {
        this.bitField0_ &= -65;
        this.playability_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearYear() {
        this.bitField0_ &= -9;
        this.year_ = 0;
    }

    private void ensureArtistsIsMutable() {
        v51 v51Var = this.artists_;
        if (((a2) v51Var).c) {
            return;
        }
        this.artists_ = i.mutableCopy(v51Var);
    }

    private void ensureCopyrightIsMutable() {
        v51 v51Var = this.copyright_;
        if (((a2) v51Var).c) {
            return;
        }
        this.copyright_ = i.mutableCopy(v51Var);
    }

    public static AlbumMetadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
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
        this.bitField0_ |= 4;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static AlbumMetadata parseDelimitedFrom(InputStream inputStream) {
        return (AlbumMetadata) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AlbumMetadata parseFrom(ByteBuffer byteBuffer) {
        return (AlbumMetadata) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeArtists(int i) {
        ensureArtistsIsMutable();
        this.artists_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setArtists(int i, AlbumArtistMetadata albumArtistMetadata) {
        albumArtistMetadata.getClass();
        ensureArtistsIsMutable();
        this.artists_.set(i, albumArtistMetadata);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCopyright(int i, String str) {
        str.getClass();
        ensureCopyrightIsMutable();
        this.copyright_.set(i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCovers(ImageGroup imageGroup) {
        imageGroup.getClass();
        this.covers_ = imageGroup;
        this.bitField0_ |= 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsPremiumOnly(boolean z) {
        this.bitField0_ |= 128;
        this.isPremiumOnly_ = z;
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

    /* JADX INFO: Access modifiers changed from: private */
    public void setNumDiscs(int i) {
        this.bitField0_ |= 16;
        this.numDiscs_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNumTracks(int i) {
        this.bitField0_ |= 32;
        this.numTracks_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayability(boolean z) {
        this.bitField0_ |= 64;
        this.playability_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setYear(int i) {
        this.bitField0_ |= 8;
        this.year_ = i;
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\n\u0000\u0001\u0001\n\n\u0000\u0002\u0000\u0001\u001b\u0002ဈ\u0000\u0003ဈ\u0001\u0004\u001a\u0005ဉ\u0002\u0006ဋ\u0003\u0007ဋ\u0004\bဋ\u0005\tဇ\u0006\nဇ\u0007", new Object[]{"bitField0_", "artists_", AlbumArtistMetadata.class, "link_", "name_", "copyright_", "covers_", "year_", "numDiscs_", "numTracks_", "playability_", "isPremiumOnly_"});
            case 3:
                return new AlbumMetadata();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (AlbumMetadata.class) {
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

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public AlbumArtistMetadata getArtists(int i) {
        return (AlbumArtistMetadata) this.artists_.get(i);
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public int getArtistsCount() {
        return this.artists_.size();
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public List<AlbumArtistMetadata> getArtistsList() {
        return this.artists_;
    }

    public AlbumArtistMetadataOrBuilder getArtistsOrBuilder(int i) {
        return (AlbumArtistMetadataOrBuilder) this.artists_.get(i);
    }

    public List<? extends AlbumArtistMetadataOrBuilder> getArtistsOrBuilderList() {
        return this.artists_;
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public String getCopyright(int i) {
        return (String) this.copyright_.get(i);
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public ByteString getCopyrightBytes(int i) {
        return ByteString.d((String) this.copyright_.get(i));
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public int getCopyrightCount() {
        return this.copyright_.size();
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public List<String> getCopyrightList() {
        return this.copyright_;
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public ImageGroup getCovers() {
        ImageGroup imageGroup = this.covers_;
        return imageGroup == null ? ImageGroup.getDefaultInstance() : imageGroup;
    }

    @Override // com.google.protobuf.i, p.yj1
    public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public boolean getIsPremiumOnly() {
        return this.isPremiumOnly_;
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public String getLink() {
        return this.link_;
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public ByteString getLinkBytes() {
        return ByteString.d(this.link_);
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public ByteString getNameBytes() {
        return ByteString.d(this.name_);
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public int getNumDiscs() {
        return this.numDiscs_;
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public int getNumTracks() {
        return this.numTracks_;
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public boolean getPlayability() {
        return this.playability_;
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public int getYear() {
        return this.year_;
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public boolean hasCovers() {
        return (this.bitField0_ & 4) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public boolean hasIsPremiumOnly() {
        return (this.bitField0_ & 128) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public boolean hasLink() {
        return (this.bitField0_ & 1) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 2) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public boolean hasNumDiscs() {
        return (this.bitField0_ & 16) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public boolean hasNumTracks() {
        return (this.bitField0_ & 32) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public boolean hasPlayability() {
        return (this.bitField0_ & 64) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.AlbumMetadataOrBuilder
    public boolean hasYear() {
        return (this.bitField0_ & 8) != 0;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(AlbumMetadata albumMetadata) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(albumMetadata);
    }

    public static AlbumMetadata parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (AlbumMetadata) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static AlbumMetadata parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (AlbumMetadata) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static AlbumMetadata parseFrom(ByteString byteString) {
        return (AlbumMetadata) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addArtists(int i, AlbumArtistMetadata albumArtistMetadata) {
        albumArtistMetadata.getClass();
        ensureArtistsIsMutable();
        this.artists_.add(i, albumArtistMetadata);
    }

    public static AlbumMetadata parseFrom(ByteString byteString, rl0 rl0Var) {
        return (AlbumMetadata) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static AlbumMetadata parseFrom(byte[] bArr) {
        return (AlbumMetadata) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AlbumMetadata parseFrom(byte[] bArr, rl0 rl0Var) {
        return (AlbumMetadata) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static AlbumMetadata parseFrom(InputStream inputStream) {
        return (AlbumMetadata) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AlbumMetadata parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (AlbumMetadata) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static AlbumMetadata parseFrom(au auVar) {
        return (AlbumMetadata) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static AlbumMetadata parseFrom(au auVar, rl0 rl0Var) {
        return (AlbumMetadata) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
