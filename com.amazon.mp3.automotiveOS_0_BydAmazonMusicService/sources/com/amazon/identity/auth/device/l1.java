package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.res.XmlResourceParser;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import java.util.List;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class l1 {
    public static final String f = "com.amazon.identity.auth.device.l1";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z5 f414a;
    public final k1 b;
    public final MAPApplicationInformationQueryer c;
    public final Object d;
    public List<j1> e;

    /* JADX INFO: compiled from: DCP */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f415a;
        public XmlResourceParser b;

        public a(String str, XmlResourceParser xmlResourceParser) {
            this.f415a = str;
            this.b = xmlResourceParser;
        }
    }

    public l1(Context context) {
        this(new k1(), new z5(context), MAPApplicationInformationQueryer.a(context));
    }

    public l1(k1 k1Var, z5 z5Var, MAPApplicationInformationQueryer mAPApplicationInformationQueryer) {
        this.d = new Object[0];
        this.f414a = z5Var;
        this.c = mAPApplicationInformationQueryer;
        this.b = k1Var;
    }
}
