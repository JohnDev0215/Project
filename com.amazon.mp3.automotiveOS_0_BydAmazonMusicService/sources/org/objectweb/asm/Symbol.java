package org.objectweb.asm;

/* JADX INFO: loaded from: classes6.dex */
abstract class Symbol {
    final long data;
    final int index;
    int info;
    final String name;
    final String owner;
    final int tag;
    final String value;

    Symbol(int i, int i2, String str, String str2, String str3, long j) {
        this.index = i;
        this.tag = i2;
        this.owner = str;
        this.name = str2;
        this.value = str3;
        this.data = j;
    }

    int getArgumentsAndReturnSizes() {
        if (this.info == 0) {
            this.info = Type.getArgumentsAndReturnSizes(this.value);
        }
        return this.info;
    }
}
