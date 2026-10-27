package com.android.bluetooth.map;

import android.content.Context;
import android.net.INetd;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.google.common.primitives.UnsignedBytes;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMapSmsPdu {
    private static final int ELT_ID_NATIONAL_LANGUAGE_LOCKING_SHIFT = 37;
    private static final int ELT_ID_NATIONAL_LANGUAGE_SINGLE_SHIFT = 36;
    private static final int INVALID_VALUE = -1;
    private static final int MESSAGE_TYPE_DELIVER = 1;
    public static final int SMS_TYPE_CDMA = 2;
    public static final int SMS_TYPE_GSM = 1;
    private static final String TAG = "BluetoothMapSmsPdu";
    private static final boolean V = false;
    private static int sConcatenatedRef = new Random().nextInt(256);

    public static class SmsPdu {
        private static final byte BEARER_DATA = 8;
        private static final byte BEARER_DATA_MSG_ID = 0;
        private static final byte BEARER_REPLY_OPTION = 6;
        private static final byte CAUSE_CODES = 7;
        private static final byte DESTINATION_ADDRESS = 4;
        private static final byte DESTINATION_SUB_ADDRESS = 5;
        private static final byte ORIGINATING_ADDRESS = 2;
        private static final byte ORIGINATING_SUB_ADDRESS = 3;
        private static final byte SERVICE_CATEGORY = 1;
        private static final byte TELESERVICE_IDENTIFIER = 0;
        private static final byte TP_MIT_DELIVER = 0;
        private static final byte TP_MMS_NO_MORE = 4;
        private static final byte TP_RP_NO_REPLY_PATH = 0;
        private static final byte TP_SRI_NO_REPORT = 0;
        private static final byte TP_UDHI_MASK = 64;
        private byte[] mData;
        private int mEncoding;
        private int mLanguageShiftTable;
        private int mLanguageTable;
        private int mMsgSeptetCount;
        private byte[] mScAddress;
        private int mType;
        private int mUserDataMsgOffset;
        private int mUserDataSeptetPadding;

        SmsPdu(byte[] data, int type) {
            this.mScAddress = new byte[]{0};
            this.mUserDataMsgOffset = 0;
            this.mUserDataSeptetPadding = -1;
            this.mMsgSeptetCount = 0;
            this.mData = data;
            this.mEncoding = -1;
            this.mType = type;
            this.mLanguageTable = -1;
            this.mLanguageShiftTable = -1;
            this.mUserDataMsgOffset = gsmSubmitGetTpUdOffset();
        }

        SmsPdu(byte[] data, int encoding, int type, int languageTable) {
            this.mScAddress = new byte[]{0};
            this.mUserDataMsgOffset = 0;
            this.mUserDataSeptetPadding = -1;
            this.mMsgSeptetCount = 0;
            this.mData = data;
            this.mEncoding = encoding;
            this.mType = type;
            this.mLanguageTable = languageTable;
        }

        public byte[] getData() {
            return this.mData;
        }

        public byte[] getScAddress() {
            return this.mScAddress;
        }

        public void setEncoding(int encoding) {
            this.mEncoding = encoding;
        }

        public int getEncoding() {
            return this.mEncoding;
        }

        public int getType() {
            return this.mType;
        }

        public int getUserDataMsgOffset() {
            return this.mUserDataMsgOffset;
        }

        public int getUserDataMsgSize() {
            return this.mData.length - this.mUserDataMsgOffset;
        }

        public int getLanguageShiftTable() {
            return this.mLanguageShiftTable;
        }

        public int getLanguageTable() {
            return this.mLanguageTable;
        }

        public int getUserDataSeptetPadding() {
            return this.mUserDataSeptetPadding;
        }

        public int getMsgSeptetCount() {
            return this.mMsgSeptetCount;
        }

        private int cdmaGetParameterOffset(byte parameterId) {
            ByteArrayInputStream pdu = new ByteArrayInputStream(this.mData);
            int offset = 0;
            boolean found = false;
            try {
                pdu.skip(1L);
                while (true) {
                    if (pdu.available() <= 0) {
                        break;
                    }
                    int currentId = pdu.read();
                    int currentLen = pdu.read();
                    if (currentId == parameterId) {
                        found = true;
                        break;
                    }
                    pdu.skip(currentLen);
                    offset += currentLen + 2;
                }
                pdu.close();
            } catch (Exception e) {
                Log.e(BluetoothMapSmsPdu.TAG, "cdmaGetParameterOffset: ", e);
            }
            if (found) {
                return offset;
            }
            return 0;
        }

        private int cdmaGetSubParameterOffset(byte subParameterId) {
            ByteArrayInputStream pdu = new ByteArrayInputStream(this.mData);
            boolean found = false;
            int offset = cdmaGetParameterOffset((byte) 8) + 2;
            pdu.skip(offset);
            while (true) {
                try {
                    if (pdu.available() <= 0) {
                        break;
                    }
                    int currentId = pdu.read();
                    int currentLen = pdu.read();
                    if (currentId == subParameterId) {
                        found = true;
                        break;
                    }
                    pdu.skip(currentLen);
                    offset += currentLen + 2;
                } catch (Exception e) {
                    Log.e(BluetoothMapSmsPdu.TAG, "cdmaGetParameterOffset: ", e);
                }
            }
            pdu.close();
            if (found) {
                return offset;
            }
            return 0;
        }

        public void cdmaChangeToDeliverPdu(long date) {
            if (this.mData == null) {
                throw new IllegalArgumentException("Unable to convert PDU to Deliver type");
            }
            int offset = cdmaGetParameterOffset((byte) 4);
            byte[] bArr = this.mData;
            if (bArr.length < offset) {
                throw new IllegalArgumentException("Unable to convert PDU to Deliver type");
            }
            bArr[offset] = 2;
            int offset2 = cdmaGetParameterOffset((byte) 5);
            byte[] bArr2 = this.mData;
            if (bArr2.length < offset2) {
                throw new IllegalArgumentException("Unable to convert PDU to Deliver type");
            }
            bArr2[offset2] = 3;
            int offset3 = cdmaGetSubParameterOffset((byte) 0);
            byte[] bArr3 = this.mData;
            if (bArr3.length > offset3 + 2) {
                int tmp = bArr3[offset3 + 2] & UnsignedBytes.MAX_VALUE;
                bArr3[offset3 + 2] = (byte) ((tmp & 15) | 16);
                return;
            }
            throw new IllegalArgumentException("Unable to convert PDU to Deliver type");
        }

        private int gsmSubmitGetTpPidOffset() {
            byte[] bArr = this.mData;
            int offset = (((bArr[2] + 1) & 255) / 2) + 2 + 2;
            if (offset > bArr.length || offset > 14) {
                throw new IllegalArgumentException("wrongly formatted gsm submit PDU. offset = " + offset);
            }
            return offset;
        }

        public int gsmSubmitGetTpDcs() {
            return this.mData[gsmSubmitGetTpDcsOffset()] & UnsignedBytes.MAX_VALUE;
        }

        public boolean gsmSubmitHasUserDataHeader() {
            return ((this.mData[0] & UnsignedBytes.MAX_VALUE) & 64) == 64;
        }

        private int gsmSubmitGetTpDcsOffset() {
            return gsmSubmitGetTpPidOffset() + 1;
        }

        private int gsmSubmitGetTpUdlOffset() {
            int i = ((this.mData[0] & UnsignedBytes.MAX_VALUE) & 12) >> 2;
            if (i == 0) {
                return gsmSubmitGetTpPidOffset() + 2;
            }
            if (i == 1) {
                return gsmSubmitGetTpPidOffset() + 2 + 1;
            }
            return gsmSubmitGetTpPidOffset() + 2 + 7;
        }

        private int gsmSubmitGetTpUdOffset() {
            return gsmSubmitGetTpUdlOffset() + 1;
        }

        public void gsmDecodeUserDataHeader() {
            ByteArrayInputStream pdu = new ByteArrayInputStream(this.mData);
            pdu.skip(gsmSubmitGetTpUdlOffset());
            int userDataLength = pdu.read();
            if (gsmSubmitHasUserDataHeader()) {
                int userDataHeaderLength = pdu.read();
                if (this.mEncoding == 1) {
                    byte[] udh = new byte[userDataHeaderLength];
                    try {
                        pdu.read(udh);
                    } catch (IOException e) {
                        Log.w(BluetoothMapSmsPdu.TAG, "unable to read userDataHeader", e);
                    }
                    int[] tableValue = BluetoothMapSmsPdu.getTableFromByteArray(udh);
                    this.mLanguageTable = tableValue[0];
                    this.mLanguageShiftTable = tableValue[1];
                    int headerBits = (userDataHeaderLength + 1) * 8;
                    int headerSeptets = (headerBits / 7) + (headerBits % 7 > 0 ? 1 : 0);
                    this.mUserDataSeptetPadding = (headerSeptets * 7) - headerBits;
                    this.mMsgSeptetCount = userDataLength - headerSeptets;
                }
                this.mUserDataMsgOffset = gsmSubmitGetTpUdOffset() + userDataHeaderLength + 1;
                return;
            }
            this.mUserDataSeptetPadding = 0;
            this.mMsgSeptetCount = userDataLength;
            this.mUserDataMsgOffset = gsmSubmitGetTpUdOffset();
        }

        private void gsmWriteDate(ByteArrayOutputStream header, long time) throws UnsupportedEncodingException {
            SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
            Date date = new Date(time);
            String timeStr = format.format(date);
            byte[] timeChars = timeStr.getBytes("US-ASCII");
            int n = timeStr.length();
            for (int i = 0; i < n; i += 2) {
                header.write(((timeChars[i + 1] - 48) << 4) | (timeChars[i] - 48));
            }
            Calendar cal = Calendar.getInstance();
            int offset = (cal.get(15) + cal.get(16)) / 900000;
            if (offset < 0) {
                String offsetString = String.format("%1$02d", Integer.valueOf(-offset));
                char[] offsetChars = offsetString.toCharArray();
                header.write((offsetChars[0] - '0') | ((offsetChars[1] - '0') << 4) | 64);
            } else {
                String offsetString2 = String.format("%1$02d", Integer.valueOf(offset));
                char[] offsetChars2 = offsetString2.toCharArray();
                header.write((offsetChars2[0] - '0') | ((offsetChars2[1] - '0') << 4));
            }
        }

        public void gsmChangeToDeliverPdu(long date, String originator) {
            ByteArrayOutputStream newPdu = new ByteArrayOutputStream(22);
            try {
                newPdu.write((this.mData[0] & UnsignedBytes.MAX_VALUE & 64) | 4);
                byte[] encodedAddress = PhoneNumberUtils.networkPortionToCalledPartyBCDWithLength(originator);
                if (encodedAddress != null) {
                    int padding = (encodedAddress[encodedAddress.length - 1] & 240) == 240 ? 1 : 0;
                    encodedAddress[0] = (byte) (((encodedAddress[0] - 1) * 2) - padding);
                    newPdu.write(encodedAddress);
                } else {
                    newPdu.write(0);
                    newPdu.write(BluetoothMapContent.MMS_BCC);
                }
                newPdu.write(this.mData[gsmSubmitGetTpPidOffset()]);
                newPdu.write(this.mData[gsmSubmitGetTpDcsOffset()]);
                gsmWriteDate(newPdu, date);
                int userDataLength = this.mData[gsmSubmitGetTpUdlOffset()] & UnsignedBytes.MAX_VALUE;
                newPdu.write(userDataLength);
                newPdu.write(this.mData, gsmSubmitGetTpUdOffset(), this.mData.length - gsmSubmitGetTpUdOffset());
                this.mData = newPdu.toByteArray();
            } catch (IOException e) {
                Log.e(BluetoothMapSmsPdu.TAG, INetd.NEXTHOP_NONE, e);
                throw new IllegalArgumentException("Failed to change type to deliver PDU.");
            }
        }

        public String getEncodingString() {
            if (this.mType == 1) {
                int i = this.mEncoding;
                if (i != 1) {
                    if (i != 2) {
                        return i != 3 ? INetd.NEXTHOP_NONE : "G-16BIT";
                    }
                    return "G-8BIT";
                }
                if (this.mLanguageTable == 0) {
                    return "G-7BIT";
                }
                return "G-7BITEXT";
            }
            int i2 = this.mEncoding;
            if (i2 == 1) {
                return "C-7ASCII";
            }
            if (i2 == 2) {
                return "C-8BIT";
            }
            if (i2 != 3) {
                return i2 != 4 ? INetd.NEXTHOP_NONE : "C-KOREAN";
            }
            return "C-UNICODE";
        }
    }

    protected static int getNextConcatenatedRef() {
        int i = sConcatenatedRef + 1;
        sConcatenatedRef = i;
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ArrayList<SmsPdu> getSubmitPdus(Context context, String messageText, String address) {
        int activePhone = ((TelephonyManager) context.getSystemService(TelephonyManager.class)).getCurrentPhoneType();
        int[] ted = SmsMessage.calculateLength((CharSequence) messageText, false);
        int msgCount = ted[0];
        int refNumber = getNextConcatenatedRef() & 255;
        SmsManager smsMng = SmsManager.getDefault();
        ArrayList<String> smsFragments = smsMng.divideMessage(messageText);
        ArrayList<SmsPdu> pdus = new ArrayList<>(msgCount);
        int i = 1;
        int phoneType = activePhone == 2 ? 2 : 1;
        int languageTable = ted[3];
        int encoding = ted[4];
        int languageShiftTable = ted[5];
        String destinationAddress = PhoneNumberUtils.stripSeparators(address);
        String destinationAddress2 = (destinationAddress == null || destinationAddress.length() < 2) ? "12" : destinationAddress;
        if (msgCount == 1) {
            byte[] data = SmsMessage.getSubmitPdu(null, destinationAddress2, smsFragments.get(0), false).encodedMessage;
            SmsPdu newPdu = new SmsPdu(data, languageTable, phoneType, encoding);
            pdus.add(newPdu);
            return pdus;
        }
        int i2 = 0;
        int i3 = 0;
        while (i3 < msgCount) {
            int i4 = phoneType == i ? i : i2;
            String str = smsFragments.get(i3);
            int i5 = i3 + 1;
            int i6 = i3;
            boolean z = i4;
            int i7 = i2;
            int languageTable2 = encoding;
            int encoding2 = languageTable;
            int phoneType2 = phoneType;
            int activePhone2 = activePhone;
            ArrayList<SmsPdu> pdus2 = pdus;
            byte[] data2 = SmsMessage.getSubmitPduEncodedMessage(z, destinationAddress2, str, languageTable, languageTable2, languageShiftTable, refNumber, i5, msgCount);
            SmsPdu newPdu2 = new SmsPdu(data2, encoding2, phoneType2, languageTable2);
            pdus2.add(newPdu2);
            pdus = pdus2;
            i3 = i6 + 1;
            i2 = i7;
            smsMng = smsMng;
            i = i;
            activePhone = activePhone2;
            smsFragments = smsFragments;
            phoneType = phoneType2;
            languageTable = encoding2;
            encoding = languageTable2;
        }
        return pdus;
    }

    public static ArrayList<SmsPdu> getDeliverPdus(Context context, String messageText, String address, long date) {
        ArrayList<SmsPdu> deliverPdus = getSubmitPdus(context, messageText, address);
        for (SmsPdu currentPdu : deliverPdus) {
            if (currentPdu.getType() == 2) {
                currentPdu.cdmaChangeToDeliverPdu(date);
            } else {
                currentPdu.gsmChangeToDeliverPdu(date, address);
            }
        }
        return deliverPdus;
    }

    public static String decodePdu(byte[] data, int type) {
        if (type == 2) {
            SmsMessage smsMessage = SmsMessage.createFromNativeSmsSubmitPdu(data, true);
            if (smsMessage == null) {
                return INetd.NEXTHOP_NONE;
            }
            String ret = smsMessage.getMessageBody();
            return ret;
        }
        String ret2 = gsmParseSubmitPdu(data);
        return ret2;
    }

    private static byte[] gsmStripOffScAddress(byte[] data) {
        int addressLength = data[0] & 255;
        if (addressLength >= data.length) {
            throw new IllegalArgumentException("Length of address exeeds the length of the PDU data.");
        }
        int pduLength = data.length - (addressLength + 1);
        byte[] newData = new byte[pduLength];
        System.arraycopy(data, addressLength + 1, newData, 0, pduLength);
        return newData;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0173 A[Catch: UnsupportedEncodingException -> 0x0171, TRY_LEAVE, TryCatch #0 {UnsupportedEncodingException -> 0x0171, blocks: (B:55:0x00ec, B:56:0x0116, B:57:0x013f, B:60:0x0173), top: B:67:0x00df }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String gsmParseSubmitPdu(byte[] r16) {
        /*
            Method dump skipped, instruction units count: 402
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.map.BluetoothMapSmsPdu.gsmParseSubmitPdu(byte[]):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int[] getTableFromByteArray(byte[] data) {
        ByteArrayInputStream inStream = new ByteArrayInputStream(data);
        int[] tableValue = new int[2];
        while (inStream.available() > 0) {
            int id = inStream.read();
            int length = inStream.read();
            if (id == 36) {
                tableValue[1] = inStream.read();
            } else if (id == 37) {
                tableValue[0] = inStream.read();
            } else {
                inStream.skip(length);
            }
        }
        return tableValue;
    }
}
