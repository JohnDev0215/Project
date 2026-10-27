package h2;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import f2.a;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: AwtCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements t0, g2.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final i f5239a = new i();

    public static boolean k(Class<?> cls) {
        return cls == Point.class || cls == Rectangle.class || cls == Font.class || cls == Color.class;
    }

    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        T t7;
        f2.b bVar = aVar.f4861j;
        if (bVar.q() == 8) {
            bVar.V(16);
            return null;
        }
        if (bVar.q() != 12 && bVar.q() != 16) {
            throw new JSONException("syntax error");
        }
        bVar.E();
        if (type == Point.class) {
            t7 = (T) h(aVar, obj);
        } else if (type == Rectangle.class) {
            t7 = (T) i(aVar);
        } else if (type == Color.class) {
            t7 = (T) f(aVar);
        } else {
            if (type != Font.class) {
                throw new JSONException("not support awt class : " + type);
            }
            t7 = (T) g(aVar);
        }
        f2.g gVarQ = aVar.q();
        aVar.q0(t7, obj);
        aVar.r0(gVarQ);
        return t7;
    }

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        d1 d1Var = i0Var.f5241k;
        if (obj == null) {
            d1Var.Z();
            return;
        }
        if (obj instanceof Point) {
            Point point = (Point) obj;
            d1Var.L(l(d1Var, Point.class, '{'), "x", point.x);
            d1Var.L(',', "y", point.y);
        } else if (obj instanceof Font) {
            Font font = (Font) obj;
            d1Var.N(l(d1Var, Font.class, '{'), "name", font.getName());
            d1Var.L(',', "style", font.getStyle());
            d1Var.L(',', "size", font.getSize());
        } else if (obj instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) obj;
            d1Var.L(l(d1Var, Rectangle.class, '{'), "x", rectangle.x);
            d1Var.L(',', "y", rectangle.y);
            d1Var.L(',', "width", rectangle.width);
            d1Var.L(',', "height", rectangle.height);
        } else {
            if (!(obj instanceof Color)) {
                throw new JSONException("not support awt class : " + obj.getClass().getName());
            }
            Color color = (Color) obj;
            d1Var.L(l(d1Var, Color.class, '{'), "r", color.getRed());
            d1Var.L(',', "g", color.getGreen());
            d1Var.L(',', "b", color.getBlue());
            if (color.getAlpha() > 0) {
                d1Var.L(',', "alpha", color.getAlpha());
            }
        }
        d1Var.write(125);
    }

    @Override // g2.t
    public int e() {
        return 12;
    }

    public Color f(f2.a aVar) {
        f2.b bVar = aVar.f4861j;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        while (bVar.q() != 13) {
            if (bVar.q() != 4) {
                throw new JSONException("syntax error");
            }
            String strN0 = bVar.n0();
            bVar.m0(2);
            if (bVar.q() != 2) {
                throw new JSONException("syntax error");
            }
            int iN = bVar.N();
            bVar.E();
            if (strN0.equalsIgnoreCase("r")) {
                i8 = iN;
            } else if (strN0.equalsIgnoreCase("g")) {
                i9 = iN;
            } else if (strN0.equalsIgnoreCase("b")) {
                i10 = iN;
            } else {
                if (!strN0.equalsIgnoreCase("alpha")) {
                    throw new JSONException("syntax error, " + strN0);
                }
                i11 = iN;
            }
            if (bVar.q() == 16) {
                bVar.V(4);
            }
        }
        bVar.E();
        return new Color(i8, i9, i10, i11);
    }

    public Font g(f2.a aVar) {
        f2.b bVar = aVar.f4861j;
        int iN = 0;
        String strN0 = null;
        int iN2 = 0;
        while (bVar.q() != 13) {
            if (bVar.q() != 4) {
                throw new JSONException("syntax error");
            }
            String strN02 = bVar.n0();
            bVar.m0(2);
            if (strN02.equalsIgnoreCase("name")) {
                if (bVar.q() != 4) {
                    throw new JSONException("syntax error");
                }
                strN0 = bVar.n0();
                bVar.E();
            } else if (strN02.equalsIgnoreCase("style")) {
                if (bVar.q() != 2) {
                    throw new JSONException("syntax error");
                }
                iN = bVar.N();
                bVar.E();
            } else {
                if (!strN02.equalsIgnoreCase("size")) {
                    throw new JSONException("syntax error, " + strN02);
                }
                if (bVar.q() != 2) {
                    throw new JSONException("syntax error");
                }
                iN2 = bVar.N();
                bVar.E();
            }
            if (bVar.q() == 16) {
                bVar.V(4);
            }
        }
        bVar.E();
        return new Font(strN0, iN, iN2);
    }

    public Point h(f2.a aVar, Object obj) {
        int iM;
        f2.b bVar = aVar.f4861j;
        int i8 = 0;
        int i9 = 0;
        while (bVar.q() != 13) {
            if (bVar.q() != 4) {
                throw new JSONException("syntax error");
            }
            String strN0 = bVar.n0();
            if (c2.a.DEFAULT_TYPE_KEY.equals(strN0)) {
                aVar.g("java.awt.Point");
            } else {
                if ("$ref".equals(strN0)) {
                    return (Point) j(aVar, obj);
                }
                bVar.m0(2);
                int iQ = bVar.q();
                if (iQ == 2) {
                    iM = bVar.N();
                    bVar.E();
                } else {
                    if (iQ != 3) {
                        throw new JSONException("syntax error : " + bVar.c0());
                    }
                    iM = (int) bVar.m();
                    bVar.E();
                }
                if (strN0.equalsIgnoreCase("x")) {
                    i8 = iM;
                } else {
                    if (!strN0.equalsIgnoreCase("y")) {
                        throw new JSONException("syntax error, " + strN0);
                    }
                    i9 = iM;
                }
                if (bVar.q() == 16) {
                    bVar.V(4);
                }
            }
        }
        bVar.E();
        return new Point(i8, i9);
    }

    public Rectangle i(f2.a aVar) {
        int iM;
        f2.b bVar = aVar.f4861j;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        while (bVar.q() != 13) {
            if (bVar.q() != 4) {
                throw new JSONException("syntax error");
            }
            String strN0 = bVar.n0();
            bVar.m0(2);
            int iQ = bVar.q();
            if (iQ == 2) {
                iM = bVar.N();
                bVar.E();
            } else {
                if (iQ != 3) {
                    throw new JSONException("syntax error");
                }
                iM = (int) bVar.m();
                bVar.E();
            }
            if (strN0.equalsIgnoreCase("x")) {
                i8 = iM;
            } else if (strN0.equalsIgnoreCase("y")) {
                i9 = iM;
            } else if (strN0.equalsIgnoreCase("width")) {
                i10 = iM;
            } else {
                if (!strN0.equalsIgnoreCase("height")) {
                    throw new JSONException("syntax error, " + strN0);
                }
                i11 = iM;
            }
            if (bVar.q() == 16) {
                bVar.V(4);
            }
        }
        bVar.E();
        return new Rectangle(i8, i9, i10, i11);
    }

    public final Object j(f2.a aVar, Object obj) {
        f2.b bVarC = aVar.C();
        bVarC.m0(4);
        String strN0 = bVarC.n0();
        aVar.q0(aVar.q(), obj);
        aVar.j(new a.C0039a(aVar.q(), strN0));
        aVar.m0();
        aVar.u0(1);
        bVarC.V(13);
        aVar.e(13);
        return null;
    }

    public char l(d1 d1Var, Class<?> cls, char c8) {
        if (!d1Var.t(SerializerFeature.WriteClassName)) {
            return c8;
        }
        d1Var.write(123);
        d1Var.E(c2.a.DEFAULT_TYPE_KEY);
        d1Var.d0(cls.getName());
        return ',';
    }
}
