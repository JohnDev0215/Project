package c.b.a.a.x;

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
import androidx.fragment.app.DialogFragment;
import b.h.m.t;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateSelector;
import com.google.android.material.datepicker.MaterialCalendar;
import com.google.android.material.datepicker.Month;
import com.google.android.material.internal.CheckableImageButton;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: compiled from: MaterialDatePicker.java */
/* JADX INFO: loaded from: classes.dex */
public final class f<S> extends DialogFragment {
    public static final Object p = "CONFIRM_BUTTON_TAG";
    public static final Object q = "CANCEL_BUTTON_TAG";
    public static final Object r = "TOGGLE_BUTTON_TAG";
    public MaterialCalendar<S> A;
    public int B;
    public CharSequence C;
    public boolean D;
    public int E;
    public TextView F;
    public CheckableImageButton G;
    public c.b.a.a.i0.h H;
    public Button I;
    public final LinkedHashSet<g<? super S>> s = new LinkedHashSet<>();
    public final LinkedHashSet<View.OnClickListener> t = new LinkedHashSet<>();
    public final LinkedHashSet<DialogInterface.OnCancelListener> u = new LinkedHashSet<>();
    public final LinkedHashSet<DialogInterface.OnDismissListener> v = new LinkedHashSet<>();
    public int w;
    public DateSelector<S> x;
    public l<S> y;
    public CalendarConstraints z;

