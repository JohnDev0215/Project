package F0;

import android.content.ClipData;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.ContentInfo;
import android.view.ScrollCaptureCallback;
import android.view.ScrollCaptureSession;
import android.view.ScrollCaptureTarget;
import android.view.View;
import android.view.autofill.AutofillId;
import android.view.translation.ViewTranslationRequest;

/* JADX INFO: renamed from: F0.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class AbstractC0207i {
    public static /* synthetic */ ContentInfo.Builder g(ClipData clipData, int i7) {
        return new ContentInfo.Builder(clipData, i7);
    }

    public static /* bridge */ /* synthetic */ ContentInfo i(Object obj) {
        return (ContentInfo) obj;
    }

    public static /* bridge */ /* synthetic */ ScrollCaptureSession j(Object obj) {
        return (ScrollCaptureSession) obj;
    }

    public static /* synthetic */ ScrollCaptureTarget k(View view, Rect rect, Point point, ScrollCaptureCallback scrollCaptureCallback) {
        return new ScrollCaptureTarget(view, rect, point, scrollCaptureCallback);
    }

    public static /* synthetic */ ViewTranslationRequest.Builder n(AutofillId autofillId, long j3) {
        return new ViewTranslationRequest.Builder(autofillId, j3);
    }

    public static /* synthetic */ void o() {
    }
}
