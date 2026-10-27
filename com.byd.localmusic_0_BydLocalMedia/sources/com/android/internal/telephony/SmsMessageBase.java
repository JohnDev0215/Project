package com.android.internal.telephony;

import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Patterns;
import com.android.internal.telephony.GsmAlphabet;
import com.android.internal.telephony.SmsConstants;
import java.text.BreakIterator;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes4.dex */
public abstract class SmsMessageBase {
    public static final Pattern NAME_ADDR_EMAIL_PATTERN = Pattern.compile("\\s*(\"[^\"]*\"|[^<>\"]+)\\s*<([^<>]+)>\\s*");
    protected String mEmailBody;
    protected String mEmailFrom;
    protected boolean mIsEmail;
    protected boolean mIsMwi;
    protected String mMessageBody;
    public int mMessageRef;
    protected boolean mMwiDontStore;
    protected boolean mMwiSense;
    protected SmsAddress mOriginatingAddress;
    protected byte[] mPdu;
    protected String mPseudoSubject;
    protected SmsAddress mRecipientAddress;
    protected String mScAddress;
    protected long mScTimeMillis;
    protected byte[] mUserData;
    protected SmsHeader mUserDataHeader;
    protected int mStatusOnIcc = -1;
    protected int mIndexOnIcc = -1;

    private static boolean isRegionalIndicatorSymbol(int i) {
        return 127462 <= i && i <= 127487;
    }

    public abstract SmsConstants.MessageClass getMessageClass();

    public abstract int getProtocolIdentifier();

    public abstract int getStatus();

    public abstract boolean isCphsMwiMessage();

    public abstract boolean isMWIClearMessage();

    public abstract boolean isMWISetMessage();

    public abstract boolean isMwiDontStore();

    public abstract boolean isReplace();

    public abstract boolean isReplyPathPresent();

    public abstract boolean isStatusReportMessage();

    public static abstract class SubmitPduBase {
        public byte[] encodedMessage;
        public byte[] encodedScAddress;

        public String toString() {
            return "SubmitPdu: encodedScAddress = " + Arrays.toString(this.encodedScAddress) + ", encodedMessage = " + Arrays.toString(this.encodedMessage);
        }
    }

    public String getServiceCenterAddress() {
        return this.mScAddress;
    }

    public String getOriginatingAddress() {
        SmsAddress smsAddress = this.mOriginatingAddress;
        if (smsAddress == null) {
            return null;
        }
        return smsAddress.getAddressString();
    }

    public String getDisplayOriginatingAddress() {
        if (this.mIsEmail) {
            return this.mEmailFrom;
        }
        return getOriginatingAddress();
    }

    public String getMessageBody() {
        return this.mMessageBody;
    }

    public String getDisplayMessageBody() {
        if (this.mIsEmail) {
            return this.mEmailBody;
        }
        return getMessageBody();
    }

    public String getPseudoSubject() {
        String str = this.mPseudoSubject;
        return str == null ? "" : str;
    }

    public long getTimestampMillis() {
        return this.mScTimeMillis;
    }

    public boolean isEmail() {
        return this.mIsEmail;
    }

    public String getEmailBody() {
        return this.mEmailBody;
    }

    public String getEmailFrom() {
        return this.mEmailFrom;
    }

    public byte[] getUserData() {
        return this.mUserData;
    }

    public SmsHeader getUserDataHeader() {
        return this.mUserDataHeader;
    }

    public byte[] getPdu() {
        return this.mPdu;
    }

    public int getStatusOnIcc() {
        return this.mStatusOnIcc;
    }

    public int getIndexOnIcc() {
        return this.mIndexOnIcc;
    }

    protected void parseMessageBody() {
        SmsAddress smsAddress = this.mOriginatingAddress;
        if (smsAddress == null || !smsAddress.couldBeEmailGateway()) {
            return;
        }
        extractEmailAddressFromMessageBody();
    }

    private static String extractAddrSpec(String str) {
        Matcher matcher = NAME_ADDR_EMAIL_PATTERN.matcher(str);
        return matcher.matches() ? matcher.group(2) : str;
    }

    public static boolean isEmailAddress(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(extractAddrSpec(str)).matches();
    }

    protected void extractEmailAddressFromMessageBody() {
        String[] strArrSplit = this.mMessageBody.split("( /)|( )", 2);
        if (strArrSplit.length < 2) {
            return;
        }
        String str = strArrSplit[0];
        this.mEmailFrom = str;
        this.mEmailBody = strArrSplit[1];
        this.mIsEmail = isEmailAddress(str);
    }

    public static int findNextUnicodePosition(int i, int i2, CharSequence charSequence) {
        int iMin = Math.min((i2 / 2) + i, charSequence.length());
        if (iMin >= charSequence.length()) {
            return iMin;
        }
        BreakIterator characterInstance = BreakIterator.getCharacterInstance();
        characterInstance.setText(charSequence.toString());
        if (characterInstance.isBoundary(iMin)) {
            return iMin;
        }
        int iPreceding = characterInstance.preceding(iMin);
        while (true) {
            int i3 = iPreceding + 4;
            if (i3 > iMin || !isRegionalIndicatorSymbol(Character.codePointAt(charSequence, iPreceding)) || !isRegionalIndicatorSymbol(Character.codePointAt(charSequence, iPreceding + 2))) {
                break;
            }
            iPreceding = i3;
        }
        return iPreceding > i ? iPreceding : Character.isHighSurrogate(charSequence.charAt(iMin + (-1))) ? iMin - 1 : iMin;
    }

    public static GsmAlphabet.TextEncodingDetails calcUnicodeEncodingDetails(CharSequence charSequence) {
        GsmAlphabet.TextEncodingDetails textEncodingDetails = new GsmAlphabet.TextEncodingDetails();
        int length = charSequence.length() * 2;
        textEncodingDetails.codeUnitSize = 3;
        textEncodingDetails.codeUnitCount = charSequence.length();
        if (length > 140) {
            int i = 134;
            if (!SmsMessage.hasEmsSupport() && length <= 1188) {
                i = 132;
            }
            int i2 = 0;
            int i3 = 0;
            while (i2 < charSequence.length()) {
                int iFindNextUnicodePosition = findNextUnicodePosition(i2, i, charSequence);
                if (iFindNextUnicodePosition == charSequence.length()) {
                    textEncodingDetails.codeUnitsRemaining = (i2 + (i / 2)) - charSequence.length();
                }
                i3++;
                i2 = iFindNextUnicodePosition;
            }
            textEncodingDetails.msgCount = i3;
        } else {
            textEncodingDetails.msgCount = 1;
            textEncodingDetails.codeUnitsRemaining = (140 - length) / 2;
        }
        return textEncodingDetails;
    }

    public String getRecipientAddress() {
        SmsAddress smsAddress = this.mRecipientAddress;
        if (smsAddress == null) {
            return null;
        }
        return smsAddress.getAddressString();
    }
}
