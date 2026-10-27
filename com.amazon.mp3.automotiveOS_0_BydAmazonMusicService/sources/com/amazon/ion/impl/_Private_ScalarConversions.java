package com.amazon.ion.impl;

import com.amazon.ion.Decimal;
import com.amazon.ion.IntegerSize;
import com.amazon.ion.IonException;
import com.amazon.ion.IonType;
import com.amazon.ion.Timestamp;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/* JADX INFO: loaded from: classes3.dex */
public class _Private_ScalarConversions {
    static int[] from_string_conversion = {-1, 1, 2, 3, 4, 5, 6, 7, 0, 8, 9, -1, -1};
    static int[] to_string_conversions = {-1, 10, 11, 12, 13, 14, 15, 16, 0, 17, 18, -1, -1};
    static int[] to_int_conversion = {-1, -1, -1, 0, 19, 20, 21, 22, 3, -1, -1, -1, -1};
    static int[] to_long_conversion = {-1, -1, -1, 23, 0, 24, 25, 26, 4, -1, -1, -1, -1};
    static int[] to_bigInteger_conversion = {-1, -1, -1, 27, 28, 0, 29, 30, 5, -1, -1, -1, -1};
    static int[] to_decimal_conversion = {-1, -1, -1, 31, 32, 33, 0, 34, 6, -1, -1, -1, -1};
    static int[] to_double_conversion = {-1, -1, -1, 35, 36, 37, 38, 0, 7, -1, -1, -1, -1};

    public static final class AS_TYPE {
        public static final int convertable_type;
        public static final int datetime_types;
        public static final int numeric_types;

        static final int idx_to_bit_mask(int i) {
            return 1 << (i - 1);
        }

        static {
            int iIdx_to_bit_mask = idx_to_bit_mask(3) | idx_to_bit_mask(4) | idx_to_bit_mask(5) | idx_to_bit_mask(6) | idx_to_bit_mask(7);
            numeric_types = iIdx_to_bit_mask;
            int iIdx_to_bit_mask2 = idx_to_bit_mask(9) | idx_to_bit_mask(10);
            datetime_types = iIdx_to_bit_mask2;
            convertable_type = idx_to_bit_mask(iIdx_to_bit_mask) | idx_to_bit_mask(iIdx_to_bit_mask2) | idx_to_bit_mask(2) | idx_to_bit_mask(8);
        }
    }

    public static IntegerSize getIntegerSize(int i) {
        if (i == 3) {
            return IntegerSize.INT;
        }
        if (i == 4) {
            return IntegerSize.LONG;
        }
        if (i != 5) {
            return null;
        }
        return IntegerSize.BIG_INTEGER;
    }

    public static String getValueTypeName(int i) {
        switch (i) {
            case 1:
                return "null";
            case 2:
                return "boolean";
            case 3:
                return "int";
            case 4:
                return "long";
            case 5:
                return "bigInteger";
            case 6:
                return "decimal";
            case 7:
                return "double";
            case 8:
                return "string";
            case 9:
                return "date";
            case 10:
                return TetheredMessageKey.timestamp;
            default:
                return "<unrecognized conversion value type: " + Integer.toString(i) + ">";
        }
    }

    protected static final int getConversionFnid(int i, int i2) {
        if (i2 == i) {
            return 0;
        }
        switch (i2) {
            case 1:
                return from_string_conversion[1];
            case 2:
                return from_string_conversion[2];
            case 3:
                return to_int_conversion[i];
            case 4:
                return to_long_conversion[i];
            case 5:
                return to_bigInteger_conversion[i];
            case 6:
                return to_decimal_conversion[i];
            case 7:
                return to_double_conversion[i];
            case 8:
                return to_string_conversions[i];
            case 9:
                return 39;
            case 10:
                return 40;
            default:
                throw new CantConvertException("can't convert from " + getValueTypeName(i) + " to " + getValueTypeName(i2));
        }
    }

    public static class ConversionException extends IonException {
        ConversionException(String str) {
            super(str);
        }
    }

    public static class CantConvertException extends ConversionException {
        CantConvertException(String str) {
            super(str);
        }
    }

