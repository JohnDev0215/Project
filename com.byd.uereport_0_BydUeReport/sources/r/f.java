package r;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$color;
import androidx.appcompat.R$drawable;
import r.v;

/* JADX INFO: compiled from: AppCompatDrawableManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final PorterDuff.Mode f7514a = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static f f7515b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public v f7516c;

    /* JADX INFO: compiled from: AppCompatDrawableManager.java */
    public class a implements v.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int[] f7517a = {R$drawable.abc_textfield_search_default_mtrl_alpha, R$drawable.abc_textfield_default_mtrl_alpha, R$drawable.abc_ab_share_pack_mtrl_alpha};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int[] f7518b = {R$drawable.abc_ic_commit_search_api_mtrl_alpha, R$drawable.abc_seekbar_tick_mark_material, R$drawable.abc_ic_menu_share_mtrl_alpha, R$drawable.abc_ic_menu_copy_mtrl_am_alpha, R$drawable.abc_ic_menu_cut_mtrl_alpha, R$drawable.abc_ic_menu_selectall_mtrl_alpha, R$drawable.abc_ic_menu_paste_mtrl_am_alpha};

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int[] f7519c = {R$drawable.abc_textfield_activated_mtrl_alpha, R$drawable.abc_textfield_search_activated_mtrl_alpha, R$drawable.abc_cab_background_top_mtrl_alpha, R$drawable.abc_text_cursor_material, R$drawable.abc_text_select_handle_left_mtrl_dark, R$drawable.abc_text_select_handle_middle_mtrl_dark, R$drawable.abc_text_select_handle_right_mtrl_dark, R$drawable.abc_text_select_handle_left_mtrl_light, R$drawable.abc_text_select_handle_middle_mtrl_light, R$drawable.abc_text_select_handle_right_mtrl_light};

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int[] f7520d = {R$drawable.abc_popup_background_mtrl_mult, R$drawable.abc_cab_background_internal_bg, R$drawable.abc_menu_hardkey_panel_mtrl_mult};

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int[] f7521e = {R$drawable.abc_tab_indicator_material, R$drawable.abc_textfield_search_material};

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final int[] f7522f = {R$drawable.abc_btn_check_material, R$drawable.abc_btn_radio_material, R$drawable.abc_btn_check_material_anim, R$drawable.abc_btn_radio_material_anim};

        /* JADX WARN: Removed duplicated region for block: B:21:0x0046  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0061 A[RETURN] */
        @Override // r.v.e
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean a(android.content.Context r7, int r8, android.graphics.drawable.Drawable r9) {
            /*
                r6 = this;
                android.graphics.PorterDuff$Mode r0 = r.f.a()
                int[] r1 = r6.f7517a
                boolean r1 = r6.f(r1, r8)
                r2 = 16842801(0x1010031, float:2.3693695E-38)
                r3 = -1
                r4 = 0
                r5 = 1
                if (r1 == 0) goto L17
                int r2 = androidx.appcompat.R$attr.colorControlNormal
            L14:
                r8 = r3
            L15:
                r1 = r5
                goto L44
            L17:
                int[] r1 = r6.f7519c
                boolean r1 = r6.f(r1, r8)
                if (r1 == 0) goto L22
                int r2 = androidx.appcompat.R$attr.colorControlActivated
                goto L14
            L22:
                int[] r1 = r6.f7520d
                boolean r1 = r6.f(r1, r8)
                if (r1 == 0) goto L2d
                android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
                goto L14
            L2d:
                int r1 = androidx.appcompat.R$drawable.abc_list_divider_mtrl_alpha
                if (r8 != r1) goto L3c
                r2 = 16842800(0x1010030, float:2.3693693E-38)
                r8 = 1109603123(0x42233333, float:40.8)
                int r8 = java.lang.Math.round(r8)
                goto L15
            L3c:
                int r1 = androidx.appcompat.R$drawable.abc_dialog_material_background
                if (r8 != r1) goto L41
                goto L14
            L41:
                r8 = r3
                r1 = r4
                r2 = r1
            L44:
                if (r1 == 0) goto L61
                boolean r1 = r.q.a(r9)
                if (r1 == 0) goto L50
                android.graphics.drawable.Drawable r9 = r9.mutate()
            L50:
                int r7 = r.z.c(r7, r2)
                android.graphics.PorterDuffColorFilter r7 = r.f.e(r7, r0)
                r9.setColorFilter(r7)
                if (r8 == r3) goto L60
                r9.setAlpha(r8)
            L60:
                return r5
            L61:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: r.f.a.a(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
        }

        @Override // r.v.e
        public PorterDuff.Mode b(int i8) {
            if (i8 == R$drawable.abc_switch_thumb_material) {
                return PorterDuff.Mode.MULTIPLY;
            }
            return null;
        }

        @Override // r.v.e
        public Drawable c(v vVar, Context context, int i8) {
            if (i8 == R$drawable.abc_cab_background_top_material) {
                return new LayerDrawable(new Drawable[]{vVar.j(context, R$drawable.abc_cab_background_internal_bg), vVar.j(context, R$drawable.abc_cab_background_top_mtrl_alpha)});
            }
            return null;
        }

        @Override // r.v.e
        public ColorStateList d(Context context, int i8) {
            if (i8 == R$drawable.abc_edit_text_material) {
                return m.a.c(context, R$color.abc_tint_edittext);
            }
            if (i8 == R$drawable.abc_switch_track_mtrl_alpha) {
                return m.a.c(context, R$color.abc_tint_switch_track);
            }
            if (i8 == R$drawable.abc_switch_thumb_material) {
                return k(context);
            }
            if (i8 == R$drawable.abc_btn_default_mtrl_shape) {
                return j(context);
            }
            if (i8 == R$drawable.abc_btn_borderless_material) {
                return g(context);
            }
            if (i8 == R$drawable.abc_btn_colored_material) {
                return i(context);
            }
            if (i8 == R$drawable.abc_spinner_mtrl_am_alpha || i8 == R$drawable.abc_spinner_textfield_background_material) {
                return m.a.c(context, R$color.abc_tint_spinner);
            }
            if (f(this.f7518b, i8)) {
                return z.e(context, R$attr.colorControlNormal);
            }
            if (f(this.f7521e, i8)) {
                return m.a.c(context, R$color.abc_tint_default);
            }
            if (f(this.f7522f, i8)) {
                return m.a.c(context, R$color.abc_tint_btn_checkable);
            }
            if (i8 == R$drawable.abc_seekbar_thumb_material) {
                return m.a.c(context, R$color.abc_tint_seek_thumb);
            }
            return null;
        }

        @Override // r.v.e
        public boolean e(Context context, int i8, Drawable drawable) {
            if (i8 == R$drawable.abc_seekbar_track_material) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                Drawable drawableFindDrawableByLayerId = layerDrawable.findDrawableByLayerId(R.id.background);
                int i9 = R$attr.colorControlNormal;
                l(drawableFindDrawableByLayerId, z.c(context, i9), f.f7514a);
                l(layerDrawable.findDrawableByLayerId(R.id.secondaryProgress), z.c(context, i9), f.f7514a);
                l(layerDrawable.findDrawableByLayerId(R.id.progress), z.c(context, R$attr.colorControlActivated), f.f7514a);
                return true;
            }
            if (i8 != R$drawable.abc_ratingbar_material && i8 != R$drawable.abc_ratingbar_indicator_material && i8 != R$drawable.abc_ratingbar_small_material) {
                return false;
            }
            LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
            l(layerDrawable2.findDrawableByLayerId(R.id.background), z.b(context, R$attr.colorControlNormal), f.f7514a);
            Drawable drawableFindDrawableByLayerId2 = layerDrawable2.findDrawableByLayerId(R.id.secondaryProgress);
            int i10 = R$attr.colorControlActivated;
            l(drawableFindDrawableByLayerId2, z.c(context, i10), f.f7514a);
            l(layerDrawable2.findDrawableByLayerId(R.id.progress), z.c(context, i10), f.f7514a);
            return true;
        }

        public final boolean f(int[] iArr, int i8) {
            for (int i9 : iArr) {
                if (i9 == i8) {
                    return true;
                }
            }
            return false;
        }

        public final ColorStateList g(Context context) {
            return h(context, 0);
        }

        public final ColorStateList h(Context context, int i8) {
            int iC = z.c(context, R$attr.colorControlHighlight);
            return new ColorStateList(new int[][]{z.f7684b, z.f7687e, z.f7685c, z.f7691i}, new int[]{z.b(context, R$attr.colorButtonNormal), g0.a.b(iC, i8), g0.a.b(iC, i8), i8});
        }

        public final ColorStateList i(Context context) {
            return h(context, z.c(context, R$attr.colorAccent));
        }

        public final ColorStateList j(Context context) {
            return h(context, z.c(context, R$attr.colorButtonNormal));
        }

        public final ColorStateList k(Context context) {
            int[][] iArr = new int[3][];
            int[] iArr2 = new int[3];
            int i8 = R$attr.colorSwitchThumbNormal;
            ColorStateList colorStateListE = z.e(context, i8);
            if (colorStateListE == null || !colorStateListE.isStateful()) {
                iArr[0] = z.f7684b;
                iArr2[0] = z.b(context, i8);
                iArr[1] = z.f7688f;
                iArr2[1] = z.c(context, R$attr.colorControlActivated);
                iArr[2] = z.f7691i;
                iArr2[2] = z.c(context, i8);
            } else {
                iArr[0] = z.f7684b;
                iArr2[0] = colorStateListE.getColorForState(iArr[0], 0);
                iArr[1] = z.f7688f;
                iArr2[1] = z.c(context, R$attr.colorControlActivated);
                iArr[2] = z.f7691i;
                iArr2[2] = colorStateListE.getDefaultColor();
            }
            return new ColorStateList(iArr, iArr2);
        }

        public final void l(Drawable drawable, int i8, PorterDuff.Mode mode) {
            if (q.a(drawable)) {
                drawable = drawable.mutate();
            }
            if (mode == null) {
                mode = f.f7514a;
            }
            drawable.setColorFilter(f.e(i8, mode));
        }
    }

    public static synchronized f b() {
        if (f7515b == null) {
            h();
        }
        return f7515b;
    }

    public static synchronized PorterDuffColorFilter e(int i8, PorterDuff.Mode mode) {
        return v.l(i8, mode);
    }

    public static synchronized void h() {
        if (f7515b == null) {
            f fVar = new f();
            f7515b = fVar;
            fVar.f7516c = v.h();
            f7515b.f7516c.u(new a());
        }
    }

    public static void i(Drawable drawable, c0 c0Var, int[] iArr) {
        v.w(drawable, c0Var, iArr);
    }

    public synchronized Drawable c(Context context, int i8) {
        return this.f7516c.j(context, i8);
    }

    public synchronized Drawable d(Context context, int i8, boolean z7) {
        return this.f7516c.k(context, i8, z7);
    }

    public synchronized ColorStateList f(Context context, int i8) {
        return this.f7516c.m(context, i8);
    }

    public synchronized void g(Context context) {
        this.f7516c.s(context);
    }
}
