package com.amazon.scrublib;

import com.amazon.scrublib.models.PeakRaw;
import com.amazon.scrublib.models.Signature;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt___SequencesKt;

/* JADX INFO: compiled from: SignatureCreator.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J2\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003J\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\bR\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/scrublib/SignatureCreator;", "", "regionTime", "", "regionFreq", "neighborhoodSize", "(III)V", "convertSignatureIdxTimestampToMs", "", "Lcom/amazon/scrublib/models/Signature;", "signatureList", "samplingFreq", "windowSize", "windowOverlap", "generateSignature", "peakRawList", "Lcom/amazon/scrublib/models/PeakRaw;", "DMPScrubCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SignatureCreator {
    private final int neighborhoodSize;
    private final int regionFreq;
    private final int regionTime;

    public SignatureCreator(int i, int i2, int i3) {
        this.regionTime = i;
        this.regionFreq = i2;
        this.neighborhoodSize = i3;
    }

    public final List<Signature> generateSignature(List<PeakRaw> peakRawList) {
        Intrinsics.checkNotNullParameter(peakRawList, "peakRawList");
        ArrayList arrayList = new ArrayList();
        int size = peakRawList.size();
        for (int i = 0; i < size; i++) {
            int i2 = this.neighborhoodSize;
            for (int i3 = 1; i3 < i2; i3++) {
                int i4 = i + i3;
                if (i4 < peakRawList.size()) {
                    Integer timeStampIdx = peakRawList.get(i4).getTimeStampIdx();
                    Intrinsics.checkNotNull(timeStampIdx);
                    int iIntValue = timeStampIdx.intValue();
                    Integer timeStampIdx2 = peakRawList.get(i).getTimeStampIdx();
                    Intrinsics.checkNotNull(timeStampIdx2);
                    int iIntValue2 = iIntValue - timeStampIdx2.intValue();
                    StringBuilder sb = new StringBuilder();
                    sb.append(peakRawList.get(i).getFrequencyIdx());
                    sb.append('|');
                    sb.append(peakRawList.get(i4).getFrequencyIdx());
                    sb.append('|');
                    sb.append(iIntValue2);
                    String string = sb.toString();
                    if (iIntValue2 < this.regionTime) {
                        Integer frequencyIdx = peakRawList.get(i4).getFrequencyIdx();
                        Intrinsics.checkNotNull(frequencyIdx);
                        int iIntValue3 = frequencyIdx.intValue();
                        Integer frequencyIdx2 = peakRawList.get(i).getFrequencyIdx();
                        Intrinsics.checkNotNull(frequencyIdx2);
                        if (Math.abs(iIntValue3 - frequencyIdx2.intValue()) < this.regionFreq && iIntValue2 > 0) {
                            Integer timeStampIdx3 = peakRawList.get(i).getTimeStampIdx();
                            Intrinsics.checkNotNull(timeStampIdx3);
                            arrayList.add(new Signature(timeStampIdx3.intValue(), string));
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public final List<Signature> convertSignatureIdxTimestampToMs(List<Signature> signatureList, final int samplingFreq, final int windowSize, final int windowOverlap) {
        Intrinsics.checkNotNullParameter(signatureList, "signatureList");
        return SequencesKt___SequencesKt.toList(SequencesKt___SequencesKt.map(CollectionsKt___CollectionsKt.asSequence(signatureList), new Function1<Signature, Signature>() { // from class: com.amazon.scrublib.SignatureCreator.convertSignatureIdxTimestampToMs.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Signature invoke(Signature sig) {
                Intrinsics.checkNotNullParameter(sig, "sig");
                return sig.clone(SpectrogramGenerator.INSTANCE.getTimeFromIdx(sig.getStartTime(), samplingFreq, windowSize, windowOverlap));
            }
        }));
    }
}
