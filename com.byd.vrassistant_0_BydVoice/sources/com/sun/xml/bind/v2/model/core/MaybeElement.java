package com.sun.xml.bind.v2.model.core;

import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
public interface MaybeElement<T, C> extends NonElement<T, C> {
    Element<T, C> asElement();

    QName getElementName();

    boolean isElement();
}
