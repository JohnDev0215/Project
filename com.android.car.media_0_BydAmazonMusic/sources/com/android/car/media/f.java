package com.android.car.media;

import android.car.content.pm.XCarPackageManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.android.car.media.common.source.MediaSourceViewModel;
import com.android.car.ui.baselayout.Insets;
import spi.b7;
import spi.m;
import spi.z4;

/* JADX INFO: compiled from: ViewControllerBase.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f implements z4 {
    public final boolean a;
    public final XCarPackageManager b;
    public final FragmentActivity c;
    public final int d;
    public final View e;
    public final m f;
    public final MediaSourceViewModel g;
    public Intent h;

    /* JADX INFO: compiled from: ViewControllerBase.java */
    public class a extends m.a {
        public a() {
        }

        @Override // spi.m.a
        public final void a() {
            f.this.c.startActivityForResult(new Intent("android.media.action.DISPLAY_AUDIO_EFFECT_CONTROL_PANEL"), 0);
        }

        @Override // spi.m.a
        public final void d() {
            if (Log.isLoggable("ViewControllerBase", 3)) {
                Log.d("ViewControllerBase", "onSettingsSelection");
            }
            try {
                f fVar = f.this;
                Intent intent = fVar.h;
                if (intent != null) {
                    fVar.c.startActivity(intent);
                }
            } catch (ActivityNotFoundException e) {
                if (Log.isLoggable("ViewControllerBase", 6)) {
                    Log.e("ViewControllerBase", "onSettingsSelection " + e);
                }
            }
        }
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to com.android.car.media.f for r3v1 'this'  java.lang.Object
        	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
        	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
        	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
        	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
        */
    public f(androidx.fragment.app.FragmentActivity r4, android.car.content.pm.XCarPackageManager r5, android.view.ViewGroup r6, @androidx.annotation.LayoutRes int r7) {
        /*
            r3 = this;
            r3.<init>()
            r3.c = r4
            android.content.res.Resources r0 = r4.getResources()
            int r1 = com.android.car.media.main.R$integer.new_album_art_fade_in_duration
            int r1 = r0.getInteger(r1)
            r3.d = r1
            int r1 = com.android.car.media.main.R$bool.show_sound_settings
            boolean r0 = r0.getBoolean(r1)
            r3.a = r0
            android.content.Context r0 = r6.getContext()
            android.view.LayoutInflater r0 = android.view.LayoutInflater.from(r0)
            r1 = 0
            android.view.View r7 = r0.inflate(r7, r6, r1)
            r3.e = r7
            r6.addView(r7)
            spi.l4 r6 = new spi.l4
            r6.<init>(r7)
            java.util.Set<spi.z4> r0 = r6.b
            r0.add(r3)
            android.content.Context r0 = r7.getContext()
            spi.w8 r0 = com.android.car.ui.pluginsupport.c.a(r0)
            r2 = 1
            com.android.car.ui.toolbar.j r6 = r0.installBaseLayoutAround(r7, r6, r2, r2)
            spi.m r7 = new spi.m
            r7.<init>(r4, r6)
            r3.f = r7
            r7.k = r1
            com.android.car.ui.toolbar.b r6 = r7.f
            r6.e(r1)
            com.android.car.ui.toolbar.b r6 = r7.h
            r6.e(r1)
            r3.b = r5
            android.app.Application r4 = r4.getApplication()
            com.android.car.media.common.source.MediaSourceViewModel r4 = com.android.car.media.common.source.MediaSourceViewModel.a(r4, r2)
            r3.g = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.media.f.<init>(androidx.fragment.app.FragmentActivity, android.car.content.pm.XCarPackageManager, android.view.ViewGroup, int):void");
    }

    @CallSuper
    public void a(@Nullable b7 b7Var) {
        String packageName;
        Drawable drawable;
        BitmapDrawable bitmapDrawable;
        ActivityInfo activityInfo;
        Resources resources = this.c.getResources();
        if (b7Var != null) {
            bitmapDrawable = new BitmapDrawable(resources, b7Var.c());
            drawable = this.c.getDrawable(com.android.car.ui.R$drawable.car_ui_toolbar_search_search_icon);
            packageName = b7Var.a.getPackageName();
        } else {
            packageName = null;
            drawable = null;
            bitmapDrawable = null;
        }
        m mVar = this.f;
        mVar.o = bitmapDrawable;
        mVar.f();
        this.f.c.setSearchIcon(drawable);
        this.h = null;
        if (packageName != null) {
            Intent intent = new Intent("android.intent.action.APPLICATION_PREFERENCES");
            intent.setPackage(packageName);
            ResolveInfo resolveInfoResolveActivity = this.c.getPackageManager().resolveActivity(intent, 0);
            if (resolveInfoResolveActivity != null && (activityInfo = resolveInfoResolveActivity.activityInfo) != null && activityInfo.exported) {
                Intent intent2 = new Intent(intent.getAction());
                ActivityInfo activityInfo2 = resolveInfoResolveActivity.activityInfo;
                this.h = intent2.setClassName(activityInfo2.packageName, activityInfo2.name);
                m mVar2 = this.f;
                XCarPackageManager xCarPackageManager = this.b;
                ActivityInfo activityInfo3 = resolveInfoResolveActivity.activityInfo;
                boolean zIsActivityDistractionOptimized = xCarPackageManager != null ? xCarPackageManager.isActivityDistractionOptimized(activityInfo3.packageName, activityInfo3.name) : false;
                com.android.car.ui.toolbar.b bVar = mVar2.g;
                int i = zIsActivityDistractionOptimized ? 0 : 64;
                if (bVar.m != i) {
                    bVar.m = i;
                    bVar.f();
                }
            }
        }
        this.f.g.e(this.h != null);
        this.f.h.e(this.a);
    }

    @Override // spi.z4
    public void b(@NonNull Insets insets) {
    }
}
