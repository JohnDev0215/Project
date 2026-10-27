package com.android.bluetooth.avrcpcontroller;

import android.util.Log;
import java.util.Date;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class BipAttachmentFormat {
    private static final String TAG = "avrcpcontroller.BipAttachmentFormat";
    private final String mCharset;
    private final String mContentType;
    private final BipDateTime mCreated;
    private final BipDateTime mModified;
    private final String mName;
    private final int mSize;

    public BipAttachmentFormat(String contentType, String charset, String name, String size, String created, String modified) {
        BipDateTime bipCreated;
        BipDateTime bipModified;
        if (contentType == null) {
            throw new ParseException("ContentType is required and must be valid");
        }
        if (name == null) {
            throw new ParseException("Name is required and must be valid");
        }
        this.mContentType = contentType;
        this.mName = name;
        this.mCharset = charset;
        this.mSize = parseInt(size);
        try {
            bipCreated = new BipDateTime(created);
        } catch (ParseException e) {
            bipCreated = null;
        }
        this.mCreated = bipCreated;
        try {
            bipModified = new BipDateTime(modified);
        } catch (ParseException e2) {
            bipModified = null;
        }
        this.mModified = bipModified;
    }

    public BipAttachmentFormat(String contentType, String charset, String name, int size, Date created, Date modified) {
        Objects.requireNonNull(contentType, "Content-Type cannot be null");
        this.mContentType = contentType;
        Objects.requireNonNull(name, "Name cannot be null");
        this.mName = name;
        this.mCharset = charset;
        this.mSize = size;
        this.mCreated = created != null ? new BipDateTime(created) : null;
        this.mModified = modified != null ? new BipDateTime(modified) : null;
    }

    private static int parseInt(String s) {
        if (s == null) {
            return -1;
        }
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            Log.e(TAG, "Invalid number format for '" + s + "'");
            return -1;
        }
    }

    public String getContentType() {
        return this.mContentType;
    }

    public String getName() {
        return this.mName;
    }

    public String getCharset() {
        return this.mCharset;
    }

    public int getSize() {
        return this.mSize;
    }

    public BipDateTime getCreatedDate() {
        return this.mCreated;
    }

    public BipDateTime getModifiedDate() {
        return this.mModified;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BipAttachmentFormat)) {
            return false;
        }
        BipAttachmentFormat a = (BipAttachmentFormat) o;
        return a.getContentType() == getContentType() && a.getName() == getName() && a.getCharset() == getCharset() && a.getSize() == getSize() && a.getCreatedDate() == getCreatedDate() && a.getModifiedDate() == getModifiedDate();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<attachment");
        sb.append(" content-type=\"" + this.mContentType + "\"");
        if (this.mCharset != null) {
            sb.append(" charset=\"" + this.mCharset + "\"");
        }
        sb.append(" name=\"" + this.mName + "\"");
        if (this.mSize > -1) {
            sb.append(" size=\"" + this.mSize + "\"");
        }
        if (this.mCreated != null) {
            sb.append(" created=\"" + this.mCreated.toString() + "\"");
        }
        if (this.mModified != null) {
            sb.append(" modified=\"" + this.mModified.toString() + "\"");
        }
        sb.append(" />");
        return sb.toString();
    }
}
