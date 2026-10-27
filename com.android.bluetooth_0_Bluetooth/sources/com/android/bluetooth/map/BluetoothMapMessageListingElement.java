package com.android.bluetooth.map;

import android.net.INetd;
import androidx.core.app.NotificationCompat;
import com.android.bluetooth.DeviceWorkArounds;
import com.android.bluetooth.map.BluetoothMapUtils;
import com.android.bluetooth.mapapi.BluetoothMapContract;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMapMessageListingElement implements Comparable<BluetoothMapMessageListingElement> {
    private static final boolean D = false;
    private static final String TAG = "BluetoothMapMessageListingElement";
    private static final boolean V = false;
    private long mCpHandle = 0;
    private String mSubject = null;
    private long mDateTime = 0;
    private String mSenderName = null;
    private String mSenderAddressing = null;
    private String mReplytoAddressing = null;
    private String mRecipientName = null;
    private String mRecipientAddressing = null;
    private BluetoothMapUtils.TYPE mType = null;
    private boolean mMsgTypeAppParamSet = false;
    private int mSize = -1;
    private String mText = null;
    private String mReceptionStatus = null;
    private String mDeliveryStatus = null;
    private int mAttachmentSize = -1;
    private String mPriority = null;
    private boolean mRead = false;
    private String mSent = null;
    private String mProtect = null;
    private String mFolderType = null;
    private String mThreadId = null;
    private String mThreadName = null;
    private String mAttachmentMimeTypes = null;
    private boolean mReportRead = false;
    private int mCursorIndex = 0;

    public int getCursorIndex() {
        return this.mCursorIndex;
    }

    public void setCursorIndex(int cursorIndex) {
        this.mCursorIndex = cursorIndex;
    }

    public long getHandle() {
        return this.mCpHandle;
    }

    public void setHandle(long handle) {
        this.mCpHandle = handle;
    }

    public long getDateTime() {
        return this.mDateTime;
    }

    public String getDateTimeString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        Date date = new Date(this.mDateTime);
        return format.format(date);
    }

    public void setDateTime(long dateTime) {
        this.mDateTime = dateTime;
    }

    public String getSubject() {
        return this.mSubject;
    }

    public void setSubject(String subject) {
        this.mSubject = subject;
    }

    public String getSenderName() {
        return this.mSenderName;
    }

    public void setSenderName(String senderName) {
        this.mSenderName = senderName;
    }

    public String getSenderAddressing() {
        return this.mSenderAddressing;
    }

    public void setSenderAddressing(String senderAddressing) {
        this.mSenderAddressing = senderAddressing;
    }

    public String getReplyToAddressing() {
        return this.mReplytoAddressing;
    }

    public void setReplytoAddressing(String replytoAddressing) {
        this.mReplytoAddressing = replytoAddressing;
    }

    public String getRecipientName() {
        return this.mRecipientName;
    }

    public void setRecipientName(String recipientName) {
        this.mRecipientName = recipientName;
    }

    public String getRecipientAddressing() {
        return this.mRecipientAddressing;
    }

    public void setRecipientAddressing(String recipientAddressing) {
        this.mRecipientAddressing = recipientAddressing;
    }

    public BluetoothMapUtils.TYPE getType() {
        return this.mType;
    }

    public void setType(BluetoothMapUtils.TYPE type, boolean appParamSet) {
        this.mMsgTypeAppParamSet = appParamSet;
        this.mType = type;
    }

    public int getSize() {
        return this.mSize;
    }

    public void setSize(int size) {
        this.mSize = size;
    }

    public String getText() {
        return this.mText;
    }

    public void setText(String text) {
        this.mText = text;
    }

    public String getReceptionStatus() {
        return this.mReceptionStatus;
    }

    public void setReceptionStatus(String receptionStatus) {
        this.mReceptionStatus = receptionStatus;
    }

    public String getDeliveryStatus() {
        return this.mDeliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.mDeliveryStatus = deliveryStatus;
    }

    public int getAttachmentSize() {
        return this.mAttachmentSize;
    }

    public void setAttachmentSize(int attachmentSize) {
        this.mAttachmentSize = attachmentSize;
    }

    public String getAttachmentMimeTypes() {
        return this.mAttachmentMimeTypes;
    }

    public void setAttachmentMimeTypes(String attachmentMimeTypes) {
        this.mAttachmentMimeTypes = attachmentMimeTypes;
    }

    public String getPriority() {
        return this.mPriority;
    }

    public void setPriority(String priority) {
        this.mPriority = priority;
    }

    public String getRead() {
        return this.mRead ? "yes" : "no";
    }

    public boolean getReadBool() {
        return this.mRead;
    }

    public void setRead(boolean read, boolean reportRead) {
        this.mRead = read;
        this.mReportRead = reportRead;
    }

    public String getSent() {
        return this.mSent;
    }

    public void setSent(String sent) {
        this.mSent = sent;
    }

    public String getProtect() {
        return this.mProtect;
    }

    public void setProtect(String protect) {
        this.mProtect = protect;
    }

    public void setThreadId(long threadId, BluetoothMapUtils.TYPE type) {
        if (threadId != -1) {
            this.mThreadId = BluetoothMapUtils.getMapConvoHandle(threadId, type);
        }
    }

    public String getThreadName() {
        return this.mThreadName;
    }

    public void setThreadName(String name) {
        this.mThreadName = name;
    }

    public String getFolderType() {
        return this.mFolderType;
    }

    public void setFolderType(String folderType) {
        this.mFolderType = folderType;
    }

    @Override // java.lang.Comparable
    public int compareTo(BluetoothMapMessageListingElement e) {
        long j = this.mDateTime;
        long j2 = e.mDateTime;
        if (j < j2) {
            return 1;
        }
        if (j > j2) {
            return -1;
        }
        return 0;
    }

    public void encode(XmlSerializer xmlMsgElement, boolean includeThreadId) throws IllegalStateException, IOException, IllegalArgumentException {
        BluetoothMapUtils.TYPE type;
        xmlMsgElement.startTag(null, NotificationCompat.CATEGORY_MESSAGE);
        xmlMsgElement.attribute(null, "handle", BluetoothMapUtils.getMapHandle(this.mCpHandle, this.mType));
        String str = this.mSubject;
        if (str != null) {
            String stripped = BluetoothMapUtils.stripInvalidChars(str);
            if (DeviceWorkArounds.addressStartsWith(BluetoothMapService.getRemoteDevice().getAddress(), DeviceWorkArounds.MERCEDES_BENZ_CARKIT)) {
                stripped = stripped.replaceAll("[\\P{ASCII}&\"><]", INetd.NEXTHOP_NONE);
                if (stripped.isEmpty()) {
                    stripped = "---";
                }
            }
            xmlMsgElement.attribute(null, BluetoothMapContract.MessageColumns.SUBJECT, stripped.substring(0, stripped.length() < 256 ? stripped.length() : 256));
        }
        if (this.mDateTime != 0) {
            xmlMsgElement.attribute(null, "datetime", BluetoothMapUtils.getDateTimeString(getDateTime()));
        }
        String str2 = this.mSenderName;
        if (str2 != null) {
            xmlMsgElement.attribute(null, "sender_name", BluetoothMapUtils.stripInvalidChars(str2));
        }
        String str3 = this.mSenderAddressing;
        if (str3 != null) {
            xmlMsgElement.attribute(null, "sender_addressing", str3);
        }
        String str4 = this.mReplytoAddressing;
        if (str4 != null) {
            xmlMsgElement.attribute(null, "replyto_addressing", str4);
        }
        String str5 = this.mRecipientName;
        if (str5 != null) {
            xmlMsgElement.attribute(null, "recipient_name", BluetoothMapUtils.stripInvalidChars(str5));
        }
        String str6 = this.mRecipientAddressing;
        if (str6 != null) {
            xmlMsgElement.attribute(null, "recipient_addressing", str6);
        }
        if (this.mMsgTypeAppParamSet && (type = this.mType) != null) {
            xmlMsgElement.attribute(null, BluetoothMapContentObserver.EXTRA_MESSAGE_SENT_MSG_TYPE, type.name());
        }
        int i = this.mSize;
        if (i != -1) {
            xmlMsgElement.attribute(null, "size", Integer.toString(i));
        }
        String str7 = this.mText;
        if (str7 != null) {
            xmlMsgElement.attribute(null, BluetoothMapContract.MessagePartColumns.TEXT, str7);
        }
        String str8 = this.mReceptionStatus;
        if (str8 != null) {
            xmlMsgElement.attribute(null, "reception_status", str8);
        }
        String str9 = this.mDeliveryStatus;
        if (str9 != null) {
            xmlMsgElement.attribute(null, "delivery_status", str9);
        }
        int i2 = this.mAttachmentSize;
        if (i2 != -1) {
            xmlMsgElement.attribute(null, BluetoothMapContract.MessageColumns.ATTACHMENT_SIZE, Integer.toString(i2));
        }
        String str10 = this.mAttachmentMimeTypes;
        if (str10 != null) {
            xmlMsgElement.attribute(null, BluetoothMapContract.MessageColumns.ATTACHMENT_MINE_TYPES, str10);
        }
        String str11 = this.mPriority;
        if (str11 != null) {
            xmlMsgElement.attribute(null, BluetoothMapContract.PresenceColumns.PRIORITY, str11);
        }
        if (this.mReportRead) {
            xmlMsgElement.attribute(null, BluetoothMapContract.FILTER_READ_STATUS, getRead());
        }
        String str12 = this.mSent;
        if (str12 != null) {
            xmlMsgElement.attribute(null, "sent", str12);
        }
        String str13 = this.mProtect;
        if (str13 != null) {
            xmlMsgElement.attribute(null, "protected", str13);
        }
        String str14 = this.mThreadId;
        if (str14 != null && includeThreadId) {
            xmlMsgElement.attribute(null, "conversation_id", str14);
        }
        String str15 = this.mThreadName;
        if (str15 != null && includeThreadId) {
            xmlMsgElement.attribute(null, "conversation_name", str15);
        }
        String str16 = this.mFolderType;
        if (str16 != null) {
            xmlMsgElement.attribute(null, "folder_type", str16);
        }
        xmlMsgElement.endTag(null, NotificationCompat.CATEGORY_MESSAGE);
    }
}
