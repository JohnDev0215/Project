package com.amazon.identity.auth.device;

import android.content.Intent;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public interface x0 {
    String a();

    void a(String str, Intent intent, String str2);

    void a(String str, Bundle bundle, Bundle bundle2);

    void a(String str, Set<Integer> set, Intent intent, String str2);

    boolean a(String str);

    MultipleAccountManager.AccountMappingType[] a(String str, int i);

    void b(String str, Intent intent, String str2);

    void c(String str, Intent intent, String str2);
}
