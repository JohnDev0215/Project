package com.amazon.ion.impl.lite;

import com.amazon.ion.IonText;

/* JADX INFO: loaded from: classes3.dex */
abstract class IonTextLite extends IonValueLite implements IonText {
    private String _text_value;

    @Override // com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public abstract IonTextLite mo103clone();

    protected IonTextLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    IonTextLite(IonTextLite ionTextLite, IonContext ionContext) {
        super(ionTextLite, ionContext);
        this._text_value = ionTextLite._text_value;
    }

    public void setValue(String str) {
        checkForLock();
        _set_value(str);
    }

    protected final String _get_value() {
        return this._text_value;
    }

    public String stringValue() {
        return this._text_value;
    }

    protected final void _set_value(String str) {
        this._text_value = str;
        _isNullValue(str == null);
    }
}
