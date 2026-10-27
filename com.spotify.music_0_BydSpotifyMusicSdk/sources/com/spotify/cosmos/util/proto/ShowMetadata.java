package com.spotify.cosmos.util.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.a;
import com.google.protobuf.i;
import com.spotify.cosmos.util.proto.Extension;
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
public final class ShowMetadata extends i implements ShowMetadataOrBuilder {
    public static final int CONSUMPTION_ORDER_FIELD_NUMBER = 10;
    public static final int COPYRIGHT_FIELD_NUMBER = 12;
    public static final int COVERS_FIELD_NUMBER = 8;
    private static final ShowMetadata DEFAULT_INSTANCE;
    public static final int DESCRIPTION_FIELD_NUMBER = 3;
    public static final int EXTENSION_FIELD_NUMBER = 15;
    public static final int IS_BOOK_FIELD_NUMBER = 16;
    public static final int IS_CREATOR_CHANNEL_FIELD_NUMBER = 17;
    public static final int IS_EXPLICIT_FIELD_NUMBER = 7;
    public static final int IS_MUSIC_AND_TALK_FIELD_NUMBER = 14;
    public static final int LANGUAGE_FIELD_NUMBER = 6;
    public static final int LINK_FIELD_NUMBER = 1;
    public static final int MEDIA_TYPE_ENUM_FIELD_NUMBER = 11;
    public static final int NAME_FIELD_NUMBER = 2;
    public static final int NUM_EPISODES_FIELD_NUMBER = 9;
    private static volatile ds1 PARSER = null;
    public static final int POPULARITY_FIELD_NUMBER = 4;
    public static final int PUBLISHER_FIELD_NUMBER = 5;
    public static final int TRAILER_URI_FIELD_NUMBER = 13;
    private int bitField0_;
    private ImageGroup covers_;
    private boolean isBook_;
    private boolean isCreatorChannel_;
    private boolean isExplicit_;
    private boolean isMusicAndTalk_;
    private int mediaTypeEnum_;
    private int numEpisodes_;
    private int popularity_;
    private String link_ = "";
    private String name_ = "";
    private String description_ = "";
    private String publisher_ = "";
    private String language_ = "";
    private String consumptionOrder_ = "";
    private v51 copyright_ = i.emptyProtobufList();
    private String trailerUri_ = "";
    private v51 extension_ = i.emptyProtobufList();

