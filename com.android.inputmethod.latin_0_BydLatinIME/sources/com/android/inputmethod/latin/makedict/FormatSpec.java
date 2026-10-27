package com.android.inputmethod.latin.makedict;

import java.util.Date;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class FormatSpec {
    static final int FLAG_BIGRAM_ATTR_ADDRESS_TYPE_ONEBYTE = 16;
    static final int FLAG_BIGRAM_ATTR_ADDRESS_TYPE_THREEBYTES = 48;
    static final int FLAG_BIGRAM_ATTR_ADDRESS_TYPE_TWOBYTES = 32;
    static final int FLAG_BIGRAM_ATTR_OFFSET_NEGATIVE = 64;
    static final int FLAG_BIGRAM_SHORTCUT_ATTR_FREQUENCY = 15;
    static final int FLAG_BIGRAM_SHORTCUT_ATTR_HAS_NEXT = 128;
    static final int FLAG_CHILDREN_ADDRESS_TYPE_NOADDRESS = 0;
    static final int FLAG_CHILDREN_ADDRESS_TYPE_ONEBYTE = 64;
    static final int FLAG_CHILDREN_ADDRESS_TYPE_THREEBYTES = 192;
    static final int FLAG_CHILDREN_ADDRESS_TYPE_TWOBYTES = 128;
    static final int FLAG_HAS_BIGRAMS = 4;
    static final int FLAG_HAS_MULTIPLE_CHARS = 32;
    static final int FLAG_HAS_SHORTCUT_TARGETS = 8;
    static final int FLAG_IS_NOT_A_WORD = 2;
    static final int FLAG_IS_POSSIBLY_OFFENSIVE = 1;
    static final int FLAG_IS_TERMINAL = 16;
    static final int INVALID_CHARACTER = -1;
    static final int LARGE_PTNODE_ARRAY_SIZE_FIELD_SIZE_FLAG = 32768;
    public static final int MAGIC_NUMBER = -1681835266;
    static final int MASK_BIGRAM_ATTR_ADDRESS_TYPE = 48;
    static final int MASK_CHILDREN_ADDRESS_TYPE = 192;
    static final int MAXIMAL_ONE_BYTE_CHARACTER_VALUE = 255;
    static final int MAXIMUM_SUPPORTED_DYNAMIC_VERSION = 403;
    public static final int MAXIMUM_SUPPORTED_STATIC_VERSION = 86736212;
    static final int MAX_BIGRAMS_IN_A_PTNODE = 10000;
    static final int MAX_BIGRAM_FREQUENCY = 15;
    static final int MAX_PTNODES_FOR_ONE_BYTE_PTNODE_COUNT = 127;
    static final int MAX_PTNODES_IN_A_PT_NODE_ARRAY = 32767;
    static final int MAX_SHORTCUT_LIST_SIZE_IN_A_PTNODE = 65535;
    static final int MAX_TERMINAL_FREQUENCY = 255;
    static final int MAX_WORD_LENGTH = 48;
    static final int MINIMAL_ONE_BYTE_CHARACTER_VALUE = 32;
    static final int MINIMUM_SUPPORTED_DYNAMIC_VERSION = 403;
    public static final int MINIMUM_SUPPORTED_STATIC_VERSION = 202;
    public static final int MINIMUM_SUPPORTED_VERSION_OF_CODE_POINT_TABLE = 201;
    static final int MSB8 = 128;
    static final int NOT_A_VERSION_NUMBER = -1;
    static final int NOT_VALID_WORD = -99;
    static final int NO_CHILDREN_ADDRESS = Integer.MIN_VALUE;
    static final int PTNODE_ATTRIBUTE_FLAGS_SIZE = 1;
    static final int PTNODE_ATTRIBUTE_MAX_ADDRESS_SIZE = 3;
    static final int PTNODE_CHARACTERS_TERMINATOR = 31;
    static final int PTNODE_FLAGS_SIZE = 1;
    static final int PTNODE_FREQUENCY_SIZE = 1;
    static final int PTNODE_MAX_ADDRESS_SIZE = 3;
    static final int PTNODE_SHORTCUT_LIST_SIZE_SIZE = 2;
    static final int PTNODE_TERMINATOR_SIZE = 1;
    public static final int SHORTCUT_WHITELIST_FREQUENCY = 15;
    static final int UINT16_MAX = 65535;
    static final int UINT24_MAX = 16777215;
    static final int UINT8_MAX = 255;
    public static final int VERSION2 = 2;
    public static final int VERSION201 = 201;
    public static final int VERSION202 = 202;
    public static final int VERSION4 = 403;
    public static final int VERSION402 = 402;
    public static final int VERSION403 = 403;
    public static final int VERSION4_ONLY_FOR_TESTING = 399;
    public static final int VERSION_DELIGHT3 = 86736212;

    public static final class FormatOptions {
        public final boolean mHasTimestamp;
        public final int mVersion;

        public FormatOptions(final int version) {
            this(version, false);
        }

        public FormatOptions(final int version, final boolean hasTimestamp) {
            this.mVersion = version;
            this.mHasTimestamp = hasTimestamp;
        }
    }

    public static final class DictionaryOptions {
        public final HashMap<String, String> mAttributes;

        public DictionaryOptions(final HashMap<String, String> attributes) {
            this.mAttributes = attributes;
        }

        public String toString() {
            return toString(0, false);
        }

        public String toString(final int indentCount, final boolean plumbing) {
            StringBuilder sb = new StringBuilder();
            if (plumbing) {
                sb.append("H:");
            } else {
                for (int i = 0; i < indentCount; i++) {
                    sb.append(" ");
                }
            }
            StringBuilder sb2 = new StringBuilder();
            for (String str : this.mAttributes.keySet()) {
                sb2.append((CharSequence) sb);
                sb2.append(str);
                sb2.append(" = ");
                if ("date".equals(str) && !plumbing) {
                    sb2.append(new Date(Long.parseLong(this.mAttributes.get(str)) * 1000).toString());
                } else {
                    sb2.append(this.mAttributes.get(str));
                }
                sb2.append("\n");
            }
            return sb2.toString();
        }
    }

    private FormatSpec() {
    }
}
