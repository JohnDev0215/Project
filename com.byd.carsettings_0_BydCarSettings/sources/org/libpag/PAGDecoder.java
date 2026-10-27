package org.libpag;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes3.dex */
public class PAGDecoder {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f4015a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f4016b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4017c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private PAGSurface f4018d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private PAGPlayer f4019e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Bitmap f4020f;

    static {
        org.extra.tools.a.a("pag");
    }

    public static PAGDecoder Make(PAGComposition pAGComposition) {
        return Make(pAGComposition, pAGComposition.frameRate(), 1.0f);
    }

    public boolean copyFrameTo(Bitmap bitmap, int i) {
        int i2;
        if (bitmap == null || bitmap.isRecycled() || i < 0 || i >= (i2 = this.f4017c)) {
            return false;
        }
        this.f4019e.setProgress(l.b(i, i2));
        this.f4019e.flush();
        return this.f4018d.copyPixelsTo(bitmap);
    }

    public Bitmap frameAtIndex(int i) {
        int i2;
        Bitmap bitmap;
        if (i < 0 || i >= (i2 = this.f4017c)) {
            return null;
        }
        this.f4019e.setProgress(l.b(i, i2));
        if (!this.f4019e.flush() && (bitmap = this.f4020f) != null && !bitmap.isRecycled()) {
            return this.f4020f;
        }
        Bitmap bitmapMakeSnapshot = this.f4018d.makeSnapshot();
        this.f4020f = bitmapMakeSnapshot;
        return bitmapMakeSnapshot;
    }

    public int height() {
        return this.f4016b;
    }

    public int numFrames() {
        return this.f4017c;
    }

    public void release() {
        this.f4018d.release();
        this.f4019e.setSurface(null);
        this.f4019e.setComposition(null);
        this.f4019e.release();
    }

    public int width() {
        return this.f4015a;
    }

    public static PAGDecoder Make(PAGComposition pAGComposition, float f2, float f3) {
        if (pAGComposition == null) {
            return null;
        }
        if (f3 <= 0.0f) {
            f3 = 1.0f;
        }
        float fFrameRate = f2 <= 0.0f ? pAGComposition.frameRate() : Math.min(pAGComposition.frameRate(), f2);
        PAGDecoder pAGDecoder = new PAGDecoder();
        pAGDecoder.f4015a = Math.round(pAGComposition.width() * f3);
        pAGDecoder.f4016b = Math.round(pAGComposition.height() * f3);
        pAGDecoder.f4017c = (int) ((pAGComposition.duration() * fFrameRate) / 1000000.0f);
        PAGSurface pAGSurfaceMakeOffscreen = PAGSurface.MakeOffscreen(pAGDecoder.f4015a, pAGDecoder.f4016b);
        pAGDecoder.f4018d = pAGSurfaceMakeOffscreen;
        if (pAGSurfaceMakeOffscreen == null) {
            return null;
        }
        PAGPlayer pAGPlayer = new PAGPlayer();
        pAGDecoder.f4019e = pAGPlayer;
        if (fFrameRate > 0.0f) {
            pAGPlayer.setMaxFrameRate(fFrameRate);
        }
        pAGDecoder.f4019e.setSurface(pAGDecoder.f4018d);
        pAGDecoder.f4019e.setComposition(pAGComposition);
        return pAGDecoder;
    }
}
