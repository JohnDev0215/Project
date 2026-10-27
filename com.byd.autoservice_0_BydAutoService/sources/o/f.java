package o;

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
import o.v;

/* JADX INFO: compiled from: AppCompatDrawableManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final PorterDuff.Mode f4255a = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static f f4256b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public v f4257c;

    /* JADX INFO: compiled from: AppCompatDrawableManager.java */
    public class a implements v.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int[] f4258a = {R$drawable.abc_textfield_search_default_mtrl_alpha, R$drawable.abc_textfield_default_mtrl_alpha, R$drawable.abc_ab_share_pack_mtrl_alpha};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int[] f4259b = {R$drawable.abc_ic_commit_search_api_mtrl_alpha, R$drawable.abc_seekbar_tick_mark_material, R$drawable.abc_ic_menu_share_mtrl_alpha, R$drawable.abc_ic_menu_copy_mtrl_am_alpha, R$drawable.abc_ic_menu_cut_mtrl_alpha, R$drawable.abc_ic_menu_selectall_mtrl_alpha, R$drawable.abc_ic_menu_paste_mtrl_am_alpha};

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int[] f4260c = {R$drawable.abc_textfield_activated_mtrl_alpha, R$drawable.abc_textfield_search_activated_mtrl_alpha, R$drawable.abc_cab_background_top_mtrl_alpha, R$drawable.abc_text_cursor_material, R$drawable.abc_text_select_handle_left_mtrl_dark, R$drawable.abc_text_select_handle_middle_mtrl_dark, R$drawable.abc_text_select_handle_right_mtrl_dark, R$drawable.abc_text_select_handle_left_mtrl_light, R$drawable.abc_text_select_handle_middle_mtrl_light, R$drawable.abc_text_select_handle_right_mtrl_light};

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int[] f4261d = {R$drawable.abc_popup_background_mtrl_mult, R$drawable.abc_cab_background_internal_bg, R$drawable.abc_menu_hardkey_panel_mtrl_mult};

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int[] f4262e = {R$drawable.abc_tab_indicator_material, R$drawable.abc_textfield_search_material};

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final int[] f4263f = {R$drawable.abc_btn_check_material, R$drawable.abc_btn_radio_material, R$drawable.abc_btn_check_material_anim, R$drawable.abc_btn_radio_material_anim};

        @Override // o.v.e
        public Drawable a(v vVar, Context context, int i3) {
            if (i3 == R$drawable.abc_cab_background_top_material) {
                return new LayerDrawable(new Drawable[]{vVar.j(context, R$drawable.abc_cab_background_internal_bg), vVar.j(context, R$drawable.abc_cab_background_top_mtrl_alpha)});
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0046  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0061 A[RETURN] */
        @Override // o.v.e
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean b(android.content.Context r7, int r8, android.graphics.drawable.Drawable r9) {
            /*
                r6 = this;
                android.graphics.PorterDuff$Mode r0 = o.f.a()
                int[] r1 = r6.f4258a
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
                int[] r1 = r6.f4260c
                boolean r1 = r6.f(r1, r8)
                if (r1 == 0) goto L22
                int r2 = androidx.appcompat.R$attr.colorControlActivated
                goto L14
            L22:
                int[] r1 = r6.f4261d
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
                boolean r1 = o.q.a(r9)
                if (r1 == 0) goto L50
                android.graphics.drawable.Drawable r9 = r9.mutate()
            L50:
                int r7 = o.z.c(r7, r2)
                android.graphics.PorterDuffColorFilter r7 = o.f.e(r7, r0)
                r9.setColorFilter(r7)
                if (r8 == r3) goto L60
                r9.setAlpha(r8)
            L60:
                return r5
            L61:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: o.f.a.b(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
        }

        @Override // o.v.e
        public ColorStateList c(Context context, int i3) {
            if (i3 == R$drawable.abc_edit_text_material) {
                return j.a.c(context, R$color.abc_tint_edittext);
            }
            if (i3 == R$drawable.abc_switch_track_mtrl_alpha) {
                return j.a.c(context, R$color.abc_tint_switch_track);
            }
            if (i3 == R$drawable.abc_switch_thumb_material) {
                return k(context);
            }
            if (i3 == R$drawable.abc_btn_default_mtrl_shape) {
                return j(context);
            }
            if (i3 == R$drawable.abc_btn_borderless_material) {
                return g(context);
            }
            if (i3 == R$drawable.abc_btn_colored_material) {
                return i(context);
            }
            if (i3 == R$drawable.abc_spinner_mtrl_am_alpha || i3 == R$drawable.abc_spinner_textfield_background_material) {
                return j.a.c(context, R$color.abc_tint_spinner);
            }
            if (f(this.f4259b, i3)) {
                return z.e(context, R$attr.colorControlNormal);
            }
            if (f(this.f4262e, i3)) {
                return j.a.c(context, R$color.abc_tint_default);
            }
            if (f(this.f4263f, i3)) {
                return j.a.c(context, R$color.abc_tint_btn_checkable);
            }
            if (i3 == R$drawable.abc_seekbar_thumb_material) {
                return j.a.c(context, R$color.abc_tint_seek_thumb);
            }
            return null;
        }

        @Override // o.v.e
        public boolean d(Context context, int i3, Drawable drawable) {
            if (i3 == R$drawable.abc_seekbar_track_material) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                Drawable drawableFindDrawableByLayerId = layerDrawable.findDrawableByLayerId(R.id.background);
                int i4 = R$attr.colorControlNormal;
                l(drawableFindDrawableByLayerId, z.c(context, i4), f.f4255a);
                l(layerDrawable.findDrawableByLayerId(R.id.secondaryProgress), z.c(context, i4), f.f4255a);
                l(layerDrawable.findDrawableByLayerId(R.id.progress), z.c(context, R$attr.colorControlActivated), f.f4255a);
                return true;
            }
            if (i3 != R$drawable.abc_ratingbar_material && i3 != R$drawable.abc_ratingbar_indicator_material && i3 != R$drawable.abc_ratingbar_small_material) {
                return false;
            }
            LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
            l(layerDrawable2.findDrawableByLayerId(R.id.background), z.b(context, R$attr.colorControlNormal), f.f4255a);
            Drawable drawableFindDrawableByLayerId2 = layerDrawable2.findDrawableByLayerId(R.id.secondaryProgress);
            int i5 = R$attr.colorControlActivated;
            l(drawableFindDrawableByLayerId2, z.c(context, i5), f.f4255a);
            l(layerDrawable2.findDrawableByLayerId(R.id.progress), z.c(context, i5), f.f4255a);
            return true;
        }

        @Override // o.v.e
        public PorterDuff.Mode e(int i3) {
            if (i3 == R$drawable.abc_switch_thumb_material) {
                return PorterDuff.Mode.MULTIPLY;
            }
            return null;
        }

        public final boolean f(int[] iArr, int i3) {
            for (int i4 : iArr) {
                if (i4 == i3) {
                    return true;
                }
            }
            return false;
        }

        public final ColorStateList g(Context context) {
            return h(context, 0);
        }

        public final ColorStateList h(Context context, int i3) {
            int iC = z.c(context, R$attr.colorControlHighlight);
            return new ColorStateList(new int[][]{z.f4427b, z.f4430e, z.f4428c, z.f4434i}, new int[]{z.b(context, R$attr.colorButtonNormal), d0.a.b(iC, i3), d0.a.b(iC, i3), i3});
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
            int i3 = R$attr.colorSwitchThumbNormal;
            ColorStateList colorStateListE = z.e(context, i3);
            if (colorStateListE == null || !colorStateListE.isStateful()) {
                iArr[0] = z.f4427b;
                iArr2[0] = z.b(context, i3);
                iArr[1] = z.f4431f;
                iArr2[1] = z.c(context, R$attr.colorControlActivated);
                iArr[2] = z.f4434i;
                iArr2[2] = z.c(context, i3);
            } else {
                iArr[0] = z.f4427b;
                iArr2[0] = colorStateListE.getColorForState(iArr[0], 0);
                iArr[1] = z.f4431f;
                iArr2[1] = z.c(context, R$attr.colorControlActivated);
                iArr[2] = z.f4434i;
                iArr2[2] = colorStateListE.getDefaultColor();
            }
            return new ColorStateList(iArr, iArr2);
        }

        public final void l(Drawable drawable, int i3, PorterDuff.Mode mode) {
            if (q.a(drawable)) {
                drawable = drawable.mutate();
            }
            if (mode == null) {
                mode = f.f4255a;
            }
            drawable.setColorFilter(f.e(i3, mode));
        }
    }

    public static synchronized f b() {
        if (f4256b == null) {
            h();
        }
        return f4256b;
    }

    public static synchronized PorterDuffColorFilter e(int i3, PorterDuff.Mode mode) {
        return v.l(i3, mode);
    }

    public static synchronized void h() {
        if (f4256b == null) {
            f fVar = new f();
            f4256b = fVar;
            fVar.f4257c = v.h();
            f4256b.f4257c.u(new a());
        }
    }

    public static void i(Drawable drawable, c0 c0Var, int[] iArr) {
        v.w(drawable, c0Var, iArr);
    }

    public synchronized Drawable c(Context context, int i3) {
        return this.f4257c.j(context, i3);
    }

    public synchronized Drawable d(Context context, int i3, boolean z3) {
        return this.f4257c.k(context, i3, z3);
    }

    public synchronized ColorStateList f(Context context, int i3) {
        return this.f4257c.m(context, i3);
    }

    public synchronized void g(Context context) {
        this.f4257c.s(context);
    }
}
