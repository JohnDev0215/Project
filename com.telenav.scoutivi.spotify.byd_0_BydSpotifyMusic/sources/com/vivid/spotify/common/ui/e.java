package com.vivid.spotify.common.ui;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.l;
import com.vivid.spotify.rootui.x;

/* JADX INFO: compiled from: RootMenuItemViewModel.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ObservableBoolean f5234a = new ObservableBoolean(false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ObservableBoolean f5235b = new ObservableBoolean();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final l<String> f5236c = new l<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final l<Uri> f5237d = new l<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final l<Drawable> f5238e = new l<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private x f5239f;

    public x a() {
        return this.f5239f;
    }

    public void b(x xVar) {
        this.f5239f = xVar;
    }
}
