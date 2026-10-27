package com.byd.music.fragment.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.bluetoothaidl.manage.HfpManage;
import com.byd.music.BR;
import com.byd.music.OnLineMusic;
import com.byd.music.R;
import com.byd.music.ThirdSDK.ThirdManager;
import com.byd.music.base.BaseFragment;
import com.byd.music.databinding.FragmentMusicBluetoothBinding;
import com.byd.music.style.BydUiStyleConfig;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.StringSubUtil;
import com.byd.music.utils.ToastUtil;
import com.byd.music.utils.binding.BindingUtils;
import com.jakewharton.rxbinding3.view.RxView;
import io.reactivex.functions.Consumer;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;

/* JADX INFO: loaded from: classes5.dex */
public class BlueToothFragment extends BaseFragment<FragmentMusicBluetoothBinding, BluetoothViewModel> {
    public static final int Gone = 8;
    public static final String SERVICE_CLASS = "com.byd.dialogservice.DialogService";
    public static final String SERVICE_PKG = "com.byd.carsettings";
    public static final int Visibility = 0;
    private String TAG = BlueToothFragment.class.getName();

    @Override // com.byd.music.base.BaseFragment
    protected boolean disableBackground() {
        return true;
    }

    @Override // com.byd.music.base.BaseFragment
    protected void initData() {
    }

    @Override // com.byd.music.base.BaseFragment
    protected boolean isPreLoad() {
        return true;
    }

    @Override // com.byd.music.base.BaseFragment
    public boolean onBack() {
        return false;
    }

    @Override // com.byd.music.base.BaseFragment
    protected void onLazyInit() {
    }

    @Override // com.byd.music.base.BaseFragment
    protected int layoutId() {
        return R.layout.fragment_music_bluetooth;
    }

    @Override // com.byd.music.base.BaseFragment
    protected int viewModelId() {
        return BR.viewModel;
    }

