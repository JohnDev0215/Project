package com.amazon.mp3.playback.augmentation;

import com.amazon.digitalmusicxp.inputs.QueueSeedContextInput;
import com.amazon.mp3.playback.converters.SeedContextTransformersKt;
import com.amazon.music.platform.playback.augmentation.SeedContextAugmentation;
import com.amazon.music.platform.playback.augmentation.models.SeedContextInput;
import com.fasterxml.jackson.core.base.GeneratorBase;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CloudQueueAugmentation.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¨\u0006\u0005"}, d2 = {"augmentSeedContextWith", "Lcom/amazon/digitalmusicxp/inputs/QueueSeedContextInput;", "augmentations", "", "Lcom/amazon/music/platform/playback/augmentation/SeedContextAugmentation;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class CloudQueueAugmentationKt {
    public static final QueueSeedContextInput augmentSeedContextWith(QueueSeedContextInput queueSeedContextInput, List<? extends SeedContextAugmentation> augmentations) {
        Intrinsics.checkNotNullParameter(queueSeedContextInput, "<this>");
        Intrinsics.checkNotNullParameter(augmentations, "augmentations");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(augmentations, 10));
        Iterator<T> it = augmentations.iterator();
        while (it.hasNext()) {
            arrayList.add(((SeedContextAugmentation) it.next()).retrieveSeedContextInput());
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.add(SeedContextTransformersKt.toExternal((SeedContextInput) it2.next()));
        }
        return QueueSeedContextInput.copy$default(queueSeedContextInput, null, null, null, null, null, null, null, null, null, null, null, null, null, arrayList2, null, null, GeneratorBase.SURR2_LAST, null);
    }
}
