package com.google.firebase.perf.plugin.instrumentation.model;

import java.util.ArrayList;
import java.util.List;
import org.objectweb.asm.Type;

/* JADX INFO: loaded from: classes6.dex */
public class ClassInfo {
    public List<AnnotationInfo> annotations = new ArrayList();
    public String[] interfaces;
    public String superclass;
    public Type type;
}
