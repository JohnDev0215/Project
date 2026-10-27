package com.amazon.weblab.mobile.repository;

import com.amazon.music.destination.parser.ParserUtil;
import com.amplifyframework.core.model.ModelIdentifier;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes5.dex */
public class LazyJSONKeys {
    public static byte[] APP_VERSION;
    public static byte[] CAN_TRIGGER;
    public static byte COLON;
    public static byte[] DATE_MODIFIED;
    public static byte FALSE_PREFIX;
    public static byte[] IS_LOCKED;
    public static byte[] KEEP_IN_CACHE;
    public static byte LEFT_BRACKET;
    public static byte LEFT_CURLY_BRACKET;
    public static byte[] MARKETPLACE_ID;
    public static byte NINE;
    public static byte QUOTE;
    public static byte RIGHT_BRACKET;
    public static byte RIGHT_CURLY_BRACKET;
    public static byte[] SESSION_ID;
    public static byte[] SUGGESTED_EXPIRATION;
    public static byte[] TREATMENT;
    public static byte[] TREATMENT_ASSIGNMENTS;
    public static byte TRUE_PREFIX;
    private static final Charset UTF8_CHARSET;
    public static byte[] VERSION;
    public static byte[] WEBLAB;
    public static byte ZERO;

    static {
        Charset charsetForName = Charset.forName(ParserUtil.UTF_8);
        UTF8_CHARSET = charsetForName;
        APP_VERSION = "\"app_version\"".getBytes(charsetForName);
        SESSION_ID = "\"session_id\"".getBytes(charsetForName);
        MARKETPLACE_ID = "\"marketplace_id\"".getBytes(charsetForName);
        TREATMENT_ASSIGNMENTS = "\"treatment_assignments\"".getBytes(charsetForName);
        WEBLAB = "\"weblab\"".getBytes(charsetForName);
        CAN_TRIGGER = "\"can_trigger\"".getBytes(charsetForName);
        DATE_MODIFIED = "\"date_modified\"".getBytes(charsetForName);
        SUGGESTED_EXPIRATION = "\"suggested_expiration\"".getBytes(charsetForName);
        KEEP_IN_CACHE = "\"keep_in_cache_date_in_millis\"".getBytes(charsetForName);
        TREATMENT = "\"treatment\"".getBytes(charsetForName);
        IS_LOCKED = "\"is_locked\"".getBytes(charsetForName);
        VERSION = "\"version\"".getBytes(charsetForName);
        QUOTE = ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR.getBytes(charsetForName)[0];
        COLON = ":".getBytes(charsetForName)[0];
        LEFT_BRACKET = "[".getBytes(charsetForName)[0];
        RIGHT_BRACKET = "]".getBytes(charsetForName)[0];
        LEFT_CURLY_BRACKET = "{".getBytes(charsetForName)[0];
        RIGHT_CURLY_BRACKET = "}".getBytes(charsetForName)[0];
        TRUE_PREFIX = "true".getBytes(charsetForName)[0];
        FALSE_PREFIX = "false".getBytes(charsetForName)[0];
        ZERO = "0".getBytes(charsetForName)[0];
        NINE = "9".getBytes(charsetForName)[0];
    }
}
