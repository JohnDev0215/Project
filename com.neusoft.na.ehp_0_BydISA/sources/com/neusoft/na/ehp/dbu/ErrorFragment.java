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
public class ErrorFragment extends Fragment {
    private static final String ARG_PARAM = "value";
    private static final String ARG_PARAM2 = "type";
    private static final String TAG = "NeuEhpError";
    private Button btn_ok;
    private int mErrorType;
    private int mUpdateType;
    private TextView tv_content;

    public static ErrorFragment newInstance(int i, int i2) {
        ErrorFragment errorFragment = new ErrorFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PARAM, i);
        bundle.putInt(ARG_PARAM2, i2);
        errorFragment.setArguments(bundle);
        return errorFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.mErrorType = getArguments().getInt(ARG_PARAM);
            this.mUpdateType = getArguments().getInt(ARG_PARAM2);
        }
        if (bundle == null || !bundle.containsKey(ARG_PARAM)) {
            return;
        }
        Log.i(TAG, "onCreate: savedInstanceState");
        this.mErrorType = bundle.getInt(ARG_PARAM);
        this.mUpdateType = bundle.getInt(ARG_PARAM2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        Log.i(TAG, "onSaveInstanceState");
        bundle.putInt(ARG_PARAM, this.mErrorType);
        bundle.putInt(ARG_PARAM2, this.mUpdateType);
        super.onSaveInstanceState(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_error, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.tv_content = (TextView) view.findViewById(R.id.tv_content);
        this.btn_ok = (Button) view.findViewById(R.id.btn_ok);
        view.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.ehp.dbu.ErrorFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.m59lambda$onViewCreated$0$comneusoftnaehpdbuErrorFragment(view2);
            }
        });
        updateText();
        changeTheme(2 == ((UiModeManager) getActivity().getSystemService("uimode")).getNightMode());
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$0$com-neusoft-na-ehp-dbu-ErrorFragment, reason: not valid java name */
    /* synthetic */ void m59lambda$onViewCreated$0$comneusoftnaehpdbuErrorFragment(View view) {
        Log.i(TAG, "onViewCreated: finish");
        getActivity().finish();
    }

    public void setErrorType(int i, int i2) {
        Log.i(TAG, "setErrorType: " + i);
        this.mErrorType = i;
        this.mUpdateType = i2;
        updateText();
    }

    private void updateText() {
        String str;
        if (this.tv_content == null) {
            return;
        }
        int i = this.mErrorType;
        if (i == 1) {
            str = "Status Error. \nPlease contact the Dealer. \nError code:1";
        } else if (i == 2 || i == 3) {
            str = "Invalid map file.\nError code:" + this.mErrorType;
        } else if (i == 4) {
            str = this.mUpdateType == 1 ? "Map update completed.\nNew database will be applied." : "Map update completed.\nNew database will be applied after next restart.";
        } else if (i == 5) {
            str = "Not enough space. \nError code:5";
        } else if (i == 7) {
            str = "Map data in USB is not applicable to ISA.\n Error code:7";
        } else if (i != 8) {
            str = i != 11 ? "Unknown Error.\nError code:" + this.mErrorType : "Online database updating in the background. Please wait.";
        } else {
            str = "The version of map file in USB is lower than or the same as the existing version. \nError code:8";
        }
        this.tv_content.setText(str);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Log.i(TAG, "ErrorFragment onConfigurationChanged: " + configuration.uiMode);
        changeTheme((configuration.uiMode & 48) == 32);
    }

    private void changeTheme(boolean z) {
        FragmentActivity activity;
        int i;
        TextView textView = this.tv_content;
        if (z) {
            activity = getActivity();
            i = R.color.pop_text_color_black_night;
        } else {
            activity = getActivity();
            i = R.color.pop_text_color_black;
        }
        textView.setTextColor(activity.getColor(i));
    }
}
