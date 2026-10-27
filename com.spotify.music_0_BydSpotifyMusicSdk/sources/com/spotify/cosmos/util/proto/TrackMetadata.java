package com.spotify.cosmos.util.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.a;
import com.google.protobuf.i;
import com.spotify.cosmos.util.proto.Extension;
import com.spotify.cosmos.util.proto.TrackAlbumMetadata;
import com.spotify.cosmos.util.proto.TrackArtistMetadata;
import com.spotify.cosmos.util.proto.TrackDescriptor;
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
public final class TrackMetadata extends i implements TrackMetadataOrBuilder {
    public static final int ALBUM_FIELD_NUMBER = 1;
    public static final int ARTIST_FIELD_NUMBER = 2;
    private static final TrackMetadata DEFAULT_INSTANCE;
    public static final int DISC_NUMBER_FIELD_NUMBER = 7;
    public static final int EXTENSION_FIELD_NUMBER = 20;
    public static final int HAS_LYRICS_FIELD_NUMBER = 13;
    public static final int IS_19_PLUS_ONLY_FIELD_NUMBER = 18;
    public static final int IS_CURATED_FIELD_NUMBER = 21;
    public static final int IS_EXPLICIT_FIELD_NUMBER = 9;
    public static final int IS_LOCAL_FIELD_NUMBER = 11;
    public static final int IS_PREMIUM_ONLY_FIELD_NUMBER = 14;
    public static final int LENGTH_FIELD_NUMBER = 5;
    public static final int LINK_FIELD_NUMBER = 3;
    public static final int LOCALLY_PLAYABLE_FIELD_NUMBER = 15;
    public static final int NAME_FIELD_NUMBER = 4;
    private static volatile ds1 PARSER = null;
    public static final int PLAYABLE_FIELD_NUMBER = 6;
    public static final int PLAYABLE_LOCAL_TRACK_FIELD_NUMBER = 12;
    public static final int PLAYABLE_TRACK_LINK_FIELD_NUMBER = 16;
    public static final int POPULARITY_FIELD_NUMBER = 17;
    public static final int PREVIEW_ID_FIELD_NUMBER = 10;
    public static final int TRACK_DESCRIPTORS_FIELD_NUMBER = 19;
    public static final int TRACK_NUMBER_FIELD_NUMBER = 8;
    private TrackAlbumMetadata album_;
    private int bitField0_;
    private int discNumber_;
    private boolean hasLyrics_;
    private boolean is19PlusOnly_;
    private boolean isCurated_;
    private boolean isExplicit_;
    private boolean isLocal_;
    private boolean isPremiumOnly_;
    private int length_;
    private boolean locallyPlayable_;
    private boolean playableLocalTrack_;
    private boolean playable_;
    private int popularity_;
    private int trackNumber_;
    private v51 artist_ = i.emptyProtobufList();
    private String link_ = "";
    private String name_ = "";
    private String previewId_ = "";
    private String playableTrackLink_ = "";
    private v51 trackDescriptors_ = i.emptyProtobufList();
    private v51 extension_ = i.emptyProtobufList();

