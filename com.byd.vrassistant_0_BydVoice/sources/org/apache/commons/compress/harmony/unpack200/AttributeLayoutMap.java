package org.apache.commons.compress.harmony.unpack200;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;

/* JADX INFO: loaded from: classes4.dex */
public class AttributeLayoutMap {
    private final Map classLayouts;
    private final Map codeLayouts;
    private final Map fieldLayouts;
    private final Map[] layouts;
    private final Map layoutsToBands;
    private final Map methodLayouts;

    private static AttributeLayout[] getDefaultAttributeLayouts() throws Pack200Exception {
        return new AttributeLayout[]{new AttributeLayout(AttributeLayout.ACC_PUBLIC, 0, "", 0), new AttributeLayout(AttributeLayout.ACC_PUBLIC, 1, "", 0), new AttributeLayout(AttributeLayout.ACC_PUBLIC, 2, "", 0), new AttributeLayout(AttributeLayout.ACC_PRIVATE, 0, "", 1), new AttributeLayout(AttributeLayout.ACC_PRIVATE, 1, "", 1), new AttributeLayout(AttributeLayout.ACC_PRIVATE, 2, "", 1), new AttributeLayout("LineNumberTable", 3, "NH[PHH]", 1), new AttributeLayout(AttributeLayout.ACC_PROTECTED, 0, "", 2), new AttributeLayout(AttributeLayout.ACC_PROTECTED, 1, "", 2), new AttributeLayout(AttributeLayout.ACC_PROTECTED, 2, "", 2), new AttributeLayout("LocalVariableTable", 3, "NH[PHOHRUHRSHH]", 2), new AttributeLayout(AttributeLayout.ACC_STATIC, 0, "", 3), new AttributeLayout(AttributeLayout.ACC_STATIC, 1, "", 3), new AttributeLayout(AttributeLayout.ACC_STATIC, 2, "", 3), new AttributeLayout("LocalVariableTypeTable", 3, "NH[PHOHRUHRSHH]", 3), new AttributeLayout(AttributeLayout.ACC_FINAL, 0, "", 4), new AttributeLayout(AttributeLayout.ACC_FINAL, 1, "", 4), new AttributeLayout(AttributeLayout.ACC_FINAL, 2, "", 4), new AttributeLayout(AttributeLayout.ACC_SYNCHRONIZED, 0, "", 5), new AttributeLayout(AttributeLayout.ACC_SYNCHRONIZED, 1, "", 5), new AttributeLayout(AttributeLayout.ACC_SYNCHRONIZED, 2, "", 5), new AttributeLayout(AttributeLayout.ACC_VOLATILE, 0, "", 6), new AttributeLayout(AttributeLayout.ACC_VOLATILE, 1, "", 6), new AttributeLayout(AttributeLayout.ACC_VOLATILE, 2, "", 6), new AttributeLayout(AttributeLayout.ACC_TRANSIENT, 0, "", 7), new AttributeLayout(AttributeLayout.ACC_TRANSIENT, 1, "", 7), new AttributeLayout(AttributeLayout.ACC_TRANSIENT, 2, "", 7), new AttributeLayout(AttributeLayout.ACC_NATIVE, 0, "", 8), new AttributeLayout(AttributeLayout.ACC_NATIVE, 1, "", 8), new AttributeLayout(AttributeLayout.ACC_NATIVE, 2, "", 8), new AttributeLayout(AttributeLayout.ACC_INTERFACE, 0, "", 9), new AttributeLayout(AttributeLayout.ACC_INTERFACE, 1, "", 9), new AttributeLayout(AttributeLayout.ACC_INTERFACE, 2, "", 9), new AttributeLayout(AttributeLayout.ACC_ABSTRACT, 0, "", 10), new AttributeLayout(AttributeLayout.ACC_ABSTRACT, 1, "", 10), new AttributeLayout(AttributeLayout.ACC_ABSTRACT, 2, "", 10), new AttributeLayout(AttributeLayout.ACC_STRICT, 0, "", 11), new AttributeLayout(AttributeLayout.ACC_STRICT, 1, "", 11), new AttributeLayout(AttributeLayout.ACC_STRICT, 2, "", 11), new AttributeLayout(AttributeLayout.ACC_SYNTHETIC, 0, "", 12), new AttributeLayout(AttributeLayout.ACC_SYNTHETIC, 1, "", 12), new AttributeLayout(AttributeLayout.ACC_SYNTHETIC, 2, "", 12), new AttributeLayout(AttributeLayout.ACC_ANNOTATION, 0, "", 13), new AttributeLayout(AttributeLayout.ACC_ANNOTATION, 1, "", 13), new AttributeLayout(AttributeLayout.ACC_ANNOTATION, 2, "", 13), new AttributeLayout(AttributeLayout.ACC_ENUM, 0, "", 14), new AttributeLayout(AttributeLayout.ACC_ENUM, 1, "", 14), new AttributeLayout(AttributeLayout.ACC_ENUM, 2, "", 14), new AttributeLayout("SourceFile", 0, "RUNH", 17), new AttributeLayout("ConstantValue", 1, "KQH", 17), new AttributeLayout("Code", 2, "", 17), new AttributeLayout("EnclosingMethod", 0, "RCHRDNH", 18), new AttributeLayout("Exceptions", 2, "NH[RCH]", 18), new AttributeLayout("Signature", 0, "RSH", 19), new AttributeLayout("Signature", 1, "RSH", 19), new AttributeLayout("Signature", 2, "RSH", 19), new AttributeLayout("Deprecated", 0, "", 20), new AttributeLayout("Deprecated", 1, "", 20), new AttributeLayout("Deprecated", 2, "", 20), new AttributeLayout("RuntimeVisibleAnnotations", 0, "*", 21), new AttributeLayout("RuntimeVisibleAnnotations", 1, "*", 21), new AttributeLayout("RuntimeVisibleAnnotations", 2, "*", 21), new AttributeLayout("RuntimeInvisibleAnnotations", 0, "*", 22), new AttributeLayout("RuntimeInvisibleAnnotations", 1, "*", 22), new AttributeLayout("RuntimeInvisibleAnnotations", 2, "*", 22), new AttributeLayout("InnerClasses", 0, "", 23), new AttributeLayout("RuntimeVisibleParameterAnnotations", 2, "*", 23), new AttributeLayout(AttributeLayout.ATTRIBUTE_CLASS_FILE_VERSION, 0, "", 24), new AttributeLayout("RuntimeInvisibleParameterAnnotations", 2, "*", 24), new AttributeLayout("AnnotationDefault", 2, "*", 25)};
    }

