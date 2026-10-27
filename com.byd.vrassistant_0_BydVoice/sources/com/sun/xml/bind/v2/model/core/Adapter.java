package com.sun.xml.bind.v2.model.core;

import com.byd.common.bean.WuwInfoBean;
import com.sun.xml.bind.v2.model.annotation.AnnotationReader;
import com.sun.xml.bind.v2.model.nav.Navigator;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/* JADX INFO: loaded from: classes3.dex */
public class Adapter<TypeT, ClassDeclT> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public final ClassDeclT adapterType;
    public final TypeT customType;
    public final TypeT defaultType;

    public Adapter(XmlJavaTypeAdapter xmlJavaTypeAdapter, AnnotationReader<TypeT, ClassDeclT, ?, ?> annotationReader, Navigator<TypeT, ClassDeclT, ?, ?> navigator) {
        this(navigator.asDecl(annotationReader.getClassValue(xmlJavaTypeAdapter, WuwInfoBean.ValueKey)), navigator);
    }

    public Adapter(ClassDeclT classdeclt, Navigator<TypeT, ClassDeclT, ?, ?> navigator) {
        this.adapterType = classdeclt;
        TypeT baseClass = navigator.getBaseClass(navigator.use(classdeclt), navigator.asDecl(XmlAdapter.class));
        if (navigator.isParameterizedType(baseClass)) {
            this.defaultType = navigator.getTypeArgument(baseClass, 0);
        } else {
            this.defaultType = navigator.ref(Object.class);
        }
        if (navigator.isParameterizedType(baseClass)) {
            this.customType = navigator.getTypeArgument(baseClass, 1);
        } else {
            this.customType = navigator.ref(Object.class);
        }
    }
}