    @Override // com.byd.music.base.BaseFragment
    protected void initView() {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).llBlueContent.getLayoutParams();
        layoutParams.leftMargin = ((BluetoothViewModel) this.viewModel).isPortraitMode.get() ? 0 : SizeUtils.dp2px(65.0f);
        layoutParams.rightMargin = ((BluetoothViewModel) this.viewModel).isPortraitMode.get() ? 0 : SizeUtils.dp2px(106.0f);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).clControl.getLayoutParams();
        layoutParams2.width = SizeUtils.dp2px(((BluetoothViewModel) this.viewModel).isPortraitMode.get() ? 304.0f : 360.0f);
        ((FragmentMusicBluetoothBinding) this.dataBinding).llBlueContent.setLayoutParams(layoutParams);
        ((FragmentMusicBluetoothBinding) this.dataBinding).clControl.setLayoutParams(layoutParams2);
        ((FragmentMusicBluetoothBinding) this.dataBinding).tvToConnect.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.bluetooth.-$$Lambda$BlueToothFragment$AMTub15P2EfDtUBAVS8Qm9Eh1VI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$0$BlueToothFragment(view);
            }
        });
        RxView.clicks(((FragmentMusicBluetoothBinding) this.dataBinding).btRadioPlay).throttleFirst(1L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.byd.music.fragment.bluetooth.-$$Lambda$BlueToothFragment$UYpZbcbkVLmxEw2gPLLN3c198XI
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) throws Exception {
                this.f$0.lambda$initView$1$BlueToothFragment((Unit) obj);
            }
        });
        ((BluetoothViewModel) this.viewModel).openBluetoothConnect.observe(this, new Observer() { // from class: com.byd.music.fragment.bluetooth.BlueToothFragment.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(Object obj) {
                BlueToothFragment.toConnectBlueTooth();
            }
        });
        ((FragmentMusicBluetoothBinding) this.dataBinding).btRadioPlayNext.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.bluetooth.BlueToothFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BluetoothViewModel.nextMusic();
            }
        });
        ((FragmentMusicBluetoothBinding) this.dataBinding).btRadioPlayPre.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.bluetooth.BlueToothFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BluetoothViewModel.preMusic();
            }
        });
        ((FragmentMusicBluetoothBinding) this.dataBinding).seekBar.setEnabled(false);
        if (BydUiStyleConfig.isFancy()) {
            ((FragmentMusicBluetoothBinding) this.dataBinding).seekBar.setThumb(ContextCompat.getDrawable(Utils.getApp(), R.drawable.fancy_seekbar_thumb));
        }
        ((FragmentMusicBluetoothBinding) this.dataBinding).ivVoice.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.bluetooth.BlueToothFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BlueToothFragment.this.onSoundEffectClick();
            }
        });
        ((FragmentMusicBluetoothBinding) this.dataBinding).clProgress.setOnTouchListener(new View.OnTouchListener() { // from class: com.byd.music.fragment.bluetooth.BlueToothFragment.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 0) {
                    return true;
                }
                ToastUtil.showCenter(BlueToothFragment.this.getString(R.string.bluetooth_drag_toast));
                return true;
            }
        });
        BluetoothViewModel.isShowConnectedPhone.set(BluetoothViewModel.isA2DPConnected.get() && ((BluetoothViewModel) this.viewModel).isPortraitMode.get());
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).ivIconAlbumPort.getLayoutParams();
        layoutParams3.width = SizeUtils.dp2px(((BluetoothViewModel) this.viewModel).isMultiMode.get() ? 140.0f : 200.0f);
        layoutParams3.height = SizeUtils.dp2px(((BluetoothViewModel) this.viewModel).isMultiMode.get() ? 140.0f : 200.0f);
        ((FragmentMusicBluetoothBinding) this.dataBinding).ivIconAlbumPort.setLayoutParams(layoutParams3);
        ((FragmentMusicBluetoothBinding) this.dataBinding).androidauto.setVisibility(ThirdManager.isAndroidAutoConnected() ? 0 : 8);
        LogUtil.e("android Auto" + ThirdManager.isAndroidAutoConnected());
        ThirdManager.getInstance().isAndroidAutoConnected.observe(this, new Observer<Boolean>() { // from class: com.byd.music.fragment.bluetooth.BlueToothFragment.6
            @Override // androidx.lifecycle.Observer
            public void onChanged(Boolean bool) {
                if (bool != null) {
                    ((FragmentMusicBluetoothBinding) BlueToothFragment.this.dataBinding).androidauto.setVisibility(bool.booleanValue() ? 0 : 8);
                    ((FragmentMusicBluetoothBinding) BlueToothFragment.this.dataBinding).llBlueContent.setVisibility(bool.booleanValue() ? 8 : 0);
                }
            }
        });
        if (BluetoothViewModel.isUnknownTitle.get()) {
            BluetoothViewModel.title.set(getString(R.string.unknown_music));
        }
        if (BluetoothViewModel.isUnknownArtist.get()) {
            BluetoothViewModel.artist.set(getString(R.string.unknown_artist));
        }
        applyViewByOrientation(this.lastIsMultiMode, this.lastDeviceOrientation);
    }

    public /* synthetic */ void lambda$initView$0$BlueToothFragment(View view) {
        if (getActivity() != null) {
            BluetoothAdapter.getDefaultAdapter().enable();
            toConnectBlueTooth();
        }
    }

    public /* synthetic */ void lambda$initView$1$BlueToothFragment(Unit unit) throws Exception {
        LogUtil.e(this.TAG, "playStatus change = getInstance().Play_or_Pause(), currentPlayStatus = " + BluetoothViewModel.playOrPause.get());
        BluetoothViewModel.playOrPause();
    }

    private void applyViewByOrientation(boolean z, int i) {
        if (z) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).clNoConnect.getLayoutParams();
            layoutParams.topToBottom = -1;
            ((FragmentMusicBluetoothBinding) this.dataBinding).clNoConnect.setLayoutParams(layoutParams);
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).ivNoConnect.getLayoutParams();
            layoutParams2.topMargin = SizeUtils.dp2px(0.0f);
            ((FragmentMusicBluetoothBinding) this.dataBinding).ivNoConnect.setLayoutParams(layoutParams2);
            ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).clContent.getLayoutParams();
            layoutParams3.topMargin = SizeUtils.dp2px(0.0f);
            layoutParams3.topToBottom = -1;
            ((FragmentMusicBluetoothBinding) this.dataBinding).clContent.setLayoutParams(layoutParams3);
            ((FragmentMusicBluetoothBinding) this.dataBinding).clContent.setPadding(0, 0, 0, 0);
            BindingUtils.setConstraintCustom(((FragmentMusicBluetoothBinding) this.dataBinding).llBlueContent, 0, 0, 0, 0);
            ConstraintLayout.LayoutParams layoutParams4 = (ConstraintLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).llBlueContent.getLayoutParams();
            layoutParams4.leftMargin = 0;
            layoutParams4.rightMargin = 0;
            layoutParams4.topMargin = SizeUtils.dp2px(12.0f);
            layoutParams4.width = -1;
            ((FragmentMusicBluetoothBinding) this.dataBinding).llBlueContent.setLayoutParams(layoutParams4);
            ((FragmentMusicBluetoothBinding) this.dataBinding).llBlueContent.setGravity(1);
            LinearLayout.LayoutParams layoutParams5 = (LinearLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).tvMusicName.getLayoutParams();
            layoutParams5.topMargin = SizeUtils.dp2px(20.0f);
            ((FragmentMusicBluetoothBinding) this.dataBinding).tvMusicName.setLayoutParams(layoutParams5);
            ((FragmentMusicBluetoothBinding) this.dataBinding).tvMusicName.setTextSize(30.0f);
            LinearLayout.LayoutParams layoutParams6 = (LinearLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).clControl.getLayoutParams();
            layoutParams6.width = SizeUtils.dp2px(260.0f);
            layoutParams6.gravity = 1;
            layoutParams6.topMargin = SizeUtils.dp2px(5.0f);
            ((FragmentMusicBluetoothBinding) this.dataBinding).clControl.setLayoutParams(layoutParams6);
            LinearLayout.LayoutParams layoutParams7 = (LinearLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).ivIconAlbumPort.getLayoutParams();
            layoutParams7.width = SizeUtils.dp2px(140.0f);
            layoutParams7.height = SizeUtils.dp2px(140.0f);
            layoutParams7.topMargin = 10;
            ((FragmentMusicBluetoothBinding) this.dataBinding).ivIconAlbumPort.setLayoutParams(layoutParams7);
            LinearLayout.LayoutParams layoutParams8 = (LinearLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).tvSinger.getLayoutParams();
            layoutParams8.topMargin = 0;
            ((FragmentMusicBluetoothBinding) this.dataBinding).tvSinger.setLayoutParams(layoutParams8);
            LinearLayout.LayoutParams layoutParams9 = (LinearLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).clProgress.getLayoutParams();
            layoutParams9.width = SizeUtils.dp2px(540.0f);
            layoutParams9.topMargin = SizeUtils.dp2px(5.0f);
            layoutParams9.setMarginEnd(0);
            layoutParams9.gravity = 1;
            ((FragmentMusicBluetoothBinding) this.dataBinding).clProgress.setLayoutParams(layoutParams9);
            ((FragmentMusicBluetoothBinding) this.dataBinding).clProgress.setPaddingRelative(0, 0, 0, 0);
            ((ConstraintLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).llBluetooth.getLayoutParams()).topMargin = 0;
            ConstraintLayout.LayoutParams layoutParams10 = (ConstraintLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).androidauto.getLayoutParams();
            layoutParams10.width = 0;
            layoutParams10.height = SizeUtils.dp2px(513.0f);
            ((FragmentMusicBluetoothBinding) this.dataBinding).androidauto.setLayoutParams(layoutParams10);
        } else if (i == 2) {
            ConstraintLayout.LayoutParams layoutParams11 = (ConstraintLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).clNoConnect.getLayoutParams();
            layoutParams11.topToBottom = ((FragmentMusicBluetoothBinding) this.dataBinding).ivVoice.getId();
            ((FragmentMusicBluetoothBinding) this.dataBinding).clNoConnect.setLayoutParams(layoutParams11);
            ConstraintLayout.LayoutParams layoutParams12 = (ConstraintLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).ivNoConnect.getLayoutParams();
            layoutParams12.topMargin = SizeUtils.dp2px(0.0f);
            ((FragmentMusicBluetoothBinding) this.dataBinding).ivNoConnect.setLayoutParams(layoutParams12);
            ConstraintLayout.LayoutParams layoutParams13 = (ConstraintLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).clContent.getLayoutParams();
            layoutParams13.topMargin = SizeUtils.dp2px(0.0f);
            layoutParams13.topToBottom = ((FragmentMusicBluetoothBinding) this.dataBinding).ivVoice.getId();
            ((FragmentMusicBluetoothBinding) this.dataBinding).clContent.setLayoutParams(layoutParams13);
            ((FragmentMusicBluetoothBinding) this.dataBinding).clContent.setPadding(SizeUtils.dp2px(33.0f), 0, SizeUtils.dp2px(84.0f), 0);
            LinearLayout linearLayout = ((FragmentMusicBluetoothBinding) this.dataBinding).llBlueContent;
            boolean z2 = ((BluetoothViewModel) this.viewModel).isRight.get();
            BindingUtils.setConstraintCustom(linearLayout, z2 ? 1 : 0, ((BluetoothViewModel) this.viewModel).isRight.get() ? ((FragmentMusicBluetoothBinding) this.dataBinding).ivIconAblum.getId() : 0, ((BluetoothViewModel) this.viewModel).isRight.get() ? 0 : ((FragmentMusicBluetoothBinding) this.dataBinding).ivIconAblum.getId(), !((BluetoothViewModel) this.viewModel).isRight.get() ? 1 : 0);
            ConstraintLayout.LayoutParams layoutParams14 = (ConstraintLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).llBlueContent.getLayoutParams();
            layoutParams14.leftMargin = SizeUtils.dp2px(65.0f);
            layoutParams14.rightMargin = SizeUtils.dp2px(106.0f);
            layoutParams14.topMargin = SizeUtils.dp2px(34.0f);
            layoutParams14.width = SizeUtils.dp2px(540.0f);
            ((FragmentMusicBluetoothBinding) this.dataBinding).llBlueContent.setLayoutParams(layoutParams14);
            ((FragmentMusicBluetoothBinding) this.dataBinding).llBlueContent.setGravity(8388611);
            LinearLayout.LayoutParams layoutParams15 = (LinearLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).tvMusicName.getLayoutParams();
            layoutParams15.topMargin = SizeUtils.dp2px(0.0f);
            ((FragmentMusicBluetoothBinding) this.dataBinding).tvMusicName.setLayoutParams(layoutParams15);
            ((FragmentMusicBluetoothBinding) this.dataBinding).tvMusicName.setTextSize(34.0f);
            LinearLayout.LayoutParams layoutParams16 = (LinearLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).clControl.getLayoutParams();
            layoutParams16.width = SizeUtils.dp2px(360.0f);
            layoutParams16.gravity = 3;
            layoutParams16.topMargin = SizeUtils.dp2px(57.0f);
            ((FragmentMusicBluetoothBinding) this.dataBinding).clControl.setLayoutParams(layoutParams16);
            LinearLayout.LayoutParams layoutParams17 = (LinearLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).ivIconAlbumPort.getLayoutParams();
            layoutParams17.width = SizeUtils.dp2px(200.0f);
            layoutParams17.height = SizeUtils.dp2px(200.0f);
            layoutParams17.topMargin = 30;
            ((FragmentMusicBluetoothBinding) this.dataBinding).ivIconAlbumPort.setLayoutParams(layoutParams17);
            LinearLayout.LayoutParams layoutParams18 = (LinearLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).tvSinger.getLayoutParams();
            layoutParams18.topMargin = SizeUtils.dp2px(10.0f);
            ((FragmentMusicBluetoothBinding) this.dataBinding).tvSinger.setLayoutParams(layoutParams18);
            LinearLayout.LayoutParams layoutParams19 = (LinearLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).clProgress.getLayoutParams();
            layoutParams19.width = SizeUtils.dp2px(580.0f);
            layoutParams19.topMargin = SizeUtils.dp2px(36.0f);
            layoutParams19.setMarginEnd(10);
            layoutParams19.gravity = 3;
            ((FragmentMusicBluetoothBinding) this.dataBinding).clProgress.setLayoutParams(layoutParams19);
            ((FragmentMusicBluetoothBinding) this.dataBinding).clProgress.setPaddingRelative(SizeUtils.dp2px(6.0f), 0, 0, 0);
            ((ConstraintLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).llBluetooth.getLayoutParams()).topMargin = 0;
            ConstraintLayout.LayoutParams layoutParams20 = (ConstraintLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).androidauto.getLayoutParams();
            layoutParams20.width = 0;
            layoutParams20.height = SizeUtils.dp2px(513.0f);
            ((FragmentMusicBluetoothBinding) this.dataBinding).androidauto.setLayoutParams(layoutParams20);
        } else {
            ConstraintLayout.LayoutParams layoutParams21 = (ConstraintLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).clNoConnect.getLayoutParams();
            layoutParams21.topToBottom = -1;
            ((FragmentMusicBluetoothBinding) this.dataBinding).clNoConnect.setLayoutParams(layoutParams21);
            ConstraintLayout.LayoutParams layoutParams22 = (ConstraintLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).ivNoConnect.getLayoutParams();
            layoutParams22.topMargin = SizeUtils.dp2px(200.0f);
            ((FragmentMusicBluetoothBinding) this.dataBinding).ivNoConnect.setLayoutParams(layoutParams22);
            ConstraintLayout.LayoutParams layoutParams23 = (ConstraintLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).clContent.getLayoutParams();
            layoutParams23.topMargin = SizeUtils.dp2px(0.0f);
            layoutParams23.topToBottom = -1;
            ((FragmentMusicBluetoothBinding) this.dataBinding).clContent.setLayoutParams(layoutParams23);
            ((FragmentMusicBluetoothBinding) this.dataBinding).clContent.setPadding(0, 0, 0, 0);
            BindingUtils.setConstraintCustom(((FragmentMusicBluetoothBinding) this.dataBinding).llBlueContent, 0, 0, 0, 0);
            ConstraintLayout.LayoutParams layoutParams24 = (ConstraintLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).llBlueContent.getLayoutParams();
            layoutParams24.leftMargin = 0;
            layoutParams24.rightMargin = 0;
            layoutParams24.topMargin = 0;
            layoutParams24.width = -1;
            ((FragmentMusicBluetoothBinding) this.dataBinding).llBlueContent.setLayoutParams(layoutParams24);
            ((FragmentMusicBluetoothBinding) this.dataBinding).llBlueContent.setGravity(1);
            LinearLayout.LayoutParams layoutParams25 = (LinearLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).tvMusicName.getLayoutParams();
            layoutParams25.topMargin = SizeUtils.dp2px(150.0f);
            ((FragmentMusicBluetoothBinding) this.dataBinding).tvMusicName.setLayoutParams(layoutParams25);
            ((FragmentMusicBluetoothBinding) this.dataBinding).tvMusicName.setTextSize(30.0f);
            LinearLayout.LayoutParams layoutParams26 = (LinearLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).clControl.getLayoutParams();
            layoutParams26.width = SizeUtils.dp2px(260.0f);
            layoutParams26.gravity = 1;
            layoutParams26.topMargin = SizeUtils.dp2px(5.0f);
            ((FragmentMusicBluetoothBinding) this.dataBinding).clControl.setLayoutParams(layoutParams26);
            LinearLayout.LayoutParams layoutParams27 = (LinearLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).ivIconAlbumPort.getLayoutParams();
            layoutParams27.width = SizeUtils.dp2px(200.0f);
            layoutParams27.height = SizeUtils.dp2px(200.0f);
            layoutParams27.topMargin = 10;
            ((FragmentMusicBluetoothBinding) this.dataBinding).ivIconAlbumPort.setLayoutParams(layoutParams27);
            LinearLayout.LayoutParams layoutParams28 = (LinearLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).tvSinger.getLayoutParams();
            layoutParams28.topMargin = 0;
            ((FragmentMusicBluetoothBinding) this.dataBinding).tvSinger.setLayoutParams(layoutParams28);
            LinearLayout.LayoutParams layoutParams29 = (LinearLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).clProgress.getLayoutParams();
            layoutParams29.width = SizeUtils.dp2px(540.0f);
            layoutParams29.topMargin = SizeUtils.dp2px(5.0f);
            layoutParams29.setMarginEnd(0);
            layoutParams29.gravity = 1;
            ((FragmentMusicBluetoothBinding) this.dataBinding).clProgress.setLayoutParams(layoutParams29);
            ((FragmentMusicBluetoothBinding) this.dataBinding).clProgress.setPaddingRelative(0, 0, 0, 0);
            ((ConstraintLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).llBluetooth.getLayoutParams()).topMargin = SizeUtils.dp2px(200.0f);
            ConstraintLayout.LayoutParams layoutParams30 = (ConstraintLayout.LayoutParams) ((FragmentMusicBluetoothBinding) this.dataBinding).androidauto.getLayoutParams();
            layoutParams30.width = 0;
            layoutParams30.height = SizeUtils.dp2px(880.0f);
            ((FragmentMusicBluetoothBinding) this.dataBinding).androidauto.setLayoutParams(layoutParams30);
        }
        ((FragmentMusicBluetoothBinding) this.dataBinding).tvMusicName.setSelected(true);
    }

    @Override // com.byd.music.base.BaseFragment
    protected void onWindowModeChanged(boolean z, int i, boolean z2, int i2) {
        super.onWindowModeChanged(z, i, z2, i2);
        LogUtil.e(this.TAG, "onWindowModeChanged, isMultiWindowMode = " + z + ", deviceOrientation = " + i);
        applyViewByOrientation(z, i);
    }

    public static void toConnectBlueTooth() {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("byd_pad://byd_car_settings:2933/launcher?activity=Bluetooth"));
        intent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
        if (!Utils.getApp().getPackageManager().queryIntentServices(intent, 0).isEmpty()) {
            Utils.getApp().startService(intent);
        }
        LogUtil.e("open BlueTooth by start service");
    }

    @Override // com.byd.music.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        boolean zIsA2DPConnect = HfpManage.getInstance().isA2DPConnect();
        LogUtil.e(this.TAG, "updateA2dpConnectState on resume = " + zIsA2DPConnect);
        BluetoothViewModel.isA2DPConnected.set(zIsA2DPConnect);
        BluetoothViewModel.connectedPhoneName.set(HfpManage.getInstance().getA2dpConnectDeviceName());
        BluetoothViewModel.isHFPConnected.set(HfpManage.getInstance().isHFPConnect());
        OnLineMusic.isBTViewActive = true;
        OnLineMusic.checkBluetoothMusicActive();
        updateTitle();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        LogUtil.e(this.TAG, "bluetoothFragment onStop");
        OnLineMusic.isBTViewActive = false;
        OnLineMusic.checkBluetoothMusicActive();
    }

    private void updateTitle() {
        try {
            String string = HfpManage.getInstance().getmMusicTile();
            String artist = HfpManage.getInstance().getArtist();
            LogUtil.e(this.TAG, "blueToothFragment onResume getTitle = " + StringSubUtil.subString(string) + ", artist = " + StringSubUtil.subString(artist));
            if (TextUtils.isEmpty(string) || StringUtils.isTrimEmpty(string)) {
                string = getResources().getString(R.string.unknown_song);
                LogUtil.e("musicAppliction" + StringSubUtil.subString(string));
            }
            if (TextUtils.isEmpty(artist) || StringUtils.isTrimEmpty(artist)) {
                artist = getResources().getString(R.string.unknown_artist);
            }
            if (!TextUtils.equals(artist, BluetoothViewModel.artist.get())) {
                BluetoothViewModel.artist.set(BluetoothViewModel.getFormatArtist(artist));
            }
            if (TextUtils.equals(string, BluetoothViewModel.title.get())) {
                return;
            }
            BluetoothViewModel.title.set(BluetoothViewModel.getFormatTitle(string));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
