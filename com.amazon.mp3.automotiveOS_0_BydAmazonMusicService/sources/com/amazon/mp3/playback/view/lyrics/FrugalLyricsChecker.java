package com.amazon.mp3.playback.view.lyrics;

import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.lyrics.item.LyricsLine;
import com.amazon.mp3.util.Log;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FrugalLyricsChecker.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u0010\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0004J\u0006\u0010\f\u001a\u00020\u0004J\u0006\u0010\r\u001a\u00020\bJ\u000e\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006J\u0006\u0010\u000f\u001a\u00020\u0002R\u001c\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0014\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0016\u0010\n\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u0010\u0016R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001c"}, d2 = {"Lcom/amazon/mp3/playback/view/lyrics/FrugalLyricsChecker;", "", "", "position", "", "shouldShowFrugalLyrics", "Lcom/amazon/mp3/lyrics/item/Lyrics;", "lyrics", "", "setLyrics", "hasUserInteracted", "setHasUserInteracted", "getHasUserInteracted", "reset", "calculateThresholdIndex", "getThresholdIndex", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "thresholdIndex", "I", "Z", "Lio/reactivex/disposables/Disposable;", "currentDisposable", "Lio/reactivex/disposables/Disposable;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class FrugalLyricsChecker {
    private static Disposable currentDisposable;
    private static boolean hasUserInteracted;
    public static final FrugalLyricsChecker INSTANCE = new FrugalLyricsChecker();
    private static final String TAG = FrugalLyricsChecker.class.getSimpleName();
    private static int thresholdIndex = -2;

    private FrugalLyricsChecker() {
    }

    public final boolean shouldShowFrugalLyrics(int position) {
        int i;
        return (hasUserInteracted || (i = thresholdIndex) == -1 || i == -2 || position <= i) ? false : true;
    }

    public final void setLyrics(final Lyrics lyrics) {
        if (lyrics == null) {
            return;
        }
        thresholdIndex = -2;
        if (hasUserInteracted) {
            return;
        }
        Disposable disposable = currentDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        currentDisposable = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.playback.view.lyrics.FrugalLyricsChecker$$ExternalSyntheticLambda0
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                FrugalLyricsChecker.m1526setLyrics$lambda0(lyrics, observableEmitter);
            }
        }).subscribeOn(Schedulers.computation()).subscribe(new Consumer() { // from class: com.amazon.mp3.playback.view.lyrics.FrugalLyricsChecker$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                FrugalLyricsChecker.m1527setLyrics$lambda1((Integer) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.playback.view.lyrics.FrugalLyricsChecker$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                FrugalLyricsChecker.m1528setLyrics$lambda2(lyrics, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setLyrics$lambda-0, reason: not valid java name */
    public static final void m1526setLyrics$lambda0(Lyrics lyrics, ObservableEmitter it) {
        Intrinsics.checkNotNullParameter(it, "it");
        it.onNext(Integer.valueOf(INSTANCE.calculateThresholdIndex(lyrics)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setLyrics$lambda-1, reason: not valid java name */
    public static final void m1527setLyrics$lambda1(Integer it) {
        Intrinsics.checkNotNullExpressionValue(it, "it");
        thresholdIndex = it.intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setLyrics$lambda-2, reason: not valid java name */
    public static final void m1528setLyrics$lambda2(Lyrics lyrics, Throwable th) {
        thresholdIndex = -1;
        Log.error(TAG, Intrinsics.stringPlus("Error calculating threshold point for track ", lyrics.getAsin()), th);
    }

    public final void setHasUserInteracted(boolean hasUserInteracted2) {
        hasUserInteracted = hasUserInteracted2;
    }

    public final boolean getHasUserInteracted() {
        return hasUserInteracted;
    }

    public final void reset() {
        Disposable disposable = currentDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        currentDisposable = null;
        setHasUserInteracted(false);
        thresholdIndex = -2;
    }

    public final int calculateThresholdIndex(Lyrics lyrics) {
        Intrinsics.checkNotNullParameter(lyrics, "lyrics");
        int size = (int) (lyrics.getLines().size() * 0.175f);
        Iterator<LyricsLine> it = lyrics.getLines().iterator();
        int i = 0;
        int characterCount = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            int i2 = i + 1;
            characterCount += LyricsMetricsReporter.getCharacterCount(it.next());
            if (characterCount > 150) {
                size = Math.min(i - 1, size);
                break;
            }
            i = i2;
        }
        return Math.max(0, size);
    }

    public final int getThresholdIndex() {
        return thresholdIndex;
    }
}
