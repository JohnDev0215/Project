package b.a.a.k;

import androidx.constraintlayout.motion.widget.Key;
import b.a.a.j.a;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: AwtCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements t0, b.a.a.j.j.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final i f369a = new i();

    public static boolean k(Class<?> cls) {
        return cls == Point.class || cls == Rectangle.class || cls == Font.class || cls == Color.class;
    }

    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        T t;
        b.a.a.j.b bVar = aVar.g;
        if (bVar.j() == 8) {
            bVar.F(16);
            return null;
        }
        if (bVar.j() != 12 && bVar.j() != 16) {
            throw new JSONException("syntax error");
        }
        bVar.u();
        if (type == Point.class) {
            t = (T) h(aVar, obj);
        } else if (type == Rectangle.class) {
            t = (T) i(aVar);
        } else if (type == Color.class) {
            t = (T) f(aVar);
        } else {
            if (type != Font.class) {
                throw new JSONException("not support awt class : " + type);
            }
            t = (T) g(aVar);
        }
        b.a.a.j.g gVarJ = aVar.j();
        aVar.X(t, obj);
        aVar.Y(gVarJ);
        return t;
    }

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        d1 d1Var = i0Var.k;
        if (obj == null) {
            d1Var.J();
            return;
        }
        if (obj instanceof Point) {
            Point point = (Point) obj;
            d1Var.x(l(d1Var, Point.class, '{'), "x", point.x);
            d1Var.x(',', "y", point.y);
        } else if (obj instanceof Font) {
            Font font = (Font) obj;
            d1Var.z(l(d1Var, Font.class, '{'), "name", font.getName());
            d1Var.x(',', "style", font.getStyle());
            d1Var.x(',', "size", font.getSize());
        } else if (obj instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) obj;
            d1Var.x(l(d1Var, Rectangle.class, '{'), "x", rectangle.x);
            d1Var.x(',', "y", rectangle.y);
            d1Var.x(',', "width", rectangle.width);
            d1Var.x(',', "height", rectangle.height);
        } else {
            if (!(obj instanceof Color)) {
                throw new JSONException("not support awt class : " + obj.getClass().getName());
            }
            Color color = (Color) obj;
            d1Var.x(l(d1Var, Color.class, '{'), "r", color.getRed());
            d1Var.x(',', "g", color.getGreen());
            d1Var.x(',', "b", color.getBlue());
            if (color.getAlpha() > 0) {
                d1Var.x(',', Key.ALPHA, color.getAlpha());
            }
        }
        d1Var.write(125);
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 12;
    }

    public Color f(b.a.a.j.a aVar) {
        b.a.a.j.b bVar = aVar.g;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (bVar.j() != 13) {
            if (bVar.j() != 4) {
                throw new JSONException("syntax error");
            }
            String strV = bVar.V();
            bVar.U(2);
            if (bVar.j() != 2) {
                throw new JSONException("syntax error");
            }
            int iZ = bVar.z();
            bVar.u();
            if (strV.equalsIgnoreCase("r")) {
                i = iZ;
            } else if (strV.equalsIgnoreCase("g")) {
                i2 = iZ;
            } else if (strV.equalsIgnoreCase("b")) {
                i3 = iZ;
            } else {
                if (!strV.equalsIgnoreCase(Key.ALPHA)) {
                    throw new JSONException("syntax error, " + strV);
                }
                i4 = iZ;
            }
            if (bVar.j() == 16) {
                bVar.F(4);
            }
        }
        bVar.u();
        return new Color(i, i2, i3, i4);
    }

    public Font g(b.a.a.j.a aVar) {
        b.a.a.j.b bVar = aVar.g;
        int iZ = 0;
        String strV = null;
        int iZ2 = 0;
        while (bVar.j() != 13) {
            if (bVar.j() != 4) {
                throw new JSONException("syntax error");
            }
            String strV2 = bVar.V();
            bVar.U(2);
            if (strV2.equalsIgnoreCase("name")) {
                if (bVar.j() != 4) {
                    throw new JSONException("syntax error");
                }
                strV = bVar.V();
                bVar.u();
            } else if (strV2.equalsIgnoreCase("style")) {
                if (bVar.j() != 2) {
                    throw new JSONException("syntax error");
                }
                iZ = bVar.z();
                bVar.u();
            } else {
                if (!strV2.equalsIgnoreCase("size")) {
                    throw new JSONException("syntax error, " + strV2);
                }
                if (bVar.j() != 2) {
                    throw new JSONException("syntax error");
                }
                iZ2 = bVar.z();
                bVar.u();
            }
            if (bVar.j() == 16) {
                bVar.F(4);
            }
        }
        bVar.u();
        return new Font(strV, iZ, iZ2);
    }

    public Point h(b.a.a.j.a aVar, Object obj) {
        int iG;
        b.a.a.j.b bVar = aVar.g;
        int i = 0;
        int i2 = 0;
        while (bVar.j() != 13) {
            if (bVar.j() != 4) {
                throw new JSONException("syntax error");
            }
            String strV = bVar.V();
            if (b.a.a.a.DEFAULT_TYPE_KEY.equals(strV)) {
                aVar.c("java.awt.Point");
            } else {
                if ("$ref".equals(strV)) {
                    return (Point) j(aVar, obj);
                }
                bVar.U(2);
                int iJ = bVar.j();
                if (iJ == 2) {
                    iG = bVar.z();
                    bVar.u();
                } else {
                    if (iJ != 3) {
                        throw new JSONException("syntax error : " + bVar.M());
                    }
                    iG = (int) bVar.g();
                    bVar.u();
                }
                if (strV.equalsIgnoreCase("x")) {
                    i = iG;
                } else {
                    if (!strV.equalsIgnoreCase("y")) {
                        throw new JSONException("syntax error, " + strV);
                    }
                    i2 = iG;
                }
                if (bVar.j() == 16) {
                    bVar.F(4);
                }
            }
        }
        bVar.u();
        return new Point(i, i2);
    }

    public Rectangle i(b.a.a.j.a aVar) {
        int iG;
        b.a.a.j.b bVar = aVar.g;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (bVar.j() != 13) {
            if (bVar.j() != 4) {
                throw new JSONException("syntax error");
            }
            String strV = bVar.V();
            bVar.U(2);
            int iJ = bVar.j();
            if (iJ == 2) {
                iG = bVar.z();
                bVar.u();
            } else {
                if (iJ != 3) {
                    throw new JSONException("syntax error");
                }
                iG = (int) bVar.g();
                bVar.u();
            }
            if (strV.equalsIgnoreCase("x")) {
                i = iG;
            } else if (strV.equalsIgnoreCase("y")) {
                i2 = iG;
            } else if (strV.equalsIgnoreCase("width")) {
                i3 = iG;
            } else {
                if (!strV.equalsIgnoreCase("height")) {
                    throw new JSONException("syntax error, " + strV);
                }
                i4 = iG;
            }
            if (bVar.j() == 16) {
                bVar.F(4);
            }
        }
        bVar.u();
        return new Rectangle(i, i2, i3, i4);
    }

    public final Object j(b.a.a.j.a aVar, Object obj) {
        b.a.a.j.b bVarT = aVar.t();
        bVarT.U(4);
        String strV = bVarT.V();
        aVar.X(aVar.j(), obj);
        aVar.f(new a.C0016a(aVar.j(), strV));
        aVar.U();
        aVar.b0(1);
        bVarT.F(13);
        aVar.a(13);
        return null;
    }

    public char l(d1 d1Var, Class<?> cls, char c2) {
        if (!d1Var.l(SerializerFeature.WriteClassName)) {
            return c2;
        }
        d1Var.write(123);
        d1Var.u(b.a.a.a.DEFAULT_TYPE_KEY);
        d1Var.N(cls.getName());
        return ',';
    }
}
