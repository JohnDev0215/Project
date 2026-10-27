package com.amazon.mp3.fragment.navigation;

import androidx.fragment.app.Fragment;
import com.amazon.mp3.activity.navigation.FragmentController;

/* JADX INFO: loaded from: classes3.dex */
public interface NavigationProvider {
    void changeScreenFragment(Fragment fragment, boolean addToBackStack, boolean forceReplace, boolean animate);

    FragmentController getFragmentController();
}
