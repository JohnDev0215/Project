package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.directwriting.IDirectWritingService;
import java.util.ArrayList;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.ViewAndroidDelegate;

/* JADX INFO: compiled from: DirectWritingTrigger.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ka0 implements yv2, tv2, ViewAndroidDelegate.a {
    public boolean g;
    public Object i;
    public Object j;
    public boolean k;
    public zv2 l;
    public ga0 m;
    public MotionEvent n;
    public MotionEvent o;
    public Rect p;
    public boolean q;
    public boolean r;
    public boolean s;
    public final fa0 e = new fa0();
    public final ha0 f = new ha0();
    public final Handler h = new Handler();

    @Override // defpackage.yv2
    public final boolean a() {
        return false;
    }

    @Override // defpackage.yv2
    public final void b(View view, MotionEvent motionEvent) {
        if (this.k) {
            if (motionEvent.getToolType(0) == 2 || motionEvent.getToolType(0) == 4) {
                fa0 fa0Var = this.e;
                if (!fa0Var.a() && motionEvent.getAction() == 9) {
                    Context context = view.getContext();
                    ja0 ja0Var = new ja0(this);
                    if (!fa0Var.a() && !context.getPackageName().equals(fa0Var.b)) {
                        ArrayList arrayListB = zt1.b("com.samsung.android.honeyboard");
                        if (arrayListB == null || arrayListB.size() > 1 || !(((String) arrayListB.get(0)).equals("34:DF:0E:7A:9F:1C:F1:89:2E:45:C0:56:B4:97:3C:D8:1C:CF:14:8A:40:50:D1:1A:EA:4A:C5:A6:5F:90:0A:42") || ((String) arrayListB.get(0)).equals("C8:A2:E9:BC:CF:59:7C:2F:B6:DC:66:BE:E2:93:FC:13:F2:FC:47:EC:77:BC:6B:2B:0D:52:C1:1F:51:19:2A:B8"))) {
                            Log.e("cr_DWServiceBinder", "Don't connect to service due to package fingerprint mismatch");
                        } else {
                            try {
                                Intent intent = new Intent();
                                intent.setComponent(new ComponentName("com.samsung.android.honeyboard", "com.samsung.android.directwriting.service.DirectWritingService"));
                                context.bindService(intent, fa0Var.c, 1);
                                fa0Var.b = context.getPackageName();
                                fa0Var.d = ja0Var;
                                fa0Var.e = context;
                            } catch (RuntimeException e) {
                                Log.e("cr_DWServiceBinder", "bindService failed," + e);
                            }
                        }
                    }
                }
                p(view, motionEvent);
            }
        }
    }

    @Override // defpackage.yv2
    public final boolean d(View view, MotionEvent motionEvent) {
        if (!this.k) {
            return false;
        }
        boolean z = true;
        if (motionEvent.isButtonPressed(32)) {
            if (motionEvent.getAction() == 0) {
                this.s = true;
            }
        } else if (!this.s) {
            z = false;
        } else if (motionEvent.getAction() == 1) {
            this.s = false;
        }
        if (z) {
            return false;
        }
        if (motionEvent.getToolType(0) == 2 || motionEvent.getToolType(0) == 4) {
            return p(view, motionEvent);
        }
        if (motionEvent.getActionMasked() == 0) {
            q();
        }
        return false;
    }

    @Override // defpackage.yv2
    public final void e(View view, Rect rect, boolean z) {
        if (this.k) {
            fa0 fa0Var = this.e;
            if (fa0Var.a()) {
                this.p = rect;
                ga0 ga0Var = this.m;
                Point point = new Point();
                ga0Var.i = rect;
                ga0Var.j = point;
                if (!z) {
                    q();
                    s(null, null, view);
                } else {
                    if (this.q || !this.r || this.o == null) {
                        return;
                    }
                    fa0Var.e(view, rect);
                    s(rect, this.o, view);
                    this.r = false;
                }
            }
        }
    }

    @Override // defpackage.yv2
    public final void g(Context context) {
        if (this.k) {
            fa0 fa0Var = this.e;
            if (fa0Var.a()) {
                fa0Var.d(context);
            }
        }
    }

    @Override // defpackage.tv2
    public final void h(Context context, WebContents webContents) {
        t(context);
        webContents.Z(this);
    }

    @Override // defpackage.yv2
    public final boolean i(ImeAdapterImpl.a aVar) {
        if (!this.k || !this.e.a()) {
            return false;
        }
        this.l = aVar;
        this.m.k = aVar;
        tv2.c(1);
        this.q = true;
        return true;
    }

    @Override // defpackage.yv2
    public final void j(int i, int i2, String str) {
        if (this.k && this.e.a()) {
            ga0 ga0Var = this.m;
            ga0Var.h = str;
            ga0Var.f = i;
            ga0Var.g = i2;
        }
    }

    @Override // defpackage.tv2
    public final void k(Context context, boolean z) {
        if (z) {
            t(context);
        } else {
            q();
        }
        if (this.k) {
            fa0 fa0Var = this.e;
            if (z) {
                fa0Var.b();
                return;
            }
            fa0Var.getClass();
            if (context.getPackageName().equals(fa0Var.b)) {
                String packageName = context.getPackageName();
                if (fa0Var.a()) {
                    try {
                        fa0Var.a.onWindowFocusLost(packageName);
                    } catch (DeadObjectException e) {
                        Log.e("cr_DWServiceBinder", "onWindowFocusLost failed due to DeadObjectException.", e);
                        fa0Var.c();
                    } catch (Exception e2) {
                        Log.e("cr_DWServiceBinder", "onWindowFocusLost failed.", e2);
                    }
                }
            }
        }
    }

    @Override // defpackage.yv2
    public final void l(EditorInfo editorInfo) {
        if (this.k) {
            fa0 fa0Var = this.e;
            if (fa0Var.a()) {
                this.m.e = editorInfo;
                if (fa0Var.a()) {
                    try {
                        fa0Var.a.onUpdateImeOptions(editorInfo.imeOptions);
                    } catch (DeadObjectException e) {
                        Log.e("cr_DWServiceBinder", "updateEditorInfo failed due to DeadObjectException.", e);
                        fa0Var.c();
                    } catch (Exception e2) {
                        Log.e("cr_DWServiceBinder", "updateEditorInfo failed.", e2);
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    @Override // defpackage.yv2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m(android.graphics.Rect r6, android.graphics.Point r7) {
        /*
            r5 = this;
            boolean r0 = r6.isEmpty()
            if (r0 == 0) goto L7
            return
        L7:
            boolean r0 = r5.q
            if (r0 == 0) goto L7f
            zv2 r0 = r5.l
            if (r0 != 0) goto L11
            goto L7f
        L11:
            ga0 r1 = r5.m
            r1.i = r6
            r1.j = r7
            org.chromium.content.browser.input.ImeAdapterImpl$a r0 = (org.chromium.content.browser.input.ImeAdapterImpl.a) r0
            android.view.View r7 = r0.a()
            fa0 r0 = r5.e
            r0.e(r7, r6)
            android.view.MotionEvent r7 = r5.n
            if (r7 != 0) goto L27
            goto L7f
        L27:
            zv2 r7 = r5.l
            org.chromium.content.browser.input.ImeAdapterImpl$a r7 = (org.chromium.content.browser.input.ImeAdapterImpl.a) r7
            android.view.View r7 = r7.a()
            android.view.MotionEvent r1 = r5.n
            boolean r2 = r0.a()
            r3 = 1
            java.lang.String r4 = "cr_DWServiceBinder"
            if (r2 != 0) goto L40
            java.lang.String r6 = "startRecognition failed, not bounded"
            android.util.Log.e(r4, r6)
            goto L5b
        L40:
            android.widget.directwriting.IDirectWritingService r2 = r0.a     // Catch: java.lang.Exception -> L4b android.os.DeadObjectException -> L52
            android.os.Bundle r6 = defpackage.a8.l(r6, r1, r7)     // Catch: java.lang.Exception -> L4b android.os.DeadObjectException -> L52
            r2.onStartRecognition(r6)     // Catch: java.lang.Exception -> L4b android.os.DeadObjectException -> L52
            r6 = r3
            goto L5c
        L4b:
            r6 = move-exception
            java.lang.String r0 = "startRecognition failed with exception."
            android.util.Log.e(r4, r0, r6)
            goto L5b
        L52:
            r6 = move-exception
            java.lang.String r1 = "startRecognition failed due to DeadObjectException."
            android.util.Log.e(r4, r1, r6)
            r0.c()
        L5b:
            r6 = 0
        L5c:
            if (r6 != 0) goto L5f
            goto L7f
        L5f:
            r5.g = r3
            android.view.MotionEvent r6 = r5.n
            r5.r(r7, r6)
            zv2 r5 = r5.l
            org.chromium.content.browser.input.ImeAdapterImpl$a r5 = (org.chromium.content.browser.input.ImeAdapterImpl.a) r5
            org.chromium.content.browser.input.ImeAdapterImpl r5 = org.chromium.content.browser.input.ImeAdapterImpl.this
            org.chromium.content.browser.webcontents.WebContentsImpl r5 = r5.j
            org.chromium.content.browser.GestureListenerManagerImpl r5 = org.chromium.content.browser.GestureListenerManagerImpl.e(r5)
            if (r5 == 0) goto L7f
            long r6 = r5.m
            r0 = 0
            int r0 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r0 == 0) goto L7f
            J.N.MMR0DKoy(r6, r5)
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ka0.m(android.graphics.Rect, android.graphics.Point):void");
    }

    @Override // org.chromium.ui.base.ViewAndroidDelegate.a
    public final boolean n(ViewGroup viewGroup) {
        if (Build.VERSION.SDK_INT < 31) {
            return false;
        }
        viewGroup.setPointerIcon(PointerIcon.getSystemIcon(viewGroup.getContext(), 20024));
        return true;
    }

    @Override // defpackage.yv2
    public final void o(boolean z) {
        if (z) {
            return;
        }
        q();
        zv2 zv2Var = this.l;
        if (zv2Var == null) {
            return;
        }
        s(null, null, ((ImeAdapterImpl.a) zv2Var).a());
    }

    public final boolean p(View view, MotionEvent motionEvent) {
        MotionEvent motionEvent2;
        int action = motionEvent.getAction();
        Handler handler = this.h;
        if (action == 0) {
            Object obj = this.j;
            if (obj != null) {
                handler.removeCallbacksAndMessages(obj);
                this.j = null;
            }
            this.n = MotionEvent.obtain(motionEvent);
            this.r = false;
            Object obj2 = this.i;
            if (obj2 == null) {
                this.q = false;
                this.g = false;
                return false;
            }
            handler.removeCallbacksAndMessages(obj2);
            this.i = null;
            r(view, motionEvent);
            return true;
        }
        ha0 ha0Var = this.f;
        if (action == 1) {
            if (this.g) {
                r(view, motionEvent);
                Object obj3 = new Object();
                this.i = obj3;
                handler.postDelayed(new es3(23, this), obj3, ha0Var.b);
                return true;
            }
            Rect rect = this.p;
            if (rect == null || rect.isEmpty() || (motionEvent2 = this.n) == null || !this.p.contains((int) motionEvent2.getX(), (int) this.n.getY())) {
                this.o = MotionEvent.obtain(motionEvent);
                this.r = true;
            } else {
                s(this.p, motionEvent, view);
            }
            return false;
        }
        if (action == 2) {
            if (!this.g) {
                return false;
            }
            r(view, motionEvent);
            return true;
        }
        if (action == 9) {
            Object obj4 = this.j;
            if (obj4 != null) {
                handler.removeCallbacksAndMessages(obj4);
                this.j = null;
            }
        } else if (action == 10 && this.g) {
            Object obj5 = new Object();
            this.j = obj5;
            handler.postDelayed(new ul2(this, 4, view), obj5, ha0Var.a);
        }
        return false;
    }

    public final void q() {
        if (this.k) {
            fa0 fa0Var = this.e;
            if (fa0Var.a()) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString(IDirectWritingService.KEY_BUNDLE_SERVICE_HOST_SOURCE, IDirectWritingService.VALUE_BUNDLE_SERVICE_HOST_SOURCE_WEBVIEW);
                    fa0Var.a.onEditTextActionModeStarted(bundle);
                } catch (DeadObjectException e) {
                    Log.e("cr_DWServiceBinder", "hideDWToolbar failed due to DeadObjectException.", e);
                    fa0Var.c();
                } catch (Exception e2) {
                    Log.e("cr_DWServiceBinder", "hideDWToolbar failed.", e2);
                }
            }
        }
    }

    public final void r(View view, MotionEvent motionEvent) {
        fa0 fa0Var = this.e;
        if (fa0Var.a()) {
            try {
                IDirectWritingService iDirectWritingService = fa0Var.a;
                Bundle bundle = new Bundle();
                bundle.putParcelable(IDirectWritingService.KEY_BUNDLE_EVENT, motionEvent);
                bundle.putParcelable(IDirectWritingService.KEY_BUNDLE_ROOT_VIEW_RECT, a8.G(view));
                iDirectWritingService.onDispatchEvent(bundle);
            } catch (DeadObjectException e) {
                Log.e("cr_DWServiceBinder", "onDispatchEvent failed due to DeadObjectException.", e);
                fa0Var.c();
            } catch (Exception e2) {
                Log.e("cr_DWServiceBinder", "onDispatchEvent failed.", e2);
            }
        }
    }

    public final void s(Rect rect, MotionEvent motionEvent, View view) {
        if (this.k) {
            fa0 fa0Var = this.e;
            if (fa0Var.a()) {
                try {
                    fa0Var.a.onStopRecognition(a8.l(rect, motionEvent, view));
                } catch (DeadObjectException e) {
                    Log.e("cr_DWServiceBinder", "onStopRecognition failed due to DeadObjectException.", e);
                    fa0Var.c();
                } catch (Exception e2) {
                    Log.e("cr_DWServiceBinder", "onStopRecognition failed.", e2);
                }
            }
            this.g = false;
            this.n = null;
            this.o = null;
        }
    }

    public final void t(Context context) {
        boolean z = this.k;
        boolean zA = ia0.a(context);
        this.k = zA;
        Log.i("cr_DWTrigger", "updateDWServiceStatus() : isEnabled = " + zA);
        if (!z && this.k && this.m == null) {
            this.m = new ga0();
        }
    }

    @Override // defpackage.tv2
    public final ViewAndroidDelegate.a f() {
        return this;
    }
}
