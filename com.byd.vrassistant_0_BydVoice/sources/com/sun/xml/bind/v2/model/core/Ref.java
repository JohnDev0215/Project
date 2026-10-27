package com.sun.xml.bind.v2.model.core;

import com.sun.xml.bind.v2.model.annotation.AnnotationReader;
import com.sun.xml.bind.v2.model.impl.ModelBuilderI;
import com.sun.xml.bind.v2.model.nav.Navigator;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/* JADX INFO: loaded from: classes3.dex */
public final class Ref<T, C> {
    public final Adapter<T, C> adapter;
    public final T type;
    public final boolean valueList;

    public Ref(T t) {
        this(t, null, false);
    }

    public Ref(T t, Adapter<T, C> adapter, boolean z) {
        this.adapter = adapter;
        this.type = adapter != null ? adapter.defaultType : t;
        this.valueList = z;
    }

    public Ref(ModelBuilderI<T, C, ?, ?> modelBuilderI, T t, XmlJavaTypeAdapter xmlJavaTypeAdapter, XmlList xmlList) {
        this(modelBuilderI.getReader(), modelBuilderI.getNavigator(), t, xmlJavaTypeAdapter, xmlList);
    }

    public Ref(AnnotationReader<T, C, ?, ?> annotationReader, Navigator<T, C, ?, ?> navigator, T t, XmlJavaTypeAdapter xmlJavaTypeAdapter, XmlList xmlList) {
        Adapter<T, C> adapter;
        if (xmlJavaTypeAdapter != null) {
            Adapter<T, C> adapter2 = new Adapter<>(xmlJavaTypeAdapter, annotationReader, navigator);
            t = adapter2.defaultType;
            adapter = adapter2;
        } else {
            adapter = null;
        }
        this.type = t;
        this.adapter = adapter;
        this.valueList = xmlList != null;
    }
}
