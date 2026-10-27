package com.byd.auto_emanual;

import android.os.Message;
import android.text.TextUtils;
import com.byd.auto_emanual.fragment.PageData;
import com.byd.auto_emanual.fragment.RecommendModel;
import com.byd.auto_emanual.search.LevelMenu;
import com.byd.auto_emanual.search.LevelOne;
import com.byd.auto_emanual.util.CompressionUtil;
import com.byd.auto_emanual.util.ListUtil;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.SharedPrefUtil;
import com.byd.auto_emanual.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class JsonDataRunnable extends ModelRunnable {
    public static final String MENU_LIST = "menuList";
    public static final String MENU_TREE_LIST = "menuTreelist";
    public static final String MULTI_MENU_LIST = "multiMenuList";
    public static final String MULTI_MENU_TREE_LIST = "multiMenuTreelist";
    public static final String NET_ADDRESS = "baseurl";
    private static final String TAG = "JsonDataRunnable";

    public void updateMenuData(PageData pageData) {
    }

    public JsonDataRunnable(String path, String page, RecommendModel.ModelCallback callback) {
        super(path, page, callback);
    }

    @Override // com.byd.auto_emanual.ModelRunnable
    public PageData transformData(String resultData, String page) {
        if (resultData != null && !"null".equals(resultData)) {
            PageData pageData = new PageData(page, new ArrayList());
            try {
                JSONObject jSONObject = new JSONObject(resultData).getJSONObject(MENU_TREE_LIST);
                if (jSONObject.has(MENU_LIST)) {
                    updateMenuData(jSONObject.getString(MENU_LIST), pageData);
                }
                if (jSONObject.has(MULTI_MENU_LIST)) {
                    updateMultiMenuData(jSONObject.getString(MULTI_MENU_LIST), pageData);
                }
                updateNetAddress(jSONObject.getString(NET_ADDRESS), pageData);
                return pageData;
            } catch (JSONException e) {
                MLog.e(TAG, "transformData", e);
            }
        }
        return null;
    }

    private void updateMenuData(String menuDataString, PageData pageData) {
        updateMenuDataImpl(menuDataString, pageData);
    }

    private void updateMenuDataImpl(String menuDataString, PageData pageData) {
        List<LevelOne> arrayList;
        if (TextUtils.isEmpty(menuDataString)) {
            MLog.i(TAG, "multi language data package.");
            arrayList = new ArrayList<>();
        } else {
            arrayList = (List) new Gson().fromJson(menuDataString, new TypeToken<List<LevelOne>>() { // from class: com.byd.auto_emanual.JsonDataRunnable.1
            }.getType());
        }
        pageData.setMenuData(arrayList);
    }

    private void updateMultiMenuData(String multiMenuDataString, PageData pageData) {
        updateMultiMenuDataImpl(CompressionUtil.decompress(multiMenuDataString), pageData);
    }

    private void updateMultiMenuDataImpl(String multiMenuDataString, PageData pageData) {
        List<LevelMenu> arrayList;
        if (TextUtils.isEmpty(multiMenuDataString)) {
            MLog.i(TAG, "single language data package.");
            arrayList = new ArrayList<>();
        } else {
            arrayList = (List) new Gson().fromJson(multiMenuDataString, new TypeToken<List<LevelMenu>>() { // from class: com.byd.auto_emanual.JsonDataRunnable.2
            }.getType());
        }
        pageData.setMultiMenuData(arrayList);
        updateCurrentLanguageMenu(arrayList, pageData);
    }

    private static void updateCurrentLanguageMenu(List<LevelMenu> multiMenuData, PageData pageData) {
        if (!ListUtil.isNullOrEmpty(pageData.getMenuData())) {
            MLog.e(TAG, "data conflict. cover single with multi.");
        }
        if (ListUtil.isNullOrEmpty(multiMenuData)) {
            if (pageData.getMenuData() == null) {
                pageData.setMenuData(new ArrayList());
                return;
            }
            return;
        }
        List<LevelOne> arrayList = null;
        Iterator<LevelMenu> it = multiMenuData.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            LevelMenu next = it.next();
            if (Utils.getStandardLanguageCode().equals(next.getLanguageCode())) {
                arrayList = next.getLanguageMenu();
                break;
            }
        }
        if (arrayList == null) {
            Iterator<LevelMenu> it2 = multiMenuData.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                LevelMenu next2 = it2.next();
                if (String.valueOf(Boolean.TRUE).equalsIgnoreCase(next2.getIsDefaultLanguage())) {
                    arrayList = next2.getLanguageMenu();
                    break;
                }
            }
        }
        if (arrayList == null) {
            MLog.e(TAG, "updateCurrentLanguageMenu is null");
            arrayList = new ArrayList<>();
        }
        pageData.setMenuData(arrayList);
    }

    private void updateNetAddress(String netAddressString, PageData pageData) {
        pageData.setNetAddress(netAddressString);
    }

    @Override // com.byd.auto_emanual.ModelRunnable
    protected void handleData(PageData data) {
        String str = TAG;
        MLog.v(str, "handleData " + data);
        if (data == null || !data.hasData()) {
            this.callback.sendEmptyMessage(4);
            return;
        }
        Message messageObtainMessage = this.callback.obtainMessage(2);
        messageObtainMessage.arg1 = 1;
        messageObtainMessage.obj = data;
        MLog.v(str, "handleData " + data.getPage());
        if (this.dataSource == 0) {
            saveDataToDb(data);
        } else if (this.dataSource == 1 || this.dataSource == 2) {
            updateMenuData(data);
        }
        this.callback.sendMessage(messageObtainMessage);
    }

    @Override // com.byd.auto_emanual.ModelRunnable
    public void deleteIfExists(PageData data) {
        SharedPrefUtil.remove(MApp.context, MENU_TREE_LIST);
        SharedPrefUtil.remove(MApp.context, MULTI_MENU_TREE_LIST);
        SharedPrefUtil.remove(MApp.context, NET_ADDRESS);
    }

    @Override // com.byd.auto_emanual.ModelRunnable
    public void insertToDb(PageData data) {
        SharedPrefUtil.save(MApp.context, MENU_TREE_LIST, new Gson().toJson(data.getMenuData()));
        SharedPrefUtil.save(MApp.context, MULTI_MENU_TREE_LIST, new Gson().toJson(data.getMultiMenuData()));
        SharedPrefUtil.save(MApp.context, NET_ADDRESS, data.getNetAddress());
    }

    @Override // com.byd.auto_emanual.ModelRunnable
    protected PageData loadDataFromLocal(String path, String page) {
        String str = TAG;
        MLog.d(str, "readFromCache start");
        PageData pageData = new PageData(page, new ArrayList());
        if (SharedPrefUtil.contains(MApp.context, MENU_TREE_LIST)) {
            updateMenuDataImpl(SharedPrefUtil.load(MApp.context, MENU_TREE_LIST), pageData);
        }
        if (SharedPrefUtil.contains(MApp.context, MULTI_MENU_TREE_LIST)) {
            updateMultiMenuDataImpl(SharedPrefUtil.load(MApp.context, MULTI_MENU_TREE_LIST), pageData);
        }
        updateNetAddress(SharedPrefUtil.load(MApp.context, NET_ADDRESS), pageData);
        int size = pageData.getMenuData() != null ? pageData.getMenuData().size() : 0;
        MLog.d(str, "readFromCache end. menuCount = " + size);
        if (size == 0) {
            return null;
        }
        loadLog(page, pageData, "loadDataFromLocal");
        return pageData;
    }

    public PageData transformDataForDiskData(String menuDataString, String multiMenuDataString, String page) {
        MLog.i(TAG, "transformDataForDiskData");
        PageData pageData = new PageData(page, new ArrayList());
        if (!TextUtils.isEmpty(menuDataString)) {
            updateMenuDataImpl(menuDataString, pageData);
        }
        if (!TextUtils.isEmpty(multiMenuDataString)) {
            updateMultiMenuDataImpl(multiMenuDataString, pageData);
        }
        return pageData;
    }
}
