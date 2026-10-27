package com.android.car.developeroptions.core.instrumentation;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.intelligence.LogProto$SettingsLog;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.LogWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SettingsIntelligenceLogWriter implements LogWriter {
    private SendLogHandler mLogHandler;
    private final Runnable mSendLogsRunnable = new Runnable() { // from class: com.android.car.developeroptions.core.instrumentation.-$$Lambda$SettingsIntelligenceLogWriter$7opZmiqYXAhMmTTBpbM2Pg8Hq3U
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$1$SettingsIntelligenceLogWriter();
        }
    };
    private List<LogProto$SettingsLog> mSettingsLogList = new LinkedList();

    public SettingsIntelligenceLogWriter() {
        HandlerThread handlerThread = new HandlerThread("SettingsIntelligenceLogWriter", 10);
        handlerThread.start();
        this.mLogHandler = new SendLogHandler(handlerThread.getLooper());
    }

    @Override // com.android.settingslib.core.instrumentation.LogWriter
    public void visible(Context context, int i, int i2, int i3) {
        action(i, 1, i2, "", i3);
    }

    @Override // com.android.settingslib.core.instrumentation.LogWriter
    public void hidden(Context context, int i, int i2) {
        action(0, 2, i, "", i2);
    }

    @Override // com.android.settingslib.core.instrumentation.LogWriter
    public void action(Context context, int i, Pair<Integer, Object>... pairArr) {
        action(0, i, 0, "", 0);
    }

    @Override // com.android.settingslib.core.instrumentation.LogWriter
    public void action(Context context, int i, int i2) {
        action(0, i, 0, "", i2);
    }

    @Override // com.android.settingslib.core.instrumentation.LogWriter
    public void action(Context context, int i, boolean z) {
        action(0, i, 0, "", z ? 1 : 0);
    }

    @Override // com.android.settingslib.core.instrumentation.LogWriter
    public void action(Context context, int i, String str) {
        action(0, i, 0, str, 1);
    }

    @Override // com.android.settingslib.core.instrumentation.LogWriter
    public void action(int i, int i2, int i3, String str, int i4) {
        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.systemDefault());
        LogProto$SettingsLog.Builder builderNewBuilder = LogProto$SettingsLog.newBuilder();
        builderNewBuilder.setAttribution(i);
        builderNewBuilder.setAction(i2);
        builderNewBuilder.setPageId(i3);
        if (str == null) {
            str = "";
        }
        builderNewBuilder.setChangedPreferenceKey(str);
        builderNewBuilder.setChangedPreferenceIntValue(i4);
        builderNewBuilder.setTimestamp(zonedDateTimeNow.toString());
        final LogProto$SettingsLog logProto$SettingsLogBuild = builderNewBuilder.build();
        this.mLogHandler.post(new Runnable() { // from class: com.android.car.developeroptions.core.instrumentation.-$$Lambda$SettingsIntelligenceLogWriter$VpDYXyiBK_1n1eKzp3YyLxDtAhI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$action$0$SettingsIntelligenceLogWriter(logProto$SettingsLogBuild);
            }
        });
        this.mLogHandler.scheduleSendLog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$action$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$action$0$SettingsIntelligenceLogWriter(LogProto$SettingsLog logProto$SettingsLog) {
        this.mSettingsLogList.add(logProto$SettingsLog);
    }

    static byte[] serialize(List<LogProto$SettingsLog> list) {
        int size = list.size();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            try {
                dataOutputStream.writeInt(size);
                Iterator<LogProto$SettingsLog> it = list.iterator();
                while (it.hasNext()) {
                    byte[] byteArray = it.next().toByteArray();
                    dataOutputStream.writeInt(byteArray.length);
                    dataOutputStream.write(byteArray);
                }
                return byteArrayOutputStream.toByteArray();
            } finally {
                try {
                    dataOutputStream.close();
                } catch (Exception e) {
                    Log.e("IntelligenceLogWriter", "close error", e);
                }
            }
        } catch (Exception e2) {
            Log.e("IntelligenceLogWriter", "serialize error", e2);
            try {
                dataOutputStream.close();
            } catch (Exception e3) {
                Log.e("IntelligenceLogWriter", "close error", e3);
            }
            return null;
        }
    }

    private class SendLogHandler extends Handler {
        public SendLogHandler(Looper looper) {
            super(looper);
        }

        public void scheduleSendLog() {
            removeCallbacks(SettingsIntelligenceLogWriter.this.mSendLogsRunnable);
            postDelayed(SettingsIntelligenceLogWriter.this.mSendLogsRunnable, 60000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$new$1$SettingsIntelligenceLogWriter() {
        Context appContext = FeatureFactory.getAppContext();
        if (appContext == null) {
            Log.e("IntelligenceLogWriter", "context is null");
            return;
        }
        String string = appContext.getString(R.string.config_settingsintelligence_log_action);
        if (TextUtils.isEmpty(string) || this.mSettingsLogList.isEmpty()) {
            return;
        }
        Intent intent = new Intent();
        intent.setPackage(appContext.getString(R.string.config_settingsintelligence_package_name));
        intent.setAction(string);
        intent.putExtra("logs", serialize(this.mSettingsLogList));
        appContext.sendBroadcastAsUser(intent, UserHandle.CURRENT);
        this.mSettingsLogList.clear();
    }
}