    public static class ValueNotSetException extends ConversionException {
        ValueNotSetException(String str) {
            super(str);
        }
    }

    public static final class ValueVariant {
        int _authoritative_type_idx;
        BigInteger _bigInteger_value;
        boolean _boolean_value;
        Date _date_value;
        Decimal _decimal_value;
        double _double_value;
        int _int_value;
        boolean _is_null;
        long _long_value;
        IonType _null_type;
        String _string_value;
        Timestamp _timestamp_value;
        int _types_set;
        private static final BigInteger min_int_value = BigInteger.valueOf(-2147483648L);
        private static final BigInteger max_int_value = BigInteger.valueOf(2147483647L);
        private static final BigInteger min_long_value = BigInteger.valueOf(Long.MIN_VALUE);
        private static final BigInteger max_long_value = BigInteger.valueOf(Long.MAX_VALUE);
        private static final BigDecimal min_int_decimal_value = BigDecimal.valueOf(-2147483648L);
        private static final BigDecimal max_int_decimal_value = BigDecimal.valueOf(2147483647L);
        private static final BigDecimal min_long_decimal_value = BigDecimal.valueOf(Long.MIN_VALUE);
        private static final BigDecimal max_long_decimal_value = BigDecimal.valueOf(Long.MAX_VALUE);

        public final boolean isEmpty() {
            return this._authoritative_type_idx == 0;
        }

        public final void clear() {
            this._authoritative_type_idx = 0;
            this._types_set = 0;
        }

        public final boolean hasValueOfType(int i) {
            return (AS_TYPE.idx_to_bit_mask(i) & this._types_set) != 0;
        }

        public static final boolean isNumericType(int i) {
            return (AS_TYPE.idx_to_bit_mask(i) & AS_TYPE.numeric_types) != 0;
        }

        public final void setAuthoritativeType(int i) {
            if (this._authoritative_type_idx == i) {
                return;
            }
            if (!hasValueOfType(i)) {
                throw new IllegalStateException("you must set the " + _Private_ScalarConversions.getValueTypeName(i) + " value before you can set the authoritative type to " + _Private_ScalarConversions.getValueTypeName(i));
            }
            this._authoritative_type_idx = i;
        }

        public final void setValueToNull(IonType ionType) {
            this._is_null = true;
            this._null_type = ionType;
            set_value_type(1);
        }

        public final void setValue(boolean z) {
            this._boolean_value = z;
            set_value_type(2);
        }

        public final void setValue(int i) {
            this._int_value = i;
            set_value_type(3);
        }

        public final void setValue(long j) {
            this._long_value = j;
            set_value_type(4);
        }

        public final void setValue(double d) {
            this._double_value = d;
            set_value_type(7);
        }

        public final void setValue(String str) {
            this._string_value = str;
            set_value_type(8);
        }

        public final void setValue(BigInteger bigInteger) {
            this._bigInteger_value = bigInteger;
            set_value_type(5);
        }

        public final void setValue(Decimal decimal) {
            this._decimal_value = decimal;
            set_value_type(6);
        }

        public final void setValue(Timestamp timestamp) {
            this._timestamp_value = timestamp;
            set_value_type(10);
        }

        public final void addValue(int i) {
            this._int_value = i;
            add_value_type(3);
        }

        public final void addValue(long j) {
            this._long_value = j;
            add_value_type(4);
        }

        public final void addValue(double d) {
            this._double_value = d;
            add_value_type(7);
        }

        public final void addValue(String str) {
            this._string_value = str;
            add_value_type(8);
        }

        public final void addValue(Decimal decimal) {
            this._decimal_value = decimal;
            add_value_type(6);
        }

        public final int getAuthoritativeType() {
            return this._authoritative_type_idx;
        }

        public final boolean isNull() {
            return hasValueOfType(1);
        }

        public final boolean getBoolean() {
            if (!hasValueOfType(2)) {
                throw new ValueNotSetException("boolean not set");
            }
            return this._boolean_value;
        }

        public final int getInt() {
            if (!hasValueOfType(3)) {
                throw new ValueNotSetException("int value not set");
            }
            return this._int_value;
        }

