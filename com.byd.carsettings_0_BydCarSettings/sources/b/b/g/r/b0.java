package b.b.g.r;

import com.byd.carsettings.model.XmlItem;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: compiled from: XmlParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static XmlPullParserFactory f1790a;

    public static void a(XmlPullParser xmlPullParser, XmlItem xmlItem) {
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            xmlItem.put(xmlPullParser.getAttributeName(i), xmlPullParser.getAttributeValue(i));
        }
    }

    public static List<XmlItem> b(XmlPullParser xmlPullParser, String str, String str2, String str3, String str4) {
        ArrayList arrayList = null;
        try {
            ArrayList arrayList2 = null;
            XmlItem xmlItem = null;
            int eventType = xmlPullParser.getEventType();
            boolean z = false;
            ArrayList arrayList3 = null;
            while (eventType != 1) {
                String name = xmlPullParser.getName();
                if (eventType == 3 && name.equals(str)) {
                    break;
                }
                if (eventType != 2) {
                    eventType = xmlPullParser.next();
                } else {
                    if (name.equals(str)) {
                        z = true;
                        arrayList = new ArrayList();
                    }
                    if (z) {
                        if (name.equals(str2) && arrayList != null) {
                            XmlItem xmlItem2 = new XmlItem();
                            a(xmlPullParser, xmlItem2);
                            ArrayList arrayList4 = new ArrayList();
                            xmlItem2.setList(arrayList4);
                            arrayList.add(xmlItem2);
                            arrayList3 = arrayList4;
                        }
                        if (name.equals(str3) && arrayList3 != null) {
                            XmlItem xmlItem3 = new XmlItem();
                            a(xmlPullParser, xmlItem3);
                            ArrayList arrayList5 = new ArrayList();
                            xmlItem3.setList(arrayList5);
                            arrayList3.add(xmlItem3);
                            arrayList2 = arrayList5;
                        }
                        int next = xmlPullParser.next();
                        if (next == 4) {
                            if (name.equals(str4) && arrayList2 != null) {
                                xmlItem = new XmlItem();
                                arrayList2.add(xmlItem);
                            }
                            String text = xmlPullParser.getText();
                            if (text != null && !text.trim().isEmpty()) {
                                xmlItem.put(name, text);
                            }
                        }
                        eventType = next;
                    } else {
                        eventType = xmlPullParser.next();
                    }
                }
            }
            return arrayList;
        } catch (IOException e2) {
            b.b.m.c.a.c("XmlParser", "--doParse--e  = " + e2.toString());
            return arrayList;
        } catch (ArrayIndexOutOfBoundsException e3) {
            b.b.m.c.a.c("XmlParser", "--doParse--e  = " + e3.toString());
            return arrayList;
        } catch (XmlPullParserException e4) {
            b.b.m.c.a.c("XmlParser", "---doParse--e  = " + e4.toString());
            return arrayList;
        }
    }

    public static String c(XmlPullParser xmlPullParser, String str) {
        if (str != null && !str.isEmpty() && xmlPullParser != null) {
            try {
                int eventType = xmlPullParser.getEventType();
                while (eventType != 1) {
                    String name = xmlPullParser.getName();
                    if (eventType == 3 && name.equals(str)) {
                        break;
                    }
                    if (eventType == 2 && name != null && name.equals(str)) {
                        eventType = xmlPullParser.next();
                        if (eventType == 4) {
                            return xmlPullParser.getText();
                        }
                    } else {
                        eventType = xmlPullParser.next();
                    }
                }
            } catch (IOException e2) {
                b.b.m.c.a.c("XmlParser", "--grtFileVersion--e  = " + e2.toString());
            } catch (ArrayIndexOutOfBoundsException e3) {
                b.b.m.c.a.c("XmlParser", "--grtFileVersion--e  = " + e3.toString());
            } catch (XmlPullParserException e4) {
                b.b.m.c.a.c("XmlParser", "---grtFileVersion--e  = " + e4.toString());
            }
        }
        return "";
    }

    public static List<XmlItem> d(InputStream inputStream, String str, String str2, String str3, String str4) {
        if (inputStream == null) {
            return null;
        }
        try {
            if (f1790a == null) {
                f1790a = XmlPullParserFactory.newInstance();
            }
            XmlPullParser xmlPullParserNewPullParser = f1790a.newPullParser();
            xmlPullParserNewPullParser.setInput(new InputStreamReader(inputStream));
            return b(xmlPullParserNewPullParser, str, str2, str3, str4);
        } catch (XmlPullParserException e2) {
            b.b.m.c.a.c("XmlParser", "--parse--e  = " + e2.toString());
            return null;
        }
    }

    public static String e(InputStream inputStream, String str) {
        b.b.m.c.a.a("XmlParser", "--parseFileVersion--is  = " + inputStream + ", versionLabel = " + str);
        if (inputStream == null) {
            return null;
        }
        try {
            if (f1790a == null) {
                f1790a = XmlPullParserFactory.newInstance();
            }
            XmlPullParser xmlPullParserNewPullParser = f1790a.newPullParser();
            xmlPullParserNewPullParser.setInput(new InputStreamReader(inputStream));
            return c(xmlPullParserNewPullParser, str);
        } catch (XmlPullParserException e2) {
            b.b.m.c.a.c("XmlParser", "--parseFileVersion--e  = " + e2.toString());
            return null;
        }
    }
}
