package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.appcompat.widget.w;

/* JADX INFO: compiled from: AppCompatDrawableManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a */
    private static final PorterDuff.Mode f608a = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b */
    private static f f609b;

    /* JADX INFO: renamed from: c */
    private w f610c;

    /* JADX INFO: compiled from: AppCompatDrawableManager.java */
    class a implements w.e {

        /* JADX INFO: renamed from: a */
        private final int[] f611a = {b.a.e.abc_textfield_search_default_mtrl_alpha, b.a.e.abc_textfield_default_mtrl_alpha, b.a.e.abc_ab_share_pack_mtrl_alpha};

        /* JADX INFO: renamed from: b */
        private final int[] f612b = {b.a.e.abc_ic_commit_search_api_mtrl_alpha, b.a.e.abc_seekbar_tick_mark_material, b.a.e.abc_ic_menu_share_mtrl_alpha, b.a.e.abc_ic_menu_copy_mtrl_am_alpha, b.a.e.abc_ic_menu_cut_mtrl_alpha, b.a.e.abc_ic_menu_selectall_mtrl_alpha, b.a.e.abc_ic_menu_paste_mtrl_am_alpha};

        /* JADX INFO: renamed from: c */
        private final int[] f613c = {b.a.e.abc_textfield_activated_mtrl_alpha, b.a.e.abc_textfield_search_activated_mtrl_alpha, b.a.e.abc_cab_background_top_mtrl_alpha, b.a.e.abc_text_cursor_material, b.a.e.abc_text_select_handle_left_mtrl_dark, b.a.e.abc_text_select_handle_middle_mtrl_dark, b.a.e.abc_text_select_handle_right_mtrl_dark, b.a.e.abc_text_select_handle_left_mtrl_light, b.a.e.abc_text_select_handle_middle_mtrl_light, b.a.e.abc_text_select_handle_right_mtrl_light};

        /* JADX INFO: renamed from: d */
        private final int[] f614d = {b.a.e.abc_popup_background_mtrl_mult, b.a.e.abc_cab_background_internal_bg, b.a.e.abc_menu_hardkey_panel_mtrl_mult};

        /* JADX INFO: renamed from: e */
        private final int[] f615e = {b.a.e.abc_tab_indicator_material, b.a.e.abc_textfield_search_material};

        /* JADX INFO: renamed from: f */
        private final int[] f616f = {b.a.e.abc_btn_check_material, b.a.e.abc_btn_radio_material, b.a.e.abc_btn_check_material_anim, b.a.e.abc_btn_radio_material_anim};

        a() {
        }

        private boolean f(int[] iArr, int i2) {
            for (int i3 : iArr) {
                if (i3 == i2) {
                    return true;
                }
            }
            return false;
        }

        private ColorStateList g(Context context) {
            return h(context, 0);
        }

        private ColorStateList h(Context context, int i2) {
            int iC = a0.c(context, b.a.a.colorControlHighlight);
            return new ColorStateList(new int[][]{a0.f578b, a0.f581e, a0.f579c, a0.f585i}, new int[]{a0.b(context, b.a.a.colorButtonNormal), b.g.d.a.b(iC, i2), b.g.d.a.b(iC, i2), i2});
        }

        private ColorStateList i(Context context) {
            return h(context, a0.c(context, b.a.a.colorAccent));
        }

        private ColorStateList j(Context context) {
            return h(context, a0.c(context, b.a.a.colorButtonNormal));
        }

        private ColorStateList k(Context context) {
            int[][] iArr = new int[3][];
            int[] iArr2 = new int[3];
            int i2 = b.a.a.colorSwitchThumbNormal;
            ColorStateList colorStateListE = a0.e(context, i2);
            if (colorStateListE == null || !colorStateListE.isStateful()) {
                iArr[0] = a0.f578b;
                iArr2[0] = a0.b(context, i2);
                iArr[1] = a0.f582f;
                iArr2[1] = a0.c(context, b.a.a.colorControlActivated);
                iArr[2] = a0.f585i;
                iArr2[2] = a0.c(context, i2);
            } else {
                iArr[0] = a0.f578b;
                iArr2[0] = colorStateListE.getColorForState(iArr[0], 0);
                iArr[1] = a0.f582f;
                iArr2[1] = a0.c(context, b.a.a.colorControlActivated);
                iArr[2] = a0.f585i;
                iArr2[2] = colorStateListE.getDefaultColor();
            }
            return new ColorStateList(iArr, iArr2);
        }

        private void l(Drawable drawable, int i2, PorterDuff.Mode mode) {
            if (q.a(drawable)) {
                drawable = drawable.mutate();
            }
            if (mode == null) {
                mode = f.f608a;
            }
            drawable.setColorFilter(f.e(i2, mode));
        }

        @Override // androidx.appcompat.widget.w.e
        public Drawable a(w wVar, Context context, int i2) {
            if (i2 == b.a.e.abc_cab_background_top_material) {
                return new LayerDrawable(new Drawable[]{wVar.j(context, b.a.e.abc_cab_background_internal_bg), wVar.j(context, b.a.e.abc_cab_background_top_mtrl_alpha)});
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:50:0x0046  */
        /* JADX WARN: Removed duplicated region for block: B:57:0x0061 A[RETURN] */
        @Override // androidx.appcompat.widget.w.e
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean b(android.content.Context r7, int r8, android.graphics.drawable.Drawable r9) {
            /*
                r6 = this;
                android.graphics.PorterDuff$Mode r0 = androidx.appcompat.widget.f.a()
                int[] r1 = r6.f611a
                boolean r1 = r6.f(r1, r8)
                r2 = 16842801(0x1010031, float:2.3693695E-38)
                r3 = -1
                r4 = 0
                r5 = 1
                if (r1 == 0) goto L17
                int r2 = b.a.a.colorControlNormal
            L14:
                r8 = r3
            L15:
                r1 = r5
                goto L44
            L17:
                int[] r1 = r6.f613c
                boolean r1 = r6.f(r1, r8)
                if (r1 == 0) goto L22
                int r2 = b.a.a.colorControlActivated
                goto L14
            L22:
                int[] r1 = r6.f614d
                boolean r1 = r6.f(r1, r8)
                if (r1 == 0) goto L2d
                android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
                goto L14
            L2d:
                int r1 = b.a.e.abc_list_divider_mtrl_alpha
                if (r8 != r1) goto L3c
                r2 = 16842800(0x1010030, float:2.3693693E-38)
                r8 = 1109603123(0x42233333, float:40.8)
                int r8 = java.lang.Math.round(r8)
                goto L15
            L3c:
                int r1 = b.a.e.abc_dialog_material_background
                if (r8 != r1) goto L41
                goto L14
            L41:
                r8 = r3
                r1 = r4
                r2 = r1
            L44:
                if (r1 == 0) goto L61
                boolean r1 = androidx.appcompat.widget.q.a(r9)
                if (r1 == 0) goto L50
                android.graphics.drawable.Drawable r9 = r9.mutate()
            L50:
                int r7 = androidx.appcompat.widget.a0.c(r7, r2)
                android.graphics.PorterDuffColorFilter r7 = androidx.appcompat.widget.f.e(r7, r0)
                r9.setColorFilter(r7)
                if (r8 == r3) goto L60
                r9.setAlpha(r8)
            L60:
                return r5
            L61:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.f.a.b(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
        }

        @Override // androidx.appcompat.widget.w.e
        public ColorStateList c(Context context, int i2) {
            if (i2 == b.a.e.abc_edit_text_material) {
                return b.a.k.a.a.c(context, b.a.c.abc_tint_edittext);
            }
            if (i2 == b.a.e.abc_switch_track_mtrl_alpha) {
                return b.a.k.a.a.c(context, b.a.c.abc_tint_switch_track);
            }
            if (i2 == b.a.e.abc_switch_thumb_material) {
                return k(context);
            }
            if (i2 == b.a.e.abc_btn_default_mtrl_shape) {
                return j(context);
            }
            if (i2 == b.a.e.abc_btn_borderless_material) {
                return g(context);
            }
            if (i2 == b.a.e.abc_btn_colored_material) {
                return i(context);
            }
            if (i2 == b.a.e.abc_spinner_mtrl_am_alpha || i2 == b.a.e.abc_spinner_textfield_background_material) {
                return b.a.k.a.a.c(context, b.a.c.abc_tint_spinner);
            }
            if (f(this.f612b, i2)) {
                return a0.e(context, b.a.a.colorControlNormal);
            }
            if (f(this.f615e, i2)) {
                return b.a.k.a.a.c(context, b.a.c.abc_tint_default);
            }
            if (f(this.f616f, i2)) {
                return b.a.k.a.a.c(context, b.a.c.abc_tint_btn_checkable);
            }
            if (i2 == b.a.e.abc_seekbar_thumb_material) {
                return b.a.k.a.a.c(context, b.a.c.abc_tint_seek_thumb);
            }
            return null;
        }

        @Override // androidx.appcompat.widget.w.e
        public boolean d(Context context, int i2, Drawable drawable) {
            if (i2 == b.a.e.abc_seekbar_track_material) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                Drawable drawableFindDrawableByLayerId = layerDrawable.findDrawableByLayerId(R.id.background);
                int i3 = b.a.a.colorControlNormal;
                l(drawableFindDrawableByLayerId, a0.c(context, i3), f.f608a);
                l(layerDrawable.findDrawableByLayerId(R.id.secondaryProgress), a0.c(context, i3), f.f608a);
                l(layerDrawable.findDrawableByLayerId(R.id.progress), a0.c(context, b.a.a.colorControlActivated), f.f608a);
                return true;
            }
            if (i2 != b.a.e.abc_ratingbar_material && i2 != b.a.e.abc_ratingbar_indicator_material && i2 != b.a.e.abc_ratingbar_small_material) {
                return false;
            }
            LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
            l(layerDrawable2.findDrawableByLayerId(R.id.background), a0.b(context, b.a.a.colorControlNormal), f.f608a);
            Drawable drawableFindDrawableByLayerId2 = layerDrawable2.findDrawableByLayerId(R.id.secondaryProgress);
            int i4 = b.a.a.colorControlActivated;
            l(drawableFindDrawableByLayerId2, a0.c(context, i4), f.f608a);
            l(layerDrawable2.findDrawableByLayerId(R.id.progress), a0.c(context, i4), f.f608a);
            return true;
        }

        @Override // androidx.appcompat.widget.w.e
        public PorterDuff.Mode e(int i2) {
            if (i2 == b.a.e.abc_switch_thumb_material) {
                return PorterDuff.Mode.MULTIPLY;
            }
            return null;
        }
    }

    public static synchronized f b() {
        if (f609b == null) {
            h();
        }
        return f609b;
    }

    public static synchronized PorterDuffColorFilter e(int i2, PorterDuff.Mode mode) {
        return w.l(i2, mode);
    }

    public static synchronized void h() {
        if (f609b == null) {
            f fVar = new f();
            f609b = fVar;
            fVar.f610c = w.h();
            f609b.f610c.u(new a());
        }
    }

    static void i(Drawable drawable, d0 d0Var, int[] iArr) {
        w.w(drawable, d0Var, iArr);
    }

    public synchronized Drawable c(Context context, int i2) {
        return this.f610c.j(context, i2);
    }

    synchronized Drawable d(Context context, int i2, boolean z) {
        return this.f610c.k(context, i2, z);
    }

    synchronized ColorStateList f(Context context, int i2) {
        return this.f610c.m(context, i2);
    }

    public synchronized void g(Context context) {
        this.f610c.s(context);
    }
}
