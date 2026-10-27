package com.android.bluetooth.avrcpcontroller;

import android.net.INetd;
import android.util.Log;
import com.android.internal.util.FastXmlSerializer;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes.dex */
public class BipImageDescriptor {
    private static final String TAG = "avrcpcontroller.BipImageDescriptor";
    private static final String sVersion = "1.0";
    private BipEncoding mEncoding;
    private int mMaxSize;
    private BipPixel mPixel;
    private int mSize;
    private BipTransformation mTransformation;
    private String mVersion;

    public static class Builder {
        private BipImageDescriptor mImageDescriptor = new BipImageDescriptor();

        public Builder setEncoding(BipEncoding encoding) {
            this.mImageDescriptor.mEncoding = encoding;
            return this;
        }

        public Builder setEncoding(int encoding) {
            this.mImageDescriptor.mEncoding = new BipEncoding(encoding, null);
            return this;
        }

        public Builder setPropietaryEncoding(String encoding) {
            this.mImageDescriptor.mEncoding = new BipEncoding(6, encoding);
            return this;
        }

        public Builder setFixedDimensions(int width, int height) {
            this.mImageDescriptor.mPixel = BipPixel.createFixed(width, height);
            return this;
        }

        public Builder setTransformation(int transformation) {
            this.mImageDescriptor.mTransformation = new BipTransformation(transformation);
            return this;
        }

        public Builder setFileSize(int size) {
            this.mImageDescriptor.mSize = size;
            return this;
        }

        public Builder setMaxFileSize(int size) {
            this.mImageDescriptor.mMaxSize = size;
            return this;
        }

        public BipImageDescriptor build() {
            return this.mImageDescriptor;
        }
    }

    private BipImageDescriptor() {
        this.mVersion = null;
        this.mEncoding = null;
        this.mPixel = null;
        this.mTransformation = null;
        this.mSize = -1;
        this.mMaxSize = -1;
        this.mVersion = sVersion;
    }

    public BipImageDescriptor(InputStream inputStream) throws XmlPullParserException, IOException {
        this.mVersion = null;
        this.mEncoding = null;
        this.mPixel = null;
        this.mTransformation = null;
        this.mSize = -1;
        this.mMaxSize = -1;
        parse(inputStream);
    }

    private void parse(InputStream inputStream) throws XmlPullParserException, IOException {
        try {
            XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
            xpp.setInput(inputStream, "utf-8");
            for (int event = xpp.getEventType(); event != 1; event = xpp.next()) {
                if (event == 2) {
                    String tag = xpp.getName();
                    if (tag.equals("image-descriptor")) {
                        this.mVersion = xpp.getAttributeValue(null, "version");
                    } else if (!tag.equals("image")) {
                        Log.w(TAG, "Unrecognized tag in x-bt/img-Description object: " + tag);
                    } else {
                        this.mEncoding = new BipEncoding(xpp.getAttributeValue(null, "encoding"));
                        this.mPixel = new BipPixel(xpp.getAttributeValue(null, "pixel"));
                        this.mSize = parseInt(xpp.getAttributeValue(null, "size"));
                        this.mMaxSize = parseInt(xpp.getAttributeValue(null, "maxsize"));
                        this.mTransformation = new BipTransformation(xpp.getAttributeValue(null, "transformation"));
                    }
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "I/O error when parsing XML", e);
            throw new ParseException("Failed to parse image-descriptor from stream");
        } catch (XmlPullParserException e2) {
            Log.e(TAG, "XML parser error when parsing XML", e2);
            throw new ParseException("Failed to parse image-descriptor from stream");
        }
    }

    private static int parseInt(String s) {
        if (s == null) {
            return -1;
        }
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            error("Failed to parse '" + s + "'");
            return -1;
        }
    }

    public BipEncoding getEncoding() {
        return this.mEncoding;
    }

    public BipPixel getPixel() {
        return this.mPixel;
    }

    public BipTransformation getTransformation() {
        return this.mTransformation;
    }

    public int getSize() {
        return this.mSize;
    }

    public int getMaxSize() {
        return this.mMaxSize;
    }

    public byte[] serialize() {
        String s = toString();
        if (s != null) {
            try {
                return s.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        }
        return null;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BipImageDescriptor)) {
            return false;
        }
        BipImageDescriptor d = (BipImageDescriptor) o;
        return d.getEncoding() == getEncoding() && d.getPixel() == getPixel() && d.getTransformation() == getTransformation() && d.getSize() == getSize() && d.getMaxSize() == getMaxSize();
    }

    public String toString() {
        if (this.mEncoding == null || this.mPixel == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Missing required fields [ ");
            BipEncoding bipEncoding = this.mEncoding;
            String str = INetd.NEXTHOP_NONE;
            sb.append(bipEncoding == null ? "encoding " : INetd.NEXTHOP_NONE);
            if (this.mPixel == null) {
                str = "pixel ";
            }
            sb.append(str);
            error(sb.toString());
            return null;
        }
        StringWriter writer = new StringWriter();
        FastXmlSerializer fastXmlSerializer = new FastXmlSerializer();
        try {
            fastXmlSerializer.setOutput(writer);
            fastXmlSerializer.startDocument("UTF-8", true);
            fastXmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            fastXmlSerializer.startTag(null, "image-descriptor");
            fastXmlSerializer.attribute(null, "version", sVersion);
            fastXmlSerializer.startTag(null, "image");
            fastXmlSerializer.attribute(null, "encoding", this.mEncoding.toString());
            fastXmlSerializer.attribute(null, "pixel", this.mPixel.toString());
            if (this.mSize != -1) {
                fastXmlSerializer.attribute(null, "size", Integer.toString(this.mSize));
            }
            if (this.mMaxSize != -1) {
                fastXmlSerializer.attribute(null, "maxsize", Integer.toString(this.mMaxSize));
            }
            if (this.mTransformation != null && this.mTransformation.supportsAny()) {
                fastXmlSerializer.attribute(null, "transformation", this.mTransformation.toString());
            }
            fastXmlSerializer.endTag(null, "image");
            fastXmlSerializer.endTag(null, "image-descriptor");
            fastXmlSerializer.endDocument();
            return writer.toString();
        } catch (IOException e) {
            error(e.toString());
            return null;
        } catch (IllegalArgumentException e2) {
            error(e2.toString());
            return null;
        } catch (IllegalStateException e3) {
            error(e3.toString());
            return null;
        }
    }

    private static void error(String msg) {
        Log.e(TAG, msg);
    }
}
