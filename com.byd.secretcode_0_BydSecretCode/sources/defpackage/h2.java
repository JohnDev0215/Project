package defpackage;

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
import defpackage.x2;

/* JADX INFO: compiled from: AppCompatDrawableManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class h2 {
    public static final PorterDuff.Mode a = PorterDuff.Mode.SRC_IN;
    public static h2 b;
    public x2 c;

    /* JADX INFO: compiled from: AppCompatDrawableManager.java */
    public class a implements x2.e {
        public final int[] a = {R$drawable.abc_textfield_search_default_mtrl_alpha, R$drawable.abc_textfield_default_mtrl_alpha, R$drawable.abc_ab_share_pack_mtrl_alpha};
        public final int[] b = {R$drawable.abc_ic_commit_search_api_mtrl_alpha, R$drawable.abc_seekbar_tick_mark_material, R$drawable.abc_ic_menu_share_mtrl_alpha, R$drawable.abc_ic_menu_copy_mtrl_am_alpha, R$drawable.abc_ic_menu_cut_mtrl_alpha, R$drawable.abc_ic_menu_selectall_mtrl_alpha, R$drawable.abc_ic_menu_paste_mtrl_am_alpha};
        public final int[] c = {R$drawable.abc_textfield_activated_mtrl_alpha, R$drawable.abc_textfield_search_activated_mtrl_alpha, R$drawable.abc_cab_background_top_mtrl_alpha, R$drawable.abc_text_cursor_material, R$drawable.abc_text_select_handle_left_mtrl_dark, R$drawable.abc_text_select_handle_middle_mtrl_dark, R$drawable.abc_text_select_handle_right_mtrl_dark, R$drawable.abc_text_select_handle_left_mtrl_light, R$drawable.abc_text_select_handle_middle_mtrl_light, R$drawable.abc_text_select_handle_right_mtrl_light};
        public final int[] d = {R$drawable.abc_popup_background_mtrl_mult, R$drawable.abc_cab_background_internal_bg, R$drawable.abc_menu_hardkey_panel_mtrl_mult};
        public final int[] e = {R$drawable.abc_tab_indicator_material, R$drawable.abc_textfield_search_material};
        public final int[] f = {R$drawable.abc_btn_check_material, R$drawable.abc_btn_radio_material, R$drawable.abc_btn_check_material_anim, R$drawable.abc_btn_radio_material_anim};

        @Override // x2.e
        public Drawable a(x2 x2Var, Context context, int i) {
            if (i == R$drawable.abc_cab_background_top_material) {
                return new LayerDrawable(new Drawable[]{x2Var.j(context, R$drawable.abc_cab_background_internal_bg), x2Var.j(context, R$drawable.abc_cab_background_top_mtrl_alpha)});
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0046  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0061 A[RETURN] */
        @Override // x2.e
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean b(android.content.Context r7, int r8, android.graphics.drawable.Drawable r9) {
            /*
                r6 = this;
                android.graphics.PorterDuff$Mode r0 = defpackage.h2.a()
                int[] r1 = r6.a
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
                int[] r1 = r6.c
                boolean r1 = r6.f(r1, r8)
                if (r1 == 0) goto L22
                int r2 = androidx.appcompat.R$attr.colorControlActivated
                goto L14
            L22:
                int[] r1 = r6.d
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
                boolean r1 = defpackage.s2.a(r9)
                if (r1 == 0) goto L50
                android.graphics.drawable.Drawable r9 = r9.mutate()
            L50:
                int r7 = defpackage.b3.c(r7, r2)
                android.graphics.PorterDuffColorFilter r7 = defpackage.h2.e(r7, r0)
                r9.setColorFilter(r7)
                if (r8 == r3) goto L60
                r9.setAlpha(r8)
            L60:
                return r5
            L61:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: h2.a.b(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
        }

        @Override // x2.e
        public ColorStateList c(Context context, int i) {
            if (i == R$drawable.abc_edit_text_material) {
                return u0.c(context, R$color.abc_tint_edittext);
            }
            if (i == R$drawable.abc_switch_track_mtrl_alpha) {
                return u0.c(context, R$color.abc_tint_switch_track);
            }
            if (i == R$drawable.abc_switch_thumb_material) {
                return k(context);
            }
            if (i == R$drawable.abc_btn_default_mtrl_shape) {
                return j(context);
            }
            if (i == R$drawable.abc_btn_borderless_material) {
                return g(context);
            }
            if (i == R$drawable.abc_btn_colored_material) {
                return i(context);
            }
            if (i == R$drawable.abc_spinner_mtrl_am_alpha || i == R$drawable.abc_spinner_textfield_background_material) {
                return u0.c(context, R$color.abc_tint_spinner);
            }
            if (f(this.b, i)) {
                return b3.e(context, R$attr.colorControlNormal);
            }
            if (f(this.e, i)) {
                return u0.c(context, R$color.abc_tint_default);
            }
            if (f(this.f, i)) {
                return u0.c(context, R$color.abc_tint_btn_checkable);
            }
            if (i == R$drawable.abc_seekbar_thumb_material) {
                return u0.c(context, R$color.abc_tint_seek_thumb);
            }
            return null;
        }

        @Override // x2.e
        public boolean d(Context context, int i, Drawable drawable) {
            if (i == R$drawable.abc_seekbar_track_material) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                Drawable drawableFindDrawableByLayerId = layerDrawable.findDrawableByLayerId(R.id.background);
                int i2 = R$attr.colorControlNormal;
                l(drawableFindDrawableByLayerId, b3.c(context, i2), h2.a);
                l(layerDrawable.findDrawableByLayerId(R.id.secondaryProgress), b3.c(context, i2), h2.a);
                l(layerDrawable.findDrawableByLayerId(R.id.progress), b3.c(context, R$attr.colorControlActivated), h2.a);
                return true;
            }
            if (i != R$drawable.abc_ratingbar_material && i != R$drawable.abc_ratingbar_indicator_material && i != R$drawable.abc_ratingbar_small_material) {
                return false;
            }
            LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
            l(layerDrawable2.findDrawableByLayerId(R.id.background), b3.b(context, R$attr.colorControlNormal), h2.a);
            Drawable drawableFindDrawableByLayerId2 = layerDrawable2.findDrawableByLayerId(R.id.secondaryProgress);
            int i3 = R$attr.colorControlActivated;
            l(drawableFindDrawableByLayerId2, b3.c(context, i3), h2.a);
            l(layerDrawable2.findDrawableByLayerId(R.id.progress), b3.c(context, i3), h2.a);
            return true;
        }

        @Override // x2.e
        public PorterDuff.Mode e(int i) {
            if (i == R$drawable.abc_switch_thumb_material) {
                return PorterDuff.Mode.MULTIPLY;
            }
            return null;
        }

        public final boolean f(int[] iArr, int i) {
            for (int i2 : iArr) {
                if (i2 == i) {
                    return true;
                }
            }
            return false;
        }

        public final ColorStateList g(Context context) {
            return h(context, 0);
        }

        public final ColorStateList h(Context context, int i) {
            int iC = b3.c(context, R$attr.colorControlHighlight);
            return new ColorStateList(new int[][]{b3.b, b3.e, b3.c, b3.i}, new int[]{b3.b(context, R$attr.colorButtonNormal), n7.b(iC, i), n7.b(iC, i), i});
        }

        public final ColorStateList i(Context context) {
            return h(context, b3.c(context, R$attr.colorAccent));
        }

        public final ColorStateList j(Context context) {
            return h(context, b3.c(context, R$attr.colorButtonNormal));
        }

        public final ColorStateList k(Context context) {
            int[][] iArr = new int[3][];
            int[] iArr2 = new int[3];
            int i = R$attr.colorSwitchThumbNormal;
            ColorStateList colorStateListE = b3.e(context, i);
            if (colorStateListE == null || !colorStateListE.isStateful()) {
                iArr[0] = b3.b;
                iArr2[0] = b3.b(context, i);
                iArr[1] = b3.f;
                iArr2[1] = b3.c(context, R$attr.colorControlActivated);
                iArr[2] = b3.i;
                iArr2[2] = b3.c(context, i);
            } else {
                iArr[0] = b3.b;
                iArr2[0] = colorStateListE.getColorForState(iArr[0], 0);
                iArr[1] = b3.f;
                iArr2[1] = b3.c(context, R$attr.colorControlActivated);
                iArr[2] = b3.i;
                iArr2[2] = colorStateListE.getDefaultColor();
            }
            return new ColorStateList(iArr, iArr2);
        }

        public final void l(Drawable drawable, int i, PorterDuff.Mode mode) {
            if (s2.a(drawable)) {
                drawable = drawable.mutate();
            }
            if (mode == null) {
                mode = h2.a;
            }
            drawable.setColorFilter(h2.e(i, mode));
        }
    }

    public static synchronized h2 b() {
        if (b == null) {
            h();
        }
        return b;
    }

    public static synchronized PorterDuffColorFilter e(int i, PorterDuff.Mode mode) {
        return x2.l(i, mode);
    }

    public static synchronized void h() {
        if (b == null) {
            h2 h2Var = new h2();
            b = h2Var;
            h2Var.c = x2.h();
            b.c.u(new a());
        }
    }

    public static void i(Drawable drawable, e3 e3Var, int[] iArr) {
        x2.w(drawable, e3Var, iArr);
    }

    public synchronized Drawable c(Context context, int i) {
        return this.c.j(context, i);
    }

    public synchronized Drawable d(Context context, int i, boolean z) {
        return this.c.k(context, i, z);
    }

    public synchronized ColorStateList f(Context context, int i) {
        return this.c.m(context, i);
    }

    public synchronized void g(Context context) {
        this.c.s(context);
    }
}
