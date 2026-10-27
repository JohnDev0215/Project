package com.fasterxml.jackson.module.jaxb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;

/* JADX INFO: loaded from: classes6.dex */
public class JaxbAnnotationModule extends Module {
    protected JaxbAnnotationIntrospector _introspector;
    protected JsonInclude.Include _nonNillableInclusion;
    protected Priority _priority = Priority.PRIMARY;
    protected String _nameUsedForXmlValue = "value";

    public enum Priority {
        PRIMARY,
        SECONDARY
    }

    public JaxbAnnotationModule() {
    }

    public JaxbAnnotationModule(JaxbAnnotationIntrospector jaxbAnnotationIntrospector) {
        this._introspector = jaxbAnnotationIntrospector;
    }

    @Override // com.fasterxml.jackson.databind.Module
    public String getModuleName() {
        return getClass().getSimpleName();
    }

    @Override // com.fasterxml.jackson.databind.Module, com.fasterxml.jackson.core.Versioned
    public Version version() {
        return PackageVersion.VERSION;
    }

    @Override // com.fasterxml.jackson.databind.Module
    public void setupModule(Module.SetupContext setupContext) {
        JaxbAnnotationIntrospector jaxbAnnotationIntrospector = this._introspector;
        if (jaxbAnnotationIntrospector == null) {
            jaxbAnnotationIntrospector = new JaxbAnnotationIntrospector(setupContext.getTypeFactory());
            JsonInclude.Include include = this._nonNillableInclusion;
            if (include != null) {
                jaxbAnnotationIntrospector.setNonNillableInclusion(include);
            }
            jaxbAnnotationIntrospector.setNameUsedForXmlValue(this._nameUsedForXmlValue);
        }
        int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$module$jaxb$JaxbAnnotationModule$Priority[this._priority.ordinal()];
        if (i == 1) {
            setupContext.insertAnnotationIntrospector(jaxbAnnotationIntrospector);
        } else {
            if (i != 2) {
                return;
            }
            setupContext.appendAnnotationIntrospector(jaxbAnnotationIntrospector);
        }
    }

    /* JADX INFO: renamed from: com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$module$jaxb$JaxbAnnotationModule$Priority;

        static {
            int[] iArr = new int[Priority.values().length];
            $SwitchMap$com$fasterxml$jackson$module$jaxb$JaxbAnnotationModule$Priority = iArr;
            try {
                iArr[Priority.PRIMARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$module$jaxb$JaxbAnnotationModule$Priority[Priority.SECONDARY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public JaxbAnnotationModule setPriority(Priority priority) {
        this._priority = priority;
        return this;
    }

    public Priority getPriority() {
        return this._priority;
    }

    public JaxbAnnotationModule setNonNillableInclusion(JsonInclude.Include include) {
        this._nonNillableInclusion = include;
        JaxbAnnotationIntrospector jaxbAnnotationIntrospector = this._introspector;
        if (jaxbAnnotationIntrospector != null) {
            jaxbAnnotationIntrospector.setNonNillableInclusion(include);
        }
        return this;
    }

    public JsonInclude.Include getNonNillableInclusion() {
        return this._nonNillableInclusion;
    }

    public JaxbAnnotationModule setNameUsedForXmlValue(String str) {
        this._nameUsedForXmlValue = str;
        return this;
    }

    public String getNameUsedForXmlValue() {
        return this._nameUsedForXmlValue;
    }
}
