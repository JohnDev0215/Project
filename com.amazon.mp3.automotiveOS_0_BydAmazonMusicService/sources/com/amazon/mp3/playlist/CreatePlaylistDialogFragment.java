package com.amazon.mp3.playlist;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.helper.CreatePlaylistHelper;
import com.amazon.mp3.catalog.fragment.viewmodel.CreatePlaylistResultViewModel;
import com.amazon.mp3.customerprofile.CustomerProfileDetailManager;
import com.amazon.mp3.playlist.CreatePlaylistDialog;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class CreatePlaylistDialogFragment extends DialogFragment implements CreatePlaylistDialog {
    private static final String TAG = CreatePlaylistDialogFragment.class.getSimpleName();
    private CreatePlaylistResultViewModel createPlaylistResultViewModel;
    private EditText mEditText;
    private PageType mPageType;
    private String mPlaylistId;
    private String mPlaylistName;
    private SharedPreferences mSharedPreferences;
    private ToggleButton mToggleButton;
    private final CustomerProfileDetailManager profileManager = CustomerProfileDetailManager.getInstance();

    @Override // com.amazon.mp3.playlist.CreatePlaylistDialog
    public void onDismissed() {
    }

    public static class Builder {
        private PageType mPageType;
        private String mPlaylistId;
        private String mPlaylistName;

        private Builder(final PageType pageType) {
            this.mPageType = pageType;
        }

        public CreatePlaylistDialogFragment build() {
            CreatePlaylistDialogFragment createPlaylistDialogFragment = new CreatePlaylistDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("playlistId", this.mPlaylistId);
            bundle.putString("playlistName", this.mPlaylistName);
            bundle.putSerializable("pageType", this.mPageType);
            createPlaylistDialogFragment.setArguments(bundle);
            return createPlaylistDialogFragment;
        }
    }

    public static Builder create(final PageType pageType) {
        return new Builder(pageType);
    }

    public static class Result {
        public final PageType mPageType;
        public final String mPlaylistId;
        public final String mPlaylistName;
        public final Boolean mPublicToggleState = Boolean.FALSE;

        public Result(final String playlistId, final String playlistName, final PageType pageType) {
            this.mPlaylistId = playlistId;
            this.mPlaylistName = playlistName;
            this.mPageType = pageType;
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPlaylistId = getArguments().getString("playlistId");
        this.mPlaylistName = getArguments().getString("playlistName");
        this.mPageType = (PageType) getArguments().getSerializable("pageType");
        this.createPlaylistResultViewModel = (CreatePlaylistResultViewModel) new ViewModelProvider(requireActivity()).get(CreatePlaylistResultViewModel.class);
        this.mSharedPreferences = requireActivity().getSharedPreferences("com.amazon.mp3.CreatePlaylistDialogFragment", 0);
    }

    @Override // androidx.fragment.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(@Nullable final Bundle savedInstanceState) {
        Dialog dialog = getDialog(requireContext());
        if (dialog instanceof AlertDialog) {
            final AlertDialog alertDialog = (AlertDialog) dialog;
            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.amazon.mp3.playlist.CreatePlaylistDialogFragment$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnShowListener
                public final void onShow(DialogInterface dialogInterface) {
                    this.f$0.lambda$onCreateDialog$0(alertDialog, dialogInterface);
                }
            });
        }
        dialog.getWindow().clearFlags(131080);
        dialog.getWindow().setSoftInputMode(4);
        return dialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateDialog$0(AlertDialog alertDialog, DialogInterface dialogInterface) {
        Button button = alertDialog.getButton(-1);
        button.setEnabled(false);
        button.setTextColor(getResources().getColor(R.color.create_new_playlist_color));
        alertDialog.getButton(-2).setTextColor(getResources().getColor(R.color.create_new_playlist_color));
    }

    public Dialog getDialog(Context context) {
        return CreatePlaylistDialog.DefaultImpls.getDialog(this, context);
    }

    @Override // com.amazon.mp3.playlist.CreatePlaylistDialog
    public void onPositiveButtonClicked() {
        onSaveButtonSelected();
        dismiss();
    }

    @Override // com.amazon.mp3.playlist.CreatePlaylistDialog
    public void onNegativeButtonClicked() {
        getDialog().cancel();
    }

    @Override // com.amazon.mp3.playlist.CreatePlaylistDialog
    public AlertDialog.Builder getAlertDialogBuilder() {
        return new AlertDialog.Builder(requireContext(), R.style.CreatePlaylistDialog);
    }

    @Override // com.amazon.mp3.playlist.CreatePlaylistDialog
    public boolean isCreatePlaylist() {
        return this.mPlaylistId == null;
    }

    @Override // com.amazon.mp3.playlist.CreatePlaylistDialog
    public void onEditTextInitialized(EditText editText) {
        this.mEditText = editText;
        String str = this.mPlaylistName;
        if (str != null) {
            editText.setText(str);
            this.mEditText.selectAll();
        }
    }

    @Override // com.amazon.mp3.playlist.CreatePlaylistDialog
    public void onPublicToggleInitialized(ToggleButton toggleButton) {
        this.mToggleButton = toggleButton;
        toggleButton.setChecked(Boolean.valueOf(this.mSharedPreferences.getBoolean(getString(R.string.playlist_creation_public_toggle_last_state), true)).booleanValue());
    }

    private void onSaveButtonSelected() {
        if (AmazonApplication.getCapabilities().isPlaylistCreationPublicToggleEnabled()) {
            ToggleButton toggleButton = this.mToggleButton;
            if (toggleButton == null || !toggleButton.isChecked()) {
                createPlaylist(this.mEditText.getText().toString(), Boolean.FALSE);
                return;
            } else if ("PRIVATE".equals(this.profileManager.getProfileStatus()) && !hasSeenPublicPlaylistCreationWarning().booleanValue()) {
                CreatePublicPlaylistWarningDialogFragment.INSTANCE.create().build(this.mEditText.getText().toString(), this.mPageType, true).show(requireActivity().getSupportFragmentManager(), CreatePublicPlaylistWarningDialogFragment.class.getSimpleName());
                return;
            } else {
                createPlaylist(this.mEditText.getText().toString(), Boolean.TRUE);
                return;
            }
        }
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            this.createPlaylistResultViewModel.setPlaylistInfoLiveData(new CreatePlaylistHelper.PlaylistInfo(this.mPlaylistId, this.mEditText.getText().toString(), this.mPageType));
        } else {
            EventBus.getDefault().post(new Result(this.mPlaylistId, this.mEditText.getText().toString(), this.mPageType));
        }
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.CREATE_PLAYLIST).withInteractionType(InteractionType.TAP).withPageType(this.mPageType).withEventTime(System.currentTimeMillis()).build());
    }

    private Boolean hasSeenPublicPlaylistCreationWarning() {
        return Boolean.valueOf(this.mSharedPreferences.getBoolean(requireContext().getString(R.string.playlist_creation_flow_public_toggle_warning_shown), false));
    }

    private void createPlaylist(String playlistName, Boolean isPublicToggleEnabled) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putBoolean(getString(R.string.playlist_creation_public_toggle_last_state), isPublicToggleEnabled.booleanValue());
        editorEdit.apply();
        new CreatePlaylistHelper(requireActivity()).createPlaylist(requireContext(), playlistName, isPublicToggleEnabled.booleanValue(), this.mPageType, new CreatePlaylistHelper.PlaylistCreationListener() { // from class: com.amazon.mp3.playlist.CreatePlaylistDialogFragment$$ExternalSyntheticLambda1
            @Override // com.amazon.mp3.catalog.fragment.helper.CreatePlaylistHelper.PlaylistCreationListener
            public final void onCompleted(boolean z) {
                this.f$0.lambda$createPlaylist$1(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createPlaylist$1(boolean z) {
        this.createPlaylistResultViewModel.getDidCreatePlaylistEntryInDb().postValue(Boolean.valueOf(z));
    }
}
