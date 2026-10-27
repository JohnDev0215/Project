package com.spotify.cosmos.util.policy.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.a;
import com.google.protobuf.i;
import com.spotify.extendedmetadata.proto.ExtensionKind;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import p.a2;
import p.au;
import p.ds1;
import p.e51;
import p.h01;
import p.i01;
import p.r1;
import p.r51;
import p.rl0;
import p.s51;
import p.t51;
import p.vj1;
import p.wj1;

/* JADX INFO: loaded from: classes.dex */
public final class ShowDecorationPolicy extends i implements ShowDecorationPolicyOrBuilder {
    public static final int CONSUMPTION_ORDER_FIELD_NUMBER = 10;
    public static final int COPYRIGHTS_FIELD_NUMBER = 12;
    public static final int COVERS_FIELD_NUMBER = 8;
    private static final ShowDecorationPolicy DEFAULT_INSTANCE;
    public static final int DESCRIPTION_FIELD_NUMBER = 3;
    public static final int EXTENSION_FIELD_NUMBER = 16;
    public static final int IS_BOOK_FIELD_NUMBER = 17;
    public static final int IS_CREATOR_CHANNEL_FIELD_NUMBER = 18;
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
    private static final s51 extension_converter_ = new s51() { // from class: com.spotify.cosmos.util.policy.proto.ShowDecorationPolicy.1
        @Override // p.s51
        public ExtensionKind convert(Integer num) {
            ExtensionKind extensionKindA = ExtensionKind.a(num.intValue());
            return extensionKindA == null ? ExtensionKind.UNRECOGNIZED : extensionKindA;
        }
    };
    private boolean consumptionOrder_;
    private boolean copyrights_;
    private boolean covers_;
    private boolean description_;
    private int extensionMemoizedSerializedSize;
    private r51 extension_ = i.emptyIntList();
    private boolean isBook_;
    private boolean isCreatorChannel_;
    private boolean isExplicit_;
    private boolean isMusicAndTalk_;
    private boolean language_;
    private boolean link_;
    private boolean mediaTypeEnum_;
    private boolean name_;
    private boolean numEpisodes_;
    private boolean popularity_;
    private boolean publisher_;
    private boolean trailerUri_;

