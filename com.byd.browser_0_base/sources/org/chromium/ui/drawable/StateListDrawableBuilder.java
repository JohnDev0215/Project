package org.chromium.ui.drawable;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.Drawable;
import androidx.appcompat.content.res.AppCompatResources;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class StateListDrawableBuilder {
    public final Context a;
    public final ArrayList b = new ArrayList();
    public final ArrayList c = new ArrayList();

    public static class a {
        public final int a;
        public final int[] b;
        public final int c;

        public a(int i, int[] iArr, int i2) {
            this.a = i;
            this.b = iArr;
            this.c = i2;
        }
    }

    public static class b {
        public final int a;
        public final int b;
        public final int c;

        public b(int i, int i2, int i3) {
            this.a = i;
            this.b = i2;
            this.c = i3;
        }
    }

    public StateListDrawableBuilder(Context context) {
        this.a = context;
    }

    public final a a(int i, int... iArr) {
        ArrayList arrayList = this.b;
        a aVar = new a(i, iArr, arrayList.size() + 1);
        arrayList.add(aVar);
        return aVar;
    }

    public final void b(a aVar, a aVar2, int i) {
        this.c.add(new b(i, aVar.c, aVar2.c));
    }

    public final AnimatedStateListDrawable c() {
        Context context;
        AnimatedStateListDrawable animatedStateListDrawable = new AnimatedStateListDrawable();
        ArrayList arrayList = this.b;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            context = this.a;
            if (i >= size) {
                break;
            }
            a aVar = (a) arrayList.get(i);
            animatedStateListDrawable.addState(aVar.b, AppCompatResources.a(context, aVar.a), aVar.c);
            i++;
        }
        ArrayList arrayList2 = this.c;
        int size2 = arrayList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            b bVar = (b) arrayList2.get(i2);
            animatedStateListDrawable.addTransition(bVar.b, bVar.c, (Drawable) ((Animatable) AppCompatResources.a(context, bVar.a)), false);
        }
        return animatedStateListDrawable;
    }
}
