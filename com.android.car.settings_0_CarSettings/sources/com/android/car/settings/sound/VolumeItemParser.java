package com.android.car.settings.sound;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.Xml;
import com.android.car.settings.R;
import com.android.car.settings.common.Logger;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes5.dex */
public class VolumeItemParser {
    private static final Logger LOG = new Logger((Class<?>) VolumeItemParser.class);
    private static final String XML_TAG_VOLUME_ITEM = "item";
    private static final String XML_TAG_VOLUME_ITEMS = "carVolumeItems";

    public static SparseArray<VolumeItem> loadAudioUsageItems(Context context, int volumeItemsXml) {
        XmlResourceParser parser;
        AttributeSet attrs;
        int type;
        SparseArray<VolumeItem> volumeItems = new SparseArray<>();
        try {
            parser = context.getResources().getXml(volumeItemsXml);
            try {
                attrs = Xml.asAttributeSet(parser);
                do {
                    type = parser.next();
                    if (type == 1) {
                        break;
                    }
                } while (type != 2);
            } catch (Throwable th) {
                if (parser != null) {
                    try {
                        parser.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException | XmlPullParserException e) {
            LOG.e("Error parsing volume groups configuration", e);
        }
        if (!XML_TAG_VOLUME_ITEMS.equals(parser.getName())) {
            throw new RuntimeException("Meta-data does not start with carVolumeItems tag");
        }
        int outerDepth = parser.getDepth();
        int rank = 0;
        while (true) {
            int type2 = parser.next();
            if (type2 == 1 || (type2 == 3 && parser.getDepth() <= outerDepth)) {
                break;
            }
            if (type2 != 3 && XML_TAG_VOLUME_ITEM.equals(parser.getName())) {
                TypedArray item = context.getResources().obtainAttributes(attrs, R.styleable.carVolumeItems_item);
                int usage = item.getInt(R.styleable.carVolumeItems_item_usage, -1);
                if (usage >= 0) {
                    volumeItems.put(usage, new VolumeItem(usage, rank, item.getResourceId(R.styleable.carVolumeItems_item_titleText, 0), item.getResourceId(R.styleable.carVolumeItems_item_icon, 0)));
                    rank++;
                }
                item.recycle();
            }
        }
        if (parser != null) {
            parser.close();
        }
        return volumeItems;
    }

    public static class VolumeItem {
        private final int mIcon;
        private final int mRank;
        private final int mTitle;
        private final int mUsage;

        public VolumeItem(int usage, int rank, int title, int icon) {
            this.mUsage = usage;
            this.mRank = rank;
            this.mTitle = title;
            this.mIcon = icon;
        }

        public int getUsage() {
            return this.mUsage;
        }

        public int getRank() {
            return this.mRank;
        }

        public int getTitle() {
            return this.mTitle;
        }

        public int getIcon() {
            return this.mIcon;
        }
    }
}
