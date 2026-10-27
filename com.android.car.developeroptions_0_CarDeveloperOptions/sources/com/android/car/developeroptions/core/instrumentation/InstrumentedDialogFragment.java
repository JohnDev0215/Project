package com.android.car.developeroptions.core.instrumentation;

import android.content.Context;
import com.android.car.developeroptions.DialogCreatable;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.Instrumentable;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.core.instrumentation.VisibilityLoggerMixin;
import com.android.settingslib.core.lifecycle.ObservableDialogFragment;

/* JADX INFO: loaded from: classes.dex */
public abstract class InstrumentedDialogFragment extends ObservableDialogFragment implements Instrumentable {
    protected int mDialogId;
    protected MetricsFeatureProvider mMetricsFeatureProvider;

    public InstrumentedDialogFragment() {
        this(null, 0);
    }

    public InstrumentedDialogFragment(DialogCreatable dialogCreatable, int i) {
        this.mDialogId = i;
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mMetricsFeatureProvider = FeatureFactory.getFactory(context).getMetricsFeatureProvider();
        this.mLifecycle.addObserver(new VisibilityLoggerMixin(getMetricsCategory(), this.mMetricsFeatureProvider));
        this.mLifecycle.onAttach(context);
    }
}
