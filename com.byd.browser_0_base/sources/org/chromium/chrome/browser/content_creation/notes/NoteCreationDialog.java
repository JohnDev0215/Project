package org.chromium.chrome.browser.content_creation.notes;

import android.app.Dialog;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.u;
import defpackage.bo1;
import defpackage.bq;
import defpackage.co1;
import defpackage.fo1;
import defpackage.ke0;
import defpackage.o61;
import defpackage.r90;
import defpackage.wq0;
import defpackage.ya1;
import org.chromium.chrome.browser.content_creation.internal.R;
import org.chromium.components.content_creation.notes.models.Background;
import org.chromium.components.content_creation.notes.models.NoteTemplate;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.m;
import org.chromium.ui.widget.Toast;

/* JADX INFO: loaded from: classes.dex */
public class NoteCreationDialog extends r90 {
    public static final /* synthetic */ int z0 = 0;
    public View p0;
    public String q0;
    public String r0;
    public String s0;
    public int t0;
    public Toast u0;
    public int v0;
    public boolean w0;
    public Runnable x0;
    public d y0;

    public class a extends m {
        public a(ya1 ya1Var) {
            super(ya1Var);
        }

        @Override // org.chromium.ui.modelutil.m, androidx.recyclerview.widget.RecyclerView.e
        /* JADX INFO: renamed from: E */
        public final void t(m.b bVar, int i) {
            bVar.e.setTag(Integer.valueOf(i));
            super.t(bVar, i);
        }

        @Override // org.chromium.ui.modelutil.m, androidx.recyclerview.widget.RecyclerView.e
        public final /* bridge */ /* synthetic */ RecyclerView.a0 v(int i, RecyclerView recyclerView) {
            return v(i, recyclerView);
        }

        @Override // org.chromium.ui.modelutil.m, androidx.recyclerview.widget.RecyclerView.e
        public final void z(RecyclerView.a0 a0Var) {
            ((m.b) a0Var).u(null);
        }
    }

    public class b extends RecyclerView.q {
        public final /* synthetic */ ya1 e;

        public b(ya1 ya1Var) {
            this.e = ya1Var;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.q
        public final void b(RecyclerView recyclerView, int i, int i2) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            View viewT0 = linearLayoutManager.T0(0, linearLayoutManager.A(), true, false);
            if ((viewT0 == null ? -1 : RecyclerView.m.K(viewT0)) < 0) {
                return;
            }
            View viewT02 = linearLayoutManager.T0(0, linearLayoutManager.A(), true, false);
            int iK = viewT02 == null ? -1 : RecyclerView.m.K(viewT02);
            View viewT03 = linearLayoutManager.T0(linearLayoutManager.A() - 1, -1, true, false);
            int iK2 = (((viewT03 != null ? RecyclerView.m.K(viewT03) : -1) - iK) / 2) + iK;
            NoteCreationDialog noteCreationDialog = NoteCreationDialog.this;
            int i3 = noteCreationDialog.t0;
            if (i3 == iK2) {
                return;
            }
            View viewV0 = noteCreationDialog.V0(i3);
            if (viewV0 != null) {
                viewV0.setElevation(0.0f);
            }
            noteCreationDialog.t0 = iK2;
            noteCreationDialog.Z0(this.e.get(iK2).b);
            noteCreationDialog.T0(noteCreationDialog.t0);
        }
    }

    public class c implements ViewTreeObserver.OnGlobalLayoutListener {
        public final /* synthetic */ RelativeLayout e;
        public final /* synthetic */ int f;

        public c(RelativeLayout relativeLayout, int i) {
            this.e = relativeLayout;
            this.f = i;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public final void onGlobalLayout() {
            int measuredHeight = this.e.getMeasuredHeight();
            View viewFindViewById = NoteCreationDialog.this.p0.findViewById(R.id.note_carousel);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewFindViewById.getLayoutParams();
            marginLayoutParams.topMargin = (measuredHeight - this.f) / 2;
            viewFindViewById.setLayoutParams(marginLayoutParams);
        }
    }

    public interface d {
    }

