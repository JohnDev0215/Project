package com.google.android.material.datepicker;

import android.R;
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
import androidx.fragment.app.r;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.internal.CheckableImageButton;
import i0.u;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: compiled from: MaterialDatePicker.java */
/* JADX INFO: loaded from: classes.dex */
public final class g<S> extends androidx.fragment.app.c {
    static final Object N0 = "CONFIRM_BUTTON_TAG";
    static final Object O0 = "CANCEL_BUTTON_TAG";
    static final Object P0 = "TOGGLE_BUTTON_TAG";
    private int A0;
    private DateSelector<S> B0;
    private m<S> C0;
    private CalendarConstraints D0;
    private f<S> E0;
    private int F0;
    private CharSequence G0;
    private boolean H0;
    private int I0;
    private TextView J0;
    private CheckableImageButton K0;
    private e3.g L0;
    private Button M0;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    private final LinkedHashSet<h<? super S>> f4466w0 = new LinkedHashSet<>();

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    private final LinkedHashSet<View.OnClickListener> f4467x0 = new LinkedHashSet<>();

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    private final LinkedHashSet<DialogInterface.OnCancelListener> f4468y0 = new LinkedHashSet<>();

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    private final LinkedHashSet<DialogInterface.OnDismissListener> f4469z0 = new LinkedHashSet<>();

