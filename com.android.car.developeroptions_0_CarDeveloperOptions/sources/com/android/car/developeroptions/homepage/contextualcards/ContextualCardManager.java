package com.android.car.developeroptions.homepage.contextualcards;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.util.ArrayMap;
import android.util.Log;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCardLoader;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnSaveInstanceState;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class ContextualCardManager implements ContextualCardLoader.CardContentLoaderListener, ContextualCardUpdateListener, LifecycleObserver, OnSaveInstanceState {
    static final long CARD_CONTENT_LOADER_TIMEOUT_MS = 1000;
    static final String KEY_CONTEXTUAL_CARDS = "key_contextual_cards";
    static final String KEY_GLOBAL_CARD_LOADER_TIMEOUT = "global_card_loader_timeout_key";
    private static final int[] SETTINGS_CARDS = {3, 2};
    private final Context mContext;
    boolean mIsFirstLaunch;
    private final Lifecycle mLifecycle;
    private ContextualCardUpdateListener mListener;
    List<String> mSavedCards;
    long mStartTime;
    final List<ContextualCard> mContextualCards = new ArrayList();
    private final List<LifecycleObserver> mLifecycleObservers = new ArrayList();
    private final ControllerRendererPool mControllerRendererPool = new ControllerRendererPool();

    public ContextualCardManager(Context context, Lifecycle lifecycle, Bundle bundle) {
        this.mContext = context;
        this.mLifecycle = lifecycle;
        this.mLifecycle.addObserver(this);
        if (bundle == null) {
            this.mIsFirstLaunch = true;
            this.mSavedCards = null;
        } else {
            this.mSavedCards = bundle.getStringArrayList(KEY_CONTEXTUAL_CARDS);
        }
        for (int i : SETTINGS_CARDS) {
            setupController(i);
        }
    }

    void loadContextualCards(LoaderManager loaderManager) {
        this.mStartTime = System.currentTimeMillis();
        CardContentLoaderCallbacks cardContentLoaderCallbacks = new CardContentLoaderCallbacks(this.mContext);
        cardContentLoaderCallbacks.setListener(this);
        loaderManager.initLoader(1, null, cardContentLoaderCallbacks);
    }

    private void loadCardControllers() {
        Iterator<ContextualCard> it = this.mContextualCards.iterator();
        while (it.hasNext()) {
            setupController(it.next().getCardType());
        }
    }

    void setupController(int i) {
        ContextualCardController controller = this.mControllerRendererPool.getController(this.mContext, i);
        if (controller == null) {
            Log.w("ContextualCardManager", "Cannot find ContextualCardController for type " + i);
            return;
        }
        controller.setCardUpdateListener(this);
        if (!(controller instanceof LifecycleObserver) || this.mLifecycleObservers.contains(controller)) {
            return;
        }
        LifecycleObserver lifecycleObserver = (LifecycleObserver) controller;
        this.mLifecycleObservers.add(lifecycleObserver);
        this.mLifecycle.addObserver(lifecycleObserver);
    }

    List<ContextualCard> sortCards(List<ContextualCard> list) {
        return (List) list.stream().sorted(new Comparator() { // from class: com.android.car.developeroptions.homepage.contextualcards.-$$Lambda$ContextualCardManager$DDYR9O6RrdNTYhhBmXTE4ef8fn8
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Double.compare(((ContextualCard) obj2).getRankingScore(), ((ContextualCard) obj).getRankingScore());
            }
        }).collect(Collectors.toList());
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCardUpdateListener
    public void onContextualCardUpdated(Map<Integer, List<ContextualCard>> map) {
        List list;
        final Set<Integer> setKeySet = map.keySet();
        if (setKeySet.isEmpty()) {
            final TreeSet treeSet = new TreeSet() { // from class: com.android.car.developeroptions.homepage.contextualcards.ContextualCardManager.1
                {
                    add(3);
                    add(4);
                    add(5);
                }
            };
            list = (List) this.mContextualCards.stream().filter(new Predicate() { // from class: com.android.car.developeroptions.homepage.contextualcards.-$$Lambda$ContextualCardManager$U2sWvcdlIItcXpbT1EOOMcr21p0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return treeSet.contains(Integer.valueOf(((ContextualCard) obj).getCardType()));
                }
            }).collect(Collectors.toList());
        } else {
            list = (List) this.mContextualCards.stream().filter(new Predicate() { // from class: com.android.car.developeroptions.homepage.contextualcards.-$$Lambda$ContextualCardManager$yJ0CTJPCFKO--R_wklWeHL2pXns
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ContextualCardManager.lambda$onContextualCardUpdated$2(setKeySet, (ContextualCard) obj);
                }
            }).collect(Collectors.toList());
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        arrayList.addAll((Collection) map.values().stream().flatMap(new Function() { // from class: com.android.car.developeroptions.homepage.contextualcards.-$$Lambda$seyL25CSW2NInOydsTbSDrNW6pM
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((List) obj).stream();
            }
        }).collect(Collectors.toList()));
        this.mContextualCards.clear();
        this.mContextualCards.addAll(getCardsWithViewType(sortCards(arrayList)));
        loadCardControllers();
        if (this.mListener != null) {
            ArrayMap arrayMap = new ArrayMap();
            arrayMap.put(0, this.mContextualCards);
            this.mListener.onContextualCardUpdated(arrayMap);
        }
    }

    static /* synthetic */ boolean lambda$onContextualCardUpdated$2(Set set, ContextualCard contextualCard) {
        return !set.contains(Integer.valueOf(contextualCard.getCardType()));
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCardLoader.CardContentLoaderListener
    public void onFinishCardLoading(List<ContextualCard> list) {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.mStartTime;
        Log.d("ContextualCardManager", "Total loading time = " + jCurrentTimeMillis);
        List<ContextualCard> cardsToKeep = getCardsToKeep(list);
        if (!this.mIsFirstLaunch) {
            onContextualCardUpdated((Map) cardsToKeep.stream().collect(Collectors.groupingBy(new Function() { // from class: com.android.car.developeroptions.homepage.contextualcards.-$$Lambda$8KSx1iQVoPBL9IYWMXMDQhGHbW8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Integer.valueOf(((ContextualCard) obj).getCardType());
                }
            })));
            return;
        }
        MetricsFeatureProvider metricsFeatureProvider = FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider();
        if (jCurrentTimeMillis <= getCardLoaderTimeout()) {
            onContextualCardUpdated((Map) list.stream().collect(Collectors.groupingBy(new Function() { // from class: com.android.car.developeroptions.homepage.contextualcards.-$$Lambda$8KSx1iQVoPBL9IYWMXMDQhGHbW8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Integer.valueOf(((ContextualCard) obj).getCardType());
                }
            })));
        } else {
            metricsFeatureProvider.action(0, 1685, 1502, null, (int) jCurrentTimeMillis);
        }
        long jCurrentTimeMillis2 = System.currentTimeMillis() - this.mStartTime;
        FeatureFactory.getFactory(this.mContext).getContextualCardFeatureProvider(this.mContext).logHomepageDisplay(jCurrentTimeMillis2);
        metricsFeatureProvider.action(this.mContext, 1662, (int) jCurrentTimeMillis2);
        this.mIsFirstLaunch = false;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnSaveInstanceState
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putStringArrayList(KEY_CONTEXTUAL_CARDS, (ArrayList) this.mContextualCards.stream().map(new Function() { // from class: com.android.car.developeroptions.homepage.contextualcards.-$$Lambda$VkrlOsvgl-00jihd76E6nSvcKEg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ContextualCard) obj).getName();
            }
        }).collect(Collectors.toCollection(new Supplier() { // from class: com.android.car.developeroptions.homepage.contextualcards.-$$Lambda$OGSS2qx6njxlnp0dnKb4lA3jnw8
            @Override // java.util.function.Supplier
            public final Object get() {
                return new ArrayList();
            }
        })));
    }

    public void onWindowFocusChanged(boolean z) {
        Iterator it = new ArrayList(this.mContextualCards).iterator();
        while (it.hasNext()) {
            ContextualCardController controller = this.mControllerRendererPool.getController(this.mContext, ((ContextualCard) it.next()).getCardType());
            if (z && (controller instanceof OnStart)) {
                ((OnStart) controller).onStart();
            }
            if (!z && (controller instanceof OnStop)) {
                ((OnStop) controller).onStop();
            }
        }
    }

    public ControllerRendererPool getControllerRendererPool() {
        return this.mControllerRendererPool;
    }

    void setListener(ContextualCardUpdateListener contextualCardUpdateListener) {
        this.mListener = contextualCardUpdateListener;
    }

    List<ContextualCard> getCardsWithViewType(List<ContextualCard> list) {
        return list.isEmpty() ? list : getCardsWithSuggestionViewType(getCardsWithDeferredSetupViewType(list));
    }

    long getCardLoaderTimeout() {
        return Settings.Global.getLong(this.mContext.getContentResolver(), KEY_GLOBAL_CARD_LOADER_TIMEOUT, CARD_CONTENT_LOADER_TIMEOUT_MS);
    }

    private List<ContextualCard> getCardsWithSuggestionViewType(List<ContextualCard> list) {
        ArrayList arrayList = new ArrayList(list);
        int i = 1;
        while (i < arrayList.size()) {
            int i2 = i - 1;
            ContextualCard contextualCard = (ContextualCard) arrayList.get(i2);
            ContextualCard contextualCard2 = (ContextualCard) arrayList.get(i);
            if (contextualCard2.getCategory() == 1 && contextualCard.getCategory() == 1) {
                ContextualCard.Builder builderMutate = contextualCard.mutate();
                builderMutate.setViewType(R.layout.contextual_slice_half_tile);
                arrayList.set(i2, builderMutate.build());
                ContextualCard.Builder builderMutate2 = contextualCard2.mutate();
                builderMutate2.setViewType(R.layout.contextual_slice_half_tile);
                arrayList.set(i, builderMutate2.build());
                i++;
            }
            i++;
        }
        return arrayList;
    }

    private List<ContextualCard> getCardsWithDeferredSetupViewType(List<ContextualCard> list) {
        ArrayList arrayList = new ArrayList(list);
        for (int i = 0; i < arrayList.size(); i++) {
            ContextualCard contextualCard = list.get(i);
            if (contextualCard.getCategory() == 5) {
                ContextualCard.Builder builderMutate = contextualCard.mutate();
                builderMutate.setViewType(R.layout.contextual_slice_deferred_setup);
                arrayList.set(i, builderMutate.build());
                return arrayList;
            }
        }
        return arrayList;
    }

    List<ContextualCard> getCardsToKeep(List<ContextualCard> list) {
        if (this.mSavedCards != null) {
            List<ContextualCard> list2 = (List) list.stream().filter(new Predicate() { // from class: com.android.car.developeroptions.homepage.contextualcards.-$$Lambda$ContextualCardManager$skEw_HKcazNKey3FuimoGfctwcA
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.f$0.lambda$getCardsToKeep$3$ContextualCardManager((ContextualCard) obj);
                }
            }).collect(Collectors.toList());
            this.mSavedCards = null;
            return list2;
        }
        return (List) list.stream().filter(new Predicate() { // from class: com.android.car.developeroptions.homepage.contextualcards.-$$Lambda$ContextualCardManager$XrszeRMEycs4tYNCWadeHa9wq1Y
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$getCardsToKeep$4$ContextualCardManager((ContextualCard) obj);
            }
        }).collect(Collectors.toList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$getCardsToKeep$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$getCardsToKeep$3$ContextualCardManager(ContextualCard contextualCard) {
        return this.mSavedCards.contains(contextualCard.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$getCardsToKeep$4, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$getCardsToKeep$4$ContextualCardManager(ContextualCard contextualCard) {
        return this.mContextualCards.contains(contextualCard);
    }

    static class CardContentLoaderCallbacks implements LoaderManager.LoaderCallbacks<List<ContextualCard>> {
        private Context mContext;
        private ContextualCardLoader.CardContentLoaderListener mListener;

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<List<ContextualCard>> loader) {
        }

        CardContentLoaderCallbacks(Context context) {
            this.mContext = context.getApplicationContext();
        }

        protected void setListener(ContextualCardLoader.CardContentLoaderListener cardContentLoaderListener) {
            this.mListener = cardContentLoaderListener;
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<List<ContextualCard>> onCreateLoader(int i, Bundle bundle) {
            if (i == 1) {
                return new ContextualCardLoader(this.mContext);
            }
            throw new IllegalArgumentException("Unknown loader id: " + i);
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<List<ContextualCard>> loader, List<ContextualCard> list) {
            ContextualCardLoader.CardContentLoaderListener cardContentLoaderListener = this.mListener;
            if (cardContentLoaderListener != null) {
                cardContentLoaderListener.onFinishCardLoading(list);
            }
        }
    }
}
