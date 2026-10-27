package com.byd.vrassistant;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import cerence.ark.assistant.controller.enumeration.VehicleAreaEnum;
import com.byd.vrassistant.common.ICommonSuccessInterface;
import com.byd.vrassistant.manager.SystemSettingManager;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import com.byd.vrassistant.utils.ForegroundAppUtil;
import com.byd.vrassistant.utils.RLog;

/* JADX INFO: loaded from: classes.dex */
public class MonkeyActivity extends AppCompatActivity {
    private static final String TAG = "NEU_MonkeyActivity";
    Runnable hardStart8155 = new Runnable() { // from class: com.byd.vrassistant.MonkeyActivity.1
        @Override // java.lang.Runnable
        public void run() {
            Log.i(MonkeyActivity.TAG, "run:hardStart8155 ");
            if (!VoiceAssistantManager.get().getIsVoiceAssistantIniting()) {
                RLog.i(MonkeyActivity.TAG, "getIsVoiceAssistantIniting = false");
                if (VoiceAssistantManager.get().getShowGuideResult() && !ForegroundAppUtil.isForegroundApp(BydVoiceApplication.getContext(), "com.byd.vrsettings")) {
                    SystemSettingManager.get().openScreenByName("com.byd.vrsettings", "com.byd.vrsettings.MainActivity");
                }
            }
            try {
                VoiceAssistantManager.get().sendPTTEvent(SystemSettingManager.get().isRightDriver() ? VehicleAreaEnum.SEAT_ROW_1_RIGHT : VehicleAreaEnum.SEAT_ROW_1_LEFT, new ICommonSuccessInterface() { // from class: com.byd.vrassistant.MonkeyActivity.1.1
                    @Override // com.byd.vrassistant.common.ICommonSuccessInterface
                    public void isSuccess(boolean z) {
                        VoiceAssistantManager.get().setHardKeyPTTEvent(true);
                        VoiceAssistantManager.get().setVirtualKeyPTTEvent(false);
                    }
                });
            } catch (RemoteException e) {
                Log.e(MonkeyActivity.TAG, "hardStart8155: ", e);
            }
        }
    };
    Runnable hardStart6125 = new Runnable() { // from class: com.byd.vrassistant.MonkeyActivity.2
        @Override // java.lang.Runnable
        public void run() {
            Log.i(MonkeyActivity.TAG, "run:hardStart6125 ");
            MonkeyActivity.this.sendBroadcast(new Intent("android.intent.action.MEDIA_VOICE"));
        }
    };

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setGravity(17);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        setContentView(linearLayout);
        linearLayout.addView(getButton("太极开始", new Runnable() { // from class: com.byd.vrassistant.MonkeyActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m125lambda$onCreate$0$combydvrassistantMonkeyActivity();
            }
        }));
        linearLayout.addView(getButton("太极取消", new Runnable() { // from class: com.byd.vrassistant.MonkeyActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m126lambda$onCreate$1$combydvrassistantMonkeyActivity();
            }
        }));
        linearLayout.addView(getButton("硬按键8155", this.hardStart8155));
    }

    /* JADX INFO: renamed from: lambda$onCreate$0$com-byd-vrassistant-MonkeyActivity, reason: not valid java name */
    /* synthetic */ void m125lambda$onCreate$0$combydvrassistantMonkeyActivity() {
        Log.i(TAG, "run:太极Start ");
        Intent intent = new Intent("com.intent.action.StartVoice_From_Launcher");
        intent.setComponent(new ComponentName(BuildConfig.APPLICATION_ID, "com.byd.vrassistant.receiver.HardKeyReceiver"));
        sendBroadcast(intent);
    }

    /* JADX INFO: renamed from: lambda$onCreate$1$com-byd-vrassistant-MonkeyActivity, reason: not valid java name */
    /* synthetic */ void m126lambda$onCreate$1$combydvrassistantMonkeyActivity() {
        Log.i(TAG, "run:太极Start ");
        Intent intent = new Intent("com.byd.vrassistant.ACTION_CANCEL");
        intent.setComponent(new ComponentName(BuildConfig.APPLICATION_ID, "com.byd.vrassistant.receiver.HardKeyReceiver"));
        sendBroadcast(intent);
    }

    private /* synthetic */ void lambda$onCreate$2() {
        Log.i(TAG, "run:太极Setting ");
        Intent intent = new Intent("com.byd.action.come.from.launcher_Receiver");
        intent.setComponent(new ComponentName(BuildConfig.APPLICATION_ID, "com.byd.vrassistant.receiver.HardKeyReceiver"));
        sendBroadcast(intent);
    }

    private Button getButton(String str, final Runnable runnable) {
        Button button = new Button(this);
        button.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        button.setText(str);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.MonkeyActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                runnable.run();
            }
        });
        return button;
    }
}
