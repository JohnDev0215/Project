package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$color;
import androidx.appcompat.R$drawable;
import androidx.appcompat.widget.J;

/* JADX INFO: renamed from: androidx.appcompat.widget.i, reason: case insensitive filesystem */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public final class C0063i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final PorterDuff.Mode f364b = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static C0063i f365c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private J f366a;

    /* JADX INFO: renamed from: androidx.appcompat.widget.i$a */
    /* JADX INFO: compiled from: caradapter-bug */
    class a implements J.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int[] f367a = {R$drawable.abc_textfield_search_default_mtrl_alpha, R$drawable.abc_textfield_default_mtrl_alpha, R$drawable.abc_ab_share_pack_mtrl_alpha};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int[] f368b = {R$drawable.abc_ic_commit_search_api_mtrl_alpha, R$drawable.abc_seekbar_tick_mark_material, R$drawable.abc_ic_menu_share_mtrl_alpha, R$drawable.abc_ic_menu_copy_mtrl_am_alpha, R$drawable.abc_ic_menu_cut_mtrl_alpha, R$drawable.abc_ic_menu_selectall_mtrl_alpha, R$drawable.abc_ic_menu_paste_mtrl_am_alpha};

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int[] f369c = {R$drawable.abc_textfield_activated_mtrl_alpha, R$drawable.abc_textfield_search_activated_mtrl_alpha, R$drawable.abc_cab_background_top_mtrl_alpha, R$drawable.abc_text_cursor_material, R$drawable.abc_text_select_handle_left_mtrl_dark, R$drawable.abc_text_select_handle_middle_mtrl_dark, R$drawable.abc_text_select_handle_right_mtrl_dark, R$drawable.abc_text_select_handle_left_mtrl_light, R$drawable.abc_text_select_handle_middle_mtrl_light, R$drawable.abc_text_select_handle_right_mtrl_light};
        private final int[] d = {R$drawable.abc_popup_background_mtrl_mult, R$drawable.abc_cab_background_internal_bg, R$drawable.abc_menu_hardkey_panel_mtrl_mult};
        private final int[] e = {R$drawable.abc_tab_indicator_material, R$drawable.abc_textfield_search_material};
        private final int[] f = {R$drawable.abc_btn_check_material, R$drawable.abc_btn_radio_material, R$drawable.abc_btn_check_material_anim, R$drawable.abc_btn_radio_material_anim};

        a() {
        }

        private boolean f(int[] iArr, int i) {
            for (int i2 : iArr) {
                if (i2 == i) {
                    return true;
                }
            }
            return false;
        }

        private ColorStateList g(@NonNull Context context) {
            return h(context, 0);
        }

        private ColorStateList h(@NonNull Context context, @ColorInt int i) {
            int iC = O.c(context, R$attr.colorControlHighlight);
            return new ColorStateList(new int[][]{O.f296b, O.d, O.f297c, O.f}, new int[]{O.b(context, R$attr.colorButtonNormal), androidx.core.graphics.a.f(iC, i), androidx.core.graphics.a.f(iC, i), i});
        }

        private ColorStateList i(@NonNull Context context) {
            return h(context, O.c(context, R$attr.colorAccent));
        }

        private ColorStateList j(@NonNull Context context) {
            return h(context, O.c(context, R$attr.colorButtonNormal));
        }

        private ColorStateList k(Context context) {
            int[][] iArr = new int[3][];
            int[] iArr2 = new int[3];
            ColorStateList colorStateListE = O.e(context, R$attr.colorSwitchThumbNormal);
            if (colorStateListE == null || !colorStateListE.isStateful()) {
                iArr[0] = O.f296b;
                iArr2[0] = O.b(context, R$attr.colorSwitchThumbNormal);
                iArr[1] = O.e;
                iArr2[1] = O.c(context, R$attr.colorControlActivated);
                iArr[2] = O.f;
                iArr2[2] = O.c(context, R$attr.colorSwitchThumbNormal);
            } else {
                iArr[0] = O.f296b;
                iArr2[0] = colorStateListE.getColorForState(iArr[0], 0);
                iArr[1] = O.e;
                iArr2[1] = O.c(context, R$attr.colorControlActivated);
                iArr[2] = O.f;
                iArr2[2] = colorStateListE.getDefaultColor();
            }
            return new ColorStateList(iArr, iArr2);
        }

        private void l(Drawable drawable, int i, PorterDuff.Mode mode) {
            if (C.a(drawable)) {
                drawable = drawable.mutate();
            }
            if (mode == null) {
                mode = C0063i.f364b;
            }
            drawable.setColorFilter(C0063i.e(i, mode));
        }

        @Override // androidx.appcompat.widget.J.f
        public Drawable a(@NonNull J j, @NonNull Context context, int i) {
            if (i == R$drawable.abc_cab_background_top_material) {
                return new LayerDrawable(new Drawable[]{j.j(context, R$drawable.abc_cab_background_internal_bg), j.j(context, R$drawable.abc_cab_background_top_mtrl_alpha)});
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0046  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0061 A[RETURN] */
        @Override // androidx.appcompat.widget.J.f
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean b(@androidx.annotation.NonNull android.content.Context r7, int r8, @androidx.annotation.NonNull android.graphics.drawable.Drawable r9) {
            /*
                r6 = this;
                android.graphics.PorterDuff$Mode r0 = androidx.appcompat.widget.C0063i.a()
                int[] r1 = r6.f367a
                boolean r1 = r6.f(r1, r8)
                r2 = 16842801(0x1010031, float:2.3693695E-38)
                r3 = -1
                r4 = 0
                r5 = 1
                if (r1 == 0) goto L17
                int r2 = androidx.appcompat.R$attr.colorControlNormal
            L14:
                r6 = r3
            L15:
                r8 = r5
                goto L44
            L17:
                int[] r1 = r6.f369c
                boolean r1 = r6.f(r1, r8)
                if (r1 == 0) goto L22
                int r2 = androidx.appcompat.R$attr.colorControlActivated
                goto L14
            L22:
                int[] r1 = r6.d
                boolean r6 = r6.f(r1, r8)
                if (r6 == 0) goto L2d
                android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
                goto L14
            L2d:
                int r6 = androidx.appcompat.R$drawable.abc_list_divider_mtrl_alpha
                if (r8 != r6) goto L3c
                r2 = 16842800(0x1010030, float:2.3693693E-38)
                r6 = 1109603123(0x42233333, float:40.8)
                int r6 = java.lang.Math.round(r6)
                goto L15
            L3c:
                int r6 = androidx.appcompat.R$drawable.abc_dialog_material_background
                if (r8 != r6) goto L41
                goto L14
            L41:
                r6 = r3
                r8 = r4
                r2 = r8
            L44:
                if (r8 == 0) goto L61
                boolean r8 = androidx.appcompat.widget.C.a(r9)
                if (r8 == 0) goto L50
                android.graphics.drawable.Drawable r9 = r9.mutate()
            L50:
                int r7 = androidx.appcompat.widget.O.c(r7, r2)
                android.graphics.PorterDuffColorFilter r7 = androidx.appcompat.widget.C0063i.e(r7, r0)
                r9.setColorFilter(r7)
                if (r6 == r3) goto L60
                r9.setAlpha(r6)
            L60:
                return r5
            L61:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.C0063i.a.b(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
        }

        @Override // androidx.appcompat.widget.J.f
        public ColorStateList c(@NonNull Context context, int i) {
            if (i == R$drawable.abc_edit_text_material) {
                return androidx.appcompat.a.a.a.a(context, R$color.abc_tint_edittext);
            }
            if (i == R$drawable.abc_switch_track_mtrl_alpha) {
                return androidx.appcompat.a.a.a.a(context, R$color.abc_tint_switch_track);
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
                return androidx.appcompat.a.a.a.a(context, R$color.abc_tint_spinner);
            }
            if (f(this.f368b, i)) {
                return O.e(context, R$attr.colorControlNormal);
            }
            if (f(this.e, i)) {
                return androidx.appcompat.a.a.a.a(context, R$color.abc_tint_default);
            }
            if (f(this.f, i)) {
                return androidx.appcompat.a.a.a.a(context, R$color.abc_tint_btn_checkable);
            }
            if (i == R$drawable.abc_seekbar_thumb_material) {
                return androidx.appcompat.a.a.a.a(context, R$color.abc_tint_seek_thumb);
            }
            return null;
        }

        @Override // androidx.appcompat.widget.J.f
        public boolean d(@NonNull Context context, int i, @NonNull Drawable drawable) {
            if (i == R$drawable.abc_seekbar_track_material) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                l(layerDrawable.findDrawableByLayerId(R.id.background), O.c(context, R$attr.colorControlNormal), C0063i.f364b);
                l(layerDrawable.findDrawableByLayerId(R.id.secondaryProgress), O.c(context, R$attr.colorControlNormal), C0063i.f364b);
                l(layerDrawable.findDrawableByLayerId(R.id.progress), O.c(context, R$attr.colorControlActivated), C0063i.f364b);
                return true;
            }
            if (i != R$drawable.abc_ratingbar_material && i != R$drawable.abc_ratingbar_indicator_material && i != R$drawable.abc_ratingbar_small_material) {
                return false;
            }
            LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
            l(layerDrawable2.findDrawableByLayerId(R.id.background), O.b(context, R$attr.colorControlNormal), C0063i.f364b);
            l(layerDrawable2.findDrawableByLayerId(R.id.secondaryProgress), O.c(context, R$attr.colorControlActivated), C0063i.f364b);
            l(layerDrawable2.findDrawableByLayerId(R.id.progress), O.c(context, R$attr.colorControlActivated), C0063i.f364b);
            return true;
        }

        @Override // androidx.appcompat.widget.J.f
        public PorterDuff.Mode e(int i) {
            if (i == R$drawable.abc_switch_thumb_material) {
                return PorterDuff.Mode.MULTIPLY;
            }
            return null;
        }
    }

    public static synchronized C0063i b() {
        if (f365c == null) {
            h();
        }
        return f365c;
    }

    public static synchronized PorterDuffColorFilter e(int i, PorterDuff.Mode mode) {
        return J.l(i, mode);
    }

    public static synchronized void h() {
        if (f365c == null) {
            C0063i c0063i = new C0063i();
            f365c = c0063i;
            c0063i.f366a = J.h();
            f365c.f366a.u(new a());
        }
    }

    static void i(Drawable drawable, S s, int[] iArr) {
        J.w(drawable, s, iArr);
    }

    public synchronized Drawable c(@NonNull Context context, @DrawableRes int i) {
        return this.f366a.j(context, i);
    }

    synchronized Drawable d(@NonNull Context context, @DrawableRes int i, boolean z) {
        return this.f366a.k(context, i, z);
    }

    synchronized ColorStateList f(@NonNull Context context, @DrawableRes int i) {
        return this.f366a.m(context, i);
    }

    public synchronized void g(@NonNull Context context) {
        this.f366a.s(context);
    }
}