    /* JADX INFO: renamed from: com.spotify.cosmos.util.proto.ShowMetadata$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements ShowMetadataOrBuilder {
        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        public Builder addAllCopyright(Iterable<String> iterable) {
            copyOnWrite();
            ((ShowMetadata) this.instance).addAllCopyright(iterable);
            return this;
        }

        public Builder addAllExtension(Iterable<? extends Extension> iterable) {
            copyOnWrite();
            ((ShowMetadata) this.instance).addAllExtension(iterable);
            return this;
        }

        public Builder addCopyright(String str) {
            copyOnWrite();
            ((ShowMetadata) this.instance).addCopyright(str);
            return this;
        }

        public Builder addCopyrightBytes(ByteString byteString) {
            copyOnWrite();
            ((ShowMetadata) this.instance).addCopyrightBytes(byteString);
            return this;
        }

        public Builder addExtension(Extension extension) {
            copyOnWrite();
            ((ShowMetadata) this.instance).addExtension(extension);
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

        public Builder clearConsumptionOrder() {
            copyOnWrite();
            ((ShowMetadata) this.instance).clearConsumptionOrder();
            return this;
        }

        public Builder clearCopyright() {
            copyOnWrite();
            ((ShowMetadata) this.instance).clearCopyright();
            return this;
        }

        public Builder clearCovers() {
            copyOnWrite();
            ((ShowMetadata) this.instance).clearCovers();
            return this;
        }

        public Builder clearDescription() {
            copyOnWrite();
            ((ShowMetadata) this.instance).clearDescription();
            return this;
        }

        public Builder clearExtension() {
            copyOnWrite();
            ((ShowMetadata) this.instance).clearExtension();
            return this;
        }

        public Builder clearIsBook() {
            copyOnWrite();
            ((ShowMetadata) this.instance).clearIsBook();
            return this;
        }

        public Builder clearIsCreatorChannel() {
            copyOnWrite();
            ((ShowMetadata) this.instance).clearIsCreatorChannel();
            return this;
        }

        public Builder clearIsExplicit() {
            copyOnWrite();
            ((ShowMetadata) this.instance).clearIsExplicit();
            return this;
        }

        public Builder clearIsMusicAndTalk() {
            copyOnWrite();
            ((ShowMetadata) this.instance).clearIsMusicAndTalk();
            return this;
        }

        public Builder clearLanguage() {
            copyOnWrite();
            ((ShowMetadata) this.instance).clearLanguage();
            return this;
        }

        public Builder clearLink() {
            copyOnWrite();
            ((ShowMetadata) this.instance).clearLink();
            return this;
        }

        public Builder clearMediaTypeEnum() {
            copyOnWrite();
            ((ShowMetadata) this.instance).clearMediaTypeEnum();
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((ShowMetadata) this.instance).clearName();
            return this;
        }

        public Builder clearNumEpisodes() {
            copyOnWrite();
            ((ShowMetadata) this.instance).clearNumEpisodes();
            return this;
        }

        public Builder clearPopularity() {
            copyOnWrite();
            ((ShowMetadata) this.instance).clearPopularity();
            return this;
        }

        public Builder clearPublisher() {
            copyOnWrite();
            ((ShowMetadata) this.instance).clearPublisher();
            return this;
        }

        public Builder clearTrailerUri() {
            copyOnWrite();
            ((ShowMetadata) this.instance).clearTrailerUri();
            return this;
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ Object clone() {
            return super.clone();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public String getConsumptionOrder() {
            return ((ShowMetadata) this.instance).getConsumptionOrder();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public ByteString getConsumptionOrderBytes() {
            return ((ShowMetadata) this.instance).getConsumptionOrderBytes();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public String getCopyright(int i) {
            return ((ShowMetadata) this.instance).getCopyright(i);
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public ByteString getCopyrightBytes(int i) {
            return ((ShowMetadata) this.instance).getCopyrightBytes(i);
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public int getCopyrightCount() {
            return ((ShowMetadata) this.instance).getCopyrightCount();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public List<String> getCopyrightList() {
            return Collections.unmodifiableList(((ShowMetadata) this.instance).getCopyrightList());
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public ImageGroup getCovers() {
            return ((ShowMetadata) this.instance).getCovers();
        }

        @Override // p.h01, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public String getDescription() {
            return ((ShowMetadata) this.instance).getDescription();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public ByteString getDescriptionBytes() {
            return ((ShowMetadata) this.instance).getDescriptionBytes();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public Extension getExtension(int i) {
            return ((ShowMetadata) this.instance).getExtension(i);
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public int getExtensionCount() {
            return ((ShowMetadata) this.instance).getExtensionCount();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public List<Extension> getExtensionList() {
            return Collections.unmodifiableList(((ShowMetadata) this.instance).getExtensionList());
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public boolean getIsBook() {
            return ((ShowMetadata) this.instance).getIsBook();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public boolean getIsCreatorChannel() {
            return ((ShowMetadata) this.instance).getIsCreatorChannel();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public boolean getIsExplicit() {
            return ((ShowMetadata) this.instance).getIsExplicit();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public boolean getIsMusicAndTalk() {
            return ((ShowMetadata) this.instance).getIsMusicAndTalk();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public String getLanguage() {
            return ((ShowMetadata) this.instance).getLanguage();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public ByteString getLanguageBytes() {
            return ((ShowMetadata) this.instance).getLanguageBytes();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public String getLink() {
            return ((ShowMetadata) this.instance).getLink();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public ByteString getLinkBytes() {
            return ((ShowMetadata) this.instance).getLinkBytes();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public int getMediaTypeEnum() {
            return ((ShowMetadata) this.instance).getMediaTypeEnum();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public String getName() {
            return ((ShowMetadata) this.instance).getName();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public ByteString getNameBytes() {
            return ((ShowMetadata) this.instance).getNameBytes();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public int getNumEpisodes() {
            return ((ShowMetadata) this.instance).getNumEpisodes();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public int getPopularity() {
            return ((ShowMetadata) this.instance).getPopularity();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public String getPublisher() {
            return ((ShowMetadata) this.instance).getPublisher();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public ByteString getPublisherBytes() {
            return ((ShowMetadata) this.instance).getPublisherBytes();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public String getTrailerUri() {
            return ((ShowMetadata) this.instance).getTrailerUri();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public ByteString getTrailerUriBytes() {
            return ((ShowMetadata) this.instance).getTrailerUriBytes();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public boolean hasConsumptionOrder() {
            return ((ShowMetadata) this.instance).hasConsumptionOrder();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public boolean hasCovers() {
            return ((ShowMetadata) this.instance).hasCovers();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public boolean hasDescription() {
            return ((ShowMetadata) this.instance).hasDescription();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public boolean hasIsBook() {
            return ((ShowMetadata) this.instance).hasIsBook();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public boolean hasIsCreatorChannel() {
            return ((ShowMetadata) this.instance).hasIsCreatorChannel();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public boolean hasIsExplicit() {
            return ((ShowMetadata) this.instance).hasIsExplicit();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public boolean hasIsMusicAndTalk() {
            return ((ShowMetadata) this.instance).hasIsMusicAndTalk();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public boolean hasLanguage() {
            return ((ShowMetadata) this.instance).hasLanguage();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public boolean hasLink() {
            return ((ShowMetadata) this.instance).hasLink();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public boolean hasMediaTypeEnum() {
            return ((ShowMetadata) this.instance).hasMediaTypeEnum();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public boolean hasName() {
            return ((ShowMetadata) this.instance).hasName();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public boolean hasNumEpisodes() {
            return ((ShowMetadata) this.instance).hasNumEpisodes();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public boolean hasPopularity() {
            return ((ShowMetadata) this.instance).hasPopularity();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public boolean hasPublisher() {
            return ((ShowMetadata) this.instance).hasPublisher();
        }

        @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
        public boolean hasTrailerUri() {
            return ((ShowMetadata) this.instance).hasTrailerUri();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        public Builder mergeCovers(ImageGroup imageGroup) {
            copyOnWrite();
            ((ShowMetadata) this.instance).mergeCovers(imageGroup);
            return this;
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder removeExtension(int i) {
            copyOnWrite();
            ((ShowMetadata) this.instance).removeExtension(i);
            return this;
        }

        public Builder setConsumptionOrder(String str) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setConsumptionOrder(str);
            return this;
        }

        public Builder setConsumptionOrderBytes(ByteString byteString) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setConsumptionOrderBytes(byteString);
            return this;
        }

        public Builder setCopyright(int i, String str) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setCopyright(i, str);
            return this;
        }

        public Builder setCovers(ImageGroup imageGroup) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setCovers(imageGroup);
            return this;
        }

        public Builder setDescription(String str) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setDescription(str);
            return this;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setDescriptionBytes(byteString);
            return this;
        }

        public Builder setExtension(int i, Extension extension) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setExtension(i, extension);
            return this;
        }

        public Builder setIsBook(boolean z) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setIsBook(z);
            return this;
        }

        public Builder setIsCreatorChannel(boolean z) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setIsCreatorChannel(z);
            return this;
        }

        public Builder setIsExplicit(boolean z) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setIsExplicit(z);
            return this;
        }

        public Builder setIsMusicAndTalk(boolean z) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setIsMusicAndTalk(z);
            return this;
        }

        public Builder setLanguage(String str) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setLanguage(str);
            return this;
        }

        public Builder setLanguageBytes(ByteString byteString) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setLanguageBytes(byteString);
            return this;
        }

        public Builder setLink(String str) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setLink(str);
            return this;
        }

        public Builder setLinkBytes(ByteString byteString) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setLinkBytes(byteString);
            return this;
        }

        public Builder setMediaTypeEnum(int i) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setMediaTypeEnum(i);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setNameBytes(byteString);
            return this;
        }

        public Builder setNumEpisodes(int i) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setNumEpisodes(i);
            return this;
        }

        public Builder setPopularity(int i) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setPopularity(i);
            return this;
        }

        public Builder setPublisher(String str) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setPublisher(str);
            return this;
        }

        public Builder setPublisherBytes(ByteString byteString) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setPublisherBytes(byteString);
            return this;
        }

        public Builder setTrailerUri(String str) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setTrailerUri(str);
            return this;
        }

        public Builder setTrailerUriBytes(ByteString byteString) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setTrailerUriBytes(byteString);
            return this;
        }

        private Builder() {
            super(ShowMetadata.DEFAULT_INSTANCE);
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ r1 clone() {
            return super.clone();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        public Builder addExtension(int i, Extension extension) {
            copyOnWrite();
            ((ShowMetadata) this.instance).addExtension(i, extension);
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

        public Builder setCovers(ImageGroup.Builder builder) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setCovers((ImageGroup) builder.build());
            return this;
        }

        public Builder setExtension(int i, Extension.Builder builder) {
            copyOnWrite();
            ((ShowMetadata) this.instance).setExtension(i, (Extension) builder.build());
            return this;
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString) {
            return super.mergeFrom(byteString);
        }

        public Builder addExtension(Extension.Builder builder) {
            copyOnWrite();
            ((ShowMetadata) this.instance).addExtension((Extension) builder.build());
            return this;
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString, rl0 rl0Var) {
            return super.mergeFrom(byteString, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream) {
            return super.mergeFrom(inputStream);
        }

        public Builder addExtension(int i, Extension.Builder builder) {
            copyOnWrite();
            ((ShowMetadata) this.instance).addExtension(i, (Extension) builder.build());
            return this;
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream, rl0 rl0Var) {
            return super.mergeFrom(inputStream, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(au auVar) {
            return super.mergeFrom(auVar);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m289mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m290mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m291mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        ShowMetadata showMetadata = new ShowMetadata();
        DEFAULT_INSTANCE = showMetadata;
        i.registerDefaultInstance(ShowMetadata.class, showMetadata);
    }

    private ShowMetadata() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllCopyright(Iterable<String> iterable) {
        ensureCopyrightIsMutable();
        a.addAll((Iterable) iterable, (List) this.copyright_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllExtension(Iterable<? extends Extension> iterable) {
        ensureExtensionIsMutable();
        a.addAll((Iterable) iterable, (List) this.extension_);
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
    public void addExtension(Extension extension) {
        extension.getClass();
        ensureExtensionIsMutable();
        this.extension_.add(extension);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearConsumptionOrder() {
        this.bitField0_ &= -513;
        this.consumptionOrder_ = getDefaultInstance().getConsumptionOrder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCopyright() {
        this.copyright_ = i.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCovers() {
        this.covers_ = null;
        this.bitField0_ &= -129;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDescription() {
        this.bitField0_ &= -5;
        this.description_ = getDefaultInstance().getDescription();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearExtension() {
        this.extension_ = i.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsBook() {
        this.bitField0_ &= -8193;
        this.isBook_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsCreatorChannel() {
        this.bitField0_ &= -16385;
        this.isCreatorChannel_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsExplicit() {
        this.bitField0_ &= -65;
        this.isExplicit_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsMusicAndTalk() {
        this.bitField0_ &= -4097;
        this.isMusicAndTalk_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLanguage() {
        this.bitField0_ &= -33;
        this.language_ = getDefaultInstance().getLanguage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLink() {
        this.bitField0_ &= -2;
        this.link_ = getDefaultInstance().getLink();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMediaTypeEnum() {
        this.bitField0_ &= -1025;
        this.mediaTypeEnum_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.bitField0_ &= -3;
        this.name_ = getDefaultInstance().getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNumEpisodes() {
        this.bitField0_ &= -257;
        this.numEpisodes_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPopularity() {
        this.bitField0_ &= -9;
        this.popularity_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPublisher() {
        this.bitField0_ &= -17;
        this.publisher_ = getDefaultInstance().getPublisher();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTrailerUri() {
        this.bitField0_ &= -2049;
        this.trailerUri_ = getDefaultInstance().getTrailerUri();
    }

    private void ensureCopyrightIsMutable() {
        v51 v51Var = this.copyright_;
        if (((a2) v51Var).c) {
            return;
        }
        this.copyright_ = i.mutableCopy(v51Var);
    }

    private void ensureExtensionIsMutable() {
        v51 v51Var = this.extension_;
        if (((a2) v51Var).c) {
            return;
        }
        this.extension_ = i.mutableCopy(v51Var);
    }

    public static ShowMetadata getDefaultInstance() {
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
        this.bitField0_ |= 128;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static ShowMetadata parseDelimitedFrom(InputStream inputStream) {
        return (ShowMetadata) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ShowMetadata parseFrom(ByteBuffer byteBuffer) {
        return (ShowMetadata) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeExtension(int i) {
        ensureExtensionIsMutable();
        this.extension_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setConsumptionOrder(String str) {
        str.getClass();
        this.bitField0_ |= 512;
        this.consumptionOrder_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setConsumptionOrderBytes(ByteString byteString) {
        this.consumptionOrder_ = byteString.s();
        this.bitField0_ |= 512;
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
        this.bitField0_ |= 128;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDescription(String str) {
        str.getClass();
        this.bitField0_ |= 4;
        this.description_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDescriptionBytes(ByteString byteString) {
        this.description_ = byteString.s();
        this.bitField0_ |= 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExtension(int i, Extension extension) {
        extension.getClass();
        ensureExtensionIsMutable();
        this.extension_.set(i, extension);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsBook(boolean z) {
        this.bitField0_ |= 8192;
        this.isBook_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsCreatorChannel(boolean z) {
        this.bitField0_ |= 16384;
        this.isCreatorChannel_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsExplicit(boolean z) {
        this.bitField0_ |= 64;
        this.isExplicit_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsMusicAndTalk(boolean z) {
        this.bitField0_ |= 4096;
        this.isMusicAndTalk_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLanguage(String str) {
        str.getClass();
        this.bitField0_ |= 32;
        this.language_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLanguageBytes(ByteString byteString) {
        this.language_ = byteString.s();
        this.bitField0_ |= 32;
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
    public void setMediaTypeEnum(int i) {
        this.bitField0_ |= 1024;
        this.mediaTypeEnum_ = i;
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
    public void setNumEpisodes(int i) {
        this.bitField0_ |= 256;
        this.numEpisodes_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPopularity(int i) {
        this.bitField0_ |= 8;
        this.popularity_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPublisher(String str) {
        str.getClass();
        this.bitField0_ |= 16;
        this.publisher_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPublisherBytes(ByteString byteString) {
        this.publisher_ = byteString.s();
        this.bitField0_ |= 16;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTrailerUri(String str) {
        str.getClass();
        this.bitField0_ |= 2048;
        this.trailerUri_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTrailerUriBytes(ByteString byteString) {
        this.trailerUri_ = byteString.s();
        this.bitField0_ |= 2048;
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0011\u0000\u0001\u0001\u0011\u0011\u0000\u0002\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဋ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဇ\u0006\bဉ\u0007\tဋ\b\nဈ\t\u000bင\n\f\u001a\rဈ\u000b\u000eဇ\f\u000f\u001b\u0010ဇ\r\u0011ဇ\u000e", new Object[]{"bitField0_", "link_", "name_", "description_", "popularity_", "publisher_", "language_", "isExplicit_", "covers_", "numEpisodes_", "consumptionOrder_", "mediaTypeEnum_", "copyright_", "trailerUri_", "isMusicAndTalk_", "extension_", Extension.class, "isBook_", "isCreatorChannel_"});
            case 3:
                return new ShowMetadata();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (ShowMetadata.class) {
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

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public String getConsumptionOrder() {
        return this.consumptionOrder_;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public ByteString getConsumptionOrderBytes() {
        return ByteString.d(this.consumptionOrder_);
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public String getCopyright(int i) {
        return (String) this.copyright_.get(i);
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public ByteString getCopyrightBytes(int i) {
        return ByteString.d((String) this.copyright_.get(i));
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public int getCopyrightCount() {
        return this.copyright_.size();
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public List<String> getCopyrightList() {
        return this.copyright_;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public ImageGroup getCovers() {
        ImageGroup imageGroup = this.covers_;
        return imageGroup == null ? ImageGroup.getDefaultInstance() : imageGroup;
    }

    @Override // com.google.protobuf.i, p.yj1
    public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public String getDescription() {
        return this.description_;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public ByteString getDescriptionBytes() {
        return ByteString.d(this.description_);
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public Extension getExtension(int i) {
        return (Extension) this.extension_.get(i);
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public int getExtensionCount() {
        return this.extension_.size();
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public List<Extension> getExtensionList() {
        return this.extension_;
    }

    public ExtensionOrBuilder getExtensionOrBuilder(int i) {
        return (ExtensionOrBuilder) this.extension_.get(i);
    }

    public List<? extends ExtensionOrBuilder> getExtensionOrBuilderList() {
        return this.extension_;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public boolean getIsBook() {
        return this.isBook_;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public boolean getIsCreatorChannel() {
        return this.isCreatorChannel_;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public boolean getIsExplicit() {
        return this.isExplicit_;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public boolean getIsMusicAndTalk() {
        return this.isMusicAndTalk_;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public String getLanguage() {
        return this.language_;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public ByteString getLanguageBytes() {
        return ByteString.d(this.language_);
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public String getLink() {
        return this.link_;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public ByteString getLinkBytes() {
        return ByteString.d(this.link_);
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public int getMediaTypeEnum() {
        return this.mediaTypeEnum_;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public ByteString getNameBytes() {
        return ByteString.d(this.name_);
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public int getNumEpisodes() {
        return this.numEpisodes_;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public int getPopularity() {
        return this.popularity_;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public String getPublisher() {
        return this.publisher_;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public ByteString getPublisherBytes() {
        return ByteString.d(this.publisher_);
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public String getTrailerUri() {
        return this.trailerUri_;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public ByteString getTrailerUriBytes() {
        return ByteString.d(this.trailerUri_);
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public boolean hasConsumptionOrder() {
        return (this.bitField0_ & 512) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public boolean hasCovers() {
        return (this.bitField0_ & 128) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public boolean hasDescription() {
        return (this.bitField0_ & 4) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public boolean hasIsBook() {
        return (this.bitField0_ & 8192) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public boolean hasIsCreatorChannel() {
        return (this.bitField0_ & 16384) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public boolean hasIsExplicit() {
        return (this.bitField0_ & 64) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public boolean hasIsMusicAndTalk() {
        return (this.bitField0_ & 4096) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public boolean hasLanguage() {
        return (this.bitField0_ & 32) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public boolean hasLink() {
        return (this.bitField0_ & 1) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public boolean hasMediaTypeEnum() {
        return (this.bitField0_ & 1024) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 2) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public boolean hasNumEpisodes() {
        return (this.bitField0_ & 256) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public boolean hasPopularity() {
        return (this.bitField0_ & 8) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public boolean hasPublisher() {
        return (this.bitField0_ & 16) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ShowMetadataOrBuilder
    public boolean hasTrailerUri() {
        return (this.bitField0_ & 2048) != 0;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(ShowMetadata showMetadata) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(showMetadata);
    }

    public static ShowMetadata parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (ShowMetadata) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static ShowMetadata parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (ShowMetadata) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static ShowMetadata parseFrom(ByteString byteString) {
        return (ShowMetadata) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addExtension(int i, Extension extension) {
        extension.getClass();
        ensureExtensionIsMutable();
        this.extension_.add(i, extension);
    }

    public static ShowMetadata parseFrom(ByteString byteString, rl0 rl0Var) {
        return (ShowMetadata) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static ShowMetadata parseFrom(byte[] bArr) {
        return (ShowMetadata) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ShowMetadata parseFrom(byte[] bArr, rl0 rl0Var) {
        return (ShowMetadata) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static ShowMetadata parseFrom(InputStream inputStream) {
        return (ShowMetadata) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ShowMetadata parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (ShowMetadata) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static ShowMetadata parseFrom(au auVar) {
        return (ShowMetadata) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static ShowMetadata parseFrom(au auVar, rl0 rl0Var) {
        return (ShowMetadata) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
