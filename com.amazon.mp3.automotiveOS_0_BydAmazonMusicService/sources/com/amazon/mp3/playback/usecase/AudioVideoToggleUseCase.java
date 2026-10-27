package com.amazon.mp3.playback.usecase;

import android.content.Context;
import android.view.View;
import com.amazon.mp3.prime.requester.HybridQueueType;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import rx.Observable;

/* JADX INFO: compiled from: AudioVideoToggleUseCase.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H&J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&J\u0018\u0010\t\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H&J\"\u0010\r\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\nH&J\b\u0010\u000f\u001a\u00020\u000eH&J\u0010\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0010H&J\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\u0014\u001a\u00020\u0002H&R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u00158&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/playback/usecase/AudioVideoToggleUseCase;", "", "", "hideToggle", "Landroid/content/Context;", "context", "updateToggleState", "Lcom/amazon/mp3/prime/requester/HybridQueueType;", "hybridQueueType", "doToggle", "", "asin", "blockref", "sendToggleMetrics", "Landroid/view/View;", "getToggleView", "Landroid/view/View$OnClickListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setAudioVideoToggleListener", "loadTrackAssociationsBatch", "removeAudioVideoToggleListener", "Lrx/Observable;", "getToggleSuccessfulObservable", "()Lrx/Observable;", "toggleSuccessfulObservable", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface AudioVideoToggleUseCase {
    void doToggle(Context context, HybridQueueType hybridQueueType);

    Observable<HybridQueueType> getToggleSuccessfulObservable();

    View getToggleView();

    void hideToggle();

    void loadTrackAssociationsBatch(Context context);

    void removeAudioVideoToggleListener();

    void sendToggleMetrics(String asin, HybridQueueType hybridQueueType, String blockref);

    void setAudioVideoToggleListener(View.OnClickListener listener);

    void updateToggleState(Context context);
}
