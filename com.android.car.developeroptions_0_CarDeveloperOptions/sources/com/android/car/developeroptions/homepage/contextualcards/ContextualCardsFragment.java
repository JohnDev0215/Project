package com.android.car.developeroptions.homepage.contextualcards;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.loader.app.LoaderManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.InstrumentedFragment;
import com.android.car.developeroptions.homepage.contextualcards.FocusRecyclerView;
import com.android.car.developeroptions.homepage.contextualcards.slices.SwipeDismissalDelegate;
import com.android.car.developeroptions.overlay.FeatureFactory;

/* JADX INFO: loaded from: classes.dex */
public class ContextualCardsFragment extends InstrumentedFragment implements FocusRecyclerView.FocusListener {
    private FocusRecyclerView mCardsContainer;
    private ContextualCardManager mContextualCardManager;
    private ContextualCardsAdapter mContextualCardsAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private GridLayoutManager mLayoutManager;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1502;
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context context = getContext();
        if (bundle == null) {
            FeatureFactory.getFactory(context).getSlicesFeatureProvider().newUiSession();
        }
        this.mContextualCardManager = new ContextualCardManager(context, getSettingsLifecycle(), bundle);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mContextualCardManager.loadContextualCards(LoaderManager.getInstance(this));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Context context = getContext();
        View viewInflate = layoutInflater.inflate(R.layout.settings_homepage, viewGroup, false);
        this.mCardsContainer = (FocusRecyclerView) viewInflate.findViewById(R.id.card_container);
        GridLayoutManager gridLayoutManager = new GridLayoutManager((Context) getActivity(), 2, 1, false);
        this.mLayoutManager = gridLayoutManager;
        this.mCardsContainer.setLayoutManager(gridLayoutManager);
        ContextualCardsAdapter contextualCardsAdapter = new ContextualCardsAdapter(context, this, this.mContextualCardManager);
        this.mContextualCardsAdapter = contextualCardsAdapter;
        this.mCardsContainer.setAdapter(contextualCardsAdapter);
        this.mContextualCardManager.setListener(this.mContextualCardsAdapter);
        this.mCardsContainer.setListener(this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeDismissalDelegate(this.mContextualCardsAdapter));
        this.mItemTouchHelper = itemTouchHelper;
        itemTouchHelper.attachToRecyclerView(this.mCardsContainer);
        return viewInflate;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.FocusRecyclerView.FocusListener
    public void onWindowFocusChanged(boolean z) {
        this.mContextualCardManager.onWindowFocusChanged(z);
    }
}
