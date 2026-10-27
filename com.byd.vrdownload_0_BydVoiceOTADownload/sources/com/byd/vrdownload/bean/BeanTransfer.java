package com.byd.vrdownload.bean;

import com.byd.vrdownload.model.DownloadTask;
import com.byd.vrdownload.model.LanguageOTA;
import com.byd.vrdownload.model.LanguageUpdate;
import com.byd.vrdownload.model.PkgVersion;
import com.byd.vrdownload.model.ProfileDownloadTask;
import com.byd.vrdownload.utils.FileUtils;
import com.byd.vrdownload.utils.LogUtils;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class BeanTransfer {
    private static final String TAG = "BeanTransfer";

    public static DownloadTask getDownloadTask(FileDownloadModel fileDownloadModel) {
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.setPath(fileDownloadModel.getPath());
        downloadTask.setFileName(fileDownloadModel.getFilename());
        downloadTask.setId(fileDownloadModel.getId());
        downloadTask.setTotal(fileDownloadModel.getTotal());
        downloadTask.setStatus(fileDownloadModel.getStatus());
        downloadTask.setReDownload(false);
        downloadTask.setTotal(fileDownloadModel.getTotal());
        downloadTask.setSofar(fileDownloadModel.getSoFar());
        return downloadTask;
    }

    public static List<DownloadTask> getDownloadTaskList(List<LanguageUpdate> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            LanguageUpdate languageUpdate = list.get(i);
            DownloadTask downloadTask = new DownloadTask();
            downloadTask.setPath(languageUpdate.getDownloadingPath());
            downloadTask.setFileName(FileUtils.getFileName(languageUpdate));
            downloadTask.setId(languageUpdate.getId());
            downloadTask.setStatus(FileDownloader.getImpl().getStatus(languageUpdate.getId(), languageUpdate.getDownloadingPath()));
            downloadTask.setReDownload(false);
            downloadTask.setTotal(FileDownloader.getImpl().getTotal(languageUpdate.getId()));
            downloadTask.setSofar(FileDownloader.getImpl().getSoFar(languageUpdate.getId()));
            LogUtils.i(TAG, "getDownloadTaskList: sofar=" + downloadTask.getSofar() + ",total=" + downloadTask.getTotal());
            downloadTask.setLanguageUpdate(languageUpdate);
            arrayList.add(downloadTask);
        }
        return arrayList;
    }

    public static DownloadTask getDownloadTask(BaseDownloadTask baseDownloadTask, LanguageUpdate languageUpdate) {
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.setPath(baseDownloadTask.getPath());
        downloadTask.setFileName(baseDownloadTask.getFilename());
        downloadTask.setId(baseDownloadTask.getId());
        downloadTask.setTotal(baseDownloadTask.getSmallFileTotalBytes());
        downloadTask.setStatus(baseDownloadTask.getStatus());
        downloadTask.setReDownload(false);
        downloadTask.setSofar(baseDownloadTask.getSmallFileSoFarBytes());
        downloadTask.setLanguageUpdate(languageUpdate);
        return downloadTask;
    }

    public static ProfileDownloadTask getProfileTask(BaseDownloadTask baseDownloadTask) {
        ProfileDownloadTask profileDownloadTask = new ProfileDownloadTask();
        profileDownloadTask.setPath(baseDownloadTask.getPath());
        profileDownloadTask.setFileName(baseDownloadTask.getFilename());
        profileDownloadTask.setId(baseDownloadTask.getId());
        profileDownloadTask.setTotal(baseDownloadTask.getSmallFileTotalBytes());
        profileDownloadTask.setStatus(baseDownloadTask.getStatus());
        profileDownloadTask.setReDownload(false);
        profileDownloadTask.setSofar(baseDownloadTask.getSmallFileSoFarBytes());
        return profileDownloadTask;
    }

    public static LanguageUpdate getLanguageUpdate(LanguageOTA languageOTA, Map<String, PkgVersion> map, String str) {
        PkgVersion pkgVersion = map.get(languageOTA.getLanguage());
        LanguageUpdate languageUpdate = new LanguageUpdate();
        languageUpdate.setApkVersion(str);
        if (pkgVersion != null) {
            languageUpdate.setCurrentVersion(pkgVersion.getPkgVersion());
        }
        languageUpdate.setLanguage(languageOTA.getLanguage());
        languageUpdate.setLatestApkVersion(languageUpdate.getApkVersion());
        languageUpdate.setLatestPkgSize(languageOTA.getPkgSize());
        languageUpdate.setLatestPkgMd5(languageOTA.getPkgMd5());
        languageUpdate.setLatestPkgVersion(languageOTA.getPkgVersion());
        languageUpdate.setLatestPkgKey(languageOTA.getPkgKey());
        languageUpdate.setLatestPkgDesc(languageOTA.getPkgDesc());
        languageUpdate.setLatestPatchPkgMd5(languageOTA.getPatchPkgMd5());
        languageUpdate.setLatestPatchPkgSize(languageOTA.getPatchPkgSize());
        languageUpdate.setLatestPatchPkgVersion(languageOTA.getPatchPkgVersion());
        languageUpdate.setLatestPatchPkgKey(languageOTA.getPatchPkgKey());
        return languageUpdate;
    }
}