    /* JADX INFO: renamed from: com.spotify.cosmos.util.proto.TrackMetadata$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements TrackMetadataOrBuilder {
        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        public Builder addAllArtist(Iterable<? extends TrackArtistMetadata> iterable) {
            copyOnWrite();
            ((TrackMetadata) this.instance).addAllArtist(iterable);
            return this;
        }

        public Builder addAllExtension(Iterable<? extends Extension> iterable) {
            copyOnWrite();
            ((TrackMetadata) this.instance).addAllExtension(iterable);
            return this;
        }

        public Builder addAllTrackDescriptors(Iterable<? extends TrackDescriptor> iterable) {
            copyOnWrite();
            ((TrackMetadata) this.instance).addAllTrackDescriptors(iterable);
            return this;
        }

        public Builder addArtist(TrackArtistMetadata trackArtistMetadata) {
            copyOnWrite();
            ((TrackMetadata) this.instance).addArtist(trackArtistMetadata);
            return this;
        }

        public Builder addExtension(Extension extension) {
            copyOnWrite();
            ((TrackMetadata) this.instance).addExtension(extension);
            return this;
        }

        public Builder addTrackDescriptors(TrackDescriptor trackDescriptor) {
            copyOnWrite();
            ((TrackMetadata) this.instance).addTrackDescriptors(trackDescriptor);
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

        public Builder clearAlbum() {
            copyOnWrite();
            ((TrackMetadata) this.instance).clearAlbum();
            return this;
        }

        public Builder clearArtist() {
            copyOnWrite();
            ((TrackMetadata) this.instance).clearArtist();
            return this;
        }

        public Builder clearDiscNumber() {
            copyOnWrite();
            ((TrackMetadata) this.instance).clearDiscNumber();
            return this;
        }

        public Builder clearExtension() {
            copyOnWrite();
            ((TrackMetadata) this.instance).clearExtension();
            return this;
        }

        public Builder clearHasLyrics() {
            copyOnWrite();
            ((TrackMetadata) this.instance).clearHasLyrics();
            return this;
        }

        public Builder clearIs19PlusOnly() {
            copyOnWrite();
            ((TrackMetadata) this.instance).clearIs19PlusOnly();
            return this;
        }

        public Builder clearIsCurated() {
            copyOnWrite();
            ((TrackMetadata) this.instance).clearIsCurated();
            return this;
        }

        public Builder clearIsExplicit() {
            copyOnWrite();
            ((TrackMetadata) this.instance).clearIsExplicit();
            return this;
        }

        public Builder clearIsLocal() {
            copyOnWrite();
            ((TrackMetadata) this.instance).clearIsLocal();
            return this;
        }

        public Builder clearIsPremiumOnly() {
            copyOnWrite();
            ((TrackMetadata) this.instance).clearIsPremiumOnly();
            return this;
        }

        public Builder clearLength() {
            copyOnWrite();
            ((TrackMetadata) this.instance).clearLength();
            return this;
        }

        public Builder clearLink() {
            copyOnWrite();
            ((TrackMetadata) this.instance).clearLink();
            return this;
        }

        public Builder clearLocallyPlayable() {
            copyOnWrite();
            ((TrackMetadata) this.instance).clearLocallyPlayable();
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((TrackMetadata) this.instance).clearName();
            return this;
        }

        public Builder clearPlayable() {
            copyOnWrite();
            ((TrackMetadata) this.instance).clearPlayable();
            return this;
        }

        public Builder clearPlayableLocalTrack() {
            copyOnWrite();
            ((TrackMetadata) this.instance).clearPlayableLocalTrack();
            return this;
        }

        public Builder clearPlayableTrackLink() {
            copyOnWrite();
            ((TrackMetadata) this.instance).clearPlayableTrackLink();
            return this;
        }

        public Builder clearPopularity() {
            copyOnWrite();
            ((TrackMetadata) this.instance).clearPopularity();
            return this;
        }

        public Builder clearPreviewId() {
            copyOnWrite();
            ((TrackMetadata) this.instance).clearPreviewId();
            return this;
        }

        public Builder clearTrackDescriptors() {
            copyOnWrite();
            ((TrackMetadata) this.instance).clearTrackDescriptors();
            return this;
        }

        public Builder clearTrackNumber() {
            copyOnWrite();
            ((TrackMetadata) this.instance).clearTrackNumber();
            return this;
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ Object clone() {
            return super.clone();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public TrackAlbumMetadata getAlbum() {
            return ((TrackMetadata) this.instance).getAlbum();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public TrackArtistMetadata getArtist(int i) {
            return ((TrackMetadata) this.instance).getArtist(i);
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public int getArtistCount() {
            return ((TrackMetadata) this.instance).getArtistCount();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public List<TrackArtistMetadata> getArtistList() {
            return Collections.unmodifiableList(((TrackMetadata) this.instance).getArtistList());
        }

        @Override // p.h01, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public int getDiscNumber() {
            return ((TrackMetadata) this.instance).getDiscNumber();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public Extension getExtension(int i) {
            return ((TrackMetadata) this.instance).getExtension(i);
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public int getExtensionCount() {
            return ((TrackMetadata) this.instance).getExtensionCount();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public List<Extension> getExtensionList() {
            return Collections.unmodifiableList(((TrackMetadata) this.instance).getExtensionList());
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean getHasLyrics() {
            return ((TrackMetadata) this.instance).getHasLyrics();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean getIs19PlusOnly() {
            return ((TrackMetadata) this.instance).getIs19PlusOnly();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean getIsCurated() {
            return ((TrackMetadata) this.instance).getIsCurated();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean getIsExplicit() {
            return ((TrackMetadata) this.instance).getIsExplicit();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean getIsLocal() {
            return ((TrackMetadata) this.instance).getIsLocal();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean getIsPremiumOnly() {
            return ((TrackMetadata) this.instance).getIsPremiumOnly();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public int getLength() {
            return ((TrackMetadata) this.instance).getLength();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public String getLink() {
            return ((TrackMetadata) this.instance).getLink();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public ByteString getLinkBytes() {
            return ((TrackMetadata) this.instance).getLinkBytes();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean getLocallyPlayable() {
            return ((TrackMetadata) this.instance).getLocallyPlayable();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public String getName() {
            return ((TrackMetadata) this.instance).getName();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public ByteString getNameBytes() {
            return ((TrackMetadata) this.instance).getNameBytes();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean getPlayable() {
            return ((TrackMetadata) this.instance).getPlayable();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean getPlayableLocalTrack() {
            return ((TrackMetadata) this.instance).getPlayableLocalTrack();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public String getPlayableTrackLink() {
            return ((TrackMetadata) this.instance).getPlayableTrackLink();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public ByteString getPlayableTrackLinkBytes() {
            return ((TrackMetadata) this.instance).getPlayableTrackLinkBytes();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public int getPopularity() {
            return ((TrackMetadata) this.instance).getPopularity();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public String getPreviewId() {
            return ((TrackMetadata) this.instance).getPreviewId();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public ByteString getPreviewIdBytes() {
            return ((TrackMetadata) this.instance).getPreviewIdBytes();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public TrackDescriptor getTrackDescriptors(int i) {
            return ((TrackMetadata) this.instance).getTrackDescriptors(i);
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public int getTrackDescriptorsCount() {
            return ((TrackMetadata) this.instance).getTrackDescriptorsCount();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public List<TrackDescriptor> getTrackDescriptorsList() {
            return Collections.unmodifiableList(((TrackMetadata) this.instance).getTrackDescriptorsList());
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public int getTrackNumber() {
            return ((TrackMetadata) this.instance).getTrackNumber();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean hasAlbum() {
            return ((TrackMetadata) this.instance).hasAlbum();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean hasDiscNumber() {
            return ((TrackMetadata) this.instance).hasDiscNumber();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean hasHasLyrics() {
            return ((TrackMetadata) this.instance).hasHasLyrics();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean hasIs19PlusOnly() {
            return ((TrackMetadata) this.instance).hasIs19PlusOnly();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean hasIsCurated() {
            return ((TrackMetadata) this.instance).hasIsCurated();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean hasIsExplicit() {
            return ((TrackMetadata) this.instance).hasIsExplicit();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean hasIsLocal() {
            return ((TrackMetadata) this.instance).hasIsLocal();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean hasIsPremiumOnly() {
            return ((TrackMetadata) this.instance).hasIsPremiumOnly();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean hasLength() {
            return ((TrackMetadata) this.instance).hasLength();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean hasLink() {
            return ((TrackMetadata) this.instance).hasLink();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean hasLocallyPlayable() {
            return ((TrackMetadata) this.instance).hasLocallyPlayable();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean hasName() {
            return ((TrackMetadata) this.instance).hasName();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean hasPlayable() {
            return ((TrackMetadata) this.instance).hasPlayable();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean hasPlayableLocalTrack() {
            return ((TrackMetadata) this.instance).hasPlayableLocalTrack();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean hasPlayableTrackLink() {
            return ((TrackMetadata) this.instance).hasPlayableTrackLink();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean hasPopularity() {
            return ((TrackMetadata) this.instance).hasPopularity();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean hasPreviewId() {
            return ((TrackMetadata) this.instance).hasPreviewId();
        }

        @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
        public boolean hasTrackNumber() {
            return ((TrackMetadata) this.instance).hasTrackNumber();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        public Builder mergeAlbum(TrackAlbumMetadata trackAlbumMetadata) {
            copyOnWrite();
            ((TrackMetadata) this.instance).mergeAlbum(trackAlbumMetadata);
            return this;
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder removeArtist(int i) {
            copyOnWrite();
            ((TrackMetadata) this.instance).removeArtist(i);
            return this;
        }

        public Builder removeExtension(int i) {
            copyOnWrite();
            ((TrackMetadata) this.instance).removeExtension(i);
            return this;
        }

        public Builder removeTrackDescriptors(int i) {
            copyOnWrite();
            ((TrackMetadata) this.instance).removeTrackDescriptors(i);
            return this;
        }

        public Builder setAlbum(TrackAlbumMetadata trackAlbumMetadata) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setAlbum(trackAlbumMetadata);
            return this;
        }

        public Builder setArtist(int i, TrackArtistMetadata trackArtistMetadata) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setArtist(i, trackArtistMetadata);
            return this;
        }

        public Builder setDiscNumber(int i) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setDiscNumber(i);
            return this;
        }

        public Builder setExtension(int i, Extension extension) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setExtension(i, extension);
            return this;
        }

        public Builder setHasLyrics(boolean z) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setHasLyrics(z);
            return this;
        }

        public Builder setIs19PlusOnly(boolean z) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setIs19PlusOnly(z);
            return this;
        }

        public Builder setIsCurated(boolean z) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setIsCurated(z);
            return this;
        }

        public Builder setIsExplicit(boolean z) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setIsExplicit(z);
            return this;
        }

        public Builder setIsLocal(boolean z) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setIsLocal(z);
            return this;
        }

        public Builder setIsPremiumOnly(boolean z) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setIsPremiumOnly(z);
            return this;
        }

        public Builder setLength(int i) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setLength(i);
            return this;
        }

        public Builder setLink(String str) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setLink(str);
            return this;
        }

        public Builder setLinkBytes(ByteString byteString) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setLinkBytes(byteString);
            return this;
        }

        public Builder setLocallyPlayable(boolean z) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setLocallyPlayable(z);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setNameBytes(byteString);
            return this;
        }

        public Builder setPlayable(boolean z) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setPlayable(z);
            return this;
        }

        public Builder setPlayableLocalTrack(boolean z) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setPlayableLocalTrack(z);
            return this;
        }

        public Builder setPlayableTrackLink(String str) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setPlayableTrackLink(str);
            return this;
        }

        public Builder setPlayableTrackLinkBytes(ByteString byteString) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setPlayableTrackLinkBytes(byteString);
            return this;
        }

        public Builder setPopularity(int i) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setPopularity(i);
            return this;
        }

        public Builder setPreviewId(String str) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setPreviewId(str);
            return this;
        }

        public Builder setPreviewIdBytes(ByteString byteString) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setPreviewIdBytes(byteString);
            return this;
        }

        public Builder setTrackDescriptors(int i, TrackDescriptor trackDescriptor) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setTrackDescriptors(i, trackDescriptor);
            return this;
        }

        public Builder setTrackNumber(int i) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setTrackNumber(i);
            return this;
        }

        private Builder() {
            super(TrackMetadata.DEFAULT_INSTANCE);
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ r1 clone() {
            return super.clone();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        public Builder addArtist(int i, TrackArtistMetadata trackArtistMetadata) {
            copyOnWrite();
            ((TrackMetadata) this.instance).addArtist(i, trackArtistMetadata);
            return this;
        }

        public Builder addExtension(int i, Extension extension) {
            copyOnWrite();
            ((TrackMetadata) this.instance).addExtension(i, extension);
            return this;
        }

        public Builder addTrackDescriptors(int i, TrackDescriptor trackDescriptor) {
            copyOnWrite();
            ((TrackMetadata) this.instance).addTrackDescriptors(i, trackDescriptor);
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

        public Builder setAlbum(TrackAlbumMetadata.Builder builder) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setAlbum((TrackAlbumMetadata) builder.build());
            return this;
        }

        public Builder setArtist(int i, TrackArtistMetadata.Builder builder) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setArtist(i, (TrackArtistMetadata) builder.build());
            return this;
        }

        public Builder setExtension(int i, Extension.Builder builder) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setExtension(i, (Extension) builder.build());
            return this;
        }

        public Builder setTrackDescriptors(int i, TrackDescriptor.Builder builder) {
            copyOnWrite();
            ((TrackMetadata) this.instance).setTrackDescriptors(i, (TrackDescriptor) builder.build());
            return this;
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString) {
            return super.mergeFrom(byteString);
        }

        public Builder addArtist(TrackArtistMetadata.Builder builder) {
            copyOnWrite();
            ((TrackMetadata) this.instance).addArtist((TrackArtistMetadata) builder.build());
            return this;
        }

        public Builder addExtension(Extension.Builder builder) {
            copyOnWrite();
            ((TrackMetadata) this.instance).addExtension((Extension) builder.build());
            return this;
        }

        public Builder addTrackDescriptors(TrackDescriptor.Builder builder) {
            copyOnWrite();
            ((TrackMetadata) this.instance).addTrackDescriptors((TrackDescriptor) builder.build());
            return this;
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString, rl0 rl0Var) {
            return super.mergeFrom(byteString, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream) {
            return super.mergeFrom(inputStream);
        }

        public Builder addArtist(int i, TrackArtistMetadata.Builder builder) {
            copyOnWrite();
            ((TrackMetadata) this.instance).addArtist(i, (TrackArtistMetadata) builder.build());
            return this;
        }

        public Builder addExtension(int i, Extension.Builder builder) {
            copyOnWrite();
            ((TrackMetadata) this.instance).addExtension(i, (Extension) builder.build());
            return this;
        }

        public Builder addTrackDescriptors(int i, TrackDescriptor.Builder builder) {
            copyOnWrite();
            ((TrackMetadata) this.instance).addTrackDescriptors(i, (TrackDescriptor) builder.build());
            return this;
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream, rl0 rl0Var) {
            return super.mergeFrom(inputStream, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(au auVar) {
            return super.mergeFrom(auVar);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m311mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m312mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m313mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        TrackMetadata trackMetadata = new TrackMetadata();
        DEFAULT_INSTANCE = trackMetadata;
        i.registerDefaultInstance(TrackMetadata.class, trackMetadata);
    }

    private TrackMetadata() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllArtist(Iterable<? extends TrackArtistMetadata> iterable) {
        ensureArtistIsMutable();
        a.addAll((Iterable) iterable, (List) this.artist_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllExtension(Iterable<? extends Extension> iterable) {
        ensureExtensionIsMutable();
        a.addAll((Iterable) iterable, (List) this.extension_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllTrackDescriptors(Iterable<? extends TrackDescriptor> iterable) {
        ensureTrackDescriptorsIsMutable();
        a.addAll((Iterable) iterable, (List) this.trackDescriptors_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addArtist(TrackArtistMetadata trackArtistMetadata) {
        trackArtistMetadata.getClass();
        ensureArtistIsMutable();
        this.artist_.add(trackArtistMetadata);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addExtension(Extension extension) {
        extension.getClass();
        ensureExtensionIsMutable();
        this.extension_.add(extension);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addTrackDescriptors(TrackDescriptor trackDescriptor) {
        trackDescriptor.getClass();
        ensureTrackDescriptorsIsMutable();
        this.trackDescriptors_.add(trackDescriptor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAlbum() {
        this.album_ = null;
        this.bitField0_ &= -2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearArtist() {
        this.artist_ = i.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDiscNumber() {
        this.bitField0_ &= -33;
        this.discNumber_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearExtension() {
        this.extension_ = i.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHasLyrics() {
        this.bitField0_ &= -2049;
        this.hasLyrics_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIs19PlusOnly() {
        this.bitField0_ &= -65537;
        this.is19PlusOnly_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsCurated() {
        this.bitField0_ &= -131073;
        this.isCurated_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsExplicit() {
        this.bitField0_ &= -129;
        this.isExplicit_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsLocal() {
        this.bitField0_ &= -513;
        this.isLocal_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsPremiumOnly() {
        this.bitField0_ &= -4097;
        this.isPremiumOnly_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLength() {
        this.bitField0_ &= -9;
        this.length_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLink() {
        this.bitField0_ &= -3;
        this.link_ = getDefaultInstance().getLink();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLocallyPlayable() {
        this.bitField0_ &= -8193;
        this.locallyPlayable_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.bitField0_ &= -5;
        this.name_ = getDefaultInstance().getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPlayable() {
        this.bitField0_ &= -17;
        this.playable_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPlayableLocalTrack() {
        this.bitField0_ &= -1025;
        this.playableLocalTrack_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPlayableTrackLink() {
        this.bitField0_ &= -16385;
        this.playableTrackLink_ = getDefaultInstance().getPlayableTrackLink();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPopularity() {
        this.bitField0_ &= -32769;
        this.popularity_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPreviewId() {
        this.bitField0_ &= -257;
        this.previewId_ = getDefaultInstance().getPreviewId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTrackDescriptors() {
        this.trackDescriptors_ = i.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTrackNumber() {
        this.bitField0_ &= -65;
        this.trackNumber_ = 0;
    }

    private void ensureArtistIsMutable() {
        v51 v51Var = this.artist_;
        if (((a2) v51Var).c) {
            return;
        }
        this.artist_ = i.mutableCopy(v51Var);
    }

    private void ensureExtensionIsMutable() {
        v51 v51Var = this.extension_;
        if (((a2) v51Var).c) {
            return;
        }
        this.extension_ = i.mutableCopy(v51Var);
    }

    private void ensureTrackDescriptorsIsMutable() {
        v51 v51Var = this.trackDescriptors_;
        if (((a2) v51Var).c) {
            return;
        }
        this.trackDescriptors_ = i.mutableCopy(v51Var);
    }

    public static TrackMetadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeAlbum(TrackAlbumMetadata trackAlbumMetadata) {
        trackAlbumMetadata.getClass();
        TrackAlbumMetadata trackAlbumMetadata2 = this.album_;
        if (trackAlbumMetadata2 == null || trackAlbumMetadata2 == TrackAlbumMetadata.getDefaultInstance()) {
            this.album_ = trackAlbumMetadata;
        } else {
            this.album_ = (TrackAlbumMetadata) ((TrackAlbumMetadata.Builder) TrackAlbumMetadata.newBuilder(this.album_).mergeFrom((i) trackAlbumMetadata)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static TrackMetadata parseDelimitedFrom(InputStream inputStream) {
        return (TrackMetadata) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TrackMetadata parseFrom(ByteBuffer byteBuffer) {
        return (TrackMetadata) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeArtist(int i) {
        ensureArtistIsMutable();
        this.artist_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeExtension(int i) {
        ensureExtensionIsMutable();
        this.extension_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeTrackDescriptors(int i) {
        ensureTrackDescriptorsIsMutable();
        this.trackDescriptors_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAlbum(TrackAlbumMetadata trackAlbumMetadata) {
        trackAlbumMetadata.getClass();
        this.album_ = trackAlbumMetadata;
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setArtist(int i, TrackArtistMetadata trackArtistMetadata) {
        trackArtistMetadata.getClass();
        ensureArtistIsMutable();
        this.artist_.set(i, trackArtistMetadata);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDiscNumber(int i) {
        this.bitField0_ |= 32;
        this.discNumber_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExtension(int i, Extension extension) {
        extension.getClass();
        ensureExtensionIsMutable();
        this.extension_.set(i, extension);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHasLyrics(boolean z) {
        this.bitField0_ |= 2048;
        this.hasLyrics_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIs19PlusOnly(boolean z) {
        this.bitField0_ |= 65536;
        this.is19PlusOnly_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsCurated(boolean z) {
        this.bitField0_ |= 131072;
        this.isCurated_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsExplicit(boolean z) {
        this.bitField0_ |= 128;
        this.isExplicit_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsLocal(boolean z) {
        this.bitField0_ |= 512;
        this.isLocal_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsPremiumOnly(boolean z) {
        this.bitField0_ |= 4096;
        this.isPremiumOnly_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLength(int i) {
        this.bitField0_ |= 8;
        this.length_ = i;
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
    public void setLocallyPlayable(boolean z) {
        this.bitField0_ |= 8192;
        this.locallyPlayable_ = z;
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

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayable(boolean z) {
        this.bitField0_ |= 16;
        this.playable_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayableLocalTrack(boolean z) {
        this.bitField0_ |= 1024;
        this.playableLocalTrack_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayableTrackLink(String str) {
        str.getClass();
        this.bitField0_ |= 16384;
        this.playableTrackLink_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayableTrackLinkBytes(ByteString byteString) {
        this.playableTrackLink_ = byteString.s();
        this.bitField0_ |= 16384;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPopularity(int i) {
        this.bitField0_ |= 32768;
        this.popularity_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPreviewId(String str) {
        str.getClass();
        this.bitField0_ |= 256;
        this.previewId_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPreviewIdBytes(ByteString byteString) {
        this.previewId_ = byteString.s();
        this.bitField0_ |= 256;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTrackDescriptors(int i, TrackDescriptor trackDescriptor) {
        trackDescriptor.getClass();
        ensureTrackDescriptorsIsMutable();
        this.trackDescriptors_.set(i, trackDescriptor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTrackNumber(int i) {
        this.bitField0_ |= 64;
        this.trackNumber_ = i;
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0015\u0000\u0001\u0001\u0015\u0015\u0000\u0003\u0000\u0001ဉ\u0000\u0002\u001b\u0003ဈ\u0001\u0004ဈ\u0002\u0005ဋ\u0003\u0006ဇ\u0004\u0007ဋ\u0005\bဋ\u0006\tဇ\u0007\nဈ\b\u000bဇ\t\fဇ\n\rဇ\u000b\u000eဇ\f\u000fဇ\r\u0010ဈ\u000e\u0011ဋ\u000f\u0012ဇ\u0010\u0013\u001b\u0014\u001b\u0015ဇ\u0011", new Object[]{"bitField0_", "album_", "artist_", TrackArtistMetadata.class, "link_", "name_", "length_", "playable_", "discNumber_", "trackNumber_", "isExplicit_", "previewId_", "isLocal_", "playableLocalTrack_", "hasLyrics_", "isPremiumOnly_", "locallyPlayable_", "playableTrackLink_", "popularity_", "is19PlusOnly_", "trackDescriptors_", TrackDescriptor.class, "extension_", Extension.class, "isCurated_"});
            case 3:
                return new TrackMetadata();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (TrackMetadata.class) {
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

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public TrackAlbumMetadata getAlbum() {
        TrackAlbumMetadata trackAlbumMetadata = this.album_;
        return trackAlbumMetadata == null ? TrackAlbumMetadata.getDefaultInstance() : trackAlbumMetadata;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public TrackArtistMetadata getArtist(int i) {
        return (TrackArtistMetadata) this.artist_.get(i);
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public int getArtistCount() {
        return this.artist_.size();
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public List<TrackArtistMetadata> getArtistList() {
        return this.artist_;
    }

    public TrackArtistMetadataOrBuilder getArtistOrBuilder(int i) {
        return (TrackArtistMetadataOrBuilder) this.artist_.get(i);
    }

    public List<? extends TrackArtistMetadataOrBuilder> getArtistOrBuilderList() {
        return this.artist_;
    }

    @Override // com.google.protobuf.i, p.yj1
    public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public int getDiscNumber() {
        return this.discNumber_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public Extension getExtension(int i) {
        return (Extension) this.extension_.get(i);
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public int getExtensionCount() {
        return this.extension_.size();
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public List<Extension> getExtensionList() {
        return this.extension_;
    }

    public ExtensionOrBuilder getExtensionOrBuilder(int i) {
        return (ExtensionOrBuilder) this.extension_.get(i);
    }

    public List<? extends ExtensionOrBuilder> getExtensionOrBuilderList() {
        return this.extension_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean getHasLyrics() {
        return this.hasLyrics_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean getIs19PlusOnly() {
        return this.is19PlusOnly_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean getIsCurated() {
        return this.isCurated_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean getIsExplicit() {
        return this.isExplicit_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean getIsLocal() {
        return this.isLocal_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean getIsPremiumOnly() {
        return this.isPremiumOnly_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public int getLength() {
        return this.length_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public String getLink() {
        return this.link_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public ByteString getLinkBytes() {
        return ByteString.d(this.link_);
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean getLocallyPlayable() {
        return this.locallyPlayable_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public ByteString getNameBytes() {
        return ByteString.d(this.name_);
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean getPlayable() {
        return this.playable_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean getPlayableLocalTrack() {
        return this.playableLocalTrack_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public String getPlayableTrackLink() {
        return this.playableTrackLink_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public ByteString getPlayableTrackLinkBytes() {
        return ByteString.d(this.playableTrackLink_);
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public int getPopularity() {
        return this.popularity_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public String getPreviewId() {
        return this.previewId_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public ByteString getPreviewIdBytes() {
        return ByteString.d(this.previewId_);
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public TrackDescriptor getTrackDescriptors(int i) {
        return (TrackDescriptor) this.trackDescriptors_.get(i);
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public int getTrackDescriptorsCount() {
        return this.trackDescriptors_.size();
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public List<TrackDescriptor> getTrackDescriptorsList() {
        return this.trackDescriptors_;
    }

    public TrackDescriptorOrBuilder getTrackDescriptorsOrBuilder(int i) {
        return (TrackDescriptorOrBuilder) this.trackDescriptors_.get(i);
    }

    public List<? extends TrackDescriptorOrBuilder> getTrackDescriptorsOrBuilderList() {
        return this.trackDescriptors_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public int getTrackNumber() {
        return this.trackNumber_;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean hasAlbum() {
        return (this.bitField0_ & 1) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean hasDiscNumber() {
        return (this.bitField0_ & 32) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean hasHasLyrics() {
        return (this.bitField0_ & 2048) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean hasIs19PlusOnly() {
        return (this.bitField0_ & 65536) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean hasIsCurated() {
        return (this.bitField0_ & 131072) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean hasIsExplicit() {
        return (this.bitField0_ & 128) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean hasIsLocal() {
        return (this.bitField0_ & 512) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean hasIsPremiumOnly() {
        return (this.bitField0_ & 4096) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean hasLength() {
        return (this.bitField0_ & 8) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean hasLink() {
        return (this.bitField0_ & 2) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean hasLocallyPlayable() {
        return (this.bitField0_ & 8192) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 4) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean hasPlayable() {
        return (this.bitField0_ & 16) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean hasPlayableLocalTrack() {
        return (this.bitField0_ & 1024) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean hasPlayableTrackLink() {
        return (this.bitField0_ & 16384) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean hasPopularity() {
        return (this.bitField0_ & 32768) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean hasPreviewId() {
        return (this.bitField0_ & 256) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.TrackMetadataOrBuilder
    public boolean hasTrackNumber() {
        return (this.bitField0_ & 64) != 0;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(TrackMetadata trackMetadata) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(trackMetadata);
    }

    public static TrackMetadata parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (TrackMetadata) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static TrackMetadata parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (TrackMetadata) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static TrackMetadata parseFrom(ByteString byteString) {
        return (TrackMetadata) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addArtist(int i, TrackArtistMetadata trackArtistMetadata) {
        trackArtistMetadata.getClass();
        ensureArtistIsMutable();
        this.artist_.add(i, trackArtistMetadata);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addExtension(int i, Extension extension) {
        extension.getClass();
        ensureExtensionIsMutable();
        this.extension_.add(i, extension);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addTrackDescriptors(int i, TrackDescriptor trackDescriptor) {
        trackDescriptor.getClass();
        ensureTrackDescriptorsIsMutable();
        this.trackDescriptors_.add(i, trackDescriptor);
    }

    public static TrackMetadata parseFrom(ByteString byteString, rl0 rl0Var) {
        return (TrackMetadata) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static TrackMetadata parseFrom(byte[] bArr) {
        return (TrackMetadata) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static TrackMetadata parseFrom(byte[] bArr, rl0 rl0Var) {
        return (TrackMetadata) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static TrackMetadata parseFrom(InputStream inputStream) {
        return (TrackMetadata) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TrackMetadata parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (TrackMetadata) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static TrackMetadata parseFrom(au auVar) {
        return (TrackMetadata) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static TrackMetadata parseFrom(au auVar, rl0 rl0Var) {
        return (TrackMetadata) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
