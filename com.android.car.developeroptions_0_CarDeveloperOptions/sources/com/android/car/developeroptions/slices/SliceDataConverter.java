package com.android.car.developeroptions.slices;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.view.accessibility.AccessibilityManager;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.accessibility.AccessibilitySettings;
import com.android.car.developeroptions.accessibility.AccessibilitySlicePreferenceController;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.core.PreferenceXmlParserUtils;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.slices.SliceData;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import com.android.settingslib.search.SearchIndexableData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
class SliceDataConverter {
    private Context mContext;

    public SliceDataConverter(Context context) {
        this.mContext = context;
    }

    public List<SliceData> getSliceData() {
        ArrayList arrayList = new ArrayList();
        for (SearchIndexableData searchIndexableData : FeatureFactory.getFactory(this.mContext).getSearchFeatureProvider().getSearchIndexableResources().getProviderValues()) {
            String name = searchIndexableData.getTargetClass().getName();
            Indexable$SearchIndexProvider searchIndexProvider = searchIndexableData.getSearchIndexProvider();
            if (searchIndexProvider == null) {
                Log.e("SliceDataConverter", name + " dose not implement Search Index Provider");
            } else {
                arrayList.addAll(getSliceDataFromProvider(searchIndexProvider, name));
            }
        }
        arrayList.addAll(getAccessibilitySliceData());
        return arrayList;
    }

    private List<SliceData> getSliceDataFromProvider(Indexable$SearchIndexProvider indexable$SearchIndexProvider, String str) {
        ArrayList arrayList = new ArrayList();
        List<SearchIndexableResource> xmlResourcesToIndex = indexable$SearchIndexProvider.getXmlResourcesToIndex(this.mContext, true);
        if (xmlResourcesToIndex == null) {
            return arrayList;
        }
        Iterator<SearchIndexableResource> it = xmlResourcesToIndex.iterator();
        while (it.hasNext()) {
            int i = it.next().xmlResId;
            if (i == 0) {
                Log.e("SliceDataConverter", str + " provides invalid XML (0) in search provider.");
            } else {
                arrayList.addAll(getSliceDataFromXML(i, str));
            }
        }
        return arrayList;
    }

    private List<SliceData> getSliceDataFromXML(int i, String str) {
        int next;
        String name;
        ArrayList arrayList = new ArrayList();
        XmlResourceParser xml = null;
        try {
            try {
                try {
                    xml = this.mContext.getResources().getXml(i);
                    do {
                        next = xml.next();
                        if (next == 1) {
                            break;
                        }
                    } while (next != 2);
                    name = xml.getName();
                } catch (Resources.NotFoundException e) {
                    Log.w("SliceDataConverter", "Resource not found error parsing PreferenceScreen: ", e);
                    if (0 != 0) {
                    }
                } catch (IOException e2) {
                    Log.w("SliceDataConverter", "IO Error parsing PreferenceScreen: ", e2);
                    if (0 != 0) {
                    }
                }
            } catch (SliceData.InvalidSliceDataException e3) {
                Log.w("SliceDataConverter", "Invalid data when building SliceData for " + str, e3);
                if (0 != 0) {
                }
            } catch (XmlPullParserException e4) {
                Log.w("SliceDataConverter", "XML Error parsing PreferenceScreen: ", e4);
                if (0 != 0) {
                }
            }
            if (!"PreferenceScreen".equals(name)) {
                throw new RuntimeException("XML document must start with <PreferenceScreen> tag; found" + name + " at " + xml.getPositionDescription());
            }
            String dataTitle = PreferenceXmlParserUtils.getDataTitle(this.mContext, Xml.asAttributeSet(xml));
            for (Bundle bundle : PreferenceXmlParserUtils.extractMetadata(this.mContext, i, 2302)) {
                String string = bundle.getString("controller");
                if (!TextUtils.isEmpty(string)) {
                    String string2 = bundle.getString("key");
                    String string3 = bundle.getString("title");
                    String string4 = bundle.getString("summary");
                    int i2 = bundle.getInt("icon");
                    int sliceType = SliceBuilderUtils.getSliceType(this.mContext, string, string2);
                    boolean z = bundle.getBoolean("platform_slice");
                    String string5 = bundle.getString("unavailable_slice_subtitle");
                    SliceData.Builder builder = new SliceData.Builder();
                    builder.setKey(string2);
                    builder.setTitle(string3);
                    builder.setSummary(string4);
                    builder.setIcon(i2);
                    builder.setScreenTitle(dataTitle);
                    builder.setPreferenceControllerClassName(string);
                    builder.setFragmentName(str);
                    builder.setSliceType(sliceType);
                    builder.setPlatformDefined(z);
                    builder.setUnavailableSliceSubtitle(string5);
                    SliceData sliceDataBuild = builder.build();
                    BasePreferenceController preferenceController = SliceBuilderUtils.getPreferenceController(this.mContext, sliceDataBuild);
                    if (preferenceController.isSliceable() && preferenceController.isAvailable()) {
                        arrayList.add(sliceDataBuild);
                    }
                }
            }
            if (xml != null) {
                xml.close();
            }
            return arrayList;
        } catch (Throwable th) {
            if (0 != 0) {
                xml.close();
            }
            throw th;
        }
    }

    private List<SliceData> getAccessibilitySliceData() {
        ArrayList arrayList = new ArrayList();
        String name = AccessibilitySlicePreferenceController.class.getName();
        String name2 = AccessibilitySettings.class.getName();
        CharSequence text = this.mContext.getText(R.string.accessibility_settings);
        SliceData.Builder builder = new SliceData.Builder();
        builder.setFragmentName(name2);
        builder.setScreenTitle(text);
        builder.setPreferenceControllerClassName(name);
        HashSet hashSet = new HashSet();
        Collections.addAll(hashSet, this.mContext.getResources().getStringArray(R.array.config_settings_slices_accessibility_components));
        List<AccessibilityServiceInfo> accessibilityServiceInfoList = getAccessibilityServiceInfoList();
        PackageManager packageManager = this.mContext.getPackageManager();
        Iterator<AccessibilityServiceInfo> it = accessibilityServiceInfoList.iterator();
        while (it.hasNext()) {
            ResolveInfo resolveInfo = it.next().getResolveInfo();
            ServiceInfo serviceInfo = resolveInfo.serviceInfo;
            String strFlattenToString = new ComponentName(serviceInfo.packageName, serviceInfo.name).flattenToString();
            if (hashSet.contains(strFlattenToString)) {
                String string = resolveInfo.loadLabel(packageManager).toString();
                int iconResource = resolveInfo.getIconResource();
                if (iconResource == 0) {
                    iconResource = R.drawable.ic_accessibility_generic;
                }
                builder.setKey(strFlattenToString);
                builder.setTitle(string);
                builder.setIcon(iconResource);
                builder.setSliceType(1);
                try {
                    arrayList.add(builder.build());
                } catch (SliceData.InvalidSliceDataException e) {
                    Log.w("SliceDataConverter", "Invalid data when building a11y SliceData for " + strFlattenToString, e);
                }
            }
        }
        return arrayList;
    }

    List<AccessibilityServiceInfo> getAccessibilityServiceInfoList() {
        return AccessibilityManager.getInstance(this.mContext).getInstalledAccessibilityServiceList();
    }
}
