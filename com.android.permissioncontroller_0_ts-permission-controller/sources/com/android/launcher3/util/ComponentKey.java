package com.android.launcher3.util;

import android.content.ComponentName;
import android.os.UserHandle;
import java.util.Arrays;

/* JADX INFO: loaded from: classes7.dex */
public class ComponentKey {
    public final ComponentName componentName;
    private final int mHashCode;
    public final UserHandle user;

    public ComponentKey(ComponentName componentName, UserHandle user) {
        if (componentName == null || user == null) {
            throw null;
        }
        this.componentName = componentName;
        this.user = user;
        this.mHashCode = Arrays.hashCode(new Object[]{componentName, user});
    }

    public int hashCode() {
        return this.mHashCode;
    }

    public boolean equals(Object o) {
        ComponentKey other = (ComponentKey) o;
        return other.componentName.equals(this.componentName) && other.user.equals(this.user);
    }

    public String toString() {
        return this.componentName.flattenToString() + "#" + this.user.hashCode();
    }

    public static ComponentKey fromString(String str) {
        ComponentName componentName;
        int sep = str.indexOf(35);
        if (sep < 0 || sep + 1 >= str.length() || (componentName = ComponentName.unflattenFromString(str.substring(0, sep))) == null) {
            return null;
        }
        try {
            return new ComponentKey(componentName, UserHandle.getUserHandleForUid(Integer.parseInt(str.substring(sep + 1))));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
