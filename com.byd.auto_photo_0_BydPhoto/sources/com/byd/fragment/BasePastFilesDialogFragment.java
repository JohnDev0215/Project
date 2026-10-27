package com.byd.fragment;

import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import b.a.d;
import b.a.e.a;
import b.a.e.b;
import b.a.k.c;
import b.a.k.g;
import b.a.k.h;
import com.byd.auto_photo.MainBaseActivity;
import com.byd.auto_photo.R;
import com.byd.bean.AlbumItem;
import com.byd.bean.FileBean;
import com.byd.fragment.BaseNewFolderOrRenameFragment;
import com.byd.fragment.PastProgressFragment;
import com.byd.taskmanager.b.b;
import com.byd.util.e;
import com.byd.util.n;
import com.byd.util.w;
import com.byd.view.GridItemDecoration;
import com.byd.view.WrapGridLayoutManager;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BasePastFilesDialogFragment extends BaseTaskDialogFragment implements h.b {
    private static final int MSG_CHECK_SYNC_FINISH = 4;
    private static final int MSG_DELAY_CANCEL_TASK = 5;
    private static final int MSG_LOAD_ALBUMS = 3;
    private static final int MSG_UPDATE_PROGRESS = 1;
    private static final int MSG_UPDATE_PROGRESS_2 = 2;
    private static volatile int copyTotalCount;
    private static volatile long copyTotalSize;
    protected FragmentActivity activity;
    protected a adapter;
    private List<AlbumItem> albumItems;
    private boolean cancelByUser;
    private h externalLoaderManager;
    protected boolean isMove;
    private MHandler mHandler;
    private RecyclerView mRecycler;
    private NewFolderOrRenameFragment newFolderDialog;
    private boolean notifyAlbum;
    protected int numColumn;
    protected OnPastListener onPastListener;
    private int orientation;
    private String pastFromFolder;
    private PastProgressFragment pastProgress;
    private String pastTarget;
    private ProgressDialogFragment progressDialog;
    private boolean reLayout;
    private MountedReceiver receiver;
    private ScannerListener scannerListener;
    private int softInputMode;
    private List<FileBean> sourceFiles;
    private long startPastTime;
    private volatile List<String> syncTargets;
    private int total;
    private List<String> wantToDelFromMediaStore;
    protected int HORIZONTAL_SPACING = 80;
    protected int VERTICAL_SPACING = 2;
    private volatile boolean cancel = false;
    private c.e onAlbumLoaderListener = new c.e() { // from class: com.byd.fragment.BasePastFilesDialogFragment.2
        @Override // b.a.k.c.e
        public void onCancel() {
            if (BasePastFilesDialogFragment.this.progressDialog != null) {
                BasePastFilesDialogFragment.this.progressDialog.dismissAllowingStateLoss();
                BasePastFilesDialogFragment.this.progressDialog = null;
            }
        }

        @Override // b.a.k.c.e
        public void onComplete() {
            w.c("past dialog load complete");
            List<AlbumItem> listC = c.D(BasePastFilesDialogFragment.this.activity).C(true);
            if (listC != null) {
                int size = listC.size();
                BasePastFilesDialogFragment.this.albumItems = new ArrayList(size);
                for (int i = 0; i < size; i++) {
                    BasePastFilesDialogFragment.this.albumItems.add(listC.get(i));
                }
            }
            BasePastFilesDialogFragment basePastFilesDialogFragment = BasePastFilesDialogFragment.this;
            basePastFilesDialogFragment.adapter.v(basePastFilesDialogFragment.pastFromFolder);
            BasePastFilesDialogFragment basePastFilesDialogFragment2 = BasePastFilesDialogFragment.this;
            basePastFilesDialogFragment2.adapter.w(basePastFilesDialogFragment2.albumItems);
            BasePastFilesDialogFragment basePastFilesDialogFragment3 = BasePastFilesDialogFragment.this;
            basePastFilesDialogFragment3.configWindowSize(basePastFilesDialogFragment3.activity.getResources().getConfiguration(), BasePastFilesDialogFragment.this.getTotalHeight());
            if (BasePastFilesDialogFragment.this.progressDialog != null) {
                BasePastFilesDialogFragment.this.progressDialog.dismissAllowingStateLoss();
                BasePastFilesDialogFragment.this.progressDialog = null;
            }
        }

        @Override // b.a.k.c.e
        public void onStart() {
            BasePastFilesDialogFragment.this.progressDialog = new ProgressDialogFragment(R.string.loading_please_wait);
            BasePastFilesDialogFragment.this.progressDialog.show(BasePastFilesDialogFragment.this.getChildFragmentManager(), "load_album");
        }
    };
    private volatile HashMap<String, com.byd.taskmanager.b.a> tasks = new HashMap<>();
    private n.d onCopyProgressListener = new n.d() { // from class: com.byd.fragment.BasePastFilesDialogFragment.6
        @Override // com.byd.util.n.d
        public boolean isCancel() {
            return BasePastFilesDialogFragment.this.cancel;
        }

        @Override // com.byd.util.n.d
        public void onProgress(long j, long j2) {
            BasePastFilesDialogFragment.this.mHandler.obtainMessage(2, BasePastFilesDialogFragment.copyTotalCount + "/" + BasePastFilesDialogFragment.this.total + "\n" + n.i(j) + "/" + n.i(j2)).sendToTarget();
        }
    };
    private PastProgressFragment.OnCancelListener onCancelListener = new PastProgressFragment.OnCancelListener() { // from class: com.byd.fragment.BasePastFilesDialogFragment.8
        @Override // com.byd.fragment.PastProgressFragment.OnCancelListener
        public void onCanceled() {
            w.c("cancel past progress");
            BasePastFilesDialogFragment.this.cancelByUser = true;
            try {
                FrameLayout frameLayout = (FrameLayout) BasePastFilesDialogFragment.this.getDialog().getWindow().getDecorView();
                TextView textView = new TextView(BasePastFilesDialogFragment.this.getActivity());
                textView.setText(R.string.task_cancel_tips);
                textView.setTextColor(-1);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                layoutParams.gravity = 17;
                frameLayout.addView(textView, layoutParams);
            } catch (Exception e) {
                e.printStackTrace();
            }
            BasePastFilesDialogFragment.this.cancelAllTask();
            w.f(BasePastFilesDialogFragment.this.getActivity(), R.string.task_cancel_tips, 1);
        }

        @Override // com.byd.fragment.PastProgressFragment.OnCancelListener
        public void onCanceledFail() {
            w.f(BasePastFilesDialogFragment.this.getActivity(), R.string.data_sync, 0);
        }
    };

    private class CopyCallback implements b<Integer[]> {
        private CopyStateInfo info;

        private CopyCallback(String str) {
            this.info = new CopyStateInfo(str);
        }

        @Override // com.byd.taskmanager.b.b
        public void onCanceled(String str) {
            if (BasePastFilesDialogFragment.this.cancelByUser || BasePastFilesDialogFragment.this.pastProgress == null) {
                return;
            }
            BasePastFilesDialogFragment.this.pastProgress.dismissAllowingStateLoss();
            BasePastFilesDialogFragment.this.pastProgress = null;
        }

        @Override // com.byd.taskmanager.b.b
        public void onComplete(String str, Integer[] numArr) {
            synchronized (this) {
                CopyStateInfo.access$2612(this.info, numArr[0].intValue());
                CopyStateInfo.access$2712(this.info, numArr[1].intValue());
                CopyStateInfo.access$2812(this.info, numArr[2].intValue());
                CopyStateInfo.access$2912(this.info, numArr[3].intValue());
                w.c("past onComplete:" + this.info.copyItems + " " + this.info.copyOkFiles + " " + this.info.doPastTotal + " " + this.info.diskState + " " + BasePastFilesDialogFragment.this.total + " " + BasePastFilesDialogFragment.this.tasks.size());
                if (BasePastFilesDialogFragment.this.tasks.size() == 0 || this.info.doPastTotal == BasePastFilesDialogFragment.this.total) {
                    if (BasePastFilesDialogFragment.this.syncTargets != null && BasePastFilesDialogFragment.this.syncTargets.size() > 0) {
                        BasePastFilesDialogFragment.this.syncMediaStore(this.info);
                        return;
                    }
                    w.c("past copyItems:" + this.info.copyItems + ",success copyOkFiles:" + this.info.copyOkFiles + " times:" + (System.currentTimeMillis() - BasePastFilesDialogFragment.this.startPastTime));
                    BasePastFilesDialogFragment.this.pastComplete(this.info);
                }
            }
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    private static class CopyStateInfo {
        private int copyItems;
        private int copyOkFiles;
        private int diskState;
        private int doPastTotal;
        private String targetFolder;

        private CopyStateInfo(String str) {
            this.targetFolder = str;
        }

        static /* synthetic */ int access$2612(CopyStateInfo copyStateInfo, int i) {
            int i2 = copyStateInfo.copyItems + i;
            copyStateInfo.copyItems = i2;
            return i2;
        }

        static /* synthetic */ int access$2712(CopyStateInfo copyStateInfo, int i) {
            int i2 = copyStateInfo.copyOkFiles + i;
            copyStateInfo.copyOkFiles = i2;
            return i2;
        }

        static /* synthetic */ int access$2812(CopyStateInfo copyStateInfo, int i) {
            int i2 = copyStateInfo.doPastTotal + i;
            copyStateInfo.doPastTotal = i2;
            return i2;
        }

        static /* synthetic */ int access$2912(CopyStateInfo copyStateInfo, int i) {
            int i2 = copyStateInfo.diskState + i;
            copyStateInfo.diskState = i2;
            return i2;
        }
    }

    class CopyTask extends com.byd.taskmanager.b.a<Integer[]> {
        List<FileBean> list;
        String target;

        CopyTask(List<FileBean> list, String str, b bVar) {
            super(bVar);
            this.list = list;
            this.target = str;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Removed duplicated region for block: B:31:0x00d5 A[PHI: r6
  0x00d5: PHI (r6v3 int) = (r6v1 int), (r6v5 int) binds: [B:13:0x0072, B:29:0x00bd] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // com.byd.taskmanager.b.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Integer[] doTask() {
            /*
                Method dump skipped, instruction units count: 440
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.fragment.BasePastFilesDialogFragment.CopyTask.doTask():java.lang.Integer[]");
        }
    }

    private static class MHandler extends d<BasePastFilesDialogFragment> {
        public MHandler(BasePastFilesDialogFragment basePastFilesDialogFragment) {
            super(basePastFilesDialogFragment);
        }

        @Override // b.a.d
        public void handleMessage(BasePastFilesDialogFragment basePastFilesDialogFragment, Message message) {
            int i = message.what;
            if (i == 1) {
                basePastFilesDialogFragment.updateProgress(message);
                return;
            }
            if (i == 2) {
                if (basePastFilesDialogFragment.pastProgress != null) {
                    basePastFilesDialogFragment.pastProgress.updateMsg((String) message.obj);
                    return;
                }
                return;
            }
            if (i == 3) {
                basePastFilesDialogFragment.loadAlbums();
                return;
            }
            if (i == 4) {
                basePastFilesDialogFragment.doSynFinish();
                return;
            }
            if (i != 5) {
                return;
            }
            w.c("PastFileDialog check can cancel task");
            if (basePastFilesDialogFragment.cancelByUser) {
                return;
            }
            basePastFilesDialogFragment.cancelByUser = true;
            basePastFilesDialogFragment.cancelAllTask();
        }
    }

    private class MountedReceiver extends BroadcastReceiver {
        private MountedReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (BasePastFilesDialogFragment.this.pastProgress == null || !BasePastFilesDialogFragment.this.pastProgress.isShowing()) {
                BasePastFilesDialogFragment.this.mHandler.removeMessages(3);
                BasePastFilesDialogFragment.this.mHandler.sendEmptyMessageDelayed(3, 350L);
            }
        }
    }

    public interface OnPastListener {
        void onCancelPast(boolean z, boolean z2);

        void onPastFinish(boolean z, String str, int i, int i2);
    }

    private class ScannerListener implements MediaScannerConnection.OnScanCompletedListener {
        private volatile int count;
        private CopyStateInfo info;
        private int size;
        private List<String> syncPaths;

        private ScannerListener(List<String> list, CopyStateInfo copyStateInfo) {
            init(list, copyStateInfo);
        }

        private synchronized void addCount(String str) {
            this.count++;
            this.syncPaths.remove(str);
            BasePastFilesDialogFragment.this.mHandler.removeMessages(4);
            BasePastFilesDialogFragment.this.mHandler.sendEmptyMessageDelayed(4, 3000L);
        }

        public void init(List<String> list, CopyStateInfo copyStateInfo) {
            this.syncPaths = list;
            this.info = copyStateInfo;
            this.count = 0;
            this.size = list.size();
        }

        @Override // android.media.MediaScannerConnection.OnScanCompletedListener
        public void onScanCompleted(String str, Uri uri) {
            addCount(str);
            BasePastFilesDialogFragment.this.mHandler.obtainMessage(2, BasePastFilesDialogFragment.this.activity.getResources().getString(R.string.data_sync) + "\n" + this.count + "/" + this.size).sendToTarget();
            w.c("Past onScanCompleted:" + str + " " + this.count + " " + this.size);
            if (this.syncPaths.isEmpty() || this.count == this.size) {
                w.c("---past and sync complete--");
                BasePastFilesDialogFragment.this.mHandler.removeMessages(4);
                BasePastFilesDialogFragment.this.pastComplete(this.info);
            }
        }
    }

    private void addDelPath(String str) {
        File file;
        Uri uriJ;
        if (this.activity == null || (uriJ = n.j(this.activity, (file = new File(str)))) == null || !uriJ.toString().startsWith("content://")) {
            return;
        }
        e.b(this.activity, file.getPath(), 1, String.valueOf(ContentUris.parseId(uriJ)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean cancelAllTask() {
        if (this.tasks == null || this.tasks.size() == 0) {
            return false;
        }
        try {
            Iterator it = new ArrayList(this.tasks.values()).iterator();
            while (it.hasNext()) {
                com.byd.taskmanager.a.e().a((com.byd.taskmanager.b.a) it.next());
            }
            w.c("PastFileDialog cancel all task");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void configWindowSize(Configuration configuration, int i) {
        View view;
        int i2;
        if (getDialog() == null) {
            dismissAllowingStateLoss();
            return;
        }
        Window window = getDialog().getWindow();
        if (i == 0) {
            i = this.activity.getResources().getDimensionPixelSize(R.dimen.dp_160);
            view = this.mainLayout;
            i2 = 4;
        } else {
            view = this.mainLayout;
            i2 = 0;
        }
        view.setVisibility(i2);
        window.setLayout(this.activity.getResources().getDimensionPixelSize(R.dimen.dialog_min_width), i);
    }

    private void dismissNewFolderDialog() {
        NewFolderOrRenameFragment newFolderOrRenameFragment = this.newFolderDialog;
        if (newFolderOrRenameFragment != null) {
            if (newFolderOrRenameFragment.isShowing()) {
                this.newFolderDialog.dismissAllowingStateLoss();
            }
            this.newFolderDialog.setOnCreateListener(null);
            this.newFolderDialog = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissProgress() {
        ProgressDialogFragment progressDialogFragment = this.progressDialog;
        if (progressDialogFragment != null) {
            progressDialogFragment.dismissAllowingStateLoss();
            this.progressDialog = null;
        }
        PastProgressFragment pastProgressFragment = this.pastProgress;
        if (pastProgressFragment != null) {
            pastProgressFragment.dismissAllowingStateLoss();
            this.pastProgress.setOnCancelListener(null);
            this.pastProgress = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00f8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long doPast(java.lang.String r11, java.lang.String r12, java.lang.String r13) {
        /*
            Method dump skipped, instruction units count: 318
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.fragment.BasePastFilesDialogFragment.doPast(java.lang.String, java.lang.String, java.lang.String):long");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void doPast(String str) {
        View view = this.mainLayout;
        if (view != null && view.getVisibility() == 0) {
            this.mainLayout.setVisibility(4);
        }
        if (this.pastProgress == null) {
            PastProgressFragment pastProgressFragment = new PastProgressFragment(this.isMove ? R.string.moving : R.string.copying);
            this.pastProgress = pastProgressFragment;
            pastProgressFragment.setOnCancelListener(this.onCancelListener);
            this.pastProgress.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.byd.fragment.BasePastFilesDialogFragment.3
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    w.c("PastProgressDialog onDismiss");
                    BasePastFilesDialogFragment.this.mHandler.sendEmptyMessageDelayed(5, 200L);
                }
            });
        }
        PastProgressFragment pastProgressFragment2 = this.pastProgress;
        if (pastProgressFragment2 != null) {
            if (pastProgressFragment2.isShowing()) {
                return;
            } else {
                this.pastProgress.show(this.activity.getSupportFragmentManager(), "past_progress");
            }
        }
        this.startPastTime = System.currentTimeMillis();
        this.wantToDelFromMediaStore = new LinkedList();
        this.syncTargets = new LinkedList();
        this.total = this.sourceFiles.size();
        this.tasks.clear();
        w.c("thread num:5");
        CopyCallback copyCallback = new CopyCallback(str);
        int i = this.total;
        if (i <= 5) {
            CopyTask copyTask = new CopyTask(this.sourceFiles, str, copyCallback);
            copyTask.setTaskName("copy_0");
            this.tasks.put(copyTask.getTaskName(), copyTask);
            com.byd.taskmanager.a.e().d(copyTask);
        } else {
            int i2 = i / 5;
            w.c("single thread copy num:" + i2);
            int i3 = 0;
            while (i3 < 5) {
                CopyTask copyTask2 = new CopyTask(i3 == 4 ? this.sourceFiles.subList(i3 * i2, this.total) : this.sourceFiles.subList(i3 * i2, (i3 + 1) * i2), str, copyCallback);
                copyTask2.setTaskName("copy_" + i3);
                this.tasks.put(copyTask2.getTaskName(), copyTask2);
                com.byd.taskmanager.a.e().d(copyTask2);
                i3++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doSynFinish() {
        if (this.scannerListener != null) {
            w.c("check sync time out");
            pastComplete(this.scannerListener.info);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getTotalHeight() {
        if (this.adapter.getItemCount() == 0) {
            return 0;
        }
        int iCeil = ((int) (Math.ceil(this.adapter.getItemCount() / this.numColumn) * ((double) (this.adapter.i() + this.VERTICAL_SPACING)))) + 10 + this.activity.getResources().getDimensionPixelSize(R.dimen.dialog_title_height) + this.activity.getResources().getDimensionPixelSize(R.dimen.past_dialog_extras_padding) + getTotalHeightExtras();
        Point point = new Point();
        this.activity.getWindowManager().getDefaultDisplay().getSize(point);
        int dimensionPixelSize = point.y - this.activity.getResources().getDimensionPixelSize(R.dimen.past_dialog_max_padding);
        return iCeil <= dimensionPixelSize ? iCeil : dimensionPixelSize;
    }

    private int getViewWidth() {
        return this.activity.getResources().getDimensionPixelSize(R.dimen.dialog_min_width) - (this.activity.getResources().getDimensionPixelSize(R.dimen.past_dialog_grid_horizontal_margin) * 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSdOrAllMedia(int i) {
        return i == 4 || i == 1 || i == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isTargetExist(String str, String str2) {
        String str3;
        String str4 = File.separator;
        if (str.endsWith(str4)) {
            str3 = str + str2;
        } else {
            str3 = str + str4 + str2;
        }
        return new File(str3).exists();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isUdiskOrAllMedia(int i) {
        return i == 5 || i == 1 || i == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadAlbums() {
        if (!c.D(this.activity).H()) {
            configWindowSize(this.activity.getResources().getConfiguration(), 0);
            c.D(this.activity).J();
            return;
        }
        List<AlbumItem> listC = c.D(this.activity).C(true);
        if (listC != null) {
            int size = listC.size();
            this.albumItems = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                this.albumItems.add(listC.get(i));
            }
        }
        this.adapter.v(this.pastFromFolder);
        this.adapter.w(this.albumItems);
        if (this.adapter.i() > 0) {
            configWindowSize(this.activity.getResources().getConfiguration(), getTotalHeight());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAlbumFragment(boolean z) {
        this.notifyAlbum = true;
        Intent intent = new Intent(AlbumBaseFragment.ACTION_ALBUM_DATA_CHANGE);
        intent.putExtra("want_reload", z);
        LocalBroadcastManager.getInstance(this.activity).sendBroadcast(intent);
    }

    private void notifyAlbumJustCopyModeChange() {
        this.notifyAlbum = true;
        LocalBroadcastManager.getInstance(this.activity).sendBroadcast(new Intent(AlbumBaseFragment.ACTION_ALBUM_COPY_MODE_CHANGE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pastComplete(final CopyStateInfo copyStateInfo) {
        List<String> list = this.wantToDelFromMediaStore;
        if (list != null && list.size() > 0) {
            g.f(this.activity).d(this.wantToDelFromMediaStore);
        }
        this.activity.runOnUiThread(new Runnable() { // from class: com.byd.fragment.BasePastFilesDialogFragment.5
            /* JADX WARN: Removed duplicated region for block: B:14:0x004a A[PHI: r0
  0x004a: PHI (r0v27 com.byd.fragment.BasePastFilesDialogFragment) = (r0v22 com.byd.fragment.BasePastFilesDialogFragment), (r0v30 com.byd.fragment.BasePastFilesDialogFragment) binds: [B:25:0x00bd, B:12:0x0047] A[DONT_GENERATE, DONT_INLINE]] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instruction units count: 221
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.byd.fragment.BasePastFilesDialogFragment.AnonymousClass5.run():void");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNewFolderDialog() {
        if (this.newFolderDialog == null) {
            NewFolderOrRenameFragment newFolderOrRenameFragment = new NewFolderOrRenameFragment();
            this.newFolderDialog = newFolderOrRenameFragment;
            int i = this.softInputMode;
            if (i != 0) {
                newFolderOrRenameFragment.setSoftInputMode(i);
            }
            this.newFolderDialog.setOnCreateListener(new BaseNewFolderOrRenameFragment.OnCreateListener() { // from class: com.byd.fragment.BasePastFilesDialogFragment.7
                /* JADX WARN: Removed duplicated region for block: B:12:0x003a  */
                @Override // com.byd.fragment.BaseNewFolderOrRenameFragment.OnCreateListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public boolean isAlbumExist(java.lang.String r4) {
                    /*
                        r3 = this;
                        com.byd.fragment.BasePastFilesDialogFragment r0 = com.byd.fragment.BasePastFilesDialogFragment.this
                        b.a.e.a r0 = r0.adapter
                        boolean r0 = r0.k(r4)
                        if (r0 != 0) goto L41
                        com.byd.fragment.BasePastFilesDialogFragment r1 = com.byd.fragment.BasePastFilesDialogFragment.this     // Catch: java.lang.Exception -> L3d
                        r2 = 2131624118(0x7f0e00b6, float:1.8875407E38)
                        java.lang.String r1 = r1.getString(r2)     // Catch: java.lang.Exception -> L3d
                        boolean r1 = r4.equals(r1)     // Catch: java.lang.Exception -> L3d
                        if (r1 != 0) goto L3a
                        com.byd.fragment.BasePastFilesDialogFragment r1 = com.byd.fragment.BasePastFilesDialogFragment.this     // Catch: java.lang.Exception -> L3d
                        r2 = 2131624225(0x7f0e0121, float:1.8875624E38)
                        java.lang.String r1 = r1.getString(r2)     // Catch: java.lang.Exception -> L3d
                        boolean r1 = r4.equals(r1)     // Catch: java.lang.Exception -> L3d
                        if (r1 != 0) goto L3a
                        com.byd.fragment.BasePastFilesDialogFragment r1 = com.byd.fragment.BasePastFilesDialogFragment.this     // Catch: java.lang.Exception -> L3d
                        r2 = 2131623986(0x7f0e0032, float:1.8875139E38)
                        java.lang.String r1 = r1.getString(r2)     // Catch: java.lang.Exception -> L3d
                        boolean r4 = r4.equals(r1)     // Catch: java.lang.Exception -> L3d
                        if (r4 == 0) goto L38
                        goto L3a
                    L38:
                        r4 = 0
                        goto L3b
                    L3a:
                        r4 = 1
                    L3b:
                        r0 = r4
                        goto L41
                    L3d:
                        r4 = move-exception
                        r4.printStackTrace()
                    L41:
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.byd.fragment.BasePastFilesDialogFragment.AnonymousClass7.isAlbumExist(java.lang.String):boolean");
                }

                @Override // com.byd.fragment.BaseNewFolderOrRenameFragment.OnCreateListener
                public void onCreateOk(File file) {
                    c cVarD = c.D(BasePastFilesDialogFragment.this.activity);
                    int iH = cVarD.h(file, true);
                    AlbumItem albumItemA = cVarD.A(iH);
                    w.c("past file dialog -> create album ok : " + iH + " " + albumItemA.getNum());
                    int iF = iH + cVarD.F();
                    StringBuilder sb = new StringBuilder();
                    sb.append("past file dialog -> notify index : ");
                    sb.append(iF);
                    w.c(sb.toString());
                    BasePastFilesDialogFragment.this.adapter.n(iF, albumItemA);
                    BasePastFilesDialogFragment.this.doPast(file.getPath());
                }

                @Override // com.byd.fragment.BaseNewFolderOrRenameFragment.OnCreateListener
                public void onRenameOk(File file, File file2) {
                }

                @Override // com.byd.fragment.BaseNewFolderOrRenameFragment.OnCreateListener
                public void onRenameOrCreateCancel() {
                }
            });
        }
        this.newFolderDialog.setEditAlbum(getActivity(), null, null);
        this.newFolderDialog.show(getFragmentManager(), "new_album");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncMediaStore(CopyStateInfo copyStateInfo) {
        w.c("past files dialog sync --start--");
        this.pastProgress.setCanAutoCancel(false);
        this.mHandler.obtainMessage(2, this.activity.getResources().getString(R.string.data_sync)).sendToTarget();
        String[] strArr = new String[this.syncTargets.size()];
        this.syncTargets.toArray(strArr);
        ScannerListener scannerListener = this.scannerListener;
        if (scannerListener == null) {
            this.scannerListener = new ScannerListener(this.syncTargets, copyStateInfo);
        } else {
            scannerListener.init(this.syncTargets, copyStateInfo);
        }
        try {
            MediaScannerConnection.scanFile(this.activity, strArr, null, this.scannerListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateProgress(Message message) {
        copyTotalCount++;
        copyTotalSize += ((Long) message.obj).longValue();
        if (this.pastProgress != null) {
            this.pastProgress.updateMsg(copyTotalCount + "/" + this.total + "\n" + n.i(copyTotalSize));
        }
    }

    protected void configRecyclerExtras() {
        this.numColumn = this.activity.getResources().getInteger(R.integer.past_folder_columns);
        this.HORIZONTAL_SPACING = this.activity.getResources().getDimensionPixelSize(R.dimen.past_dialog_item_horizontal_spacing);
        this.VERTICAL_SPACING = this.activity.getResources().getDimensionPixelSize(R.dimen.past_dialog_item_vertical_spacing);
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getButtonConfig() {
        return 2;
    }

    protected int getImageDefHeight() {
        return getResources().getDimensionPixelSize(R.dimen.past_dialog_item_icon_def_height);
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getLayout() {
        return R.layout.fragment_past_files_dialog;
    }

    public String getPastTarget() {
        return this.pastTarget;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected String getTitle() {
        Resources resources;
        int i;
        if (this.isMove) {
            resources = getResources();
            i = R.string.move_to;
        } else {
            resources = getResources();
            i = R.string.copy_to;
        }
        return resources.getString(i);
    }

    protected int getTotalHeightExtras() {
        return this.activity.getResources().getDimensionPixelSize(R.dimen.dialog_button_height);
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected void initUiData() {
        super.initUiData();
        if (c.D(this.activity).H()) {
            configWindowSize(this.activity.getResources().getConfiguration(), getTotalHeight());
            PastProgressFragment pastProgressFragment = this.pastProgress;
            if (pastProgressFragment == null || !pastProgressFragment.isShowing()) {
                return;
            }
            this.mainLayout.setVisibility(4);
        }
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected void onCancelClick() {
        super.onCancelClick();
        OnPastListener onPastListener = this.onPastListener;
        if (onPastListener != null) {
            onPastListener.onCancelPast(false, false);
        }
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i = this.orientation;
        int i2 = configuration.orientation;
        if (i != i2) {
            this.orientation = i2;
            int integer = this.activity.getResources().getInteger(R.integer.past_folder_columns);
            this.numColumn = integer;
            this.mRecycler.setLayoutManager(new WrapGridLayoutManager(this.activity, integer));
            this.adapter.x(this.numColumn);
            this.adapter.b(getViewWidth(), getResources().getDimensionPixelSize(R.dimen.past_dialog_item_icon_def_height));
            configWindowSize(configuration, getTotalHeight());
            PastProgressFragment pastProgressFragment = this.pastProgress;
            if (pastProgressFragment == null || !pastProgressFragment.isShowing()) {
                return;
            }
            this.mainLayout.setVisibility(4);
        }
    }

    @Override // com.byd.fragment.BaseTaskDialogFragment, com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        this.mHandler = new MHandler(this);
        super.onCreate(bundle);
        h hVarE = h.e(getActivity());
        this.externalLoaderManager = hVarE;
        hVarE.m(this);
        if (this.activity == null) {
            dismissAllowingStateLoss();
            w.c("PastFileDialog dismiss by no activity");
            return;
        }
        this.cancelByUser = false;
        this.notifyAlbum = false;
        copyTotalCount = 0;
        copyTotalSize = 0L;
        this.cancel = false;
        configRecyclerExtras();
        this.receiver = new MountedReceiver();
        LocalBroadcastManager.getInstance(this.activity).registerReceiver(this.receiver, new IntentFilter("com.byd.external_mounted_state"));
    }

    @Override // com.byd.fragment.BaseTaskDialogFragment, com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        if (this.receiver != null) {
            LocalBroadcastManager.getInstance(this.activity).unregisterReceiver(this.receiver);
            this.receiver = null;
        }
        this.externalLoaderManager.o(this);
        MHandler mHandler = this.mHandler;
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            this.mHandler.release();
        }
        super.onDestroy();
        this.externalLoaderManager = null;
    }

    @Override // com.byd.fragment.BaseTaskDialogFragment, com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        dismissProgress();
        dismissNewFolderDialog();
        super.onDestroyView();
        c.D(this.activity).S(this.onAlbumLoaderListener);
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected void onDialogDismiss(DialogInterface dialogInterface) {
        this.cancel = true;
        this.pastTarget = null;
        super.onDialogDismiss(dialogInterface);
        w.c("past dialog onDismiss");
        if (this.cancelByUser) {
            if (cancelAllTask() && !this.notifyAlbum) {
                notifyAlbumFragment(true);
            }
            OnPastListener onPastListener = this.onPastListener;
            if (onPastListener != null) {
                onPastListener.onCancelPast(false, false);
            }
        }
        if (!this.notifyAlbum) {
            notifyAlbumJustCopyModeChange();
        }
        this.notifyAlbum = false;
    }

    @Override // b.a.k.h.b
    public void onExternalEject(final String str, final boolean z) {
        getActivity().runOnUiThread(new Runnable() { // from class: com.byd.fragment.BasePastFilesDialogFragment.4
            @Override // java.lang.Runnable
            public void run() {
                if (BasePastFilesDialogFragment.this.pastTarget != null && BasePastFilesDialogFragment.this.pastTarget.startsWith(str)) {
                    BasePastFilesDialogFragment.this.dismissProgress();
                    BasePastFilesDialogFragment.this.onCancelListener.onCanceled();
                    BasePastFilesDialogFragment.this.dismissAllowingStateLoss();
                    return;
                }
                MediaFileGridBaseFragment mediaFileGridBaseFragmentC0 = ((MainBaseActivity) BasePastFilesDialogFragment.this.activity).C0();
                if (mediaFileGridBaseFragmentC0 != null) {
                    if ((z || !BasePastFilesDialogFragment.this.isSdOrAllMedia(mediaFileGridBaseFragmentC0.getType())) && !(z && BasePastFilesDialogFragment.this.isUdiskOrAllMedia(mediaFileGridBaseFragmentC0.getType()))) {
                        return;
                    }
                    if (BasePastFilesDialogFragment.this.pastProgress != null && BasePastFilesDialogFragment.this.pastProgress.isShowing()) {
                        BasePastFilesDialogFragment.this.pastProgress.dismissAllowingStateLoss();
                        BasePastFilesDialogFragment.this.onCancelListener.onCanceled();
                    }
                    BasePastFilesDialogFragment.this.dismissAllowingStateLoss();
                }
            }
        });
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        PastProgressFragment pastProgressFragment = this.pastProgress;
        if (pastProgressFragment == null || this.mainLayout == null || !pastProgressFragment.isShowing()) {
            loadAlbums();
        } else {
            this.mainLayout.setVisibility(4);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        this.orientation = this.activity.getResources().getConfiguration().orientation;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.album_recycler);
        this.mRecycler = recyclerView;
        recyclerView.setLayoutManager(new WrapGridLayoutManager(this.activity, this.numColumn));
        this.mRecycler.addItemDecoration(new GridItemDecoration(this.HORIZONTAL_SPACING, this.VERTICAL_SPACING));
        a aVar = this.adapter;
        if (aVar == null) {
            a aVar2 = new a(this.activity, this.numColumn, this.HORIZONTAL_SPACING);
            this.adapter = aVar2;
            aVar2.A(false);
            this.adapter.b(getViewWidth(), getImageDefHeight());
            this.adapter.y(new b.c() { // from class: com.byd.fragment.BasePastFilesDialogFragment.1
                @Override // b.a.e.b.c
                public void onAlbumItemClick(AlbumItem albumItem, int i) {
                    if (albumItem.getSourceType() == 0) {
                        BasePastFilesDialogFragment.this.showNewFolderDialog();
                        return;
                    }
                    String path = albumItem.getUri().getPath();
                    BasePastFilesDialogFragment.this.pastTarget = path;
                    if (new File(path).exists()) {
                        BasePastFilesDialogFragment.this.doPast(path);
                    } else {
                        w.e(BasePastFilesDialogFragment.this.activity, R.string.file_no_found);
                    }
                }

                @Override // b.a.e.b.c
                public void onAlbumItemLongClick(AlbumItem albumItem, int i) {
                }
            });
            this.mRecycler.setAdapter(this.adapter);
        } else if (this.reLayout) {
            this.mRecycler.setAdapter(aVar);
            this.adapter.s(this.activity);
        } else {
            this.mRecycler.removeAllViews();
            this.adapter.w(null);
            this.adapter.v(null);
            this.adapter.x(this.numColumn);
            this.adapter.s(this.activity);
            this.mRecycler.swapAdapter(this.adapter, true);
        }
        PastProgressFragment pastProgressFragment = this.pastProgress;
        if (pastProgressFragment != null && pastProgressFragment.isShowing()) {
            this.mainLayout.setVisibility(4);
        }
        this.reLayout = false;
        c.D(this.activity).K(this.onAlbumLoaderListener);
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected void reLayout() {
        this.reLayout = true;
        super.reLayout();
    }

    public void setActivity(FragmentActivity fragmentActivity) {
        this.activity = fragmentActivity;
    }

    public void setOnPastListener(OnPastListener onPastListener) {
        this.onPastListener = onPastListener;
    }

    public void setSoftInputMode(int i) {
        this.softInputMode = i;
    }

    public void setSourceFiles(List<FileBean> list, boolean z, String str) {
        this.sourceFiles = list;
        this.pastFromFolder = str;
        w.c("==setSourceFiles== pastFromFolder : " + str);
        this.isMove = z;
    }
}
