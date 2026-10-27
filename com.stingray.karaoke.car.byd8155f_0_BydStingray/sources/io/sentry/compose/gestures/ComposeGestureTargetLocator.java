package io.sentry.compose.gestures;

import C0.N;
import E0.F;
import L0.j;
import L0.u;
import android.view.View;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.semantics.AppendedSemanticsElement;
import g0.o;
import io.sentry.C1489n1;
import io.sentry.K;
import io.sentry.internal.gestures.a;
import io.sentry.internal.gestures.b;
import io.sentry.internal.gestures.c;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import m0.d;

/* JADX INFO: loaded from: classes.dex */
public final class ComposeGestureTargetLocator implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final K f16503a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile C1489n1 f16504b;

    public ComposeGestureTargetLocator(K k10) {
        this.f16503a = k10;
        C1489n1.s().f("ComposeUserInteraction");
        C1489n1.s().g("maven:io.sentry:sentry-compose");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.sentry.internal.gestures.a
    public final c a(View view, float f10, float f11, b bVar) {
        String str;
        d dVarT;
        if (this.f16504b == null) {
            synchronized (this) {
                try {
                    if (this.f16504b == null) {
                        this.f16504b = new C1489n1(this.f16503a);
                    }
                } finally {
                }
            }
        }
        if (!(view instanceof Owner)) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        linkedList.add(((Owner) view).getRoot());
        String str2 = null;
        String str3 = null;
        while (true) {
            if (linkedList.isEmpty()) {
                str = str2;
                break;
            }
            F f12 = (F) linkedList.poll();
            if (f12 != null) {
                if (f12.W() && (dVarT = this.f16504b.t(f12)) != null && f10 >= dVarT.f18882a && f10 <= dVarT.c && f11 >= dVarT.f18883b && f11 <= dVarT.d) {
                    Iterator it = f12.E().iterator();
                    boolean z6 = false;
                    boolean z10 = false;
                    while (it.hasNext()) {
                        o oVar = ((N) it.next()).f863a;
                        if (oVar instanceof AppendedSemanticsElement) {
                            AppendedSemanticsElement appendedSemanticsElement = (AppendedSemanticsElement) oVar;
                            appendedSemanticsElement.getClass();
                            j<Map.Entry> jVar = new j();
                            jVar.f3909m = appendedSemanticsElement.f11692a;
                            appendedSemanticsElement.f11693b.b(jVar);
                            for (Map.Entry entry : jVar) {
                                String str4 = ((u) entry.getKey()).f3965a;
                                if ("ScrollBy".equals(str4)) {
                                    z10 = true;
                                } else if ("OnClick".equals(str4)) {
                                    z6 = true;
                                } else if ("SentryTag".equals(str4) || "TestTag".equals(str4)) {
                                    if (entry.getValue() instanceof String) {
                                        str3 = (String) entry.getValue();
                                    }
                                }
                            }
                        } else {
                            String canonicalName = oVar.getClass().getCanonicalName();
                            if ("androidx.compose.foundation.ClickableElement".equals(canonicalName) || "androidx.compose.foundation.CombinedClickableElement".equals(canonicalName)) {
                                z6 = true;
                            } else if ("androidx.compose.foundation.ScrollingLayoutElement".equals(canonicalName)) {
                                z10 = true;
                            }
                        }
                    }
                    if (z6 && bVar == b.CLICKABLE) {
                        str2 = str3;
                    }
                    if (z10 && bVar == b.SCROLLABLE) {
                        str = str3;
                        break;
                    }
                }
                linkedList.addAll(f12.K().f());
            }
        }
        if (str == null) {
            return null;
        }
        return new c(null, null, null, str, "jetpack_compose");
    }
}
