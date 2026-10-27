package com.google.firebase;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import com.amazon.music.skyfire.core.template.OwnerIdGenerator;
import com.fasterxml.jackson.core.JsonPointer;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.heartbeatinfo.DefaultHeartBeatController;
import com.google.firebase.platforminfo.DefaultUserAgentPublisher;
import com.google.firebase.platforminfo.KotlinDetector;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.ArrayList;
import java.util.List;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: loaded from: classes6.dex */
public class FirebaseCommonRegistrar implements ComponentRegistrar {
    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(DefaultUserAgentPublisher.component());
        arrayList.add(DefaultHeartBeatController.component());
        arrayList.add(LibraryVersionComponent.create("fire-android", String.valueOf(Build.VERSION.SDK_INT)));
        arrayList.add(LibraryVersionComponent.create("fire-core", "20.3.0"));
        arrayList.add(LibraryVersionComponent.create("device-name", safeValue(Build.PRODUCT)));
        arrayList.add(LibraryVersionComponent.create("device-model", safeValue(Build.DEVICE)));
        arrayList.add(LibraryVersionComponent.create("device-brand", safeValue(Build.BRAND)));
        arrayList.add(LibraryVersionComponent.fromContext("android-target-sdk", new LibraryVersionComponent.VersionExtractor() { // from class: com.google.firebase.FirebaseCommonRegistrar$$ExternalSyntheticLambda0
            @Override // com.google.firebase.platforminfo.LibraryVersionComponent.VersionExtractor
            public final String extract(Object obj) {
                return FirebaseCommonRegistrar.lambda$getComponents$0((Context) obj);
            }
        }));
        arrayList.add(LibraryVersionComponent.fromContext("android-min-sdk", new LibraryVersionComponent.VersionExtractor() { // from class: com.google.firebase.FirebaseCommonRegistrar$$ExternalSyntheticLambda1
            @Override // com.google.firebase.platforminfo.LibraryVersionComponent.VersionExtractor
            public final String extract(Object obj) {
                return FirebaseCommonRegistrar.lambda$getComponents$1((Context) obj);
            }
        }));
        arrayList.add(LibraryVersionComponent.fromContext("android-platform", new LibraryVersionComponent.VersionExtractor() { // from class: com.google.firebase.FirebaseCommonRegistrar$$ExternalSyntheticLambda2
            @Override // com.google.firebase.platforminfo.LibraryVersionComponent.VersionExtractor
            public final String extract(Object obj) {
                return FirebaseCommonRegistrar.lambda$getComponents$2((Context) obj);
            }
        }));
        arrayList.add(LibraryVersionComponent.fromContext("android-installer", new LibraryVersionComponent.VersionExtractor() { // from class: com.google.firebase.FirebaseCommonRegistrar$$ExternalSyntheticLambda3
            @Override // com.google.firebase.platforminfo.LibraryVersionComponent.VersionExtractor
            public final String extract(Object obj) {
                return FirebaseCommonRegistrar.lambda$getComponents$3((Context) obj);
            }
        }));
        String strDetectVersion = KotlinDetector.detectVersion();
        if (strDetectVersion != null) {
            arrayList.add(LibraryVersionComponent.create("kotlin", strDetectVersion));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$getComponents$0(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        return applicationInfo != null ? String.valueOf(applicationInfo.targetSdkVersion) : "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$getComponents$1(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        return applicationInfo != null ? String.valueOf(applicationInfo.minSdkVersion) : "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$getComponents$2(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.type.television") ? "tv" : context.getPackageManager().hasSystemFeature("android.hardware.type.watch") ? OwnerIdGenerator.watch : context.getPackageManager().hasSystemFeature("android.hardware.type.automotive") ? DebugKt.DEBUG_PROPERTY_VALUE_AUTO : (Build.VERSION.SDK_INT < 26 || !context.getPackageManager().hasSystemFeature("android.hardware.type.embedded")) ? "" : "embedded";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$getComponents$3(Context context) {
        String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
        return installerPackageName != null ? safeValue(installerPackageName) : "";
    }

    private static String safeValue(String str) {
        return str.replace(' ', '_').replace(JsonPointer.SEPARATOR, '_');
    }
}
