package com.amazon.mp3.prime.browse.pager;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.prime.browse.BrowseContentSelectionProvider;
import com.amazon.mp3.prime.browse.RecommendationsFactory;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.music.pager.Pager;
import com.amazon.music.recommendation.SimilarityRequest;
import com.amazon.music.recommendation.rx.RxRecommendation;
import com.amazon.music.storeservice.model.RecommendedAlbumItem;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class SimilarRecommendedAlbumPagerProvider extends AbstractPagerProvider<RecommendedAlbumItem> {
    private final String mAsin;

    public SimilarRecommendedAlbumPagerProvider(final String asin) {
        super(PaginatedRankType.SIMILAR, PaginatedDataType.ALBUM, "empty_filter");
        this.mAsin = asin;
    }

    @Override // com.amazon.mp3.prime.browse.pager.PagerProvider
    public Pager<List<RecommendedAlbumItem>> createPager(Context context) {
        RxRecommendation rxRecommendationCreateRxBrowseRecommendation = RecommendationsFactory.createRxBrowseRecommendation(context);
        SimilarityRequest explicitFilterEnabled = new SimilarityRequest(20, this.mAsin, new BrowseContentSelectionProvider().getRecommendationsContentSelection(context), AccountCredentialStorage.get().getDeviceId(), AccountCredentialStorage.get().getDeviceType()).setExplicitFilterEnabled(AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled());
        ChildUserUtil childUserUtil = ChildUserUtil.INSTANCE;
        if (childUserUtil.isChildUser(context)) {
            explicitFilterEnabled.setMusicRequestIdentityContextToken(childUserUtil.getMusicRequestIdentityContextToken(context));
        }
        return rxRecommendationCreateRxBrowseRecommendation.getSimilarityAlbums(explicitFilterEnabled);
    }
}
