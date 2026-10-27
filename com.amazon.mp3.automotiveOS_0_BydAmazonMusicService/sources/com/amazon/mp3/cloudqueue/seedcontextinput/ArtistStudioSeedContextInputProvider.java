package com.amazon.mp3.cloudqueue.seedcontextinput;

import androidx.annotation.VisibleForTesting;
import com.amazon.digitalmusicxp.enums.PlayModeEnum;
import com.amazon.digitalmusicxp.enums.SeedContextTypeEnum;
import com.amazon.digitalmusicxp.inputs.QueueSeedContextInput;
import com.amazon.digitalmusicxp.inputs.SeedContextInput;
import com.amazon.mp3.featuregate.Feature;
import com.fasterxml.jackson.core.base.GeneratorBase;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;

/* JADX INFO: compiled from: ArtistStudioSeedContextInputProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007J\u0012\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u0010\u0010\u000e\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fR\"\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0002\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/cloudqueue/seedcontextinput/ArtistStudioSeedContextInputProvider;", "", "()V", "ARTIST_STUDIO_SEED_CONTEXT", "", "Lcom/amazon/digitalmusicxp/inputs/SeedContextInput;", "getARTIST_STUDIO_SEED_CONTEXT$annotations", "getARTIST_STUDIO_SEED_CONTEXT", "()Ljava/util/List;", "isAugmentedShuffle", "", "seed", "Lcom/amazon/digitalmusicxp/inputs/QueueSeedContextInput;", "provideSeedContextInput", "shouldProvideSeedContextInput", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ArtistStudioSeedContextInputProvider {
    public static final ArtistStudioSeedContextInputProvider INSTANCE = new ArtistStudioSeedContextInputProvider();
    private static final List<SeedContextInput> ARTIST_STUDIO_SEED_CONTEXT = CollectionsKt__CollectionsJVMKt.listOf(new SeedContextInput(SeedContextTypeEnum.INTERLUDE_FACET, "DYNAMIC"));

    private ArtistStudioSeedContextInputProvider() {
    }

    public final List<SeedContextInput> getARTIST_STUDIO_SEED_CONTEXT() {
        return ARTIST_STUDIO_SEED_CONTEXT;
    }

    public final boolean shouldProvideSeedContextInput(QueueSeedContextInput seed) {
        return isAugmentedShuffle(seed) && Feature.artist_studio_shuffle_sonic_rush.isEnabled();
    }

    public final QueueSeedContextInput provideSeedContextInput(QueueSeedContextInput seed) {
        if (seed == null) {
            return null;
        }
        return QueueSeedContextInput.copy$default(seed, null, null, null, null, null, null, null, null, null, null, null, null, null, INSTANCE.getARTIST_STUDIO_SEED_CONTEXT(), null, null, GeneratorBase.SURR2_LAST, null);
    }

    @VisibleForTesting
    public final boolean isAugmentedShuffle(QueueSeedContextInput seed) {
        return (seed == null ? null : seed.getPlayMode()) == PlayModeEnum.AUGMENTED;
    }
}
