package com.ctc.wstx.msv;

import com.ctc.wstx.api.ValidatorConfig;
import org.codehaus.stax2.validation.XMLValidationSchemaFactory;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseSchemaFactory extends XMLValidationSchemaFactory {
    protected final ValidatorConfig mConfig;

    protected BaseSchemaFactory(String str) {
        super(str);
        this.mConfig = ValidatorConfig.createDefaults();
    }
}
