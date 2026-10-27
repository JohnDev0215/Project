package com.amazon.mp3.library.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ToggleButton;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.customerprofile.CustomerProfileDetailManager;
import com.amazon.mp3.playlist.CreatePlaylistDialog;
import com.amazon.mp3.playlist.CreatePublicPlaylistWarningDialogFragment;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;

/* JADX INFO: loaded from: classes3.dex */
public class CreateOrRenamePlaylistActivity extends DialogActivity implements CreatePlaylistDialog {
    public static final int REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    private Dialog mDialog;
    private EditText mEditText;
    private long mId;
    private int mMode;
    private String mName;
    private PageType mPageType;
    private SharedPreferences mSharedPreferences;
    private ToggleButton mToggleButton;
    private final CustomerProfileDetailManager profileManager = CustomerProfileDetailManager.getInstance();
    private final DialogInterface.OnShowListener mOnDialogShowListener = new DialogInterface.OnShowListener() { // from class: com.amazon.mp3.library.activity.CreateOrRenamePlaylistActivity.1
        @Override // android.content.DialogInterface.OnShowListener
        public void onShow(DialogInterface dialog) {
            if (CreateOrRenamePlaylistActivity.this.mName == null || CreateOrRenamePlaylistActivity.this.mName.length() <= 0) {
                if (CreateOrRenamePlaylistActivity.this.mEditText.getText().toString().trim().length() == 0 && (CreateOrRenamePlaylistActivity.this.mDialog instanceof AlertDialog)) {
                    ((AlertDialog) CreateOrRenamePlaylistActivity.this.mDialog).getButton(-1).setEnabled(false);
                }
            } else {
                CreateOrRenamePlaylistActivity.this.mEditText.setText(CreateOrRenamePlaylistActivity.this.mName);
                CreateOrRenamePlaylistActivity.this.mEditText.selectAll();
            }
            ((InputMethodManager) CreateOrRenamePlaylistActivity.this.getSystemService("input_method")).showSoftInput(CreateOrRenamePlaylistActivity.this.mEditText, 0);
        }
    };

    public static Intent getStartIntentForCreate(Context context, PageType pageType) {
        Intent intent = new Intent(context, (Class<?>) CreateOrRenamePlaylistActivity.class);
        intent.putExtra("com.amazon.mp3.library.activity.playlist.EXTRA_PLAYLIST_MODE", 0);
        intent.putExtra("com.amazon.mp3.library.activity.EXTRA_PAGE_TYPE", pageType);
        return intent;
    }

