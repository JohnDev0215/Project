package com.android.car.developeroptions.deviceinfo;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.storage.DiskInfo;
import android.os.storage.StorageEventListener;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.R;
import com.android.settingslib.Utils;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.google.android.setupdesign.GlifLayout;
import java.text.NumberFormat;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class StorageWizardBase extends FragmentActivity {
    private FooterButton mBack;
    protected DiskInfo mDisk;
    private FooterBarMixin mFooterBarMixin;
    private FooterButton mNext;
    protected StorageManager mStorage;
    private final StorageEventListener mStorageListener = new StorageEventListener() { // from class: com.android.car.developeroptions.deviceinfo.StorageWizardBase.1
        public void onDiskDestroyed(DiskInfo diskInfo) {
            if (StorageWizardBase.this.mDisk.id.equals(diskInfo.id)) {
                StorageWizardBase.this.finish();
            }
        }
    };
    protected VolumeInfo mVolume;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mStorage = (StorageManager) getSystemService(StorageManager.class);
        String stringExtra = getIntent().getStringExtra("android.os.storage.extra.VOLUME_ID");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.mVolume = this.mStorage.findVolumeById(stringExtra);
        }
        String stringExtra2 = getIntent().getStringExtra("android.os.storage.extra.DISK_ID");
        if (!TextUtils.isEmpty(stringExtra2)) {
            this.mDisk = this.mStorage.findDiskById(stringExtra2);
        } else {
            VolumeInfo volumeInfo = this.mVolume;
            if (volumeInfo != null) {
                this.mDisk = volumeInfo.getDisk();
            }
        }
        if (this.mDisk != null) {
            this.mStorage.registerListener(this.mStorageListener);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(int i) {
        super.setContentView(i);
        FooterBarMixin footerBarMixin = (FooterBarMixin) getGlifLayout().getMixin(FooterBarMixin.class);
        this.mFooterBarMixin = footerBarMixin;
        FooterButton.Builder builder = new FooterButton.Builder(this);
        builder.setText(R.string.wizard_back);
        builder.setListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.deviceinfo.-$$Lambda$L0fpcWBOU3bvtCky0-RhaU3VX_M
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onNavigateBack(view);
            }
        });
        builder.setButtonType(0);
        builder.setTheme(R.style.SudGlifButton_Secondary);
        footerBarMixin.setSecondaryButton(builder.build());
        FooterBarMixin footerBarMixin2 = this.mFooterBarMixin;
        FooterButton.Builder builder2 = new FooterButton.Builder(this);
        builder2.setText(R.string.wizard_next);
        builder2.setListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.deviceinfo.-$$Lambda$dhJdhzAqL7F4ZI2WrQxdr_1gNkg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onNavigateNext(view);
            }
        });
        builder2.setButtonType(5);
        builder2.setTheme(R.style.SudGlifButton_Primary);
        footerBarMixin2.setPrimaryButton(builder2.build());
        this.mBack = this.mFooterBarMixin.getSecondaryButton();
        this.mNext = this.mFooterBarMixin.getPrimaryButton();
        setIcon(android.R.drawable.ic_media_route_connecting_light_29_mtrl);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.mStorage.unregisterListener(this.mStorageListener);
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper
    protected void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        theme.applyStyle(R.style.SetupWizardPartnerResource, true);
        super.onApplyThemeResource(theme, i, z);
    }

    protected GlifLayout getGlifLayout() {
        return (GlifLayout) requireViewById(R.id.setup_wizard_layout);
    }

    protected ProgressBar getProgressBar() {
        return (ProgressBar) requireViewById(R.id.storage_wizard_progress);
    }

    protected void setCurrentProgress(int i) {
        getProgressBar().setProgress(i);
        ((TextView) requireViewById(R.id.storage_wizard_progress_summary)).setText(NumberFormat.getPercentInstance().format(((double) i) / 100.0d));
    }

    protected void setHeaderText(int i, CharSequence... charSequenceArr) {
        CharSequence charSequenceExpandTemplate = TextUtils.expandTemplate(getText(i), charSequenceArr);
        getGlifLayout().setHeaderText(charSequenceExpandTemplate);
        setTitle(charSequenceExpandTemplate);
    }

    protected void setBodyText(int i, CharSequence... charSequenceArr) {
        TextView textView = (TextView) requireViewById(R.id.storage_wizard_body);
        textView.setText(TextUtils.expandTemplate(getText(i), charSequenceArr));
        textView.setVisibility(0);
    }

    protected void setAuxChecklist() {
        FrameLayout frameLayout = (FrameLayout) requireViewById(R.id.storage_wizard_aux);
        frameLayout.addView(LayoutInflater.from(frameLayout.getContext()).inflate(R.layout.storage_wizard_checklist, (ViewGroup) frameLayout, false));
        frameLayout.setVisibility(0);
        ((TextView) frameLayout.requireViewById(R.id.storage_wizard_migrate_v2_checklist_media)).setText(TextUtils.expandTemplate(getText(R.string.storage_wizard_migrate_v2_checklist_media), getDiskShortDescription()));
    }

    protected void setBackButtonText(int i, CharSequence... charSequenceArr) {
        this.mBack.setText(TextUtils.expandTemplate(getText(i), charSequenceArr));
        this.mBack.setVisibility(0);
    }

    protected void setNextButtonText(int i, CharSequence... charSequenceArr) {
        this.mNext.setText(TextUtils.expandTemplate(getText(i), charSequenceArr));
        this.mNext.setVisibility(0);
    }

    protected void setIcon(int i) {
        GlifLayout glifLayout = getGlifLayout();
        Drawable drawableMutate = getDrawable(i).mutate();
        drawableMutate.setTintList(Utils.getColorAccent(glifLayout.getContext()));
        glifLayout.setIcon(drawableMutate);
    }

    protected void setKeepScreenOn(boolean z) {
        getGlifLayout().setKeepScreenOn(z);
    }

    public void onNavigateBack(View view) {
        throw new UnsupportedOperationException();
    }

    public void onNavigateNext(View view) {
        throw new UnsupportedOperationException();
    }

    private void copyStringExtra(Intent intent, Intent intent2, String str) {
        if (!intent.hasExtra(str) || intent2.hasExtra(str)) {
            return;
        }
        intent2.putExtra(str, intent.getStringExtra(str));
    }

    private void copyBooleanExtra(Intent intent, Intent intent2, String str) {
        if (!intent.hasExtra(str) || intent2.hasExtra(str)) {
            return;
        }
        intent2.putExtra(str, intent.getBooleanExtra(str, false));
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        Intent intent2 = getIntent();
        copyStringExtra(intent2, intent, "android.os.storage.extra.DISK_ID");
        copyStringExtra(intent2, intent, "android.os.storage.extra.VOLUME_ID");
        copyStringExtra(intent2, intent, "format_forget_uuid");
        copyBooleanExtra(intent2, intent, "format_private");
        copyBooleanExtra(intent2, intent, "format_slow");
        copyBooleanExtra(intent2, intent, "migrate_skip");
        super.startActivity(intent);
    }

    protected VolumeInfo findFirstVolume(int i) {
        return findFirstVolume(i, 1);
    }

    protected VolumeInfo findFirstVolume(int i, int i2) {
        while (true) {
            for (VolumeInfo volumeInfo : this.mStorage.getVolumes()) {
                if (Objects.equals(this.mDisk.getId(), volumeInfo.getDiskId()) && volumeInfo.getType() == i && volumeInfo.getState() == 2) {
                    return volumeInfo;
                }
            }
            i2--;
            if (i2 <= 0) {
                return null;
            }
            Log.w("StorageSettings", "Missing mounted volume of type " + i + " hosted by disk " + this.mDisk.getId() + "; trying again");
            SystemClock.sleep(250L);
        }
    }

    protected CharSequence getDiskDescription() {
        DiskInfo diskInfo = this.mDisk;
        if (diskInfo != null) {
            return diskInfo.getDescription();
        }
        VolumeInfo volumeInfo = this.mVolume;
        if (volumeInfo != null) {
            return volumeInfo.getDescription();
        }
        return getText(R.string.unknown);
    }

    protected CharSequence getDiskShortDescription() {
        DiskInfo diskInfo = this.mDisk;
        if (diskInfo != null) {
            return diskInfo.getShortDescription();
        }
        VolumeInfo volumeInfo = this.mVolume;
        if (volumeInfo != null) {
            return volumeInfo.getDescription();
        }
        return getText(R.string.unknown);
    }
}
