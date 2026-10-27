package com.fasterxml.jackson.datatype.guava;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.guava.ser.GuavaBeanSerializerModifier;
import com.google.common.base.Preconditions;
import com.google.common.collect.BoundType;

/* JADX INFO: loaded from: classes6.dex */
public class GuavaModule extends Module {
    private final String NAME = "GuavaModule";
    protected boolean _cfgHandleAbsentAsNull = true;
    protected BoundType _defaultBoundType;

    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override // com.fasterxml.jackson.databind.Module
    public String getModuleName() {
        return "GuavaModule";
    }

    public int hashCode() {
        return -1784411382;
    }

    @Override // com.fasterxml.jackson.databind.Module, com.fasterxml.jackson.core.Versioned
    public Version version() {
        return PackageVersion.VERSION;
    }

    @Override // com.fasterxml.jackson.databind.Module
    public void setupModule(Module.SetupContext setupContext) {
        setupContext.addDeserializers(new GuavaDeserializers(this._defaultBoundType));
        setupContext.addSerializers(new GuavaSerializers());
        setupContext.addTypeModifier(new GuavaTypeModifier());
        if (this._cfgHandleAbsentAsNull) {
            setupContext.addBeanSerializerModifier(new GuavaBeanSerializerModifier());
        }
    }

    public GuavaModule configureAbsentsAsNulls(boolean z) {
        this._cfgHandleAbsentAsNull = z;
        return this;
    }

    public GuavaModule defaultBoundType(BoundType boundType) {
        Preconditions.checkNotNull(boundType);
        this._defaultBoundType = boundType;
        return this;
    }
}
