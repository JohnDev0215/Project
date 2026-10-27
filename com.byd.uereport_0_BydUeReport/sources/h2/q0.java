package h2;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Node;

/* JADX INFO: compiled from: MiscCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class q0 implements t0, g2.t {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Method f5302c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final q0 f5301b = new q0();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f5303d = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f5300a = "true".equals(l2.e.k("fastjson.deserializer.fileRelativePathSupport"));

    public static String f(Node node) {
        try {
            Transformer transformerNewTransformer = TransformerFactory.newInstance().newTransformer();
            DOMSource dOMSource = new DOMSource(node);
            StringWriter stringWriter = new StringWriter();
            transformerNewTransformer.transform(dOMSource, new StreamResult(stringWriter));
            return stringWriter.toString();
        } catch (TransformerException e8) {
            throw new JSONException("xml node to string error", e8);
        }
    }

    /* JADX WARN: Type inference failed for: r10v41, types: [T, java.text.SimpleDateFormat] */
    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        Object objN;
        String str;
        f2.b bVar = aVar.f4861j;
        InetAddress inetAddress = null;
        int i8 = 0;
        if (type != InetSocketAddress.class) {
            if (aVar.f4866o == 2) {
                aVar.f4866o = 0;
                aVar.e(16);
                if (bVar.q() != 4) {
                    throw new JSONException("syntax error");
                }
                if (!"val".equals(bVar.n0())) {
                    throw new JSONException("syntax error");
                }
                bVar.E();
                aVar.e(17);
                objN = aVar.N();
                aVar.e(13);
            } else {
                objN = aVar.N();
            }
            if (objN == null) {
                str = null;
            } else {
                if (!(objN instanceof String)) {
                    if (!(objN instanceof JSONObject)) {
                        throw new JSONException("expect string");
                    }
                    JSONObject jSONObject = (JSONObject) objN;
                    if (type == Currency.class) {
                        String string = jSONObject.getString("currency");
                        if (string != null) {
                            return (T) Currency.getInstance(string);
                        }
                        String string2 = jSONObject.getString("currencyCode");
                        if (string2 != null) {
                            return (T) Currency.getInstance(string2);
                        }
                    }
                    return type == Map.Entry.class ? (T) jSONObject.entrySet().iterator().next() : (T) jSONObject.toJavaObject(type);
                }
                str = (String) objN;
            }
            if (str == null || str.length() == 0) {
                return null;
            }
            if (type == UUID.class) {
                return (T) UUID.fromString(str);
            }
            if (type == URI.class) {
                return (T) URI.create(str);
            }
            if (type == URL.class) {
                try {
                    return (T) new URL(str);
                } catch (MalformedURLException e8) {
                    throw new JSONException("create url error", e8);
                }
            }
            if (type == Pattern.class) {
                return (T) Pattern.compile(str);
            }
            if (type == Locale.class) {
                return (T) l2.l.M0(str);
            }
            if (type == SimpleDateFormat.class) {
                ?? r10 = (T) new SimpleDateFormat(str, bVar.h0());
                r10.setTimeZone(bVar.o0());
                return r10;
            }
            if (type == InetAddress.class || type == Inet4Address.class || type == Inet6Address.class) {
                try {
                    return (T) InetAddress.getByName(str);
                } catch (UnknownHostException e9) {
                    throw new JSONException("deserialize inet adress error", e9);
                }
            }
            if (type == File.class) {
                if (str.indexOf("..") < 0 || f5300a) {
                    return (T) new File(str);
                }
                throw new JSONException("file relative path not support.");
            }
            if (type == TimeZone.class) {
                return (T) TimeZone.getTimeZone(str);
            }
            if (type instanceof ParameterizedType) {
                type = ((ParameterizedType) type).getRawType();
            }
            if (type == Class.class) {
                return (T) l2.l.B0(str, aVar.o().m(), false);
            }
            if (type == Charset.class) {
                return (T) Charset.forName(str);
            }
            if (type == Currency.class) {
                return (T) Currency.getInstance(str);
            }
            if (type == JSONPath.class) {
                return (T) new JSONPath(str);
            }
            if (!(type instanceof Class)) {
                throw new JSONException("MiscCodec not support " + type.toString());
            }
            String name = ((Class) type).getName();
            if (name.equals("java.nio.file.Path")) {
                try {
                    if (f5302c == null && !f5303d) {
                        f5302c = l2.l.z0("java.nio.file.Paths").getMethod("get", String.class, String[].class);
                    }
                    Method method = f5302c;
                    if (method != null) {
                        return (T) method.invoke(null, str, new String[0]);
                    }
                    throw new JSONException("Path deserialize erorr");
                } catch (IllegalAccessException e10) {
                    throw new JSONException("Path deserialize erorr", e10);
                } catch (NoSuchMethodException unused) {
                    f5303d = true;
                } catch (InvocationTargetException e11) {
                    throw new JSONException("Path deserialize erorr", e11);
                }
            }
            throw new JSONException("MiscCodec not support " + name);
        }
        if (bVar.q() == 8) {
            bVar.E();
            return null;
        }
        aVar.e(12);
        while (true) {
            String strN0 = bVar.n0();
            bVar.V(17);
            if (strN0.equals("address")) {
                aVar.e(17);
                inetAddress = (InetAddress) aVar.d0(InetAddress.class);
            } else if (strN0.equals("port")) {
                aVar.e(17);
                if (bVar.q() != 2) {
                    throw new JSONException("port is not int");
                }
                int iN = bVar.N();
                bVar.E();
                i8 = iN;
            } else {
                aVar.e(17);
                aVar.N();
            }
            if (bVar.q() != 16) {
                aVar.e(13);
                return (T) new InetSocketAddress(inetAddress, i8);
            }
            bVar.E();
        }
    }

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        String strF;
        d1 d1Var = i0Var.f5241k;
        if (obj == null) {
            d1Var.Z();
            return;
        }
        Class<?> cls = obj.getClass();
        if (cls == SimpleDateFormat.class) {
            strF = ((SimpleDateFormat) obj).toPattern();
            if (d1Var.t(SerializerFeature.WriteClassName) && obj.getClass() != type) {
                d1Var.write(123);
                d1Var.E(c2.a.DEFAULT_TYPE_KEY);
                i0Var.I(obj.getClass().getName());
                d1Var.N(',', "val", strF);
                d1Var.write(125);
                return;
            }
        } else if (cls == Class.class) {
            strF = ((Class) obj).getName();
        } else {
            if (cls == InetSocketAddress.class) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) obj;
                InetAddress address = inetSocketAddress.getAddress();
                d1Var.write(123);
                if (address != null) {
                    d1Var.E("address");
                    i0Var.H(address);
                    d1Var.write(44);
                }
                d1Var.E("port");
                d1Var.V(inetSocketAddress.getPort());
                d1Var.write(125);
                return;
            }
            if (obj instanceof File) {
                strF = ((File) obj).getPath();
            } else if (obj instanceof InetAddress) {
                strF = ((InetAddress) obj).getHostAddress();
            } else if (obj instanceof TimeZone) {
                strF = ((TimeZone) obj).getID();
            } else if (obj instanceof Currency) {
                strF = ((Currency) obj).getCurrencyCode();
            } else {
                if (obj instanceof c2.e) {
                    ((c2.e) obj).writeJSONString(d1Var);
                    return;
                }
                if (obj instanceof Iterator) {
                    g(i0Var, d1Var, (Iterator) obj);
                    return;
                }
                if (obj instanceof Iterable) {
                    g(i0Var, d1Var, ((Iterable) obj).iterator());
                    return;
                }
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    if (key instanceof String) {
                        String str = (String) key;
                        if (value instanceof String) {
                            d1Var.P('{', str, (String) value);
                        } else {
                            d1Var.write(123);
                            d1Var.E(str);
                            i0Var.H(value);
                        }
                    } else {
                        d1Var.write(123);
                        i0Var.H(key);
                        d1Var.write(58);
                        i0Var.H(value);
                    }
                    d1Var.write(125);
                    return;
                }
                if (obj.getClass().getName().equals("net.sf.json.JSONNull")) {
                    d1Var.Z();
                    return;
                } else {
                    if (!(obj instanceof Node)) {
                        throw new JSONException("not support class : " + cls);
                    }
                    strF = f((Node) obj);
                }
            }
        }
        d1Var.d0(strF);
    }

    @Override // g2.t
    public int e() {
        return 4;
    }

    public void g(i0 i0Var, d1 d1Var, Iterator<?> it) {
        d1Var.write(91);
        int i8 = 0;
        while (it.hasNext()) {
            if (i8 != 0) {
                d1Var.write(44);
            }
            i0Var.H(it.next());
            i8++;
        }
        d1Var.write(93);
    }
}
