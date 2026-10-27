package com.android.launcher3.icons;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import com.android.launcher3.icons.cache.CachingLogic;

/* JADX INFO: loaded from: classes4.dex */
public interface ComponentWithLabel {
    ComponentName getComponent();

    CharSequence getLabel(PackageManager pm);

    UserHandle getUser();

    public static class ComponentCachingLogic implements CachingLogic<ComponentWithLabel> {
        private final PackageManager mPackageManager;

        public ComponentCachingLogic(Context context) {
            this.mPackageManager = context.getPackageManager();
        }

        @Override // com.android.launcher3.icons.cache.CachingLogic
        public ComponentName getComponent(ComponentWithLabel object) {
            return object.getComponent();
        }

        @Override // com.android.launcher3.icons.cache.CachingLogic
        public UserHandle getUser(ComponentWithLabel object) {
            return object.getUser();
        }

        @Override // com.android.launcher3.icons.cache.CachingLogic
        public CharSequence getLabel(ComponentWithLabel object) {
            return object.getLabel(this.mPackageManager);
        }

        @Override // com.android.launcher3.icons.cache.CachingLogic
        public void loadIcon(Context context, ComponentWithLabel object, BitmapInfo target) {
            target.icon = BitmapInfo.LOW_RES_ICON;
        }
    }
}
