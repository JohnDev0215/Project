package com.android.certinstaller;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: classes.dex */
class Util {
    static byte[] toBytes(Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.close();
        } catch (Exception e) {
            Log.w("certinstaller.Util", "toBytes(): " + e + ": " + obj);
        }
        return byteArrayOutputStream.toByteArray();
    }

    static <T> T fromBytes(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return (T) new ObjectInputStream(new ByteArrayInputStream(bArr)).readObject();
        } catch (Exception e) {
            Log.w("certinstaller.Util", "fromBytes(): " + e);
            return null;
        }
    }
}
