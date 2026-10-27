package com.stingray.shared.qrcode;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"com/stingray/shared/qrcode/QRCodeGenerator$QrShape", "", "Lcom/stingray/shared/qrcode/QRCodeGenerator$QrShape;", "<init>", "(Ljava/lang/String;I)V", "Default", "Rounded", "shared_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class QRCodeGenerator$QrShape {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ QRCodeGenerator$QrShape[] $VALUES;
    public static final QRCodeGenerator$QrShape Default = new QRCodeGenerator$QrShape("Default", 0);
    public static final QRCodeGenerator$QrShape Rounded = new QRCodeGenerator$QrShape("Rounded", 1);

    private static final /* synthetic */ QRCodeGenerator$QrShape[] $values() {
        return new QRCodeGenerator$QrShape[]{Default, Rounded};
    }

    static {
        QRCodeGenerator$QrShape[] qRCodeGenerator$QrShapeArr$values = $values();
        $VALUES = qRCodeGenerator$QrShapeArr$values;
        $ENTRIES = r.e(qRCodeGenerator$QrShapeArr$values);
    }

    private QRCodeGenerator$QrShape(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static QRCodeGenerator$QrShape valueOf(String str) {
        return (QRCodeGenerator$QrShape) Enum.valueOf(QRCodeGenerator$QrShape.class, str);
    }

    public static QRCodeGenerator$QrShape[] values() {
        return (QRCodeGenerator$QrShape[]) $VALUES.clone();
    }
}
