package com.android.permissioncontroller.permission.ui.handheld;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.preference.PreferenceFragmentCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.utils.Utils;
import com.android.settingslib.widget.ActionBarShadowController;

/* JADX INFO: loaded from: classes8.dex */
public abstract class PermissionsFrameFragment extends PreferenceFragmentCompat {
    private static final String LOG_TAG = PermissionsFrameFragment.class.getSimpleName();
    static final int MENU_ALL_PERMS = 2;
    static final int MENU_HIDE_SYSTEM = 4;
    static final int MENU_SHOW_SYSTEM = 3;
    private TextView mEmptyView;
    private boolean mIsLoading;
    private View mLoadingView;
    private ViewGroup mPreferencesContainer;
    private ViewGroup mPrefsView;
    private View mProgressHeader;
    private View mProgressView;
    protected boolean mUseShadowController = true;

    protected final ViewGroup getPreferencesContainer() {
        return this.mPreferencesContainer;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        Utils.prepareSearchMenuItem(menu, requireContext());
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.permissions_frame, container, false);
        ViewGroup viewGroup = (ViewGroup) rootView.findViewById(R.id.prefs_container);
        this.mPrefsView = viewGroup;
        if (viewGroup == null) {
            this.mPrefsView = rootView;
        }
        TextView textView = (TextView) this.mPrefsView.findViewById(R.id.no_permissions);
        this.mEmptyView = textView;
        textView.setText(getEmptyViewString());
        this.mLoadingView = rootView.findViewById(R.id.loading_container);
        this.mPreferencesContainer = (ViewGroup) super.onCreateView(inflater, this.mPrefsView, savedInstanceState);
        setLoading(this.mIsLoading, false, true);
        this.mPrefsView.addView(this.mPreferencesContainer, 0);
        this.mProgressHeader = rootView.requireViewById(R.id.progress_bar_animation);
        this.mProgressView = rootView.requireViewById(R.id.progress_bar_background);
        setProgressBarVisible(false);
        getListView().setFocusable(false);
        return rootView;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (getListView() != null) {
            ActionBar ab = getActivity().getActionBar();
            if (ab != null) {
                ab.setElevation(0.0f);
            }
            if (this.mUseShadowController) {
                ActionBarShadowController.attachToView(getActivity(), getLifecycle(), getListView());
            }
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String s) {
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

    protected void setProgressBarVisible(boolean visible) {
        this.mProgressHeader.setVisibility(visible ? 0 : 8);
        this.mProgressView.setVisibility(visible ? 0 : 8);
    }

    void updateEmptyState() {
        RecyclerView prefs = getListView();
        if (this.mEmptyView != null && prefs != null) {
            if (prefs.getAdapter() != null && prefs.getAdapter().getItemCount() != 0) {
                this.mEmptyView.setVisibility(8);
                prefs.setVisibility(0);
            } else {
                this.mEmptyView.setVisibility(0);
                prefs.setVisibility(8);
            }
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    protected void onBindPreferences() {
        super.onBindPreferences();
        RecyclerView.Adapter adapter = getListView().getAdapter();
        if (adapter != null) {
            adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() { // from class: com.android.permissioncontroller.permission.ui.handheld.PermissionsFrameFragment.1
                @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
                public void onChanged() {
                    PermissionsFrameFragment.this.updateEmptyState();
                }

                @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
                public void onItemRangeInserted(int positionStart, int itemCount) {
                    PermissionsFrameFragment.this.updateEmptyState();
                }

                @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
                public void onItemRangeRemoved(int positionStart, int itemCount) {
                    PermissionsFrameFragment.this.updateEmptyState();
                }
            });
        }
        updateEmptyState();
    }

    private void setViewShown(final View view, boolean shown, boolean animate) {
        if (animate) {
            Animation animation = AnimationUtils.loadAnimation(getContext(), shown ? android.R.anim.fade_in : android.R.anim.fade_out);
            if (shown) {
                view.setVisibility(0);
            } else {
                animation.setAnimationListener(new Animation.AnimationListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.PermissionsFrameFragment.2
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

    public int getEmptyViewString() {
        return R.string.no_permissions;
    }
}
