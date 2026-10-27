package com.android.bluetooth.map;

import android.net.INetd;
import android.util.Log;
import com.android.bluetooth.SignedLongLong;
import com.android.bluetooth.Utils;
import com.android.bluetooth.map.BluetoothMapUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMapConvoListingElement implements Comparable<BluetoothMapConvoListingElement> {
    private static final boolean D = false;
    private static final String TAG = "BluetoothMapConvoListingElement";
    private static final boolean V = false;
    private static final String XML_ATT_ID = "id";
    private static final String XML_ATT_LAST_ACTIVITY = "last_activity";
    private static final String XML_ATT_NAME = "name";
    private static final String XML_ATT_READ = "readstatus";
    private static final String XML_ATT_SUMMARY = "summary";
    private static final String XML_ATT_VERSION_COUNTER = "version_counter";
    public static final String XML_TAG_CONVERSATION = "conversation";
    private List<BluetoothMapConvoContactElement> mContacts;
    private SignedLongLong mId = null;
    private String mName = INetd.NEXTHOP_NONE;
    private long mLastActivity = -1;
    private boolean mRead = false;
    private boolean mReportRead = false;
    private long mVersionCounter = -1;
    private int mCursorIndex = 0;
    private BluetoothMapUtils.TYPE mType = null;
    private String mSummary = null;
    private String mSmsMmsContacts = null;

    public int getCursorIndex() {
        return this.mCursorIndex;
    }

    public void setCursorIndex(int cursorIndex) {
        this.mCursorIndex = cursorIndex;
    }

    public long getVersionCounter() {
        return this.mVersionCounter;
    }

    public void setVersionCounter(long vcount) {
        this.mVersionCounter = vcount;
    }

    public void incrementVersionCounter() {
        this.mVersionCounter++;
    }

    private void setVersionCounter(String vcount) {
        try {
            this.mVersionCounter = Long.parseLong(vcount);
        } catch (NumberFormatException e) {
            Log.w(TAG, "unable to parse XML versionCounter:" + vcount);
            this.mVersionCounter = -1L;
        }
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public BluetoothMapUtils.TYPE getType() {
        return this.mType;
    }

    public void setType(BluetoothMapUtils.TYPE type) {
        this.mType = type;
    }

    public List<BluetoothMapConvoContactElement> getContacts() {
        return this.mContacts;
    }

    public void setContacts(List<BluetoothMapConvoContactElement> contacts) {
        this.mContacts = contacts;
    }

    public void addContact(BluetoothMapConvoContactElement contact) {
        if (this.mContacts == null) {
            this.mContacts = new ArrayList();
        }
        this.mContacts.add(contact);
    }

    public void removeContact(BluetoothMapConvoContactElement contact) {
        this.mContacts.remove(contact);
    }

    public void removeContact(int index) {
        this.mContacts.remove(index);
    }

    public long getLastActivity() {
        return this.mLastActivity;
    }

    public String getLastActivityString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        Date date = new Date(this.mLastActivity);
        return format.format(date);
    }

    public void setLastActivity(long last) {
        this.mLastActivity = last;
    }

    public void setLastActivity(String lastActivity) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        Date date = format.parse(lastActivity);
        this.mLastActivity = date.getTime();
    }

    public String getRead() {
        if (this.mReportRead) {
            return this.mRead ? "READ" : "UNREAD";
        }
        return "UNKNOWN";
    }

    public boolean getReadBool() {
        return this.mRead;
    }

    public void setRead(boolean read, boolean reportRead) {
        this.mRead = read;
        this.mReportRead = reportRead;
    }

    private void setRead(String value) {
        if (value.trim().equalsIgnoreCase("yes")) {
            this.mRead = true;
        } else {
            this.mRead = false;
        }
        this.mReportRead = true;
    }

    public void setConvoId(long type, long threadId) {
        this.mId = new SignedLongLong(threadId, type);
    }

    public String getConvoId() {
        return this.mId.toHexString();
    }

    public long getCpConvoId() {
        return this.mId.getLeastSignificantBits();
    }

    public void setSummary(String summary) {
        this.mSummary = summary;
    }

    public String getFullSummary() {
        return this.mSummary;
    }

    private String getSummary() {
        if (this.mSummary != null) {
            try {
                return new String(BluetoothMapUtils.truncateUtf8StringToBytearray(this.mSummary, 256), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                Log.e(TAG, "Missing UTF-8 support on platform", e);
                return null;
            }
        }
        return null;
    }

    public String getSmsMmsContacts() {
        return this.mSmsMmsContacts;
    }

    public void setSmsMmsContacts(String smsMmsContacts) {
        this.mSmsMmsContacts = smsMmsContacts;
    }

    @Override // java.lang.Comparable
    public int compareTo(BluetoothMapConvoListingElement e) {
        long j = this.mLastActivity;
        long j2 = e.mLastActivity;
        if (j < j2) {
            return 1;
        }
        if (j > j2) {
            return -1;
        }
        return 0;
    }

    public void encode(XmlSerializer xmlConvoElement) throws IllegalStateException, IOException, IllegalArgumentException {
        xmlConvoElement.startTag(null, XML_TAG_CONVERSATION);
        xmlConvoElement.attribute(null, XML_ATT_ID, this.mId.toHexString());
        String str = this.mName;
        if (str != null) {
            xmlConvoElement.attribute(null, "name", BluetoothMapUtils.stripInvalidChars(str));
        }
        if (this.mLastActivity != -1) {
            xmlConvoElement.attribute(null, XML_ATT_LAST_ACTIVITY, getLastActivityString());
        }
        if (this.mReportRead) {
            xmlConvoElement.attribute(null, XML_ATT_READ, getRead());
        }
        if (this.mVersionCounter != -1) {
            xmlConvoElement.attribute(null, "version_counter", Long.toString(getVersionCounter()));
        }
        if (this.mSummary != null) {
            xmlConvoElement.attribute(null, XML_ATT_SUMMARY, getSummary());
        }
        List<BluetoothMapConvoContactElement> list = this.mContacts;
        if (list != null) {
            for (BluetoothMapConvoContactElement contact : list) {
                contact.encode(xmlConvoElement);
            }
        }
        xmlConvoElement.endTag(null, XML_TAG_CONVERSATION);
    }

    public static BluetoothMapConvoListingElement createFromXml(XmlPullParser parser) throws XmlPullParserException, IOException, ParseException {
        BluetoothMapConvoListingElement newElement = new BluetoothMapConvoListingElement();
        int count = parser.getAttributeCount();
        for (int i = 0; i < count; i++) {
            String attributeName = parser.getAttributeName(i).trim();
            String attributeValue = parser.getAttributeValue(i);
            if (attributeName.equalsIgnoreCase(XML_ATT_ID)) {
                newElement.mId = SignedLongLong.fromString(attributeValue);
            } else if (attributeName.equalsIgnoreCase("name")) {
                newElement.mName = attributeValue;
            } else if (attributeName.equalsIgnoreCase(XML_ATT_LAST_ACTIVITY)) {
                newElement.setLastActivity(attributeValue);
            } else if (attributeName.equalsIgnoreCase(XML_ATT_READ)) {
                newElement.setRead(attributeValue);
            } else if (attributeName.equalsIgnoreCase("version_counter")) {
                newElement.setVersionCounter(attributeValue);
            } else if (attributeName.equalsIgnoreCase(XML_ATT_SUMMARY)) {
                newElement.setSummary(attributeValue);
            }
        }
        while (true) {
            int type = parser.next();
            if (type == 3 || type == 1) {
                break;
            }
            if (parser.getEventType() == 2) {
                String name = parser.getName().trim();
                if (name.equalsIgnoreCase("convocontact")) {
                    newElement.addContact(BluetoothMapConvoContactElement.createFromXml(parser));
                } else {
                    Utils.skipCurrentTag(parser);
                }
            }
        }
        return newElement;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BluetoothMapConvoListingElement other = (BluetoothMapConvoListingElement) obj;
        List<BluetoothMapConvoContactElement> list = this.mContacts;
        if (list == null) {
            if (other.mContacts != null) {
                return false;
            }
        } else if (!list.equals(other.mContacts)) {
            return false;
        }
        if (this.mLastActivity != other.mLastActivity) {
            return false;
        }
        String str = this.mName;
        if (str == null) {
            if (other.mName != null) {
                return false;
            }
        } else if (!str.equals(other.mName)) {
            return false;
        }
        if (this.mRead == other.mRead) {
            return true;
        }
        return false;
    }
}
