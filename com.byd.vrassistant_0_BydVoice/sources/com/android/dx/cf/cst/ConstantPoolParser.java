package com.android.dx.cf.cst;

import com.android.dx.cf.iface.ParseException;
import com.android.dx.cf.iface.ParseObserver;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstDouble;
import com.android.dx.rop.cst.CstFieldRef;
import com.android.dx.rop.cst.CstFloat;
import com.android.dx.rop.cst.CstInteger;
import com.android.dx.rop.cst.CstInterfaceMethodRef;
import com.android.dx.rop.cst.CstLong;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.cst.StdConstantPool;
import com.android.dx.rop.type.Type;
import com.android.dx.util.ByteArray;
import com.android.dx.util.Hex;
import java.util.BitSet;

/* JADX INFO: loaded from: classes.dex */
public final class ConstantPoolParser {
    private final ByteArray bytes;
    private int endOffset;
    private ParseObserver observer;
    private final int[] offsets;
    private final StdConstantPool pool;

    public ConstantPoolParser(ByteArray byteArray) {
        int unsignedShort = byteArray.getUnsignedShort(8);
        this.bytes = byteArray;
        this.pool = new StdConstantPool(unsignedShort);
        this.offsets = new int[unsignedShort];
        this.endOffset = -1;
    }

    public void setObserver(ParseObserver parseObserver) {
        this.observer = parseObserver;
    }

    public int getEndOffset() {
        parseIfNecessary();
        return this.endOffset;
    }

    public StdConstantPool getPool() {
        parseIfNecessary();
        return this.pool;
    }

    private void parseIfNecessary() {
        if (this.endOffset < 0) {
            parse();
        }
    }

    private void parse() {
        String str;
        determineOffsets();
        ParseObserver parseObserver = this.observer;
        if (parseObserver != null) {
            parseObserver.parsed(this.bytes, 8, 2, "constant_pool_count: " + Hex.u2(this.offsets.length));
            this.observer.parsed(this.bytes, 10, 0, "\nconstant_pool:");
            this.observer.changeIndent(1);
        }
        BitSet bitSet = new BitSet(this.offsets.length);
        int i = 1;
        while (true) {
            int[] iArr = this.offsets;
            if (i >= iArr.length) {
                break;
            }
            if (iArr[i] != 0 && this.pool.getOrNull(i) == null) {
                parse0(i, bitSet);
            }
            i++;
        }
        if (this.observer != null) {
            for (int i2 = 1; i2 < this.offsets.length; i2++) {
                Constant orNull = this.pool.getOrNull(i2);
                if (orNull != null) {
                    int i3 = this.offsets[i2];
                    int i4 = this.endOffset;
                    int i5 = i2 + 1;
                    while (true) {
                        int[] iArr2 = this.offsets;
                        if (i5 >= iArr2.length) {
                            break;
                        }
                        int i6 = iArr2[i5];
                        if (i6 != 0) {
                            i4 = i6;
                            break;
                        }
                        i5++;
                    }
                    if (bitSet.get(i2)) {
                        str = Hex.u2(i2) + ": utf8{\"" + orNull.toHuman() + "\"}";
                    } else {
                        str = Hex.u2(i2) + ": " + orNull.toString();
                    }
                    this.observer.parsed(this.bytes, i3, i4 - i3, str);
                }
            }
            this.observer.changeIndent(-1);
            this.observer.parsed(this.bytes, this.endOffset, 0, "end constant_pool");
        }
    }

