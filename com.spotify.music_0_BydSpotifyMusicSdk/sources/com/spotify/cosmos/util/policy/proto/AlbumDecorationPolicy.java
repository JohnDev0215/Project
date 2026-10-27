package com.spotify.cosmos.util.policy.proto;

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
public final class AlbumDecorationPolicy extends i implements AlbumDecorationPolicyOrBuilder {
    public static final int COPYRIGHTS_FIELD_NUMBER = 3;
    public static final int COVERS_FIELD_NUMBER = 4;
    private static final AlbumDecorationPolicy DEFAULT_INSTANCE;
    public static final int IS_PREMIUM_ONLY_FIELD_NUMBER = 9;
    public static final int LINK_FIELD_NUMBER = 1;
    public static final int NAME_FIELD_NUMBER = 2;
    public static final int NUM_DISCS_FIELD_NUMBER = 6;
    public static final int NUM_TRACKS_FIELD_NUMBER = 7;
    private static volatile ds1 PARSER = null;
    public static final int PLAYABILITY_FIELD_NUMBER = 8;
    public static final int YEAR_FIELD_NUMBER = 5;
    private boolean copyrights_;
    private boolean covers_;
    private boolean isPremiumOnly_;
    private boolean link_;
    private boolean name_;
    private boolean numDiscs_;
    private boolean numTracks_;
    private boolean playability_;
    private boolean year_;

