package com.byd.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.a.e.r;
import b.a.e.t;
import b.a.e.u;
import b.a.k.c;
import b.a.k.n;
import b.a.k.o;
import b.a.k.q;
import com.byd.auto_photo.ImageShowActivity;
import com.byd.auto_photo.ImageShowBaseActivity;
import com.byd.auto_photo.MainActivity;
import com.byd.auto_photo.MainBaseActivity;
import com.byd.auto_photo.R;
import com.byd.auto_photo.ShareToOtherDevActivity;
import com.byd.bean.AlbumItem;
import com.byd.bean.FileBean;
import com.byd.bean.FileBeanInGroup;
import com.byd.fragment.BaseFileDeleteDialogFragment;
import com.byd.fragment.BasePastFilesDialogFragment;
import com.byd.fragment.BaseProgressDialogFragment;
import com.byd.fragment.SearchAndSelectBaseFragment;
import com.byd.share.d;
import com.byd.taskmanager.b.a;
import com.byd.util.e;
import com.byd.util.f;
import com.byd.util.g;
import com.byd.util.j;
import com.byd.util.l;
import com.byd.util.m;
import com.byd.util.w;
import com.byd.util.x;
import com.byd.view.GridItemDecoration;
import com.byd.view.WrapGridLayoutManager;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MediaFileGridBaseFragment extends MediaDataBaseFragment implements SearchAndSelectBaseFragment.OnHeaderListener, d.e, l.i, t.c, BasePastFilesDialogFragment.OnPastListener {
    public static final String ACTION_PHOTO_DATA_CHANGE = "com.byd.ACTION_PHOTO_DATA_CHANGE";
    private static final int DIALOG_DISMISS_TO_LOAD_TIME = 100;
    private static final int MSG_DEL_AFTER_MOVE = 5;
    private static final int MSG_DEL_AFTER_MOVE_FAIL = 6;
    private static final int MSG_SCROLLING_FINISH = 1;
    private static final int MSG_UNMOUNTED_TO_EXIT = 2;
    private static final int MSG_UNMOUNTED_TO_RELOAD = 3;
    private static final int MSG_UPDATE_ITEM_BY_PATH = 4;
    protected static final int REQUEST_FULL_IMAGE = 2;
    protected t adapter;
    protected AlbumItem album;
    private Thread calcDataNumThread;
    protected String cameraPath;
    protected boolean configurationChange;
    protected q dataManager;
    protected int datasNum;
    protected FileDeleteDialogFragment deleteDialog;
    protected boolean disableItemTouch;
    protected l fileDeleteUtil;
    private boolean isPause;
    private LoadCallback loadCallback;
    private ProgressDialogFragment loadingDialog;
    private int moveSelectCounter;
    private boolean moveToSelectChild;
    private int originalOrientation;
    private PastFilesDialogFragment pastDialog;
    protected String path;
    protected RecyclerView photoListView;
    private FileChangeReceiver receiver;
    protected String screenShotPath;
    protected SearchAndSelectBaseFragment searchAndSelectFragment;
    private Boolean selectState;
    protected ShareDialogFragment shareDialog;
    protected int shareIconOffsetX;
    protected d shareIconWindow;
    protected boolean showItemName;
    protected int showLevel;
    private int startDrawSelectPos;
    private float startX;
    private float startY;
    private int type;
    private int uiMode;
    protected TextView viewEmpty;
    protected int ITEM_SPACING = 8;
    private int searchSource = 10;
    protected boolean isGroup = true;
    private int headerMode = 1;
    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.byd.fragment.MediaFileGridBaseFragment.1
        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            FragmentActivity activity = MediaFileGridBaseFragment.this.getActivity();
            if (activity == null) {
                return;
            }
            ((MainBaseActivity) activity).O0(x.m(activity, true) ? 1 : 2);
        }
    };
    private View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.byd.fragment.MediaFileGridBaseFragment.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MediaFileGridBaseFragment.this.searchAndSelectFragment.hideSoftInput();
        }
    };
    private RecyclerView.OnItemTouchListener onItemTouchListener = new RecyclerView.OnItemTouchListener() { // from class: com.byd.fragment.MediaFileGridBaseFragment.3
        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            View viewFindChildViewUnder;
            MediaFileGridBaseFragment mediaFileGridBaseFragment = MediaFileGridBaseFragment.this;
            if (mediaFileGridBaseFragment.disableItemTouch) {
                w.c("disableItemTouch!!!");
                return false;
            }
            if (mediaFileGridBaseFragment.adapter.j()) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    MediaFileGridBaseFragment.this.startX = motionEvent.getX();
                    MediaFileGridBaseFragment.this.startY = motionEvent.getY();
                    MediaFileGridBaseFragment.this.moveToSelectChild = false;
                    MediaFileGridBaseFragment.this.startDrawSelectPos = -1;
                    if (MediaFileGridBaseFragment.this.moveSelectCounter > 1) {
                        MediaFileGridBaseFragment.this.selectState = null;
                    }
                    MediaFileGridBaseFragment.access$708(MediaFileGridBaseFragment.this);
                } else if (action == 2) {
                    if (MediaFileGridBaseFragment.this.moveToSelectChild) {
                        return true;
                    }
                    if (Math.abs(motionEvent.getX() - MediaFileGridBaseFragment.this.startX) > Math.abs(motionEvent.getY() - MediaFileGridBaseFragment.this.startY) && (viewFindChildViewUnder = recyclerView.findChildViewUnder((int) motionEvent.getX(), (int) motionEvent.getY())) != null) {
                        MediaFileGridBaseFragment.this.startDrawSelectPos = recyclerView.getChildLayoutPosition(viewFindChildViewUnder);
                        MediaFileGridBaseFragment.this.moveToSelectChild = true;
                        w.c("============>startDrawSelectPos : " + MediaFileGridBaseFragment.this.startDrawSelectPos);
                        return true;
                    }
                }
            }
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
            w.c("onRequestDisallowInterceptTouchEvent " + z);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            int childLayoutPosition;
            if (MediaFileGridBaseFragment.this.disableItemTouch) {
                w.c("disableItemTouch!!!");
                return;
            }
            View viewFindChildViewUnder = recyclerView.findChildViewUnder((int) motionEvent.getX(), (int) motionEvent.getY());
            if (viewFindChildViewUnder == null || (childLayoutPosition = recyclerView.getChildLayoutPosition(viewFindChildViewUnder)) == MediaFileGridBaseFragment.this.startDrawSelectPos) {
                return;
            }
            w.c("startPos : " + MediaFileGridBaseFragment.this.startDrawSelectPos + " pos : " + childLayoutPosition);
            if (childLayoutPosition <= MediaFileGridBaseFragment.this.startDrawSelectPos) {
                while (childLayoutPosition <= MediaFileGridBaseFragment.this.startDrawSelectPos) {
                    MediaFileGridBaseFragment.this.selectPos(childLayoutPosition);
                    childLayoutPosition++;
                }
            } else {
                for (int i = MediaFileGridBaseFragment.this.startDrawSelectPos; i <= childLayoutPosition; i++) {
                    MediaFileGridBaseFragment.this.selectPos(i);
                }
            }
        }
    };
    private Handler mHandler = new MyHandler(this);

    /* JADX INFO: renamed from: com.byd.fragment.MediaFileGridBaseFragment$10, reason: invalid class name */
    class AnonymousClass10 extends Thread {
        boolean isPhotoDataChange;
        final /* synthetic */ int val$copyCounts;
        final /* synthetic */ List val$fromList;
        final /* synthetic */ boolean val$selectAll;

        AnonymousClass10(int i, List list, boolean z) {
            this.val$copyCounts = i;
            this.val$fromList = list;
            this.val$selectAll = z;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            MediaFileGridBaseFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.byd.fragment.MediaFileGridBaseFragment.10.1
                @Override // java.lang.Runnable
                public void run() {
                    MediaFileGridBaseFragment.this.showProgressDialog();
                }
            });
            this.isPhotoDataChange = false;
            final ArrayList arrayList = new ArrayList(this.val$copyCounts);
            List list = this.val$fromList;
            int size = list != null ? list.size() : 0;
            for (int i = 0; i < size; i++) {
                String path = ((FileBean) this.val$fromList.get(i)).getPath();
                arrayList.add(path);
                if (!this.isPhotoDataChange && (path.startsWith(MediaFileGridBaseFragment.this.cameraPath) || path.startsWith(MediaFileGridBaseFragment.this.screenShotPath))) {
                    this.isPhotoDataChange = true;
                }
            }
            w.c("===>isPhotoDataChange==>" + this.isPhotoDataChange + " " + arrayList.size() + " " + size);
            try {
                if (this.val$selectAll) {
                    MediaFileGridBaseFragment.this.dataManager.c();
                } else {
                    MediaFileGridBaseFragment.this.dataManager.d(arrayList);
                }
                w.c("delete after move");
                MediaFileGridBaseFragment.this.mHandler.obtainMessage(5, MediaFileGridBaseFragment.this.dataManager.f()).sendToTarget();
            } catch (Exception e) {
                e.printStackTrace();
                w.c("delete after move exception : " + e.getMessage());
                MediaFileGridBaseFragment.this.mHandler.sendEmptyMessage(6);
            }
            MediaFileGridBaseFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.byd.fragment.MediaFileGridBaseFragment.10.2
                @Override // java.lang.Runnable
                public void run() {
                    MediaFileGridBaseFragment.this.searchAndSelectFragment.cancelSelectMode();
                    ImageShowBaseActivity.a1(MediaFileGridBaseFragment.this.getActivity(), arrayList);
                    c.D(MediaFileGridBaseFragment.this.getActivity()).t();
                    AnonymousClass10 anonymousClass10 = AnonymousClass10.this;
                    if (MediaFileGridBaseFragment.this.album == null || !anonymousClass10.isPhotoDataChange) {
                        return;
                    }
                    w.c("onPastFinish -> send photo change msg");
                    LocalBroadcastManager.getInstance(MediaFileGridBaseFragment.this.getActivity()).sendBroadcast(new Intent(MediaFileGridBaseFragment.ACTION_PHOTO_DATA_CHANGE));
                }
            });
        }
    }

    private class CalcDataNum extends Thread {
        private List<FileBeanInGroup> list;

        private CalcDataNum(List<FileBeanInGroup> list) {
            this.list = list;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                Thread.sleep(0L);
                int size = this.list.size();
                int i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    FileBeanInGroup fileBeanInGroup = this.list.get(i2);
                    if (fileBeanInGroup != null && fileBeanInGroup.getType() == 2) {
                        i++;
                    }
                }
                MediaFileGridBaseFragment.this.datasNum = i;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class FileChangeReceiver extends BroadcastReceiver {
        private FileChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String str;
            String action = intent.getAction();
            if (!"com.byd.ACTION_FILE_CHANGE".equals(action)) {
                if (MediaFileGridBaseFragment.ACTION_PHOTO_DATA_CHANGE.equals(action)) {
                    if (MediaFileGridBaseFragment.this.album != null) {
                        return;
                    }
                    w.c("receive ACTION_PHOTO_DATA_CHANGE " + MediaFileGridBaseFragment.this.showLevel);
                    MediaFileGridBaseFragment.this.onDataChange(null, null);
                    return;
                }
                if (!"com.byd.external_mounted_state".equals(action) || MediaFileGridBaseFragment.this.showLevel == 0) {
                    return;
                }
                w.c("MediaFileGridBaseFragment onReceive ACTION_EXTERNAL_MOUNT : " + MediaFileGridBaseFragment.this.type + " " + MediaFileGridBaseFragment.this.path);
                MediaFileGridBaseFragment.this.checkReloadOnMountStateChange();
                return;
            }
            String stringExtra = intent.getStringExtra("msg_from");
            if (stringExtra != null) {
                if (stringExtra.equals(hashCode() + "-" + MediaFileGridBaseFragment.this.showLevel)) {
                    return;
                }
            }
            String stringExtra2 = intent.getStringExtra("path");
            boolean booleanExtra = intent.getBooleanExtra("delete", false);
            w.c("MediaFileGrid FileUtils.ACTION_FILE_CHANGE -> del:" + booleanExtra);
            if (!booleanExtra) {
                if ("db:///collect".equals(stringExtra2)) {
                    String stringExtra3 = intent.getStringExtra("collect_file");
                    w.c("collect change : " + stringExtra3 + " " + MediaFileGridBaseFragment.this.showLevel);
                    if (stringExtra3 != null) {
                        MediaFileGridBaseFragment mediaFileGridBaseFragment = MediaFileGridBaseFragment.this;
                        if ((mediaFileGridBaseFragment.showLevel != 0 || (!stringExtra3.startsWith(mediaFileGridBaseFragment.cameraPath) && !stringExtra3.startsWith(MediaFileGridBaseFragment.this.screenShotPath))) && ((str = MediaFileGridBaseFragment.this.path) == null || !str.equals(new File(stringExtra3).getParent()))) {
                            String strA = m.a(stringExtra3);
                            if ((!m.b(strA) || MediaFileGridBaseFragment.this.type != 1) && (!m.d(strA) || MediaFileGridBaseFragment.this.type != 2)) {
                                w.c("collected change : " + stringExtra3 + " " + MediaFileGridBaseFragment.this.path + " " + MediaFileGridBaseFragment.this.type);
                            }
                        }
                        MediaFileGridBaseFragment.this.mHandler.obtainMessage(4, stringExtra3).sendToTarget();
                        return;
                    }
                    if (!"/collect".equals(MediaFileGridBaseFragment.this.path)) {
                        return;
                    }
                } else {
                    if (stringExtra2 == null) {
                        return;
                    }
                    String str2 = MediaFileGridBaseFragment.this.path;
                    if (str2 != null && !stringExtra2.equals(str2) && !stringExtra2.startsWith(MediaFileGridBaseFragment.this.cameraPath) && !stringExtra2.startsWith(MediaFileGridBaseFragment.this.screenShotPath) && MediaFileGridBaseFragment.this.type != 1 && MediaFileGridBaseFragment.this.type != 2) {
                        return;
                    }
                }
            }
            MediaFileGridBaseFragment.this.isDataChange = true;
        }
    }

    private class LoadCallback<T> extends com.byd.taskmanager.b.c<List<T>> {
        private WeakReference<MediaFileGridBaseFragment> fragment;

        private LoadCallback(MediaFileGridBaseFragment mediaFileGridBaseFragment) {
            this.fragment = new WeakReference<>(mediaFileGridBaseFragment);
        }

        @Override // com.byd.taskmanager.b.c, com.byd.taskmanager.b.b
        public void onCanceled(String str) {
            w.c("onCancel:" + str);
            MediaFileGridBaseFragment.this.cancelProgressDialog();
        }

        @Override // com.byd.taskmanager.b.b
        public void onComplete(String str, List<T> list) {
            MediaFileGridBaseFragment mediaFileGridBaseFragment = this.fragment.get();
            if (this.fragment == null || mediaFileGridBaseFragment == null) {
                return;
            }
            w.c("==load onComplete==source:" + mediaFileGridBaseFragment.searchSource);
            mediaFileGridBaseFragment.adapter.t(list);
            mediaFileGridBaseFragment.onLoadDataFinish();
            if (list == null || list.size() == 0) {
                mediaFileGridBaseFragment.viewEmpty.setVisibility(0);
                mediaFileGridBaseFragment.searchAndSelectFragment.onDataSizeChange(0);
            } else {
                mediaFileGridBaseFragment.viewEmpty.setVisibility(8);
                mediaFileGridBaseFragment.searchAndSelectFragment.onDataSizeChange(list.size());
            }
            mediaFileGridBaseFragment.datasNum = mediaFileGridBaseFragment.isGroup ? ((n) this.fragment.get().getTask(0)).b() : list.size();
            MediaFileGridBaseFragment.this.cancelProgressDialog();
            AlbumItem albumItem = MediaFileGridBaseFragment.this.album;
            if (albumItem == null || albumItem.getNum() == mediaFileGridBaseFragment.datasNum) {
                return;
            }
            MediaFileGridBaseFragment.this.album.setNum(-1);
            if (MediaFileGridBaseFragment.this.getActivity() == null) {
                return;
            }
            LocalBroadcastManager.getInstance(MediaFileGridBaseFragment.this.getActivity()).sendBroadcast(new Intent(AlbumBaseFragment.ACTION_ALBUM_NUM_CHANGE));
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    private static class MyHandler extends b.a.d<MediaFileGridBaseFragment> {
        public MyHandler(MediaFileGridBaseFragment mediaFileGridBaseFragment) {
            super(mediaFileGridBaseFragment);
        }

        @Override // b.a.d
        public void handleMessage(MediaFileGridBaseFragment mediaFileGridBaseFragment, Message message) {
            switch (message.what) {
                case 1:
                    mediaFileGridBaseFragment.adapter.notifyDataSetChanged();
                    break;
                case 2:
                    w.c("==MSG_UNMOUNTED_TO_EXIT==");
                    mediaFileGridBaseFragment.dismissAllDialog();
                    ((MainBaseActivity) mediaFileGridBaseFragment.getActivity()).Q0();
                    break;
                case 3:
                    mediaFileGridBaseFragment.mountedReload();
                    break;
                case 4:
                    mediaFileGridBaseFragment.updateCollectedItem((String) message.obj);
                    break;
                case 5:
                    mediaFileGridBaseFragment.showMoveResult((List) message.obj);
                    break;
                case 6:
                    mediaFileGridBaseFragment.loadData();
                    break;
            }
        }
    }

    static /* synthetic */ int access$708(MediaFileGridBaseFragment mediaFileGridBaseFragment) {
        int i = mediaFileGridBaseFragment.moveSelectCounter;
        mediaFileGridBaseFragment.moveSelectCounter = i + 1;
        return i;
    }

    private void checkIsCameraDataMove(final Context context, final List<FileBean> list) {
        if (this.album == null) {
            return;
        }
        w.c("start checkIsCameraDataMove");
        new Thread() { // from class: com.byd.fragment.MediaFileGridBaseFragment.9
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    String path = ((FileBean) it.next()).getPath();
                    if (path.startsWith(MediaFileGridBaseFragment.this.cameraPath) || path.startsWith(MediaFileGridBaseFragment.this.screenShotPath)) {
                        w.c("checkIsCameraDataMove update!!!");
                        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(MediaFileGridBaseFragment.ACTION_PHOTO_DATA_CHANGE));
                        return;
                    }
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkReloadOnMountStateChange() {
        Handler handler;
        long j;
        int i = this.type;
        if (i == 1 || i == 2) {
            PastFilesDialogFragment pastFilesDialogFragment = this.pastDialog;
            if (pastFilesDialogFragment == null || !pastFilesDialogFragment.isShowing()) {
                clearAllShowToReload();
                if (!this.isPause && isVisible()) {
                    handler = this.mHandler;
                    j = 500;
                    handler.sendEmptyMessageDelayed(3, j);
                    return;
                }
                this.isDataChange = true;
            }
            if (this.pastDialog.getPastTarget() == null) {
                clearAllShowToReload();
                if (!this.isPause && isVisible()) {
                    handler = this.mHandler;
                    j = 100;
                    handler.sendEmptyMessageDelayed(3, j);
                    return;
                }
                this.isDataChange = true;
            }
        }
    }

    private void initRecyclerView() {
        GridLayoutManager wrapGridLayoutManager;
        int i = getResources().getConfiguration().orientation;
        final int numColumns = getNumColumns(i);
        if (this.photoListView.getLayoutManager() == null) {
            wrapGridLayoutManager = new WrapGridLayoutManager(getActivity(), numColumns);
            this.photoListView.setLayoutManager(wrapGridLayoutManager);
            this.photoListView.setHasFixedSize(true);
            this.photoListView.setItemAnimator(null);
            this.photoListView.addItemDecoration(new GridItemDecoration(this.ITEM_SPACING));
        } else {
            wrapGridLayoutManager = (GridLayoutManager) this.photoListView.getLayoutManager();
            wrapGridLayoutManager.setSpanCount(numColumns);
        }
        t tVar = this.adapter;
        if (tVar == null) {
            t rVar = this.isGroup ? new r(getActivity(), numColumns, this.ITEM_SPACING) : new u(getActivity(), numColumns, this.ITEM_SPACING);
            this.adapter = rVar;
            rVar.v(this);
            this.adapter.x(this.showItemName);
        } else {
            tVar.u(numColumns);
            this.adapter.y(getActivity());
        }
        if (this.isGroup) {
            wrapGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.byd.fragment.MediaFileGridBaseFragment.6
                @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
                public int getSpanSize(int i2) {
                    if (MediaFileGridBaseFragment.this.adapter.getItemCount() == 0) {
                        return 0;
                    }
                    if (MediaFileGridBaseFragment.this.adapter.getItemViewType(i2) == 1) {
                        return numColumns;
                    }
                    return 1;
                }
            });
        }
        this.photoListView.setAdapter(this.adapter);
        this.adapter.c(getMediaShowGridWidth(i), getMediaShowItemHeight(i));
        this.originalOrientation = i;
        this.uiMode = getResources().getConfiguration().uiMode;
    }

    private boolean isSelectSameType() {
        List<FileBean> listI = this.adapter.i();
        int size = listI.size();
        if (size == 1) {
            return listI.get(0).getType() != 3;
        }
        int i = 0;
        while (i < size - 1) {
            FileBean fileBean = listI.get(i);
            i++;
            FileBean fileBean2 = listI.get(i);
            if (fileBean.getType() != fileBean2.getType() || fileBean2.getType() == 3) {
                return false;
            }
        }
        return true;
    }

    private void load(boolean z) {
        a oVar;
        if (this.adapter.getItemCount() != 0) {
            this.adapter.t(null);
        }
        if (z) {
            showProgressDialog();
        }
        if (!isTaskExist(0)) {
            if (this.isGroup) {
                this.loadCallback = new LoadCallback(this);
                if (this.dataManager == null) {
                    this.dataManager = new q(getActivity());
                }
                oVar = new n(getActivity(), this.path, this.type, this.dataManager);
            } else {
                this.loadCallback = new LoadCallback(this);
                oVar = new o(getActivity(), this.path, this.type);
            }
            addTask(0, oVar, this.loadCallback);
        }
        executeTask(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mountedReload() {
        dismissAllDialog();
        if (this.isPause || !isVisible()) {
            return;
        }
        loadData();
    }

    private void notifyDataChangeAfterMove(List<FileBean> list, int i, boolean z) {
        new AnonymousClass10(i, list, z).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectPos(int i) {
        Object item = this.adapter.getItem(i);
        FileBean fileBean = this.adapter instanceof r ? ((FileBeanInGroup) item).getFileBean() : (FileBean) item;
        if (fileBean != null) {
            if (this.selectState == null) {
                this.selectState = Boolean.valueOf(!fileBean.isSelect());
            }
            if (fileBean.isSelect() != this.selectState.booleanValue()) {
                onSelectSizeChange(this.adapter.e(fileBean, i));
            }
        }
    }

    private void showDeleteDialog() {
        int num;
        int type;
        if (this.fileDeleteUtil == null) {
            l lVar = new l(getActivity(), 0);
            this.fileDeleteUtil = lVar;
            lVar.B(this);
        }
        if (this.deleteDialog == null) {
            FileDeleteDialogFragment fileDeleteDialogFragment = new FileDeleteDialogFragment();
            this.deleteDialog = fileDeleteDialogFragment;
            fileDeleteDialogFragment.setOnDeleteDialogListener(new BaseFileDeleteDialogFragment.OnDeleteDialogListener() { // from class: com.byd.fragment.MediaFileGridBaseFragment.8
                @Override // com.byd.fragment.BaseFileDeleteDialogFragment.OnDeleteDialogListener
                public void onSureDelete() {
                    MediaFileGridBaseFragment.this.fileDeleteUtil.D();
                }
            });
        }
        List<FileBean> listI = this.adapter.i();
        if (listI.size() == 1) {
            FileBean fileBean = listI.get(0);
            type = fileBean.getType();
            num = type == 3 ? fileBean.getNum() : 0;
        } else {
            num = 0;
            type = 0;
        }
        this.fileDeleteUtil.A(listI);
        this.deleteDialog.setInfo(type, num, listI.size() > 1);
        this.deleteDialog.show(getActivity().getSupportFragmentManager(), "delete_img");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showMoveResult(List<FileBeanInGroup> list) {
        this.adapter.t(list);
        this.datasNum = this.dataManager.e();
        w.c("showMoveResult : " + this.adapter.getItemCount());
        if (this.adapter.getItemCount() == 0) {
            this.viewEmpty.setVisibility(0);
        }
        cancelProgressDialog();
        refreshAfterMove();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showProgressDialog() {
        if (this.isPause) {
            return;
        }
        if (this.showLevel == 0 && getActivity() != null && ((MainActivity) getActivity()).J0()) {
            return;
        }
        if (this.loadingDialog == null) {
            ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment(R.string.loading_please_wait);
            this.loadingDialog = progressDialogFragment;
            progressDialogFragment.setOnCancelListener(new BaseProgressDialogFragment.onCancelListener() { // from class: com.byd.fragment.MediaFileGridBaseFragment.5
                @Override // com.byd.fragment.BaseProgressDialogFragment.onCancelListener
                public void onCanceled() {
                    w.c("MediaFileGridFragment cancel load");
                    MediaFileGridBaseFragment.this.cancelTask(0);
                }
            });
        }
        if (this.loadingDialog.isShowing()) {
            return;
        }
        this.loadingDialog.show(getActivity().getSupportFragmentManager(), "load_dialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCollectedItem(String str) {
        t tVar = this.adapter;
        if (tVar == null || tVar.getItemCount() == 0) {
            return;
        }
        w.c("updateCollectedItem : " + str + " " + this.showLevel);
        this.adapter.k(str);
    }

    public void cancelProgressDialog() {
        if (this.loadingDialog != null) {
            w.c("==cancel loadingDialog , showing:" + this.loadingDialog.isShowing());
            if (this.loadingDialog.isShowing()) {
                this.loadingDialog.dismissAllowingStateLoss();
            }
            this.loadingDialog = null;
        }
    }

    public boolean cancelSelectMode() {
        SearchAndSelectBaseFragment searchAndSelectBaseFragment;
        t tVar = this.adapter;
        if (tVar == null || !tVar.j() || (searchAndSelectBaseFragment = this.searchAndSelectFragment) == null) {
            return false;
        }
        searchAndSelectBaseFragment.cancelSelectMode();
        return true;
    }

    protected boolean checkFileAvailable(String str) {
        File file = new File(str);
        if (file.exists() && file.length() != 0) {
            return true;
        }
        toastMsg(R.string.file_disable);
        return false;
    }

    protected boolean checkToRefresh() {
        if (this.isPause) {
            w.c("checkToRefresh onPause , return");
            return true;
        }
        if (!this.isDataChange) {
            return false;
        }
        w.c("data changed , reload");
        loadData();
        return true;
    }

    protected void clearAllShowToReload() {
        this.adapter.t(null);
    }

    @Override // com.byd.fragment.MediaDataBaseFragment
    public void dismissAllDialog() {
        w.c("==dismissAllDialog==" + this.configurationChange);
        FileDeleteDialogFragment fileDeleteDialogFragment = this.deleteDialog;
        if (fileDeleteDialogFragment == null || !fileDeleteDialogFragment.isShowing()) {
            PastFilesDialogFragment pastFilesDialogFragment = this.pastDialog;
            if (pastFilesDialogFragment == null || !pastFilesDialogFragment.isShowing()) {
                ShareDialogFragment shareDialogFragment = this.shareDialog;
                if (shareDialogFragment != null && shareDialogFragment.isShowing()) {
                    this.shareDialog.dismissAllowingStateLoss();
                    this.shareDialog = null;
                }
            } else {
                this.pastDialog.dismissAllowingStateLoss();
                this.pastDialog = null;
            }
        } else {
            this.deleteDialog.dismissAllowingStateLoss();
            this.deleteDialog = null;
        }
        cancelProgressDialog();
    }

    protected void doSelectOrCancel(FileBean fileBean, int i) {
        int iE = this.adapter.e(fileBean, i);
        w.c("doSelectOrCancel size : " + iE);
        onSelectSizeChange(iE);
    }

    protected int getMainContainerWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    protected int getMediaShowGridWidth(int i) {
        int mainContainerWidth = getMainContainerWidth();
        if (i == 2) {
            mainContainerWidth -= getResources().getDimensionPixelSize(R.dimen.tab_size);
        }
        return mainContainerWidth - (getResources().getDimensionPixelSize(R.dimen.content_padding) * 2);
    }

    protected int getMediaShowItemHeight(int i) {
        return 0;
    }

    protected int getNumColumns(int i) {
        return i == 1 ? 4 : 6;
    }

    public String getPath() {
        return this.path;
    }

    public SearchAndSelectBaseFragment getSearchAndSelectFragment() {
        return this.searchAndSelectFragment;
    }

    public int getSearchSource() {
        return this.searchSource;
    }

    public int getType() {
        return this.type;
    }

    protected void initShareIconWindowOnCreate() {
        d dVar = this.shareIconWindow;
        if (dVar == null) {
            d dVar2 = new d(getActivity());
            this.shareIconWindow = dVar2;
            dVar2.o(this);
        } else {
            dVar.r(this.shareIconOffsetX, 0);
            if (this.shareIconWindow.m()) {
                this.shareIconWindow.q();
            }
        }
    }

    public boolean isInSearchMode() {
        return this.searchSource != 10;
    }

    protected boolean isOutOfFragment() {
        FileDeleteDialogFragment fileDeleteDialogFragment = this.deleteDialog;
        if (fileDeleteDialogFragment != null && fileDeleteDialogFragment.isShowing()) {
            return true;
        }
        PastFilesDialogFragment pastFilesDialogFragment = this.pastDialog;
        if (pastFilesDialogFragment != null && pastFilesDialogFragment.isShowing()) {
            return true;
        }
        ShareDialogFragment shareDialogFragment = this.shareDialog;
        if (shareDialogFragment != null && shareDialogFragment.isShowing()) {
            return true;
        }
        if (getActivity() == null || this.showLevel != 0 || ((MainActivity) getActivity()).C0() == null) {
            return false;
        }
        w.c("isOutOfFragment 111");
        return true;
    }

    public boolean isPause() {
        return this.isPause;
    }

    protected void loadData() {
        w.c("MediaFile load data : " + this.type + " | " + this.path);
        this.isDataChange = false;
        cancelSelectMode();
        load(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        final MainBaseActivity mainBaseActivity;
        super.onActivityResult(i, i2, intent);
        if (i == 2 && isInSearchMode() && (mainBaseActivity = (MainBaseActivity) getActivity()) != null) {
            new Thread() { // from class: com.byd.fragment.MediaFileGridBaseFragment.4
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    com.byd.util.u uVarA0 = mainBaseActivity.A0();
                    if (uVarA0 == null || uVarA0.c()) {
                        w.c("searchResult is empty");
                        mainBaseActivity.s0();
                        mainBaseActivity.runOnUiThread(new Runnable() { // from class: com.byd.fragment.MediaFileGridBaseFragment.4.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MediaFileGridBaseFragment.this.setSearchResultDatas(null, 0);
                            }
                        });
                        return;
                    }
                    int size = 0;
                    List<FileBean> listG = null;
                    try {
                        MediaFileGridBaseFragment mediaFileGridBaseFragment = MediaFileGridBaseFragment.this;
                        if (mediaFileGridBaseFragment.isGroup) {
                            listG = ((r) mediaFileGridBaseFragment.adapter).g();
                            size = listG.size();
                            Iterator<FileBean> it = listG.iterator();
                            while (it.hasNext()) {
                                FileBeanInGroup fileBeanInGroup = (FileBeanInGroup) it.next();
                                if (fileBeanInGroup.getType() != 1) {
                                    FileBean fileBean = fileBeanInGroup.getFileBean();
                                    if (fileBean.isEditAddItem() || !new File(fileBean.getPath()).exists()) {
                                        it.remove();
                                    }
                                }
                            }
                        } else {
                            listG = ((u) mediaFileGridBaseFragment.adapter).g();
                            size = listG.size();
                            Iterator<FileBean> it2 = listG.iterator();
                            while (it2.hasNext()) {
                                FileBean next = it2.next();
                                if (next.isEditAddItem() || !new File(next.getPath()).exists()) {
                                    it2.remove();
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (listG == null || size == listG.size()) {
                        return;
                    }
                    mainBaseActivity.runOnUiThread(new Runnable() { // from class: com.byd.fragment.MediaFileGridBaseFragment.4.2
                        @Override // java.lang.Runnable
                        public void run() {
                            MediaFileGridBaseFragment.this.adapter.notifyDataSetChanged();
                        }
                    });
                }
            }.start();
        }
    }

    @Override // com.byd.fragment.BasePastFilesDialogFragment.OnPastListener
    public void onCancelPast(boolean z, boolean z2) {
        SearchAndSelectBaseFragment searchAndSelectBaseFragment;
        w.c("onCancelPast : " + z);
        if (z2 && (searchAndSelectBaseFragment = this.searchAndSelectFragment) != null) {
            searchAndSelectBaseFragment.cancelSelectMode();
        }
        if (z || (!z2 && this.isDataChange)) {
            loadData();
            c.D(getActivity()).t();
            if (this.album != null) {
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent(ACTION_PHOTO_DATA_CHANGE));
            }
        }
    }

    protected void onConfigPastDialog(PastFilesDialogFragment pastFilesDialogFragment) {
    }

    @Override // com.byd.fragment.SubBaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        w.c("MediaFileGrid onConfigurationChanged " + this.showLevel);
        int i = configuration.orientation;
        if (i != this.originalOrientation) {
            this.originalOrientation = i;
            this.configurationChange = true;
            cancelTask(0);
            cancelProgressDialog();
        }
        int i2 = this.uiMode;
        int i3 = configuration.uiMode;
        if (i2 != i3) {
            this.uiMode = i3;
            this.configurationChange = true;
        }
        super.onConfigurationChanged(configuration);
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment.OnHeaderListener
    public void onCopy(boolean z) {
        if (this.adapter.s() > 0) {
            showPastDialog(z);
        } else {
            toastMsg(R.string.select_nothing);
        }
    }

    @Override // com.byd.fragment.SubBaseFragment, com.byd.ui.a, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        w.c("MediaFileGrid onCreate " + this.showLevel);
        if (this.searchSource == 10) {
            this.receiver = new FileChangeReceiver();
            IntentFilter intentFilter = new IntentFilter("com.byd.ACTION_FILE_CHANGE");
            intentFilter.addAction(ACTION_PHOTO_DATA_CHANGE);
            intentFilter.addAction("com.byd.external_mounted_state");
            LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.receiver, intentFilter);
        }
        this.cameraPath = g.g(getActivity());
        this.screenShotPath = g.k(getActivity());
        this.shareIconOffsetX = getResources().getDimensionPixelSize(R.dimen.more_dev_share_icon_offset_x);
        initShareIconWindowOnCreate();
    }

    @Override // com.byd.fragment.MediaDataBaseFragment
    public void onDataChange(Uri uri, String str) {
        w.c("==MediaFileGrid onDataChange==" + this.showLevel + "|" + this.isPause + "|" + this.type + "|" + str);
        if (isOutOfFragment()) {
            if (!this.isPause) {
                this.isDataChange = true;
                w.c("MediaFileGrid isOutOfFragment , return !");
                return;
            }
            w.c("MediaFileGrid isOutOfFragment , load silence");
            this.isDataChange = false;
            SearchAndSelectBaseFragment searchAndSelectBaseFragment = this.searchAndSelectFragment;
            if (searchAndSelectBaseFragment != null && searchAndSelectBaseFragment.isSelectAll) {
                searchAndSelectBaseFragment.setSelectAll(false);
            }
            load(false);
            return;
        }
        w.c("onChange in MediaFileGridFragment " + this.showLevel);
        if (this.isPause || !isVisible()) {
            this.isDataChange = true;
        } else {
            if (this.mHandler.hasMessages(3)) {
                return;
            }
            loadData();
        }
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment.OnHeaderListener
    public void onDelete() {
        if (this.adapter.s() > 0) {
            showDeleteDialog();
        } else {
            toastMsg(R.string.select_nothing);
        }
    }

    @Override // com.byd.util.l.i
    public void onDeleteCancel() {
        w.c("MediaFileGrid onDeleteCancel");
        cancelSelectMode();
        if (this.isPause) {
            w.c("checkToRefresh onPause , return");
        } else {
            loadData();
        }
    }

    @Override // com.byd.util.l.i
    public void onDeleteComplete(List<String> list) {
        boolean z;
        SearchAndSelectBaseFragment searchAndSelectBaseFragment;
        int i = 0;
        int size = list != null ? list.size() : 0;
        if (this.album != null) {
            for (int i2 = 0; i2 < size; i2++) {
                try {
                    String str = list.get(i2);
                    if (str.startsWith(this.cameraPath) || str.startsWith(this.screenShotPath)) {
                        z = true;
                        break;
                    }
                } catch (Exception e) {
                    w.c("onDeleteComplete check isCameraPicture exception : " + e.getMessage());
                    e.printStackTrace();
                    z = false;
                }
            }
            z = false;
        } else {
            z = false;
        }
        if (list != null) {
            ImageShowBaseActivity.a1(getActivity(), new ArrayList(list));
        }
        if (!this.searchAndSelectFragment.isSelectAll && !wantReloadAfterDelete(size)) {
            try {
                this.dataManager.d(list);
                this.datasNum -= this.adapter.p(list);
                if (this.adapter.getItemCount() == 0) {
                    this.viewEmpty.setVisibility(0);
                    searchAndSelectBaseFragment = this.searchAndSelectFragment;
                } else {
                    this.viewEmpty.setVisibility(8);
                    searchAndSelectBaseFragment = this.searchAndSelectFragment;
                    i = this.datasNum;
                }
                searchAndSelectBaseFragment.onDataSizeChange(i);
                this.searchAndSelectFragment.cancelSelectMode();
                w.c("onDeleteComplete remove from list");
            } catch (Exception e2) {
                e2.printStackTrace();
                w.c("onDeleteComplete exception:" + e2.getMessage());
                loadData();
            }
            c.D(getActivity()).t();
            Intent intent = new Intent("com.byd.ACTION_FILE_CHANGE");
            intent.putExtra("path", this.path);
            intent.putExtra("delete", true);
            intent.putExtra("msg_from", hashCode() + "-" + this.showLevel);
            LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
            if (this.album == null && z) {
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent(ACTION_PHOTO_DATA_CHANGE));
                return;
            }
        }
        w.c("onDeleteComplete reload : " + this.isDataChange + " " + size + " " + this.datasNum);
        this.searchAndSelectFragment.cancelSelectMode();
        loadData();
        c.D(getActivity()).t();
        Intent intent2 = new Intent("com.byd.ACTION_FILE_CHANGE");
        intent2.putExtra("path", this.path);
        intent2.putExtra("delete", true);
        intent2.putExtra("msg_from", hashCode() + "-" + this.showLevel);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent2);
        if (this.album == null) {
        }
    }

    @Override // com.byd.fragment.SubBaseFragment, com.byd.ui.a, androidx.fragment.app.Fragment
    public void onDestroy() {
        w.c("MediaFileGrid onDestroy " + this.showLevel);
        if (this.searchSource == 10) {
            LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.receiver);
            this.receiver = null;
        }
        super.onDestroy();
        if (this.releaseOnDestroy) {
            q qVar = this.dataManager;
            if (qVar != null) {
                qVar.c();
            }
            t tVar = this.adapter;
            if (tVar != null) {
                tVar.t(null);
            }
            w.c("MediaGridFragment release onDestroy");
        }
    }

    @Override // com.byd.ui.a, androidx.fragment.app.Fragment
    public void onDestroyView() {
        w.c("MediaFileGrid onDestroyView " + this.showLevel + " " + this.configurationChange);
        this.photoListView.removeOnItemTouchListener(this.onItemTouchListener);
        if (this.searchAndSelectFragment != null) {
            FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction.remove(this.searchAndSelectFragment);
            fragmentTransactionBeginTransaction.commitNowAllowingStateLoss();
            this.searchAndSelectFragment = null;
        }
        if (getActivity() != null) {
            getActivity().getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(this.onGlobalLayoutListener);
        }
        super.onDestroyView();
        this.mHandler.removeCallbacksAndMessages(null);
        if (this.configurationChange) {
            return;
        }
        dismissAllDialog();
    }

    @Override // com.byd.fragment.MediaDataBaseFragment, com.byd.fragment.SubBaseFragment, b.a.k.h.b
    public void onExternalEject(String str, boolean z) {
        PastFilesDialogFragment pastFilesDialogFragment;
        if (this.showLevel == 0) {
            return;
        }
        w.c("==MediaFileGrid onExternalEject==");
        if ((z && this.type == 5) || (!z && this.type == 4)) {
            clearAllShowToReload();
            if (this.isPause || ((pastFilesDialogFragment = this.pastDialog) != null && pastFilesDialogFragment.isShowing())) {
                this.mHandler.sendEmptyMessageDelayed(2, 100L);
                return;
            } else {
                this.mHandler.sendEmptyMessage(2);
                return;
            }
        }
        if (!"/collect".equals(this.path)) {
            checkReloadOnMountStateChange();
            return;
        }
        w.c("collect page unmount : " + str + " udisk : " + z);
        if (f.k(getActivity()).n(str)) {
            clearAllShowToReload();
            f.k(getActivity()).v(str);
            dismissAllDialog();
            if (this.isPause || !isVisible()) {
                this.isDataChange = true;
            } else {
                loadData();
            }
        }
    }

    public void onFragmentExit() {
        t tVar;
        w.c("===onFragmentExit===" + this.showLevel);
        if (isInSearchMode() && (tVar = this.adapter) != null && tVar.getItemCount() > 0) {
            this.adapter.t(null);
            this.datasNum = 0;
            w.c("reset search result");
        }
        this.album = null;
    }

    @Override // b.a.e.t.c
    public void onItemClick(FileBean fileBean, int i, View view) {
        if (e.y()) {
            return;
        }
        if (this.adapter.j()) {
            doSelectOrCancel(fileBean, i);
            return;
        }
        b.a.h.l.m(getActivity()).u();
        Intent intent = new Intent(getActivity(), (Class<?>) ImageShowActivity.class);
        intent.putExtra("from_main", true);
        startActivityForResult(intent, 2);
        if (this.isGroup) {
            com.byd.util.r.h().o(((r) this.adapter).g(), fileBean);
        } else {
            com.byd.util.r.h().m(((u) this.adapter).g(), fileBean);
        }
    }

    @Override // b.a.e.t.c
    public void onItemDoubleClick(FileBean fileBean, int i, View view) {
    }

    @Override // b.a.e.t.c
    public void onItemLongClick(FileBean fileBean, int i, View view) {
        if (isInSearchMode()) {
            return;
        }
        this.moveSelectCounter = 0;
        if (!this.searchAndSelectFragment.isSelectMode()) {
            this.searchAndSelectFragment.intoSelectMode(true);
        }
        this.selectState = Boolean.valueOf(!fileBean.isSelect());
        this.moveSelectCounter++;
        this.startDrawSelectPos = i;
        doSelectOrCancel(fileBean, i);
    }

    protected void onLoadDataFinish() {
    }

    @Override // com.byd.fragment.SubBaseFragment
    protected void onLocaleChanged(Configuration configuration) {
        TextView textView;
        int i;
        this.configurationChange = true;
        t tVar = this.adapter;
        if (tVar != null) {
            tVar.n();
            this.photoListView.swapAdapter(this.adapter, true);
        }
        if (this.viewEmpty != null) {
            if (isInSearchMode()) {
                textView = this.viewEmpty;
                i = R.string.search_not_found;
            } else {
                textView = this.viewEmpty;
                i = R.string.no_data;
            }
            textView.setText(i);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onMultiWindowModeChanged(boolean z) {
        this.configurationChange = true;
        if (getActivity() != null) {
            getActivity().getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(this.onGlobalLayoutListener);
        }
        super.onMultiWindowModeChanged(z);
        w.c("MediaFileGridFragment onMultiWindowModeChanged");
    }

    @Override // com.byd.fragment.BasePastFilesDialogFragment.OnPastListener
    public void onPastFinish(boolean z, String str, int i, int i2) {
        int i3;
        int i4;
        w.c("onPastFinish isMove:" + z + " copy counts:" + i + " totalFiles:" + i2);
        SearchAndSelectBaseFragment searchAndSelectBaseFragment = this.searchAndSelectFragment;
        if (searchAndSelectBaseFragment == null) {
            return;
        }
        if (i == 0 && !this.isDataChange) {
            searchAndSelectBaseFragment.cancelSelectMode();
            return;
        }
        LinkedList linkedList = new LinkedList(this.adapter.i());
        if (this.isDataChange) {
            w.c("onPastFinish -> reload 0000");
            this.searchAndSelectFragment.cancelSelectMode();
            loadData();
            if (z) {
                checkIsCameraDataMove(getActivity(), linkedList);
                return;
            }
            return;
        }
        if (!z) {
            String str2 = this.path;
            if ((str2 == null && ((i3 = this.type) == 1 || i3 == 2)) || str.equals(str2)) {
                w.c("onPastFinish -> reload 1111");
                loadData();
            } else {
                c.D(getActivity()).i(str, i2);
            }
        } else if (i2 > 0) {
            if (this.path != null || ((i4 = this.type) != 1 && i4 != 2)) {
                notifyDataChangeAfterMove(linkedList, i2, this.searchAndSelectFragment.isSelectAll);
                return;
            } else {
                w.c("onPastFinish -> reload 222");
                loadData();
                checkIsCameraDataMove(getActivity(), linkedList);
            }
        }
        this.searchAndSelectFragment.cancelSelectMode();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.isPause = true;
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment.OnHeaderListener
    public void onRename() {
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        String str;
        super.onResume();
        this.isPause = false;
        w.c("==onResume==" + this.path + " " + this.isDataChange + " " + this.searchSource + " showLevel:" + this.showLevel);
        t tVar = this.adapter;
        if (tVar != null) {
            if (tVar instanceof r) {
                ((r) tVar).A(getActivity());
            }
            if (this.searchSource != 10 || (this.adapter.getItemCount() != 0 && !this.isDataChange)) {
                this.adapter.notifyDataSetChanged();
                return;
            }
            if (this.isDataChange && isOutOfFragment()) {
                str = "MediaFileGridFragment data change , isOutOfFragment";
            } else {
                int i = this.type;
                if ((i != 5 && i != 4) || j.e(getActivity()).d(this.path) != 0) {
                    w.c("MediaFileGridFragment onResume load :" + hashCode());
                    loadData();
                    return;
                }
                str = "external folder no exist , return";
            }
            w.c(str);
        }
    }

    public void onSearchEditorAction() {
    }

    public void onSecondLevelRemove() {
        w.c("MediaFileGridFragment onSecondLevelRemove : " + this.isPause + "|" + this.isDataChange);
        if (this.isPause || !this.isDataChange) {
            return;
        }
        loadData();
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment.OnHeaderListener
    public void onSelectModeChange(boolean z) {
        this.adapter.w(z);
        if (z) {
            this.photoListView.addOnItemTouchListener(this.onItemTouchListener);
            return;
        }
        this.photoListView.removeOnItemTouchListener(this.onItemTouchListener);
        this.selectState = null;
        this.moveSelectCounter = 0;
        this.startDrawSelectPos = -1;
        this.shareIconWindow.l();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onSelectSizeChange(int r5) {
        /*
            r4 = this;
            com.byd.fragment.SearchAndSelectBaseFragment r0 = r4.searchAndSelectFragment
            if (r0 != 0) goto L5
            return
        L5:
            r1 = 9
            r2 = 1
            r3 = 0
            if (r5 > r1) goto L21
            if (r5 != 0) goto Le
            goto L21
        Le:
            if (r5 <= 0) goto L24
            r0.setShareEnable(r2)
            boolean r0 = r4.isSelectSameType()
            if (r0 == 0) goto L24
            com.byd.share.d r0 = r4.shareIconWindow
            int r1 = r4.shareIconOffsetX
            r0.p(r1, r3)
            goto L29
        L21:
            r0.setShareEnable(r3)
        L24:
            com.byd.share.d r0 = r4.shareIconWindow
            r0.l()
        L29:
            int r0 = r4.datasNum
            if (r5 != r0) goto L33
            com.byd.fragment.SearchAndSelectBaseFragment r5 = r4.searchAndSelectFragment
            r5.setSelectAll(r2)
            goto L38
        L33:
            com.byd.fragment.SearchAndSelectBaseFragment r5 = r4.searchAndSelectFragment
            r5.setSelectAll(r3)
        L38:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.fragment.MediaFileGridBaseFragment.onSelectSizeChange(int):void");
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment.OnHeaderListener
    public void onShare() {
        if (this.adapter.s() <= 0) {
            toastMsg(R.string.select_nothing);
            return;
        }
        if (this.shareDialog == null) {
            ShareDialogFragment shareDialogFragment = new ShareDialogFragment();
            this.shareDialog = shareDialogFragment;
            shareDialogFragment.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.byd.fragment.MediaFileGridBaseFragment.7
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    MediaFileGridBaseFragment.this.checkToRefresh();
                }
            });
        }
        this.shareDialog.setShareFileBeans(this.adapter.i());
        this.shareDialog.show(getActivity().getSupportFragmentManager(), "share");
    }

    @Override // com.byd.share.d.e
    public void onShareIconClick() {
        Intent intent = new Intent(getActivity(), (Class<?>) ShareToOtherDevActivity.class);
        ArrayList<String> arrayList = new ArrayList<>(this.adapter.i().size());
        List<FileBean> listI = this.adapter.i();
        com.byd.share.a aVarS = com.byd.share.a.s(getActivity());
        int type = listI.get(0).getType();
        for (FileBean fileBean : listI) {
            if (type != fileBean.getType()) {
                w.c("type not same ,can not share");
                return;
            }
            arrayList.add(aVarS.o(fileBean.getPath()));
        }
        intent.putStringArrayListExtra("paths", arrayList);
        intent.putExtra("data_type", type);
        intent.putExtra("cover_path", listI.get(listI.size() - 1).getPath());
        startActivity(intent);
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
        w.c("MediaFileGrid onStart " + this.showLevel);
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment.OnHeaderListener
    public void onToolBarViewClick(View view) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        w.c("MediaFileGrid onViewCreated");
        this.configurationChange = false;
        this.photoListView = (RecyclerView) view.findViewById(R.id.photo_list);
        this.viewEmpty = (TextView) view.findViewById(R.id.tv_empty);
        initRecyclerView();
        SearchAndSelectBaseFragment searchAndSelectBaseFragment = (SearchAndSelectBaseFragment) getChildFragmentManager().findFragmentById(R.id.fragment_header);
        this.searchAndSelectFragment = searchAndSelectBaseFragment;
        searchAndSelectBaseFragment.setOnHeaderListener(this);
        this.searchAndSelectFragment.setMode(this.headerMode);
        this.searchAndSelectFragment.setAlbum(this.album);
        if (isInSearchMode()) {
            Bundle arguments = getArguments();
            boolean z = arguments != null && arguments.containsKey("search_word");
            this.searchAndSelectFragment.initSearchModeUI(z, arguments != null ? arguments.getInt("soft_state", 0) : 0);
            this.searchAndSelectFragment.setSearchSource(this.searchSource);
            if (z) {
                this.searchAndSelectFragment.startSearch(arguments.getString("search_word"), arguments.getInt("search_type"));
                arguments.clear();
            } else {
                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity.A0() != null) {
                    this.searchAndSelectFragment.setSearchTextNoWatcher(mainActivity.y0());
                    setSearchResultDatas(mainActivity.A0(), this.datasNum);
                }
            }
            this.viewEmpty.setOnClickListener(this.onClickListener);
            this.viewEmpty.setText(R.string.search_not_found);
            if (getActivity() != null) {
                getActivity().getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(this.onGlobalLayoutListener);
            }
        } else {
            setSearchFragmentSearchSource();
            this.viewEmpty.setText(R.string.no_data);
        }
        if (this.adapter.j()) {
            this.photoListView.addOnItemTouchListener(this.onItemTouchListener);
            this.searchAndSelectFragment.intoSelectMode(false);
            if (this.adapter.s() == this.datasNum) {
                this.searchAndSelectFragment.setSelectAll(true);
            } else {
                this.searchAndSelectFragment.setSelectAll(false);
            }
            onSelectSizeChange(this.adapter.i().size());
        }
    }

    protected void refreshAfterMove() {
    }

    @Override // com.byd.fragment.SearchAndSelectBaseFragment.OnHeaderListener
    public void selectAllOrCancel() {
        int iS = this.adapter.s();
        w.c("selectAllOrCancel : datasNum:" + this.datasNum + " selectSize:" + iS);
        if (this.searchAndSelectFragment == null) {
            return;
        }
        if (iS == this.datasNum) {
            this.adapter.d();
            this.searchAndSelectFragment.setSelectAll(false);
            this.searchAndSelectFragment.setShareEnable(true);
        } else {
            this.adapter.q();
            int iS2 = this.adapter.s();
            if (iS2 != this.datasNum) {
                this.datasNum = iS2;
            }
            this.searchAndSelectFragment.setSelectAll(true);
        }
        onSelectSizeChange(this.adapter.s());
    }

    public void setAlbum(AlbumItem albumItem) {
        this.album = albumItem;
    }

    public void setFocusBean(String str) {
    }

    public void setGroup(boolean z) {
        this.isGroup = z;
    }

    public void setHeaderMode(int i) {
        this.headerMode = i;
    }

    public void setPath(String str) {
        this.path = str;
    }

    protected void setSearchFragmentSearchSource() {
        this.searchAndSelectFragment.setSearchSource(0);
    }

    public void setSearchResultDatas(com.byd.util.u uVar, int i) {
        this.datasNum = i;
        if (this.adapter != null) {
            Thread thread = this.calcDataNumThread;
            if (thread != null && thread.isAlive()) {
                this.calcDataNumThread.interrupt();
                this.calcDataNumThread = null;
            }
            if (uVar == null || uVar.e() == 0) {
                this.viewEmpty.setVisibility(0);
                this.datasNum = 0;
                this.photoListView.stopScroll();
                this.photoListView.stopNestedScroll();
                this.adapter.t(null);
                return;
            }
            this.viewEmpty.setVisibility(8);
            if (this.datasNum == 0) {
                if (this.isGroup) {
                    CalcDataNum calcDataNum = new CalcDataNum(uVar.b());
                    this.calcDataNumThread = calcDataNum;
                    calcDataNum.start();
                } else {
                    this.datasNum = uVar.e();
                }
            }
            this.adapter.t(uVar.b());
            w.c("setSearchResultDatas data counts : " + i + " adapter item count : " + this.adapter.getItemCount());
        }
    }

    public void setSearchSource(int i) {
        this.searchSource = i;
        SearchAndSelectBaseFragment searchAndSelectBaseFragment = this.searchAndSelectFragment;
        if (searchAndSelectBaseFragment != null) {
            searchAndSelectBaseFragment.setSearchSource(i);
        }
    }

    public void setShowLevel(int i) {
        this.showLevel = i;
    }

    public void setType(int i) {
        this.type = i;
    }

    protected void showPastDialog(boolean z) {
        if (this.pastDialog == null) {
            PastFilesDialogFragment pastFilesDialogFragment = new PastFilesDialogFragment();
            this.pastDialog = pastFilesDialogFragment;
            pastFilesDialogFragment.setOnPastListener(this);
        }
        this.pastDialog.setActivity(getActivity());
        this.pastDialog.setSourceFiles(this.adapter.i(), z, this.path);
        onConfigPastDialog(this.pastDialog);
        this.pastDialog.show(getActivity().getSupportFragmentManager(), "past");
    }

    public void startSearchByVoice(String str, int i) {
        SearchAndSelectBaseFragment searchAndSelectBaseFragment = this.searchAndSelectFragment;
        if (searchAndSelectBaseFragment != null) {
            searchAndSelectBaseFragment.setSearchForVoice(true);
            this.searchAndSelectFragment.startSearch(str, i);
            return;
        }
        w.c("startSearchByVoice ==> searchFragment is null return ");
        Bundle bundle = new Bundle();
        bundle.putString("search_word", str);
        bundle.putInt("search_type", i);
        try {
            setArguments(bundle);
        } catch (Exception e) {
            w.c("startSearchByVoice exception:" + e.getMessage());
        }
    }

    protected boolean wantReloadAfterDelete(int i) {
        return this.isDataChange || (i > 10 && i >= this.datasNum / 2);
    }
}
