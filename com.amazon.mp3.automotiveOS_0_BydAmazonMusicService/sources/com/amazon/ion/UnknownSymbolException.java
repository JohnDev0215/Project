package com.amazon.ion;

/* JADX INFO: loaded from: classes3.dex */
public class UnknownSymbolException extends IonException {
    private final int mySid;
    private final String myText;

    public UnknownSymbolException(int i) {
        this.mySid = i;
        this.myText = null;
    }

    public UnknownSymbolException(String str) {
        this.myText = str;
        this.mySid = 0;
    }

    public int getSid() {
        return this.mySid;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String str = this.myText;
        if (str != null) {
            return str;
        }
        return "Unknown symbol text for $" + this.mySid;
    }
}
