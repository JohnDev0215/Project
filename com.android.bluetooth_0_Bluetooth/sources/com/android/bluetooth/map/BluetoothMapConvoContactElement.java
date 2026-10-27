package com.android.bluetooth.map;

import android.util.Log;
import com.android.bluetooth.SignedLongLong;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMapConvoContactElement implements Comparable<BluetoothMapConvoContactElement> {
    public static final long CONTACT_ID_TYPE_EMAIL = 2;
    public static final long CONTACT_ID_TYPE_IM = 3;
    public static final long CONTACT_ID_TYPE_SMS_MMS = 1;
    private static final boolean D = false;
    private static final String TAG = "BluetoothMapConvoContactElement";
    private static final boolean V = false;
    private static final String XML_ATT_CHAT_STATE = "chat_state";
    private static final String XML_ATT_DISPLAY_NAME = "display_name";
    private static final String XML_ATT_LAST_ACTIVITY = "last_activity";
    private static final String XML_ATT_NAME = "name";
    private static final String XML_ATT_PRESENCE_AVAILABILITY = "presence_availability";
    private static final String XML_ATT_PRESENCE_STATUS = "presence_status";
    private static final String XML_ATT_PRIORITY = "priority";
    private static final String XML_ATT_UCI = "x_bt_uci";
    private static final String XML_ATT_X_BT_UID = "x_bt_uid";
    protected static final String XML_TAG_CONVOCONTACT = "convocontact";
    private SignedLongLong mBtUid;
    private int mChatState;
    private String mDisplayName;
    private long mLastActivity;
    private String mName;
    private int mPresenceAvailability;
    private String mPresenceStatus;
    private int mPriority;
    private String mUci;

    public static BluetoothMapConvoContactElement createFromMapContact(MapContact contact, String address) {
        BluetoothMapConvoContactElement newElement = new BluetoothMapConvoContactElement();
        newElement.mUci = address;
        newElement.mBtUid = new SignedLongLong(contact.getId(), 0L);
        newElement.mDisplayName = contact.getName();
        return newElement;
    }

    public BluetoothMapConvoContactElement(String uci, String name, String displayName, String presenceStatus, int presenceAvailability, long lastActivity, int chatState, int priority, String btUid) {
        this.mUci = null;
        this.mName = null;
        this.mDisplayName = null;
        this.mPresenceStatus = null;
        this.mPresenceAvailability = -1;
        this.mPriority = -1;
        this.mLastActivity = -1L;
        this.mBtUid = null;
        this.mChatState = -1;
        this.mUci = uci;
        this.mName = name;
        this.mDisplayName = displayName;
        this.mPresenceStatus = presenceStatus;
        this.mPresenceAvailability = presenceAvailability;
        this.mLastActivity = lastActivity;
        this.mChatState = chatState;
        this.mPresenceStatus = presenceStatus;
        this.mPriority = priority;
        if (btUid != null) {
            try {
                this.mBtUid = SignedLongLong.fromString(btUid);
            } catch (UnsupportedEncodingException e) {
                Log.w(TAG, e);
            }
        }
    }

    public BluetoothMapConvoContactElement() {
        this.mUci = null;
        this.mName = null;
        this.mDisplayName = null;
        this.mPresenceStatus = null;
        this.mPresenceAvailability = -1;
        this.mPriority = -1;
        this.mLastActivity = -1L;
        this.mBtUid = null;
        this.mChatState = -1;
    }

    public String getPresenceStatus() {
        return this.mPresenceStatus;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public void setDisplayName(String displayName) {
        this.mDisplayName = displayName;
    }

    public void setPresenceStatus(String presenceStatus) {
        this.mPresenceStatus = presenceStatus;
    }

    public int getPresenceAvailability() {
        return this.mPresenceAvailability;
    }

    public void setPresenceAvailability(int presenceAvailability) {
        this.mPresenceAvailability = presenceAvailability;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public void setPriority(int priority) {
        this.mPriority = priority;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getBtUid() {
        return this.mBtUid.toHexString();
    }

    public void setBtUid(SignedLongLong btUid) {
        this.mBtUid = btUid;
    }

    public int getChatState() {
        return this.mChatState;
    }

    public void setChatState(int chatState) {
        this.mChatState = chatState;
    }

    public void setChatState(String chatState) {
        this.mChatState = Integer.valueOf(chatState).intValue();
    }

    public String getLastActivityString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        Date date = new Date(this.mLastActivity);
        return format.format(date);
    }

    public void setLastActivity(long dateTime) {
        this.mLastActivity = dateTime;
    }

    public void setLastActivity(String lastActivity) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        Date date = format.parse(lastActivity);
        this.mLastActivity = date.getTime();
    }

    public void setContactId(String uci) {
        this.mUci = uci;
    }

    public String getContactId() {
        return this.mUci;
    }

    @Override // java.lang.Comparable
    public int compareTo(BluetoothMapConvoContactElement e) {
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
        xmlConvoElement.startTag(null, XML_TAG_CONVOCONTACT);
        String str = this.mUci;
        if (str != null) {
            xmlConvoElement.attribute(null, "x_bt_uci", str);
        }
        String str2 = this.mDisplayName;
        if (str2 != null) {
            xmlConvoElement.attribute(null, XML_ATT_DISPLAY_NAME, BluetoothMapUtils.stripInvalidChars(str2));
        }
        String str3 = this.mName;
        if (str3 != null) {
            xmlConvoElement.attribute(null, "name", BluetoothMapUtils.stripInvalidChars(str3));
        }
        int i = this.mChatState;
        if (i != -1) {
            xmlConvoElement.attribute(null, "chat_state", String.valueOf(i));
        }
        if (this.mLastActivity != -1) {
            xmlConvoElement.attribute(null, XML_ATT_LAST_ACTIVITY, getLastActivityString());
        }
        SignedLongLong signedLongLong = this.mBtUid;
        if (signedLongLong != null) {
            xmlConvoElement.attribute(null, "x_bt_uid", signedLongLong.toHexString());
        }
        int i2 = this.mPresenceAvailability;
        if (i2 != -1) {
            xmlConvoElement.attribute(null, XML_ATT_PRESENCE_AVAILABILITY, String.valueOf(i2));
        }
        String str4 = this.mPresenceStatus;
        if (str4 != null) {
            xmlConvoElement.attribute(null, XML_ATT_PRESENCE_STATUS, str4);
        }
        int i3 = this.mPriority;
        if (i3 != -1) {
            xmlConvoElement.attribute(null, "priority", String.valueOf(i3));
        }
        xmlConvoElement.endTag(null, XML_TAG_CONVOCONTACT);
    }

    public static BluetoothMapConvoContactElement createFromXml(XmlPullParser parser) throws XmlPullParserException, IOException, ParseException {
        int count = parser.getAttributeCount();
        if (count < 1) {
            throw new IllegalArgumentException("convocontact is not decorated with attributes");
        }
        BluetoothMapConvoContactElement newElement = new BluetoothMapConvoContactElement();
        for (int i = 0; i < count; i++) {
            String attributeName = parser.getAttributeName(i).trim();
            String attributeValue = parser.getAttributeValue(i);
            if (attributeName.equalsIgnoreCase("x_bt_uci")) {
                newElement.mUci = attributeValue;
            } else if (attributeName.equalsIgnoreCase("name")) {
                newElement.mName = attributeValue;
            } else if (attributeName.equalsIgnoreCase(XML_ATT_DISPLAY_NAME)) {
                newElement.mDisplayName = attributeValue;
            } else if (attributeName.equalsIgnoreCase("chat_state")) {
                newElement.setChatState(attributeValue);
            } else if (attributeName.equalsIgnoreCase(XML_ATT_LAST_ACTIVITY)) {
                newElement.setLastActivity(attributeValue);
            } else if (attributeName.equalsIgnoreCase("x_bt_uid")) {
                newElement.setBtUid(SignedLongLong.fromString(attributeValue));
            } else if (attributeName.equalsIgnoreCase(XML_ATT_PRESENCE_AVAILABILITY)) {
                newElement.mPresenceAvailability = Integer.parseInt(attributeValue);
            } else if (attributeName.equalsIgnoreCase(XML_ATT_PRESENCE_STATUS)) {
                newElement.setPresenceStatus(attributeValue);
            } else if (attributeName.equalsIgnoreCase("priority")) {
                newElement.setPriority(Integer.parseInt(attributeValue));
            }
        }
        parser.nextTag();
        return newElement;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BluetoothMapConvoContactElement other = (BluetoothMapConvoContactElement) obj;
        if (this.mChatState != other.mChatState) {
            return false;
        }
        String str = this.mDisplayName;
        if (str == null) {
            if (other.mDisplayName != null) {
                return false;
            }
        } else if (!str.equals(other.mDisplayName)) {
            return false;
        }
        if (this.mLastActivity != other.mLastActivity) {
            return false;
        }
        String str2 = this.mName;
        if (str2 == null) {
            if (other.mName != null) {
                return false;
            }
        } else if (!str2.equals(other.mName)) {
            return false;
        }
        if (this.mPresenceAvailability != other.mPresenceAvailability) {
            return false;
        }
        String str3 = this.mPresenceStatus;
        if (str3 == null) {
            if (other.mPresenceStatus != null) {
                return false;
            }
        } else if (!str3.equals(other.mPresenceStatus)) {
            return false;
        }
        if (this.mPriority == other.mPriority) {
            return true;
        }
        return false;
    }
}
