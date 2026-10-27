package com.android.launcher3.widget.custom;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Parcel;
import android.os.Process;
import android.provider.DeviceConfig;
import android.util.SparseArray;
import android.util.Xml;
import com.android.launcher3.LauncherAppWidgetProviderInfo;
import com.android.launcher3.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes4.dex */
public class CustomWidgetParser {
    private static List<LauncherAppWidgetProviderInfo> sCustomWidgets;
    private static SparseArray<ComponentName> sWidgetsIdMap;

    public static List<LauncherAppWidgetProviderInfo> getCustomWidgets(Context context) {
        if (sCustomWidgets == null) {
            parseCustomWidgets(context);
        }
        return sCustomWidgets;
    }

    public static int getWidgetIdForCustomProvider(Context context, ComponentName provider) {
        if (sWidgetsIdMap == null) {
            parseCustomWidgets(context);
        }
        int iIndexOfValue = sWidgetsIdMap.indexOfValue(provider);
        if (iIndexOfValue >= 0) {
            return (-100) - sWidgetsIdMap.keyAt(iIndexOfValue);
        }
        return 0;
    }

    public static LauncherAppWidgetProviderInfo getWidgetProvider(Context context, int widgetId) {
        if (sWidgetsIdMap == null || sCustomWidgets == null) {
            parseCustomWidgets(context);
        }
        ComponentName componentName = sWidgetsIdMap.get((-100) - widgetId);
        for (LauncherAppWidgetProviderInfo launcherAppWidgetProviderInfo : sCustomWidgets) {
            if (launcherAppWidgetProviderInfo.provider.equals(componentName)) {
                return launcherAppWidgetProviderInfo;
            }
        }
        return null;
    }

    private static void parseCustomWidgets(Context context) {
        ArrayList arrayList = new ArrayList();
        SparseArray<ComponentName> sparseArray = new SparseArray<>();
        List<AppWidgetProviderInfo> installedProvidersForProfile = AppWidgetManager.getInstance(context).getInstalledProvidersForProfile(Process.myUserHandle());
        if (installedProvidersForProfile.isEmpty()) {
            sCustomWidgets = arrayList;
            sWidgetsIdMap = sparseArray;
            return;
        }
        Parcel parcelObtain = Parcel.obtain();
        installedProvidersForProfile.get(0).writeToParcel(parcelObtain, 0);
        try {
            XmlResourceParser xml = context.getResources().getXml(R.xml.custom_widgets);
            try {
                int depth = xml.getDepth();
                while (true) {
                    int next = xml.next();
                    if ((next == 3 && xml.getDepth() <= depth) || next == 1) {
                        break;
                    }
                    if (next == 2 && DeviceConfig.NAMESPACE_WIDGET.equals(xml.getName())) {
                        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xml), R.styleable.CustomAppWidgetProviderInfo);
                        parcelObtain.setDataPosition(0);
                        CustomAppWidgetProviderInfo customAppWidgetProviderInfoNewInfo = newInfo(typedArrayObtainStyledAttributes, parcelObtain, context);
                        arrayList.add(customAppWidgetProviderInfoNewInfo);
                        typedArrayObtainStyledAttributes.recycle();
                        sparseArray.put(customAppWidgetProviderInfoNewInfo.providerId, customAppWidgetProviderInfoNewInfo.provider);
                    }
                }
                if (xml != null) {
                    xml.close();
                }
                parcelObtain.recycle();
                sCustomWidgets = arrayList;
                sWidgetsIdMap = sparseArray;
            } catch (Throwable th) {
                if (xml != null) {
                    try {
                        xml.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException | XmlPullParserException e) {
            throw new RuntimeException(e);
        }
    }

    private static CustomAppWidgetProviderInfo newInfo(TypedArray a, Parcel parcel, Context context) {
        int i = a.getInt(9, 0);
        CustomAppWidgetProviderInfo customAppWidgetProviderInfo = new CustomAppWidgetProviderInfo(parcel, false, i);
        customAppWidgetProviderInfo.provider = new ComponentName(context.getPackageName(), LauncherAppWidgetProviderInfo.CLS_CUSTOM_WIDGET_PREFIX + i);
        customAppWidgetProviderInfo.label = a.getString(0);
        customAppWidgetProviderInfo.initialLayout = a.getResourceId(2, 0);
        customAppWidgetProviderInfo.icon = a.getResourceId(1, 0);
        customAppWidgetProviderInfo.previewImage = a.getResourceId(3, 0);
        customAppWidgetProviderInfo.resizeMode = a.getInt(4, 0);
        customAppWidgetProviderInfo.spanX = a.getInt(5, 1);
        customAppWidgetProviderInfo.spanY = a.getInt(8, 1);
        customAppWidgetProviderInfo.minSpanX = a.getInt(6, 1);
        customAppWidgetProviderInfo.minSpanY = a.getInt(7, 1);
        return customAppWidgetProviderInfo;
    }
}
