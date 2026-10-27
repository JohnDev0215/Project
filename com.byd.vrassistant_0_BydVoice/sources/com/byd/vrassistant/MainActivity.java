package com.byd.vrassistant;

import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.media.AudioDeviceInfo;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import cerence.ark.assistant.api.ArkAssistant;
import cerence.ark.assistant.controller.enumeration.VehicleAreaEnum;
import cerence.ark.assistant.controller.struct.MusicInfo;
import com.blankj.utilcode.util.ToastUtils;
import com.byd.common.countrycode.CountryCodeUtil;
import com.byd.vrassistant.RadioAudioDemo.MyService;
import com.byd.vrassistant.conversation.RadioSdk;
import com.byd.vrassistant.conversation.ScanMusicList;
import com.byd.vrassistant.conversation.Song;
import com.byd.vrassistant.eventbus.SkinEvent;
import com.byd.vrassistant.eventbus.TtsEvent;
import com.byd.vrassistant.manager.LocalMediaManager;
import com.byd.vrassistant.manager.SpotifyManager;
import com.byd.vrassistant.manager.SystemSettingManager;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import com.byd.vrassistant.utils.ForegroundAppUtil;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.SystemPropertiesUtils;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.ExplainReasonCallbackWithBeforeParam;
import com.permissionx.guolindev.callback.ForwardToSettingsCallback;
import com.permissionx.guolindev.callback.RequestCallback;
import com.permissionx.guolindev.request.ExplainScope;
import com.permissionx.guolindev.request.ForwardScope;
import com.vivid.sdk.spotify.RepeatMode;
import com.vivid.sdk.spotify.ShuffleMode;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends AppCompatActivity {
    private static final int OVERLAY_PERMISSION_REQUEST_CODE = 100;
    public static final String TAG = "NEU_MainActivity";
    public Button appevent;
    public Button dark;
    public Button dialogcancel;
    public Button left;
    public Button light;
    public Button musicplay;
    public Button musicstop;
    Spinner mvoiceZoneSpinner;
    public Button onLowMemory;
    public Button prompter;
    public Button right;
    public Button spotifyopen;
    public Button spotifypause;
    public Button spotifyplay;
    public Button spotifystop;
    public Button stop;
    public TextView test_msg;
    public Button volumndown;
    public Button volumnup;
    String zone;
    int play_index = 0;
    private String[] permissions = {"android.permission.RECORD_AUDIO", "android.permission.READ_CONTACTS", "android.permission.READ_PHONE_STATE", "android.permission.READ_CALL_LOG", "android.permission.CALL_PHONE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.READ_SMS", "android.permission.RECEIVE_SMS", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ANSWER_PHONE_CALLS", "android.permission.SEND_SMS"};
    private String text_enu = "Cerence is the world’s leading provider of automotive assistants, creating intelligent, flexible and intuitive in-car experiences for the world’s leading automakers. With deep integration with the car itself and the flexibility to bring drivers’ expansive digital life into the car, we stand in a league of our own.";
    boolean isInitial = true;

    static /* synthetic */ void lambda$onCreate$24(View view) {
    }

    static /* synthetic */ void lambda$onCreate$25(View view) {
    }

    static /* synthetic */ void lambda$onCreate$30(View view) {
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String str = TAG;
        RLog.i(str, "onCreate");
        setContentView(R.layout.activity_main);
        this.test_msg = (TextView) findViewById(R.id.test_msg);
        this.appevent = (Button) findViewById(R.id.appevent);
        findViewById(R.id.cartype).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m107lambda$onCreate$0$combydvrassistantMainActivity(view);
            }
        });
        if (!Settings.canDrawOverlays(this)) {
            RLog.i(str, "DrawOverlays Permission Not Allowed");
            Toast.makeText(this, "can not DrawOverlays", 0).show();
        } else {
            Toast.makeText(this, "Permission Allowed", 0).show();
            RLog.i(str, "DrawOverlays Permission Allowed");
        }
        requestPermission();
        this.appevent.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m108lambda$onCreate$1$combydvrassistantMainActivity(view);
            }
        });
        this.light = (Button) findViewById(R.id.light);
        this.dark = (Button) findViewById(R.id.dark);
        this.left = (Button) findViewById(R.id.left);
        this.right = (Button) findViewById(R.id.right);
        this.light.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m112lambda$onCreate$2$combydvrassistantMainActivity(view);
            }
        });
        this.dark.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda26
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m115lambda$onCreate$3$combydvrassistantMainActivity(view);
            }
        });
        this.left.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda35
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m118lambda$onCreate$4$combydvrassistantMainActivity(view);
            }
        });
        this.right.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda36
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.lambda$onCreate$5(view);
            }
        });
        Button button = (Button) findViewById(R.id.dialogcancel);
        this.dialogcancel = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda37
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VoiceAssistantManager.get().sendCancelPTTEvent();
            }
        });
        Button button2 = (Button) findViewById(R.id.prompter);
        this.prompter = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda38
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m123lambda$onCreate$7$combydvrassistantMainActivity(view);
            }
        });
        Button button3 = (Button) findViewById(R.id.stop);
        this.stop = button3;
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda39
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EventBus.getDefault().post(new TtsEvent("", 1));
            }
        });
        findViewById(R.id.ac).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda40
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m124lambda$onCreate$9$combydvrassistantMainActivity(view);
            }
        });
        findViewById(R.id.phone).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m109lambda$onCreate$10$combydvrassistantMainActivity(view);
            }
        });
        Button button4 = (Button) findViewById(R.id.musicplay);
        this.musicplay = button4;
        button4.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda22
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m110lambda$onCreate$11$combydvrassistantMainActivity(view);
            }
        });
        Button button5 = (Button) findViewById(R.id.musicstop);
        this.musicstop = button5;
        button5.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda33
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m111lambda$onCreate$12$combydvrassistantMainActivity(view);
            }
        });
        Button button6 = (Button) findViewById(R.id.spotifyplay);
        this.spotifyplay = button6;
        button6.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda44
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SpotifyManager.get().play();
            }
        });
        Button button7 = (Button) findViewById(R.id.spotifystop);
        this.spotifystop = button7;
        button7.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda45
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SpotifyManager.get().stop();
            }
        });
        Button button8 = (Button) findViewById(R.id.spotifyopen);
        this.spotifyopen = button8;
        button8.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda46
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SystemSettingManager.get().openScreenByName("com.vivid.music.byd", "com.vivid.spotify.MainActivity");
            }
        });
        findViewById(R.id.spotifydestroy).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda47
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SpotifyManager.get().uninit();
            }
        });
        Button button9 = (Button) findViewById(R.id.spotifypause);
        this.spotifypause = button9;
        button9.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda48
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.lambda$onCreate$17(view);
            }
        });
        findViewById(R.id.spotifyadd).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda49
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SpotifyManager.get().addFavorite();
            }
        });
        findViewById(R.id.spotifyremove).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SpotifyManager.get().removeFavorite();
            }
        });
        findViewById(R.id.spotifyshuffle).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SpotifyManager.get().setShuffleMode(ShuffleMode.SHUFFLE_MODE_NONE);
            }
        });
        findViewById(R.id.spotifyrepeat).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SpotifyManager.get().setRepeatMode(RepeatMode.REPEAT_MODE_ONE);
            }
        });
        findViewById(R.id.spotifynext).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SpotifyManager.get().setShuffleMode(ShuffleMode.SHUFFLE_MODE_ALL);
            }
        });
        findViewById(R.id.spotifyprev).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SpotifyManager.get().setRepeatMode(RepeatMode.REPEAT_MODE_ALL);
            }
        });
        Button button10 = (Button) findViewById(R.id.volumnup);
        this.volumnup = button10;
        button10.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.lambda$onCreate$24(view);
            }
        });
        Button button11 = (Button) findViewById(R.id.volumndown);
        this.volumndown = button11;
        button11.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.lambda$onCreate$25(view);
            }
        });
        findViewById(R.id.mute).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SystemSettingManager.get().mute();
            }
        });
        findViewById(R.id.unmute).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SystemSettingManager.get().unmute();
            }
        });
        findViewById(R.id.bootguide).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m113lambda$onCreate$28$combydvrassistantMainActivity(view);
            }
        });
        findViewById(R.id.gears_r).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m114lambda$onCreate$29$combydvrassistantMainActivity(view);
            }
        });
        findViewById(R.id.gears_auto).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.lambda$onCreate$30(view);
            }
        });
        findViewById(R.id.home).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda16
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.lambda$onCreate$31(view);
            }
        });
        findViewById(R.id.openvoice).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda17
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VoiceAssistantManager.get().openVoiceAssistant();
            }
        });
        findViewById(R.id.closevoice).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda18
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VoiceAssistantManager.get().closeVoiceAssistant();
            }
        });
        findViewById(R.id.subwuw).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda19
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.lambda$onCreate$34(view);
            }
        });
        findViewById(R.id.shortcut).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda20
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.lambda$onCreate$35(view);
            }
        });
        findViewById(R.id.delshort).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda21
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.lambda$onCreate$36(view);
            }
        });
        findViewById(R.id.opencdr).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda23
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.lambda$onCreate$37(view);
            }
        });
        findViewById(R.id.searchmedia).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda24
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m117lambda$onCreate$39$combydvrassistantMainActivity(view);
            }
        });
        findViewById(R.id.playusb).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda25
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m120lambda$onCreate$42$combydvrassistantMainActivity(view);
            }
        });
        findViewById(R.id.getmeida).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda27
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LocalMediaManager.get().getCurrentMediaProvider();
            }
        });
        findViewById(R.id.openusb).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda28
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LocalMediaManager.get().openLocalMedia(new CountDownLatch(1));
            }
        });
        findViewById(R.id.openbt).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda29
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LocalMediaManager.get().openBlutoothMedia(new CountDownLatch(1), 1);
            }
        });
        findViewById(R.id.help).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda30
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SystemSettingManager.get().openScreenByName("com.byd.vrsettings", "com.byd.vrsettings.MainActivity");
            }
        });
        findViewById(R.id.killApp).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda31
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m121lambda$onCreate$47$combydvrassistantMainActivity(view);
            }
        });
        findViewById(R.id.onLowMemory).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda32
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.lambda$onCreate$48(view);
            }
        });
        findViewById(R.id.getVolumeTest).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda34
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m122lambda$onCreate$49$combydvrassistantMainActivity(view);
            }
        });
        RLog.i(str, "countrycode = " + CountryCodeUtil.getInstance().getCountryCode(BydVoiceApplication.getContext()));
        Spinner spinner = (Spinner) findViewById(R.id.voice_zone);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getEnableAudioZones(SystemSettingManager.get().isRightDriver()));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.byd.vrassistant.MainActivity.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                MainActivity.this.zone = (String) adapterView.getItemAtPosition(i);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onCreate$0$com-byd-vrassistant-MainActivity, reason: not valid java name */
    /* synthetic */ void m107lambda$onCreate$0$combydvrassistantMainActivity(View view) {
        Toast.makeText(this, "GetCarType=====" + SystemSettingManager.get().getVehicleType(this), 0).show();
    }

    /* JADX INFO: renamed from: lambda$onCreate$1$com-byd-vrassistant-MainActivity, reason: not valid java name */
    /* synthetic */ void m108lambda$onCreate$1$combydvrassistantMainActivity(View view) {
        Intent intent = new Intent(Constant.TELENAV_NAVNOTIFICATION);
        intent.putExtra("navigation_ready_for_binding", true);
        intent.setPackage(BuildConfig.APPLICATION_ID);
        sendBroadcast(intent);
        VoiceAssistantManager.get().sendPTTEvent(this.zone);
    }

    /* JADX INFO: renamed from: lambda$onCreate$2$com-byd-vrassistant-MainActivity, reason: not valid java name */
    /* synthetic */ void m112lambda$onCreate$2$combydvrassistantMainActivity(View view) {
        EventBus.getDefault().post(new SkinEvent(1));
        RLog.e(TAG, "MainActivity_skinType==1");
        Toast.makeText(this, ToastUtils.MODE.LIGHT, 1).show();
    }

    /* JADX INFO: renamed from: lambda$onCreate$3$com-byd-vrassistant-MainActivity, reason: not valid java name */
    /* synthetic */ void m115lambda$onCreate$3$combydvrassistantMainActivity(View view) {
        EventBus.getDefault().post(new SkinEvent(2));
        RLog.e(TAG, "MainActivity_skinType==2");
        Toast.makeText(this, ToastUtils.MODE.DARK, 1).show();
    }

    /* JADX INFO: renamed from: lambda$onCreate$4$com-byd-vrassistant-MainActivity, reason: not valid java name */
    /* synthetic */ void m118lambda$onCreate$4$combydvrassistantMainActivity(View view) {
        SystemSettingManager.get().isRightDriver();
        RLog.e(TAG, "MainActivity_leftOrRightType==1");
        Toast.makeText(this, "left", 1).show();
    }

    static /* synthetic */ void lambda$onCreate$5(View view) {
        Locale.setDefault(Locale.GERMAN);
        VoiceAssistantManager.get().reinit();
    }

    /* JADX INFO: renamed from: lambda$onCreate$7$com-byd-vrassistant-MainActivity, reason: not valid java name */
    /* synthetic */ void m123lambda$onCreate$7$combydvrassistantMainActivity(View view) {
        EventBus.getDefault().post(new TtsEvent(this.text_enu, 0));
    }

    /* JADX INFO: renamed from: lambda$onCreate$9$com-byd-vrassistant-MainActivity, reason: not valid java name */
    /* synthetic */ void m124lambda$onCreate$9$combydvrassistantMainActivity(View view) {
        startActivity(new Intent(this, (Class<?>) AcActivity.class));
    }

    /* JADX INFO: renamed from: lambda$onCreate$10$com-byd-vrassistant-MainActivity, reason: not valid java name */
    /* synthetic */ void m109lambda$onCreate$10$combydvrassistantMainActivity(View view) {
        startActivity(new Intent(this, (Class<?>) PhoneDemoActivity.class));
    }

    /* JADX INFO: renamed from: lambda$onCreate$11$com-byd-vrassistant-MainActivity, reason: not valid java name */
    /* synthetic */ void m110lambda$onCreate$11$combydvrassistantMainActivity(View view) {
        Intent intent = new Intent("com.byd.action.come.from.launcher_Receiver");
        intent.setFlags(32);
        intent.putExtra("VOICE_TEXT", intent);
        intent.setPackage(BuildConfig.APPLICATION_ID);
        sendBroadcast(intent);
    }

    /* JADX INFO: renamed from: lambda$onCreate$12$com-byd-vrassistant-MainActivity, reason: not valid java name */
    /* synthetic */ void m111lambda$onCreate$12$combydvrassistantMainActivity(View view) {
        try {
            RadioSdk.getInstance(getApplicationContext()).getPacketAudio();
            RadioSdk.getInstance(getApplicationContext()).getLatestPacketAudio();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }

    static /* synthetic */ void lambda$onCreate$17(View view) {
        if (SpotifyManager.get().getState() == SpotifyManager.STATE_PLAYING) {
            SpotifyManager.get().pause();
        } else if (SpotifyManager.get().getState() == SpotifyManager.STATE_PAUSED) {
            SpotifyManager.get().resume();
        }
    }

    /* JADX INFO: renamed from: lambda$onCreate$28$com-byd-vrassistant-MainActivity, reason: not valid java name */
    /* synthetic */ void m113lambda$onCreate$28$combydvrassistantMainActivity(View view) {
        RLog.d(TAG, "POWER_IVI_BOOT_ANIMATION_STATUS_SET : " + BYDAutoSettingDevice.getInstance(this).get(new int[]{2264}, Integer.TYPE).intValue);
    }

    /* JADX INFO: renamed from: lambda$onCreate$29$com-byd-vrassistant-MainActivity, reason: not valid java name */
    /* synthetic */ void m114lambda$onCreate$29$combydvrassistantMainActivity(View view) {
        RLog.d(TAG, "GEARBOX_AUTOMATIC_GEAR : " + BYDAutoSettingDevice.getInstance(this).get(new int[]{3600}, Integer.TYPE).intValue);
    }

    static /* synthetic */ void lambda$onCreate$31(View view) {
        VoiceAssistantManager.get().sendExitSplitScreen();
        SystemSettingManager.get().returnToHomePage();
    }

    static /* synthetic */ void lambda$onCreate$34(View view) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("scott");
        arrayList.add("gene");
        VoiceAssistantManager.get().setVoiceAssistantWUW(arrayList);
    }

    static /* synthetic */ void lambda$onCreate$35(View view) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("return to home page");
        arrayList.add("rotate screen");
        VoiceAssistantManager.get().setShortcutCommand(arrayList);
    }

    static /* synthetic */ void lambda$onCreate$36(View view) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("return to home page");
        arrayList.add("rotate screen");
        VoiceAssistantManager.get().delShortcutCommand(arrayList);
    }

    static /* synthetic */ void lambda$onCreate$37(View view) {
        AudioDeviceInfo[] devices = SystemSettingManager.get().audioManager.getDevices(3);
        for (int i = 0; i < devices.length; i++) {
            String str = TAG;
            RLog.i(str, "id:" + devices[i].getId());
            RLog.i(str, "add:" + devices[i].getAddress());
            RLog.i(str, "type:" + devices[i].getType());
        }
    }

    /* JADX INFO: renamed from: lambda$onCreate$38$com-byd-vrassistant-MainActivity, reason: not valid java name */
    /* synthetic */ void m116lambda$onCreate$38$combydvrassistantMainActivity(MusicInfo musicInfo) {
        this.test_msg.append("id:" + musicInfo.getId() + ",name:" + musicInfo.getSongName() + ",artist:" + musicInfo.getArtist() + ",album:" + musicInfo.getAlbum() + "\n");
    }

    /* JADX INFO: renamed from: lambda$onCreate$39$com-byd-vrassistant-MainActivity, reason: not valid java name */
    /* synthetic */ void m117lambda$onCreate$39$combydvrassistantMainActivity(View view) {
        LocalMediaManager.get().searchLocalMedia().forEach(new Consumer() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda41
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m116lambda$onCreate$38$combydvrassistantMainActivity((MusicInfo) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onCreate$42$com-byd-vrassistant-MainActivity, reason: not valid java name */
    /* synthetic */ void m120lambda$onCreate$42$combydvrassistantMainActivity(View view) {
        List list = (List) LocalMediaManager.get().searchLocalMedia().stream().filter(new Predicate() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda42
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((MusicInfo) obj).getSongName().equalsIgnoreCase("See You Again");
            }
        }).collect(Collectors.toList());
        if (list.size() > 0) {
            list.forEach(new Consumer() { // from class: com.byd.vrassistant.MainActivity$$ExternalSyntheticLambda43
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.m119lambda$onCreate$41$combydvrassistantMainActivity((MusicInfo) obj);
                }
            });
            this.play_index++;
            LocalMediaManager.get().playUsbMediaBySongName((MusicInfo) list.get(0), this.play_index, new CountDownLatch(1));
            if (this.play_index >= 4) {
                this.play_index = 0;
            }
        }
    }

    /* JADX INFO: renamed from: lambda$onCreate$41$com-byd-vrassistant-MainActivity, reason: not valid java name */
    /* synthetic */ void m119lambda$onCreate$41$combydvrassistantMainActivity(MusicInfo musicInfo) {
        this.test_msg.append("name:" + musicInfo.getSongName() + ",id:" + musicInfo.getId() + "\n");
    }

    /* JADX INFO: renamed from: lambda$onCreate$47$com-byd-vrassistant-MainActivity, reason: not valid java name */
    /* synthetic */ void m121lambda$onCreate$47$combydvrassistantMainActivity(View view) {
        ForegroundAppUtil.killApp(this, LocalMediaManager.SPOTIFY_PACKAGE_NAME);
    }

    static /* synthetic */ void lambda$onCreate$48(View view) {
        String str = TAG;
        RLog.i(str, str + "==onLowMemory");
        RLog.i(str, str + "==onLowMemory===== ArkAssistant.get().requestTrimMemory==" + ArkAssistant.get().requestTrimMemory());
    }

    /* JADX INFO: renamed from: lambda$onCreate$49$com-byd-vrassistant-MainActivity, reason: not valid java name */
    /* synthetic */ void m122lambda$onCreate$49$combydvrassistantMainActivity(View view) {
        getStorage(BydVoiceApplication.getContext());
    }

    private List<String> getEnableAudioZones(boolean z) {
        ArrayList arrayList = new ArrayList();
        if (z) {
            arrayList.add(VehicleAreaEnum.SEAT_ROW_1_RIGHT);
            arrayList.add(VehicleAreaEnum.SEAT_ROW_1_LEFT);
            arrayList.add(VehicleAreaEnum.SEAT_ROW_2_RIGHT);
            arrayList.add(VehicleAreaEnum.SEAT_ROW_2_LEFT);
        } else {
            arrayList.add(VehicleAreaEnum.SEAT_ROW_1_LEFT);
            arrayList.add(VehicleAreaEnum.SEAT_ROW_1_RIGHT);
            arrayList.add(VehicleAreaEnum.SEAT_ROW_2_LEFT);
            arrayList.add(VehicleAreaEnum.SEAT_ROW_2_RIGHT);
        }
        return arrayList;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        this.isInitial = true;
        String str = TAG;
        RLog.i(str, "onResume");
        super.onResume();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        RLog.i(str, "display width = " + displayMetrics.widthPixels + ",height=" + displayMetrics.heightPixels + ",dpi = " + displayMetrics.densityDpi);
        VoiceAssistantManager.get().initLanguageDatas();
        startMyService();
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.language);
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        arrayAdapter.addAll(VoiceAssistantManager.get().getAsrLanguages());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.byd.vrassistant.MainActivity.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (MainActivity.this.isInitial) {
                    MainActivity.this.isInitial = false;
                } else {
                    RLog.i(MainActivity.TAG, "onItemSelected = " + i);
                    VoiceAssistantManager.get().setVoiceAssistantLanguage(VoiceAssistantManager.get().getAsrLanguages().get(i));
                }
            }
        });
        RLog.d(str, "BYD_Radio MainActivity onResume xxxx");
        int iIndexOf = VoiceAssistantManager.get().getAsrLanguages().indexOf(VoiceAssistantManager.get().getLanguage());
        RLog.i(str, "mLanguageSpinner selected : " + iIndexOf);
        if (iIndexOf >= 0) {
            spinner.setSelection(iIndexOf);
        } else {
            spinner.setSelection(0);
        }
    }

    public void startMyService() {
        RLog.d(TAG, "BYD_Radio startMyService");
        startService(new Intent(this, (Class<?>) MyService.class));
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        RLog.i(TAG, "onDestroy");
        super.onDestroy();
    }

    public void requestPermission() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.permissions) {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), str) != 0) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        ActivityCompat.requestPermissions(this, (String[]) arrayList.toArray(new String[arrayList.size()]), 100);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        finish();
    }

    private void checkPhoneStateListenerCallPermission() {
        RLog.d(TAG, "检测的权限checkPhoneStateListenerCallPermission");
        PermissionX.init(this).permissions("android.permission.READ_PHONE_STATE", "android.permission.READ_CONTACTS").onExplainRequestReason(new ExplainReasonCallbackWithBeforeParam() { // from class: com.byd.vrassistant.MainActivity.5
            @Override // com.permissionx.guolindev.callback.ExplainReasonCallbackWithBeforeParam
            public void onExplainReason(ExplainScope explainScope, List<String> list, boolean z) {
                explainScope.showRequestReasonDialog(list, "来电监听需要授予'访问手机通话记录'的权限，依据来电号码查询联系人需要'读取手机通讯录'权限,否则应用无法正常使用", "我已明白");
            }
        }).onForwardToSettings(new ForwardToSettingsCallback() { // from class: com.byd.vrassistant.MainActivity.4
            @Override // com.permissionx.guolindev.callback.ForwardToSettingsCallback
            public void onForwardToSettings(ForwardScope forwardScope, List<String> list) {
                forwardScope.showForwardToSettingsDialog(list, "您需要去应用程序设置当中手动开启权限", "我已明白");
            }
        }).request(new RequestCallback() { // from class: com.byd.vrassistant.MainActivity.3
            @Override // com.permissionx.guolindev.callback.RequestCallback
            public void onResult(boolean z, List<String> list, List<String> list2) {
                if (z) {
                    RLog.d(MainActivity.TAG, "所有申请的权限都已通过");
                } else {
                    RLog.d(MainActivity.TAG, "您拒绝了如下权限");
                }
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        RLog.d(TAG, "keyCode " + keyEvent.getKeyCode());
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 327) {
            RLog.i(TAG, "KeyEvent.KEYCODE_328 DOWN");
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 327) {
            RLog.i(TAG, "KeyEvent.KEYCODE_328 UP");
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100) {
            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "Permission Denieddd by user.Please Check it in Settings", 0).show();
            } else {
                Toast.makeText(this, "Permission Allowed", 0).show();
            }
        }
    }

    public void getStorage(Context context) {
        String str = TAG;
        RLog.d(str, "getStorage start");
        List<StorageVolume> storageVolumes = ((StorageManager) context.getSystemService("storage")).getStorageVolumes();
        RLog.d(str, "getStorage mlist.size()===========" + storageVolumes.size());
        Toast.makeText(getApplication(), "mUsbotgUuid==" + SystemPropertiesUtils.get("sys.byd.mUsbotgUuid", "defaultvalue"), 0).show();
        Toast.makeText(getApplication(), "mSdcardUuid==" + SystemPropertiesUtils.get("sys.byd.mSdcardUuid", "defaultvalue"), 0).show();
        String uuid = "";
        String uuid2 = uuid;
        for (StorageVolume storageVolume : storageVolumes) {
            Toast.makeText(getApplication(), "getUuid==" + storageVolume.getUuid(), 0).show();
            Toast.makeText(getApplication(), "getState==" + storageVolume.getState(), 0).show();
            Toast.makeText(getApplication(), "getDescription==" + storageVolume.getDescription(context), 0).show();
            if (storageVolume.getUuid() != null && storageVolume.getUuid().equals(SystemPropertiesUtils.get("sys.byd.mUsbotgUuid", "defaultvalue"))) {
                uuid2 = storageVolume.getUuid();
            }
            if (storageVolume.getUuid() != null && storageVolume.getUuid().equals(SystemPropertiesUtils.get("sys.byd.mSdcardUuid", "defaultvalue"))) {
                uuid = storageVolume.getUuid();
            }
        }
        Toast.makeText(getApplication(), "SDUUID==" + uuid, 0).show();
        Toast.makeText(getApplication(), "UsbUUID==" + uuid2, 0).show();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(new ScanMusicList(BydVoiceApplication.getContext()).scanMusicList("external"));
        Toast.makeText(getApplication(), "All songlist size==" + arrayList.size(), 0).show();
        if (!uuid2.equals("")) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(new ScanMusicList(BydVoiceApplication.getContext()).scanMusicList(uuid2.toLowerCase(Locale.ROOT)));
            Toast.makeText(getApplication(), "usb songlist size==" + arrayList2.size(), 0).show();
            for (int i = 0; i < arrayList2.size(); i++) {
                Toast.makeText(getApplication(), "usb songlist getName==" + ((Song) arrayList2.get(i)).getName(), 0).show();
                Toast.makeText(getApplication(), "usb songlist getSize==" + ((Song) arrayList2.get(i)).getSize(), 0).show();
            }
        }
        if (uuid.equals("")) {
            return;
        }
        ArrayList arrayList3 = new ArrayList();
        arrayList3.addAll(new ScanMusicList(BydVoiceApplication.getContext()).scanMusicList(uuid.toLowerCase(Locale.ROOT)));
        Toast.makeText(getApplication(), "sd songlist size==" + arrayList3.size(), 0).show();
        for (int i2 = 0; i2 < arrayList3.size(); i2++) {
            Toast.makeText(getApplication(), "sd songlist getName==" + ((Song) arrayList3.get(i2)).getName(), 0).show();
            Toast.makeText(getApplication(), "sd songlist getSize==" + ((Song) arrayList3.get(i2)).getSize(), 0).show();
        }
    }
}
