package com.fasterxml.jackson.dataformat.xml.ser;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.dataformat.xml.util.XmlInfo;
import java.io.IOException;
import java.util.BitSet;
import java.util.Set;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes6.dex */
public abstract class XmlBeanSerializerBase extends BeanSerializerBase {
    public static final String KEY_XML_INFO = new String("xmlInfo");
    protected final int _attributeCount;
    protected final BitSet _cdata;
    protected final int _textPropertyIndex;
    protected final QName[] _xmlNames;

    public XmlBeanSerializerBase(BeanSerializerBase beanSerializerBase) {
        int i_orderAttributesFirst;
        String namespace;
        super(beanSerializerBase);
        BeanPropertyWriter[] beanPropertyWriterArr = this._props;
        int length = beanPropertyWriterArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                i_orderAttributesFirst = 0;
                break;
            } else {
                if (_isAttribute(beanPropertyWriterArr[i])) {
                    i_orderAttributesFirst = _orderAttributesFirst(this._props, this._filteredProps);
                    break;
                }
                i++;
            }
        }
        this._attributeCount = i_orderAttributesFirst;
        int length2 = this._props.length;
        BitSet bitSet = null;
        for (int i2 = 0; i2 < length2; i2++) {
            if (_isCData(this._props[i2])) {
                bitSet = bitSet == null ? new BitSet(length2) : bitSet;
                bitSet.set(i2);
            }
        }
        this._cdata = bitSet;
        BeanPropertyWriter[] beanPropertyWriterArr2 = this._props;
        this._xmlNames = new QName[beanPropertyWriterArr2.length];
        int i3 = -1;
        int length3 = beanPropertyWriterArr2.length;
        for (int i4 = 0; i4 < length3; i4++) {
            BeanPropertyWriter beanPropertyWriter = this._props[i4];
            XmlInfo xmlInfo = (XmlInfo) beanPropertyWriter.getInternalSetting(KEY_XML_INFO);
            if (xmlInfo != null) {
                namespace = xmlInfo.getNamespace();
                if (i3 < 0 && xmlInfo.isText()) {
                    i3 = i4;
                }
            } else {
                namespace = null;
            }
            QName[] qNameArr = this._xmlNames;
            if (namespace == null) {
                namespace = "";
            }
            qNameArr[i4] = new QName(namespace, beanPropertyWriter.getName());
        }
        this._textPropertyIndex = i3;
    }

    protected XmlBeanSerializerBase(XmlBeanSerializerBase xmlBeanSerializerBase, ObjectIdWriter objectIdWriter) {
        super(xmlBeanSerializerBase, objectIdWriter);
        this._attributeCount = xmlBeanSerializerBase._attributeCount;
        this._textPropertyIndex = xmlBeanSerializerBase._textPropertyIndex;
        this._xmlNames = xmlBeanSerializerBase._xmlNames;
        this._cdata = xmlBeanSerializerBase._cdata;
    }

    protected XmlBeanSerializerBase(XmlBeanSerializerBase xmlBeanSerializerBase, ObjectIdWriter objectIdWriter, Object obj) {
        super(xmlBeanSerializerBase, objectIdWriter, obj);
        this._attributeCount = xmlBeanSerializerBase._attributeCount;
        this._textPropertyIndex = xmlBeanSerializerBase._textPropertyIndex;
        this._xmlNames = xmlBeanSerializerBase._xmlNames;
        this._cdata = xmlBeanSerializerBase._cdata;
    }

    protected XmlBeanSerializerBase(XmlBeanSerializerBase xmlBeanSerializerBase, Set<String> set, Set<String> set2) {
        super(xmlBeanSerializerBase, set, set2);
        this._attributeCount = xmlBeanSerializerBase._attributeCount;
        this._textPropertyIndex = xmlBeanSerializerBase._textPropertyIndex;
        this._xmlNames = xmlBeanSerializerBase._xmlNames;
        this._cdata = xmlBeanSerializerBase._cdata;
    }

    public XmlBeanSerializerBase(XmlBeanSerializerBase xmlBeanSerializerBase, NameTransformer nameTransformer) {
        super(xmlBeanSerializerBase, nameTransformer);
        this._attributeCount = xmlBeanSerializerBase._attributeCount;
        this._textPropertyIndex = xmlBeanSerializerBase._textPropertyIndex;
        this._xmlNames = xmlBeanSerializerBase._xmlNames;
        this._cdata = xmlBeanSerializerBase._cdata;
    }

    protected XmlBeanSerializerBase(XmlBeanSerializerBase xmlBeanSerializerBase, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2) {
        super(xmlBeanSerializerBase, beanPropertyWriterArr, beanPropertyWriterArr2);
        this._attributeCount = xmlBeanSerializerBase._attributeCount;
        this._textPropertyIndex = xmlBeanSerializerBase._textPropertyIndex;
        this._xmlNames = xmlBeanSerializerBase._xmlNames;
        this._cdata = xmlBeanSerializerBase._cdata;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0043 A[Catch: StackOverflowError -> 0x0082, Exception -> 0x0084, TryCatch #4 {Exception -> 0x0084, StackOverflowError -> 0x0082, blocks: (B:21:0x003d, B:23:0x0043, B:25:0x0048, B:26:0x004b, B:29:0x0056, B:31:0x005c, B:32:0x0067, B:34:0x006c, B:35:0x0070, B:36:0x0074, B:38:0x0078), top: B:58:0x003d }] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void serializeFields(java.lang.Object r19, com.fasterxml.jackson.core.JsonGenerator r20, com.fasterxml.jackson.databind.SerializerProvider r21) throws java.io.IOException {
        /*
            r18 = this;
            r1 = r18
            r2 = r19
            r0 = r20
            r3 = r21
            java.lang.String r4 = "[anySetter]"
            boolean r5 = r0 instanceof com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator
            if (r5 != 0) goto L12
            super.serializeFields(r19, r20, r21)
            return
        L12:
            r5 = r0
            com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator r5 = (com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator) r5
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r6 = r1._filteredProps
            if (r6 == 0) goto L22
            java.lang.Class r6 = r21.getActiveView()
            if (r6 == 0) goto L22
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r6 = r1._filteredProps
            goto L24
        L22:
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r6 = r1._props
        L24:
            int r7 = r1._attributeCount
            boolean r8 = r5._nextIsAttribute
            r9 = 1
            if (r7 <= 0) goto L2e
            r5.setNextIsAttribute(r9)
        L2e:
            int r10 = r1._textPropertyIndex
            javax.xml.namespace.QName[] r11 = r1._xmlNames
            java.util.BitSet r12 = r1._cdata
            r13 = 0
            int r14 = r6.length     // Catch: java.lang.StackOverflowError -> L87 java.lang.Exception -> La1
            r15 = r13
        L37:
            if (r15 >= r14) goto L74
            if (r15 != r7) goto L46
            if (r8 == 0) goto L43
            boolean r16 = r18.isUnwrappingSerializer()     // Catch: java.lang.StackOverflowError -> L82 java.lang.Exception -> L84
            if (r16 != 0) goto L46
        L43:
            r5.setNextIsAttribute(r13)     // Catch: java.lang.StackOverflowError -> L82 java.lang.Exception -> L84
        L46:
            if (r15 != r10) goto L4b
            r5.setNextIsUnwrapped(r9)     // Catch: java.lang.StackOverflowError -> L82 java.lang.Exception -> L84
        L4b:
            r13 = r11[r15]     // Catch: java.lang.StackOverflowError -> L82 java.lang.Exception -> L84
            r5.setNextName(r13)     // Catch: java.lang.StackOverflowError -> L82 java.lang.Exception -> L84
            r13 = r6[r15]     // Catch: java.lang.StackOverflowError -> L82 java.lang.Exception -> L84
            if (r13 == 0) goto L6a
            if (r12 == 0) goto L67
            boolean r17 = r12.get(r15)     // Catch: java.lang.StackOverflowError -> L82 java.lang.Exception -> L84
            if (r17 == 0) goto L67
            r5.setNextIsCData(r9)     // Catch: java.lang.StackOverflowError -> L82 java.lang.Exception -> L84
            r13.serializeAsField(r2, r5, r3)     // Catch: java.lang.StackOverflowError -> L82 java.lang.Exception -> L84
            r13 = 0
            r5.setNextIsCData(r13)     // Catch: java.lang.StackOverflowError -> L82 java.lang.Exception -> L84
            goto L6a
        L67:
            r13.serializeAsField(r2, r5, r3)     // Catch: java.lang.StackOverflowError -> L82 java.lang.Exception -> L84
        L6a:
            if (r15 != r10) goto L70
            r13 = 0
            r5.setNextIsUnwrapped(r13)     // Catch: java.lang.StackOverflowError -> L82 java.lang.Exception -> L84
        L70:
            int r15 = r15 + 1
            r13 = 0
            goto L37
        L74:
            com.fasterxml.jackson.databind.ser.AnyGetterWriter r7 = r1._anyGetterWriter     // Catch: java.lang.StackOverflowError -> L82 java.lang.Exception -> L84
            if (r7 == 0) goto Lb0
            r7 = 0
            r5.setNextIsAttribute(r7)     // Catch: java.lang.StackOverflowError -> L82 java.lang.Exception -> L84
            com.fasterxml.jackson.databind.ser.AnyGetterWriter r7 = r1._anyGetterWriter     // Catch: java.lang.StackOverflowError -> L82 java.lang.Exception -> L84
            r7.getAndSerialize(r2, r5, r3)     // Catch: java.lang.StackOverflowError -> L82 java.lang.Exception -> L84
            goto Lb0
        L82:
            r13 = r15
            goto L88
        L84:
            r0 = move-exception
            r13 = r15
            goto La3
        L87:
            r7 = r13
        L88:
            java.lang.String r3 = "Infinite recursion (StackOverflowError)"
            com.fasterxml.jackson.databind.JsonMappingException r0 = com.fasterxml.jackson.databind.JsonMappingException.from(r0, r3)
            int r3 = r6.length
            if (r13 != r3) goto L92
            goto L98
        L92:
            r3 = r6[r13]
            java.lang.String r4 = r3.getName()
        L98:
            com.fasterxml.jackson.databind.JsonMappingException$Reference r3 = new com.fasterxml.jackson.databind.JsonMappingException$Reference
            r3.<init>(r2, r4)
            r0.prependPath(r3)
            throw r0
        La1:
            r0 = move-exception
            r7 = r13
        La3:
            int r5 = r6.length
            if (r13 != r5) goto La7
            goto Lad
        La7:
            r4 = r6[r13]
            java.lang.String r4 = r4.getName()
        Lad:
            r1.wrapAndThrow(r3, r0, r2, r4)
        Lb0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.dataformat.xml.ser.XmlBeanSerializerBase.serializeFields(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x004f A[Catch: StackOverflowError -> 0x0087, Exception -> 0x008b, TryCatch #5 {Exception -> 0x008b, StackOverflowError -> 0x0087, blocks: (B:25:0x0049, B:27:0x004f, B:29:0x0054, B:30:0x0058, B:33:0x0063), top: B:70:0x0049 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00ce  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void serializeFieldsFiltered(java.lang.Object r19, com.fasterxml.jackson.core.JsonGenerator r20, com.fasterxml.jackson.databind.SerializerProvider r21) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.dataformat.xml.ser.XmlBeanSerializerBase.serializeFieldsFiltered(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider):void");
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase, com.fasterxml.jackson.databind.JsonSerializer
    public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        if (this._objectIdWriter != null) {
            _serializeWithObjectId(obj, jsonGenerator, serializerProvider, typeSerializer);
            return;
        }
        if (typeSerializer.getTypeInclusion() == JsonTypeInfo.As.PROPERTY) {
            ToXmlGenerator toXmlGenerator = (ToXmlGenerator) jsonGenerator;
            toXmlGenerator.setNextIsAttribute(true);
            super.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
            if (this._attributeCount == 0) {
                toXmlGenerator.setNextIsAttribute(false);
                return;
            }
            return;
        }
        super.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    protected void _serializeObjectId(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer, WritableObjectId writableObjectId) throws IOException {
        if (typeSerializer.getTypeInclusion() == JsonTypeInfo.As.PROPERTY) {
            ToXmlGenerator toXmlGenerator = (ToXmlGenerator) jsonGenerator;
            toXmlGenerator.setNextIsAttribute(true);
            super._serializeObjectId(obj, jsonGenerator, serializerProvider, typeSerializer, writableObjectId);
            if (this._attributeCount == 0) {
                toXmlGenerator.setNextIsAttribute(false);
                return;
            }
            return;
        }
        super._serializeObjectId(obj, jsonGenerator, serializerProvider, typeSerializer, writableObjectId);
    }

    protected static boolean _isAttribute(BeanPropertyWriter beanPropertyWriter) {
        XmlInfo xmlInfo = (XmlInfo) beanPropertyWriter.getInternalSetting(KEY_XML_INFO);
        return xmlInfo != null && xmlInfo.isAttribute();
    }

    protected static boolean _isCData(BeanPropertyWriter beanPropertyWriter) {
        XmlInfo xmlInfo = (XmlInfo) beanPropertyWriter.getInternalSetting(KEY_XML_INFO);
        return xmlInfo != null && xmlInfo.isCData();
    }

    protected static int _orderAttributesFirst(BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2) {
        int length = beanPropertyWriterArr.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i2];
            if (_isAttribute(beanPropertyWriter)) {
                int i3 = i2 - i;
                if (i3 > 0) {
                    int i4 = i + 1;
                    System.arraycopy(beanPropertyWriterArr, i, beanPropertyWriterArr, i4, i3);
                    beanPropertyWriterArr[i] = beanPropertyWriter;
                    if (beanPropertyWriterArr2 != null) {
                        BeanPropertyWriter beanPropertyWriter2 = beanPropertyWriterArr2[i2];
                        System.arraycopy(beanPropertyWriterArr2, i, beanPropertyWriterArr2, i4, i3);
                        beanPropertyWriterArr2[i] = beanPropertyWriter2;
                    }
                }
                i++;
            }
        }
        return i;
    }
}
