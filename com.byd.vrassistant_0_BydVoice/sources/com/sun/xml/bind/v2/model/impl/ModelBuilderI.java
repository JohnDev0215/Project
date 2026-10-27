package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.annotation.AnnotationReader;
import com.sun.xml.bind.v2.model.nav.Navigator;

/* JADX INFO: loaded from: classes3.dex */
public interface ModelBuilderI<T, C, F, M> {
    Navigator<T, C, F, M> getNavigator();

    AnnotationReader<T, C, F, M> getReader();
}
