package com.neusoft.na.ehp.dbu;

import android.app.UiModeManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.neusoft.na.ehp.R;

/* JADX INFO: loaded from: classes.dex */
public class LaunchFragment extends Fragment {
    private static final String TAG = "NeuEhpLaunch";
    private Button btn_cancel;
    private Button btn_update;
    private TextView tvContent;
    private TextView tvTitle;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_launch, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Log.i(TAG, "onViewCreated");
        this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        this.tvContent = (TextView) view.findViewById(R.id.tvContent);
        this.btn_update = (Button) view.findViewById(R.id.btn_update);
        Button button = (Button) view.findViewById(R.id.btn_cancel);
        this.btn_cancel = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.ehp.dbu.LaunchFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.m60lambda$onViewCreated$0$comneusoftnaehpdbuLaunchFragment(view2);
            }
        });
        this.btn_update.setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.ehp.dbu.LaunchFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.m61lambda$onViewCreated$1$comneusoftnaehpdbuLaunchFragment(view2);
            }
        });
        changeTheme(2 == ((UiModeManager) getActivity().getSystemService("uimode")).getNightMode());
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$0$com-neusoft-na-ehp-dbu-LaunchFragment, reason: not valid java name */
    /* synthetic */ void m60lambda$onViewCreated$0$comneusoftnaehpdbuLaunchFragment(View view) {
        getActivity().finish();
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$1$com-neusoft-na-ehp-dbu-LaunchFragment, reason: not valid java name */
    /* synthetic */ void m61lambda$onViewCreated$1$comneusoftnaehpdbuLaunchFragment(View view) {
        Log.i(TAG, "startDbUpdate");
        ((DialogActivity) getActivity()).startDbUpdate();
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Log.i(TAG, "LaunchFragment onConfigurationChanged: " + configuration.uiMode);
        changeTheme((configuration.uiMode & 48) == 32);
    }

    private void changeTheme(boolean z) {
        TextView textView = this.tvTitle;
        FragmentActivity activity = getActivity();
        textView.setTextColor(z ? activity.getColor(R.color.pop_text_color_black_night) : activity.getColor(R.color.pop_text_color_black));
        TextView textView2 = this.tvContent;
        FragmentActivity activity2 = getActivity();
        textView2.setTextColor(z ? activity2.getColor(R.color.pop_text_color_black_night) : activity2.getColor(R.color.pop_text_color_black));
        this.btn_cancel.setTextColor(z ? getActivity().getColor(R.color.pop_text_color_black_night) : getActivity().getColor(R.color.pop_text_color_black));
        this.btn_cancel.setBackgroundResource(z ? R.drawable.gray_btn_bg : R.drawable.white_btn_bg);
    }
}
