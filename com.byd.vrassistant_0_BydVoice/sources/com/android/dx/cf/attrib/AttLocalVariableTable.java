package com.android.dx.cf.attrib;

import com.android.dx.cf.code.LocalVariableList;

/* JADX INFO: loaded from: classes.dex */
public final class AttLocalVariableTable extends BaseLocalVariables {
    public static final String ATTRIBUTE_NAME = "LocalVariableTable";

    public AttLocalVariableTable(LocalVariableList localVariableList) {
        super("LocalVariableTable", localVariableList);
    }
}
