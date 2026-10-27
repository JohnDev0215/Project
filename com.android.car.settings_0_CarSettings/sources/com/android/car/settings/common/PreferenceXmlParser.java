package com.android.car.settings.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Xml;
import com.android.car.settings.R;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes5.dex */
public class PreferenceXmlParser {
    static final String METADATA_CONTROLLER = "controller";
    public static final String METADATA_KEY = "key";
    public static final String METADATA_SEARCHABLE = "searchable";
    private static final String PREF_GROUP_TAG_ENDS_WITH = "PreferenceGroup";
    private static final Logger LOG = new Logger((Class<?>) PreferenceXmlParser.class);
    private static final String PREF_TAG_ENDS_WITH = "Preference";
    private static final List<String> SUPPORTED_PREF_TYPES = Arrays.asList(PREF_TAG_ENDS_WITH, "PreferenceCategory", "PreferenceScreen");

    @Retention(RetentionPolicy.SOURCE)
    public @interface MetadataFlag {
        public static final int FLAG_NEED_KEY = 1;
        public static final int FLAG_NEED_PREF_CONTROLLER = 2;
        public static final int FLAG_NEED_SEARCHABLE = 512;
    }

    public static List<Bundle> extractMetadata(Context context, int xmlResId, int flags) throws XmlPullParserException, IOException {
        int type;
        List<Bundle> metadata = new ArrayList<>();
        if (xmlResId <= 0) {
            LOG.d(xmlResId + " is invalid.");
            return metadata;
        }
        XmlResourceParser parser = context.getResources().getXml(xmlResId);
        do {
            int next = parser.next();
            type = next;
            if (next == 1) {
                break;
            }
        } while (type != 2);
        int outerDepth = parser.getDepth();
        while (true) {
            if (type == 2) {
                String nodeName = parser.getName();
                if (SUPPORTED_PREF_TYPES.contains(nodeName) || nodeName.endsWith(PREF_TAG_ENDS_WITH) || nodeName.endsWith(PREF_GROUP_TAG_ENDS_WITH)) {
                    Bundle preferenceMetadata = new Bundle();
                    AttributeSet attrs = Xml.asAttributeSet(parser);
                    TypedArray preferenceAttributes = context.obtainStyledAttributes(attrs, R.styleable.Preference);
                    if (hasFlag(flags, 1)) {
                        preferenceMetadata.putString(METADATA_KEY, getKey(preferenceAttributes));
                    }
                    if (hasFlag(flags, 2)) {
                        preferenceMetadata.putString(METADATA_CONTROLLER, getController(preferenceAttributes));
                    }
                    if (hasFlag(flags, 512)) {
                        preferenceMetadata.putBoolean(METADATA_SEARCHABLE, isSearchable(preferenceAttributes));
                    }
                    metadata.add(preferenceMetadata);
                    preferenceAttributes.recycle();
                }
            }
            int next2 = parser.next();
            type = next2;
            if (next2 == 1 || (type == 3 && parser.getDepth() <= outerDepth)) {
                break;
            }
        }
        parser.close();
        return metadata;
    }

    private static boolean hasFlag(int flags, int flag) {
        return (flags & flag) != 0;
    }

    private static String getKey(TypedArray styledAttributes) {
        return styledAttributes.getString(6);
    }

    private static String getController(TypedArray styledAttributes) {
        return styledAttributes.getString(R.styleable.Preference_controller);
    }

    private static boolean isSearchable(TypedArray styledAttributes) {
        return styledAttributes.getBoolean(R.styleable.Preference_searchable, true);
    }
}
