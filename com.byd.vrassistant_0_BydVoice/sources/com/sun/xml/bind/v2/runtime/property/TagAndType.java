package com.sun.xml.bind.v2.runtime.property;

import com.sun.xml.bind.v2.runtime.JaxBeanInfo;
import com.sun.xml.bind.v2.runtime.Name;

/* JADX INFO: loaded from: classes3.dex */
class TagAndType {
    final JaxBeanInfo beanInfo;
    final Name tagName;

    TagAndType(Name name, JaxBeanInfo jaxBeanInfo) {
        this.tagName = name;
        this.beanInfo = jaxBeanInfo;
    }
}