    private void determineOffsets() {
        int i;
        int unsignedShort = 10;
        int i2 = 1;
        while (true) {
            int[] iArr = this.offsets;
            if (i2 < iArr.length) {
                iArr[i2] = unsignedShort;
                int unsignedByte = this.bytes.getUnsignedByte(unsignedShort);
                switch (unsignedByte) {
                    case 1:
                        unsignedShort += this.bytes.getUnsignedShort(unsignedShort + 1) + 3;
                        break;
                    case 2:
                    case 13:
                    case 14:
                    case 17:
                    default:
                        throw new ParseException("unknown tag byte: " + Hex.u1(unsignedByte));
                    case 3:
                    case 4:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                        unsignedShort += 5;
                        break;
                    case 5:
                    case 6:
                        unsignedShort += 9;
                        i = 2;
                        continue;
                        i2 += i;
                        break;
                    case 7:
                    case 8:
                        try {
                            unsignedShort += 3;
                        } catch (ParseException e) {
                            e.addContext("...while preparsing cst " + Hex.u2(i2) + " at offset " + Hex.u4(unsignedShort));
                            throw e;
                        }
                        break;
                    case 15:
                        throw new ParseException("MethodHandle not supported");
                    case 16:
                        throw new ParseException("MethodType not supported");
                    case 18:
                        throw new ParseException("InvokeDynamic not supported");
                }
                i = 1;
                i2 += i;
            } else {
                this.endOffset = unsignedShort;
                return;
            }
        }
    }

    private Constant parse0(int i, BitSet bitSet) {
        Constant utf8;
        Constant orNull = this.pool.getOrNull(i);
        if (orNull != null) {
            return orNull;
        }
        int i2 = this.offsets[i];
        try {
            int unsignedByte = this.bytes.getUnsignedByte(i2);
            switch (unsignedByte) {
                case 1:
                    utf8 = parseUtf8(i2);
                    bitSet.set(i);
                    break;
                case 2:
                case 13:
                case 14:
                case 17:
                default:
                    throw new ParseException("unknown tag byte: " + Hex.u1(unsignedByte));
                case 3:
                    utf8 = CstInteger.make(this.bytes.getInt(i2 + 1));
                    break;
                case 4:
                    utf8 = CstFloat.make(this.bytes.getInt(i2 + 1));
                    break;
                case 5:
                    utf8 = CstLong.make(this.bytes.getLong(i2 + 1));
                    break;
                case 6:
                    utf8 = CstDouble.make(this.bytes.getLong(i2 + 1));
                    break;
                case 7:
                    utf8 = new CstType(Type.internClassName(((CstString) parse0(this.bytes.getUnsignedShort(i2 + 1), bitSet)).getString()));
                    break;
                case 8:
                    utf8 = parse0(this.bytes.getUnsignedShort(i2 + 1), bitSet);
                    break;
                case 9:
                    utf8 = new CstFieldRef((CstType) parse0(this.bytes.getUnsignedShort(i2 + 1), bitSet), (CstNat) parse0(this.bytes.getUnsignedShort(i2 + 3), bitSet));
                    break;
                case 10:
                    utf8 = new CstMethodRef((CstType) parse0(this.bytes.getUnsignedShort(i2 + 1), bitSet), (CstNat) parse0(this.bytes.getUnsignedShort(i2 + 3), bitSet));
                    break;
                case 11:
                    utf8 = new CstInterfaceMethodRef((CstType) parse0(this.bytes.getUnsignedShort(i2 + 1), bitSet), (CstNat) parse0(this.bytes.getUnsignedShort(i2 + 3), bitSet));
                    break;
                case 12:
                    utf8 = new CstNat((CstString) parse0(this.bytes.getUnsignedShort(i2 + 1), bitSet), (CstString) parse0(this.bytes.getUnsignedShort(i2 + 3), bitSet));
                    break;
                case 15:
                    throw new ParseException("MethodHandle not supported");
                case 16:
                    throw new ParseException("MethodType not supported");
                case 18:
                    throw new ParseException("InvokeDynamic not supported");
            }
            this.pool.set(i, utf8);
            return utf8;
        } catch (ParseException e) {
            e.addContext("...while parsing cst " + Hex.u2(i) + " at offset " + Hex.u4(i2));
            throw e;
        } catch (RuntimeException e2) {
            ParseException parseException = new ParseException(e2);
            parseException.addContext("...while parsing cst " + Hex.u2(i) + " at offset " + Hex.u4(i2));
            throw parseException;
        }
    }

    private CstString parseUtf8(int i) {
        int unsignedShort = this.bytes.getUnsignedShort(i + 1);
        int i2 = i + 3;
        try {
            return new CstString(this.bytes.slice(i2, unsignedShort + i2));
        } catch (IllegalArgumentException e) {
            throw new ParseException(e);
        }
    }
}
