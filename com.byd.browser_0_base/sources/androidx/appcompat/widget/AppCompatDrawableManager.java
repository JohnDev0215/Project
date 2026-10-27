package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.appcompat.R;
import androidx.appcompat.widget.ResourceManagerInternal;
import defpackage.bz;
import defpackage.gv;

/* JADX INFO: loaded from: classes.dex */
public final class AppCompatDrawableManager {
    public static final PorterDuff.Mode b = PorterDuff.Mode.SRC_IN;
    public static AppCompatDrawableManager c;
    public ResourceManagerInternal a;

    public static synchronized AppCompatDrawableManager a() {
        if (c == null) {
            d();
        }
        return c;
    }

    public static synchronized PorterDuffColorFilter c(int i, PorterDuff.Mode mode) {
        return ResourceManagerInternal.f(i, mode);
    }

    public static synchronized void d() {
        if (c == null) {
            AppCompatDrawableManager appCompatDrawableManager = new AppCompatDrawableManager();
            c = appCompatDrawableManager;
            appCompatDrawableManager.a = ResourceManagerInternal.b();
            ResourceManagerInternal resourceManagerInternal = c.a;
            ResourceManagerInternal.ResourceManagerHooks resourceManagerHooks = new ResourceManagerInternal.ResourceManagerHooks() { // from class: androidx.appcompat.widget.AppCompatDrawableManager.1
                public final int[] a = {R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha};
                public final int[] b = {R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_seekbar_tick_mark_material, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha};
                public final int[] c = {R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material, R.drawable.abc_text_select_handle_left_mtrl, R.drawable.abc_text_select_handle_middle_mtrl, R.drawable.abc_text_select_handle_right_mtrl};
                public final int[] d = {R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult};
                public final int[] e = {R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material};
                public final int[] f = {R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material, R.drawable.abc_btn_check_material_anim, R.drawable.abc_btn_radio_material_anim};

                public static boolean f(int i, int[] iArr) {
                    for (int i2 : iArr) {
                        if (i2 == i) {
                            return true;
                        }
                    }
                    return false;
                }

                public static ColorStateList g(Context context, int i) {
                    int iC = ThemeUtils.c(context, R.attr.colorControlHighlight);
                    return new ColorStateList(new int[][]{ThemeUtils.b, ThemeUtils.d, ThemeUtils.c, ThemeUtils.f}, new int[]{ThemeUtils.b(context, R.attr.colorButtonNormal), gv.f(iC, i), gv.f(iC, i), i});
                }

                public static LayerDrawable h(ResourceManagerInternal resourceManagerInternal2, Context context, int i) {
                    BitmapDrawable bitmapDrawable;
                    BitmapDrawable bitmapDrawable2;
                    BitmapDrawable bitmapDrawable3;
                    int dimensionPixelSize = context.getResources().getDimensionPixelSize(i);
                    Drawable drawableE = resourceManagerInternal2.e(context, R.drawable.abc_star_black_48dp);
                    Drawable drawableE2 = resourceManagerInternal2.e(context, R.drawable.abc_star_half_black_48dp);
                    if ((drawableE instanceof BitmapDrawable) && drawableE.getIntrinsicWidth() == dimensionPixelSize && drawableE.getIntrinsicHeight() == dimensionPixelSize) {
                        bitmapDrawable = (BitmapDrawable) drawableE;
                        bitmapDrawable2 = new BitmapDrawable(bitmapDrawable.getBitmap());
                    } else {
                        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(bitmapCreateBitmap);
                        drawableE.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
                        drawableE.draw(canvas);
                        bitmapDrawable = new BitmapDrawable(bitmapCreateBitmap);
                        bitmapDrawable2 = new BitmapDrawable(bitmapCreateBitmap);
                    }
                    bitmapDrawable2.setTileModeX(Shader.TileMode.REPEAT);
                    if ((drawableE2 instanceof BitmapDrawable) && drawableE2.getIntrinsicWidth() == dimensionPixelSize && drawableE2.getIntrinsicHeight() == dimensionPixelSize) {
                        bitmapDrawable3 = (BitmapDrawable) drawableE2;
                    } else {
                        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
                        Canvas canvas2 = new Canvas(bitmapCreateBitmap2);
                        drawableE2.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
                        drawableE2.draw(canvas2);
                        bitmapDrawable3 = new BitmapDrawable(bitmapCreateBitmap2);
                    }
                    LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{bitmapDrawable, bitmapDrawable3, bitmapDrawable2});
                    layerDrawable.setId(0, android.R.id.background);
                    layerDrawable.setId(1, android.R.id.secondaryProgress);
                    layerDrawable.setId(2, android.R.id.progress);
                    return layerDrawable;
                }

                public static void i(Drawable drawable, int i, PorterDuff.Mode mode) {
                    int[] iArr = DrawableUtils.a;
                    Drawable drawableMutate = drawable.mutate();
                    if (mode == null) {
                        mode = AppCompatDrawableManager.b;
                    }
                    drawableMutate.setColorFilter(AppCompatDrawableManager.c(i, mode));
                }

                /* JADX WARN: Removed duplicated region for block: B:21:0x0046  */
                /* JADX WARN: Removed duplicated region for block: B:25:0x005d A[RETURN] */
                @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final boolean a(android.content.Context r6, int r7, android.graphics.drawable.Drawable r8) {
                    /*
                        r5 = this;
                        android.graphics.PorterDuff$Mode r0 = androidx.appcompat.widget.AppCompatDrawableManager.b
                        int[] r1 = r5.a
                        boolean r1 = f(r7, r1)
                        r2 = -1
                        r3 = 0
                        r4 = 1
                        if (r1 == 0) goto L10
                        int r5 = androidx.appcompat.R.attr.colorControlNormal
                        goto L3d
                    L10:
                        int[] r1 = r5.c
                        boolean r1 = f(r7, r1)
                        if (r1 == 0) goto L1b
                        int r5 = androidx.appcompat.R.attr.colorControlActivated
                        goto L3d
                    L1b:
                        int[] r5 = r5.d
                        boolean r5 = f(r7, r5)
                        if (r5 == 0) goto L27
                        android.graphics.PorterDuff$Mode r5 = android.graphics.PorterDuff.Mode.MULTIPLY
                        r0 = r5
                        goto L3a
                    L27:
                        int r5 = androidx.appcompat.R.drawable.abc_list_divider_mtrl_alpha
                        if (r7 != r5) goto L36
                        r5 = 1109603123(0x42233333, float:40.8)
                        int r5 = java.lang.Math.round(r5)
                        r7 = 16842800(0x1010030, float:2.3693693E-38)
                        goto L3f
                    L36:
                        int r5 = androidx.appcompat.R.drawable.abc_dialog_material_background
                        if (r7 != r5) goto L41
                    L3a:
                        r5 = 16842801(0x1010031, float:2.3693695E-38)
                    L3d:
                        r7 = r5
                        r5 = r2
                    L3f:
                        r1 = r4
                        goto L44
                    L41:
                        r5 = r2
                        r7 = r3
                        r1 = r7
                    L44:
                        if (r1 == 0) goto L5d
                        int[] r1 = androidx.appcompat.widget.DrawableUtils.a
                        android.graphics.drawable.Drawable r8 = r8.mutate()
                        int r6 = androidx.appcompat.widget.ThemeUtils.c(r6, r7)
                        android.graphics.PorterDuffColorFilter r6 = androidx.appcompat.widget.AppCompatDrawableManager.c(r6, r0)
                        r8.setColorFilter(r6)
                        if (r5 == r2) goto L5c
                        r8.setAlpha(r5)
                    L5c:
                        return r4
                    L5d:
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatDrawableManager.AnonymousClass1.a(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
                }

                @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                public final PorterDuff.Mode b(int i) {
                    if (i == R.drawable.abc_switch_thumb_material) {
                        return PorterDuff.Mode.MULTIPLY;
                    }
                    return null;
                }

                @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                public final LayerDrawable c(ResourceManagerInternal resourceManagerInternal2, Context context, int i) {
                    if (i == R.drawable.abc_cab_background_top_material) {
                        return new LayerDrawable(new Drawable[]{resourceManagerInternal2.e(context, R.drawable.abc_cab_background_internal_bg), resourceManagerInternal2.e(context, R.drawable.abc_cab_background_top_mtrl_alpha)});
                    }
                    if (i == R.drawable.abc_ratingbar_material) {
                        return h(resourceManagerInternal2, context, R.dimen.abc_star_big);
                    }
                    if (i == R.drawable.abc_ratingbar_indicator_material) {
                        return h(resourceManagerInternal2, context, R.dimen.abc_star_medium);
                    }
                    if (i == R.drawable.abc_ratingbar_small_material) {
                        return h(resourceManagerInternal2, context, R.dimen.abc_star_small);
                    }
                    return null;
                }

                @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                public final ColorStateList d(Context context, int i) {
                    if (i == R.drawable.abc_edit_text_material) {
                        return bz.b(context, R.color.abc_tint_edittext);
                    }
                    if (i == R.drawable.abc_switch_track_mtrl_alpha) {
                        return bz.b(context, R.color.abc_tint_switch_track);
                    }
                    if (i != R.drawable.abc_switch_thumb_material) {
                        if (i == R.drawable.abc_btn_default_mtrl_shape) {
                            return g(context, ThemeUtils.c(context, R.attr.colorButtonNormal));
                        }
                        if (i == R.drawable.abc_btn_borderless_material) {
                            return g(context, 0);
                        }
                        if (i == R.drawable.abc_btn_colored_material) {
                            return g(context, ThemeUtils.c(context, R.attr.colorAccent));
                        }
                        if (i == R.drawable.abc_spinner_mtrl_am_alpha || i == R.drawable.abc_spinner_textfield_background_material) {
                            return bz.b(context, R.color.abc_tint_spinner);
                        }
                        if (f(i, this.b)) {
                            return ThemeUtils.d(context, R.attr.colorControlNormal);
                        }
                        if (f(i, this.e)) {
                            return bz.b(context, R.color.abc_tint_default);
                        }
                        if (f(i, this.f)) {
                            return bz.b(context, R.color.abc_tint_btn_checkable);
                        }
                        if (i == R.drawable.abc_seekbar_thumb_material) {
                            return bz.b(context, R.color.abc_tint_seek_thumb);
                        }
                        return null;
                    }
                    int[][] iArr = new int[3][];
                    int[] iArr2 = new int[3];
                    int i2 = R.attr.colorSwitchThumbNormal;
                    ColorStateList colorStateListD = ThemeUtils.d(context, i2);
                    if (colorStateListD == null || !colorStateListD.isStateful()) {
                        iArr[0] = ThemeUtils.b;
                        iArr2[0] = ThemeUtils.b(context, i2);
                        iArr[1] = ThemeUtils.e;
                        iArr2[1] = ThemeUtils.c(context, R.attr.colorControlActivated);
                        iArr[2] = ThemeUtils.f;
                        iArr2[2] = ThemeUtils.c(context, i2);
                    } else {
                        int[] iArr3 = ThemeUtils.b;
                        iArr[0] = iArr3;
                        iArr2[0] = colorStateListD.getColorForState(iArr3, 0);
                        iArr[1] = ThemeUtils.e;
                        iArr2[1] = ThemeUtils.c(context, R.attr.colorControlActivated);
                        iArr[2] = ThemeUtils.f;
                        iArr2[2] = colorStateListD.getDefaultColor();
                    }
                    return new ColorStateList(iArr, iArr2);
                }

                @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                public final boolean e(Context context, int i, Drawable drawable) {
                    if (i == R.drawable.abc_seekbar_track_material) {
                        LayerDrawable layerDrawable = (LayerDrawable) drawable;
                        Drawable drawableFindDrawableByLayerId = layerDrawable.findDrawableByLayerId(android.R.id.background);
                        int i2 = R.attr.colorControlNormal;
                        int iC = ThemeUtils.c(context, i2);
                        PorterDuff.Mode mode = AppCompatDrawableManager.b;
                        i(drawableFindDrawableByLayerId, iC, mode);
                        i(layerDrawable.findDrawableByLayerId(android.R.id.secondaryProgress), ThemeUtils.c(context, i2), mode);
                        i(layerDrawable.findDrawableByLayerId(android.R.id.progress), ThemeUtils.c(context, R.attr.colorControlActivated), mode);
                        return true;
                    }
                    if (i != R.drawable.abc_ratingbar_material && i != R.drawable.abc_ratingbar_indicator_material && i != R.drawable.abc_ratingbar_small_material) {
                        return false;
                    }
                    LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
                    Drawable drawableFindDrawableByLayerId2 = layerDrawable2.findDrawableByLayerId(android.R.id.background);
                    int iB = ThemeUtils.b(context, R.attr.colorControlNormal);
                    PorterDuff.Mode mode2 = AppCompatDrawableManager.b;
                    i(drawableFindDrawableByLayerId2, iB, mode2);
                    Drawable drawableFindDrawableByLayerId3 = layerDrawable2.findDrawableByLayerId(android.R.id.secondaryProgress);
                    int i3 = R.attr.colorControlActivated;
                    i(drawableFindDrawableByLayerId3, ThemeUtils.c(context, i3), mode2);
                    i(layerDrawable2.findDrawableByLayerId(android.R.id.progress), ThemeUtils.c(context, i3), mode2);
                    return true;
                }
            };
            synchronized (resourceManagerInternal) {
                resourceManagerInternal.e = resourceManagerHooks;
            }
        }
    }

    public static void e(Drawable drawable, TintInfo tintInfo, int[] iArr) {
        PorterDuff.Mode mode = ResourceManagerInternal.f;
        int[] state = drawable.getState();
        int[] iArr2 = DrawableUtils.a;
        if (drawable.mutate() == drawable) {
            if ((drawable instanceof LayerDrawable) && drawable.isStateful()) {
                drawable.setState(new int[0]);
                drawable.setState(state);
            }
            boolean z = tintInfo.d;
            if (!z && !tintInfo.c) {
                drawable.clearColorFilter();
                return;
            }
            PorterDuffColorFilter porterDuffColorFilterF = null;
            ColorStateList colorStateList = z ? tintInfo.a : null;
            PorterDuff.Mode mode2 = tintInfo.c ? tintInfo.b : ResourceManagerInternal.f;
            if (colorStateList != null && mode2 != null) {
                porterDuffColorFilterF = ResourceManagerInternal.f(colorStateList.getColorForState(iArr, 0), mode2);
            }
            drawable.setColorFilter(porterDuffColorFilterF);
        }
    }

    public final synchronized Drawable b(Context context, int i) {
        return this.a.e(context, i);
    }
}
