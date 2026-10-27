package com.android.permissioncontroller.role.model;

import android.app.ActivityManager;
import android.app.role.RoleManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Process;
import android.os.UserHandle;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.role.utils.UserUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes8.dex */
public class AssistantRoleBehavior implements RoleBehavior {
    private static final String LOG_TAG = AssistantRoleBehavior.class.getSimpleName();
    private static final Intent ASSIST_SERVICE_PROBE = new Intent("android.service.voice.VoiceInteractionService");
    private static final Intent ASSIST_ACTIVITY_PROBE = new Intent("android.intent.action.ASSIST");

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public void onRoleAdded(Role role, Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager.isDeviceUpgrading()) {
            RoleManager roleManager = (RoleManager) context.getSystemService(RoleManager.class);
            List<String> packageNames = roleManager.getRoleHolders(role.getName());
            if (packageNames.isEmpty()) {
                role.onNoneHolderSelectedAsUser(Process.myUserHandle(), context);
            }
        }
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public boolean isAvailableAsUser(Role role, UserHandle user, Context context) {
        return !UserUtils.isWorkProfile(user, context);
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public boolean isVisibleAsUser(Role role, UserHandle user, Context context) {
        return VisibilityMixin.isVisible("config_showDefaultAssistant", context);
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public Intent getManageIntentAsUser(Role role, UserHandle user, Context context) {
        return new Intent("android.settings.VOICE_INPUT_SETTINGS");
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public CharSequence getConfirmationMessage(Role role, String packageName, Context context) {
        return context.getString(R.string.assistant_confirmation_message);
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public List<String> getQualifyingPackagesAsUser(Role role, UserHandle user, Context context) {
        Context userContext = UserUtils.getUserContext(context, user);
        ActivityManager userActivityManager = (ActivityManager) userContext.getSystemService(ActivityManager.class);
        PackageManager userPackageManager = userContext.getPackageManager();
        Set<String> availableAssistants = new ArraySet<>();
        if (!userActivityManager.isLowRamDevice()) {
            List<ResolveInfo> services = userPackageManager.queryIntentServices(ASSIST_SERVICE_PROBE, 786560);
            int numServices = services.size();
            for (int i = 0; i < numServices; i++) {
                ResolveInfo service = services.get(i);
                if (isAssistantVoiceInteractionService(userPackageManager, service.serviceInfo)) {
                    availableAssistants.add(service.serviceInfo.packageName);
                }
            }
        }
        List<ResolveInfo> activities = userPackageManager.queryIntentActivities(ASSIST_ACTIVITY_PROBE, 851968);
        int numActivities = activities.size();
        for (int i2 = 0; i2 < numActivities; i2++) {
            availableAssistants.add(activities.get(i2).activityInfo.packageName);
        }
        return new ArrayList(availableAssistants);
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public Boolean isPackageQualified(Role role, String packageName, Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ActivityManager.class);
        PackageManager packageManager = context.getPackageManager();
        boolean hasAssistantService = false;
        if (!activityManager.isLowRamDevice()) {
            Intent pkgServiceProbe = new Intent(ASSIST_SERVICE_PROBE).setPackage(packageName);
            List<ResolveInfo> services = packageManager.queryIntentServices(pkgServiceProbe, 786560);
            hasAssistantService = !services.isEmpty();
            int numServices = services.size();
            for (int i = 0; i < numServices; i++) {
                ResolveInfo service = services.get(i);
                if (isAssistantVoiceInteractionService(packageManager, service.serviceInfo)) {
                    return true;
                }
            }
        }
        Intent pkgActivityProbe = new Intent(ASSIST_ACTIVITY_PROBE).setPackage(packageName);
        boolean hasAssistantActivity = true ^ packageManager.queryIntentActivities(pkgActivityProbe, 851968).isEmpty();
        if (!hasAssistantActivity) {
            String str = LOG_TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Package ");
            sb.append(packageName);
            sb.append(" not qualified for ");
            sb.append(role.getName());
            sb.append(" due to ");
            sb.append(hasAssistantService ? "unqualified" : "missing");
            sb.append(" service and missing activity");
            Log.w(str, sb.toString());
        }
        return Boolean.valueOf(hasAssistantActivity);
    }

    private boolean isAssistantVoiceInteractionService(PackageManager pm, ServiceInfo si) {
        int type;
        if (!"android.permission.BIND_VOICE_INTERACTION".equals(si.permission)) {
            return false;
        }
        try {
            XmlResourceParser parser = si.loadXmlMetaData(pm, "android.voice_interaction");
            if (parser == null) {
                if (parser != null) {
                    parser.close();
                }
                return false;
            }
            do {
                try {
                    type = parser.next();
                    if (type == 1) {
                        break;
                    }
                } catch (Throwable th) {
                    if (parser != null) {
                        try {
                            parser.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } while (type != 2);
            String sessionService = null;
            String recognitionService = null;
            boolean supportsAssist = false;
            AttributeSet attrs = Xml.asAttributeSet(parser);
            int numAttrs = attrs.getAttributeCount();
            for (int i = 0; i < numAttrs; i++) {
                int attributeNameResource = attrs.getAttributeNameResource(i);
                if (attributeNameResource == 16843837) {
                    sessionService = attrs.getAttributeValue(i);
                } else if (attributeNameResource == 16843932) {
                    recognitionService = attrs.getAttributeValue(i);
                } else if (attributeNameResource == 16844016) {
                    supportsAssist = attrs.getAttributeBooleanValue(i, false);
                }
            }
            if (sessionService == null || recognitionService == null || !supportsAssist) {
                if (parser != null) {
                    parser.close();
                }
                return false;
            }
            if (parser != null) {
                parser.close();
            }
            return true;
        } catch (Resources.NotFoundException | IOException | XmlPullParserException e) {
            return false;
        }
    }
}
