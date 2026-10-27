package com.android.car.developeroptions.biometrics.face;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.hardware.face.Face;
import android.hardware.face.FaceManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.widget.LayoutPreference;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FaceSettingsRemoveButtonPreferenceController extends BasePreferenceController implements View.OnClickListener {
    private static final String KEY = "security_settings_face_delete_faces_container";
    private static final String TAG = "FaceSettings/Remove";
    private SettingsActivity mActivity;
    private Button mButton;
    private final Context mContext;
    private final FaceManager mFaceManager;
    private Listener mListener;
    private final DialogInterface.OnClickListener mOnClickListener;
    private final FaceManager.RemovalCallback mRemovalCallback;
    private int mUserId;

    interface Listener {
        void onRemoved();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return KEY;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public static class ConfirmRemoveDialog extends InstrumentedDialogFragment {
        private DialogInterface.OnClickListener mOnClickListener;

        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 1693;
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.security_settings_face_settings_remove_dialog_title).setMessage(R.string.security_settings_face_settings_remove_dialog_details).setPositiveButton(R.string.okay, this.mOnClickListener).setNegativeButton(R.string.cancel, this.mOnClickListener);
            AlertDialog alertDialogCreate = builder.create();
            alertDialogCreate.setCanceledOnTouchOutside(false);
            return alertDialogCreate;
        }

        public void setOnClickListener(DialogInterface.OnClickListener onClickListener) {
            this.mOnClickListener = onClickListener;
        }
    }

    public FaceSettingsRemoveButtonPreferenceController(Context context, String str) {
        super(context, str);
        this.mRemovalCallback = new FaceManager.RemovalCallback() { // from class: com.android.car.developeroptions.biometrics.face.FaceSettingsRemoveButtonPreferenceController.1
            public void onRemovalError(Face face, int i, CharSequence charSequence) {
                Log.e(FaceSettingsRemoveButtonPreferenceController.TAG, "Unable to remove face: " + face.getBiometricId() + " error: " + i + " " + ((Object) charSequence));
                Toast.makeText(FaceSettingsRemoveButtonPreferenceController.this.mContext, charSequence, 0).show();
            }

            public void onRemovalSucceeded(Face face, int i) {
                if (i == 0) {
                    if (!FaceSettingsRemoveButtonPreferenceController.this.mFaceManager.getEnrolledFaces(FaceSettingsRemoveButtonPreferenceController.this.mUserId).isEmpty()) {
                        FaceSettingsRemoveButtonPreferenceController.this.mButton.setEnabled(true);
                        return;
                    } else {
                        FaceSettingsRemoveButtonPreferenceController.this.mListener.onRemoved();
                        return;
                    }
                }
                Log.v(FaceSettingsRemoveButtonPreferenceController.TAG, "Remaining: " + i);
            }
        };
        this.mOnClickListener = new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.biometrics.face.FaceSettingsRemoveButtonPreferenceController.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == -1) {
                    List enrolledFaces = FaceSettingsRemoveButtonPreferenceController.this.mFaceManager.getEnrolledFaces(FaceSettingsRemoveButtonPreferenceController.this.mUserId);
                    if (enrolledFaces.isEmpty()) {
                        Log.e(FaceSettingsRemoveButtonPreferenceController.TAG, "No faces");
                        return;
                    }
                    if (enrolledFaces.size() > 1) {
                        Log.e(FaceSettingsRemoveButtonPreferenceController.TAG, "Multiple enrollments: " + enrolledFaces.size());
                    }
                    FaceSettingsRemoveButtonPreferenceController.this.mFaceManager.remove((Face) enrolledFaces.get(0), FaceSettingsRemoveButtonPreferenceController.this.mUserId, FaceSettingsRemoveButtonPreferenceController.this.mRemovalCallback);
                    return;
                }
                FaceSettingsRemoveButtonPreferenceController.this.mButton.setEnabled(true);
            }
        };
        this.mContext = context;
        this.mFaceManager = (FaceManager) context.getSystemService(FaceManager.class);
    }

    public FaceSettingsRemoveButtonPreferenceController(Context context) {
        this(context, KEY);
    }

    public void setUserId(int i) {
        this.mUserId = i;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        Button button = (Button) ((LayoutPreference) preference).findViewById(R.id.security_settings_face_settings_remove_button);
        this.mButton = button;
        button.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Button button = this.mButton;
        if (view == button) {
            button.setEnabled(false);
            ConfirmRemoveDialog confirmRemoveDialog = new ConfirmRemoveDialog();
            confirmRemoveDialog.setOnClickListener(this.mOnClickListener);
            confirmRemoveDialog.show(this.mActivity.getSupportFragmentManager(), ConfirmRemoveDialog.class.getName());
        }
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    public void setActivity(SettingsActivity settingsActivity) {
        this.mActivity = settingsActivity;
    }
}
