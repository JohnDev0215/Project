package com.byd.auto_emanual;

import android.content.ContentValues;
import android.os.Message;
import android.text.TextUtils;
import com.byd.auto_emanual.ClassifyDbUtil;
import com.byd.auto_emanual.fragment.ClassifyData;
import com.byd.auto_emanual.fragment.Page;
import com.byd.auto_emanual.fragment.PageData;
import com.byd.auto_emanual.fragment.RecommendModel;
import com.byd.auto_emanual.fragment.ZipInfo;
import com.byd.auto_emanual.util.AutoInfoUtil;
import com.byd.auto_emanual.util.EncryptUtil;
import com.byd.auto_emanual.util.FileUtil;
import com.byd.auto_emanual.util.HttpUtil;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.NetUtil;
import java.io.File;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class ModelRunnable implements Runnable {
    private static final String TAG = "ModelRunnable";
    protected RecommendModel.ModelCallback callback;
    protected int dataSource = 0;
    Future future;
    private String page;
    private String path;

    private String getUrl(String path, String classify) {
        return path;
    }

    protected Map<String, String> createExtBody() {
        return null;
    }

    public abstract PageData transformData(String resultData, String page);

    public ModelRunnable(String path, String page, RecommendModel.ModelCallback callback) {
        this.path = path;
        this.page = page;
        this.callback = callback;
    }

    @Override // java.lang.Runnable
    public void run() {
        loadStart();
        handleData(loadData());
        loadComplete();
    }

    private PageData loadData() throws Throwable {
        PageData pageDataLoadDataFromNet = loadDataFromNet(this.path, this.page);
        this.dataSource = 0;
        if (pageDataLoadDataFromNet == null) {
            pageDataLoadDataFromNet = loadDataFromLocal(this.path, this.page);
            this.dataSource = 1;
        }
        return loadDataFromDisk(pageDataLoadDataFromNet, this.page);
    }

    private PageData loadDataFromDisk(PageData pageData, String page) {
        if (pageData == null) {
            pageData = new PageData(page, null);
        }
        String manualZipPathInDisk = FileUtil.getManualZipPathInDisk();
        File file = new File(manualZipPathInDisk);
        MLog.i(TAG, "loadDataFromDisk " + file.exists() + " " + file.isFile());
        if (Page.MANUAL.equals(page) && file.exists() && file.isFile() && pageData.getZipInfo() == null) {
            this.dataSource = 2;
            ZipInfo zipInfo = new ZipInfo();
            zipInfo.setZipUploadTime(ZipInfo.NO_UPLOAD_TIME);
            zipInfo.setZipUrl(manualZipPathInDisk);
            zipInfo.setResourceLanguage(Locale.US.toString());
            pageData.setZipInfo(zipInfo);
        }
        return pageData;
    }

    protected void loadStart() {
        MLog.v(TAG, "loadStart: classify = " + this.page + ", path = " + this.path);
        this.callback.sendEmptyMessageDelayed(1, 500L);
    }

    protected void loadComplete() {
        MLog.v(TAG, "loadComplete " + this.page);
        this.callback.removeMessages(1);
        this.callback.sendEmptyMessage(3);
    }

    protected void handleData(PageData data) {
        String str = TAG;
        MLog.v(str, "handleData " + data);
        if (data == null || !data.hasData()) {
            this.callback.sendEmptyMessage(4);
            return;
        }
        this.callback.removeMessages(1);
        Message messageObtainMessage = this.callback.obtainMessage(2);
        messageObtainMessage.obj = data;
        MLog.v(str, "handleData " + data.getPage());
        this.callback.sendMessage(messageObtainMessage);
        if (this.dataSource == 0) {
            saveDataToDb(data);
        }
    }

    private PageData loadDataFromNet(String path, String page) throws Throwable {
        String str = TAG;
        MLog.v(str, "loadDataFromNet " + page);
        PageData pageDataTransformData = null;
        if (!NetUtil.isNetAvailable(MApp.context)) {
            MLog.i(str, "network is unavailable!!!");
            return null;
        }
        Map<String, String> headers = HttpUtil.getHeaders();
        String strCreateBody = HttpUtil.createBody(createExtBody());
        if (!TextUtils.isEmpty(strCreateBody)) {
            strCreateBody = EncryptUtil.encrypt(strCreateBody);
        }
        String url = getUrl(path, page);
        MLog.i(str, "url = " + url);
        String strRequest = HttpUtil.request(url, headers, strCreateBody);
        if (TextUtils.isEmpty(strRequest)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(strRequest);
            String string = jSONObject.getString(HttpUtil.Response.CODE);
            MLog.v(str, "resultCode = " + string);
            if ("1".equals(string)) {
                String string2 = jSONObject.getString(HttpUtil.Response.DATA);
                MLog.v(str, "resultData = " + string2);
                pageDataTransformData = transformData(string2, page);
            } else {
                MLog.e(str, "error : resultCode = " + string);
            }
        } catch (JSONException e) {
            MLog.e(TAG, "json exception : ", e);
        }
        loadLog(page, pageDataTransformData, "loadDataFromNet");
        return pageDataTransformData;
    }

    protected void loadLog(String page, PageData pageData, String methodName) {
        StringBuilder sb = new StringBuilder();
        sb.append(page).append(" ").append(methodName).append(" ");
        if (pageData == null) {
            sb.append("pageData is null");
        } else {
            for (ClassifyData classifyData : pageData.getClassifyData()) {
                sb.append(" ").append(classifyData.getClassify()).append(" ").append(classifyData.getContentItems().size());
            }
        }
        MLog.v(TAG, "loadData " + sb.toString());
    }

    protected PageData loadDataFromLocal(String path, String page) {
        PageData pageDataQueryByPage = ClassifyDbUtil.getInstance(MApp.context).queryByPage(page);
        loadLog(page, pageDataQueryByPage, "loadDataFromLocal");
        return pageDataQueryByPage;
    }

    public void saveDataToDb(PageData data) {
        MLog.v(TAG, "begin saveDataToDb " + this.page);
        deleteIfExists(data);
        insertToDb(data);
    }

    public void deleteIfExists(PageData data) {
        String page = data.getPage();
        String resourceLanguage = data.getZipInfo().getResourceLanguage();
        String multiResourceLanguage = data.getZipInfo().getMultiResourceLanguage();
        if (ClassifyDbUtil.getInstance(MApp.context).querySizeByResourceLanguage(page, resourceLanguage, multiResourceLanguage) != 0) {
            ClassifyDbUtil.getInstance(MApp.context).deleteByResourceLanguage(page, resourceLanguage, multiResourceLanguage);
        }
    }

    public void insertToDb(PageData data) {
        int i = 0;
        for (ClassifyData classifyData : data.getClassifyData()) {
            String classify = classifyData.getClassify();
            for (ContentItem contentItem : classifyData.getContentItems()) {
                i++;
                ContentValues contentValues = new ContentValues();
                contentValues.put(ClassifyDbUtil.TableClassify.COLUMN_VIN, AutoInfoUtil.getAutoVIN(MApp.context));
                contentValues.put("id", ZipInfo.NO_UPLOAD_TIME);
                contentValues.put(ClassifyDbUtil.TableClassify.COLUMN_PAGE, data.getPage());
                contentValues.put(ClassifyDbUtil.TableClassify.COLUMN_CLASSIFY, classify);
                contentValues.put(ClassifyDbUtil.TableClassify.COLUMN_HEADER, contentItem.getHeader());
                contentValues.put(ClassifyDbUtil.TableClassify.COLUMN_COVER_PATH, contentItem.getCoverPath());
                contentValues.put(ClassifyDbUtil.TableClassify.COLUMN_FOOTER, contentItem.getFooter());
                contentValues.put(ClassifyDbUtil.TableClassify.COLUMN_PATH, contentItem.getPath());
                contentValues.put("type", contentItem.getContentItemType() == null ? null : contentItem.getContentItemType().toString());
                contentValues.put("managerId", contentItem.getManagerId());
                contentValues.put(ClassifyDbUtil.TableClassify.COLUMN_NAME_INDEX, contentItem.getNameIndex());
                contentValues.put(ClassifyDbUtil.TableClassify.COLUMN_RESERVE_1, contentItem.getReserve1());
                contentValues.put(ClassifyDbUtil.TableClassify.COLUMN_RESERVE_2, classifyData.getModelType());
                contentValues.put(ClassifyDbUtil.TableClassify.COLUMN_RESERVE_3, contentItem.getReserve3());
                ClassifyDbUtil.getInstance(MApp.context).insert(contentValues);
            }
        }
        MLog.v(TAG, this.page + " db inserted successfully. count = " + i);
    }

    public void setFuture(Future future) {
        this.future = future;
    }

    public Future getFuture() {
        return this.future;
    }
}
