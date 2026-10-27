package com.visualon.OSMPSubTitle;

import android.graphics.Color;
import android.graphics.Rect;
import com.visualon.OSMPSubTitle.DataObject.ImageStore;
import com.visualon.OSMPSubTitle.DataObject.JSImage;
import com.visualon.OSMPSubTitle.DataObject.JSImageDescription;
import com.visualon.OSMPSubTitle.DataObject.JSRect;
import com.visualon.OSMPSubTitle.DataObject.JSSegment;
import com.visualon.OSMPSubTitle.DataObject.JSWindow;
import com.visualon.OSMPSubTitle.DataObject.VORect;
import com.visualon.OSMPSubTitle.DataObject.VOSubtitleImageInfo;
import com.visualon.OSMPSubTitle.DataObject.VOSubtitleImageInfoData;
import com.visualon.OSMPSubTitle.DataObject.VOSubtitleImageInfoDescriptor;
import com.visualon.OSMPSubTitle.DataObject.VOSubtitleInfo;
import com.visualon.OSMPSubTitle.DataObject.VOSubtitleInfoEntry;
import com.visualon.OSMPUtils.voLog;
import com.visualon.OSMPUtils.voOSType;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
public class VOSubTitleJsonParser {
    static final int EDGE_TYPE_DEPRESSED = 2;
    static final int EDGE_TYPE_LEFT_DROP_SHADOW = 4;
    static final int EDGE_TYPE_NONE = 0;
    static final int EDGE_TYPE_RAISED = 1;
    static final int EDGE_TYPE_RIGHT_DROP_SHADOW = 5;
    static final int EDGE_TYPE_UNIFORM = 3;
    private static final String TAG = "VOSubTitleJsonParser";
    static Pattern pNumber = Pattern.compile("[^0-9-.]");
    private static Pattern unicodePattern = Pattern.compile("\\\\u");
    private static Pattern printablePattern = Pattern.compile("\\P{Print}");
    public HashMap<String, ImageStore> mImageStores = new HashMap<>();
    private Pattern removeBlanksPatternStart = null;
    private Pattern removeBlanksPatternEnd = null;

    public static int extractValue(String str, String str2) {
        float f;
        String strReplaceAll = pNumber.matcher(str).replaceAll("");
        try {
            f = Float.parseFloat(strReplaceAll);
        } catch (NumberFormatException unused) {
            if (voLog.enablePrintLog()) {
                voLog.d(TAG, "extractValue encounter something shouldn't happen. Executing workaround.", new Object[0]);
            }
            if (strReplaceAll.indexOf("px", strReplaceAll.length() - 2) != -1 || strReplaceAll.indexOf("em", strReplaceAll.length() - 2) != -1) {
                f = Float.parseFloat(strReplaceAll.substring(0, strReplaceAll.length() - 2));
            } else if (strReplaceAll.indexOf("%", strReplaceAll.length() - 1) != -1) {
                f = Float.parseFloat(strReplaceAll.substring(0, strReplaceAll.length() - 1));
            } else {
                f = strReplaceAll.indexOf("cell", strReplaceAll.length() + (-4)) != -1 ? Float.parseFloat(strReplaceAll.substring(0, strReplaceAll.length() - 4)) : 0.0f;
            }
        }
        return (int) f;
    }

    private boolean isUnitPercentage(String str) {
        return str.indexOf("%") != -1;
    }

