package com.byd.automultipletheme.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.core.app.NotificationCompat;
import com.blankj.utilcode.util.FileUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\r\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\tR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/byd/automultipletheme/common/SaveCropBitmapCallable;", "Ljava/util/concurrent/Callable;", "", "path", "", "cropBitmap", "Landroid/graphics/Bitmap;", "(Ljava/lang/String;Landroid/graphics/Bitmap;)V", NotificationCompat.CATEGORY_CALL, "()Ljava/lang/Boolean;", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SaveCropBitmapCallable implements Callable<Boolean> {
    private Bitmap cropBitmap;
    private String path;

    public SaveCropBitmapCallable(String path, Bitmap cropBitmap) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(cropBitmap, "cropBitmap");
        this.path = path;
        this.cropBitmap = cropBitmap;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Boolean call() throws IOException {
        if (FileUtils.createOrExistsFile(this.path)) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
            this.cropBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream2);
            int i = 100;
            while (byteArrayOutputStream.toByteArray().length / 1024 > 1000) {
                byteArrayOutputStream.reset();
                i -= 40;
                if (i <= 0) {
                    i = 20;
                }
                this.cropBitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream2);
                LogUtils.INSTANCE.i("size:", Integer.valueOf(byteArrayOutputStream.toByteArray().length / 1024), Integer.valueOf(i));
            }
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, null);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(this.path));
            zCompress = bitmapDecodeStream != null ? bitmapDecodeStream.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream) : false;
            fileOutputStream.flush();
            fileOutputStream.close();
        }
        return Boolean.valueOf(zCompress);
    }
}
