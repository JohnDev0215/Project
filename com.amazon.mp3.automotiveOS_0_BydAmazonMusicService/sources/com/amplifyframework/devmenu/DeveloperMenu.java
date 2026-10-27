package com.amplifyframework.devmenu;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.devmenu.ShakeDetector;
import com.amplifyframework.logging.LogLevel;
import com.amplifyframework.logging.Logger;
import com.amplifyframework.util.Empty;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes5.dex */
public final class DeveloperMenu implements ShakeDetector.Listener {
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:devmenu");
    private static DeveloperMenu sInstance;
    private Context context;
    private HideAction hideAction;
    private PersistentLogStoragePlugin loggingPlugin;
    private boolean visible;

    public interface HideAction {
        void hideDeveloperMenu();
    }

    private DeveloperMenu(Context context) {
        this.context = context.getApplicationContext();
    }

    public static DeveloperMenu singletonInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DeveloperMenu(context);
        }
        return sInstance;
    }

    public void enableDeveloperMenu() throws AmplifyException {
        if ((this.context.getApplicationInfo().flags & 2) != 0) {
            PersistentLogStoragePlugin persistentLogStoragePlugin = new PersistentLogStoragePlugin();
            this.loggingPlugin = persistentLogStoragePlugin;
            Amplify.addPlugin(persistentLogStoragePlugin);
            startListening();
        }
    }

    public void startListening() {
        new ShakeDetector(this.context, this).startDetecting();
    }

    @Override // com.amplifyframework.devmenu.ShakeDetector.Listener
    public void onShakeDetected() {
        if (this.visible) {
            HideAction hideAction = this.hideAction;
            if (hideAction != null) {
                hideAction.hideDeveloperMenu();
            }
            this.visible = false;
            return;
        }
        Intent intent = new Intent(this.context, (Class<?>) DeveloperMenuActivity.class);
        intent.setAction("android.intent.action.MAIN");
        intent.setFlags(268435456);
        this.context.startActivity(intent);
        this.visible = true;
    }

    public void setVisible(boolean z) {
        this.visible = z;
    }

    public void setOnHideAction(HideAction hideAction) {
        this.hideAction = hideAction;
    }

    public String getLogs() {
        List<LogEntry> logs = this.loggingPlugin.getLogs();
        return logs.isEmpty() ? "No logs to display." : TextUtils.join("", logs);
    }

    public String getFilteredLogs(@Nullable String str, @Nullable LogLevel logLevel) {
        if (Empty.check(str) && logLevel == null) {
            return getLogs();
        }
        List<LogEntry> logs = this.loggingPlugin.getLogs();
        if (logs.isEmpty()) {
            return "No logs to display.";
        }
        StringBuilder sb = new StringBuilder();
        String lowerCase = str == null ? "" : str.toLowerCase(Locale.US);
        for (LogEntry logEntry : logs) {
            String string = logEntry.toString();
            if (string.toLowerCase(Locale.US).contains(lowerCase) && (logLevel == null || logEntry.getLogLevel() == logLevel)) {
                sb.append(string);
            }
        }
        String string2 = sb.toString();
        return string2.isEmpty() ? "No logs match the search criteria." : string2;
    }

    public String createIssueBody(String str, boolean z) {
        String developerEnvironmentInfo;
        String str2 = "";
        EnvironmentInfo environmentInfo = new EnvironmentInfo();
        String str3 = "*Amplify Plugins Information:*\n" + environmentInfo.getPluginVersions();
        try {
            developerEnvironmentInfo = environmentInfo.getDeveloperEnvironmentInfo(this.context);
        } catch (AmplifyException unused) {
            LOG.warn("Error reading developer environment information.");
            developerEnvironmentInfo = "";
        }
        if (!developerEnvironmentInfo.isEmpty()) {
            str3 = str3 + "\n\n*Developer Environment Information:*\n" + developerEnvironmentInfo;
        }
        String string = new DeviceInfo().toString();
        if (z && !this.loggingPlugin.getLogs().isEmpty()) {
            str2 = "**Logs**\n```\n" + getLogs() + "```";
        }
        return String.format(Locale.US, "**Issue Description**\n%s\n\n**Environment Information**\n%s\n\n**Device Information**\n%s\n\n%s", str, str3, string, str2);
    }

    public void copyToClipboard(String str) {
        ((ClipboardManager) this.context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Copied Text", str));
    }
}
