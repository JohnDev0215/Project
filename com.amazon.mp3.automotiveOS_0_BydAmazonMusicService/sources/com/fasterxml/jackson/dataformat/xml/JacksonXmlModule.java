package com.fasterxml.jackson.dataformat.xml;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.deser.XmlBeanDeserializerModifier;
import com.fasterxml.jackson.dataformat.xml.ser.XmlBeanSerializerModifier;

/* JADX INFO: loaded from: classes6.dex */
public class JacksonXmlModule extends SimpleModule {
    private static final long serialVersionUID = 1;
    protected boolean _cfgDefaultUseWrapper;
    protected String _cfgNameForTextElement;

    public JacksonXmlModule() {
        super("JacksonXmlModule", PackageVersion.VERSION);
        this._cfgDefaultUseWrapper = true;
        this._cfgNameForTextElement = "";
    }

    @Override // com.fasterxml.jackson.databind.module.SimpleModule, com.fasterxml.jackson.databind.Module
    public void setupModule(Module.SetupContext setupContext) {
        setupContext.addBeanSerializerModifier(new XmlBeanSerializerModifier());
        setupContext.addBeanDeserializerModifier(new XmlBeanDeserializerModifier(this._cfgNameForTextElement));
        setupContext.insertAnnotationIntrospector(_constructIntrospector());
        if (this._cfgNameForTextElement != "") {
            ((XmlMapper) setupContext.getOwner()).setXMLTextElementName(this._cfgNameForTextElement);
        }
        super.setupModule(setupContext);
    }

    public void setDefaultUseWrapper(boolean z) {
        this._cfgDefaultUseWrapper = z;
    }

    public void setXMLTextElementName(String str) {
        this._cfgNameForTextElement = str;
    }

    protected AnnotationIntrospector _constructIntrospector() {
        return new JacksonXmlAnnotationIntrospector(this._cfgDefaultUseWrapper);
    }
}
