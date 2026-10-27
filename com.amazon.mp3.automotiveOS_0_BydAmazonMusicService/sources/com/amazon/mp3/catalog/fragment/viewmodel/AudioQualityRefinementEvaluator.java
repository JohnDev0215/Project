package com.amazon.mp3.catalog.fragment.viewmodel;

import com.amazon.mp3.view.refinements.RefinementFilterType;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RefinementEvaluator.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0016R'\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00040\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/AudioQualityRefinementEvaluator;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementEvaluator;", "contentEncodings", "", "", "(Ljava/util/Set;)V", "audioQualityFilterMap", "", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "getAudioQualityFilterMap", "()Ljava/util/Map;", "audioQualityFilterMap$delegate", "Lkotlin/Lazy;", "isEligible", "", "refinementContentType", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AudioQualityRefinementEvaluator implements RefinementEvaluator {

    /* JADX INFO: renamed from: audioQualityFilterMap$delegate, reason: from kotlin metadata */
    private final Lazy audioQualityFilterMap = LazyKt__LazyJVMKt.lazy(new Function0<Map<RefinementFilterType, ? extends String>>() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.AudioQualityRefinementEvaluator$audioQualityFilterMap$2
        @Override // kotlin.jvm.functions.Function0
        public final Map<RefinementFilterType, ? extends String> invoke() {
            return MapsKt__MapsKt.mapOf(TuplesKt.to(RefinementFilterType.RA360, "ra360Available"), TuplesKt.to(RefinementFilterType.ATMOS, "atmosAvailable"), TuplesKt.to(RefinementFilterType.ULTRA_HD, "uhdAvailable"));
        }
    });
    private final Set<String> contentEncodings;

    public AudioQualityRefinementEvaluator(Set<String> set) {
        this.contentEncodings = set;
    }

    private final Map<RefinementFilterType, String> getAudioQualityFilterMap() {
        return (Map) this.audioQualityFilterMap.getValue();
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.RefinementEvaluator
    public boolean isEligible(RefinementFilterType refinementContentType) {
        Intrinsics.checkNotNullParameter(refinementContentType, "refinementContentType");
        if (!getAudioQualityFilterMap().keySet().contains(refinementContentType)) {
            return true;
        }
        String str = getAudioQualityFilterMap().get(refinementContentType);
        if (str == null) {
            str = "";
        }
        Set<String> set = this.contentEncodings;
        return set != null && set.contains(str);
    }
}
