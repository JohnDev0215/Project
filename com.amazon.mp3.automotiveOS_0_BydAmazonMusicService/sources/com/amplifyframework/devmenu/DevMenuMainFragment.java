package com.amplifyframework.devmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.amplifyframework.core.R;

/* JADX INFO: loaded from: classes5.dex */
public final class DevMenuMainFragment extends Fragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.dev_menu_fragment_main, viewGroup, false);
        viewInflate.findViewById(R.id.env_button).setOnClickListener(new View.OnClickListener() { // from class: com.amplifyframework.devmenu.DevMenuMainFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DevMenuMainFragment.lambda$onCreateView$0(view);
            }
        });
        viewInflate.findViewById(R.id.device_button).setOnClickListener(new View.OnClickListener() { // from class: com.amplifyframework.devmenu.DevMenuMainFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DevMenuMainFragment.lambda$onCreateView$1(view);
            }
        });
        viewInflate.findViewById(R.id.logs_button).setOnClickListener(new View.OnClickListener() { // from class: com.amplifyframework.devmenu.DevMenuMainFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DevMenuMainFragment.lambda$onCreateView$2(view);
            }
        });
        viewInflate.findViewById(R.id.file_issue_button).setOnClickListener(new View.OnClickListener() { // from class: com.amplifyframework.devmenu.DevMenuMainFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DevMenuMainFragment.lambda$onCreateView$3(view);
            }
        });
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onCreateView$0(View view) {
        Navigation.findNavController(view).navigate(R.id.show_env_info);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onCreateView$1(View view) {
        Navigation.findNavController(view).navigate(R.id.show_device_info);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onCreateView$2(View view) {
        Navigation.findNavController(view).navigate(R.id.show_logs);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onCreateView$3(View view) {
        Navigation.findNavController(view).navigate(R.id.show_file_issue);
    }
}
