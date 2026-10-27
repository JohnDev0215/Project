package com.google.i18n.phonenumbers.prefixmapper;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Iterator;
import java.util.SortedMap;

/* JADX INFO: compiled from: DefaultMapStorage.java */
/* JADX INFO: loaded from: classes.dex */
class a extends c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int[] f4151c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String[] f4152d;

    @Override // com.google.i18n.phonenumbers.prefixmapper.c
    public String a(int i) {
        return this.f4152d[i];
    }

    @Override // com.google.i18n.phonenumbers.prefixmapper.c
    public int d(int i) {
        return this.f4151c[i];
    }

    @Override // com.google.i18n.phonenumbers.prefixmapper.c
    public void e(ObjectInput objectInput) throws IOException {
        int i = objectInput.readInt();
        this.f4156a = i;
        int[] iArr = this.f4151c;
        if (iArr == null || iArr.length < i) {
            this.f4151c = new int[i];
        }
        String[] strArr = this.f4152d;
        if (strArr == null || strArr.length < i) {
            this.f4152d = new String[i];
        }
        for (int i2 = 0; i2 < this.f4156a; i2++) {
            this.f4151c[i2] = objectInput.readInt();
            this.f4152d[i2] = objectInput.readUTF();
        }
        int i3 = objectInput.readInt();
        this.f4157b.clear();
        for (int i4 = 0; i4 < i3; i4++) {
            this.f4157b.add(Integer.valueOf(objectInput.readInt()));
        }
    }

    @Override // com.google.i18n.phonenumbers.prefixmapper.c
    public void f(SortedMap<Integer, String> sortedMap) {
        int size = sortedMap.size();
        this.f4156a = size;
        this.f4151c = new int[size];
        this.f4152d = new String[size];
        Iterator<Integer> it = sortedMap.keySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            this.f4151c[i] = iIntValue;
            this.f4157b.add(Integer.valueOf(((int) Math.log10(iIntValue)) + 1));
            i++;
        }
        sortedMap.values().toArray(this.f4152d);
    }

    @Override // com.google.i18n.phonenumbers.prefixmapper.c
    public void g(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeInt(this.f4156a);
        for (int i = 0; i < this.f4156a; i++) {
            objectOutput.writeInt(this.f4151c[i]);
            objectOutput.writeUTF(this.f4152d[i]);
        }
        objectOutput.writeInt(this.f4157b.size());
        Iterator<Integer> it = this.f4157b.iterator();
        while (it.hasNext()) {
            objectOutput.writeInt(it.next().intValue());
        }
    }
}