    @Override // com.amazon.mp3.activity.DialogActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        this.mMode = intent.getIntExtra("com.amazon.mp3.library.activity.playlist.EXTRA_PLAYLIST_MODE", 0);
        this.mName = intent.getStringExtra("com.amazon.mp3.library.activity.playlist.EXTRA_PLAYLIST_NAME");
        this.mId = intent.getLongExtra("com.amazon.mp3.library.activity.playlist.EXTRA_PLAYLIST_ID", -1L);
        this.mPageType = (PageType) intent.getSerializableExtra("com.amazon.mp3.library.activity.EXTRA_PAGE_TYPE");
        this.mSharedPreferences = getSharedPreferences("com.amazon.mp3.CreatePlaylistDialogFragment", 0);
        String str = this.mName;
        if (str != null) {
            this.mName = str.trim();
        }
        setResult(0);
        getWindow().setBackgroundDrawable(new ColorDrawable(537397256));
    }

    @Override // com.amazon.mp3.activity.DialogActivity
    protected Dialog createDialog() {
        Dialog dialog = getDialog(this);
        this.mDialog = dialog;
        dialog.setOnShowListener(this.mOnDialogShowListener);
        return this.mDialog;
    }

    @Override // com.amazon.mp3.playlist.CreatePlaylistDialog
    public void onPositiveButtonClicked() {
        String string = this.mEditText.getText().toString();
        int i = this.mMode;
        if (i == 1) {
            if (string.equals(this.mName)) {
                return;
            }
            Intent intent = new Intent();
            intent.putExtra("com.amazon.mp3.library.activity.playlist.EXTRA_PLAYLIST_MODE", this.mMode);
            intent.putExtra("com.amazon.mp3.library.activity.playlist.EXTRA_PLAYLIST_NAME", string);
            intent.putExtra("com.amazon.mp3.library.activity.playlist.EXTRA_PLAYLIST_ID", this.mId);
            setResult(-1, intent);
            return;
        }
        if (i == 0) {
            if (AmazonApplication.getCapabilities().isPlaylistCreationPublicToggleEnabled()) {
                ToggleButton toggleButton = this.mToggleButton;
                if (toggleButton == null || !toggleButton.isChecked()) {
                    setPublicPlaylistCreationToggleSavedState(Boolean.FALSE);
                } else {
                    setPublicPlaylistCreationToggleSavedState(Boolean.TRUE);
                }
                if ("PRIVATE".equals(this.profileManager.getProfileStatus()) && !hasSeenPublicPlaylistCreationWarning().booleanValue()) {
                    CreatePublicPlaylistWarningDialogFragment.INSTANCE.create().build(this.mEditText.getText().toString(), this.mPageType, false).show(getSupportFragmentManager(), CreatePublicPlaylistWarningDialogFragment.class.getSimpleName());
                    return;
                } else {
                    createPlaylist(string);
                    finish();
                    return;
                }
            }
            createPlaylist(string);
            finish();
        }
    }

    @Override // com.amazon.mp3.playlist.CreatePlaylistDialog
    public void onNegativeButtonClicked() {
        finish();
    }

    public Dialog getDialog(Context context) {
        return CreatePlaylistDialog.DefaultImpls.getDialog(this, context);
    }

    @Override // com.amazon.mp3.playlist.CreatePlaylistDialog
    public void onEditTextInitialized(EditText editText) {
        this.mEditText = editText;
        if (Build.VERSION.SDK_INT >= 29) {
            if (AmazonApplication.getCapabilities().isSonicRushEnabled()) {
                this.mEditText.setTextCursorDrawable(R.drawable.sr_on_cursor);
            } else {
                this.mEditText.setTextCursorDrawable(R.drawable.sr_off_cursor);
            }
        }
    }

    @Override // com.amazon.mp3.playlist.CreatePlaylistDialog
    public void onPublicToggleInitialized(ToggleButton toggleButton) {
        this.mToggleButton = toggleButton;
        toggleButton.setChecked(Boolean.valueOf(getSharedPreferences("com.amazon.mp3.CreatePlaylistDialogFragment", 0).getBoolean(getString(R.string.playlist_creation_public_toggle_last_state), true)).booleanValue());
    }

    @Override // com.amazon.mp3.playlist.CreatePlaylistDialog
    public boolean isCreatePlaylist() {
        return this.mMode == 0;
    }

    @Override // com.amazon.mp3.playlist.CreatePlaylistDialog
    public AlertDialog.Builder getAlertDialogBuilder() {
        return new AlertDialog.Builder(this);
    }

    @Override // com.amazon.mp3.playlist.CreatePlaylistDialog
    public void onDismissed() {
        overridePendingTransition(0, 0);
    }

    private Boolean hasSeenPublicPlaylistCreationWarning() {
        return Boolean.valueOf(this.mSharedPreferences.getBoolean(getString(R.string.playlist_creation_flow_public_toggle_warning_shown), false));
    }

    private void setPublicPlaylistCreationToggleSavedState(Boolean stateToSave) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putBoolean(getString(R.string.playlist_creation_public_toggle_last_state), stateToSave.booleanValue());
        editorEdit.apply();
    }

    private void createPlaylist(String newName) {
        Intent intent = new Intent();
        intent.putExtra("com.amazon.mp3.library.activity.playlist.EXTRA_PLAYLIST_MODE", this.mMode);
        intent.putExtra("com.amazon.mp3.library.activity.playlist.EXTRA_PLAYLIST_NAME", newName);
        setResult(-1, intent);
    }
}
