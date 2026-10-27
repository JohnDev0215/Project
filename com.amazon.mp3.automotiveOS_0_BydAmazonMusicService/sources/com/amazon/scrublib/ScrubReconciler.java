package com.amazon.scrublib;

import com.amazon.scrublib.models.AtomicPlayerState;
import com.amazon.scrublib.models.ContentType;
import com.amazon.scrublib.models.PlayerState;
import com.amazon.scrublib.models.ScrubSegment;
import com.amazon.scrublib.models.Signature;
import com.amazon.scrublib.models.TranscriptLoadingType;
import com.amazon.scrublib.models.TranscriptUpdatedListener;
import com.amazon.scrublib.utils.AtomicInt;
import com.amazon.scrublib.utils.Counter;
import com.amazon.scrublib.utils.PlatformUtilsKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsJvmKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;

/* JADX INFO: compiled from: ScrubReconciler.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 L2\u00020\u0001:\u0001LB-\u0012\u0006\u00103\u001a\u00020\u0007\u0012\u0006\u00106\u001a\u000205\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u00109\u001a\u000208¢\u0006\u0004\bJ\u0010KJ\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0002J\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0002J(\u0010\r\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007H\u0002J\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002J\u0016\u0010\u0011\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0007J\u0016\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0007J,\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0010\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\fJ\"\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00022\u0006\u0010\u0017\u001a\u00020\u0007J\u0006\u0010\u001a\u001a\u00020\u0019J\u0006\u0010\u001b\u001a\u00020\u0019J\u0006\u0010\u001c\u001a\u00020\u0019J\u001e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0010\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\fJ \u0010!\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\b\b\u0002\u0010 \u001a\u00020\fJ\u000e\u0010#\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\u0007J\u000e\u0010%\u001a\u00020\f2\u0006\u0010$\u001a\u00020\u0007J$\u0010)\u001a\u00020\u00192\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010'\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u0007J\u000e\u0010+\u001a\u00020\u00192\u0006\u0010*\u001a\u00020\u0007J&\u0010.\u001a\u0014\u0012\u0004\u0012\u00020-\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00020,2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0002J7\u00101\u001a\u0004\u0018\u00010\u00072\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00150\u00022\u0018\u00100\u001a\u0014\u0012\u0004\u0012\u00020-\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00020,¢\u0006\u0004\b1\u00102R\u0014\u00103\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00104R\u0014\u00106\u001a\u0002058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u00107R\u0014\u00109\u001a\u0002088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u0010:R\u0017\u0010<\u001a\u00020;8\u0006¢\u0006\f\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010@\u001a\u0004\bA\u0010BR\u0017\u0010D\u001a\u00020C8\u0006¢\u0006\f\n\u0004\bD\u0010E\u001a\u0004\bF\u0010GR\u0017\u0010H\u001a\u00020;8\u0006¢\u0006\f\n\u0004\bH\u0010=\u001a\u0004\bI\u0010?¨\u0006M"}, d2 = {"Lcom/amazon/scrublib/ScrubReconciler;", "", "", "Lcom/amazon/scrublib/models/ScrubSegment;", "allScrubSegments", "suppressOverlappingAds", "joinUntranscribedSegments", "", "startA", "endA", "startB", "endB", "", "rangeIntersect", "getScrub", "scrubSeg", "curTime", "isAd", "mapScrub", "replace", "tagDynamicAds", "Lcom/amazon/scrublib/models/Signature;", "clientSignatures", "latestProcessedTime", "alignScrubIncremental", "", "transitionToAdStart", "transitionToNormal", "transitionToTransitional", "updateTranscript", "windowStart", "windowEnd", "default", "hasProcessedInWindow", "currentTime", "transcriptIsInAdsRegion", "time", "shouldFireLoadingOnSeek", "segments", "index", "offset", "realignScrubSegments", "newTime", "seek", "", "", "generateMapOfSignatureHashToMatchingSignatureList", "scrubSignatures", "clientHashToSignatureMap", "getMostLikelyTimeDiff", "(Ljava/util/List;Ljava/util/Map;)Ljava/lang/Integer;", "bucketWidth", "I", "", "percentageCutoff", "D", "Lcom/amazon/scrublib/models/TranscriptUpdatedListener;", "onTranscriptUpdatedListener", "Lcom/amazon/scrublib/models/TranscriptUpdatedListener;", "Lcom/amazon/scrublib/utils/AtomicInt;", "currentUnprocessedSegment", "Lcom/amazon/scrublib/utils/AtomicInt;", "getCurrentUnprocessedSegment", "()Lcom/amazon/scrublib/utils/AtomicInt;", "Ljava/util/List;", "getAllScrubSegments", "()Ljava/util/List;", "Lcom/amazon/scrublib/models/AtomicPlayerState;", "playerState", "Lcom/amazon/scrublib/models/AtomicPlayerState;", "getPlayerState", "()Lcom/amazon/scrublib/models/AtomicPlayerState;", "lastMatchedSegmentTime", "getLastMatchedSegmentTime", "<init>", "(IDLjava/util/List;Lcom/amazon/scrublib/models/TranscriptUpdatedListener;)V", "Companion", "DMPScrubCore_release"}, k = 1, mv = {1, 7, 1})
public final class ScrubReconciler {
    private static final Set<ContentType> MERGABLE_CONTENT_TYPE = SetsKt__SetsKt.setOf((Object[]) new ContentType[]{ContentType.MUSIC, ContentType.UNTRANSCRIBED_CONTENT});
    private final List<ScrubSegment> allScrubSegments;
    private final int bucketWidth;
    private final AtomicInt currentUnprocessedSegment;
    private final AtomicInt lastMatchedSegmentTime;
    private final TranscriptUpdatedListener onTranscriptUpdatedListener;
    private final double percentageCutoff;
    private final AtomicPlayerState playerState;

    private final boolean rangeIntersect(int startA, int endA, int startB, int endB) {
        return startA <= endB && endA >= startB;
    }

    public ScrubReconciler(int i, double d, List<ScrubSegment> allScrubSegments, TranscriptUpdatedListener onTranscriptUpdatedListener) {
        Intrinsics.checkNotNullParameter(allScrubSegments, "allScrubSegments");
        Intrinsics.checkNotNullParameter(onTranscriptUpdatedListener, "onTranscriptUpdatedListener");
        this.bucketWidth = i;
        this.percentageCutoff = d;
        this.onTranscriptUpdatedListener = onTranscriptUpdatedListener;
        this.allScrubSegments = new ArrayList(allScrubSegments);
        this.currentUnprocessedSegment = new AtomicInt(0);
        this.playerState = new AtomicPlayerState(PlayerState.TRANSITIONAL);
        this.lastMatchedSegmentTime = new AtomicInt(0);
        PlatformUtilsKt.freeze(this);
    }

    public final List<ScrubSegment> getAllScrubSegments() {
        return this.allScrubSegments;
    }

    public final List<ScrubSegment> getScrub(List<ScrubSegment> allScrubSegments) {
        Intrinsics.checkNotNullParameter(allScrubSegments, "allScrubSegments");
        return joinUntranscribedSegments(suppressOverlappingAds(allScrubSegments));
    }

    private final List<ScrubSegment> suppressOverlappingAds(List<ScrubSegment> allScrubSegments) {
        ArrayList arrayList = new ArrayList();
        int size = allScrubSegments.size();
        ScrubSegment scrubSegment = null;
        for (int i = 0; i < size; i++) {
            ScrubSegment scrubSegment2 = allScrubSegments.get((allScrubSegments.size() - i) - 1);
            if (scrubSegment == null || !scrubSegment.isProcessed() || scrubSegment2.isProcessed() || scrubSegment.getStartTime() >= scrubSegment2.getEndTime()) {
                arrayList.add(scrubSegment2);
                scrubSegment = scrubSegment2;
            }
        }
        CollectionsKt___CollectionsJvmKt.reverse(arrayList);
        return arrayList;
    }

    private final List<ScrubSegment> joinUntranscribedSegments(List<ScrubSegment> allScrubSegments) {
        ArrayList arrayList = new ArrayList();
        if (allScrubSegments.isEmpty()) {
            return arrayList;
        }
        Iterator<ScrubSegment> it = allScrubSegments.iterator();
        ScrubSegment next = it.next();
        while (it.hasNext()) {
            ScrubSegment next2 = it.next();
            Set<ContentType> set = MERGABLE_CONTENT_TYPE;
            if (set.contains(next2.getContentType()) && set.contains(next.getContentType())) {
                next = new ScrubSegment(next.getStartTime(), next2.getEndTime(), null, null, null, null, false, ContentType.UNTRANSCRIBED_CONTENT, 124, null);
            } else {
                arrayList.add(next);
                next = next2;
            }
        }
        arrayList.add(next);
        return arrayList;
    }

    public final boolean isAd(ScrubSegment scrubSeg, int curTime) {
        Intrinsics.checkNotNullParameter(scrubSeg, "scrubSeg");
        return scrubSeg.getStartTime() < curTime && scrubSeg.getEndTime() > this.lastMatchedSegmentTime.get() && !scrubSeg.isProcessed();
    }

    public final ScrubSegment mapScrub(ScrubSegment scrubSeg, int curTime) {
        Intrinsics.checkNotNullParameter(scrubSeg, "scrubSeg");
        return isAd(scrubSeg, curTime) ? ScrubSegment.clone$default(scrubSeg, null, null, null, ContentType.UNTRANSCRIBED_CONTENT, 7, null) : scrubSeg;
    }

    public final List<ScrubSegment> alignScrubIncremental(List<Signature> clientSignatures, int latestProcessedTime) {
        boolean z;
        Intrinsics.checkNotNullParameter(clientSignatures, "clientSignatures");
        if (this.playerState.get() == PlayerState.TRANSITIONAL && latestProcessedTime - this.lastMatchedSegmentTime.get() >= 5000) {
            transitionToNormal();
        }
        int i = this.currentUnprocessedSegment.get();
        while (true) {
            z = true;
            if (i >= this.allScrubSegments.size()) {
                break;
            }
            ScrubSegment scrubSegment = this.allScrubSegments.get(i);
            if (rangeIntersect(scrubSegment.getStartTime(), scrubSegment.getEndTime(), latestProcessedTime - 300000, 300000 + latestProcessedTime)) {
                if (!scrubSegment.isProcessed()) {
                    List<Signature> audioSignature = this.allScrubSegments.get(i).getAudioSignature();
                    Intrinsics.checkNotNull(audioSignature);
                    Integer mostLikelyTimeDiff = getMostLikelyTimeDiff(clientSignatures, generateMapOfSignatureHashToMatchingSignatureList(audioSignature));
                    if (mostLikelyTimeDiff != null) {
                        int i2 = -mostLikelyTimeDiff.intValue();
                        if (Math.abs(i2) > 200) {
                            realignScrubSegments(this.allScrubSegments, i, i2);
                        }
                        this.allScrubSegments.get(i).setProcessed(true);
                        if (mostLikelyTimeDiff.intValue() != 0 || this.playerState.get() == PlayerState.AD) {
                            updateTranscript$default(this, latestProcessedTime, false, 2, null);
                        }
                        this.lastMatchedSegmentTime.set(scrubSegment.getEndTime());
                        if (this.playerState.get() != PlayerState.NORMAL) {
                            transitionToNormal();
                        }
                        this.currentUnprocessedSegment.set(i);
                    }
                } else if (this.playerState.get() != PlayerState.NORMAL && rangeIntersect(scrubSegment.getStartTime(), scrubSegment.getEndTime(), latestProcessedTime, latestProcessedTime)) {
                    updateTranscript$default(this, latestProcessedTime, false, 2, null);
                    transitionToNormal();
                    this.lastMatchedSegmentTime.set(scrubSegment.getEndTime());
                    this.currentUnprocessedSegment.set(i);
                    break;
                }
            }
            i++;
        }
        z = false;
        if (z) {
            if (this.playerState.get() == PlayerState.NORMAL && transcriptIsInAdsRegion(latestProcessedTime)) {
                transitionToAdStart();
            } else if (this.playerState.get() == PlayerState.AD) {
                updateTranscript(latestProcessedTime, false);
            }
        }
        return this.allScrubSegments;
    }

    public final void transitionToAdStart() {
        this.onTranscriptUpdatedListener.onTranscriptLoading(TranscriptLoadingType.AD_START);
        this.playerState.set(PlayerState.AD);
    }

    public final void transitionToNormal() {
        if (this.playerState.get() == PlayerState.AD) {
            this.onTranscriptUpdatedListener.onTranscriptLoaded();
        }
        this.playerState.set(PlayerState.NORMAL);
    }

    public final void transitionToTransitional() {
        this.playerState.set(PlayerState.TRANSITIONAL);
    }

    public static /* synthetic */ List updateTranscript$default(ScrubReconciler scrubReconciler, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = true;
        }
        return scrubReconciler.updateTranscript(i, z);
    }

    public final List<ScrubSegment> updateTranscript(int curTime, boolean replace) {
        List<ScrubSegment> scrub;
        if (this.playerState.get() == PlayerState.AD) {
            scrub = getScrub(tagDynamicAds(this.allScrubSegments, curTime, replace));
        } else {
            scrub = getScrub(this.allScrubSegments);
        }
        this.onTranscriptUpdatedListener.onTranscriptUpdated(scrub);
        return scrub;
    }

    public static /* synthetic */ boolean hasProcessedInWindow$default(ScrubReconciler scrubReconciler, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            z = true;
        }
        return scrubReconciler.hasProcessedInWindow(i, i2, z);
    }

    public final boolean hasProcessedInWindow(int windowStart, int windowEnd, boolean z) {
        List<ScrubSegment> list = this.allScrubSegments;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            ScrubSegment scrubSegment = (ScrubSegment) obj;
            if (rangeIntersect(scrubSegment.getStartTime(), scrubSegment.getEndTime(), windowStart, windowEnd)) {
                arrayList.add(obj);
            }
        }
        if (!arrayList.isEmpty()) {
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(Boolean.valueOf(((ScrubSegment) it.next()).isProcessed()));
            }
            Iterator it2 = arrayList2.iterator();
            if (!it2.hasNext()) {
                throw new UnsupportedOperationException("Empty collection can't be reduced.");
            }
            Object next = it2.next();
            while (it2.hasNext()) {
                next = Boolean.valueOf(((Boolean) next).booleanValue() || ((Boolean) it2.next()).booleanValue());
            }
            z = ((Boolean) next).booleanValue();
        }
        return !z;
    }

    public final boolean transcriptIsInAdsRegion(int currentTime) {
        return hasProcessedInWindow$default(this, currentTime - 5000, currentTime, false, 4, null);
    }

    public final boolean shouldFireLoadingOnSeek(int time) {
        return hasProcessedInWindow(time, time + DisconnectedBufferOptions.DISCONNECTED_BUFFER_SIZE_DEFAULT, false);
    }

    public final void realignScrubSegments(List<ScrubSegment> segments, int index, int offset) {
        Intrinsics.checkNotNullParameter(segments, "segments");
        int size = segments.size();
        while (index < size && !segments.get(index).isProcessed()) {
            segments.get(index).setStartTime(segments.get(index).getStartTime() + offset);
            List<Signature> audioSignature = segments.get(index).getAudioSignature();
            Intrinsics.checkNotNull(audioSignature);
            for (Signature signature : audioSignature) {
                signature.setStartTime(signature.getStartTime() + offset);
            }
            segments.get(index).setEndTime(segments.get(index).getEndTime() + offset);
            index++;
        }
    }

    public final void seek(int newTime) {
        if (this.playerState.get() == PlayerState.AD) {
            this.onTranscriptUpdatedListener.onTranscriptLoaded();
        }
        if (shouldFireLoadingOnSeek(newTime)) {
            this.onTranscriptUpdatedListener.onTranscriptLoading(TranscriptLoadingType.PLAYBACK_SEEK);
            this.onTranscriptUpdatedListener.onTranscriptLoaded();
            transitionToTransitional();
        } else {
            transitionToNormal();
        }
        this.lastMatchedSegmentTime.set(newTime);
        this.currentUnprocessedSegment.set(0);
    }

    public final Integer getMostLikelyTimeDiff(List<Signature> scrubSignatures, Map<String, ? extends List<Signature>> clientHashToSignatureMap) {
        Intrinsics.checkNotNullParameter(scrubSignatures, "scrubSignatures");
        Intrinsics.checkNotNullParameter(clientHashToSignatureMap, "clientHashToSignatureMap");
        Counter counter = new Counter();
        for (Signature signature : scrubSignatures) {
            List<Signature> arrayList = clientHashToSignatureMap.get(signature.getSignature());
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            Iterator<T> it = arrayList.iterator();
            while (it.hasNext()) {
                int startTime = ((Signature) it.next()).getStartTime() - signature.getStartTime();
                int i = this.bucketWidth;
                counter.put(Integer.valueOf((startTime / i) * i));
            }
        }
        List listMostCommon = counter.mostCommon(1);
        if (!(true ^ listMostCommon.isEmpty())) {
            return null;
        }
        int iIntValue = ((Number) listMostCommon.get(0)).intValue();
        int count = counter.getCount(Integer.valueOf(iIntValue));
        if (((double) count) / ((double) counter.getTotalCount()) < this.percentageCutoff || count <= 5) {
            return null;
        }
        return Integer.valueOf(iIntValue);
    }

    public final List<ScrubSegment> tagDynamicAds(List<ScrubSegment> allScrubSegments, int curTime, boolean replace) {
        Intrinsics.checkNotNullParameter(allScrubSegments, "allScrubSegments");
        if (replace) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : allScrubSegments) {
                if (isAd((ScrubSegment) obj, curTime)) {
                    arrayList.add(obj);
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((ScrubSegment) it.next()).setContentType(ContentType.UNTRANSCRIBED_CONTENT);
            }
            return allScrubSegments;
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(allScrubSegments, 10));
        Iterator<T> it2 = allScrubSegments.iterator();
        while (it2.hasNext()) {
            arrayList2.add(mapScrub((ScrubSegment) it2.next(), curTime));
        }
        return arrayList2;
    }

    public final Map<String, List<Signature>> generateMapOfSignatureHashToMatchingSignatureList(List<Signature> clientSignatures) {
        Intrinsics.checkNotNullParameter(clientSignatures, "clientSignatures");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : clientSignatures) {
            String signature = ((Signature) obj).getSignature();
            Intrinsics.checkNotNull(signature);
            Object arrayList = linkedHashMap.get(signature);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(signature, arrayList);
            }
            ((List) arrayList).add(obj);
        }
        return linkedHashMap;
    }
}
