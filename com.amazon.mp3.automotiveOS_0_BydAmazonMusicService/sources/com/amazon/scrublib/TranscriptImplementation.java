package com.amazon.scrublib;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.amazon.scrublib.models.ScrubSegment;
import com.amazon.scrublib.models.Signature;
import com.amazon.scrublib.models.TranscriptLoadingType;
import com.amazon.scrublib.models.TranscriptUpdatedListener;
import com.amazon.scrublib.utils.AtomicInt;
import com.amazon.scrublib.utils.DoubleBuffer;
import com.amazon.scrublib.utils.PlatformUtilsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TranscriptImplementation.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0013\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 +2\u00020\u0001:\u0001+B-\u0012\f\u0010&\u001a\b\u0012\u0004\u0012\u00020%0\u0004\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010(\u001a\u00020'¢\u0006\u0004\b)\u0010*J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0016R\u0016\u0010\r\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0016\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001f\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\u000eR\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$¨\u0006,"}, d2 = {"Lcom/amazon/scrublib/TranscriptImplementation;", "", "", "frameEndIdx", "", "Lcom/amazon/scrublib/models/Signature;", "calculateSignatureInCurrentFrame", "timeMs", "", "seek", "", "inputChannel0WaveForm", "putData", "samplingFrequency", "I", "", "minimumPeakHeight", "D", "Lcom/amazon/scrublib/SpectrogramGenerator;", "spectrogramGenerator", "Lcom/amazon/scrublib/SpectrogramGenerator;", "Lcom/amazon/scrublib/PeakFinder;", "peakFinder", "Lcom/amazon/scrublib/PeakFinder;", "Lcom/amazon/scrublib/SignatureCreator;", "scrubGenerator", "Lcom/amazon/scrublib/SignatureCreator;", "Lcom/amazon/scrublib/ScrubReconciler;", "scrubAligner", "Lcom/amazon/scrublib/ScrubReconciler;", "Lcom/amazon/scrublib/utils/DoubleBuffer;", "unprocessedDataStore", "Lcom/amazon/scrublib/utils/DoubleBuffer;", "minLengthProcess", "Lcom/amazon/scrublib/utils/AtomicInt;", "latestProcessedTime", "Lcom/amazon/scrublib/utils/AtomicInt;", "Lcom/amazon/scrublib/models/ScrubSegment;", "originalScrub", "Lcom/amazon/scrublib/models/TranscriptUpdatedListener;", "transcriptUpdatedListener", "<init>", "(Ljava/util/List;IDLcom/amazon/scrublib/models/TranscriptUpdatedListener;)V", "Companion", "DMPScrubCore_release"}, k = 1, mv = {1, 7, 1})
public final class TranscriptImplementation {
    private final AtomicInt latestProcessedTime;
    private final int minLengthProcess;
    private double minimumPeakHeight;
    private final PeakFinder peakFinder;
    private int samplingFrequency;
    private final ScrubReconciler scrubAligner;
    private final SignatureCreator scrubGenerator;
    private final SpectrogramGenerator spectrogramGenerator;
    private DoubleBuffer unprocessedDataStore;

    public TranscriptImplementation(List<ScrubSegment> originalScrub, int i, double d, TranscriptUpdatedListener transcriptUpdatedListener) {
        Intrinsics.checkNotNullParameter(originalScrub, "originalScrub");
        Intrinsics.checkNotNullParameter(transcriptUpdatedListener, "transcriptUpdatedListener");
        this.samplingFrequency = i;
        this.minimumPeakHeight = d;
        transcriptUpdatedListener.onTranscriptLoading(TranscriptLoadingType.PLAYBACK_START);
        this.spectrogramGenerator = new SpectrogramGenerator(4096, 1024, this.samplingFrequency);
        this.peakFinder = new PeakFinder();
        this.scrubGenerator = new SignatureCreator(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 200, 10);
        this.unprocessedDataStore = new DoubleBuffer(this.samplingFrequency * 3);
        this.minLengthProcess = this.samplingFrequency * 2;
        ScrubReconciler scrubReconciler = new ScrubReconciler(1000, 0.3d, originalScrub, transcriptUpdatedListener);
        this.scrubAligner = scrubReconciler;
        this.latestProcessedTime = new AtomicInt(0);
        PlatformUtilsKt.freeze(this);
        transcriptUpdatedListener.onTranscriptUpdated(scrubReconciler.getScrub(scrubReconciler.getAllScrubSegments()));
        transcriptUpdatedListener.onTranscriptLoaded();
    }

    public void seek(int timeMs) {
        this.latestProcessedTime.set(timeMs);
        this.unprocessedDataStore.reset();
        this.scrubAligner.seek(timeMs);
    }

    public void putData(double[] inputChannel0WaveForm) {
        Intrinsics.checkNotNullParameter(inputChannel0WaveForm, "inputChannel0WaveForm");
        this.unprocessedDataStore.addAll(inputChannel0WaveForm);
        if (this.unprocessedDataStore.getSize() >= this.minLengthProcess) {
            int closestWindow = this.spectrogramGenerator.getClosestWindow(this.unprocessedDataStore.getSize());
            try {
                this.scrubAligner.alignScrubIncremental(calculateSignatureInCurrentFrame(closestWindow), this.latestProcessedTime.get());
            } catch (IndexOutOfBoundsException unused) {
                this.unprocessedDataStore = new DoubleBuffer(this.samplingFrequency * 3);
            }
            AtomicInt atomicInt = this.latestProcessedTime;
            atomicInt.set(atomicInt.get() + ((int) ((((double) closestWindow) * 1000.0d) / ((double) this.samplingFrequency))));
        }
    }

    private final List<Signature> calculateSignatureInCurrentFrame(int frameEndIdx) {
        List<Signature> listConvertSignatureIdxTimestampToMs = this.scrubGenerator.convertSignatureIdxTimestampToMs(this.scrubGenerator.generateSignature(this.peakFinder.generatePeakFromSpectrogram(this.spectrogramGenerator.generateSpectrogram(this.unprocessedDataStore.popNum(frameEndIdx)), this.minimumPeakHeight)), this.samplingFrequency, 4096, 1024);
        for (Signature signature : listConvertSignatureIdxTimestampToMs) {
            signature.setStartTime(signature.getStartTime() + this.latestProcessedTime.get());
        }
        return listConvertSignatureIdxTimestampToMs;
    }
}
