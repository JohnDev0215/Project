package com.byd.overseaappstore.module.ability.data.upper;

import android.graphics.drawable.Drawable;
import com.byd.car.locale.ccTLD;
import com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareDtFixInfo;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareExtParam;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\n\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001BÑ\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a\u0012\u0006\u0010\u001c\u001a\u00020\u001d¢\u0006\u0002\u0010\u001eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/upper/SoftwareInstalled;", "Lcom/byd/overseaappstore/module/ability/data/upper/Software;", ccTLD.INDONESIA, "", "name", "", "icon", "iconDrawable", "Landroid/graphics/drawable/Drawable;", "packageName", "classifyId", "classifyName", "totalSize", "", "percentCount", "sizeTip", "versionCode", "versionTipRaw", "versionTip", "introduction", "downloadUrl", "signature", "state", "sourceType", "", "extra", "", "Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareExtParam;", "dtFixInfo", "Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareDtFixInfo;", "(ILjava/lang/String;Ljava/lang/String;Landroid/graphics/drawable/Drawable;Ljava/lang/String;ILjava/lang/String;JILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ISLjava/util/List;Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareDtFixInfo;)V", "getIconDrawable", "()Landroid/graphics/drawable/Drawable;", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SoftwareInstalled extends Software {

    @Nullable
    private final Drawable iconDrawable;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SoftwareInstalled(int i, @NotNull String name, @NotNull String icon, @Nullable Drawable drawable, @NotNull String packageName, int i2, @NotNull String classifyName, long j, int i3, @NotNull String sizeTip, int i4, @NotNull String versionTipRaw, @NotNull String versionTip, @NotNull String introduction, @NotNull String downloadUrl, @NotNull String signature, int i5, short s, @Nullable List<SoftwareExtParam> list, @NotNull SoftwareDtFixInfo dtFixInfo) {
        super(i, name, icon, packageName, i2, classifyName, j, i3, sizeTip, i4, versionTipRaw, versionTip, introduction, downloadUrl, signature, i5, s, list, dtFixInfo);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(classifyName, "classifyName");
        Intrinsics.checkNotNullParameter(sizeTip, "sizeTip");
        Intrinsics.checkNotNullParameter(versionTipRaw, "versionTipRaw");
        Intrinsics.checkNotNullParameter(versionTip, "versionTip");
        Intrinsics.checkNotNullParameter(introduction, "introduction");
        Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(dtFixInfo, "dtFixInfo");
        this.iconDrawable = drawable;
    }

    public /* synthetic */ SoftwareInstalled(int i, String str, String str2, Drawable drawable, String str3, int i2, String str4, long j, int i3, String str5, int i4, String str6, String str7, String str8, String str9, String str10, int i5, short s, List list, SoftwareDtFixInfo softwareDtFixInfo, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this((i6 & 1) != 0 ? 0 : i, (i6 & 2) != 0 ? "" : str, (i6 & 4) != 0 ? "" : str2, (i6 & 8) != 0 ? null : drawable, (i6 & 16) != 0 ? "" : str3, (i6 & 32) != 0 ? 0 : i2, (i6 & 64) != 0 ? "" : str4, (i6 & 128) != 0 ? 0L : j, (i6 & 256) != 0 ? 0 : i3, (i6 & 512) != 0 ? "" : str5, (i6 & 1024) != 0 ? 0 : i4, (i6 & 2048) != 0 ? "" : str6, (i6 & 4096) != 0 ? "" : str7, (i6 & 8192) != 0 ? "" : str8, (i6 & 16384) != 0 ? "" : str9, (32768 & i6) != 0 ? "" : str10, (i6 & 65536) != 0 ? 0 : i5, s, list, softwareDtFixInfo);
    }

    @Nullable
    public final Drawable getIconDrawable() {
        return this.iconDrawable;
    }
}
