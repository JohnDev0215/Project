package com.android.permissioncontroller.role.model;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Process;
import android.os.UserHandle;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes8.dex */
public abstract class RequiredComponent {
    private final IntentFilterData mIntentFilterData;
    private final String mPermission;

    protected abstract ComponentName getComponentComponentName(ResolveInfo resolveInfo);

    protected abstract String getComponentPermission(ResolveInfo resolveInfo);

    protected abstract List<ResolveInfo> queryIntentComponentsAsUser(Intent intent, int i, UserHandle userHandle, Context context);

    public RequiredComponent(IntentFilterData intentFilterData, String permission) {
        this.mIntentFilterData = intentFilterData;
        this.mPermission = permission;
    }

    public IntentFilterData getIntentFilterData() {
        return this.mIntentFilterData;
    }

    public String getPermission() {
        return this.mPermission;
    }

    public ComponentName getQualifyingComponentForPackage(String packageName, Context context) {
        List<ComponentName> componentNames = getQualifyingComponentsInternal(packageName, Process.myUserHandle(), context);
        if (componentNames.isEmpty()) {
            return null;
        }
        return componentNames.get(0);
    }

    public List<ComponentName> getQualifyingComponentsAsUser(UserHandle user, Context context) {
        return getQualifyingComponentsInternal(null, user, context);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.List<android.content.ComponentName> getQualifyingComponentsInternal(java.lang.String r11, android.os.UserHandle r12, android.content.Context r13) {
        /*
            r10 = this;
            com.android.permissioncontroller.role.model.IntentFilterData r0 = r10.mIntentFilterData
            android.content.Intent r0 = r0.createIntent()
            if (r11 == 0) goto Lb
            r0.setPackage(r11)
        Lb:
            r1 = 786432(0xc0000, float:1.102026E-39)
            java.util.List r1 = r10.queryIntentComponentsAsUser(r0, r1, r12, r13)
            android.util.ArraySet r2 = new android.util.ArraySet
            r2.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            int r4 = r1.size()
            r5 = 0
        L20:
            if (r5 >= r4) goto L51
            java.lang.Object r6 = r1.get(r5)
            android.content.pm.ResolveInfo r6 = (android.content.pm.ResolveInfo) r6
            java.lang.String r7 = r10.mPermission
            if (r7 == 0) goto L39
            java.lang.String r7 = r10.getComponentPermission(r6)
            java.lang.String r8 = r10.mPermission
            boolean r8 = java.util.Objects.equals(r7, r8)
            if (r8 != 0) goto L39
            goto L4e
        L39:
            android.content.ComponentName r7 = r10.getComponentComponentName(r6)
            java.lang.String r8 = r7.getPackageName()
            boolean r9 = r2.contains(r8)
            if (r9 == 0) goto L48
            goto L4e
        L48:
            r2.add(r8)
            r3.add(r7)
        L4e:
            int r5 = r5 + 1
            goto L20
        L51:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.role.model.RequiredComponent.getQualifyingComponentsInternal(java.lang.String, android.os.UserHandle, android.content.Context):java.util.List");
    }

    public String toString() {
        return "RequiredComponent{mIntentFilterData=" + this.mIntentFilterData + ", mPermission='" + this.mPermission + "'}";
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        RequiredComponent that = (RequiredComponent) object;
        if (Objects.equals(this.mIntentFilterData, that.mIntentFilterData) && Objects.equals(this.mPermission, that.mPermission)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.mIntentFilterData, this.mPermission);
    }
}
