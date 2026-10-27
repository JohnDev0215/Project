package com.android.car.developeroptions.intelligence;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.Parser;

/* JADX INFO: loaded from: classes.dex */
public final class ContextualCardProto$ContextualCard extends GeneratedMessageLite<ContextualCardProto$ContextualCard, Builder> implements Object {
    public static final int CARDNAME_FIELD_NUMBER = 3;
    public static final int CARD_CATEGORY_FIELD_NUMBER = 4;
    public static final int CARD_SCORE_FIELD_NUMBER = 5;
    private static final ContextualCardProto$ContextualCard DEFAULT_INSTANCE;
    private static volatile Parser<ContextualCardProto$ContextualCard> PARSER = null;
    public static final int SLICEURI_FIELD_NUMBER = 1;
    private int bitField0_;
    private int cardCategory_;
    private double cardScore_;
    private String sliceUri_ = "";
    private String cardName_ = "";

    private ContextualCardProto$ContextualCard() {
    }

    public enum Category implements Internal.EnumLite {
        DEFAULT(0),
        SUGGESTION(1),
        POSSIBLE(2),
        IMPORTANT(3),
        DEFERRED_SETUP(5);

        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static Category forNumber(int i) {
            if (i == 0) {
                return DEFAULT;
            }
            if (i == 1) {
                return SUGGESTION;
            }
            if (i == 2) {
                return POSSIBLE;
            }
            if (i == 3) {
                return IMPORTANT;
            }
            if (i != 5) {
                return null;
            }
            return DEFERRED_SETUP;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return CategoryVerifier.INSTANCE;
        }

        private static final class CategoryVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new CategoryVerifier();

            private CategoryVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return Category.forNumber(i) != null;
            }
        }

        Category(int i) {
            this.value = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSliceUri(String str) {
        if (str == null) {
            throw null;
        }
        this.bitField0_ |= 1;
        this.sliceUri_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCardName(String str) {
        if (str == null) {
            throw null;
        }
        this.bitField0_ |= 2;
        this.cardName_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCardScore(double d) {
        this.bitField0_ |= 8;
        this.cardScore_ = d;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ContextualCardProto$ContextualCard, Builder> implements Object {
        /* synthetic */ Builder(ContextualCardProto$1 contextualCardProto$1) {
            this();
        }

        private Builder() {
            super(ContextualCardProto$ContextualCard.DEFAULT_INSTANCE);
        }

        public Builder setSliceUri(String str) {
            copyOnWrite();
            ((ContextualCardProto$ContextualCard) this.instance).setSliceUri(str);
            return this;
        }

        public Builder setCardName(String str) {
            copyOnWrite();
            ((ContextualCardProto$ContextualCard) this.instance).setCardName(str);
            return this;
        }

        public Builder setCardScore(double d) {
            copyOnWrite();
            ((ContextualCardProto$ContextualCard) this.instance).setCardScore(d);
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        ContextualCardProto$1 contextualCardProto$1 = null;
        switch (ContextualCardProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new ContextualCardProto$ContextualCard();
            case 2:
                return new Builder(contextualCardProto$1);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0005\u0004\u0000\u0000\u0000\u0001\b\u0000\u0003\b\u0001\u0004\f\u0002\u0005\u0000\u0003", new Object[]{"bitField0_", "sliceUri_", "cardName_", "cardCategory_", Category.internalGetVerifier(), "cardScore_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<ContextualCardProto$ContextualCard> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (ContextualCardProto$ContextualCard.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                }
                return defaultInstanceBasedParser;
            case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        ContextualCardProto$ContextualCard contextualCardProto$ContextualCard = new ContextualCardProto$ContextualCard();
        DEFAULT_INSTANCE = contextualCardProto$ContextualCard;
        GeneratedMessageLite.registerDefaultInstance(ContextualCardProto$ContextualCard.class, contextualCardProto$ContextualCard);
    }
}
