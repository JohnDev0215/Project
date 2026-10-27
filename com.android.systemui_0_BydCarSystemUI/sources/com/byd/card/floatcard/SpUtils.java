package com.byd.card.floatcard;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.android.systemui.SystemUIApplication;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class SpUtils {
    public static final String APPWIDGET = "app_widget";
    public static final String AUTO_TYPE = "auto_type";
    public static final String CARD_ITEMS = "card_items";
    private static final String CARD_NAME = "float_card";
    public static final String CODE = "code";
    public static final String COUNTRY_CODE = "country_code";
    private static final String PATH = "/card_shared_prefs";
    public static final String SOC_VERSION = "soc_version";
    private static final String SPLIT = ";";
    public static final String STYLE = "style";
    public static final String TAG = "SpUtils";
    public static final String UI_STYLE = "ui_style";
    public static final String WIDGET_IDS = "widget_ids";

    public static void saveCardList(Context context, List<CardItemBean> data) {
        if (data == null || data.isEmpty()) {
            return;
        }
        File file = new File(context.getCacheDir().getAbsolutePath() + PATH, "float_card.xml");
        if (file.exists()) {
            file.delete();
        }
        Iterator<CardItemBean> it = data.iterator();
        String strConcat = "";
        while (it.hasNext()) {
            strConcat = strConcat.concat(SPLIT).concat(it.next().flattenToString());
        }
        SharedPreferences.Editor editorEdit = getSharedPreferences(context, CARD_NAME).edit();
        editorEdit.putString(CARD_ITEMS, strConcat);
        editorEdit.apply();
        Log.d(TAG, "saveCardList pref_xml: " + file.exists() + ", size = " + data.size() + " value = " + strConcat);
        try {
            Runtime.getRuntime().exec("sync");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<CardItemBean> getCardList(Context context) {
        String string = getSharedPreferences(context, CARD_NAME).getString(CARD_ITEMS, "");
        Log.i(TAG, "getCardList value: " + string);
        ArrayList arrayList = new ArrayList();
        if (string.isEmpty()) {
            return arrayList;
        }
        for (String str : string.split(SPLIT)) {
            if (!str.isEmpty() && !SPLIT.equals(str)) {
                arrayList.add(CardItemBean.unFlattenFromString(str));
            }
        }
        return arrayList;
    }

    public static HashMap<ComponentName, Integer> getWidgetIdMap(Context context) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (WidgetIdBean widgetIdBean : getWidgetIDList(context)) {
            linkedHashMap.put(ComponentName.unflattenFromString(widgetIdBean.getComponentNameStr()), Integer.valueOf(widgetIdBean.getAppWidgetId()));
        }
        return linkedHashMap;
    }

    public static List<WidgetIdBean> getWidgetIDList(Context context) {
        String string = context.getSharedPreferences(APPWIDGET, 0).getString(WIDGET_IDS, "");
        ArrayList arrayList = new ArrayList();
        if (string.isEmpty()) {
            return arrayList;
        }
        for (String str : string.split(SPLIT)) {
            if (!str.isEmpty() && !SPLIT.equals(str)) {
                arrayList.add(WidgetIdBean.unFlattenFromString(str));
            }
        }
        return arrayList;
    }

    public static void saveWidgetIDList(Context context, List<WidgetIdBean> data) {
        if (data == null || data.isEmpty()) {
            return;
        }
        Iterator<WidgetIdBean> it = data.iterator();
        String strConcat = "";
        while (it.hasNext()) {
            strConcat = strConcat.concat(SPLIT).concat(it.next().flattenToString());
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(APPWIDGET, 0).edit();
        Log.d(TAG, "saveWidgetIDList WIDGET_IDS = widget_ids");
        editorEdit.putString(WIDGET_IDS, strConcat);
        editorEdit.apply();
    }

    public static void saveWidgetIDMap(Context context, Map<ComponentName, Integer> map) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<ComponentName, Integer> entry : map.entrySet()) {
            arrayList.add(new WidgetIdBean(entry.getKey(), entry.getValue().intValue()));
        }
        saveWidgetIDList(context, arrayList);
    }

    public static void removeCarPlayOrAuto(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context, CARD_NAME);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        String string = sharedPreferences.getString(CARD_ITEMS, "");
        Log.i(TAG, "removeCarPlayOrAuto values: " + string);
        if (string.isEmpty()) {
            return;
        }
        int iIndexOf = string.indexOf(SPLIT, 1);
        String strSubstring = string.substring(iIndexOf);
        Log.i(TAG, "removeCarPlayOrAuto value: " + strSubstring + ", index = " + iIndexOf);
        editorEdit.putString(CARD_ITEMS, strSubstring);
        editorEdit.apply();
    }

    public static void removeCard(Context context, String packageName, String type) {
        SharedPreferences sharedPreferences = getSharedPreferences(context, CARD_NAME);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        String string = sharedPreferences.getString(CARD_ITEMS, "");
        if (string.isEmpty()) {
            return;
        }
        String str = SPLIT + packageName + "$" + type;
        Log.i(TAG, "removeAppCard item: " + str + ", contains = " + string.contains(str));
        if (string.contains(str)) {
            String strReplace = string.replace(str, "");
            Log.i(TAG, "removeAppCard result: " + strReplace);
            editorEdit.putString(CARD_ITEMS, strReplace);
            editorEdit.apply();
        }
    }

    private static SharedPreferences getSharedPreferences(Context context, String fileName) {
        return context.getSharedPreferences(fileName, 0);
    }

    public static void setCountryCode(String countryCode) {
        SharedPreferences.Editor editorEdit = getSharedPreferences(SystemUIApplication.getInstance().getApplicationContext(), CODE).edit();
        editorEdit.putString(COUNTRY_CODE, countryCode);
        editorEdit.apply();
        Log.d(TAG, "setCountryCode countryCode " + countryCode);
    }

    public static String getCountryCode() {
        return getSharedPreferences(SystemUIApplication.getInstance().getApplicationContext(), CODE).getString(COUNTRY_CODE, "");
    }

    public static void setSocVersion(String version) {
        SharedPreferences.Editor editorEdit = getSharedPreferences(SystemUIApplication.getInstance().getApplicationContext(), CODE).edit();
        editorEdit.putString(SOC_VERSION, version);
        editorEdit.apply();
    }

    public static String getSocVersion() {
        return getSharedPreferences(SystemUIApplication.getInstance().getApplicationContext(), CODE).getString(SOC_VERSION, "");
    }

    public static void setUiStyle(int style) {
        SharedPreferences.Editor editorEdit = getSharedPreferences(SystemUIApplication.getInstance().getApplicationContext(), STYLE).edit();
        editorEdit.putInt(UI_STYLE, style);
        editorEdit.apply();
        Log.d(TAG, "setUiStyle style " + style);
    }

    public static int getUiStyle() {
        return getSharedPreferences(SystemUIApplication.getInstance().getApplicationContext(), STYLE).getInt(UI_STYLE, -1);
    }

    public static void setAutoType(int autoType) {
        SharedPreferences.Editor editorEdit = getSharedPreferences(SystemUIApplication.getInstance().getApplicationContext(), STYLE).edit();
        editorEdit.putInt(AUTO_TYPE, autoType);
        editorEdit.apply();
        Log.d(TAG, "setAutoType autoType " + autoType);
    }

    public static int getAutoType() {
        return getSharedPreferences(SystemUIApplication.getInstance().getApplicationContext(), STYLE).getInt(AUTO_TYPE, 0);
    }

    public static void clearFloatCard() {
        SharedPreferences.Editor editorEdit = getSharedPreferences(SystemUIApplication.getInstance().getApplicationContext(), CARD_NAME).edit();
        editorEdit.remove(CARD_ITEMS);
        editorEdit.apply();
    }

    public static void clearAppWidgetIds() {
        SharedPreferences.Editor editorEdit = getSharedPreferences(SystemUIApplication.getInstance().getApplicationContext(), APPWIDGET).edit();
        editorEdit.remove(WIDGET_IDS);
        editorEdit.apply();
        Log.i(TAG, "clearAppWidgetIds");
    }
}
