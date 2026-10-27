package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.annotation.AnnotationSource;
import com.sun.xml.bind.v2.model.annotation.Locatable;

/* JADX INFO: loaded from: classes3.dex */
interface PropertySeed<T, C, F, M> extends Locatable, AnnotationSource {
    String getName();

    T getRawType();
}
