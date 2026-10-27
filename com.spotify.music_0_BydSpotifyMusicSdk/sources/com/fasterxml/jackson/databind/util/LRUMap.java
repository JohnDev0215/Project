package com.fasterxml.jackson.databind.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import p.yd1;

/* JADX INFO: loaded from: classes.dex */
public class LRUMap<K, V> implements yd1, Serializable {
    private static final long serialVersionUID = 1;
    public transient int c;

    public LRUMap(int i, int i2) {
        new ConcurrentHashMap(i, 0.8f, 4);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        this.c = objectInputStream.readInt();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.c);
    }

    public Object readResolve() {
        int i = this.c;
        return new LRUMap(i, i);
    }
}
