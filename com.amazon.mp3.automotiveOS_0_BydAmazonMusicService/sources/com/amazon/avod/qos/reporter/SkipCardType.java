package com.amazon.avod.qos.reporter;

import com.amazon.atvtransitiontimecodeservice.types.ElementType;
import com.amazon.avod.util.Preconditions2;
import com.google.common.collect.ImmutableMap;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum SkipCardType {
    Intro,
    Recap;

    private static final ImmutableMap<ElementType, SkipCardType> ELEMENT_TYPE_TO_SKIP_CARD_TYPE;

    static {
        SkipCardType skipCardType = Intro;
        ELEMENT_TYPE_TO_SKIP_CARD_TYPE = (ImmutableMap) Preconditions2.checkFullKeyMapping(ElementType.class, ImmutableMap.builder().put(ElementType.INTRO, skipCardType).put(ElementType.RECAP, Recap).build());
    }

    public static SkipCardType lookup(@Nonnull ElementType elementType) {
        return ELEMENT_TYPE_TO_SKIP_CARD_TYPE.get(elementType);
    }
}
