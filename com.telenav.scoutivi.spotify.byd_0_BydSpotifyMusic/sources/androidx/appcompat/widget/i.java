package androidx.appcompat.widget;

import android.R;
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
import androidx.appcompat.widget.i0;

/* JADX INFO: compiled from: AppCompatDrawableManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final PorterDuff.Mode f1080b = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static i f1081c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private i0 f1082a;

    /* JADX INFO: compiled from: AppCompatDrawableManager.java */
    class a implements i0.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int[] f1083a = {e.e.abc_textfield_search_default_mtrl_alpha, e.e.abc_textfield_default_mtrl_alpha, e.e.abc_ab_share_pack_mtrl_alpha};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int[] f1084b = {e.e.abc_ic_commit_search_api_mtrl_alpha, e.e.abc_seekbar_tick_mark_material, e.e.abc_ic_menu_share_mtrl_alpha, e.e.abc_ic_menu_copy_mtrl_am_alpha, e.e.abc_ic_menu_cut_mtrl_alpha, e.e.abc_ic_menu_selectall_mtrl_alpha, e.e.abc_ic_menu_paste_mtrl_am_alpha};

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int[] f1085c = {e.e.abc_textfield_activated_mtrl_alpha, e.e.abc_textfield_search_activated_mtrl_alpha, e.e.abc_cab_background_top_mtrl_alpha, e.e.abc_text_cursor_material, e.e.abc_text_select_handle_left_mtrl, e.e.abc_text_select_handle_middle_mtrl, e.e.abc_text_select_handle_right_mtrl};

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int[] f1086d = {e.e.abc_popup_background_mtrl_mult, e.e.abc_cab_background_internal_bg, e.e.abc_menu_hardkey_panel_mtrl_mult};

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int[] f1087e = {e.e.abc_tab_indicator_material, e.e.abc_textfield_search_material};

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final int[] f1088f = {e.e.abc_btn_check_material, e.e.abc_btn_radio_material, e.e.abc_btn_check_material_anim, e.e.abc_btn_radio_material_anim};

        a() {
        }

        private boolean f(int[] iArr, int i7) {
            for (int i8 : iArr) {
                if (i8 == i7) {
                    return true;
                }
            }
            return false;
        }

        private ColorStateList g(Context context) {
            return h(context, 0);
        }

        private ColorStateList h(Context context, int i7) {
            int iC = n0.c(context, e.a.colorControlHighlight);
            return new ColorStateList(new int[][]{n0.f1155b, n0.f1158e, n0.f1156c, n0.f1162i}, new int[]{n0.b(context, e.a.colorButtonNormal), z.a.b(iC, i7), z.a.b(iC, i7), i7});
        }

        private ColorStateList i(Context context) {
            return h(context, n0.c(context, e.a.colorAccent));
        }

        private ColorStateList j(Context context) {
            return h(context, n0.c(context, e.a.colorButtonNormal));
        }

        private ColorStateList k(Context context) {
            int[][] iArr = new int[3][];
            int[] iArr2 = new int[3];
            int i7 = e.a.colorSwitchThumbNormal;
            ColorStateList colorStateListE = n0.e(context, i7);
            if (colorStateListE == null || !colorStateListE.isStateful()) {
                iArr[0] = n0.f1155b;
                iArr2[0] = n0.b(context, i7);
                iArr[1] = n0.f1159f;
                iArr2[1] = n0.c(context, e.a.colorControlActivated);
                iArr[2] = n0.f1162i;
                iArr2[2] = n0.c(context, i7);
            } else {
                iArr[0] = n0.f1155b;
                iArr2[0] = colorStateListE.getColorForState(iArr[0], 0);
                iArr[1] = n0.f1159f;
                iArr2[1] = n0.c(context, e.a.colorControlActivated);
                iArr[2] = n0.f1162i;
                iArr2[2] = colorStateListE.getDefaultColor();
            }
            return new ColorStateList(iArr, iArr2);
        }

        private LayerDrawable l(i0 i0Var, Context context, int i7) {
            BitmapDrawable bitmapDrawable;
            BitmapDrawable bitmapDrawable2;
            BitmapDrawable bitmapDrawable3;
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(i7);
            Drawable drawableI = i0Var.i(context, e.e.abc_star_black_48dp);
            Drawable drawableI2 = i0Var.i(context, e.e.abc_star_half_black_48dp);
            if ((drawableI instanceof BitmapDrawable) && drawableI.getIntrinsicWidth() == dimensionPixelSize && drawableI.getIntrinsicHeight() == dimensionPixelSize) {
                bitmapDrawable = (BitmapDrawable) drawableI;
                bitmapDrawable2 = new BitmapDrawable(bitmapDrawable.getBitmap());
            } else {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmapCreateBitmap);
                drawableI.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
                drawableI.draw(canvas);
                bitmapDrawable = new BitmapDrawable(bitmapCreateBitmap);
                bitmapDrawable2 = new BitmapDrawable(bitmapCreateBitmap);
            }
            bitmapDrawable2.setTileModeX(Shader.TileMode.REPEAT);
            if ((drawableI2 instanceof BitmapDrawable) && drawableI2.getIntrinsicWidth() == dimensionPixelSize && drawableI2.getIntrinsicHeight() == dimensionPixelSize) {
                bitmapDrawable3 = (BitmapDrawable) drawableI2;
            } else {
                Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(bitmapCreateBitmap2);
                drawableI2.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
                drawableI2.draw(canvas2);
                bitmapDrawable3 = new BitmapDrawable(bitmapCreateBitmap2);
            }
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{bitmapDrawable, bitmapDrawable3, bitmapDrawable2});
            layerDrawable.setId(0, R.id.background);
            layerDrawable.setId(1, R.id.secondaryProgress);
            layerDrawable.setId(2, R.id.progress);
            return layerDrawable;
        }

        private void m(Drawable drawable, int i7, PorterDuff.Mode mode) {
            if (b0.a(drawable)) {
                drawable = drawable.mutate();
            }
            if (mode == null) {
                mode = i.f1080b;
            }
            drawable.setColorFilter(i.e(i7, mode));
        }

        @Override // androidx.appcompat.widget.i0.c
        public Drawable a(i0 i0Var, Context context, int i7) {
            if (i7 == e.e.abc_cab_background_top_material) {
                return new LayerDrawable(new Drawable[]{i0Var.i(context, e.e.abc_cab_background_internal_bg), i0Var.i(context, e.e.abc_cab_background_top_mtrl_alpha)});
            }
            if (i7 == e.e.abc_ratingbar_material) {
                return l(i0Var, context, e.d.abc_star_big);
            }
            if (i7 == e.e.abc_ratingbar_indicator_material) {
                return l(i0Var, context, e.d.abc_star_medium);
            }
            if (i7 == e.e.abc_ratingbar_small_material) {
                return l(i0Var, context, e.d.abc_star_small);
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0046  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0061 A[RETURN] */
        @Override // androidx.appcompat.widget.i0.c
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean b(android.content.Context r7, int r8, android.graphics.drawable.Drawable r9) {
            /*
                r6 = this;
                android.graphics.PorterDuff$Mode r0 = androidx.appcompat.widget.i.a()
                int[] r1 = r6.f1083a
                boolean r1 = r6.f(r1, r8)
                r2 = 16842801(0x1010031, float:2.3693695E-38)
                r3 = -1
                r4 = 0
                r5 = 1
                if (r1 == 0) goto L17
                int r2 = e.a.colorControlNormal
            L14:
                r8 = r3
            L15:
                r1 = r5
                goto L44
            L17:
                int[] r1 = r6.f1085c
                boolean r1 = r6.f(r1, r8)
                if (r1 == 0) goto L22
                int r2 = e.a.colorControlActivated
                goto L14
            L22:
                int[] r1 = r6.f1086d
                boolean r1 = r6.f(r1, r8)
                if (r1 == 0) goto L2d
                android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
                goto L14
            L2d:
                int r1 = e.e.abc_list_divider_mtrl_alpha
                if (r8 != r1) goto L3c
                r2 = 16842800(0x1010030, float:2.3693693E-38)
                r8 = 1109603123(0x42233333, float:40.8)
                int r8 = java.lang.Math.round(r8)
                goto L15
            L3c:
                int r1 = e.e.abc_dialog_material_background
                if (r8 != r1) goto L41
                goto L14
            L41:
                r8 = r3
                r1 = r4
                r2 = r1
            L44:
                if (r1 == 0) goto L61
                boolean r1 = androidx.appcompat.widget.b0.a(r9)
                if (r1 == 0) goto L50
                android.graphics.drawable.Drawable r9 = r9.mutate()
            L50:
                int r7 = androidx.appcompat.widget.n0.c(r7, r2)
                android.graphics.PorterDuffColorFilter r7 = androidx.appcompat.widget.i.e(r7, r0)
                r9.setColorFilter(r7)
                if (r8 == r3) goto L60
                r9.setAlpha(r8)
            L60:
                return r5
            L61:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.i.a.b(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
        }

        @Override // androidx.appcompat.widget.i0.c
        public ColorStateList c(Context context, int i7) {
            if (i7 == e.e.abc_edit_text_material) {
                return f.a.a(context, e.c.abc_tint_edittext);
            }
            if (i7 == e.e.abc_switch_track_mtrl_alpha) {
                return f.a.a(context, e.c.abc_tint_switch_track);
            }
            if (i7 == e.e.abc_switch_thumb_material) {
                return k(context);
            }
            if (i7 == e.e.abc_btn_default_mtrl_shape) {
                return j(context);
            }
            if (i7 == e.e.abc_btn_borderless_material) {
                return g(context);
            }
            if (i7 == e.e.abc_btn_colored_material) {
                return i(context);
            }
            if (i7 == e.e.abc_spinner_mtrl_am_alpha || i7 == e.e.abc_spinner_textfield_background_material) {
                return f.a.a(context, e.c.abc_tint_spinner);
            }
            if (f(this.f1084b, i7)) {
                return n0.e(context, e.a.colorControlNormal);
            }
            if (f(this.f1087e, i7)) {
                return f.a.a(context, e.c.abc_tint_default);
            }
            if (f(this.f1088f, i7)) {
                return f.a.a(context, e.c.abc_tint_btn_checkable);
            }
            if (i7 == e.e.abc_seekbar_thumb_material) {
                return f.a.a(context, e.c.abc_tint_seek_thumb);
            }
            return null;
        }

        @Override // androidx.appcompat.widget.i0.c
        public boolean d(Context context, int i7, Drawable drawable) {
            if (i7 == e.e.abc_seekbar_track_material) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                Drawable drawableFindDrawableByLayerId = layerDrawable.findDrawableByLayerId(R.id.background);
                int i8 = e.a.colorControlNormal;
                m(drawableFindDrawableByLayerId, n0.c(context, i8), i.f1080b);
                m(layerDrawable.findDrawableByLayerId(R.id.secondaryProgress), n0.c(context, i8), i.f1080b);
                m(layerDrawable.findDrawableByLayerId(R.id.progress), n0.c(context, e.a.colorControlActivated), i.f1080b);
                return true;
            }
            if (i7 != e.e.abc_ratingbar_material && i7 != e.e.abc_ratingbar_indicator_material && i7 != e.e.abc_ratingbar_small_material) {
                return false;
            }
            LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
            m(layerDrawable2.findDrawableByLayerId(R.id.background), n0.b(context, e.a.colorControlNormal), i.f1080b);
            Drawable drawableFindDrawableByLayerId2 = layerDrawable2.findDrawableByLayerId(R.id.secondaryProgress);
            int i9 = e.a.colorControlActivated;
            m(drawableFindDrawableByLayerId2, n0.c(context, i9), i.f1080b);
            m(layerDrawable2.findDrawableByLayerId(R.id.progress), n0.c(context, i9), i.f1080b);
            return true;
        }

        @Override // androidx.appcompat.widget.i0.c
        public PorterDuff.Mode e(int i7) {
            if (i7 == e.e.abc_switch_thumb_material) {
                return PorterDuff.Mode.MULTIPLY;
            }
            return null;
        }
    }

    public static synchronized i b() {
        if (f1081c == null) {
            h();
        }
        return f1081c;
    }

    public static synchronized PorterDuffColorFilter e(int i7, PorterDuff.Mode mode) {
        return i0.k(i7, mode);
    }

    public static synchronized void h() {
        if (f1081c == null) {
            i iVar = new i();
            f1081c = iVar;
            iVar.f1082a = i0.g();
            f1081c.f1082a.t(new a());
        }
    }

    static void i(Drawable drawable, q0 q0Var, int[] iArr) {
        i0.v(drawable, q0Var, iArr);
    }

    public synchronized Drawable c(Context context, int i7) {
        return this.f1082a.i(context, i7);
    }

    synchronized Drawable d(Context context, int i7, boolean z6) {
        return this.f1082a.j(context, i7, z6);
    }

    synchronized ColorStateList f(Context context, int i7) {
        return this.f1082a.l(context, i7);
    }

    public synchronized void g(Context context) {
        this.f1082a.r(context);
    }
}