    private JSRect getRectData(JSONObject jSONObject) {
        JSRect jSRect = new JSRect();
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("rect");
            jSRect.top = extractValue(jSONObject2.getString("top"), "top");
            jSRect.left = extractValue(jSONObject2.getString("left"), "left");
            jSRect.right = extractValue(jSONObject2.getString("right"), "right");
            jSRect.bottom = extractValue(jSONObject2.getString("bottom"), "bottom");
        } catch (JSONException e) {
            if (voLog.enablePrintLog()) {
                voLog.w(TAG, "There is something wrong: " + e.toString(), new Object[0]);
            }
        }
        return jSRect;
    }

    private JSWindow getWindowData(JSONObject jSONObject) {
        JSWindow jSWindow = new JSWindow();
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("window");
            jSWindow.jsRect = getRectData(jSONObject2);
            jSWindow.setAlignment(JSWindow.ALIGNMENT.HORIZONTAL, jSONObject2.getString("ha"));
            jSWindow.setAlignment(JSWindow.ALIGNMENT.VERTICAL, jSONObject2.getString("va"));
            jSWindow.opacity = jSONObject2.getDouble("opacity");
            jSWindow.color = jSONObject2.getString("color");
            JSONObject jSONObject3 = jSONObject2.getJSONObject("edge");
            jSWindow.edgeOpacity = jSONObject3.getDouble("opacity");
            jSWindow.edgeColor = jSONObject3.getString("color");
            jSWindow.edgeStyle = jSONObject3.getString("style");
        } catch (JSONException e) {
            if (voLog.enablePrintLog()) {
                voLog.w(TAG, "There is something wrong: " + e.toString(), new Object[0]);
            }
        }
        return jSWindow;
    }

    private JSImageDescription getImageDescription(JSONObject jSONObject) {
        JSImageDescription jSImageDescription = new JSImageDescription();
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("img-descriptor");
            jSImageDescription.img_id = jSONObject2.getString("img-id");
            jSImageDescription.scaling = jSONObject2.getString("scaling");
            jSImageDescription.scaleFactor = jSONObject2.getDouble("scaleFactor");
        } catch (JSONException e) {
            if (voLog.enablePrintLog()) {
                voLog.w(TAG, "Error in getImageDescription:" + e.toString(), new Object[0]);
            }
        }
        return jSImageDescription;
    }

    private JSSegment getSegment(JSONObject jSONObject) {
        JSSegment jSSegment;
        JSSegment jSSegment2 = null;
        try {
            jSSegment = new JSSegment();
        } catch (JSONException e) {
            e = e;
        }
        try {
            jSSegment.text = jSONObject.getString("text");
            JSONObject jSONObject2 = jSONObject.getJSONObject("format");
            jSSegment.isBold = jSONObject2.getBoolean("bold");
            jSSegment.isItalic = jSONObject2.getBoolean("italic");
            jSSegment.hasTextUnderline = jSONObject2.getBoolean("underline");
            jSSegment.opacity = jSONObject2.getDouble("opacity");
            jSSegment.color = jSONObject2.getString("color");
            String string = jSONObject2.getString("size");
            jSSegment.fontSizeStr = string;
            if (isUnitPercentage(string)) {
                jSSegment.fontSize = extractValue(string, "fontSize") / 100.0f;
            } else {
                jSSegment.fontSize = extractValue(string, "fontSize");
            }
            jSSegment.fontFamily = jSONObject2.getString("font");
            JSONObject jSONObject3 = jSONObject2.getJSONObject("edge");
            jSSegment.edgeOpacity = jSONObject3.getDouble("opacity");
            jSSegment.edgeColor = jSONObject3.getString("color");
            jSSegment.edgeStyle = jSONObject3.getString("style");
            return jSSegment;
        } catch (JSONException e2) {
            e = e2;
            jSSegment2 = jSSegment;
            if (voLog.enablePrintLog()) {
                voLog.w(TAG, "There is something wrong: " + e.toString(), new Object[0]);
            }
            return jSSegment2;
        }
    }

    public static String addAlpha(String str, double d) {
        String hexString = Long.toHexString(Math.round(d * 255.0d));
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        return str.replace("#", "#" + hexString);
    }

    private int mappingEdgeType(String str) {
        if (str.equalsIgnoreCase("raised")) {
            return 1;
        }
        if (str.equalsIgnoreCase("depressed")) {
            return 2;
        }
        if (str.equalsIgnoreCase("uniform")) {
            return 3;
        }
        if (str.equalsIgnoreCase("left-shadowed")) {
            return 4;
        }
        return str.equalsIgnoreCase("right-shadowed") ? 5 : 0;
    }

    public static byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i2 >= length) {
                return bArr;
            }
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i2), 16));
            i += 2;
        }
    }

    public static String uencodeStr(String str) throws UnsupportedEncodingException {
        String strReplaceAll = unicodePattern.matcher(str).replaceAll("");
        if (!strReplaceAll.matches("-?[0-9a-fA-F]+") || !str.contains("\\u")) {
            return str;
        }
        return printablePattern.matcher(new String(hexStringToByteArray(strReplaceAll), "utf-16")).replaceAll("");
    }

    public static int mappingFontFamily(String str) {
        if (str.equalsIgnoreCase("default")) {
            return 0;
        }
        if (str.equalsIgnoreCase("courier")) {
            return 14;
        }
        if (str.equalsIgnoreCase("monospace-serif")) {
            return 1;
        }
        if (str.equalsIgnoreCase("times new roman")) {
            return 13;
        }
        if (str.equalsIgnoreCase("helvetica")) {
            return 15;
        }
        if (str.equalsIgnoreCase("monospace")) {
            return 8;
        }
        if (str.equalsIgnoreCase("arial")) {
            return 16;
        }
        if (str.equalsIgnoreCase("casual")) {
            return 5;
        }
        if (str.equalsIgnoreCase("cursive")) {
            return 6;
        }
        if (str.equalsIgnoreCase("sans-serif-smallcaps")) {
            return 7;
        }
        if (str.equalsIgnoreCase("Yu Gothic")) {
            return 19;
        }
        if (str.equalsIgnoreCase("sans serif")) {
            return 9;
        }
        return str.equalsIgnoreCase("serif") ? 10 : -1;
    }

    private Rect getBoundingBoxData(JSONObject jSONObject) {
        JSONObject jSONObject2;
        Rect rect;
        Rect rect2 = null;
        try {
            jSONObject2 = jSONObject.getJSONObject("boundingbox");
            rect = new Rect();
        } catch (JSONException unused) {
        }
        try {
            rect.top = jSONObject2.getInt("top");
            rect.bottom = jSONObject2.getInt("bottom");
            rect.left = jSONObject2.getInt("left");
            rect.right = jSONObject2.getInt("right");
            return rect;
        } catch (JSONException unused2) {
            rect2 = rect;
            if (voLog.enablePrintLog()) {
                voLog.i(TAG, "There is no boundingbox Data", new Object[0]);
            }
            return rect2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x02d1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.visualon.OSMPSubTitle.DataObject.VOSubtitleInfo parseObj2SubtitleInfo(org.json.JSONObject r20) {
        /*
            Method dump skipped, instruction units count: 748
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.visualon.OSMPSubTitle.VOSubTitleJsonParser.parseObj2SubtitleInfo(org.json.JSONObject):com.visualon.OSMPSubTitle.DataObject.VOSubtitleInfo");
    }

    public VOSubtitleInfo parseObj2SubtitleInfo(String str) {
        boolean z;
        VOSubtitleInfo obj2SubtitleInfo;
        if (voLog.enablePrintLog()) {
            voLog.d(TAG, "var parseObj2SubtitleInfo = " + str, new Object[0]);
        }
        if (str.indexOf("#ID3") == str.length() - 4) {
            str = str.substring(0, str.length() - 4);
            z = true;
            if (voLog.enablePrintLog()) {
                voLog.i(TAG, "2var parseObj2SubtitleInfo = " + str, new Object[0]);
            }
        } else {
            z = false;
        }
        try {
            obj2SubtitleInfo = parseObj2SubtitleInfo(new JSONObject(str));
            obj2SubtitleInfo.jsonString = str;
        } catch (JSONException e) {
            obj2SubtitleInfo = null;
            if (voLog.enablePrintLog()) {
                voLog.w(TAG, "There is something wrong: " + e.toString(), new Object[0]);
            }
        }
        if (obj2SubtitleInfo != null) {
            obj2SubtitleInfo.isForID3 = z;
        }
        return obj2SubtitleInfo;
    }

    public void setSubtitleTrim(String str) {
        if (str.length() != 0) {
            this.removeBlanksPatternStart = Pattern.compile("[" + str + "]+$");
            this.removeBlanksPatternEnd = Pattern.compile("^[" + str + "]+");
            return;
        }
        this.removeBlanksPatternStart = null;
        this.removeBlanksPatternEnd = null;
    }

    private void retrieveImageData(VOSubtitleInfoEntry vOSubtitleInfoEntry, JSONObject jSONObject) {
        vOSubtitleInfoEntry.subtitleDispInfo.imageInfo.clear();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("images");
            for (int i = 0; i < jSONArray.length(); i++) {
                VOSubtitleImageInfo vOSubtitleImageInfo = new VOSubtitleImageInfo();
                JSImage jSImage = new JSImage();
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                jSImage.jsWindow = getWindowData(jSONObject2);
                jSImage.jsImgDesc = getImageDescription(jSONObject2);
                VOSubtitleImageInfoDescriptor vOSubtitleImageInfoDescriptor = new VOSubtitleImageInfoDescriptor();
                vOSubtitleImageInfo.imageInfoDescriptor = vOSubtitleImageInfoDescriptor;
                JSImageDescription jSImageDescription = jSImage.jsImgDesc;
                vOSubtitleImageInfoDescriptor.img_id = jSImageDescription.img_id;
                vOSubtitleImageInfoDescriptor.scaling = jSImageDescription.scaling;
                vOSubtitleImageInfoDescriptor.scaleFactor = jSImageDescription.scaleFactor;
                VORect vORect = vOSubtitleImageInfoDescriptor.imageRectInfo;
                Rect rect = vORect.rect;
                JSWindow jSWindow = jSImage.jsWindow;
                JSRect jSRect = jSWindow.jsRect;
                rect.top = jSRect.top;
                rect.bottom = jSRect.bottom;
                rect.right = jSRect.right;
                rect.left = jSRect.left;
                jSWindow.opacity = 0.0d;
                vORect.color = Color.parseColor(addAlpha(jSWindow.color, 0.0d));
                vOSubtitleImageInfo.imageInfoDescriptor.imageRectInfo.edgeType = mappingEdgeType(jSImage.jsWindow.edgeStyle);
                VORect vORect2 = vOSubtitleImageInfo.imageInfoDescriptor.imageRectInfo;
                JSWindow jSWindow2 = jSImage.jsWindow;
                vORect2.edgeColor = Color.parseColor(addAlpha(jSWindow2.edgeColor, jSWindow2.edgeOpacity));
                ImageStore imageStore = this.mImageStores.get(vOSubtitleImageInfo.imageInfoDescriptor.img_id);
                VOSubtitleImageInfoData vOSubtitleImageInfoData = new VOSubtitleImageInfoData();
                vOSubtitleImageInfo.imageData = vOSubtitleImageInfoData;
                vOSubtitleImageInfoData.width = imageStore.width;
                vOSubtitleImageInfoData.height = imageStore.height;
                vOSubtitleImageInfoData.size = imageStore.size;
                vOSubtitleImageInfoData.picData = imageStore.data;
                vOSubtitleImageInfoData.imageType = voOSType.VOOSMP_IMAGE_TYPE.valueOf(imageStore.type);
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "ImageType = " + vOSubtitleImageInfo.imageData.imageType, new Object[0]);
                }
                vOSubtitleInfoEntry.subtitleDispInfo.imageInfo.add(vOSubtitleImageInfo);
            }
        } catch (JSONException unused) {
            if (voLog.enablePrintLog()) {
                voLog.i(TAG, "There is no image info. for subtitle", new Object[0]);
            }
        }
    }

    private JSONArray getLineArray(JSONObject jSONObject) {
        try {
            return jSONObject.getJSONArray("lines");
        } catch (JSONException e) {
            if (voLog.enablePrintLog()) {
                voLog.w(TAG, "There is something wrong: " + e.toString(), new Object[0]);
            }
            return null;
        }
    }
}
