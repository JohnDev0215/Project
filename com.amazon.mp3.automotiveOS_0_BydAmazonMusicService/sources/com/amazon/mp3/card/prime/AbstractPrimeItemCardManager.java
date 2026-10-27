package com.amazon.mp3.card.prime;

import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.card.Card;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.events.types.EntityIdType;
import com.amazon.music.events.types.InteractionType;
import com.amazon.music.find.model.search.SearchItem;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractPrimeItemCardManager<T extends SearchItem & CatalogContent> implements Card.OnItemClickListener<T>, ContentAccessUtil.CatalogActionListener<T> {
    protected final BaseActivity mActivity;
    private List<Map<String, String>> mContentInfo;
    private final NetworkErrorDialog.INetworkErrorDialogHandler mNetworkErrorDialogHandler;
    private final String mPageType;

    protected abstract boolean canViewItemDetail();

    protected abstract void handleOnClick(T item);

    public AbstractPrimeItemCardManager(final BaseActivity activity, final NetworkErrorDialog.INetworkErrorDialogHandler networkErrorDialogHandler, final List<Map<String, String>> contentInfo, final String pageType) {
        this.mActivity = activity;
        this.mNetworkErrorDialogHandler = networkErrorDialogHandler;
        this.mContentInfo = contentInfo;
        this.mPageType = pageType;
    }

    @Override // com.amazon.mp3.card.Card.OnItemClickListener
    public void onItemClick(final T item) {
        if (canViewItemDetail()) {
            handleOnClick(item);
        } else {
            ContentAccessUtil.checkCanContinueCatalogAction(this.mActivity, item, ContentAccessUtil.ContentAccessOperation.STREAM, this);
        }
    }

    @Override // com.amazon.mp3.util.ContentAccessUtil.CatalogActionListener
    public void continueCatalogAction(final T item, final boolean canContinue) {
        if (canContinue) {
            handleOnClick(item);
        }
    }

    public void sendUiClickMetric(final String identifier, final String entityId, final EntityIdType entityIdType, final String pageType) {
        UserInteractionAppEvent.builder(identifier).withEntityId(entityId).withEntityIdType(entityIdType).withPageType(pageType).withInteractionType(InteractionType.TAP).withContentInfo(this.mContentInfo).publish();
    }

    public String getPageType() {
        return this.mPageType;
    }
}