    /* JADX INFO: renamed from: com.spotify.cosmos.util.policy.proto.AlbumDecorationPolicy$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements AlbumDecorationPolicyOrBuilder {
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

        public Builder clearCopyrights() {
            copyOnWrite();
            ((AlbumDecorationPolicy) this.instance).clearCopyrights();
            return this;
        }

        public Builder clearCovers() {
            copyOnWrite();
            ((AlbumDecorationPolicy) this.instance).clearCovers();
            return this;
        }

        public Builder clearIsPremiumOnly() {
            copyOnWrite();
            ((AlbumDecorationPolicy) this.instance).clearIsPremiumOnly();
            return this;
        }

        public Builder clearLink() {
            copyOnWrite();
            ((AlbumDecorationPolicy) this.instance).clearLink();
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((AlbumDecorationPolicy) this.instance).clearName();
            return this;
        }

        public Builder clearNumDiscs() {
            copyOnWrite();
            ((AlbumDecorationPolicy) this.instance).clearNumDiscs();
            return this;
        }

        public Builder clearNumTracks() {
            copyOnWrite();
            ((AlbumDecorationPolicy) this.instance).clearNumTracks();
            return this;
        }

        public Builder clearPlayability() {
            copyOnWrite();
            ((AlbumDecorationPolicy) this.instance).clearPlayability();
            return this;
        }

        public Builder clearYear() {
            copyOnWrite();
            ((AlbumDecorationPolicy) this.instance).clearYear();
            return this;
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ Object clone() {
            return super.clone();
        }

        @Override // com.spotify.cosmos.util.policy.proto.AlbumDecorationPolicyOrBuilder
        public boolean getCopyrights() {
            return ((AlbumDecorationPolicy) this.instance).getCopyrights();
        }

        @Override // com.spotify.cosmos.util.policy.proto.AlbumDecorationPolicyOrBuilder
        public boolean getCovers() {
            return ((AlbumDecorationPolicy) this.instance).getCovers();
        }

        @Override // p.h01, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.cosmos.util.policy.proto.AlbumDecorationPolicyOrBuilder
        public boolean getIsPremiumOnly() {
            return ((AlbumDecorationPolicy) this.instance).getIsPremiumOnly();
        }

        @Override // com.spotify.cosmos.util.policy.proto.AlbumDecorationPolicyOrBuilder
        public boolean getLink() {
            return ((AlbumDecorationPolicy) this.instance).getLink();
        }

        @Override // com.spotify.cosmos.util.policy.proto.AlbumDecorationPolicyOrBuilder
        public boolean getName() {
            return ((AlbumDecorationPolicy) this.instance).getName();
        }

        @Override // com.spotify.cosmos.util.policy.proto.AlbumDecorationPolicyOrBuilder
        public boolean getNumDiscs() {
            return ((AlbumDecorationPolicy) this.instance).getNumDiscs();
        }

        @Override // com.spotify.cosmos.util.policy.proto.AlbumDecorationPolicyOrBuilder
        public boolean getNumTracks() {
            return ((AlbumDecorationPolicy) this.instance).getNumTracks();
        }

        @Override // com.spotify.cosmos.util.policy.proto.AlbumDecorationPolicyOrBuilder
        public boolean getPlayability() {
            return ((AlbumDecorationPolicy) this.instance).getPlayability();
        }

        @Override // com.spotify.cosmos.util.policy.proto.AlbumDecorationPolicyOrBuilder
        public boolean getYear() {
            return ((AlbumDecorationPolicy) this.instance).getYear();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder setCopyrights(boolean z) {
            copyOnWrite();
            ((AlbumDecorationPolicy) this.instance).setCopyrights(z);
            return this;
        }

        public Builder setCovers(boolean z) {
            copyOnWrite();
            ((AlbumDecorationPolicy) this.instance).setCovers(z);
            return this;
        }

        public Builder setIsPremiumOnly(boolean z) {
            copyOnWrite();
            ((AlbumDecorationPolicy) this.instance).setIsPremiumOnly(z);
            return this;
        }

        public Builder setLink(boolean z) {
            copyOnWrite();
            ((AlbumDecorationPolicy) this.instance).setLink(z);
            return this;
        }

        public Builder setName(boolean z) {
            copyOnWrite();
            ((AlbumDecorationPolicy) this.instance).setName(z);
            return this;
        }

        public Builder setNumDiscs(boolean z) {
            copyOnWrite();
            ((AlbumDecorationPolicy) this.instance).setNumDiscs(z);
            return this;
        }

        public Builder setNumTracks(boolean z) {
            copyOnWrite();
            ((AlbumDecorationPolicy) this.instance).setNumTracks(z);
            return this;
        }

        public Builder setPlayability(boolean z) {
            copyOnWrite();
            ((AlbumDecorationPolicy) this.instance).setPlayability(z);
            return this;
        }

        public Builder setYear(boolean z) {
            copyOnWrite();
            ((AlbumDecorationPolicy) this.instance).setYear(z);
            return this;
        }

        private Builder() {
            super(AlbumDecorationPolicy.DEFAULT_INSTANCE);
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
        public /* bridge */ /* synthetic */ vj1 m199mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m200mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m201mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        AlbumDecorationPolicy albumDecorationPolicy = new AlbumDecorationPolicy();
        DEFAULT_INSTANCE = albumDecorationPolicy;
        i.registerDefaultInstance(AlbumDecorationPolicy.class, albumDecorationPolicy);
    }

    private AlbumDecorationPolicy() {
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
    public void clearIsPremiumOnly() {
        this.isPremiumOnly_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLink() {
        this.link_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNumDiscs() {
        this.numDiscs_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNumTracks() {
        this.numTracks_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPlayability() {
        this.playability_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearYear() {
        this.year_ = false;
    }

    public static AlbumDecorationPolicy getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static AlbumDecorationPolicy parseDelimitedFrom(InputStream inputStream) {
        return (AlbumDecorationPolicy) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AlbumDecorationPolicy parseFrom(ByteBuffer byteBuffer) {
        return (AlbumDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
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
    public void setIsPremiumOnly(boolean z) {
        this.isPremiumOnly_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLink(boolean z) {
        this.link_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setName(boolean z) {
        this.name_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNumDiscs(boolean z) {
        this.numDiscs_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNumTracks(boolean z) {
        this.numTracks_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayability(boolean z) {
        this.playability_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setYear(boolean z) {
        this.year_ = z;
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\t\u0000\u0000\u0001\t\t\u0000\u0000\u0000\u0001\u0007\u0002\u0007\u0003\u0007\u0004\u0007\u0005\u0007\u0006\u0007\u0007\u0007\b\u0007\t\u0007", new Object[]{"link_", "name_", "copyrights_", "covers_", "year_", "numDiscs_", "numTracks_", "playability_", "isPremiumOnly_"});
            case 3:
                return new AlbumDecorationPolicy();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (AlbumDecorationPolicy.class) {
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

    @Override // com.spotify.cosmos.util.policy.proto.AlbumDecorationPolicyOrBuilder
    public boolean getCopyrights() {
        return this.copyrights_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.AlbumDecorationPolicyOrBuilder
    public boolean getCovers() {
        return this.covers_;
    }

    @Override // com.google.protobuf.i, p.yj1
    public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override // com.spotify.cosmos.util.policy.proto.AlbumDecorationPolicyOrBuilder
    public boolean getIsPremiumOnly() {
        return this.isPremiumOnly_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.AlbumDecorationPolicyOrBuilder
    public boolean getLink() {
        return this.link_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.AlbumDecorationPolicyOrBuilder
    public boolean getName() {
        return this.name_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.AlbumDecorationPolicyOrBuilder
    public boolean getNumDiscs() {
        return this.numDiscs_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.AlbumDecorationPolicyOrBuilder
    public boolean getNumTracks() {
        return this.numTracks_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.AlbumDecorationPolicyOrBuilder
    public boolean getPlayability() {
        return this.playability_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.AlbumDecorationPolicyOrBuilder
    public boolean getYear() {
        return this.year_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(AlbumDecorationPolicy albumDecorationPolicy) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(albumDecorationPolicy);
    }

    public static AlbumDecorationPolicy parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (AlbumDecorationPolicy) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static AlbumDecorationPolicy parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (AlbumDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static AlbumDecorationPolicy parseFrom(ByteString byteString) {
        return (AlbumDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AlbumDecorationPolicy parseFrom(ByteString byteString, rl0 rl0Var) {
        return (AlbumDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static AlbumDecorationPolicy parseFrom(byte[] bArr) {
        return (AlbumDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AlbumDecorationPolicy parseFrom(byte[] bArr, rl0 rl0Var) {
        return (AlbumDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static AlbumDecorationPolicy parseFrom(InputStream inputStream) {
        return (AlbumDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AlbumDecorationPolicy parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (AlbumDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static AlbumDecorationPolicy parseFrom(au auVar) {
        return (AlbumDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static AlbumDecorationPolicy parseFrom(au auVar, rl0 rl0Var) {
        return (AlbumDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
