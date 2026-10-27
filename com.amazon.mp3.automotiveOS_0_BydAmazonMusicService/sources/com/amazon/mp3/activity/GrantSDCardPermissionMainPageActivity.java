package com.amazon.mp3.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.UniqueCodeUtil;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class GrantSDCardPermissionMainPageActivity extends AppCompatActivity {
    private Uri tracksUri;
    private static final int SDCARD_PERMISSION_STEP_PAGE_REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    public static final int SDCARD_PERMISSION_MAIN_PAGE_REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        Objects.requireNonNull(extras);
        this.tracksUri = Uri.parse(extras.getString("com.amazon.mp3.tracksuri"));
        if (!PlatformUtil.isFireOS()) {
            setContentView(R.layout.activity_grant_sdcard_permission_main_page);
            Button button = (Button) findViewById(R.id.cancel_button);
            Button button2 = (Button) findViewById(R.id.confirm_button);
            button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.activity.GrantSDCardPermissionMainPageActivity$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onCreate$0(view);
                }
            });
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.activity.GrantSDCardPermissionMainPageActivity$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onCreate$1(view);
                }
            });
            return;
        }
        showGrantSDCardPermissionDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        finishActivity();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        startActivityForResult(new Intent(this, (Class<?>) GrantSDCardPermissionStepPageActivity.class), SDCARD_PERMISSION_STEP_PAGE_REQUEST_CODE);
    }

    private void showGrantSDCardPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.amazon.mp3.activity.GrantSDCardPermissionMainPageActivity$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                this.f$0.lambda$showGrantSDCardPermissionDialog$2(dialogInterface);
            }
        });
        builder.setTitle(R.string.sdcard_storage_permission_activity_hint_main_page_title);
        builder.setMessage(R.string.sdcard_storage_permission_activity_hint_main_page_content);
        builder.setNegativeButton(R.string.sdcard_storage_permission_activity_cancel, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.GrantSDCardPermissionMainPageActivity$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$showGrantSDCardPermissionDialog$3(dialogInterface, i);
            }
        });
        builder.setPositiveButton(R.string.sdcard_storage_permission_activity_confirm, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.GrantSDCardPermissionMainPageActivity$$ExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$showGrantSDCardPermissionDialog$4(dialogInterface, i);
            }
        });
        builder.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showGrantSDCardPermissionDialog$2(DialogInterface dialogInterface) {
        finishActivity();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showGrantSDCardPermissionDialog$3(DialogInterface dialogInterface, int i) {
        finishActivity();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showGrantSDCardPermissionDialog$4(DialogInterface dialogInterface, int i) {
        startActivityForResult(new Intent(this, (Class<?>) GrantSDCardPermissionStepPageActivity.class), SDCARD_PERMISSION_STEP_PAGE_REQUEST_CODE);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        finishActivity();
    }

    private void finishActivity() {
        Intent intent = new Intent();
        intent.putExtra("com.amazon.mp3.tracksuri", this.tracksUri.toString());
        setResult(SDCARD_PERMISSION_MAIN_PAGE_REQUEST_CODE, intent);
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        finishActivity();
    }
}
