package com.android.car.settings.common;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes5.dex */
class PreferenceControllerListHelper {
    private PreferenceControllerListHelper() {
    }

    static List<PreferenceController> getPreferenceControllersFromXml(Context context, int xmlResId, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        List<PreferenceController> controllers = new ArrayList<>();
        try {
            List<Bundle> preferenceMetadata = PreferenceXmlParser.extractMetadata(context, xmlResId, 3);
            for (Bundle metadata : preferenceMetadata) {
                String controllerName = metadata.getString("controller");
                if (!TextUtils.isEmpty(controllerName)) {
                    String key = metadata.getString(PreferenceXmlParser.METADATA_KEY);
                    if (TextUtils.isEmpty(key)) {
                        throw new IllegalArgumentException("Missing key for controller: " + controllerName);
                    }
                    controllers.add(createInstance(controllerName, context, key, fragmentController, uxRestrictions));
                }
            }
            return controllers;
        } catch (IOException | XmlPullParserException e) {
            throw new IllegalArgumentException("Failed to parse preference XML for getting controllers", e);
        }
    }

    private static PreferenceController createInstance(String controllerName, Context context, String key, FragmentController fragmentController, CarUxRestrictions restrictionInfo) {
        try {
            Class<?> clazz = Class.forName(controllerName);
            Constructor<?> preferenceConstructor = clazz.getConstructor(Context.class, String.class, FragmentController.class, CarUxRestrictions.class);
            Object[] params = {context, key, fragmentController, restrictionInfo};
            return (PreferenceController) preferenceConstructor.newInstance(params);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalArgumentException("Invalid preference controller: " + controllerName, e);
        }
    }
}
