package com.example.rashmi.test_volley_singleton;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by QCS2015 on 22-11-2017.
 */

public class NetworkController {
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private static Context mcontext;
    private static NetworkController mInstance;


    private NetworkController(Context context){
        mcontext=context;
        requestQueue=getRequestQueue();
        imageLoader= new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String,Bitmap>
            cache= new LruCache<String, Bitmap>(10);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);

            }
        });
    }
    public static synchronized NetworkController getInstance(Context context){
        if(mInstance==null){
            mInstance = new NetworkController(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(mcontext.getApplicationContext());
        }
        return requestQueue;
    }
    public <T> void addToRequestQueue(Request<T> request){
        requestQueue.add(request);

    }
    public ImageLoader getImageLoader(){
        return imageLoader;
    }
}
