package com.byd.card.utils;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.Constants;
import com.android.systemui.R;
import com.byd.card.bean.AppInfo;
import com.byd.card.bean.Constant;
import com.byd.card.drag.ApplicationInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class CountryCodeUtil {
    private static final String COUNTRY_CODE = "countryCode";
    private static final String PACKAGE = "package";
    private static final String TAG = "CountryCodeUtil";
    private static final String VEHICLE = "vehicle";

    public static List<String> parserMainXml(Context context, String code) {
        if (TextUtils.isEmpty(code) || context == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        XmlResourceParser xml = context.getResources().getXml(R.xml.application_main);
        try {
            try {
                String attributeValue = "";
                String attributeValue2 = "";
                for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                    if (eventType != 0 && eventType != 1) {
                        if (eventType == 2) {
                            String name = xml.getName();
                            if (name.equals(COUNTRY_CODE)) {
                                attributeValue = xml.getAttributeValue(null, "id");
                            }
                            if (attributeValue.equals(code)) {
                                if (name.equals(VEHICLE)) {
                                    attributeValue2 = xml.getAttributeValue(null, "id");
                                } else if (name.equals("package") && attributeValue2.equals("-1")) {
                                    arrayList.add(xml.getAttributeValue(null, "name"));
                                }
                            }
                        } else if (eventType != 3) {
                            throw new IllegalStateException("Unexpected value: " + eventType);
                        }
                    }
                }
                xml.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e2) {
                e2.printStackTrace();
            }
            return arrayList;
        } finally {
            xml.close();
        }
    }

    public static void parserProduceXml(Context context, String code) {
        XmlResourceParser xml = context.getResources().getXml(R.xml.application_produce);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 2) {
                    xml.getName().equals("XXXTAGXXX");
                }
            }
            xml.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
    }

    public static List<String> getPackages() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("com.telenav.app.arp");
        arrayList.add("com.neusoft.na.navigation");
        arrayList.add("net.zmap.znav.byd");
        arrayList.add(Constant.PackageName.AUTO_MAP_PKG);
        arrayList.add("com.telenav.scoutivi.spotify.byd");
        arrayList.add(Constants.VR_PACKAGE_NAME);
        arrayList.add("com.byd.vrsettings");
        arrayList.add("com.sohu.inputmethod.sogou");
        arrayList.add(Constant.PackageName.IQQIJNI_INPUT);
        arrayList.add("com.ts.androidauto.app");
        arrayList.add("com.ts.carplay.app");
        return arrayList;
    }

    public static List<AppInfo> chooseForCountryCode(Context context, List<AppInfo> allApps) {
        List<String> packages = getPackages();
        String str = SystemProperties.get(BYDAutoSysPropertiesDevice.COUNTRY_CODE);
        Log.d(TAG, "chooseForCountryCode code = " + str + ", start = " + System.currentTimeMillis());
        List<String> list = parserMainXml(context, str);
        ArrayList arrayList = new ArrayList();
        for (AppInfo appInfo : allApps) {
            if (packages.contains(appInfo.packageName) && (list == null || !list.contains(appInfo.packageName))) {
                arrayList.add(appInfo);
            }
        }
        allApps.removeAll(arrayList);
        Log.d(TAG, "chooseForCountryCode code = " + str + ", remove = " + System.currentTimeMillis());
        return allApps;
    }

    public static boolean selectForCountryCode(Context context, ApplicationInfo applicationInfo) {
        List<String> packages = getPackages();
        List<String> list = parserMainXml(context, "65");
        if (packages.contains(applicationInfo.mPackageName)) {
            return list != null && list.contains(applicationInfo.mPackageName);
        }
        return true;
    }

    public static boolean isShowApp(Context context, AppInfo appInfo) {
        List<String> packages = getPackages();
        List<String> list = parserMainXml(context, "65");
        if (packages.contains(appInfo.packageName)) {
            return list != null && list.contains(appInfo.packageName);
        }
        return true;
    }
}
