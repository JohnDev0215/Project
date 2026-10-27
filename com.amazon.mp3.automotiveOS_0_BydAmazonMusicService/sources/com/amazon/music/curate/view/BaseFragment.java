package com.amazon.music.curate.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J!\u0010\u000b\u001a\u00020\f2\u0012\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\"\u00020\u0005H\u0004¢\u0006\u0002\u0010\tJ\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J&\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0016\u001a\u00020\fH\u0016J\b\u0010\u0017\u001a\u00020\fH\u0016J\b\u0010\u0018\u001a\u00020\fH\u0016J\b\u0010\u0019\u001a\u00020\fH\u0016R&\u0010\u0003\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/curate/view/BaseFragment;", "Landroidx/fragment/app/Fragment;", "()V", "callbacks", "", "Lcom/amazon/music/curate/view/FragmentCallbacks;", "getCallbacks", "()[Lcom/amazon/music/curate/view/FragmentCallbacks;", "setCallbacks", "([Lcom/amazon/music/curate/view/FragmentCallbacks;)V", "[Lcom/amazon/music/curate/view/FragmentCallbacks;", "addFragmentCallbacks", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onDestroyView", "onPause", "onResume", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class BaseFragment extends Fragment {
    private FragmentCallbacks[] callbacks;

    public final FragmentCallbacks[] getCallbacks() {
        return this.callbacks;
    }

    public final void setCallbacks(FragmentCallbacks[] fragmentCallbacksArr) {
        this.callbacks = fragmentCallbacksArr;
    }

    protected final void addFragmentCallbacks(FragmentCallbacks... callbacks) {
        Intrinsics.checkNotNullParameter(callbacks, "callbacks");
        this.callbacks = callbacks;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentCallbacks[] fragmentCallbacksArr = this.callbacks;
        if (fragmentCallbacksArr == null) {
            return;
        }
        int i = 0;
        int length = fragmentCallbacksArr.length;
        while (i < length) {
            FragmentCallbacks fragmentCallbacks = fragmentCallbacksArr[i];
            i++;
            fragmentCallbacks.onCreate(savedInstanceState);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
        FragmentCallbacks[] fragmentCallbacksArr = this.callbacks;
        if (fragmentCallbacksArr != null) {
            int i = 0;
            int length = fragmentCallbacksArr.length;
            while (i < length) {
                FragmentCallbacks fragmentCallbacks = fragmentCallbacksArr[i];
                i++;
                fragmentCallbacks.onCreateView(inflater, container, savedInstanceState);
            }
        }
        return viewOnCreateView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentCallbacks[] fragmentCallbacksArr = this.callbacks;
        if (fragmentCallbacksArr == null) {
            return;
        }
        int i = 0;
        int length = fragmentCallbacksArr.length;
        while (i < length) {
            FragmentCallbacks fragmentCallbacks = fragmentCallbacksArr[i];
            i++;
            fragmentCallbacks.onResume();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        FragmentCallbacks[] fragmentCallbacksArr = this.callbacks;
        if (fragmentCallbacksArr != null) {
            int i = 0;
            int length = fragmentCallbacksArr.length;
            while (i < length) {
                FragmentCallbacks fragmentCallbacks = fragmentCallbacksArr[i];
                i++;
                fragmentCallbacks.onPause();
            }
        }
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        FragmentCallbacks[] fragmentCallbacksArr = this.callbacks;
        if (fragmentCallbacksArr != null) {
            int i = 0;
            int length = fragmentCallbacksArr.length;
            while (i < length) {
                FragmentCallbacks fragmentCallbacks = fragmentCallbacksArr[i];
                i++;
                fragmentCallbacks.onDestroyView();
            }
        }
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        FragmentCallbacks[] fragmentCallbacksArr = this.callbacks;
        if (fragmentCallbacksArr != null) {
            int i = 0;
            int length = fragmentCallbacksArr.length;
            while (i < length) {
                FragmentCallbacks fragmentCallbacks = fragmentCallbacksArr[i];
                i++;
                fragmentCallbacks.onDestroy();
            }
        }
        super.onDestroy();
    }
}
