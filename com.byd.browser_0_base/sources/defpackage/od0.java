package defpackage;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.DragAndDropPermissions;
import android.view.DragEvent;
import android.view.View;
import org.chromium.chrome.browser.dragdrop.ChromeDragAndDropBrowserDelegate;
import org.chromium.ui.dragdrop.DropDataAndroid;
import org.chromium.ui.dragdrop.DropDataProviderImpl;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: DragAndDropDelegateImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class od0 implements qd0 {
    public int e;
    public int f;
    public boolean g;
    public boolean h;
    public int i;
    public float j;
    public float k;
    public long l;
    public nd0 m;

    /* JADX INFO: compiled from: DragAndDropDelegateImpl.java */
    public static class a {
        public final int a;
        public final int b;
        public final int c;
        public final int d;
        public final boolean e;

        public a(boolean z, int i, int i2, int i3, int i4) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = z;
        }
    }

    public static String a(DropDataAndroid dropDataAndroid) {
        boolean zIsEmpty = TextUtils.isEmpty(dropDataAndroid.a);
        GURL gurl = dropDataAndroid.b;
        if (zIsEmpty) {
            return gurl.j();
        }
        return dropDataAndroid.a + "\n" + gurl.j();
    }

    @Override // android.view.View.OnDragListener
    public final boolean onDrag(View view, DragEvent dragEvent) {
        nd0 nd0Var;
        if (!this.g) {
            nd0 nd0Var2 = this.m;
            if (nd0Var2 != null && ((ChromeDragAndDropBrowserDelegate) nd0Var2).b && dragEvent.getAction() == 3 && (nd0Var = this.m) != null) {
                Activity activityA = xz.a(((ChromeDragAndDropBrowserDelegate) nd0Var).a);
                DragAndDropPermissions dragAndDropPermissionsRequestDragAndDropPermissions = activityA == null ? null : activityA.requestDragAndDropPermissions(dragEvent);
                if (dragAndDropPermissionsRequestDragAndDropPermissions != null) {
                    dragAndDropPermissionsRequestDragAndDropPermissions.release();
                }
            }
            return false;
        }
        int action = dragEvent.getAction();
        if (action == 1) {
            this.j = dragEvent.getX();
            this.k = dragEvent.getY();
        } else if (action == 3) {
            this.h = true;
            float f = this.j;
            float f2 = this.k;
            float x = dragEvent.getX() - f;
            float y = dragEvent.getY() - f2;
            oj2.U(Math.round((float) Math.sqrt((y * y) + (x * x))), 51, "Android.DragDrop.FromWebContent.DropInWebContent.DistanceDip");
            oj2.Y(SystemClock.elapsedRealtime() - this.l, "Android.DragDrop.FromWebContent.DropInWebContent.Duration");
        } else if (action == 4) {
            boolean result = dragEvent.getResult();
            if (!this.h) {
                oj2.Y(SystemClock.elapsedRealtime() - this.l, "Android.DragDrop.FromWebContent.Duration.".concat(result ? "Success" : "Canceled"));
                oj2.U(this.i, 4, "Android.DragDrop.FromWebContent.TargetType");
            }
            boolean z = !this.h && result;
            Bundle bundle = new Bundle();
            bundle.putBoolean("imageIsInUse", z);
            try {
                xz.a.getContentResolver().call(DropDataProviderImpl.n, "onDragEnd", "", bundle);
            } catch (IllegalArgumentException | NullPointerException unused) {
            }
            this.f = 0;
            this.e = 0;
            this.i = 0;
            this.g = false;
            this.h = false;
            this.l = -1L;
        }
        return false;
    }
}
