package com.amazon.mp3.prime;

import UpsellInterface.v1_0.UpsellType;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.library.fetcher.FetchListener;
import com.amazon.mp3.library.fetcher.Fetcher;
import com.amazon.mp3.util.Log;
import com.amazon.music.station.StationItem;
import com.amazon.music.station.StationManager;
import com.amazon.music.storeservice.model.GetStationRecommendationsResponse;
import com.amazon.music.storeservice.model.RecommendedStationItem;
import com.amazon.music.storeservice.model.StationRecommendations;
import com.android.volley.VolleyError;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class RecommendedStationsFetcher extends Fetcher<StationItem> {
    private static final String TAG = "RecommendedStationsFetcher";
    private Map mNextTokenMap;
    private final StationManager mStationManager;

    @Override // com.amazon.mp3.library.fetcher.Fetcher
    public int getItemsPerPage() {
        return 20;
    }

    public RecommendedStationsFetcher(final StationManager stationManager) {
        this.mStationManager = stationManager;
    }

    @Override // com.amazon.mp3.library.fetcher.Fetcher
    protected Collection<StationItem> doFetch() {
        try {
            GetStationRecommendationsResponse stationRecommendations = this.mStationManager.getStationRecommendations(20, 20, getParentalControls(), this.mNextTokenMap);
            this.mNextTokenMap = getNextTokenMap(stationRecommendations);
            return onResponse(stationRecommendations);
        } catch (VolleyError e) {
            onErrorResponse(e, Fetcher.ErrorType.PROCESSING);
            return new ArrayList(0);
        }
    }

    private Map getNextTokenMap(final GetStationRecommendationsResponse response) {
        StationRecommendations stationRecommendations;
        if (response == null || response.getRecommendations() == null || (stationRecommendations = response.getRecommendations().get(0)) == null) {
            return null;
        }
        return Collections.singletonMap(stationRecommendations.getWidgetId(), stationRecommendations.getNextResultsToken());
    }

    private Collection<StationItem> onResponse(final GetStationRecommendationsResponse getStationRecommendationsResponse) throws VolleyError {
        ArrayList arrayList = new ArrayList(getStationRecommendationsResponse.getRecommendations().size());
        Iterator<StationRecommendations> it = getStationRecommendationsResponse.getRecommendations().iterator();
        while (it.hasNext()) {
            Iterator<RecommendedStationItem> it2 = it.next().getStations().iterator();
            while (it2.hasNext()) {
                arrayList.add(createStationItemFromRecItem(it2.next()));
            }
        }
        return arrayList;
    }

    private void onErrorResponse(final VolleyError volleyError, final Fetcher.ErrorType errorType) {
        Log.error(TAG, volleyError.toString());
        setHasError(true, errorType);
    }

    private StationItem createStationItemFromRecItem(RecommendedStationItem recommendedStationItem) {
        Boolean bool = Boolean.TRUE;
        return new StationItem(bool.equals(recommendedStationItem.isIsMusicSubscription()), bool.equals(recommendedStationItem.isIsPrime()), bool.equals(Boolean.valueOf(PrimeItemsTransformationUtil.isStationExplicit(recommendedStationItem.getSubscriptionTierToDetails(), UpsellType.PRIME))), bool.equals(Boolean.valueOf(PrimeItemsTransformationUtil.isStationExplicit(recommendedStationItem.getSubscriptionTierToDetails(), "MUSIC_SUBSCRIPTION"))), recommendedStationItem.getStationTitle(), recommendedStationItem.getStationImageUrl(), recommendedStationItem.getSeed().getSeedId(), recommendedStationItem.getSeed().getType(), recommendedStationItem.getStationKey());
    }

    @Override // com.amazon.mp3.library.fetcher.Fetcher
    public void requestNextPage() {
        if (this.mIsFetching) {
            return;
        }
        if (!hasMore()) {
            Iterator it = this.mFetchListeners.iterator();
            while (it.hasNext()) {
                ((FetchListener) it.next()).onFetchCompleted(this.mPageNo, null);
            }
            return;
        }
        super.requestNextPage();
    }
}
