package com.sun.xml.txw2;

import com.sun.xml.txw2.annotation.XmlElement;
import com.sun.xml.txw2.annotation.XmlNamespace;
import com.sun.xml.txw2.output.TXWSerializer;
import com.sun.xml.txw2.output.XmlSerializer;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
public abstract class TXW {
    private TXW() {
    }

    static QName getTagName(Class<?> cls) {
        String strValue;
        String strValue2;
        Package r7;
        XmlNamespace xmlNamespace;
        XmlElement xmlElement = (XmlElement) cls.getAnnotation(XmlElement.class);
        if (xmlElement != null) {
            strValue = xmlElement.value();
            strValue2 = xmlElement.ns();
        } else {
            strValue = "";
            strValue2 = "##default";
        }
        if (strValue.length() == 0) {
            String name = cls.getName();
            int iLastIndexOf = name.lastIndexOf(46);
            if (iLastIndexOf >= 0) {
                name = name.substring(iLastIndexOf + 1);
            }
            strValue = Character.toLowerCase(name.charAt(0)) + name.substring(1);
        }
        if (strValue2.equals("##default") && (r7 = cls.getPackage()) != null && (xmlNamespace = (XmlNamespace) r7.getAnnotation(XmlNamespace.class)) != null) {
            strValue2 = xmlNamespace.value();
        }
        return new QName(strValue2.equals("##default") ? "" : strValue2, strValue);
    }

    public static <T extends TypedXmlWriter> T create(Class<T> cls, XmlSerializer xmlSerializer) {
        if (xmlSerializer instanceof TXWSerializer) {
            return (T) ((TXWSerializer) xmlSerializer).txw._element(cls);
        }
        Document document = new Document(xmlSerializer);
        QName tagName = getTagName(cls);
        return (T) new ContainerElement(document, null, tagName.getNamespaceURI(), tagName.getLocalPart())._cast(cls);
    }

    public static <T extends TypedXmlWriter> T create(QName qName, Class<T> cls, XmlSerializer xmlSerializer) {
        if (xmlSerializer instanceof TXWSerializer) {
            return (T) ((TXWSerializer) xmlSerializer).txw._element(qName, cls);
        }
        return (T) new ContainerElement(new Document(xmlSerializer), null, qName.getNamespaceURI(), qName.getLocalPart())._cast(cls);
    }
}
