package com.fasterxml.jackson.core;

/* JADX INFO: loaded from: classes.dex */
public enum JsonToken {
    /* JADX INFO: Fake field, exist only in values array */
    EF0(-1, null),
    c(1, "{"),
    e(2, "}"),
    k(3, "["),
    l(4, "]"),
    m(5, null),
    n(12, null),
    o(6, null),
    /* JADX INFO: Fake field, exist only in values array */
    EF8(7, null),
    f27p(8, null),
    q(9, "true"),
    r(10, "false"),
    s(11, "null");

    final int _id;
    final boolean _isBoolean;
    final boolean _isNumber;
    final boolean _isScalar;
    final boolean _isStructEnd;
    final boolean _isStructStart;
    final String _serialized;
    final byte[] _serializedBytes;
    final char[] _serializedChars;

    JsonToken(int i, String str) {
        boolean z = false;
        if (str == null) {
            this._serialized = null;
            this._serializedChars = null;
            this._serializedBytes = null;
        } else {
            this._serialized = str;
            char[] charArray = str.toCharArray();
            this._serializedChars = charArray;
            int length = charArray.length;
            this._serializedBytes = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                this._serializedBytes[i2] = (byte) this._serializedChars[i2];
            }
        }
        this._id = i;
        this._isBoolean = i == 10 || i == 9;
        this._isNumber = i == 7 || i == 8;
        boolean z2 = i == 1 || i == 3;
        this._isStructStart = z2;
        boolean z3 = i == 2 || i == 4;
        this._isStructEnd = z3;
        if (!z2 && !z3 && i != 5 && i != -1) {
            z = true;
        }
        this._isScalar = z;
    }
}
