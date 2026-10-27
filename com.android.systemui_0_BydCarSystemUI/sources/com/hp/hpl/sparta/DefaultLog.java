package com.hp.hpl.sparta;

/* JADX INFO: loaded from: classes2.dex */
class DefaultLog implements ParseLog {
    DefaultLog() {
    }

    @Override // com.hp.hpl.sparta.ParseLog
    public void error(String str, String str2, int i) {
        System.err.println(new StringBuffer().append(str2).append("(").append(i).append("): ").append(str).append(" (ERROR)").toString());
    }

    @Override // com.hp.hpl.sparta.ParseLog
    public void note(String str, String str2, int i) {
        System.out.println(new StringBuffer().append(str2).append("(").append(i).append("): ").append(str).append(" (NOTE)").toString());
    }

    @Override // com.hp.hpl.sparta.ParseLog
    public void warning(String str, String str2, int i) {
        System.out.println(new StringBuffer().append(str2).append("(").append(i).append("): ").append(str).append(" (WARNING)").toString());
    }
}
