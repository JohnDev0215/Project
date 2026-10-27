package com.android.bluetooth.map;

import android.util.Log;
import android.util.Xml;
import com.android.bluetooth.Utils;
import com.android.internal.util.FastXmlSerializer;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMapConvoListing {
    private static final boolean D = false;
    private static final String TAG = "BluetoothMapConvoListing";
    private static final String XML_TAG = "MAP-convo-listing";
    private boolean mHasUnread = false;
    private List<BluetoothMapConvoListingElement> mList = new ArrayList();

    public void add(BluetoothMapConvoListingElement element) {
        this.mList.add(element);
        if (element.getReadBool()) {
            this.mHasUnread = true;
        }
    }

    public int getCount() {
        List<BluetoothMapConvoListingElement> list = this.mList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public boolean hasUnread() {
        return this.mHasUnread;
    }

    public List<BluetoothMapConvoListingElement> getList() {
        return this.mList;
    }

    public byte[] encode() throws UnsupportedEncodingException {
        StringWriter sw = new StringWriter();
        XmlSerializer xmlConvoElement = new FastXmlSerializer(0);
        try {
            xmlConvoElement.setOutput(sw);
            xmlConvoElement.startDocument("UTF-8", true);
            xmlConvoElement.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            xmlConvoElement.startTag(null, XML_TAG);
            xmlConvoElement.attribute(null, "version", "1.0");
            for (BluetoothMapConvoListingElement element : this.mList) {
                element.encode(xmlConvoElement);
            }
            xmlConvoElement.endTag(null, XML_TAG);
            xmlConvoElement.endDocument();
        } catch (IOException e) {
            Log.w(TAG, e);
        } catch (IllegalArgumentException e2) {
            Log.w(TAG, e2);
        } catch (IllegalStateException e3) {
            Log.w(TAG, e3);
        }
        return sw.toString().getBytes("UTF-8");
    }

    public void sort() {
        Collections.sort(this.mList);
    }

    public void segment(int count, int offset) {
        int count2 = Math.min(count, this.mList.size() - offset);
        if (count2 > 0) {
            List<BluetoothMapConvoListingElement> listSubList = this.mList.subList(offset, offset + count2);
            this.mList = listSubList;
            if (listSubList == null) {
                this.mList = new ArrayList();
                return;
            }
            return;
        }
        if (offset > this.mList.size()) {
            this.mList = new ArrayList();
            Log.d(TAG, "offset greater than list size. Returning empty list");
        } else {
            List<BluetoothMapConvoListingElement> list = this.mList;
            this.mList = list.subList(offset, list.size());
        }
    }

    public void appendFromXml(InputStream xmlDocument) throws XmlPullParserException, IOException, ParseException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(xmlDocument, "UTF-8");
            while (true) {
                int type = parser.next();
                if (type == 3 || type == 1) {
                    break;
                }
                if (parser.getEventType() == 2) {
                    String name = parser.getName();
                    if (!name.equalsIgnoreCase(XML_TAG)) {
                        Utils.skipCurrentTag(parser);
                    }
                    readConversations(parser);
                }
            }
        } finally {
            xmlDocument.close();
        }
    }

    private void readConversations(XmlPullParser parser) throws XmlPullParserException, IOException, ParseException {
        while (true) {
            int type = parser.next();
            if (type != 3 && type != 1) {
                if (parser.getEventType() == 2) {
                    String name = parser.getName();
                    if (!name.trim().equalsIgnoreCase(BluetoothMapConvoListingElement.XML_TAG_CONVERSATION)) {
                        Utils.skipCurrentTag(parser);
                    } else {
                        add(BluetoothMapConvoListingElement.createFromXml(parser));
                    }
                }
            } else {
                return;
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BluetoothMapConvoListing other = (BluetoothMapConvoListing) obj;
        if (this.mHasUnread != other.mHasUnread) {
            return false;
        }
        List<BluetoothMapConvoListingElement> list = this.mList;
        if (list == null) {
            if (other.mList != null) {
                return false;
            }
        } else if (!list.equals(other.mList)) {
            return false;
        }
        return true;
    }
}
