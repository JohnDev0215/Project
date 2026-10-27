package com.android.bluetooth.mapclient;

import android.util.Log;
import com.android.bluetooth.mapclient.Bmessage;
import com.android.bluetooth.mapclient.BmsgTokenizer;
import com.android.vcard.VCardConstants;
import com.android.vcard.VCardEntry;
import com.android.vcard.VCardEntryConstructor;
import com.android.vcard.VCardEntryHandler;
import com.android.vcard.VCardParser_V21;
import com.android.vcard.VCardParser_V30;
import com.android.vcard.exception.VCardException;
import com.android.vcard.exception.VCardVersionException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

/* JADX INFO: loaded from: classes.dex */
class BmessageParser {
    private static final String CRLF = "\r\n";
    private static final int CRLF_LEN = 2;
    private static final boolean DBG = false;
    private static final int MSG_CONTAINER_LEN = 22;
    private static final String TAG = "BmessageParser";
    private final Bmessage mBmsg = new Bmessage();
    private BmsgTokenizer mParser;
    private static final BmsgTokenizer.Property BEGIN_BMSG = new BmsgTokenizer.Property(VCardConstants.PROPERTY_BEGIN, "BMSG");
    private static final BmsgTokenizer.Property END_BMSG = new BmsgTokenizer.Property(VCardConstants.PROPERTY_END, "BMSG");
    private static final BmsgTokenizer.Property BEGIN_VCARD = new BmsgTokenizer.Property(VCardConstants.PROPERTY_BEGIN, "VCARD");
    private static final BmsgTokenizer.Property END_VCARD = new BmsgTokenizer.Property(VCardConstants.PROPERTY_END, "VCARD");
    private static final BmsgTokenizer.Property BEGIN_BENV = new BmsgTokenizer.Property(VCardConstants.PROPERTY_BEGIN, "BENV");
    private static final BmsgTokenizer.Property END_BENV = new BmsgTokenizer.Property(VCardConstants.PROPERTY_END, "BENV");
    private static final BmsgTokenizer.Property BEGIN_BBODY = new BmsgTokenizer.Property(VCardConstants.PROPERTY_BEGIN, "BBODY");
    private static final BmsgTokenizer.Property END_BBODY = new BmsgTokenizer.Property(VCardConstants.PROPERTY_END, "BBODY");
    private static final BmsgTokenizer.Property BEGIN_MSG = new BmsgTokenizer.Property(VCardConstants.PROPERTY_BEGIN, VCardConstants.PARAM_TYPE_MSG);
    private static final BmsgTokenizer.Property END_MSG = new BmsgTokenizer.Property(VCardConstants.PROPERTY_END, VCardConstants.PARAM_TYPE_MSG);

    private BmessageParser() {
    }

    public static Bmessage createBmessage(String str) {
        BmessageParser p = new BmessageParser();
        try {
            p.parse(str);
            return p.mBmsg;
        } catch (IOException e) {
            Log.e(TAG, "I/O exception when parsing bMessage", e);
            return null;
        } catch (ParseException e2) {
            Log.e(TAG, "Cannot parse bMessage", e2);
            return null;
        }
    }

    private ParseException expected(BmsgTokenizer.Property... props) {
        boolean first = true;
        StringBuilder sb = new StringBuilder();
        for (BmsgTokenizer.Property prop : props) {
            if (!first) {
                sb.append(" or ");
            }
            sb.append(prop);
            first = false;
        }
        return new ParseException("Expected: " + sb.toString(), this.mParser.pos());
    }

    private void parse(String str) throws IOException, ParseException {
        BmsgTokenizer bmsgTokenizer = new BmsgTokenizer(str + "\r\n");
        this.mParser = bmsgTokenizer;
        BmsgTokenizer.Property prop = bmsgTokenizer.next();
        if (!prop.equals(BEGIN_BMSG)) {
            throw expected(BEGIN_BMSG);
        }
        BmsgTokenizer.Property prop2 = parseProperties();
        while (prop2.equals(BEGIN_VCARD)) {
            StringBuilder vcard = new StringBuilder();
            prop2 = extractVcard(vcard);
            VCardEntry entry = parseVcard(vcard.toString());
            this.mBmsg.mOriginators.add(entry);
        }
        if (!prop2.equals(BEGIN_BENV)) {
            throw expected(BEGIN_BENV);
        }
        BmsgTokenizer.Property prop3 = parseEnvelope(1);
        if (!prop3.equals(END_BMSG)) {
            throw expected(END_BENV);
        }
        this.mParser = null;
    }

