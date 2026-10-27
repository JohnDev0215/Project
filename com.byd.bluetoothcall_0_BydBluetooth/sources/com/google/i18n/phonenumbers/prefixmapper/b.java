package com.google.i18n.phonenumbers.prefixmapper;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

/* JADX INFO: compiled from: FlyweightMapStorage.java */
/* JADX INFO: loaded from: classes.dex */
final class b extends c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4153c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f4154d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ByteBuffer f4155e;
    private ByteBuffer f;
    private String[] g;

    b() {
    }

    private void h(SortedSet<String> sortedSet, SortedMap<Integer, String> sortedMap) {
        int i = i(sortedSet.size() - 1);
        this.f4154d = i;
        this.f = ByteBuffer.allocate(this.f4156a * i);
        String[] strArr = new String[sortedSet.size()];
        this.g = strArr;
        sortedSet.toArray(strArr);
        int i2 = 0;
        for (int i3 = 0; i3 < this.f4156a; i3++) {
            m(this.f, this.f4154d, i2, Arrays.binarySearch(this.g, sortedMap.get(Integer.valueOf(l(this.f4155e, this.f4153c, i3)))));
            i2++;
        }
    }

    private static int i(int i) {
        return i <= 32767 ? 2 : 4;
    }

    private void j(ObjectInput objectInput) throws IOException {
        this.f4156a = objectInput.readInt();
        ByteBuffer byteBuffer = this.f4155e;
        if (byteBuffer == null || byteBuffer.capacity() < this.f4156a) {
            this.f4155e = ByteBuffer.allocate(this.f4156a * this.f4153c);
        }
        ByteBuffer byteBuffer2 = this.f;
        if (byteBuffer2 == null || byteBuffer2.capacity() < this.f4156a) {
            this.f = ByteBuffer.allocate(this.f4156a * this.f4154d);
        }
        for (int i = 0; i < this.f4156a; i++) {
            k(objectInput, this.f4153c, this.f4155e, i);
            k(objectInput, this.f4154d, this.f, i);
        }
    }

    private static void k(ObjectInput objectInput, int i, ByteBuffer byteBuffer, int i2) throws IOException {
        int i3 = i2 * i;
        if (i == 2) {
            byteBuffer.putShort(i3, objectInput.readShort());
        } else {
            byteBuffer.putInt(i3, objectInput.readInt());
        }
    }

    private static int l(ByteBuffer byteBuffer, int i, int i2) {
        int i3 = i2 * i;
        return i == 2 ? byteBuffer.getShort(i3) : byteBuffer.getInt(i3);
    }

    private static void m(ByteBuffer byteBuffer, int i, int i2, int i3) {
        int i4 = i2 * i;
        if (i == 2) {
            byteBuffer.putShort(i4, (short) i3);
        } else {
            byteBuffer.putInt(i4, i3);
        }
    }

    private static void n(ObjectOutput objectOutput, int i, ByteBuffer byteBuffer, int i2) throws IOException {
        int i3 = i2 * i;
        if (i == 2) {
            objectOutput.writeShort(byteBuffer.getShort(i3));
        } else {
            objectOutput.writeInt(byteBuffer.getInt(i3));
        }
    }

    @Override // com.google.i18n.phonenumbers.prefixmapper.c
    public String a(int i) {
        return this.g[l(this.f, this.f4154d, i)];
    }

    @Override // com.google.i18n.phonenumbers.prefixmapper.c
    public int d(int i) {
        return l(this.f4155e, this.f4153c, i);
    }

    @Override // com.google.i18n.phonenumbers.prefixmapper.c
    public void e(ObjectInput objectInput) throws IOException {
        this.f4153c = objectInput.readInt();
        this.f4154d = objectInput.readInt();
        int i = objectInput.readInt();
        this.f4157b.clear();
        for (int i2 = 0; i2 < i; i2++) {
            this.f4157b.add(Integer.valueOf(objectInput.readInt()));
        }
        int i3 = objectInput.readInt();
        String[] strArr = this.g;
        if (strArr == null || strArr.length < i3) {
            this.g = new String[i3];
        }
        for (int i4 = 0; i4 < i3; i4++) {
            this.g[i4] = objectInput.readUTF();
        }
        j(objectInput);
    }

    @Override // com.google.i18n.phonenumbers.prefixmapper.c
    public void f(SortedMap<Integer, String> sortedMap) {
        TreeSet treeSet = new TreeSet();
        this.f4156a = sortedMap.size();
        int i = i(sortedMap.lastKey().intValue());
        this.f4153c = i;
        this.f4155e = ByteBuffer.allocate(this.f4156a * i);
        int i2 = 0;
        for (Map.Entry<Integer, String> entry : sortedMap.entrySet()) {
            int iIntValue = entry.getKey().intValue();
            m(this.f4155e, this.f4153c, i2, iIntValue);
            this.f4157b.add(Integer.valueOf(((int) Math.log10(iIntValue)) + 1));
            treeSet.add(entry.getValue());
            i2++;
        }
        h(treeSet, sortedMap);
    }

    @Override // com.google.i18n.phonenumbers.prefixmapper.c
    public void g(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeInt(this.f4153c);
        objectOutput.writeInt(this.f4154d);
        objectOutput.writeInt(this.f4157b.size());
        Iterator<Integer> it = this.f4157b.iterator();
        while (it.hasNext()) {
            objectOutput.writeInt(it.next().intValue());
        }
        objectOutput.writeInt(this.g.length);
        for (String str : this.g) {
            objectOutput.writeUTF(str);
        }
        objectOutput.writeInt(this.f4156a);
        for (int i = 0; i < this.f4156a; i++) {
            n(objectOutput, this.f4153c, this.f4155e, i);
            n(objectOutput, this.f4154d, this.f, i);
        }
    }
}