        public final long getLong() {
            if (!hasValueOfType(4)) {
                throw new ValueNotSetException("long value not set");
            }
            return this._long_value;
        }

        public final double getDouble() {
            if (!hasValueOfType(7)) {
                throw new ValueNotSetException("double value not set");
            }
            return this._double_value;
        }

        public final String getString() {
            if (!hasValueOfType(8)) {
                throw new ValueNotSetException("String value not set");
            }
            return this._string_value;
        }

        public final BigInteger getBigInteger() {
            if (!hasValueOfType(5)) {
                throw new ValueNotSetException("BigInteger value not set");
            }
            return this._bigInteger_value;
        }

        public final Decimal getDecimal() {
            if (!hasValueOfType(6)) {
                throw new ValueNotSetException("BigDecimal value not set");
            }
            return this._decimal_value;
        }

        public final Timestamp getTimestamp() {
            if (!hasValueOfType(10)) {
                throw new ValueNotSetException("timestamp value not set");
            }
            return this._timestamp_value;
        }

        public final boolean can_convert(int i) {
            switch (i) {
                case 1:
                case 2:
                    return this._authoritative_type_idx == 8;
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    return is_numeric_type(this._authoritative_type_idx) || this._authoritative_type_idx == 8;
                case 8:
                    return true;
                case 9:
                case 10:
                    return is_datetime_type(this._authoritative_type_idx) || this._authoritative_type_idx == 8;
                default:
                    return false;
            }
        }

        public final int get_conversion_fnid(int i) {
            return _Private_ScalarConversions.getConversionFnid(this._authoritative_type_idx, i);
        }

        public final void cast(int i) {
            switch (i) {
                case 1:
                    fn_from_string_to_null();
                    return;
                case 2:
                    fn_from_string_to_boolean();
                    return;
                case 3:
                    fn_from_string_to_int();
                    return;
                case 4:
                    fn_from_string_to_long();
                    return;
                case 5:
                    fn_from_string_to_biginteger();
                    return;
                case 6:
                    fn_from_string_to_decimal();
                    return;
                case 7:
                    fn_from_string_to_double();
                    return;
                case 8:
                    fn_from_string_to_date();
                    return;
                case 9:
                    fn_from_string_to_timestamp();
                    return;
                case 10:
                    fn_from_null_to_string();
                    return;
                case 11:
                    fn_from_boolean_to_string();
                    return;
                case 12:
                    fn_from_int_to_string();
                    return;
                case 13:
                    fn_from_long_to_string();
                    return;
                case 14:
                    fn_from_biginteger_to_string();
                    return;
                case 15:
                    fn_from_decimal_to_string();
                    return;
                case 16:
                    fn_from_double_to_string();
                    return;
                case 17:
                    fn_from_date_to_string();
                    return;
                case 18:
                    fn_from_timestamp_to_string();
                    return;
                case 19:
                    fn_from_long_to_int();
                    return;
                case 20:
                    fn_from_biginteger_to_int();
                    return;
                case 21:
                    fn_from_decimal_to_int();
                    return;
                case 22:
                    fn_from_double_to_int();
                    return;
                case 23:
                    fn_from_int_to_long();
                    return;
                case 24:
                    fn_from_biginteger_to_long();
                    return;
                case 25:
                    fn_from_decimal_to_long();
                    return;
                case 26:
                    fn_from_double_to_long();
                    return;
                case 27:
                    fn_from_int_to_biginteger();
                    return;
                case 28:
                    fn_from_long_to_biginteger();
                    return;
                case 29:
                    fn_from_decimal_to_biginteger();
                    return;
                case 30:
                    fn_from_double_to_biginteger();
                    return;
                case 31:
                    fn_from_int_to_decimal();
                    return;
                case 32:
                    fn_from_long_to_decimal();
                    return;
                case 33:
                    fn_from_biginteger_to_decimal();
                    return;
                case 34:
                    fn_from_double_to_decimal();
                    return;
                case 35:
                    fn_from_int_to_double();
                    return;
                case 36:
                    fn_from_long_to_double();
                    return;
                case 37:
                    fn_from_biginteger_to_double();
                    return;
                case 38:
                    fn_from_decimal_to_double();
                    return;
                case 39:
                    fn_from_timestamp_to_date();
                    return;
                case 40:
                    fn_from_date_to_timestamp();
                    return;
                default:
                    throw new ConversionException("unrecognized conversion fnid [" + i + "]invoked");
            }
        }

