/**
 * Project: Neusoft Automotives Navigation
 * Company: Neusoft Automotives
 *
 * (c) Copyright 2018-2021
 *
 * All rights reserved.
 */

package com.neusoft.na.ehp;

import java.lang.reflect.Constructor;

/**
 * Wraps Ehp for any JAVA based system.
 */
public class Ehp
{
   static
   {
      // load native library
      System.loadLibrary("na_serv_ehp");
   }

   /**
    * ID of native service instance. Technically stores a pointer to the C++ object representing
    * the native service.
    */
   private long mNativeInstance = 0;

   /**
    * Creates a new EHP instance.
    */
   public Ehp()
   {
   }

   /**
    * Starts electronic horizon provider. If EHP has already been started before, the call will return with true without
    * altering its state.
    *
    * Since this method blocks until EHP has been started, it is recommended not to call it from a thread
    * which needs to stay responsive, like a GUI thread.
    *
    * @param   configuration EHP configuration in text format (using new lines to separate switches)
    * @return  true, if EHP has been started or was already running, false if starting EHP failed
    */
   public synchronized boolean start( String configuration)
   {
      if ( mNativeInstance != 0)
      {
         return true;
      }
      else
      {
         mNativeInstance = nativeStart( configuration);
         return (mNativeInstance != 0);
      }
   }

   /**
    * Stops electronic horizon provider.
    *
    * @return  true, if EHP has been stopped, false if EHP has not been started.
    */
   public synchronized boolean stop()
   {
      if ( mNativeInstance != 0)
      {
         nativeStop(mNativeInstance);
         mNativeInstance = 0;

         return true;
      }
      else
      {
         return true;
      }
   }

   /**
    * Checks if electronic horizon provider is running.
    *
    * @return true, if EHP is running, otherwise false
    */
   public synchronized boolean isRunning()
   {
      return mNativeInstance != 0;
   }

   /**
    * Gets a specific additional feature from this Ehp instance. The feature is represented by a Java class
    * wrapping its functions. Depending on the build, a feature may be present or not.
    *
    * Currently two different features are supported
    *  - <code>com.neusoft.na.ehp.AndroidManager</code> provides Android specific functions
    *  - <code>com.neusoft.na.ehp.CommunicationTask</code> provides communication functionality provided by the JNI adaptor
    * @param c feature class
    * @return instance of feature (or null if Ehp not started)
    * @throws IllegalArgumentException if <code>c</code> is no Ehp feature class
    */
   public <T> T getFeature( Class<T> c)
   {
      // this code could be partially moved to native code to allow getting rid of the native peer getters
      if ( mNativeInstance != 0)
      {
         long nativePeerId = 0;
         if ( "com.neusoft.na.ehp.AndroidManager".equals( c.getName()))
         {
            nativePeerId = getAndroidManagerPeer( mNativeInstance);
         }
         else if ( "com.neusoft.na.ehp.CommunicationTask".equals( c.getName()))
         {
            nativePeerId = getCommunicationTaskPeer( mNativeInstance);
         }

         if ( nativePeerId != 0)
         {
            try
            {
               Constructor<T> ctor = c.getDeclaredConstructor( long.class);
               T inst = ctor.newInstance( nativePeerId);
               return inst;
            }
            catch (Exception e)
            {
               e.printStackTrace();
               throw new IllegalArgumentException( "Feature not supported: " + c.getName());
            }
         }
      }

      return null;
   }

   /**
    * Runs the navigation. This must be executed in a separate thread as it will block until
    * the native service has stopped.
    *
    * @return instance ID
    */
   private native long nativeStart(String configuration);

   /**
    * Stops the native side of the navigation.
    *
    * @param instanceId ID of the service instance
    */
   private native void nativeStop(long instanceId);

   /**
    * Returns the native peer of the CommunicationTask if available.
    */
   private native long getCommunicationTaskPeer(long instanceId);

   /**
    * Returns the native peer of the AndroidManager if available.
    */
   private native long getAndroidManagerPeer(long instanceId);
}
