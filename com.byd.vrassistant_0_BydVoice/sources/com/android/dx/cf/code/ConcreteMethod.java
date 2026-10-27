package com.android.dx.cf.code;

import com.android.dx.cf.attrib.AttCode;
import com.android.dx.cf.attrib.AttLineNumberTable;
import com.android.dx.cf.attrib.AttLocalVariableTable;
import com.android.dx.cf.attrib.AttLocalVariableTypeTable;
import com.android.dx.cf.iface.AttributeList;
import com.android.dx.cf.iface.ClassFile;
import com.android.dx.cf.iface.Method;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Prototype;

/* JADX INFO: loaded from: classes.dex */
public final class ConcreteMethod implements Method {
    private final boolean accSuper;
    private final AttCode attCode;
    private final LineNumberList lineNumbers;
    private final LocalVariableList localVariables;
    private final Method method;
    private final CstString sourceFile;

    public ConcreteMethod(Method method, ClassFile classFile, boolean z, boolean z2) {
        this(method, classFile.getAccessFlags(), classFile.getSourceFile(), z, z2);
    }

    public ConcreteMethod(Method method, int i, CstString cstString, boolean z, boolean z2) {
        this.method = method;
        this.accSuper = (i & 32) != 0;
        this.sourceFile = cstString;
        AttCode attCode = (AttCode) method.getAttributes().findFirst("Code");
        this.attCode = attCode;
        AttributeList attributes = attCode.getAttributes();
        LineNumberList lineNumberListConcat = LineNumberList.EMPTY;
        if (z) {
            for (AttLineNumberTable attLineNumberTable = (AttLineNumberTable) attributes.findFirst("LineNumberTable"); attLineNumberTable != null; attLineNumberTable = (AttLineNumberTable) attributes.findNext(attLineNumberTable)) {
                lineNumberListConcat = LineNumberList.concat(lineNumberListConcat, attLineNumberTable.getLineNumbers());
            }
        }
        this.lineNumbers = lineNumberListConcat;
        LocalVariableList localVariableListMergeDescriptorsAndSignatures = LocalVariableList.EMPTY;
        if (z2) {
            for (AttLocalVariableTable attLocalVariableTable = (AttLocalVariableTable) attributes.findFirst("LocalVariableTable"); attLocalVariableTable != null; attLocalVariableTable = (AttLocalVariableTable) attributes.findNext(attLocalVariableTable)) {
                localVariableListMergeDescriptorsAndSignatures = LocalVariableList.concat(localVariableListMergeDescriptorsAndSignatures, attLocalVariableTable.getLocalVariables());
            }
            LocalVariableList localVariableListConcat = LocalVariableList.EMPTY;
            for (AttLocalVariableTypeTable attLocalVariableTypeTable = (AttLocalVariableTypeTable) attributes.findFirst("LocalVariableTypeTable"); attLocalVariableTypeTable != null; attLocalVariableTypeTable = (AttLocalVariableTypeTable) attributes.findNext(attLocalVariableTypeTable)) {
                localVariableListConcat = LocalVariableList.concat(localVariableListConcat, attLocalVariableTypeTable.getLocalVariables());
            }
            if (localVariableListConcat.size() != 0) {
                localVariableListMergeDescriptorsAndSignatures = LocalVariableList.mergeDescriptorsAndSignatures(localVariableListMergeDescriptorsAndSignatures, localVariableListConcat);
            }
        }
        this.localVariables = localVariableListMergeDescriptorsAndSignatures;
    }

    @Override // com.android.dx.cf.iface.Member
    public CstNat getNat() {
        return this.method.getNat();
    }

    @Override // com.android.dx.cf.iface.Member
    public CstString getName() {
        return this.method.getName();
    }

    @Override // com.android.dx.cf.iface.Member
    public CstString getDescriptor() {
        return this.method.getDescriptor();
    }

    @Override // com.android.dx.cf.iface.Member
    public int getAccessFlags() {
        return this.method.getAccessFlags();
    }

    @Override // com.android.dx.cf.iface.Member, com.android.dx.cf.iface.HasAttribute
    public AttributeList getAttributes() {
        return this.method.getAttributes();
    }

    @Override // com.android.dx.cf.iface.Member
    public CstType getDefiningClass() {
        return this.method.getDefiningClass();
    }

    @Override // com.android.dx.cf.iface.Method
    public Prototype getEffectiveDescriptor() {
        return this.method.getEffectiveDescriptor();
    }

    public boolean getAccSuper() {
        return this.accSuper;
    }

    public int getMaxStack() {
        return this.attCode.getMaxStack();
    }

    public int getMaxLocals() {
        return this.attCode.getMaxLocals();
    }

    public BytecodeArray getCode() {
        return this.attCode.getCode();
    }

    public ByteCatchList getCatches() {
        return this.attCode.getCatches();
    }

    public LineNumberList getLineNumbers() {
        return this.lineNumbers;
    }

    public LocalVariableList getLocalVariables() {
        return this.localVariables;
    }

    public SourcePosition makeSourcePosistion(int i) {
        return new SourcePosition(this.sourceFile, i, this.lineNumbers.pcToLine(i));
    }
}
