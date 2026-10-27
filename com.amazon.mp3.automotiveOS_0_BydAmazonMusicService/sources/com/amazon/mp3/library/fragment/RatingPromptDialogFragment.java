package com.amazon.mp3.library.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.ratingprompt.actions.UserRatingStatus;
import com.amazon.mp3.ratingprompt.policy.RatingPromptPolicy;
import com.amazon.mp3.util.health.AppHealthUtil;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;

/* JADX INFO: loaded from: classes3.dex */
public class RatingPromptDialogFragment extends DialogFragment {
    private static boolean sWasShownSinceLaunch;
    private RatingPromptPolicy mRatingPromptPolicy;

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, 0);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        this.mRatingPromptPolicy = new RatingPromptPolicy(defaultSharedPreferences, new AppHealthUtil(defaultSharedPreferences));
        setWasShownSinceLaunch(true);
    }

    private static void setWasShownSinceLaunch(boolean wasShownSinceLaunch) {
        sWasShownSinceLaunch = wasShownSinceLaunch;
    }

    public static boolean wasShownSinceLaunch() {
        return sWasShownSinceLaunch;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.app_rating_dialog, container, false);
        Button button = (Button) viewInflate.findViewById(R.id.app_rating_rate_button);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.RatingPromptDialogFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                UserInteractionAppEvent.builder(ActionType.GO_APP_RATING.getMetricsValue()).publish();
                RatingPromptDialogFragment.this.onAcceptedClicked();
                RatingPromptDialogFragment.this.getDialog().dismiss();
            }
        });
        viewInflate.findViewById(R.id.app_rating_not_now_button).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.RatingPromptDialogFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                UserInteractionAppEvent.builder(ActionType.SELECT_DEFER_APP_RATING.getMetricsValue()).publish();
                RatingPromptDialogFragment.this.onNotNowClicked();
                RatingPromptDialogFragment.this.getDialog().dismiss();
            }
        });
        viewInflate.findViewById(R.id.app_rating_dismiss_button).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.RatingPromptDialogFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                UserInteractionAppEvent.builder(ActionType.SELECT_NO_APP_RATING.getMetricsValue()).publish();
                RatingPromptDialogFragment.this.getDialog().cancel();
            }
        });
        if (AmazonApplication.BETA) {
            TextView textView = (TextView) viewInflate.findViewById(R.id.title);
            TextView textView2 = (TextView) viewInflate.findViewById(R.id.body);
            textView.setText(getText(R.string.dmusic_beta_rating_dialog_title));
            textView2.setText(getText(R.string.dmusic_beta_rating_dialog_body));
            button.setText(getText(R.string.dmusic_beta_rating_dialog_positive_button));
        }
        this.mRatingPromptPolicy.updateUserRatingStatus(UserRatingStatus.REJECTED);
        return viewInflate;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        this.mRatingPromptPolicy.updateUserRatingStatus(UserRatingStatus.REJECTED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAcceptedClicked() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("market://details?id=" + getActivity().getPackageName()));
        startActivity(intent);
        this.mRatingPromptPolicy.updateUserRatingStatus(UserRatingStatus.ACCEPTED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNotNowClicked() {
        this.mRatingPromptPolicy.updateUserRatingStatus(UserRatingStatus.DEFERRED);
    }
}