    @Override // defpackage.r90
    public final Dialog J0(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(D(), R.style.ThemeOverlay_BrowserUI_Fullscreen);
        View viewInflate = D().getLayoutInflater().inflate(R.layout.creation_dialog, (ViewGroup) null);
        this.p0 = viewInflate;
        builder.j(viewInflate);
        a1();
        O0();
        if (bq.b("WebNotesDynamicTemplates")) {
            View viewFindViewById = this.p0.findViewById(R.id.title);
            ((ViewGroup) viewFindViewById.getParent()).removeView(viewFindViewById);
        }
        d dVar = this.y0;
        if (dVar != null) {
            ((ke0) dVar).v(this.p0);
        }
        return builder.a();
    }

    public final void O0() {
        int i = D().getResources().getDisplayMetrics().heightPixels;
        int dimension = (int) D().getResources().getDimension(R.dimen.min_dialog_height);
        if (dimension < i && this.p0.findViewById(R.id.scrollview) != null) {
            X0();
        }
        if (dimension <= i || this.p0.findViewById(R.id.scrollview) != null) {
            return;
        }
        P0();
    }

    public final void P0() {
        ScrollView scrollView = new ScrollView(D());
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        scrollView.setId(R.id.scrollview);
        LinearLayout linearLayout = (LinearLayout) this.p0.findViewById(R.id.dialog_layout);
        RelativeLayout relativeLayout = (RelativeLayout) linearLayout.findViewById(R.id.main_content);
        linearLayout.removeView(relativeLayout);
        scrollView.addView(relativeLayout);
        linearLayout.addView(scrollView);
    }

    public final void Q0(PropertyModel propertyModel, ViewGroup viewGroup) {
        PropertyModel.r<Boolean> rVar = fo1.a;
        if (((Boolean) propertyModel.j(rVar)).booleanValue() && this.t0 == 0) {
            Z0(propertyModel);
        }
        PropertyModel.r<NoteTemplate> rVar2 = fo1.c;
        NoteTemplate noteTemplate = (NoteTemplate) propertyModel.j(rVar2);
        View viewFindViewById = viewGroup.findViewById(R.id.item);
        noteTemplate.c.a(viewFindViewById, U0());
        viewFindViewById.setClipToOutline(true);
        viewFindViewById.setContentDescription(D().getString(R.string.content_creation_note_template_selected, ((NoteTemplate) propertyModel.j(rVar2)).b));
        Typeface typeface = (Typeface) propertyModel.j(fo1.d);
        LineLimitedTextView lineLimitedTextView = (LineLimitedTextView) viewGroup.findViewById(R.id.text);
        lineLimitedTextView.setTypeface(typeface);
        noteTemplate.d.a(lineLimitedTextView, this.s0);
        lineLimitedTextView.setIsEllipsizedListener(new wq0(22, this));
        Background background = noteTemplate.f;
        if (background != null) {
            background.a(lineLimitedTextView, U0());
        } else {
            lineLimitedTextView.setBackground(null);
        }
        TextView textView = (TextView) viewGroup.findViewById(R.id.footer_link);
        TextView textView2 = (TextView) viewGroup.findViewById(R.id.footer_title);
        ImageView imageView = (ImageView) viewGroup.findViewById(R.id.footer_icon);
        textView.setText(this.q0);
        textView2.setText(this.r0);
        noteTemplate.e.a(textView, textView2, imageView);
        Y0(viewGroup.findViewById(R.id.item), ((Boolean) propertyModel.j(rVar)).booleanValue(), ((Boolean) propertyModel.j(fo1.b)).booleanValue());
        viewFindViewById.setAccessibilityDelegate(new co1(this));
    }

    public final void R0() {
        ((LinearLayoutManager) ((RecyclerView) this.p0.findViewById(R.id.note_carousel)).getLayoutManager()).g1(this.t0, ((D().getResources().getDisplayMetrics().widthPixels / 2) - (V0(this.t0).getWidth() / 2)) - D().getResources().getDimensionPixelSize(R.dimen.note_side_padding));
    }