    /* JADX INFO: renamed from: com.spotify.cosmos.util.policy.proto.ShowDecorationPolicy$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
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

    public static final class Builder extends h01 implements ShowDecorationPolicyOrBuilder {
        public Builder addAllExtension(Iterable<? extends ExtensionKind> iterable) {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).addAllExtension(iterable);
            return this;
        }

        public Builder addAllExtensionValue(Iterable<Integer> iterable) {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).addAllExtensionValue(iterable);
            return this;
        }

        public Builder addExtension(ExtensionKind extensionKind) {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).addExtension(extensionKind);
            return this;
        }

        public Builder addExtensionValue(int i) {
            ((ShowDecorationPolicy) this.instance).addExtensionValue(i);
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
            ((ShowDecorationPolicy) this.instance).clearConsumptionOrder();
            return this;
        }

        public Builder clearCopyrights() {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).clearCopyrights();
            return this;
        }

        public Builder clearCovers() {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).clearCovers();
            return this;
        }

        public Builder clearDescription() {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).clearDescription();
            return this;
        }

        public Builder clearExtension() {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).clearExtension();
            return this;
        }

        public Builder clearIsBook() {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).clearIsBook();
            return this;
        }

        public Builder clearIsCreatorChannel() {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).clearIsCreatorChannel();
            return this;
        }

        public Builder clearIsExplicit() {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).clearIsExplicit();
            return this;
        }

        public Builder clearIsMusicAndTalk() {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).clearIsMusicAndTalk();
            return this;
        }

        public Builder clearLanguage() {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).clearLanguage();
            return this;
        }

        public Builder clearLink() {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).clearLink();
            return this;
        }

        public Builder clearMediaTypeEnum() {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).clearMediaTypeEnum();
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).clearName();
            return this;
        }

        public Builder clearNumEpisodes() {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).clearNumEpisodes();
            return this;
        }

        public Builder clearPopularity() {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).clearPopularity();
            return this;
        }

        public Builder clearPublisher() {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).clearPublisher();
            return this;
        }

        public Builder clearTrailerUri() {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).clearTrailerUri();
            return this;
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ Object clone() {
            return super.clone();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
        public boolean getConsumptionOrder() {
            return ((ShowDecorationPolicy) this.instance).getConsumptionOrder();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
        public boolean getCopyrights() {
            return ((ShowDecorationPolicy) this.instance).getCopyrights();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
        public boolean getCovers() {
            return ((ShowDecorationPolicy) this.instance).getCovers();
        }

        @Override // p.h01, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
        public boolean getDescription() {
            return ((ShowDecorationPolicy) this.instance).getDescription();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
        public ExtensionKind getExtension(int i) {
            return ((ShowDecorationPolicy) this.instance).getExtension(i);
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
        public int getExtensionCount() {
            return ((ShowDecorationPolicy) this.instance).getExtensionCount();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
        public List<ExtensionKind> getExtensionList() {
            return ((ShowDecorationPolicy) this.instance).getExtensionList();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
        public int getExtensionValue(int i) {
            return ((ShowDecorationPolicy) this.instance).getExtensionValue(i);
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
        public List<Integer> getExtensionValueList() {
            return Collections.unmodifiableList(((ShowDecorationPolicy) this.instance).getExtensionValueList());
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
        public boolean getIsBook() {
            return ((ShowDecorationPolicy) this.instance).getIsBook();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
        public boolean getIsCreatorChannel() {
            return ((ShowDecorationPolicy) this.instance).getIsCreatorChannel();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
        public boolean getIsExplicit() {
            return ((ShowDecorationPolicy) this.instance).getIsExplicit();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
        public boolean getIsMusicAndTalk() {
            return ((ShowDecorationPolicy) this.instance).getIsMusicAndTalk();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
        public boolean getLanguage() {
            return ((ShowDecorationPolicy) this.instance).getLanguage();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
        public boolean getLink() {
            return ((ShowDecorationPolicy) this.instance).getLink();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
        public boolean getMediaTypeEnum() {
            return ((ShowDecorationPolicy) this.instance).getMediaTypeEnum();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
        public boolean getName() {
            return ((ShowDecorationPolicy) this.instance).getName();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
        public boolean getNumEpisodes() {
            return ((ShowDecorationPolicy) this.instance).getNumEpisodes();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
        public boolean getPopularity() {
            return ((ShowDecorationPolicy) this.instance).getPopularity();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
        public boolean getPublisher() {
            return ((ShowDecorationPolicy) this.instance).getPublisher();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
        public boolean getTrailerUri() {
            return ((ShowDecorationPolicy) this.instance).getTrailerUri();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder setConsumptionOrder(boolean z) {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).setConsumptionOrder(z);
            return this;
        }

        public Builder setCopyrights(boolean z) {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).setCopyrights(z);
            return this;
        }

        public Builder setCovers(boolean z) {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).setCovers(z);
            return this;
        }

        public Builder setDescription(boolean z) {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).setDescription(z);
            return this;
        }

        public Builder setExtension(int i, ExtensionKind extensionKind) {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).setExtension(i, extensionKind);
            return this;
        }

        public Builder setExtensionValue(int i, int i2) {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).setExtensionValue(i, i2);
            return this;
        }

        public Builder setIsBook(boolean z) {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).setIsBook(z);
            return this;
        }

        public Builder setIsCreatorChannel(boolean z) {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).setIsCreatorChannel(z);
            return this;
        }

        public Builder setIsExplicit(boolean z) {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).setIsExplicit(z);
            return this;
        }

        public Builder setIsMusicAndTalk(boolean z) {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).setIsMusicAndTalk(z);
            return this;
        }

        public Builder setLanguage(boolean z) {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).setLanguage(z);
            return this;
        }

        public Builder setLink(boolean z) {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).setLink(z);
            return this;
        }

        public Builder setMediaTypeEnum(boolean z) {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).setMediaTypeEnum(z);
            return this;
        }

        public Builder setName(boolean z) {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).setName(z);
            return this;
        }

        public Builder setNumEpisodes(boolean z) {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).setNumEpisodes(z);
            return this;
        }

        public Builder setPopularity(boolean z) {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).setPopularity(z);
            return this;
        }

        public Builder setPublisher(boolean z) {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).setPublisher(z);
            return this;
        }

        public Builder setTrailerUri(boolean z) {
            copyOnWrite();
            ((ShowDecorationPolicy) this.instance).setTrailerUri(z);
            return this;
        }

        private Builder() {
            super(ShowDecorationPolicy.DEFAULT_INSTANCE);
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
        public /* bridge */ /* synthetic */ vj1 m229mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m230mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m231mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        ShowDecorationPolicy showDecorationPolicy = new ShowDecorationPolicy();
        DEFAULT_INSTANCE = showDecorationPolicy;
        i.registerDefaultInstance(ShowDecorationPolicy.class, showDecorationPolicy);
    }

    private ShowDecorationPolicy() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllExtension(Iterable<? extends ExtensionKind> iterable) {
        ensureExtensionIsMutable();
        for (ExtensionKind extensionKind : iterable) {
            ((e51) this.extension_).c(extensionKind.getNumber());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllExtensionValue(Iterable<Integer> iterable) {
        ensureExtensionIsMutable();
        Iterator<Integer> it = iterable.iterator();
        while (it.hasNext()) {
            ((e51) this.extension_).c(it.next().intValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addExtension(ExtensionKind extensionKind) {
        extensionKind.getClass();
        ensureExtensionIsMutable();
        ((e51) this.extension_).c(extensionKind.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addExtensionValue(int i) {
        ensureExtensionIsMutable();
        ((e51) this.extension_).c(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearConsumptionOrder() {
        this.consumptionOrder_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCopyrights() {
        this.copyrights_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCovers() {
        this.covers_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDescription() {
        this.description_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearExtension() {
        this.extension_ = i.emptyIntList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsBook() {
        this.isBook_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsCreatorChannel() {
        this.isCreatorChannel_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsExplicit() {
        this.isExplicit_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsMusicAndTalk() {
        this.isMusicAndTalk_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLanguage() {
        this.language_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLink() {
        this.link_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMediaTypeEnum() {
        this.mediaTypeEnum_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNumEpisodes() {
        this.numEpisodes_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPopularity() {
        this.popularity_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPublisher() {
        this.publisher_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTrailerUri() {
        this.trailerUri_ = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void ensureExtensionIsMutable() {
        r51 r51Var = this.extension_;
        if (((a2) r51Var).c) {
            return;
        }
        this.extension_ = i.mutableCopy(r51Var);
    }

    public static ShowDecorationPolicy getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static ShowDecorationPolicy parseDelimitedFrom(InputStream inputStream) {
        return (ShowDecorationPolicy) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ShowDecorationPolicy parseFrom(ByteBuffer byteBuffer) {
        return (ShowDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setConsumptionOrder(boolean z) {
        this.consumptionOrder_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCopyrights(boolean z) {
        this.copyrights_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCovers(boolean z) {
        this.covers_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDescription(boolean z) {
        this.description_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExtension(int i, ExtensionKind extensionKind) {
        extensionKind.getClass();
        ensureExtensionIsMutable();
        ((e51) this.extension_).h(i, extensionKind.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExtensionValue(int i, int i2) {
        ensureExtensionIsMutable();
        ((e51) this.extension_).h(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsBook(boolean z) {
        this.isBook_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsCreatorChannel(boolean z) {
        this.isCreatorChannel_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsExplicit(boolean z) {
        this.isExplicit_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsMusicAndTalk(boolean z) {
        this.isMusicAndTalk_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLanguage(boolean z) {
        this.language_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLink(boolean z) {
        this.link_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMediaTypeEnum(boolean z) {
        this.mediaTypeEnum_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setName(boolean z) {
        this.name_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNumEpisodes(boolean z) {
        this.numEpisodes_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPopularity(boolean z) {
        this.popularity_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPublisher(boolean z) {
        this.publisher_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTrailerUri(boolean z) {
        this.trailerUri_ = z;
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0011\u0000\u0000\u0001\u0012\u0011\u0000\u0001\u0000\u0001\u0007\u0002\u0007\u0003\u0007\u0004\u0007\u0005\u0007\u0006\u0007\u0007\u0007\b\u0007\t\u0007\n\u0007\u000b\u0007\f\u0007\r\u0007\u000e\u0007\u0010,\u0011\u0007\u0012\u0007", new Object[]{"link_", "name_", "description_", "popularity_", "publisher_", "language_", "isExplicit_", "covers_", "numEpisodes_", "consumptionOrder_", "mediaTypeEnum_", "copyrights_", "trailerUri_", "isMusicAndTalk_", "extension_", "isBook_", "isCreatorChannel_"});
            case 3:
                return new ShowDecorationPolicy();
            case 4:
                return new Builder();
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (ShowDecorationPolicy.class) {
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

    @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
    public boolean getConsumptionOrder() {
        return this.consumptionOrder_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
    public boolean getCopyrights() {
        return this.copyrights_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
    public boolean getCovers() {
        return this.covers_;
    }

    @Override // com.google.protobuf.i, p.yj1
    public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
    public boolean getDescription() {
        return this.description_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
    public ExtensionKind getExtension(int i) {
        ExtensionKind extensionKindA = ExtensionKind.a(((e51) this.extension_).e(i));
        return extensionKindA == null ? ExtensionKind.UNRECOGNIZED : extensionKindA;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
    public int getExtensionCount() {
        return ((e51) this.extension_).k;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
    public List<ExtensionKind> getExtensionList() {
        return new t51(this.extension_, extension_converter_);
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
    public int getExtensionValue(int i) {
        return ((e51) this.extension_).e(i);
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
    public List<Integer> getExtensionValueList() {
        return this.extension_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
    public boolean getIsBook() {
        return this.isBook_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
    public boolean getIsCreatorChannel() {
        return this.isCreatorChannel_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
    public boolean getIsExplicit() {
        return this.isExplicit_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
    public boolean getIsMusicAndTalk() {
        return this.isMusicAndTalk_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
    public boolean getLanguage() {
        return this.language_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
    public boolean getLink() {
        return this.link_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
    public boolean getMediaTypeEnum() {
        return this.mediaTypeEnum_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
    public boolean getName() {
        return this.name_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
    public boolean getNumEpisodes() {
        return this.numEpisodes_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
    public boolean getPopularity() {
        return this.popularity_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
    public boolean getPublisher() {
        return this.publisher_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ShowDecorationPolicyOrBuilder
    public boolean getTrailerUri() {
        return this.trailerUri_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(ShowDecorationPolicy showDecorationPolicy) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(showDecorationPolicy);
    }

    public static ShowDecorationPolicy parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (ShowDecorationPolicy) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static ShowDecorationPolicy parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (ShowDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static ShowDecorationPolicy parseFrom(ByteString byteString) {
        return (ShowDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ShowDecorationPolicy parseFrom(ByteString byteString, rl0 rl0Var) {
        return (ShowDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static ShowDecorationPolicy parseFrom(byte[] bArr) {
        return (ShowDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ShowDecorationPolicy parseFrom(byte[] bArr, rl0 rl0Var) {
        return (ShowDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static ShowDecorationPolicy parseFrom(InputStream inputStream) {
        return (ShowDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ShowDecorationPolicy parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (ShowDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static ShowDecorationPolicy parseFrom(au auVar) {
        return (ShowDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static ShowDecorationPolicy parseFrom(au auVar, rl0 rl0Var) {
        return (ShowDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
