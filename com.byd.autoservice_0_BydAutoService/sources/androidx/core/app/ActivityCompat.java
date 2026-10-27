package androidx.core.app;

import a0.h;
import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.View;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ActivityCompat extends b0.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static c f1116c;

    public class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String[] f1117b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Activity f1118c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f1119d;

        public a(String[] strArr, Activity activity, int i3) {
            this.f1117b = strArr;
            this.f1118c = activity;
            this.f1119d = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            int[] iArr = new int[this.f1117b.length];
            PackageManager packageManager = this.f1118c.getPackageManager();
            String packageName = this.f1118c.getPackageName();
            int length = this.f1117b.length;
            for (int i3 = 0; i3 < length; i3++) {
                iArr[i3] = packageManager.checkPermission(this.f1117b[i3], packageName);
            }
            ((b) this.f1118c).onRequestPermissionsResult(this.f1119d, this.f1117b, iArr);
        }
    }

    public interface b {
        void onRequestPermissionsResult(int i3, String[] strArr, int[] iArr);
    }

    public interface c {
        boolean a(Activity activity, String[] strArr, int i3);

        boolean b(Activity activity, int i3, int i4, Intent intent);
    }

    public interface d {
        void validateRequestPermissionsRequestCode(int i3);
    }

    public static class e extends SharedElementCallback {

        public class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ SharedElementCallback.OnSharedElementsReadyListener f1120a;

            public a(SharedElementCallback.OnSharedElementsReadyListener onSharedElementsReadyListener) {
                this.f1120a = onSharedElementsReadyListener;
            }
        }

        public e(h hVar) {
        }

        @Override // android.app.SharedElementCallback
        public Parcelable onCaptureSharedElementSnapshot(View view, Matrix matrix, RectF rectF) {
            throw null;
        }

        @Override // android.app.SharedElementCallback
        public View onCreateSnapshotView(Context context, Parcelable parcelable) {
            throw null;
        }

        @Override // android.app.SharedElementCallback
        public void onMapSharedElements(List<String> list, Map<String, View> map) {
            throw null;
        }

        @Override // android.app.SharedElementCallback
        public void onRejectSharedElements(List<View> list) {
            throw null;
        }

        @Override // android.app.SharedElementCallback
        public void onSharedElementEnd(List<String> list, List<View> list2, List<View> list3) {
            throw null;
        }

        @Override // android.app.SharedElementCallback
        public void onSharedElementStart(List<String> list, List<View> list2, List<View> list3) {
            throw null;
        }

        @Override // android.app.SharedElementCallback
        public void onSharedElementsArrived(List<String> list, List<View> list2, SharedElementCallback.OnSharedElementsReadyListener onSharedElementsReadyListener) {
            new a(onSharedElementsReadyListener);
            throw null;
        }
    }

    public static void h(Activity activity) {
        if (Build.VERSION.SDK_INT >= 16) {
            activity.finishAffinity();
        } else {
            activity.finish();
        }
    }

    public static void i(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.finishAfterTransition();
        } else {
            activity.finish();
        }
    }

    public static c j() {
        return f1116c;
    }

    public static void k(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.postponeEnterTransition();
        }
    }

    public static void l(Activity activity) {
        if (Build.VERSION.SDK_INT >= 28) {
            activity.recreate();
        } else {
            if (a0.a.i(activity)) {
                return;
            }
            activity.recreate();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void m(Activity activity, String[] strArr, int i3) {
        c cVar = f1116c;
        if (cVar == null || !cVar.a(activity, strArr, i3)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (activity instanceof d) {
                    ((d) activity).validateRequestPermissionsRequestCode(i3);
                }
                activity.requestPermissions(strArr, i3);
            } else if (activity instanceof b) {
                new Handler(Looper.getMainLooper()).post(new a(strArr, activity, i3));
            }
        }
    }

    public static void n(Activity activity, h hVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.setEnterSharedElementCallback(hVar != null ? new e(hVar) : null);
        }
    }

    public static void o(Activity activity, h hVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.setExitSharedElementCallback(hVar != null ? new e(hVar) : null);
        }
    }

    public static boolean p(Activity activity, String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            return activity.shouldShowRequestPermissionRationale(str);
        }
        return false;
    }

    public static void q(Activity activity, Intent intent, int i3, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            activity.startActivityForResult(intent, i3, bundle);
        } else {
            activity.startActivityForResult(intent, i3);
        }
    }

    public static void r(Activity activity, IntentSender intentSender, int i3, Intent intent, int i4, int i5, int i6, Bundle bundle) throws IntentSender.SendIntentException {
        if (Build.VERSION.SDK_INT >= 16) {
            activity.startIntentSenderForResult(intentSender, i3, intent, i4, i5, i6, bundle);
        } else {
            activity.startIntentSenderForResult(intentSender, i3, intent, i4, i5, i6);
        }
    }

    public static void s(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.startPostponedEnterTransition();
        }
    }
}
