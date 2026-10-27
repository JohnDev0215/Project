package com.android.server.telecom.ui;

import android.content.Context;
import android.widget.Toast;

/* JADX INFO: loaded from: classes.dex */
public interface ToastFactory {
    Toast makeText(Context context, int i, int i2);

    Toast makeText(Context context, CharSequence charSequence, int i);
}
