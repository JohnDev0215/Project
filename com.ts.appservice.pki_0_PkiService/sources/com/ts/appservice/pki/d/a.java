package com.ts.appservice.pki.d;

import android.text.TextUtils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static ArrayList<Byte> a(byte[] bArr) {
        if (bArr == null) {
            b.d("FileUtil", " sourceData == null ");
            return null;
        }
        ArrayList<Byte> arrayList = new ArrayList<>();
        for (byte b2 : bArr) {
            arrayList.add(Byte.valueOf(b2));
        }
        return arrayList;
    }

    public static byte[] b(ArrayList<Byte> arrayList) {
        if (arrayList == null) {
            return null;
        }
        byte[] bArr = new byte[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            bArr[i] = arrayList.get(i).byteValue();
        }
        return bArr;
    }

    public static ArrayList<Byte> c(String str) {
        ArrayList<Byte> arrayList = new ArrayList<>();
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(str)) {
            b.f("FileUtil", " hexToByteList is null");
            return arrayList;
        }
        int i = 0;
        while (i < str.length() - 1) {
            int i2 = i + 2;
            arrayList.add(Byte.valueOf((byte) Integer.parseInt(str.substring(i, i2), 16)));
            i = i2;
        }
        return arrayList;
    }

    public static boolean d(ArrayList<Byte> arrayList, ArrayList<Byte> arrayList2) {
        if (arrayList2 != null && arrayList != null && arrayList2.size() == arrayList.size()) {
            for (int i = 0; i < arrayList2.size(); i++) {
                if (!arrayList.get(i).equals(arrayList2.get(i))) {
                    b.d("FileUtil", "isSame not same Data ");
                    return false;
                }
            }
            return true;
        }
        b.d("FileUtil", "two data has null , or size is not equal");
        b.d("FileUtil", "two data has null hasSaveData " + arrayList);
        b.d("FileUtil", "two data has null sourceData " + arrayList2);
        return false;
    }

    public static boolean e(ArrayList<Byte> arrayList, byte[] bArr) {
        if (bArr == null || arrayList == null || bArr.length != arrayList.size()) {
            b.d("FileUtil", "two data has null , or size is not equal isSameData hasSaveData " + arrayList + " sourceData " + Arrays.toString(bArr));
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (!arrayList.get(i).equals(Byte.valueOf(bArr[i]))) {
                b.d("FileUtil", "isSame not same Data ");
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Byte> f(String str) throws Throwable {
        byte[] bArrG = g(str);
        if (bArrG == null) {
            return null;
        }
        ArrayList<Byte> arrayList = new ArrayList<>();
        for (byte b2 : bArrG) {
            arrayList.add(Byte.valueOf(b2));
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.FileInputStream] */
    public static byte[] g(String str) throws Throwable {
        FileInputStream fileInputStream;
        File file = new File(str);
        ?? r3 = 0;
        try {
            if (!file.exists()) {
                b.d("FileUtil", "readFileToByteArray ，File doesn't exist! " + str);
                return null;
            }
            try {
                fileInputStream = new FileInputStream(file);
            } catch (Exception e) {
                e = e;
                fileInputStream = null;
            } catch (Throwable th) {
                th = th;
                if (r3 != 0) {
                    try {
                        r3.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                throw th;
            }
            try {
                long size = fileInputStream.getChannel().size();
                b.f("FileUtil", "readFileToByteArray inSize " + size);
                if (size == 0) {
                    b.f("FileUtil", "The FileInputStream has no content!");
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    return null;
                }
                byte[] bArr = new byte[fileInputStream.available()];
                b.b("FileUtil", "the total number of bytes read into the buffer is " + fileInputStream.read(bArr));
                try {
                    fileInputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                return bArr;
            } catch (Exception e5) {
                e = e5;
                e.printStackTrace();
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            r3 = str;
        }
    }

    public static boolean h(ArrayList<Byte> arrayList, String str) {
        byte[] bArr = new byte[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            bArr[i] = arrayList.get(i).byteValue();
        }
        return i(bArr, str);
    }

    public static boolean i(byte[] bArr, String str) throws Throwable {
        FileOutputStream fileOutputStream;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            File file = new File(str);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            fileOutputStream = new FileOutputStream(file);
            try {
                try {
                    BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(fileOutputStream);
                    try {
                        bufferedOutputStream2.write(bArr);
                        bufferedOutputStream2.flush();
                        try {
                            bufferedOutputStream2.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            fileOutputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        return true;
                    } catch (Exception e3) {
                        e = e3;
                        bufferedOutputStream = bufferedOutputStream2;
                        b.d("FileUtil", "write fail： " + str);
                        e.printStackTrace();
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        if (fileOutputStream == null) {
                            return false;
                        }
                        try {
                            fileOutputStream.close();
                            return false;
                        } catch (IOException e5) {
                            e5.printStackTrace();
                            return false;
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedOutputStream = bufferedOutputStream2;
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e6) {
                                e6.printStackTrace();
                            }
                        }
                        if (fileOutputStream == null) {
                            throw th;
                        }
                        try {
                            fileOutputStream.close();
                            throw th;
                        } catch (IOException e7) {
                            e7.printStackTrace();
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e8) {
                e = e8;
            }
        } catch (Exception e9) {
            e = e9;
            fileOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
    }
}
