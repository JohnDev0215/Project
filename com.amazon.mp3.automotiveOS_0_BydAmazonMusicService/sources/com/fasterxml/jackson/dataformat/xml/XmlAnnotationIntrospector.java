package com.fasterxml.jackson.dataformat.xml;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

/* JADX INFO: loaded from: classes6.dex */
public interface XmlAnnotationIntrospector {
    String findNamespace(Annotated annotated);

    Boolean isOutputAsAttribute(Annotated annotated);

    Boolean isOutputAsCData(Annotated annotated);

    Boolean isOutputAsText(Annotated annotated);

    void setDefaultUseWrapper(boolean z);

    public static class Pair extends AnnotationIntrospectorPair implements XmlAnnotationIntrospector {
        private static final long serialVersionUID = 1;
        protected final XmlAnnotationIntrospector _xmlPrimary;
        protected final XmlAnnotationIntrospector _xmlSecondary;

        /* JADX WARN: Multi-variable type inference failed */
        public Pair(AnnotationIntrospector annotationIntrospector, AnnotationIntrospector annotationIntrospector2) {
            super(annotationIntrospector, annotationIntrospector2);
            if (annotationIntrospector instanceof XmlAnnotationIntrospector) {
                this._xmlPrimary = (XmlAnnotationIntrospector) annotationIntrospector;
            } else if (annotationIntrospector instanceof JaxbAnnotationIntrospector) {
                this._xmlPrimary = new JaxbWrapper((JaxbAnnotationIntrospector) annotationIntrospector);
            } else {
                this._xmlPrimary = null;
            }
            if (annotationIntrospector2 instanceof XmlAnnotationIntrospector) {
                this._xmlSecondary = (XmlAnnotationIntrospector) annotationIntrospector2;
            } else if (annotationIntrospector2 instanceof JaxbAnnotationIntrospector) {
                this._xmlSecondary = new JaxbWrapper((JaxbAnnotationIntrospector) annotationIntrospector2);
            } else {
                this._xmlSecondary = null;
            }
        }

        public static Pair instance(AnnotationIntrospector annotationIntrospector, AnnotationIntrospector annotationIntrospector2) {
            return new Pair(annotationIntrospector, annotationIntrospector2);
        }

        @Override // com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector
        public String findNamespace(Annotated annotated) {
            XmlAnnotationIntrospector xmlAnnotationIntrospector;
            XmlAnnotationIntrospector xmlAnnotationIntrospector2 = this._xmlPrimary;
            String strFindNamespace = xmlAnnotationIntrospector2 == null ? null : xmlAnnotationIntrospector2.findNamespace(annotated);
            return (strFindNamespace != null || (xmlAnnotationIntrospector = this._xmlSecondary) == null) ? strFindNamespace : xmlAnnotationIntrospector.findNamespace(annotated);
        }

        @Override // com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector
        public Boolean isOutputAsAttribute(Annotated annotated) {
            XmlAnnotationIntrospector xmlAnnotationIntrospector;
            XmlAnnotationIntrospector xmlAnnotationIntrospector2 = this._xmlPrimary;
            Boolean boolIsOutputAsAttribute = xmlAnnotationIntrospector2 == null ? null : xmlAnnotationIntrospector2.isOutputAsAttribute(annotated);
            return (boolIsOutputAsAttribute != null || (xmlAnnotationIntrospector = this._xmlSecondary) == null) ? boolIsOutputAsAttribute : xmlAnnotationIntrospector.isOutputAsAttribute(annotated);
        }

        @Override // com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector
        public Boolean isOutputAsText(Annotated annotated) {
            XmlAnnotationIntrospector xmlAnnotationIntrospector;
            XmlAnnotationIntrospector xmlAnnotationIntrospector2 = this._xmlPrimary;
            Boolean boolIsOutputAsText = xmlAnnotationIntrospector2 == null ? null : xmlAnnotationIntrospector2.isOutputAsText(annotated);
            return (boolIsOutputAsText != null || (xmlAnnotationIntrospector = this._xmlSecondary) == null) ? boolIsOutputAsText : xmlAnnotationIntrospector.isOutputAsText(annotated);
        }

        @Override // com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector
        public Boolean isOutputAsCData(Annotated annotated) {
            XmlAnnotationIntrospector xmlAnnotationIntrospector;
            XmlAnnotationIntrospector xmlAnnotationIntrospector2 = this._xmlPrimary;
            Boolean boolIsOutputAsCData = xmlAnnotationIntrospector2 == null ? null : xmlAnnotationIntrospector2.isOutputAsCData(annotated);
            return (boolIsOutputAsCData != null || (xmlAnnotationIntrospector = this._xmlSecondary) == null) ? boolIsOutputAsCData : xmlAnnotationIntrospector.isOutputAsCData(annotated);
        }

        @Override // com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector
        public void setDefaultUseWrapper(boolean z) {
            XmlAnnotationIntrospector xmlAnnotationIntrospector = this._xmlPrimary;
            if (xmlAnnotationIntrospector != null) {
                xmlAnnotationIntrospector.setDefaultUseWrapper(z);
            }
            XmlAnnotationIntrospector xmlAnnotationIntrospector2 = this._xmlSecondary;
            if (xmlAnnotationIntrospector2 != null) {
                xmlAnnotationIntrospector2.setDefaultUseWrapper(z);
            }
        }
    }

    public static class JaxbWrapper implements XmlAnnotationIntrospector {
        protected final JaxbAnnotationIntrospector _intr;

        @Override // com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector
        public Boolean isOutputAsCData(Annotated annotated) {
            return null;
        }

        @Override // com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector
        public void setDefaultUseWrapper(boolean z) {
        }

        public JaxbWrapper(JaxbAnnotationIntrospector jaxbAnnotationIntrospector) {
            this._intr = jaxbAnnotationIntrospector;
        }

        @Override // com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector
        public String findNamespace(Annotated annotated) {
            return this._intr.findNamespace(annotated);
        }

        @Override // com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector
        public Boolean isOutputAsAttribute(Annotated annotated) {
            return this._intr.isOutputAsAttribute(annotated);
        }

        @Override // com.fasterxml.jackson.dataformat.xml.XmlAnnotationIntrospector
        public Boolean isOutputAsText(Annotated annotated) {
            return this._intr.isOutputAsText(annotated);
        }
    }
}