        private final void set_value_type(int i) {
            this._types_set = AS_TYPE.idx_to_bit_mask(i);
            this._authoritative_type_idx = i;
        }

        private final void add_value_type(int i) {
            this._types_set = AS_TYPE.idx_to_bit_mask(i) | this._types_set;
        }

        private final boolean is_numeric_type(int i) {
            return (AS_TYPE.idx_to_bit_mask(i) & AS_TYPE.numeric_types) != 0;
        }

        private final boolean is_datetime_type(int i) {
            return (AS_TYPE.idx_to_bit_mask(i) & AS_TYPE.datetime_types) != 0;
        }

        private final void fn_from_string_to_null() {
            this._null_type = IonTokenConstsX.getNullType(this._string_value);
            this._is_null = true;
            add_value_type(1);
        }

        private final void fn_from_string_to_boolean() {
            this._boolean_value = Boolean.parseBoolean(this._string_value);
            add_value_type(2);
        }

        private final void fn_from_string_to_int() {
            this._int_value = Integer.parseInt(this._string_value);
            add_value_type(3);
        }

        private final void fn_from_string_to_long() {
            this._long_value = Long.parseLong(this._string_value);
            add_value_type(4);
        }

        private final void fn_from_string_to_biginteger() {
            this._bigInteger_value = new BigInteger(this._string_value);
            add_value_type(5);
        }

        private final void fn_from_string_to_decimal() {
            this._decimal_value = Decimal.valueOf(this._string_value);
            add_value_type(6);
        }

        private final void fn_from_string_to_double() {
            this._double_value = Double.parseDouble(this._string_value);
            add_value_type(7);
        }

        private final void fn_from_string_to_date() {
            if (!hasValueOfType(10)) {
                fn_from_string_to_timestamp();
            }
            this._date_value = new Date(this._timestamp_value.getMillis());
            add_value_type(9);
        }

        private final void fn_from_string_to_timestamp() {
            this._timestamp_value = Timestamp.valueOf(this._string_value);
            add_value_type(10);
            add_value_type(10);
        }

        private final void fn_from_null_to_string() {
            this._string_value = IonTokenConstsX.getNullImage(this._null_type);
            add_value_type(8);
        }

        private final void fn_from_boolean_to_string() {
            this._string_value = this._boolean_value ? "true" : "false";
            add_value_type(8);
        }

        private final void fn_from_int_to_string() {
            this._string_value = Integer.toString(this._int_value);
            add_value_type(8);
        }

        private final void fn_from_long_to_string() {
            this._string_value = Long.toString(this._long_value);
            add_value_type(8);
        }

        private final void fn_from_biginteger_to_string() {
            this._string_value = this._bigInteger_value.toString();
            add_value_type(8);
        }

        private final void fn_from_decimal_to_string() {
            this._string_value = this._decimal_value.toString();
            add_value_type(8);
        }

        private final void fn_from_double_to_string() {
            this._string_value = Double.toString(this._double_value);
            add_value_type(8);
        }

        private final void fn_from_date_to_string() {
            if (!hasValueOfType(10)) {
                fn_from_date_to_timestamp();
            }
            fn_from_timestamp_to_string();
        }

        private final void fn_from_timestamp_to_string() {
            this._string_value = this._timestamp_value.toString();
            add_value_type(8);
        }

        private final void fn_from_long_to_int() {
            long j = this._long_value;
            if (j < -2147483648L || j > 2147483647L) {
                throw new CantConvertException("long is too large to fit in an int");
            }
            this._int_value = (int) j;
            add_value_type(3);
        }

