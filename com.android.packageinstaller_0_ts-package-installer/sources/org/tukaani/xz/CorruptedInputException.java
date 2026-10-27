package org.tukaani.xz;

/* JADX INFO: loaded from: classes2.dex */
public class CorruptedInputException extends XZIOException {
    private static final long serialVersionUID = 3;

    public CorruptedInputException() {
        super("Compressed data is corrupt");
    }

    public CorruptedInputException(String s) {
        super(s);
    }
}
