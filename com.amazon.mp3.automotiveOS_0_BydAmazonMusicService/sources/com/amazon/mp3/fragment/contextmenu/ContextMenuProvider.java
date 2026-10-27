package com.amazon.mp3.fragment.contextmenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.auto.carmode.CarModePresetsUtility;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dialog.fragment.NetworkErrorDialogHandler;
import com.amazon.mp3.library.fragment.BasePrimeFragment;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.util.ShopLinkUtil;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playlist.EntityPlaylistUtil;
import com.amazon.mp3.playlist.PlaylistSeedEntityType;
import com.amazon.mp3.prime.browse.SimilarRecommendationsFragment;
import com.amazon.mp3.prime.browse.pager.PaginatedDataType;
import com.amazon.mp3.styles.BauhausTextStyler;
import com.amazon.mp3.util.ArrayUtil;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.MetricsUtil;
import com.amazon.music.find.model.search.SearchItem;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ContextMenuProvider<T> {
    protected FragmentActivity mActivity;
    protected BauhausTextStyler mBauhausTextStyler = new BauhausTextStyler();
    protected String mBlockRef;
    protected T mClickedItemHolder;
    protected Map<String, String> mContentInfo;
    protected String mEntityId;
    protected EntityIdType mEntityIdType;
    protected InteractionType mInteractionType;
    private ContextMenuMetricHandler mMetricHandler;
    private NetworkErrorDialogHandler mNetworkErrorHandler;
    protected PageType mPageType;
    protected String mSourceID;

    public <D extends SearchItem & CatalogContent> void addToPlayQueue(final D content, final boolean playNext) {
    }

    protected abstract int getContextMenuLayoutId();

    public boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, Object tag, String sourceID) {
        return false;
    }

    protected boolean shouldDisplayMenuForItem(int position, Object tag) {
        return true;
    }

    protected abstract void updateContextMenuItems(Activity activity, ContextMenu menu, Object tag, int position, String sourceID);

    protected ContextMenuProvider() {
    }

    public ContextMenuProvider(FragmentActivity activity) {
        init(activity);
    }

    protected void init(FragmentActivity activity) {
        this.mActivity = activity;
        this.mNetworkErrorHandler = new NetworkErrorDialogHandler(activity);
    }

    public void onCreateContextMenu(Activity activity, ContextMenu menu, Object tag, int position, String sourceID) {
        if (shouldDisplayMenuForItem(position, tag)) {
            activity.getMenuInflater().inflate(getContextMenuLayoutId(), menu);
            updateContextMenuItems(activity, menu, tag, position, sourceID);
            this.mBauhausTextStyler.styleMenu(menu);
        }
    }

    protected void setStyledHeaderTitle(CharSequence title, ContextMenu menu) {
        menu.setHeaderTitle(this.mBauhausTextStyler.styleText(title));
    }

    public void onCreateContextMenu(final Activity activity, final ContextMenu menu, final Object tag, final int position, final String sourceID, final ActionType actionType, final PageType pageType, final InteractionType interactionType, final String entityId, final EntityIdType entityIdType) {
        this.mPageType = pageType;
        this.mInteractionType = interactionType;
        setClickedEntityIdAndType(entityId, entityIdType);
        setSourceID(sourceID);
        sendMetricEvent(actionType);
        onCreateContextMenu(activity, menu, tag, position, sourceID);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, AdapterView.AdapterContextMenuInfo menuInfo, String sourceID) {
        setClickedItemHolder(menuInfo.targetView.getTag());
        return false;
    }

    protected void setClickedItemHolder(T clickedItemHolder) {
        this.mClickedItemHolder = clickedItemHolder;
    }

    public T getClickedItemHolder() {
        return this.mClickedItemHolder;
    }

    public void setClickedEntityIdAndType(final String entityId, final EntityIdType entityIdType) {
        this.mEntityId = entityId;
        this.mEntityIdType = entityIdType;
    }

    protected void updateExploreArtistContextMenu(Activity activity, Menu menu, String artist) {
        if (ShopLinkUtil.isArtistBlacklisted(artist) || !AmazonApplication.getCapabilities().isStoreSupported() || ChildUserUtil.INSTANCE.isChildUser(activity)) {
            menu.removeItem(R.id.MenuExploreArtist);
            return;
        }
        String string = activity.getString(R.string.dmusic_context_shop_for, artist);
        MenuItem menuItemFindItem = menu.findItem(R.id.MenuExploreArtist);
        if (menuItemFindItem != null) {
            menuItemFindItem.setTitle(string);
        }
    }

    protected void updatePresetsContextMenu(Context context, CatalogContent content, Menu menu, int add, int remove) {
        if (!CarModeUtility.isCarModeEnabled() || content == null) {
            menu.removeItem(add);
            menu.removeItem(remove);
            return;
        }
        if (CarModePresetsUtility.isPreset(context, content)) {
            menu.removeItem(add);
            return;
        }
        menu.removeItem(remove);
        if ((content instanceof Playlist) && ((Playlist) content).getTrackCount() == 0) {
            MenuItem menuItemFindItem = menu.findItem(add);
            if (menuItemFindItem != null) {
                menuItemFindItem.setEnabled(false);
                return;
            }
            return;
        }
        if (CarModePresetsUtility.isContentEligible(content)) {
            return;
        }
        menu.removeItem(add);
    }

    protected void goToSimilarRecommendationsFragment(PaginatedDataType dataType, String asin) {
        if (asin == null) {
            return;
        }
        BasePrimeFragment basePrimeFragmentBuild = new SimilarRecommendationsFragment.Builder(new Bundle()).setDataType(dataType).setAsin(asin).build();
        FragmentActivity fragmentActivity = this.mActivity;
        if (fragmentActivity instanceof NowPlayingFragmentActivity) {
            Intent intent = new Intent();
            intent.putExtra("SIMILAR_RECS_FRAGMENT_DATA_TYPE_KEY", dataType);
            intent.putExtra("SIMILAR_RECS_FRAGMENT_ASIN_KEY", asin);
            intent.setClass(this.mActivity.getApplicationContext(), MusicHomeActivity.class);
            intent.putExtra("com.amazon.mp3.fragment.extra", SimilarRecommendationsFragment.class.getSimpleName());
            this.mActivity.startActivity(intent);
            return;
        }
        changeScreenFragment(fragmentActivity.getSupportFragmentManager(), basePrimeFragmentBuild);
    }

    protected void goToEntityPlaylistFragment(String entityId, PlaylistSeedEntityType entityType) {
        sendMetricEvent(ActionType.GO_ENTITY_PLAYLIST);
        if (entityId == null || entityType == null) {
            return;
        }
        FragmentActivity fragmentActivity = this.mActivity;
        if (fragmentActivity instanceof NowPlayingFragmentActivity) {
            this.mActivity.startActivity(EntityPlaylistUtil.buildEntityPlaylistIntent(fragmentActivity.getApplicationContext(), entityId, entityType));
        } else {
            changeScreenFragment(this.mActivity.getSupportFragmentManager(), EntityPlaylistUtil.buildEntityPlaylistFetchFragment(entityId, entityType));
        }
    }

    protected void changeScreenFragment(FragmentManager manager, Fragment fragment) {
        FragmentTransaction fragmentTransactionBeginTransaction = manager.beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.music_screen_container, fragment, fragment.getClass().getSimpleName());
        fragmentTransactionBeginTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    protected void sendMetricEvent(final ActionType actionType) {
        MetricsUtil.emitLatencyStartLegMetricForAllowedActionTypes(actionType);
        ContextMenuMetricHandler contextMenuMetricHandler = this.mMetricHandler;
        if (contextMenuMetricHandler == null || !contextMenuMetricHandler.handleSendMetricEvent(new ContextMenuItemClickEvent(getClickedItemHolder(), this.mSourceID, actionType, this.mInteractionType, this.mPageType, this.mBlockRef, this.mEntityId, this.mEntityIdType, this.mContentInfo))) {
            if (actionType != ActionType.VIEW_CONTEXT_MENU) {
                this.mInteractionType = InteractionType.TAP;
            }
            if (actionType != null) {
                MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withInteractionType(this.mInteractionType).withPageType(this.mPageType).withEntityId(this.mEntityId).withEntityIdType(this.mEntityIdType).withEventTime(System.currentTimeMillis()).withBlockRef(this.mBlockRef).withContentInfo(ArrayUtil.convertToListExcludingNulls(this.mContentInfo)).build());
            }
        }
    }

    public <D extends SearchItem & CatalogContent> void addToPlayQueue(final FragmentActivity activity, final D content, final boolean playNext) {
        ContentAccessUtil.checkCanContinueCatalogAction(activity, content, ContentAccessUtil.ContentAccessOperation.STREAM, new ContentAccessUtil.CatalogActionListener<D>() { // from class: com.amazon.mp3.fragment.contextmenu.ContextMenuProvider.1
            /* JADX WARN: Incorrect types in method signature: (TD;Z)V */
            @Override // com.amazon.mp3.util.ContentAccessUtil.CatalogActionListener
            public void continueCatalogAction(final SearchItem item, final boolean canContinue) {
                if (canContinue) {
                    ContextMenuProvider.this.addToPlayQueue(content, playNext);
                }
            }
        });
    }

    protected boolean isOfflineLibrary(final String sourceID) {
        return "cirrus-local".equals(sourceID);
    }

    public void setBlockRef(final String blockRef) {
        this.mBlockRef = blockRef;
    }

    public void setContentInfo(final Map<String, String> contentInfo) {
        this.mContentInfo = contentInfo;
    }

    public void setPageType(final PageType pageType) {
        this.mPageType = pageType;
    }

    public PageType getPageType() {
        return this.mPageType;
    }

    public void setSourceID(final String sourceID) {
        this.mSourceID = sourceID;
    }

    public void setMetricHandler(final ContextMenuMetricHandler metricHandler) {
        this.mMetricHandler = metricHandler;
    }
}
