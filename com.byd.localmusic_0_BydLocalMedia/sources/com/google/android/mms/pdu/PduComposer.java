package com.google.android.mms.pdu;

import android.content.ContentResolver;
import android.content.Context;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public class PduComposer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int END_STRING_FLAG = 0;
    private static final int LENGTH_QUOTE = 31;
    private static final int LONG_INTEGER_LENGTH_MAX = 8;
    private static final int PDU_COMPOSER_BLOCK_SIZE = 1024;
    private static final int PDU_COMPOSE_CONTENT_ERROR = 1;
    private static final int PDU_COMPOSE_FIELD_NOT_SET = 2;
    private static final int PDU_COMPOSE_FIELD_NOT_SUPPORTED = 3;
    private static final int PDU_COMPOSE_SUCCESS = 0;
    private static final int PDU_EMAIL_ADDRESS_TYPE = 2;
    private static final int PDU_IPV4_ADDRESS_TYPE = 3;
    private static final int PDU_IPV6_ADDRESS_TYPE = 4;
    private static final int PDU_PHONE_NUMBER_ADDRESS_TYPE = 1;
    private static final int PDU_UNKNOWN_ADDRESS_TYPE = 5;
    private static final int QUOTED_STRING_FLAG = 34;
    static final String REGEXP_EMAIL_ADDRESS_TYPE = "[a-zA-Z| ]*\\<{0,1}[a-zA-Z| ]+@{1}[a-zA-Z| ]+\\.{1}[a-zA-Z| ]+\\>{0,1}";
    static final String REGEXP_IPV4_ADDRESS_TYPE = "[0-9]{1,3}\\.{1}[0-9]{1,3}\\.{1}[0-9]{1,3}\\.{1}[0-9]{1,3}";
    static final String REGEXP_IPV6_ADDRESS_TYPE = "[a-fA-F]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}";
    static final String REGEXP_PHONE_NUMBER_ADDRESS_TYPE = "\\+?[0-9|\\.|\\-]+";
    private static final int SHORT_INTEGER_MAX = 127;
    static final String STRING_IPV4_ADDRESS_TYPE = "/TYPE=IPV4";
    static final String STRING_IPV6_ADDRESS_TYPE = "/TYPE=IPV6";
    static final String STRING_PHONE_NUMBER_ADDRESS_TYPE = "/TYPE=PLMN";
    private static final int TEXT_MAX = 127;
    private static HashMap<String, Integer> mContentTypeMap = new HashMap<>();
    protected ByteArrayOutputStream mMessage;
    private GenericPdu mPdu;
    private PduHeaders mPduHeader;
    protected int mPosition;
    private final ContentResolver mResolver;
    private BufferStack mStack;

    static {
        for (int i = 0; i < PduContentTypes.contentTypes.length; i++) {
            mContentTypeMap.put(PduContentTypes.contentTypes[i], Integer.valueOf(i));
        }
    }

    public PduComposer(Context context, GenericPdu genericPdu) {
        this.mMessage = null;
        this.mPdu = null;
        this.mPosition = 0;
        this.mStack = null;
        this.mPduHeader = null;
        this.mPdu = genericPdu;
        this.mResolver = context.getContentResolver();
        this.mPduHeader = genericPdu.getPduHeaders();
        this.mStack = new BufferStack();
        this.mMessage = new ByteArrayOutputStream();
        this.mPosition = 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] make() {
        /*
            r3 = this;
            com.google.android.mms.pdu.GenericPdu r0 = r3.mPdu
            int r0 = r0.getMessageType()
            r1 = 128(0x80, float:1.8E-43)
            r2 = 0
            if (r0 == r1) goto L28
            r1 = 135(0x87, float:1.89E-43)
            if (r0 == r1) goto L21
            switch(r0) {
                case 131: goto L1a;
                case 132: goto L28;
                case 133: goto L13;
                default: goto L12;
            }
        L12:
            return r2
        L13:
            int r0 = r3.makeAckInd()
            if (r0 == 0) goto L2f
            return r2
        L1a:
            int r0 = r3.makeNotifyResp()
            if (r0 == 0) goto L2f
            return r2
        L21:
            int r0 = r3.makeReadRecInd()
            if (r0 == 0) goto L2f
            return r2
        L28:
            int r0 = r3.makeSendRetrievePdu(r0)
            if (r0 == 0) goto L2f
            return r2
        L2f:
            java.io.ByteArrayOutputStream r0 = r3.mMessage
            byte[] r0 = r0.toByteArray()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.mms.pdu.PduComposer.make():byte[]");
    }

    protected void arraycopy(byte[] bArr, int i, int i2) {
        this.mMessage.write(bArr, i, i2);
        this.mPosition += i2;
    }

    protected void append(int i) {
        this.mMessage.write(i);
        this.mPosition++;
    }

    protected void appendShortInteger(int i) {
        append((i | 128) & 255);
    }

    protected void appendOctet(int i) {
        append(i);
    }

    protected void appendShortLength(int i) {
        append(i);
    }

    protected void appendLongInteger(long j) {
        long j2 = j;
        int i = 0;
        while (j2 != 0 && i < 8) {
            j2 >>>= 8;
            i++;
        }
        appendShortLength(i);
        int i2 = (i - 1) * 8;
        for (int i3 = 0; i3 < i; i3++) {
            append((int) ((j >>> i2) & 255));
            i2 -= 8;
        }
    }

    protected void appendTextString(byte[] bArr) {
        if ((bArr[0] & 255) > 127) {
            append(127);
        }
        arraycopy(bArr, 0, bArr.length);
        append(0);
    }

    protected void appendTextString(String str) {
        appendTextString(str.getBytes());
    }

    protected void appendEncodedString(EncodedStringValue encodedStringValue) {
        int characterSet = encodedStringValue.getCharacterSet();
        byte[] textString = encodedStringValue.getTextString();
        if (textString == null) {
            return;
        }
        this.mStack.newbuf();
        PositionMarker positionMarkerMark = this.mStack.mark();
        appendShortInteger(characterSet);
        appendTextString(textString);
        int length = positionMarkerMark.getLength();
        this.mStack.pop();
        appendValueLength(length);
        this.mStack.copy();
    }

    protected void appendUintvarInteger(long j) {
        int i = 0;
        long j2 = 127;
        while (i < 5 && j >= j2) {
            j2 = (j2 << 7) | 127;
            i++;
        }
        while (i > 0) {
            append((int) ((((j >>> (i * 7)) & 127) | 128) & 255));
            i--;
        }
        append((int) (j & 127));
    }

    protected void appendDateValue(long j) {
        appendLongInteger(j);
    }

    protected void appendValueLength(long j) {
        if (j < 31) {
            appendShortLength((int) j);
        } else {
            append(31);
            appendUintvarInteger(j);
        }
    }

    protected void appendQuotedString(byte[] bArr) {
        append(34);
        arraycopy(bArr, 0, bArr.length);
        append(0);
    }

    protected void appendQuotedString(String str) {
        appendQuotedString(str.getBytes());
    }

    private EncodedStringValue appendAddressType(EncodedStringValue encodedStringValue) {
        try {
            int iCheckAddressType = checkAddressType(encodedStringValue.getString());
            EncodedStringValue encodedStringValueCopy = EncodedStringValue.copy(encodedStringValue);
            if (1 == iCheckAddressType) {
                encodedStringValueCopy.appendTextString(STRING_PHONE_NUMBER_ADDRESS_TYPE.getBytes());
            } else if (3 == iCheckAddressType) {
                encodedStringValueCopy.appendTextString(STRING_IPV4_ADDRESS_TYPE.getBytes());
            } else if (4 == iCheckAddressType) {
                encodedStringValueCopy.appendTextString(STRING_IPV6_ADDRESS_TYPE.getBytes());
            }
            return encodedStringValueCopy;
        } catch (NullPointerException unused) {
            return null;
        }
    }

    private int appendHeader(int i) {
        switch (i) {
            case 129:
            case 130:
            case 151:
                EncodedStringValue[] encodedStringValues = this.mPduHeader.getEncodedStringValues(i);
                if (encodedStringValues == null) {
                    return 2;
                }
                for (EncodedStringValue encodedStringValue : encodedStringValues) {
                    EncodedStringValue encodedStringValueAppendAddressType = appendAddressType(encodedStringValue);
                    if (encodedStringValueAppendAddressType == null) {
                        return 1;
                    }
                    appendOctet(i);
                    appendEncodedString(encodedStringValueAppendAddressType);
                }
                return 0;
            case 131:
            case 132:
            case 135:
            case 140:
            case 142:
            case 146:
            case 147:
            case 148:
            default:
                return 3;
            case 133:
                long longInteger = this.mPduHeader.getLongInteger(i);
                if (-1 == longInteger) {
                    return 2;
                }
                appendOctet(i);
                appendDateValue(longInteger);
                return 0;
            case 134:
            case 143:
            case 144:
            case 145:
            case 149:
            case 153:
            case 155:
                int octet = this.mPduHeader.getOctet(i);
                if (octet == 0) {
                    return 2;
                }
                appendOctet(i);
                appendOctet(octet);
                return 0;
            case 136:
                long longInteger2 = this.mPduHeader.getLongInteger(i);
                if (-1 == longInteger2) {
                    return 2;
                }
                appendOctet(i);
                this.mStack.newbuf();
                PositionMarker positionMarkerMark = this.mStack.mark();
                append(129);
                appendLongInteger(longInteger2);
                int length = positionMarkerMark.getLength();
                this.mStack.pop();
                appendValueLength(length);
                this.mStack.copy();
                return 0;
            case 137:
                appendOctet(i);
                EncodedStringValue encodedStringValue2 = this.mPduHeader.getEncodedStringValue(i);
                if (encodedStringValue2 == null || TextUtils.isEmpty(encodedStringValue2.getString()) || new String(encodedStringValue2.getTextString()).equals(PduHeaders.FROM_INSERT_ADDRESS_TOKEN_STR)) {
                    append(1);
                    append(129);
                } else {
                    this.mStack.newbuf();
                    PositionMarker positionMarkerMark2 = this.mStack.mark();
                    append(128);
                    EncodedStringValue encodedStringValueAppendAddressType2 = appendAddressType(encodedStringValue2);
                    if (encodedStringValueAppendAddressType2 == null) {
                        return 1;
                    }
                    appendEncodedString(encodedStringValueAppendAddressType2);
                    int length2 = positionMarkerMark2.getLength();
                    this.mStack.pop();
                    appendValueLength(length2);
                    this.mStack.copy();
                }
                return 0;
            case 138:
                byte[] textString = this.mPduHeader.getTextString(i);
                if (textString == null) {
                    return 2;
                }
                appendOctet(i);
                if (Arrays.equals(textString, PduHeaders.MESSAGE_CLASS_ADVERTISEMENT_STR.getBytes())) {
                    appendOctet(129);
                } else if (Arrays.equals(textString, "auto".getBytes())) {
                    appendOctet(131);
                } else if (Arrays.equals(textString, PduHeaders.MESSAGE_CLASS_PERSONAL_STR.getBytes())) {
                    appendOctet(128);
                } else if (Arrays.equals(textString, PduHeaders.MESSAGE_CLASS_INFORMATIONAL_STR.getBytes())) {
                    appendOctet(130);
                } else {
                    appendTextString(textString);
                }
                return 0;
            case 139:
            case 152:
                byte[] textString2 = this.mPduHeader.getTextString(i);
                if (textString2 == null) {
                    return 2;
                }
                appendOctet(i);
                appendTextString(textString2);
                return 0;
            case 141:
                appendOctet(i);
                int octet2 = this.mPduHeader.getOctet(i);
                if (octet2 == 0) {
                    appendShortInteger(18);
                } else {
                    appendShortInteger(octet2);
                }
                return 0;
            case 150:
            case 154:
                EncodedStringValue encodedStringValue3 = this.mPduHeader.getEncodedStringValue(i);
                if (encodedStringValue3 == null) {
                    return 2;
                }
                appendOctet(i);
                appendEncodedString(encodedStringValue3);
                return 0;
        }
    }

    private int makeReadRecInd() {
        if (this.mMessage == null) {
            this.mMessage = new ByteArrayOutputStream();
            this.mPosition = 0;
        }
        appendOctet(140);
        appendOctet(135);
        if (appendHeader(141) != 0 || appendHeader(139) != 0 || appendHeader(151) != 0 || appendHeader(137) != 0) {
            return 1;
        }
        appendHeader(133);
        return appendHeader(155) != 0 ? 1 : 0;
    }

    private int makeNotifyResp() {
        if (this.mMessage == null) {
            this.mMessage = new ByteArrayOutputStream();
            this.mPosition = 0;
        }
        appendOctet(140);
        appendOctet(131);
        return (appendHeader(152) == 0 && appendHeader(141) == 0 && appendHeader(149) == 0) ? 0 : 1;
    }

    private int makeAckInd() {
        if (this.mMessage == null) {
            this.mMessage = new ByteArrayOutputStream();
            this.mPosition = 0;
        }
        appendOctet(140);
        appendOctet(133);
        if (appendHeader(152) != 0 || appendHeader(141) != 0) {
            return 1;
        }
        appendHeader(145);
        return 0;
    }

    private int makeSendRetrievePdu(int i) {
        if (this.mMessage == null) {
            this.mMessage = new ByteArrayOutputStream();
            this.mPosition = 0;
        }
        appendOctet(140);
        appendOctet(i);
        appendOctet(152);
        byte[] textString = this.mPduHeader.getTextString(152);
        if (textString == null) {
            throw new IllegalArgumentException("Transaction-ID is null.");
        }
        appendTextString(textString);
        if (appendHeader(141) != 0) {
            return 1;
        }
        appendHeader(133);
        if (appendHeader(137) != 0) {
            return 1;
        }
        boolean z = appendHeader(151) != 1;
        if (appendHeader(130) != 1) {
            z = true;
        }
        if (appendHeader(129) != 1) {
            z = true;
        }
        if (!z) {
            return 1;
        }
        appendHeader(150);
        appendHeader(138);
        appendHeader(136);
        appendHeader(143);
        appendHeader(134);
        appendHeader(144);
        if (i == 132) {
            appendHeader(153);
            appendHeader(154);
        }
        appendOctet(132);
        return makeMessageBody(i);
    }

    private int makeMessageBody(int i) {
        PduBody body;
        int length;
        this.mStack.newbuf();
        PositionMarker positionMarkerMark = this.mStack.mark();
        Integer num = mContentTypeMap.get(new String(this.mPduHeader.getTextString(132)));
        if (num == null) {
            return 1;
        }
        appendShortInteger(num.intValue());
        if (i == 132) {
            body = ((RetrieveConf) this.mPdu).getBody();
        } else {
            body = ((SendReq) this.mPdu).getBody();
        }
        PduBody pduBody = body;
        if (pduBody == null || pduBody.getPartsNum() == 0) {
            appendUintvarInteger(0L);
            this.mStack.pop();
            this.mStack.copy();
            return 0;
        }
        byte b = 62;
        try {
            PduPart part = pduBody.getPart(0);
            byte[] contentId = part.getContentId();
            if (contentId != null) {
                appendOctet(138);
                if (60 == contentId[0] && 62 == contentId[contentId.length - 1]) {
                    appendTextString(contentId);
                } else {
                    appendTextString("<" + new String(contentId) + ">");
                }
            }
            appendOctet(137);
            appendTextString(part.getContentType());
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        int length2 = positionMarkerMark.getLength();
        this.mStack.pop();
        appendValueLength(length2);
        this.mStack.copy();
        int partsNum = pduBody.getPartsNum();
        appendUintvarInteger(partsNum);
        int i2 = 0;
        while (i2 < partsNum) {
            PduPart part2 = pduBody.getPart(i2);
            this.mStack.newbuf();
            PositionMarker positionMarkerMark2 = this.mStack.mark();
            this.mStack.newbuf();
            PositionMarker positionMarkerMark3 = this.mStack.mark();
            byte[] contentType = part2.getContentType();
            if (contentType == null) {
                return 1;
            }
            Integer num2 = mContentTypeMap.get(new String(contentType));
            if (num2 == null) {
                appendTextString(contentType);
            } else {
                appendShortInteger(num2.intValue());
            }
            byte[] name = part2.getName();
            if (name == null && (name = part2.getFilename()) == null && (name = part2.getContentLocation()) == null) {
                return 1;
            }
            appendOctet(133);
            appendTextString(name);
            int charset = part2.getCharset();
            if (charset != 0) {
                appendOctet(129);
                appendShortInteger(charset);
            }
            int length3 = positionMarkerMark3.getLength();
            this.mStack.pop();
            appendValueLength(length3);
            this.mStack.copy();
            byte[] contentId2 = part2.getContentId();
            if (contentId2 != null) {
                appendOctet(192);
                if (60 == contentId2[0] && b == contentId2[contentId2.length - 1]) {
                    appendQuotedString(contentId2);
                } else {
                    appendQuotedString("<" + new String(contentId2) + ">");
                }
            }
            byte[] contentLocation = part2.getContentLocation();
            if (contentLocation != null) {
                appendOctet(142);
                appendTextString(contentLocation);
            }
            int length4 = positionMarkerMark2.getLength();
            byte[] data = part2.getData();
            if (data != null) {
                arraycopy(data, 0, data.length);
                length = data.length;
            } else {
                InputStream inputStreamOpenInputStream = null;
                try {
                    byte[] bArr = new byte[1024];
                    inputStreamOpenInputStream = this.mResolver.openInputStream(part2.getDataUri());
                    length = 0;
                    while (true) {
                        int i3 = inputStreamOpenInputStream.read(bArr);
                        if (i3 == -1) {
                            break;
                        }
                        this.mMessage.write(bArr, 0, i3);
                        this.mPosition += i3;
                        length += i3;
                    }
                    if (inputStreamOpenInputStream != null) {
                        try {
                            inputStreamOpenInputStream.close();
                        } catch (IOException unused) {
                        }
                    }
                } catch (FileNotFoundException unused2) {
                    if (inputStreamOpenInputStream != null) {
                        try {
                            inputStreamOpenInputStream.close();
                        } catch (IOException unused3) {
                        }
                    }
                    return 1;
                } catch (IOException unused4) {
                    if (inputStreamOpenInputStream != null) {
                        try {
                            inputStreamOpenInputStream.close();
                        } catch (IOException unused5) {
                        }
                    }
                    return 1;
                } catch (RuntimeException unused6) {
                    if (inputStreamOpenInputStream != null) {
                        try {
                            inputStreamOpenInputStream.close();
                        } catch (IOException unused7) {
                        }
                    }
                    return 1;
                } catch (Throwable th) {
                    if (inputStreamOpenInputStream != null) {
                        try {
                            inputStreamOpenInputStream.close();
                        } catch (IOException unused8) {
                        }
                    }
                    throw th;
                }
            }
            if (length != positionMarkerMark2.getLength() - length4) {
                throw new RuntimeException("BUG: Length sanity check failed");
            }
            this.mStack.pop();
            appendUintvarInteger(length4);
            appendUintvarInteger(length);
            this.mStack.copy();
            i2++;
            b = 62;
        }
        return 0;
    }

    private static class LengthRecordNode {
        ByteArrayOutputStream currentMessage;
        public int currentPosition;
        public LengthRecordNode next;

        private LengthRecordNode() {
            this.currentMessage = null;
            this.currentPosition = 0;
            this.next = null;
        }
    }

    private class PositionMarker {
        private int c_pos;
        private int currentStackSize;

        private PositionMarker() {
        }

        int getLength() {
            if (this.currentStackSize != PduComposer.this.mStack.stackSize) {
                throw new RuntimeException("BUG: Invalid call to getLength()");
            }
            return PduComposer.this.mPosition - this.c_pos;
        }
    }

    private class BufferStack {
        private LengthRecordNode stack;
        int stackSize;
        private LengthRecordNode toCopy;

        private BufferStack() {
            this.stack = null;
            this.toCopy = null;
            this.stackSize = 0;
        }

        void newbuf() {
            if (this.toCopy != null) {
                throw new RuntimeException("BUG: Invalid newbuf() before copy()");
            }
            LengthRecordNode lengthRecordNode = new LengthRecordNode();
            lengthRecordNode.currentMessage = PduComposer.this.mMessage;
            lengthRecordNode.currentPosition = PduComposer.this.mPosition;
            lengthRecordNode.next = this.stack;
            this.stack = lengthRecordNode;
            this.stackSize++;
            PduComposer.this.mMessage = new ByteArrayOutputStream();
            PduComposer.this.mPosition = 0;
        }

        void pop() {
            ByteArrayOutputStream byteArrayOutputStream = PduComposer.this.mMessage;
            int i = PduComposer.this.mPosition;
            PduComposer.this.mMessage = this.stack.currentMessage;
            PduComposer.this.mPosition = this.stack.currentPosition;
            LengthRecordNode lengthRecordNode = this.stack;
            this.toCopy = lengthRecordNode;
            this.stack = lengthRecordNode.next;
            this.stackSize--;
            this.toCopy.currentMessage = byteArrayOutputStream;
            this.toCopy.currentPosition = i;
        }

        void copy() {
            PduComposer.this.arraycopy(this.toCopy.currentMessage.toByteArray(), 0, this.toCopy.currentPosition);
            this.toCopy = null;
        }

        PositionMarker mark() {
            PositionMarker positionMarker = new PositionMarker();
            positionMarker.c_pos = PduComposer.this.mPosition;
            positionMarker.currentStackSize = this.stackSize;
            return positionMarker;
        }
    }

    protected static int checkAddressType(String str) {
        if (str == null) {
            return 5;
        }
        if (str.matches(REGEXP_IPV4_ADDRESS_TYPE)) {
            return 3;
        }
        if (str.matches(REGEXP_PHONE_NUMBER_ADDRESS_TYPE)) {
            return 1;
        }
        if (str.matches(REGEXP_EMAIL_ADDRESS_TYPE)) {
            return 2;
        }
        return str.matches(REGEXP_IPV6_ADDRESS_TYPE) ? 4 : 5;
    }
}
