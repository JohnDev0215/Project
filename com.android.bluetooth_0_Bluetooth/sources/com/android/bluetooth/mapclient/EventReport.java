package com.android.bluetooth.mapclient;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.android.bluetooth.map.BluetoothMapContentObserver;
import com.android.bluetooth.mapclient.Bmessage;
import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes.dex */
public class EventReport {
    private static final String TAG = "EventReport";
    private final String mFolder;
    private final String mHandle;
    private final Bmessage.Type mMsgType;
    private final String mOldFolder;
    private final Type mType;

    private EventReport(HashMap<String, String> attrs) throws IllegalArgumentException {
        Type type = parseType(attrs.get(BluetoothMapContentObserver.EXTRA_MESSAGE_SENT_MSG_TYPE));
        this.mType = type;
        if (type != Type.MEMORY_FULL && this.mType != Type.MEMORY_AVAILABLE) {
            String handle = attrs.get("handle");
            try {
                new BigInteger(attrs.get("handle"), 16);
                this.mHandle = attrs.get("handle");
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid value for handle:" + handle);
            }
        } else {
            this.mHandle = null;
        }
        this.mFolder = attrs.get("folder");
        this.mOldFolder = attrs.get("old_folder");
        if (this.mType != Type.MEMORY_FULL && this.mType != Type.MEMORY_AVAILABLE) {
            String s = attrs.get("msg_type");
            if (s != null && s.isEmpty()) {
                this.mMsgType = null;
                return;
            } else {
                this.mMsgType = parseMsgType(s);
                return;
            }
        }
        this.mMsgType = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.android.bluetooth.mapclient.EventReport] */
    static EventReport fromStream(DataInputStream in) throws XmlPullParserException, IOException {
        String eventReport = TAG;
        try {
            XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
            xpp.setInput(in, "utf-8");
            for (int event = xpp.getEventType(); event != 1; event = xpp.next()) {
                if (event == 2 && xpp.getName().equals(NotificationCompat.CATEGORY_EVENT)) {
                    HashMap<String, String> attrs = new HashMap<>();
                    for (int i = 0; i < xpp.getAttributeCount(); i++) {
                        attrs.put(xpp.getAttributeName(i), xpp.getAttributeValue(i));
                    }
                    eventReport = new EventReport(attrs);
                    return eventReport;
                }
            }
        } catch (IOException e) {
            Log.e(eventReport, "I/O error when parsing XML", e);
        } catch (IllegalArgumentException e2) {
            Log.e(eventReport, "Invalid event received", e2);
        } catch (XmlPullParserException e3) {
            Log.e(eventReport, "XML parser error when parsing XML", e3);
        }
        return null;
    }

    private Type parseType(String type) throws IllegalArgumentException {
        for (Type t : Type.values()) {
            if (t.toString().equals(type)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Invalid value for type: " + type);
    }

    private Bmessage.Type parseMsgType(String msgType) throws IllegalArgumentException {
        for (Bmessage.Type t : Bmessage.Type.values()) {
            if (t.name().equals(msgType)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Invalid value for msg_type: " + msgType);
    }

    public Type getType() {
        return this.mType;
    }

    public String getHandle() {
        return this.mHandle;
    }

    public String getFolder() {
        return this.mFolder;
    }

    public String getOldFolder() {
        return this.mOldFolder;
    }

    public Bmessage.Type getMsgType() {
        return this.mMsgType;
    }

    public String toString() {
        JSONObject json = new JSONObject();
        try {
            json.put(BluetoothMapContentObserver.EXTRA_MESSAGE_SENT_MSG_TYPE, this.mType);
            json.put("handle", this.mHandle);
            json.put("folder", this.mFolder);
            json.put("old_folder", this.mOldFolder);
            json.put("msg_type", this.mMsgType);
        } catch (JSONException e) {
        }
        return json.toString();
    }

    public enum Type {
        NEW_MESSAGE("NewMessage"),
        DELIVERY_SUCCESS("DeliverySuccess"),
        SENDING_SUCCESS("SendingSuccess"),
        DELIVERY_FAILURE("DeliveryFailure"),
        SENDING_FAILURE("SendingFailure"),
        MEMORY_FULL("MemoryFull"),
        MEMORY_AVAILABLE("MemoryAvailable"),
        MESSAGE_DELETED("MessageDeleted"),
        MESSAGE_SHIFT("MessageShift"),
        READ_STATUS_CHANGED("ReadStatusChanged"),
        MESSAGE_REMOVED("MessageRemoved"),
        MESSAGE_EXTENDED_DATA_CHANGED("MessageExtendedDataChanged"),
        PARTICIPANT_PRESENCE_CHANGED("ParticipantPresenceChanged"),
        PARTICIPANT_CHAT_STATE_CHANGED("ParticipantChatStateChanged"),
        CONCERSATION_CHANGED("ConversationChanged");

        private final String mSpecName;

        Type(String specName) {
            this.mSpecName = specName;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mSpecName;
        }
    }
}
