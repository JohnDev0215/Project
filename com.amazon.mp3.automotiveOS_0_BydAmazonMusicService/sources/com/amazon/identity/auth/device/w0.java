package com.amazon.identity.auth.device;

import android.content.Intent;
import com.amazon.identity.auth.device.api.MultipleAccountManager;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public interface w0 {
    String a(MultipleAccountManager.AccountMappingType accountMappingType);

    String a(MultipleAccountManager.AccountMappingType... accountMappingTypeArr);

    boolean a(String str, MultipleAccountManager.AccountMappingType accountMappingType);

    boolean a(String str, MultipleAccountManager.AccountMappingType... accountMappingTypeArr);

    Intent b(MultipleAccountManager.AccountMappingType accountMappingType);

    boolean b(String str, MultipleAccountManager.AccountMappingType... accountMappingTypeArr);
}
