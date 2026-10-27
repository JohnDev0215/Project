package com.hp.hpl.sparta;

import java.io.PrintStream;

/* JADX INFO: loaded from: classes.dex */
class b implements k {
    b() {
    }

    @Override // com.hp.hpl.sparta.k
    public void a(String str, String str2, int i) {
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2);
        stringBuffer.append("(");
        stringBuffer.append(i);
        stringBuffer.append("): ");
        stringBuffer.append(str);
        stringBuffer.append(" (NOTE)");
        printStream.println(stringBuffer.toString());
    }

    @Override // com.hp.hpl.sparta.k
    public void b(String str, String str2, int i) {
        PrintStream printStream = System.err;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2);
        stringBuffer.append("(");
        stringBuffer.append(i);
        stringBuffer.append("): ");
        stringBuffer.append(str);
        stringBuffer.append(" (ERROR)");
        printStream.println(stringBuffer.toString());
    }

    @Override // com.hp.hpl.sparta.k
    public void c(String str, String str2, int i) {
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2);
        stringBuffer.append("(");
        stringBuffer.append(i);
        stringBuffer.append("): ");
        stringBuffer.append(str);
        stringBuffer.append(" (WARNING)");
        printStream.println(stringBuffer.toString());
    }
}
