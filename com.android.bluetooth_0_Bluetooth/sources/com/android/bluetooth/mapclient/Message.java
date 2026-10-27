package com.android.bluetooth.mapclient;

import com.android.bluetooth.map.BluetoothMapContentObserver;
import com.android.bluetooth.mapapi.BluetoothMapContract;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class Message {
    private final int mAttachmentSize;
    private final Date mDateTime;
    private final String mHandle;
    private final boolean mPriority;
    private final boolean mProtected;
    private final boolean mRead;
    private final ReceptionStatus mReceptionStatus;
    private final String mRecipientAddressing;
    private final String mRecipientName;
    private final String mReplytoAddressing;
    private final String mSenderAddressing;
    private final String mSenderName;
    private final boolean mSent;
    private final int mSize;
    private final String mSubject;
    private final boolean mText;
    private final Type mType;

    public enum ReceptionStatus {
        UNKNOWN,
        COMPLETE,
        FRACTIONED,
        NOTIFICATION
    }

    public enum Type {
        UNKNOWN,
        EMAIL,
        SMS_GSM,
        SMS_CDMA,
        MMS
    }

    Message(HashMap<String, String> attrs) throws IllegalArgumentException {
        int size;
        int size2;
        try {
            new BigInteger(attrs.get("handle"), 16);
            this.mHandle = attrs.get("handle");
            this.mSubject = attrs.get(BluetoothMapContract.MessageColumns.SUBJECT);
            String dateTime = attrs.get("datetime");
            if (dateTime != null) {
                this.mDateTime = new ObexTime(dateTime).getTime();
            } else {
                this.mDateTime = null;
            }
            this.mSenderName = attrs.get("sender_name");
            this.mSenderAddressing = attrs.get("sender_addressing");
            this.mReplytoAddressing = attrs.get("replyto_addressing");
            this.mRecipientName = attrs.get("recipient_name");
            this.mRecipientAddressing = attrs.get("recipient_addressing");
            this.mType = strToType(attrs.get(BluetoothMapContentObserver.EXTRA_MESSAGE_SENT_MSG_TYPE));
            try {
                size = Integer.parseInt(attrs.get("size"));
            } catch (NumberFormatException e) {
                size = 0;
            }
            this.mSize = size;
            this.mText = yesnoToBoolean(attrs.get(BluetoothMapContract.MessagePartColumns.TEXT));
            this.mReceptionStatus = strToReceptionStatus(attrs.get("reception_status"));
            try {
                size2 = Integer.parseInt(attrs.get(BluetoothMapContract.MessageColumns.ATTACHMENT_SIZE));
            } catch (NumberFormatException e2) {
                size2 = 0;
            }
            this.mAttachmentSize = size2;
            this.mPriority = yesnoToBoolean(attrs.get(BluetoothMapContract.PresenceColumns.PRIORITY));
            this.mRead = yesnoToBoolean(attrs.get(BluetoothMapContract.FILTER_READ_STATUS));
            this.mSent = yesnoToBoolean(attrs.get("sent"));
            this.mProtected = yesnoToBoolean(attrs.get("protected"));
        } catch (NumberFormatException e3) {
            throw new IllegalArgumentException(e3);
        }
    }

    private boolean yesnoToBoolean(String yesno) {
        return "yes".equals(yesno);
    }

    private Type strToType(String s) {
        if ("EMAIL".equals(s)) {
            return Type.EMAIL;
        }
        if ("SMS_GSM".equals(s)) {
            return Type.SMS_GSM;
        }
        if ("SMS_CDMA".equals(s)) {
            return Type.SMS_CDMA;
        }
        if ("MMS".equals(s)) {
            return Type.MMS;
        }
        return Type.UNKNOWN;
    }

    private ReceptionStatus strToReceptionStatus(String s) {
        if (BluetoothMapContract.RECEPTION_STATE_COMPLETE.equals(s)) {
            return ReceptionStatus.COMPLETE;
        }
        if (BluetoothMapContract.RECEPTION_STATE_FRACTIONED.equals(s)) {
            return ReceptionStatus.FRACTIONED;
        }
        if (BluetoothMapContract.RECEPTION_STATE_NOTIFICATION.equals(s)) {
            return ReceptionStatus.NOTIFICATION;
        }
        return ReceptionStatus.UNKNOWN;
    }

    public String toString() {
        JSONObject json = new JSONObject();
        try {
            json.put("handle", this.mHandle);
            json.put(BluetoothMapContract.MessageColumns.SUBJECT, this.mSubject);
            json.put("datetime", this.mDateTime);
            json.put("sender_name", this.mSenderName);
            json.put("sender_addressing", this.mSenderAddressing);
            json.put("replyto_addressing", this.mReplytoAddressing);
            json.put("recipient_name", this.mRecipientName);
            json.put("recipient_addressing", this.mRecipientAddressing);
            json.put(BluetoothMapContentObserver.EXTRA_MESSAGE_SENT_MSG_TYPE, this.mType);
            json.put("size", this.mSize);
            json.put(BluetoothMapContract.MessagePartColumns.TEXT, this.mText);
            json.put("reception_status", this.mReceptionStatus);
            json.put(BluetoothMapContract.MessageColumns.ATTACHMENT_SIZE, this.mAttachmentSize);
            json.put(BluetoothMapContract.PresenceColumns.PRIORITY, this.mPriority);
            json.put(BluetoothMapContract.FILTER_READ_STATUS, this.mRead);
            json.put("sent", this.mSent);
            json.put("protected", this.mProtected);
        } catch (JSONException e) {
        }
        return json.toString();
    }

    public String getHandle() {
        return this.mHandle;
    }

    public String getSubject() {
        return this.mSubject;
    }

    public Date getDateTime() {
        return this.mDateTime;
    }

    public String getSenderName() {
        return this.mSenderName;
    }

    public String getSenderAddressing() {
        return this.mSenderAddressing;
    }

    public String getReplytoAddressing() {
        return this.mReplytoAddressing;
    }

    public String getRecipientName() {
        return this.mRecipientName;
    }

    public String getRecipientAddressing() {
        return this.mRecipientAddressing;
    }

    public Type getType() {
        return this.mType;
    }

    public int getSize() {
        return this.mSize;
    }

    public ReceptionStatus getReceptionStatus() {
        return this.mReceptionStatus;
    }

    public int getAttachmentSize() {
        return this.mAttachmentSize;
    }

    public boolean isText() {
        return this.mText;
    }

    public boolean isPriority() {
        return this.mPriority;
    }

    public boolean isRead() {
        return this.mRead;
    }

    public boolean isSent() {
        return this.mSent;
    }

    public boolean isProtected() {
        return this.mProtected;
    }
}