    public AttributeLayoutMap() throws Pack200Exception {
        HashMap map = new HashMap();
        this.classLayouts = map;
        HashMap map2 = new HashMap();
        this.fieldLayouts = map2;
        HashMap map3 = new HashMap();
        this.methodLayouts = map3;
        HashMap map4 = new HashMap();
        this.codeLayouts = map4;
        this.layouts = new Map[]{map, map2, map3, map4};
        this.layoutsToBands = new HashMap();
        for (AttributeLayout attributeLayout : getDefaultAttributeLayouts()) {
            add(attributeLayout);
        }
    }

    public void add(AttributeLayout attributeLayout) {
        this.layouts[attributeLayout.getContext()].put(Integer.valueOf(attributeLayout.getIndex()), attributeLayout);
    }

    public void add(AttributeLayout attributeLayout, NewAttributeBands newAttributeBands) {
        add(attributeLayout);
        this.layoutsToBands.put(attributeLayout, newAttributeBands);
    }

    public AttributeLayout getAttributeLayout(String str, int i) throws Pack200Exception {
        for (AttributeLayout attributeLayout : this.layouts[i].values()) {
            if (attributeLayout.getName().equals(str)) {
                return attributeLayout;
            }
        }
        return null;
    }

    public AttributeLayout getAttributeLayout(int i, int i2) throws Pack200Exception {
        return (AttributeLayout) this.layouts[i2].get(Integer.valueOf(i));
    }

    public void checkMap() throws Pack200Exception {
        int i = 0;
        while (true) {
            Map[] mapArr = this.layouts;
            if (i >= mapArr.length) {
                return;
            }
            Collection collectionValues = mapArr[i].values();
            if (!(collectionValues instanceof List)) {
                collectionValues = new ArrayList(collectionValues);
            }
            List list = (List) collectionValues;
            int i2 = 0;
            while (i2 < list.size()) {
                AttributeLayout attributeLayout = (AttributeLayout) list.get(i2);
                i2++;
                for (int i3 = i2; i3 < list.size(); i3++) {
                    AttributeLayout attributeLayout2 = (AttributeLayout) list.get(i3);
                    if (attributeLayout.getName().equals(attributeLayout2.getName()) && attributeLayout.getLayout().equals(attributeLayout2.getLayout())) {
                        throw new Pack200Exception("Same layout/name combination: " + attributeLayout.getLayout() + "/" + attributeLayout.getName() + " exists twice for context: " + AttributeLayout.contextNames[attributeLayout.getContext()]);
                    }
                }
            }
            i++;
        }
    }

    public NewAttributeBands getAttributeBands(AttributeLayout attributeLayout) {
        return (NewAttributeBands) this.layoutsToBands.get(attributeLayout);
    }
}
