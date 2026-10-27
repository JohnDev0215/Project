package com.sun.xml.bind.v2.runtime.output;

import com.sun.istack.FinalArrayList;
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;
import com.sun.xml.bind.v2.runtime.Name;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;

/* JADX INFO: loaded from: classes3.dex */
public class C14nXmlOutput extends UTF8XmlOutput {
    private int len;
    private final boolean namedAttributesAreOrdered;
    private int[] nsBuf;
    private final FinalArrayList<DynamicAttribute> otherAttributes;
    private StaticAttribute[] staticAttributes;

    public C14nXmlOutput(OutputStream outputStream, Encoded[] encodedArr, boolean z, CharacterEscapeHandler characterEscapeHandler) {
        super(outputStream, encodedArr, characterEscapeHandler);
        this.staticAttributes = new StaticAttribute[8];
        int i = 0;
        this.len = 0;
        this.nsBuf = new int[8];
        this.otherAttributes = new FinalArrayList<>();
        this.namedAttributesAreOrdered = z;
        while (true) {
            StaticAttribute[] staticAttributeArr = this.staticAttributes;
            if (i >= staticAttributeArr.length) {
                return;
            }
            staticAttributeArr[i] = new StaticAttribute();
            i++;
        }
    }

    final class StaticAttribute implements Comparable<StaticAttribute> {
        Name name;
        String value;

        StaticAttribute() {
        }

        public void set(Name name, String str) {
            this.name = name;
            this.value = str;
        }

        void write() throws IOException {
            C14nXmlOutput.super.attribute(this.name, this.value);
        }

        DynamicAttribute toDynamicAttribute() {
            short s = this.name.nsUriIndex;
            return C14nXmlOutput.this.new DynamicAttribute(s != -1 ? C14nXmlOutput.this.nsUriIndex2prefixIndex[s] : -1, this.name.localName, this.value);
        }

        @Override // java.lang.Comparable
        public int compareTo(StaticAttribute staticAttribute) {
            return this.name.compareTo(staticAttribute.name);
        }
    }

    final class DynamicAttribute implements Comparable<DynamicAttribute> {
        final String localName;
        final int prefix;
        final String value;

        public DynamicAttribute(int i, String str, String str2) {
            this.prefix = i;
            this.localName = str;
            this.value = str2;
        }

        private String getURI() {
            return this.prefix == -1 ? "" : C14nXmlOutput.this.nsContext.getNamespaceURI(this.prefix);
        }

        @Override // java.lang.Comparable
        public int compareTo(DynamicAttribute dynamicAttribute) {
            int iCompareTo = getURI().compareTo(dynamicAttribute.getURI());
            return iCompareTo != 0 ? iCompareTo : this.localName.compareTo(dynamicAttribute.localName);
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.output.UTF8XmlOutput, com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void attribute(Name name, String str) throws IOException {
        StaticAttribute[] staticAttributeArr = this.staticAttributes;
        int length = staticAttributeArr.length;
        int i = this.len;
        if (length == i) {
            int i2 = i * 2;
            StaticAttribute[] staticAttributeArr2 = new StaticAttribute[i2];
            System.arraycopy(staticAttributeArr, 0, staticAttributeArr2, 0, i);
            for (int i3 = this.len; i3 < i2; i3++) {
                this.staticAttributes[i3] = new StaticAttribute();
            }
            this.staticAttributes = staticAttributeArr2;
        }
        StaticAttribute[] staticAttributeArr3 = this.staticAttributes;
        int i4 = this.len;
        this.len = i4 + 1;
        staticAttributeArr3[i4].set(name, str);
    }

    @Override // com.sun.xml.bind.v2.runtime.output.UTF8XmlOutput, com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void attribute(int i, String str, String str2) throws IOException {
        this.otherAttributes.add(new DynamicAttribute(i, str, str2));
    }

    @Override // com.sun.xml.bind.v2.runtime.output.UTF8XmlOutput, com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endStartTag() throws IOException {
        if (this.otherAttributes.isEmpty()) {
            int i = this.len;
            if (i != 0) {
                if (!this.namedAttributesAreOrdered) {
                    Arrays.sort(this.staticAttributes, 0, i);
                }
                for (int i2 = 0; i2 < this.len; i2++) {
                    this.staticAttributes[i2].write();
                }
                this.len = 0;
            }
        } else {
            for (int i3 = 0; i3 < this.len; i3++) {
                this.otherAttributes.add(this.staticAttributes[i3].toDynamicAttribute());
            }
            this.len = 0;
            Collections.sort(this.otherAttributes);
            int size = this.otherAttributes.size();
            for (int i4 = 0; i4 < size; i4++) {
                DynamicAttribute dynamicAttribute = this.otherAttributes.get(i4);
                super.attribute(dynamicAttribute.prefix, dynamicAttribute.localName, dynamicAttribute.value);
            }
            this.otherAttributes.clear();
        }
        super.endStartTag();
    }

    @Override // com.sun.xml.bind.v2.runtime.output.UTF8XmlOutput
    protected void writeNsDecls(int i) throws IOException {
        int iCount = this.nsContext.getCurrent().count();
        if (iCount == 0) {
            return;
        }
        if (iCount > this.nsBuf.length) {
            this.nsBuf = new int[iCount];
        }
        for (int i2 = iCount - 1; i2 >= 0; i2--) {
            this.nsBuf[i2] = i + i2;
        }
        int i3 = 0;
        while (i3 < iCount) {
            int i4 = i3 + 1;
            for (int i5 = i4; i5 < iCount; i5++) {
                if (this.nsContext.getPrefix(this.nsBuf[i3]).compareTo(this.nsContext.getPrefix(this.nsBuf[i5])) > 0) {
                    int[] iArr = this.nsBuf;
                    int i6 = iArr[i5];
                    iArr[i5] = iArr[i3];
                    iArr[i3] = i6;
                }
            }
            i3 = i4;
        }
        for (int i7 = 0; i7 < iCount; i7++) {
            writeNsDecl(this.nsBuf[i7]);
        }
    }
}
