package com.android.bluetooth.mapclient;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes.dex */
class MessagesListing {
    private static final String TAG = "MessagesListing";
    private final ArrayList<Message> mMessages = new ArrayList<>();

    MessagesListing(InputStream in) throws XmlPullParserException, IOException {
        parse(in);
    }

    public void parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
            xpp.setInput(in, "utf-8");
            for (int event = xpp.getEventType(); event != 1; event = xpp.next()) {
                if (event == 2 && xpp.getName().equals(NotificationCompat.CATEGORY_MESSAGE)) {
                    HashMap<String, String> attrs = new HashMap<>();
                    for (int i = 0; i < xpp.getAttributeCount(); i++) {
                        attrs.put(xpp.getAttributeName(i), xpp.getAttributeValue(i));
                    }
                    try {
                        Message msg = new Message(attrs);
                        this.mMessages.add(msg);
                    } catch (IllegalArgumentException e) {
                        Log.w(TAG, "Invalid <msg/>");
                    }
                }
            }
        } catch (IOException e2) {
            Log.e(TAG, "I/O error when parsing XML", e2);
        } catch (XmlPullParserException e3) {
            Log.e(TAG, "XML parser error when parsing XML", e3);
        }
    }

    public ArrayList<Message> getList() {
        return this.mMessages;
    }
}
