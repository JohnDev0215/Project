package com.android.car.developeroptions.homepage.contextualcards;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;
import com.android.car.developeroptions.homepage.contextualcards.logging.ContextualCardLogUtils;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.slices.CustomSliceRegistry;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.utils.AsyncLoaderCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class ContextualCardLoader extends AsyncLoaderCompat<List<ContextualCard>> {
    static final int DEFAULT_CARD_COUNT = 4;
    private final Context mContext;
    private final ExecutorService mExecutorService;
    Uri mNotifyUri;
    private final ContentObserver mObserver;

    public interface CardContentLoaderListener {
        void onFinishCardLoading(List<ContextualCard> list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.utils.AsyncLoaderCompat
    public void onDiscardResult(List<ContextualCard> list) {
    }

    ContextualCardLoader(Context context) {
        super(context);
        this.mObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.android.car.developeroptions.homepage.contextualcards.ContextualCardLoader.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                if (ContextualCardLoader.this.isStarted()) {
                    ContextualCardLoader contextualCardLoader = ContextualCardLoader.this;
                    contextualCardLoader.mNotifyUri = uri;
                    contextualCardLoader.forceLoad();
                }
            }
        };
        this.mContext = context.getApplicationContext();
        this.mExecutorService = Executors.newCachedThreadPool();
    }

    @Override // com.android.settingslib.utils.AsyncLoaderCompat, androidx.loader.content.Loader
    protected void onStartLoading() {
        super.onStartLoading();
        this.mNotifyUri = null;
        this.mContext.getContentResolver().registerContentObserver(CardContentProvider.REFRESH_CARD_URI, false, this.mObserver);
        this.mContext.getContentResolver().registerContentObserver(CardContentProvider.DELETE_CARD_URI, false, this.mObserver);
    }

    @Override // com.android.settingslib.utils.AsyncLoaderCompat, androidx.loader.content.Loader
    protected void onStopLoading() {
        super.onStopLoading();
        this.mContext.getContentResolver().unregisterContentObserver(this.mObserver);
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public List<ContextualCard> loadInBackground() {
        ArrayList arrayList = new ArrayList();
        if (this.mContext.getResources().getBoolean(R.bool.config_use_legacy_suggestion)) {
            Log.d("ContextualCardLoader", "Skipping - in legacy suggestion mode");
            return arrayList;
        }
        Cursor contextualCardsFromProvider = getContextualCardsFromProvider();
        try {
            if (contextualCardsFromProvider.getCount() > 0) {
                contextualCardsFromProvider.moveToFirst();
                while (!contextualCardsFromProvider.isAfterLast()) {
                    ContextualCard contextualCard = new ContextualCard(contextualCardsFromProvider);
                    if (!contextualCard.isCustomCard()) {
                        if (lambda$getNumberOfLargeCard$0(contextualCard)) {
                            ContextualCard.Builder builderMutate = contextualCard.mutate();
                            builderMutate.setIsLargeCard(true);
                            arrayList.add(builderMutate.build());
                        } else {
                            arrayList.add(contextualCard);
                        }
                    }
                    contextualCardsFromProvider.moveToNext();
                }
            }
            if (contextualCardsFromProvider != null) {
                contextualCardsFromProvider.close();
            }
            return getDisplayableCards(arrayList);
        } catch (Throwable th) {
            if (contextualCardsFromProvider != null) {
                try {
                    contextualCardsFromProvider.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    List<ContextualCard> getDisplayableCards(List<ContextualCard> list) {
        List<ContextualCard> listFilterEligibleCards = filterEligibleCards(list);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int size = listFilterEligibleCards.size();
        for (int i = 0; i < size; i++) {
            if (i < 4) {
                arrayList.add(listFilterEligibleCards.get(i));
            } else {
                arrayList2.add(listFilterEligibleCards.get(i));
            }
        }
        try {
            if (arrayList.size() > 2 && getNumberOfLargeCard(arrayList) != 0) {
                if (arrayList.size() == 4) {
                    arrayList2.add(arrayList.remove(arrayList.size() - 1));
                }
                if (getNumberOfLargeCard(arrayList) == 1) {
                    return arrayList;
                }
                arrayList2.add(arrayList.remove(arrayList.size() - 1));
                if (!CardContentProvider.DELETE_CARD_URI.equals(this.mNotifyUri)) {
                    MetricsFeatureProvider metricsFeatureProvider = FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider();
                    metricsFeatureProvider.action(this.mContext, 1663, ContextualCardLogUtils.buildCardListLog(arrayList));
                    metricsFeatureProvider.action(this.mContext, 1664, ContextualCardLogUtils.buildCardListLog(arrayList2));
                }
                return arrayList;
            }
            if (!CardContentProvider.DELETE_CARD_URI.equals(this.mNotifyUri)) {
                MetricsFeatureProvider metricsFeatureProvider2 = FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider();
                metricsFeatureProvider2.action(this.mContext, 1663, ContextualCardLogUtils.buildCardListLog(arrayList));
                metricsFeatureProvider2.action(this.mContext, 1664, ContextualCardLogUtils.buildCardListLog(arrayList2));
            }
            return arrayList;
        } finally {
            if (!CardContentProvider.DELETE_CARD_URI.equals(this.mNotifyUri)) {
                MetricsFeatureProvider metricsFeatureProvider3 = FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider();
                metricsFeatureProvider3.action(this.mContext, 1663, ContextualCardLogUtils.buildCardListLog(arrayList));
                metricsFeatureProvider3.action(this.mContext, 1664, ContextualCardLogUtils.buildCardListLog(arrayList2));
            }
        }
    }

    Cursor getContextualCardsFromProvider() {
        return CardDatabaseHelper.getInstance(this.mContext).getContextualCards();
    }

    List<ContextualCard> filterEligibleCards(List<ContextualCard> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<ContextualCard> it = list.iterator();
        while (it.hasNext()) {
            arrayList2.add(this.mExecutorService.submit(new EligibleCardChecker(this.mContext, it.next())));
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            try {
                ContextualCard contextualCard = (ContextualCard) ((Future) it2.next()).get(250L, TimeUnit.MILLISECONDS);
                if (contextualCard != null) {
                    arrayList.add(contextualCard);
                }
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                Log.w("ContextualCardLoader", "Failed to get eligible state for card, likely timeout. Skipping", e);
            }
        }
        return arrayList;
    }

    private int getNumberOfLargeCard(List<ContextualCard> list) {
        return (int) list.stream().filter(new Predicate() { // from class: com.android.car.developeroptions.homepage.contextualcards.-$$Lambda$ContextualCardLoader$Ehsjgk-gUeXA0na5HL5IO-9nKRM
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$getNumberOfLargeCard$0$ContextualCardLoader((ContextualCard) obj);
            }
        }).count();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isLargeCard, reason: merged with bridge method [inline-methods] */
    public boolean lambda$getNumberOfLargeCard$0$ContextualCardLoader(ContextualCard contextualCard) {
        return contextualCard.getSliceUri().equals(CustomSliceRegistry.CONTEXTUAL_WIFI_SLICE_URI) || contextualCard.getSliceUri().equals(CustomSliceRegistry.BLUETOOTH_DEVICES_SLICE_URI) || contextualCard.getSliceUri().equals(CustomSliceRegistry.CONTEXTUAL_NOTIFICATION_CHANNEL_SLICE_URI);
    }
}
