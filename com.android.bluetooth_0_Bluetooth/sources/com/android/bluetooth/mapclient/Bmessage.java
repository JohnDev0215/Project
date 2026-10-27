package com.android.bluetooth.mapclient;

import com.android.bluetooth.map.BluetoothMapContentObserver;
import com.android.bluetooth.mapapi.BluetoothMapContract;
import com.android.vcard.VCardEntry;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class Bmessage {
    String mBbodyCharset;
    String mBbodyEncoding;
    String mBbodyLanguage;
    int mBbodyLength;
    String mBmsgFolder;
    Status mBmsgStatus;
    Type mBmsgType;
    String mBmsgVersion;
    String mMessage;
    ArrayList<VCardEntry> mOriginators = new ArrayList<>();
    ArrayList<VCardEntry> mRecipients = new ArrayList<>();

    public enum Status {
        READ,
        UNREAD
    }

    public enum Type {
        EMAIL,
        SMS_GSM,
        SMS_CDMA,
        MMS
    }

    public VCardEntry getOriginator() {
        if (this.mOriginators.size() > 0) {
            return this.mOriginators.get(0);
        }
        return null;
    }

    public ArrayList<VCardEntry> getOriginators() {
        return this.mOriginators;
    }

    public Bmessage addOriginator(VCardEntry vcard) {
        this.mOriginators.add(vcard);
        return this;
    }

    public ArrayList<VCardEntry> getRecipients() {
        return this.mRecipients;
    }

    public Bmessage addRecipient(VCardEntry vcard) {
        this.mRecipients.add(vcard);
        return this;
    }

    public Status getStatus() {
        return this.mBmsgStatus;
    }

    public Bmessage setStatus(Status status) {
        this.mBmsgStatus = status;
        return this;
    }

    public Type getType() {
        return this.mBmsgType;
    }

    public Bmessage setType(Type type) {
        this.mBmsgType = type;
        return this;
    }

    public String getFolder() {
        return this.mBmsgFolder;
    }

    public Bmessage setFolder(String folder) {
        this.mBmsgFolder = folder;
        return this;
    }

    public String getEncoding() {
        return this.mBbodyEncoding;
    }

    public Bmessage setEncoding(String encoding) {
        this.mBbodyEncoding = encoding;
        return this;
    }

    public String getCharset() {
        return this.mBbodyCharset;
    }

    public Bmessage setCharset(String charset) {
        this.mBbodyCharset = charset;
        return this;
    }

    public String getLanguage() {
        return this.mBbodyLanguage;
    }

    public Bmessage setLanguage(String language) {
        this.mBbodyLanguage = language;
        return this;
    }

    public String getBodyContent() {
        return this.mMessage;
    }

    public Bmessage setBodyContent(String body) {
        this.mMessage = body;
        return this;
    }

    public String toString() {
        JSONObject json = new JSONObject();
        try {
            json.put("status", this.mBmsgStatus);
            json.put(BluetoothMapContentObserver.EXTRA_MESSAGE_SENT_MSG_TYPE, this.mBmsgType);
            json.put("folder", this.mBmsgFolder);
            json.put(BluetoothMapContract.MessagePartColumns.CHARSET, this.mBbodyCharset);
            json.put("message", this.mMessage);
        } catch (JSONException e) {
        }
        return json.toString();
    }
}