        private final void fn_from_biginteger_to_int() {
            if (min_int_value.compareTo(this._bigInteger_value) > 0 || max_int_value.compareTo(this._bigInteger_value) < 0) {
                throw new CantConvertException("bigInteger value is too large to fit in an int");
            }
            this._int_value = this._bigInteger_value.intValue();
            add_value_type(3);
        }

        private final void fn_from_decimal_to_int() {
            if (min_int_decimal_value.compareTo((BigDecimal) this._decimal_value) > 0 || max_int_decimal_value.compareTo((BigDecimal) this._decimal_value) < 0) {
                throw new CantConvertException("BigDecimal value is too large to fit in an int");
            }
            this._int_value = this._decimal_value.intValue();
            add_value_type(3);
        }

        private final void fn_from_double_to_int() {
            double d = this._double_value;
            if (d < -2.147483648E9d || d > 2.147483647E9d) {
                throw new CantConvertException("double is too large to fit in an int");
            }
            this._int_value = (int) d;
            add_value_type(3);
        }

        private final void fn_from_int_to_long() {
            this._long_value = this._int_value;
            add_value_type(4);
        }

        private final void fn_from_biginteger_to_long() {
            if (min_long_value.compareTo(this._bigInteger_value) > 0 || max_long_value.compareTo(this._bigInteger_value) < 0) {
                throw new CantConvertException("BigInteger is too large to fit in a long");
            }
            this._long_value = this._bigInteger_value.longValue();
            add_value_type(4);
        }

        private final void fn_from_decimal_to_long() {
            if (min_long_decimal_value.compareTo((BigDecimal) this._decimal_value) > 0 || max_long_decimal_value.compareTo((BigDecimal) this._decimal_value) < 0) {
                throw new CantConvertException("BigDecimal value is too large to fit in a long");
            }
            this._long_value = this._decimal_value.longValue();
            add_value_type(4);
        }

        private final void fn_from_double_to_long() {
            double d = this._double_value;
            if (d < -9.223372036854776E18d || d > 9.223372036854776E18d) {
                throw new CantConvertException("double is too large to fit in a long");
            }
            this._long_value = (long) d;
            add_value_type(4);
        }

        private final void fn_from_int_to_biginteger() {
            this._bigInteger_value = BigInteger.valueOf(this._int_value);
            add_value_type(5);
        }

        private final void fn_from_long_to_biginteger() {
            this._bigInteger_value = BigInteger.valueOf(this._long_value);
            add_value_type(5);
        }

        private final void fn_from_decimal_to_biginteger() {
            this._bigInteger_value = this._decimal_value.toBigInteger();
            add_value_type(5);
        }

        private final void fn_from_double_to_biginteger() {
            this._bigInteger_value = Decimal.valueOf(this._double_value).toBigInteger();
            add_value_type(5);
        }

        private final void fn_from_int_to_decimal() {
            this._decimal_value = Decimal.valueOf(this._int_value);
            add_value_type(6);
        }

        private final void fn_from_long_to_decimal() {
            this._decimal_value = Decimal.valueOf(this._long_value);
            add_value_type(6);
        }

        private final void fn_from_biginteger_to_decimal() {
            this._decimal_value = Decimal.valueOf(this._bigInteger_value);
            add_value_type(6);
        }

        private final void fn_from_double_to_decimal() {
            this._decimal_value = Decimal.valueOf(this._double_value);
            add_value_type(6);
        }

        private final void fn_from_int_to_double() {
            this._double_value = this._int_value;
            add_value_type(7);
        }

        private final void fn_from_long_to_double() {
            this._double_value = this._long_value;
            add_value_type(7);
        }

        private final void fn_from_biginteger_to_double() {
            this._double_value = this._bigInteger_value.doubleValue();
            add_value_type(7);
        }

        private final void fn_from_decimal_to_double() {
            this._double_value = this._decimal_value.doubleValue();
            add_value_type(7);
        }

        private final void fn_from_timestamp_to_date() {
            this._date_value = this._timestamp_value.dateValue();
            add_value_type(9);
        }

        private final void fn_from_date_to_timestamp() {
            this._timestamp_value = new Timestamp(this._date_value.getTime(), (Integer) null);
            add_value_type(10);
        }
    }
}
