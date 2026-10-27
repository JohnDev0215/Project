package com.android.dx.command;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/* JADX INFO: loaded from: classes.dex */
public class DxConsole {
    public static PrintStream out = System.out;
    public static PrintStream err = System.err;
    public static final PrintStream noop = new PrintStream(new OutputStream() { // from class: com.android.dx.command.DxConsole.1
        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
        }
    });
}
