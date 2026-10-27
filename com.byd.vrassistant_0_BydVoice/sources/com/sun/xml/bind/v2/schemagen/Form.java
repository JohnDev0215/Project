package com.sun.xml.bind.v2.schemagen;

import com.sun.xml.bind.v2.schemagen.xmlschema.LocalAttribute;
import com.sun.xml.bind.v2.schemagen.xmlschema.LocalElement;
import com.sun.xml.bind.v2.schemagen.xmlschema.Schema;
import com.sun.xml.txw2.TypedXmlWriter;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
enum Form {
    QUALIFIED(XmlNsForm.QUALIFIED, true) { // from class: com.sun.xml.bind.v2.schemagen.Form.1
        @Override // com.sun.xml.bind.v2.schemagen.Form
        void declare(String str, Schema schema) {
            schema._attribute(str, "qualified");
        }
    },
    UNQUALIFIED(XmlNsForm.UNQUALIFIED, 0 == true ? 1 : 0) { // from class: com.sun.xml.bind.v2.schemagen.Form.2
        @Override // com.sun.xml.bind.v2.schemagen.Form
        void declare(String str, Schema schema) {
            schema._attribute(str, "unqualified");
        }
    },
    UNSET(XmlNsForm.UNSET, 0 == true ? 1 : 0) { // from class: com.sun.xml.bind.v2.schemagen.Form.3
        @Override // com.sun.xml.bind.v2.schemagen.Form
        void declare(String str, Schema schema) {
        }
    };

    public final boolean isEffectivelyQualified;
    private final XmlNsForm xnf;

    abstract void declare(String str, Schema schema);

    Form(XmlNsForm xmlNsForm, boolean z) {
        this.xnf = xmlNsForm;
        this.isEffectivelyQualified = z;
    }

    public void writeForm(LocalElement localElement, QName qName) {
        _writeForm(localElement, qName);
    }

    public void writeForm(LocalAttribute localAttribute, QName qName) {
        _writeForm(localAttribute, qName);
    }

    private void _writeForm(TypedXmlWriter typedXmlWriter, QName qName) {
        boolean z = qName.getNamespaceURI().length() > 0;
        if (z && this != QUALIFIED) {
            typedXmlWriter._attribute("form", "qualified");
        } else {
            if (z || this != QUALIFIED) {
                return;
            }
            typedXmlWriter._attribute("form", "unqualified");
        }
    }

    public static Form get(XmlNsForm xmlNsForm) {
        for (Form form : values()) {
            if (form.xnf == xmlNsForm) {
                return form;
            }
        }
        throw new IllegalArgumentException();
    }
}
