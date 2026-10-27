package com.android.bluetooth.avrcpcontroller;

import android.util.Log;
import com.android.bluetooth.mapapi.BluetoothMapContract;
import com.android.internal.util.FastXmlSerializer;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes.dex */
public class BipImageProperties {
    private static final String TAG = "avrcpcontroller.BipImageProperties";
    private static final String sVersion = "1.0";
    private ArrayList<BipAttachmentFormat> mAttachments;
    private String mFriendlyName;
    private boolean mHasThumbnailFormat;
    private String mImageHandle;
    private ArrayList<BipImageFormat> mNativeFormats;
    private ArrayList<BipImageFormat> mVariantFormats;
    private String mVersion;

    public static class Builder {
        private BipImageProperties mProperties = new BipImageProperties();

        public Builder setImageHandle(String handle) {
            this.mProperties.mImageHandle = handle;
            return this;
        }

        public Builder setFriendlyName(String friendlyName) {
            this.mProperties.mFriendlyName = friendlyName;
            return this;
        }

        public Builder addNativeFormat(BipImageFormat format) {
            this.mProperties.addNativeFormat(format);
            return this;
        }

        public Builder addVariantFormat(BipImageFormat format) {
            this.mProperties.addVariantFormat(format);
            return this;
        }

        public Builder addAttachment(BipAttachmentFormat format) {
            this.mProperties.addAttachment(format);
            return this;
        }

        public BipImageProperties build() {
            return this.mProperties;
        }
    }

    private BipImageProperties() {
        this.mImageHandle = null;
        this.mVersion = null;
        this.mFriendlyName = null;
        this.mHasThumbnailFormat = false;
        this.mVersion = sVersion;
        this.mNativeFormats = new ArrayList<>();
        this.mVariantFormats = new ArrayList<>();
        this.mAttachments = new ArrayList<>();
    }

    public BipImageProperties(InputStream inputStream) throws XmlPullParserException, IOException {
        this.mImageHandle = null;
        this.mVersion = null;
        this.mFriendlyName = null;
        this.mHasThumbnailFormat = false;
        this.mNativeFormats = new ArrayList<>();
        this.mVariantFormats = new ArrayList<>();
        this.mAttachments = new ArrayList<>();
        parse(inputStream);
    }

    private void parse(InputStream inputStream) throws XmlPullParserException, IOException {
        try {
            XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
            xpp.setInput(inputStream, "utf-8");
            for (int event = xpp.getEventType(); event != 1; event = xpp.next()) {
                if (event == 2) {
                    String tag = xpp.getName();
                    if (tag.equals("image-properties")) {
                        this.mVersion = xpp.getAttributeValue(null, "version");
                        this.mImageHandle = xpp.getAttributeValue(null, "handle");
                        this.mFriendlyName = xpp.getAttributeValue(null, "friendly-name");
                    } else if (tag.equals(AvrcpCoverArtManager.SCHEME_NATIVE)) {
                        String encoding = xpp.getAttributeValue(null, "encoding");
                        String pixel = xpp.getAttributeValue(null, "pixel");
                        String size = xpp.getAttributeValue(null, "size");
                        addNativeFormat(BipImageFormat.parseNative(encoding, pixel, size));
                    } else if (tag.equals("variant")) {
                        String encoding2 = xpp.getAttributeValue(null, "encoding");
                        String pixel2 = xpp.getAttributeValue(null, "pixel");
                        String maxSize = xpp.getAttributeValue(null, "maxsize");
                        String trans = xpp.getAttributeValue(null, "transformation");
                        addVariantFormat(BipImageFormat.parseVariant(encoding2, pixel2, maxSize, trans));
                    } else if (tag.equals("attachment")) {
                        String contentType = xpp.getAttributeValue(null, "content-type");
                        String name = xpp.getAttributeValue(null, "name");
                        String charset = xpp.getAttributeValue(null, BluetoothMapContract.MessagePartColumns.CHARSET);
                        String size2 = xpp.getAttributeValue(null, "size");
                        String created = xpp.getAttributeValue(null, "created");
                        String modified = xpp.getAttributeValue(null, "modified");
                        addAttachment(new BipAttachmentFormat(contentType, charset, name, size2, created, modified));
                    } else {
                        warn("Unrecognized tag in x-bt/img-properties object: " + tag);
                    }
                }
            }
        } catch (IOException e) {
            error("I/O error when parsing XML", e);
            throw new ParseException("Failed to parse image-properties from stream");
        } catch (XmlPullParserException e2) {
            error("XML parser error when parsing XML", e2);
            throw new ParseException("Failed to parse image-properties from stream");
        }
    }

