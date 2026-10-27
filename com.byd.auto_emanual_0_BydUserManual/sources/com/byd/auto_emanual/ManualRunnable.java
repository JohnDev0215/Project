package com.byd.auto_emanual;

import android.content.ContentValues;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.byd.auto_emanual.ClassifyDbUtil;
import com.byd.auto_emanual.ContentItem;
import com.byd.auto_emanual.fragment.BaseFragment;
import com.byd.auto_emanual.fragment.ManualFragment;
import com.byd.auto_emanual.fragment.NormalDataAdapter;
import com.byd.auto_emanual.fragment.Page;
import com.byd.auto_emanual.fragment.PageData;
import com.byd.auto_emanual.fragment.RecommendModel;
import com.byd.auto_emanual.fragment.SettingsFragment;
import com.byd.auto_emanual.fragment.ZipInfo;
import com.byd.auto_emanual.util.AutoInfoUtil;
import com.byd.auto_emanual.util.Downloader;
import com.byd.auto_emanual.util.FileUtil;
import com.byd.auto_emanual.util.HttpUtil;
import com.byd.auto_emanual.util.LocalPathUtil;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.SharedPrefUtil;
import com.byd.auto_emanual.util.Utils;
import com.byd.auto_emanual.util.ZipUtils;
import com.google.gson.Gson;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ManualRunnable extends ModelRunnable {
    private static final String TAG = "ManualRunnable";
    private Handler handler;
    Downloader.IDownloadListener listener;
    private PageData pageData;

    public ManualRunnable(String path, String page, RecommendModel.ModelCallback callback) {
        super(path, page, callback);
        this.listener = new Downloader.IDownloadListener() { // from class: com.byd.auto_emanual.ManualRunnable.1
            @Override // com.byd.auto_emanual.util.Downloader.IDownloadListener
            public void onDownloading(String path2, String localPath, int from, int downloadedSize, int fileSize) {
            }

            @Override // com.byd.auto_emanual.util.Downloader.IDownloadListener
            public void onDownloadStart(String path2, String localPath, int from, int to, int fileSize) {
                ManualRunnable.this.downloadZipStart();
            }

            @Override // com.byd.auto_emanual.util.Downloader.IDownloadListener
            public void onDownloadError(String path2, String localPath, int from, int responseCode) {
                MLog.v(ManualRunnable.TAG, "onDownloadError responseCode = " + responseCode + ", localPath = " + localPath);
                ManualRunnable.this.downloadZipError();
            }

            @Override // com.byd.auto_emanual.util.Downloader.IDownloadListener
            public void onDownloadComplete(String path2, String localPath) {
                MLog.d(ManualRunnable.TAG, "onDownloadComplete " + path2);
                if (path2 == null) {
                    MLog.i(ManualRunnable.TAG, "onDownloadComplete null == path error.");
                    ManualRunnable.this.downloadZipError();
                    return;
                }
                File file = new File(localPath);
                String unZipFolder = ManualRunnable.this.getUnZipFolder(file);
                MLog.i(ManualRunnable.TAG, "start unZip " + file + " -> " + unZipFolder);
                if (ManualRunnable.this.doUnZip(file, unZipFolder)) {
                    if (ManualRunnable.this.pageData != null) {
                        ManualRunnable.this.pageData.getZipInfo().setTargetFile(unZipFolder);
                        NormalDataAdapter.localAddress = unZipFolder;
                        if (ManualRunnable.this.pageData.getZipInfo().getZipUrl().equals(FileUtil.getManualZipPathInDisk())) {
                            String file2 = FileUtil.readFile(unZipFolder + File.separator + HttpUtil.Response.DATA + File.separator + "menu.json");
                            String file3 = FileUtil.readFile(unZipFolder + File.separator + HttpUtil.Response.DATA + File.separator + "menu_multi.json");
                            JsonDataRunnable jsonDataRunnable = ExecutorManager.getJsonDataRunnable();
                            if (jsonDataRunnable == null) {
                                MLog.e(ManualRunnable.TAG, "jsonDataRunnable is null");
                            } else {
                                PageData pageDataTransformDataForDiskData = jsonDataRunnable.transformDataForDiskData(file2, file3, Page.JSON_DATA);
                                NormalDataAdapter.menuData = pageDataTransformDataForDiskData.getMenuData();
                                NormalDataAdapter.netAddress = pageDataTransformDataForDiskData.getNetAddress();
                                jsonDataRunnable.saveDataToDb(pageDataTransformDataForDiskData);
                            }
                        }
                        if (ExecutorManager.getManualRunnable() != null) {
                            Message messageObtainMessage = ExecutorManager.getManualRunnable().getModelCallback().obtainMessage(2);
                            messageObtainMessage.obj = ManualRunnable.this.pageData;
                            ExecutorManager.getManualRunnable().getModelCallback().sendMessage(messageObtainMessage);
                            ExecutorManager.getManualRunnable().saveDataToDb(ManualRunnable.this.pageData);
                        }
                    }
                    String strLoad = SharedPrefUtil.load(MApp.context, ManualFragment.ZIP_IN_TYPE);
                    if (!TextUtils.isEmpty(strLoad)) {
                        strLoad = strLoad + ",";
                    }
                    String str = strLoad + AutoInfoUtil.getAutoType(MApp.context);
                    MLog.d(ManualRunnable.TAG, "update zipInType");
                    SharedPrefUtil.save(MApp.context, ManualFragment.ZIP_IN_TYPE, str);
                    ManualRunnable.this.downloadZipEnd();
                    return;
                }
                MLog.i(ManualRunnable.TAG, "unZip error");
                if (ExecutorManager.getManualRunnable() != null) {
                    ExecutorManager.getManualRunnable().getModelCallback().sendEmptyMessage(4);
                }
                ManualRunnable.this.downloadZipError();
            }

            @Override // com.byd.auto_emanual.util.Downloader.IDownloadListener
            public void onNetworkUnavailable() {
                MLog.d(ManualRunnable.TAG, "onNetworkUnavailable");
                if (ExecutorManager.getManualRunnable() != null) {
                    ExecutorManager.getManualRunnable().getModelCallback().sendEmptyMessage(4);
                }
                ManualRunnable.this.downloadZipError();
            }
        };
        BaseFragment fragment = FragmentHolder.getFragment(FragmentHolder.getFragmentCount() - 1);
        if (fragment instanceof SettingsFragment) {
            this.handler = ((SettingsFragment) fragment).getHandler();
        }
    }

    @Override // com.byd.auto_emanual.ModelRunnable
    public PageData transformData(String resultData, String page) {
        ZipInfo zipInfo = (ZipInfo) new Gson().fromJson(resultData, ZipInfo.class);
        PageData pageData = new PageData(page, new ArrayList());
        pageData.setZipInfo(zipInfo);
        return pageData;
    }

    @Override // com.byd.auto_emanual.ModelRunnable
    public PageData loadDataFromLocal(String path, String page) {
        PageData pageDataQueryByPage = ClassifyDbUtil.getInstance(MApp.context).queryByPage(page);
        loadLog(page, pageDataQueryByPage, "loadDataFromLocal");
        return pageDataQueryByPage;
    }

    @Override // com.byd.auto_emanual.ModelRunnable
    protected void handleData(PageData data) {
        if (data == null || !data.hasData()) {
            this.callback.sendEmptyMessage(4);
            return;
        }
        this.pageData = data;
        boolean zIsDownloadZip = isDownloadZip();
        this.callback.removeMessages(1);
        Message messageObtainMessage = this.callback.obtainMessage(2);
        messageObtainMessage.obj = data;
        this.callback.sendMessage(messageObtainMessage);
        String str = TAG;
        MLog.v(str, "handleData " + data.getPage() + " " + data.getZipInfo());
        if (zIsDownloadZip && ZipInfo.NO_UPLOAD_TIME.equals(data.getZipInfo().getZipUploadTime())) {
            MLog.i(str, "handleData to unzip emanual.zip.");
            this.callback.sendEmptyMessage(1);
            downloadZip(null, data.getZipInfo().getZipUrl());
        }
        if (Boolean.parseBoolean(SharedPrefUtil.load(MApp.context, ManualFragment.zipRequireUpdateKey()))) {
            this.handler.removeMessages(5);
            this.handler.sendEmptyMessage(5);
        }
    }

    public boolean isDownloadZip() {
        PageData pageData = this.pageData;
        boolean z = false;
        boolean z2 = true;
        if (pageData != null && pageData.getZipInfo() != null && this.pageData.getZipInfo().getZipUploadTime() != null) {
            String resourceLanguage = this.pageData.getZipInfo().getResourceLanguage();
            String multiResourceLanguage = this.pageData.getZipInfo().getMultiResourceLanguage();
            String str = TAG;
            MLog.i(str, Utils.getCurrentLanguage() + ", " + resourceLanguage + ", " + multiResourceLanguage);
            ZipInfo zipInfo = ClassifyDbUtil.getInstance(MApp.context).queryByResourceLanguage(this.pageData.getPage(), resourceLanguage, multiResourceLanguage).getZipInfo();
            PageData pageDataQueryByPage = ClassifyDbUtil.getInstance(MApp.context).queryByPage(this.pageData.getPage());
            if (zipInfo != null && pageDataQueryByPage.getZipInfo() == null) {
                pageDataQueryByPage.setZipInfo(zipInfo);
                insertWithCurrentLanguage(pageDataQueryByPage);
            }
            if (!isTypeChanged() && zipInfo != null) {
                MLog.d(str, "isDownloadData " + this.pageData.getZipInfo().getZipUploadTime() + ", " + zipInfo.toString());
                if (zipInfo.getZipUploadTime() != null && new File(zipInfo.getTargetFile() + "/home.html").exists()) {
                    if (Long.parseLong(zipInfo.getZipUploadTime()) < Long.parseLong(this.pageData.getZipInfo().getZipUploadTime())) {
                        z = true;
                    } else {
                        this.pageData.setZipInfo(zipInfo);
                        z2 = false;
                    }
                    boolean z3 = z2;
                    z2 = z;
                    z = z3;
                }
            }
        } else {
            MLog.i(TAG, "isDownloadZip no data source");
        }
        MLog.i(TAG, "isDownloadZip : " + z2 + z);
        SharedPrefUtil.save(MApp.context, ManualFragment.zipRequireUpdateKey(), String.valueOf(z));
        SharedPrefUtil.save(MApp.context, ManualFragment.zipRequireDownloadKey(), String.valueOf(z2));
        PageData pageData2 = this.pageData;
        if (pageData2 != null && pageData2.getZipInfo() != null) {
            SharedPrefUtil.save(MApp.context, ManualFragment.zipRequireDownloadSizeKey(), this.pageData.getZipInfo().getZipSize());
            SharedPrefUtil.save(MApp.context, ManualFragment.zipRequireDownloadResourceLanguage(), this.pageData.getZipInfo().getResourceLanguage());
            SharedPrefUtil.save(MApp.context, ManualFragment.zipRequireDownloadMultiResourceLanguage(), this.pageData.getZipInfo().getMultiResourceLanguage());
        } else {
            SharedPrefUtil.save(MApp.context, ManualFragment.zipRequireDownloadSizeKey(), ZipInfo.NO_UPLOAD_TIME);
            SharedPrefUtil.save(MApp.context, ManualFragment.zipRequireDownloadResourceLanguage(), "");
        }
        return z2;
    }

    public void downloadZip(Handler handler) {
        MLog.d(TAG, "downloadZip start");
        this.handler = handler;
        LocalPathUtil.getLocalPath(MApp.context, NormalDataAdapter.zipUrl, this.listener);
    }

    public void downloadZip(Handler handler, String path) {
        MLog.d(TAG, "downloadZip start");
        this.handler = handler;
        LocalPathUtil.getLocalPath(MApp.context, path, this.listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadZipStart() {
        Handler handler = this.handler;
        if (handler != null) {
            handler.sendEmptyMessage(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadZipEnd() {
        SharedPrefUtil.save(MApp.context, ManualFragment.zipRequireUpdateKey(), String.valueOf(false));
        SharedPrefUtil.save(MApp.context, ManualFragment.zipRequireDownloadKey(), String.valueOf(false));
        Handler handler = this.handler;
        if (handler != null) {
            handler.sendEmptyMessage(3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadZipError() {
        Handler handler = this.handler;
        if (handler != null) {
            handler.sendEmptyMessage(4);
        }
    }

    private boolean isTypeChanged() {
        String str;
        String strLoad = SharedPrefUtil.load(MApp.context, ManualFragment.ZIP_IN_TYPE);
        MLog.i(TAG, "oldTypeSaved = " + strLoad);
        if (TextUtils.isEmpty(strLoad)) {
            return false;
        }
        String[] strArrSplit = strLoad.split(",");
        if (strArrSplit.length == 0) {
            return false;
        }
        int length = strArrSplit.length - 1;
        while (true) {
            if (length < 0) {
                str = "";
                break;
            }
            if (!TextUtils.isEmpty(strArrSplit[length])) {
                str = strArrSplit[length];
                break;
            }
            length--;
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String autoType = AutoInfoUtil.getAutoType(MApp.context);
        if (autoType.equalsIgnoreCase(str)) {
            return false;
        }
        MLog.i(TAG, "Auto type is changed, get new data. " + str + " > " + autoType);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean doUnZip(File zipFile, String targetFile) {
        File file = new File(targetFile);
        boolean zUnzipFile = false;
        try {
            zUnzipFile = ZipUtils.unzipFile(zipFile, file, Charset.forName("GBK"));
            if (!zUnzipFile) {
                return zUnzipFile;
            }
            MLog.d(TAG, "unzip finish : " + targetFile);
            return zUnzipFile;
        } catch (Exception e) {
            e.printStackTrace();
            String str = TAG;
            MLog.d(str, "unzip exception:" + e.getMessage());
            try {
                zUnzipFile = ZipUtils.unzipFile(zipFile, file, StandardCharsets.UTF_8);
                MLog.d(str, "unzip by UTF-8 result:" + zUnzipFile);
                return zUnzipFile;
            } catch (Exception e2) {
                e2.printStackTrace();
                MLog.d(TAG, "unzip gbk exception:" + e2.getMessage());
                return zUnzipFile;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getUnZipFolder(File zipFile) {
        String strSubstring = (FileUtil.getCountryAndLanguagePath() + File.separator + zipFile.getName()).substring(0, r3.length() - 3);
        return strSubstring.endsWith(".") ? strSubstring.substring(0, strSubstring.length() - 1) : strSubstring;
    }

    @Override // com.byd.auto_emanual.ModelRunnable
    protected void loadComplete() {
        MLog.v(TAG, "loadComplete");
        this.callback.removeMessages(1);
        this.callback.sendEmptyMessage(3);
    }

    @Override // com.byd.auto_emanual.ModelRunnable
    public void insertToDb(PageData data) {
        insertWithCurrentLanguage(data);
        insertWithResourceLanguage(data);
        insertWithMultiResourceLanguage(data);
        MLog.v(TAG, data.getPage() + " db inserted successfully.");
    }

    private void insertWithCurrentLanguage(PageData data) {
        insertByLanguage(data, Utils.getCurrentLanguage());
    }

    private void insertWithResourceLanguage(PageData data) {
        String currentLanguage = Utils.getCurrentLanguage();
        String resourceLanguage = data.getZipInfo().getResourceLanguage();
        if (TextUtils.isEmpty(resourceLanguage) || resourceLanguage.equals(currentLanguage)) {
            return;
        }
        insertByLanguage(data, resourceLanguage);
    }

    private void insertWithMultiResourceLanguage(PageData data) {
        String currentLanguage = Utils.getCurrentLanguage();
        String multiResourceLanguage = data.getZipInfo().getMultiResourceLanguage();
        if (TextUtils.isEmpty(multiResourceLanguage)) {
            return;
        }
        for (String str : multiResourceLanguage.split(",")) {
            if (!TextUtils.isEmpty(str) && !str.equals(currentLanguage)) {
                insertByLanguage(data, str);
            }
        }
    }

    private void insertByLanguage(PageData data, String currentLanguage) {
        ZipInfo zipInfo = data.getZipInfo();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ClassifyDbUtil.TableClassify.COLUMN_VIN, AutoInfoUtil.getAutoVIN(MApp.context));
        contentValues.put("id", ZipInfo.NO_UPLOAD_TIME);
        contentValues.put(ClassifyDbUtil.TableClassify.COLUMN_PAGE, data.getPage());
        contentValues.put(ClassifyDbUtil.TableClassify.COLUMN_CLASSIFY, LocalPathUtil.getNameFromPath(data.getZipInfo().getZipUrl()));
        contentValues.put(ClassifyDbUtil.TableClassify.COLUMN_HEADER, zipInfo.getZipUploadTime());
        contentValues.put(ClassifyDbUtil.TableClassify.COLUMN_COVER_PATH, zipInfo.getZipUrl());
        contentValues.put(ClassifyDbUtil.TableClassify.COLUMN_FOOTER, zipInfo.getZipSize());
        contentValues.put(ClassifyDbUtil.TableClassify.COLUMN_PATH, zipInfo.getTargetFile());
        contentValues.put("type", ContentItem.ContentItemType.ZIP.toString());
        contentValues.put(ClassifyDbUtil.TableClassify.COLUMN_SERVER_FILE_PATH, zipInfo.getServerFilepath());
        contentValues.put(ClassifyDbUtil.TableClassify.COLUMN_COUNTRY, AutoInfoUtil.getCountryCode());
        contentValues.put("language", currentLanguage);
        contentValues.put(ClassifyDbUtil.TableClassify.COLUMN_RESOURCE_LANGUAGE, zipInfo.getResourceLanguage());
        contentValues.put(ClassifyDbUtil.TableClassify.COLUMN_MULTI_RESOURCE_LANGUAGE, zipInfo.getMultiResourceLanguage());
        ClassifyDbUtil.getInstance(MApp.context).insert(contentValues);
    }

    @Override // com.byd.auto_emanual.ModelRunnable
    protected void loadLog(String page, PageData pageData, String methodName) {
        StringBuilder sb = new StringBuilder();
        sb.append(page).append(" ").append(methodName).append(" ");
        if (pageData == null || pageData.getZipInfo() == null) {
            sb.append("pageData or zipInfo is null");
        } else {
            sb.append(pageData.getZipInfo().toString());
        }
        MLog.v(TAG, "loadData " + sb.toString());
    }

    public RecommendModel.ModelCallback getModelCallback() {
        return this.callback;
    }
}
