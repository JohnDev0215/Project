package spi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.android.car.ui.R$bool;
import com.android.car.ui.R$id;
import com.android.car.ui.R$layout;
import com.android.car.ui.baselayout.Insets;
import com.android.car.ui.recyclerview.CarUiRecyclerView;
import com.android.car.ui.recyclerview.CarUiRecyclerViewImpl;
import com.android.car.ui.widget.CarUiTextView;
import com.android.car.ui.widget.CarUiTextViewImpl;
import java.util.Objects;
import spi.a9;

/* JADX INFO: compiled from: PluginFactoryStub.java */
/* JADX INFO: loaded from: classes2.dex */
@RequiresApi(28)
public final class a9 implements w8 {

    /* JADX INFO: compiled from: PluginFactoryStub.java */
    public static final class a {
        public final View a;
        public final View b;
        public final View c;
        public final View d;
        public final View e;
        public final View f;
        public z4 g;

        @NonNull
        public Insets h = new Insets();

        public a(@NonNull View view, @NonNull View view2) {
            this.a = view2;
            View viewN = b2.n(view, R$id.car_ui_base_layout_content_container);
            this.b = viewN;
            View viewFindViewWithTag = view.findViewWithTag("car_ui_left_inset");
            this.c = viewFindViewWithTag;
            View viewFindViewWithTag2 = view.findViewWithTag("car_ui_right_inset");
            this.d = viewFindViewWithTag2;
            View viewFindViewWithTag3 = view.findViewWithTag("car_ui_top_inset");
            this.e = viewFindViewWithTag3;
            View viewFindViewWithTag4 = view.findViewWithTag("car_ui_bottom_inset");
            this.f = viewFindViewWithTag4;
            View.OnLayoutChangeListener onLayoutChangeListener = new View.OnLayoutChangeListener() { // from class: spi.z8
                @Override // android.view.View.OnLayoutChangeListener
                public final void onLayoutChange(View view3, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    a9.a aVar = this.a;
                    Objects.requireNonNull(aVar);
                    if (i == i5 && i2 == i6 && i3 == i7 && i4 == i8) {
                        return;
                    }
                    int iMax = Math.max(0, a9.a.d(aVar.b) - a9.a.d(aVar.a));
                    int iMax2 = Math.max(0, a9.a.b(aVar.b) - a9.a.b(aVar.a));
                    int iMax3 = Math.max(0, a9.a.c(aVar.a) - a9.a.c(aVar.b));
                    int iMax4 = Math.max(0, a9.a.a(aVar.a) - a9.a.a(aVar.b));
                    View view4 = aVar.e;
                    if (view4 != null) {
                        iMax += Math.max(0, a9.a.a(view4) - a9.a.d(aVar.b));
                    }
                    if (aVar.f != null) {
                        iMax4 += Math.max(0, a9.a.a(aVar.b) - a9.a.d(aVar.f));
                    }
                    View view5 = aVar.c;
                    if (view5 != null) {
                        iMax2 += Math.max(0, a9.a.c(view5) - a9.a.b(aVar.b));
                    }
                    if (aVar.d != null) {
                        iMax3 += Math.max(0, a9.a.c(aVar.b) - a9.a.b(aVar.d));
                    }
                    Insets insets = new Insets(iMax2, iMax, iMax3, iMax4);
                    if (insets.equals(aVar.h)) {
                        return;
                    }
                    aVar.h = insets;
                    z4 z4Var = aVar.g;
                    if (z4Var != null) {
                        z4Var.b(insets);
                    } else {
                        aVar.a.setPadding(insets.getLeft(), insets.getTop(), insets.getRight(), insets.getBottom());
                    }
                }
            };
            if (viewFindViewWithTag != null) {
                viewFindViewWithTag.addOnLayoutChangeListener(onLayoutChangeListener);
            }
            if (viewFindViewWithTag2 != null) {
                viewFindViewWithTag2.addOnLayoutChangeListener(onLayoutChangeListener);
            }
            if (viewFindViewWithTag3 != null) {
                viewFindViewWithTag3.addOnLayoutChangeListener(onLayoutChangeListener);
            }
            if (viewFindViewWithTag4 != null) {
                viewFindViewWithTag4.addOnLayoutChangeListener(onLayoutChangeListener);
            }
            view2.addOnLayoutChangeListener(onLayoutChangeListener);
            viewN.addOnLayoutChangeListener(onLayoutChangeListener);
        }

        public static int a(View view) {
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            return view.getHeight() + iArr[1];
        }

        public static int b(View view) {
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            return iArr[0];
        }

        public static int c(View view) {
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            return view.getWidth() + iArr[0];
        }

        public static int d(View view) {
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            return iArr[1];
        }
    }

    @Override // spi.w8
    public final CarUiRecyclerView createRecyclerView(Context context, AttributeSet attributeSet) {
        return new CarUiRecyclerViewImpl(context, attributeSet);
    }

    @Override // spi.w8
    @NonNull
    public final CarUiTextView createTextView(Context context, AttributeSet attributeSet) {
        return new CarUiTextViewImpl(context, attributeSet);
    }

    @Override // spi.w8
    @Nullable
    public final com.android.car.ui.toolbar.j installBaseLayoutAround(View view, z4 z4Var, boolean z, boolean z2) {
        View viewInflate = LayoutInflater.from(view.getContext()).inflate(z ? view.getResources().getBoolean(R$bool.car_ui_toolbar_tabs_on_second_row) ? R$layout.car_ui_base_layout_toolbar_legacy_two_row : R$layout.car_ui_base_layout_toolbar_legacy : R$layout.car_ui_base_layout, (ViewGroup) null, false);
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        int iIndexOfChild = viewGroup.indexOfChild(view);
        viewGroup.removeView(view);
        viewGroup.addView(viewInflate, iIndexOfChild, view.getLayoutParams());
        ((FrameLayout) b2.n(viewInflate, R$id.car_ui_base_layout_content_container)).addView(view, new FrameLayout.LayoutParams(-1, -1));
        jb jbVar = z ? new jb(b2.n(viewInflate, R$id.car_ui_toolbar)) : null;
        new a(viewInflate, view).g = z4Var;
        return jbVar;
    }
}
