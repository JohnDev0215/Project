package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public interface Intercepter {
    Object intercept(UnmarshallingContext.State state, Object obj) throws SAXException;
}
