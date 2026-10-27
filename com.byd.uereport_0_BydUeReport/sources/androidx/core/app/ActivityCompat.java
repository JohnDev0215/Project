package androidx.core.app;

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
import d0.i;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ActivityCompat extends e0.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static c f1205c;

    public class a implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String[] f1206d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Activity f1207e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ int f1208f;

        public a(String[] strArr, Activity activity, int i8) {
            this.f1206d = strArr;
            this.f1207e = activity;
            this.f1208f = i8;
        }

        @Override // java.lang.Runnable
        public void run() {
            int[] iArr = new int[this.f1206d.length];
            PackageManager packageManager = this.f1207e.getPackageManager();
            String packageName = this.f1207e.getPackageName();
            int length = this.f1206d.length;
            for (int i8 = 0; i8 < length; i8++) {
                iArr[i8] = packageManager.checkPermission(this.f1206d[i8], packageName);
            }
            ((b) this.f1207e).onRequestPermissionsResult(this.f1208f, this.f1206d, iArr);
        }
    }

    public interface b {
        void onRequestPermissionsResult(int i8, String[] strArr, int[] iArr);
    }

    public interface c {
        boolean a(Activity activity, int i8, int i9, Intent intent);

        boolean b(Activity activity, String[] strArr, int i8);
    }

    public interface d {
        void validateRequestPermissionsRequestCode(int i8);
    }

    public static class e extends SharedElementCallback {

        public class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ SharedElementCallback.OnSharedElementsReadyListener f1209a;

            public a(SharedElementCallback.OnSharedElementsReadyListener onSharedElementsReadyListener) {
                this.f1209a = onSharedElementsReadyListener;
            }
        }

        public e(i iVar) {
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
        return f1205c;
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
            if (d0.a.i(activity)) {
                return;
            }
            activity.recreate();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void m(Activity activity, String[] strArr, int i8) {
        c cVar = f1205c;
        if (cVar == null || !cVar.b(activity, strArr, i8)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (activity instanceof d) {
                    ((d) activity).validateRequestPermissionsRequestCode(i8);
                }
                activity.requestPermissions(strArr, i8);
            } else if (activity instanceof b) {
                new Handler(Looper.getMainLooper()).post(new a(strArr, activity, i8));
            }
        }
    }

    public static void n(Activity activity, i iVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.setEnterSharedElementCallback(iVar != null ? new e(iVar) : null);
        }
    }

    public static void o(Activity activity, i iVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.setExitSharedElementCallback(iVar != null ? new e(iVar) : null);
        }
    }

    public static boolean p(Activity activity, String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            return activity.shouldShowRequestPermissionRationale(str);
        }
        return false;
    }

    public static void q(Activity activity, Intent intent, int i8, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            activity.startActivityForResult(intent, i8, bundle);
        } else {
            activity.startActivityForResult(intent, i8);
        }
    }

    public static void r(Activity activity, IntentSender intentSender, int i8, Intent intent, int i9, int i10, int i11, Bundle bundle) throws IntentSender.SendIntentException {
        if (Build.VERSION.SDK_INT >= 16) {
            activity.startIntentSenderForResult(intentSender, i8, intent, i9, i10, i11, bundle);
        } else {
            activity.startIntentSenderForResult(intentSender, i8, intent, i9, i10, i11);
        }
    }

    public static void s(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.startPostponedEnterTransition();
        }
    }
}
