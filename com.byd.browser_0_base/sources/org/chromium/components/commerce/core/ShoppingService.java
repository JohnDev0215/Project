package org.chromium.components.commerce.core;

import defpackage.aw2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class ShoppingService {
    public long a;
    public final b<aw2> b = new b<>();

    public static final class MerchantInfo {
        public final float a;
        public final int b;
        public final GURL c;
        public final boolean d;
        public final float e;
        public final boolean f;
        public final boolean g;

        public MerchantInfo(float f, int i, GURL gurl, boolean z, float f2, boolean z2, boolean z3) {
            this.a = f;
            this.b = i;
            this.c = gurl;
            this.d = z;
            this.e = f2;
            this.f = z2;
            this.g = z3;
        }
    }

    public interface MerchantInfoCallback {
        void a(MerchantInfo merchantInfo);
    }

    public static final class ProductInfo {
        public final long a;

        public ProductInfo(long j) {
            this.a = j;
        }
    }

    public interface ProductInfoCallback {
        void a(ProductInfo productInfo);
    }

    public ShoppingService(long j) {
        this.a = j;
    }

    @CalledByNative
    public static ShoppingService create(long j) {
        return new ShoppingService(j);
    }

    @CalledByNative
    public static MerchantInfo createMerchantInfo(float f, int i, GURL gurl, boolean z, float f2, boolean z2, boolean z3) {
        return new MerchantInfo(f, i, gurl, z, f2, z2, z3);
    }

    @CalledByNative
    public static ProductInfo createProductInfo(String str, GURL gurl, long j, long j2, String str2, long j3, String str3, boolean z, long j4) {
        if (z) {
            Optional.empty();
        } else {
            Optional.of(Long.valueOf(j4));
        }
        return new ProductInfo(j);
    }

    @CalledByNative
    public static void runMerchantInfoCallback(MerchantInfoCallback merchantInfoCallback, GURL gurl, MerchantInfo merchantInfo) {
        merchantInfoCallback.a(merchantInfo);
    }

    @CalledByNative
    public static void runProductInfoCallback(ProductInfoCallback productInfoCallback, GURL gurl, ProductInfo productInfo) {
        productInfoCallback.a(productInfo);
    }

    @CalledByNative
    public final List<a> createSubscriptionAndAddToList(List<a> list, int i, int i2, int i3, String str) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(new a(i, i2, str, i3, null));
        return list;
    }

    @CalledByNative
    public final void destroy() {
        this.a = 0L;
        this.b.clear();
    }

    @CalledByNative
    public final void onSubscribe(List<a> list, boolean z) {
        Iterator<aw2> it = this.b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((aw2) aVar.next()).c(list, z);
            }
        }
    }

    @CalledByNative
    public final void onUnsubscribe(List<a> list, boolean z) {
        Iterator<aw2> it = this.b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((aw2) aVar.next()).b(list, z);
            }
        }
    }
}
