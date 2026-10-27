package com.amazon.ion.impl;

import com.amazon.ion.SymbolToken;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes3.dex */
public final class SymbolTokenImpl implements _Private_SymbolToken {
    private final int mySid;
    private final String myText;

    SymbolTokenImpl(String str, int i) {
        this.myText = str;
        this.mySid = i;
    }

    @Override // com.amazon.ion.SymbolToken
    public String getText() {
        return this.myText;
    }

    @Override // com.amazon.ion.impl._Private_SymbolToken, com.amazon.ion.SymbolToken
    public int getSid() {
        return this.mySid;
    }

    public String toString() {
        return "SymbolToken::{text:" + this.myText + ",id:" + this.mySid + "}";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SymbolToken)) {
            return false;
        }
        SymbolToken symbolToken = (SymbolToken) obj;
        if (getText() == null || symbolToken.getText() == null) {
            return getText() == symbolToken.getText();
        }
        return getText().equals(symbolToken.getText());
    }

    public int hashCode() {
        if (getText() != null) {
            return getText().hashCode();
        }
        return 0;
    }
}
