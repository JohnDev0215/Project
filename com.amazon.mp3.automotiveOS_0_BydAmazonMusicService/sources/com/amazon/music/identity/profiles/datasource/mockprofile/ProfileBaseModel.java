package com.amazon.music.identity.profiles.datasource.mockprofile;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ProfileBaseModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001B;\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0015¢\u0006\u0004\b\u001c\u0010\u001dR$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR$\u0010\f\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010\u0004\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\"\u0010\u0010\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0010\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/identity/profiles/datasource/mockprofile/ProfileBaseModel;", "", "", "fullName", "Ljava/lang/String;", "getFullName", "()Ljava/lang/String;", "setFullName", "(Ljava/lang/String;)V", "handle", "getHandle", "setHandle", "avatarUrl", "getAvatarUrl", "setAvatarUrl", "", "isSelected", "Z", "()Z", "setSelected", "(Z)V", "Landroid/graphics/Bitmap;", "bitmap", "Landroid/graphics/Bitmap;", "getBitmap", "()Landroid/graphics/Bitmap;", "setBitmap", "(Landroid/graphics/Bitmap;)V", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLandroid/graphics/Bitmap;)V", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0})
public class ProfileBaseModel {
    private String avatarUrl;
    private Bitmap bitmap;
    private String fullName;
    private String handle;
    private boolean isSelected;

    public ProfileBaseModel(String str, String str2, String str3, boolean z, Bitmap bitmap) {
        this.fullName = str;
        this.handle = str2;
        this.avatarUrl = str3;
        this.isSelected = z;
        this.bitmap = bitmap;
    }

    public /* synthetic */ ProfileBaseModel(String str, String str2, String str3, boolean z, Bitmap bitmap, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? false : z, (i & 16) != 0 ? null : bitmap);
    }

    public final String getFullName() {
        return this.fullName;
    }

    public final String getHandle() {
        return this.handle;
    }

    public final String getAvatarUrl() {
        return this.avatarUrl;
    }

    /* JADX INFO: renamed from: isSelected, reason: from getter */
    public final boolean getIsSelected() {
        return this.isSelected;
    }

    public final void setSelected(boolean z) {
        this.isSelected = z;
    }

    public final Bitmap getBitmap() {
        return this.bitmap;
    }
}
