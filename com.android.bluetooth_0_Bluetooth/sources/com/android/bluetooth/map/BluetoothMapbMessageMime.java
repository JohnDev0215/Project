package com.android.bluetooth.map;

import android.net.INetd;
import android.text.util.Rfc822Token;
import android.text.util.Rfc822Tokenizer;
import android.util.Base64;
import android.util.Log;
import com.android.bluetooth.mapapi.BluetoothMapContract;
import com.android.vcard.VCardBuilder;
import com.android.vcard.VCardConstants;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMapbMessageMime extends BluetoothMapbMessage {
    private boolean mIncludeAttachments;
    private long mDate = -1;
    private String mSubject = null;
    private ArrayList<Rfc822Token> mFrom = null;
    private ArrayList<Rfc822Token> mSender = null;
    private ArrayList<Rfc822Token> mTo = null;
    private ArrayList<Rfc822Token> mCc = null;
    private ArrayList<Rfc822Token> mBcc = null;
    private ArrayList<Rfc822Token> mReplyTo = null;
    private String mMessageId = null;
    private ArrayList<MimePart> mParts = null;
    private String mContentType = null;
    private String mBoundary = null;
    private boolean mTextonly = false;
    private boolean mHasHeaders = false;
    private String mMyEncoding = null;

    public static class MimePart {
        public long mId = -1;
        public String mContentType = null;
        public String mContentId = null;
        public String mContentLocation = null;
        public String mContentDisposition = null;
        public String mPartName = null;
        public String mCharsetName = null;
        public String mFileName = null;
        public byte[] mData = null;

        String getDataAsString() {
            String charset;
            String charset2 = this.mCharsetName;
            if (charset2 == null) {
                charset = "UTF-8";
            } else {
                charset = charset2.toUpperCase();
                try {
                    if (!Charset.isSupported(charset)) {
                        charset = "UTF-8";
                    }
                } catch (IllegalCharsetNameException e) {
                    Log.w("BluetoothMapbMessage", "Received unknown charset: " + charset + " - using UTF-8.");
                    charset = "UTF-8";
                }
            }
            try {
                String result = new String(this.mData, charset);
                return result;
            } catch (UnsupportedEncodingException e2) {
                try {
                    String result2 = new String(this.mData, "UTF-8");
                    return result2;
                } catch (UnsupportedEncodingException e3) {
                    Log.e("BluetoothMapbMessage", "getDataAsString: " + e2);
                    return null;
                }
            }
        }

        public void encode(StringBuilder sb, String boundaryTag, boolean last) throws UnsupportedEncodingException {
            sb.append("--");
            sb.append(boundaryTag);
            sb.append(VCardBuilder.VCARD_END_OF_LINE);
            if (this.mContentType != null) {
                sb.append("Content-Type: ");
                sb.append(this.mContentType);
            }
            if (this.mCharsetName != null) {
                sb.append("; ");
                sb.append("charset=\"");
                sb.append(this.mCharsetName);
                sb.append("\"");
            }
            sb.append(VCardBuilder.VCARD_END_OF_LINE);
            if (this.mContentLocation != null) {
                sb.append("Content-Location: ");
                sb.append(this.mContentLocation);
                sb.append(VCardBuilder.VCARD_END_OF_LINE);
            }
            if (this.mContentId != null) {
                sb.append("Content-ID: ");
                sb.append(this.mContentId);
                sb.append(VCardBuilder.VCARD_END_OF_LINE);
            }
            if (this.mContentDisposition != null) {
                sb.append("Content-Disposition: ");
                sb.append(this.mContentDisposition);
                sb.append(VCardBuilder.VCARD_END_OF_LINE);
            }
            if (this.mData != null) {
                String str = this.mContentType;
                if (str != null && (str.toUpperCase().contains("TEXT") || this.mContentType.toUpperCase().contains("SMIL"))) {
                    String text = new String(this.mData, "UTF-8");
                    if (text.getBytes().length == text.getBytes("UTF-8").length) {
                        sb.append("Content-Transfer-Encoding: 8BIT\r\n\r\n");
                    } else {
                        sb.append("Content-Transfer-Encoding: Quoted-Printable\r\n\r\n");
                        text = BluetoothMapUtils.encodeQuotedPrintable(this.mData);
                    }
                    sb.append(text);
                    sb.append(VCardBuilder.VCARD_END_OF_LINE);
                } else {
                    sb.append("Content-Transfer-Encoding: Base64\r\n\r\n");
                    sb.append(Base64.encodeToString(this.mData, 0));
                    sb.append(VCardBuilder.VCARD_END_OF_LINE);
                }
            }
            if (last) {
                sb.append("--");
                sb.append(boundaryTag);
                sb.append("--");
                sb.append(VCardBuilder.VCARD_END_OF_LINE);
            }
        }

        public void encodePlainText(StringBuilder sb) throws UnsupportedEncodingException {
            String str = this.mContentType;
            if (str != null && str.toUpperCase().contains("TEXT")) {
                String text = new String(this.mData, "UTF-8");
                if (text.getBytes().length != text.getBytes("UTF-8").length) {
                    text = BluetoothMapUtils.encodeQuotedPrintable(this.mData);
                }
                sb.append(text);
                sb.append(VCardBuilder.VCARD_END_OF_LINE);
                return;
            }
            String str2 = this.mContentType;
            if (str2 == null || !str2.toUpperCase().contains("/SMIL")) {
                if (this.mPartName != null) {
                    sb.append("<");
                    sb.append(this.mPartName);
                    sb.append(">\r\n");
                } else {
                    sb.append("<");
                    sb.append("attachment");
                    sb.append(">\r\n");
                }
            }
        }
    }

    private String getBoundary() {
        if (this.mBoundary == null) {
            this.mBoundary = "--=_" + UUID.randomUUID();
        }
        return this.mBoundary;
    }

    public ArrayList<MimePart> getMimeParts() {
        return this.mParts;
    }

    public String getMessageAsText() {
        StringBuilder sb = new StringBuilder();
        String str = this.mSubject;
        if (str != null && !str.isEmpty()) {
            sb.append("<Sub:");
            sb.append(this.mSubject);
            sb.append("> ");
        }
        ArrayList<MimePart> arrayList = this.mParts;
        if (arrayList != null) {
            for (MimePart part : arrayList) {
                if (part.mContentType.toUpperCase().contains("TEXT")) {
                    sb.append(new String(part.mData));
                }
            }
        }
        return sb.toString();
    }

    public MimePart addMimePart() {
        if (this.mParts == null) {
            this.mParts = new ArrayList<>();
        }
        MimePart newPart = new MimePart();
        this.mParts.add(newPart);
        return newPart;
    }

    public String getDateString() {
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
        Date dateObj = new Date(this.mDate);
        return format.format(dateObj);
    }

    public long getDate() {
        return this.mDate;
    }

    public void setDate(long date) {
        this.mDate = date;
    }

    public String getSubject() {
        return this.mSubject;
    }

    public void setSubject(String subject) {
        this.mSubject = subject;
    }

    public ArrayList<Rfc822Token> getFrom() {
        return this.mFrom;
    }

    public void setFrom(ArrayList<Rfc822Token> from) {
        this.mFrom = from;
    }

    public void addFrom(String name, String address) {
        if (this.mFrom == null) {
            this.mFrom = new ArrayList<>(1);
        }
        this.mFrom.add(new Rfc822Token(name, address, null));
    }

    public ArrayList<Rfc822Token> getSender() {
        return this.mSender;
    }

    public void setSender(ArrayList<Rfc822Token> sender) {
        this.mSender = sender;
    }

    public void addSender(String name, String address) {
        if (this.mSender == null) {
            this.mSender = new ArrayList<>(1);
        }
        this.mSender.add(new Rfc822Token(name, address, null));
    }

    public ArrayList<Rfc822Token> getTo() {
        return this.mTo;
    }

    public void setTo(ArrayList<Rfc822Token> to) {
        this.mTo = to;
    }

    public void addTo(String name, String address) {
        if (this.mTo == null) {
            this.mTo = new ArrayList<>(1);
        }
        this.mTo.add(new Rfc822Token(name, address, null));
    }

    public ArrayList<Rfc822Token> getCc() {
        return this.mCc;
    }

    public void setCc(ArrayList<Rfc822Token> cc) {
        this.mCc = cc;
    }

    public void addCc(String name, String address) {
        if (this.mCc == null) {
            this.mCc = new ArrayList<>(1);
        }
        this.mCc.add(new Rfc822Token(name, address, null));
    }

    public ArrayList<Rfc822Token> getBcc() {
        return this.mBcc;
    }

    public void setBcc(ArrayList<Rfc822Token> bcc) {
        this.mBcc = bcc;
    }

    public void addBcc(String name, String address) {
        if (this.mBcc == null) {
            this.mBcc = new ArrayList<>(1);
        }
        this.mBcc.add(new Rfc822Token(name, address, null));
    }

    public ArrayList<Rfc822Token> getReplyTo() {
        return this.mReplyTo;
    }

    public void setReplyTo(ArrayList<Rfc822Token> replyTo) {
        this.mReplyTo = replyTo;
    }

    public void addReplyTo(String name, String address) {
        if (this.mReplyTo == null) {
            this.mReplyTo = new ArrayList<>(1);
        }
        this.mReplyTo.add(new Rfc822Token(name, address, null));
    }

    public void setMessageId(String messageId) {
        this.mMessageId = messageId;
    }

    public String getMessageId() {
        return this.mMessageId;
    }

    public void setContentType(String contentType) {
        this.mContentType = contentType;
    }

    public String getContentType() {
        return this.mContentType;
    }

    public void setTextOnly(boolean textOnly) {
        this.mTextonly = textOnly;
    }

    public boolean getTextOnly() {
        return this.mTextonly;
    }

    public void setIncludeAttachments(boolean includeAttachments) {
        this.mIncludeAttachments = includeAttachments;
    }

    public boolean getIncludeAttachments() {
        return this.mIncludeAttachments;
    }

    public void updateCharset() {
        if (this.mParts != null) {
            this.mCharset = null;
            for (MimePart part : this.mParts) {
                if (part.mContentType != null && part.mContentType.toUpperCase().contains("TEXT")) {
                    this.mCharset = "UTF-8";
                    return;
                }
            }
        }
    }

    public int getSize() {
        int messageSize = 0;
        ArrayList<MimePart> arrayList = this.mParts;
        if (arrayList != null) {
            for (MimePart part : arrayList) {
                messageSize += part.mData.length;
            }
        }
        return messageSize;
    }

    public void encodeHeaderAddresses(StringBuilder sb, String headerName, ArrayList<Rfc822Token> addresses) {
        int lineLength = 0 + headerName.getBytes().length;
        sb.append(headerName);
        for (Rfc822Token address : addresses) {
            int partLength = address.toString().getBytes().length + 1;
            if (lineLength + partLength >= 998) {
                sb.append("\r\n ");
                lineLength = 0;
            }
            sb.append(address.toString());
            sb.append(";");
            lineLength += partLength;
        }
        sb.append(VCardBuilder.VCARD_END_OF_LINE);
    }

    public void encodeHeaders(StringBuilder sb) throws UnsupportedEncodingException {
        if (this.mDate != -1) {
            sb.append("Date: ");
            sb.append(getDateString());
            sb.append(VCardBuilder.VCARD_END_OF_LINE);
        }
        if (this.mSubject != null) {
            sb.append("Subject: ");
            sb.append(this.mSubject);
            sb.append(VCardBuilder.VCARD_END_OF_LINE);
        }
        if (this.mFrom == null) {
            sb.append("From: \r\n");
        }
        ArrayList<Rfc822Token> arrayList = this.mFrom;
        if (arrayList != null) {
            encodeHeaderAddresses(sb, "From: ", arrayList);
        }
        ArrayList<Rfc822Token> arrayList2 = this.mSender;
        if (arrayList2 != null) {
            encodeHeaderAddresses(sb, "Sender: ", arrayList2);
        }
        if (this.mTo == null && this.mCc == null && this.mBcc == null) {
            sb.append("To:  undisclosed-recipients:;\r\n");
        }
        ArrayList<Rfc822Token> arrayList3 = this.mTo;
        if (arrayList3 != null) {
            encodeHeaderAddresses(sb, "To: ", arrayList3);
        }
        ArrayList<Rfc822Token> arrayList4 = this.mCc;
        if (arrayList4 != null) {
            encodeHeaderAddresses(sb, "Cc: ", arrayList4);
        }
        ArrayList<Rfc822Token> arrayList5 = this.mBcc;
        if (arrayList5 != null) {
            encodeHeaderAddresses(sb, "Bcc: ", arrayList5);
        }
        ArrayList<Rfc822Token> arrayList6 = this.mReplyTo;
        if (arrayList6 != null) {
            encodeHeaderAddresses(sb, "Reply-To: ", arrayList6);
        }
        if (this.mIncludeAttachments) {
            if (this.mMessageId != null) {
                sb.append("Message-Id: ");
                sb.append(this.mMessageId);
                sb.append(VCardBuilder.VCARD_END_OF_LINE);
            }
            if (this.mContentType != null) {
                sb.append("Content-Type: ");
                sb.append(this.mContentType);
                sb.append("; boundary=");
                sb.append(getBoundary());
                sb.append(VCardBuilder.VCARD_END_OF_LINE);
            }
        }
        sb.append(VCardBuilder.VCARD_END_OF_LINE);
    }

    public byte[] encodeMime() throws UnsupportedEncodingException {
        ArrayList<byte[]> bodyFragments = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        this.mEncoding = VCardConstants.PARAM_ENCODING_8BIT;
        encodeHeaders(sb);
        if (this.mParts != null) {
            if (!getIncludeAttachments()) {
                for (MimePart part : this.mParts) {
                    part.encodePlainText(sb);
                }
            } else {
                for (MimePart part2 : this.mParts) {
                    count++;
                    part2.encode(sb, getBoundary(), count == this.mParts.size());
                }
            }
        }
        String mimeBody = sb.toString();
        if (mimeBody != null) {
            String tmpBody = mimeBody.replaceAll("END:MSG", "/END\\:MSG");
            bodyFragments.add(tmpBody.getBytes("UTF-8"));
        } else {
            bodyFragments.add(new byte[0]);
        }
        return encodeGeneric(bodyFragments);
    }

    private String parseMimeHeaders(String hdrPart) {
        int i;
        String[] headers = hdrPart.split(VCardBuilder.VCARD_END_OF_LINE);
        char c = 0;
        this.mHasHeaders = false;
        int i2 = 0;
        int c2 = headers.length;
        while (i2 < c2) {
            String header = headers[i2];
            if (!header.trim().isEmpty()) {
                int i3 = 2;
                String[] headerParts = header.split(":", 2);
                if (headerParts.length != 2) {
                    StringBuilder remaining = new StringBuilder();
                    while (i2 < c2) {
                        remaining.append(headers[i2]);
                        i2++;
                    }
                    return remaining.toString();
                }
                String headerType = headerParts[c].toUpperCase();
                String headerValue = headerParts[1].trim();
                if (headerType.contains("FROM")) {
                    Rfc822Token[] tokens = Rfc822Tokenizer.tokenize(BluetoothMapUtils.stripEncoding(headerValue));
                    this.mFrom = new ArrayList<>(Arrays.asList(tokens));
                } else if (headerType.contains("TO")) {
                    Rfc822Token[] tokens2 = Rfc822Tokenizer.tokenize(BluetoothMapUtils.stripEncoding(headerValue));
                    this.mTo = new ArrayList<>(Arrays.asList(tokens2));
                } else if (headerType.contains("CC")) {
                    Rfc822Token[] tokens3 = Rfc822Tokenizer.tokenize(BluetoothMapUtils.stripEncoding(headerValue));
                    this.mCc = new ArrayList<>(Arrays.asList(tokens3));
                } else if (headerType.contains("BCC")) {
                    Rfc822Token[] tokens4 = Rfc822Tokenizer.tokenize(BluetoothMapUtils.stripEncoding(headerValue));
                    this.mBcc = new ArrayList<>(Arrays.asList(tokens4));
                } else if (headerType.contains("REPLY-TO")) {
                    Rfc822Token[] tokens5 = Rfc822Tokenizer.tokenize(BluetoothMapUtils.stripEncoding(headerValue));
                    this.mReplyTo = new ArrayList<>(Arrays.asList(tokens5));
                } else if (headerType.contains("SUBJECT")) {
                    this.mSubject = BluetoothMapUtils.stripEncoding(headerValue);
                } else if (headerType.contains("MESSAGE-ID")) {
                    this.mMessageId = headerValue;
                } else if (!headerType.contains("DATE") && !headerType.contains("MIME-VERSION")) {
                    if (headerType.contains("CONTENT-TYPE")) {
                        String[] contentTypeParts = headerValue.split(";");
                        this.mContentType = contentTypeParts[c];
                        int j = 1;
                        int n = contentTypeParts.length;
                        while (j < n) {
                            if (contentTypeParts[j].contains("boundary")) {
                                String strTrim = contentTypeParts[j].split("boundary[\\s]*=", i3)[1].trim();
                                this.mBoundary = strTrim;
                                if (strTrim.charAt(0) == '\"') {
                                    if (this.mBoundary.charAt(r3.length() - 1) != '\"') {
                                        i = 2;
                                    } else {
                                        String str = this.mBoundary;
                                        this.mBoundary = str.substring(1, str.length() - 1);
                                        i = 2;
                                    }
                                } else {
                                    i = 2;
                                }
                            } else if (contentTypeParts[j].contains(BluetoothMapContract.MessagePartColumns.CHARSET)) {
                                i = 2;
                                this.mCharset = contentTypeParts[j].split("charset[\\s]*=", 2)[1].trim();
                            } else {
                                i = 2;
                            }
                            j++;
                            i3 = i;
                        }
                    } else if (headerType.contains("CONTENT-TRANSFER-ENCODING")) {
                        this.mMyEncoding = headerValue;
                    }
                }
            }
            i2++;
            c = 0;
        }
        return null;
    }

    private void parseMimePart(String partStr) {
        String body;
        String partEncoding;
        String partEncoding2;
        int i = 2;
        String[] parts = partStr.split("\r\n\r\n", 2);
        MimePart newPart = addMimePart();
        String partEncoding3 = this.mMyEncoding;
        char c = 0;
        String[] headers = parts[0].split(VCardBuilder.VCARD_END_OF_LINE);
        if (parts.length != 2) {
            body = partStr;
        } else {
            int length = headers.length;
            int i2 = 0;
            while (i2 < length) {
                String header = headers[i2];
                if (header.length() == 0 || header.trim().isEmpty() || header.trim().equals("--")) {
                    partEncoding = partEncoding3;
                } else {
                    String[] headerParts = header.split(":", i);
                    if (headerParts.length != i) {
                        partEncoding = partEncoding3;
                    } else {
                        String headerType = headerParts[c].toUpperCase();
                        String headerValue = headerParts[1].trim();
                        if (headerType.contains("CONTENT-TYPE")) {
                            String[] contentTypeParts = headerValue.split(";");
                            newPart.mContentType = contentTypeParts[c];
                            int j = 1;
                            int n = contentTypeParts.length;
                            while (j < n) {
                                String value = contentTypeParts[j].toLowerCase();
                                if (!value.contains(BluetoothMapContract.MessagePartColumns.CHARSET)) {
                                    partEncoding2 = partEncoding3;
                                } else {
                                    partEncoding2 = partEncoding3;
                                    newPart.mCharsetName = value.split("charset[\\s]*=", 2)[1].trim();
                                }
                                j++;
                                partEncoding3 = partEncoding2;
                            }
                            partEncoding = partEncoding3;
                        } else {
                            partEncoding = partEncoding3;
                            if (headerType.contains("CONTENT-LOCATION")) {
                                newPart.mContentLocation = headerValue;
                                newPart.mPartName = headerValue;
                            } else if (headerType.contains("CONTENT-TRANSFER-ENCODING")) {
                                partEncoding3 = headerValue;
                                i2++;
                                i = 2;
                                c = 0;
                            } else if (headerType.contains("CONTENT-ID")) {
                                newPart.mContentId = headerValue;
                            } else if (headerType.contains("CONTENT-DISPOSITION")) {
                                newPart.mContentDisposition = headerValue;
                            }
                        }
                    }
                }
                partEncoding3 = partEncoding;
                i2++;
                i = 2;
                c = 0;
            }
            String partEncoding4 = partEncoding3;
            body = parts[1];
            if (body.length() > 2 && body.charAt(body.length() - 2) == '\r' && body.charAt(body.length() - 2) == '\n') {
                body = body.substring(0, body.length() - 2);
                partEncoding3 = partEncoding4;
            } else {
                partEncoding3 = partEncoding4;
            }
        }
        newPart.mData = decodeBody(body, partEncoding3, newPart.mCharsetName);
    }

    private void parseMimeBody(String body) {
        MimePart newPart = addMimePart();
        newPart.mCharsetName = this.mCharset;
        newPart.mData = decodeBody(body, this.mMyEncoding, this.mCharset);
    }

    private byte[] decodeBody(String body, String encoding, String charset) {
        if (encoding != null && encoding.toUpperCase().contains(VCardConstants.PARAM_ENCODING_BASE64)) {
            return Base64.decode(body, 0);
        }
        if (encoding != null && encoding.toUpperCase().contains(VCardConstants.PARAM_ENCODING_QP)) {
            return BluetoothMapUtils.quotedPrintableToUtf8(body, charset);
        }
        try {
            return body.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    private void parseMime(String message) {
        String messageBody;
        if (message == null) {
            Log.e("BluetoothMapbMessage", "parseMime called with a NULL message, terminating early");
            return;
        }
        String message2 = message.replaceAll("\\r\\n[ \\\t]+", INetd.NEXTHOP_NONE);
        String[] messageParts = message2.split("\r\n\r\n", 2);
        if (messageParts.length != 2) {
            messageBody = message2;
        } else {
            String remaining = parseMimeHeaders(messageParts[0]);
            messageBody = remaining != null ? remaining + messageParts[1] : messageParts[1];
        }
        if (this.mBoundary == null) {
            parseMimeBody(messageBody);
            setTextOnly(true);
            if (this.mContentType == null) {
                this.mContentType = "text/plain";
            }
            this.mParts.get(0).mContentType = this.mContentType;
            return;
        }
        String[] mimeParts = messageBody.split("--" + this.mBoundary);
        for (int i = 1; i < mimeParts.length - 1; i++) {
            String part = mimeParts[i];
            if (part != null && part.length() > 0) {
                parseMimePart(part);
            }
        }
    }

    @Override // com.android.bluetooth.map.BluetoothMapbMessage
    public void parseMsgPart(String msgPart) {
        parseMime(msgPart);
    }

    @Override // com.android.bluetooth.map.BluetoothMapbMessage
    public void parseMsgInit() {
    }

    @Override // com.android.bluetooth.map.BluetoothMapbMessage
    public byte[] encode() throws UnsupportedEncodingException {
        return encodeMime();
    }
}
