package com.amazon.mp3.prime.upsell.nightwing;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: GeometricBackoffWithCapUpsellPacing.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\u0004H\u0002J\b\u0010\f\u001a\u00020\u0004H\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004H\u0002J\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0004H\u0002J\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/prime/upsell/nightwing/GeometricBackoffWithCapUpsellPacing;", "", "()V", "BACKOFF_CAP", "", "BACKOFF_FACTOR", "TAG", "", "kotlin.jvm.PlatformType", "TOTAL_UPSELL_COUNT_KEY", "UPSELL_OPPORTUNITY_COUNT_KEY", "getTotalUpsellCount", "getUpsellOpportunityCount", "incrementTotalUpsellAndResetOpportunityCount", "", "totalUpsells", "incrementUpsellOpportunityCount", "upsellOpportunityCount", "shouldUpsell", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class GeometricBackoffWithCapUpsellPacing {
    public static final GeometricBackoffWithCapUpsellPacing INSTANCE = new GeometricBackoffWithCapUpsellPacing();
    private static final String TAG = GeometricBackoffWithCapUpsellPacing.class.getSimpleName();
    private static final String UPSELL_OPPORTUNITY_COUNT_KEY = "upsell_opportunity_count_key";
    private static final String TOTAL_UPSELL_COUNT_KEY = "total_upsell_count_key";
    private static final int BACKOFF_FACTOR = 1;
    private static final int BACKOFF_CAP = 8;

    private GeometricBackoffWithCapUpsellPacing() {
    }

    public final boolean shouldUpsell() {
        int totalUpsellCount = getTotalUpsellCount();
        int upsellOpportunityCount = getUpsellOpportunityCount();
        boolean z = upsellOpportunityCount >= RangesKt___RangesKt.coerceAtMost(BACKOFF_FACTOR * totalUpsellCount, BACKOFF_CAP);
        if (z) {
            incrementTotalUpsellAndResetOpportunityCount(totalUpsellCount);
        } else {
            incrementUpsellOpportunityCount(upsellOpportunityCount);
        }
        Log.info(TAG, "Upsell Decision: " + z + " -> UpsellOpportunityCount: " + upsellOpportunityCount + ", TotalUpsells: " + totalUpsellCount);
        return z;
    }

    private final int getUpsellOpportunityCount() {
        return PreferenceManager.getDefaultSharedPreferences(AmazonApplication.getContext()).getInt(UPSELL_OPPORTUNITY_COUNT_KEY, 0);
    }

    private final int getTotalUpsellCount() {
        return PreferenceManager.getDefaultSharedPreferences(AmazonApplication.getContext()).getInt(TOTAL_UPSELL_COUNT_KEY, 0);
    }

    private final void incrementTotalUpsellAndResetOpportunityCount(int totalUpsells) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AmazonApplication.getContext());
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "sharedPreferences");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Intrinsics.checkNotNullExpressionValue(editor, "editor");
        editor.putInt(TOTAL_UPSELL_COUNT_KEY, totalUpsells + 1);
        editor.apply();
        SharedPreferences.Editor editor2 = sharedPreferences.edit();
        Intrinsics.checkNotNullExpressionValue(editor2, "editor");
        editor2.putInt(UPSELL_OPPORTUNITY_COUNT_KEY, 0);
        editor2.apply();
    }

    private final void incrementUpsellOpportunityCount(int upsellOpportunityCount) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AmazonApplication.getContext());
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "sharedPreferences");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Intrinsics.checkNotNullExpressionValue(editor, "editor");
        editor.putInt(UPSELL_OPPORTUNITY_COUNT_KEY, upsellOpportunityCount + 1);
        editor.apply();
    }
}
