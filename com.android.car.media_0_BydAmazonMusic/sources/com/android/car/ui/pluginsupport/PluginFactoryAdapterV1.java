package com.android.car.ui.pluginsupport;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.FocusAreaAdapterV1;
import com.android.car.ui.FocusParkingViewAdapterV1;
import com.android.car.ui.appstyledview.AppStyledViewControllerAdapterV1;
import com.android.car.ui.baselayout.Insets;
import com.android.car.ui.recyclerview.CarUiListItemAdapter;
import com.android.car.ui.recyclerview.CarUiRecyclerView;
import com.android.car.ui.recyclerview.CarUiRecyclerViewImpl;
import com.android.car.ui.rotary.FocusArea;
import com.android.car.ui.rotary.FocusParkingView;
import com.android.car.ui.toolbar.ToolbarControllerAdapterV1;
import com.android.car.ui.toolbar.j;
import com.android.car.ui.widget.CarUiTextView;
import com.android.car.ui.widget.CarUiTextViewImpl;
import java.util.List;
import java.util.Objects;
import spi.a5;
import spi.a9;
import spi.c4;
import spi.kb;
import spi.l1;
import spi.o;
import spi.p;
import spi.q;
import spi.w8;
import spi.x8;
import spi.z3;
import spi.z4;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public final class PluginFactoryAdapterV1 implements w8 {

    @NonNull
    private final a9 mFactoryStub = new a9();

    @NonNull
    private final x8 mOem;

    public PluginFactoryAdapterV1(@NonNull x8 x8Var) {
        this.mOem = x8Var;
        x8Var.c();
    }

    private Insets adaptInsets(a5 a5Var) {
        return new Insets(a5Var.a, a5Var.c, a5Var.b, a5Var.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$installBaseLayoutAround$2(z4 z4Var, a5 a5Var) {
        z4Var.b(adaptInsets(a5Var));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ c4 lambda$new$0(Context context) {
        return new FocusParkingViewAdapterV1(new FocusParkingView(context));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ z3 lambda$new$1(Context context) {
        return new FocusAreaAdapterV1(new FocusArea(context));
    }

    public o createAppStyledView(Context context) {
        q qVarB = this.mOem.b();
        return qVarB == null ? new p(context) : new AppStyledViewControllerAdapterV1(qVarB);
    }

    public RecyclerView.Adapter<? extends RecyclerView.ViewHolder> createListItemAdapter(List<? extends l1> list) {
        Objects.requireNonNull(this.mFactoryStub);
        return new CarUiListItemAdapter(list);
    }

    @Override // spi.w8
    public CarUiRecyclerView createRecyclerView(Context context, AttributeSet attributeSet) {
        Objects.requireNonNull(this.mFactoryStub);
        return new CarUiRecyclerViewImpl(context, attributeSet);
    }

    @Override // spi.w8
    @NonNull
    public CarUiTextView createTextView(Context context, AttributeSet attributeSet) {
        Objects.requireNonNull(this.mFactoryStub);
        return new CarUiTextViewImpl(context, attributeSet);
    }

    @Override // spi.w8
    @Nullable
    public j installBaseLayoutAround(View view, z4 z4Var, boolean z, boolean z2) {
        if (!this.mOem.a()) {
            return this.mFactoryStub.installBaseLayoutAround(view, z4Var, z, z2);
        }
        x8 x8Var = this.mOem;
        view.getContext();
        kb kbVarD = x8Var.d();
        if (kbVarD != null) {
            return new ToolbarControllerAdapterV1(view.getContext(), kbVarD);
        }
        return null;
    }
}
