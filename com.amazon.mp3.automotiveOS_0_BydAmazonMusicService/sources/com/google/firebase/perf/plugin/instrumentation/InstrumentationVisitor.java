package com.google.firebase.perf.plugin.instrumentation;

import com.google.firebase.perf.plugin.FirebasePerfPlugin;
import com.google.firebase.perf.plugin.instrumentation.annotation.AnnotatedMethodAdapter;
import com.google.firebase.perf.plugin.instrumentation.annotation.AnnotatedMethodInstrumentationFactory;
import com.google.firebase.perf.plugin.instrumentation.exceptions.AlreadyPerfInstrumentedException;
import com.google.firebase.perf.plugin.instrumentation.model.AnnotationInfo;
import com.google.firebase.perf.plugin.instrumentation.model.ClassInfo;
import com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentation;
import com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentationFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;
import org.slf4j.Logger;

/* JADX INFO: loaded from: classes6.dex */
public class InstrumentationVisitor extends ClassVisitor {
    private static final boolean LOG_INSTRUMENTATION = true;
    private static final boolean LOG_VISITS = false;
    private static final Logger logger = FirebasePerfPlugin.getLogger();
    private final ClassInfo classInfo;
    private final ClassVisitor classVisitor;
    private boolean ending;
    private final InstrumentationConfig instrConfig;
    private final InstrumentationContext instrContext;

    public InstrumentationVisitor(int i, ClassVisitor classVisitor, InstrumentationConfig instrumentationConfig) {
        super(i, classVisitor);
        this.classInfo = new ClassInfo();
        this.instrContext = new InstrumentationContext();
        this.classVisitor = classVisitor;
        this.instrConfig = instrumentationConfig;
    }

