package com.amazon.mp3.playback.converters;

import com.amazon.digitalmusicxp.inputs.SeedContextInput;
import com.amazon.music.platform.playback.augmentation.models.SeedContextTypeEnum;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SeedContextTransformers.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u000e\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00020\u0003\u001a\u000e\u0010\u0000\u001a\u00060\u0004j\u0002`\u0005*\u00020\u0006*\f\b\u0002\u0010\u0007\"\u00020\u00012\u00020\u0001*\f\b\u0002\u0010\b\"\u00020\u00042\u00020\u0004¨\u0006\t"}, d2 = {"toExternal", "Lcom/amazon/digitalmusicxp/inputs/SeedContextInput;", "Lcom/amazon/mp3/playback/converters/DMCQXPSeedContextInput;", "Lcom/amazon/music/platform/playback/augmentation/models/SeedContextInput;", "Lcom/amazon/digitalmusicxp/enums/SeedContextTypeEnum;", "Lcom/amazon/mp3/playback/converters/DMCQXPSeedContextTypeEnum;", "Lcom/amazon/music/platform/playback/augmentation/models/SeedContextTypeEnum;", "DMCQXPSeedContextInput", "DMCQXPSeedContextTypeEnum", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class SeedContextTransformersKt {

    /* JADX INFO: compiled from: SeedContextTransformers.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SeedContextTypeEnum.values().length];
            iArr[SeedContextTypeEnum.SPOTLIGHT.ordinal()] = 1;
            iArr[SeedContextTypeEnum.INTERLUDE_FACET.ordinal()] = 2;
            iArr[SeedContextTypeEnum.ELIGIBILITY_ID.ordinal()] = 3;
            iArr[SeedContextTypeEnum.APPEND_AUTOPLAY_STATION.ordinal()] = 4;
            iArr[SeedContextTypeEnum.ARTIST_COMMENTARY.ordinal()] = 5;
            iArr[SeedContextTypeEnum.ON_DEMAND_UNLOCK_THROUGH_AD.ordinal()] = 6;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final SeedContextInput toExternal(com.amazon.music.platform.playback.augmentation.models.SeedContextInput seedContextInput) {
        Intrinsics.checkNotNullParameter(seedContextInput, "<this>");
        SeedContextTypeEnum contextType = seedContextInput.getContextType();
        return new SeedContextInput(contextType == null ? null : toExternal(contextType), seedContextInput.getContextValue());
    }

    public static final com.amazon.digitalmusicxp.enums.SeedContextTypeEnum toExternal(SeedContextTypeEnum seedContextTypeEnum) {
        Intrinsics.checkNotNullParameter(seedContextTypeEnum, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[seedContextTypeEnum.ordinal()]) {
            case 1:
                return com.amazon.digitalmusicxp.enums.SeedContextTypeEnum.SPOTLIGHT;
            case 2:
                return com.amazon.digitalmusicxp.enums.SeedContextTypeEnum.INTERLUDE_FACET;
            case 3:
                return com.amazon.digitalmusicxp.enums.SeedContextTypeEnum.ELIGIBILITY_ID;
            case 4:
                return com.amazon.digitalmusicxp.enums.SeedContextTypeEnum.APPEND_AUTOPLAY_STATION;
            case 5:
                return com.amazon.digitalmusicxp.enums.SeedContextTypeEnum.ARTIST_COMMENTARY;
            case 6:
                return com.amazon.digitalmusicxp.enums.SeedContextTypeEnum.ON_DEMAND_UNLOCK_THROUGH_AD;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
