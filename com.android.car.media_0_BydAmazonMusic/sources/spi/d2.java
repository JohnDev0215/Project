package spi;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import com.android.car.uxr.R$styleable;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import spi.c2;

/* JADX INFO: compiled from: CarUxRestrictionsAppConfigParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d2 {
    public static c2.b a(Context context, XmlResourceParser xmlResourceParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        String str;
        String str2;
        xmlResourceParser.require(2, null, "ListConfig");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CarUxRestrictionsAppConfig_ListConfig);
        try {
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.CarUxRestrictionsAppConfig_ListConfig_id, -1);
            if (resourceId == -1) {
                throw new IllegalStateException("Id field is required");
            }
            int i = R$styleable.CarUxRestrictionsAppConfig_ListConfig_message;
            boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(i);
            int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(i, -1);
            if (Log.isLoggable("UxrAppConfigParser", 3)) {
                if (zHasValue) {
                    str2 = "message field is set to " + resourceId2;
                } else {
                    str2 = "message field not specified";
                }
                Log.d("UxrAppConfigParser", str2);
            }
            int i2 = R$styleable.CarUxRestrictionsAppConfig_ListConfig_maxLength;
            boolean zHasValue2 = typedArrayObtainStyledAttributes.hasValue(i2);
            int i3 = typedArrayObtainStyledAttributes.getInt(i2, 0);
            if (Log.isLoggable("UxrAppConfigParser", 3)) {
                if (zHasValue2) {
                    str = "maxLength field is set to " + i3;
                } else {
                    str = "maxLength field not specified";
                }
                Log.d("UxrAppConfigParser", str);
            }
            xmlResourceParser.next();
            xmlResourceParser.require(3, null, "ListConfig");
            c2.b.a aVar = new c2.b.a(resourceId);
            if (zHasValue2) {
                aVar.b = Integer.valueOf(i3);
            }
            if (zHasValue) {
                aVar.c = Integer.valueOf(resourceId2);
            }
            return new c2.b(aVar.a, aVar.b, aVar.c);
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }
}