    /* JADX INFO: compiled from: MaterialDatePicker.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Iterator it = f.this.s.iterator();
            while (it.hasNext()) {
                ((g) it.next()).a(f.this.C());
            }
            f.this.e();
        }
    }

    /* JADX INFO: compiled from: MaterialDatePicker.java */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Iterator it = f.this.t.iterator();
            while (it.hasNext()) {
                ((View.OnClickListener) it.next()).onClick(view);
            }
            f.this.e();
        }
    }

    /* JADX INFO: compiled from: MaterialDatePicker.java */
    public class c extends k<S> {
        public c() {
        }

        @Override // c.b.a.a.x.k
        public void a() {
            f.this.I.setEnabled(false);
        }

        @Override // c.b.a.a.x.k
        public void b(S s) {
            f.this.I();
            f.this.I.setEnabled(f.this.x.f());
        }
    }

    /* JADX INFO: compiled from: MaterialDatePicker.java */
    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f.this.I.setEnabled(f.this.x.f());
            f.this.G.toggle();
            f fVar = f.this;
            fVar.J(fVar.G);
            f.this.G();
        }
    }

    public static int B(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(c.b.a.a.d.mtrl_calendar_content_padding);
        int i2 = Month.m().f4699f;
        return (dimensionPixelOffset * 2) + (resources.getDimensionPixelSize(c.b.a.a.d.mtrl_calendar_day_width) * i2) + ((i2 - 1) * resources.getDimensionPixelOffset(c.b.a.a.d.mtrl_calendar_month_horizontal_padding));
    }

    public static boolean F(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(c.b.a.a.f0.b.c(context, c.b.a.a.b.materialCalendarStyle, MaterialCalendar.class.getCanonicalName()), new int[]{R.attr.windowFullscreen});
        boolean z = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        return z;
    }

    public static long H() {
        return Month.m().f4701h;
    }

    public static Drawable y(Context context) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_checked}, b.b.l.a.a.d(context, c.b.a.a.e.material_ic_calendar_black_24dp));
        stateListDrawable.addState(new int[0], b.b.l.a.a.d(context, c.b.a.a.e.material_ic_edit_black_24dp));
        return stateListDrawable;
    }

    public static int z(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(c.b.a.a.d.mtrl_calendar_navigation_height) + resources.getDimensionPixelOffset(c.b.a.a.d.mtrl_calendar_navigation_top_padding) + resources.getDimensionPixelOffset(c.b.a.a.d.mtrl_calendar_navigation_bottom_padding);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(c.b.a.a.d.mtrl_calendar_days_of_week_height);
        int i2 = i.f4130b;
        return dimensionPixelSize + dimensionPixelSize2 + (resources.getDimensionPixelSize(c.b.a.a.d.mtrl_calendar_day_height) * i2) + ((i2 - 1) * resources.getDimensionPixelOffset(c.b.a.a.d.mtrl_calendar_month_vertical_padding)) + resources.getDimensionPixelOffset(c.b.a.a.d.mtrl_calendar_bottom_padding);
    }

    public String A() {
        return this.x.b(getContext());
    }

    public final S C() {
        return this.x.a();
    }

    public final int D(Context context) {
        int i2 = this.w;
        return i2 != 0 ? i2 : this.x.c(context);
    }

    public final void E(Context context) {
        this.G.setTag(r);
        this.G.setImageDrawable(y(context));
        this.G.setChecked(this.E != 0);
        t.k0(this.G, null);
        J(this.G);
        this.G.setOnClickListener(new d());
    }

    public final void G() {
        this.A = MaterialCalendar.s(this.x, D(requireContext()), this.z);
        this.y = this.G.isChecked() ? h.d(this.x, this.z) : this.A;
        I();
        b.l.d.p pVarM = getChildFragmentManager().m();
        pVarM.q(c.b.a.a.f.mtrl_calendar_frame, this.y);
        pVarM.j();
        this.y.b(new c());
    }

    public final void I() {
        String strA = A();
        this.F.setContentDescription(String.format(getString(c.b.a.a.j.mtrl_picker_announce_current_selection), strA));
        this.F.setText(strA);
    }

    public final void J(CheckableImageButton checkableImageButton) {
        this.G.setContentDescription(this.G.isChecked() ? checkableImageButton.getContext().getString(c.b.a.a.j.mtrl_picker_toggle_to_calendar_input_mode) : checkableImageButton.getContext().getString(c.b.a.a.j.mtrl_picker_toggle_to_text_input_mode));
    }

    @Override // androidx.fragment.app.DialogFragment
    public final Dialog i(Bundle bundle) {
        Dialog dialog = new Dialog(requireContext(), D(requireContext()));
        Context context = dialog.getContext();
        this.D = F(context);
        int iC = c.b.a.a.f0.b.c(context, c.b.a.a.b.colorSurface, f.class.getCanonicalName());
        c.b.a.a.i0.h hVar = new c.b.a.a.i0.h(context, null, c.b.a.a.b.materialCalendarStyle, c.b.a.a.k.Widget_MaterialComponents_MaterialCalendar);
        this.H = hVar;
        hVar.N(context);
        this.H.X(ColorStateList.valueOf(iC));
        this.H.W(t.u(dialog.getWindow().getDecorView()));
        return dialog;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        Iterator<DialogInterface.OnCancelListener> it = this.u.iterator();
        while (it.hasNext()) {
            it.next().onCancel(dialogInterface);
        }
        super.onCancel(dialogInterface);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.w = bundle.getInt("OVERRIDE_THEME_RES_ID");
        this.x = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.z = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.B = bundle.getInt("TITLE_TEXT_RES_ID_KEY");
        this.C = bundle.getCharSequence("TITLE_TEXT_KEY");
        this.E = bundle.getInt("INPUT_MODE_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(this.D ? c.b.a.a.h.mtrl_picker_fullscreen : c.b.a.a.h.mtrl_picker_dialog, viewGroup);
        Context context = viewInflate.getContext();
        if (this.D) {
            viewInflate.findViewById(c.b.a.a.f.mtrl_calendar_frame).setLayoutParams(new LinearLayout.LayoutParams(B(context), -2));
        } else {
            View viewFindViewById = viewInflate.findViewById(c.b.a.a.f.mtrl_calendar_main_pane);
            View viewFindViewById2 = viewInflate.findViewById(c.b.a.a.f.mtrl_calendar_frame);
            viewFindViewById.setLayoutParams(new LinearLayout.LayoutParams(B(context), -1));
            viewFindViewById2.setMinimumHeight(z(requireContext()));
        }
        TextView textView = (TextView) viewInflate.findViewById(c.b.a.a.f.mtrl_picker_header_selection_text);
        this.F = textView;
        t.m0(textView, 1);
        this.G = (CheckableImageButton) viewInflate.findViewById(c.b.a.a.f.mtrl_picker_header_toggle);
        TextView textView2 = (TextView) viewInflate.findViewById(c.b.a.a.f.mtrl_picker_title_text);
        CharSequence charSequence = this.C;
        if (charSequence != null) {
            textView2.setText(charSequence);
        } else {
            textView2.setText(this.B);
        }
        E(context);
        this.I = (Button) viewInflate.findViewById(c.b.a.a.f.confirm_button);
        if (this.x.f()) {
            this.I.setEnabled(true);
        } else {
            this.I.setEnabled(false);
        }
        this.I.setTag(p);
        this.I.setOnClickListener(new a());
        Button button = (Button) viewInflate.findViewById(c.b.a.a.f.cancel_button);
        button.setTag(q);
        button.setOnClickListener(new b());
        return viewInflate;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        Iterator<DialogInterface.OnDismissListener> it = this.v.iterator();
        while (it.hasNext()) {
            it.next().onDismiss(dialogInterface);
        }
        ViewGroup viewGroup = (ViewGroup) getView();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        super.onDismiss(dialogInterface);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("OVERRIDE_THEME_RES_ID", this.w);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.x);
        CalendarConstraints.b bVar = new CalendarConstraints.b(this.z);
        if (this.A.o() != null) {
            bVar.b(this.A.o().f4701h);
        }
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", bVar.a());
        bundle.putInt("TITLE_TEXT_RES_ID_KEY", this.B);
        bundle.putCharSequence("TITLE_TEXT_KEY", this.C);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Window window = m().getWindow();
        if (this.D) {
            window.setLayout(-1, -1);
            window.setBackgroundDrawable(this.H);
        } else {
            window.setLayout(-2, -2);
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(c.b.a.a.d.mtrl_calendar_dialog_background_inset);
            Rect rect = new Rect(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            window.setBackgroundDrawable(new InsetDrawable((Drawable) this.H, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset));
            window.getDecorView().setOnTouchListener(new c.b.a.a.y.a(m(), rect));
        }
        G();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStop() {
        this.y.c();
        super.onStop();
    }
}
