package androidx.media3.exoplayer.image;

import android.graphics.Bitmap;
import r2.C2283e;

/* JADX INFO: loaded from: classes.dex */
public interface ImageOutput {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2283e f12018a = new C2283e();

    void a();

    void onImageAvailable(long j3, Bitmap bitmap);
}
