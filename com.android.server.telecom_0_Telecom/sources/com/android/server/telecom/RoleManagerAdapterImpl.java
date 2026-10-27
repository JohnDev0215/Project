package com.android.server.telecom;

import android.app.role.OnRoleHoldersChangedListener;
import android.app.role.RoleManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.telecom.Log;
import com.android.internal.util.IndentingPrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class RoleManagerAdapterImpl implements RoleManagerAdapter {
    private Context mContext;
    private UserHandle mCurrentUserHandle;
    private RoleManager mRoleManager;
    private String mOverrideDefaultCallRedirectionApp = null;
    private String mOverrideDefaultCallScreeningApp = null;
    private String mOverrideDefaultDialerApp = null;
    private List<String> mOverrideCallCompanionApps = new ArrayList();

    public RoleManagerAdapterImpl(Context context, RoleManager roleManager) {
        this.mContext = context;
        this.mRoleManager = roleManager;
    }

    @Override // com.android.server.telecom.RoleManagerAdapter
    public String getDefaultCallRedirectionApp() {
        String str = this.mOverrideDefaultCallRedirectionApp;
        return str != null ? str : getRoleManagerCallRedirectionApp();
    }

    @Override // com.android.server.telecom.RoleManagerAdapter
    public void setTestDefaultCallRedirectionApp(String str) {
        this.mOverrideDefaultCallRedirectionApp = str;
    }

    @Override // com.android.server.telecom.RoleManagerAdapter
    public String getDefaultCallScreeningApp() {
        String str = this.mOverrideDefaultCallScreeningApp;
        return str != null ? str : getRoleManagerCallScreeningApp();
    }

    @Override // com.android.server.telecom.RoleManagerAdapter
    public void setTestDefaultCallScreeningApp(String str) {
        this.mOverrideDefaultCallScreeningApp = str;
    }

    @Override // com.android.server.telecom.RoleManagerAdapter
    public String getDefaultDialerApp(int i) {
        String str = this.mOverrideDefaultDialerApp;
        return str != null ? str : getRoleManagerDefaultDialerApp(i);
    }

    @Override // com.android.server.telecom.RoleManagerAdapter
    public void observeDefaultDialerApp(Executor executor, final IntConsumer intConsumer) {
        this.mRoleManager.addOnRoleHoldersChangedListenerAsUser(executor, new OnRoleHoldersChangedListener() { // from class: com.android.server.telecom.-$$Lambda$RoleManagerAdapterImpl$47dIpf4gW451IC3PfEg8sCyX3pQ
            public final void onRoleHoldersChanged(String str, UserHandle userHandle) {
                intConsumer.accept(userHandle.getIdentifier());
            }
        }, UserHandle.ALL);
    }

    @Override // com.android.server.telecom.RoleManagerAdapter
    public void setTestDefaultDialer(String str) {
        this.mOverrideDefaultDialerApp = str;
    }

    @Override // com.android.server.telecom.RoleManagerAdapter
    public List<String> getCallCompanionApps() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.mOverrideCallCompanionApps);
        return arrayList;
    }

    @Override // com.android.server.telecom.RoleManagerAdapter
    public void addOrRemoveTestCallCompanionApp(String str, boolean z) {
        if (z) {
            this.mOverrideCallCompanionApps.add(str);
        } else {
            this.mOverrideCallCompanionApps.remove(str);
        }
    }

    @Override // com.android.server.telecom.RoleManagerAdapter
    public void setCurrentUserHandle(UserHandle userHandle) {
        this.mCurrentUserHandle = userHandle;
    }

    private String getRoleManagerCallScreeningApp() {
        List roleHoldersAsUser = this.mRoleManager.getRoleHoldersAsUser("android.app.role.CALL_SCREENING", this.mCurrentUserHandle);
        if (roleHoldersAsUser == null || roleHoldersAsUser.isEmpty()) {
            return null;
        }
        return (String) roleHoldersAsUser.get(0);
    }

    private String getRoleManagerDefaultDialerApp(int i) {
        List roleHoldersAsUser = this.mRoleManager.getRoleHoldersAsUser("android.app.role.DIALER", new UserHandle(i));
        if (roleHoldersAsUser == null || roleHoldersAsUser.isEmpty()) {
            return null;
        }
        return (String) roleHoldersAsUser.get(0);
    }

    private List<String> getRoleManagerCallCompanionApps() {
        return new ArrayList();
    }

    private String getRoleManagerCallRedirectionApp() {
        List roleHoldersAsUser = this.mRoleManager.getRoleHoldersAsUser("android.app.role.CALL_REDIRECTION", this.mCurrentUserHandle);
        if (roleHoldersAsUser == null || roleHoldersAsUser.isEmpty()) {
            return null;
        }
        return (String) roleHoldersAsUser.get(0);
    }

    @Override // com.android.server.telecom.RoleManagerAdapter
    public String getApplicationLabelForPackageName(String str) {
        ApplicationInfo applicationInfo;
        PackageManager packageManager = this.mContext.getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            Log.d(this, "Application info not found for packageName " + str, new Object[0]);
            applicationInfo = null;
        }
        return applicationInfo == null ? str : applicationInfo.loadLabel(packageManager).toString();
    }

    public void dump(IndentingPrintWriter indentingPrintWriter) {
        indentingPrintWriter.print("DefaultCallRedirectionApp: ");
        if (this.mOverrideDefaultCallRedirectionApp != null) {
            indentingPrintWriter.print("(override ");
            indentingPrintWriter.print(this.mOverrideDefaultCallRedirectionApp);
            indentingPrintWriter.print(") ");
            indentingPrintWriter.print(getRoleManagerCallRedirectionApp());
        }
        indentingPrintWriter.println();
        indentingPrintWriter.print("DefaultCallScreeningApp: ");
        if (this.mOverrideDefaultCallScreeningApp != null) {
            indentingPrintWriter.print("(override ");
            indentingPrintWriter.print(this.mOverrideDefaultCallScreeningApp);
            indentingPrintWriter.print(") ");
            indentingPrintWriter.print(getRoleManagerCallScreeningApp());
        }
        indentingPrintWriter.println();
        indentingPrintWriter.print("DefaultCallCompanionApps: ");
        if (this.mOverrideCallCompanionApps != null) {
            indentingPrintWriter.print("(override ");
            indentingPrintWriter.print((String) this.mOverrideCallCompanionApps.stream().collect(Collectors.joining(", ")));
            indentingPrintWriter.print(") ");
            List<String> roleManagerCallCompanionApps = getRoleManagerCallCompanionApps();
            if (roleManagerCallCompanionApps != null) {
                indentingPrintWriter.print((String) roleManagerCallCompanionApps.stream().collect(Collectors.joining(", ")));
            }
        }
        indentingPrintWriter.println();
    }
}
