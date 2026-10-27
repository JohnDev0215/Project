package com.bydux.api.common.tools.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.widget.ImageView;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class YUVDataUtil {
    public static byte[] gzip(byte[] data) {
        byte[] byteArray = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(data);
            gZIPOutputStream.finish();
            gZIPOutputStream.close();
            byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Exception e) {
            e.printStackTrace();
            return byteArray;
        }
    }

    public static byte[] unGZip(byte[] data) {
        byte[] byteArray = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int i = gZIPInputStream.read(bArr, 0, 1024);
                if (i != -1) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArray;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return byteArray;
        }
    }

    public static byte[] zip(byte[] data) {
        byte[] byteArray = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
            ZipEntry zipEntry = new ZipEntry("zip");
            zipEntry.setSize(data.length);
            zipOutputStream.putNextEntry(zipEntry);
            zipOutputStream.write(data);
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Exception e) {
            e.printStackTrace();
            return byteArray;
        }
    }

    public static byte[] unZip(byte[] data) {
        byte[] byteArray = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            ZipInputStream zipInputStream = new ZipInputStream(byteArrayInputStream);
            while (zipInputStream.getNextEntry() != null) {
                byte[] bArr = new byte[1024];
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int i = zipInputStream.read(bArr, 0, 1024);
                    if (i != -1) {
                        byteArrayOutputStream.write(bArr, 0, i);
                    }
                }
                byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
            }
            zipInputStream.close();
            byteArrayInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArray;
    }

    public static void showNV21Data(ImageView imageView, int width, int height, byte[] data) {
        if (data == null) {
            return;
        }
        try {
            YuvImage yuvImage = new YuvImage(data, 17, width, height, null);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            yuvImage.compressToJpeg(new Rect(0, 0, width, height), 100, byteArrayOutputStream);
            Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size());
            Matrix matrix = new Matrix();
            matrix.preRotate(90.0f);
            imageView.setImageBitmap(Bitmap.createBitmap(bitmapDecodeByteArray, 0, 0, bitmapDecodeByteArray.getWidth(), bitmapDecodeByteArray.getHeight(), matrix, true));
            byteArrayOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
