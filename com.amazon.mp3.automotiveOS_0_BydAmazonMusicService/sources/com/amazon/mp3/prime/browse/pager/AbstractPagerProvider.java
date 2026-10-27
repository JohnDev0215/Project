package com.amazon.mp3.prime.browse.pager;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.prime.browse.BrowseContentSelectionProvider;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.music.browse.BrowseItemRequest;
import com.amazon.music.browse.RankType;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractPagerProvider<T> implements PagerProvider<T> {
    protected PaginatedDataType mDataType;
    protected PaginatedRankType mRankType;
    protected String mRefinementId;

    protected AbstractPagerProvider(final PaginatedRankType rankType, final PaginatedDataType dataType, final String refinementID) {
        this.mRankType = rankType;
        this.mDataType = dataType;
        this.mRefinementId = refinementID;
    }

    @Override // com.amazon.mp3.prime.browse.pager.PagerProvider
    public PaginatedRankType getRankType() {
        return this.mRankType;
    }

    @Override // com.amazon.mp3.prime.browse.pager.PagerProvider
    public PaginatedDataType getDataType() {
        return this.mDataType;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.prime.browse.pager.AbstractPagerProvider$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedRankType;

        static {
            int[] iArr = new int[PaginatedRankType.values().length];
            $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedRankType = iArr;
            try {
                iArr[PaginatedRankType.TOP_SELLERS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedRankType[PaginatedRankType.POPULAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedRankType[PaginatedRankType.NEWLY_ADDED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedRankType[PaginatedRankType.NEWLY_RELEASED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    protected RankType convertToRankType() {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedRankType[this.mRankType.ordinal()];
        if (i == 1) {
            return RankType.TOP_SELLERS;
        }
        if (i == 2) {
            return RankType.POPULAR;
        }
        if (i == 3) {
            return RankType.NEWLY_ADDED;
        }
        if (i == 4) {
            return RankType.NEWLY_RELEASED;
        }
        throw new IllegalArgumentException("Illegal PaginatedRankType: " + this.mRankType.getValue());
    }

    protected BrowseItemRequest createRequest(Context context) {
        BrowseItemRequest browseItemRequest = new BrowseItemRequest(convertToRankType(), 20, new BrowseContentSelectionProvider().getBrowseContentSelection(context), AccountCredentialStorage.get().getDeviceId(), AccountCredentialStorage.get().getDeviceType(), AmazonApplication.getLocale().toString());
        if (!"empty_filter".equals(this.mRefinementId)) {
            browseItemRequest.setRefinement(this.mRefinementId);
        }
        browseItemRequest.setExplicitFilterEnabled(AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled());
        ChildUserUtil childUserUtil = ChildUserUtil.INSTANCE;
        if (childUserUtil.isChildUser(context)) {
            browseItemRequest.setMusicRequestIdentityContextToken(childUserUtil.getMusicRequestIdentityContextToken(context));
        }
        return browseItemRequest;
    }
}
