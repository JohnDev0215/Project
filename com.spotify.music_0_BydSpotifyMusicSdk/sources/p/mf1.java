package p;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.datepicker.MaterialCalendar;
import com.google.android.material.internal.CheckableImageButton;
import com.spotify.music.R;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class mf1<S> extends s90 {
    public CheckableImageButton A0;
    public qf1 B0;
    public Button C0;
    public final LinkedHashSet q0;
    public final LinkedHashSet r0;
    public int s0;
    public ws1 t0;
    public ip u0;
    public MaterialCalendar v0;
    public int w0;
    public CharSequence x0;
    public boolean y0;
    public int z0;

    public mf1() {
        new LinkedHashSet();
        new LinkedHashSet();
        this.q0 = new LinkedHashSet();
        this.r0 = new LinkedHashSet();
    }

    public static int I(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_content_padding);
        bl1 bl1Var = new bl1(bs2.b());
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.mtrl_calendar_day_width);
        int dimensionPixelOffset2 = resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_month_horizontal_padding);
        int i = bl1Var.l;
        return ((i - 1) * dimensionPixelOffset2) + (dimensionPixelSize * i) + (dimensionPixelOffset * 2);
    }

    public static boolean J(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(he1.O(R.attr.materialCalendarStyle, context, MaterialCalendar.class.getCanonicalName()), new int[]{i});
        boolean z = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        return z;
    }

    @Override // p.s90
    public final Dialog G() {
        Context contextB = B();
        B();
        int i = this.s0;
        if (i == 0) {
            H();
            throw null;
        }
        Dialog dialog = new Dialog(contextB, i);
        Context context = dialog.getContext();
        this.y0 = J(context, android.R.attr.windowFullscreen);
        int iO = he1.O(R.attr.colorSurface, context, mf1.class.getCanonicalName());
        qf1 qf1Var = new qf1(context, null, R.attr.materialCalendarStyle, R.style.Widget_MaterialComponents_MaterialCalendar);
        this.B0 = qf1Var;
        qf1Var.i(context);
        this.B0.k(ColorStateList.valueOf(iO));
        qf1 qf1Var2 = this.B0;
        View decorView = dialog.getWindow().getDecorView();
        WeakHashMap weakHashMap = zt2.a;
        qf1Var2.j(ot2.i(decorView));
        return dialog;
    }

    public final void H() {
        u4.t(this.n.getParcelable("DATE_SELECTOR_KEY"));
    }

    @Override // p.s90, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        Iterator it = this.q0.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnCancelListener) it.next()).onCancel(dialogInterface);
        }
    }

    @Override // p.s90, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        Iterator it = this.r0.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnDismissListener) it.next()).onDismiss(dialogInterface);
        }
        ViewGroup viewGroup = (ViewGroup) this.M;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        super.onDismiss(dialogInterface);
    }

    @Override // p.s90, p.ww0
    public final void r(Bundle bundle) {
        super.r(bundle);
        if (bundle == null) {
            bundle = this.n;
        }
        this.s0 = bundle.getInt("OVERRIDE_THEME_RES_ID");
        u4.t(bundle.getParcelable("DATE_SELECTOR_KEY"));
        this.u0 = (ip) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.w0 = bundle.getInt("TITLE_TEXT_RES_ID_KEY");
        this.x0 = bundle.getCharSequence("TITLE_TEXT_KEY");
        this.z0 = bundle.getInt("INPUT_MODE_KEY");
    }

    @Override // p.ww0
    public final View s(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(this.y0 ? R.layout.mtrl_picker_fullscreen : R.layout.mtrl_picker_dialog, viewGroup);
        Context context = viewInflate.getContext();
        if (this.y0) {
            viewInflate.findViewById(R.id.mtrl_calendar_frame).setLayoutParams(new LinearLayout.LayoutParams(I(context), -2));
        } else {
            viewInflate.findViewById(R.id.mtrl_calendar_main_pane).setLayoutParams(new LinearLayout.LayoutParams(I(context), -1));
        }
        TextView textView = (TextView) viewInflate.findViewById(R.id.mtrl_picker_header_selection_text);
        WeakHashMap weakHashMap = zt2.a;
        lt2.f(textView, 1);
        this.A0 = (CheckableImageButton) viewInflate.findViewById(R.id.mtrl_picker_header_toggle);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.mtrl_picker_title_text);
        CharSequence charSequence = this.x0;
        if (charSequence != null) {
            textView2.setText(charSequence);
        } else {
            textView2.setText(this.w0);
        }
        this.A0.setTag("TOGGLE_BUTTON_TAG");
        CheckableImageButton checkableImageButton = this.A0;
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_checked}, l13.f(context, R.drawable.material_ic_calendar_black_24dp));
        stateListDrawable.addState(new int[0], l13.f(context, R.drawable.material_ic_edit_black_24dp));
        checkableImageButton.setImageDrawable(stateListDrawable);
        this.A0.setChecked(this.z0 != 0);
        zt2.k(this.A0, null);
        CheckableImageButton checkableImageButton2 = this.A0;
        this.A0.setContentDescription(checkableImageButton2.isChecked() ? checkableImageButton2.getContext().getString(R.string.mtrl_picker_toggle_to_calendar_input_mode) : checkableImageButton2.getContext().getString(R.string.mtrl_picker_toggle_to_text_input_mode));
        this.A0.setOnClickListener(new lf1(this));
        this.C0 = (Button) viewInflate.findViewById(R.id.confirm_button);
        H();
        throw null;
    }

    @Override // p.s90, p.ww0
    public final void w(Bundle bundle) {
        super.w(bundle);
        bundle.putInt("OVERRIDE_THEME_RES_ID", this.s0);
        bundle.putParcelable("DATE_SELECTOR_KEY", null);
        ip ipVar = this.u0;
        gp gpVar = new gp();
        int i = gp.b;
        int i2 = gp.b;
        long j = ipVar.c.n;
        long j2 = ipVar.e.n;
        gpVar.a = Long.valueOf(ipVar.l.n);
        bl1 bl1Var = this.v0.e0;
        if (bl1Var != null) {
            gpVar.a = Long.valueOf(bl1Var.n);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("DEEP_COPY_VALIDATOR_KEY", ipVar.k);
        bl1 bl1VarB = bl1.b(j);
        bl1 bl1VarB2 = bl1.b(j2);
        hp hpVar = (hp) bundle2.getParcelable("DEEP_COPY_VALIDATOR_KEY");
        Long l = gpVar.a;
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", new ip(bl1VarB, bl1VarB2, hpVar, l != null ? bl1.b(l.longValue()) : null));
        bundle.putInt("TITLE_TEXT_RES_ID_KEY", this.w0);
        bundle.putCharSequence("TITLE_TEXT_KEY", this.x0);
    }

    @Override // p.s90, p.ww0
    public final void x() {
        ws1 rf1Var;
        super.x();
        Dialog dialog = this.m0;
        if (dialog == null) {
            throw new IllegalStateException("DialogFragment " + this + " does not have a Dialog.");
        }
        Window window = dialog.getWindow();
        if (this.y0) {
            window.setLayout(-1, -1);
            window.setBackgroundDrawable(this.B0);
        } else {
            window.setLayout(-2, -2);
            int dimensionPixelOffset = B().getResources().getDimensionPixelOffset(R.dimen.mtrl_calendar_dialog_background_inset);
            Rect rect = new Rect(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            window.setBackgroundDrawable(new InsetDrawable((Drawable) this.B0, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset));
            View decorView = window.getDecorView();
            Dialog dialog2 = this.m0;
            if (dialog2 == null) {
                throw new IllegalStateException("DialogFragment " + this + " does not have a Dialog.");
            }
            decorView.setOnTouchListener(new u41(dialog2, rect));
        }
        B();
        int i = this.s0;
        if (i == 0) {
            H();
            throw null;
        }
        H();
        ip ipVar = this.u0;
        MaterialCalendar materialCalendar = new MaterialCalendar();
        Bundle bundle = new Bundle();
        bundle.putInt("THEME_RES_ID_KEY", i);
        bundle.putParcelable("GRID_SELECTOR_KEY", null);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", ipVar);
        bundle.putParcelable("CURRENT_MONTH_KEY", ipVar.l);
        materialCalendar.E(bundle);
        this.v0 = materialCalendar;
        if (this.A0.isChecked()) {
            H();
            ip ipVar2 = this.u0;
            rf1Var = new rf1();
            Bundle bundle2 = new Bundle();
            bundle2.putInt("THEME_RES_ID_KEY", i);
            bundle2.putParcelable("DATE_SELECTOR_KEY", null);
            bundle2.putParcelable("CALENDAR_CONSTRAINTS_KEY", ipVar2);
            rf1Var.E(bundle2);
        } else {
            rf1Var = this.v0;
        }
        this.t0 = rf1Var;
        H();
        e();
        throw null;
    }

    @Override // p.s90, p.ww0
    public final void y() {
        this.t0.b0.clear();
        super.y();
    }
}
