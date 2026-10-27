package org.apache.commons.compress.harmony.unpack200;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.AnnotationDefaultAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CodeAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.ConstantValueAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.DeprecatedAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.EnclosingMethodAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.ExceptionsAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.InnerClassesAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LineNumberTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LocalVariableTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LocalVariableTypeTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.SignatureAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.SourceFileAttribute;

/* JADX INFO: loaded from: classes4.dex */
public class AttrDefinitionBands extends BandSet {
    private int[] attributeDefinitionHeader;
    private String[] attributeDefinitionLayout;
    private AttributeLayoutMap attributeDefinitionMap;
    private String[] attributeDefinitionName;
    private final String[] cpUTF8;

    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    public void unpack() throws Pack200Exception, IOException {
    }

    public AttrDefinitionBands(Segment segment) {
        super(segment);
        this.cpUTF8 = segment.getCpBands().getCpUTF8();
    }

    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    public void read(InputStream inputStream) throws Pack200Exception, IOException {
        int i;
        int attributeDefinitionCount = this.header.getAttributeDefinitionCount();
        this.attributeDefinitionHeader = decodeBandInt("attr_definition_headers", inputStream, Codec.BYTE1, attributeDefinitionCount);
        this.attributeDefinitionName = parseReferences("attr_definition_name", inputStream, Codec.UNSIGNED5, attributeDefinitionCount, this.cpUTF8);
        this.attributeDefinitionLayout = parseReferences("attr_definition_layout", inputStream, Codec.UNSIGNED5, attributeDefinitionCount, this.cpUTF8);
        this.attributeDefinitionMap = new AttributeLayoutMap();
        int i2 = this.segment.getSegmentHeader().getOptions().hasClassFlagsHi() ? 63 : 32;
        for (int i3 = 0; i3 < attributeDefinitionCount; i3++) {
            int i4 = this.attributeDefinitionHeader[i3];
            int i5 = i4 & 3;
            int i6 = (i4 >> 2) - 1;
            if (i6 == -1) {
                i = i2;
                i2++;
            } else {
                i = i6;
            }
            AttributeLayout attributeLayout = new AttributeLayout(this.attributeDefinitionName[i3], i5, this.attributeDefinitionLayout[i3], i, false);
            this.attributeDefinitionMap.add(attributeLayout, new NewAttributeBands(this.segment, attributeLayout));
        }
        this.attributeDefinitionMap.checkMap();
        setupDefaultAttributeNames();
    }

    private void setupDefaultAttributeNames() {
        AnnotationDefaultAttribute.setAttributeName(this.segment.getCpBands().cpUTF8Value("AnnotationDefault"));
        CodeAttribute.setAttributeName(this.segment.getCpBands().cpUTF8Value("Code"));
        ConstantValueAttribute.setAttributeName(this.segment.getCpBands().cpUTF8Value("ConstantValue"));
        DeprecatedAttribute.setAttributeName(this.segment.getCpBands().cpUTF8Value("Deprecated"));
        EnclosingMethodAttribute.setAttributeName(this.segment.getCpBands().cpUTF8Value("EnclosingMethod"));
        ExceptionsAttribute.setAttributeName(this.segment.getCpBands().cpUTF8Value("Exceptions"));
        InnerClassesAttribute.setAttributeName(this.segment.getCpBands().cpUTF8Value("InnerClasses"));
        LineNumberTableAttribute.setAttributeName(this.segment.getCpBands().cpUTF8Value("LineNumberTable"));
        LocalVariableTableAttribute.setAttributeName(this.segment.getCpBands().cpUTF8Value("LocalVariableTable"));
        LocalVariableTypeTableAttribute.setAttributeName(this.segment.getCpBands().cpUTF8Value("LocalVariableTypeTable"));
        SignatureAttribute.setAttributeName(this.segment.getCpBands().cpUTF8Value("Signature"));
        SourceFileAttribute.setAttributeName(this.segment.getCpBands().cpUTF8Value("SourceFile"));
        MetadataBandGroup.setRvaAttributeName(this.segment.getCpBands().cpUTF8Value("RuntimeVisibleAnnotations"));
        MetadataBandGroup.setRiaAttributeName(this.segment.getCpBands().cpUTF8Value("RuntimeInvisibleAnnotations"));
        MetadataBandGroup.setRvpaAttributeName(this.segment.getCpBands().cpUTF8Value("RuntimeVisibleParameterAnnotations"));
        MetadataBandGroup.setRipaAttributeName(this.segment.getCpBands().cpUTF8Value("RuntimeInvisibleParameterAnnotations"));
    }

    public AttributeLayoutMap getAttributeDefinitionMap() {
        return this.attributeDefinitionMap;
    }
}
