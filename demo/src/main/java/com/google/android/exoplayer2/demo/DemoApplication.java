/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.exoplayer2.demo;

import android.app.Application;
import android.content.SharedPreferences;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;

import okhttp3.OkHttpClient;

/**
 * Placeholder application to facilitate overriding Application methods for debugging and testing.
 */
public class DemoApplication extends Application {

  protected String userAgent;
  private static DemoApplication INSTANCE = null;
  private OkHttpClient okHttpClient;

  public static final String DEMO_PREF = "demo_pref";
  public static final String KEY_OFFLINE_OFFSET_ID = "key_offline_offset_id";
  public static final String EMPTY = "";

  @Override
  public void onCreate() {
    super.onCreate();
    INSTANCE = this;
    userAgent = Util.getUserAgent(this, "ExoPlayerDemo");
    okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).build();
    Stetho.initializeWithDefaults(this);
  }

  public static final DemoApplication getAppInstance(){
      return INSTANCE;
  }

  public DataSource.Factory buildDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
    return new DefaultDataSourceFactory(this, bandwidthMeter,
        buildHttpDataSourceFactory(bandwidthMeter));
  }

  public HttpDataSource.Factory buildHttpDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
//    return new DefaultHttpDataSourceFactory(userAgent, bandwidthMeter);
    return new OkHttpDataSourceFactory(okHttpClient,userAgent,bandwidthMeter);
  }

  public boolean useExtensionRenderers() {
    return BuildConfig.FLAVOR.equals("withExtensions");
  }

  public SharedPreferences getSharedPreferences(){
    return getSharedPreferences(DEMO_PREF,MODE_PRIVATE);
  }

}