    /* JADX INFO: compiled from: MaterialDatePicker.java */
    class a implements View.OnClickListener {
        a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Iterator it = g.this.f4466w0.iterator();
            while (it.hasNext()) {
                ((h) it.next()).a(g.this.n2());
            }
            g.this.Q1();
        }
    }

    /* JADX INFO: compiled from: MaterialDatePicker.java */
    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Iterator it = g.this.f4467x0.iterator();
            while (it.hasNext()) {
                ((View.OnClickListener) it.next()).onClick(view);
            }
            g.this.Q1();
        }
    }

    /* JADX INFO: compiled from: MaterialDatePicker.java */
    class c extends l<S> {
        c() {
        }

        @Override // com.google.android.material.datepicker.l
        public void a() {
            g.this.M0.setEnabled(false);
        }

        @Override // com.google.android.material.datepicker.l
        public void b(S s6) {
            g.this.u2();
            g.this.M0.setEnabled(g.this.B0.i());
        }
    }

    /* JADX INFO: compiled from: MaterialDatePicker.java */
    class d implements View.OnClickListener {
        d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            g.this.M0.setEnabled(g.this.B0.i());
            g.this.K0.toggle();
            g gVar = g.this;
            gVar.v2(gVar.K0);
            g.this.t2();
        }
    }

    private static Drawable j2(Context context) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_checked}, f.a.b(context, o2.e.material_ic_calendar_black_24dp));
        stateListDrawable.addState(new int[0], f.a.b(context, o2.e.material_ic_edit_black_24dp));
        return stateListDrawable;
    }

    private static int k2(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(o2.d.mtrl_calendar_navigation_height) + resources.getDimensionPixelOffset(o2.d.mtrl_calendar_navigation_top_padding) + resources.getDimensionPixelOffset(o2.d.mtrl_calendar_navigation_bottom_padding);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(o2.d.mtrl_calendar_days_of_week_height);
        int i7 = j.f4478k;
        return dimensionPixelSize + dimensionPixelSize2 + (resources.getDimensionPixelSize(o2.d.mtrl_calendar_day_height) * i7) + ((i7 - 1) * resources.getDimensionPixelOffset(o2.d.mtrl_calendar_month_vertical_padding)) + resources.getDimensionPixelOffset(o2.d.mtrl_calendar_bottom_padding);
    }

    private static int m2(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(o2.d.mtrl_calendar_content_padding);
        int i7 = Month.j().f4386i;
        return (dimensionPixelOffset * 2) + (resources.getDimensionPixelSize(o2.d.mtrl_calendar_day_width) * i7) + ((i7 - 1) * resources.getDimensionPixelOffset(o2.d.mtrl_calendar_month_horizontal_padding));
    }

    private int o2(Context context) {
        int i7 = this.A0;
        return i7 != 0 ? i7 : this.B0.e(context);
    }

    private void p2(Context context) {
        this.K0.setTag(P0);
        this.K0.setImageDrawable(j2(context));
        this.K0.setChecked(this.I0 != 0);
        u.k0(this.K0, null);
        v2(this.K0);
        this.K0.setOnClickListener(new d());
    }

    static boolean q2(Context context) {
        return s2(context, R.attr.windowFullscreen);
    }

    static boolean r2(Context context) {
        return s2(context, o2.b.nestedScrollable);
    }

    static boolean s2(Context context, int i7) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(b3.b.c(context, o2.b.materialCalendarStyle, f.class.getCanonicalName()), new int[]{i7});
        boolean z6 = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        return z6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t2() {
        int iO2 = o2(u1());
        this.E0 = f.e2(this.B0, iO2, this.D0);
        this.C0 = this.K0.isChecked() ? i.P1(this.B0, iO2, this.D0) : this.E0;
        u2();
        r rVarL = r().l();
        rVarL.q(o2.f.mtrl_calendar_frame, this.C0);
        rVarL.k();
        this.C0.N1(new c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u2() {
        String strL2 = l2();
        this.J0.setContentDescription(String.format(T(o2.j.mtrl_picker_announce_current_selection), strL2));
        this.J0.setText(strL2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v2(CheckableImageButton checkableImageButton) {
        this.K0.setContentDescription(this.K0.isChecked() ? checkableImageButton.getContext().getString(o2.j.mtrl_picker_toggle_to_calendar_input_mode) : checkableImageButton.getContext().getString(o2.j.mtrl_picker_toggle_to_text_input_mode));
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public final void Q0(Bundle bundle) {
        super.Q0(bundle);
        bundle.putInt("OVERRIDE_THEME_RES_ID", this.A0);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.B0);
        CalendarConstraints.b bVar = new CalendarConstraints.b(this.D0);
        if (this.E0.a2() != null) {
            bVar.b(this.E0.a2().f4388k);
        }
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", bVar.a());
        bundle.putInt("TITLE_TEXT_RES_ID_KEY", this.F0);
        bundle.putCharSequence("TITLE_TEXT_KEY", this.G0);
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void R0() {
        super.R0();
        Window window = Y1().getWindow();
        if (this.H0) {
            window.setLayout(-1, -1);
            window.setBackgroundDrawable(this.L0);
        } else {
            window.setLayout(-2, -2);
            int dimensionPixelOffset = N().getDimensionPixelOffset(o2.d.mtrl_calendar_dialog_background_inset);
            Rect rect = new Rect(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            window.setBackgroundDrawable(new InsetDrawable((Drawable) this.L0, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset));
            window.getDecorView().setOnTouchListener(new v2.a(Y1(), rect));
        }
        t2();
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void S0() {
        this.C0.O1();
        super.S0();
    }

    @Override // androidx.fragment.app.c
    public final Dialog U1(Bundle bundle) {
        Dialog dialog = new Dialog(u1(), o2(u1()));
        Context context = dialog.getContext();
        this.H0 = q2(context);
        int iC = b3.b.c(context, o2.b.colorSurface, g.class.getCanonicalName());
        e3.g gVar = new e3.g(context, null, o2.b.materialCalendarStyle, o2.k.Widget_MaterialComponents_MaterialCalendar);
        this.L0 = gVar;
        gVar.M(context);
        this.L0.X(ColorStateList.valueOf(iC));
        this.L0.W(u.t(dialog.getWindow().getDecorView()));
        return dialog;
    }

    public String l2() {
        return this.B0.d(s());
    }

    public final S n2() {
        return this.B0.a();
    }

    @Override // androidx.fragment.app.c, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        Iterator<DialogInterface.OnCancelListener> it = this.f4468y0.iterator();
        while (it.hasNext()) {
            it.next().onCancel(dialogInterface);
        }
        super.onCancel(dialogInterface);
    }

    @Override // androidx.fragment.app.c, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        Iterator<DialogInterface.OnDismissListener> it = this.f4469z0.iterator();
        while (it.hasNext()) {
            it.next().onDismiss(dialogInterface);
        }
        ViewGroup viewGroup = (ViewGroup) W();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        super.onDismiss(dialogInterface);
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public final void u0(Bundle bundle) {
        super.u0(bundle);
        if (bundle == null) {
            bundle = q();
        }
        this.A0 = bundle.getInt("OVERRIDE_THEME_RES_ID");
        this.B0 = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.D0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.F0 = bundle.getInt("TITLE_TEXT_RES_ID_KEY");
        this.G0 = bundle.getCharSequence("TITLE_TEXT_KEY");
        this.I0 = bundle.getInt("INPUT_MODE_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public final View y0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(this.H0 ? o2.h.mtrl_picker_fullscreen : o2.h.mtrl_picker_dialog, viewGroup);
        Context context = viewInflate.getContext();
        if (this.H0) {
            viewInflate.findViewById(o2.f.mtrl_calendar_frame).setLayoutParams(new LinearLayout.LayoutParams(m2(context), -2));
        } else {
            View viewFindViewById = viewInflate.findViewById(o2.f.mtrl_calendar_main_pane);
            View viewFindViewById2 = viewInflate.findViewById(o2.f.mtrl_calendar_frame);
            viewFindViewById.setLayoutParams(new LinearLayout.LayoutParams(m2(context), -1));
            viewFindViewById2.setMinimumHeight(k2(u1()));
        }
        TextView textView = (TextView) viewInflate.findViewById(o2.f.mtrl_picker_header_selection_text);
        this.J0 = textView;
        u.m0(textView, 1);
        this.K0 = (CheckableImageButton) viewInflate.findViewById(o2.f.mtrl_picker_header_toggle);
        TextView textView2 = (TextView) viewInflate.findViewById(o2.f.mtrl_picker_title_text);
        CharSequence charSequence = this.G0;
        if (charSequence != null) {
            textView2.setText(charSequence);
        } else {
            textView2.setText(this.F0);
        }
        p2(context);
        this.M0 = (Button) viewInflate.findViewById(o2.f.confirm_button);
        if (this.B0.i()) {
            this.M0.setEnabled(true);
        } else {
            this.M0.setEnabled(false);
        }
        this.M0.setTag(N0);
        this.M0.setOnClickListener(new a());
        Button button = (Button) viewInflate.findViewById(o2.f.cancel_button);
        button.setTag(O0);
        button.setOnClickListener(new b());
        return viewInflate;
    }
}
