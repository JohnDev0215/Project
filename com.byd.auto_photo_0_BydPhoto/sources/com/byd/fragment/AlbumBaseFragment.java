package com.byd.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.a.d;
import b.a.e.b;
import b.a.k.c;
import com.byd.auto_photo.MainBaseActivity;
import com.byd.auto_photo.R;
import com.byd.auto_photo.a;
import com.byd.bean.AlbumItem;
import com.byd.fragment.BaseFileDeleteDialogFragment;
import com.byd.fragment.BaseNewFolderOrRenameFragment;
import com.byd.fragment.BaseProgressDialogFragment;
import com.byd.fragment.SearchAndSelectBaseFragment;
import com.byd.util.e;
import com.byd.util.g;
import com.byd.util.l;
import com.byd.util.m;
import com.byd.util.w;
import com.byd.view.GridItemDecoration;
import com.byd.view.WrapGridLayoutManager;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AlbumBaseFragment extends MediaDataBaseFragment implements SearchAndSelectBaseFragment.OnHeaderListener, l.i {
    public static final String ACTION_ALBUM_COPY_MODE_CHANGE = "com.byd.ALBUM_COPY_MODE_CHANGE";
    public static final String ACTION_ALBUM_DATA_CHANGE = "com.byd.ALBUM_DATA_CHANGE";
    public static final String ACTION_ALBUM_NUM_CHANGE = "com.byd.ALBUM_NUM_CHANGE";
    protected static int ITEM_SPACING = 40;
    private static final int MSG_DELAY_LOAD_DATA = 1;
    private static final int MSG_LOAD_DATA_COPY_MODE_CHANGE = 2;
    protected b adapter;
    private boolean configurationChange;
    private FileDeleteDialogFragment deleteDialog;
    private l fileDeleteUtil;
    private FileReceiver fileReceiver;
    private boolean isPause;
    private c mAlbumLoaderManager;
    protected RecyclerView mRecycler;
    protected MediaFileGridFragment mediaFileGridFragment;
    private NewFolderOrRenameFragment newFolderFragment;
    private ProgressDialogFragment progressDialog;
    protected SearchAndSelectBaseFragment searchAndSelectFragment;
    protected boolean subFragmentShowFull;
    private c.e onAlbumLoaderListener = new c.e() { // from class: com.byd.fragment.AlbumBaseFragment.1
        @Override // b.a.k.c.e
        public void onCancel() {
            AlbumBaseFragment.this.dismissProgress();
            w.c("load album onCancel");
        }

        @Override // b.a.k.c.e
        public void onComplete() {
            AlbumBaseFragment albumBaseFragment = AlbumBaseFragment.this;
            albumBaseFragment.adapter.w(albumBaseFragment.mAlbumLoaderManager.C(false));
            AlbumBaseFragment albumBaseFragment2 = AlbumBaseFragment.this;
            albumBaseFragment2.searchAndSelectFragment.onDataSizeChange(albumBaseFragment2.adapter.f());
            AlbumBaseFragment.this.dismissProgress();
            w.c("load album complete " + AlbumBaseFragment.this.adapter.getItemCount());
        }

        @Override // b.a.k.c.e
        public void onStart() {
        }
    };
    private MHandler mHandler = new MHandler(this);

    private class FileReceiver extends BroadcastReceiver {
        private FileReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Message messageObtainMessage;
            File[] fileArrListFiles;
            String action = intent.getAction();
            if ("com.byd.ACTION_FILE_CHANGE".equals(action)) {
                String stringExtra = intent.getStringExtra("path");
                if (stringExtra != null) {
                    File file = new File(stringExtra);
                    if (file.exists() && file.isDirectory() && ((fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.byd.fragment.AlbumBaseFragment.FileReceiver.1
                        @Override // java.io.FileFilter
                        public boolean accept(File file2) {
                            if (!file2.isFile()) {
                                return false;
                            }
                            String strA = m.a(file2.getName());
                            return m.b(strA) || m.d(strA);
                        }
                    })) == null || fileArrListFiles.length == 0)) {
                        ArrayList arrayList = new ArrayList(1);
                        arrayList.add(stringExtra);
                        AlbumBaseFragment.this.mAlbumLoaderManager.n(arrayList);
                    }
                }
                if (((MainBaseActivity) AlbumBaseFragment.this.getActivity()).J0()) {
                    AlbumBaseFragment.this.mAlbumLoaderManager.t();
                    if (AlbumBaseFragment.this.isVisible()) {
                        AlbumBaseFragment.this.adapter.notifyDataSetChanged();
                        return;
                    }
                    return;
                }
                w.c("AlbumFragment receiver ACTION_FILE_CHANGE");
                if (AlbumBaseFragment.this.isPause) {
                    AlbumBaseFragment.this.mAlbumLoaderManager.t();
                    return;
                } else {
                    AlbumBaseFragment.this.mHandler.removeMessages(1);
                    AlbumBaseFragment.this.mHandler.removeMessages(2);
                    messageObtainMessage = AlbumBaseFragment.this.mHandler.obtainMessage(1, Boolean.TRUE);
                }
            } else {
                if (!AlbumBaseFragment.ACTION_ALBUM_DATA_CHANGE.equals(action)) {
                    if (AlbumBaseFragment.ACTION_ALBUM_NUM_CHANGE.equals(action)) {
                        if (AlbumBaseFragment.this.isVisible()) {
                            AlbumBaseFragment.this.adapter.notifyDataSetChanged();
                            return;
                        }
                        return;
                    } else {
                        if (AlbumBaseFragment.ACTION_ALBUM_COPY_MODE_CHANGE.equals(action)) {
                            AlbumBaseFragment.this.mHandler.removeMessages(2);
                            AlbumBaseFragment.this.mHandler.sendEmptyMessageDelayed(2, 100L);
                            return;
                        }
                        return;
                    }
                }
                w.c("AlbumFragment receiver ACTION_ALBUM_DATA_CHANGE");
                boolean booleanExtra = intent.getBooleanExtra("want_reload", true);
                w.c("==album data change==" + booleanExtra);
                AlbumBaseFragment.this.mHandler.removeMessages(1);
                AlbumBaseFragment.this.mHandler.removeMessages(2);
                messageObtainMessage = AlbumBaseFragment.this.mHandler.obtainMessage(1, Boolean.valueOf(booleanExtra));
            }
            AlbumBaseFragment.this.mHandler.sendMessageDelayed(messageObtainMessage, 350L);
        }
    }

    private static class MHandler extends d<AlbumBaseFragment> {
        public MHandler(AlbumBaseFragment albumBaseFragment) {
            super(albumBaseFragment);
        }

        @Override // b.a.d
        public void handleMessage(AlbumBaseFragment albumBaseFragment, Message message) {
            int i = message.what;
            if (i != 1) {
                if (i != 2) {
                    return;
                }
                albumBaseFragment.notifyByCopyModeChange();
            } else {
                Object obj = message.obj;
                if (obj instanceof Boolean) {
                    albumBaseFragment.resetDataShow(((Boolean) obj).booleanValue());
                }
            }
        }
    }

    private class MSpanSizeLookUp extends GridLayoutManager.SpanSizeLookup {
        int num;

        public MSpanSizeLookUp(int i) {
            this.num = i;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int i) {
            if (AlbumBaseFragment.this.adapter.getItemViewType(i) == 4) {
                return this.num;
            }
            return 1;
        }
    }

    private boolean canRename() {
        AlbumItem albumItem = this.adapter.j().get(0);
        return albumItem.getAlbumType() == 2 || albumItem.isCanReName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissProgress() {
        ProgressDialogFragment progressDialogFragment = this.progressDialog;
        if (progressDialogFragment != null) {
            if (progressDialogFragment.isShowing()) {
                this.progressDialog.dismissAllowingStateLoss();
            }
            this.progressDialog.setOnCancelListener(null);
            this.progressDialog = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doSelectOrCancel(AlbumItem albumItem, int i) {
        this.adapter.e(albumItem, i);
        int iU = this.adapter.u();
        if (iU == 1 && canRename()) {
            this.searchAndSelectFragment.setRenameEnable(true);
        } else {
            this.searchAndSelectFragment.setRenameEnable(false);
        }
        if (this.adapter.l()) {
            this.searchAndSelectFragment.setSelectAll(true);
        } else {
            this.searchAndSelectFragment.setSelectAll(false);
        }
        onSelectSizeChange(iU);
    }

    private void initRecyclerView() {
        GridLayoutManager wrapGridLayoutManager;
        int numColumns = getNumColumns(getResources().getConfiguration().orientation);
        if (this.mRecycler.getLayoutManager() == null) {
            wrapGridLayoutManager = new WrapGridLayoutManager(getActivity(), numColumns);
            this.mRecycler.setLayoutManager(wrapGridLayoutManager);
            if (a.f1178a.intValue() == 3) {
                int i = ITEM_SPACING;
                this.mRecycler.addItemDecoration(new GridItemDecoration(i, i >> 2));
            } else {
                this.mRecycler.addItemDecoration(new GridItemDecoration(ITEM_SPACING));
            }
        } else {
            wrapGridLayoutManager = (GridLayoutManager) this.mRecycler.getLayoutManager();
            wrapGridLayoutManager.setSpanCount(numColumns);
        }
        b bVar = this.adapter;
        if (bVar == null) {
            b.a.e.a aVar = new b.a.e.a(getActivity(), numColumns, ITEM_SPACING);
            this.adapter = aVar;
            aVar.y(new b.c() { // from class: com.byd.fragment.AlbumBaseFragment.3
                @Override // b.a.e.b.c
                public void onAlbumItemClick(AlbumItem albumItem, int i2) {
                    if (AlbumBaseFragment.this.adapter.m()) {
                        if (albumItem.getAlbumType() != 1) {
                            AlbumBaseFragment.this.doSelectOrCancel(albumItem, i2);
                        }
                    } else {
                        if (e.y()) {
                            return;
                        }
                        if (albumItem.getSourceType() == 0) {
                            AlbumBaseFragment.this.showCreateFolderDialog(null, null);
                        } else {
                            AlbumBaseFragment.this.showSecondPage(albumItem, i2);
                        }
                    }
                }

                @Override // b.a.e.b.c
                public void onAlbumItemLongClick(AlbumItem albumItem, int i2) {
                    if (albumItem.getAlbumType() > 1) {
                        if (!AlbumBaseFragment.this.searchAndSelectFragment.isSelectMode()) {
                            c.D(AlbumBaseFragment.this.getActivity()).P();
                            AlbumBaseFragment.this.searchAndSelectFragment.intoSelectMode(true);
                        }
                        AlbumBaseFragment.this.doSelectOrCancel(albumItem, i2);
                    }
                }
            });
        } else {
            bVar.x(numColumns);
            this.adapter.s(getActivity());
        }
        this.adapter.b(getAlbumShowGridWidth(), getItemHeight());
        wrapGridLayoutManager.setSpanSizeLookup(new MSpanSizeLookUp(numColumns));
        this.mRecycler.setAdapter(this.adapter);
    }

    private boolean isDialogShow() {
        FileDeleteDialogFragment fileDeleteDialogFragment = this.deleteDialog;
        if (fileDeleteDialogFragment != null && fileDeleteDialogFragment.isShowing()) {
            return true;
        }
        NewFolderOrRenameFragment newFolderOrRenameFragment = this.newFolderFragment;
        return newFolderOrRenameFragment != null && newFolderOrRenameFragment.isShowing();
    }

    private void load() {
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
        this.isDataChange = false;
        this.mAlbumLoaderManager.s();
        cancelSelectMode();
        showLoadProgress();
        b bVar = this.adapter;
        if (bVar != null && bVar.getItemCount() > 0) {
            this.adapter.w(this.mAlbumLoaderManager.G(false));
        }
        this.mAlbumLoaderManager.J();
    }

    private void loadAlbum() {
        if (this.isDataChange) {
            if (isDialogShow()) {
                return;
            }
            load();
            return;
        }
        if (getActivity() != null && ((MainBaseActivity) getActivity()).J0()) {
            w.c("has second level show , can't load , return");
            if (this.adapter.getItemCount() > 0) {
                return;
            }
        }
        if (this.adapter.getItemCount() > 0) {
            this.adapter.notifyDataSetChanged();
        } else {
            load();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyByCopyModeChange() {
        w.c("album fragment notifyByCopyModeChange");
        this.adapter.w(this.mAlbumLoaderManager.C(false));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetDataShow(boolean z) {
        this.mAlbumLoaderManager.t();
        if (z) {
            this.isDataChange = true;
            if (this.isPause) {
                return;
            }
            loadAlbum();
            return;
        }
        if (this.isPause) {
            this.isDataChange = true;
        } else {
            this.adapter.w(this.mAlbumLoaderManager.C(false));
            this.searchAndSelectFragment.onDataSizeChange(this.adapter.f());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCreateFolderDialog(AlbumItem albumItem, String str) {
        if (this.newFolderFragment == null) {
            NewFolderOrRenameFragment newFolderOrRenameFragment = new NewFolderOrRenameFragment();
            this.newFolderFragment = newFolderOrRenameFragment;
            newFolderOrRenameFragment.setOnCreateListener(new BaseNewFolderOrRenameFragment.OnCreateListener() { // from class: com.byd.fragment.AlbumBaseFragment.4
                @Override // com.byd.fragment.BaseNewFolderOrRenameFragment.OnCreateListener
                public boolean isAlbumExist(String str2) {
                    return AlbumBaseFragment.this.adapter.k(str2);
                }

                @Override // com.byd.fragment.BaseNewFolderOrRenameFragment.OnCreateListener
                public void onCreateOk(File file) {
                    if (AlbumBaseFragment.this.checkToRefresh()) {
                        return;
                    }
                    AlbumBaseFragment.this.toastMsg(R.string.new_album_ok, true);
                    boolean zI = AlbumBaseFragment.this.mAlbumLoaderManager.I();
                    int iH = AlbumBaseFragment.this.mAlbumLoaderManager.h(file, false);
                    AlbumItem albumItemA = AlbumBaseFragment.this.mAlbumLoaderManager.A(iH);
                    int iF = AlbumBaseFragment.this.mAlbumLoaderManager.F();
                    w.c("create album ok : " + iH + " " + albumItemA.getNum() + " sys count : " + iF);
                    if (iF == 0) {
                        AlbumBaseFragment.this.mAlbumLoaderManager.G(false);
                        iF = AlbumBaseFragment.this.mAlbumLoaderManager.F();
                    }
                    int i = iH + iF;
                    w.c("notify album index : " + i + " sys count : " + iF);
                    if (!zI) {
                        List<AlbumItem> listG = AlbumBaseFragment.this.adapter.g();
                        AlbumItem albumItemE = AlbumBaseFragment.this.mAlbumLoaderManager.E();
                        if (listG == null || listG.contains(albumItemE)) {
                            w.c("AlbumAdapter has more tips");
                        } else {
                            AlbumBaseFragment.this.adapter.n(i, albumItemE);
                        }
                    }
                    AlbumBaseFragment.this.adapter.n(i, albumItemA);
                    AlbumBaseFragment albumBaseFragment = AlbumBaseFragment.this;
                    albumBaseFragment.searchAndSelectFragment.onDataSizeChange(albumBaseFragment.adapter.f());
                }

                @Override // com.byd.fragment.BaseNewFolderOrRenameFragment.OnCreateListener
                public void onRenameOk(File file, File file2) {
                    AlbumBaseFragment.this.mAlbumLoaderManager.N(file, file2);
                    AlbumBaseFragment.this.searchAndSelectFragment.cancelSelectMode();
                    w.c("==onRenameOk==");
                    if (AlbumBaseFragment.this.checkToRefresh()) {
                        return;
                    }
                    AlbumBaseFragment.this.toastMsg(R.string.rename_ok, true);
                    AlbumBaseFragment.this.mAlbumLoaderManager.t();
                    AlbumBaseFragment.this.adapter.notifyDataSetChanged();
                }

                @Override // com.byd.fragment.BaseNewFolderOrRenameFragment.OnCreateListener
                public void onRenameOrCreateCancel() {
                    w.c("--onRenameOrCreateCancel--");
                    AlbumBaseFragment.this.checkToRefresh();
                }
            });
        }
        this.newFolderFragment.setEditAlbum(getActivity(), albumItem, str);
        configNewAlbumDialog(this.newFolderFragment);
        this.newFolderFragment.show(getActivity().getSupportFragmentManager(), "new_folder");
    }

    private void showDeleteDialog() {
        if (this.fileDeleteUtil == null) {
            l lVar = new l(getActivity(), 0);
            this.fileDeleteUtil = lVar;
            lVar.B(this);
        }
        if (this.deleteDialog == null) {
            FileDeleteDialogFragment fileDeleteDialogFragment = new FileDeleteDialogFragment();
            this.deleteDialog = fileDeleteDialogFragment;
            fileDeleteDialogFragment.setOnDeleteDialogListener(new BaseFileDeleteDialogFragment.OnDeleteDialogListener() { // from class: com.byd.fragment.AlbumBaseFragment.5
                @Override // com.byd.fragment.BaseFileDeleteDialogFragment.OnDeleteDialogListener
                public void onSureDelete() {
                    AlbumBaseFragment.this.fileDeleteUtil.D();
                }
            });
        }
        List<AlbumItem> listJ = this.adapter.j();
        int size = listJ.size();
        for (int i = 0; i < size; i++) {
            this.fileDeleteUtil.q(listJ.get(i).getUri().getPath());
        }
        this.deleteDialog.setFolder(true);
        this.deleteDialog.show(getActivity().getSupportFragmentManager(), "delete_album");
    }

    private void showLoadProgress() {
        if (this.isPause) {
            return;
        }
        if (getActivity() == null || !((MainBaseActivity) getActivity()).J0()) {
            if (this.progressDialog == null) {
                ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
                this.progressDialog = progressDialogFragment;
                progressDialogFragment.setOnCancelListener(new BaseProgressDialogFragment.onCancelListener() { // from class: com.byd.fragment.AlbumBaseFragment.2
                    @Override // com.byd.fragment.BaseProgressDialogFragment.onCancelListener
                    public void onCanceled() {
                        w.c("on load progress cancel");
                        AlbumBaseFragment.this.mAlbumLoaderManager.l();
                    }
                });
            }
            if (this.progressDialog.isShowing()) {
                return;
            }
            this.progressDialog.setMsgRes(R.string.loading_please_wait);
            this.progressDialog.show(getActivity().getSupportFragmentManager(), "album_load");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void showSecondPage(AlbumItem albumItem, int i) {
        MediaFileGridFragment mediaFileGridFragment = this.mediaFileGridFragment;
        if (mediaFileGridFragment == null) {
            createChildMediaGridFragment();
        } else {
            mediaFileGridFragment.setSearchResultDatas(null, 0);
        }
        this.mediaFileGridFragment.setShowLevel(1);
        if (albumItem.getSourceType() == 2 || albumItem.getSourceType() == 3) {
            this.mediaFileGridFragment.setPath(albumItem.getUri().getPath());
            this.mediaFileGridFragment.setType(0);
        } else if (albumItem.getSourceType() == 4 || albumItem.getSourceType() == 5) {
            this.mediaFileGridFragment.setPath(albumItem.getUri().getPath());
            this.mediaFileGridFragment.setType(albumItem.getSourceType());
        } else {
            this.mediaFileGridFragment.setPath(null);
            if (i == 0) {
                this.mediaFileGridFragment.setType(1);
            } else {
                this.mediaFileGridFragment.setType(2);
            }
        }
        w.c("album item click : " + albumItem.getName());
        this.mediaFileGridFragment.setAlbum(albumItem);
        ((MainBaseActivity) getActivity()).k0(this.mediaFileGridFragment, false, this.subFragmentShowFull);
    }

    public boolean cancelSelectMode() {
        b bVar = this.adapter;
        if (bVar == null || !bVar.m()) {
            return false;
        }
        this.searchAndSelectFragment.cancelSelectMode();
        return true;
    }

    protected boolean checkToRefresh() {
        if (this.isPause) {
            return true;
        }
        if (!this.isDataChange) {
            return false;
        }
        w.c("data has change , reload");
        load();
        return true;
    }

    protected void configNewAlbumDialog(NewFolderOrRenameFragment newFolderOrRenameFragment) {
    }

    protected void createChildMediaGridFragment() {
        MediaFileGridFragment mediaFileGridFragment = new MediaFileGridFragment();
        this.mediaFileGridFragment = mediaFileGridFragment;
        mediaFileGridFragment.setGroup(false);
        this.mediaFileGridFragment.setHeaderMode(2);
    }

    @Override // com.byd.fragment.MediaDataBaseFragment
    public void dismissAllDialog() {
        if (this.deleteDialog != null) {
            w.c("dismiss delete dialog");
            if (this.deleteDialog.isShowing()) {
                this.deleteDialog.dismissAllowingStateLoss();
            }
            this.deleteDialog = null;
        }
        if (this.newFolderFragment != null) {
            w.c("dismiss new folder dialog");
            if (this.newFolderFragment.isShowing()) {
                this.newFolderFragment.dismissAllowingStateLoss();
            }
            this.newFolderFragment.setOnCreateListener(null);
            this.newFolderFragment = null;
        }
    }

    protected int getAlbumShowGridWidth() {
        Resources resources = getActivity().getResources();
        int mainContainerWidth = getMainContainerWidth();
        if (resources.getConfiguration().orientation == 2) {
            mainContainerWidth -= resources.getDimensionPixelSize(R.dimen.tab_size);
        }
        return mainContainerWidth - (resources.getDimensionPixelSize(R.dimen.content_padding) * 2);
    }

    protected int getItemHeight() {
        return 0;
    }

    protected int getMainContainerWidth() {
        return getActivity().getResources().getDisplayMetrics().widthPixels;
    }

    protected int getNumColumns(int i) {
        return i == 1 ? 4 : 5;
    }

    public SearchAndSelectBaseFragment getSearchAndSelectFragment() {
        return this.searchAndSelectFragment;
    }

    @Override // com.byd.fragment.SubBaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        w.c("AlbumBaseFragment onConfigurationChanged");
        this.configurationChange = true;
        super.onConfigurationChanged(configuration);
        this.adapter.b(getAlbumShowGridWidth(), getItemHeight());
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment.OnHeaderListener
    public void onCopy(boolean z) {
    }

    @Override // com.byd.fragment.SubBaseFragment, com.byd.ui.a, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.fileReceiver = new FileReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.byd.ACTION_FILE_CHANGE");
        intentFilter.addAction(ACTION_ALBUM_DATA_CHANGE);
        intentFilter.addAction(ACTION_ALBUM_COPY_MODE_CHANGE);
        intentFilter.addAction(ACTION_ALBUM_NUM_CHANGE);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.fileReceiver, intentFilter);
        this.mAlbumLoaderManager = c.D(getActivity());
    }

    @Override // com.byd.fragment.MediaDataBaseFragment
    public void onDataChange(Uri uri, String str) {
        if (isDialogShow()) {
            this.isDataChange = true;
            return;
        }
        w.c("AlbumFragment onChange:" + uri);
        if (this.adapter != null) {
            if (this.mAlbumLoaderManager.q(getActivity(), str)) {
                w.c("AlbumFragment onChange has new album");
                if (!this.isPause) {
                    load();
                    return;
                }
            }
            if (getActivity() != null && ((MainBaseActivity) getActivity()).J0()) {
                w.c("AlbumFragment onChange has second level");
                c.D(getActivity()).t();
                this.adapter.notifyDataSetChanged();
            } else if (this.isPause) {
                this.isDataChange = true;
            } else {
                this.mAlbumLoaderManager.t();
                this.adapter.notifyDataSetChanged();
            }
        }
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment.OnHeaderListener
    public void onDelete() {
        if (this.adapter.u() > 0) {
            showDeleteDialog();
        } else {
            toastMsg(R.string.select_nothing);
        }
    }

    @Override // com.byd.util.l.i
    public void onDeleteCancel() {
        w.c("AlbumFragment onDeleteCancel");
        cancelSelectMode();
        if (this.isPause) {
            return;
        }
        w.c("delete cancel , reload");
        load();
    }

    @Override // com.byd.util.l.i
    public void onDeleteComplete(List<String> list) {
        boolean z;
        w.c("album onDeleteComplete");
        this.adapter.z(false);
        this.searchAndSelectFragment.cancelSelectMode();
        if (checkToRefresh()) {
            return;
        }
        int size = list.size();
        String strK = g.k(getActivity());
        int i = 0;
        while (true) {
            if (i >= size) {
                z = false;
                break;
            } else {
                if (list.get(i).equals(strK)) {
                    this.mAlbumLoaderManager.y();
                    z = true;
                    break;
                }
                i++;
            }
        }
        this.mAlbumLoaderManager.x(list);
        this.mAlbumLoaderManager.t();
        this.adapter.w(this.mAlbumLoaderManager.C(false));
        this.searchAndSelectFragment.onDataSizeChange(this.adapter.f());
        if (z) {
            ((MainBaseActivity) getActivity()).l0();
        }
        toastMsg(R.string.delete_finish, true);
    }

    @Override // com.byd.fragment.SubBaseFragment, com.byd.ui.a, androidx.fragment.app.Fragment
    public void onDestroy() {
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.fileReceiver);
        super.onDestroy();
        if (this.releaseOnDestroy) {
            this.mAlbumLoaderManager.s();
            b bVar = this.adapter;
            if (bVar != null) {
                bVar.w(null);
            }
            w.c("AlbumFragment release onDestroy");
        }
        this.mediaFileGridFragment = null;
        this.fileReceiver = null;
        w.c("AlbumFragment onDestroy");
    }

    @Override // com.byd.ui.a, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.mAlbumLoaderManager.S(this.onAlbumLoaderListener);
        super.onDestroyView();
        w.c("AlbumFragment onDestroyView : " + this.configurationChange);
        if (this.searchAndSelectFragment != null) {
            FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction.remove(this.searchAndSelectFragment);
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
            this.searchAndSelectFragment = null;
        }
        if (this.configurationChange) {
            return;
        }
        dismissAllDialog();
    }

    @Override // com.byd.fragment.MediaDataBaseFragment, com.byd.fragment.SubBaseFragment, b.a.k.h.b
    public void onExternalEject(String str, boolean z) {
        w.c("AlbumBaseFragment onExternalEject : " + str);
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
        this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1, Boolean.TRUE), 500L);
    }

    @Override // com.byd.fragment.SubBaseFragment
    protected void onLocaleChanged(Configuration configuration) {
        b bVar = this.adapter;
        if (bVar != null) {
            bVar.notifyDataSetChanged();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        this.configurationChange = true;
        w.c("AlbumFragment onMultiWindowModeChanged");
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.isPause = true;
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment.OnHeaderListener
    public void onRename() {
        if (this.adapter.u() != 1) {
            toastMsg(R.string.select_nothing);
        } else {
            AlbumItem albumItem = this.adapter.j().get(0);
            showCreateFolderDialog(albumItem, new File(albumItem.getUri().getPath()).getName());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        this.isPause = false;
        super.onResume();
        w.c("AlbumFragment onResume : " + this.isDataChange);
        loadAlbum();
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment.OnHeaderListener
    public void onSelectModeChange(boolean z) {
        this.adapter.z(z);
    }

    protected void onSelectSizeChange(int i) {
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment.OnHeaderListener
    public void onShare() {
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment.OnHeaderListener
    public void onShowDetail() {
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment.OnHeaderListener
    public void onSort(int i, boolean z) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        w.c("AlbumFragment onStart");
        b bVar = this.adapter;
        if (bVar != null && bVar.m()) {
            w.c("onStart========================" + this.searchAndSelectFragment.isSelectMode());
        }
        if (this.isDataChange || !this.mAlbumLoaderManager.H()) {
            return;
        }
        this.mAlbumLoaderManager.m();
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment.OnHeaderListener
    public void onToolBarViewClick(View view) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        this.configurationChange = false;
        this.mRecycler = (RecyclerView) view.findViewById(R.id.album_recycler);
        this.searchAndSelectFragment = (SearchAndSelectBaseFragment) getChildFragmentManager().findFragmentById(R.id.fragment_album_header);
        initRecyclerView();
        this.searchAndSelectFragment.setSearchSource(1);
        this.searchAndSelectFragment.setMode(1);
        if (this.adapter.m()) {
            this.searchAndSelectFragment.intoSelectMode(false);
            if (this.adapter.l()) {
                this.searchAndSelectFragment.setSelectAll(true);
            } else {
                this.searchAndSelectFragment.setSelectAll(false);
            }
            if (this.adapter.u() != 1 || !canRename()) {
                this.searchAndSelectFragment.setRenameEnable(false);
            }
            onSelectSizeChange(this.adapter.u());
        }
        this.searchAndSelectFragment.setOnHeaderListener(this);
        this.mAlbumLoaderManager.K(this.onAlbumLoaderListener);
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment.OnHeaderListener
    public void selectAllOrCancel() {
        if (this.adapter.f() > 0) {
            if (this.adapter.t()) {
                this.searchAndSelectFragment.setSelectAll(true);
            } else {
                this.adapter.B();
                this.searchAndSelectFragment.setSelectAll(false);
            }
            if (this.adapter.u() == 1 && canRename()) {
                this.searchAndSelectFragment.setRenameEnable(true);
            } else {
                this.searchAndSelectFragment.setRenameEnable(false);
            }
        }
        onSelectSizeChange(this.adapter.u());
    }
}
