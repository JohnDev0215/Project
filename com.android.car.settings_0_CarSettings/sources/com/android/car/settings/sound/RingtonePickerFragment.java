package com.android.car.settings.sound;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.ui.toolbar.MenuItem;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class RingtonePickerFragment extends SettingsFragment {
    private RingtonePickerPreferenceController mPreferenceController;
    private MenuItem mSaveButton;

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.ringtone_picker_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        RingtonePickerPreferenceController ringtonePickerPreferenceController = (RingtonePickerPreferenceController) use(RingtonePickerPreferenceController.class, R.string.pk_ringtone_picker);
        this.mPreferenceController = ringtonePickerPreferenceController;
        ringtonePickerPreferenceController.setArguments(getArguments());
        this.mSaveButton = new MenuItem.Builder(getContext()).setTitle(R.string.ringtone_picker_save_title).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.sound.-$$Lambda$RingtonePickerFragment$LqEV6faHAc02Bo1x8qCnlk8itrY
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onAttach$0$RingtonePickerFragment(menuItem);
            }
        }).build();
    }

    public /* synthetic */ void lambda$onAttach$0$RingtonePickerFragment(MenuItem item) {
        this.mPreferenceController.saveRingtone();
        goBack();
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected List<MenuItem> getToolbarMenuItems() {
        return Collections.singletonList(this.mSaveButton);
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getToolbar().setTitle(getArguments().getCharSequence("android.intent.extra.ringtone.TITLE"));
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getListView().getViewTreeObserver().addOnGlobalLayoutListener(new AnonymousClass1());
    }

    /* JADX INFO: renamed from: com.android.car.settings.sound.RingtonePickerFragment$1, reason: invalid class name */
    class AnonymousClass1 implements ViewTreeObserver.OnGlobalLayoutListener {
        AnonymousClass1() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            RingtonePickerFragment.this.getListView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
            final LinearLayoutManager layoutManager = (LinearLayoutManager) RingtonePickerFragment.this.getListView().getLayoutManager();
            layoutManager.scrollToPositionWithOffset(RingtonePickerFragment.this.mPreferenceController.getCurrentlySelectedPreferencePos(), 0);
            RingtonePickerFragment.this.getListView().post(new Runnable() { // from class: com.android.car.settings.sound.-$$Lambda$RingtonePickerFragment$1$bsODb0gF7CulN0baKoebe2X-B40
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onGlobalLayout$0$RingtonePickerFragment$1(layoutManager);
                }
            });
        }

        public /* synthetic */ void lambda$onGlobalLayout$0$RingtonePickerFragment$1(LinearLayoutManager layoutManager) {
            View itemView = RingtonePickerFragment.this.getListView().findViewById(R.id.ringtone_picker_selected_id);
            layoutManager.findContainingItemView(itemView).setSelected(true);
        }
    }
}
