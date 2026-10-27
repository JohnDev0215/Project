package spi;

import android.car.drivingstate.XCarUxRestrictions;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import androidx.annotation.NonNull;
import androidx.annotation.XmlRes;
import java.io.IOException;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParserException;
import spi.c2;
import spi.e2;

/* JADX INFO: compiled from: UxrContentLimiterImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jc {
    public w2 a;
    public c2.b b;
    public final e2 c;
    public final c2 d;
    public final b e = new b();

    /* JADX INFO: compiled from: UxrContentLimiterImpl.java */
    public class b implements e2.a {
        private b() {
        }

        @Override // spi.e2.a
        public final void a(@NonNull XCarUxRestrictions xCarUxRestrictions) {
            int maxCumulativeContentItems;
            w2 w2Var = jc.this.a;
            if (w2Var == null) {
                Log.w("ContentLimitListener", "No adapter registered.");
                return;
            }
            int iC = w2Var.c();
            if ((xCarUxRestrictions.getActiveRestrictions() & 32) == 0) {
                b("Lists are unrestricted.");
                maxCumulativeContentItems = -1;
            } else {
                c2.b bVar = jc.this.b;
                if (bVar == null || bVar.b == null) {
                    b("No configs found for adapter with the ID: " + iC + " Using the default limit");
                    maxCumulativeContentItems = xCarUxRestrictions.getMaxCumulativeContentItems();
                } else {
                    b("Using the provided override.");
                    maxCumulativeContentItems = jc.this.b.b.intValue();
                }
            }
            b("New limit " + maxCumulativeContentItems);
            jc.this.a.a(maxCumulativeContentItems);
        }

        public final void b(String str) {
            if (Log.isLoggable("ContentLimitListener", 3)) {
                Log.d("ContentLimitListener", str);
            }
        }
    }

    public jc(Context context, @XmlRes int i) {
        this.c = e2.a(context);
        if (c2.b == null) {
            try {
                XmlResourceParser xml = context.getResources().getXml(i);
                try {
                    AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
                    HashMap map = new HashMap();
                    xml.next();
                    xml.next();
                    xml.require(2, null, "Mapping");
                    while (xml.next() != 3) {
                        c2.b bVarA = d2.a(context, xml, attributeSetAsAttributeSet);
                        map.put(Integer.valueOf(bVarA.a), bVarA);
                    }
                    c2 c2Var = new c2(map);
                    xml.close();
                    c2.b = c2Var;
                } catch (Throwable th) {
                    if (xml != null) {
                        try {
                            xml.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (IOException | XmlPullParserException e) {
                throw new RuntimeException("Unable to parse CarUxRestrictionsAppConfig", e);
            }
        }
        this.d = c2.b;
    }
}
