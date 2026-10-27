package com.android.vcard;

import android.util.Log;
import com.android.vcard.exception.VCardException;
import java.io.IOException;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
class VCardParserImpl_V30 extends VCardParserImpl_V21 {
    private static final String LOG_TAG = "vCard";
    private boolean mEmittedAgentWarning;
    private String mPreviousLine;

    public VCardParserImpl_V30() {
        this.mEmittedAgentWarning = false;
    }

    public VCardParserImpl_V30(int vcardType) {
        super(vcardType);
        this.mEmittedAgentWarning = false;
    }

    @Override // com.android.vcard.VCardParserImpl_V21
    protected int getVersion() {
        return 1;
    }

    @Override // com.android.vcard.VCardParserImpl_V21
    protected String getVersionString() {
        return VCardConstants.VERSION_V30;
    }

    @Override // com.android.vcard.VCardParserImpl_V21
    protected String peekLine() throws IOException {
        if (this.mPreviousLine != null) {
            String ret = this.mPreviousLine;
            return ret;
        }
        return this.mReader.peekLine();
    }

    @Override // com.android.vcard.VCardParserImpl_V21
    protected String getLine() throws IOException {
        if (this.mPreviousLine != null) {
            String ret = this.mPreviousLine;
            this.mPreviousLine = null;
            return ret;
        }
        return this.mReader.readLine();
    }

    @Override // com.android.vcard.VCardParserImpl_V21
    protected String getNonEmptyLine() throws VCardException, IOException {
        String line;
        StringBuilder builder = null;
        while (true) {
            line = this.mReader.readLine();
            if (line == null) {
                break;
            }
            if (line.length() != 0) {
                if (line.charAt(0) == ' ' || line.charAt(0) == '\t') {
                    if (builder == null) {
                        builder = new StringBuilder();
                    }
                    String str = this.mPreviousLine;
                    if (str != null) {
                        builder.append(str);
                        this.mPreviousLine = null;
                    }
                    builder.append(line.substring(1));
                } else {
                    if (builder != null || this.mPreviousLine != null) {
                        break;
                    }
                    this.mPreviousLine = line;
                }
            }
        }
        String ret = null;
        if (builder != null) {
            ret = builder.toString();
        } else if (this.mPreviousLine != null) {
            ret = this.mPreviousLine;
        }
        this.mPreviousLine = line;
        if (ret == null) {
            throw new VCardException("Reached end of buffer.");
        }
        return ret;
    }

    @Override // com.android.vcard.VCardParserImpl_V21
    protected boolean readBeginVCard(boolean allowGarbage) throws VCardException, IOException {
        return super.readBeginVCard(allowGarbage);
    }

    @Override // com.android.vcard.VCardParserImpl_V21
    protected void handleParams(VCardProperty propertyData, String params) throws VCardException {
        try {
            super.handleParams(propertyData, params);
        } catch (VCardException e) {
            String[] strArray = params.split("=", 2);
            if (strArray.length == 2) {
                handleAnyParam(propertyData, strArray[0], strArray[1]);
                return;
            }
            throw new VCardException("Unknown params value: " + params);
        }
    }

    @Override // com.android.vcard.VCardParserImpl_V21
    protected void handleAnyParam(VCardProperty propertyData, String paramName, String paramValue) {
        splitAndPutParam(propertyData, paramName, paramValue);
    }

    @Override // com.android.vcard.VCardParserImpl_V21
    protected void handleParamWithoutName(VCardProperty property, String paramValue) {
        handleType(property, paramValue);
    }

    @Override // com.android.vcard.VCardParserImpl_V21
    protected void handleType(VCardProperty property, String paramValue) {
        splitAndPutParam(property, VCardConstants.PARAM_TYPE, paramValue);
    }

    private void splitAndPutParam(VCardProperty property, String paramName, String paramValue) {
        StringBuilder builder = null;
        boolean insideDquote = false;
        int length = paramValue.length();
        for (int i = 0; i < length; i++) {
            char ch = paramValue.charAt(i);
            if (ch == '\"') {
                if (insideDquote) {
                    property.addParameter(paramName, encodeParamValue(builder.toString()));
                    builder = null;
                    insideDquote = false;
                } else {
                    if (builder != null) {
                        if (builder.length() > 0) {
                            Log.w(LOG_TAG, "Unexpected Dquote inside property.");
                        } else {
                            property.addParameter(paramName, encodeParamValue(builder.toString()));
                        }
                    }
                    insideDquote = true;
                }
            } else if (ch == ',' && !insideDquote) {
                if (builder == null) {
                    Log.w(LOG_TAG, "Comma is used before actual string comes. (" + paramValue + ")");
                } else {
                    property.addParameter(paramName, encodeParamValue(builder.toString()));
                    builder = null;
                }
            } else {
                if (builder == null) {
                    builder = new StringBuilder();
                }
                builder.append(ch);
            }
        }
        if (insideDquote) {
            Log.d(LOG_TAG, "Dangling Dquote.");
        }
        if (builder != null) {
            if (builder.length() == 0) {
                Log.w(LOG_TAG, "Unintended behavior. We must not see empty StringBuilder at the end of parameter value parsing.");
            } else {
                property.addParameter(paramName, encodeParamValue(builder.toString()));
            }
        }
    }

    protected String encodeParamValue(String paramValue) {
        return VCardUtils.convertStringCharset(paramValue, VCardConfig.DEFAULT_INTERMEDIATE_CHARSET, "UTF-8");
    }

    @Override // com.android.vcard.VCardParserImpl_V21
    protected void handleAgent(VCardProperty property) {
        if (!this.mEmittedAgentWarning) {
            Log.w(LOG_TAG, "AGENT in vCard 3.0 is not supported yet. Ignore it");
            this.mEmittedAgentWarning = true;
        }
    }

    @Override // com.android.vcard.VCardParserImpl_V21
    protected String getBase64(String firstString) throws VCardException, IOException {
        return firstString;
    }

    @Override // com.android.vcard.VCardParserImpl_V21
    protected String maybeUnescapeText(String text) {
        return unescapeText(text);
    }

    public static String unescapeText(String text) {
        StringBuilder builder = new StringBuilder();
        int length = text.length();
        int i = 0;
        while (i < length) {
            char ch = text.charAt(i);
            if (ch == '\\' && i < length - 1) {
                i++;
                char next_ch = text.charAt(i);
                if (next_ch == 'n' || next_ch == 'N') {
                    builder.append("\n");
                } else {
                    builder.append(next_ch);
                }
            } else {
                builder.append(ch);
            }
            i++;
        }
        return builder.toString();
    }

    @Override // com.android.vcard.VCardParserImpl_V21
    protected String maybeUnescapeCharacter(char ch) {
        return unescapeCharacter(ch);
    }

    public static String unescapeCharacter(char ch) {
        if (ch == 'n' || ch == 'N') {
            return "\n";
        }
        return String.valueOf(ch);
    }

    @Override // com.android.vcard.VCardParserImpl_V21
    protected Set<String> getKnownPropertyNameSet() {
        return VCardParser_V30.sKnownPropertyNameSet;
    }
}