    public String getImageHandle() {
        return this.mImageHandle;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public String getFriendlyName() {
        return this.mFriendlyName;
    }

    public ArrayList<BipImageFormat> getNativeFormats() {
        return this.mNativeFormats;
    }

    public ArrayList<BipImageFormat> getVariantFormats() {
        return this.mVariantFormats;
    }

    public ArrayList<BipAttachmentFormat> getAttachments() {
        return this.mAttachments;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addNativeFormat(BipImageFormat format) {
        Objects.requireNonNull(format);
        if (format.getType() != 0) {
            throw new IllegalArgumentException("Format type '" + format.getType() + "' but expected '0'");
        }
        this.mNativeFormats.add(format);
        if (!this.mHasThumbnailFormat && isThumbnailFormat(format)) {
            this.mHasThumbnailFormat = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addVariantFormat(BipImageFormat format) {
        Objects.requireNonNull(format);
        if (format.getType() != 1) {
            throw new IllegalArgumentException("Format type '" + format.getType() + "' but expected '1'");
        }
        this.mVariantFormats.add(format);
        if (!this.mHasThumbnailFormat && isThumbnailFormat(format)) {
            this.mHasThumbnailFormat = true;
        }
    }

    private boolean isThumbnailFormat(BipImageFormat format) {
        BipEncoding encoding;
        BipPixel pixel;
        if (format == null || (encoding = format.getEncoding()) == null || encoding.getType() != 0 || (pixel = format.getPixel()) == null) {
            return false;
        }
        int type = pixel.getType();
        return type != 1 ? type != 2 ? type == 3 && pixel.getMaxWidth() == pixel.getMaxHeight() && pixel.getMaxWidth() >= 200 : pixel.getMaxWidth() >= 200 && pixel.getMaxHeight() >= 200 : pixel.getMaxWidth() == 200 && pixel.getMaxHeight() == 200;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAttachment(BipAttachmentFormat format) {
        Objects.requireNonNull(format);
        this.mAttachments.add(format);
    }

    public String toString() {
        Iterator<BipAttachmentFormat> it;
        Iterator<BipImageFormat> it2;
        StringWriter writer = new StringWriter();
        FastXmlSerializer fastXmlSerializer = new FastXmlSerializer();
        String str = null;
        try {
            fastXmlSerializer.setOutput(writer);
            fastXmlSerializer.startDocument("UTF-8", true);
            fastXmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            fastXmlSerializer.startTag(null, "image-properties");
            if (this.mVersion != null) {
                fastXmlSerializer.attribute(null, "version", this.mVersion);
            }
            if (this.mImageHandle != null) {
                fastXmlSerializer.attribute(null, "handle", this.mImageHandle);
            }
            if (this.mFriendlyName != null) {
                fastXmlSerializer.attribute(null, "friendly-name", this.mFriendlyName);
            }
            for (BipImageFormat format : this.mNativeFormats) {
                BipEncoding encoding = format.getEncoding();
                BipPixel pixel = format.getPixel();
                int size = format.getSize();
                if (encoding == null || pixel == null) {
                    error("Native format " + format.toString() + " is invalid.");
                } else {
                    fastXmlSerializer.startTag(null, AvrcpCoverArtManager.SCHEME_NATIVE);
                    fastXmlSerializer.attribute(null, "encoding", encoding.toString());
                    fastXmlSerializer.attribute(null, "pixel", pixel.toString());
                    if (size >= 0) {
                        fastXmlSerializer.attribute(null, "size", Integer.toString(size));
                    }
                    fastXmlSerializer.endTag(null, AvrcpCoverArtManager.SCHEME_NATIVE);
                }
            }
            Iterator<BipImageFormat> it3 = this.mVariantFormats.iterator();
            while (it3.hasNext()) {
                BipImageFormat format2 = it3.next();
                BipEncoding encoding2 = format2.getEncoding();
                BipPixel pixel2 = format2.getPixel();
                int maxSize = format2.getMaxSize();
                BipTransformation trans = format2.getTransformation();
                if (encoding2 == null) {
                    it2 = it3;
                } else if (pixel2 == null) {
                    it2 = it3;
                } else {
                    fastXmlSerializer.startTag(str, "variant");
                    Iterator<BipImageFormat> it4 = it3;
                    fastXmlSerializer.attribute(null, "encoding", encoding2.toString());
                    fastXmlSerializer.attribute(null, "pixel", pixel2.toString());
                    if (maxSize >= 0) {
                        fastXmlSerializer.attribute(null, "maxsize", Integer.toString(maxSize));
                    }
                    if (trans != null && trans.supportsAny()) {
                        fastXmlSerializer.attribute(null, "transformation", trans.toString());
                    }
                    fastXmlSerializer.endTag(null, "variant");
                    it3 = it4;
                    str = null;
                }
                error("Variant format " + format2.toString() + " is invalid.");
                it3 = it2;
                str = null;
            }
            Iterator<BipAttachmentFormat> it5 = this.mAttachments.iterator();
            while (it5.hasNext()) {
                BipAttachmentFormat format3 = it5.next();
                String contentType = format3.getContentType();
                String charset = format3.getCharset();
                String name = format3.getName();
                int size2 = format3.getSize();
                BipDateTime created = format3.getCreatedDate();
                BipDateTime modified = format3.getModifiedDate();
                if (contentType == null) {
                    it = it5;
                } else if (name == null) {
                    it = it5;
                } else {
                    fastXmlSerializer.startTag(null, "attachment");
                    Iterator<BipAttachmentFormat> it6 = it5;
                    fastXmlSerializer.attribute(null, "content-type", contentType.toString());
                    if (charset != null) {
                        fastXmlSerializer.attribute(null, BluetoothMapContract.MessagePartColumns.CHARSET, charset.toString());
                    }
                    fastXmlSerializer.attribute(null, "name", name.toString());
                    if (size2 >= 0) {
                        fastXmlSerializer.attribute(null, "size", Integer.toString(size2));
                    }
                    if (created != null) {
                        fastXmlSerializer.attribute(null, "created", created.toString());
                    }
                    if (modified != null) {
                        fastXmlSerializer.attribute(null, "modified", modified.toString());
                    }
                    fastXmlSerializer.endTag(null, "attachment");
                    it5 = it6;
                }
                error("Attachment format " + format3.toString() + " is invalid.");
                it5 = it;
            }
            fastXmlSerializer.endTag(null, "image-properties");
            fastXmlSerializer.endDocument();
            return writer.toString();
        } catch (IOException e) {
            error("Falied to serialize ImageProperties", e);
            return null;
        } catch (IllegalArgumentException e2) {
            error("Falied to serialize ImageProperties", e2);
            return null;
        } catch (IllegalStateException e3) {
            error("Falied to serialize ImageProperties", e3);
            return null;
        }
    }

    public byte[] serialize() {
        String s;
        if (!isValid() || (s = toString()) == null) {
            return null;
        }
        try {
            return s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public boolean isValid() {
        return sVersion.equals(this.mVersion) && this.mImageHandle != null && this.mHasThumbnailFormat;
    }

    private static void warn(String msg) {
        Log.w(TAG, msg);
    }

    private static void error(String msg) {
        Log.e(TAG, msg);
    }

    private static void error(String msg, Throwable e) {
        Log.e(TAG, msg, e);
    }
}