    public final void S0(ya1 ya1Var) {
        RecyclerView recyclerView = (RecyclerView) this.p0.findViewById(R.id.note_carousel);
        a aVar = new a(ya1Var);
        aVar.H(1, new o61(R.layout.carousel_item), new bo1(0, this));
        recyclerView.setAdapter(aVar);
        D();
        recyclerView.setLayoutManager(new LinearLayoutManager(0));
        new u().a(recyclerView);
        recyclerView.addOnScrollListener(new b(ya1Var));
    }

    public final void T0(int i) {
        this.v0++;
        View viewV0 = V0(i);
        if (viewV0 == null) {
            return;
        }
        viewV0.setElevation(D().getResources().getDimension(R.dimen.focused_note_elevation));
    }

    public final float U0() {
        return D().getResources().getDimensionPixelSize(R.dimen.note_corner_radius);
    }

    public final View V0(int i) {
        View viewU;
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) ((RecyclerView) this.p0.findViewById(R.id.note_carousel)).getLayoutManager();
        if (linearLayoutManager == null || (viewU = linearLayoutManager.u(i)) == null) {
            return null;
        }
        return viewU.findViewById(R.id.item);
    }

    public final void W0() {
        if (this.u0 != null) {
            return;
        }
        Toast toastB = Toast.b(1, D(), D().getString(R.string.content_creation_note_shortened_message));
        this.u0 = toastB;
        toastB.e();
    }

    public final void X0() {
        LinearLayout linearLayout = (LinearLayout) this.p0.findViewById(R.id.dialog_layout);
        RelativeLayout relativeLayout = (RelativeLayout) linearLayout.findViewById(R.id.main_content);
        ScrollView scrollView = (ScrollView) linearLayout.findViewById(R.id.scrollview);
        scrollView.removeView(relativeLayout);
        linearLayout.removeView(scrollView);
        linearLayout.addView(relativeLayout);
    }

    public final void Y0(View view, boolean z, boolean z2) {
        int i = D().getResources().getDisplayMetrics().widthPixels;
        int dimension = (int) D().getResources().getDimension(R.dimen.note_width);
        int dimension2 = (int) D().getResources().getDimension(R.dimen.note_side_padding);
        int i2 = z ? (int) (((i - dimension) * 0.5f) + 0.5f) : dimension2;
        if (z2) {
            dimension2 = (int) (((i - dimension) * 0.5f) + 0.5f);
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.setMarginStart(i2);
        marginLayoutParams.setMarginEnd(dimension2);
        view.setLayoutParams(marginLayoutParams);
    }

    public final void Z0(PropertyModel propertyModel) {
        if (bq.b("WebNotesDynamicTemplates")) {
            return;
        }
        ((TextView) this.p0.findViewById(R.id.title)).setText(((NoteTemplate) propertyModel.j(fo1.c)).b);
    }

    public final void a1() {
        int dimensionPixelSize = D().getResources().getDimensionPixelSize(R.dimen.note_title_min_top_margin);
        int i = D().getResources().getDisplayMetrics().heightPixels;
        int dimensionPixelSize2 = D().getResources().getDimensionPixelSize(R.dimen.note_title_top_margin_offset);
        int dimensionPixelSize3 = D().getResources().getDimensionPixelSize(R.dimen.note_width);
        if (bq.b("WebNotesDynamicTemplates")) {
            RelativeLayout relativeLayout = (RelativeLayout) this.p0.findViewById(R.id.main_content);
            relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new c(relativeLayout, dimensionPixelSize3));
            return;
        }
        View viewFindViewById = this.p0.findViewById(R.id.title);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewFindViewById.getLayoutParams();
        marginLayoutParams.topMargin = (int) (((i - dimensionPixelSize2) * 0.15f) + dimensionPixelSize);
        viewFindViewById.setLayoutParams(marginLayoutParams);
    }

    @Override // defpackage.r90, androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) {
        super.c0(bundle);
        if (this.w0) {
            return;
        }
        I0(false, false);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (!this.w0) {
            H0();
            return;
        }
        a1();
        O0();
        ((RecyclerView) this.p0.findViewById(R.id.note_carousel)).getAdapter().o();
        R0();
    }
}
