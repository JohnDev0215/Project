package com.byd.automultipletheme.bean;

import com.blankj.utilcode.util.ToastUtils;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImageData.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0006J\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J)\u0010\f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/byd/automultipletheme/bean/ImageData;", "Ljava/io/Serializable;", ToastUtils.MODE.DARK, "", "", ToastUtils.MODE.LIGHT, "(Ljava/util/List;Ljava/util/List;)V", "getDark", "()Ljava/util/List;", "getLight", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class ImageData implements Serializable {
    private final List<String> dark;
    private final List<String> light;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ImageData copy$default(ImageData imageData, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = imageData.dark;
        }
        if ((i & 2) != 0) {
            list2 = imageData.light;
        }
        return imageData.copy(list, list2);
    }

    public final List<String> component1() {
        return this.dark;
    }

    public final List<String> component2() {
        return this.light;
    }

    public final ImageData copy(List<String> dark, List<String> light) {
        Intrinsics.checkNotNullParameter(dark, "dark");
        Intrinsics.checkNotNullParameter(light, "light");
        return new ImageData(dark, light);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImageData)) {
            return false;
        }
        ImageData imageData = (ImageData) other;
        return Intrinsics.areEqual(this.dark, imageData.dark) && Intrinsics.areEqual(this.light, imageData.light);
    }

    public int hashCode() {
        return (this.dark.hashCode() * 31) + this.light.hashCode();
    }

    public String toString() {
        return "ImageData(dark=" + this.dark + ", light=" + this.light + ')';
    }

    public ImageData(List<String> dark, List<String> light) {
        Intrinsics.checkNotNullParameter(dark, "dark");
        Intrinsics.checkNotNullParameter(light, "light");
        this.dark = dark;
        this.light = light;
    }

    public final List<String> getDark() {
        return this.dark;
    }

    public final List<String> getLight() {
        return this.light;
    }
}
