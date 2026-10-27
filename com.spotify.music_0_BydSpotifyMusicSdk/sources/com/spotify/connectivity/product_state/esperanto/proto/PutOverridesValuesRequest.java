package com.spotify.connectivity.product_state.esperanto.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.WireFormat$FieldType;
import com.google.protobuf.a;
import com.google.protobuf.i;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;
import p.au;
import p.ds1;
import p.h01;
import p.i01;
import p.oe1;
import p.r1;
import p.rl0;
import p.vj1;
import p.wj1;

/* JADX INFO: loaded from: classes.dex */
public final class PutOverridesValuesRequest extends i implements PutOverridesValuesRequestOrBuilder {
    private static final PutOverridesValuesRequest DEFAULT_INSTANCE;
    public static final int PAIRS_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER;
    private MapFieldLite<String, String> pairs_ = MapFieldLite.c;

    /* JADX INFO: renamed from: com.spotify.connectivity.product_state.esperanto.proto.PutOverridesValuesRequest$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements PutOverridesValuesRequestOrBuilder {
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

        public Builder clearPairs() {
            copyOnWrite();
            ((PutOverridesValuesRequest) this.instance).getMutablePairsMap().clear();
            return this;
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ Object clone() {
            return super.clone();
        }

        @Override // com.spotify.connectivity.product_state.esperanto.proto.PutOverridesValuesRequestOrBuilder
        public boolean containsPairs(String str) {
            str.getClass();
            return ((PutOverridesValuesRequest) this.instance).getPairsMap().containsKey(str);
        }

        @Override // p.h01, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.connectivity.product_state.esperanto.proto.PutOverridesValuesRequestOrBuilder
        @Deprecated
        public Map<String, String> getPairs() {
            return getPairsMap();
        }

        @Override // com.spotify.connectivity.product_state.esperanto.proto.PutOverridesValuesRequestOrBuilder
        public int getPairsCount() {
            return ((PutOverridesValuesRequest) this.instance).getPairsMap().size();
        }

        @Override // com.spotify.connectivity.product_state.esperanto.proto.PutOverridesValuesRequestOrBuilder
        public Map<String, String> getPairsMap() {
            return Collections.unmodifiableMap(((PutOverridesValuesRequest) this.instance).getPairsMap());
        }

        @Override // com.spotify.connectivity.product_state.esperanto.proto.PutOverridesValuesRequestOrBuilder
        public String getPairsOrDefault(String str, String str2) {
            str.getClass();
            Map<String, String> pairsMap = ((PutOverridesValuesRequest) this.instance).getPairsMap();
            return pairsMap.containsKey(str) ? pairsMap.get(str) : str2;
        }

        @Override // com.spotify.connectivity.product_state.esperanto.proto.PutOverridesValuesRequestOrBuilder
        public String getPairsOrThrow(String str) {
            str.getClass();
            Map<String, String> pairsMap = ((PutOverridesValuesRequest) this.instance).getPairsMap();
            if (pairsMap.containsKey(str)) {
                return pairsMap.get(str);
            }
            throw new IllegalArgumentException();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder putAllPairs(Map<String, String> map) {
            copyOnWrite();
            ((PutOverridesValuesRequest) this.instance).getMutablePairsMap().putAll(map);
            return this;
        }

        public Builder putPairs(String str, String str2) {
            str.getClass();
            str2.getClass();
            copyOnWrite();
            ((PutOverridesValuesRequest) this.instance).getMutablePairsMap().put(str, str2);
            return this;
        }

        public Builder removePairs(String str) {
            str.getClass();
            copyOnWrite();
            ((PutOverridesValuesRequest) this.instance).getMutablePairsMap().remove(str);
            return this;
        }

        private Builder() {
            super(PutOverridesValuesRequest.DEFAULT_INSTANCE);
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
        public /* bridge */ /* synthetic */ vj1 m176mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m177mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m178mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    public static final class PairsDefaultEntryHolder {
        static final oe1 defaultEntry;

        static {
            WireFormat$FieldType wireFormat$FieldType = WireFormat$FieldType.e;
            defaultEntry = new oe1(wireFormat$FieldType, wireFormat$FieldType, "");
        }

        private PairsDefaultEntryHolder() {
        }
    }

