package com.byd.music.fragment.localMusic.utils;

import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import com.blankj.utilcode.util.Utils;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.db.LocalMusicDB;
import com.byd.music.fragment.localInterface.DeleteInterface;
import com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper;
import com.byd.music.utils.LogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class LocalMusicDeleteTask implements DeleteInterface<Integer> {
    private DeleteMusicTask deleteMusicTask;
    private List<LocalMusicBean> localMusicBeanList;
    private DeleteInterface.DeleteResultListener<Integer> mDeleteResultListener;
    MediaScannerConnection mediaScannerConnection;
    private String TAG = getClass().getSimpleName();
    private List<Integer> mDeleteId = new ArrayList();
    private List<LocalMusicBean> mDeleteTempLocalBean = new ArrayList();
    boolean isMediaConnected = false;

    public LocalMusicDeleteTask(List<LocalMusicBean> list) {
        this.localMusicBeanList = list;
        LogUtil.d(this.TAG, "LocalMusicDeleteTask:");
        MediaScannerConnection mediaScannerConnection = new MediaScannerConnection(Utils.getApp().getApplicationContext(), new MediaScannerConnection.MediaScannerConnectionClient() { // from class: com.byd.music.fragment.localMusic.utils.LocalMusicDeleteTask.1
            @Override // android.media.MediaScannerConnection.OnScanCompletedListener
            public void onScanCompleted(String str, Uri uri) {
            }

            @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
            public void onMediaScannerConnected() {
                LogUtil.d(LocalMusicDeleteTask.this.TAG, "onMediaScannerConnected:");
                LocalMusicDeleteTask.this.isMediaConnected = true;
            }
        });
        this.mediaScannerConnection = mediaScannerConnection;
        mediaScannerConnection.connect();
    }

    @Override // com.byd.music.fragment.localInterface.DeleteInterface
    public void deleteBegin() {
        this.mDeleteTempLocalBean.clear();
        DeleteMusicTask deleteMusicTask = new DeleteMusicTask();
        this.deleteMusicTask = deleteMusicTask;
        deleteMusicTask.execute(0);
    }

    @Override // com.byd.music.fragment.localInterface.DeleteInterface
    public void deleteCancel() {
        this.deleteMusicTask.cancel(true);
        mediaScannerDisconnect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mediaScannerDisconnect() {
        MediaScannerConnection mediaScannerConnection = this.mediaScannerConnection;
        if (mediaScannerConnection == null || !mediaScannerConnection.isConnected()) {
            return;
        }
        this.mediaScannerConnection.disconnect();
    }

    @Override // com.byd.music.fragment.localInterface.DeleteInterface
    public int getDeleteTotal() {
        int size = this.localMusicBeanList.size();
        LogUtil.d(this.TAG, "getDeleteTotal=" + size);
        return size;
    }

    @Override // com.byd.music.fragment.localInterface.DeleteInterface
    public void setDeleteResultListener(DeleteInterface.DeleteResultListener<Integer> deleteResultListener) {
        this.mDeleteResultListener = deleteResultListener;
    }

    class DeleteMusicTask extends AsyncTask<Integer, Integer, Integer> {
        int mTotalDeleteNum;

        DeleteMusicTask() {
            this.mTotalDeleteNum = LocalMusicDeleteTask.this.getDeleteTotal();
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            LogUtil.d(LocalMusicDeleteTask.this.TAG, "onPreExecute mTotalDeleteNum=" + this.mTotalDeleteNum);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Integer doInBackground(Integer... numArr) {
            LogUtil.d(LocalMusicDeleteTask.this.TAG, "doInBackground: ");
            int[] iArr = {0};
            try {
                for (LocalMusicBean localMusicBean : LocalMusicDeleteTask.this.localMusicBeanList) {
                    if (LocalMusicDeleteTask.this.deleteMusicTask.isCancelled()) {
                        return null;
                    }
                    if (LocalMusicDeleteTask.this.deleteLocalMusicFile(localMusicBean)) {
                        iArr[0] = iArr[0] + 1;
                        publishProgress(Integer.valueOf((int) (((((double) iArr[0]) * 1.0d) / ((double) this.mTotalDeleteNum)) * 100.0d)));
                        LocalMusicDeleteTask.this.mDeleteId.add(Integer.valueOf(localMusicBean.getMusicId()));
                        LocalMusicDeleteTask.this.mDeleteTempLocalBean.add(localMusicBean);
                        LocalMusicDeleteTask.this.deleteLocalMusicDB(localMusicBean);
                    }
                }
            } catch (Exception e) {
                LogUtil.e(LocalMusicDeleteTask.this.TAG, "doInBackground:LocalMusicDeleteTask " + e.toString());
                e.printStackTrace();
            }
            return Integer.valueOf(iArr[0]);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Integer... numArr) {
            super.onProgressUpdate((Object[]) numArr);
            if (LocalMusicDeleteTask.this.mDeleteResultListener != null) {
                LogUtil.e(LocalMusicDeleteTask.this.TAG, "onProgressUpdate values: " + numArr[0].intValue());
                LocalMusicDeleteTask.this.mDeleteResultListener.updateDeleteMusicNum(numArr[0].intValue());
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Integer num) {
            super.onPostExecute(num);
            StringBuilder sb = new StringBuilder("onPostExecute=");
            sb.append(num);
            if (LocalMusicDeleteTask.this.mDeleteResultListener != null) {
                int size = LocalMusicDeleteTask.this.mDeleteId.size();
                sb.append(" mDeleteId size=");
                sb.append(size);
                sb.append(" mTotalDeleteNum=");
                sb.append(this.mTotalDeleteNum);
                Integer[] numArr = (Integer[]) LocalMusicDeleteTask.this.mDeleteId.toArray(new Integer[size]);
                MusicPlayHelper.getInstance().deleteMusicList.removeAll(LocalMusicDeleteTask.this.mDeleteTempLocalBean);
                if (num.intValue() == this.mTotalDeleteNum) {
                    LocalMusicDeleteTask.this.mDeleteResultListener.deleteSuccess(numArr);
                } else {
                    LocalMusicDeleteTask.this.mDeleteResultListener.deleteFail(numArr);
                }
                LocalMusicDeleteTask.this.mediaScannerDisconnect();
            }
            LogUtil.d(LocalMusicDeleteTask.this.TAG, sb.toString());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onCancelled(Integer num) {
            super.onCancelled(num);
            StringBuilder sb = new StringBuilder("onCancelled=");
            sb.append(num);
            if (LocalMusicDeleteTask.this.mDeleteResultListener != null) {
                MusicPlayHelper.getInstance().deleteMusicList.removeAll(LocalMusicDeleteTask.this.mDeleteTempLocalBean);
                int size = LocalMusicDeleteTask.this.mDeleteId.size();
                sb.append(" mDeleteId size=");
                sb.append(size);
                sb.append(" mTotalDeleteNum=");
                sb.append(this.mTotalDeleteNum);
                boolean z = size < this.mTotalDeleteNum;
                sb.append(" isCancelled=");
                sb.append(z);
                LocalMusicDeleteTask.this.mDeleteResultListener.cancelDelete(z);
            }
            LocalMusicDeleteTask.this.mediaScannerConnection.disconnect();
            LogUtil.d(LocalMusicDeleteTask.this.TAG, sb.toString());
        }
    }

    public boolean deleteLocalMusicFile(LocalMusicBean localMusicBean) {
        StringBuilder sb = new StringBuilder();
        String uri = localMusicBean.getUri();
        boolean z = false;
        if (uri == null) {
            return false;
        }
        sb.append("deleteLocalMusicFile-uri=");
        sb.append(uri);
        LogUtil.e(this.TAG, sb.toString());
        File file = new File(uri);
        sb.append("--isFile=");
        sb.append(file.isFile());
        sb.append("--exists=");
        sb.append(file.exists());
        if (file.isFile() && file.exists() && file.delete()) {
            if (this.isMediaConnected) {
                MediaScannerConnection.scanFile(Utils.getApp().getApplicationContext(), new String[]{localMusicBean.getUri()}, null, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.byd.music.fragment.localMusic.utils.LocalMusicDeleteTask.2
                    @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                    public void onScanCompleted(String str, Uri uri2) {
                        LogUtil.d(LocalMusicDeleteTask.this.TAG, "onScanCompleted:" + str);
                    }
                });
            }
            z = true;
        }
        sb.append("--delete=");
        sb.append(z);
        sb.append(" isMediaConnected:");
        sb.append(this.isMediaConnected);
        LogUtil.e(this.TAG, sb.toString());
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteLocalMusicDB(LocalMusicBean localMusicBean) {
        LocalMusicDB.deleteLocalMusicById(localMusicBean.getMusicId());
    }
}
