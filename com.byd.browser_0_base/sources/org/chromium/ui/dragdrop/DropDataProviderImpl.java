package org.chromium.ui.dragdrop;

import android.content.ContentProvider;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.os.SystemClock;
import defpackage.oj2;
import defpackage.xz;
import java.io.FileOutputStream;
import org.chromium.build.annotations.UsedByReflection;

/* JADX INFO: loaded from: classes3.dex */
@UsedByReflection
public class DropDataProviderImpl {
    public static final Uri n = Uri.parse("content://" + xz.a.getPackageName() + ".DropDataProvider");
    public static final String[] o = {"_display_name", "_size"};
    public static final Object p = new Object();
    public byte[] b;
    public String c;
    public String d;
    public Uri e;
    public Handler f;
    public long g;
    public long h;
    public Uri i;
    public long j;
    public long k;
    public boolean l;
    public int a = 60000;
    public a m = new a();

    public static class a implements ContentProvider.PipeDataWriter<byte[]> {
        @Override // android.content.ContentProvider.PipeDataWriter
        public final void writeDataToPipe(ParcelFileDescriptor parcelFileDescriptor, Uri uri, String str, Bundle bundle, byte[] bArr) {
            byte[] bArr2 = bArr;
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(parcelFileDescriptor.getFileDescriptor());
                if (bArr2 != null) {
                    try {
                        fileOutputStream.write(bArr2);
                    } catch (Throwable th) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable unused) {
                        }
                        throw th;
                    }
                }
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception unused2) {
            }
        }
    }

    @UsedByReflection
    public static DropDataProviderImpl onCreate() {
        return new DropDataProviderImpl();
    }

    public final void a() {
        synchronized (p) {
            b();
            long j = this.g;
            if (j > 0) {
                long j2 = this.h;
                if (j2 > 0) {
                    oj2.Y(Math.max(0L, j2 - j), "Android.DragDrop.Image.OpenFileTime.LastAttempt");
                }
            }
        }
    }

    public final void b() {
        this.b = null;
        this.c = null;
        this.d = null;
        Uri uri = this.e;
        if (uri != null) {
            this.i = uri;
            this.j = SystemClock.elapsedRealtime();
            this.l = false;
        }
        this.e = null;
        Handler handler = this.f;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.f = null;
        }
    }
}
