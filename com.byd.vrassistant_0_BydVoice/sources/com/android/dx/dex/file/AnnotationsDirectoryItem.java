package com.android.dx.dex.file;

import com.android.dx.rop.annotation.Annotations;
import com.android.dx.rop.annotation.AnnotationsList;
import com.android.dx.rop.cst.CstFieldRef;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class AnnotationsDirectoryItem extends OffsettedItem {
    private static final int ALIGNMENT = 4;
    private static final int ELEMENT_SIZE = 8;
    private static final int HEADER_SIZE = 16;
    private AnnotationSetItem classAnnotations;
    private ArrayList<FieldAnnotationStruct> fieldAnnotations;
    private ArrayList<MethodAnnotationStruct> methodAnnotations;
    private ArrayList<ParameterAnnotationStruct> parameterAnnotations;

    public AnnotationsDirectoryItem() {
        super(4, -1);
        this.classAnnotations = null;
        this.fieldAnnotations = null;
        this.methodAnnotations = null;
        this.parameterAnnotations = null;
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_ANNOTATIONS_DIRECTORY_ITEM;
    }

    public boolean isEmpty() {
        return this.classAnnotations == null && this.fieldAnnotations == null && this.methodAnnotations == null && this.parameterAnnotations == null;
    }

    public boolean isInternable() {
        return this.classAnnotations != null && this.fieldAnnotations == null && this.methodAnnotations == null && this.parameterAnnotations == null;
    }

    public int hashCode() {
        AnnotationSetItem annotationSetItem = this.classAnnotations;
        if (annotationSetItem == null) {
            return 0;
        }
        return annotationSetItem.hashCode();
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public int compareTo0(OffsettedItem offsettedItem) {
        if (!isInternable()) {
            throw new UnsupportedOperationException("uninternable instance");
        }
        return this.classAnnotations.compareTo((OffsettedItem) ((AnnotationsDirectoryItem) offsettedItem).classAnnotations);
    }

    public void setClassAnnotations(Annotations annotations, DexFile dexFile) {
        if (annotations == null) {
            throw new NullPointerException("annotations == null");
        }
        if (this.classAnnotations != null) {
            throw new UnsupportedOperationException("class annotations already set");
        }
        this.classAnnotations = new AnnotationSetItem(annotations, dexFile);
    }

    public void addFieldAnnotations(CstFieldRef cstFieldRef, Annotations annotations, DexFile dexFile) {
        if (this.fieldAnnotations == null) {
            this.fieldAnnotations = new ArrayList<>();
        }
        this.fieldAnnotations.add(new FieldAnnotationStruct(cstFieldRef, new AnnotationSetItem(annotations, dexFile)));
    }

    public void addMethodAnnotations(CstMethodRef cstMethodRef, Annotations annotations, DexFile dexFile) {
        if (this.methodAnnotations == null) {
            this.methodAnnotations = new ArrayList<>();
        }
        this.methodAnnotations.add(new MethodAnnotationStruct(cstMethodRef, new AnnotationSetItem(annotations, dexFile)));
    }

    public void addParameterAnnotations(CstMethodRef cstMethodRef, AnnotationsList annotationsList, DexFile dexFile) {
        if (this.parameterAnnotations == null) {
            this.parameterAnnotations = new ArrayList<>();
        }
        this.parameterAnnotations.add(new ParameterAnnotationStruct(cstMethodRef, annotationsList, dexFile));
    }

    public Annotations getMethodAnnotations(CstMethodRef cstMethodRef) {
        ArrayList<MethodAnnotationStruct> arrayList = this.methodAnnotations;
        if (arrayList == null) {
            return null;
        }
        for (MethodAnnotationStruct methodAnnotationStruct : arrayList) {
            if (methodAnnotationStruct.getMethod().equals(cstMethodRef)) {
                return methodAnnotationStruct.getAnnotations();
            }
        }
        return null;
    }

    public AnnotationsList getParameterAnnotations(CstMethodRef cstMethodRef) {
        ArrayList<ParameterAnnotationStruct> arrayList = this.parameterAnnotations;
        if (arrayList == null) {
            return null;
        }
        for (ParameterAnnotationStruct parameterAnnotationStruct : arrayList) {
            if (parameterAnnotationStruct.getMethod().equals(cstMethodRef)) {
                return parameterAnnotationStruct.getAnnotationsList();
            }
        }
        return null;
    }

    @Override // com.android.dx.dex.file.Item
    public void addContents(DexFile dexFile) {
        MixedItemSection wordData = dexFile.getWordData();
        AnnotationSetItem annotationSetItem = this.classAnnotations;
        if (annotationSetItem != null) {
            this.classAnnotations = (AnnotationSetItem) wordData.intern(annotationSetItem);
        }
        ArrayList<FieldAnnotationStruct> arrayList = this.fieldAnnotations;
        if (arrayList != null) {
            Iterator<FieldAnnotationStruct> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().addContents(dexFile);
            }
        }
        ArrayList<MethodAnnotationStruct> arrayList2 = this.methodAnnotations;
        if (arrayList2 != null) {
            Iterator<MethodAnnotationStruct> it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                it2.next().addContents(dexFile);
            }
        }
        ArrayList<ParameterAnnotationStruct> arrayList3 = this.parameterAnnotations;
        if (arrayList3 != null) {
            Iterator<ParameterAnnotationStruct> it3 = arrayList3.iterator();
            while (it3.hasNext()) {
                it3.next().addContents(dexFile);
            }
        }
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public String toHuman() {
        throw new RuntimeException("unsupported");
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    protected void place0(Section section, int i) {
        setWriteSize(((listSize(this.fieldAnnotations) + listSize(this.methodAnnotations) + listSize(this.parameterAnnotations)) * 8) + 16);
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    protected void writeTo0(DexFile dexFile, AnnotatedOutput annotatedOutput) {
        boolean zAnnotates = annotatedOutput.annotates();
        int absoluteOffsetOr0 = OffsettedItem.getAbsoluteOffsetOr0(this.classAnnotations);
        int iListSize = listSize(this.fieldAnnotations);
        int iListSize2 = listSize(this.methodAnnotations);
        int iListSize3 = listSize(this.parameterAnnotations);
        if (zAnnotates) {
            annotatedOutput.annotate(0, offsetString() + " annotations directory");
            annotatedOutput.annotate(4, "  class_annotations_off: " + Hex.u4(absoluteOffsetOr0));
            annotatedOutput.annotate(4, "  fields_size:           " + Hex.u4(iListSize));
            annotatedOutput.annotate(4, "  methods_size:          " + Hex.u4(iListSize2));
            annotatedOutput.annotate(4, "  parameters_size:       " + Hex.u4(iListSize3));
        }
        annotatedOutput.writeInt(absoluteOffsetOr0);
        annotatedOutput.writeInt(iListSize);
        annotatedOutput.writeInt(iListSize2);
        annotatedOutput.writeInt(iListSize3);
        if (iListSize != 0) {
            Collections.sort(this.fieldAnnotations);
            if (zAnnotates) {
                annotatedOutput.annotate(0, "  fields:");
            }
            Iterator<FieldAnnotationStruct> it = this.fieldAnnotations.iterator();
            while (it.hasNext()) {
                it.next().writeTo(dexFile, annotatedOutput);
            }
        }
        if (iListSize2 != 0) {
            Collections.sort(this.methodAnnotations);
            if (zAnnotates) {
                annotatedOutput.annotate(0, "  methods:");
            }
            Iterator<MethodAnnotationStruct> it2 = this.methodAnnotations.iterator();
            while (it2.hasNext()) {
                it2.next().writeTo(dexFile, annotatedOutput);
            }
        }
        if (iListSize3 != 0) {
            Collections.sort(this.parameterAnnotations);
            if (zAnnotates) {
                annotatedOutput.annotate(0, "  parameters:");
            }
            Iterator<ParameterAnnotationStruct> it3 = this.parameterAnnotations.iterator();
            while (it3.hasNext()) {
                it3.next().writeTo(dexFile, annotatedOutput);
            }
        }
    }

    private static int listSize(ArrayList<?> arrayList) {
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    void debugPrint(PrintWriter printWriter) {
        if (this.classAnnotations != null) {
            printWriter.println("  class annotations: " + this.classAnnotations);
        }
        if (this.fieldAnnotations != null) {
            printWriter.println("  field annotations:");
            Iterator<FieldAnnotationStruct> it = this.fieldAnnotations.iterator();
            while (it.hasNext()) {
                printWriter.println("    " + it.next().toHuman());
            }
        }
        if (this.methodAnnotations != null) {
            printWriter.println("  method annotations:");
            Iterator<MethodAnnotationStruct> it2 = this.methodAnnotations.iterator();
            while (it2.hasNext()) {
                printWriter.println("    " + it2.next().toHuman());
            }
        }
        if (this.parameterAnnotations != null) {
            printWriter.println("  parameter annotations:");
            Iterator<ParameterAnnotationStruct> it3 = this.parameterAnnotations.iterator();
            while (it3.hasNext()) {
                printWriter.println("    " + it3.next().toHuman());
            }
        }
    }
}
