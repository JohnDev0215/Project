package com.android.car.ui.toolbar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.text.TextUtils;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.android.car.ui.R$array;
import com.android.car.ui.R$bool;
import com.android.car.ui.R$string;
import com.android.car.ui.core.SearchResultsProvider;
import com.android.car.ui.recyclerview.CarUiRecyclerView;
import com.android.car.ui.recyclerview.a;
import com.android.car.ui.toolbar.d;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import spi.aa;
import spi.b2;
import spi.g1;
import spi.u1;

/* JADX INFO: compiled from: SearchWidescreenController.java */
/* JADX INFO: loaded from: classes2.dex */
@RequiresApi(30)
public final class f {
    public final Context b;
    public final InputMethodManager c;

    @Nullable
    public TextView d;
    public SurfaceControlViewHost e;
    public int f;
    public int g;
    public ViewGroup i;
    public ViewGroup.LayoutParams j;
    public final Handler a = new Handler(Looper.getMainLooper());
    public boolean h = false;

    @NonNull
    public e k = e.a().a();

    public f(@NonNull Context context) {
        this.b = context;
        this.c = (InputMethodManager) context.getSystemService(InputMethodManager.class);
    }

    public static WindowInsets a(final f fVar, View view, WindowInsets windowInsets) {
        final FrameLayout frameLayout;
        Objects.requireNonNull(fVar);
        if (Build.VERSION.SDK_INT < 30) {
            throw new IllegalStateException("Cannot check if the ime is visible pre R");
        }
        if (fVar.d == null) {
            return view.onApplyWindowInsets(windowInsets);
        }
        if (windowInsets.isVisible(WindowInsets.Type.ime())) {
            View viewF = fVar.f();
            if (fVar.h || viewF == null) {
                frameLayout = null;
            } else {
                fVar.j = viewF.getLayoutParams();
                ViewGroup viewGroup = (ViewGroup) viewF.getParent();
                fVar.i = viewGroup;
                if (viewGroup != null) {
                    viewGroup.removeView(viewF);
                }
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
                View view2 = fVar.k.a;
                if (view2 instanceof CarUiRecyclerView) {
                    layoutParams.topMargin = -view2.getPaddingTop();
                    layoutParams.bottomMargin = -view2.getPaddingBottom();
                    layoutParams.leftMargin = -view2.getPaddingLeft();
                    layoutParams.rightMargin = -view2.getPaddingRight();
                }
                frameLayout = new FrameLayout(fVar.b);
                frameLayout.addView(viewF, layoutParams);
            }
            Uri uriA = SearchResultsProvider.a(fVar.b);
            fVar.b.getContentResolver().delete(uriA, null, null);
            if (fVar.f() == null || !e(fVar.b).a) {
                List<? extends g1> list = fVar.k.c;
                if (list == null) {
                    fVar.c.sendAppPrivateCommand(fVar.d, "automotive_wide_screen", null);
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        g1 g1Var = list.get(i);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("primaryId", Integer.valueOf(i));
                        contentValues.put("secondary", Integer.valueOf(i));
                        BitmapDrawable bitmapDrawable = (BitmapDrawable) g1Var.a;
                        contentValues.put("primary_image", bitmapDrawable != null ? d(bitmapDrawable.getBitmap()) : null);
                        a.EnumC0011a enumC0011a = a.EnumC0011a.ICON;
                        contentValues.put("secondary_image", (byte[]) null);
                        u1 u1Var = g1Var.b;
                        contentValues.put("title", u1Var != null ? u1Var.b().toString() : null);
                        contentValues.put("subtitle", (String) null);
                        fVar.b.getContentResolver().insert(uriA, contentValues);
                    }
                    fVar.c.sendAppPrivateCommand(fVar.d, "wide_screen_search_results", new Bundle());
                }
            }
            final int i2 = 1;
            fVar.a.post(new Runnable() { // from class: com.android.car.ui.toolbar.h
                @Override // java.lang.Runnable
                public final void run() {
                    switch (i2) {
                        case 0:
                            ((TabAdapterV1) fVar).lambda$new$0((Consumer) frameLayout);
                            break;
                        case 1:
                            f.c((f) fVar, (FrameLayout) frameLayout);
                            break;
                        case 2:
                            f fVar2 = (f) fVar;
                            View view3 = (View) frameLayout;
                            Objects.requireNonNull(fVar2);
                            ViewGroup viewGroup2 = (ViewGroup) view3.getParent();
                            if (viewGroup2 != null) {
                                viewGroup2.removeView(view3);
                            }
                            fVar2.i.addView(view3, fVar2.j);
                            fVar2.i.requestLayout();
                            fVar2.i = null;
                            fVar2.j = null;
                            break;
                        default:
                            ((ToolbarControllerAdapterV1) fVar).lambda$new$0((Activity) frameLayout);
                            break;
                    }
                }
            });
        } else {
            final View viewF2 = fVar.f();
            if (fVar.i != null && viewF2 != null) {
                final int i3 = 2;
                fVar.a.post(new Runnable() { // from class: com.android.car.ui.toolbar.h
                    @Override // java.lang.Runnable
                    public final void run() {
                        switch (i3) {
                            case 0:
                                ((TabAdapterV1) fVar).lambda$new$0((Consumer) viewF2);
                                break;
                            case 1:
                                f.c((f) fVar, (FrameLayout) viewF2);
                                break;
                            case 2:
                                f fVar2 = (f) fVar;
                                View view3 = (View) viewF2;
                                Objects.requireNonNull(fVar2);
                                ViewGroup viewGroup2 = (ViewGroup) view3.getParent();
                                if (viewGroup2 != null) {
                                    viewGroup2.removeView(view3);
                                }
                                fVar2.i.addView(view3, fVar2.j);
                                fVar2.i.requestLayout();
                                fVar2.i = null;
                                fVar2.j = null;
                                break;
                            default:
                                ((ToolbarControllerAdapterV1) fVar).lambda$new$0((Activity) viewF2);
                                break;
                        }
                    }
                });
            }
            fVar.h = false;
        }
        return view.onApplyWindowInsets(windowInsets);
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void b(com.android.car.ui.toolbar.f r5, java.lang.String r6, android.os.Bundle r7) {
        /*
            Method dump skipped, instruction units count: 341
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.ui.toolbar.f.b(com.android.car.ui.toolbar.f, java.lang.String, android.os.Bundle):void");
    }

    public static /* synthetic */ void c(f fVar, FrameLayout frameLayout) {
        SurfaceControlViewHost surfaceControlViewHost = fVar.e;
        if (surfaceControlViewHost == null || frameLayout == null || surfaceControlViewHost.getView() != null) {
            return;
        }
        fVar.h = true;
        fVar.e.setView(frameLayout, fVar.g, fVar.f);
    }

    public static byte[] d(Bitmap bitmap) {
        Parcel parcelObtain = Parcel.obtain();
        bitmap.writeToParcel(parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        return bArrMarshall;
    }

    public static d e(Context context) {
        boolean z;
        String strH = b2.h(context.getResources(), R$string.car_ui_ime_wide_screen_system_property_name);
        boolean z2 = false;
        boolean z3 = (!TextUtils.isEmpty(strH) ? Boolean.parseBoolean(strH) : false) && Build.VERSION.SDK_INT >= 30;
        boolean z4 = context.getResources().getBoolean(R$bool.car_ui_ime_wide_screen_allow_app_hide_content_area);
        String[] stringArray = context.getResources().getStringArray(R$array.car_ui_ime_wide_screen_allowed_package_list);
        int length = stringArray.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            }
            if (stringArray[i].equals(context.getPackageName())) {
                z = true;
                break;
            }
            i++;
        }
        if (z3 && (z || z4)) {
            z2 = true;
        }
        d.b bVar = new d.b();
        bVar.a = z2;
        bVar.b = z3;
        return new d(bVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public final View f() {
        View view = this.k.a;
        return view instanceof CarUiRecyclerView ? ((CarUiRecyclerView) view).getView() : view;
    }

    public final void g(@Nullable TextView textView) {
        if (this.d != null) {
            throw new IllegalStateException("TextView already set");
        }
        this.d = textView;
        spi.h hVar = new spi.h(this, 1);
        if (Build.VERSION.SDK_INT < 30 || textView == null) {
            return;
        }
        if (textView.isAttachedToWindow()) {
            this.d.getRootView().setOnApplyWindowInsetsListener(hVar);
        } else {
            this.d.addOnAttachStateChangeListener(new aa(this, hVar));
        }
    }
}
