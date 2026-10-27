package defpackage;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.share.qrcode.scan_tab.CameraPreview;
import org.chromium.chrome.browser.share.qrcode.scan_tab.CameraPreviewOverlay;
import org.chromium.ui.widget.ButtonCompat;

/* JADX INFO: compiled from: QrCodeScanView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c82 {
    public final Context a;
    public final FrameLayout b;
    public final Camera.PreviewCallback c;
    public boolean d;
    public boolean e;
    public boolean f;
    public CameraPreview g;
    public final View h;
    public final View i;
    public final View j;
    public final a k = new a();

    /* JADX INFO: compiled from: QrCodeScanView.java */
    public class a implements Camera.ErrorCallback {
        public a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0013  */
        @Override // android.hardware.Camera.ErrorCallback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void onError(int r3, android.hardware.Camera r4) {
            /*
                r2 = this;
                r4 = 2
                if (r3 == r4) goto L13
                r4 = 100
                if (r3 == r4) goto L13
                switch(r3) {
                    case 1000: goto L10;
                    case 1001: goto Ld;
                    case 1002: goto L13;
                    default: goto La;
                }
            La:
                int r3 = org.chromium.chrome.R$string.qr_code_hardware_camera_error
                goto L15
            Ld:
                int r3 = org.chromium.chrome.R$string.qr_code_disabled_camera_error
                goto L15
            L10:
                int r3 = org.chromium.chrome.R$string.qr_code_no_camera_error
                goto L15
            L13:
                int r3 = org.chromium.chrome.R$string.qr_code_in_use_camera_error
            L15:
                c82 r4 = defpackage.c82.this
                org.chromium.chrome.browser.share.qrcode.scan_tab.CameraPreview r0 = r4.g
                if (r0 == 0) goto L21
                r0.d()
                r0 = 0
                r4.g = r0
            L21:
                android.content.Context r4 = r4.a
                android.content.res.Resources r4 = r4.getResources()
                java.lang.String r3 = r4.getString(r3)
                d73 r4 = defpackage.rh3.a
                a9 r0 = new a9
                r1 = 20
                r0.<init>(r2, r1, r3)
                org.chromium.base.task.PostTask.d(r4, r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: c82.a.onError(int, android.hardware.Camera):void");
        }
    }

    /* JADX INFO: compiled from: QrCodeScanView.java */
    public interface b {
    }

    public c82(Activity activity, y72 y72Var, pq2 pq2Var) {
        this.a = activity;
        this.c = y72Var;
        FrameLayout frameLayout = new FrameLayout(activity);
        this.b = frameLayout;
        View viewInflate = LayoutInflater.from(activity).inflate(R$layout.qrcode_open_settings_layout, (ViewGroup) null, false);
        ((ButtonCompat) viewInflate.findViewById(R$id.open_settings_button)).setOnClickListener(new d82(this, activity));
        this.j = viewInflate;
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        View viewInflate2 = LayoutInflater.from(activity).inflate(R$layout.qrcode_permission_layout, (ViewGroup) null, false);
        ((ButtonCompat) viewInflate2.findViewById(R$id.ask_for_permission)).setOnClickListener(new b82(pq2Var));
        this.h = viewInflate2;
        this.i = LayoutInflater.from(activity).inflate(R$layout.qrcode_camera_error_layout, (ViewGroup) null, false);
    }

    public final void a() {
        CameraPreview cameraPreview;
        boolean z = this.f;
        if (z) {
            boolean z2 = this.d;
            FrameLayout frameLayout = this.b;
            if (!z2 || this.g != null) {
                if (!z2 || (cameraPreview = this.g) == null) {
                    if (this.e) {
                        frameLayout.removeAllViews();
                        frameLayout.addView(this.h);
                        return;
                    } else {
                        frameLayout.removeAllViews();
                        frameLayout.addView(this.j);
                        return;
                    }
                }
                if (cameraPreview == null) {
                    return;
                }
                if (z && z2) {
                    cameraPreview.b();
                    return;
                } else {
                    cameraPreview.d();
                    return;
                }
            }
            frameLayout.removeAllViews();
            CameraPreview cameraPreview2 = this.g;
            if (cameraPreview2 != null) {
                cameraPreview2.d();
                this.g = null;
            }
            if (this.d) {
                Context context = this.a;
                CameraPreview cameraPreview3 = new CameraPreview(context, this.c, this.k);
                this.g = cameraPreview3;
                frameLayout.addView(cameraPreview3);
                frameLayout.addView(new CameraPreviewOverlay(context));
                CameraPreview cameraPreview4 = this.g;
                if (cameraPreview4 == null) {
                    return;
                }
                if (this.f && this.d) {
                    cameraPreview4.b();
                } else {
                    cameraPreview4.d();
                }
            }
        }
    }
}
