package com.amazon.mp3.library.fragment;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import com.amazon.mp3.actionbar.view.ActionBarSourceToggleView;
import com.amazon.mp3.activity.GrantPermissionActivity;
import com.amazon.mp3.activity.GrantStoragePermissionActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class SourceToggleDialogFragment extends DialogFragment {
    private static final String TAG = SourceToggleDialogFragment.class.getSimpleName();
    private ActionBarSourceToggleView mSourceToggleView;

    public static SourceToggleDialogFragment newInstance() {
        return new SourceToggleDialogFragment();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, 0);
    }

    @Override // androidx.fragment.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View viewFindViewById;
        if (BauhausDialogBuilder.INSTANCE.isBauhausDialogEnabled()) {
            View toggleView = getToggleView();
            if (toggleView != null && (viewFindViewById = toggleView.findViewById(R.id.title)) != null) {
                viewFindViewById.setBackgroundColor(0);
            }
            return new BauhausDialogBuilder(requireContext()).setView(toggleView).create();
        }
        return super.onCreateDialog(savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (BauhausDialogBuilder.INSTANCE.isBauhausDialogEnabled()) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
        getDialog().setTitle(R.string.dmusic_library_source_dialog_header);
        return getToggleView();
    }

    private View getToggleView() {
        final AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        if (appCompatActivity.getSupportActionBar().getCustomView() instanceof ActionBarSourceToggleView) {
            this.mSourceToggleView = (ActionBarSourceToggleView) appCompatActivity.getSupportActionBar().getCustomView();
            final View viewInflate = LayoutInflater.from(requireContext()).inflate(R.layout.source_toggle_dialog, (ViewGroup) null);
            selectCurrentSource(viewInflate);
            viewInflate.findViewById(R.id.cloudContainer).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.SourceToggleDialogFragment.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    SourceToggleDialogFragment.this.mSourceToggleView.onSourceToggled(ActionBarSourceToggleView.Source.CLOUD);
                    SourceToggleDialogFragment.this.selectCurrentSource(viewInflate);
                    LastViewedScreenUtil.setLastViewed(SourceToggleDialogFragment.this.getActivity(), LastViewedScreenUtil.LastViewedScreen.LIBRARY, LastViewedScreenUtil.LastViewedSource.fromCirrusMediaSource("cirrus"));
                    SourceToggleDialogFragment.this.getDialog().dismiss();
                }
            });
            viewInflate.findViewById(R.id.deviceContainer).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.SourceToggleDialogFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    SourceToggleDialogFragment.this.mSourceToggleView.onSourceToggled(ActionBarSourceToggleView.Source.DEVICE);
                    SourceToggleDialogFragment.this.selectCurrentSource(viewInflate);
                    LastViewedScreenUtil.setLastViewed(SourceToggleDialogFragment.this.getActivity(), LastViewedScreenUtil.LastViewedScreen.LIBRARY, LastViewedScreenUtil.LastViewedSource.fromCirrusMediaSource("cirrus-local"));
                    SourceToggleDialogFragment.this.getDialog().dismiss();
                    GrantStoragePermissionActivity.requestIfNotGranted(SourceToggleDialogFragment.this.getContext(), MusicDownloader.PERMISSIONS_FOR_DOWNLOAD, new GrantPermissionActivity.PermissionResultReceiver() { // from class: com.amazon.mp3.library.fragment.SourceToggleDialogFragment.2.1
                        @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
                        protected void onGranted() {
                            SharedPreferences sharedPreferences = appCompatActivity.getSharedPreferences(SourceToggleDialogFragment.class.getSimpleName(), 0);
                            if (sharedPreferences.getBoolean("first_time_offline_permission_granted_key", true)) {
                                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                                editorEdit.putBoolean("first_time_offline_permission_granted_key", false);
                                editorEdit.apply();
                                SyncService.startSync(appCompatActivity, 667);
                            }
                        }

                        @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
                        protected void onDenied(String[] permissions) {
                            Log.warning(SourceToggleDialogFragment.TAG, "Do not have permission to view offline files!");
                        }
                    });
                }
            });
            return viewInflate;
        }
        Log.error(TAG, "Source Toggle trying to access Action Bar View from " + appCompatActivity.getClass().getName());
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectCurrentSource(View layout) {
        ActionBarSourceToggleView.Source currentSource = this.mSourceToggleView.getCurrentSource();
        View viewFindViewById = layout.findViewById(R.id.cloudSelectedBar);
        View viewFindViewById2 = layout.findViewById(R.id.deviceSelectedBar);
        if (currentSource == ActionBarSourceToggleView.Source.CLOUD) {
            viewFindViewById.setVisibility(0);
            viewFindViewById2.setVisibility(4);
        } else {
            viewFindViewById.setVisibility(4);
            viewFindViewById2.setVisibility(0);
        }
    }
}
