package org.apache.commons.compress.archivers.dump;

/* JADX INFO: loaded from: classes4.dex */
public class UnrecognizedFormatException extends DumpArchiveException {
    private static final long serialVersionUID = 1;

    public UnrecognizedFormatException() {
        super("this is not a recognized format.");
    }
}
