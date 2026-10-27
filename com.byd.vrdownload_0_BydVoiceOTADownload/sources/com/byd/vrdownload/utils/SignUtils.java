package com.byd.vrdownload.utils;

import com.byd.vrdownload.model.LanguageUpdate;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: classes.dex */
public class SignUtils {
    private static final String TAG = "SignUtils";
    private static final String key = "v-resource-d";

    public static String SHA256(List list) {
        list.add("signKey=v-resource-d");
        Collections.sort(list);
        try {
            return bytesToHexString(MessageDigest.getInstance("SHA-256").digest(join(list.iterator(), "&").getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    private static String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            int i = b & FileDownloadStatus.error;
            if (i < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(i));
        }
        return sb.toString();
    }

    public static void main(String[] strArr) {
        HashMap map = new HashMap();
        map.put("vin", "123456");
        map.put(LanguageUpdate.APK_VERSION, "1.1.0");
        map.put("pkgVersion", "[]");
        map.put("reqId", "2121");
        map.put("xPlatform", "release");
        final ArrayList arrayList = new ArrayList();
        map.forEach(new BiConsumer() { // from class: com.byd.vrdownload.utils.SignUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                arrayList.add(obj + "=" + obj2);
            }
        });
        LogUtils.i(TAG, "SHA256: " + SHA256(arrayList));
    }

    private static String join(Iterator<?> it, String str) {
        if (it == null) {
            return null;
        }
        if (!it.hasNext()) {
            return "";
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return Objects.toString(next, "");
        }
        StringBuilder sb = new StringBuilder(256);
        if (next != null) {
            sb.append(next);
        }
        while (it.hasNext()) {
            if (str != null) {
                sb.append(str);
            }
            Object next2 = it.next();
            if (next2 != null) {
                sb.append(next2);
            }
        }
        return sb.toString();
    }
}
