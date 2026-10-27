package com.byd.auto_emanual.util;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class CompressionUtil {
    private static final String TAG = "CompressionUtil";

    public static String compress(String value) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        if (TextUtils.isEmpty(value)) {
            return "";
        }
        GZIPOutputStream gZIPOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                try {
                    GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(byteArrayOutputStream);
                    try {
                        gZIPOutputStream2.write(value.getBytes());
                        gZIPOutputStream2.close();
                        String strEncodeToString = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
                        String str = TAG;
                        CloseUtil.close(gZIPOutputStream2, str);
                        CloseUtil.close(byteArrayOutputStream, str);
                        return strEncodeToString;
                    } catch (Exception e) {
                        e = e;
                        gZIPOutputStream = gZIPOutputStream2;
                        String str2 = TAG;
                        MLog.e(str2, "compress error.", e);
                        CloseUtil.close(gZIPOutputStream, str2);
                        CloseUtil.close(byteArrayOutputStream, str2);
                        return "";
                    } catch (Throwable th) {
                        th = th;
                        gZIPOutputStream = gZIPOutputStream2;
                        String str3 = TAG;
                        CloseUtil.close(gZIPOutputStream, str3);
                        CloseUtil.close(byteArrayOutputStream, str3);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            byteArrayOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
        }
    }

    public static String decompress(String compressedStr) throws Throwable {
        GZIPInputStream gZIPInputStream;
        Throwable th;
        BufferedReader bufferedReader;
        Exception e;
        if (TextUtils.isEmpty(compressedStr)) {
            return "";
        }
        try {
            gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(Base64.getDecoder().decode(compressedStr)));
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(gZIPInputStream, "UTF-8"));
            } catch (Exception e2) {
                e = e2;
                bufferedReader = null;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
                String str = TAG;
                CloseUtil.close(gZIPInputStream, str);
                CloseUtil.close(bufferedReader, str);
                throw th;
            }
        } catch (Exception e3) {
            gZIPInputStream = null;
            e = e3;
            bufferedReader = null;
        } catch (Throwable th3) {
            gZIPInputStream = null;
            th = th3;
            bufferedReader = null;
        }
        try {
            try {
                String str2 = (String) bufferedReader.lines().collect(Collectors.joining());
                String str3 = TAG;
                CloseUtil.close(gZIPInputStream, str3);
                CloseUtil.close(bufferedReader, str3);
                return str2;
            } catch (Throwable th4) {
                th = th4;
                String str4 = TAG;
                CloseUtil.close(gZIPInputStream, str4);
                CloseUtil.close(bufferedReader, str4);
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            String str5 = TAG;
            MLog.e(str5, "decompress error.", e);
            CloseUtil.close(gZIPInputStream, str5);
            CloseUtil.close(bufferedReader, str5);
            return "";
        }
    }
}
