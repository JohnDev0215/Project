package com.android.car.developeroptions.homepage.contextualcards;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import androidx.slice.Slice;
import androidx.slice.SliceViewManager;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class EligibleCardChecker implements Callable<ContextualCard> {
    private final ContextualCard mCard;
    private final Context mContext;

    EligibleCardChecker(Context context, ContextualCard contextualCard) {
        this.mContext = context;
        this.mCard = contextualCard;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public ContextualCard call() throws Exception {
        ContextualCard contextualCard;
        long jCurrentTimeMillis = System.currentTimeMillis();
        MetricsFeatureProvider metricsFeatureProvider = FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider();
        if (isCardEligibleToDisplay(this.mCard)) {
            metricsFeatureProvider.action(0, 1686, 1502, this.mCard.getTextSliceUri(), 1);
            contextualCard = this.mCard;
        } else {
            metricsFeatureProvider.action(0, 1686, 1502, this.mCard.getTextSliceUri(), 0);
            contextualCard = null;
        }
        ContextualCard contextualCard2 = contextualCard;
        metricsFeatureProvider.action(0, 1684, 1502, this.mCard.getTextSliceUri(), (int) (System.currentTimeMillis() - jCurrentTimeMillis));
        return contextualCard2;
    }

    boolean isCardEligibleToDisplay(ContextualCard contextualCard) {
        if (contextualCard.getRankingScore() < 0.0d) {
            return false;
        }
        if (contextualCard.isCustomCard()) {
            return true;
        }
        Uri sliceUri = contextualCard.getSliceUri();
        if (!"content".equals(sliceUri.getScheme())) {
            return false;
        }
        Slice sliceBindSlice = bindSlice(sliceUri);
        if (sliceBindSlice != null && !sliceBindSlice.hasHint("error")) {
            return true;
        }
        Log.w("EligibleCardChecker", "Failed to bind slice, not eligible for display " + sliceUri);
        return false;
    }

    Slice bindSlice(final Uri uri) {
        final SliceViewManager sliceViewManager = SliceViewManager.getInstance(this.mContext);
        final Slice[] sliceArr = new Slice[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        SliceViewManager.SliceCallback sliceCallback = new SliceViewManager.SliceCallback(this) { // from class: com.android.car.developeroptions.homepage.contextualcards.EligibleCardChecker.1
            @Override // androidx.slice.SliceViewManager.SliceCallback
            public void onSliceUpdated(Slice slice) {
                try {
                    try {
                        sliceArr[0] = slice;
                        countDownLatch.countDown();
                    } catch (Exception e) {
                        Log.w("EligibleCardChecker", uri + " cannot be indexed", e);
                    }
                } finally {
                    sliceViewManager.unregisterSliceCallback(uri, this);
                }
            }
        };
        sliceViewManager.registerSliceCallback(uri, sliceCallback);
        sliceCallback.onSliceUpdated(sliceViewManager.bindSlice(uri));
        try {
            countDownLatch.await(200L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Log.w("EligibleCardChecker", "Error waiting for slice binding for uri" + uri, e);
            sliceViewManager.unregisterSliceCallback(uri, sliceCallback);
        }
        return sliceArr[0];
    }
}
