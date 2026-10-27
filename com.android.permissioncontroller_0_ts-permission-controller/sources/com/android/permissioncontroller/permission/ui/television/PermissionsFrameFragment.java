package com.android.permissioncontroller.permission.ui.television;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.leanback.widget.VerticalGridView;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import androidx.recyclerview.widget.RecyclerView;
import com.android.permissioncontroller.R;

/* JADX INFO: loaded from: classes8.dex */
public abstract class PermissionsFrameFragment extends PreferenceFragmentCompat {
    protected static final String HEADER_PREFERENCE_KEY = "HeaderPreferenceKey";
    private RecyclerView mGridView;
    private boolean mIsLoading;
    private View mLoadingView;
    private ViewGroup mPreferencesContainer;
    private ViewGroup mPrefsView;

    protected final ViewGroup getPreferencesContainer() {
        return this.mPreferencesContainer;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.permissions_frame, container, false);
        ViewGroup viewGroup = (ViewGroup) rootView.findViewById(R.id.prefs_container);
        this.mPrefsView = viewGroup;
        if (viewGroup == null) {
            this.mPrefsView = rootView;
        }
        this.mLoadingView = rootView.findViewById(R.id.loading_container);
        this.mPreferencesContainer = (ViewGroup) super.onCreateView(inflater, this.mPrefsView, savedInstanceState);
        setLoading(this.mIsLoading, false, true);
        this.mPrefsView.addView(this.mPreferencesContainer);
        return rootView;
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        PreferenceScreen preferences = getPreferenceScreen();
        if (preferences == null) {
            PreferenceScreen preferences2 = getPreferenceManager().createPreferenceScreen(getActivity());
            setPreferenceScreen(preferences2);
        }
    }

    protected void setLoading(boolean loading, boolean animate) {
        setLoading(loading, animate, false);
    }

    private void setLoading(boolean loading, boolean animate, boolean force) {
        if (this.mIsLoading != loading || force) {
            this.mIsLoading = loading;
            if (getView() == null) {
                animate = false;
            }
            ViewGroup viewGroup = this.mPrefsView;
            if (viewGroup != null) {
                setViewShown(viewGroup, !loading, animate);
            }
            View view = this.mLoadingView;
            if (view != null) {
                setViewShown(view, loading, animate);
            }
        }
    }

    private void setViewShown(final View view, boolean shown, boolean animate) {
        if (animate) {
            Animation animation = AnimationUtils.loadAnimation(getContext(), shown ? android.R.anim.fade_in : android.R.anim.fade_out);
            if (shown) {
                view.setVisibility(0);
            } else {
                animation.setAnimationListener(new Animation.AnimationListener() { // from class: com.android.permissioncontroller.permission.ui.television.PermissionsFrameFragment.1
                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationStart(Animation animation2) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationRepeat(Animation animation2) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationEnd(Animation animation2) {
                        view.setVisibility(4);
                    }
                });
            }
            view.startAnimation(animation);
            return;
        }
        view.clearAnimation();
        view.setVisibility(shown ? 0 : 4);
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public RecyclerView onCreateRecyclerView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        VerticalGridView verticalGridView = (VerticalGridView) inflater.inflate(R.layout.leanback_preferences_list, parent, false);
        verticalGridView.setWindowAlignment(3);
        verticalGridView.setFocusScrollStrategy(0);
        this.mGridView = verticalGridView;
        return verticalGridView;
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    protected RecyclerView.Adapter<?> onCreateAdapter(PreferenceScreen preferenceScreen) {
        final RecyclerView.Adapter<?> adapter = super.onCreateAdapter(preferenceScreen);
        if (adapter != null) {
            final TextView emptyView = (TextView) getView().findViewById(R.id.no_permissions);
            emptyView.setText(R.string.no_permissions);
            onSetEmptyText(emptyView);
            final RecyclerView recyclerView = getListView();
            adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() { // from class: com.android.permissioncontroller.permission.ui.television.PermissionsFrameFragment.2
                @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
                public void onChanged() {
                    PermissionsFrameFragment.this.checkEmpty(adapter, recyclerView, emptyView);
                }

                @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
                public void onItemRangeInserted(int positionStart, int itemCount) {
                    PermissionsFrameFragment.this.checkEmpty(adapter, recyclerView, emptyView);
                }

                @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
                public void onItemRangeRemoved(int positionStart, int itemCount) {
                    PermissionsFrameFragment.this.checkEmpty(adapter, recyclerView, emptyView);
                }
            });
            checkEmpty(adapter, recyclerView, emptyView);
        }
        return adapter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkEmpty(final RecyclerView.Adapter<?> adapter, final View recyclerView, final View emptyView) {
        emptyView.postDelayed(new Runnable() { // from class: com.android.permissioncontroller.permission.ui.television.-$$Lambda$PermissionsFrameFragment$bTDv21O6plJExf2u7V1vJ2M_AKQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$checkEmpty$0$PermissionsFrameFragment(emptyView, recyclerView, adapter);
            }
        }, 250L);
    }

    public /* synthetic */ void lambda$checkEmpty$0$PermissionsFrameFragment(View emptyView, View recyclerView, RecyclerView.Adapter adapter) {
        RecyclerView recyclerView2;
        boolean isEmpty = isPreferenceListEmpty();
        int i = 0;
        emptyView.setVisibility(isEmpty ? 0 : 8);
        if (isEmpty && adapter.getItemCount() == 0) {
            i = 8;
        }
        recyclerView.setVisibility(i);
        if (!isEmpty && (recyclerView2 = this.mGridView) != null) {
            recyclerView2.requestFocus();
        }
    }

    private boolean isPreferenceListEmpty() {
        PreferenceScreen screen = getPreferenceScreen();
        if (screen.getPreferenceCount() != 0) {
            return screen.getPreferenceCount() == 1 && screen.findPreference(HEADER_PREFERENCE_KEY) != null;
        }
        return true;
    }

    protected void onSetEmptyText(TextView textView) {
    }
}
