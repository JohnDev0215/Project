package com.amazon.identity.auth.device;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class o0 implements x0 {
    public static final String c = "com.amazon.identity.auth.device.o0";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f468a;
    public final z0 b;

    public o0(t5 t5Var, z0 z0Var) {
        this.f468a = t5Var;
        this.b = z0Var;
    }

    @Override // com.amazon.identity.auth.device.x0
    public void a(String str, Bundle bundle, Bundle bundle2) {
    }

    @Override // com.amazon.identity.auth.device.x0
    public void a(String str, Set<Integer> set, Intent intent, String str2) {
        e.a(this.f468a, intent, str2, (m4) null);
    }

    @Override // com.amazon.identity.auth.device.x0
    public void b(String str, Intent intent, String str2) {
        ga.a(c);
    }

    @Override // com.amazon.identity.auth.device.x0
    public void c(String str, Intent intent, String str2) {
        e.a(this.f468a, intent, str2, (m4) null);
    }

    @Override // com.amazon.identity.auth.device.x0
    public void a(String str, Intent intent, String str2) {
        e.a(this.f468a, intent, str2, (m4) null);
    }

    @Override // com.amazon.identity.auth.device.x0
    public MultipleAccountManager.AccountMappingType[] a(String str, int i) {
        return new MultipleAccountManager.AccountMappingType[]{MultipleAccountManager.SessionPackageMappingType.createSessionPackageMappingInstance(this.f468a), new MultipleAccountManager.PackageMappingType(str), new MultipleAccountManager.SessionUserMappingType(), MultipleAccountManager.PrimaryUserMappingType.createPrimaryMappingForProfile(i)};
    }

    @Override // com.amazon.identity.auth.device.x0
    public String a() {
        return this.b.a(new MultipleAccountManager.SessionUserMappingType(), new MultipleAccountManager.PrimaryUserMappingType());
    }

    @Override // com.amazon.identity.auth.device.x0
    public boolean a(String str) {
        boolean zA = this.b.a(str);
        Log.i(ga.a(c), "deregisterAllAccountsOnAccountRemoval returns: " + zA);
        return zA;
    }
}
