package com.sun.xml.bind.v2.runtime.property;

import com.sun.xml.bind.v2.runtime.unmarshaller.ChildLoader;
import com.sun.xml.bind.v2.util.QNameMap;
import javax.xml.namespace.QName;
import org.bouncycastle.i18n.TextBundle;

/* JADX INFO: loaded from: classes3.dex */
public interface StructureLoaderBuilder {
    public static final QName TEXT_HANDLER = new QName("\u0000", TextBundle.TEXT_ENTRY);
    public static final QName CATCH_ALL = new QName("\u0000", "catchAll");

    void buildChildElementUnmarshallers(UnmarshallerChain unmarshallerChain, QNameMap<ChildLoader> qNameMap);
}
