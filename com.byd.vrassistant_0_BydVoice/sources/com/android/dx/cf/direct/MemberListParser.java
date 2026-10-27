package com.android.dx.cf.direct;

import com.android.dx.cf.iface.AttributeList;
import com.android.dx.cf.iface.Member;
import com.android.dx.cf.iface.ParseException;
import com.android.dx.cf.iface.ParseObserver;
import com.android.dx.cf.iface.StdAttributeList;
import com.android.dx.rop.cst.ConstantPool;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.util.ByteArray;
import com.android.dx.util.Hex;

/* JADX INFO: loaded from: classes.dex */
abstract class MemberListParser {
    private final AttributeFactory attributeFactory;
    private final DirectClassFile cf;
    private final CstType definer;
    private int endOffset;
    private ParseObserver observer;
    private final int offset;

    protected abstract int getAttributeContext();

    protected abstract String humanAccessFlags(int i);

    protected abstract String humanName();

    protected abstract Member set(int i, int i2, CstNat cstNat, AttributeList attributeList);

    public MemberListParser(DirectClassFile directClassFile, CstType cstType, int i, AttributeFactory attributeFactory) {
        if (directClassFile == null) {
            throw new NullPointerException("cf == null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("offset < 0");
        }
        if (attributeFactory == null) {
            throw new NullPointerException("attributeFactory == null");
        }
        this.cf = directClassFile;
        this.definer = cstType;
        this.offset = i;
        this.attributeFactory = attributeFactory;
        this.endOffset = -1;
    }

    public int getEndOffset() {
        parseIfNecessary();
        return this.endOffset;
    }

    public final void setObserver(ParseObserver parseObserver) {
        this.observer = parseObserver;
    }

    protected final void parseIfNecessary() {
        if (this.endOffset < 0) {
            parse();
        }
    }

    protected final int getCount() {
        return this.cf.getBytes().getUnsignedShort(this.offset);
    }

    protected final CstType getDefiner() {
        return this.definer;
    }

    private void parse() {
        int i;
        ConstantPool constantPool;
        char c;
        int attributeContext = getAttributeContext();
        int count = getCount();
        int endOffset = this.offset + 2;
        ByteArray bytes = this.cf.getBytes();
        ConstantPool constantPool2 = this.cf.getConstantPool();
        ParseObserver parseObserver = this.observer;
        if (parseObserver != null) {
            parseObserver.parsed(bytes, this.offset, 2, humanName() + "s_count: " + Hex.u2(count));
        }
        int i2 = 0;
        while (i2 < count) {
            try {
                int unsignedShort = bytes.getUnsignedShort(endOffset);
                int i3 = endOffset + 2;
                int unsignedShort2 = bytes.getUnsignedShort(i3);
                int i4 = endOffset + 4;
                int unsignedShort3 = bytes.getUnsignedShort(i4);
                CstString cstString = (CstString) constantPool2.get(unsignedShort2);
                CstString cstString2 = (CstString) constantPool2.get(unsignedShort3);
                ParseObserver parseObserver2 = this.observer;
                int i5 = count;
                if (parseObserver2 != null) {
                    constantPool = constantPool2;
                    parseObserver2.startParsingMember(bytes, endOffset, cstString.getString(), cstString2.getString());
                    this.observer.parsed(bytes, endOffset, 0, "\n" + humanName() + "s[" + i2 + "]:\n");
                    this.observer.changeIndent(1);
                    this.observer.parsed(bytes, endOffset, 2, "access_flags: " + humanAccessFlags(unsignedShort));
                    this.observer.parsed(bytes, i3, 2, "name: " + cstString.toHuman());
                    c = 2;
                    this.observer.parsed(bytes, i4, 2, "descriptor: " + cstString2.toHuman());
                } else {
                    constantPool = constantPool2;
                    c = 2;
                }
                AttributeListParser attributeListParser = new AttributeListParser(this.cf, attributeContext, endOffset + 6, this.attributeFactory);
                attributeListParser.setObserver(this.observer);
                endOffset = attributeListParser.getEndOffset();
                StdAttributeList list = attributeListParser.getList();
                list.setImmutable();
                Member member = set(i2, unsignedShort, new CstNat(cstString, cstString2), list);
                ParseObserver parseObserver3 = this.observer;
                if (parseObserver3 != null) {
                    parseObserver3.changeIndent(-1);
                    this.observer.parsed(bytes, endOffset, 0, "end " + humanName() + "s[" + i2 + "]\n");
                    i = i2;
                    try {
                        this.observer.endParsingMember(bytes, endOffset, cstString.getString(), cstString2.getString(), member);
                    } catch (ParseException e) {
                        e = e;
                        e.addContext("...while parsing " + humanName() + "s[" + i + "]");
                        throw e;
                    } catch (RuntimeException e2) {
                        e = e2;
                        ParseException parseException = new ParseException(e);
                        parseException.addContext("...while parsing " + humanName() + "s[" + i + "]");
                        throw parseException;
                    }
                } else {
                    i = i2;
                }
                i2 = i + 1;
                count = i5;
                constantPool2 = constantPool;
            } catch (ParseException e3) {
                e = e3;
                i = i2;
            } catch (RuntimeException e4) {
                e = e4;
                i = i2;
            }
        }
        this.endOffset = endOffset;
    }
}
