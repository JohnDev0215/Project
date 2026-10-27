package com.android.bluetooth.mapclient;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes.dex */
class FolderListing {
    private static final String TAG = "FolderListing";
    private final ArrayList<String> mFolders = new ArrayList<>();

    FolderListing(InputStream in) throws XmlPullParserException, IOException {
        parse(in);
    }

    public void parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
            xpp.setInput(in, "utf-8");
            for (int event = xpp.getEventType(); event != 1; event = xpp.next()) {
                if (event == 2 && xpp.getName().equals("folder")) {
                    this.mFolders.add(xpp.getAttributeValue(null, "name"));
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "I/O error when parsing XML", e);
        } catch (XmlPullParserException e2) {
            Log.e(TAG, "XML parser error when parsing XML", e2);
        }
    }

    public ArrayList<String> getList() {
        return this.mFolders;
    }
}
