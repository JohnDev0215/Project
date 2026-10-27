package com.amazon.mp3.library.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.DownloadItem;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.fragment.BaseFragment;
import com.amazon.mp3.library.adapter.DownloadsAdapter;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.IArtCache;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.styles.BauhausTextStyler;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ListViewUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.RequestItem;
import com.amazon.music.downloads.RequestProvider;
import com.amazon.music.downloads.notification.DownloadListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadsFragment extends LibraryBaseFragment implements AbsListView.OnScrollListener, IArtCache {
    private static final String TAG = DownloadsFragment.class.getSimpleName();
    protected ActionBarProvider mActionBarProvider;
    protected int mAlbumArtSize;
    protected ActionBarView mHeaderView;
    private View mRootView;
    private boolean mShowHomeAsUp;
    private DownloadsAdapter mAdapter = null;
    private CacheManager mCacheManager = null;
    private AbsListView mListView = null;
    protected int mSavedPosition = -1;
    private final View.OnClickListener mSeeCompletedClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.DownloadsFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(DownloadsFragment.this.getActivity(), MusicHomeActivity.class);
            intent.putExtra("com.amazon.mp3.fragment.extra", RecentActivityTabFragmentHost.class.getSimpleName());
            intent.putExtra("com.amazon.mp3.library.fragment.EXTRA_TAB_POSITION_TO_OPEN", RecentActivityTabFragmentHost.getRecentActivityDownloadedPosition());
            DownloadsFragment.this.startActivity(intent);
        }
    };
    private RequestProvider.RequestProviderCallback mRequestCallback = new RequestProvider.RequestProviderCallback() { // from class: com.amazon.mp3.library.fragment.DownloadsFragment.2
        @Override // com.amazon.music.downloads.RequestProvider.RequestProviderCallback
        public void requestsSubmitted(final Set<RequestItem> requests) {
            Iterator<RequestItem> it;
            Uri primeAdditionalTrackContentUri;
            MusicDownloader musicDownloader = MusicDownloader.getInstance(DownloadsFragment.this.getApplication());
            ArrayList arrayList = new ArrayList();
            Iterator<RequestItem> it2 = requests.iterator();
            while (it2.hasNext()) {
                RequestItem next = it2.next();
                String requestId = next.getRequestId();
                long timestamp = next.getTimestamp();
                if (!next.isBackground()) {
                    DownloadsFragment.this.mDownloadListener.addRequestId(requestId);
                    int i = AnonymousClass9.$SwitchMap$com$amazon$music$downloads$RequestItem$RequestType[next.getRequestType().ordinal()];
                    if (i == 1) {
                        it = it2;
                        Group group = musicDownloader.getGroup(next.getRequestId());
                        if (group == null) {
                            Log.warning(DownloadsFragment.TAG, "Unable to download a group for " + next.getRequestId());
                        } else {
                            arrayList.add(new DownloadItem(requestId, RequestItem.RequestType.GROUP, Uri.parse(requestId), group.getDownloadState(), group.getErrorReason(), group.getNotificationInfo(), DownloadsFragment.this.isAnyOwned(group), group.size(), timestamp));
                        }
                    } else if (i != 2) {
                        it = it2;
                    } else {
                        Item item = musicDownloader.getItem(next.getRequestId());
                        if (item.isOwned()) {
                            primeAdditionalTrackContentUri = MediaProvider.Tracks.getContentUri("cirrus", item.getLuid());
                        } else {
                            primeAdditionalTrackContentUri = MediaProvider.Tracks.getPrimeAdditionalTrackContentUri("cirrus", "cta", item.getAsin());
                        }
                        it = it2;
                        arrayList.add(new DownloadItem(requestId, RequestItem.RequestType.ITEM, primeAdditionalTrackContentUri, item.getDownloadState(), item.getErrorReason(), item.getNotificationInfo(), item.isOwned(), 1, timestamp));
                    }
                    it2 = it;
                }
            }
            DownloadsFragment.this.loadAdapter(arrayList);
            if (arrayList.isEmpty()) {
                ((BaseFragment) DownloadsFragment.this).mHandler.post(new Runnable() { // from class: com.amazon.mp3.library.fragment.DownloadsFragment.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        DownloadsFragment.this.setShowEmptyList();
                    }
                });
            }
        }
    };
    private View.OnCreateContextMenuListener mOnCreateContextMenuListener = new View.OnCreateContextMenuListener() { // from class: com.amazon.mp3.library.fragment.DownloadsFragment.4
        @Override // android.view.View.OnCreateContextMenuListener
        public void onCreateContextMenu(ContextMenu menu, View unused, ContextMenu.ContextMenuInfo menuInfo) {
            BauhausTextStyler bauhausTextStyler = new BauhausTextStyler();
            if (menuInfo instanceof AdapterView.AdapterContextMenuInfo) {
                DownloadsAdapter.RowViewHolder rowViewHolder = (DownloadsAdapter.RowViewHolder) ((AdapterView.AdapterContextMenuInfo) menuInfo).targetView.getTag();
                DownloadsFragment.this.getActivity().getMenuInflater().inflate(DownloadsFragment.this.getContextMenuId(), menu);
                menu.setHeaderTitle(bauhausTextStyler.styleText(rowViewHolder.mName.getText().toString()));
                bauhausTextStyler.styleMenu(menu);
            }
        }
    };
    private BroadcastReceiver mArtLoadedReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.library.fragment.DownloadsFragment.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DownloadsFragment.this.onArtReceived(context, intent);
        }
    };
    private Runnable mShowNetworkErrorDialog = new Runnable() { // from class: com.amazon.mp3.library.fragment.DownloadsFragment.6
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // java.lang.Runnable
        public void run() {
            DownloadsFragment downloadsFragment = DownloadsFragment.this;
            downloadsFragment.showNetworkErrorDialog(downloadsFragment);
        }
    };
    private final BroadcastReceiver mNewDownloadReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.library.fragment.DownloadsFragment.7
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, final Intent intent) {
            DownloadsFragment.this.refreshDownloadList();
        }
    };
    private final DownloadListener mDownloadListener = new DownloadListener(new HashSet()) { // from class: com.amazon.mp3.library.fragment.DownloadsFragment.8
        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onProgressUpdate(final String id, final Group group, final float percentageDownloaded) {
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onProgressUpdate(final String id, final Item item, final float percentageDownloaded) {
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onStatusUpdate(final DownloadState state, final String id, final Group group) {
            if (state == DownloadState.DOWNLOADED || state == DownloadState.CANCELED) {
                DownloadsFragment.this.refreshDownloadList();
            } else {
                ((BaseFragment) DownloadsFragment.this).mHandler.post(new Runnable() { // from class: com.amazon.mp3.library.fragment.DownloadsFragment.8.1
                    @Override // java.lang.Runnable
                    public void run() {
                        DownloadsFragment.this.mAdapter.updateItem(id, state, group.getErrorReason());
                    }
                });
            }
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onStatusUpdate(final DownloadState state, final String id, final Item item) {
            if (state == DownloadState.DOWNLOADED || state == DownloadState.CANCELED) {
                DownloadsFragment.this.refreshDownloadList();
            } else {
                ((BaseFragment) DownloadsFragment.this).mHandler.post(new Runnable() { // from class: com.amazon.mp3.library.fragment.DownloadsFragment.8.2
                    @Override // java.lang.Runnable
                    public void run() {
                        DownloadsFragment.this.mAdapter.updateItem(id, state, item.getErrorReason());
                    }
                });
            }
        }
    };

    protected int getContentViewLayoutId() {
        return R.layout.library_downloads_fragment;
    }

    protected int getContextMenuId() {
        return R.menu.download_active_item_context;
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
    public void onConnectionErrorDialogCancel() {
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
    public void onDialogDismiss() {
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    public static DownloadsFragment newInstance(boolean showHomeAsUp) {
        DownloadsFragment downloadsFragment = new DownloadsFragment();
        Intent intent = new Intent();
        intent.putExtra("com.amazon.mp3.library.EXTRA_SHOW_HEADER", false);
        intent.putExtra("add_to_back_stack", showHomeAsUp);
        downloadsFragment.setArguments(intent.getExtras());
        return downloadsFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = View.inflate(getActivity(), getContentViewLayoutId(), null);
        this.mRootView = viewInflate;
        ListView listView = (ListView) viewInflate.findViewById(R.id.ListView);
        this.mListView = listView;
        listView.setOnScrollListener(this);
        if (savedInstanceState != null && savedInstanceState.containsKey("key_list_saved_position")) {
            this.mSavedPosition = savedInstanceState.getInt("key_list_saved_position");
        }
        init();
        return this.mRootView;
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mAdapter.onPause();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.mNewDownloadReceiver);
        MusicDownloader.getInstance(getContext()).unregisterDownloadListener(this.mDownloadListener);
    }

    @Override // com.amazon.mp3.fragment.BaseFragment
    public void onDeactivated() {
        super.onDeactivated();
        unregisterReceiver(this.mArtLoadedReceiver);
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment
    public void onActivated() {
        super.onActivated();
        registerReceiver(this.mArtLoadedReceiver, new IntentFilter("com.amazon.mp3.library.cache.image.CacheManager.ACTION_ALBUM_ARTWORK_CACHED"));
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mAdapter.onResume();
        ActionBarView actionBarView = new ActionBarView(getActivity(), getString(R.string.dmusic_more_downloads_tab));
        this.mHeaderView = actionBarView;
        ActionBarProvider actionBarProvider = this.mActionBarProvider;
        if (actionBarProvider != null) {
            actionBarProvider.setHeaderView(actionBarView);
            if (this.mShowHomeAsUp) {
                this.mActionBarProvider.requestHomeButtonAsBack();
            }
        }
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.mNewDownloadReceiver, new IntentFilter("com.amazon.music.download.NEW_DOWNLOAD_REQUEST"));
        MusicDownloader.getInstance(getActivity()).registerDownloadListener(this.mDownloadListener);
        refreshDownloadList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshDownloadList() {
        MusicDownloader.getInstance(getActivity()).getRequestProvider().getRequests(this.mRequestCallback);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.fragment.DownloadsFragment$9, reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$downloads$RequestItem$RequestType;

        static {
            int[] iArr = new int[RequestItem.RequestType.values().length];
            $SwitchMap$com$amazon$music$downloads$RequestItem$RequestType = iArr;
            try {
                iArr[RequestItem.RequestType.GROUP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$RequestItem$RequestType[RequestItem.RequestType.ITEM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        removeHeaderView();
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver
    public void setActionBarProvider(ActionBarProvider provider) {
        super.setActionBarProvider(provider);
        this.mActionBarProvider = provider;
        Bundle arguments = getArguments();
        boolean z = false;
        if (arguments != null && arguments.getBoolean("add_to_back_stack", false)) {
            z = true;
        }
        this.mShowHomeAsUp = z;
    }

    @Override // com.amazon.mp3.library.cache.image.IArtCache
    public Drawable getDrawable(ImageLoaderFactory.ItemType itemType, String source, String id) {
        return getAlbumArtCache().get(itemType, source, getAlbumArtSize(), id);
    }

    @Override // com.amazon.mp3.library.cache.image.IArtCache
    public Drawable getDrawable(ImageLoaderFactory.ItemType itemType, String source, long id) {
        return getAlbumArtCache().get(itemType, source, getAlbumArtSize(), Long.toString(id));
    }

    protected int getAlbumArtSize() {
        if (this.mAlbumArtSize == 0) {
            this.mAlbumArtSize = getResources().getDimensionPixelSize(R.dimen.default_listview_art_size);
        }
        return this.mAlbumArtSize;
    }

    private void init() {
        if (this.mAdapter == null) {
            this.mAdapter = new DownloadsAdapter(getActivity(), this);
        }
        this.mListView.setOnCreateContextMenuListener(this.mOnCreateContextMenuListener);
        this.mListView.setOnItemLongClickListener(null);
        ListViewUtil.stopScroll(this.mListView);
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
    }

    public void setShowEmptyList() {
        this.mListView.setEmptyView((TextView) this.mRootView.findViewById(R.id.EmptyView));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadAdapter(final ArrayList<DownloadItem> downloadsData) {
        this.mHandler.post(new Runnable() { // from class: com.amazon.mp3.library.fragment.DownloadsFragment.3
            @Override // java.lang.Runnable
            public void run() {
                DownloadsFragment.this.mAdapter.clear();
                DownloadsFragment.this.mAdapter.addItems(downloadsData);
                DownloadsFragment.this.notifyDataSetChanged();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAnyOwned(Group group) {
        Iterator<Item> it = group.iterator();
        while (it.hasNext()) {
            if (it.next().isOwned()) {
                return true;
            }
        }
        return false;
    }

    private void removeItem(String requestId) {
        this.mAdapter.removeItem(requestId);
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDataSetChanged() {
        String str;
        this.mAdapter.notifyDataSetChanged();
        TextView textView = (TextView) this.mRootView.findViewById(R.id.EmptyView);
        Button button = (Button) this.mRootView.findViewById(R.id.SeeCompleted);
        ImageView imageView = (ImageView) this.mRootView.findViewById(R.id.EmptyDownloadImage);
        if (this.mAdapter.getCount() > 0) {
            str = " (" + this.mAdapter.getCount() + ")";
            textView.setVisibility(8);
            button.setVisibility(8);
            imageView.setVisibility(8);
        } else {
            setShowEmptyList();
            button.setOnClickListener(this.mSeeCompletedClickListener);
            button.setVisibility(0);
            textView.setVisibility(0);
            imageView.setVisibility(0);
            str = "";
        }
        ActionBarView actionBarView = this.mHeaderView;
        if (actionBarView != null) {
            actionBarView.setTitles(getApplication().getString(R.string.dmusic_more_downloads_tab) + str, null);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        return adapterContextMenuInfo != null && onContextMenuItemSelected(item, adapterContextMenuInfo.targetView.getTag(), adapterContextMenuInfo.position, adapterContextMenuInfo.id);
    }

    protected boolean onContextMenuItemSelected(MenuItem item, Object tag, int position, long id) {
        DownloadsAdapter.RowViewHolder rowViewHolder = (DownloadsAdapter.RowViewHolder) tag;
        if (rowViewHolder == null || item.getItemId() != R.id.MenuDownloadContextRemoveItem) {
            return false;
        }
        Log.info(TAG, "User cancel from download page with item " + rowViewHolder.mItem.getRequestId() + " with state " + rowViewHolder.mItem.getDownloadState());
        MusicDownloader.getInstance(getContext()).cancel(rowViewHolder.mDownloadId);
        removeItem(rowViewHolder.mItem.getRequestId());
        return true;
    }

    protected CacheManager getAlbumArtCache() {
        if (this.mCacheManager == null) {
            this.mCacheManager = CacheManager.getInstance();
        }
        return this.mCacheManager;
    }

    protected void onArtReceived(Context context, Intent intent) {
        if (getActivity() == null || intent == null || intent.getExtras() == null) {
            return;
        }
        ImageLoaderFactory.ItemType itemType = ImageLoaderFactory.ItemType.getItemType(intent.getIntExtra("com.amazon.mp3.library.cache.image.CacheManager.EXTRA_IMAGE_TYPE", -1));
        String stringExtra = intent.getStringExtra("com.amazon.mp3.library.cache.image.CacheManager.EXTRA_IMAGE_SOURCE");
        String stringExtra2 = intent.getStringExtra("com.amazon.mp3.library.cache.image.CacheManager.EXTRA_IMAGE_ID");
        if (stringExtra2 == null) {
            return;
        }
        this.mAdapter.onDrawableLoaded(ImageCacheUri.generateUri(itemType.intValue(), stringExtra2), getDrawable(itemType, stringExtra, stringExtra2));
    }

    public void displayNetworkErrorDlg() {
        getActivity().runOnUiThread(this.mShowNetworkErrorDialog);
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
    public void onRetryConnection() {
        if (ConnectivityUtil.hasAnyInternetConnection()) {
            return;
        }
        showNetworkErrorDialog(this);
    }
}
