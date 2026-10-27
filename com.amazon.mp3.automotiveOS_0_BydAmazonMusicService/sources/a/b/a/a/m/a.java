package a.b.a.a.m;

import a.b.a.a.k.c;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.netinsight.sye.syeClient.timeshift.ISyeThumbnail;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class a implements ISyeThumbnail {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0016a f84a = new C0016a();
    public final String b;
    public final a.b.a.a.i.b c;
    public final a.b.a.a.k.e.b d;
    public final String e;
    public final long f;
    public final int g;
    public final int h;
    public final byte[] i;
    public Bitmap j;

    /* JADX INFO: renamed from: a.b.a.a.m.a$a, reason: collision with other inner class name */
    public static final class C0016a {
    }

    public a(c cVar) {
        String name = a.class.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "SyeThumbnail::class.java.name");
        this.b = name;
        this.c = a.b.a.a.i.b.d.a();
        this.d = cVar.a();
        this.e = cVar.d();
        this.f = cVar.e();
        this.g = cVar.f();
        this.h = cVar.c();
        this.i = cVar.b();
    }

    @Override // com.netinsight.sye.syeClient.timeshift.ISyeThumbnail
    public Bitmap getBitmap() {
        Bitmap bitmap;
        synchronized (this) {
            if (this.j == null) {
                byte[] bArr = this.i;
                Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
                this.j = bitmapDecodeByteArray;
                if (bitmapDecodeByteArray == null) {
                    this.c.e(this.b, "The image could not be decoded by BitmapFactory.");
                }
            }
            bitmap = this.j;
        }
        return bitmap;
    }

    @Override // com.netinsight.sye.syeClient.timeshift.ISyeThumbnail
    public a.b.a.a.k.e.b getCodec() {
        return this.d;
    }

    @Override // com.netinsight.sye.syeClient.timeshift.ISyeThumbnail
    public byte[] getData() {
        return this.i;
    }

    @Override // com.netinsight.sye.syeClient.timeshift.ISyeThumbnail
    public int getHeight() {
        return this.h;
    }

    @Override // com.netinsight.sye.syeClient.timeshift.ISyeThumbnail
    public String getMimeType() {
        return this.e;
    }

    @Override // com.netinsight.sye.syeClient.timeshift.ISyeThumbnail
    public long getServerUtcTimeMillis() {
        return this.f;
    }

    @Override // com.netinsight.sye.syeClient.timeshift.ISyeThumbnail
    public int getWidth() {
        return this.g;
    }
}