    @Override // org.objectweb.asm.ClassVisitor
    public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
        super.visit(i, i2, str, str2, str3, strArr);
        this.classInfo.type = Type.getObjectType(str);
        ClassInfo classInfo = this.classInfo;
        classInfo.interfaces = strArr;
        this.instrContext.classMap.put("name", classInfo.type.getClassName());
    }

    @Override // org.objectweb.asm.ClassVisitor
    public void visitOuterClass(String str, String str2, String str3) {
        super.visitOuterClass(str, str2, str3);
    }

    @Override // org.objectweb.asm.ClassVisitor
    public AnnotationVisitor visitAnnotation(String str, boolean z) {
        AnnotationInfo annotationInfo = new AnnotationInfo();
        annotationInfo.type = Type.getType(str);
        this.classInfo.annotations.add(annotationInfo);
        return new FirebasePerfAnnotationVisitor(this.api, super.visitAnnotation(str, z), annotationInfo);
    }

    @Override // org.objectweb.asm.ClassVisitor
    public void visitAttribute(Attribute attribute) {
        super.visitAttribute(attribute);
        if (!this.ending && (attribute instanceof PerfInstrumentedAttribute)) {
            throw new AlreadyPerfInstrumentedException();
        }
    }

    @Override // org.objectweb.asm.ClassVisitor
    public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
        this.instrContext.methodMap.clear();
        return new FirebasePerfMethodVisitor(this.classInfo.type.getDescriptor(), this.api, this.classVisitor.visitMethod(i, str, str2, str3, strArr), i, str, str2, this.instrConfig);
    }

    @Override // org.objectweb.asm.ClassVisitor
    public void visitInnerClass(String str, String str2, String str3, int i) {
        super.visitInnerClass(str, str2, str3, i);
    }

    @Override // org.objectweb.asm.ClassVisitor
    public void visitEnd() {
        this.ending = true;
        visitAttribute(new PerfInstrumentedAttribute("instrumented"));
        super.visitEnd();
    }

    private class FirebasePerfMethodVisitor extends AdviceAdapter {
        private final List<AnnotatedMethodAdapter> annotatedMethodAdapters;
        private final InstrumentationConfig instrConfig;
        private final String perfClassDesc;
        private final String perfMethodDesc;
        private final String perfMethodName;

        protected FirebasePerfMethodVisitor(String str, int i, MethodVisitor methodVisitor, int i2, String str2, String str3, InstrumentationConfig instrumentationConfig) {
            super(i, methodVisitor, i2, str2, str3);
            this.annotatedMethodAdapters = new ArrayList();
            this.instrConfig = instrumentationConfig;
            this.perfClassDesc = str;
            this.perfMethodName = str2;
            this.perfMethodDesc = str3;
        }

        @Override // org.objectweb.asm.MethodVisitor
        public AnnotationVisitor visitAnnotationDefault() {
            return new FirebasePerfAnnotationVisitor(this.api, super.visitAnnotationDefault(), new AnnotationInfo());
        }

        @Override // org.objectweb.asm.MethodVisitor
        public AnnotationVisitor visitAnnotation(String str, boolean z) {
            AnnotationVisitor annotationVisitorVisitAnnotation = super.visitAnnotation(str, z);
            AnnotationInfo annotationInfo = new AnnotationInfo();
            FirebasePerfAnnotationVisitor firebasePerfAnnotationVisitor = new FirebasePerfAnnotationVisitor(this.api, annotationVisitorVisitAnnotation, annotationInfo);
            List<AnnotatedMethodInstrumentationFactory> annotatedMethodInstrumentationFactories = this.instrConfig.getAnnotatedMethodInstrumentationFactories(str);
            if (annotatedMethodInstrumentationFactories != null) {
                InstrumentationVisitor.logger.debug("Instrumenting annotation: " + this.perfClassDesc + ", perfMethodName: " + this.perfMethodName + ", perfMethodDesc: " + this.perfMethodDesc);
                Iterator<AnnotatedMethodInstrumentationFactory> it = annotatedMethodInstrumentationFactories.iterator();
                while (it.hasNext()) {
                    this.annotatedMethodAdapters.add(it.next().newAnnotatedMethodInstrumentation(InstrumentationVisitor.this.instrContext, this, annotationInfo, this.perfMethodName, this.perfMethodDesc));
                }
            }
            return firebasePerfAnnotationVisitor;
        }

        @Override // org.objectweb.asm.commons.AdviceAdapter
        protected void onMethodEnter() {
            super.onMethodEnter();
            for (AnnotatedMethodAdapter annotatedMethodAdapter : this.annotatedMethodAdapters) {
                InstrumentationVisitor.logger.debug("Apply annotation instrumentation: " + this.perfClassDesc + ", perfMethodName: " + this.perfMethodName + ", perfMethodDesc: " + this.perfMethodDesc);
                annotatedMethodAdapter.onMethodEnter();
            }
        }

        @Override // org.objectweb.asm.commons.AdviceAdapter
        protected void onMethodExit(int i) {
            super.onMethodExit(i);
            Iterator<AnnotatedMethodAdapter> it = this.annotatedMethodAdapters.iterator();
            while (it.hasNext()) {
                it.next().onMethodExit();
            }
        }

        @Override // org.objectweb.asm.commons.AdviceAdapter, org.objectweb.asm.MethodVisitor
        public void visitMethodInsn(int i, String str, String str2, String str3, boolean z) {
            NetworkObjectInstrumentation networkObjectInstrumentationNewObjectInstrumentation;
            NetworkObjectInstrumentationFactory networkObjectInstrumentationFactory = this.instrConfig.getNetworkObjectInstrumentationFactory(str, str2, str3);
            if (networkObjectInstrumentationFactory != null) {
                networkObjectInstrumentationNewObjectInstrumentation = networkObjectInstrumentationFactory.newObjectInstrumentation(str, str2, str3);
                InstrumentationVisitor.logger.debug("Instrumenting return obj [owner: " + str + ", name: " + str2 + ", desc: " + str3 + "] with: " + networkObjectInstrumentationNewObjectInstrumentation);
            } else {
                networkObjectInstrumentationNewObjectInstrumentation = null;
            }
            if (networkObjectInstrumentationNewObjectInstrumentation != null) {
                networkObjectInstrumentationNewObjectInstrumentation.injectBefore(this.mv);
                if (!networkObjectInstrumentationNewObjectInstrumentation.replaceMethod(this.mv, i)) {
                    super.visitMethodInsn(i, str, str2, str3, z);
                }
                networkObjectInstrumentationNewObjectInstrumentation.injectAfter(this.mv);
                return;
            }
            super.visitMethodInsn(i, str, str2, str3, z);
        }
    }

    private static class FirebasePerfAnnotationVisitor extends AnnotationVisitor {
        private final AnnotationInfo annotationInfo;

        public FirebasePerfAnnotationVisitor(int i, AnnotationVisitor annotationVisitor, AnnotationInfo annotationInfo) {
            super(i, annotationVisitor);
            this.annotationInfo = annotationInfo;
        }

        @Override // org.objectweb.asm.AnnotationVisitor
        public void visit(String str, Object obj) {
            this.annotationInfo.values.put(str, obj);
            super.visit(str, obj);
        }
    }
}
