package com.android.bluetooth.map;

import android.util.Log;
import android.util.Xml;
import com.android.bluetooth.DeviceWorkArounds;
import com.android.internal.util.FastXmlSerializer;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMapMessageListing {
    private static final boolean D = false;
    private static final String TAG = "BluetoothMapMessageListing";
    private boolean mHasUnread = false;
    private List<BluetoothMapMessageListingElement> mList = new ArrayList();

    public void add(BluetoothMapMessageListingElement element) {
        this.mList.add(element);
        if (!element.getReadBool()) {
            this.mHasUnread = true;
        }
    }

    public int getCount() {
        List<BluetoothMapMessageListingElement> list = this.mList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public boolean hasUnread() {
        return this.mHasUnread;
    }

    public List<BluetoothMapMessageListingElement> getList() {
        return this.mList;
    }

    public byte[] encode(boolean includeThreadId, String version) throws UnsupportedEncodingException {
        XmlSerializer xmlMsgElement;
        StringWriter sw = new StringWriter();
        boolean isBenzCarkit = DeviceWorkArounds.addressStartsWith(BluetoothMapService.getRemoteDevice().getAddress(), DeviceWorkArounds.MERCEDES_BENZ_CARKIT);
        try {
            if (isBenzCarkit) {
                Log.d(TAG, "java_interop: Remote is Mercedes Benz, using Xml Workaround.");
                xmlMsgElement = Xml.newSerializer();
                xmlMsgElement.setOutput(sw);
                xmlMsgElement.text("\n");
            } else {
                xmlMsgElement = new FastXmlSerializer(0);
                xmlMsgElement.setOutput(sw);
                xmlMsgElement.startDocument("UTF-8", true);
                xmlMsgElement.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            }
            xmlMsgElement.startTag(null, "MAP-msg-listing");
            xmlMsgElement.attribute(null, "version", version);
            for (BluetoothMapMessageListingElement element : this.mList) {
                element.encode(xmlMsgElement, includeThreadId);
            }
            xmlMsgElement.endTag(null, "MAP-msg-listing");
            xmlMsgElement.endDocument();
        } catch (IOException e) {
            Log.w(TAG, e);
        } catch (IllegalArgumentException e2) {
            Log.w(TAG, e2);
        } catch (IllegalStateException e3) {
            Log.w(TAG, e3);
        }
        if (!DeviceWorkArounds.addressStartsWith(BluetoothMapService.getRemoteDevice().getAddress(), DeviceWorkArounds.BREZZA_ZDI_CARKIT)) {
            return sw.toString().getBytes("UTF-8");
        }
        return sw.toString().replaceAll("&amp;", "&").replaceAll("&lt;", "<").replaceAll("&gt;", ">").getBytes("UTF-8");
    }

    public void sort() {
        Collections.sort(this.mList);
    }

    public void segment(int count, int offset) {
        int count2 = Math.min(count, this.mList.size() - offset);
        if (count2 > 0) {
            List<BluetoothMapMessageListingElement> listSubList = this.mList.subList(offset, offset + count2);
            this.mList = listSubList;
            if (listSubList == null) {
                this.mList = new ArrayList();
                return;
            }
            return;
        }
        if (offset > this.mList.size()) {
            this.mList = new ArrayList();
            Log.d(TAG, "offset greater than list size. Returning empty list");
        } else {
            List<BluetoothMapMessageListingElement> list = this.mList;
            this.mList = list.subList(offset, list.size());
        }
    }
}
