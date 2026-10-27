package com.amazon.music.downloads;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt___SequencesKt;

/* JADX INFO: compiled from: GroupDownloadProgressCalculator.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/downloads/GroupDownloadProgressCalculator;", "", "()V", "calculate", "", ContextMappingConstants.ITEMS, "", "Lcom/amazon/music/downloads/Item;", "DMMDownloadManager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class GroupDownloadProgressCalculator {
    public static final GroupDownloadProgressCalculator INSTANCE = new GroupDownloadProgressCalculator();

    private GroupDownloadProgressCalculator() {
    }

    @JvmStatic
    public static final float calculate(Collection<? extends Item> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        ArrayList arrayList = new ArrayList();
        for (Object obj : items) {
            if (((Item) obj).getDownloadState() != DownloadState.CANCELED) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            return 0.0f;
        }
        return (float) SequencesKt___SequencesKt.averageOfFloat(SequencesKt___SequencesKt.map(CollectionsKt___CollectionsKt.asSequence(arrayList), new Function1<Item, Float>() { // from class: com.amazon.music.downloads.GroupDownloadProgressCalculator.calculate.1
            @Override // kotlin.jvm.functions.Function1
            public final Float invoke(Item it) {
                Intrinsics.checkNotNullParameter(it, "it");
                float percentageDownloaded = (it.getDownloadState() != DownloadState.DOWNLOADING && it.getDownloadState() == DownloadState.DOWNLOADED) ? 100.0f : it.getPercentageDownloaded();
                return Float.valueOf(percentageDownloaded);
            }
        }));
    }
}
