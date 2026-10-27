package com.sun.xml.bind.v2.model.core;

import com.sun.xml.bind.v2.model.annotation.AnnotationSource;
import java.util.Collection;
import javax.activation.MimeType;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
public interface PropertyInfo<T, C> extends AnnotationSource {
    String displayName();

    Adapter<T, C> getAdapter();

    MimeType getExpectedMimeType();

    String getName();

    QName getSchemaType();

    ID id();

    boolean inlineBinaryData();

    boolean isCollection();

    PropertyKind kind();

    TypeInfo<T, C> parent();

    Collection<? extends TypeInfo<T, C>> ref();
}
