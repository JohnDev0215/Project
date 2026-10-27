/**
 * Project: Neusoft Automotives Navigation
 * Company: Neusoft Automotives
 *
 * (c) Copyright 2016 - 2018
 *
 * All rights reserved.
 */

package com.neusoft.na.ehp;

import android.util.Log;

public final class AndroidManager
{
   public static final String LOGTAG = "AndroidManager";

   private final long mNativeInstance;

   /**
    * Creates a new AndroidManager using the provided native instance.
    * @param nativeInstance native instance
    */
   AndroidManager(long nativeInstance)
   {
      mNativeInstance = nativeInstance;
   }

   // public void setPosition(long timestamp, double lon, double lat, Double alt, Float bearing, Float speed)
   // {
   //    setPosition(mNativeInstance, timestamp, lon, lat, alt, bearing, speed);
   // }

   /**
    * Set System Status to EHP.
    * @param systemStatus <br/>
    *    0 : idle. Idle status: EHP shall not send map message to EHR. <br/>
    *    1 : work. Work status: EHP can send map message to EHR.
    */
   public void setSystemStatus(int systemStatus)
   {
      if (mNativeInstance != 0)
      {
         setSystemStatus(mNativeInstance, systemStatus);
      }
      else
      {
         Log.d(LOGTAG, "[setSystemStatus] mNativeInstance=null");
      }
   }

   public void resetAdasis()
   {
      if (mNativeInstance != 0)
      {
         resetAdasis(mNativeInstance);
      }
      else
      {
         Log.d(LOGTAG, "[resetAdasis] mNativeInstance=null");
      }
   }

   public boolean register(IEhpInfoFetchCallBack callBack)
   {
      if (mNativeInstance != 0)
      {
         nativeRegister(mNativeInstance, callBack);
         return true;
      }
      else
      {
         return false;
      }
   }

   /**
    * Update the current position weather info.
    * @param lon
    *             lon of CCP
    * @param lat
    *             lat of CCP
    * @param timestamp
    * @param weatherInfo
    *             {<br/>
    *                0: Sunny/Clear; 1: Cloudy; 2: Few Clouds; 3: Partly Cloudy; <br/>
    *                4: Overcast; 5: Windy; 6: Calm; 7: Light Breeze; <br/>
    *                8: Moderate/Gentle Breeze; 9: Fresh Breeze; 10: Strong Breeze; 11: High Wind, Near Gale; <br/>
    *                12: Gale; 13: Strong Gale; 14: Storm; 15: Violent Storm; <br/>
    *                16: Hurricane; 17: Tornado; 18: Tropical Storm; 19: Shower Rain; <br/>
    *                20: Heavy Shower Rain; 21: Thundershower; 22: Heavy Thunderstorm; 23: Hail; <br/>
    *                24: Light Rain; 25: Moderate Rain; 26: Heavy Rain; 27: Extreme Rain; <br/>
    *                28: Drizzle Rain; 29: Storm; 30: Heavy Storm; 31: Severe Storm; <br/>
    *                32: Freezing Rain; 33: Light Snow; 34: Moderate Snow; 35: Heavy Snow; <br/>
    *                36: Snowstorm; 37: Sleet; 38: Rain And Snow; 39: Shower Snow; <br/>
    *                40: Snow Flurry; 41: Mist; 42: Foggy; 43: Haze; <br/>
    *                44: Sand; 45: Dust; 46: Duststorm; 47: Sandstorm; <br/>
    *                48: Hot; 49: Cold; <br/>
    *                127: Unknown; <br/>
    *             }
    */
   public void setCCPWeatherInfo(double lon, double lat, long timestamp, int weatherInfo)
   {
      setCCPWeatherInfo(mNativeInstance, lon, lat, timestamp, weatherInfo);
   }

   /**
    * Updates the current position in OneCore.
    *
    * @param instanceId the instance ID of the native peer
    * @param timestamp UTC time of this fix, in milliseconds since January 1, 1970
    * @param lon longitude in degrees
    * @param lat latitude in degrees
    * @param alt altitude in meters above the WGS 84 reference ellipsoid (or {@code null}, if
    *            not available)
    * @param bearing bearing in degrees (or {@code null}, if not available)
    * @param speed speed in meters/second over ground (or {@code null}, if not available)
    */
   // private native void setPosition(long instanceId, long timestamp, double lon, double lat, Double alt, Float bearing, Float speed);

   private native void setSystemStatus(long instanceId, int systemStatus);

   private native void resetAdasis(long instanceId);

   private native void nativeRegister(long instanceId, IEhpInfoFetchCallBack callBack);

   private native void setCCPWeatherInfo(long instanceId, double lon, double lat, long timestamp, int weatherInfo);
}
