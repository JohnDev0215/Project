package defpackage;

import org.chromium.chrome.R$string;

/* JADX INFO: compiled from: ShippingStrings.java */
/* JADX INFO: loaded from: classes2.dex */
public final class sl2 {
    public final int a;
    public final int b;
    public final int c;
    public final int d;

    public sl2(int i) {
        if (i == 0) {
            this.a = R$string.payments_shipping_address_label;
            this.b = R$string.payments_shipping_option_label;
            this.c = R$string.payments_select_shipping_address_for_shipping_methods;
            this.d = R$string.payments_unsupported_shipping_address;
            return;
        }
        if (i == 1) {
            this.a = R$string.payments_delivery_address_label;
            this.b = R$string.payments_delivery_option_label;
            this.c = R$string.payments_select_delivery_address_for_delivery_methods;
            this.d = R$string.payments_unsupported_delivery_address;
            return;
        }
        if (i != 2) {
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.d = 0;
            return;
        }
        this.a = R$string.payments_pickup_address_label;
        this.b = R$string.payments_pickup_option_label;
        this.c = R$string.payments_select_pickup_address_for_pickup_methods;
        this.d = R$string.payments_unsupported_pickup_address;
    }
}
