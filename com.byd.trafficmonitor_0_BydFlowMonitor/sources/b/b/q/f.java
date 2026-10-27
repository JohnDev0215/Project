package b.b.q;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import b.b.q.v;

/* JADX INFO: compiled from: AppCompatDrawableManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final PorterDuff.Mode f1724a = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static f f1725b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public v f1726c;

    /* JADX INFO: compiled from: AppCompatDrawableManager.java */
    public class a implements v.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int[] f1727a = {b.b.e.abc_textfield_search_default_mtrl_alpha, b.b.e.abc_textfield_default_mtrl_alpha, b.b.e.abc_ab_share_pack_mtrl_alpha};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int[] f1728b = {b.b.e.abc_ic_commit_search_api_mtrl_alpha, b.b.e.abc_seekbar_tick_mark_material, b.b.e.abc_ic_menu_share_mtrl_alpha, b.b.e.abc_ic_menu_copy_mtrl_am_alpha, b.b.e.abc_ic_menu_cut_mtrl_alpha, b.b.e.abc_ic_menu_selectall_mtrl_alpha, b.b.e.abc_ic_menu_paste_mtrl_am_alpha};

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int[] f1729c = {b.b.e.abc_textfield_activated_mtrl_alpha, b.b.e.abc_textfield_search_activated_mtrl_alpha, b.b.e.abc_cab_background_top_mtrl_alpha, b.b.e.abc_text_cursor_material, b.b.e.abc_text_select_handle_left_mtrl_dark, b.b.e.abc_text_select_handle_middle_mtrl_dark, b.b.e.abc_text_select_handle_right_mtrl_dark, b.b.e.abc_text_select_handle_left_mtrl_light, b.b.e.abc_text_select_handle_middle_mtrl_light, b.b.e.abc_text_select_handle_right_mtrl_light};

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int[] f1730d = {b.b.e.abc_popup_background_mtrl_mult, b.b.e.abc_cab_background_internal_bg, b.b.e.abc_menu_hardkey_panel_mtrl_mult};

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int[] f1731e = {b.b.e.abc_tab_indicator_material, b.b.e.abc_textfield_search_material};

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final int[] f1732f = {b.b.e.abc_btn_check_material, b.b.e.abc_btn_radio_material, b.b.e.abc_btn_check_material_anim, b.b.e.abc_btn_radio_material_anim};

        @Override // b.b.q.v.e
        public Drawable a(v vVar, Context context, int i2) {
            if (i2 == b.b.e.abc_cab_background_top_material) {
                return new LayerDrawable(new Drawable[]{vVar.j(context, b.b.e.abc_cab_background_internal_bg), vVar.j(context, b.b.e.abc_cab_background_top_mtrl_alpha)});
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0046  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0061 A[RETURN] */
        @Override // b.b.q.v.e
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean b(android.content.Context r7, int r8, android.graphics.drawable.Drawable r9) {
            /*
                r6 = this;
                android.graphics.PorterDuff$Mode r0 = b.b.q.f.a()
                int[] r1 = r6.f1727a
                boolean r1 = r6.f(r1, r8)
                r2 = 16842801(0x1010031, float:2.3693695E-38)
                r3 = -1
                r4 = 0
                r5 = 1
                if (r1 == 0) goto L17
                int r2 = b.b.a.colorControlNormal
            L14:
                r8 = r3
            L15:
                r1 = r5
                goto L44
            L17:
                int[] r1 = r6.f1729c
                boolean r1 = r6.f(r1, r8)
                if (r1 == 0) goto L22
                int r2 = b.b.a.colorControlActivated
                goto L14
            L22:
                int[] r1 = r6.f1730d
                boolean r1 = r6.f(r1, r8)
                if (r1 == 0) goto L2d
                android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
                goto L14
            L2d:
                int r1 = b.b.e.abc_list_divider_mtrl_alpha
                if (r8 != r1) goto L3c
                r2 = 16842800(0x1010030, float:2.3693693E-38)
                r8 = 1109603123(0x42233333, float:40.8)
                int r8 = java.lang.Math.round(r8)
                goto L15
            L3c:
                int r1 = b.b.e.abc_dialog_material_background
                if (r8 != r1) goto L41
                goto L14
            L41:
                r8 = r3
                r1 = r4
                r2 = r1
            L44:
                if (r1 == 0) goto L61
                boolean r1 = b.b.q.q.a(r9)
                if (r1 == 0) goto L50
                android.graphics.drawable.Drawable r9 = r9.mutate()
            L50:
                int r7 = b.b.q.z.c(r7, r2)
                android.graphics.PorterDuffColorFilter r7 = b.b.q.f.e(r7, r0)
                r9.setColorFilter(r7)
                if (r8 == r3) goto L60
                r9.setAlpha(r8)
            L60:
                return r5
            L61:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: b.b.q.f.a.b(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
        }

        @Override // b.b.q.v.e
        public ColorStateList c(Context context, int i2) {
            if (i2 == b.b.e.abc_edit_text_material) {
                return b.b.l.a.a.c(context, b.b.c.abc_tint_edittext);
            }
            if (i2 == b.b.e.abc_switch_track_mtrl_alpha) {
                return b.b.l.a.a.c(context, b.b.c.abc_tint_switch_track);
            }
            if (i2 == b.b.e.abc_switch_thumb_material) {
                return k(context);
            }
            if (i2 == b.b.e.abc_btn_default_mtrl_shape) {
                return j(context);
            }
            if (i2 == b.b.e.abc_btn_borderless_material) {
                return g(context);
            }
            if (i2 == b.b.e.abc_btn_colored_material) {
                return i(context);
            }
            if (i2 == b.b.e.abc_spinner_mtrl_am_alpha || i2 == b.b.e.abc_spinner_textfield_background_material) {
                return b.b.l.a.a.c(context, b.b.c.abc_tint_spinner);
            }
            if (f(this.f1728b, i2)) {
                return z.e(context, b.b.a.colorControlNormal);
            }
            if (f(this.f1731e, i2)) {
                return b.b.l.a.a.c(context, b.b.c.abc_tint_default);
            }
            if (f(this.f1732f, i2)) {
                return b.b.l.a.a.c(context, b.b.c.abc_tint_btn_checkable);
            }
            if (i2 == b.b.e.abc_seekbar_thumb_material) {
                return b.b.l.a.a.c(context, b.b.c.abc_tint_seek_thumb);
            }
            return null;
        }

        @Override // b.b.q.v.e
        public boolean d(Context context, int i2, Drawable drawable) {
            if (i2 == b.b.e.abc_seekbar_track_material) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                Drawable drawableFindDrawableByLayerId = layerDrawable.findDrawableByLayerId(R.id.background);
                int i3 = b.b.a.colorControlNormal;
                l(drawableFindDrawableByLayerId, z.c(context, i3), f.f1724a);
                l(layerDrawable.findDrawableByLayerId(R.id.secondaryProgress), z.c(context, i3), f.f1724a);
                l(layerDrawable.findDrawableByLayerId(R.id.progress), z.c(context, b.b.a.colorControlActivated), f.f1724a);
                return true;
            }
            if (i2 != b.b.e.abc_ratingbar_material && i2 != b.b.e.abc_ratingbar_indicator_material && i2 != b.b.e.abc_ratingbar_small_material) {
                return false;
            }
            LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
            l(layerDrawable2.findDrawableByLayerId(R.id.background), z.b(context, b.b.a.colorControlNormal), f.f1724a);
            Drawable drawableFindDrawableByLayerId2 = layerDrawable2.findDrawableByLayerId(R.id.secondaryProgress);
            int i4 = b.b.a.colorControlActivated;
            l(drawableFindDrawableByLayerId2, z.c(context, i4), f.f1724a);
            l(layerDrawable2.findDrawableByLayerId(R.id.progress), z.c(context, i4), f.f1724a);
            return true;
        }

        @Override // b.b.q.v.e
        public PorterDuff.Mode e(int i2) {
            if (i2 == b.b.e.abc_switch_thumb_material) {
                return PorterDuff.Mode.MULTIPLY;
            }
            return null;
        }

        public final boolean f(int[] iArr, int i2) {
            for (int i3 : iArr) {
                if (i3 == i2) {
                    return true;
                }
            }
            return false;
        }

        public final ColorStateList g(Context context) {
            return h(context, 0);
        }

        public final ColorStateList h(Context context, int i2) {
            int iC = z.c(context, b.b.a.colorControlHighlight);
            return new ColorStateList(new int[][]{z.f1854b, z.f1857e, z.f1855c, z.f1861i}, new int[]{z.b(context, b.b.a.colorButtonNormal), b.h.f.a.b(iC, i2), b.h.f.a.b(iC, i2), i2});
        }

        public final ColorStateList i(Context context) {
            return h(context, z.c(context, b.b.a.colorAccent));
        }

        public final ColorStateList j(Context context) {
            return h(context, z.c(context, b.b.a.colorButtonNormal));
        }

        public final ColorStateList k(Context context) {
            int[][] iArr = new int[3][];
            int[] iArr2 = new int[3];
            int i2 = b.b.a.colorSwitchThumbNormal;
            ColorStateList colorStateListE = z.e(context, i2);
            if (colorStateListE == null || !colorStateListE.isStateful()) {
                iArr[0] = z.f1854b;
                iArr2[0] = z.b(context, i2);
                iArr[1] = z.f1858f;
                iArr2[1] = z.c(context, b.b.a.colorControlActivated);
                iArr[2] = z.f1861i;
                iArr2[2] = z.c(context, i2);
            } else {
                iArr[0] = z.f1854b;
                iArr2[0] = colorStateListE.getColorForState(iArr[0], 0);
                iArr[1] = z.f1858f;
                iArr2[1] = z.c(context, b.b.a.colorControlActivated);
                iArr[2] = z.f1861i;
                iArr2[2] = colorStateListE.getDefaultColor();
            }
            return new ColorStateList(iArr, iArr2);
        }

        public final void l(Drawable drawable, int i2, PorterDuff.Mode mode) {
            if (q.a(drawable)) {
                drawable = drawable.mutate();
            }
            if (mode == null) {
                mode = f.f1724a;
            }
            drawable.setColorFilter(f.e(i2, mode));
        }
    }

    public static synchronized f b() {
        if (f1725b == null) {
            h();
        }
        return f1725b;
    }

    public static synchronized PorterDuffColorFilter e(int i2, PorterDuff.Mode mode) {
        return v.l(i2, mode);
    }

    public static synchronized void h() {
        if (f1725b == null) {
            f fVar = new f();
            f1725b = fVar;
            fVar.f1726c = v.h();
            f1725b.f1726c.u(new a());
        }
    }

    public static void i(Drawable drawable, c0 c0Var, int[] iArr) {
        v.w(drawable, c0Var, iArr);
    }

    public synchronized Drawable c(Context context, int i2) {
        return this.f1726c.j(context, i2);
    }

    public synchronized Drawable d(Context context, int i2, boolean z) {
        return this.f1726c.k(context, i2, z);
    }

    public synchronized ColorStateList f(Context context, int i2) {
        return this.f1726c.m(context, i2);
    }

    public synchronized void g(Context context) {
        this.f1726c.s(context);
    }
}
