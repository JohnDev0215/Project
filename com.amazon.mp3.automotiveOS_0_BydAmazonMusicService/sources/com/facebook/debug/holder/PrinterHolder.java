package com.facebook.debug.holder;

/* JADX INFO: loaded from: classes5.dex */
public class PrinterHolder {
    private static Printer sPrinter = NoopPrinter.INSTANCE;

    public static Printer getPrinter() {
        return sPrinter;
    }
}
