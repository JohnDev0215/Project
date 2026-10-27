package com.android.bluetooth.map;

import android.net.INetd;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import com.android.bluetooth.map.BluetoothMapUtils;
import com.android.vcard.VCardBuilder;
import com.android.vcard.VCardConstants;
import com.google.common.base.Ascii;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class BluetoothMapbMessage {
    protected static final boolean D = false;
    public static final int INVALID_VALUE = -1;
    protected static final String TAG = "BluetoothMapbMessage";
    protected static final boolean V = false;
    private String mVersionString = "VERSION:1.0";
    protected int mAppParamCharset = -1;
    private String mStatus = null;
    protected BluetoothMapUtils.TYPE mType = null;
    private String mFolder = null;
    private long mPartId = -1;
    protected String mEncoding = null;
    protected String mCharset = null;
    private String mLanguage = null;
    private int mBMsgLength = -1;
    private ArrayList<VCard> mOriginator = null;
    private ArrayList<VCard> mRecipient = null;

    public abstract byte[] encode() throws UnsupportedEncodingException;

    public abstract void parseMsgInit();

    public abstract void parseMsgPart(String str);

    public static class VCard {
        private String[] mBtUcis;
        private String[] mBtUids;
        private String[] mEmailAddresses;
        private int mEnvLevel;
        private String mFormattedName;
        private String mName;
        private String[] mPhoneNumbers;
        private String mVersion;

        public VCard(String name, String formattedName, String[] phoneNumbers, String[] emailAddresses, int envLevel) {
            this.mName = null;
            this.mFormattedName = null;
            this.mPhoneNumbers = new String[0];
            this.mEmailAddresses = new String[0];
            this.mEnvLevel = 0;
            this.mBtUcis = new String[0];
            this.mBtUids = new String[0];
            this.mEnvLevel = envLevel;
            this.mVersion = VCardConstants.VERSION_V30;
            String str = INetd.NEXTHOP_NONE;
            this.mName = name != null ? name : INetd.NEXTHOP_NONE;
            this.mFormattedName = formattedName != null ? formattedName : str;
            setPhoneNumbers(phoneNumbers);
            if (emailAddresses != null) {
                this.mEmailAddresses = emailAddresses;
            }
        }

        public VCard(String name, String[] phoneNumbers, String[] emailAddresses, int envLevel) {
            this.mName = null;
            this.mFormattedName = null;
            this.mPhoneNumbers = new String[0];
            this.mEmailAddresses = new String[0];
            this.mEnvLevel = 0;
            this.mBtUcis = new String[0];
            this.mBtUids = new String[0];
            this.mEnvLevel = envLevel;
            this.mVersion = VCardConstants.VERSION_V21;
            this.mName = name != null ? name : INetd.NEXTHOP_NONE;
            setPhoneNumbers(phoneNumbers);
            if (emailAddresses != null) {
                this.mEmailAddresses = emailAddresses;
            }
        }

        public VCard(String name, String formattedName, String[] phoneNumbers, String[] emailAddresses, String[] btUids, String[] btUcis) {
            this.mName = null;
            this.mFormattedName = null;
            this.mPhoneNumbers = new String[0];
            this.mEmailAddresses = new String[0];
            this.mEnvLevel = 0;
            this.mBtUcis = new String[0];
            this.mBtUids = new String[0];
            this.mVersion = VCardConstants.VERSION_V30;
            String str = INetd.NEXTHOP_NONE;
            this.mName = name != null ? name : INetd.NEXTHOP_NONE;
            this.mFormattedName = formattedName != null ? formattedName : str;
            setPhoneNumbers(phoneNumbers);
            if (emailAddresses != null) {
                this.mEmailAddresses = emailAddresses;
            }
            if (btUcis != null) {
                this.mBtUcis = btUcis;
            }
        }

        public VCard(String name, String[] phoneNumbers, String[] emailAddresses) {
            this.mName = null;
            this.mFormattedName = null;
            this.mPhoneNumbers = new String[0];
            this.mEmailAddresses = new String[0];
            this.mEnvLevel = 0;
            this.mBtUcis = new String[0];
            this.mBtUids = new String[0];
            this.mVersion = VCardConstants.VERSION_V21;
            this.mName = name != null ? name : INetd.NEXTHOP_NONE;
            setPhoneNumbers(phoneNumbers);
            if (emailAddresses != null) {
                this.mEmailAddresses = emailAddresses;
            }
        }

        private void setPhoneNumbers(String[] numbers) {
            if (numbers != null && numbers.length > 0) {
                this.mPhoneNumbers = new String[numbers.length];
                int n = numbers.length;
                for (int i = 0; i < n; i++) {
                    String networkNumber = PhoneNumberUtils.extractNetworkPortion(numbers[i]);
                    String strippedNumber = PhoneNumberUtils.stripSeparators(numbers[i]);
                    Boolean alpha = false;
                    if (strippedNumber != null) {
                        alpha = Boolean.valueOf(strippedNumber.matches("[0-9]*[a-zA-Z]+[0-9]*"));
                    }
                    if (networkNumber != null && networkNumber.length() > 1 && !alpha.booleanValue()) {
                        this.mPhoneNumbers[i] = networkNumber;
                    } else {
                        this.mPhoneNumbers[i] = numbers[i];
                    }
                }
            }
        }

        public String getFirstPhoneNumber() {
            String[] strArr = this.mPhoneNumbers;
            if (strArr.length > 0) {
                return strArr[0];
            }
            return null;
        }

        public int getEnvLevel() {
            return this.mEnvLevel;
        }

        public String getName() {
            return this.mName;
        }

        public String getFirstEmail() {
            String[] strArr = this.mEmailAddresses;
            if (strArr.length > 0) {
                return strArr[0];
            }
            return null;
        }

        public String getFirstBtUci() {
            String[] strArr = this.mBtUcis;
            if (strArr.length > 0) {
                return strArr[0];
            }
            return null;
        }

        public String getFirstBtUid() {
            String[] strArr = this.mBtUids;
            if (strArr.length > 0) {
                return strArr[0];
            }
            return null;
        }

        public void encode(StringBuilder sb) {
            sb.append("BEGIN:VCARD");
            sb.append(VCardBuilder.VCARD_END_OF_LINE);
            sb.append("VERSION:");
            sb.append(this.mVersion);
            sb.append(VCardBuilder.VCARD_END_OF_LINE);
            if (this.mVersion.equals(VCardConstants.VERSION_V30) && this.mFormattedName != null) {
                sb.append("FN:");
                sb.append(this.mFormattedName);
                sb.append(VCardBuilder.VCARD_END_OF_LINE);
            }
            if (this.mName != null) {
                sb.append("N:");
                sb.append(this.mName);
                sb.append(VCardBuilder.VCARD_END_OF_LINE);
            }
            for (String phoneNumber : this.mPhoneNumbers) {
                sb.append("TEL:");
                sb.append(phoneNumber);
                sb.append(VCardBuilder.VCARD_END_OF_LINE);
            }
            for (String emailAddress : this.mEmailAddresses) {
                sb.append("EMAIL:");
                sb.append(emailAddress);
                sb.append(VCardBuilder.VCARD_END_OF_LINE);
            }
            for (String btUid : this.mBtUids) {
                sb.append("X-BT-UID:");
                sb.append(btUid);
                sb.append(VCardBuilder.VCARD_END_OF_LINE);
            }
            for (String btUci : this.mBtUcis) {
                sb.append("X-BT-UCI:");
                sb.append(btUci);
                sb.append(VCardBuilder.VCARD_END_OF_LINE);
            }
            sb.append("END:VCARD");
            sb.append(VCardBuilder.VCARD_END_OF_LINE);
        }

        public static VCard parseVcard(BMsgReader reader, int envLevel) {
            String formattedName = null;
            String name = null;
            ArrayList<String> phoneNumbers = null;
            ArrayList<String> emailAddresses = null;
            ArrayList<String> btUids = null;
            ArrayList<String> btUcis = null;
            String line = reader.getLineEnforce();
            while (!line.contains("END:VCARD")) {
                String line2 = line.trim();
                if (line2.startsWith("N:")) {
                    String[] parts = line2.split("[^\\\\]:");
                    if (parts.length == 2) {
                        name = parts[1];
                    } else {
                        name = INetd.NEXTHOP_NONE;
                    }
                } else if (line2.startsWith("FN:")) {
                    String[] parts2 = line2.split("[^\\\\]:");
                    if (parts2.length == 2) {
                        formattedName = parts2[1];
                    } else {
                        formattedName = INetd.NEXTHOP_NONE;
                    }
                } else if (line2.startsWith("TEL:")) {
                    String[] parts3 = line2.split("[^\\\\]:");
                    if (parts3.length == 2) {
                        String[] subParts = parts3[1].split("[^\\\\];");
                        if (phoneNumbers == null) {
                            phoneNumbers = new ArrayList<>(1);
                        }
                        phoneNumbers.add(subParts[subParts.length - 1]);
                    }
                } else if (line2.startsWith("EMAIL:")) {
                    String[] parts4 = line2.split("[^\\\\]:");
                    if (parts4.length == 2) {
                        String[] subParts2 = parts4[1].split("[^\\\\];");
                        if (emailAddresses == null) {
                            emailAddresses = new ArrayList<>(1);
                        }
                        emailAddresses.add(subParts2[subParts2.length - 1]);
                    }
                } else if (line2.startsWith("X-BT-UCI:")) {
                    String[] parts5 = line2.split("[^\\\\]:");
                    if (parts5.length == 2) {
                        String[] subParts3 = parts5[1].split("[^\\\\];");
                        if (btUcis == null) {
                            btUcis = new ArrayList<>(1);
                        }
                        btUcis.add(subParts3[subParts3.length - 1]);
                    }
                } else if (line2.startsWith("X-BT-UID:")) {
                    String[] parts6 = line2.split("[^\\\\]:");
                    if (parts6.length == 2) {
                        String[] subParts4 = parts6[1].split("[^\\\\];");
                        if (btUids == null) {
                            btUids = new ArrayList<>(1);
                        }
                        btUids.add(subParts4[subParts4.length - 1]);
                    }
                }
                line = reader.getLineEnforce();
            }
            return new VCard(name, formattedName, phoneNumbers == null ? null : (String[]) phoneNumbers.toArray(new String[phoneNumbers.size()]), emailAddresses == null ? null : (String[]) emailAddresses.toArray(new String[emailAddresses.size()]), envLevel);
        }
    }

    private static class BMsgReader {
        InputStream mInStream;

        BMsgReader(InputStream is) {
            this.mInStream = is;
        }

        private byte[] getLineAsBytes() {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            while (true) {
                try {
                    int i = this.mInStream.read();
                    int readByte = i;
                    if (i == -1) {
                        break;
                    }
                    if (readByte == 13) {
                        int i2 = this.mInStream.read();
                        readByte = i2;
                        if (i2 != -1 && readByte == 10) {
                            if (output.size() != 0) {
                                break;
                            }
                        } else {
                            output.write(13);
                            output.write(readByte);
                        }
                    } else if (readByte != 10 || output.size() != 0) {
                        output.write(readByte);
                    }
                } catch (IOException e) {
                    Log.w(BluetoothMapbMessage.TAG, e);
                    return null;
                }
            }
            return output.toByteArray();
        }

        public String getLine() {
            try {
                byte[] line = getLineAsBytes();
                if (line.length == 0) {
                    return null;
                }
                return new String(line, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                Log.w(BluetoothMapbMessage.TAG, e);
                return null;
            }
        }

        public String getLineEnforce() {
            String line = getLine();
            if (line == null) {
                throw new IllegalArgumentException("Bmessage too short");
            }
            return line;
        }

        public void expect(String subString) throws IllegalArgumentException {
            String line = getLine();
            if (line == null || subString == null) {
                throw new IllegalArgumentException("Line or substring is null");
            }
            if (!line.toUpperCase().contains(subString.toUpperCase())) {
                throw new IllegalArgumentException("Expected \"" + subString + "\" in: \"" + line + "\"");
            }
        }

        public void expect(String subString, String subString2) throws IllegalArgumentException {
            String line = getLine();
            if (!line.toUpperCase().contains(subString.toUpperCase())) {
                throw new IllegalArgumentException("Expected \"" + subString + "\" in: \"" + line + "\"");
            }
            if (!line.toUpperCase().contains(subString2.toUpperCase())) {
                throw new IllegalArgumentException("Expected \"" + subString + "\" in: \"" + line + "\"");
            }
        }

        public byte[] getDataBytes(int length) {
            byte[] data = new byte[length];
            int offset = 0;
            while (true) {
                try {
                    int bytesRead = this.mInStream.read(data, offset, length - offset);
                    if (bytesRead != length - offset) {
                        if (bytesRead == -1) {
                            return null;
                        }
                        offset += bytesRead;
                    } else {
                        return data;
                    }
                } catch (IOException e) {
                    Log.w(BluetoothMapbMessage.TAG, e);
                    return null;
                }
            }
        }
    }

    public String getVersionString() {
        return this.mVersionString;
    }

    public void setVersionString(String version) {
        this.mVersionString = "VERSION:" + version;
    }

    public static BluetoothMapbMessage parse(InputStream bMsgStream, int appParamCharset) throws IllegalArgumentException {
        String[] arg;
        String[] arg2;
        BluetoothMapbMessage newBMsg = null;
        boolean status = false;
        BluetoothMapUtils.TYPE type = null;
        String folder = null;
        BMsgReader reader = new BMsgReader(bMsgStream);
        reader.expect("BEGIN:BMSG");
        reader.expect(VCardConstants.PROPERTY_VERSION);
        String line = reader.getLineEnforce();
        while (!line.contains("BEGIN:VCARD") && !line.contains("BEGIN:BENV")) {
            if (line.contains("STATUS")) {
                String[] arg3 = line.split(":");
                if (arg3 != null && arg3.length == 2) {
                    if (arg3[1].trim().equals("READ")) {
                        status = true;
                    } else if (arg3[1].trim().equals("UNREAD")) {
                        status = false;
                    } else {
                        throw new IllegalArgumentException("Wrong value in 'STATUS': " + arg3[1]);
                    }
                } else {
                    throw new IllegalArgumentException("Missing value for 'STATUS': " + line);
                }
            }
            if (line.contains("EXTENDEDDATA") && (arg2 = line.split(":")) != null && arg2.length == 2) {
                String value = arg2[1].trim();
                Log.i(TAG, "We got extended data with: " + value);
            }
            if (line.contains(VCardConstants.PARAM_TYPE)) {
                String[] arg4 = line.split(":");
                if (arg4 != null && arg4.length == 2) {
                    String value2 = arg4[1].trim();
                    type = BluetoothMapUtils.TYPE.valueOf(value2);
                    if (appParamCharset == 0 && type != BluetoothMapUtils.TYPE.SMS_CDMA && type != BluetoothMapUtils.TYPE.SMS_GSM) {
                        throw new IllegalArgumentException("Native appParamsCharset only supported for SMS");
                    }
                    int i = AnonymousClass1.$SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE[type.ordinal()];
                    if (i == 1 || i == 2) {
                        newBMsg = new BluetoothMapbMessageSms();
                    } else if (i == 3) {
                        newBMsg = new BluetoothMapbMessageMime();
                    } else if (i == 4) {
                        newBMsg = new BluetoothMapbMessageEmail();
                    } else if (i == 5) {
                        newBMsg = new BluetoothMapbMessageMime();
                    }
                } else {
                    throw new IllegalArgumentException("Missing value for 'TYPE':" + line);
                }
            }
            if (line.contains("FOLDER") && (arg = line.split(":")) != null && arg.length == 2) {
                folder = arg[1].trim();
            }
            line = reader.getLineEnforce();
        }
        if (newBMsg == null) {
            throw new IllegalArgumentException("Missing bMessage TYPE: - unable to parse body-content");
        }
        newBMsg.setType(type);
        newBMsg.mAppParamCharset = appParamCharset;
        if (folder != null) {
            newBMsg.setCompleteFolder(folder);
        }
        if (0 != 0) {
            newBMsg.setStatus(status);
        }
        while (line.contains("BEGIN:VCARD")) {
            newBMsg.addOriginator(VCard.parseVcard(reader, 0));
            line = reader.getLineEnforce();
        }
        if (line.contains("BEGIN:BENV")) {
            newBMsg.parseEnvelope(reader, 0);
            try {
                bMsgStream.close();
            } catch (IOException e) {
            }
            return newBMsg;
        }
        throw new IllegalArgumentException("Bmessage has no BEGIN:BENV - line:" + line);
    }

    /* JADX INFO: renamed from: com.android.bluetooth.map.BluetoothMapbMessage$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE;

        static {
            int[] iArr = new int[BluetoothMapUtils.TYPE.values().length];
            $SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE = iArr;
            try {
                iArr[BluetoothMapUtils.TYPE.SMS_CDMA.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE[BluetoothMapUtils.TYPE.SMS_GSM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE[BluetoothMapUtils.TYPE.MMS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE[BluetoothMapUtils.TYPE.EMAIL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE[BluetoothMapUtils.TYPE.IM.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    private void parseEnvelope(BMsgReader reader, int level) {
        String line = reader.getLineEnforce();
        while (line.contains("BEGIN:VCARD")) {
            if (this.mRecipient == null) {
                this.mRecipient = new ArrayList<>(1);
            }
            this.mRecipient.add(VCard.parseVcard(reader, level));
            line = reader.getLineEnforce();
        }
        if (line.contains("BEGIN:BENV")) {
            parseEnvelope(reader, level + 1);
        }
        if (line.contains("BEGIN:BBODY")) {
            parseBody(reader);
        }
    }

    private void parseBody(BMsgReader reader) {
        String line = reader.getLineEnforce();
        parseMsgInit();
        while (!line.contains("END:")) {
            if (line.contains("PARTID:")) {
                String[] arg = line.split(":");
                if (arg != null && arg.length == 2) {
                    try {
                        this.mPartId = Long.parseLong(arg[1].trim());
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Wrong value in 'PARTID': " + arg[1]);
                    }
                } else {
                    throw new IllegalArgumentException("Missing value for 'PARTID': " + line);
                }
            } else if (line.contains("ENCODING:")) {
                String[] arg2 = line.split(":");
                if (arg2 != null && arg2.length == 2) {
                    this.mEncoding = arg2[1].trim();
                } else {
                    throw new IllegalArgumentException("Missing value for 'ENCODING': " + line);
                }
            } else if (line.contains("CHARSET:")) {
                String[] arg3 = line.split(":");
                if (arg3 != null && arg3.length == 2) {
                    this.mCharset = arg3[1].trim();
                } else {
                    throw new IllegalArgumentException("Missing value for 'CHARSET': " + line);
                }
            } else if (line.contains("LANGUAGE:")) {
                String[] arg4 = line.split(":");
                if (arg4 != null && arg4.length == 2) {
                    this.mLanguage = arg4[1].trim();
                } else {
                    throw new IllegalArgumentException("Missing value for 'LANGUAGE': " + line);
                }
            } else if (line.contains("LENGTH:")) {
                String[] arg5 = line.split(":");
                if (arg5 != null && arg5.length == 2) {
                    try {
                        this.mBMsgLength = Integer.parseInt(arg5[1].trim());
                    } catch (NumberFormatException e2) {
                        throw new IllegalArgumentException("Wrong value in 'LENGTH': " + arg5[1]);
                    }
                } else {
                    throw new IllegalArgumentException("Missing value for 'LENGTH': " + line);
                }
            } else if (!line.contains("BEGIN:MSG")) {
                continue;
            } else {
                if (this.mBMsgLength == -1) {
                    throw new IllegalArgumentException("Missing value for 'LENGTH'. Unable to read remaining part of the message");
                }
                String data = INetd.NEXTHOP_NONE;
                String messageLine = INetd.NEXTHOP_NONE;
                while (!messageLine.equals("END:MSG")) {
                    data = data + messageLine;
                    messageLine = reader.getLineEnforce();
                }
                data.replaceAll("([/]*)/END\\:MSG", "$1END:MSG");
                data.trim();
                parseMsgPart(data);
            }
            line = reader.getLineEnforce();
        }
    }

    public void setStatus(boolean read) {
        if (read) {
            this.mStatus = "READ";
        } else {
            this.mStatus = "UNREAD";
        }
    }

    public void setType(BluetoothMapUtils.TYPE type) {
        this.mType = type;
    }

    public BluetoothMapUtils.TYPE getType() {
        return this.mType;
    }

    public void setCompleteFolder(String folder) {
        this.mFolder = folder;
    }

    public void setFolder(String folder) {
        this.mFolder = "telecom/msg/" + folder;
    }

    public String getFolder() {
        return this.mFolder;
    }

    public void setEncoding(String encoding) {
        this.mEncoding = encoding;
    }

    public ArrayList<VCard> getOriginators() {
        return this.mOriginator;
    }

    public void addOriginator(VCard originator) {
        if (this.mOriginator == null) {
            this.mOriginator = new ArrayList<>();
        }
        this.mOriginator.add(originator);
    }

    public void addOriginator(String name, String formattedName, String[] phoneNumbers, String[] emailAddresses, String[] btUids, String[] btUcis) {
        if (this.mOriginator == null) {
            this.mOriginator = new ArrayList<>();
        }
        this.mOriginator.add(new VCard(name, formattedName, phoneNumbers, emailAddresses, btUids, btUcis));
    }

    public void addOriginator(String[] btUcis, String[] btUids) {
        if (this.mOriginator == null) {
            this.mOriginator = new ArrayList<>();
        }
        this.mOriginator.add(new VCard(null, null, null, null, btUids, btUcis));
    }

    public void addOriginator(String name, String[] phoneNumbers, String[] emailAddresses) {
        if (this.mOriginator == null) {
            this.mOriginator = new ArrayList<>();
        }
        this.mOriginator.add(new VCard(name, phoneNumbers, emailAddresses));
    }

    public ArrayList<VCard> getRecipients() {
        return this.mRecipient;
    }

    public void setRecipient(VCard recipient) {
        if (this.mRecipient == null) {
            this.mRecipient = new ArrayList<>();
        }
        this.mRecipient.add(recipient);
    }

    public void addRecipient(String[] btUcis, String[] btUids) {
        if (this.mRecipient == null) {
            this.mRecipient = new ArrayList<>();
        }
        this.mRecipient.add(new VCard(null, null, null, null, btUids, btUcis));
    }

    public void addRecipient(String name, String formattedName, String[] phoneNumbers, String[] emailAddresses, String[] btUids, String[] btUcis) {
        if (this.mRecipient == null) {
            this.mRecipient = new ArrayList<>();
        }
        this.mRecipient.add(new VCard(name, formattedName, phoneNumbers, emailAddresses, btUids, btUcis));
    }

    public void addRecipient(String name, String[] phoneNumbers, String[] emailAddresses) {
        if (this.mRecipient == null) {
            this.mRecipient = new ArrayList<>();
        }
        this.mRecipient.add(new VCard(name, phoneNumbers, emailAddresses));
    }

    protected String encodeBinary(byte[] pduData, byte[] scAddressData) {
        StringBuilder out = new StringBuilder((pduData.length + scAddressData.length) * 2);
        for (int i = 0; i < scAddressData.length; i++) {
            out.append(Integer.toString((scAddressData[i] >> 4) & 15, 16));
            out.append(Integer.toString(scAddressData[i] & Ascii.SI, 16));
        }
        for (int i2 = 0; i2 < pduData.length; i2++) {
            out.append(Integer.toString((pduData[i2] >> 4) & 15, 16));
            out.append(Integer.toString(pduData[i2] & Ascii.SI, 16));
        }
        return out.toString();
    }

    protected byte[] decodeBinary(String data) {
        byte[] out = new byte[data.length() / 2];
        int i = 0;
        int j = 0;
        int n = out.length;
        while (i < n) {
            int j2 = j + 1 + 1;
            String value = data.substring(j, j2);
            out[i] = (byte) (Integer.valueOf(value, 16).intValue() & 255);
            i++;
            j = j2;
        }
        return out;
    }

    public byte[] encodeGeneric(ArrayList<byte[]> bodyFragments) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder(256);
        sb.append("BEGIN:BMSG");
        sb.append(VCardBuilder.VCARD_END_OF_LINE);
        sb.append(this.mVersionString);
        sb.append(VCardBuilder.VCARD_END_OF_LINE);
        sb.append("STATUS:");
        sb.append(this.mStatus);
        sb.append(VCardBuilder.VCARD_END_OF_LINE);
        sb.append("TYPE:");
        sb.append(this.mType.name());
        sb.append(VCardBuilder.VCARD_END_OF_LINE);
        if (this.mFolder.length() > 512) {
            sb.append("FOLDER:");
            String str = this.mFolder;
            sb.append(str.substring(str.length() - 512, this.mFolder.length()));
            sb.append(VCardBuilder.VCARD_END_OF_LINE);
        } else {
            sb.append("FOLDER:");
            sb.append(this.mFolder);
            sb.append(VCardBuilder.VCARD_END_OF_LINE);
        }
        if (!this.mVersionString.contains("1.0")) {
            sb.append("EXTENDEDDATA:");
            sb.append(VCardBuilder.VCARD_END_OF_LINE);
        }
        ArrayList<VCard> arrayList = this.mOriginator;
        if (arrayList != null) {
            for (VCard element : arrayList) {
                element.encode(sb);
            }
        }
        sb.append("BEGIN:BENV");
        sb.append(VCardBuilder.VCARD_END_OF_LINE);
        ArrayList<VCard> arrayList2 = this.mRecipient;
        if (arrayList2 != null) {
            for (VCard element2 : arrayList2) {
                element2.encode(sb);
            }
        }
        sb.append("BEGIN:BBODY");
        sb.append(VCardBuilder.VCARD_END_OF_LINE);
        String str2 = this.mEncoding;
        if (str2 != null && !str2.isEmpty()) {
            sb.append("ENCODING:");
            sb.append(this.mEncoding);
            sb.append(VCardBuilder.VCARD_END_OF_LINE);
        }
        String str3 = this.mCharset;
        if (str3 != null && !str3.isEmpty()) {
            sb.append("CHARSET:");
            sb.append(this.mCharset);
            sb.append(VCardBuilder.VCARD_END_OF_LINE);
        }
        int length = 0;
        for (byte[] fragment : bodyFragments) {
            length += fragment.length + 22;
        }
        sb.append("LENGTH:");
        sb.append(length);
        sb.append(VCardBuilder.VCARD_END_OF_LINE);
        byte[] msgStart = sb.toString().getBytes("UTF-8");
        StringBuilder sb2 = new StringBuilder(31);
        sb2.append("END:BBODY");
        sb2.append(VCardBuilder.VCARD_END_OF_LINE);
        sb2.append("END:BENV");
        sb2.append(VCardBuilder.VCARD_END_OF_LINE);
        sb2.append("END:BMSG");
        sb2.append(VCardBuilder.VCARD_END_OF_LINE);
        byte[] msgEnd = sb2.toString().getBytes("UTF-8");
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream(msgStart.length + msgEnd.length + length);
            stream.write(msgStart);
            for (byte[] fragment2 : bodyFragments) {
                stream.write("BEGIN:MSG\r\n".getBytes("UTF-8"));
                stream.write(fragment2);
                stream.write("\r\nEND:MSG\r\n".getBytes("UTF-8"));
            }
            stream.write(msgEnd);
            return stream.toByteArray();
        } catch (IOException e) {
            Log.w(TAG, e);
            return null;
        }
    }
}
