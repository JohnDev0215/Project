package spi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.car.ui.recyclerview.CarUiRecyclerView;
import com.android.car.ui.widget.CarUiTextView;

/* JADX INFO: compiled from: PluginFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public interface w8 {
    CarUiRecyclerView createRecyclerView(Context context, AttributeSet attributeSet);

    @NonNull
    CarUiTextView createTextView(Context context, AttributeSet attributeSet);

    @Nullable
    com.android.car.ui.toolbar.j installBaseLayoutAround(View view, z4 z4Var, boolean z, boolean z2);
}
