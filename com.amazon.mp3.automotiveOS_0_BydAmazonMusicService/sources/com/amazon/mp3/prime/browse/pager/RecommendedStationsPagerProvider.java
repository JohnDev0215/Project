package com.amazon.mp3.prime.browse.pager;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.prime.browse.BrowseContentSelectionProvider;
import com.amazon.mp3.prime.browse.RecommendationsFactory;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.music.pager.Pager;
import com.amazon.music.recommendation.BrowseRequest;
import com.amazon.music.recommendation.RecommendationRequestPageOptions;
import com.amazon.music.recommendation.rx.RxRecommendation;
import com.amazon.music.storeservice.model.RecommendedStationItem;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RecommendedStationsPagerProvider extends AbstractPagerProvider<RecommendedStationItem> {
    private final String mWidgetId;

    public RecommendedStationsPagerProvider(final String widgetId) {
        super(PaginatedRankType.RECOMMENDED, PaginatedDataType.STATION, "empty_filter");
        this.mWidgetId = widgetId;
    }

    @Override // com.amazon.mp3.prime.browse.pager.PagerProvider
    public Pager<List<RecommendedStationItem>> createPager(Context context) {
        RxRecommendation rxRecommendationCreateRxBrowseRecommendation = RecommendationsFactory.createRxBrowseRecommendation(context);
        BrowseRequest explicitFilterEnabled = new BrowseRequest(20, new BrowseContentSelectionProvider().getRecommendationsContentSelection(context), AccountCredentialStorage.get().getDeviceId(), AccountCredentialStorage.get().getDeviceType()).setExplicitFilterEnabled(AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled());
        ChildUserUtil childUserUtil = ChildUserUtil.INSTANCE;
        if (childUserUtil.isChildUser(context)) {
            explicitFilterEnabled.setMusicRequestIdentityContextToken(childUserUtil.getMusicRequestIdentityContextToken(context));
        }
        return rxRecommendationCreateRxBrowseRecommendation.getBrowseStations(explicitFilterEnabled, new RecommendationRequestPageOptions(this.mWidgetId));
    }
}
