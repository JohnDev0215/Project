package b.a.a.k;

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
public class q0 implements t0, b.a.a.j.j.t {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Method f399c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final q0 f398b = new q0();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f400d = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f397a = "true".equals(b.a.a.n.e.k("fastjson.deserializer.fileRelativePathSupport"));

    public static String f(Node node) {
        try {
            Transformer transformerNewTransformer = TransformerFactory.newInstance().newTransformer();
            DOMSource dOMSource = new DOMSource(node);
            StringWriter stringWriter = new StringWriter();
            transformerNewTransformer.transform(dOMSource, new StreamResult(stringWriter));
            return stringWriter.toString();
        } catch (TransformerException e2) {
            throw new JSONException("xml node to string error", e2);
        }
    }

    /* JADX WARN: Type inference failed for: r10v41, types: [T, java.text.SimpleDateFormat] */
    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        Object objZ;
        String str;
        b.a.a.j.b bVar = aVar.g;
        InetAddress inetAddress = null;
        int i = 0;
        if (type != InetSocketAddress.class) {
            if (aVar.l == 2) {
                aVar.l = 0;
                aVar.a(16);
                if (bVar.j() != 4) {
                    throw new JSONException("syntax error");
                }
                if (!"val".equals(bVar.V())) {
                    throw new JSONException("syntax error");
                }
                bVar.u();
                aVar.a(17);
                objZ = aVar.z();
                aVar.a(13);
            } else {
                objZ = aVar.z();
            }
            if (objZ == null) {
                str = null;
            } else {
                if (!(objZ instanceof String)) {
                    if (!(objZ instanceof JSONObject)) {
                        throw new JSONException("expect string");
                    }
                    JSONObject jSONObject = (JSONObject) objZ;
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
                str = (String) objZ;
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
                } catch (MalformedURLException e2) {
                    throw new JSONException("create url error", e2);
                }
            }
            if (type == Pattern.class) {
                return (T) Pattern.compile(str);
            }
            if (type == Locale.class) {
                return (T) b.a.a.n.l.M0(str);
            }
            if (type == SimpleDateFormat.class) {
                ?? r10 = (T) new SimpleDateFormat(str, bVar.R());
                r10.setTimeZone(bVar.W());
                return r10;
            }
            if (type == InetAddress.class || type == Inet4Address.class || type == Inet6Address.class) {
                try {
                    return (T) InetAddress.getByName(str);
                } catch (UnknownHostException e3) {
                    throw new JSONException("deserialize inet adress error", e3);
                }
            }
            if (type == File.class) {
                if (str.indexOf("..") < 0 || f397a) {
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
                return (T) b.a.a.n.l.B0(str, aVar.i().m(), false);
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
                    if (f399c == null && !f400d) {
                        f399c = b.a.a.n.l.z0("java.nio.file.Paths").getMethod("get", String.class, String[].class);
                    }
                    Method method = f399c;
                    if (method != null) {
                        return (T) method.invoke(null, str, new String[0]);
                    }
                    throw new JSONException("Path deserialize erorr");
                } catch (IllegalAccessException e4) {
                    throw new JSONException("Path deserialize erorr", e4);
                } catch (NoSuchMethodException unused) {
                    f400d = true;
                } catch (InvocationTargetException e5) {
                    throw new JSONException("Path deserialize erorr", e5);
                }
            }
            throw new JSONException("MiscCodec not support " + name);
        }
        if (bVar.j() == 8) {
            bVar.u();
            return null;
        }
        aVar.a(12);
        while (true) {
            String strV = bVar.V();
            bVar.F(17);
            if (strV.equals("address")) {
                aVar.a(17);
                inetAddress = (InetAddress) aVar.N(InetAddress.class);
            } else if (strV.equals("port")) {
                aVar.a(17);
                if (bVar.j() != 2) {
                    throw new JSONException("port is not int");
                }
                int iZ = bVar.z();
                bVar.u();
                i = iZ;
            } else {
                aVar.a(17);
                aVar.z();
            }
            if (bVar.j() != 16) {
                aVar.a(13);
                return (T) new InetSocketAddress(inetAddress, i);
            }
            bVar.u();
        }
    }

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        String strF;
        d1 d1Var = i0Var.k;
        if (obj == null) {
            d1Var.J();
            return;
        }
        Class<?> cls = obj.getClass();
        if (cls == SimpleDateFormat.class) {
            strF = ((SimpleDateFormat) obj).toPattern();
            if (d1Var.l(SerializerFeature.WriteClassName) && obj.getClass() != type) {
                d1Var.write(123);
                d1Var.u(b.a.a.a.DEFAULT_TYPE_KEY);
                i0Var.I(obj.getClass().getName());
                d1Var.z(',', "val", strF);
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
                    d1Var.u("address");
                    i0Var.H(address);
                    d1Var.write(44);
                }
                d1Var.u("port");
                d1Var.F(inetSocketAddress.getPort());
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
                if (obj instanceof b.a.a.e) {
                    ((b.a.a.e) obj).writeJSONString(d1Var);
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
                            d1Var.B('{', str, (String) value);
                        } else {
                            d1Var.write(123);
                            d1Var.u(str);
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
                    d1Var.J();
                    return;
                } else {
                    if (!(obj instanceof Node)) {
                        throw new JSONException("not support class : " + cls);
                    }
                    strF = f((Node) obj);
                }
            }
        }
        d1Var.N(strF);
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 4;
    }

    public void g(i0 i0Var, d1 d1Var, Iterator<?> it) {
        d1Var.write(91);
        int i = 0;
        while (it.hasNext()) {
            if (i != 0) {
                d1Var.write(44);
            }
            i0Var.H(it.next());
            i++;
        }
        d1Var.write(93);
    }
}