    static {
        PutOverridesValuesRequest putOverridesValuesRequest = new PutOverridesValuesRequest();
        DEFAULT_INSTANCE = putOverridesValuesRequest;
        i.registerDefaultInstance(PutOverridesValuesRequest.class, putOverridesValuesRequest);
    }

    private PutOverridesValuesRequest() {
    }

    public static PutOverridesValuesRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> getMutablePairsMap() {
        return internalGetMutablePairs();
    }

    private MapFieldLite<String, String> internalGetMutablePairs() {
        if (!this.pairs_.e()) {
            this.pairs_ = this.pairs_.g();
        }
        return this.pairs_;
    }

    private MapFieldLite<String, String> internalGetPairs() {
        return this.pairs_;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static PutOverridesValuesRequest parseDelimitedFrom(InputStream inputStream) {
        return (PutOverridesValuesRequest) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static PutOverridesValuesRequest parseFrom(ByteBuffer byteBuffer) {
        return (PutOverridesValuesRequest) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // com.spotify.connectivity.product_state.esperanto.proto.PutOverridesValuesRequestOrBuilder
    public boolean containsPairs(String str) {
        str.getClass();
        return internalGetPairs().containsKey(str);
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"pairs_", PairsDefaultEntryHolder.defaultEntry});
            case 3:
                return new PutOverridesValuesRequest();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (PutOverridesValuesRequest.class) {
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

    @Override // com.spotify.connectivity.product_state.esperanto.proto.PutOverridesValuesRequestOrBuilder
    @Deprecated
    public Map<String, String> getPairs() {
        return getPairsMap();
    }

    @Override // com.spotify.connectivity.product_state.esperanto.proto.PutOverridesValuesRequestOrBuilder
    public int getPairsCount() {
        return internalGetPairs().size();
    }

    @Override // com.spotify.connectivity.product_state.esperanto.proto.PutOverridesValuesRequestOrBuilder
    public Map<String, String> getPairsMap() {
        return Collections.unmodifiableMap(internalGetPairs());
    }

    @Override // com.spotify.connectivity.product_state.esperanto.proto.PutOverridesValuesRequestOrBuilder
    public String getPairsOrDefault(String str, String str2) {
        str.getClass();
        MapFieldLite<String, String> mapFieldLiteInternalGetPairs = internalGetPairs();
        return mapFieldLiteInternalGetPairs.containsKey(str) ? mapFieldLiteInternalGetPairs.get(str) : str2;
    }

    @Override // com.spotify.connectivity.product_state.esperanto.proto.PutOverridesValuesRequestOrBuilder
    public String getPairsOrThrow(String str) {
        str.getClass();
        MapFieldLite<String, String> mapFieldLiteInternalGetPairs = internalGetPairs();
        if (mapFieldLiteInternalGetPairs.containsKey(str)) {
            return mapFieldLiteInternalGetPairs.get(str);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(PutOverridesValuesRequest putOverridesValuesRequest) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(putOverridesValuesRequest);
    }

    public static PutOverridesValuesRequest parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (PutOverridesValuesRequest) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static PutOverridesValuesRequest parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (PutOverridesValuesRequest) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static PutOverridesValuesRequest parseFrom(ByteString byteString) {
        return (PutOverridesValuesRequest) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static PutOverridesValuesRequest parseFrom(ByteString byteString, rl0 rl0Var) {
        return (PutOverridesValuesRequest) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static PutOverridesValuesRequest parseFrom(byte[] bArr) {
        return (PutOverridesValuesRequest) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static PutOverridesValuesRequest parseFrom(byte[] bArr, rl0 rl0Var) {
        return (PutOverridesValuesRequest) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static PutOverridesValuesRequest parseFrom(InputStream inputStream) {
        return (PutOverridesValuesRequest) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static PutOverridesValuesRequest parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (PutOverridesValuesRequest) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static PutOverridesValuesRequest parseFrom(au auVar) {
        return (PutOverridesValuesRequest) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static PutOverridesValuesRequest parseFrom(au auVar, rl0 rl0Var) {
        return (PutOverridesValuesRequest) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
