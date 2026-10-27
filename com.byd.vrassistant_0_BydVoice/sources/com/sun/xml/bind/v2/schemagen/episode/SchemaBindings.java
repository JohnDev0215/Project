package com.sun.xml.bind.v2.schemagen.episode;

import com.byd.mediacenter.api.media.MediaControl;
import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

/* JADX INFO: loaded from: classes3.dex */
public interface SchemaBindings extends TypedXmlWriter {
    @XmlElement(MediaControl.PARAM_PACKAGE)
    Package _package();

    @XmlAttribute
    void map(boolean z);
}
