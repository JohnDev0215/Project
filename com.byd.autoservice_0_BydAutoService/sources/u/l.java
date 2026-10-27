package u;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R$styleable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: compiled from: KeyTrigger.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f5155g = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f5156h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5157i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f5158j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f5159k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f5160l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f5161m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public View f5162n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f5163o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f5164p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f5165q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f5166r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f5167s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Method f5168t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Method f5169u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Method f5170v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f5171w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f5172x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public RectF f5173y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public RectF f5174z;

    /* JADX INFO: compiled from: KeyTrigger.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f5175a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f5175a = sparseIntArray;
            sparseIntArray.append(R$styleable.KeyTrigger_framePosition, 8);
            f5175a.append(R$styleable.KeyTrigger_onCross, 4);
            f5175a.append(R$styleable.KeyTrigger_onNegativeCross, 1);
            f5175a.append(R$styleable.KeyTrigger_onPositiveCross, 2);
            f5175a.append(R$styleable.KeyTrigger_motionTarget, 7);
            f5175a.append(R$styleable.KeyTrigger_triggerId, 6);
            f5175a.append(R$styleable.KeyTrigger_triggerSlack, 5);
            f5175a.append(R$styleable.KeyTrigger_motion_triggerOnCollision, 9);
            f5175a.append(R$styleable.KeyTrigger_motion_postLayoutCollision, 10);
            f5175a.append(R$styleable.KeyTrigger_triggerReceiver, 11);
        }

        public static void a(l lVar, TypedArray typedArray, Context context) {
            int indexCount = typedArray.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArray.getIndex(i3);
                switch (f5175a.get(index)) {
                    case 1:
                        lVar.f5158j = typedArray.getString(index);
                        continue;
                        break;
                    case 2:
                        lVar.f5159k = typedArray.getString(index);
                        continue;
                        break;
                    case 4:
                        lVar.f5156h = typedArray.getString(index);
                        continue;
                        break;
                    case 5:
                        lVar.f5163o = typedArray.getFloat(index, lVar.f5163o);
                        continue;
                        break;
                    case 6:
                        lVar.f5160l = typedArray.getResourceId(index, lVar.f5160l);
                        continue;
                        break;
                    case 7:
                        if (MotionLayout.f736y) {
                            int resourceId = typedArray.getResourceId(index, lVar.f5052c);
                            lVar.f5052c = resourceId;
                            if (resourceId == -1) {
                                lVar.f5053d = typedArray.getString(index);
                            } else {
                                continue;
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            lVar.f5053d = typedArray.getString(index);
                        } else {
                            lVar.f5052c = typedArray.getResourceId(index, lVar.f5052c);
                        }
                        break;
                    case 8:
                        int integer = typedArray.getInteger(index, lVar.f5051b);
                        lVar.f5051b = integer;
                        lVar.f5167s = (integer + 0.5f) / 100.0f;
                        continue;
                        break;
                    case 9:
                        lVar.f5161m = typedArray.getResourceId(index, lVar.f5161m);
                        continue;
                        break;
                    case 10:
                        lVar.f5172x = typedArray.getBoolean(index, lVar.f5172x);
                        continue;
                        break;
                    case 11:
                        lVar.f5157i = typedArray.getResourceId(index, lVar.f5157i);
                        break;
                }
                Log.e("KeyTrigger", "unused attribute 0x" + Integer.toHexString(index) + "   " + f5175a.get(index));
            }
        }
    }

    public l() {
        int i3 = c.f5050a;
        this.f5157i = i3;
        this.f5158j = null;
        this.f5159k = null;
        this.f5160l = i3;
        this.f5161m = i3;
        this.f5162n = null;
        this.f5163o = 0.1f;
        this.f5164p = true;
        this.f5165q = true;
        this.f5166r = true;
        this.f5167s = Float.NaN;
        this.f5172x = false;
        this.f5173y = new RectF();
        this.f5174z = new RectF();
        this.f5054e = 5;
        this.f5055f = new HashMap<>();
    }

    @Override // u.c
    public void a(HashMap<String, r> map) {
    }

    @Override // u.c
    public void b(HashSet<String> hashSet) {
    }

    @Override // u.c
    public void c(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, R$styleable.KeyTrigger), context);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void r(float r11, android.view.View r12) {
        /*
            Method dump skipped, instruction units count: 631
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: u.l.r(float, android.view.View):void");
    }

    public final void s(RectF rectF, View view, boolean z3) {
        rectF.top = view.getTop();
        rectF.bottom = view.getBottom();
        rectF.left = view.getLeft();
        rectF.right = view.getRight();
        if (z3) {
            view.getMatrix().mapRect(rectF);
        }
    }
}
