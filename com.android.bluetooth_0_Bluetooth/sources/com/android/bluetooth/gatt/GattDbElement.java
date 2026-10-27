package com.android.bluetooth.gatt;

import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class GattDbElement {
    public static final int TYPE_CHARACTERISTIC = 3;
    public static final int TYPE_DESCRIPTOR = 4;
    public static final int TYPE_INCLUDED_SERVICE = 2;
    public static final int TYPE_PRIMARY_SERVICE = 0;
    public static final int TYPE_SECONDARY_SERVICE = 1;
    public int attributeHandle;
    public int endHandle;
    public int id;
    public int permissions;
    public int properties;
    public int startHandle;
    public int type;
    public UUID uuid;

    public static GattDbElement createPrimaryService(UUID uuid) {
        GattDbElement el = new GattDbElement();
        el.type = 0;
        el.uuid = uuid;
        return el;
    }

    public static GattDbElement createSecondaryService(UUID uuid) {
        GattDbElement el = new GattDbElement();
        el.type = 1;
        el.uuid = uuid;
        return el;
    }

    public static GattDbElement createCharacteristic(UUID uuid, int properties, int permissions) {
        GattDbElement el = new GattDbElement();
        el.type = 3;
        el.uuid = uuid;
        el.properties = properties;
        el.permissions = permissions;
        return el;
    }

    public static GattDbElement createDescriptor(UUID uuid, int permissions) {
        GattDbElement el = new GattDbElement();
        el.type = 4;
        el.uuid = uuid;
        el.permissions = permissions;
        return el;
    }

    public static GattDbElement createIncludedService(int attributeHandle) {
        GattDbElement el = new GattDbElement();
        el.type = 2;
        el.attributeHandle = attributeHandle;
        return el;
    }
}
