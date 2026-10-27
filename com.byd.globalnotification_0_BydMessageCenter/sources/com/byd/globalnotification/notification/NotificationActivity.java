package com.byd.globalnotification.notification;

import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import b.c.a.f.r;
import b.c.a.f.t;
import b.c.a.f.w;
import b.c.a.h.a;
import b.c.a.h.b;
import com.byd.globalnotification.R;
import com.byd.globalnotification.notification.NotifyMsgBean;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class NotificationActivity extends b.c.a.d.a<b.c.a.e.a, w> {
    public boolean t;
    public GestureDetector u;
    public a v;

    public class a extends GestureDetector.SimpleOnGestureListener {
        public a(r rVar) {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            StringBuilder sbD = b.a.a.a.a.d("e2.y = ");
            sbD.append(motionEvent2.getY());
            sbD.append(", e1.y = ");
            sbD.append(motionEvent.getY());
            sbD.append(", e2.x = ");
            sbD.append(motionEvent2.getX());
            sbD.append(", e1.x = ");
            sbD.append(motionEvent.getX());
            b.a(sbD.toString());
            float y = motionEvent.getY() - motionEvent2.getY();
            if (y <= motionEvent.getX() - motionEvent2.getX() || y <= 80.0f) {
                return true;
            }
            b.b("onFlingfinish by gesture");
            NotificationActivity.this.finish();
            return true;
        }
    }

    public /* synthetic */ void A(Object obj) {
        ((b.c.a.e.a) this.p).u.postDelayed(new Runnable() { // from class: b.c.a.f.c
            @Override // java.lang.Runnable
            public final void run() {
                this.f1732a.z();
            }
        }, 500L);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // a.b.k.i, a.l.d.e, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.uiMode != ((w) this.q).k) {
            finish();
        }
    }

    @Override // b.c.a.d.a, a.b.k.i, a.l.d.e, androidx.activity.ComponentActivity, a.h.c.b, android.app.Activity
    public void onCreate(Bundle bundle) {
        Bitmap bitmapCopy;
        int[] iArr;
        int i;
        int[] iArr2;
        overridePendingTransition(R.anim.in_from_top, 0);
        super.onCreate(bundle);
        ((w) this.q).k = ((UiModeManager) b.c.a.h.d.b.a().getSystemService("uimode")).getNightMode();
        Bitmap bitmapCreateScaledBitmap = null;
        a aVar = new a(null);
        this.v = aVar;
        this.u = new GestureDetector(this, aVar);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        try {
            Class<?> cls = Class.forName("android.view.SurfaceControl");
            Method declaredMethod = cls.getDeclaredMethod("screenshot", Rect.class, Integer.TYPE, Integer.TYPE, Integer.TYPE);
            declaredMethod.setAccessible(true);
            int i2 = getResources().getConfiguration().orientation;
            Object[] objArr = new Object[4];
            objArr[0] = new Rect();
            objArr[1] = Integer.valueOf(displayMetrics.widthPixels);
            objArr[2] = Integer.valueOf(displayMetrics.heightPixels);
            objArr[3] = Integer.valueOf(i2 == 2 ? 0 : 3);
            bitmapCopy = ((Bitmap) declaredMethod.invoke(cls, objArr)).copy(Bitmap.Config.ARGB_8888, true);
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            bitmapCopy = null;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            bitmapCopy = null;
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
            bitmapCopy = null;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            bitmapCopy = null;
        }
        if (b.c.a.h.a.f1769f == null) {
            synchronized (b.c.a.h.a.class) {
                if (b.c.a.h.a.f1769f == null) {
                    b.c.a.h.a.f1769f = new b.c.a.h.a(this);
                }
            }
        }
        b.c.a.h.a aVar2 = b.c.a.h.a.f1769f;
        a.EnumC0033a enumC0033a = a.EnumC0033a.RS_BLUR;
        aVar2.f1774e = enumC0033a;
        aVar2.f1772c = 1.0f / 25;
        aVar2.f1770a = bitmapCopy;
        aVar2.f1771b = 1;
        if (bitmapCopy == null) {
            throw new RuntimeException("Bitmap can not be null");
        }
        if (1 == 0) {
            throw new RuntimeException("radius must > 0");
        }
        if (enumC0033a == a.EnumC0033a.FAST_BLUR) {
            b.a("blur fast algorithm");
            Bitmap bitmap = aVar2.f1770a;
            float f2 = aVar2.f1772c;
            int i3 = aVar2.f1771b;
            Bitmap bitmapCreateScaledBitmap2 = Bitmap.createScaledBitmap(bitmap, Math.round(bitmap.getWidth() * f2), Math.round(bitmap.getHeight() * f2), false);
            Bitmap bitmapCopy2 = bitmapCreateScaledBitmap2.copy(bitmapCreateScaledBitmap2.getConfig(), true);
            if (i3 >= 1) {
                int width = bitmapCopy2.getWidth();
                int height = bitmapCopy2.getHeight();
                int i4 = width * height;
                int[] iArr3 = new int[i4];
                Log.e("pix", width + " " + height + " " + i4);
                bitmapCopy2.getPixels(iArr3, 0, width, 0, 0, width, height);
                int i5 = width + (-1);
                int i6 = height + (-1);
                int i7 = i3 + i3 + 1;
                int[] iArr4 = new int[i4];
                int[] iArr5 = new int[i4];
                int[] iArr6 = new int[i4];
                int[] iArr7 = new int[Math.max(width, height)];
                int i8 = (i7 + 1) >> 1;
                int i9 = i8 * i8;
                int i10 = i9 * 256;
                int[] iArr8 = new int[i10];
                for (int i11 = 0; i11 < i10; i11++) {
                    iArr8[i11] = i11 / i9;
                }
                int[][] iArr9 = (int[][]) Array.newInstance((Class<?>) int.class, i7, 3);
                int i12 = i3 + 1;
                int i13 = 0;
                int i14 = 0;
                int i15 = 0;
                while (i13 < height) {
                    int i16 = i4;
                    int i17 = -i3;
                    int i18 = 0;
                    int i19 = 0;
                    int i20 = 0;
                    int i21 = 0;
                    int i22 = 0;
                    int i23 = 0;
                    int i24 = 0;
                    int i25 = 0;
                    int i26 = 0;
                    while (true) {
                        i = height;
                        if (i17 > i3) {
                            break;
                        }
                        int i27 = i6;
                        int i28 = iArr3[Math.min(i5, Math.max(i17, 0)) + i14];
                        int[] iArr10 = iArr9[i17 + i3];
                        iArr10[0] = (i28 & 16711680) >> 16;
                        iArr10[1] = (i28 & 65280) >> 8;
                        iArr10[2] = i28 & 255;
                        int iAbs = i12 - Math.abs(i17);
                        i18 = (iArr10[0] * iAbs) + i18;
                        i19 = (iArr10[1] * iAbs) + i19;
                        i20 = (iArr10[2] * iAbs) + i20;
                        if (i17 > 0) {
                            i24 += iArr10[0];
                            i25 += iArr10[1];
                            i26 += iArr10[2];
                        } else {
                            i21 += iArr10[0];
                            i22 += iArr10[1];
                            i23 += iArr10[2];
                        }
                        i17++;
                        height = i;
                        i6 = i27;
                    }
                    int i29 = i6;
                    int i30 = 0;
                    int i31 = i3;
                    while (i30 < width) {
                        iArr4[i14] = iArr8[i18];
                        iArr5[i14] = iArr8[i19];
                        iArr6[i14] = iArr8[i20];
                        int i32 = i18 - i21;
                        int i33 = i19 - i22;
                        int i34 = i20 - i23;
                        int[] iArr11 = iArr9[((i31 - i3) + i7) % i7];
                        int i35 = i21 - iArr11[0];
                        int i36 = i22 - iArr11[1];
                        int i37 = i23 - iArr11[2];
                        if (i13 == 0) {
                            iArr2 = iArr8;
                            iArr7[i30] = Math.min(i30 + i3 + 1, i5);
                        } else {
                            iArr2 = iArr8;
                        }
                        int i38 = iArr3[i15 + iArr7[i30]];
                        iArr11[0] = (i38 & 16711680) >> 16;
                        iArr11[1] = (i38 & 65280) >> 8;
                        iArr11[2] = i38 & 255;
                        int i39 = i24 + iArr11[0];
                        int i40 = i25 + iArr11[1];
                        int i41 = i26 + iArr11[2];
                        i18 = i32 + i39;
                        i19 = i33 + i40;
                        i20 = i34 + i41;
                        i31 = (i31 + 1) % i7;
                        int[] iArr12 = iArr9[i31 % i7];
                        i21 = i35 + iArr12[0];
                        i22 = i36 + iArr12[1];
                        i23 = i37 + iArr12[2];
                        i24 = i39 - iArr12[0];
                        i25 = i40 - iArr12[1];
                        i26 = i41 - iArr12[2];
                        i14++;
                        i30++;
                        iArr8 = iArr2;
                    }
                    i15 += width;
                    i13++;
                    i4 = i16;
                    height = i;
                    i6 = i29;
                }
                int[] iArr13 = iArr8;
                int i42 = height;
                int i43 = i4;
                int i44 = i6;
                int i45 = 0;
                while (i45 < width) {
                    int i46 = -i3;
                    int i47 = i46 * width;
                    int i48 = 0;
                    int i49 = 0;
                    int i50 = 0;
                    int i51 = 0;
                    int i52 = 0;
                    int i53 = 0;
                    int i54 = 0;
                    int i55 = 0;
                    int i56 = 0;
                    while (true) {
                        iArr = iArr7;
                        if (i46 > i3) {
                            break;
                        }
                        int iMax = Math.max(0, i47) + i45;
                        int[] iArr14 = iArr9[i46 + i3];
                        iArr14[0] = iArr4[iMax];
                        iArr14[1] = iArr5[iMax];
                        iArr14[2] = iArr6[iMax];
                        int iAbs2 = i12 - Math.abs(i46);
                        i48 = (iArr4[iMax] * iAbs2) + i48;
                        i49 = (iArr5[iMax] * iAbs2) + i49;
                        i50 = (iArr6[iMax] * iAbs2) + i50;
                        if (i46 > 0) {
                            i54 += iArr14[0];
                            i55 += iArr14[1];
                            i56 += iArr14[2];
                        } else {
                            i51 += iArr14[0];
                            i52 += iArr14[1];
                            i53 += iArr14[2];
                        }
                        int i57 = i44;
                        if (i46 < i57) {
                            i47 += width;
                        }
                        i46++;
                        i44 = i57;
                        iArr7 = iArr;
                    }
                    int i58 = i44;
                    int i59 = 0;
                    int i60 = i45;
                    int i61 = i3;
                    int i62 = i42;
                    while (i59 < i62) {
                        iArr3[i60] = (iArr3[i60] & (-16777216)) | (iArr13[i48] << 16) | (iArr13[i49] << 8) | iArr13[i50];
                        int i63 = i48 - i51;
                        int i64 = i49 - i52;
                        int i65 = i50 - i53;
                        int[] iArr15 = iArr9[((i61 - i3) + i7) % i7];
                        int i66 = i51 - iArr15[0];
                        int i67 = i52 - iArr15[1];
                        int i68 = i53 - iArr15[2];
                        int i69 = i3;
                        if (i45 == 0) {
                            iArr[i59] = Math.min(i59 + i12, i58) * width;
                        }
                        int i70 = iArr[i59] + i45;
                        iArr15[0] = iArr4[i70];
                        iArr15[1] = iArr5[i70];
                        iArr15[2] = iArr6[i70];
                        int i71 = i54 + iArr15[0];
                        int i72 = i55 + iArr15[1];
                        int i73 = i56 + iArr15[2];
                        i48 = i63 + i71;
                        i49 = i64 + i72;
                        i50 = i65 + i73;
                        i61 = (i61 + 1) % i7;
                        int[] iArr16 = iArr9[i61];
                        i51 = i66 + iArr16[0];
                        i52 = i67 + iArr16[1];
                        i53 = i68 + iArr16[2];
                        i54 = i71 - iArr16[0];
                        i55 = i72 - iArr16[1];
                        i56 = i73 - iArr16[2];
                        i60 += width;
                        i59++;
                        i3 = i69;
                    }
                    i45++;
                    i42 = i62;
                    i44 = i58;
                    iArr7 = iArr;
                }
                int i74 = i42;
                Log.e("pix", width + " " + i74 + " " + i43);
                bitmapCopy2.setPixels(iArr3, 0, width, 0, 0, width, i74);
                bitmapCreateScaledBitmap = bitmapCopy2;
            }
        } else {
            if (!TextUtils.isEmpty("blur render script  algorithm")) {
                Log.i("[BYDGlobalNotification]", "blur render script  algorithm");
            }
            Context context = aVar2.f1773d;
            Bitmap bitmap2 = aVar2.f1770a;
            int i75 = aVar2.f1771b;
            float f3 = aVar2.f1772c;
            StringBuilder sbD = b.a.a.a.a.d("origin size:");
            sbD.append(bitmap2.getWidth());
            sbD.append("*");
            sbD.append(bitmap2.getHeight());
            Log.i("EasyBlur", sbD.toString());
            bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap2, Math.round(bitmap2.getWidth() * f3), Math.round(bitmap2.getHeight() * f3), false);
            RenderScript renderScriptCreate = RenderScript.create(context);
            StringBuilder sbD2 = b.a.a.a.a.d("scale size:");
            sbD2.append(bitmapCreateScaledBitmap.getWidth());
            sbD2.append("*");
            sbD2.append(bitmapCreateScaledBitmap.getHeight());
            Log.i("EasyBlur", sbD2.toString());
            Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(renderScriptCreate, bitmapCreateScaledBitmap);
            Allocation allocationCreateTyped = Allocation.createTyped(renderScriptCreate, allocationCreateFromBitmap.getType());
            ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScriptCreate, Element.U8_4(renderScriptCreate));
            scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
            scriptIntrinsicBlurCreate.setRadius(i75);
            scriptIntrinsicBlurCreate.forEach(allocationCreateTyped);
            allocationCreateTyped.copyTo(bitmapCreateScaledBitmap);
            renderScriptCreate.destroy();
        }
        getWindow().setBackgroundDrawable(new LayerDrawable(new Drawable[]{new BitmapDrawable(getResources(), bitmapCreateScaledBitmap), getResources().getDrawable(R.drawable.fg_msg_page)}));
    }

    @Override // b.c.a.d.a, a.b.k.i, a.l.d.e, android.app.Activity
    public void onDestroy() {
        overridePendingTransition(R.anim.in_from_top, 0);
        super.onDestroy();
        ((w) this.q).f();
        t tVar = t.b.f1752a;
        tVar.f1750b.forEach(new Consumer() { // from class: b.c.a.f.g
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((NotifyMsgBean) obj).isExpand.j(false);
            }
        });
        if (tVar.f1750b.size() == 1) {
            tVar.f1750b.stream().findFirst().get().setMultiple(false);
        }
    }

    @Override // b.c.a.d.a, a.l.d.e, android.app.Activity
    public void onPause() {
        overridePendingTransition(R.anim.in_from_top, 0);
        super.onPause();
    }

    @Override // b.c.a.d.a, a.l.d.e, android.app.Activity
    public void onResume() {
        overridePendingTransition(R.anim.in_from_top, 0);
        super.onResume();
        this.t = true;
        ((w) this.q).p();
    }

    @Override // a.b.k.i, a.l.d.e, android.app.Activity
    public void onStop() {
        super.onStop();
        this.t = false;
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        StringBuilder sbD = b.a.a.a.a.d("onTouchEvent");
        sbD.append(motionEvent.getAction());
        b.a(sbD.toString());
        return this.u.onTouchEvent(motionEvent);
    }

    public void v(int i) {
        NotifyMsgBean notifyMsgBean = ((w) this.q).f1759d.get(i);
        if (!"com.byd.uereport".equals(notifyMsgBean.getPkgName())) {
            ((w) this.q).s(notifyMsgBean);
            return;
        }
        RecyclerView.e adapter = ((b.c.a.e.a) this.p).w.getAdapter();
        if (adapter != null) {
            adapter.f1636a.b();
        }
    }

    public /* synthetic */ void w(Object obj) {
        ((b.c.a.e.a) this.p).w.n0(0);
    }

    public void x(Integer num) {
        RecyclerView.e adapter;
        if (num.intValue() <= -1 || (adapter = ((b.c.a.e.a) this.p).w.getAdapter()) == null || adapter.a() <= num.intValue()) {
            return;
        }
        adapter.f1636a.d(num.intValue(), 1, null);
    }

    public void y(Object obj) {
        if (this.t) {
            return;
        }
        ((w) this.q).f();
        ((w) this.q).f1760e.h(null);
    }

    public /* synthetic */ void z() {
        StringBuilder sbD = b.a.a.a.a.d("clearAllMsgEvent, notifyMsgs.size = ");
        sbD.append(t.a().f1750b.size());
        b.b(sbD.toString());
        ((b.c.a.e.a) this.p).w.getAdapter().d();
        if (this.p == 0 || t.a().f1750b.size() != 0) {
            return;
        }
        finish();
    }
}
