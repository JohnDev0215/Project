package com.android.car.ui.recyclerview;

import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import spi.l1;
import spi.u1;

/* JADX INFO: compiled from: CarUiContentListItem.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends l1 {

    @Nullable
    public Drawable a;

    @Nullable
    public u1 b;

    @NonNull
    public final EnumC0011a c;
    public boolean e;
    public boolean g;

    @Nullable
    public d h;

    @Nullable
    public c i;
    public boolean f = true;

    @NonNull
    public b d = b.STANDARD;

    /* JADX INFO: renamed from: com.android.car.ui.recyclerview.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CarUiContentListItem.java */
    public enum EnumC0011a {
        NONE,
        SWITCH,
        CHECK_BOX,
        RADIO_BUTTON,
        ICON,
        CHEVRON
    }

    /* JADX INFO: compiled from: CarUiContentListItem.java */
    public enum b {
        CONTENT,
        STANDARD,
        AVATAR
    }

    /* JADX INFO: compiled from: CarUiContentListItem.java */
    public interface c {
        void a(@NonNull a aVar, boolean z);
    }

    /* JADX INFO: compiled from: CarUiContentListItem.java */
    public interface d {
    }

    public a(@NonNull EnumC0011a enumC0011a) {
        this.c = enumC0011a;
    }

    public final void a(boolean z) {
        if (z == this.e) {
            return;
        }
        EnumC0011a enumC0011a = this.c;
        if (enumC0011a == EnumC0011a.CHECK_BOX || enumC0011a == EnumC0011a.SWITCH || enumC0011a == EnumC0011a.RADIO_BUTTON) {
            this.e = z;
            c cVar = this.i;
            if (cVar != null) {
                cVar.a(this, z);
            }
        }
    }

    public void b(@Nullable Drawable drawable) {
        this.a = drawable;
    }

    public final void c(@Nullable CharSequence charSequence) {
        if (charSequence != null) {
            this.b = new u1.b(charSequence).a();
        } else {
            Log.e("CarUiContentListItem", "setTitle title == null !!!");
        }
    }

    public void setOnCheckedChangeListener(@Nullable c cVar) {
        this.i = cVar;
    }

    public void setOnItemClickedListener(@Nullable d dVar) {
        this.h = dVar;
    }
}