    private BmsgTokenizer.Property parseProperties() throws ParseException {
        BmsgTokenizer.Property prop;
        do {
            prop = this.mParser.next();
            if (prop.name.equals(VCardConstants.PROPERTY_VERSION)) {
                this.mBmsg.mBmsgVersion = prop.value;
            } else {
                int i = 0;
                if (prop.name.equals("STATUS")) {
                    Bmessage.Status[] statusArrValues = Bmessage.Status.values();
                    int length = statusArrValues.length;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        Bmessage.Status s = statusArrValues[i];
                        if (!prop.value.equals(s.toString())) {
                            i++;
                        } else {
                            this.mBmsg.mBmsgStatus = s;
                            break;
                        }
                    }
                } else if (prop.name.equals(VCardConstants.PARAM_TYPE)) {
                    Bmessage.Type[] typeArrValues = Bmessage.Type.values();
                    int length2 = typeArrValues.length;
                    while (true) {
                        if (i >= length2) {
                            break;
                        }
                        Bmessage.Type t = typeArrValues[i];
                        if (!prop.value.equals(t.toString())) {
                            i++;
                        } else {
                            this.mBmsg.mBmsgType = t;
                            break;
                        }
                    }
                } else if (prop.name.equals("FOLDER")) {
                    this.mBmsg.mBmsgFolder = prop.value;
                }
            }
            if (prop.equals(BEGIN_VCARD)) {
                break;
            }
        } while (!prop.equals(BEGIN_BENV));
        return prop;
    }

    private BmsgTokenizer.Property parseEnvelope(int level) throws IOException, ParseException {
        BmsgTokenizer.Property prop;
        if (level > 3) {
            throw new ParseException("bEnvelope is nested more than 3 times", this.mParser.pos());
        }
        BmsgTokenizer.Property prop2 = this.mParser.next();
        while (prop2.equals(BEGIN_VCARD)) {
            StringBuilder vcard = new StringBuilder();
            prop2 = extractVcard(vcard);
            if (level == 1) {
                VCardEntry entry = parseVcard(vcard.toString());
                this.mBmsg.mRecipients.add(entry);
            }
        }
        if (prop2.equals(BEGIN_BENV)) {
            prop = parseEnvelope(level + 1);
        } else {
            if (!prop2.equals(BEGIN_BBODY)) {
                throw expected(BEGIN_BENV, BEGIN_BBODY);
            }
            prop = parseBody();
        }
        if (prop.equals(END_BENV)) {
            return this.mParser.next();
        }
        throw expected(END_BENV);
    }

    private BmsgTokenizer.Property parseBody() throws IOException, ParseException {
        BmsgTokenizer.Property prop;
        do {
            prop = this.mParser.next();
            if (!prop.name.equals("PARTID")) {
                if (prop.name.equals(VCardConstants.PARAM_ENCODING)) {
                    this.mBmsg.mBbodyEncoding = prop.value;
                } else if (prop.name.equals(VCardConstants.PARAM_CHARSET)) {
                    this.mBmsg.mBbodyCharset = prop.value;
                } else if (prop.name.equals(VCardConstants.PARAM_LANGUAGE)) {
                    this.mBmsg.mBbodyLanguage = prop.value;
                } else if (prop.name.equals("LENGTH")) {
                    try {
                        this.mBmsg.mBbodyLength = Integer.parseInt(prop.value);
                    } catch (NumberFormatException e) {
                        throw new ParseException("Invalid LENGTH value", this.mParser.pos());
                    }
                }
            }
        } while (!prop.equals(BEGIN_MSG));
        if (!"UTF-8".equals(this.mBmsg.mBbodyCharset)) {
            Log.d(TAG, "The charset was not set to charset UTF-8: " + this.mBmsg.mBbodyCharset);
        }
        int messageLen = this.mBmsg.mBbodyLength - 22;
        int offset = messageLen + 2;
        int restartPos = this.mParser.pos() + offset;
        String remng = this.mParser.remaining();
        byte[] data = remng.getBytes();
        if (offset < 0 || offset > data.length) {
            throw new ParseException("Invalid LENGTH value", this.mParser.pos());
        }
        BmsgTokenizer bmsgTokenizer = new BmsgTokenizer(new String(data, offset, data.length - offset), restartPos);
        this.mParser = bmsgTokenizer;
        BmsgTokenizer.Property prop2 = bmsgTokenizer.next(true);
        if (prop2 != null) {
            if (prop2.equals(END_MSG)) {
                if ("UTF-8".equals(this.mBmsg.mBbodyCharset)) {
                    this.mBmsg.mMessage = new String(data, 0, messageLen, StandardCharsets.UTF_8);
                } else {
                    this.mBmsg.mMessage = new String(data, 0, messageLen);
                }
            } else {
                Log.e(TAG, "Prop Invalid: " + prop2.toString());
                Log.e(TAG, "Possible Invalid LENGTH value");
                throw expected(END_MSG);
            }
        } else {
            if (offset < 0 || offset > remng.length()) {
                throw new ParseException("Invalid LENGTH value", this.mParser.pos());
            }
            Log.w(TAG, "byte LENGTH seems to be invalid, trying with char length");
            BmsgTokenizer bmsgTokenizer2 = new BmsgTokenizer(remng.substring(offset));
            this.mParser = bmsgTokenizer2;
            if (!bmsgTokenizer2.next().equals(END_MSG)) {
                throw expected(END_MSG);
            }
            if ("UTF-8".equals(this.mBmsg.mBbodyCharset)) {
                this.mBmsg.mMessage = remng.substring(0, messageLen);
            } else {
                this.mBmsg.mMessage = null;
            }
        }
        if (this.mParser.next().equals(END_BBODY)) {
            return this.mParser.next();
        }
        throw expected(END_BBODY);
    }

    private BmsgTokenizer.Property extractVcard(StringBuilder out) throws IOException, ParseException {
        BmsgTokenizer.Property prop;
        out.append(BEGIN_VCARD);
        out.append("\r\n");
        do {
            prop = this.mParser.next();
            out.append(prop);
            out.append("\r\n");
        } while (!prop.equals(END_VCARD));
        return this.mParser.next();
    }

    private VCardEntry parseVcard(String str) throws IOException, ParseException {
        VCardEntry vcard = null;
        try {
            VCardParser_V21 vCardParser_V21 = new VCardParser_V21();
            VCardEntryConstructor c = new VCardEntryConstructor();
            VcardHandler handler = new VcardHandler();
            c.addEntryHandler(handler);
            vCardParser_V21.addInterpreter(c);
            vCardParser_V21.parse(new ByteArrayInputStream(str.getBytes()));
            vcard = handler.vcard;
        } catch (VCardVersionException e) {
            try {
                VCardParser_V30 vCardParser_V30 = new VCardParser_V30();
                VCardEntryConstructor c2 = new VCardEntryConstructor();
                VcardHandler handler2 = new VcardHandler();
                c2.addEntryHandler(handler2);
                vCardParser_V30.addInterpreter(c2);
                vCardParser_V30.parse(new ByteArrayInputStream(str.getBytes()));
                vcard = handler2.vcard;
            } catch (VCardVersionException e2) {
            } catch (VCardException e3) {
            }
        } catch (VCardException e4) {
        }
        if (vcard == null) {
            throw new ParseException("Cannot parse vCard object (neither 2.1 nor 3.0?)", this.mParser.pos());
        }
        return vcard;
    }

    private class VcardHandler implements VCardEntryHandler {
        public VCardEntry vcard;

        private VcardHandler() {
        }

        @Override // com.android.vcard.VCardEntryHandler
        public void onStart() {
        }

        @Override // com.android.vcard.VCardEntryHandler
        public void onEntryCreated(VCardEntry entry) {
            this.vcard = entry;
        }

        @Override // com.android.vcard.VCardEntryHandler
        public void onEnd() {
        }
    }
}
